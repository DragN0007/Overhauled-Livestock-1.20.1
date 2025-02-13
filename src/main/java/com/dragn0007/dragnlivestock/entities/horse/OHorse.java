package com.dragn0007.dragnlivestock.entities.horse;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.ai.GroundTieGoal;
import com.dragn0007.dragnlivestock.entities.ai.HorseFollowHerdLeaderGoal;
import com.dragn0007.dragnlivestock.entities.ai.MountLookAtPlayerGoal;
import com.dragn0007.dragnlivestock.entities.donkey.ODonkey;
import com.dragn0007.dragnlivestock.entities.mule.OMule;
import com.dragn0007.dragnlivestock.entities.mule.OMuleModel;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.entities.util.LOAnimations;
import com.dragn0007.dragnlivestock.event.LivestockOverhaulClientEvent;
import com.dragn0007.dragnlivestock.gui.OHorseMenu;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

public class OHorse extends AbstractOMount implements GeoEntity {

	private static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_horse");
	private static final ResourceLocation CREATE_LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_horse_create");
	private static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/horse");
	@Override
	public @NotNull ResourceLocation getDefaultLootTable() {
		if (LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
			return VANILLA_LOOT_TABLE;
		}
		if (!LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get() && ModList.get().isLoaded("create")) {
			return CREATE_LOOT_TABLE;
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
//		this.getAttribute(LOAttributes.ENDURANCE).setBaseValue(this.generateRandomEndurance());
	}

	@Override
	public void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new GroundTieGoal(this));

		this.goalSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.7D));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.4, true));
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(7, new MountLookAtPlayerGoal(this, Player.class, 0.0F));
		this.goalSelector.addGoal(3, new HorseFollowHerdLeaderGoal(this));
		this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D, AbstractOMount.class));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));

		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity -> {
			boolean isWolf = livingEntity instanceof Wolf;
			return isWolf;
		}));

		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, entity ->
				(entity.getType().is(LOTags.Entity_Types.O_WOLVES) && !this.isTamed()) ||
						(entity.getType().is(LOTags.Entity_Types.O_WOLVES) && (entity instanceof TamableAnimal && !((TamableAnimal) entity).isTame())) && this.isTamed()
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

	public double generateRandomEndurance() {
		return ((double) 0.45F + this.random.nextDouble() * 0.3D + this.random.nextDouble() * 0.3D + this.random.nextDouble() * 0.3D) * 0.25D;
	}

	public boolean isDraftBreed() {
		return this.getBreed() == 1 || this.getBreed() == 5 || this.getBreed() == 8 || this.getBreed() == 12;
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
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemStack = player.getItemInHand(hand);

		if (!this.isDraftBreed() && this.hasPassenger(player)) {
			player.displayClientMessage(Component.translatable("tooltip.dragnlivestock.two_seater.tooltip").withStyle(ChatFormatting.GOLD), true);
			return InteractionResult.PASS;
		}
		return super.mobInteract(player, hand);
	}

	@Override
	protected boolean canAddPassenger(Entity entity) {
		return this.getPassengers().size() < 2;
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
	protected int getInventorySize() {
		if (this.hasChest()) {
		    if (this.isDraftBreed()) {
				return 17;
			} else if (this.isPonyBreed()) {
				return 14;
			} else if (this.isStockBreed() || this.isWarmbloodedBreed()) {
				return 11;
			} else if (this.isRacingBreed()) {
				return 5;
			}
		}
		return super.getInventorySize();
	}

	@Override
	public void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
		if (this.hasPassenger(entity)) {

			LocalDate date = LocalDate.now();
			Month month = date.getMonth();
			int day = date.getDayOfMonth();

			double offsetX = 0;
			double offsetY = 1.0;
			double offsetZ = -0.2;

			int i = this.getPassengers().indexOf(entity);

			if (getBreed() == 0) {
				offsetY = 1.0;
			}

			if (getBreed() == 1) {
				switch (i) {
					case 0:
						offsetY = 1.1;
						break;
					case 1:
						offsetY = 1.1;
						offsetZ = -1.2;
						break;
				}
			}

			if (getBreed() == 2) {
				offsetY = 1.15;
			}

			if (getBreed() == 3) {
				offsetY = 0.85;
			}

			if (getBreed() == 4) {
				offsetY = 1.2;
			}

			if (getBreed() == 5) {
				switch (i) {
					case 0:
						offsetY = 1.25;
						break;
					case 1:
						offsetY = 1.25;
						offsetZ = -1.2;
						break;
				}
			}

			if (getBreed() == 6) {
				offsetY = 0.95;
			}

			if (getBreed() == 7) {
				offsetY = 1.0;
			}

			if (getBreed() == 9) {
				switch (i) {
					case 0:
						offsetY = 1.43;
						break;
					case 1:
						offsetY = 1.43;
						offsetZ = -1.2;
						break;
				}
			}

			if (getBreed() == 8) {
				offsetY = 1.1;
			}

			if (getBreed() == 10) {
				offsetY = 1.1;
			}

			if (getBreed() == 11) {
				offsetY = 0.85;
			}

			if (getBreed() == 12) {
				switch (i) {
					case 0:
						offsetY = 1.5;
						break;
					case 1:
						offsetY = 1.5;
						offsetZ = -1.2;
						break;
				}
			}

			if (getBreed() == 13) {
				offsetY = 1.1;
			}

			if (month == Month.DECEMBER && (day == 24 || day == 25)) {
				offsetY = 1.25;
			}


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

	public int alternateIdleTimer = 0;

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
				if (this.isVehicle() || !LivestockOverhaulCommonConfig.GROUND_TIE.get() && alternateIdleTimer < 500) {
					controller.setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
//				} else if (alternateIdleTimer >= 500) {
//					controller.setAnimation(RawAnimation.begin().then("idle5", Animation.LoopType.PLAY_ONCE));
//					alternateIdleTimer = 0;
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
		stream.limit(this.getMaxHerdSize() - this.herdSize).filter((horse) -> {
			return horse != this;
		}).forEach((horse) -> {
			horse.startFollowing(this);
		});
	}

	@Override
	public void tick() {
		super.tick();

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

		alternateIdleTimer++;

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


	public static final EntityDataAccessor<Integer> REINDEER_VARIANT = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<ResourceLocation> VARIANT_TEXTURE = SynchedEntityData.defineId(OHorse.class, LivestockOverhaul.RESOURCE_LOCATION);
	public static final EntityDataAccessor<ResourceLocation> OVERLAY_TEXTURE = SynchedEntityData.defineId(OHorse.class, LivestockOverhaul.RESOURCE_LOCATION);
	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> BREED = SynchedEntityData.defineId(OHorse.class, EntityDataSerializers.INT);


	public ResourceLocation getReindeerTextureResource() {
		return OHorseModel.ReindeerVariant.reindeerVariantFromOrdinal(getReindeerVariant()).resourceLocation;
	}

	public ResourceLocation getTextureResource() {
		return this.entityData.get(VARIANT_TEXTURE);
	}

	public ResourceLocation getOverlayLocation() {
		return this.entityData.get(OVERLAY_TEXTURE);
	}

	public ResourceLocation getModelResource() {
		return HorseBreedModel.breedFromOrdinal(getBreed()).resourceLocation;
	}


	public int getReindeerVariant() {
		return this.entityData.get(REINDEER_VARIANT);
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

	public void setReindeerVariant(int reindeerVariant) {
		this.entityData.set(REINDEER_VARIANT, reindeerVariant);
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

		if (tag.contains("Reindeer_Variant")) {
			this.setReindeerVariant(tag.getInt("Reindeer_Variant"));
		}

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

		if (tag.contains("Mane")) {
			this.setManeType(tag.getInt("Mane"));
		}

		if (tag.contains("Tail")) {
			this.setTailType(tag.getInt("Tail"));
		}

		this.createInventory();
		if (this.hasChest()) {
			ListTag listtag = tag.getList("Items", 10);

			for(int i = 0; i < listtag.size(); ++i) {
				CompoundTag compoundtag = listtag.getCompound(i);
				int j = compoundtag.getByte("Slot") & 255;
				if (j >= 2 && j < this.inventory.getContainerSize()) {
					this.inventory.setItem(j, ItemStack.of(compoundtag));
				}
			}
		}

		this.updateContainerEquipment();
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Reindeer_Variant", this.getReindeerVariant());
		tag.putInt("Variant", this.getVariant());
		tag.putInt("Overlay", this.getOverlayVariant());
		tag.putString("Variant_Texture", this.getTextureResource().toString());
		tag.putString("Overlay_Texture", this.getOverlayLocation().toString());
		tag.putInt("Breed", this.getBreed());
		tag.putInt("Gender", this.getGender());
		tag.putInt("Mane", this.getManeType());
		tag.putInt("Tail", this.getTailType());

		if (this.hasChest()) {
			ListTag listtag = new ListTag();

			for(int i = 2; i < this.inventory.getContainerSize(); ++i) {
				ItemStack itemstack = this.inventory.getItem(i);
				if (!itemstack.isEmpty()) {
					CompoundTag compoundtag = new CompoundTag();
					compoundtag.putByte("Slot", (byte)i);
					itemstack.save(compoundtag);
					listtag.add(compoundtag);
				}
			}

			tag.put("Items", listtag);
		}
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
		this.setReindeerVariant(random.nextInt(OHorseModel.ReindeerVariant.values().length));

		if (spawnType == MobSpawnType.SPAWN_EGG || LivestockOverhaulCommonConfig.NATURAL_HORSE_BREEDS.get()) {
			this.setBreed(random.nextInt(HorseBreedModel.values().length));
		}

		//tfc compat
		if (ModList.get().isLoaded("tfc")) {
			this.setTamed(true);
		}

		this.randomizeOHorseAttributes();
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
		this.entityData.define(REINDEER_VARIANT, 0);
		this.entityData.define(MANE_TYPE, 0);
		this.entityData.define(TAIL_TYPE, 0);
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
				if (LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get() && this.canParent() && partner.canParent() && ((this.isFemale() && partnerIsMale) || (this.isMale() && partnerIsFemale))) {
					return isFemale();
				}
			}
		}
		return false;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		AbstractOMount foal;
		if (ageableMob instanceof ODonkey donkey) {

			foal = EntityTypes.O_MULE_ENTITY.get().create(serverLevel);

			int overlayChance = this.random.nextInt(9);
			int selectedOverlay;

			if (overlayChance < 4) {
				selectedOverlay = this.getOverlayVariant();
			} else if (overlayChance < 8) {
				selectedOverlay = donkey.getOverlayVariant();
			} else {
				selectedOverlay = this.random.nextInt(OHorseMarkingLayer.Overlay.values().length);
			}

			((OMule) foal).setOverlayVariant(selectedOverlay);
			((OMule) foal).setVariant(random.nextInt(OMuleModel.Variant.values().length));

			//horse parent is stock, warmblooded or racer
			if (this.isStockBreed() || this.isWarmbloodedBreed() || this.isRacingBreed()) {
				((OMule) foal).setBreed(0);
			}

			//horse parent is pony
			if (this.isPonyBreed()) {
				((OMule) foal).setBreed(1);
			}

			//horse parent is draft/ coldblooded
			if (this.isDraftBreed()) {
				((OMule) foal).setBreed(2);
			}

		} else {
			OHorse horse = (OHorse) ageableMob;
			foal = EntityTypes.O_HORSE_ENTITY.get().create(serverLevel);

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
				breed = this.random.nextInt(HorseBreedModel.values().length);
			} else {
				breed = (this.random.nextInt(2) == 0) ? this.getBreed() : horse.getBreed();
			}

			int gender;
			gender = this.random.nextInt(OHorse.Gender.values().length);

			((OHorse) foal).setVariant(variant);
			((OHorse) foal).setOverlayVariant(overlay);
			((OHorse) foal).setBreed(breed);
			foal.setGender(gender);

			if (this.random.nextInt(4) == 0) {
				int randomJumpStrength = (int) Math.max(horse.generateRandomOHorseJumpStrength(), this.random.nextInt(10) + 10);
				((OHorse) foal).generateRandomOHorseJumpStrength();

				int betterSpeed = (int) Math.max(horse.getSpeed(), this.random.nextInt(10) + 20);
				foal.setSpeed(betterSpeed);

				int betterHealth = (int) Math.max(horse.getHealth(), this.random.nextInt(20) + 40);
				foal.setHealth(betterHealth);

			//generate random stats of the breed standard of the baby if the breed is random
			} else if (k == 0) {
				((OHorse) foal).randomizeOHorseAttributes();
			}
		}

		this.setOffspringAttributes(ageableMob, foal);
		return foal;
	}

	public enum Mane {
		ROACHED,
		BUTTONS,
		SHORT,
		LONG,
		NONE;

		public Mane next() {
			return Mane.values()[(this.ordinal() + 1) % Mane.values().length];
		}
	}

	public boolean hasDefaultMane() {
		return this.getManeType() == 0;
	}

	public boolean hasButtonMane() {
		return this.getManeType() == 1;
	}

	public boolean hasShortMane() {
		return this.getManeType() == 2;
	}

	public boolean hasLongMane() {
		return this.getManeType() == 3;
	}

	public boolean hasNoMane() {
		return this.getManeType() == 4;
	}

	public enum Tail {
		DEFAULT,
		SHORT,
		LONG,
		TUCKED,
		TIED;

		public Tail next() {
			return Tail.values()[(this.ordinal() + 1) % Tail.values().length];
		}
	}

	public boolean hasDefaultTail() {
		return this.getTailType() == 0;
	}

	public boolean hasLongTail() {
		return this.getTailType() == 1;
	}

	public boolean hasShortTail() {
		return this.getTailType() == 2;
	}

	public boolean hasTuckedTail() {
		return this.getTailType() == 3;
	}

	public boolean hasTiedTail() {
		return this.getTailType() == 4;
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


}