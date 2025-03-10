package com.dragn0007.dragnlivestock.entities.donkey;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.ai.GroundTieGoal;
import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.entities.horse.OHorseMarkingLayer;
import com.dragn0007.dragnlivestock.entities.mule.OMule;
import com.dragn0007.dragnlivestock.entities.mule.OMuleModel;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.entities.util.LOAnimations;
import com.dragn0007.dragnlivestock.gui.ODonkeyMenu;
import com.dragn0007.dragnlivestock.util.LOTags;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
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

public class ODonkey extends AbstractOMount implements GeoEntity {
	public static final EntityDataAccessor<ResourceLocation> VARIANT_TEXTURE = SynchedEntityData.defineId(ODonkey.class, LivestockOverhaul.RESOURCE_LOCATION);
	public static final EntityDataAccessor<ResourceLocation> OVERLAY_TEXTURE = SynchedEntityData.defineId(ODonkey.class, LivestockOverhaul.RESOURCE_LOCATION);
	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(ODonkey.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(ODonkey.class, EntityDataSerializers.INT);

	public ODonkey(EntityType<? extends ODonkey> type, Level level) {
		super(type, level);
	}

	private static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_donkey");
	private static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/donkey");
	private static final ResourceLocation TFC_LOOT_TABLE = new ResourceLocation("tfc", "entities/donkey");
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
				return new ODonkeyMenu(containerId, inventory, this.inventory, this);
			}, this.getDisplayName()), (data) -> {
				data.writeInt(this.getInventorySize());
				data.writeInt(this.getId());
			});
		}
	}

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0D, (double)this.getEyeHeight() * 0.6F, (double)(this.getBbWidth() * 1F));
		//              ^ Side offset                      ^ Height offset                   ^ Length offset
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

		this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, entity ->
				entity.getType().is(LOTags.Entity_Types.WOLVES) && (entity instanceof TamableAnimal && !((TamableAnimal) entity).isTame())
		));

		this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, entity ->
				entity.getType().is(LOTags.Entity_Types.CATS) &&
						!(entity instanceof TamableAnimal && ((TamableAnimal) entity).isTame())
		));

		this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, entity ->
				entity.getType().is(LOTags.Entity_Types.FOXES) && (entity instanceof TamableAnimal && !((TamableAnimal) entity).isTame())
		));
	}

	public float generateRandomMaxHealth() {
		return 20.0F + (float)this.random.nextInt(8) + (float)this.random.nextInt(9);
	}

	public double generateRandomJumpStrength() {
		return (double)0.2F + this.random.nextDouble() * 0.2D + this.random.nextDouble() * 0.2D + this.random.nextDouble() * 0.2D;
	}

	public double generateRandomSpeed() {
		return ((double)0.35F + this.random.nextDouble() * 0.3D + this.random.nextDouble() * 0.3D + this.random.nextDouble() * 0.3D) * 0.25D;
	}

	@Override
	protected boolean canPerformRearing() {
		return false;
	}

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
		double x = this.getX() - this.xo;
		double z = this.getZ() - this.zo;

		boolean isMoving = (x * x + z * z) > 0.0001;

		double movementSpeed = this.getAttributeBaseValue(Attributes.MOVEMENT_SPEED);
		double animationSpeed = Math.max(0.1, movementSpeed);

		AnimationController<T> controller = tAnimationState.getController();

		if(this.isJumping()) {
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

				} else {
					controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
					controller.setAnimationSpeed(Math.max(0.1, 0.82 * controller.getAnimationSpeed() + animationSpeed));
				}
			} else {
				if (this.isVehicle() || !LivestockOverhaulCommonConfig.GROUND_TIE.get()) {
					controller.setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
				} else {
					controller.setAnimation(RawAnimation.begin().then("idle3", Animation.LoopType.LOOP));
				}
				controller.setAnimationSpeed(1.0);
			}
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


	public int maxSprint = 40 * LivestockOverhaulCommonConfig.BASE_HORSE_SPRINT_TIME.get();
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

		Entity controllingPassenger = this.getControllingPassenger();
		Player player = (Player) controllingPassenger;
		int sprintLeftInSeconds = sprintTick / 20;

		if (this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD) && !(sprintTick <= 0) && this.hasControllingPassenger()) {
			sprintTick--;
			if (controllingPassenger instanceof Player && !(sprintTick <= 0)) {
				player.displayClientMessage(Component.translatable("Sprint Left: " + sprintLeftInSeconds + "s").withStyle(ChatFormatting.GOLD), true);
			}
		}

		if (!this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD) && sprintTick < maxSprint) {
			sprintTick++;
		}

		if (sprintTick <= 0 && this.hasControllingPassenger()) {
			AttributeInstance movementSpeed = this.getAttribute(Attributes.MOVEMENT_SPEED);
			this.handleSpeedRequest(-1);
			movementSpeed.removeModifier(SPRINT_SPEED_MOD);
			player.displayClientMessage(Component.translatable("Sprint Depleted").withStyle(ChatFormatting.DARK_RED), true);
		} else if (player == null || !this.hasControllingPassenger()) {
			return;
		}
	}

	@Override
	public void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
		if (this.hasPassenger(entity)) {
			double offsetX = 0;
			double offsetY = 0.65;
			double offsetZ = -0.1;

			if (this.isJumping()) {
//				offsetY = 1.7;
				offsetZ = -0.4;
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
		return SoundEvents.DONKEY_AMBIENT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return SoundEvents.DONKEY_DEATH;
	}

	@Nullable
	@Override
	public SoundEvent getEatingSound() {
		return SoundEvents.DONKEY_EAT;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource damageSource) {
		super.getHurtSound(damageSource);
		return SoundEvents.DONKEY_HURT;
	}

	@Override
	public SoundEvent getAngrySound() {
		super.getAngrySound();
		return SoundEvents.DONKEY_ANGRY;
	}

	public ResourceLocation getTextureResource() {
		return this.entityData.get(VARIANT_TEXTURE);
	}

	public ResourceLocation getOverlayLocation() {
		return this.entityData.get(OVERLAY_TEXTURE);
	}

	public int getVariant() {
		return this.entityData.get(VARIANT);
	}

	public int getOverlayVariant() {
		return this.entityData.get(OVERLAY);
	}

	public void setVariant(int variant) {
		this.entityData.set(VARIANT_TEXTURE, ODonkeyModel.Variant.variantFromOrdinal(variant).resourceLocation);
		this.entityData.set(VARIANT, variant);
	}

	public void setOverlayVariant(int variant) {
		this.entityData.set(OVERLAY_TEXTURE, ODonkeyMarkingLayer.Overlay.overlayFromOrdinal(variant).resourceLocation);
		this.entityData.set(OVERLAY, variant);
	}

	public void setVariantTexture(String variant) {
		ResourceLocation resourceLocation = ResourceLocation.tryParse(variant);
		if (resourceLocation == null) {
			resourceLocation = ODonkeyModel.Variant.DEFAULT.resourceLocation;
		}
		this.entityData.set(VARIANT_TEXTURE, resourceLocation);
	}

	public void setOverlayVariantTexture(String variant) {
		ResourceLocation resourceLocation = ResourceLocation.tryParse(variant);
		if (resourceLocation == null) {
			resourceLocation = ODonkeyMarkingLayer.Overlay.NONE.resourceLocation;
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
		tag.putInt("Gender", this.getGender());
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}
		Random random = new Random();
		this.setVariant(random.nextInt(ODonkeyModel.Variant.values().length));
		this.setOverlayVariant(random.nextInt(ODonkeyMarkingLayer.Overlay.values().length));
		this.setGender(random.nextInt(Gender.values().length));

		this.randomizeAttributes();
		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	@Override
	public void playEmote(String emoteName, String loopType) {
		AnimationController<?> controller = this.getAnimatableInstanceCache().getManagerForId(this.getId()).getAnimationControllers().get("emoteController");
		controller.forceAnimationReset();
		controller.stop();
		controller.setAnimation(RawAnimation.begin().then(emoteName, Animation.LoopType.fromString(loopType)));
		this.shouldEmote = true;
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
		this.entityData.define(GENDER, 0);
		this.entityData.define(VARIANT_TEXTURE, ODonkeyModel.Variant.DEFAULT.resourceLocation);
		this.entityData.define(OVERLAY_TEXTURE, ODonkeyMarkingLayer.Overlay.NONE.resourceLocation);
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
				if (LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get() && this.canParent() && partner.canParent() && ((isFemale() && partnerIsMale) || (isMale() && partnerIsFemale))) {
					return isFemale();
				}
			}
		}
		return false;
	}
	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		AbstractOMount abstracthorse;

		if (ageableMob instanceof OHorse horse) {

			abstracthorse = EntityTypes.O_MULE_ENTITY.get().create(serverLevel);

			int overlayChance = this.random.nextInt(9);
			int selectedOverlay;

			if (overlayChance < 4) {
				selectedOverlay = this.getOverlayVariant();
			} else if (overlayChance < 8) {
				selectedOverlay = horse.getOverlayVariant();
			} else {
				selectedOverlay = this.random.nextInt(OHorseMarkingLayer.Overlay.values().length);
			}

			((OMule) abstracthorse).setOverlayVariant(selectedOverlay);
			((OMule) abstracthorse).setVariant(random.nextInt(OMuleModel.Variant.values().length));

			//horse parent is stock, warmblood or racer
			if (horse.getBreed() == 0 || horse.getBreed() == 2 || horse.getBreed() == 4 || horse.getBreed() == 7 || horse.getBreed() == 9 || horse.getBreed() == 10 || horse.getBreed() == 13) {
				((OMule) abstracthorse).setBreed(0);
			}

			//horse parent is pony
			if (horse.getBreed() == 3 || horse.getBreed() == 6 || horse.getBreed() == 11) {
				((OMule) abstracthorse).setBreed(1);
			}

			//horse parent is draft
			if (horse.getBreed() == 1 || horse.getBreed() == 5 || horse.getBreed() == 8 || horse.getBreed() == 12) {
				((OMule) abstracthorse).setBreed(2);
			}

		} else {
			ODonkey donkey = (ODonkey) ageableMob;
			abstracthorse = EntityTypes.O_DONKEY_ENTITY.get().create(serverLevel);

			int i = this.random.nextInt(9);
			int variant;
			if (i < 4) {
				variant = this.getVariant();
			} else if (i < 8) {
				variant = donkey.getVariant();
			} else {
				variant = this.random.nextInt(ODonkeyModel.Variant.values().length);
			}

			int j = this.random.nextInt(5);
			int overlay;
			if (j < 2) {
				overlay = this.getOverlayVariant();
			} else if (j < 4) {
				overlay = donkey.getOverlayVariant();
			} else {
				overlay = this.random.nextInt(ODonkeyMarkingLayer.Overlay.values().length);
			}

			int gender;
			gender = this.random.nextInt(ODonkey.Gender.values().length);

			((ODonkey) abstracthorse).setVariant(variant);
			((ODonkey) abstracthorse).setOverlayVariant(overlay);
			((ODonkey) abstracthorse).setGender(gender);
		}

		this.setOffspringAttributes(ageableMob, abstracthorse);
		return abstracthorse;
	}

}
