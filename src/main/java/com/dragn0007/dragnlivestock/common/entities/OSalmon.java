package com.dragn0007.dragnlivestock.common.entities;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.blocks.client.render.model.entity.OSalmonModel;
import com.dragn0007.dragnlivestock.common.entities.util.AbstractSchoolingOFish;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.material.Fluids;
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

public class OSalmon extends AbstractSchoolingOFish implements GeoEntity {

	public OSalmon(EntityType<? extends OSalmon> type, Level level) {
		super(type, level);
	}

	private static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/salmon");
	private static final ResourceLocation TFC_LOOT_TABLE = LivestockOverhaul.id("entities/tfc/tfc_o_salmon");
	@Override
	public @NotNull ResourceLocation getDefaultLootTable() {
		if (ModList.get().isLoaded("tfc")) {
			return TFC_LOOT_TABLE;
		}
		return VANILLA_LOOT_TABLE;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 3.0D);
	}

	public OSalmon getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		return EntityTypes.O_SALMON_ENTITY.get().create(serverLevel);
	}

	public int getMaxSchoolSize() {
		return 5;
	}

	public ItemStack getBucketItemStack() {
		return new ItemStack(Items.SALMON_BUCKET);
	}

	public SoundEvent getAmbientSound() {
		return SoundEvents.SALMON_AMBIENT;
	}

	public SoundEvent getDeathSound() {
		return SoundEvents.SALMON_DEATH;
	}

	public SoundEvent getHurtSound(DamageSource p_29795_) {
		return SoundEvents.SALMON_HURT;
	}

	public SoundEvent getFlopSound() {
		return SoundEvents.SALMON_FLOP;
	}

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
		double currentSpeed = this.getDeltaMovement().lengthSqr();
		double speedThreshold = 0.01;

		AnimationController<T> controller = tAnimationState.getController();

		if(tAnimationState.isMoving()) {
			if (currentSpeed > speedThreshold) {
				controller.setAnimation(RawAnimation.begin().then("swim_sprint", Animation.LoopType.LOOP));
			} else if(currentSpeed < speedThreshold) {
				controller.setAnimation(RawAnimation.begin().then("swim", Animation.LoopType.LOOP));
			} else {
				controller.setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
			}
		}

		if(!this.isInFluidType(Fluids.WATER.getFluidType())) {
			controller.setAnimation(RawAnimation.begin().then("flop", Animation.LoopType.LOOP));
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

	// Generates the base texture
	public ResourceLocation getTextureResource() {
		return OSalmonModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
	}

	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OSalmon.class, EntityDataSerializers.INT);

	public int getVariant() {
		return this.entityData.get(VARIANT);
	}

	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData groupData, @Nullable CompoundTag tag) {
		if (groupData == null) {
			groupData = new AgeableMob.AgeableMobGroupData(0.2F);
		}

		Random random = new Random();
		setVariant(random.nextInt(OSalmonModel.Variant.values().length));

		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, groupData, tag);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);

		if (tag.contains("Variant")) {
			setVariant(tag.getInt("Variant"));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Variant", getVariant());
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, 0);
	}
}
