package com.dragn0007.dragnlivestock.entities.bee;

import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.Random;

public class OBee extends Bee implements GeoEntity {

	public OBee(EntityType<? extends Bee> entityType, Level level) {
		super(entityType, level);
	}

	protected final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	protected <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
		double x = this.getX() - this.xo;
		double z = this.getZ() - this.zo;
		boolean isMoving = (x * x + z * z) > 0.0001;

		AnimationController<T> controller = tAnimationState.getController();

		if(isMoving) {
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
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemStack = player.getItemInHand(hand);

		if (itemStack.is(LOItems.COAT_OSCILLATOR.get()) && player.getAbilities().instabuild) {
			if (player.isShiftKeyDown()) {
				this.setVariant(this.getVariant() - 1);
				this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
				return InteractionResult.sidedSuccess(this.level().isClientSide);
			}
			this.setVariant(this.getVariant() + 1);
			this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		} else {
			return super.mobInteract(player, hand);
		}
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}

	public ResourceLocation getTextureResource() {
		return OBeeModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
	}

	@Override
	public void finalizeSpawnChildFromBreeding(ServerLevel pLevel, Animal pAnimal, @org.jetbrains.annotations.Nullable AgeableMob pBaby) {
		super.finalizeSpawnChildFromBreeding(pLevel, pAnimal, pBaby);
		if (pAnimal instanceof OBee partner) {
			this.setAge(LivestockOverhaulCommonConfig.FEMALE_COOLDOWN.get());
			partner.setAge(LivestockOverhaulCommonConfig.FEMALE_COOLDOWN.get());
		}
	}

	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OBee.class, EntityDataSerializers.INT);

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
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}
		Random random = new Random();
		setVariant(random.nextInt(OBeeModel.Variant.values().length));

		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, 0);
	}

	@Override
	public OBee getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		OBee oBee = (OBee) ageableMob;
		if (ageableMob instanceof OBee) {
			OBee mob = (OBee) ageableMob;
			oBee = EntityTypes.O_BEE_ENTITY.get().create(serverLevel);

			int i = this.random.nextInt(9);
			int variant;
			if (i < 4) {
				variant = this.getVariant();
			} else if (i < 8) {
				variant = mob.getVariant();
			} else {
				variant = this.random.nextInt(OBeeModel.Variant.values().length);
			}

			oBee.setVariant(variant);
		}

		return oBee;
	}

	public enum Breed {
		BEE,
		ASHY_MINING_BEE,
		GARDEN_BEE,
		HONEY_BEE,
		RED_MASON_BEE,
		RED_TAILED_BEE,
		TAWNY_MINING_BEE,
		TREE_BEE,
	}
}
