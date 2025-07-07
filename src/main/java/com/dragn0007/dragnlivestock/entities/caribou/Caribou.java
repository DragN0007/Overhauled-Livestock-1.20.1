package com.dragn0007.dragnlivestock.entities.caribou;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.ai.GroundTieGoal;
import com.dragn0007.dragnlivestock.entities.ai.OAvoidEntityGoal;
import com.dragn0007.dragnlivestock.entities.ai.ORunAroundLikeCrazyGoal;
import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.entities.marking_layer.EquineEyeColorOverlay;
import com.dragn0007.dragnlivestock.entities.marking_layer.EquineMarkingOverlay;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.entities.util.LOAnimations;
import com.dragn0007.dragnlivestock.entities.util.Taggable;
import com.dragn0007.dragnlivestock.event.LivestockOverhaulClientEvent;
import com.dragn0007.dragnlivestock.gui.CaribouMenu;
import com.dragn0007.dragnlivestock.items.custom.BrandTagItem;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
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

public class Caribou extends AbstractOMount implements GeoEntity, Taggable {

	private static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/caribou");
	private static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/horse");
	private static final ResourceLocation TFC_LOOT_TABLE = new ResourceLocation("tfc", "entities/caribou");
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
	public boolean canWearArmor() {
		return true;
	}

	public Caribou(EntityType<? extends Caribou> type, Level level) {
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
				.add(Attributes.MAX_HEALTH, 40.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.255F)
				.add(Attributes.ATTACK_DAMAGE, 1D);
	}

	public void randomizeAttributes() {
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.generateRandomMaxHealth());
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.generateRandomSpeed());
		this.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(this.generateRandomJumpStrength());
	}

	public static final Ingredient FOOD_ITEMS = Ingredient.of(LOTags.Items.CARIBOU_EATS);

	@Override
	public void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new GroundTieGoal(this));

		this.goalSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.7D));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.4, true));
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 0.0F));
		this.goalSelector.addGoal(1, new ORunAroundLikeCrazyGoal(this, 1.3F));

		this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D, AbstractOMount.class));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));

		this.goalSelector.addGoal(1, new OAvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, entity ->
				(entity.getType().is(LOTags.Entity_Types.WOLVES) && !this.isTamed()) ||
						(entity.getType().is(LOTags.Entity_Types.WOLVES) && (entity instanceof TamableAnimal && !((TamableAnimal) entity).isTame())) && this.isTamed()
		));

		this.goalSelector.addGoal(1, new OAvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, entity ->
				(entity.getType().is(LOTags.Entity_Types.HUNTING_DOGS) && !this.isTamed()) ||
						(entity.getType().is(LOTags.Entity_Types.HUNTING_DOGS) && (entity instanceof TamableAnimal && !((TamableAnimal) entity).isTame())) && this.isTamed()
		));
	}

	public float generateRandomMaxHealth() {
		float baseHealth;
		baseHealth = 16.0F;
		return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
	}

	public double generateRandomJumpStrength() {
		double baseStrength = 0.4F;
		double multiplier = this.random.nextDouble() * 0.2D + this.random.nextDouble() * 0.2D + this.random.nextDouble() * 0.25D;
		baseStrength = 0.55F;
		return baseStrength + multiplier;
	}

	public double generateRandomSpeed() {
		double baseSpeed = 0.0F;
		double multiplier = (this.random.nextDouble() * 0.1D + this.random.nextDouble() * 0.1D + this.random.nextDouble() * 0.1D) * 0.30D;
		baseSpeed = 0.2F;
		return baseSpeed + multiplier;
	}

	public int getInventorySize() {
		return 18;
	}

	@Override
	public void positionRider(Entity entity, MoveFunction moveFunction) {
		if (this.hasPassenger(entity)) {

			double offsetX = 0;
			double offsetY = 1.0;
			double offsetZ = -0.1;

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
	public void openInventory(Player player) {
		if(player instanceof ServerPlayer serverPlayer && this.isTamed()) {
			NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider((containerId, inventory, p) -> {
				return new CaribouMenu(containerId, inventory, this.inventory, this);
			}, this.getDisplayName()), (data) -> {
				data.writeInt(this.getInventorySize());
				data.writeInt(this.getId());
			});
		}
	}

	@Override
	public boolean handleEating(Player player, ItemStack stack) {
		int i = 0;
		int j = 0;
		float f = 0.0F;
		boolean flag = false;
		if (stack.is(LOTags.Items.CARIBOU_EATS)) {
			i = 90;
			j = 6;
			f = 10.0F;
			if (this.isTamed() && this.getAge() == 0 && this.canFallInLove()) {
				flag = true;
				this.setInLove(player);
			}
		}

		if (this.getHealth() < this.getMaxHealth() && f > 0.0F) {
			this.heal(f);
			flag = true;
		}

		if (this.isBaby() && i > 0) {
			this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), 0.0D, 0.0D, 0.0D);
			if (!this.level().isClientSide) {
				this.ageUp(i);
			}

			flag = true;
		}

		if (j > 0 && (flag || !this.isTamed()) && this.getTemper() < this.getMaxTemper()) {
			flag = true;
			if (!this.level().isClientSide) {
				this.modifyTemper(j);
			}
		}

		if (flag) {
			this.gameEvent(GameEvent.ENTITY_INTERACT);
			if (!this.isSilent()) {
				SoundEvent soundevent = this.getEatingSound();
				if (soundevent != null) {
					this.level().playSound(null, this.getX(), this.getY(), this.getZ(), this.getEatingSound(), this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
				}
			}
		}

		return flag;
	}

	@Override
	public boolean canPerformRearing() {
		return false;
	}

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "controller", 2, this::predicate));
		controllers.add(LOAnimations.genericAttackAnimation(this, LOAnimations.ATTACK));
		controllers.add(new AnimationController<>(this, "emoteController", 5, this::emotePredicate));
	}

	private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
		double x = this.getX() - this.xo;
		double z = this.getZ() - this.zo;
		double currentSpeed = this.getDeltaMovement().lengthSqr();
		double speedThreshold = 0.015;

		boolean isMoving = (x * x + z * z) > 0.0001;

		double movementSpeed = this.getAttributeBaseValue(Attributes.MOVEMENT_SPEED);
		double animationSpeed = Math.max(0.1, movementSpeed);

		AnimationController<T> controller = tAnimationState.getController();

		if ((!this.isTamed() || this.isWearingHarness()) && this.isVehicle() && !this.isJumping()) {
			controller.setAnimation(RawAnimation.begin().then("buck", Animation.LoopType.LOOP));
			controller.setAnimationSpeed(1.3);
		} else if (this.isJumping()) {
			controller.setAnimation(RawAnimation.begin().then("jump", Animation.LoopType.PLAY_ONCE));
			controller.setAnimationSpeed(1.0);
		} else {
			if (isMoving) {
				if (!LivestockOverhaulClientEvent.HORSE_WALK_BACKWARDS.isDown()) {
					if (this.isAggressive() || (this.isVehicle() && this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD)) || (!this.isVehicle() && currentSpeed > speedThreshold)) {
						controller.setAnimation(RawAnimation.begin().then("sprint", Animation.LoopType.LOOP));
						if (this.isOnSand()) {
							controller.setAnimationSpeed(Math.max(0.1, 0.78 * controller.getAnimationSpeed() + animationSpeed));
						} else if (this.isOnSnow()) {
							controller.setAnimationSpeed(Math.max(0.1, 0.84 * controller.getAnimationSpeed() + animationSpeed));
						} else {
							controller.setAnimationSpeed(Math.max(0.1, 0.82 * controller.getAnimationSpeed() + animationSpeed));
						}

					} else if (this.isVehicle() && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(WALK_SPEED_MOD) && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD)) {
						controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
						if (this.isOnSand()) {
							controller.setAnimationSpeed(Math.max(0.1, 0.76 * controller.getAnimationSpeed() + animationSpeed));
						} else if (this.isOnSnow()) {
							controller.setAnimationSpeed(Math.max(0.1, 0.82 * controller.getAnimationSpeed() + animationSpeed));
						} else {
							controller.setAnimationSpeed(Math.max(0.1, 0.78 * controller.getAnimationSpeed() + animationSpeed));
						}

					} else if (this.isVehicle() && this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(WALK_SPEED_MOD)) {
						controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
						if (this.isOnSand()) {
							controller.setAnimationSpeed(Math.max(0.1, 0.79 * controller.getAnimationSpeed() + animationSpeed));
						} else if (this.isOnSnow()) {
							controller.setAnimationSpeed(Math.max(0.1, 0.83 * controller.getAnimationSpeed() + animationSpeed));
						} else {
							controller.setAnimationSpeed(Math.max(0.1, 0.81 * controller.getAnimationSpeed() + animationSpeed));
						}

					} else if (this.isVehicle() && LivestockOverhaulClientEvent.HORSE_SPANISH_WALK_TOGGLE.isDown() && this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(WALK_SPEED_MOD)) {
						controller.setAnimation(RawAnimation.begin().then("spanish_walk", Animation.LoopType.LOOP));
						controller.setAnimationSpeed(Math.max(0.1, 0.78 * controller.getAnimationSpeed() + animationSpeed));
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

	private <T extends GeoAnimatable> PlayState emotePredicate(AnimationState<T> tAnimationState) {
		AnimationController<T> controller = tAnimationState.getController();

		if (tAnimationState.isMoving() || !this.shouldEmote) {
			controller.forceAnimationReset();
			controller.stop();
			this.shouldEmote = false;
			return PlayState.STOP;
		}

		return PlayState.CONTINUE;
	}

	private void applySpeedEffect() {
		MobEffect speedEffect = MobEffect.byId(1);
		MobEffectInstance speedEffectInstance = new MobEffectInstance(speedEffect, 200, 0, false, false);
		this.addEffect(speedEffectInstance);
	}

	private boolean hasSpeedEffect() {
		return this.hasEffect(MobEffect.byId(1));
	}

	private void removeSpeedEffect() {
		this.removeEffect(MobEffect.byId(1));
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

		if (itemstack.is(Items.SHEARS) && player.isShiftKeyDown()) {
			if (this.isTagged()) {
				this.setTagged(false);
				this.playSound(SoundEvents.SHEEP_SHEAR, 0.5f, 1f);
				return InteractionResult.sidedSuccess(this.level().isClientSide);
			}
		}

		return super.mobInteract(player, hand);
	}

	public int maxSprint = 20 * LivestockOverhaulCommonConfig.BASE_HORSE_SPRINT_TIME.get();
	public int sprintTick = maxSprint;

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

		if (this.isOnSnow()) {
			if (!this.hasSpeedEffect()) {
				this.applySpeedEffect();
			}
		} else {
			if (this.hasSpeedEffect()) {
				this.removeSpeedEffect();
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
			if ((sprintTick < maxSprint) && isMoving) {
				sprintTick++;
			} else if ((sprintTick < maxSprint) && !isMoving) {
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
	public boolean hurt(DamageSource damageSource, float v) {
		if (damageSource.is(DamageTypes.FREEZE)) {
			return false;
		}
		return super.hurt(damageSource, v);
	}

	@Override
	public SoundEvent getAmbientSound() {
		super.getAmbientSound();
		return SoundEvents.CAMEL_AMBIENT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return SoundEvents.CAMEL_DEATH;
	}

	@Nullable
	@Override
	public SoundEvent getEatingSound() {
		return SoundEvents.CAMEL_EAT;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource damageSource) {
		super.getHurtSound(damageSource);
		return SoundEvents.CAMEL_HURT;
	}

	@Override
	public SoundEvent getAngrySound() {
		super.getAngrySound();
		return SoundEvents.CAMEL_HURT;
	}

	
	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Caribou.class, EntityDataSerializers.INT);
	public int getVariant() {
		return this.entityData.get(VARIANT);
	}
	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
		this.entityData.set(VARIANT_TEXTURE, CaribouModel.Variant.variantFromOrdinal(variant).resourceLocation);
	}
	public static final EntityDataAccessor<ResourceLocation> VARIANT_TEXTURE = SynchedEntityData.defineId(Caribou.class, LivestockOverhaul.RESOURCE_LOCATION);
	public ResourceLocation getTextureResource() {
		return this.entityData.get(VARIANT_TEXTURE);
	}
	public void setVariantTexture(String variant) {
		ResourceLocation resourceLocation = ResourceLocation.tryParse(variant);
		if (resourceLocation == null) {
			resourceLocation = CaribouModel.Variant.BAY.resourceLocation;
		}
		this.entityData.set(VARIANT_TEXTURE, resourceLocation);
	}


	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(Caribou.class, EntityDataSerializers.INT);
	public int getOverlayVariant() {
		return this.entityData.get(OVERLAY);
	}
	public void setOverlayVariant(int variant) {
		this.entityData.set(OVERLAY, variant);
		this.entityData.set(OVERLAY_TEXTURE, EquineMarkingOverlay.overlayFromOrdinal(variant).resourceLocation);
	}
	public static final EntityDataAccessor<ResourceLocation> OVERLAY_TEXTURE = SynchedEntityData.defineId(Caribou.class, LivestockOverhaul.RESOURCE_LOCATION);
	public ResourceLocation getOverlayLocation() {
		return this.entityData.get(OVERLAY_TEXTURE);
	}
	public void setOverlayVariantTexture(String variant) {
		ResourceLocation resourceLocation = ResourceLocation.tryParse(variant);
		if (resourceLocation == null) {
			resourceLocation = EquineMarkingOverlay.NONE.resourceLocation;
		}
		this.entityData.set(OVERLAY_TEXTURE, resourceLocation);
	}


	public static final EntityDataAccessor<Integer> EYES = SynchedEntityData.defineId(Caribou.class, EntityDataSerializers.INT);
	public ResourceLocation getEyeTextureResource() {
		return EquineEyeColorOverlay.eyesFromOrdinal(getEyeVariant()).resourceLocation;
	}
	public int getEyeVariant() {
		return this.entityData.get(EYES);
	}
	public void setEyeVariant(int eyeVariant) {
		this.entityData.set(EYES, eyeVariant);
	}

	public enum Feathering {
		NONE,
		HALF,
		FULL;
		public OHorse.Feathering next() {
			return OHorse.Feathering.values()[(this.ordinal() + 1) % OHorse.Feathering.values().length];
		}
	}
	public static final EntityDataAccessor<Integer> FEATHERING = SynchedEntityData.defineId(Caribou.class, EntityDataSerializers.INT);
	public int getFeathering() {
		return this.entityData.get(FEATHERING);
	}
	public void setFeathering(int feathering) {
		this.entityData.set(FEATHERING, feathering);
	}

	private static final EntityDataAccessor<Integer> BRAND_TAG_COLOR = SynchedEntityData.defineId(Caribou.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> TAGGED = SynchedEntityData.defineId(Caribou.class, EntityDataSerializers.BOOLEAN);
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

		if(tag.contains("Tagged")) {
			this.setTagged(tag.getBoolean("Tagged"));
		}

		this.setBrandTagColor(DyeColor.byId(tag.getInt("BrandTagColor")));

		if (tag.contains("Feathering")) {
			this.setFeathering(tag.getInt("Feathering"));
		}

		if (tag.contains("Eyes")) {
			this.setEyeVariant(tag.getInt("Eyes"));
		}

		if (tag.contains("SprintTime")) {
			this.sprintTick = tag.getInt("SprintTime");
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
		tag.putBoolean("Tagged", this.isTagged());
		tag.putByte("BrandTagColor", (byte)this.getBrandTagColor().getId());
		tag.putInt("Feathering", this.getFeathering());
		tag.putInt("Eyes", this.getEyeVariant());
		tag.putInt("SprintTime", this.sprintTick);
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}

		Random random = new Random();
		this.setGender(random.nextInt(Gender.values().length));

		if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
			this.setColorByChance();
			this.setMarkingByChance();
			this.setFeatheringByChance();
		} else {
			this.setVariant(random.nextInt(CaribouModel.Variant.values().length));
			this.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
			this.setFeathering(random.nextInt(Caribou.Feathering.values().length));
		}

		if (LivestockOverhaulCommonConfig.EYES_BY_COLOR.get()) {
			this.setEyeColorByChance();
		} else {
			this.setEyeVariant(random.nextInt(EquineMarkingOverlay.values().length));
		}

		this.randomizeAttributes();
		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
		this.entityData.define(GENDER, 0);
		this.entityData.define(VARIANT_TEXTURE, CaribouModel.Variant.BAY.resourceLocation);
		this.entityData.define(OVERLAY_TEXTURE, EquineMarkingOverlay.NONE.resourceLocation);
		this.entityData.define(BRAND_TAG_COLOR, DyeColor.YELLOW.getId());
		this.entityData.define(TAGGED, false);
		this.entityData.define(FEATHERING, 0);
		this.entityData.define(EYES, 0);
	}

	@Override
	public boolean canParent() {
		return !this.isVehicle() && !this.isPassenger() && !this.isBaby() && this.isInLove();
	}

	public boolean canMate(Animal animal) {
		if (animal == this) {
			return false;
		} else if (!(animal instanceof Caribou)) {
			return false;
		} else {
			if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
				return this.canParent() && ((AbstractOMount) animal).canParent();
			} else {
				AbstractOMount partner = (AbstractOMount) animal;
				if (this.canParent() && partner.canParent() && this.getGender() != partner.getGender()) {
					return this.isFemale();
				}
			}
		}
		return false;
	}

	public void setEyeColorByChance() {

		//white, cream and mostly-white or bald caribou have a better chance of gaining blue or green eyes
		if (this.getVariant() == 6 || this.getVariant() == 16 || this.getOverlayVariant() == 2 || this.getOverlayVariant() == 8
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

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		Caribou calf;
		Caribou partner = (Caribou) ageableMob;
		calf = EntityTypes.CARIBOU_ENTITY.get().create(serverLevel);

		int variantChance = this.random.nextInt(14);
		int variant;
		if (variantChance < 6) {
			variant = this.getVariant();
		} else if (variantChance < 12) {
			variant = partner.getVariant();
		} else {
			variant = this.random.nextInt(CaribouModel.Variant.values().length);
		}
		((Caribou) calf).setVariant(variant);
		
		int overlayChance = this.random.nextInt(10);
		int overlay;
		if (overlayChance < 4) {
			overlay = this.getOverlayVariant();
		} else if (overlayChance < 8) {
			overlay = partner.getOverlayVariant();
		} else {
			overlay = this.random.nextInt(EquineMarkingOverlay.values().length);
		}
		((Caribou) calf).setOverlayVariant(overlay);

		int eyeColorChance = this.random.nextInt(11);
		int eyes;
		if (eyeColorChance < 5) {
			eyes = this.getEyeVariant();
		} else if (eyeColorChance < 10) {
			eyes = partner.getEyeVariant();
		} else {
			eyes = this.random.nextInt(EquineEyeColorOverlay.values().length);
		}
		((Caribou) calf).setEyeVariant(eyes);

		calf.setGender(random.nextInt(Gender.values().length));

		((Caribou) calf).setFeatheringByChance();


		if (this.random.nextInt(3) >= 1) {
			((Caribou) calf).generateRandomJumpStrength();

			int betterSpeed = (int) Math.max(partner.getSpeed(), this.random.nextInt(10) + 20);
			calf.setSpeed(betterSpeed);

			int betterHealth = (int) Math.max(partner.getHealth(), this.random.nextInt(20) + 40);
			calf.setHealth(betterHealth);

			//generate random stats of the breed standard of the baby if the breed is random
		}

		this.setOffspringAttributes(ageableMob, calf);
		return calf;
	}

	public void setFeatheringByChance() {

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

	public void setColorByChance() {

		if (random.nextDouble() < 0.05) {
			int[] variants = {2, 3, 6, 8, 11, 12};
			int randomIndex = new Random().nextInt(variants.length);
			this.setVariant(variants[randomIndex]);
		} else if (random.nextDouble() < 0.30 && random.nextDouble() > 0.05) {
			int[] variants = {1, 4, 9, 10, 13, 16};
			int randomIndex = new Random().nextInt(variants.length);
			this.setVariant(variants[randomIndex]);
		} else if (random.nextDouble() > 0.30) {
			int[] variants = {0, 5, 7, 15, 15};
			int randomIndex = new Random().nextInt(variants.length);
			this.setVariant(variants[randomIndex]);
		}
	}

	public void setMarkingByChance() {

		if (random.nextDouble() < 0.15) {
			int[] variants = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 17, 18, 19, 20,
					23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41};
			int randomIndex = new Random().nextInt(variants.length);
			this.setOverlayVariant(variants[randomIndex]);
		} else if (random.nextDouble() > 0.15) {
			int[] variants = {0, 2, 4, 5, 6, 7, 11, 12, 14, 18, 19, 21, 29, 30, 32, 33, 35, 39, 41};
			int randomIndex = new Random().nextInt(variants.length);
			this.setOverlayVariant(variants[randomIndex]);
		}
	}

}