package com.dragn0007.dragnlivestock.entities.chicken;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.ai.OAvoidEntityGoal;
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
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
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
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.Random;

public class OChicken extends Animal implements GeoEntity, Taggable {

	public int eggTime = this.random.nextInt(LivestockOverhaulCommonConfig.CHICKEN_EGG_LAY_TIME.get()) + 6000;
	public boolean isChickenJockey;

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0D, (double)this.getEyeHeight() * 0.8F, (double)(this.getBbWidth() * 0.4F));
		//              ^ Side offset                      ^ Height offset                   ^ Length offset
	}

	public OChicken(EntityType<? extends OChicken> type, Level level) {
		super(type, level);
	}

	protected static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_chicken");
	protected static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/chicken");
	protected static final ResourceLocation TFC_LOOT_TABLE = new ResourceLocation("tfc", "entities/chicken");
	@Override
	public @NotNull ResourceLocation getDefaultLootTable() {
		if (LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
			return VANILLA_LOOT_TABLE;
		}
		if (!ModList.get().isLoaded("tfc") && !LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
			return LOOT_TABLE;
		}
		if (ModList.get().isLoaded("tfc")) {
			return TFC_LOOT_TABLE;
		}
		return LOOT_TABLE;
	}

	public boolean isMeatBreed() {
		return this.getBreed() == 1 || this.getBreed() == 3;
	}

	public boolean isNormalBreed() {
		return this.getBreed() == 3 || this.getBreed() == 5;
	}

	public boolean isMiniBreed() {
		return this.getBreed() == 0 || this.getBreed() == 4;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 4.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.16F);
	}

	public static final Ingredient FOOD_ITEMS = Ingredient.of(LOTags.Items.O_CHICKEN_EATS);

	public void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.8F));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

		this.goalSelector.addGoal(1, new OAvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity ->
				livingEntity.getType().is(LOTags.Entity_Types.WOLVES))
		);

		this.goalSelector.addGoal(1, new OAvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity ->
				livingEntity.getType().is(LOTags.Entity_Types.CATS))
		);

		this.goalSelector.addGoal(1, new OAvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity ->
				livingEntity.getType().is(LOTags.Entity_Types.HUNTING_DOGS))
		);

		this.goalSelector.addGoal(1, new OAvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity ->
				livingEntity.getType().is(LOTags.Entity_Types.FOXES))
		);
	}

	@Override
	public float getStepHeight() {
		return 1F;
	}

	protected final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	protected <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
		double x = this.getX() - this.xo;
		double z = this.getZ() - this.zo;
		boolean isMoving = (x * x + z * z) > 0.0001;
		double currentSpeed = this.getDeltaMovement().lengthSqr();
		double speedThreshold = 0.01;

		AnimationController<T> controller = tAnimationState.getController();

		if (!this.onGround()) {
			controller.setAnimation(RawAnimation.begin().then("flap", Animation.LoopType.LOOP));
			controller.setAnimationSpeed(1.5);
		} else if (isMoving) {
			if (currentSpeed > speedThreshold) {
				controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(1.1);
			} else {
				controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(1.1);
			}
		} else {
			controller.setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
			controller.setAnimationSpeed(0.8);
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

	public void aiStep() {
		super.aiStep();

		Vec3 vec3 = this.getDeltaMovement();
		if (!this.onGround() && vec3.y < 0.0D) {
			this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));
		}

		if (!LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
			if (!this.level().isClientSide && this.isAlive() && !this.isBaby() && !this.isChickenJockey() && --this.eggTime <= 0 && (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() || (LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() && this.isFemale()))) {

				if (this.getBreed() == 0) {
					this.spawnAtLocation(LOItems.EGG.get());
				}

				if (this.getBreed() == 1) {
					this.spawnAtLocation(LOItems.AMERAUCANA_EGG.get());
				}

				if (this.getBreed() == 2) {
					this.spawnAtLocation(LOItems.CREAM_LEGBAR_EGG.get());
				}

				if (this.getBreed() == 3) {
					this.spawnAtLocation(LOItems.MARANS_EGG.get());
				}

				if (this.getBreed() == 4) {
					this.spawnAtLocation(LOItems.OLIVE_EGGER_EGG.get());
				}

				if (this.getBreed() == 5) {
					this.spawnAtLocation(LOItems.SUSSEX_SILKIE_EGG.get());
				}

				if (this.getBreed() == 6) {
					this.spawnAtLocation(LOItems.AYAM_CEMANI_EGG.get());
				}

				this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
				this.eggTime = this.random.nextInt(LivestockOverhaulCommonConfig.CHICKEN_EGG_LAY_TIME.get()) + 6000;
			}
		} else if (!this.level().isClientSide && this.isAlive() && !this.isBaby() && !this.isChickenJockey() && --this.eggTime <= 0 && (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() || (LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() && this.isFemale()))) {

			this.spawnAtLocation(Items.EGG);

			this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
			this.eggTime = this.random.nextInt(LivestockOverhaulCommonConfig.CHICKEN_EGG_LAY_TIME.get()) + 6000;
		}

	}

	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		Item item = itemstack.getItem();

		if (item instanceof BrandTagItem) {
			setTagged(true);
			this.playSound(SoundEvents.SHEEP_SHEAR, 0.5f, 1f);
			BrandTagItem tagItem = (BrandTagItem)item;
			DyeColor color = tagItem.getColor();
			if (color != this.getBrandTagColor()) {
				this.setBrandTagColor(color);
				if (!player.getAbilities().instabuild) {
					itemstack.shrink(1);
					return InteractionResult.sidedSuccess(this.level().isClientSide);
				}
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
		}

		if (itemstack.is(Items.SHEARS) && player.isShiftKeyDown()) {
			if (this.isTagged()) {
				this.setTagged(false);
				this.playSound(SoundEvents.SHEEP_SHEAR, 0.5f, 1f);
				return InteractionResult.sidedSuccess(this.level().isClientSide);
			}
		}

		if (itemstack.is(LOItems.GENDER_TEST_STRIP.get()) && this.isFemale()) {
			player.playSound(SoundEvents.BEEHIVE_EXIT, 1.0F, 1.0F);
			ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, LOItems.FEMALE_GENDER_TEST_STRIP.get().getDefaultInstance());
			player.setItemInHand(hand, itemstack1);
			return InteractionResult.SUCCESS;
		} else if (itemstack.is(LOItems.GENDER_TEST_STRIP.get()) && this.isMale()) {
			player.playSound(SoundEvents.BEEHIVE_EXIT, 1.0F, 1.0F);
			ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, LOItems.MALE_GENDER_TEST_STRIP.get().getDefaultInstance());
			player.setItemInHand(hand, itemstack1);
			return InteractionResult.SUCCESS;
		} else {
			return super.mobInteract(player, hand);
		}
	}

	public SoundEvent getAmbientSound() {
		super.getAmbientSound();
		return SoundEvents.CHICKEN_AMBIENT;
	}

	public SoundEvent getDeathSound() {
		super.getDeathSound();
		return SoundEvents.CHICKEN_DEATH;
	}

	public SoundEvent getHurtSound(DamageSource p_30720_) {
		super.getHurtSound(p_30720_);
		return SoundEvents.CHICKEN_HURT;
	}

	public void playStepSound(BlockPos p_28254_, BlockState p_28255_) {
		this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
	}

	public boolean causeFallDamage(float p_148875_, float p_148876_, DamageSource p_148877_) {
		return false;
	}

	public boolean isFood(ItemStack p_28271_) {
		return FOOD_ITEMS.test(p_28271_);
	}

	public int getExperienceReward() {
		return this.isChickenJockey() ? 10 : super.getExperienceReward();
	}

	// Generates the base texture
	public ResourceLocation getTextureResource() {
		return OChickenModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
	}

	public ResourceLocation getOverlayLocation() {
		return OChickenMarkingLayer.Overlay.overlayFromOrdinal(getOverlayVariant()).resourceLocation;
	}

	public ResourceLocation getModelResource() {
		return ChickenBreed.Breed.breedFromOrdinal(getBreed()).resourceLocation;
	}

	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OChicken.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(OChicken.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> BREED = SynchedEntityData.defineId(OChicken.class, EntityDataSerializers.INT);

	public int getVariant() {
		return this.entityData.get(VARIANT);
	}
	public int getOverlayVariant() {
		return this.entityData.get(OVERLAY);
	}
	public int getBreed() {
		return this.entityData.get(BREED);
	}

	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
	}
	public void setOverlayVariant(int overlayVariant) {
		this.entityData.set(OVERLAY, overlayVariant);
	}
	public void setBreed(int breed) {
		this.entityData.set(BREED, breed);
	}

	protected static final EntityDataAccessor<Integer> BRAND_TAG_COLOR = SynchedEntityData.defineId(OChicken.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> TAGGED = SynchedEntityData.defineId(OChicken.class, EntityDataSerializers.BOOLEAN);
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
	public void equipTag(@javax.annotation.Nullable SoundSource soundSource) {
		if(soundSource != null) {
			this.level().playSound(null, this, SoundEvents.BOOK_PAGE_TURN, soundSource, 0.5f, 1f);
		}
	}
	@Override
	public boolean isTagged() {
		return this.entityData.get(TAGGED);
	}
	public void setTagged(boolean tagged) {
		this.entityData.set(TAGGED, tagged);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);

		if (tag.contains("Variant")) {
			setVariant(tag.getInt("Variant"));
		}

		if (tag.contains("Overlay")) {
			setOverlayVariant(tag.getInt("Overlay"));
		}

		if (tag.contains("Breed")) {
			setBreed(tag.getInt("Breed"));
		}

		if (tag.contains("Gender")) {
			setGender(tag.getInt("Gender"));
		}

		this.isChickenJockey = tag.getBoolean("IsChickenJockey");

		if (tag.contains("EggLayTime")) {
			this.eggTime = tag.getInt("EggLayTime");
		}

		if(tag.contains("Tagged")) {
			this.setTagged(tag.getBoolean("Tagged"));
		}

		this.setBrandTagColor(DyeColor.byId(tag.getInt("BrandTagColor")));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Variant", getVariant());
		tag.putInt("Overlay", getOverlayVariant());
		tag.putInt("Breed", getBreed());
		tag.putInt("Gender", getGender());
		tag.putBoolean("IsChickenJockey", this.isChickenJockey);
		tag.putInt("EggLayTime", this.eggTime);
		tag.putBoolean("Tagged", this.isTagged());
		tag.putByte("BrandTagColor", (byte)this.getBrandTagColor().getId());
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}
		Random random = new Random();
		this.setBreed(random.nextInt(ChickenBreed.Breed.values().length));
		this.setGender(random.nextInt(OChicken.Gender.values().length));

		if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
			this.setColorByBreed();
			this.setMarkingByBreed();
		} else {
			this.setVariant(random.nextInt(OChickenModel.Variant.values().length));
			this.setOverlayVariant(random.nextInt(OChickenMarkingLayer.Overlay.values().length));
		}

		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	public void setColorByBreed() {

		if (this.getBreed() == 0) { //leghorn
			if (random.nextDouble() < 0.10) {
				this.setVariant(random.nextInt(OChickenModel.Variant.values().length));
			} else if (random.nextDouble() > 0.10 && random.nextDouble() < 0.15) {
				int[] variants = {0, 2, 4, 5, 9};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.15) {
				this.setVariant(12);
			}
		}

		if (this.getBreed() == 1) { //ameraucana
			if (random.nextDouble() < 0.10) {
				this.setVariant(random.nextInt(OChickenModel.Variant.values().length));
			} else if (random.nextDouble() > 0.10 && random.nextDouble() < 0.15) {
				int[] variants = {0, 2, 5, 8};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.15) {
				int[] variants = {1, 6, 9};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 2) { //cream legbar
			if (random.nextDouble() < 0.10) {
				this.setVariant(random.nextInt(OChickenModel.Variant.values().length));
			} else if (random.nextDouble() > 0.10 && random.nextDouble() < 0.15) {
				int[] variants = {6, 9};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.15) {
				int[] variants = {2, 3};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 3) { //marans
			if (random.nextDouble() < 0.10) {
				this.setVariant(random.nextInt(OChickenModel.Variant.values().length));
			} else if (random.nextDouble() > 0.10) {
				int[] variants = {0, 2, 7, 8};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 4) { //olive egger
			if (random.nextDouble() < 0.10) {
				this.setVariant(random.nextInt(OChickenModel.Variant.values().length));
			} else if (random.nextDouble() > 0.10 && random.nextDouble() < 0.15) {
				this.setVariant(1);
			} else if (random.nextDouble() > 0.15) {
				int[] variants = {0, 9};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 5) { //sussex silkie
			if (random.nextDouble() < 0.10) {
				this.setVariant(random.nextInt(OChickenModel.Variant.values().length));
			} else if (random.nextDouble() > 0.10 && random.nextDouble() < 0.15) {
				this.setVariant(10);
			} else if (random.nextDouble() > 0.15) {
				int[] variants = {4, 5, 12};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 6) { //ayam cemani
			this.setVariant(11);
		}

	}

	public void setMarkingByBreed() {

		if (this.getBreed() == 0) { //leghorn
			if (random.nextDouble() < 0.10) {
				this.setOverlayVariant(random.nextInt(OChickenMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.10 && random.nextDouble() < 0.25) {
				this.setOverlayVariant(0);
			} else if (random.nextDouble() > 0.25) {
				this.setOverlayVariant(47);
			}
		}

		if (this.getBreed() == 1) { //ameraucana
			if (random.nextDouble() < 0.10) {
				this.setOverlayVariant(random.nextInt(OChickenMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.10 && random.nextDouble() < 0.25) {
				this.setOverlayVariant(0);
			} else if (random.nextDouble() > 0.25) {
				int[] variants = {33, 34, 35, 36};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 2) { //cream legbar
			if (random.nextDouble() < 0.10) {
				this.setOverlayVariant(random.nextInt(OChickenMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.10 && random.nextDouble() < 0.25) {
				this.setOverlayVariant(0);
			} else if (random.nextDouble() > 0.25) {
				int[] variants = {33, 34, 35, 36};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 3) { //marans
			if (random.nextDouble() < 0.10) {
				this.setOverlayVariant(random.nextInt(OChickenMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.10) {
				int[] variants = {9, 13, 14, 15, 21, 25, 29, 30, 31, 32, 33};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 4) { //olive egger
			if (random.nextDouble() < 0.10) {
				this.setOverlayVariant(random.nextInt(OChickenMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.10 && random.nextDouble() < 0.25) {
				int[] variants = {34, 35, 36};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.25) {
				this.setOverlayVariant(0);
			}
		}

		if (this.getBreed() == 5) { //sussex silkie
			if (random.nextDouble() < 0.10) {
				this.setOverlayVariant(random.nextInt(OChickenMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.10 && random.nextDouble() < 0.25) {
				int[] variants = {2, 4};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.25) {
				this.setOverlayVariant(0);
			}
		}

		if (this.getBreed() == 6) { //ayam cemani
			this.setOverlayVariant(0);
		}

	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
		this.entityData.define(BREED, 0);
		this.entityData.define(GENDER, 0);
		this.entityData.define(BRAND_TAG_COLOR, DyeColor.YELLOW.getId());
		this.entityData.define(TAGGED, false);
	}

	public boolean canParent() {
		return !this.isBaby() && this.isInLove();
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
	public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(OChicken.class, EntityDataSerializers.INT);
	public int getGender() {
		return this.entityData.get(GENDER);
	}
	public void setGender(int gender) {
		this.entityData.set(GENDER, gender);
	}

	public boolean canMate(Animal animal) {
		if (animal == this) {
			return false;
		} else if (!(animal instanceof OChicken)) {
			return false;
		} else {
			if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
				return this.canParent() && ((OChicken) animal).canParent();
			} else {
				OChicken partner = (OChicken) animal;
				if (this.canParent() && partner.canParent() && this.getGender() != partner.getGender()) {
					return isFemale();
				}
			}
		}
		return false;
	}

	public int eggsLaid = 0;
	public int eggLayCooldown = 0;

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {

		if ((this.isMale() && LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get())
				|| !this.isInLove()
				|| !this.isAlive()
				|| eggsLaid >= LivestockOverhaulCommonConfig.CHICKEN_EGG_LAY_AMOUNT.get()) {
			return null;
		}

		eggsLaid++;
		dropFertilizedEgg(serverLevel);
		return null;
	}

	public void tick() {
		super.tick();

		if (eggsLaid >= LivestockOverhaulCommonConfig.CHICKEN_EGG_LAY_AMOUNT.get() && eggLayCooldown >= 0) {
			this.resetLove();
			eggsLaid = 0;
			eggLayCooldown = 0;
		}
	}

	protected void dropFertilizedEgg(ServerLevel serverLevel) {
		if (!this.isFemale() && LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
			return;
		}

		int rareChickenChance = this.random.nextInt(48);

		if ((this.isFemale() && LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) || !LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
			if (this.getBreed() == 0) {
				ItemStack fertilizedEgg = new ItemStack(LOItems.FERTILIZED_EGG.get());
				ItemEntity eggEntity = new ItemEntity(serverLevel, this.getX(), this.getY(), this.getZ(), fertilizedEgg);
				serverLevel.addFreshEntity(eggEntity);
			}

			if (this.getBreed() == 1) {
				ItemStack fertilizedEgg = new ItemStack(LOItems.FERTILIZED_AMERAUCANA_EGG.get());
				ItemEntity eggEntity = new ItemEntity(serverLevel, this.getX(), this.getY(), this.getZ(), fertilizedEgg);
				serverLevel.addFreshEntity(eggEntity);
			}

			if (this.getBreed() == 2) {
				ItemStack fertilizedEgg = new ItemStack(LOItems.FERTILIZED_CREAM_LEGBAR_EGG.get());
				ItemEntity eggEntity = new ItemEntity(serverLevel, this.getX(), this.getY(), this.getZ(), fertilizedEgg);
				serverLevel.addFreshEntity(eggEntity);
			}

			if (this.getBreed() == 3) {
				ItemStack fertilizedEgg = new ItemStack(LOItems.FERTILIZED_MARANS_EGG.get());
				ItemEntity eggEntity = new ItemEntity(serverLevel, this.getX(), this.getY(), this.getZ(), fertilizedEgg);
				serverLevel.addFreshEntity(eggEntity);
			}

			if (this.getBreed() == 4) {
				ItemStack fertilizedEgg = new ItemStack(LOItems.FERTILIZED_OLIVE_EGGER_EGG.get());
				ItemEntity eggEntity = new ItemEntity(serverLevel, this.getX(), this.getY(), this.getZ(), fertilizedEgg);
				serverLevel.addFreshEntity(eggEntity);
			}

			if (this.getBreed() == 5) {
				ItemStack fertilizedEgg = new ItemStack(LOItems.FERTILIZED_SUSSEX_SILKIE_EGG.get());
				ItemEntity eggEntity = new ItemEntity(serverLevel, this.getX(), this.getY(), this.getZ(), fertilizedEgg);
				serverLevel.addFreshEntity(eggEntity);
			}

			if (this.getBreed() == 6) {
				ItemStack fertilizedEgg = new ItemStack(LOItems.FERTILIZED_AYAM_CEMANI_EGG.get());
				ItemEntity eggEntity = new ItemEntity(serverLevel, this.getX(), this.getY(), this.getZ(), fertilizedEgg);
				serverLevel.addFreshEntity(eggEntity);
			}

			if (rareChickenChance <= 1) {
				ItemStack fertilizedEgg = new ItemStack(LOItems.FERTILIZED_AYAM_CEMANI_EGG.get());
				ItemEntity eggEntity = new ItemEntity(serverLevel, this.getX(), this.getY(), this.getZ(), fertilizedEgg);
				serverLevel.addFreshEntity(eggEntity);
				eggsLaid = 3;
			}
		}

		serverLevel.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.CHICKEN_EGG, SoundSource.NEUTRAL, 1.0F, 1.0F);
	}


	public boolean removeWhenFarAway(double p_28266_) {
		return this.isChickenJockey();
	}

	public void positionRider(Entity p_289537_, Entity.MoveFunction p_289541_) {
		super.positionRider(p_289537_, p_289541_);
		float f = Mth.sin(this.yBodyRot * ((float)Math.PI / 180F));
		float f1 = Mth.cos(this.yBodyRot * ((float)Math.PI / 180F));
		p_289541_.accept(p_289537_, this.getX() + (double)(0.1F * f), this.getY(0.15D) + p_289537_.getMyRidingOffset() + 0.0D, this.getZ() - (double)(0.1F * f1));
		if (p_289537_ instanceof LivingEntity) {
			((LivingEntity)p_289537_).yBodyRot = this.yBodyRot;
		}

	}

	public boolean isChickenJockey() {
		return this.isChickenJockey;
	}

	public void setChickenJockey(boolean p_28274_) {
		this.isChickenJockey = p_28274_;
	}

	@Override
	public void dropCustomDeathLoot(DamageSource p_33574_, int p_33575_, boolean p_33576_) {
		super.dropCustomDeathLoot(p_33574_, p_33575_, p_33576_);
		Random random = new Random();

		if (!LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get() || !ModList.get().isLoaded("tfc")) {
			if (this.isMeatBreed()) {
				if (random.nextDouble() < 0.40) {
					this.spawnAtLocation(Items.CHICKEN, 2);
					this.spawnAtLocation(Items.FEATHER, 2);
				} else if (random.nextDouble() > 0.40) {
					this.spawnAtLocation(Items.CHICKEN);
					this.spawnAtLocation(Items.FEATHER);
				}
			}

			if (this.isNormalBreed()) {
				if (random.nextDouble() < 0.15) {
					this.spawnAtLocation(Items.CHICKEN);
					this.spawnAtLocation(Items.FEATHER);
				}
			}

		}
	}
}
