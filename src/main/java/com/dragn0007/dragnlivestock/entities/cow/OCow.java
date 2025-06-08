package com.dragn0007.dragnlivestock.entities.cow;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.ai.BullAroundLikeCrazyGoal;
import com.dragn0007.dragnlivestock.entities.ai.CattleFollowHerdLeaderGoal;
import com.dragn0007.dragnlivestock.entities.ai.ORunAroundLikeCrazyGoal;
import com.dragn0007.dragnlivestock.entities.cow.ox.Ox;
import com.dragn0007.dragnlivestock.entities.cow.ox.OxModel;
import com.dragn0007.dragnlivestock.entities.sheep.*;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.entities.util.LOAnimations;
import com.dragn0007.dragnlivestock.entities.util.Taggable;
import com.dragn0007.dragnlivestock.gui.OMountMenu;
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
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
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
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkHooks;
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
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class OCow extends AbstractOMount implements GeoEntity, Taggable {

	public OCow leader;
	public int herdSize = 1;

	public OCow(EntityType<? extends OCow> type, Level level) {
		super(type, level);
	}

	private static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_cow");
	private static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/cow");
	private static final ResourceLocation TFC_LOOT_TABLE = new ResourceLocation("tfc", "entities/cow");
	@Override
	public @NotNull ResourceLocation getDefaultLootTable() {
		if (LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
			return VANILLA_LOOT_TABLE;
		}
		if (!ModList.get().isLoaded("tfc") && !LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get() && (this.getBreed() == 1) || this.getBreed() == 5) {
			return LOOT_TABLE;
		}
		if (ModList.get().isLoaded("tfc")) {
			return TFC_LOOT_TABLE;
		}
		return LOOT_TABLE;
	}

	public boolean isMeatBreed() {
		return this.getBreed() == 0 || this.getBreed() == 2 || this.getBreed() == 4 || this.getBreed() == 8;
	}

	public boolean isNormalBreed() {
		return this.getBreed() == 1 || this.getBreed() == 5;
	}

	public boolean isMiniBreed() {
		return this.getBreed() == 3;
	}

	public boolean isDairyBreed() {
		return this.getBreed() == 6 || this.getBreed() == 7;
	}

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0D, (double)this.getEyeHeight() * 1.0F, (double)(this.getBbWidth() * 0.9F));
		//              ^ Side offset                      ^ Height offset                   ^ Length offset
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 18.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.17F);
	}

	public static final Ingredient FOOD_ITEMS = Ingredient.of(LOTags.Items.O_COW_EATS);
	public boolean isFood(ItemStack stack) {
		return FOOD_ITEMS.test(stack);
	}

	@Override
	public void playEmote(String emoteName, String loopType) {}
	@Override
	public void openInventory(Player player) {}
	@Override
	public void openCustomInventoryScreen(Player player) {}
	@Override
	public boolean isTamed() {
		return false;
	}
	@Override
	public boolean canWearArmor() {
		return false;
	}
	@Override
	protected boolean canPerformRearing() {
		return false;
	}

	public void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.WHEAT), false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(3, new CattleFollowHerdLeaderGoal(this, 16.0F));
		this.goalSelector.addGoal(1, new BullAroundLikeCrazyGoal(this, 1.3F));

		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity ->
			livingEntity.getType().is(LOTags.Entity_Types.HERDING_DOGS) && (livingEntity instanceof TamableAnimal && ((TamableAnimal) livingEntity).isTame() && !this.isLeashed())
		));

		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity ->
			livingEntity.getType().is(LOTags.Entity_Types.HORSES) && (livingEntity instanceof AbstractHorse && livingEntity.isVehicle() && !this.isLeashed())
		));

		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity ->
				livingEntity.getType().is(LOTags.Entity_Types.WOLVES) && (livingEntity instanceof TamableAnimal && !((TamableAnimal) livingEntity).isTame() && !this.isLeashed())
		));
	}

	@Override
	public float getStepHeight() {
		return 1F;
	}

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	private <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
		double currentSpeed = this.getDeltaMovement().lengthSqr();
		double speedThreshold = 0.01;

		AnimationController<T> controller = tAnimationState.getController();

		if (this.isHarnessed() && this.isVehicle()) {
			controller.setAnimation(RawAnimation.begin().then("buck", Animation.LoopType.LOOP));
			controller.setAnimationSpeed(1.1);
		} else {
			if (tAnimationState.isMoving()) {
				if (currentSpeed > speedThreshold) {
					controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
					controller.setAnimationSpeed(1.1);
				} else {
					controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
				}
			} else {
				controller.setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
			}
		}

		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "controller", 2, this::predicate));
		controllers.add(LOAnimations.genericAttackAnimation(this, LOAnimations.ATTACK));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}

	@Override
	public void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
		if (this.hasPassenger(entity)) {

			double offsetX = 0;
			double offsetY = 1.1;
			double offsetZ = -0.055;

			if (this.isHarnessed()) {
				offsetY = 1.4;
			}

			double radYaw = Math.toRadians(this.getYRot());

			double offsetXRotated = offsetX * Math.cos(radYaw) - offsetZ * Math.sin(radYaw);
			double offsetYRotated = offsetY;
			double offsetZRotated = offsetX * Math.sin(radYaw) + offsetZ * Math.cos(radYaw);

			double x = this.getX() + offsetXRotated;
			double y = this.getY() + offsetYRotated;
			double z = this.getZ() + offsetZRotated;

			entity.setPos(x, y, z);
		}
	}

	public boolean isFollower() {
		return this.leader != null && this.leader.isAlive();
	}

	public OCow startFollowing(OCow cow) {
		this.leader = cow;
		cow.addFollower();
		return cow;
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

	public int getMaxHerdSize() {
		return LivestockOverhaulCommonConfig.COW_HERD_MAX.get();
	}

	public boolean hasFollowers() {
		return this.herdSize > 1;
	}

	public boolean inRangeOfLeader() {
		return this.distanceToSqr(this.leader) <= 120.0D;
	}

	public void pathToLeader() {
		if (this.isFollower()) {
			this.getNavigation().moveTo(this.leader, 1.0D);
		}

	}

	public void addFollowers(Stream<? extends OCow> p_27534_) {
		p_27534_.limit((long)(this.getMaxHerdSize() - this.herdSize)).filter((cow) -> {
			return cow != this;
		}).forEach((cow) -> {
			cow.startFollowing(this);
		});
	}

	public int replenishMilkCounter = 0;

	public void tick() {
		super.tick();
		if (this.hasFollowers() && this.level().random.nextInt(200) == 1) {
			List<? extends OCow> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(20.0D, 20.0D, 20.0D));
			if (list.size() <= 1) {
				this.herdSize = 1;
			}
		}

		replenishMilkCounter++;

		if (replenishMilkCounter >= LivestockOverhaulCommonConfig.MILKING_COOLDOWN.get() && !this.isDairyBreed()) {
			this.setMilked(false);
		}

		if (replenishMilkCounter >= LivestockOverhaulCommonConfig.DAIRY_MILKING_COOLDOWN.get() && this.isDairyBreed()) {
			this.setMilked(false);
		}
	}

	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemStack = player.getItemInHand(hand);
		Item item = itemStack.getItem();

		if (item instanceof BrandTagItem) {
			setTagged(true);
			this.playSound(SoundEvents.SHEEP_SHEAR, 0.5f, 1f);
			BrandTagItem tagItem = (BrandTagItem)item;
			DyeColor color = tagItem.getColor();
				if (color != this.getBrandTagColor()) {
					this.setBrandTagColor(color);
				if (!player.getAbilities().instabuild) {
					itemStack.shrink(1);
					return InteractionResult.sidedSuccess(this.level().isClientSide);
				}
			}
		}

		if (itemStack.is(Items.SHEARS) && player.isShiftKeyDown()) {
			if (this.isTagged() || this.isHarnessed()) {
				if (this.isTagged()) {
					this.setTagged(false);
				}
				if (this.isHarnessed()) {
					this.setHarnessed(false);
					spawnAtLocation(LOItems.RODEO_HARNESS.get());
				}
				this.playSound(SoundEvents.SHEEP_SHEAR, 0.5f, 1f);
				return InteractionResult.sidedSuccess(this.level().isClientSide);
			}
		}

		if (itemStack.is(LOItems.GENDER_TEST_STRIP.get()) && this.isFemale()) {
			player.playSound(SoundEvents.BEEHIVE_EXIT, 1.0F, 1.0F);
			ItemStack itemstack1 = ItemUtils.createFilledResult(itemStack, player, LOItems.FEMALE_GENDER_TEST_STRIP.get().getDefaultInstance());
			player.setItemInHand(hand, itemstack1);
			return InteractionResult.SUCCESS;
		}

		if (itemStack.is(LOItems.GENDER_TEST_STRIP.get()) && this.isMale()) {
			player.playSound(SoundEvents.BEEHIVE_EXIT, 1.0F, 1.0F);
			ItemStack itemstack1 = ItemUtils.createFilledResult(itemStack, player, LOItems.MALE_GENDER_TEST_STRIP.get().getDefaultInstance());
			player.setItemInHand(hand, itemstack1);
			return InteractionResult.SUCCESS;
		}

		if(itemStack.is(LOItems.RODEO_HARNESS.get()) && !this.isHarnessed()) {
			if(!this.level().isClientSide) {
				this.level().gameEvent(this, GameEvent.EQUIP, this.position());
				itemStack.shrink(1);
				this.setHarnessed(true);
			}
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		}

		if (itemStack.is(Items.BUCKET) && !this.isBaby()) {
			if (!wasMilked() || replenishMilkCounter >= LivestockOverhaulCommonConfig.MILKING_COOLDOWN.get() && !this.isDairyBreed() &&
					(!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() ||
							(LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() && this.isFemale()))) {
				player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
				ItemStack itemstack1 = ItemUtils.createFilledResult(itemStack, player, Items.MILK_BUCKET.getDefaultInstance());
				player.setItemInHand(hand, itemstack1);
				replenishMilkCounter = 0;
				setMilked(true);
			} else if (!wasMilked() || replenishMilkCounter >= LivestockOverhaulCommonConfig.DAIRY_MILKING_COOLDOWN.get() && this.isDairyBreed() &&
					(!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() ||
							(LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() && this.isFemale()))) {
				player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
				ItemStack itemstack1 = ItemUtils.createFilledResult(itemStack, player, Items.MILK_BUCKET.getDefaultInstance());
				player.setItemInHand(hand, itemstack1);
				replenishMilkCounter = 0;
				setMilked(true);
			}
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		} else {
			return super.mobInteract(player, hand);
		}
	}

	public SoundEvent getAmbientSound() {
		super.getAmbientSound();
		return SoundEvents.COW_AMBIENT;
	}

	public SoundEvent getDeathSound() {
		super.getDeathSound();
		return SoundEvents.COW_DEATH;
	}

	public SoundEvent getHurtSound(DamageSource p_30720_) {
		super.getHurtSound(p_30720_);
		return SoundEvents.COW_HURT;
	}

	public void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
		this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
	}

	// Generates the base texture
	public static final EntityDataAccessor<Integer> BREED = SynchedEntityData.defineId(OCow.class, EntityDataSerializers.INT);
	public int getBreedLocation() {
		return CowBreed.Breed.values().length;
	}
	public int getBreed() {
		return this.entityData.get(BREED);
	}
	public void setBreed(int breed) {
		this.entityData.set(BREED, breed);
	}

	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OCow.class, EntityDataSerializers.INT);
	public ResourceLocation getTextureLocation() {
		return OCowModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
	}
	public int getVariant() {
		return this.entityData.get(VARIANT);
	}
	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
	}


	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(OCow.class, EntityDataSerializers.INT);
	public ResourceLocation getOverlayLocation() {return OCowMarkingLayer.Overlay.overlayFromOrdinal(getOverlayVariant()).resourceLocation;}
	public int getOverlayVariant() {
		return this.entityData.get(OVERLAY);
	}
	public void setOverlayVariant(int overlayVariant) {
		this.entityData.set(OVERLAY, overlayVariant);
	}

	public static final EntityDataAccessor<Integer> HORN_TYPE = SynchedEntityData.defineId(OCow.class, EntityDataSerializers.INT);
	public int getHornVariant() {
		return this.entityData.get(HORN_TYPE);
	}
	public void setHornVariant(int hornVariant) {
		this.entityData.set(HORN_TYPE, hornVariant);
	}

	private static final EntityDataAccessor<Integer> BRAND_TAG_COLOR = SynchedEntityData.defineId(OCow.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> TAGGED = SynchedEntityData.defineId(OCow.class, EntityDataSerializers.BOOLEAN);
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

	public static final EntityDataAccessor<Boolean> MILKED = SynchedEntityData.defineId(OCow.class, EntityDataSerializers.BOOLEAN);
	public boolean wasMilked() {
		return this.entityData.get(MILKED);
	}
	public void setMilked(boolean milked) {
		this.entityData.set(MILKED, milked);
	}

	public static final EntityDataAccessor<Boolean> HARNESSED = SynchedEntityData.defineId(OCow.class, EntityDataSerializers.BOOLEAN);
	public boolean isHarnessed() {
		return this.entityData.get(HARNESSED);
	}
	public void setHarnessed(boolean harnessed) {
		this.entityData.set(HARNESSED, harnessed);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);

		if (tag.contains("Breed")) {
			setBreed(tag.getInt("Breed"));
		}

		if (tag.contains("Variant")) {
			setVariant(tag.getInt("Variant"));
		}

		if (tag.contains("Overlay")) {
			setOverlayVariant(tag.getInt("Overlay"));
		}

		if (tag.contains("HornType")) {
			setHornVariant(tag.getInt("HornType"));
		}

		if (tag.contains("Gender")) {
			this.setGender(tag.getInt("Gender"));
		}

		if (tag.contains("MilkedTime")) {
			this.replenishMilkCounter = tag.getInt("MilkedTime");
		}

		if (tag.contains("Milked")) {
			setMilked(tag.getBoolean("Milked"));
		}

		if(tag.contains("Tagged")) {
			this.setTagged(tag.getBoolean("Tagged"));
		}

		this.setBrandTagColor(DyeColor.byId(tag.getInt("BrandTagColor")));

		if(tag.contains("Harnessed")) {
			this.setHarnessed(tag.getBoolean("Harnessed"));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);

		tag.putInt("Breed", getBreed());
		tag.putInt("Variant", getVariant());
		tag.putInt("Overlay", getOverlayVariant());
		tag.putInt("HornType", getHornVariant());
		tag.putInt("Gender", this.getGender());
		tag.putBoolean("Milked", this.wasMilked());
		tag.putInt("MilkedTime", this.replenishMilkCounter);
		tag.putBoolean("Tagged", this.isTagged());
		tag.putByte("BrandTagColor", (byte)this.getBrandTagColor().getId());
		tag.putBoolean("Harnessed", this.isHarnessed());
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}
		Random random = new Random();

		this.setBreed(random.nextInt(CowBreed.Breed.values().length));
		this.setGender(random.nextInt(OCow.Gender.values().length));

		if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
			this.setColorByBreed();
			this.setMarkingByBreed();
			this.setHornsByBreed();
		} else {
			this.setVariant(random.nextInt(OCowModel.Variant.values().length));
			this.setOverlayVariant(random.nextInt(OCowMarkingLayer.Overlay.values().length));
			this.setHornVariant(random.nextInt(OCow.BreedHorns.values().length));
		}

		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(BREED, 0);
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
		this.entityData.define(GENDER, 0);
		this.entityData.define(HORN_TYPE, 0);
		this.entityData.define(BRAND_TAG_COLOR, DyeColor.YELLOW.getId());
		this.entityData.define(TAGGED, false);
		this.entityData.define(MILKED, false);
		this.entityData.define(HARNESSED, false);
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
	public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(OCow.class, EntityDataSerializers.INT);
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
		} else if (!(animal instanceof OCow)) {
			return false;
		} else {
			if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
				return this.canParent() && ((OCow) animal).canParent();
			} else {
				OCow partner = (OCow) animal;
				if (this.canParent() && partner.canParent() && this.getGender() != partner.getGender()) {
					return isFemale();
				}
			}
		}
		return false;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		OCow calf;
		OCow partner = (OCow) ageableMob;
		calf = EntityTypes.O_COW_ENTITY.get().create(serverLevel);

		if (this.random.nextInt(5) == 0) {
			Ox oX = EntityTypes.OX_ENTITY.get().create(serverLevel);
			if (oX != null) {
				oX.setVariant(this.random.nextInt(OxModel.Variant.values().length));
				return oX;
			}
		} else {

			int breedChance = this.random.nextInt(5);
			int breed;
			if (breedChance == 0) {
				breed = this.random.nextInt(CowBreed.Breed.values().length);
			} else {
				breed = (this.random.nextInt(2) == 0) ? this.getBreed() : partner.getBreed();
			}
			calf.setBreed(breed);

			if (!(breedChance == 0) && random.nextDouble() > 0.5) {
				int variantChance = this.random.nextInt(14);
				int variant;
				if (variantChance < 6) {
					variant = this.getVariant();
				} else if (variantChance < 12) {
					variant = partner.getVariant();
				} else {
					variant = this.random.nextInt(OSheepModel.Variant.values().length);
				}
				calf.setVariant(variant);
			} else if (breedChance == 0 && random.nextDouble() < 0.5) {
				calf.setColorByBreed();
			}

			if (!(breedChance == 0) && random.nextDouble() > 0.5) {
				int overlayChance = this.random.nextInt(10);
				int overlay;
				if (overlayChance < 4) {
					overlay = this.getOverlayVariant();
				} else if (overlayChance < 8) {
					overlay = partner.getOverlayVariant();
				} else {
					overlay = this.random.nextInt(OSheepMarkingLayer.Overlay.values().length);
				}
				calf.setOverlayVariant(overlay);
			} else if (breedChance == 0 && random.nextDouble() < 0.5) {
				calf.setMarkingByBreed();
			}

			if (!(breedChance == 0) && random.nextDouble() > 0.5) {
				int hornsChance = this.random.nextInt(10);
				int hornType;
				if (hornsChance < 4) {
					hornType = this.getHornVariant();
				} else if (hornsChance < 8) {
					hornType = partner.getHornVariant();
				} else {
					hornType = this.random.nextInt(OSheep.BreedHorns.values().length);
				}
				calf.setHornVariant(hornType);
			} else if (breedChance == 0 && random.nextDouble() < 0.5) {
				calf.setHornsByBreed();
			}

			int gender;
			gender = this.random.nextInt(OSheep.Gender.values().length);
			calf.setGender(gender);

		}

		return calf;
	}

	@Override
	public void dropCustomDeathLoot(DamageSource p_33574_, int p_33575_, boolean p_33576_) {
		super.dropCustomDeathLoot(p_33574_, p_33575_, p_33576_);
		Random random = new Random();

		if (!LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get() || !ModList.get().isLoaded("tfc")) {
			if (this.isMeatBreed()) {
				if (random.nextDouble() < 0.40) {
					this.spawnAtLocation(Items.BEEF, 2);
					this.spawnAtLocation(LOItems.BEEF_RIB_STEAK.get(), 2);
					this.spawnAtLocation(LOItems.BEEF_SIRLOIN_STEAK.get(), 2);
					this.spawnAtLocation(Items.LEATHER, 2);
				} else if (random.nextDouble() > 0.40) {
					this.spawnAtLocation(Items.BEEF);
					this.spawnAtLocation(LOItems.BEEF_RIB_STEAK.get());
					this.spawnAtLocation(LOItems.BEEF_SIRLOIN_STEAK.get());
					this.spawnAtLocation(Items.LEATHER);
				}
			}

			if (this.isNormalBreed()) {
				if (random.nextDouble() < 0.15) {
					this.spawnAtLocation(Items.BEEF);
					this.spawnAtLocation(LOItems.BEEF_RIB_STEAK.get());
					this.spawnAtLocation(LOItems.BEEF_SIRLOIN_STEAK.get());
					this.spawnAtLocation(Items.LEATHER);
				}
			}

		}
	}

	public void setColorByBreed() {

		if (this.getBreed() == 0) { //angus
			if (random.nextDouble() < 0.05) {
				this.setVariant(random.nextInt(OCowModel.Variant.values().length));
			} else if (random.nextDouble() > 0.05) {
				this.setVariant(0);
			}
		}

		if (this.getBreed() == 1) {
			if (random.nextDouble() < 0.05) { //longhorn
				this.setVariant(random.nextInt(OCowModel.Variant.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {2, 3, 4, 5, 7, 8};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 2) {
			if (random.nextDouble() < 0.05) { //brahman
				this.setVariant(random.nextInt(OCowModel.Variant.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {1, 2, 3, 4, 5, 6, 8, 9};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 3) { //mini
			this.setVariant(random.nextInt(OCowModel.Variant.values().length));
		}

		if (this.getBreed() == 4) { //watusi
			if (random.nextDouble() < 0.05) {
				this.setVariant(random.nextInt(OCowModel.Variant.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {2, 3, 5};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 5) {
			if (random.nextDouble() < 0.05) { //corriente
				this.setVariant(random.nextInt(OCowModel.Variant.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {0, 2, 3, 4, 5, 8, 9};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 6) {
			if (random.nextDouble() < 0.05) { //holstein
				this.setVariant(random.nextInt(OCowModel.Variant.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {0, 2, 9};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 7) {
			if (random.nextDouble() < 0.05) { //jersey
				this.setVariant(random.nextInt(OCowModel.Variant.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {2, 8};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 8) {
			if (random.nextDouble() < 0.05) { //hereford
				this.setVariant(random.nextInt(OCowModel.Variant.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {2, 5};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 9) { //highland
			this.setVariant(random.nextInt(OCowModel.Variant.values().length));
		}

	}

	public void setMarkingByBreed() {

		if (this.getBreed() == 0) { //angus
			if (random.nextDouble() < 0.05) {
				this.setOverlayVariant(random.nextInt(OCowMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05) {
				this.setOverlayVariant(0);
			}
		}

		if (this.getBreed() == 1) {
			if (random.nextDouble() < 0.05) { //longhorn
				this.setOverlayVariant(random.nextInt(OCowMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {9, 10, 11, 13, 14, 15, 17, 18, 19};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 2) {
			if (random.nextDouble() < 0.05) { //brahman
				this.setOverlayVariant(random.nextInt(OCowMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05) {
				this.setOverlayVariant(0);
			}
		}

		if (this.getBreed() == 3) { //mini
			this.setOverlayVariant(random.nextInt(OCowMarkingLayer.Overlay.values().length));
		}

		if (this.getBreed() == 4) { //watusi
			if (random.nextDouble() < 0.05) {
				this.setOverlayVariant(random.nextInt(OCowMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {0, 9, 10, 11, 13, 14, 15, 17, 18, 19};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 5) {
			if (random.nextDouble() < 0.05) { //corriente
				this.setOverlayVariant(random.nextInt(OCowMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {0, 1, 5, 9, 13, 17, 21, 22, 23};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 6) {
			if (random.nextDouble() < 0.05) { //holstein
				this.setOverlayVariant(random.nextInt(OCowMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {2, 3, 5, 10, 11, 18, 19, 21, 22, 23, 24};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 7) {
			if (random.nextDouble() < 0.05) { //jersey
				this.setOverlayVariant(random.nextInt(OCowMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {0, 21, 22, 23};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 8) {
			if (random.nextDouble() < 0.05) { //hereford
				this.setOverlayVariant(random.nextInt(OCowMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05) {
				this.setOverlayVariant(25);
			}
		}

		if (this.getBreed() == 9) { //highland
			if (random.nextDouble() < 0.05) {
				this.setOverlayVariant(random.nextInt(OCowMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05) {
				this.setOverlayVariant(0);
			}
		}

	}

	public void setHornsByBreed() {

		if (this.getBreed() == 0) { //angus
			if (random.nextDouble() < 0.05) {
				this.setHornVariant(random.nextInt(BreedHorns.values().length));
			} else if (random.nextDouble() > 0.05) {
				this.setHornVariant(0);
			}
		}

		if (this.getBreed() == 1) {
			if (random.nextDouble() < 0.05) { //longhorn
				this.setHornVariant(random.nextInt(BreedHorns.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {2, 3, 4};
				int randomIndex = new Random().nextInt(variants.length);
				this.setHornVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 2) {
			if (random.nextDouble() < 0.05) { //brahman
				this.setHornVariant(random.nextInt(BreedHorns.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {0, 8, 9, 10};
				int randomIndex = new Random().nextInt(variants.length);
				this.setHornVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 3) {
			if (random.nextDouble() < 0.05) { //mini
				this.setHornVariant(random.nextInt(BreedHorns.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {0, 8, 10};
				int randomIndex = new Random().nextInt(variants.length);
				this.setHornVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 4) { //watusi
			if (random.nextDouble() < 0.05) {
				this.setHornVariant(random.nextInt(BreedHorns.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {5, 6};
				int randomIndex = new Random().nextInt(variants.length);
				this.setHornVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 5) {
			if (random.nextDouble() < 0.05) { //corriente
				this.setHornVariant(random.nextInt(BreedHorns.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {1, 7, 8, 10};
				int randomIndex = new Random().nextInt(variants.length);
				this.setHornVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 6) {
			if (random.nextDouble() < 0.05) { //holstein
				this.setHornVariant(random.nextInt(BreedHorns.values().length));
			} else if (random.nextDouble() > 0.05) {
				this.setHornVariant(0);
			}
		}

		if (this.getBreed() == 7) {
			if (random.nextDouble() < 0.05) { //jersey
				this.setHornVariant(random.nextInt(BreedHorns.values().length));
			} else if (random.nextDouble() > 0.05) {
				this.setHornVariant(0);
			}
		}

		if (this.getBreed() == 8) {
			if (random.nextDouble() < 0.05) { //hereford
				this.setHornVariant(random.nextInt(BreedHorns.values().length));
			} else if (random.nextDouble() > 0.05) {
				this.setHornVariant(0);
			}
		}

		if (this.getBreed() == 9) {
			if (random.nextDouble() < 0.05) { //highland
				this.setHornVariant(random.nextInt(BreedHorns.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {1, 7, 8, 10};
				int randomIndex = new Random().nextInt(variants.length);
				this.setHornVariant(variants[randomIndex]);
			}
		}

	}

	public enum BreedHorns {
		NONE,
		CLASSIC_BULL_UPWARDS,
		LONGHORN_FORWARD,
		LONGHORN_UPWARDS,
		LONGHORN_DOWNWARDS,
		WATUSI_STRAIGHT,
		WATUSI_CURVED,
		SMALL_UPWARDS,
		CLASSIC_BULL_FORWARD,
		ZEBU,
		SMALL_FORWARD;

		public static OCow.BreedHorns hornsFromOrdinal(int ordinal) {
			return OCow.BreedHorns.values()[ordinal % OCow.BreedHorns.values().length];
		}
	}

}
