package com.dragn0007.dragnlivestock.entities.sheep;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.ai.OAvoidEntityGoal;
import com.dragn0007.dragnlivestock.entities.ai.SheepFollowHerdLeaderGoal;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.entities.util.Taggable;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.items.custom.BrandTagItem;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class OSheep extends Animal implements GeoEntity, Taggable {

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0D, (double)this.getEyeHeight() * 1.5F, (double)(this.getBbWidth() * 1.3F));
		//              ^ Side offset                      ^ Height offset                   ^ Length offset
	}

	public OSheep(EntityType<? extends OSheep> type, Level level) {
		super(type, level);
		setMilked(false);
		setSheared(false);
	}

	protected static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_sheep");
	protected static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/sheep");
	protected static final ResourceLocation TFC_LOOT_TABLE = new ResourceLocation("tfc", "entities/sheep");
	@Override
	public @NotNull ResourceLocation getDefaultLootTable() {
		if (LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
			return VANILLA_LOOT_TABLE;
		}
		if (ModList.get().isLoaded("tfc")) {
			return TFC_LOOT_TABLE;
		}
		return LOOT_TABLE;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 8.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.18F);
	}

	public static final Ingredient FOOD_ITEMS = Ingredient.of(LOTags.Items.O_SHEEP_EATS);

	public void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 2.0F));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(3, new SheepFollowHerdLeaderGoal(this));

		this.goalSelector.addGoal(1, new OAvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 2.0F, 1.8F, livingEntity ->
				livingEntity.getType().is(LOTags.Entity_Types.HERDING_DOGS) && (livingEntity instanceof TamableAnimal && ((TamableAnimal) livingEntity).isTame() && !this.isLeashed())
		));

		this.goalSelector.addGoal(1, new OAvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 2.0F, 1.8F, livingEntity ->
				livingEntity.getType().is(LOTags.Entity_Types.HORSES) && (livingEntity instanceof AbstractHorse && livingEntity.isVehicle()) && !this.isLeashed() && LivestockOverhaulCommonConfig.HORSE_HERD_ANIMALS.get())
		);

		this.goalSelector.addGoal(1, new OAvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 2.0F, 1.8F, livingEntity ->
				livingEntity.getType().is(LOTags.Entity_Types.WOLVES) && (livingEntity instanceof TamableAnimal && !((TamableAnimal) livingEntity).isTame() && !this.isLeashed())
		));
	}
	public OSheep leader;
	public int herdSize = 1;

	public boolean isFollower() {
		return this.leader != null && this.leader.isAlive();
	}

	public OSheep startFollowing(OSheep oSheep) {
		this.leader = oSheep;
		oSheep.addFollower();
		return oSheep;
	}

	public void stopFollowing() {
		if (this.leader != null) {
			this.leader.removeFollower();
			this.leader = null;
		}
	}

	public void addFollower() {
		++this.herdSize;
	}

	public void removeFollower() {
		--this.herdSize;
	}

	public boolean canBeFollowed() {
		return this.hasFollowers() && this.herdSize < this.getMaxHerdSize();
	}

	public boolean isFineQuality() {
		return this.getQuality() <= 25;
	}

	public boolean isGreatQuality() {
		return this.getQuality() > 25 && this.getQuality() <= 50;
	}

	public boolean isFantasticQuality() {
		return this.getQuality() > 50 && this.getQuality() <= 75;
	}

	public boolean isExquisiteQuality() {
		return this.getQuality() > 75 && this.getQuality() <= 100;
	}

	public int replenishMilkCounter = 0;
	public int regrowWoolCounter = 0;

	public void tick() {
		super.tick();
		if (this.hasFollowers() && this.level().random.nextInt(200) == 1) {
			List<? extends OSheep> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
			if (list.size() <= 1) {
				this.herdSize = 1;
			}
		}

		if (babiesBirthed > 0) {
			babyCooldown++;
		}

		if (babiesBirthed >= maxBabyAmount && babyCooldown >= 20) {
			babiesBirthed = 0;
			babyCooldown = 0;
		}

		if (babyCooldown >= 20) {
			babyCooldown = 0;
		}

		if (LivestockOverhaulCommonConfig.QUALITY.get()) {
			if (this.isFineQuality()) {
				if (regrowWoolCounter >= LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get()) {
					this.setSheared(false);
				}
			} else if (this.isGreatQuality()) {
				if (regrowWoolCounter >= (LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get() / 1.3)) {
					this.setSheared(false);
				}
			} else if (this.isFantasticQuality()) {
				if (regrowWoolCounter >= (LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get() / 2)) {
					this.setSheared(false);
				}
			} else if (this.isExquisiteQuality()) {
				if (regrowWoolCounter >= (LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get() / 2.5)) {
					this.setSheared(false);
				}
			}
		} else {
			if (regrowWoolCounter >= LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get()) {
				this.setSheared(false);
			}
		}

		replenishMilkCounter++;

		if (LivestockOverhaulCommonConfig.QUALITY.get()) {
			if (this.isFineQuality()) {
				if (replenishMilkCounter >= LivestockOverhaulCommonConfig.MILKING_COOLDOWN.get()) {
					this.setMilked(false);
				}
			} else if (this.isGreatQuality()) {
				if (replenishMilkCounter >= (LivestockOverhaulCommonConfig.MILKING_COOLDOWN.get() / 1.3)) {
					this.setMilked(false);
				}
			} else if (this.isFantasticQuality()) {
				if (replenishMilkCounter >= (LivestockOverhaulCommonConfig.MILKING_COOLDOWN.get() / 2)) {
					this.setMilked(false);
				}
			} else if (this.isExquisiteQuality()) {
				if (replenishMilkCounter >= (LivestockOverhaulCommonConfig.MILKING_COOLDOWN.get() / 2.5)) {
					this.setMilked(false);
				}
			}
		} else {
			if (replenishMilkCounter >= LivestockOverhaulCommonConfig.MILKING_COOLDOWN.get()) {
				this.setMilked(false);
			}
		}

		if (this.getBreed() == 6) {
			this.setSheared(true);
		}
	}

	public int getMaxHerdSize() {
		return LivestockOverhaulCommonConfig.SHEEP_HERD_MAX.get();
	}

	public boolean hasFollowers() {
		return this.herdSize > 1;
	}

	public boolean inRangeOfLeader() {
		return this.distanceToSqr(this.leader) <= 121.0D;
	}

	public void pathToLeader() {
		if (this.isFollower()) {
			this.getNavigation().moveTo(this.leader, 1.0D);
		}

	}

	public void addFollowers(Stream<? extends OSheep> p_27534_) {
		p_27534_.limit((long)(this.getMaxHerdSize() - this.herdSize)).filter((cow) -> {
			return cow != this;
		}).forEach((cow) -> {
			cow.startFollowing(this);
		});
	}

	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);

		Item item = itemstack.getItem();

		if (item instanceof BrandTagItem && this.isTaggable()) {
			setTagged(true);
			this.playSound(SoundEvents.SHEEP_SHEAR, 0.5f, 1f);
			BrandTagItem tagItem = (BrandTagItem)item;
			DyeColor color = tagItem.getColor();
			if (color != this.getBrandTagColor()) {
				this.setBrandTagColor(color);
				itemstack.shrink(1);
				if (!player.getAbilities().instabuild) {
					itemstack.shrink(1);
					return InteractionResult.sidedSuccess(this.level().isClientSide);
				}
			}
		}

		if (itemstack.is(Items.SHEARS) && player.isShiftKeyDown()) {
			if (this.isTagged()) {
				this.setTagged(false);
				this.playSound(SoundEvents.SHEEP_SHEAR, 0.5f, 1f);
				return InteractionResult.sidedSuccess(this.level().isClientSide);
			}
		}

		if (itemstack.is(LOItems.COAT_OSCILLATOR.get()) && player.getAbilities().instabuild) {
			if (player.isShiftKeyDown()) {
				if (this.getVariant() > 0) {
					this.setVariant(this.getVariant() - 1);
					this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
					return InteractionResult.SUCCESS;
				}
			}
			this.setVariant(this.getVariant() + 1);
			this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		} else if (itemstack.is(LOItems.MARKING_OSCILLATOR.get()) && player.getAbilities().instabuild) {
			if (player.isShiftKeyDown()) {
				if (this.getOverlayVariant() > 0) {
					this.setOverlayVariant(this.getOverlayVariant() - 1);
					this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
					return InteractionResult.sidedSuccess(this.level().isClientSide);
				}
			}
			this.setOverlayVariant(this.getOverlayVariant() + 1);
			this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		} else if (itemstack.is(LOItems.BREED_OSCILLATOR.get()) && player.getAbilities().instabuild) {
			if (player.isShiftKeyDown()) {
				if (this.getBreed() > 0) {
					this.setBreed(this.getBreed() - 1);
					this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
					return InteractionResult.SUCCESS;
				}
			}
			SheepBreed.Breed currentBreed = SheepBreed.Breed.values()[this.getBreed()];
			SheepBreed.Breed nextBreed = currentBreed.next();
			this.setBreed(nextBreed.ordinal());
			this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
			return InteractionResult.SUCCESS;
		}

		if (itemstack.is(Items.SHEARS) && !player.isShiftKeyDown() && !this.isBaby() &&
				(!isSheared() || regrowWoolCounter >= LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get()) && !(this.getBreed() == 6)) {
			player.playSound(SoundEvents.SHEEP_SHEAR, 1.0F, 1.0F);
			this.setSheared(true);
			this.dropWoolByColorAndMarking();
			regrowWoolCounter = 0;
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		}

		if (itemstack.is(LOItems.GENDER_TEST_STRIP.get()) && this.isFemale()) {
			player.playSound(SoundEvents.BEEHIVE_EXIT, 1.0F, 1.0F);
			ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, LOItems.FEMALE_GENDER_TEST_STRIP.get().getDefaultInstance());
			player.setItemInHand(hand, itemstack1);
			return InteractionResult.SUCCESS;
		}

		if (itemstack.is(LOItems.GENDER_TEST_STRIP.get()) && this.isMale()) {
			player.playSound(SoundEvents.BEEHIVE_EXIT, 1.0F, 1.0F);
			ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, LOItems.MALE_GENDER_TEST_STRIP.get().getDefaultInstance());
			player.setItemInHand(hand, itemstack1);
			return InteractionResult.SUCCESS;
		}

		if (itemstack.is(Items.BUCKET) && !this.isBaby()) {
			if (!wasMilked()) {
				if ((!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get()) || (LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() && this.isFemale())) {
					player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
					ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, LOItems.SHEEP_MILK_BUCKET.get().getDefaultInstance());
					player.setItemInHand(hand, itemstack1);
					replenishMilkCounter = 0;
					setMilked(true);
				}
				return InteractionResult.sidedSuccess(this.level().isClientSide);
			}
		}

		return super.mobInteract(player, hand);
	}

	@Override
	public float getStepHeight() {
		return 1F;
	}

	protected final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	protected <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
		double currentSpeed = this.getDeltaMovement().lengthSqr();
		double speedThreshold = 0.01;

		AnimationController<T> controller = tAnimationState.getController();

		if (tAnimationState.isMoving()) {
			if (currentSpeed > speedThreshold) {
				controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
			} else {
				controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(1.4);
			}
		} else {
			controller.setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
		}

		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "controller", 2, this::predicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}

	public SoundEvent getAmbientSound() {
		return SoundEvents.SHEEP_AMBIENT;
	}

	public SoundEvent getDeathSound() {
		return SoundEvents.SHEEP_DEATH;
	}

	public SoundEvent getHurtSound(DamageSource p_30720_) {
		return SoundEvents.SHEEP_HURT;
	}

	public void playStepSound(BlockPos p_28254_, BlockState p_28255_) {
		this.playSound(SoundEvents.SHEEP_STEP, 0.15F, 1.0F);
	}

	public boolean isFood(ItemStack p_28271_) {
		return FOOD_ITEMS.test(p_28271_);
	}


	public static final EntityDataAccessor<Integer> BREED = SynchedEntityData.defineId(OSheep.class, EntityDataSerializers.INT);
	public int getBreedLocation() {
		return SheepBreed.Breed.values().length;
	}
	public int getBreed() {
		return this.entityData.get(BREED);
	}
	public void setBreed(int breed) {
		this.entityData.set(BREED, breed);
	}

	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OSheep.class, EntityDataSerializers.INT);
	public ResourceLocation getTextureLocation() {
		return OSheepModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
	}
	public int getVariant() {
		return this.entityData.get(VARIANT);
	}
	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
	}


	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(OSheep.class, EntityDataSerializers.INT);
	public ResourceLocation getOverlayLocation() {return OSheepMarkingLayer.Overlay.overlayFromOrdinal(getOverlayVariant()).resourceLocation;}
	public int getOverlayVariant() {
		return this.entityData.get(OVERLAY);
	}
	public void setOverlayVariant(int overlayVariant) {
		this.entityData.set(OVERLAY, overlayVariant);
	}

	public static final EntityDataAccessor<Integer> WOOL_COLOR = SynchedEntityData.defineId(OSheep.class, EntityDataSerializers.INT);
	public ResourceLocation getWoolLocation() {
		return OSheepWoolLayer.Overlay.overlayFromOrdinal(getWoolVariant()).resourceLocation;
	}
	public int getWoolVariant() {
		return this.entityData.get(WOOL_COLOR);
	}
	public void setWoolVariant(int overlayVariant) {
		this.entityData.set(WOOL_COLOR, overlayVariant);
	}

	public static final EntityDataAccessor<Integer> HORN_TYPE = SynchedEntityData.defineId(OSheep.class, EntityDataSerializers.INT);
	public int getHornVariant() {
		return this.entityData.get(HORN_TYPE);
	}
	public void setHornVariant(int hornVariant) {
		this.entityData.set(HORN_TYPE, hornVariant);
	}

	protected static final EntityDataAccessor<Integer> BRAND_TAG_COLOR = SynchedEntityData.defineId(OSheep.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> TAGGED = SynchedEntityData.defineId(OSheep.class, EntityDataSerializers.BOOLEAN);
	public DyeColor getBrandTagColor() {
		return DyeColor.byId(this.entityData.get(BRAND_TAG_COLOR));
	}
	public void setBrandTagColor(DyeColor color) {
		this.entityData.set(BRAND_TAG_COLOR, color.getId());
	}
	@Override
	public boolean isTaggable() {
		return this.isAlive() && !this.isBaby();
	}
	@Override
	public boolean isTagged() {
		return this.entityData.get(TAGGED);
	}
	public void setTagged(boolean tagged) {
		this.entityData.set(TAGGED, tagged);
	}
	@Override
	public void equipTag(@javax.annotation.Nullable SoundSource soundSource) {
		if(soundSource != null) {
			this.level().playSound(null, this, SoundEvents.BOOK_PAGE_TURN, soundSource, 0.5f, 1f);
		}
	}

	public static final EntityDataAccessor<Boolean> SHEARED = SynchedEntityData.defineId(OSheep.class, EntityDataSerializers.BOOLEAN);
	public boolean isSheared() {
		return this.entityData.get(SHEARED);
	}
	public void setSheared(boolean sheared) {
		this.entityData.set(SHEARED, sheared);
	}

	public static final EntityDataAccessor<Boolean> MILKED = SynchedEntityData.defineId(OSheep.class, EntityDataSerializers.BOOLEAN);
	public boolean wasMilked() {
		return this.entityData.get(MILKED);
	}
	public void setMilked(boolean milked) {
		this.entityData.set(MILKED, milked);
	}

	public static final EntityDataAccessor<Integer> QUALITY = SynchedEntityData.defineId(OSheep.class, EntityDataSerializers.INT);
	public int getQuality() {
		return this.entityData.get(QUALITY);
	}
	public void setQuality(int i) {
		this.entityData.set(QUALITY, i);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		if(tag.contains("Quality")) {
			this.setQuality(tag.getInt("Quality"));
		}

		if (tag.contains("Breed")) {
			setBreed(tag.getInt("Breed"));
		}

		if (tag.contains("Variant")) {
			setVariant(tag.getInt("Variant"));
		}

		if (tag.contains("Overlay")) {
			setOverlayVariant(tag.getInt("Overlay"));
		}

		if (tag.contains("Wool")) {
			setWoolVariant(tag.getInt("Wool"));
		}

		if (tag.contains("HornType")) {
			setHornVariant(tag.getInt("HornType"));
		}

		if (tag.contains("Gender")) {
			this.setGender(tag.getInt("Gender"));
		}

		if (tag.contains("Milked")) {
			this.setMilked(tag.getBoolean("Milked"));
		}

		if (tag.contains("MilkedTime")) {
			this.replenishMilkCounter = tag.getInt("MilkedTime");
		}

		if (tag.contains("Sheared")) {
			this.setSheared(tag.getBoolean("Sheared"));
		}

		if (tag.contains("ShearedTime")) {
			this.regrowWoolCounter = tag.getInt("ShearedTime");
		}

		if(tag.contains("Tagged")) {
			this.setTagged(tag.getBoolean("Tagged"));
		}

		this.setBrandTagColor(DyeColor.byId(tag.getInt("BrandTagColor")));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Quality", this.getQuality());
		tag.putInt("Breed", getBreed());
		tag.putInt("Variant", getVariant());
		tag.putInt("Overlay", getOverlayVariant());
		tag.putInt("Wool", getWoolVariant());
		tag.putInt("HornType", getHornVariant());
		tag.putInt("Gender", this.getGender());
		tag.putBoolean("Sheared", this.isSheared());
		tag.putInt("ShearedTime", this.regrowWoolCounter);
		tag.putBoolean("Milked", this.wasMilked());
		tag.putInt("MilkedTime", this.replenishMilkCounter);
		tag.putBoolean("Tagged", this.isTagged());
		tag.putByte("BrandTagColor", (byte)this.getBrandTagColor().getId());
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(QUALITY, 0);
		this.entityData.define(BREED, 0);
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
		this.entityData.define(WOOL_COLOR, 0);
		this.entityData.define(HORN_TYPE, 0);
		this.entityData.define(GENDER, 0);
		this.entityData.define(BRAND_TAG_COLOR, DyeColor.YELLOW.getId());
		this.entityData.define(TAGGED, false);
		this.entityData.define(SHEARED, false);
		this.entityData.define(MILKED, false);
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}
		Random random = new Random();

		this.setBreed(random.nextInt(SheepBreed.Breed.values().length));
		this.setGender(random.nextInt(OSheep.Gender.values().length));

		if (LivestockOverhaulCommonConfig.QUALITY.get()) {
			this.setQuality(random.nextInt(30));
		}

		if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
			this.setColorByBreed();
			this.setWoolColorByBreed();
			this.setMarkingByBreed();
			this.setHornsByBreed();
		} else {
			this.setVariant(random.nextInt(OSheepModel.Variant.values().length));
			this.setOverlayVariant(random.nextInt(OSheepMarkingLayer.Overlay.values().length));
			this.setWoolVariant(random.nextInt(OSheepWoolLayer.Overlay.values().length));
			this.setHornVariant(random.nextInt(BreedHorns.values().length));
		}

		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	public enum Gender {
		FEMALE,
		MALE
	}
	public boolean isFemale() {
		return this.getGender() == 0;
	}
	public boolean isMale() {
		return this.getGender() == 1;
	}
	public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(OSheep.class, EntityDataSerializers.INT);
	public int getGender() {
		return this.entityData.get(GENDER);
	}
	public void setGender(int gender) {
		this.entityData.set(GENDER, gender);
	}
	public boolean canParent() {
		return !this.isBaby() && this.isInLove();
	}

	public boolean canMate(Animal animal) {
		if (animal == this) {
			return false;
		} else if (!(animal instanceof OSheep)) {
			return false;
		} else {
			if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
				return this.canParent() && ((OSheep) animal).canParent();
			} else {
				OSheep partner = (OSheep) animal;
				if (this.canParent() && partner.canParent() && this.getGender() != partner.getGender()) {
					return isFemale();
				}
			}
		}
		return false;
	}

	public int maxBabyAmount = 2;
	public int babiesBirthed = 0;
	public int babyCooldown = 0;

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		OSheep lamb;
		OSheep partner = (OSheep) ageableMob;
		lamb = EntityTypes.O_SHEEP_ENTITY.get().create(serverLevel);

		int breedChance = this.random.nextInt(5);
		int breed;
		if (breedChance == 0) {
			breed = this.random.nextInt(SheepBreed.Breed.values().length);
		} else {
			breed = (this.random.nextInt(2) == 0) ? this.getBreed() : partner.getBreed();
		}
		lamb.setBreed(breed);

		if (!(breedChance == 0)) {
			int variantChance = this.random.nextInt(14);
			int variant;
			if (variantChance < 6) {
				variant = this.getVariant();
			} else if (variantChance < 12) {
				variant = partner.getVariant();
			} else {
				variant = this.random.nextInt(OSheepModel.Variant.values().length);
			}
			lamb.setVariant(variant);
		} else if (random.nextDouble() < 0.5) {
			lamb.setColorByBreed();
		}

		if (!(breedChance == 0)) {
			int overlayChance = this.random.nextInt(10);
			int overlay;
			if (overlayChance < 4) {
				overlay = this.getOverlayVariant();
			} else if (overlayChance < 8) {
				overlay = partner.getOverlayVariant();
			} else {
				overlay = this.random.nextInt(OSheepMarkingLayer.Overlay.values().length);
			}
			lamb.setOverlayVariant(overlay);
		} else if (random.nextDouble() < 0.5) {
			lamb.setMarkingByBreed();
		}

		if (!(breedChance == 0)) {
			int woolColorChance = this.random.nextInt(10);
			int woolColor;
			if (woolColorChance < 4) {
				woolColor = this.getWoolVariant();
			} else if (woolColorChance < 8) {
				woolColor = partner.getWoolVariant();
			} else {
				woolColor = this.random.nextInt(OSheepWoolLayer.Overlay.values().length);
			}
			lamb.setWoolVariant(woolColor);
		} else if (random.nextDouble() < 0.5) {
			lamb.setWoolColorByBreed();
		}

		if (!(breedChance == 0)) {
			int hornsChance = this.random.nextInt(10);
			int hornType;
			if (hornsChance < 4) {
				hornType = this.getHornVariant();
			} else if (hornsChance < 8) {
				hornType = partner.getHornVariant();
			} else {
				hornType = this.random.nextInt(OSheep.BreedHorns.values().length);
			}
			lamb.setHornVariant(hornType);
		} else if (random.nextDouble() < 0.5) {
			lamb.setHornsByBreed();
		}

		lamb.setGender(random.nextInt(Gender.values().length));

		babiesBirthed++;

		if (babiesBirthed < maxBabyAmount && this.isInLove()) {
			if (random.nextDouble() <= 0.30) {
				spawnChildFromBreeding(serverLevel, partner);
				babiesBirthed++;
			}
		}

		return lamb;
	}

	@Override
	public void dropCustomDeathLoot(DamageSource p_33574_, int p_33575_, boolean p_33576_) {
		super.dropCustomDeathLoot(p_33574_, p_33575_, p_33576_);
		Random random = new Random();

		if (!this.isSheared() || this.getBreed() == 6) {
			this.dropWoolByColorAndMarking();
		}

		if (LivestockOverhaulCommonConfig.QUALITY.get()) {
			if (this.isExquisiteQuality()) {
				this.spawnAtLocation(Items.MUTTON, 3);
				this.spawnAtLocation(LOItems.MUTTON_RIB.get(), 3);
				this.spawnAtLocation(LOItems.MUTTON_LOIN.get(), 3);
				if (!this.isSheared() || this.getBreed() == 6) {
					this.dropWoolByColorAndMarking();
				} else {
					this.spawnAtLocation(Items.LEATHER, 3);
				}
			} else if (this.isFantasticQuality()) {
				this.spawnAtLocation(Items.MUTTON, 2);
				this.spawnAtLocation(LOItems.MUTTON_RIB.get(), 2);
				this.spawnAtLocation(LOItems.MUTTON_LOIN.get(), 2);
				if (!this.isSheared() || this.getBreed() == 6) {
					this.dropWoolByColorAndMarking();
				} else {
					this.spawnAtLocation(Items.LEATHER, 2);
				}
			} else if (this.isGreatQuality()) {
				this.spawnAtLocation(Items.MUTTON);
				this.spawnAtLocation(LOItems.MUTTON_RIB.get());
				this.spawnAtLocation(LOItems.MUTTON_LOIN.get());
				if (!this.isSheared() || this.getBreed() == 6) {
					this.dropWoolByColorAndMarking();
				} else {
					this.spawnAtLocation(Items.LEATHER);
				}
			}
		}

		if (!LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get() || !ModList.get().isLoaded("tfc")) {

		}

	}

	public void dropWoolByColorAndMarking() {

		//skin color generally does not affect dropped wool color, just the wool variant.
		//however, breed can affect wool and skin color

		if (!(this.getBreed() == 6)) {
			if (!LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
				if (this.getWoolVariant() == 0 && !(this.getOverlayVariant() == 3)) {
					if (random.nextDouble() < 0.20) {
						this.spawnAtLocation(LOItems.BLACK_WOOL_STAPLE.get(), 3);
					} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(LOItems.BLACK_WOOL_STAPLE.get(), 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(LOItems.BLACK_WOOL_STAPLE.get(), 1);
					}

					if (this.getOverlayVariant() == 0) {
						this.spawnAtLocation(LOItems.BLACK_WOOL_STAPLE.get(), 1);
					}
				}

				if (this.getWoolVariant() == 1 && !(this.getOverlayVariant() == 3)) {
					if (random.nextDouble() < 0.20) {
						this.spawnAtLocation(LOItems.BROWN_WOOL_STAPLE.get(), 3);
					} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(LOItems.BROWN_WOOL_STAPLE.get(), 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(LOItems.BROWN_WOOL_STAPLE.get(), 1);
					}

					if (this.getOverlayVariant() == 0) {
						this.spawnAtLocation(LOItems.BROWN_WOOL_STAPLE.get(), 1);
					}
				}

				if (this.getWoolVariant() == 2 && !(this.getOverlayVariant() == 3)) {
					if (random.nextDouble() < 0.20) {
						this.spawnAtLocation(LOItems.GREY_WOOL_STAPLE.get(), 3);
					} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(LOItems.GREY_WOOL_STAPLE.get(), 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(LOItems.GREY_WOOL_STAPLE.get(), 1);
					}

					if (this.getOverlayVariant() == 0) {
						this.spawnAtLocation(LOItems.GREY_WOOL_STAPLE.get(), 1);
					}
				}

				if (this.getWoolVariant() == 3 && !(this.getOverlayVariant() == 3)) {
					if (random.nextDouble() < 0.20) {
						this.spawnAtLocation(LOItems.LIGHT_GREY_WOOL_STAPLE.get(), 3);
					} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(LOItems.LIGHT_GREY_WOOL_STAPLE.get(), 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(LOItems.LIGHT_GREY_WOOL_STAPLE.get(), 1);
					}

					if (this.getOverlayVariant() == 0) {
						this.spawnAtLocation(LOItems.LIGHT_GREY_WOOL_STAPLE.get(), 1);
					}
				}

				if (this.getWoolVariant() == 4 && !(this.getOverlayVariant() == 3)) {
					if (random.nextDouble() < 0.20) {
						this.spawnAtLocation(LOItems.BROWN_WOOL_STAPLE.get(), 3);
					} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(LOItems.BROWN_WOOL_STAPLE.get(), 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(LOItems.BROWN_WOOL_STAPLE.get(), 1);
					}

					if (this.getOverlayVariant() == 0) {
						this.spawnAtLocation(LOItems.BROWN_WOOL_STAPLE.get(), 1);
					}
				}

				if (this.getWoolVariant() == 5 && !(this.getOverlayVariant() == 3)) {
					if (random.nextDouble() < 0.20) {
						this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 3);
					} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 1);
					}

					if (this.getOverlayVariant() == 0) {
						this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 1);
					}
				}


				if (this.getOverlayVariant() == 1 || this.getOverlayVariant() == 7 || this.getOverlayVariant() == 9 ||
						this.getOverlayVariant() == 10  || this.getOverlayVariant() == 12 || this.getOverlayVariant() == 13 ||
						this.getOverlayVariant() == 14 || this.getOverlayVariant() == 15) {
					if (random.nextDouble() < 0.20) {
						this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 1);
					}
				}

				if (this.getOverlayVariant() == 4 || this.getOverlayVariant() == 5 || this.getOverlayVariant() == 6 || this.getOverlayVariant() == 6) {
					if (random.nextDouble() < 0.20) {
						this.spawnAtLocation(LOItems.BLACK_WOOL_STAPLE.get(), 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(LOItems.BLACK_WOOL_STAPLE.get(), 1);
					}
				}


				if (this.getOverlayVariant() == 3) { //pure white, only drop white wool
					if (random.nextDouble() < 0.20) {
						this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 3);
					} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 1);
					}

					this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 1);
				}


				//if vanilla loot
			} else {
				if (this.getWoolVariant() == 0 && !(this.getOverlayVariant() == 3)) {
					if (random.nextDouble() < 0.20) {
						this.spawnAtLocation(Items.BLACK_WOOL, 3);
					} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(Items.BLACK_WOOL, 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(Items.BLACK_WOOL, 1);
					}

					if (this.getOverlayVariant() == 0) {
						this.spawnAtLocation(Items.BLACK_WOOL, 1);
					}
				}

				if (this.getWoolVariant() == 1 && !(this.getOverlayVariant() == 3)) {
					if (random.nextDouble() < 0.20) {
						this.spawnAtLocation(Items.BROWN_WOOL, 3);
					} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(Items.BROWN_WOOL, 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(Items.BROWN_WOOL, 1);
					}

					if (this.getOverlayVariant() == 0) {
						this.spawnAtLocation(Items.BROWN_WOOL, 1);
					}
				}

				if (this.getWoolVariant() == 2 && !(this.getOverlayVariant() == 3)) {
					if (random.nextDouble() < 0.20) {
						this.spawnAtLocation(Items.GRAY_WOOL, 3);
					} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(Items.GRAY_WOOL, 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(Items.GRAY_WOOL, 1);
					}

					if (this.getOverlayVariant() == 0) {
						this.spawnAtLocation(Items.GRAY_WOOL, 1);
					}
				}

				if (this.getWoolVariant() == 3 && !(this.getOverlayVariant() == 3)) {
					if (random.nextDouble() < 0.20) {
						this.spawnAtLocation(Items.LIGHT_GRAY_WOOL, 3);
					} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(Items.LIGHT_GRAY_WOOL, 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(Items.LIGHT_GRAY_WOOL, 1);
					}

					if (this.getOverlayVariant() == 0) {
						this.spawnAtLocation(Items.LIGHT_GRAY_WOOL, 1);
					}
				}

				if (this.getWoolVariant() == 4 && !(this.getOverlayVariant() == 3)) {
					if (random.nextDouble() < 0.20) {
						this.spawnAtLocation(Items.BROWN_WOOL, 3);
					} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(Items.BROWN_WOOL, 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(Items.BROWN_WOOL, 1);
					}

					if (this.getOverlayVariant() == 0) {
						this.spawnAtLocation(Items.BROWN_WOOL, 1);
					}
				}

				if (this.getWoolVariant() == 5 && !(this.getOverlayVariant() == 3)) {
					if (random.nextDouble() < 0.20) {
						this.spawnAtLocation(Items.WHITE_WOOL, 3);
					} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(Items.WHITE_WOOL, 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(Items.WHITE_WOOL, 1);
					}

					if (this.getOverlayVariant() == 0) {
						this.spawnAtLocation(Items.WHITE_WOOL, 1);
					}
				}

				if (this.getOverlayVariant() == 1 || this.getOverlayVariant() == 7 || this.getOverlayVariant() == 9 ||
						this.getOverlayVariant() == 10  || this.getOverlayVariant() == 12 || this.getOverlayVariant() == 13 ||
						this.getOverlayVariant() == 14 || this.getOverlayVariant() == 15) {
					if (random.nextDouble() < 0.20) {
						this.spawnAtLocation(Items.WHITE_WOOL, 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(Items.WHITE_WOOL, 1);
					}
				}

				if (this.getOverlayVariant() == 4 || this.getOverlayVariant() == 5 || this.getOverlayVariant() == 6) {
					if (random.nextDouble() < 0.20) {
						this.spawnAtLocation(Items.BLACK_WOOL, 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(Items.BLACK_WOOL, 1);
					}
				}


				if (this.getOverlayVariant() == 3) { //pure white, only drop white wool
					if (random.nextDouble() < 0.20) {
						this.spawnAtLocation(Items.WHITE_WOOL, 3);
					} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(Items.WHITE_WOOL, 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(Items.WHITE_WOOL, 1);
					}

					this.spawnAtLocation(Items.WHITE_WOOL, 1);
				}
			}
		} else {
			if (random.nextDouble() < 0.50) {
				this.spawnAtLocation(Items.LEATHER, 2);
			} else if (random.nextDouble() > 0.50) {
				this.spawnAtLocation(Items.LEATHER, 1);
			}
		}

	}

	public void setColorByBreed() {

		if (this.getBreed() == 0) { //gulf coast tend to come with white or tan skin
			if (random.nextDouble() < 0.05) {
				this.setVariant(random.nextInt(OSheepModel.Variant.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {4, 5};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 1) { //norfolk tend to come with black skin
			if (random.nextDouble() < 0.05) {
				this.setVariant(random.nextInt(OSheepModel.Variant.values().length));
			} else if (random.nextDouble() > 0.05) {
				this.setVariant(0);
			}
		}

		if (this.getBreed() == 2) { //dorset tend to come with white skin
			if (random.nextDouble() < 0.05) {
				this.setVariant(random.nextInt(OSheepModel.Variant.values().length));
			} else if (random.nextDouble() > 0.05) {
				this.setVariant(5);
			}
		}

		if (this.getBreed() == 3) { //jacob tend to come with black or brown skin
			if (random.nextDouble() < 0.05) {
				this.setVariant(random.nextInt(OSheepModel.Variant.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {0, 2};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 4) { //racka tend to come with black, tan or white skin
			if (random.nextDouble() < 0.05) {
				this.setVariant(random.nextInt(OSheepModel.Variant.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {0, 4, 5};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 5) { //california red tend to come with red skin
			if (random.nextDouble() < 0.05) {
				this.setVariant(random.nextInt(OSheepModel.Variant.values().length));
			} else if (random.nextDouble() > 0.05) {
				this.setVariant(3);
			}
		}

		if (this.getBreed() == 6) { //hair come in mahogany, cream, doberman, chocolate and light/ dark
			if (random.nextDouble() < 0.15) {
				this.setVariant(random.nextInt(OSheepModel.Variant.values().length));
			} else if (random.nextDouble() > 0.15) {
				int[] variants = {6, 7, 8, 9, 10, 11};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

	}

	public void setWoolColorByBreed() {

		if (this.getBreed() == 0) { //gulf coast tend to come with white wool
			if (random.nextDouble() < 0.05) {
				this.setWoolVariant(random.nextInt(OSheepWoolLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05) {
				this.setWoolVariant(5);
			}
		}

		if (this.getBreed() == 1) { //norfolk tend to come with white wool
			if (random.nextDouble() < 0.05) {
				this.setWoolVariant(random.nextInt(OSheepWoolLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05) {
				this.setWoolVariant(5);
			}
		}

		if (this.getBreed() == 2) { //dorset tend to come with white, tan or brown wool
			if (random.nextDouble() < 0.05) {
				this.setWoolVariant(random.nextInt(OSheepWoolLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {1, 4, 5};
				int randomIndex = new Random().nextInt(variants.length);
				this.setWoolVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 3) { //jacob tend to come with black, white, tan or brown wool
			if (random.nextDouble() < 0.05) {
				this.setWoolVariant(random.nextInt(OSheepWoolLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {0, 1, 4, 5};
				int randomIndex = new Random().nextInt(variants.length);
				this.setWoolVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 4) { //racka tend to come with black, white, or tan wool
			if (random.nextDouble() < 0.05) {
				this.setWoolVariant(random.nextInt(OSheepWoolLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {0, 4, 5};
				int randomIndex = new Random().nextInt(variants.length);
				this.setWoolVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 5) { //california red tend to come with brown, white, or tan wool
			if (random.nextDouble() < 0.05) {
				this.setWoolVariant(random.nextInt(OSheepWoolLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {1, 4, 5};
				int randomIndex = new Random().nextInt(variants.length);
				this.setWoolVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 6) { //hair sheep dont come with wool
			this.setWoolVariant(6);
		}

	}

	public void setMarkingByBreed() {

		if (this.getBreed() == 0) { //gulf coast don't often come with markings, and if they do, theyre small
			if (random.nextDouble() < 0.05) {
				this.setOverlayVariant(random.nextInt(OSheepMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05 && random.nextDouble() < 0.20) {
				int[] variants = {2, 8};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.20) {
				this.setOverlayVariant(0);
			}
		}

		if (this.getBreed() == 1) { //norfolk don't often come with markings, and if they do, theyre small
			if (random.nextDouble() < 0.05) {
				this.setOverlayVariant(random.nextInt(OSheepMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05 && random.nextDouble() < 0.20) {
				int[] variants = {2, 8};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.20) {
				this.setOverlayVariant(0);
			}
		}

		if (this.getBreed() == 2) { //dorset don't often come with markings, and if they do, theyre small
			if (random.nextDouble() < 0.05) {
				this.setOverlayVariant(random.nextInt(OSheepMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05 && random.nextDouble() < 0.20) {
				int[] variants = {2, 8};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.20) {
				this.setOverlayVariant(0);
			}
		}

		if (this.getBreed() == 3) { //jacob come with all sorts of different patterns
			this.setOverlayVariant(random.nextInt(OSheepMarkingLayer.Overlay.values().length));
		}

		if (this.getBreed() == 4) { //racka don't often come with markings, and if they do, theyre small
			if (random.nextDouble() < 0.05) {
				this.setOverlayVariant(random.nextInt(OSheepMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05 && random.nextDouble() < 0.20) {
				int[] variants = {2, 8};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.20) {
				this.setOverlayVariant(0);
			}
		}

		if (this.getBreed() == 5) { //california red don't often come with markings, and if they do, theyre small
			if (random.nextDouble() < 0.05) {
				this.setOverlayVariant(random.nextInt(OSheepMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05 && random.nextDouble() < 0.20) {
				int[] variants = {2, 8};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.20) {
				this.setOverlayVariant(0);
			}
		}

		if (this.getBreed() == 6) { //hair come in the fancy markings and roan
			if (random.nextDouble() < 0.15) {
				this.setOverlayVariant(random.nextInt(OSheepMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.15) {
				int[] variants = {0, 11, 12, 13, 14, 15, 16};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			}
		}

	}

	public void setHornsByBreed() {

		if (this.getBreed() == 0) { //gulf coast can come with small, curly horns. usually males, but sometimes females too
			if (this.isFemale()) {
				if (random.nextDouble() < 0.02) {
					this.setHornVariant(random.nextInt(BreedHorns.values().length));
				} else if (random.nextDouble() > 0.02 && random.nextDouble() < 0.30) {
					this.setHornVariant(1);
				} else if (random.nextDouble() > 0.30) {
					this.setHornVariant(0);
				}
			} else if (this.isMale()) {
				if (random.nextDouble() < 0.02) {
					this.setHornVariant(random.nextInt(BreedHorns.values().length));
				} else if (random.nextDouble() > 0.02 && random.nextDouble() < 0.15) {
					this.setHornVariant(0);
				} else if (random.nextDouble() > 0.15) {
					this.setHornVariant(1);
				}
			}
		}

		if (this.getBreed() == 1) { //norfolk can come with large, curly horns. usually males, but sometimes females too
			if (this.isFemale()) {
				if (random.nextDouble() < 0.02) {
					this.setHornVariant(random.nextInt(BreedHorns.values().length));
				} else if (random.nextDouble() > 0.02 && random.nextDouble() < 0.30) {
					this.setHornVariant(2);
				} else if (random.nextDouble() > 0.30) {
					this.setHornVariant(0);
				}
			} else if (this.isMale()) {
				if (random.nextDouble() < 0.02) {
					this.setHornVariant(random.nextInt(BreedHorns.values().length));
				} else if (random.nextDouble() > 0.02 && random.nextDouble() < 0.15) {
					this.setHornVariant(0);
				} else if (random.nextDouble() > 0.15) {
					this.setHornVariant(2);
				}
			}
		}

		if (this.getBreed() == 2) { //dorset can come with large, curly horns. usually males, but sometimes females too
			if (this.isFemale()) {
				if (random.nextDouble() < 0.02) {
					this.setHornVariant(random.nextInt(BreedHorns.values().length));
				} else if (random.nextDouble() > 0.02 && random.nextDouble() < 0.30) {
					this.setHornVariant(3);
				} else if (random.nextDouble() > 0.30) {
					this.setHornVariant(0);
				}
			} else if (this.isMale()) {
				if (random.nextDouble() < 0.02) {
					this.setHornVariant(random.nextInt(BreedHorns.values().length));
				} else if (random.nextDouble() > 0.02 && random.nextDouble() < 0.15) {
					this.setHornVariant(0);
				} else if (random.nextDouble() > 0.15) {
					this.setHornVariant(3);
				}
			}
		}

		if (this.getBreed() == 3) { //jacob can come with two sets of horns. usually males, but sometimes females too
			if (this.isFemale()) {
				if (random.nextDouble() < 0.02) {
					this.setHornVariant(random.nextInt(BreedHorns.values().length));
				} else if (random.nextDouble() > 0.02 && random.nextDouble() < 0.20) {
					this.setHornVariant(4);
				} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
					this.setHornVariant(2);
				} else if (random.nextDouble() > 0.50) {
					this.setHornVariant(0);
				}
			} else if (this.isMale()) {
				if (random.nextDouble() < 0.02) {
					this.setHornVariant(random.nextInt(BreedHorns.values().length));
				} else if (random.nextDouble() > 0.02 && random.nextDouble() < 0.10) {
					this.setHornVariant(0);
				} else if (random.nextDouble() > 0.10 && random.nextDouble() < 0.30) {
					this.setHornVariant(2);
				} else if (random.nextDouble() > 0.30) {
					this.setHornVariant(4);
				}
			}
		}

		if (this.getBreed() == 4) { //racka can come with long, straight horns. usually males, but sometimes females too
			if (this.isFemale()) {
				if (random.nextDouble() < 0.02) {
					this.setHornVariant(random.nextInt(BreedHorns.values().length));
				} else if (random.nextDouble() > 0.02 && random.nextDouble() < 0.20) {
					this.setHornVariant(5);
				} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
					this.setHornVariant(2);
				} else if (random.nextDouble() > 0.50) {
					this.setHornVariant(0);
				}
			} else if (this.isMale()) {
				if (random.nextDouble() < 0.02) {
					this.setHornVariant(random.nextInt(BreedHorns.values().length));
				} else if (random.nextDouble() > 0.02 && random.nextDouble() < 0.10) {
					this.setHornVariant(0);
				} else if (random.nextDouble() > 0.10 && random.nextDouble() < 0.30) {
					this.setHornVariant(2);
				} else if (random.nextDouble() > 0.30) {
					this.setHornVariant(5);
				}
			}
		}

		if (this.getBreed() == 5) { //california red dont come with horns
			if (random.nextDouble() < 0.02) {
				this.setHornVariant(random.nextInt(BreedHorns.values().length));
			} else if (random.nextDouble() > 0.02 && random.nextDouble() < 0.15) {
				this.setHornVariant(0);
			}
		}

		if (this.getBreed() == 6) { //hair can come with large, curly horns. usually males, and females have short horns
			if (this.isFemale()) {
				if (random.nextDouble() < 0.02) {
					this.setHornVariant(random.nextInt(BreedHorns.values().length));
				} else if (random.nextDouble() > 0.02 && random.nextDouble() < 0.20) {
					this.setHornVariant(0);
				} else if (random.nextDouble() > 0.20) {
					this.setHornVariant(1);
				}
			} else if (this.isMale()) {
				if (random.nextDouble() < 0.02) {
					this.setHornVariant(random.nextInt(BreedHorns.values().length));
				} else if (random.nextDouble() > 0.02 && random.nextDouble() < 0.15) {
					this.setHornVariant(1);
				} else if (random.nextDouble() > 0.15) {
					this.setHornVariant(2);
				}
			}
		}

	}

	public enum BreedHorns {
		NONE,
		GULF_COAST,
		NORFOLK,
		DORSET,
		JACOB,
		RACKA;

		public static BreedHorns hornsFromOrdinal(int ordinal) {
			return BreedHorns.values()[ordinal % BreedHorns.values().length];
		}
	}

}
