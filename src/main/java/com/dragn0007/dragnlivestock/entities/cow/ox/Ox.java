package com.dragn0007.dragnlivestock.entities.cow.ox;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.entities.util.LOAnimations;
import com.dragn0007.dragnlivestock.gui.OxMenu;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.Random;

public class Ox extends AbstractOMount implements GeoEntity {

	public Ox(EntityType<? extends Ox> type, Level level) {
		super(type, level);
	}

	private static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/ox");
	private static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/cow");
	@Override
	public @NotNull ResourceLocation getDefaultLootTable() {
		if (LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
			return VANILLA_LOOT_TABLE;
		}
		return LOOT_TABLE;
	}

	public static final AttributeModifier WALK_SPEED_MOD = new AttributeModifier(WALK_SPEED_MOD_UUID, "Walk speed mod", -0.8D, AttributeModifier.Operation.MULTIPLY_TOTAL);

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0D, (double)this.getEyeHeight() * 1.0F, (double)(this.getBbWidth() * 1F));
		//              ^ Side offset                      ^ Height offset                   ^ Length offset
	}

	public static AttributeSupplier.Builder createBaseHorseAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 24.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.17F)
				.add(Attributes.ATTACK_DAMAGE, 2D);
	}

	@Override
	public void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.7D));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.4, true));
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 0.0F));
		this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Wolf.class, false));
	}

	@Override
	protected int getInventorySize() {
		return this.hasChest() ? 26 : super.getInventorySize();
	}

	@Override
	public void openInventory(Player player) {
		if(player instanceof ServerPlayer serverPlayer && this.isTamed()) {
			NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider((containerId, inventory, p) -> {
				return new OxMenu(containerId, inventory, this.inventory, this);
			}, this.getDisplayName()), (data) -> {
				data.writeInt(this.getInventorySize());
				data.writeInt(this.getId());
			});
		}
	}

	@Override
	public boolean canWearArmor() {
		return false;
	}

	@Override
	protected boolean canPerformRearing() {
		return false;
	}

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	private <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
		double currentSpeed = this.getDeltaMovement().lengthSqr();
		double speedThreshold = 0.03;

		double x = this.getX() - this.xo;
		double z = this.getZ() - this.zo;

		boolean isMoving = (x * x + z * z) > 0.0001;

		AnimationController<T> controller = tAnimationState.getController();
		if (isMoving) {
			if (currentSpeed > speedThreshold || (this.isVehicle() && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(WALK_SPEED_MOD) && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD))) {
				controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(0.9);

			} else if (currentSpeed < speedThreshold || (this.isVehicle() && this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(WALK_SPEED_MOD) && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD))) {
				controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(0.9);
			}
		} else {
				controller.setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
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
	public boolean canJump() {
		return false;
	}

	public SoundEvent getAmbientSound() {
		super.getAmbientSound();
		return SoundEvents.COW_AMBIENT;
	}

	public SoundEvent getDeathSound() {
		super.getDeathSound();
		return SoundEvents.COW_DEATH;
	}

	public SoundEvent getHurtSound(DamageSource p_30720_) {
		super.getHurtSound(p_30720_);
		return SoundEvents.COW_HURT;
	}

	public void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
		this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
	}

	@Override
	public void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
		if (this.hasPassenger(entity)) {
			double offsetX = 0;
			double offsetY = 1.0;
			double offsetZ = -0.2;

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

	// Generates the base texture
	public ResourceLocation getTextureLocation() {
		return OxModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
	}

	public ResourceLocation getHornsLocation() {
		return OxHornLayer.HornOverlay.hornOverlayFromOrdinal(getHornVariant()).resourceLocation;
	}

	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Ox.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> HORNS = SynchedEntityData.defineId(Ox.class, EntityDataSerializers.INT);

	public int getVariant() {
		return this.entityData.get(VARIANT);
	}
	public int getHornVariant() {
		return this.entityData.get(HORNS);
	}

	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
	}
	public void setHornVariant(int hornVariant) {
		this.entityData.set(HORNS, hornVariant);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);

		if (tag.contains("Variant")) {
			setVariant(tag.getInt("Variant"));
		}

		if (tag.contains("Horns")) {
			setHornVariant(tag.getInt("Horns"));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Variant", getVariant());

		tag.putInt("Horns", getHornVariant());
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}
		Random random = new Random();
		setVariant(random.nextInt(OxModel.Variant.values().length));
		setHornVariant(random.nextInt(OxHornLayer.HornOverlay.values().length));
		setGender(1);

		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, 0);
		this.entityData.define(HORNS, 0);
		this.entityData.define(GENDER, 1);
	}

	@Override
	public void playEmote(String emoteName, String loopType) {
	}

	public boolean canParent() {
		return !this.isBaby() && this.isInLove();
	}

	public boolean canMate(Animal animal) {
		return false;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		return null;
	}
}
