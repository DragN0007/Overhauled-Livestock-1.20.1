package com.dragn0007.dragnlivestock.entities.horse;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.ai.HorseFollowHerdLeaderGoal;
import com.dragn0007.dragnlivestock.entities.donkey.ODonkey;
import com.dragn0007.dragnlivestock.entities.mule.OMule;
import com.dragn0007.dragnlivestock.entities.mule.OMuleModel;
import com.dragn0007.dragnlivestock.entities.util.AbstractOHorse;
import com.dragn0007.dragnlivestock.entities.util.LOAnimations;
import com.dragn0007.dragnlivestock.util.LONetwork;
import com.dragn0007.dragnlivestock.util.LOTags;
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
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
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

public class OHorse extends AbstractOHorse implements GeoEntity {
	public static final EntityDataAccessor<ResourceLocation> VARIANT_TEXTURE = SynchedEntityData.defineId(OHorse.class, LivestockOverhaul.RESOURCE_LOCATION);
	public static final EntityDataAccessor<ResourceLocation> OVERLAY_TEXTURE = SynchedEntityData.defineId(OHorse.class, LivestockOverhaul.RESOURCE_LOCATION);
	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> BREED = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.INT);

	public OHorse leader;
	public int herdSize = 1;

	public OHorse(EntityType<? extends OHorse> type, Level level) {
		super(type, level);
	}

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0D, (double)this.getEyeHeight() * 0.8F, (double)(this.getBbWidth() * 1.2F));
		//              ^ Side offset                      ^ Height offset                   ^ Length offset
	}

	public static AttributeSupplier.Builder createBaseHorseAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.JUMP_STRENGTH)
				.add(Attributes.MAX_HEALTH, 53.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.255F)
				.add(Attributes.ATTACK_DAMAGE, 1D);
	}

	public void randomizeAttributes() {
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.generateRandomMaxHealth());
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.generateRandomSpeed());
		this.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(this.generateRandomJumpStrength());
//		this.getAttribute(LOAttributes.ENDURANCE).setBaseValue(this.generateRandomEndurance());
	}

	@Override
	public void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.7D));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.4, true));
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 0.0F));

		this.goalSelector.addGoal(3, new HorseFollowHerdLeaderGoal(this));
		this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D, AbstractOHorse.class));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, (livingEntity) -> livingEntity instanceof Wolf));
	}

	public float generateRandomMaxHealth() {
		float baseHealth;
		if (getModelResource().equals(BreedModel.STOCK.resourceLocation)) {
			baseHealth = 16.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(4);
		}
		if (getModelResource().equals(BreedModel.DRAFT.resourceLocation)) {
			baseHealth = 20.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(4);
		}
		if (getModelResource().equals(BreedModel.WARMBLOOD.resourceLocation)) {
			baseHealth = 16.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(4);
		}
		if (getModelResource().equals(BreedModel.PONY.resourceLocation)) {
			baseHealth = 14.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(4);
		}
		if (getModelResource().equals(BreedModel.RACER.resourceLocation)) {
			baseHealth = 13.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(4);
		}
		return 15.0F + (float) this.random.nextInt(4) + (float) this.random.nextInt(5);
	}

	public double generateRandomJumpStrength() {
		double baseStrength = 0.4F;
		double multiplier = this.random.nextDouble() * 0.2D + this.random.nextDouble() * 0.2D + this.random.nextDouble() * 0.2D;

		if (getModelResource().equals(BreedModel.STOCK.resourceLocation)) {
			baseStrength = 0.5F;
			return baseStrength + multiplier;
		}
		if (getModelResource().equals(BreedModel.DRAFT.resourceLocation)) {
			baseStrength = 0.3F;
			return baseStrength + multiplier;
		}
		if (getModelResource().equals(BreedModel.WARMBLOOD.resourceLocation)) {
			baseStrength = 0.4F;
			return baseStrength + multiplier;
		}
		if (getModelResource().equals(BreedModel.PONY.resourceLocation)) {
			baseStrength = 0.35F;
			return baseStrength + multiplier;
		}
		if (getModelResource().equals(BreedModel.RACER.resourceLocation)) {
			baseStrength = 0.35F;
			return baseStrength + multiplier;
		}
		return baseStrength + this.random.nextDouble() * 0.15D;
	}

	public double generateRandomSpeed() {
		double baseSpeed = 0.0F;
		double multiplier = (this.random.nextDouble() * 0.1D + this.random.nextDouble() * 0.1D + this.random.nextDouble() * 0.1D) * 0.25D;

		if (getModelResource().equals(BreedModel.STOCK.resourceLocation)) {
			baseSpeed = 0.2F;
			return baseSpeed + multiplier;
		}
		if (getModelResource().equals(BreedModel.DRAFT.resourceLocation)) {
			baseSpeed = 0.15F;
			return baseSpeed + multiplier;
		}
		if (getModelResource().equals(BreedModel.WARMBLOOD.resourceLocation)) {
			baseSpeed = 0.2F;
			return baseSpeed + multiplier;
		}
		if (getModelResource().equals(BreedModel.PONY.resourceLocation)) {
			baseSpeed = 0.15F;
			return baseSpeed + multiplier;
		}
		if (getModelResource().equals(BreedModel.RACER.resourceLocation)) {
			baseSpeed = 0.25F;
			return baseSpeed + multiplier;
		}
		return baseSpeed + multiplier;
	}

	public double generateRandomEndurance() {
		return ((double)0.45F + this.random.nextDouble() * 0.3D + this.random.nextDouble() * 0.3D + this.random.nextDouble() * 0.3D) * 0.25D;
	}

	@Override
	protected boolean canPerformRearing() {
		return false;
	}

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	private <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> state) {
		double movementSpeed = this.getAttributeBaseValue(Attributes.MOVEMENT_SPEED);
		double animationSpeed = Math.max(0.1, movementSpeed);

		AnimationController<T> controller = state.getController();

		if(this.isJumping() || !this.onGround()) {
			controller.setAnimation(RawAnimation.begin().then("jump", Animation.LoopType.PLAY_ONCE));
			controller.setAnimationSpeed(1.0);

		} else if(state.isMoving() && getControllingPassenger() != null) {
			if(this.isAggressive() || (this.isVehicle() && this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD))) {
				controller.setAnimation(RawAnimation.begin().then("sprint", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(Math.max(0.1, 0.82 * controller.getAnimationSpeed() + animationSpeed));

			} else if (this.isVehicle() && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(WALK_SPEED_MOD) && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD)) {
				controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(Math.max(0.1, 0.8 * controller.getAnimationSpeed() + animationSpeed));

			} else if (this.isOnSand() && this.isVehicle() && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(WALK_SPEED_MOD) && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD)) {
				controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(Math.max(0.1, 0.78 * controller.getAnimationSpeed() + animationSpeed));

			} else {
				controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(Math.max(0.1, 0.82 * controller.getAnimationSpeed() + animationSpeed));
			}
		} else {
			if (this.isVehicle()) {
				controller.setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
			} else {
				controller.setAnimation(RawAnimation.begin().then("idle3", Animation.LoopType.LOOP));
			}
			controller.setAnimationSpeed(1.0);
		}
		return PlayState.CONTINUE;
	}

	private <T extends GeoAnimatable> PlayState emotePredicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
		AnimationController<T> controller = tAnimationState.getController();

		if(tAnimationState.isMoving() || !this.shouldEmote) {
			controller.forceAnimationReset();
			controller.stop();
			this.shouldEmote = false;
			return PlayState.STOP;
		}

		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "controller", 2, this::predicate));
		controllers.add(LOAnimations.genericAttackAnimation(this, LOAnimations.ATTACK));
		controllers.add(new AnimationController<>(this, "emoteController", 5, this::emotePredicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}

	@Override
	public void playEmote(String emoteName, String loopType) {
		AnimationController<?> controller = this.getAnimatableInstanceCache().getManagerForId(this.getId()).getAnimationControllers().get("emoteController");
		controller.forceAnimationReset();
		controller.stop();
		controller.setAnimation(RawAnimation.begin().then(emoteName, Animation.LoopType.fromString(loopType)));
		this.shouldEmote = true;
	}

	public boolean isFollower() {
		return this.leader != null && this.leader.isAlive();
	}

	public void startFollowing(OHorse horse) {
		this.leader = horse;
		horse.addFollower();
	}

	public void stopFollowing() {
		this.leader.removeFollower();
		this.leader = null;
	}

	public void addFollower() {
		this.herdSize++;
	}

	public void removeFollower() {
		this.herdSize--;
	}

	public boolean canBeFollowed() {
		return this.hasFollowers() && this.herdSize < this.getMaxHerdSize();
	}

	public int getMaxHerdSize() {
		return 3;
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

	public void addFollowers(Stream<? extends OHorse> stream) {
		stream.limit(this.getMaxHerdSize() - this.herdSize).filter((horse) -> {
			return horse != this;
		}).forEach((horse) -> {
			horse.startFollowing(this);
		});
	}

	//ground tie
	@Override
	public void tick() {
		super.tick();
		if (this.isSaddled() && !this.isVehicle() || this.isLeashed()) {
			this.getNavigation().stop();
		}

		if (this.hasFollowers() && this.level().random.nextInt(200) == 1) {
			List<? extends OHorse> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
			if (list.size() <= 1) {
				this.herdSize = 1;
			}
		}

		if (this.isOnSand()) {
			if (!this.hasSlownessEffect()) {
				this.applySlownessEffect();
			}
		} else {
			if (this.hasSlownessEffect()) {
				this.removeSlownessEffect();
			}
		}
	}

	@Override
	public void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
		if (this.hasPassenger(entity)) {

			double offsetX = 0;
			double offsetY = 1.1;
			double offsetZ = -0.2;

			if (this.isSaddled() && getModelResource().equals(BreedModel.STOCK.resourceLocation)) {
				offsetY = 1.3;
			}

			if (this.isSaddled() && getModelResource().equals(BreedModel.DRAFT.resourceLocation)) {
				offsetY = 1.45;
			}

			if (this.isSaddled() && getModelResource().equals(BreedModel.WARMBLOOD.resourceLocation)) {
				offsetY = 1.35;
			}

			if (this.isSaddled() && getModelResource().equals(BreedModel.PONY.resourceLocation)) {
				offsetY = 1.1;
			}

			if (this.isJumping()) {
//				offsetY = 1.7;
				offsetZ = -0.6;
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

	@Override
	public SoundEvent getAmbientSound() {
		super.getAmbientSound();
		return SoundEvents.HORSE_AMBIENT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return SoundEvents.HORSE_DEATH;
	}

	@Nullable
	@Override
	public SoundEvent getEatingSound() {
		return SoundEvents.HORSE_EAT;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource damageSource) {
		super.getHurtSound(damageSource);
		return SoundEvents.HORSE_HURT;
	}

	@Override
	public SoundEvent getAngrySound() {
		super.getAngrySound();
		return SoundEvents.HORSE_ANGRY;
	}

	public ResourceLocation getTextureResource() {
		return this.entityData.get(VARIANT_TEXTURE);
//		return OHorseModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
	}

	public ResourceLocation getOverlayLocation() {
		return this.entityData.get(OVERLAY_TEXTURE);
//		return OHorseMarkingLayer.Overlay.overlayFromOrdinal(getOverlayVariant()).resourceLocation;
	}

	public ResourceLocation getModelResource() {
		return BreedModel.breedFromOrdinal(getBreed()).resourceLocation;
	}

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
		this.entityData.set(VARIANT_TEXTURE, OHorseModel.Variant.variantFromOrdinal(variant).resourceLocation);
		this.entityData.set(VARIANT, variant);
	}

	public void setOverlayVariant(int variant) {
		this.entityData.set(OVERLAY_TEXTURE, OHorseMarkingLayer.Overlay.overlayFromOrdinal(variant).resourceLocation);
		this.entityData.set(OVERLAY, variant);
	}

	public void setBreed(int breed) {
		this.entityData.set(BREED, breed);
	}

	public void setVariantTexture(String variant) {
		ResourceLocation resourceLocation = ResourceLocation.tryParse(variant);
		if (resourceLocation == null) {
			resourceLocation = OHorseModel.Variant.BAY.resourceLocation;
		}
		this.entityData.set(VARIANT_TEXTURE, resourceLocation);
	}

	public void setOverlayVariantTexture(String variant) {
		ResourceLocation resourceLocation = ResourceLocation.tryParse(variant);
		if (resourceLocation == null) {
			resourceLocation = OHorseMarkingLayer.Overlay.NONE.resourceLocation;
		}
		this.entityData.set(OVERLAY_TEXTURE, resourceLocation);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);

		if (tag.contains("Variant")) {
			this.setVariant(tag.getInt("Variant"));
		}

		if (tag.contains("Overlay")) {
			this.setOverlayVariant(tag.getInt("Overlay"));
		}

		if (tag.contains("Variant_Texture")) {
			this.setVariantTexture(tag.getString("Variant_Texture"));
		}

		if (tag.contains("Overlay_Texture")) {
			this.setOverlayVariantTexture(tag.getString("Overlay_Texture"));
		}

		if (tag.contains("Breed")) {
			this.setBreed(tag.getInt("Breed"));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Variant", this.getVariant());
		tag.putInt("Overlay", this.getOverlayVariant());
		tag.putString("Variant_Texture", this.getTextureResource().toString());
		tag.putString("Overlay_Texture", this.getOverlayLocation().toString());
		tag.putInt("Breed", this.getBreed());
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}

		Random random = new Random();
		this.setVariant(random.nextInt(OHorseModel.Variant.values().length));
		this.setOverlayVariant(random.nextInt(OHorseMarkingLayer.Overlay.values().length));

		if (spawnType == MobSpawnType.SPAWN_EGG) {
			this.setBreed(random.nextInt(BreedModel.values().length));
		}

		this.randomizeAttributes();
		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
		this.entityData.define(BREED, 0);
		this.entityData.define(VARIANT_TEXTURE, OHorseModel.Variant.BAY.resourceLocation);
		this.entityData.define(OVERLAY_TEXTURE, OHorseMarkingLayer.Overlay.NONE.resourceLocation);
	}

	public boolean canMate(Animal animal) {
		if (animal == this) {
			return false;
		} else if (!(animal instanceof ODonkey) && !(animal instanceof OHorse)) {
			return false;
		} else {
			return this.canParent() && ((AbstractOHorse)animal).canParent();
		}
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		AbstractOHorse abstracthorse;
		if (ageableMob instanceof ODonkey) {
			ODonkey donkey = (ODonkey) ageableMob;

			abstracthorse = EntityTypes.O_MULE_ENTITY.get().create(serverLevel);

			int overlayChance = this.random.nextInt(9);
			int selectedOverlay;

			if (overlayChance < 4) {
				selectedOverlay = this.getOverlayVariant();
			} else if (overlayChance < 8) {
				selectedOverlay = donkey.getOverlayVariant();
			} else {
				selectedOverlay = this.random.nextInt(OHorseMarkingLayer.Overlay.values().length);
			}

			((OMule) abstracthorse).setOverlayVariant(selectedOverlay);
			((OMule) abstracthorse).setVariant(random.nextInt(OMuleModel.Variant.values().length));

		} else {
			OHorse horse = (OHorse) ageableMob;
			abstracthorse = EntityTypes.O_HORSE_ENTITY.get().create(serverLevel);

			int i = this.random.nextInt(9);
			int variant;
			if (i < 4) {
				variant = this.getVariant();
			} else if (i < 8) {
				variant = horse.getVariant();
			} else {
				variant = this.random.nextInt(OHorseModel.Variant.values().length);
			}

			int j = this.random.nextInt(5);
			int overlay;
			if (j < 2) {
				overlay = this.getOverlayVariant();
			} else if (j < 4) {
				overlay = horse.getOverlayVariant();
			} else {
				overlay = this.random.nextInt(OHorseMarkingLayer.Overlay.values().length);
			}

			int k = this.random.nextInt(5);
			int breed;
			if (k < 2) {
				breed = this.getBreed();
			} else if (k < 4) {
				breed = horse.getBreed();
			} else {
				breed = this.random.nextInt(BreedModel.values().length);
			}

			((OHorse) abstracthorse).setVariant(variant);
			((OHorse) abstracthorse).setOverlayVariant(overlay);
			((OHorse) abstracthorse).setBreed(breed);
		}

		this.setOffspringAttributes(ageableMob, abstracthorse);
		return abstracthorse;
	}

}
