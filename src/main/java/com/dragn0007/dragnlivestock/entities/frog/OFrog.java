package com.dragn0007.dragnlivestock.entities.frog;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.ai.FrogSitOnBlockGoal;
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
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
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
import java.util.Random;

public class OFrog extends Animal implements GeoEntity {

	public OFrog(EntityType<? extends OFrog> type, Level level) {
		super(type, level);
	}

	private static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_frog");
	private static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/frog");
	private static final ResourceLocation TFC_LOOT_TABLE = new ResourceLocation("tfc", "entities/frog");
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

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0D, (double)this.getEyeHeight() * 0.8F, (double)(this.getBbWidth() * 0.9F));
		//              ^ Side offset                      ^ Height offset                   ^ Length offset
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 10.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.18D);
	}

	public static final Ingredient FOOD_ITEMS = Ingredient.of(LOTags.Items.O_FROG_EATS);

	public void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, FOOD_ITEMS, false));
		this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(7, new FrogSitOnBlockGoal(this, 0.8D));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity ->
				livingEntity.getType().is(LOTags.Entity_Types.WOLVES))
		);

		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity ->
				livingEntity.getType().is(LOTags.Entity_Types.CATS))
		);

		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity ->
				livingEntity.getType().is(LOTags.Entity_Types.HUNTING_DOGS))
		);

		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity ->
				livingEntity.getType().is(LOTags.Entity_Types.FOXES))
		);
	}

	@Override
	public boolean hurt(DamageSource damageSource, float v) {
		if (damageSource.is(DamageTypes.DROWN)) {
			return false;
		}
		return super.hurt(damageSource, v);
	}

	@Override
	public float getStepHeight() {
		return 2F;
	}

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	private <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
		double currentSpeed = this.getDeltaMovement().lengthSqr();
		double speedThreshold = 0.01;

		AnimationController<T> controller = tAnimationState.getController();

		if (tAnimationState.isMoving()) {
			if (currentSpeed > speedThreshold) {
				controller.setAnimation(RawAnimation.begin().then("hop", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(2.0);
			} else {
				controller.setAnimation(RawAnimation.begin().then("hop", Animation.LoopType.LOOP));
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
		super.getAmbientSound();
		return SoundEvents.FROG_AMBIENT;
	}

	public SoundEvent getDeathSound() {
		super.getDeathSound();
		return SoundEvents.FROG_DEATH;
	}

	public SoundEvent getHurtSound(DamageSource p_30720_) {
		super.getHurtSound(p_30720_);
		return SoundEvents.FROG_HURT;
	}

	public void playStepSound(BlockPos p_28254_, BlockState p_28255_) {
		this.playSound(SoundEvents.FROG_STEP, 0.15F, 1.0F);
	}

	public boolean isFood(ItemStack p_28271_) {
		return FOOD_ITEMS.test(p_28271_);
	}

	public boolean isBaby() {
		return false;
	}

	public void setBaby(boolean p_218500_) {
	}

	// Generates the base texture
	public ResourceLocation getTextureLocation() {
		return OFrogModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
	}

	public ResourceLocation getOverlayLocation() {
		return OFrogMarkingLayer.Overlay.overlayFromOrdinal(getOverlayVariant()).resourceLocation;
	}

	public ResourceLocation getEyesLocation() {
		return OFrogEyeLayer.Overlay.overlayFromOrdinal(getEyesVariant()).resourceLocation;
	}

	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OFrog.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(OFrog.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> EYES = SynchedEntityData.defineId(OFrog.class, EntityDataSerializers.INT);

	public int getVariant() {
		return this.entityData.get(VARIANT);
	}
	public int getOverlayVariant() {
		return this.entityData.get(OVERLAY);
	}
	public int getEyesVariant() {
		return this.entityData.get(EYES);
	}

	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
	}
	public void setOverlayVariant(int overlayVariant) {
		this.entityData.set(OVERLAY, overlayVariant);
	}
	public void setEyesVariant(int eyesVariant) {
		this.entityData.set(EYES, eyesVariant);
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

		if (tag.contains("Eyes")) {
			setEyesVariant(tag.getInt("Eyes"));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Variant", getVariant());

		tag.putInt("Overlay", getOverlayVariant());

		tag.putInt("Eyes", getEyesVariant());
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}
		Random random = new Random();
		setVariant(random.nextInt(OFrogModel.Variant.values().length));
		setOverlayVariant(random.nextInt(OFrogMarkingLayer.Overlay.values().length));
		setEyesVariant(random.nextInt(OFrogEyeLayer.Overlay.values().length));

		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
		this.entityData.define(EYES, 0);
	}

	public boolean canParent() {
		return !this.isBaby() && this.isInLove();
	}

	public boolean canMate(Animal animal) {
		if (animal == this) {
			return false;
		} else
			return animal instanceof OFrog;
	}

	public int eggsLaid = 0;
	public int eggLayCooldown = 0;

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {

		if (!this.isInLove() || !this.isAlive() || eggsLaid >= 2) {
			return null;
		}

		eggsLaid++;
		dropFertilizedEgg(serverLevel);
		return null;
	}

	public void tick() {
		super.tick();

		if (eggsLaid >= 2 && eggLayCooldown >= 100) {
			eggsLaid = 0;
			eggLayCooldown = 0;
		}
	}

	private void dropFertilizedEgg(ServerLevel serverLevel) {

		ItemStack fertilizedEgg = new ItemStack(Items.FROGSPAWN);
		ItemEntity eggEntity = new ItemEntity(serverLevel, this.getX(), this.getY(), this.getZ(), fertilizedEgg);
		serverLevel.addFreshEntity(eggEntity);

		serverLevel.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.FROG_LAY_SPAWN, SoundSource.NEUTRAL, 1.0F, 1.0F);
	}


}
