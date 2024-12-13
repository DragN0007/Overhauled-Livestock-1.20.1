package com.dragn0007.dragnlivestock.entities.chicken;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.items.LOItems;
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
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
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

public class OChicken extends Animal implements GeoEntity {

	public int eggTime = this.random.nextInt(6000) + 6000;
	public boolean isChickenJockey;

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0D, (double)this.getEyeHeight() * 0.8F, (double)(this.getBbWidth() * 0.4F));
		//              ^ Side offset                      ^ Height offset                   ^ Length offset
	}

	public OChicken(EntityType<? extends OChicken> type, Level level) {
		super(type, level);
	}

	private static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_chicken");
	private static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/chicken");
	@Override
	public @NotNull ResourceLocation getDefaultLootTable() {
		if (LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
			return VANILLA_LOOT_TABLE;
		}
		return LOOT_TABLE;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 4.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.16F);
	}

	public static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);

	public void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.8F));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity -> {
			boolean isOWolf = livingEntity.getType().is(LOTags.Entity_Types.WOLVES);
			boolean isOCat = livingEntity.getType().is(LOTags.Entity_Types.CATS);
			boolean isWolf = livingEntity instanceof Wolf;
			return isOWolf || isWolf || isOCat;
		}));
	}

	@Override
	public float getStepHeight() {
		return 1F;
	}

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
		double currentSpeed = this.getDeltaMovement().lengthSqr();
		double speedThreshold = 0.01;

		AnimationController<T> controller = tAnimationState.getController();

		if (!onGround()) {
			controller.setAnimation(RawAnimation.begin().then("flap", Animation.LoopType.LOOP));
		}

		if (tAnimationState.isMoving()) {
			if (currentSpeed > speedThreshold) {
				controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
			} else {
				controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
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

	public void aiStep() {
		super.aiStep();

		Vec3 vec3 = this.getDeltaMovement();
		if (!this.onGround() && vec3.y < 0.0D) {
			this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));
		}

		if (!this.level().isClientSide && this.isAlive() && !this.isBaby() && !this.isChickenJockey() && --this.eggTime <= 0 && (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() || (LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() && !this.getTextureResource().equals(OChickenModel.Variant.ROOSTER.resourceLocation)))) {
			this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
			this.spawnAtLocation(LOItems.EGG.get());
			this.eggTime = this.random.nextInt(6000) + 6000;
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

	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OChicken.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(OChicken.class, EntityDataSerializers.INT);

	public int getVariant() {
		return this.entityData.get(VARIANT);
	}
	public int getOverlayVariant() {
		return this.entityData.get(OVERLAY);
	}

	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
	}
	public void setOverlayVariant(int overlayVariant) {
		this.entityData.set(OVERLAY, overlayVariant);
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

		this.isChickenJockey = tag.getBoolean("IsChickenJockey");
		if (tag.contains("EggLayTime")) {
			this.eggTime = tag.getInt("EggLayTime");
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Variant", getVariant());

		tag.putInt("Overlay", getOverlayVariant());

		tag.putBoolean("IsChickenJockey", this.isChickenJockey);

		tag.putInt("EggLayTime", this.eggTime);
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}
		Random random = new Random();
		setVariant(random.nextInt(OChickenModel.Variant.values().length));
		setOverlayVariant(random.nextInt(OChickenMarkingLayer.Overlay.values().length));

		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
	}

	public boolean canParent() {
		return !this.isBaby() && this.isInLove();
	}

	boolean isFemale = !this.getTextureResource().equals(OChickenModel.Variant.ROOSTER.resourceLocation);
	boolean isMale = this.getTextureResource().equals(OChickenModel.Variant.ROOSTER.resourceLocation);

	private static final int COOLDOWN_TIME = 100;
	private int cooldownTicks = 0;
	private boolean eggDropped = false;
	private boolean hasBred = false;
	private int eggsLaid = 0;
	private static final int MAX_EGGS = 4;

	@Override
	public boolean canMate(Animal animal) {
		if (animal == this || !(animal instanceof OChicken)) {
			return false;
		}

		OChicken partner = (OChicken) animal;

		if (!this.canParent() || !partner.canParent()) {
			return false;
		}

		if (hasBred) {
			return false;
		}

		boolean partnerIsFemale = !partner.getTextureResource().equals(OChickenModel.Variant.ROOSTER.resourceLocation);
		boolean partnerIsMale = partner.getTextureResource().equals(OChickenModel.Variant.ROOSTER.resourceLocation);

		boolean isFemale = !this.getTextureResource().equals(OChickenModel.Variant.ROOSTER.resourceLocation);
		boolean isMale = this.getTextureResource().equals(OChickenModel.Variant.ROOSTER.resourceLocation);

		if (LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
			return (isFemale && partnerIsMale) || (isMale && partnerIsFemale);
		} else {
			return true;
		}
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		boolean isFemale = !this.getTextureResource().equals(OChickenModel.Variant.ROOSTER.resourceLocation);
		boolean isMale = this.getTextureResource().equals(OChickenModel.Variant.ROOSTER.resourceLocation);

		if (isMale || eggDropped || cooldownTicks > 0 || eggsLaid >= MAX_EGGS) {
			return null;
		}

		if (!isFemale) {
			return null;
		}

		dropFertilizedEgg(serverLevel);
		eggDropped = true;
		cooldownTicks = COOLDOWN_TIME;
		hasBred = true;

		return null;
	}

	@Override
	public void tick() {
		if (cooldownTicks > 0) {
			cooldownTicks--;

			if (cooldownTicks == 0) {
				eggDropped = false;
				hasBred = false;
				eggsLaid = 0;
			}
		}

		super.tick();
	}

	private void dropFertilizedEgg(ServerLevel serverLevel) {
		boolean isFemale = !this.getTextureResource().equals(OChickenModel.Variant.ROOSTER.resourceLocation);
		boolean isMale = this.getTextureResource().equals(OChickenModel.Variant.ROOSTER.resourceLocation);

		if (!isFemale || eggsLaid >= MAX_EGGS) {
			return;
		}

		if (isMale) {
			return;
		}

		ItemStack fertilizedEgg = new ItemStack(LOItems.FERTILIZED_EGG.get());
		ItemEntity eggEntity = new ItemEntity(serverLevel, this.getX(), this.getY(), this.getZ(), fertilizedEgg);
		serverLevel.addFreshEntity(eggEntity);

		serverLevel.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.CHICKEN_EGG, SoundSource.NEUTRAL, 1.0F, 1.0F);

		eggsLaid++;
	}


	public boolean removeWhenFarAway(double p_28266_) {
		return this.isChickenJockey();
	}

	protected void positionRider(Entity p_289537_, Entity.MoveFunction p_289541_) {
		super.positionRider(p_289537_, p_289541_);
		float f = Mth.sin(this.yBodyRot * ((float)Math.PI / 180F));
		float f1 = Mth.cos(this.yBodyRot * ((float)Math.PI / 180F));
		float f2 = 0.1F;
		float f3 = 0.0F;
		p_289541_.accept(p_289537_, this.getX() + (double)(0.1F * f), this.getY(0.5D) + p_289537_.getMyRidingOffset() + 0.0D, this.getZ() - (double)(0.1F * f1));
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
}
