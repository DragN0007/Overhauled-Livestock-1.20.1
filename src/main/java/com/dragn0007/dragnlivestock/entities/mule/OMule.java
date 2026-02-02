package com.dragn0007.dragnlivestock.entities.mule;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.client.event.LivestockOverhaulClientEvent;
import com.dragn0007.dragnlivestock.common.gui.OMuleMenu;
import com.dragn0007.dragnlivestock.entities.ai.GroundTieGoal;
import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.entities.util.LOAnimations;
import com.dragn0007.dragnlivestock.entities.util.marking_layer.EquineEyeColorOverlay;
import com.dragn0007.dragnlivestock.entities.util.marking_layer.EquineMarkingOverlay;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;
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
import java.util.Random;

public class OMule extends AbstractOMount implements GeoEntity {

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0D, (double)this.getEyeHeight() * 0.9F, (double)(this.getBbWidth() * 1.1F));
		//              ^ Side offset                      ^ Height offset                   ^ Length offset
	}

	public OMule(EntityType<? extends OMule> type, Level level) {
		super(type, level);
	}

	protected static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_mule");
	protected static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/mule");
	protected static final ResourceLocation TFC_LOOT_TABLE = new ResourceLocation("tfc", "entities/mule");
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
	public void openInventory(Player player) {
		if(player instanceof ServerPlayer serverPlayer && this.isTamed()) {
			NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider((containerId, inventory, p) -> {
				return new OMuleMenu(containerId, inventory, this.inventory, this);
			}, this.getDisplayName()), (data) -> {
				data.writeInt(this.getInventorySize());
				data.writeInt(this.getId());
			});
		}
	}

	public int getInventorySize() {
		if (this.hasChest()) {
			return 18;
		} else {
			return 3;
		}
	}

	public static AttributeSupplier.Builder createBaseHorseAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.JUMP_STRENGTH)
				.add(Attributes.MAX_HEALTH, 53.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.255F)
				.add(Attributes.ATTACK_DAMAGE, 2D);
	}

	public void randomizeAttributes() {
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.generateRandomMaxHealth());
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.generateRandomSpeed());
		this.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(this.generateRandomJumpStrength());
	}

	@Override
	public void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new GroundTieGoal(this));

		this.goalSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.7D));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.4, true));
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 0.0F));
		this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D, AbstractOMount.class));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Wolf.class, false));

		this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, entity ->
				entity.getType().is(LOTags.Entity_Types.WOLVES) && (entity instanceof TamableAnimal && !((TamableAnimal) entity).isTame()) && !this.isBaby()
		));

		this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, entity ->
				entity.getType().is(LOTags.Entity_Types.CATS) &&
						!(entity instanceof TamableAnimal && ((TamableAnimal) entity).isTame()) && !this.isBaby()
		));

		this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, entity ->
				entity.getType().is(LOTags.Entity_Types.FOXES) && (entity instanceof TamableAnimal && !((TamableAnimal) entity).isTame()) && !this.isBaby()
		));
	}

	public float generateRandomMaxHealth() {
		return 17.0F + (float)this.random.nextInt(8) + (float)this.random.nextInt(9);
	}

	public double generateRandomJumpStrength() {
		return (double)0.3F + this.random.nextDouble() * 0.2D + this.random.nextDouble() * 0.2D + this.random.nextDouble() * 0.2D;
	}

	public double generateRandomSpeed() {
		return ((double)0.40F + this.random.nextDouble() * 0.3D + this.random.nextDouble() * 0.3D + this.random.nextDouble() * 0.3D) * 0.25D;
	}

	@Override
	public boolean canPerformRearing() {
		return false;
	}

	protected final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	protected <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
		double x = this.getX() - this.xo;
		double z = this.getZ() - this.zo;
		double currentSpeed = this.getDeltaMovement().lengthSqr();
		double speedThreshold = 0.025;
		double speedRunThreshold = 0.02;
		double speedTrotThreshold = 0.015;
		double wagonSpeedRunThreshold = 0.09;
		double wagonSpeedTrotThreshold = 0.06;

		boolean isMoving = (x * x + z * z) > 0.0001;

		double movementSpeed = this.getAttributeBaseValue(Attributes.MOVEMENT_SPEED);
		double animationSpeed = Math.max(0.1, movementSpeed);

		AnimationController<T> controller = tAnimationState.getController();

		if ((!this.isTamed() || this.isWearingRodeoHarness()) && this.isVehicle() && !this.isJumping()) {
			controller.setAnimation(RawAnimation.begin().then("buck", Animation.LoopType.LOOP));
			controller.setAnimationSpeed(1.3);
		} else if (this.isJumping()) {
			controller.setAnimation(RawAnimation.begin().then("jump", Animation.LoopType.PLAY_ONCE));
			controller.setAnimationSpeed(1.0);
		} else {
			if (isMoving) {
				if (!LivestockOverhaulClientEvent.HORSE_WALK_BACKWARDS.isDown()) {
					if (this.isNoAi() && !this.isVehicle()) { //for wagons
						if (currentSpeed < wagonSpeedTrotThreshold) {
							controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
							controller.setAnimationSpeed(Math.max(0.1, 0.80 * controller.getAnimationSpeed() + animationSpeed));
						} else if (currentSpeed > wagonSpeedTrotThreshold && currentSpeed < wagonSpeedRunThreshold) {
							controller.setAnimation(RawAnimation.begin().then("trot", Animation.LoopType.LOOP));
							controller.setAnimationSpeed(Math.max(0.1, 0.78 * controller.getAnimationSpeed() + animationSpeed));
						} else if (currentSpeed > wagonSpeedRunThreshold) {
							controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
							controller.setAnimationSpeed(Math.max(0.1, 0.78 * controller.getAnimationSpeed() + animationSpeed));
						} else {
							controller.setAnimation(RawAnimation.begin().then("trot", Animation.LoopType.LOOP));
							controller.setAnimationSpeed(Math.max(0.1, 0.82 * controller.getAnimationSpeed() + animationSpeed));
						}

					} else if (this.isAggressive() || (this.isVehicle() && this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD)) || (!this.isVehicle() && currentSpeed > speedThreshold)) {
						controller.setAnimation(RawAnimation.begin().then("sprint", Animation.LoopType.LOOP));
						controller.setAnimationSpeed(Math.max(0.1, 0.82 * controller.getAnimationSpeed() + animationSpeed));

					} else if ((this.isVehicle() && this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(TROT_SPEED_MOD)) || (!this.isVehicle() && currentSpeed > speedTrotThreshold)) {
						controller.setAnimation(RawAnimation.begin().then("trot", Animation.LoopType.LOOP));
						controller.setAnimationSpeed(Math.max(0.1, 0.78 * controller.getAnimationSpeed() + animationSpeed));

					} else if ((this.isVehicle() && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(WALK_SPEED_MOD) && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD))
							|| (!this.isVehicle() && currentSpeed > speedRunThreshold && currentSpeed < speedThreshold)) {
						if (this.isOnSand()) {
							controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
							controller.setAnimationSpeed(Math.max(0.1, 0.75 * controller.getAnimationSpeed() + animationSpeed));
						} else {
							controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
							controller.setAnimationSpeed(Math.max(0.1, 0.78 * controller.getAnimationSpeed() + animationSpeed));
						}

					} else if (this.isVehicle() && this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(WALK_SPEED_MOD)) {
						if (LivestockOverhaulClientEvent.HORSE_SPANISH_WALK_TOGGLE.isDown()) {
							controller.setAnimation(RawAnimation.begin().then("spanish_walk", Animation.LoopType.LOOP));
							controller.setAnimationSpeed(Math.max(0.1, 0.78 * controller.getAnimationSpeed() + animationSpeed));
						} else {
							controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
							controller.setAnimationSpeed(Math.max(0.1, 0.83 * controller.getAnimationSpeed() + animationSpeed));
						}

					} else {
						controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
						controller.setAnimationSpeed(Math.max(0.1, 0.80 * controller.getAnimationSpeed() + animationSpeed));
					}

				} else if (this.isVehicle() && LivestockOverhaulClientEvent.HORSE_WALK_BACKWARDS.isDown()) {
					if (this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(WALK_SPEED_MOD)) {
						controller.setAnimation(RawAnimation.begin().then("walk_back", Animation.LoopType.LOOP));
						controller.setAnimationSpeed(Math.max(0.1, 0.76 * controller.getAnimationSpeed() + animationSpeed));
					} else {
						controller.setAnimation(RawAnimation.begin().then("walk_back", Animation.LoopType.LOOP));
						controller.setAnimationSpeed(Math.max(0.1, 0.83 * controller.getAnimationSpeed() + animationSpeed));
					}
				}

			} else {
				if (this.isVehicle() || !LivestockOverhaulCommonConfig.GROUND_TIE.get()) {
					controller.setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
				} else {
					controller.setAnimation(RawAnimation.begin().then("ground_tie", Animation.LoopType.LOOP));
				}
				controller.setAnimationSpeed(1.0);
			}

		}
		return PlayState.CONTINUE;
	}

	protected <T extends GeoAnimatable> PlayState emotePredicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
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

	public int maxSprint = 20 * LivestockOverhaulCommonConfig.BASE_HORSE_SPRINT_TIME.get();
	public int sprintTick = maxSprint;
	public int stockSprintAddition = 800;
	public int draftSprintAddition = 400;

	@Override
	public void tick() {
		super.tick();

		if (this.isOnSand()) {
			if (!this.hasSlownessEffect()) {
				this.applySlownessEffect();
			}
		} else {
			if (this.hasSlownessEffect()) {
				this.removeSlownessEffect();
			}
		}

		Entity controllingPassenger = this.getControllingPassenger();
		Entity entity = controllingPassenger;
		int sprintLeftInSeconds = sprintTick / 20;
		double x = this.getX() - this.xo;
		double z = this.getZ() - this.zo;
		boolean isMoving = (x * x + z * z) > 0.0001;

		if (this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD) && !(sprintTick <= 0) && this.hasControllingPassenger() && isMoving) {
			sprintTick--;
			if (controllingPassenger != null && !(sprintTick <= 0)) {
				if (controllingPassenger instanceof Player player && LivestockOverhaulClientConfig.HORSE_SPRINT_TIMER.get()) {
					player.displayClientMessage(Component.translatable("Sprint Left: " + sprintLeftInSeconds + "s").withStyle(ChatFormatting.GOLD), true);
				}
			}
		}

		if ((!this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD) || !isMoving)) {
			if (((this.getBreed() == 0 && sprintTick < (maxSprint + stockSprintAddition)) ||
					(this.getBreed() == 2 && sprintTick < (maxSprint + draftSprintAddition)) ||
					(this.getBreed() == 1 && sprintTick < maxSprint)) && isMoving) {
				sprintTick++;
			} else if (((this.getBreed() == 0 && sprintTick < (maxSprint + stockSprintAddition)) ||
					(this.getBreed() == 2 && sprintTick < (maxSprint + draftSprintAddition)) ||
					(this.getBreed() == 1 && sprintTick < maxSprint)) && !isMoving) {
				sprintTick++;
				sprintTick++;
			}
		}

		if (sprintTick <= 0 && controllingPassenger != null) {
			AttributeInstance movementSpeed = this.getAttribute(Attributes.MOVEMENT_SPEED);
			this.handleSpeedRequest(-1);
			movementSpeed.removeModifier(SPRINT_SPEED_MOD);
			if (controllingPassenger != null) {
				if (controllingPassenger instanceof Player player && LivestockOverhaulClientConfig.HORSE_SPRINT_TIMER.get()) {
					player.displayClientMessage(Component.translatable("Sprint Depleted").withStyle(ChatFormatting.DARK_RED), true);
				}
			}
		} else if (entity == null || !this.hasControllingPassenger()) {
			return;
		}
	}

	@Override
	public boolean canAddPassenger(Entity entity) {
		if (!(this.getBreed() == 2)) {
			return this.getPassengers().size() < 1;
		} else if (this.getBreed() == 2) {
			return this.getPassengers().size() < 2;
		} else {
			return false;
		}
	}

	@Override
	public LivingEntity getControllingPassenger() {
		LivingEntity firstPassenger = (LivingEntity) this.getFirstPassenger();
		if (firstPassenger != null && this.isSaddled()) {
			return firstPassenger;
		}
		return null;
	}

	@Override
	public void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
		if (this.hasPassenger(entity)) {
			double offsetX = 0;
			double offsetY = 1.2;
			double offsetZ = -0.2;

			int i = this.getPassengers().indexOf(entity);

			if (!LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
				if (this.getBreed() == 0) {
					offsetY = 0.85;
				}

				if (this.getBreed() == 1) {
					offsetY = 0.6;
					offsetZ = -0.1;
				}

				if (this.getBreed() == 2) {
					switch (i) {
						case 0:
							offsetY = 1.2;
							break;
						case 1:
							offsetY = 1.2;
							offsetZ = -0.7;
							break;
					}
				}
			} else {
				offsetY = 0.60;
				offsetZ = -0.0;
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
		return SoundEvents.MULE_AMBIENT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return SoundEvents.MULE_DEATH;
	}

	@Nullable
	@Override
	public SoundEvent getEatingSound() {
		return SoundEvents.MULE_EAT;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource damageSource) {
		super.getHurtSound(damageSource);
		return SoundEvents.MULE_HURT;
	}

	@Override
	public SoundEvent getAngrySound() {
		super.getAngrySound();
		return SoundEvents.MULE_ANGRY;
	}

	public static final EntityDataAccessor<Integer> BREED = SynchedEntityData.defineId(OMule.class, EntityDataSerializers.INT);
	public ResourceLocation getModelResource() {
		return MuleBreed.breedFromOrdinal(getBreed()).resourceLocation;
	}
	public int getBreed() {
		return this.entityData.get(BREED);
	}
	public void setBreed(int breed) {
		this.entityData.set(BREED, breed);
	}


	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OMule.class, EntityDataSerializers.INT);
	public int getVariant() {
		return this.entityData.get(VARIANT);
	}
	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
		this.entityData.set(VARIANT_TEXTURE, OMuleModel.Variant.variantFromOrdinal(variant).resourceLocation.toString());
	}
	public static final EntityDataAccessor<String> VARIANT_TEXTURE = SynchedEntityData.defineId(OMule.class, EntityDataSerializers.STRING);
	public String getTextureResource() {
		return this.entityData.get(VARIANT_TEXTURE);
	}
	public void setVariantTexture(String variant) {
		this.entityData.set(VARIANT_TEXTURE, variant);
	}

	public ResourceLocation getSimplifiedVariantTextureResource() {
		return OMuleModel.SVariant.variantFromOrdinal(getSimplifiedVariant()).resourceLocation;
	}
	public int getSimplifiedVariant() {
		return this.entityData.get(VARIANT);
	}

	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(OMule.class, EntityDataSerializers.INT);
	public int getOverlayVariant() {
		return this.entityData.get(OVERLAY);
	}
	public void setOverlayVariant(int variant) {
		this.entityData.set(OVERLAY, variant);
		this.entityData.set(OVERLAY_TEXTURE, EquineMarkingOverlay.overlayFromOrdinal(variant).resourceLocation.toString());
	}
	public static final EntityDataAccessor<String> OVERLAY_TEXTURE = SynchedEntityData.defineId(OMule.class, EntityDataSerializers.STRING);
	public String getOverlayLocation() {
		return this.entityData.get(OVERLAY_TEXTURE);
	}
	public void setOverlayVariantTexture(String variant) {
		this.entityData.set(OVERLAY_TEXTURE, variant);
	}


	public static final EntityDataAccessor<Integer> EYES = SynchedEntityData.defineId(OMule.class, EntityDataSerializers.INT);
	public ResourceLocation getEyeTextureResource() {
		return EquineEyeColorOverlay.eyesFromOrdinal(getEyeVariant()).resourceLocation;
	}
	public int getEyeVariant() {
		return this.entityData.get(EYES);
	}
	public void setEyeVariant(int eyeVariant) {
		this.entityData.set(EYES, eyeVariant);
	}


	public static final EntityDataAccessor<Integer> FEATHERING = SynchedEntityData.defineId(OMule.class, EntityDataSerializers.INT);
	public int getFeathering() {
		return this.entityData.get(FEATHERING);
	}
	public void setFeathering(int feathering) {
		this.entityData.set(FEATHERING, feathering);
	}


	public static final EntityDataAccessor<ItemStack> FLOWER_ITEM = SynchedEntityData.defineId(OMule.class, EntityDataSerializers.ITEM_STACK);
	public ItemStack getFlowerItem() {
		return this.entityData.get(FLOWER_ITEM);
	}
	public void setFlowerItem(ItemStack decorItem) {
		this.entityData.set(FLOWER_ITEM, decorItem);
	}

	public static final EntityDataAccessor<Integer> FLOWER_TYPE = SynchedEntityData.defineId(OMule.class, EntityDataSerializers.INT);
	public int getFlowerType() {
		return this.entityData.get(FLOWER_TYPE);
	}
	public void setFlowerType(int decompVariant) {
		this.entityData.set(FLOWER_TYPE, decompVariant);
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

		if (tag.contains("Gender")) {
			this.setGender(tag.getInt("Gender"));
		}

		if (tag.contains("Breed")) {
			this.setBreed(tag.getInt("Breed"));
		}

		if (tag.contains("Feathering")) {
			this.setFeathering(tag.getInt("Feathering"));
		}

		if (tag.contains("Eyes")) {
			this.setEyeVariant(tag.getInt("Eyes"));
		}

		if (tag.contains("SprintTime")) {
			this.sprintTick = tag.getInt("SprintTime");
		}

		if (tag.contains("Flower_Type")) {
			this.setFlowerType(tag.getInt("Flower_Type"));
		}

		if(tag.contains("FlowerItem")) {
			ItemStack decorItem = ItemStack.of(tag.getCompound("FlowerItem"));
			this.setFlowerItem(decorItem);
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Variant", this.getVariant());
		tag.putInt("Overlay", this.getOverlayVariant());
		tag.putString("Variant_Texture", this.getTextureResource().toString());
		tag.putString("Overlay_Texture", this.getOverlayLocation().toString());
		tag.putInt("Gender", this.getGender());
		tag.putInt("Breed", this.getBreed());
		tag.putInt("Feathering", this.getFeathering());
		tag.putInt("Eyes", this.getEyeVariant());
		tag.putInt("SprintTime", this.sprintTick);
		tag.putInt("Flower_Type", this.getFlowerType());
		if(!this.getFlowerItem().isEmpty()) {
			tag.put("FlowerItem", this.getFlowerItem().save(new CompoundTag()));
		}
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}
		Random random = new Random();
		this.setGender(random.nextInt(Gender.values().length));
		this.setBreed(random.nextInt(MuleBreed.values().length));

		if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
			this.setColor();
			this.setMarking();
			this.setFeatheringByBreed();
		} else {
			this.setVariant(random.nextInt(OMuleModel.Variant.values().length));
			this.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
			this.setFeathering(random.nextInt(OHorse.Feathering.values().length));
		}

		if (LivestockOverhaulCommonConfig.EYES_BY_COLOR.get()) {
			this.setEyeColorByChance();
		} else {
			this.setEyeVariant(random.nextInt(EquineEyeColorOverlay.values().length));
		}

		this.randomizeAttributes();
		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
		this.entityData.define(VARIANT_TEXTURE, OMuleModel.Variant.RUST.resourceLocation.toString());
		this.entityData.define(OVERLAY_TEXTURE, EquineMarkingOverlay.NONE.resourceLocation.toString());
		this.entityData.define(GENDER, 0);
		this.entityData.define(BREED, 0);
		this.entityData.define(FEATHERING, 0);
		this.entityData.define(EYES, 0);
		this.entityData.define(FLOWER_ITEM, ItemStack.EMPTY);
		this.entityData.define(FLOWER_TYPE, 0);
	}

	public void setFeatheringByBreed() {

		if (this.getBreed() == 0) {
			if (random.nextDouble() < 0.05) {
				this.setFeathering(2);
			} else if (random.nextDouble() < 0.50 && random.nextDouble() > 0.05) {
				this.setFeathering(1);
			} else if (random.nextDouble() > 0.50) {
				this.setFeathering(0);
			} else {
				this.setFeathering(0);
			}
		}

		if (this.getBreed() == 1) {
			if (random.nextDouble() < 0.05) {
				this.setFeathering(0);
			} else if (random.nextDouble() < 0.30 && random.nextDouble() > 0.05) {
				this.setFeathering(1);
			} else if (random.nextDouble() > 0.30) {
				this.setFeathering(2);
			} else {
				this.setFeathering(2);
			}
		}

		if (this.getBreed() == 2) {
			if (random.nextDouble() < 0.15) {
				this.setFeathering(0);
			} else if (random.nextDouble() < 0.50 && random.nextDouble() > 0.15) {
				this.setFeathering(2);
			} else if (random.nextDouble() > 0.50) {
				this.setFeathering(1);
			} else {
				this.setFeathering(1);
			}
		}

	}

	public void setEyeColorByChance() {

		//white, cream and mostly-white or bald mules have a better chance of gaining blue or green eyes
		if (this.getVariant() == 6 || this.getVariant() == 15 || this.getOverlayVariant() == 2 || this.getOverlayVariant() == 8
				|| this.getOverlayVariant() == 9 || this.getOverlayVariant() == 10 || this.getOverlayVariant() == 15
				|| this.getOverlayVariant() == 17 || this.getOverlayVariant() == 20 || this.getOverlayVariant() == 24
				|| this.getOverlayVariant() == 26 || this.getOverlayVariant() == 32 || this.getOverlayVariant() == 34
				|| this.getOverlayVariant() == 36 || this.getOverlayVariant() == 37 || this.getOverlayVariant() == 38
				|| this.getOverlayVariant() == 39) {
			if (random.nextDouble() < 0.005) {
				this.setEyeVariant(7 + this.getRandom().nextInt(9)); //heterochromic
			} else if (random.nextDouble() < 0.10 && random.nextDouble() > 0.005) {
				this.setEyeVariant(6); //green
			} else if (random.nextDouble() < 0.30 && random.nextDouble() > 0.10) {
				this.setEyeVariant(5); //blue
			} else if (random.nextDouble() > 0.30) {
				this.setEyeVariant(this.getRandom().nextInt(4)); //random (between dark brown and dark blue)
			} else {
				this.setEyeVariant(0);
			}
		} else {
			if (random.nextDouble() < 0.005) {
				this.setEyeVariant(7 + this.getRandom().nextInt(9));
			} else if (random.nextDouble() < 0.03 && random.nextDouble() > 0.005) {
				this.setEyeVariant(6);
			} else if (random.nextDouble() < 0.10 && random.nextDouble() > 0.03) {
				this.setEyeVariant(5);
			} else if (random.nextDouble() > 0.10) {
				this.setEyeVariant(this.getRandom().nextInt(4));
			} else {
				this.setEyeVariant(0);
			}
		}

	}

	public void setColor() {

		//mules tend to come in browns, grey and black but can uncommonly come in other colors
		if (random.nextDouble() < 0.20) {
			this.setOverlayVariant(random.nextInt(OMuleModel.Variant.values().length));
		} else if (random.nextDouble() > 0.20) {
			int[] variants = {0, 1, 2, 3, 4, 8, 9, 10, 13, 14};
			int randomIndex = new Random().nextInt(variants.length);
			this.setVariant(variants[randomIndex]);
		}

	}

	public void setMarking() {

		//mules dont usually come with markings but definitely can. generally come with small ones
		if (random.nextDouble() < 0.20) {
			this.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
		} else if (random.nextDouble() > 0.20) {
			int[] variants = {0, 4, 6, 7, 11, 12, 13, 14, 18, 19, 21, 22, 23, 29, 30, 32, 33, 35, 39, 41, 42, 43};
			int randomIndex = new Random().nextInt(variants.length);
			this.setVariant(variants[randomIndex]);
		}

	}

	@Override
	public boolean canMate(Animal animal) {
		return false;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		return null;
	}
}
