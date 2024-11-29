package com.dragn0007.dragnlivestock.entities.bee;

import com.dragn0007.dragnlivestock.entities.EntityTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class OBee extends Bee implements GeoEntity {

	public OBee(EntityType<? extends Bee> entityType, Level level) {
		super(entityType, level);
	}

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {

		AnimationController<T> controller = tAnimationState.getController();

		if(tAnimationState.isMoving()) {
				controller.setAnimation(RawAnimation.begin().then("flap", Animation.LoopType.LOOP));
		} else {
				if (!tAnimationState.isMoving() && !this.onGround()) {
					controller.setAnimation(RawAnimation.begin().then("idle_flap", Animation.LoopType.LOOP));
				} else {
					controller.setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
				}
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

	public ResourceLocation getTextureResource() {
		return OBeeModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
	}

	protected static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OBee.class, EntityDataSerializers.INT);

	public int getVariant() {
		return this.entityData.get(VARIANT);
	}

	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
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
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, 0);
	}

	public OBee getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		return EntityTypes.O_BEE_ENTITY.get().create(serverLevel);
	}
}
