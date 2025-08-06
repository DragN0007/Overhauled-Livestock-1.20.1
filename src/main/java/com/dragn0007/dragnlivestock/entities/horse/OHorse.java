package com.dragn0007.dragnlivestock.entities.horse;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.ai.GroundTieGoal;
import com.dragn0007.dragnlivestock.entities.ai.HorseFollowHerdLeaderGoal;
import com.dragn0007.dragnlivestock.entities.ai.OAvoidEntityGoal;
import com.dragn0007.dragnlivestock.entities.ai.ORunAroundLikeCrazyGoal;
import com.dragn0007.dragnlivestock.entities.donkey.ODonkey;
import com.dragn0007.dragnlivestock.entities.marking_layer.EquineEyeColorOverlay;
import com.dragn0007.dragnlivestock.entities.marking_layer.EquineMarkingOverlay;
import com.dragn0007.dragnlivestock.entities.mule.OMule;
import com.dragn0007.dragnlivestock.entities.mule.OMuleModel;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.entities.util.LOAnimations;
import com.dragn0007.dragnlivestock.event.LivestockOverhaulClientEvent;
import com.dragn0007.dragnlivestock.gui.OHorseMenu;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.items.custom.LightHorseArmorItem;
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
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class OHorse extends AbstractOMount implements GeoEntity {

	private static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_horse");
	private static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/horse");
	private static final ResourceLocation TFC_LOOT_TABLE = new ResourceLocation("tfc", "entities/horse");
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
	public boolean hasGrowableHair() {
		return true;
	}

	public OHorse leader;
	public int herdSize = 1;

	public OHorse(EntityType<? extends OHorse> type, Level level) {
		super(type, level);
		this.setCanDecompose(true);
	}

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0D, (double) this.getEyeHeight() * 0.8F, (double) (this.getBbWidth() * 1.2F));
		//              ^ Side offset                      ^ Height offset                   ^ Length offset
	}

	public static AttributeSupplier.Builder createBaseOHorseAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.JUMP_STRENGTH)
				.add(Attributes.MAX_HEALTH, 53.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.255F)
				.add(Attributes.ATTACK_DAMAGE, 1D);
	}

	public void randomizeOHorseAttributes() {
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.generateRandomOHorseMaxHealth());
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.generateRandomOHorseSpeed());
		this.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(this.generateRandomOHorseJumpStrength());
	}

	@Override
	public void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new GroundTieGoal(this));

		this.goalSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(1, new ORunAroundLikeCrazyGoal(this, 1.3F));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.7D));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.4, true));
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 12.0F));
		this.goalSelector.addGoal(3, new HorseFollowHerdLeaderGoal(this));
		this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D, AbstractOMount.class));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));

		this.goalSelector.addGoal(1, new OAvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, entity ->
				(entity.getType().is(LOTags.Entity_Types.WOLVES) && !this.isTamed()) ||
						(entity.getType().is(LOTags.Entity_Types.WOLVES) && (entity instanceof TamableAnimal && !((TamableAnimal) entity).isTame())) && this.isTamed()
		));

		this.goalSelector.addGoal(1, new OAvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity ->
				livingEntity.getType().is(LOTags.Entity_Types.HORSES) && (livingEntity instanceof AbstractHorse && livingEntity.isVehicle()) && !this.isLeashed() && LivestockOverhaulCommonConfig.HORSE_HERD_ANIMALS.get() && (this.isWearingRodeoHarness() || !this.isTamed())
		));

		this.goalSelector.addGoal(1, new OAvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.3F, 1.3F, livingEntity ->
				livingEntity instanceof OMule && livingEntity.isVehicle() && ((OMule) livingEntity).isSaddled() && (this.isWearingRodeoHarness() || !this.isTamed())
		));
	}

	public float generateRandomOHorseMaxHealth() {
		float baseHealth;
		int breed = getBreed();
		if (breed == 0) {
			baseHealth = 16.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		else if (breed == 1) {
			baseHealth = 20.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		else if (breed == 2) {
			baseHealth = 16.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		else if (breed == 3) {
			baseHealth = 14.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		else if (breed == 4) {
			baseHealth = 13.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		else if (breed == 5) {
			baseHealth = 18.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		else if (breed == 6) {
			baseHealth = 18.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		else if (breed == 7) {
			baseHealth = 16.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		else if (breed == 8) {
			baseHealth = 24.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		else if (breed == 9) {
			baseHealth = 17.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		else if (breed == 10) {
			baseHealth = 18.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		else if (breed == 11) {
			baseHealth = 20.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		else if (breed == 12) {
			baseHealth = 24.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		}
		else if (breed == 13) {
			baseHealth = 15.0F;
			return baseHealth + this.random.nextInt(3) + this.random.nextInt(5);
		} else {
			return 15.0F + (float) this.random.nextInt(4) + (float) this.random.nextInt(5);
		}
	}

	public double generateRandomOHorseJumpStrength() {
		double baseStrength = 0.4F;
		int breed = getBreed();
		double multiplier = this.random.nextDouble() * 0.2D + this.random.nextDouble() * 0.2D + this.random.nextDouble() * 0.25D;

		if (breed == 0) {
			baseStrength = 0.5F;
			return baseStrength + multiplier;
		}
		if (breed == 1) {
			baseStrength = 0.3F;
			return baseStrength + multiplier;
		}
		if (breed == 2) {
			baseStrength = 0.4F;
			return baseStrength + multiplier;
		}
		if (breed == 3) {
			baseStrength = 0.35F;
			return baseStrength + multiplier;
		}
		if (breed == 4) {
			baseStrength = 0.35F;
			return baseStrength + multiplier;
		}
		if (breed == 5) {
			baseStrength = 0.35F;
			return baseStrength + multiplier;
		}
		if (breed == 6) {
			baseStrength = 0.3F;
			return baseStrength + multiplier;
		}
		if (breed == 7) {
			baseStrength = 0.4F;
			return baseStrength + multiplier;
		}
		if (breed == 8) {
			baseStrength = 0.25F;
			return baseStrength + multiplier;
		}
		if (breed == 9) {
			baseStrength = 0.55F;
			return baseStrength + multiplier;
		}
		if (breed == 10) {
			baseStrength = 0.4F;
			return baseStrength + multiplier;
		}
		if (breed == 11) {
			baseStrength = 0.3F;
			return baseStrength + multiplier;
		}
		if (breed == 12) {
			baseStrength = 0.2F;
			return baseStrength + multiplier;
		}
		if (breed == 13) {
			baseStrength = 0.3F;
			return baseStrength + multiplier;
		} else {
			return baseStrength + this.random.nextDouble() * 0.15D;
		}
	}

	public double generateRandomOHorseSpeed() {
		double baseSpeed = 0.0F;
		int breed = getBreed();
		double multiplier = (this.random.nextDouble() * 0.1D + this.random.nextDouble() * 0.1D + this.random.nextDouble() * 0.1D) * 0.30D;

		if (breed == 0) {
			baseSpeed = 0.2F;
			return baseSpeed + multiplier;
		}
		if (breed == 1) {
			baseSpeed = 0.15F;
			return baseSpeed + multiplier;
		}
		if (breed == 2) {
			baseSpeed = 0.2F;
			return baseSpeed + multiplier;
		}
		if (breed == 3) {
			baseSpeed = 0.15F;
			return baseSpeed + multiplier;
		}
		if (breed == 4) {
			baseSpeed = 0.28F;
			return baseSpeed + multiplier;
		}
		if (breed == 5) {
			baseSpeed = 0.2F;
			return baseSpeed + multiplier;
		}
		if (breed == 6) {
			baseSpeed = 0.15F;
			return baseSpeed + multiplier;
		}
		if (breed == 7) {
			baseSpeed = 0.2F;
			return baseSpeed + multiplier;
		}
		if (breed == 8) {
			baseSpeed = 0.15F;
			return baseSpeed + multiplier;
		}
		if (breed == 9) {
			baseSpeed = 0.2F;
			return baseSpeed + multiplier;
		}
		if (breed == 10) {
			baseSpeed = 0.2F;
			return baseSpeed + multiplier;
		}
		if (breed == 11) {
			baseSpeed = 0.2F;
			return baseSpeed + multiplier;
		}
		if (breed == 12) {
			baseSpeed = 0.2F;
			return baseSpeed + multiplier;
		}
		if (breed == 13) {
			baseSpeed = 0.3F;
			return baseSpeed + multiplier;
		} else {
			return baseSpeed + multiplier;
		}
	}

	public boolean isDraftBreed() {
		return this.getBreed() == 1 || this.getBreed() == 5 || this.getBreed() == 8 || this.getBreed() == 12 || this.getBreed() == 14;
	}

	public boolean isPonyBreed() {
		return this.getBreed() == 3 || this.getBreed() == 6 || this.getBreed() == 11;
	}

	public boolean isStockBreed() {
		return this.getBreed() == 0 || this.getBreed() == 7;
	}

	public boolean isWarmbloodedBreed() {
		return this.getBreed() == 2 || this.getBreed() == 9 || this.getBreed() == 10;
	}

	public boolean isRacingBreed() {
		return this.getBreed() == 4 || this.getBreed() == 13;
	}

	@Override
	public boolean canAddPassenger(Entity entity) {
		if (!this.isDraftBreed()) {
			return this.getPassengers().size() < 1;
		} else if (this.isDraftBreed()) {
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
	public int getInventorySize() {
		if (this.hasChest()) {
		    if (this.isDraftBreed()) {
				return 18;
			} else if (this.isPonyBreed()) {
				return 15;
			} else if (this.isStockBreed() || this.isWarmbloodedBreed()) {
				return 12;
			} else if (this.isRacingBreed()) {
				return 6;
			} else {
				return 18;
			}
		} else {
			return 3;
		}
	}

	@Override
	public void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
		if (this.hasPassenger(entity)) {

			LocalDate date = LocalDate.now();
			Month month = date.getMonth();
			int day = date.getDayOfMonth();

			double offsetX = 0;
			double offsetY = 1.0;
			double offsetZ = -0.055;

			int i = this.getPassengers().indexOf(entity);

			if (getBreed() == 0) {
				offsetY = 1.03;
			}

			if (getBreed() == 1) {
				switch (i) {
					case 0:
						offsetY = 1.05;
						break;
					case 1:
						offsetY = 1.05;
						offsetZ = -1.23;
						break;
				}
			}

			if (getBreed() == 2) {
				offsetY = 1.2;
			}

			if (getBreed() == 3) {
				offsetY = 0.9;
			}

			if (getBreed() == 4) {
				offsetY = 1.2;
			}

			if (getBreed() == 5) {
				switch (i) {
					case 0:
						offsetY = 1.3;
						break;
					case 1:
						offsetY = 1.25;
						offsetZ = -1.2;
						break;
				}
			}

			if (getBreed() == 6) {
				switch (i) {
					case 0:
						offsetY = 0.97;
						break;
					case 1:
						offsetY = 0.97;
						offsetZ = -1.2;
						break;
				}
			}

			if (getBreed() == 7) {
				offsetY = 1.05;
			}

			if (getBreed() == 8) {
				switch (i) {
					case 0:
						offsetY = 1.4;
						break;
					case 1:
						offsetY = 1.4;
						offsetZ = -1.2;
						break;
				}
			}

			if (getBreed() == 9) {
				offsetY = 1.05;
			}

			if (getBreed() == 10) {
				offsetY = 1.05;
			}

			if (getBreed() == 11) {
				offsetY = 0.8;
			}

			if (getBreed() == 12) {
				switch (i) {
					case 0:
						offsetY = 1.45;
						break;
					case 1:
						offsetY = 1.45;
						offsetZ = -1.2;
						break;
				}
			}

			if (getBreed() == 13) {
				offsetY = 1.1;
			}

			if (getBreed() == 14) {
				offsetY = 1.33;
			}

			if (month == Month.DECEMBER && (day == 24 || day == 25)) {
				offsetY = 1.0;
				offsetZ = -0.01;
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
	public void openInventory(Player player) {
		if(player instanceof ServerPlayer serverPlayer && this.isTamed() && ((this.isOwnedBy(player) && this.isLocked()) || !this.isLocked())) {
			NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider((containerId, inventory, p) -> {
				return new OHorseMenu(containerId, inventory, this.inventory, this);
			}, this.getDisplayName()), (data) -> {
				data.writeInt(this.getInventorySize());
				data.writeInt(this.getId());
			});
		}
	}

	@Override
	public boolean canPerformRearing() {
		return false;
	}

	@Override
	public boolean dismountsUnderwater() {
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

		if ((!this.isTamed() || this.isWearingRodeoHarness()) && this.isVehicle() && !this.isJumping()) {
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
						controller.setAnimationSpeed(Math.max(0.1, 0.82 * controller.getAnimationSpeed() + animationSpeed));

					} else if (this.isVehicle() && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(WALK_SPEED_MOD) && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD)) {
						controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
						controller.setAnimationSpeed(Math.max(0.1, 0.78 * controller.getAnimationSpeed() + animationSpeed));

					} else if (this.isOnSand() && this.isVehicle() && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(WALK_SPEED_MOD) && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD)) {
						controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
						controller.setAnimationSpeed(Math.max(0.1, 0.78 * controller.getAnimationSpeed() + animationSpeed));

					} else if (this.isVehicle() && this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(WALK_SPEED_MOD)) {
						controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
						controller.setAnimationSpeed(Math.max(0.1, 0.83 * controller.getAnimationSpeed() + animationSpeed));

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
				} else if (this.isSleeping()) {
					controller.setAnimation(RawAnimation.begin().then("idle_sleep", Animation.LoopType.LOOP));
				} else if (this.isSleeping() && !this.isVehicle() && this.isFollower()) {
					controller.setAnimation(RawAnimation.begin().then("sleep", Animation.LoopType.LOOP));
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

	public boolean isFollower() {
		return this.leader != null && this.leader.isAlive() && (!this.isSaddled() && !this.isLeashed() && !this.isGroundTied());
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
		return this.hasFollowers() && this.herdSize < this.getMaxHerdSize() && (!this.isSaddled() && !this.isLeashed() && !this.isGroundTied());
	}

	public int getMaxHerdSize() {
		return LivestockOverhaulCommonConfig.HORSE_HERD_MAX.get();
	}

	public boolean hasFollowers() {
		return this.herdSize > 1;
	}

	public boolean inRangeOfLeader() {
		return this.distanceToSqr(this.leader) <= 121.0D && (!this.isSaddled() && !this.isLeashed() && !this.isGroundTied());
	}

	public void pathToLeader() {
		if (this.isFollower() && (!this.isSaddled() && !this.isLeashed() && !this.isGroundTied())) {
			this.getNavigation().moveTo(this.leader, 1.0D);
		}
	}

	public void addFollowers(Stream<? extends OHorse> stream) {
		stream.limit(this.getMaxHerdSize() - this.herdSize).filter((horse) -> horse != this).forEach((horse) -> {
			horse.startFollowing(this);
		});
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (this.isUndead()) {
			this.level().addParticle(ParticleTypes.ASH, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
		}

	}

	@Override
	public boolean hurt(DamageSource damageSource, float v) {
		if (!this.isUndead() && random.nextDouble() < 0.03 && LivestockOverhaulCommonConfig.UNDEAD_HORSE_DEATH.get()) {
			if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
				System.out.println("[DragN's Livestock Overhaul!]: An OHorse has turned undead at: " + this.getOnPos());
			}
			if (this.getHealth() <= 4) {
				this.heal(10);
				this.setUndead(true);
				this.level().playSound(null, this, this.getDeathSound(), SoundSource.NEUTRAL, 1.0F, Mth.randomBetween(this.level().random, 0.8F, 1.2F));
				this.level().addParticle(ParticleTypes.SOUL_FIRE_FLAME, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
				this.level().addParticle(ParticleTypes.SOUL, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
				this.level().playSound(null, this, SoundEvents.ZOMBIE_HORSE_AMBIENT, SoundSource.NEUTRAL, 1.0F, Mth.randomBetween(this.level().random, 0.8F, 1.2F));
				return false;
			}
		}
		if (this.isUndead()) {
			if (damageSource.is(DamageTypes.IN_FIRE) || damageSource.is(DamageTypes.EXPLOSION)) {
				return false;
			}
		}
		return super.hurt(damageSource, v);
	}

	public MobType getMobType() {
		if (this.isUndead()) {
			return MobType.UNDEAD;
		}
		return null;
	}

	public int maxSprint = 20 * LivestockOverhaulCommonConfig.BASE_HORSE_SPRINT_TIME.get();
	public int sprintTick = maxSprint;
	public int warmbloodSprintAddition = 1200;
	public int stockSprintAddition = 800;
	public int draftSprintAddition = 400;
	public int ponySprintAddition = 200;
	public int maneGrowthTick;
	public int tailGrowthTick;
	public int decompTick;

	@Override
	public void tick() {
		super.tick();

		if (this.hasFollowers() && this.level().random.nextInt(200) == 1) {
			List<? extends OHorse> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
			if (list.size() <= 1) {
				this.herdSize = 1;
			}
		}

		List<ItemStack> armorSlots = (List<ItemStack>) this.getArmorSlots();
		ItemStack armorItemStack = armorSlots.get(2);
		
		if (this.isOnSand()) {
			if (!this.hasSlownessEffect()) {
				this.applySlownessEffect();
			}
		} else if (!(!this.isDraftBreed() && armorItemStack.getItem() instanceof HorseArmorItem) || !(this.isRacingBreed() && armorItemStack.getItem() instanceof LightHorseArmorItem)){
			if (this.hasSlownessEffect()) {
				this.removeSlownessEffect();
			}
		}

		if (armorItemStack.getItem() instanceof HorseArmorItem) {
			if (!this.isDraftBreed()) {
				if (!this.hasSlownessEffect()) {
					this.applySlownessEffect();
				}
			} else if (!this.isOnSand()) {
				if (this.hasSlownessEffect()) {
					this.removeSlownessEffect();
				}
			}
		} else if (armorItemStack.getItem() instanceof LightHorseArmorItem) {
			if (this.isRacingBreed()) {
				if (!this.hasSlownessEffect()) {
					this.applySlownessEffect();
				}
			} else if (!this.isOnSand()) {
				if (this.hasSlownessEffect()) {
					this.removeSlownessEffect();
				}
			}
		}

		if (!this.isBaby() && LivestockOverhaulCommonConfig.HORSE_HAIR_GROWTH.get()) {
			maneGrowthTick++;
			tailGrowthTick++;

			if (maneGrowthTick >= LivestockOverhaulCommonConfig.HORSE_HAIR_GROWTH_TIME.get() && this.getManeType() > 1) {
				this.setManeType(this.getManeType() - 1);
				maneGrowthTick = 0;
			}

			if (tailGrowthTick >= LivestockOverhaulCommonConfig.HORSE_HAIR_GROWTH_TIME.get() && this.getTailType() > 1) {
				this.setTailType(this.getTailType() - 1);
				tailGrowthTick = 0;
			}
		}

		if (this.isUndead() && this.getDecompVariant() < 4 && this.canDecompose()) {
			decompTick++;

			if (decompTick >= LivestockOverhaulCommonConfig.DECOMPISITION_STAGE_TIME.get()) {
				if (this.level().getBiome(this.blockPosition()).is(Tags.Biomes.IS_HOT_NETHER)) {
					this.setDecompVariant(5);
				} else if (this.level().getBiome(this.blockPosition()).is(Tags.Biomes.IS_COLD_OVERWORLD)) {
					this.setDecompVariant(6);
				} else if (this.isInWater()) {
					this.setDecompVariant(7);
				} else if (this.level().getBiome(this.blockPosition()).is(Tags.Biomes.IS_DESERT)) {
					this.setDecompVariant(8);
				} else {
					this.setDecompVariant(this.getDecompVariant() + 1);
				}
				decompTick = 0;
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
			if (((this.isWarmbloodedBreed() && sprintTick < (maxSprint + warmbloodSprintAddition)) ||
					(this.isStockBreed() && sprintTick < (maxSprint + stockSprintAddition)) ||
					(this.isDraftBreed() && sprintTick < (maxSprint + draftSprintAddition)) ||
					(this.isPonyBreed() && sprintTick < (maxSprint + ponySprintAddition)) ||
					(this.isRacingBreed() && sprintTick < maxSprint)) && isMoving) {
				sprintTick++;
			} else if (((this.isWarmbloodedBreed() && sprintTick < (maxSprint + warmbloodSprintAddition)) ||
					(this.isStockBreed() && sprintTick < (maxSprint + stockSprintAddition)) ||
					(this.isDraftBreed() && sprintTick < (maxSprint + draftSprintAddition)) ||
					(this.isPonyBreed() && sprintTick < (maxSprint + ponySprintAddition)) ||
					(this.isRacingBreed() && sprintTick < maxSprint)) && !isMoving) {
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
	public SoundEvent getAmbientSound() {
		super.getAmbientSound();
		if (this.isUndead()) {
			return SoundEvents.ZOMBIE_HORSE_AMBIENT;
		}
		return SoundEvents.HORSE_AMBIENT;
	}

	@Override
	public SoundEvent getDeathSound() {
		if (this.isUndead()) {
			return SoundEvents.ZOMBIE_HORSE_DEATH;
		}
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
		if (this.isUndead()) {
			return SoundEvents.ZOMBIE_HORSE_HURT;
		}
		return SoundEvents.HORSE_HURT;
	}

	@Override
	public SoundEvent getAngrySound() {
		super.getAngrySound();
		return SoundEvents.HORSE_ANGRY;
	}

	public static final EntityDataAccessor<Integer> BREED = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.INT);
	public ResourceLocation getModelResource() {
		return HorseBreed.breedFromOrdinal(getBreed()).resourceLocation;
	}
	public int getBreed() {
		return this.entityData.get(BREED);
	}
	public void setBreed(int breed) {
		this.entityData.set(BREED, breed);
	}


	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.INT);
	public int getVariant() {
		return this.entityData.get(VARIANT);
	}
	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
		this.entityData.set(VARIANT_TEXTURE, OHorseModel.Variant.variantFromOrdinal(variant).resourceLocation);
	}
	public static final EntityDataAccessor<ResourceLocation> VARIANT_TEXTURE = SynchedEntityData.defineId(OHorse.class, LivestockOverhaul.RESOURCE_LOCATION);
	public ResourceLocation getTextureResource() {
		return this.entityData.get(VARIANT_TEXTURE);
	}
	public void setVariantTexture(String variant) {
		ResourceLocation resourceLocation = ResourceLocation.tryParse(variant);
		if (resourceLocation == null) {
			resourceLocation = OHorseModel.Variant.BAY.resourceLocation;
		}
		this.entityData.set(VARIANT_TEXTURE, resourceLocation);
	}


	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.INT);
	public int getOverlayVariant() {
		return this.entityData.get(OVERLAY);
	}
	public void setOverlayVariant(int variant) {
		this.entityData.set(OVERLAY, variant);
		this.entityData.set(OVERLAY_TEXTURE, EquineMarkingOverlay.overlayFromOrdinal(variant).resourceLocation);
	}
	public static final EntityDataAccessor<ResourceLocation> OVERLAY_TEXTURE = SynchedEntityData.defineId(OHorse.class, LivestockOverhaul.RESOURCE_LOCATION);
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

	public static final EntityDataAccessor<Integer> EYES = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.INT);
	public ResourceLocation getEyeTextureResource() {
		return EquineEyeColorOverlay.eyesFromOrdinal(getEyeVariant()).resourceLocation;
	}
	public int getEyeVariant() {
		return this.entityData.get(EYES);
	}
	public void setEyeVariant(int eyeVariant) {
		this.entityData.set(EYES, eyeVariant);
	}

	public static final EntityDataAccessor<Integer> REINDEER_VARIANT = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.INT);
	public ResourceLocation getReindeerTextureResource() {
		return OHorseModel.ReindeerVariant.reindeerVariantFromOrdinal(getReindeerVariant()).resourceLocation;
	}
	public int getReindeerVariant() {
		return this.entityData.get(REINDEER_VARIANT);
	}
	public void setReindeerVariant(int reindeerVariant) {
		this.entityData.set(REINDEER_VARIANT, reindeerVariant);
	}

	public enum Feathering {
		NONE,
		HALF,
		FULL;
		public Feathering next() {
			return Feathering.values()[(this.ordinal() + 1) % Feathering.values().length];
		}
	}
	public static final EntityDataAccessor<Integer> FEATHERING = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.INT);
	public int getFeathering() {
		return this.entityData.get(FEATHERING);
	}
	public void setFeathering(int feathering) {
		this.entityData.set(FEATHERING, feathering);
	}

	public static final EntityDataAccessor<Boolean> UNDEAD = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.BOOLEAN);
	public boolean isUndead() {
		return this.entityData.get(UNDEAD);
	}
	public void setUndead(boolean undead) {
		this.entityData.set(UNDEAD, undead);
	}

	public static final EntityDataAccessor<Integer> DECOMP = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.INT);
	public int getDecompVariant() {
		return this.entityData.get(DECOMP);
	}
	public void setDecompVariant(int decompVariant) {
		this.entityData.set(DECOMP, decompVariant);
	}

	public static final EntityDataAccessor<Boolean> DECOMPOSE = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.BOOLEAN);
	public boolean canDecompose() {
		return this.entityData.get(DECOMPOSE);
	}
	public void setCanDecompose(boolean canDecompose) {
		this.entityData.set(DECOMPOSE, canDecompose);
	}

	public static final EntityDataAccessor<ItemStack> FLOWER_ITEM = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.ITEM_STACK);
	public ItemStack getFlowerItem() {
		return this.entityData.get(FLOWER_ITEM);
	}
	public void setFlowerItem(ItemStack decorItem) {
		this.entityData.set(FLOWER_ITEM, decorItem);
	}

	public static final EntityDataAccessor<Integer> FLOWER_TYPE = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.INT);
	public int getFlowerType() {
		return this.entityData.get(FLOWER_TYPE);
	}
	public void setFlowerType(int decompVariant) {
		this.entityData.set(FLOWER_TYPE, decompVariant);
	}

	public static final EntityDataAccessor<Boolean> BRANDED = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.BOOLEAN);
	public boolean isBranded() {
		return this.entityData.get(BRANDED);
	}
	public void setIsBranded(boolean canBeBranded) {
		this.entityData.set(BRANDED, canBeBranded);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);

		if (tag.contains("Breed")) {
			this.setBreed(tag.getInt("Breed"));
		}

		if (tag.contains("Variant")) {
			this.setVariant(tag.getInt("Variant"));
		}

		if (tag.contains("Overlay")) {
			this.setOverlayVariant(tag.getInt("Overlay"));
		}

//		if (LivestockOverhaulCommonConfig.DYNAMIC_RESOURCES.get()) {
			if (tag.contains("Variant_Texture")) {
				this.setVariantTexture(tag.getString("Variant_Texture"));
			}

			if (tag.contains("Overlay_Texture")) {
				this.setOverlayVariantTexture(tag.getString("Overlay_Texture"));
			}
//		}

		if (tag.contains("Reindeer_Variant")) {
			this.setReindeerVariant(tag.getInt("Reindeer_Variant"));
		}

		if (tag.contains("Decomp_Stage")) {
			this.setDecompVariant(tag.getInt("Decomp_Stage"));
		}

		if (tag.contains("Gender")) {
			this.setGender(tag.getInt("Gender"));
		}

		if (tag.contains("Mane")) {
			this.setManeType(tag.getInt("Mane"));
		}

		if (tag.contains("Tail")) {
			this.setTailType(tag.getInt("Tail"));
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

		if (tag.contains("ManeGrowthTime")) {
			this.maneGrowthTick = tag.getInt("ManeGrowthTime");
		}

		if (tag.contains("TailGrowthTime")) {
			this.tailGrowthTick = tag.getInt("TailGrowthTime");
		}

		if (tag.contains("Undead")) {
			this.setUndead(tag.getBoolean("Undead"));
		}

		if (tag.contains("CanDecompose")) {
			this.setCanDecompose(tag.getBoolean("CanDecompose"));
		}

		if (tag.contains("Flower_Type")) {
			this.setFlowerType(tag.getInt("Flower_Type"));
		}

		if(tag.contains("FlowerItem")) {
			ItemStack decorItem = ItemStack.of(tag.getCompound("FlowerItem"));
			this.setFlowerItem(decorItem);
		}

		if (tag.contains("IsBranded")) {
			this.setIsBranded(tag.getBoolean("IsBranded"));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Breed", this.getBreed());
		tag.putInt("Variant", this.getVariant());
		tag.putInt("Overlay", this.getOverlayVariant());
//		if (LivestockOverhaulCommonConfig.DYNAMIC_RESOURCES.get()) {
			tag.putString("Variant_Texture", this.getTextureResource().toString());
			tag.putString("Overlay_Texture", this.getOverlayLocation().toString());
//		}
		tag.putInt("Reindeer_Variant", this.getReindeerVariant());
		tag.putInt("Decomp_Stage", this.getDecompVariant());
		tag.putInt("Gender", this.getGender());
		tag.putInt("Mane", this.getManeType());
		tag.putInt("Tail", this.getTailType());
		tag.putInt("Feathering", this.getFeathering());
		tag.putInt("Eyes", this.getEyeVariant());
		tag.putInt("SprintTime", this.sprintTick);
		tag.putInt("ManeGrowthTime", this.maneGrowthTick);
		tag.putInt("TailGrowthTime", this.tailGrowthTick);
		tag.putBoolean("Undead", this.isUndead());
		tag.putBoolean("CanDecompose", this.canDecompose());
		tag.putInt("Flower_Type", this.getFlowerType());
		if(!this.getFlowerItem().isEmpty()) {
			tag.put("FlowerItem", this.getFlowerItem().save(new CompoundTag()));
		}
		tag.putBoolean("IsBranded", this.isBranded());
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}

		Random random = new Random();

		if (spawnType == MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.NATURAL_HORSE_BREEDS.get()) {
			if (!ModList.get().isLoaded("deadlydinos")) {
				int[] breeds = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
				int randomIndex = new Random().nextInt(breeds.length);
				this.setBreed(breeds[randomIndex]);
			} else {
				this.setBreed(random.nextInt(HorseBreed.values().length));
			}
		}

		this.setReindeerVariant(random.nextInt(OHorseModel.ReindeerVariant.values().length));
		this.setGender(random.nextInt(Gender.values().length));

		if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
			this.setColorByBreed();
			this.setMarkingByBreed();
			this.setFeatheringByBreed();
		} else {
			this.setVariant(random.nextInt(OHorseModel.Variant.values().length));
			this.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
			this.setFeathering(random.nextInt(Feathering.values().length));
		}

		if (LivestockOverhaulCommonConfig.EYES_BY_COLOR.get()) {
			this.setEyeColorByChance();
		} else {
			this.setEyeVariant(random.nextInt(EquineMarkingOverlay.values().length));
		}

		int randomMane = 1 + this.getRandom().nextInt(3);
		this.setManeType(randomMane);

		int randomTail = 1 + this.getRandom().nextInt(3);
		this.setTailType(randomTail);

		this.randomizeOHorseAttributes();
		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(BREED, 0);
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
		this.entityData.define(GENDER, 0);
		this.entityData.define(VARIANT_TEXTURE, OHorseModel.Variant.BAY.resourceLocation);
		this.entityData.define(OVERLAY_TEXTURE, EquineMarkingOverlay.NONE.resourceLocation);
		this.entityData.define(REINDEER_VARIANT, 0);
		this.entityData.define(DECOMP, 0);
		this.entityData.define(MANE_TYPE, 0);
		this.entityData.define(TAIL_TYPE, 0);
		this.entityData.define(FEATHERING, 0);
		this.entityData.define(EYES, 0);
		this.entityData.define(UNDEAD, false);
		this.entityData.define(DECOMPOSE, false);
		this.entityData.define(FLOWER_ITEM, ItemStack.EMPTY);
		this.entityData.define(FLOWER_TYPE, 0);
		this.entityData.define(BRANDED, false);
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
					return this.isFemale();
				}
			}
		}
		return false;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		AbstractOMount foal;
		if (ageableMob instanceof ODonkey partnerDonkey) {

			foal = EntityTypes.O_MULE_ENTITY.get().create(serverLevel);

			int overlayChance = this.random.nextInt(10);
			int overlay;
			if (overlayChance < 4) {
				overlay = this.getOverlayVariant();
			} else if (overlayChance < 8) {
				overlay = partnerDonkey.getOverlayVariant();
			} else {
				overlay = this.random.nextInt(EquineMarkingOverlay.values().length);
			}
			((OMule) foal).setVariant(overlay);

			((OMule) foal).setOverlayVariant(overlay);
			((OMule) foal).setVariant(random.nextInt(OMuleModel.Variant.values().length));

			if (this.isStockBreed() || this.isWarmbloodedBreed() || this.isRacingBreed()) {
				((OMule) foal).setBreed(0);
			}

			if (this.isPonyBreed()) {
				((OMule) foal).setBreed(1);
			}

			if (this.isDraftBreed()) {
				((OMule) foal).setBreed(2);
			}

		} else {
			OHorse partner = (OHorse) ageableMob;
			foal = EntityTypes.O_HORSE_ENTITY.get().create(serverLevel);

			int breedChance = this.random.nextInt(5);
			int breed;
			if (breedChance == 0) {
				if (!ModList.get().isLoaded("deadlydinos")) {
					int[] breeds = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
					int randomIndex = new Random().nextInt(breeds.length);
					breed = (breeds[randomIndex]);
				} else {
					breed = this.random.nextInt(HorseBreed.values().length);
				}
			} else {
				breed = (this.random.nextInt(2) == 0) ? this.getBreed() : partner.getBreed();
			}
			((OHorse) foal).setBreed(breed);

			if (!(breedChance == 0)) {
				int variantChance = this.random.nextInt(14);
				int variant;
				if (variantChance < 6) {
					variant = this.getVariant();
				} else if (variantChance < 12) {
					variant = partner.getVariant();
				} else {
					variant = this.random.nextInt(OHorseModel.Variant.values().length);
				}
				((OHorse) foal).setVariant(variant);
			} else if (breedChance == 0 && random.nextDouble() < 0.5) {
				((OHorse) foal).setColorByBreed();
			}

			if (!(breedChance == 0)) {
				int overlayChance = this.random.nextInt(10);
				int overlay;
				if (overlayChance < 4) {
					overlay = this.getOverlayVariant();
				} else if (overlayChance < 8) {
					overlay = partner.getOverlayVariant();
				} else {
					overlay = this.random.nextInt(EquineMarkingOverlay.values().length);
				}
				((OHorse) foal).setOverlayVariant(overlay);
			} else if (breedChance == 0 && random.nextDouble() < 0.5) {
				((OHorse) foal).setMarkingByBreed();
			}

			int eyeColorChance = this.random.nextInt(11);
			int eyes;
			if (eyeColorChance < 5) {
				eyes = this.getEyeVariant();
			} else if (eyeColorChance < 10) {
				eyes = partner.getEyeVariant();
			} else {
				eyes = this.random.nextInt(EquineEyeColorOverlay.values().length);
			}
			((OHorse) foal).setEyeVariant(eyes);

			int gender;
			gender = this.random.nextInt(OHorse.Gender.values().length);
			foal.setGender(gender);

			((OHorse) foal).setFeatheringByBreed();
			((OHorse) foal).setManeType(3);
			((OHorse) foal).setTailType(2);


			if (this.random.nextInt(3) >= 1) {
				((OHorse) foal).generateRandomOHorseJumpStrength();

				int betterSpeed = (int) Math.max(partner.getSpeed(), this.random.nextInt(10) + 20);
				foal.setSpeed(betterSpeed);

				int betterHealth = (int) Math.max(partner.getHealth(), this.random.nextInt(20) + 40);
				foal.setHealth(betterHealth);

			//generate random stats of the breed standard of the baby if the breed is random
			} else if (breedChance == 0) {
				((OHorse) foal).randomizeOHorseAttributes();
			}
		}

		this.setOffspringAttributes(ageableMob, foal);
		return foal;
	}

	public enum Mane {
		BUTTONS,
		LONG,
		ROACHED,
		SHORT,
		NONE;

		public Mane next() {
			return Mane.values()[(this.ordinal() + 1) % Mane.values().length];
		}
	}

	public enum Tail {
		TIED,
		LONG,
		AVERAGE,
		SHORT,
		TUCKED;

		public Tail next() {
			return Tail.values()[(this.ordinal() + 1) % Tail.values().length];
		}
	}

	public static final EntityDataAccessor<Integer> MANE_TYPE = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.INT);
	public int getManeType() {
		return this.entityData.get(MANE_TYPE);
	}
	public void setManeType(int mane) {
		this.entityData.set(MANE_TYPE, mane);
	}

	public static final EntityDataAccessor<Integer> TAIL_TYPE = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.INT);
	public int getTailType() {
		return this.entityData.get(TAIL_TYPE);
	}
	public void setTailType(int tail) {
		this.entityData.set(TAIL_TYPE, tail);
	}

	public void setFeatheringByBreed() {

		//warmbloods are more likely to have half or no feathering, but have a very small chance of having full.
		if (this.isWarmbloodedBreed()) {
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

		//drafts almost always have full feathering, but have small chances of having half, or a very small chance of having none.
		if (this.isDraftBreed()) {
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

		//ponies are more likely to have half or full feathering, but have a small chance of having none.
		if (this.isPonyBreed()) {
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

		//racing horses almost always have no feathering, but have a very small chance of having half or full.
		if (this.isRacingBreed()) {
			if (random.nextDouble() < 0.03) {
				this.setFeathering(2);
			} else if (random.nextDouble() < 0.08 && random.nextDouble() > 0.03) {
				this.setFeathering(1);
			} else if (random.nextDouble() > 0.08) {
				this.setFeathering(0);
			} else {
				this.setFeathering(0);
			}
		}

		//stock horses almost always have no feathering, but have a small chance of having half or a very small chance of having full.
		if (this.isStockBreed()) {
			if (random.nextDouble() < 0.03) {
				this.setFeathering(2);
			} else if (random.nextDouble() < 0.20 && random.nextDouble() > 0.03) {
				this.setFeathering(1);
			} else if (random.nextDouble() > 0.20) {
				this.setFeathering(0);
			} else {
				this.setFeathering(0);
			}
		}

	}

	public void setEyeColorByChance() {

		//white, cream and mostly-white or bald horses have a better chance of gaining blue or green eyes
		if (this.getVariant() == 24 || this.getVariant() == 25 || this.getOverlayVariant() == 2 || this.getOverlayVariant() == 8
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

	public void setColorByBreed() {

		if (this.getBreed() == 0) { //mustangs can come in any color naturally, aside from fjord coloring
			int[] variants = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15,
					16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
			int randomIndex = new Random().nextInt(variants.length);
			this.setVariant(variants[randomIndex]);
		}

		if (this.getBreed() == 1) { //ardennes tend to come in browns, roans and greys
			if (random.nextDouble() < 0.05) {
				int[] variants = {4, 5, 8, 11, 19, 25, 26, 27, 28, 29, 30};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() < 0.30 && random.nextDouble() > 0.05) {
				int[] variants = {1, 2, 3, 7, 9, 16, 17, 18, 20, 21};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.30) {
				int[] variants = {0, 6, 10, 12, 13, 15, 22, 23, 24, 31};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 2) { //kladrubers tend to come in blacks and greys
			if (random.nextDouble() < 0.02) {
				int[] variants = {8, 11, 25, 31};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() < 0.20 && random.nextDouble() > 0.02) {
				int[] variants = {0, 1, 3, 6, 7, 10, 12, 13, 16, 17, 18, 19, 20, 21, 26, 27, 28, 29, 30};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.20) {
				int[] variants = {2, 4, 5, 9, 15, 22, 23, 24};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 3) { //fjords tend to come in only the fjord coloring
			if (random.nextDouble() < 0.05) {
				this.setVariant(random.nextInt(OHorseModel.Variant.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {14};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 4) { //thoroughbreds can come in any color naturally, aside from fjord coloring. they usually come in bays
			if (random.nextDouble() < 0.40) {
				int[] variants = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15,
						16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.40) {
				int[] variants = {0, 1, 2, 3, 6, 10, 12, 13, 17, 20, 21, 22, 26};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 5) { //friesians usually just come in black
			if (random.nextDouble() < 0.02) {
				int[] variants = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15,
						16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.02) {
				this.setVariant(2);
			}
		}

		if (this.getBreed() == 6) { //irish cobs can come in any color naturally, aside from fjord coloring
			int[] variants = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15,
					16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
			int randomIndex = new Random().nextInt(variants.length);
			this.setVariant(variants[randomIndex]);
		}

		if (this.getBreed() == 7) { //american quarters can come in any color naturally, aside from fjord coloring
			int[] variants = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15,
					16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
			int randomIndex = new Random().nextInt(variants.length);
			this.setVariant(variants[randomIndex]);
		}

		if (this.getBreed() == 8) { //percherons usually just come in blacks and greys
			if (random.nextDouble() < 0.10) {
				int[] variants = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15,
						16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.10) {
				int[] variants = {2, 4, 5, 9, 15, 22, 23, 24, 29};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 9) { //selle francais can come in any color naturally, aside from fjord coloring. they usually come in bays
			if (random.nextDouble() < 0.20) {
				int[] variants = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15,
						16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.20) {
				int[] variants = {0, 1, 2, 3, 6, 10, 12, 13, 17, 20, 21, 22, 26, 31};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 10) { //marwaris can come in any color naturally, aside from fjord coloring
			int[] variants = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15,
					16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
			int randomIndex = new Random().nextInt(variants.length);
			this.setVariant(variants[randomIndex]);
		}

		if (this.getBreed() == 11) { //mongolian ponies can come in any color naturally, aside from fjord coloring. they usually come in duns or bays
			if (random.nextDouble() < 0.20) {
				int[] variants = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15,
						16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.20) {
				int[] variants = {0, 1, 2, 3, 6, 7, 8, 9, 10, 11, 12, 13, 17, 25, 26, 27, 28, 29, 30};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 12) { //shires usually come in greys or browns
			if (random.nextDouble() < 0.10) {
				int[] variants = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15,
						16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.10) {
				int[] variants = {0, 2, 4, 6, 10, 12, 13, 15, 17, 21, 22, 23, 24};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 13) { //ahkal tekes usually come in creams or other light colors
			if (random.nextDouble() < 0.10) {
				int[] variants = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15,
						16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.10) {
				int[] variants = {7, 8, 11, 15, 16, 18, 19, 23, 24, 25, 26, 27, 19, 30};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 14) { //american soliders usually come in blacks or greys but can be other colors as well
			if (random.nextDouble() < 0.40) {
				int[] variants = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15,
						16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.40) {
				int[] variants = {2, 4, 5, 9, 15, 22, 23, 24, 29, 31};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

	}

	public void setMarkingByBreed() {

		if (this.getBreed() == 0) { //mustangs can come in any pattern naturally
			this.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
		}

		if (this.getBreed() == 1) { //ardennes can come in any pattern naturally, but often come with socks or a face marking (or no markings)
			if (random.nextDouble() < 0.20) {
				this.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
			} else if (random.nextDouble() > 0.20) {
				int[] variants = {0, 4, 6, 7, 11, 12, 13, 14, 18, 19, 21, 22, 23, 29, 30, 32, 33, 35, 39, 41, 42, 43};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 2) { //kaldrubers usually don't come with markings, or with very small ones
			if (random.nextDouble() < 0.10) {
				this.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
			} else if (random.nextDouble() > 0.10 && random.nextDouble() < 0.30) {
				int[] variants = {0, 4, 14, 19, 21, 22, 23, 29, 33, 35, 38, 41, 42};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.30) {
				this.setOverlayVariant(0);
			}
		}

		if (this.getBreed() == 3) { //fjords usually don't come with markings, or with very small ones
			if (random.nextDouble() < 0.05) {
				this.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
			} else if (random.nextDouble() > 0.05 && random.nextDouble() < 0.20) {
				int[] variants = {0, 4, 14, 19, 21, 22, 23, 29, 33, 35, 39, 42, 43};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			} else if (random.nextDouble() > 0.20) {
				this.setOverlayVariant(0);
			}
		}

		if (this.getBreed() == 4) { //thoroughbreds can come in any pattern naturally, but usually come in solids
			if (random.nextDouble() < 0.30) {
				this.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
			} else if (random.nextDouble() > 0.30) {
				this.setOverlayVariant(0);
			}
		}

		if (this.getBreed() == 5) { //friesians usually come in solids
			if (random.nextDouble() < 0.02) {
				this.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
			} else if (random.nextDouble() > 0.02) {
				this.setOverlayVariant(0);
			}
		}

		if (this.getBreed() == 6) { //irish cobs usually come with large markings
			if (random.nextDouble() < 0.02) {
				this.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
			} else if (random.nextDouble() > 0.02) {
				int[] variants = {1, 3, 6, 8, 9, 10, 12, 15, 16, 17, 20, 24, 25, 27, 28, 30, 31, 34, 36, 37, 40};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 7) { //american quarters can come in any pattern naturally
			this.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
		}

		if (this.getBreed() == 8) { //percherons usually come with small markings
			if (random.nextDouble() < 0.02) {
				this.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
			} else if (random.nextDouble() > 0.02) {
				int[] variants = {0, 2, 4, 5, 6, 7, 11, 12, 14, 18, 19, 21, 22, 23, 29, 30, 32, 33, 35, 39, 41, 42, 43};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 9) { //selle francias can come in any pattern naturally, but usually come in solids or with socks
			if (random.nextDouble() < 0.20) {
				this.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
			} else if (random.nextDouble() > 0.20) {
				int[] variants = {0, 4, 6, 7, 14, 19, 21, 22, 23, 29, 32, 33, 42, 43};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 10) { //marwaris usually come in solids
			if (random.nextDouble() < 0.20) {
				this.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
			} else if (random.nextDouble() > 0.20) {
				this.setOverlayVariant(0);
			}
		}

		if (this.getBreed() == 11) { //mongolian ponies can come in solids and, sometimes, any marking
			if (random.nextDouble() < 0.50) {
				this.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
			} else if (random.nextDouble() > 0.50) {
				this.setOverlayVariant(0);
			}
		}

		if (this.getBreed() == 12) { //shires usually come with small markings or socks
			if (random.nextDouble() < 0.07) {
				this.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
			} else if (random.nextDouble() > 0.07) {
				int[] variants = {0, 2, 4, 5, 6, 7, 11, 12, 14, 18, 19, 21, 22, 23, 29, 30, 32, 33, 35, 39, 41, 42, 43};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			}
		}

		if (this.getBreed() == 13) { //ahkal tekes usually come in solids
			if (random.nextDouble() < 0.05) {
				this.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
			} else if (random.nextDouble() > 0.05) {
				this.setOverlayVariant(0);
			}
		}

		if (this.getBreed() == 14) { //american soldiers usually come with small markings or socks
			if (random.nextDouble() < 0.30) {
				this.setOverlayVariant(random.nextInt(EquineMarkingOverlay.values().length));
			} else if (random.nextDouble() > 0.30) {
				int[] variants = {0, 2, 4, 5, 6, 7, 11, 12, 14, 18, 19, 21, 22, 23, 29, 30, 32, 33, 35, 39, 41, 42, 43};
				int randomIndex = new Random().nextInt(variants.length);
				this.setOverlayVariant(variants[randomIndex]);
			}
		}

	}

	//drops extra loot based on animal size
	@Override
	public void dropCustomDeathLoot(DamageSource p_33574_, int p_33575_, boolean p_33576_) {
		super.dropCustomDeathLoot(p_33574_, p_33575_, p_33576_);
		Random random = new Random();

		if (!LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get() || !ModList.get().isLoaded("tfc")) {
			if (this.isDraftBreed()) {
				if (random.nextDouble() < 0.40) {
					this.spawnAtLocation(LOItems.HORSE.get(), 2);
					this.spawnAtLocation(LOItems.HORSE_RIB_STEAK.get(), 2);
					this.spawnAtLocation(LOItems.HORSE_SIRLOIN_STEAK.get(), 2);
					this.spawnAtLocation(Items.LEATHER, 2);
				} else if (random.nextDouble() > 0.40) {
					this.spawnAtLocation(LOItems.HORSE.get());
					this.spawnAtLocation(LOItems.HORSE_RIB_STEAK.get());
					this.spawnAtLocation(LOItems.HORSE_SIRLOIN_STEAK.get());
					this.spawnAtLocation(Items.LEATHER);
				}
			}

			if (this.isWarmbloodedBreed()) {
				if (random.nextDouble() < 0.20) {
					this.spawnAtLocation(LOItems.HORSE.get());
					this.spawnAtLocation(LOItems.HORSE_RIB_STEAK.get());
					this.spawnAtLocation(LOItems.HORSE_SIRLOIN_STEAK.get());
					this.spawnAtLocation(Items.LEATHER);
				}
			}

			if (this.isStockBreed()) {
				if (random.nextDouble() < 0.10) {
					this.spawnAtLocation(LOItems.HORSE.get());
					this.spawnAtLocation(LOItems.HORSE_RIB_STEAK.get());
					this.spawnAtLocation(LOItems.HORSE_SIRLOIN_STEAK.get());
					this.spawnAtLocation(Items.LEATHER);
				}
			}

			if (ModList.get().isLoaded("create")) {
				ResourceLocation resourceLocation = new ResourceLocation("create", "superglue");
				Item createSuperglue = ForgeRegistries.ITEMS.getValue(resourceLocation);
				if (random.nextDouble() < 0.25) {
					this.spawnAtLocation(createSuperglue.getDefaultInstance());
				}
			}

		}
	}

}