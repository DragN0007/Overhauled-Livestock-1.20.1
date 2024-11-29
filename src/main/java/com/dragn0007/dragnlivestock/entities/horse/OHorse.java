package com.dragn0007.dragnlivestock.entities.horse;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.ai.HorseFollowHerdLeaderGoal;
import com.dragn0007.dragnlivestock.entities.donkey.ODonkey;
import com.dragn0007.dragnlivestock.entities.mule.OMule;
import com.dragn0007.dragnlivestock.entities.mule.OMuleModel;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.entities.util.LOAnimations;
import com.dragn0007.dragnlivestock.event.LivestockOverhaulClientEvent;
import com.dragn0007.dragnlivestock.gui.OHorseMenu;
import com.dragn0007.dragnlivestock.gui.OMountMenu;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class OHorse extends AbstractOMount implements GeoEntity {
	public static final EntityDataAccessor<ResourceLocation> VARIANT_TEXTURE = SynchedEntityData.defineId(OHorse.class, LivestockOverhaul.RESOURCE_LOCATION);
	public static final EntityDataAccessor<ResourceLocation> OVERLAY_TEXTURE = SynchedEntityData.defineId(OHorse.class, LivestockOverhaul.RESOURCE_LOCATION);
	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> BREED = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.INT);

	private static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_horse");
	private static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/horse");
	@Override
	public @NotNull ResourceLocation getDefaultLootTable() {
		if (LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
			return VANILLA_LOOT_TABLE;
		}
		return LOOT_TABLE;
	}

	public OHorse leader;
	public int herdSize = 1;

	public OHorse(EntityType<? extends OHorse> type, Level level) {
		super(type, level);
	}

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0D, (double) this.getEyeHeight() * 0.8F, (double) (this.getBbWidth() * 1.2F));
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
		this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D, AbstractOMount.class));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, (livingEntity) -> livingEntity instanceof Wolf));
	}

	public float generateRandomMaxHealth() {
		float baseHealth;
		if (getModelResource().equals(BreedModel.MUSTANG.resourceLocation)) {
			baseHealth = 16.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		if (getModelResource().equals(BreedModel.ARDENNES.resourceLocation)) {
			baseHealth = 20.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		if (getModelResource().equals(BreedModel.KLADRUBER.resourceLocation)) {
			baseHealth = 16.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		if (getModelResource().equals(BreedModel.FJORD.resourceLocation)) {
			baseHealth = 14.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		if (getModelResource().equals(BreedModel.THOROUGHBRED.resourceLocation)) {
			baseHealth = 13.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		if (getModelResource().equals(BreedModel.FRIESIAN.resourceLocation)) {
			baseHealth = 18.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		if (getModelResource().equals(BreedModel.IRISH_COB.resourceLocation)) {
			baseHealth = 18.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		if (getModelResource().equals(BreedModel.AMERICAN_QUARTER.resourceLocation)) {
			baseHealth = 16.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		return 15.0F + (float) this.random.nextInt(4) + (float) this.random.nextInt(5);
	}

	public double generateRandomJumpStrength() {
		double baseStrength = 0.4F;
		double multiplier = this.random.nextDouble() * 0.2D + this.random.nextDouble() * 0.2D + this.random.nextDouble() * 0.25D;

		if (getModelResource().equals(BreedModel.MUSTANG.resourceLocation)) {
			baseStrength = 0.5F;
			return baseStrength + multiplier;
		}
		if (getModelResource().equals(BreedModel.ARDENNES.resourceLocation)) {
			baseStrength = 0.3F;
			return baseStrength + multiplier;
		}
		if (getModelResource().equals(BreedModel.KLADRUBER.resourceLocation)) {
			baseStrength = 0.4F;
			return baseStrength + multiplier;
		}
		if (getModelResource().equals(BreedModel.FJORD.resourceLocation)) {
			baseStrength = 0.35F;
			return baseStrength + multiplier;
		}
		if (getModelResource().equals(BreedModel.THOROUGHBRED.resourceLocation)) {
			baseStrength = 0.35F;
			return baseStrength + multiplier;
		}
		if (getModelResource().equals(BreedModel.FRIESIAN.resourceLocation)) {
			baseStrength = 0.35F;
			return baseStrength + multiplier;
		}
		if (getModelResource().equals(BreedModel.IRISH_COB.resourceLocation)) {
			baseStrength = 0.3F;
			return baseStrength + multiplier;
		}
		if (getModelResource().equals(BreedModel.AMERICAN_QUARTER.resourceLocation)) {
			baseStrength = 0.4F;
			return baseStrength + multiplier;
		}
		return baseStrength + this.random.nextDouble() * 0.15D;
	}

	public double generateRandomSpeed() {
		double baseSpeed = 0.0F;
		double multiplier = (this.random.nextDouble() * 0.1D + this.random.nextDouble() * 0.1D + this.random.nextDouble() * 0.1D) * 0.30D;

		if (getModelResource().equals(BreedModel.MUSTANG.resourceLocation)) {
			baseSpeed = 0.2F;
			return baseSpeed + multiplier;
		}
		if (getModelResource().equals(BreedModel.ARDENNES.resourceLocation)) {
			baseSpeed = 0.15F;
			return baseSpeed + multiplier;
		}
		if (getModelResource().equals(BreedModel.KLADRUBER.resourceLocation)) {
			baseSpeed = 0.2F;
			return baseSpeed + multiplier;
		}
		if (getModelResource().equals(BreedModel.FJORD.resourceLocation)) {
			baseSpeed = 0.15F;
			return baseSpeed + multiplier;
		}
		if (getModelResource().equals(BreedModel.THOROUGHBRED.resourceLocation)) {
			baseSpeed = 0.25F;
			return baseSpeed + multiplier;
		}
		if (getModelResource().equals(BreedModel.FRIESIAN.resourceLocation)) {
			baseSpeed = 0.2F;
			return baseSpeed + multiplier;
		}
		if (getModelResource().equals(BreedModel.IRISH_COB.resourceLocation)) {
			baseSpeed = 0.15F;
			return baseSpeed + multiplier;
		}
		if (getModelResource().equals(BreedModel.AMERICAN_QUARTER.resourceLocation)) {
			baseSpeed = 0.2F;
			return baseSpeed + multiplier;
		}
		return baseSpeed + multiplier;
	}

	public double generateRandomEndurance() {
		return ((double) 0.45F + this.random.nextDouble() * 0.3D + this.random.nextDouble() * 0.3D + this.random.nextDouble() * 0.3D) * 0.25D;
	}

	protected int getInventorySize() {
		if (this.hasChest()) {
		    if (this.getBreed() == 1 || this.getBreed() == 5) {
				return 17; //draft or coldblood
			} else if (this.getBreed() == 3 || this.getBreed() == 6) {
				return 14; //pony
			} else if (this.getBreed() == 0 || this.getBreed() == 2 || this.getBreed() == 7) {
				return 11; //stock or warmblood
			} else if (this.getBreed() == 4) {
				return 5; //racer
			}
		}
		return super.getInventorySize();
	}

	@Override
	public void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
		if (this.hasPassenger(entity)) {

			double offsetX = 0;
			double offsetY = 1.1;
			double offsetZ = -0.2;

			if (this.isSaddled() && getModelResource().equals(BreedModel.MUSTANG.resourceLocation)) {
				offsetY = 1.3;
			}

			if (this.isSaddled() && getModelResource().equals(BreedModel.ARDENNES.resourceLocation)) {
				offsetY = 1.45;
			}

			if (this.isSaddled() && getModelResource().equals(BreedModel.KLADRUBER.resourceLocation)) {
				offsetY = 1.35;
			}

			if (this.isSaddled() && getModelResource().equals(BreedModel.FJORD.resourceLocation)) {
				offsetY = 1.05;
			}

			if (this.isSaddled() && getModelResource().equals(BreedModel.FRIESIAN.resourceLocation)) {
				offsetY = 1.5;
			}

			if (this.isSaddled() && getModelResource().equals(BreedModel.IRISH_COB.resourceLocation)) {
				offsetY = 1.1;
			}

			if (this.isSaddled() && getModelResource().equals(BreedModel.AMERICAN_QUARTER.resourceLocation)) {
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

	//TODO
//	public boolean canWearShoes() {
//		return true;
//	}

	@Override
	public void openInventory(Player player) {
		if(player instanceof ServerPlayer serverPlayer && this.isTamed()) {
			NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider((containerId, inventory, p) -> {
				return new OHorseMenu(containerId, inventory, this.inventory, this);
			}, this.getDisplayName()), (data) -> {
				data.writeInt(this.getInventorySize());
				data.writeInt(this.getId());
			});
		}
	}

	@Override
	protected boolean canPerformRearing() {
		return false;
	}

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "controller", 2, this::predicate));
		controllers.add(LOAnimations.genericAttackAnimation(this, LOAnimations.ATTACK));
		controllers.add(new AnimationController<>(this, "emoteController", 5, this::emotePredicate));
//		controllers.add(new AnimationController<>(this, "stanceController", 5, this::stancePredicate));
	}

	private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
		double x = this.getX() - this.xo;
		double z = this.getZ() - this.zo;

		boolean isMoving = (x * x + z * z) > 0.0001;

		double movementSpeed = this.getAttributeBaseValue(Attributes.MOVEMENT_SPEED);
		double animationSpeed = Math.max(0.1, movementSpeed);

		AnimationController<T> controller = tAnimationState.getController();

		if (this.isJumping()) {
			controller.setAnimation(RawAnimation.begin().then("jump", Animation.LoopType.PLAY_ONCE));
			controller.setAnimationSpeed(1.0);
		} else {
			if (isMoving) {
				if (this.isAggressive() || (this.isVehicle() && this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD))) {
					controller.setAnimation(RawAnimation.begin().then("sprint", Animation.LoopType.LOOP));
					controller.setAnimationSpeed(Math.max(0.1, 0.82 * controller.getAnimationSpeed() + animationSpeed));

				} else if (this.isVehicle() && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(WALK_SPEED_MOD) && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD)) {
					controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
					controller.setAnimationSpeed(Math.max(0.1, 0.8 * controller.getAnimationSpeed() + animationSpeed));

				} else if (this.isOnSand() && this.isVehicle() && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(WALK_SPEED_MOD) && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD)) {
					controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
					controller.setAnimationSpeed(Math.max(0.1, 0.78 * controller.getAnimationSpeed() + animationSpeed));

				} else if (this.isVehicle() && LivestockOverhaulClientEvent.HORSE_SPANISH_WALK_TOGGLE.isDown() && this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(WALK_SPEED_MOD)) {
					controller.setAnimation(RawAnimation.begin().then("spanish_walk", Animation.LoopType.LOOP));
					controller.setAnimationSpeed(Math.max(0.1, 0.82 * controller.getAnimationSpeed() + animationSpeed));

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
		}
		return PlayState.CONTINUE;
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

	private <T extends GeoAnimatable> PlayState emotePredicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
		AnimationController<T> controller = tAnimationState.getController();

		if (tAnimationState.isMoving() || !this.shouldEmote) {
			controller.forceAnimationReset();
			controller.stop();
			this.shouldEmote = false;
			return PlayState.STOP;
		}

		return PlayState.CONTINUE;
	}

//	private <T extends GeoAnimatable> PlayState stancePredicate(AnimationState<T> tAnimationState) {
//
//		AnimationController<T> controller = tAnimationState.getController();
//
//		if (this.isVehicle() && LivestockOverhaulClientEvent.HORSE_REINING_TOGGLE.isDown()) {
//			controller.setAnimation(RawAnimation.begin().then("head_down", Animation.LoopType.LOOP));
//
//		} else if(!this.isVehicle() || LivestockOverhaulClientEvent.CLEAR.isDown()) {
//			controller.forceAnimationReset();
//			controller.stop();
//			return PlayState.STOP;
//		}
//
//		return PlayState.CONTINUE;
//	}

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
		return LivestockOverhaulCommonConfig.HORSE_HERD_MAX.get();
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

		if (tag.contains("Gender")) {
			this.setGender(tag.getInt("Gender"));
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
		tag.putInt("Gender", this.getGender());
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
		this.setGender(random.nextInt(Gender.values().length));

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
		this.entityData.define(GENDER, 0);
		this.entityData.define(VARIANT_TEXTURE, OHorseModel.Variant.BAY.resourceLocation);
		this.entityData.define(OVERLAY_TEXTURE, OHorseMarkingLayer.Overlay.NONE.resourceLocation);
	}

	public boolean canMate(Animal animal) {
		if (animal == this) {
			return false;
		} else if (!(animal instanceof ODonkey) && !(animal instanceof OHorse)) {
			return false;
		} else {
			if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
				return this.canParent() && ((AbstractOMount) animal).canParent();
			} else {
				AbstractOMount partner = (AbstractOMount) animal;
				if (this.canParent() && partner.canParent() && this.getGender() != partner.getGender()) {
					return true;
				}

				boolean partnerIsFemale = partner.isFemale();
				boolean partnerIsMale = partner.isMale();
				if (LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get() && this.canParent() && partner.canParent()
						&& ((isFemale() && partnerIsMale) || (isMale() && partnerIsFemale))) {
					return isFemale();
				}
			}
		}
		return false;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		AbstractOMount abstracthorse;
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

			int k = this.random.nextInt(4);
			int breed;
			if (k == 0) {
				breed = this.random.nextInt(BreedModel.values().length);
			} else {
				breed = (this.random.nextInt(2) == 0) ? this.getBreed() : horse.getBreed();
			}

			int gender;
			gender = this.random.nextInt(OHorse.Gender.values().length);

			((OHorse) abstracthorse).setVariant(variant);
			((OHorse) abstracthorse).setOverlayVariant(overlay);
			((OHorse) abstracthorse).setBreed(breed);
			((OHorse) abstracthorse).setGender(gender);

			if (this.random.nextInt(4) == 0) {
				int randomJumpStrength = (int) Math.max(horse.generateRandomJumpStrength(), this.random.nextInt(10) + 10);
				((OHorse) abstracthorse).generateRandomJumpStrength();

				int betterSpeed = (int) Math.max(horse.getSpeed(), this.random.nextInt(10) + 20);
				((OHorse) abstracthorse).setSpeed(betterSpeed);

				int betterHealth = (int) Math.max(horse.getHealth(), this.random.nextInt(20) + 40);
				((OHorse) abstracthorse).setHealth(betterHealth);
			} else {
				((OHorse) abstracthorse).setSpeed(horse.getSpeed());
				((OHorse) abstracthorse).setHealth(horse.getHealth());
			}
		}

		this.setOffspringAttributes(ageableMob, abstracthorse);
		return abstracthorse;
	}

}