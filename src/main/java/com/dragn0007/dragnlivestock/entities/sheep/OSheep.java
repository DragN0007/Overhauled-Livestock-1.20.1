package com.dragn0007.dragnlivestock.entities.sheep;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.ai.OAvoidEntityGoal;
import com.dragn0007.dragnlivestock.entities.ai.SheepFollowHerdLeaderGoal;
import com.dragn0007.dragnlivestock.entities.util.Taggable;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.items.custom.BrandTagItem;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
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
		setDyed(false);
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
				livingEntity.getType().is(LOTags.Entity_Types.DOGS) && (livingEntity instanceof TamableAnimal && ((TamableAnimal) livingEntity).isTame() && !this.isLeashed())
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
	public int dyeDissipateCounter = 0;

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

		regrowWoolCounter++;

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
		p_27534_.limit(this.getMaxHerdSize() - this.herdSize).filter((cow) -> cow != this).forEach((cow) -> {
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
				if (!player.getAbilities().instabuild) {
					itemstack.shrink(1);
				}
				return InteractionResult.sidedSuccess(this.level().isClientSide);
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
			if (this.isDyed()) {
				dyeDissipateCounter++;
				if (this.dyeDissipateCounter >= LivestockOverhaulCommonConfig.MAX_DYED_SHEARS.get()) {
					this.setDyed(false);
					this.setWoolDyeVariant(0);
				}
			}
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

		if (itemstack.is(LOTags.Items.DYES) && LivestockOverhaulCommonConfig.ALLOW_SHEEP_DYE.get()) {
			this.setDyed(true);
			dyeDissipateCounter = 0;

			if (itemstack.is(Items.BLACK_DYE) || itemstack.is(LOItems.BLACK_WOOL_DYE.get())) {
				this.setWoolDyeVariant(1);
			} else if (itemstack.is(Items.BLUE_DYE) || itemstack.is(LOItems.BLUE_WOOL_DYE.get())) {
				this.setWoolDyeVariant(2);
			} else if (itemstack.is(Items.BROWN_DYE) || itemstack.is(LOItems.BROWN_WOOL_DYE.get())) {
				this.setWoolDyeVariant(3);
			} else if (itemstack.is(Items.CYAN_DYE) || itemstack.is(LOItems.CYAN_WOOL_DYE.get())) {
				this.setWoolDyeVariant(4);
			} else if (itemstack.is(Items.GREEN_DYE) || itemstack.is(LOItems.GREEN_WOOL_DYE.get())) {
				this.setWoolDyeVariant(5);
			} else if (itemstack.is(Items.GRAY_DYE) || itemstack.is(LOItems.GREY_WOOL_DYE.get())) {
				this.setWoolDyeVariant(6);
			} else if (itemstack.is(Items.LIGHT_BLUE_DYE) || itemstack.is(LOItems.LIGHT_BLUE_WOOL_DYE.get())) {
				this.setWoolDyeVariant(7);
			} else if (itemstack.is(Items.LIGHT_GRAY_DYE) || itemstack.is(LOItems.LIGHT_GREY_WOOL_DYE.get())) {
				this.setWoolDyeVariant(8);
			} else if (itemstack.is(Items.LIME_DYE) || itemstack.is(LOItems.LIME_WOOL_DYE.get())) {
				this.setWoolDyeVariant(9);
			} else if (itemstack.is(Items.MAGENTA_DYE) || itemstack.is(LOItems.MAGENTA_WOOL_DYE.get())) {
				this.setWoolDyeVariant(10);
			} else if (itemstack.is(Items.ORANGE_DYE) || itemstack.is(LOItems.ORANGE_WOOL_DYE.get())) {
				this.setWoolDyeVariant(11);
			} else if (itemstack.is(Items.PINK_DYE) || itemstack.is(LOItems.PINK_WOOL_DYE.get())) {
				this.setWoolDyeVariant(12);
			} else if (itemstack.is(Items.PURPLE_DYE) || itemstack.is(LOItems.PURPLE_WOOL_DYE.get())) {
				this.setWoolDyeVariant(13);
			} else if (itemstack.is(Items.RED_DYE) || itemstack.is(LOItems.RED_WOOL_DYE.get())) {
				this.setWoolDyeVariant(14);
			} else if (itemstack.is(Items.WHITE_DYE) || itemstack.is(LOItems.WHITE_WOOL_DYE.get())) {
				this.setWoolDyeVariant(15);
			} else if (itemstack.is(Items.YELLOW_DYE) || itemstack.is(LOItems.YELLOW_WOOL_DYE.get())) {
				this.setWoolDyeVariant(16);
			}

			if (!player.getAbilities().instabuild) {
				itemstack.shrink(1);
			}
			return InteractionResult.sidedSuccess(this.level().isClientSide);
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
		if (!LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
			return OSheepModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
		} else {
			return OSheepModel.SVariant.variantFromOrdinal(getVariant()).resourceLocation;
		}
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
		if (!LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
			return OSheepWoolLayer.Overlay.overlayFromOrdinal(getWoolVariant()).resourceLocation;
		} else {
			return OSheepWoolLayer.SOverlay.overlayFromOrdinal(getWoolVariant()).resourceLocation;
		}
	}
	public int getWoolVariant() {
		return this.entityData.get(WOOL_COLOR);
	}
	public void setWoolVariant(int overlayVariant) {
		this.entityData.set(WOOL_COLOR, overlayVariant);
	}

	public static final EntityDataAccessor<Integer> WOOL_DYE_COLOR = SynchedEntityData.defineId(OSheep.class, EntityDataSerializers.INT);
	public ResourceLocation getWoolDyeLocation() {
		if (!LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
			return OSheepWoolLayer.DyeOverlay.overlayFromOrdinal(getWoolDyeVariant()).resourceLocation;
		} else {
			return OSheepWoolLayer.SDyeOverlay.overlayFromOrdinal(getWoolDyeVariant()).resourceLocation;
		}
	}
	public int getWoolDyeVariant() {
		return this.entityData.get(WOOL_DYE_COLOR);
	}
	public void setWoolDyeVariant(int overlayVariant) {
		this.entityData.set(WOOL_DYE_COLOR, overlayVariant);
	}
	public static final EntityDataAccessor<Boolean> DYED = SynchedEntityData.defineId(OSheep.class, EntityDataSerializers.BOOLEAN);
	public boolean isDyeable() {
		return this.isAlive() && !this.isBaby() && this.getBreed() != 6;
	}
	public boolean isDyed() {
		return this.entityData.get(DYED);
	}
	public void setDyed(boolean dyed) {
		this.entityData.set(DYED, dyed);
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

		if (tag.contains("DyeColor")) {
			setWoolDyeVariant(tag.getInt("DyeColor"));
		}

		if(tag.contains("Dyed")) {
			this.setDyed(tag.getBoolean("Dyed"));
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
		tag.putInt("DyeColor", getWoolDyeVariant());
		tag.putBoolean("Dyed", this.isDyed());
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(QUALITY, 0);
		this.entityData.define(BREED, 0);
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
		this.entityData.define(HORN_TYPE, 0);
		this.entityData.define(GENDER, 0);
		this.entityData.define(BRAND_TAG_COLOR, DyeColor.YELLOW.getId());
		this.entityData.define(WOOL_DYE_COLOR, 0);
		this.entityData.define(TAGGED, false);
		this.entityData.define(SHEARED, false);
		this.entityData.define(MILKED, false);
		this.entityData.define(WOOL_COLOR, 0);
		this.entityData.define(DYED, false);
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

	@Override
	public void finalizeSpawnChildFromBreeding(ServerLevel pLevel, Animal pAnimal, @org.jetbrains.annotations.Nullable AgeableMob pBaby) {
		super.finalizeSpawnChildFromBreeding(pLevel, pAnimal, pBaby);
		if (pAnimal instanceof OSheep partner) {
			if (LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
				if (this.isMale()) {
					this.setAge(LivestockOverhaulCommonConfig.MALE_COOLDOWN.get());
				} else {
					this.setAge(LivestockOverhaulCommonConfig.FEMALE_COOLDOWN.get());
				}
				if (partner.isMale()) {
					partner.setAge(LivestockOverhaulCommonConfig.MALE_COOLDOWN.get());
				} else {
					partner.setAge(LivestockOverhaulCommonConfig.FEMALE_COOLDOWN.get());
				}
			} else {
				this.setAge(LivestockOverhaulCommonConfig.FEMALE_COOLDOWN.get());
				partner.setAge(LivestockOverhaulCommonConfig.FEMALE_COOLDOWN.get());
			}
		}
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

		if (LivestockOverhaulCommonConfig.QUALITY.get()) {
			int qual_avg = (this.getQuality() + partner.getQuality()) / 2;
			if (random.nextDouble() <= 0.05) {
				lamb.setQuality(qual_avg + random.nextInt(50));
			} else if (random.nextDouble() >= 0.05 && random.nextDouble() <= 0.25) {
				lamb.setQuality(qual_avg + random.nextInt(25));
			} else if (random.nextDouble() >= 0.25 && random.nextDouble() <= 0.60) {
				lamb.setQuality(qual_avg + random.nextInt(10));
			} else {
				lamb.setQuality(qual_avg + random.nextInt(5));
			}
		}

		if (lamb.getQuality() > 100) {
			lamb.setQuality(100);
		}

		lamb.setGender(random.nextInt(Gender.values().length));
		lamb.setDyed(false);
		lamb.setWoolDyeVariant(0);

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

		ItemStack blackWoolStaple = LOItems.BLACK_WOOL_STAPLE.get().getDefaultInstance();
		ItemStack blueWoolStaple = LOItems.BLUE_WOOL_STAPLE.get().getDefaultInstance();
		ItemStack brownWoolStaple = LOItems.BROWN_WOOL_STAPLE.get().getDefaultInstance();
		ItemStack cyanWoolStaple = LOItems.CYAN_WOOL_STAPLE.get().getDefaultInstance();
		ItemStack greenWoolStaple = LOItems.GREEN_WOOL_STAPLE.get().getDefaultInstance();
		ItemStack greyWoolStaple = LOItems.GREY_WOOL_STAPLE.get().getDefaultInstance();
		ItemStack lightBlueWoolStaple = LOItems.LIGHT_BLUE_WOOL_STAPLE.get().getDefaultInstance();
		ItemStack lightGreyWoolStaple = LOItems.LIGHT_GREY_WOOL_STAPLE.get().getDefaultInstance();
		ItemStack limeWoolStaple = LOItems.LIME_WOOL_STAPLE.get().getDefaultInstance();
		ItemStack magentaWoolStaple = LOItems.MAGENTA_WOOL_STAPLE.get().getDefaultInstance();
		ItemStack orangeWoolStaple = LOItems.ORANGE_WOOL_STAPLE.get().getDefaultInstance();
		ItemStack pinkWoolStaple = LOItems.PINK_WOOL_STAPLE.get().getDefaultInstance();
		ItemStack purpleWoolStaple = LOItems.PURPLE_WOOL_STAPLE.get().getDefaultInstance();
		ItemStack redWoolStaple = LOItems.RED_WOOL_STAPLE.get().getDefaultInstance();
		ItemStack whiteWoolStaple = LOItems.WHITE_WOOL_STAPLE.get().getDefaultInstance();
		ItemStack yellowWoolStaple = LOItems.YELLOW_WOOL_STAPLE.get().getDefaultInstance();

		if (!(this.getBreed() == 6)) {
			if (!LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
				if (!this.isDyed()) {
					if (this.getWoolVariant() == 0 && !(this.getOverlayVariant() == 3)) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(blackWoolStaple);
							this.spawnAtLocation(blackWoolStaple);
							this.spawnAtLocation(blackWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(blackWoolStaple);
							this.spawnAtLocation(blackWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(blackWoolStaple);
						}

						if (this.getOverlayVariant() == 0) {
							this.spawnAtLocation(blackWoolStaple);
						}
					}

					if (this.getWoolVariant() == 1 && !(this.getOverlayVariant() == 3)) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(brownWoolStaple);
							this.spawnAtLocation(brownWoolStaple);
							this.spawnAtLocation(brownWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(brownWoolStaple);
							this.spawnAtLocation(brownWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(brownWoolStaple);
						}

						if (this.getOverlayVariant() == 0) {
							this.spawnAtLocation(brownWoolStaple);
						}
					}

					if (this.getWoolVariant() == 2 && !(this.getOverlayVariant() == 3)) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(greyWoolStaple);
							this.spawnAtLocation(greyWoolStaple);
							this.spawnAtLocation(greyWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(greyWoolStaple);
							this.spawnAtLocation(greyWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(greyWoolStaple);
						}

						if (this.getOverlayVariant() == 0) {
							this.spawnAtLocation(greyWoolStaple);
						}
					}

					if (this.getWoolVariant() == 3 && !(this.getOverlayVariant() == 3)) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(lightGreyWoolStaple);
							this.spawnAtLocation(lightGreyWoolStaple);
							this.spawnAtLocation(lightGreyWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(lightGreyWoolStaple);
							this.spawnAtLocation(lightGreyWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(lightGreyWoolStaple);
						}

						if (this.getOverlayVariant() == 0) {
							this.spawnAtLocation(lightGreyWoolStaple);
						}
					}

					if (this.getWoolVariant() == 4 && !(this.getOverlayVariant() == 3)) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(brownWoolStaple);
							this.spawnAtLocation(brownWoolStaple);
							this.spawnAtLocation(brownWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(brownWoolStaple);
							this.spawnAtLocation(brownWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(brownWoolStaple);
						}

						if (this.getOverlayVariant() == 0) {
							this.spawnAtLocation(brownWoolStaple);
						}
					}

					if (this.getWoolVariant() == 5 && !(this.getOverlayVariant() == 3)) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(whiteWoolStaple);
							this.spawnAtLocation(whiteWoolStaple);
							this.spawnAtLocation(whiteWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(whiteWoolStaple);
							this.spawnAtLocation(whiteWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(whiteWoolStaple);
						}

						if (this.getOverlayVariant() == 0) {
							this.spawnAtLocation(whiteWoolStaple);
						}
					}

					if (this.getOverlayVariant() == 1 || this.getOverlayVariant() == 7 || this.getOverlayVariant() == 9 ||
							this.getOverlayVariant() == 10 || this.getOverlayVariant() == 12 || this.getOverlayVariant() == 13 ||
							this.getOverlayVariant() == 14 || this.getOverlayVariant() == 15) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(whiteWoolStaple);
							this.spawnAtLocation(whiteWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(whiteWoolStaple);
						}
					}

					if (this.getOverlayVariant() == 4 || this.getOverlayVariant() == 5 || this.getOverlayVariant() == 6 || this.getOverlayVariant() == 6) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(blackWoolStaple);
							this.spawnAtLocation(blackWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(blackWoolStaple);
						}
					}

					if (this.getOverlayVariant() == 3) { //pure white, only drop white wool
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(whiteWoolStaple);
							this.spawnAtLocation(whiteWoolStaple);
							this.spawnAtLocation(whiteWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(whiteWoolStaple);
							this.spawnAtLocation(whiteWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(whiteWoolStaple);
						}

						this.spawnAtLocation(whiteWoolStaple);
					}
				} else {
					if (this.getWoolDyeVariant() == 1) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(blackWoolStaple);
							this.spawnAtLocation(blackWoolStaple);
							this.spawnAtLocation(blackWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(blackWoolStaple);
							this.spawnAtLocation(blackWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(blackWoolStaple);
						}
					} else if (this.getWoolDyeVariant() == 2) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(blueWoolStaple);
							this.spawnAtLocation(blueWoolStaple);
							this.spawnAtLocation(blueWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(blueWoolStaple);
							this.spawnAtLocation(blueWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(blueWoolStaple);
						}
					} else if (this.getWoolDyeVariant() == 3) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(brownWoolStaple);
							this.spawnAtLocation(brownWoolStaple);
							this.spawnAtLocation(brownWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(brownWoolStaple);
							this.spawnAtLocation(brownWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(brownWoolStaple);
						}
					} else if (this.getWoolDyeVariant() == 4) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(cyanWoolStaple);
							this.spawnAtLocation(cyanWoolStaple);
							this.spawnAtLocation(cyanWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(cyanWoolStaple);
							this.spawnAtLocation(cyanWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(cyanWoolStaple);
						}
					} else if (this.getWoolDyeVariant() == 5) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(greenWoolStaple);
							this.spawnAtLocation(greenWoolStaple);
							this.spawnAtLocation(greenWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(greenWoolStaple);
							this.spawnAtLocation(greenWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(greenWoolStaple);
						}
					} else if (this.getWoolDyeVariant() == 6) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(greyWoolStaple);
							this.spawnAtLocation(greyWoolStaple);
							this.spawnAtLocation(greyWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(greyWoolStaple);
							this.spawnAtLocation(greyWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(greyWoolStaple);
						}
					} else if (this.getWoolDyeVariant() == 7) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(lightBlueWoolStaple);
							this.spawnAtLocation(lightBlueWoolStaple);
							this.spawnAtLocation(lightBlueWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(lightBlueWoolStaple);
							this.spawnAtLocation(lightBlueWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(lightBlueWoolStaple);
						}
					} else if (this.getWoolDyeVariant() == 8) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(lightGreyWoolStaple);
							this.spawnAtLocation(lightGreyWoolStaple);
							this.spawnAtLocation(lightGreyWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(lightGreyWoolStaple);
							this.spawnAtLocation(lightGreyWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(lightGreyWoolStaple);
						}
					} else if (this.getWoolDyeVariant() == 9) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(limeWoolStaple);
							this.spawnAtLocation(limeWoolStaple);
							this.spawnAtLocation(limeWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(limeWoolStaple);
							this.spawnAtLocation(limeWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(limeWoolStaple);
						}
					} else if (this.getWoolDyeVariant() == 10) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(magentaWoolStaple);
							this.spawnAtLocation(magentaWoolStaple);
							this.spawnAtLocation(magentaWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(magentaWoolStaple);
							this.spawnAtLocation(magentaWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(magentaWoolStaple);
						}
					} else if (this.getWoolDyeVariant() == 11) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(orangeWoolStaple);
							this.spawnAtLocation(orangeWoolStaple);
							this.spawnAtLocation(orangeWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(orangeWoolStaple);
							this.spawnAtLocation(orangeWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(orangeWoolStaple);
						}
					} else if (this.getWoolDyeVariant() == 12) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(pinkWoolStaple);
							this.spawnAtLocation(pinkWoolStaple);
							this.spawnAtLocation(pinkWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(pinkWoolStaple);
							this.spawnAtLocation(pinkWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(pinkWoolStaple);
						}
					} else if (this.getWoolDyeVariant() == 13) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(purpleWoolStaple);
							this.spawnAtLocation(purpleWoolStaple);
							this.spawnAtLocation(purpleWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(purpleWoolStaple);
							this.spawnAtLocation(purpleWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(purpleWoolStaple);
						}
					} else if (this.getWoolDyeVariant() == 14) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(redWoolStaple);
							this.spawnAtLocation(redWoolStaple);
							this.spawnAtLocation(redWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(redWoolStaple);
							this.spawnAtLocation(redWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(redWoolStaple);
						}
					} else if (this.getWoolDyeVariant() == 15) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(whiteWoolStaple);
							this.spawnAtLocation(whiteWoolStaple);
							this.spawnAtLocation(whiteWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(whiteWoolStaple);
							this.spawnAtLocation(whiteWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(whiteWoolStaple);
						}
					} else if (this.getWoolDyeVariant() == 16) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(yellowWoolStaple);
							this.spawnAtLocation(yellowWoolStaple);
							this.spawnAtLocation(yellowWoolStaple);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(yellowWoolStaple);
							this.spawnAtLocation(yellowWoolStaple);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(yellowWoolStaple);
						}
					}
				}

			//if vanilla loot
			} else {
				ItemStack blackWool = Items.BLACK_WOOL.getDefaultInstance();
				ItemStack blueWool = Items.BLUE_WOOL.getDefaultInstance();
				ItemStack brownWool = Items.BROWN_WOOL.getDefaultInstance();
				ItemStack cyanWool = Items.CYAN_WOOL.getDefaultInstance();
				ItemStack greenWool = Items.GREEN_WOOL.getDefaultInstance();
				ItemStack greyWool = Items.GRAY_WOOL.getDefaultInstance();
				ItemStack lightBlueWool = Items.LIGHT_BLUE_WOOL.getDefaultInstance();
				ItemStack lightGreyWool = Items.LIGHT_GRAY_WOOL.getDefaultInstance();
				ItemStack limeWool = Items.LIME_WOOL.getDefaultInstance();
				ItemStack magentaWool = Items.MAGENTA_WOOL.getDefaultInstance();
				ItemStack orangeWool = Items.ORANGE_WOOL.getDefaultInstance();
				ItemStack pinkWool = Items.PINK_WOOL.getDefaultInstance();
				ItemStack purpleWool = Items.PURPLE_WOOL.getDefaultInstance();
				ItemStack redWool = Items.RED_WOOL.getDefaultInstance();
				ItemStack whiteWool = Items.WHITE_WOOL.getDefaultInstance();
				ItemStack yellowWool = Items.YELLOW_WOOL.getDefaultInstance();
				if (!this.isDyed()) {
					if (this.getWoolVariant() == 0 && !(this.getOverlayVariant() == 3)) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(blackWool);
							this.spawnAtLocation(blackWool);
							this.spawnAtLocation(blackWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(blackWool);
							this.spawnAtLocation(blackWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(blackWool);
						}

						if (this.getOverlayVariant() == 0) {
							this.spawnAtLocation(blackWool);
						}
					}

					if (this.getWoolVariant() == 1 && !(this.getOverlayVariant() == 3)) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(brownWool);
							this.spawnAtLocation(brownWool);
							this.spawnAtLocation(brownWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(brownWool);
							this.spawnAtLocation(brownWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(brownWool);
						}

						if (this.getOverlayVariant() == 0) {
							this.spawnAtLocation(brownWool);
						}
					}

					if (this.getWoolVariant() == 2 && !(this.getOverlayVariant() == 3)) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(greyWool);
							this.spawnAtLocation(greyWool);
							this.spawnAtLocation(greyWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(greyWool);
							this.spawnAtLocation(greyWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(greyWool);
						}

						if (this.getOverlayVariant() == 0) {
							this.spawnAtLocation(greyWool);
						}
					}

					if (this.getWoolVariant() == 3 && !(this.getOverlayVariant() == 3)) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(lightGreyWool);
							this.spawnAtLocation(lightGreyWool);
							this.spawnAtLocation(lightGreyWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(lightGreyWool);
							this.spawnAtLocation(lightGreyWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(lightGreyWool);
						}

						if (this.getOverlayVariant() == 0) {
							this.spawnAtLocation(lightGreyWool);
						}
					}

					if (this.getWoolVariant() == 4 && !(this.getOverlayVariant() == 3)) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(brownWool);
							this.spawnAtLocation(brownWool);
							this.spawnAtLocation(brownWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(brownWool);
							this.spawnAtLocation(brownWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(brownWool);
						}

						if (this.getOverlayVariant() == 0) {
							this.spawnAtLocation(brownWool);
						}
					}

					if (this.getWoolVariant() == 5 && !(this.getOverlayVariant() == 3)) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(whiteWool);
							this.spawnAtLocation(whiteWool);
							this.spawnAtLocation(whiteWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(whiteWool);
							this.spawnAtLocation(whiteWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(whiteWool);
						}

						if (this.getOverlayVariant() == 0) {
							this.spawnAtLocation(whiteWool);
						}
					}

					if (this.getOverlayVariant() == 1 || this.getOverlayVariant() == 7 || this.getOverlayVariant() == 9 ||
							this.getOverlayVariant() == 10 || this.getOverlayVariant() == 12 || this.getOverlayVariant() == 13 ||
							this.getOverlayVariant() == 14 || this.getOverlayVariant() == 15) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(whiteWool);
							this.spawnAtLocation(whiteWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(whiteWool);
						}
					}

					if (this.getOverlayVariant() == 4 || this.getOverlayVariant() == 5 || this.getOverlayVariant() == 6 || this.getOverlayVariant() == 6) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(blackWool);
							this.spawnAtLocation(blackWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(blackWool);
						}
					}

					if (this.getOverlayVariant() == 3) { //pure white, only drop white wool
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(whiteWool);
							this.spawnAtLocation(whiteWool);
							this.spawnAtLocation(whiteWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(whiteWool);
							this.spawnAtLocation(whiteWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(whiteWool);
						}

						this.spawnAtLocation(whiteWool);
					}
				} else {
					if (this.getWoolDyeVariant() == 1) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(blackWool);
							this.spawnAtLocation(blackWool);
							this.spawnAtLocation(blackWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(blackWool);
							this.spawnAtLocation(blackWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(blackWool);
						}
					} else if (this.getWoolDyeVariant() == 2) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(blueWool);
							this.spawnAtLocation(blueWool);
							this.spawnAtLocation(blueWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(blueWool);
							this.spawnAtLocation(blueWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(blueWool);
						}
					} else if (this.getWoolDyeVariant() == 3) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(brownWool);
							this.spawnAtLocation(brownWool);
							this.spawnAtLocation(brownWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(brownWool);
							this.spawnAtLocation(brownWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(brownWool);
						}
					} else if (this.getWoolDyeVariant() == 4) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(cyanWool);
							this.spawnAtLocation(cyanWool);
							this.spawnAtLocation(cyanWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(cyanWool);
							this.spawnAtLocation(cyanWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(cyanWool);
						}
					} else if (this.getWoolDyeVariant() == 5) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(greenWool);
							this.spawnAtLocation(greenWool);
							this.spawnAtLocation(greenWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(greenWool);
							this.spawnAtLocation(greenWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(greenWool);
						}
					} else if (this.getWoolDyeVariant() == 6) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(greyWool);
							this.spawnAtLocation(greyWool);
							this.spawnAtLocation(greyWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(greyWool);
							this.spawnAtLocation(greyWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(greyWool);
						}
					} else if (this.getWoolDyeVariant() == 7) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(lightBlueWool);
							this.spawnAtLocation(lightBlueWool);
							this.spawnAtLocation(lightBlueWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(lightBlueWool);
							this.spawnAtLocation(lightBlueWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(lightBlueWool);
						}
					} else if (this.getWoolDyeVariant() == 8) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(lightGreyWool);
							this.spawnAtLocation(lightGreyWool);
							this.spawnAtLocation(lightGreyWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(lightGreyWool);
							this.spawnAtLocation(lightGreyWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(lightGreyWool);
						}
					} else if (this.getWoolDyeVariant() == 9) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(limeWool);
							this.spawnAtLocation(limeWool);
							this.spawnAtLocation(limeWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(limeWool);
							this.spawnAtLocation(limeWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(limeWool);
						}
					} else if (this.getWoolDyeVariant() == 10) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(magentaWool);
							this.spawnAtLocation(magentaWool);
							this.spawnAtLocation(magentaWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(magentaWool);
							this.spawnAtLocation(magentaWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(magentaWool);
						}
					} else if (this.getWoolDyeVariant() == 11) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(orangeWool);
							this.spawnAtLocation(orangeWool);
							this.spawnAtLocation(orangeWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(orangeWool);
							this.spawnAtLocation(orangeWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(orangeWool);
						}
					} else if (this.getWoolDyeVariant() == 12) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(pinkWool);
							this.spawnAtLocation(pinkWool);
							this.spawnAtLocation(pinkWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(pinkWool);
							this.spawnAtLocation(pinkWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(pinkWool);
						}
					} else if (this.getWoolDyeVariant() == 13) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(purpleWool);
							this.spawnAtLocation(purpleWool);
							this.spawnAtLocation(purpleWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(purpleWool);
							this.spawnAtLocation(purpleWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(purpleWool);
						}
					} else if (this.getWoolDyeVariant() == 14) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(redWool);
							this.spawnAtLocation(redWool);
							this.spawnAtLocation(redWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(redWool);
							this.spawnAtLocation(redWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(redWool);
						}
					} else if (this.getWoolDyeVariant() == 15) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(whiteWool);
							this.spawnAtLocation(whiteWool);
							this.spawnAtLocation(whiteWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(whiteWool);
							this.spawnAtLocation(whiteWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(whiteWool);
						}
					} else if (this.getWoolDyeVariant() == 16) {
						if (random.nextDouble() <= 0.20) {
							this.spawnAtLocation(yellowWool);
							this.spawnAtLocation(yellowWool);
							this.spawnAtLocation(yellowWool);
						} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
							this.spawnAtLocation(yellowWool);
							this.spawnAtLocation(yellowWool);
						} else if (random.nextDouble() >= 0.50) {
							this.spawnAtLocation(yellowWool);
						}
					}
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

		if (this.getBreed() == 7) { //bunnies tend to come with white skin
			if (random.nextDouble() < 0.05) {
				this.setVariant(random.nextInt(OSheepModel.Variant.values().length));
			} else if (random.nextDouble() > 0.05) {
				this.setVariant(5);
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

		if (this.getBreed() == 7) { //bunnies tend to come with white wool
			if (random.nextDouble() < 0.05) {
				this.setWoolVariant(random.nextInt(OSheepWoolLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05) {
				this.setWoolVariant(5);
			}
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

		if (this.getBreed() == 7) { //bunnies don't often come with markings, and if they do, theyre small
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

		if (this.getBreed() == 7) { //bunnies dont come with horns
			if (random.nextDouble() < 0.02) {
				this.setHornVariant(random.nextInt(BreedHorns.values().length));
			} else if (random.nextDouble() > 0.02 && random.nextDouble() < 0.15) {
				this.setHornVariant(0);
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
