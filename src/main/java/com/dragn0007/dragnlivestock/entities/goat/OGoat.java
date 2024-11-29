package com.dragn0007.dragnlivestock.entities.goat;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.gui.OxMenu;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
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
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class OGoat extends AbstractOMount implements GeoEntity {

	public static final EntityDimensions LONG_JUMPING_DIMENSIONS = EntityDimensions.scalable(0.9F, 1.3F).scale(0.7F);
	protected static final ImmutableList<SensorType<? extends Sensor<? super OGoat>>> SENSOR_TYPES = ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.NEAREST_PLAYERS, SensorType.NEAREST_ITEMS, SensorType.NEAREST_ADULT, SensorType.HURT_BY, SensorType.GOAT_TEMPTATIONS);
	protected static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(MemoryModuleType.LOOK_TARGET, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.PATH, MemoryModuleType.ATE_RECENTLY, MemoryModuleType.BREED_TARGET, MemoryModuleType.LONG_JUMP_COOLDOWN_TICKS, MemoryModuleType.LONG_JUMP_MID_JUMP, MemoryModuleType.TEMPTING_PLAYER, MemoryModuleType.NEAREST_VISIBLE_ADULT, MemoryModuleType.TEMPTATION_COOLDOWN_TICKS, MemoryModuleType.IS_TEMPTED, MemoryModuleType.RAM_COOLDOWN_TICKS, MemoryModuleType.RAM_TARGET, MemoryModuleType.IS_PANICKING);
	private static final EntityDataAccessor<Boolean> DATA_IS_SCREAMING_GOAT = SynchedEntityData.defineId(OGoat.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> DATA_HAS_LEFT_HORN = SynchedEntityData.defineId(OGoat.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> DATA_HAS_RIGHT_HORN = SynchedEntityData.defineId(OGoat.class, EntityDataSerializers.BOOLEAN);
	private boolean isLoweringHead;
	private int lowerHeadTick;

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0D, (double)this.getEyeHeight() * 1F, (double)(this.getBbWidth() * 1F));
		//              ^ Side offset                      ^ Height offset                   ^ Length offset
	}

	protected Brain.Provider<OGoat> brainProvider() {
		return Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
	}

	public OGoat(EntityType<? extends OGoat> type, Level level) {
		super(type, level);
	}

	private static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_goat");
	private static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/goat");
	@Override
	public @NotNull ResourceLocation getDefaultLootTable() {
		if (LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
			return VANILLA_LOOT_TABLE;
		}
		return LOOT_TABLE;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 10.0D)
				.add(Attributes.MOVEMENT_SPEED, (double)0.2F)
				.add(Attributes.ATTACK_DAMAGE, 2.0D);
	}

	public void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.4, true));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
	}

	protected int calculateFallDamage(float p_149389_, float p_149390_) {
		return super.calculateFallDamage(p_149389_, p_149390_) - 10;
	}

	public static final Ingredient FOOD_ITEMS = Ingredient.of(Items.MELON_SLICE, Items.APPLE, Items.BREAD, Items.CARROT, Items.POTATO, Items.SWEET_BERRIES);
	private static final Set<Item> TAME_FOOD = Sets.newHashSet(Items.WHEAT);

	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);

		if (itemstack.is(Items.BUCKET) && !this.isBaby() &&
				(!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() ||
						(LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() && this.isFemale()))) {

			player.playSound(SoundEvents.GOAT_MILK, 1.0F, 1.0F);
			ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, LOItems.GOAT_MILK_BUCKET.get().getDefaultInstance());
			player.setItemInHand(hand, itemstack1);

			return InteractionResult.sidedSuccess(this.level().isClientSide);
		}

		if (this.isTamed()) {
			if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
				this.heal((float) Objects.requireNonNull(itemstack.getFoodProperties(this)).getNutrition());
				if (!player.getAbilities().instabuild) {
					itemstack.shrink(1);
				}

				this.gameEvent(GameEvent.EAT, this);
				return InteractionResult.SUCCESS;
			}
		}

		if (TAME_FOOD.contains(itemstack.getItem())) {
			int i = this.getAge();
			if (!this.level().isClientSide && i == 0 && this.canFallInLove()) {
				this.usePlayerItem(player, hand, itemstack);
				this.setInLove(player);
				return InteractionResult.SUCCESS;
			}
		}

		if (!this.isTamed() && TAME_FOOD.contains(itemstack.getItem())) {
			if (!player.getAbilities().instabuild) {
				itemstack.shrink(1);
			}

			if (!this.level().isClientSide) {
				if (this.random.nextInt(5) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
					this.setTamed(true);
					this.level().broadcastEntityEvent(this, (byte)7);
				} else {
					this.level().broadcastEntityEvent(this, (byte)6);
				}
			}
			return InteractionResult.sidedSuccess(this.level().isClientSide);

		} else {
			return super.mobInteract(player, hand);
		}
	}

	@Override
	protected boolean canRide(Entity p_20339_) {
		return false;
	}

	@Override
	public float getStepHeight() {
		return 2F;
	}

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	private <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
		double currentSpeed = this.getDeltaMovement().lengthSqr();
		double speedThreshold = 0.01;

		AnimationController<T> controller = tAnimationState.getController();

		if (tAnimationState.isMoving()) {
			if (currentSpeed > speedThreshold) {
				controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
			} else {
				controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(1.8);
			}
		} else {
			controller.setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
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

	protected SoundEvent getAmbientSound() {
		return this.isScreamingGoat() ? SoundEvents.GOAT_SCREAMING_AMBIENT : SoundEvents.GOAT_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource p_149387_) {
		return this.isScreamingGoat() ? SoundEvents.GOAT_SCREAMING_HURT : SoundEvents.GOAT_HURT;
	}

	protected SoundEvent getDeathSound() {
		return this.isScreamingGoat() ? SoundEvents.GOAT_SCREAMING_DEATH : SoundEvents.GOAT_DEATH;
	}

	protected void playStepSound(BlockPos p_149382_, BlockState p_149383_) {
		this.playSound(SoundEvents.GOAT_STEP, 0.15F, 1.0F);
	}
	public boolean isFood(ItemStack p_28271_) {
		return FOOD_ITEMS.test(p_28271_);
	}

	public boolean isScreamingGoat() {
		return this.entityData.get(DATA_IS_SCREAMING_GOAT);
	}

	public void setScreamingGoat(boolean p_149406_) {
		this.entityData.set(DATA_IS_SCREAMING_GOAT, p_149406_);
	}

	public boolean hasLeftHorn() {
		return this.entityData.get(DATA_HAS_LEFT_HORN);
	}

	public boolean hasRightHorn() {
		return this.entityData.get(DATA_HAS_RIGHT_HORN);
	}

	public void handleEntityEvent(byte p_149356_) {
		if (p_149356_ == 58) {
			this.isLoweringHead = true;
		} else if (p_149356_ == 59) {
			this.isLoweringHead = false;
		} else {
			super.handleEntityEvent(p_149356_);
		}

	}

	public void aiStep() {
		if (this.isLoweringHead) {
			++this.lowerHeadTick;
		} else {
			this.lowerHeadTick -= 2;
		}

		this.lowerHeadTick = Mth.clamp(this.lowerHeadTick, 0, 20);
		super.aiStep();
	}

	protected void sendDebugPackets() {
		super.sendDebugPackets();
		DebugPackets.sendEntityBrain(this);
	}

	public EntityDimensions getDimensions(Pose pose) {
		return pose == Pose.LONG_JUMPING ? LONG_JUMPING_DIMENSIONS.scale(this.getScale()) : super.getDimensions(pose);
	}

	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OGoat.class, EntityDataSerializers.INT);

	public ResourceLocation getTextureLocation() {
		return OGoatModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
	}

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

		this.setScreamingGoat(tag.getBoolean("IsScreamingGoat"));
		this.entityData.set(DATA_HAS_LEFT_HORN, tag.getBoolean("HasLeftHorn"));
		this.entityData.set(DATA_HAS_RIGHT_HORN, tag.getBoolean("HasRightHorn"));

		if (tag.contains("Gender")) {
			setGender(tag.getInt("Gender"));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Variant", getVariant());
		tag.putBoolean("IsScreamingGoat", this.isScreamingGoat());
		tag.putBoolean("HasLeftHorn", this.hasLeftHorn());
		tag.putBoolean("HasRightHorn", this.hasRightHorn());

		tag.putInt("Gender", getGender());
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, 0);
		this.entityData.define(GENDER, 0);
		this.entityData.define(DATA_IS_SCREAMING_GOAT, false);
		this.entityData.define(DATA_HAS_LEFT_HORN, true);
		this.entityData.define(DATA_HAS_RIGHT_HORN, true);
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}
		Random random = new Random();
		this.setVariant(random.nextInt(OGoatModel.Variant.values().length));
		this.setGender(random.nextInt(Gender.values().length));

		RandomSource randomsource = serverLevelAccessor.getRandom();
		this.setScreamingGoat(randomsource.nextDouble() < 0.02D);
		this.ageBoundaryReached();

		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	@Override
	public void playEmote(String emoteName, String loopType) {
	}

	public boolean canParent() {
		return !this.isBaby() && this.getHealth() >= this.getMaxHealth() && this.isInLove();
	}

	public boolean canMate(Animal animal) {
		if (animal == this) {
			return false;
		} else if (!(animal instanceof OGoat)) {
			return false;
		} else {
			if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
				return this.canParent() && ((OGoat) animal).canParent();
			} else {
				OGoat partner = (OGoat) animal;
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
		OGoat goat = (OGoat) ageableMob;
		if (ageableMob instanceof OGoat) {
			OGoat oGoat = (OGoat) ageableMob;
			goat = EntityTypes.O_GOAT_ENTITY.get().create(serverLevel);

			int i = this.random.nextInt(9);
			int variant;
			if (i < 4) {
				variant = this.getVariant();
			} else if (i < 8) {
				variant = oGoat.getVariant();
			} else {
				variant = this.random.nextInt(OGoatModel.Variant.values().length);
			}

			int gender;
			gender = this.random.nextInt(Gender.values().length);

			goat.setVariant(variant);
			goat.setGender(gender);
		}

		return goat;
	}

	@Override
	public boolean isSaddleable() {
		return false;
	}

	@Override
	public boolean isArmor(ItemStack itemStack) {
		return itemStack.is(ItemTags.WOOL_CARPETS);
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
	public void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
		if (this.hasPassenger(entity)) {
			double offsetX;
			double offsetY;
			double offsetZ;

			offsetX = 0;
			offsetY = 0.7;
			offsetZ = 0;

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
	protected boolean canPerformRearing() {
		return false;
	}

	@Override
	public void equipSaddle(@Nullable SoundSource p_30546_) {
	}

	@Override
	protected int getInventorySize() {
		return this.hasChest() ? 26 : super.getInventorySize();
	}

	@Override
	public boolean isVehicle() {
		return false;
	}
}