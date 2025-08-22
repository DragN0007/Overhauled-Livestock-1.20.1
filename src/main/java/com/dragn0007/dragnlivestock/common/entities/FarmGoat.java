package com.dragn0007.dragnlivestock.common.entities;

import com.dragn0007.dragnlivestock.common.LOTags;
import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.farm_goat.FarmGoatMarkingLayer;
import com.dragn0007.dragnlivestock.blocks.client.render.model.entity.FarmGoatModel;
import com.dragn0007.dragnlivestock.common.entities.ai.FarmGoatFollowCaravanGoal;
import com.dragn0007.dragnlivestock.common.entities.ai.OAvoidEntityGoal;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.sheep.OSheepMarkingLayer;
import com.dragn0007.dragnlivestock.common.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.common.entities.util.Taggable;
import com.dragn0007.dragnlivestock.common.menus.OxMenu;
import com.dragn0007.dragnlivestock.common.items.LOItems;
import com.dragn0007.dragnlivestock.common.items.custom.BrandTagItem;
import com.dragn0007.dragnlivestock.common.LivestockOverhaulCommonConfig;
import com.google.common.collect.ImmutableList;
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
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;
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

public class FarmGoat extends AbstractOMount implements GeoEntity, Taggable {

	public static final EntityDimensions LONG_JUMPING_DIMENSIONS = EntityDimensions.scalable(0.9F, 1.3F).scale(0.7F);
	public static final ImmutableList<SensorType<? extends Sensor<? super FarmGoat>>> SENSOR_TYPES = ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.NEAREST_PLAYERS, SensorType.NEAREST_ITEMS, SensorType.NEAREST_ADULT, SensorType.HURT_BY, SensorType.GOAT_TEMPTATIONS);
	public static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(MemoryModuleType.LOOK_TARGET, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.PATH, MemoryModuleType.ATE_RECENTLY, MemoryModuleType.BREED_TARGET, MemoryModuleType.LONG_JUMP_COOLDOWN_TICKS, MemoryModuleType.LONG_JUMP_MID_JUMP, MemoryModuleType.TEMPTING_PLAYER, MemoryModuleType.NEAREST_VISIBLE_ADULT, MemoryModuleType.TEMPTATION_COOLDOWN_TICKS, MemoryModuleType.IS_TEMPTED, MemoryModuleType.RAM_COOLDOWN_TICKS, MemoryModuleType.RAM_TARGET, MemoryModuleType.IS_PANICKING);
	private static final EntityDataAccessor<Boolean> DATA_IS_SCREAMING_GOAT = SynchedEntityData.defineId(FarmGoat.class, EntityDataSerializers.BOOLEAN);
	private boolean isLoweringHead;
	private int lowerHeadTick;

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0D, (double)this.getEyeHeight() * 1F, (double)(this.getBbWidth() * 1F));
		//              ^ Side offset                      ^ Height offset                   ^ Length offset
	}

	public Brain.Provider<FarmGoat> brainProvider() {
		return Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
	}

	public FarmGoat(EntityType<? extends FarmGoat> type, Level level) {
		super(type, level);
	}

	private static final ResourceLocation LOOT_TABLE = LivestockOverhaul.id("entities/o_goat");
	private static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/goat");
	private static final ResourceLocation TFC_LOOT_TABLE = new ResourceLocation("tfc", "entities/goat");
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
		this.goalSelector.addGoal(2, new FarmGoatFollowCaravanGoal(this, (double)1.6F));

		this.goalSelector.addGoal(1, new OAvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity ->
				livingEntity.getType().is(LOTags.EntityTypes.HERDING_DOGS) && (livingEntity instanceof TamableAnimal && ((TamableAnimal) livingEntity).isTame() && !this.isLeashed())
		));

		this.goalSelector.addGoal(1, new OAvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity ->
				livingEntity.getType().is(LOTags.EntityTypes.WOLVES) && (livingEntity instanceof TamableAnimal && !((TamableAnimal) livingEntity).isTame() && !this.isLeashed())
		));
	}

	public int calculateFallDamage(float p_149389_, float p_149390_) {
		return super.calculateFallDamage(p_149389_, p_149390_) - 10;
	}

	public static final Ingredient FOOD_ITEMS = Ingredient.of(LOTags.Items.O_GOAT_EATS);

	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		Item item = itemstack.getItem();

		if (itemstack.is(LOItems.BREED_OSCILLATOR.get()) && player.getAbilities().instabuild) {
			return InteractionResult.PASS;
		}

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

		if (itemstack.is(Items.SHEARS)) {
			if (player.isShiftKeyDown()) {
				if (this.isTagged()) {
					this.setTagged(false);
					this.playSound(SoundEvents.SHEEP_SHEAR, 0.5f, 1f);
					return InteractionResult.sidedSuccess(this.level().isClientSide);
				}
				if (this.hasChest()) {
					this.dropEquipment();
					this.inventory.removeAllItems();

					this.setChest(false);
					this.playChestEquipsSound();

					return InteractionResult.sidedSuccess(this.level().isClientSide);
				}
			} else if (itemstack.is(Items.SHEARS) && !player.isShiftKeyDown() && !this.isBaby() &&
					(!isSheared() || regrowWoolCounter >= LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get())) {
				player.playSound(SoundEvents.SHEEP_SHEAR, 1.0F, 1.0F);
				this.setSheared(true);
				this.dropWoolByColorAndMarking();
				regrowWoolCounter = 0;
				return InteractionResult.sidedSuccess(this.level().isClientSide);
			}
		}

		if (itemstack.is(Items.BUCKET) && !this.isBaby()) {
			if (!wasMilked() || replenishMilkCounter >= LivestockOverhaulCommonConfig.MILKING_COOLDOWN.get()) {
				if ((!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get()) || (LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() && this.isFemale())) {
					player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
					ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, LOItems.GOAT_MILK_BUCKET.get().getDefaultInstance());
					player.setItemInHand(hand, itemstack1);
					replenishMilkCounter = 0;
					setMilked(true);
				}
				return InteractionResult.sidedSuccess(this.level().isClientSide);
			}
		}

		if (this.isTamed()) {
			if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
				this.heal(2F);
				if (!player.getAbilities().instabuild) {
					itemstack.shrink(1);
				}

				this.gameEvent(GameEvent.EAT, this);
				return InteractionResult.SUCCESS;
			}
		}

		if (this.isFood(itemstack)) {
			int i = this.getAge();
			if (!this.level().isClientSide && i == 0 && this.canFallInLove()) {
				this.usePlayerItem(player, hand, itemstack);
				this.setInLove(player);
				return InteractionResult.SUCCESS;
			}
		}

		if (!this.isTamed() && this.isFood(itemstack)) {
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

	public int replenishMilkCounter = 0;
	public int regrowWoolCounter = 0;

	public void tick() {
		super.tick();

		regrowWoolCounter++;

		if (regrowWoolCounter >= LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get()) {
			this.setSheared(false);
		}

		replenishMilkCounter++;

		if (replenishMilkCounter >= LivestockOverhaulCommonConfig.MILKING_COOLDOWN.get()) {
			this.setMilked(false);
		}
	}

	@Override
	public boolean canRide(Entity p_20339_) {
		return false;
	}

	@Override
	public float getStepHeight() {
		return 2F;
	}

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	private <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
		double x = this.getX() - this.xo;
		double z = this.getZ() - this.zo;
		double currentSpeed = this.getDeltaMovement().lengthSqr();
		double speedThreshold = 0.015;

		boolean isMoving = (x * x + z * z) > 0.0001;

		AnimationController<T> controller = tAnimationState.getController();

		if (isMoving) {
			if (currentSpeed > speedThreshold) {
				controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
			} else {
				controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(1.6);
			}
		} else {
			controller.setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
			controller.setAnimationSpeed(1.0);
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

	@Nullable
	public FarmGoat caravanHead;
	@Nullable
	public FarmGoat caravanTail;

	public void leaveCaravan() {
		if (this.caravanHead != null) {
			this.caravanHead.caravanTail = null;
		}

		this.caravanHead = null;
	}

	public void joinCaravan(FarmGoat p_30767_) {
		this.caravanHead = p_30767_;
		this.caravanHead.caravanTail = this;
	}

	public boolean hasCaravanTail() {
		return this.caravanTail != null;
	}

	public boolean inCaravan() {
		return this.caravanHead != null;
	}

	@Nullable
	public FarmGoat getCaravanHead() {
		return this.caravanHead;
	}

	public double followLeashSpeed() {
		return 1.6D;
	}

	public SoundEvent getAmbientSound() {
		return this.isScreamingGoat() ? SoundEvents.GOAT_SCREAMING_AMBIENT : SoundEvents.GOAT_AMBIENT;
	}

	public SoundEvent getHurtSound(DamageSource p_149387_) {
		return this.isScreamingGoat() ? SoundEvents.GOAT_SCREAMING_HURT : SoundEvents.GOAT_HURT;
	}

	public SoundEvent getDeathSound() {
		return this.isScreamingGoat() ? SoundEvents.GOAT_SCREAMING_DEATH : SoundEvents.GOAT_DEATH;
	}

	public void playStepSound(BlockPos p_149382_, BlockState p_149383_) {
		this.playSound(SoundEvents.GOAT_STEP, 0.15F, 1.0F);
	}
	public boolean isFood(ItemStack stack) {
		return FOOD_ITEMS.test(stack);
	}

	public boolean isScreamingGoat() {
		return this.entityData.get(DATA_IS_SCREAMING_GOAT);
	}

	public void setScreamingGoat(boolean p_149406_) {
		this.entityData.set(DATA_IS_SCREAMING_GOAT, p_149406_);
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

	public void sendDebugPackets() {
		super.sendDebugPackets();
		DebugPackets.sendEntityBrain(this);
	}

	public EntityDimensions getDimensions(Pose pose) {
		return pose == Pose.LONG_JUMPING ? LONG_JUMPING_DIMENSIONS.scale(this.getScale()) : super.getDimensions(pose);
	}

	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(FarmGoat.class, EntityDataSerializers.INT);
	public ResourceLocation getTextureLocation() {
		return FarmGoatModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
	}
	public int getVariant() {
		return this.entityData.get(VARIANT);
	}
	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
	}


	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(FarmGoat.class, EntityDataSerializers.INT);
	public ResourceLocation getOverlayLocation() {return FarmGoatMarkingLayer.Overlay.overlayFromOrdinal(getOverlayVariant()).resourceLocation;}
	public int getOverlayVariant() {
		return this.entityData.get(OVERLAY);
	}
	public void setOverlayVariant(int overlayVariant) {
		this.entityData.set(OVERLAY, overlayVariant);
	}

	private static final EntityDataAccessor<Integer> BRAND_TAG_COLOR = SynchedEntityData.defineId(FarmGoat.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> TAGGED = SynchedEntityData.defineId(FarmGoat.class, EntityDataSerializers.BOOLEAN);
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
	public boolean isTagged() {
		return this.entityData.get(TAGGED);
	}
	public void setTagged(boolean tagged) {
		this.entityData.set(TAGGED, tagged);
	}
	@Override
	public void equipTag(@Nullable SoundSource soundSource) {
		if(soundSource != null) {
			this.level().playSound(null, this, SoundEvents.BOOK_PAGE_TURN, soundSource, 0.5f, 1f);
		}
	}

	public static final EntityDataAccessor<Boolean> SHEARED = SynchedEntityData.defineId(FarmGoat.class, EntityDataSerializers.BOOLEAN);
	public boolean isSheared() {
		return this.entityData.get(SHEARED);
	}
	public void setSheared(boolean sheared) {
		this.entityData.set(SHEARED, sheared);
	}

	public static final EntityDataAccessor<Boolean> MILKED = SynchedEntityData.defineId(FarmGoat.class, EntityDataSerializers.BOOLEAN);
	public boolean wasMilked() {
		return this.entityData.get(MILKED);
	}
	public void setMilked(boolean milked) {
		this.entityData.set(MILKED, milked);
	}

	@Override
	public void updateContainerEquipment() {
		if(!this.level().isClientSide) {
			this.setDecorItem(this.inventory.getItem(1));
		}
	}

	@Override
	public int decorSlot() {
		return 1;
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		if (tag.contains("Variant")) {
			setVariant(tag.getInt("Variant"));
		}

		if (tag.contains("Overlay")) {
			this.setOverlayVariant(tag.getInt("Overlay"));
		}

		if (tag.contains("Gender")) {
			setGender(tag.getInt("Gender"));
		}

		if (tag.contains("Milked")) {
			this.setMilked(tag.getBoolean("Milked"));
		}

		if (tag.contains("MilkedTime")) {
			this.replenishMilkCounter = tag.getInt("MilkedTime");
		}

		if (tag.contains("Sheared")) {
			this.setSheared(tag.getBoolean("Sheared"));
		}

		if (tag.contains("ShearedTime")) {
			this.regrowWoolCounter = tag.getInt("ShearedTime");
		}

		if(tag.contains("Tagged")) {
			this.setTagged(tag.getBoolean("Tagged"));
		}

		this.setBrandTagColor(DyeColor.byId(tag.getInt("BrandTagColor")));

		this.setScreamingGoat(tag.getBoolean("IsScreamingGoat"));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Variant", getVariant());
		tag.putInt("Overlay", this.getOverlayVariant());
		tag.putInt("Gender", getGender());
		tag.putBoolean("Sheared", this.isSheared());
		tag.putInt("ShearedTime", this.regrowWoolCounter);
		tag.putBoolean("Milked", this.wasMilked());
		tag.putInt("MilkedTime", this.replenishMilkCounter);
		tag.putBoolean("Tagged", this.isTagged());
		tag.putByte("BrandTagColor", (byte)this.getBrandTagColor().getId());
		tag.putBoolean("IsScreamingGoat", this.isScreamingGoat());
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
		this.entityData.define(GENDER, 0);
		this.entityData.define(BRAND_TAG_COLOR, DyeColor.YELLOW.getId());
		this.entityData.define(TAGGED, false);
		this.entityData.define(SHEARED, false);
		this.entityData.define(MILKED, false);
		this.entityData.define(DATA_IS_SCREAMING_GOAT, false);
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
			this.setColor();
			this.setMarking();
		} else {
			this.setVariant(random.nextInt(FarmGoatModel.Variant.values().length));
			this.setOverlayVariant(random.nextInt(FarmGoatMarkingLayer.Overlay.values().length));
		}

		RandomSource randomsource = serverLevelAccessor.getRandom();
		this.setScreamingGoat(randomsource.nextDouble() < 0.02D);
		this.ageBoundaryReached();

		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	@Override
	public void playEmote(String emoteName, String loopType) {
	}

	public boolean canParent() {
		return !this.isBaby() && this.isInLove();
	}

	public boolean canMate(Animal animal) {
		if (animal == this) {
			return false;
		} else if (!(animal instanceof FarmGoat)) {
			return false;
		} else {
			if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
				return this.canParent() && ((FarmGoat) animal).canParent();
			} else {
				FarmGoat partner = (FarmGoat) animal;
				if (this.canParent() && partner.canParent() && this.getGender() != partner.getGender()) {
					return isFemale();
				}
			}
		}
		return false;
	}


	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		FarmGoat kid;
		FarmGoat partner = (FarmGoat) ageableMob;
		kid = com.dragn0007.dragnlivestock.common.entities.EntityTypes.FARM_GOAT_ENTITY.get().create(serverLevel);

		int variantChance = this.random.nextInt(14);
		int variant;
		if (variantChance < 6) {
			variant = this.getVariant();
		} else if (variantChance < 12) {
			variant = partner.getVariant();
		} else {
			variant = this.random.nextInt(FarmGoatModel.Variant.values().length);
		}
		kid.setVariant(variant);

		int overlayChance = this.random.nextInt(10);
		int overlay;
		if (overlayChance < 4) {
			overlay = this.getOverlayVariant();
		} else if (overlayChance < 8) {
			overlay = partner.getOverlayVariant();
		} else {
			overlay = this.random.nextInt(OSheepMarkingLayer.Overlay.values().length);
		}
		kid.setOverlayVariant(overlay);

		kid.setGender(random.nextInt(Gender.values().length));

		return kid;
	}

	public void setColor() {

		if (random.nextDouble() < 0.10) {
			this.setVariant(random.nextInt(FarmGoatModel.Variant.values().length));
		} else if (random.nextDouble() > 0.10 && random.nextDouble() < 0.30) {
			int[] variants = {0, 5, 6, 10};
			int randomIndex = new Random().nextInt(variants.length);
			this.setVariant(variants[randomIndex]);
		} else if (random.nextDouble() > 0.30) {
			this.setVariant(10);
		}

	}

	public void setMarking() {

		if (random.nextDouble() < 0.10) {
			this.setOverlayVariant(random.nextInt(FarmGoatMarkingLayer.Overlay.values().length));
		} else if (random.nextDouble() > 0.10) {
			this.setOverlayVariant(0);
		}

	}

	@Override
	public boolean isSaddleable() {
		return false;
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
	public void positionRider(Entity entity, MoveFunction moveFunction) {
		if (this.hasPassenger(entity)) {
			double offsetX;
			double offsetY;
			double offsetZ;

			offsetX = 0;
			offsetY = 0.6;
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
	public boolean canPerformRearing() {
		return false;
	}

	@Override
	public int getInventorySize() {
		return 26;
	}

	@Override
	public void dropCustomDeathLoot(DamageSource p_33574_, int p_33575_, boolean p_33576_) {
		super.dropCustomDeathLoot(p_33574_, p_33575_, p_33576_);
		Random random = new Random();

		if (!this.isSheared()) {
			this.dropWoolByColorAndMarking();
		}

		if (!LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get() || !ModList.get().isLoaded("tfc")) {

		}

	}

	public void dropWoolByColorAndMarking() {

		if (!LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
			if (this.getVariant() == 0 && !(this.getOverlayVariant() == 3)) {
				if (random.nextDouble() < 0.20) {
					this.spawnAtLocation(LOItems.BLACK_WOOL_STAPLE.get(), 3);
				} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
					this.spawnAtLocation(LOItems.BLACK_WOOL_STAPLE.get(), 2);
				} else if (random.nextDouble() > 0.50) {
					this.spawnAtLocation(LOItems.BLACK_WOOL_STAPLE.get(), 1);
				}

				if (this.getOverlayVariant() == 0) {
					this.spawnAtLocation(LOItems.BLACK_WOOL_STAPLE.get(), 1);
				}
			}

			if ((this.getVariant() == 2 || this.getVariant() == 3 || this.getVariant() == 6 || this.getVariant() == 7 || this.getVariant() == 8)
					&& !(this.getOverlayVariant() == 3)) {
				if (random.nextDouble() < 0.20) {
					this.spawnAtLocation(LOItems.BROWN_WOOL_STAPLE.get(), 3);
				} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
					this.spawnAtLocation(LOItems.BROWN_WOOL_STAPLE.get(), 2);
				} else if (random.nextDouble() > 0.50) {
					this.spawnAtLocation(LOItems.BROWN_WOOL_STAPLE.get(), 1);
				}

				if (this.getOverlayVariant() == 0) {
					this.spawnAtLocation(LOItems.BROWN_WOOL_STAPLE.get(), 1);
				}
			}

			if ((this.getVariant() == 1 || this.getVariant() == 5) && !(this.getOverlayVariant() == 3)) {
				if (random.nextDouble() < 0.20) {
					this.spawnAtLocation(LOItems.GREY_WOOL_STAPLE.get(), 3);
				} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
					this.spawnAtLocation(LOItems.GREY_WOOL_STAPLE.get(), 2);
				} else if (random.nextDouble() > 0.50) {
					this.spawnAtLocation(LOItems.GREY_WOOL_STAPLE.get(), 1);
				}

				if (this.getOverlayVariant() == 0) {
					this.spawnAtLocation(LOItems.GREY_WOOL_STAPLE.get(), 1);
				}
			}

			if (this.getVariant() == 6 && !(this.getOverlayVariant() == 3)) {
				if (random.nextDouble() < 0.20) {
					this.spawnAtLocation(LOItems.LIGHT_GREY_WOOL_STAPLE.get(), 3);
				} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
					this.spawnAtLocation(LOItems.LIGHT_GREY_WOOL_STAPLE.get(), 2);
				} else if (random.nextDouble() > 0.50) {
					this.spawnAtLocation(LOItems.LIGHT_GREY_WOOL_STAPLE.get(), 1);
				}

				if (this.getOverlayVariant() == 0) {
					this.spawnAtLocation(LOItems.LIGHT_GREY_WOOL_STAPLE.get(), 1);
				}
			}

			if ((this.getVariant() == 4 || this.getVariant() == 9 || this.getVariant() == 10) && !(this.getOverlayVariant() == 3)) {
				if (random.nextDouble() < 0.20) {
					this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 3);
				} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
					this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 2);
				} else if (random.nextDouble() > 0.50) {
					this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 1);
				}

				if (this.getOverlayVariant() == 0) {
					this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 1);
				}
			}


			if (this.getOverlayVariant() == 1 || this.getOverlayVariant() == 7 || this.getOverlayVariant() == 9 ||
					this.getOverlayVariant() == 10  || this.getOverlayVariant() == 12 || this.getOverlayVariant() == 13 ||
					this.getOverlayVariant() == 14 || this.getOverlayVariant() == 15) {
				if (random.nextDouble() < 0.20) {
					this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 2);
				} else if (random.nextDouble() > 0.50) {
					this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 1);
				}
			}

			if (this.getOverlayVariant() == 4 || this.getOverlayVariant() == 5 || this.getOverlayVariant() == 6 || this.getOverlayVariant() == 6) {
				if (random.nextDouble() < 0.20) {
					this.spawnAtLocation(LOItems.BLACK_WOOL_STAPLE.get(), 2);
				} else if (random.nextDouble() > 0.50) {
					this.spawnAtLocation(LOItems.BLACK_WOOL_STAPLE.get(), 1);
				}
			}


			if (this.getOverlayVariant() == 3) { //pure white, only drop white wool
				if (random.nextDouble() < 0.20) {
					this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 3);
				} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
					this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 2);
				} else if (random.nextDouble() > 0.50) {
					this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 1);
				}

				this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 1);
			}


			//if vanilla loot
		} else {
			if (this.getVariant() == 0 && !(this.getOverlayVariant() == 3)) {
				if (random.nextDouble() < 0.20) {
					this.spawnAtLocation(Items.BLACK_WOOL, 3);
				} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
					this.spawnAtLocation(Items.BLACK_WOOL, 2);
				} else if (random.nextDouble() > 0.50) {
					this.spawnAtLocation(Items.BLACK_WOOL, 1);
				}

				if (this.getOverlayVariant() == 0) {
					this.spawnAtLocation(Items.BLACK_WOOL, 1);
				}
			}

			if ((this.getVariant() == 2 || this.getVariant() == 3 || this.getVariant() == 6 || this.getVariant() == 7 || this.getVariant() == 8)
					&& !(this.getOverlayVariant() == 3)) {
				if (random.nextDouble() < 0.20) {
					this.spawnAtLocation(Items.BROWN_WOOL, 3);
				} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
					this.spawnAtLocation(Items.BROWN_WOOL, 2);
				} else if (random.nextDouble() > 0.50) {
					this.spawnAtLocation(Items.BROWN_WOOL, 1);
				}

				if (this.getOverlayVariant() == 0) {
					this.spawnAtLocation(Items.BROWN_WOOL, 1);
				}
			}

			if ((this.getVariant() == 1 || this.getVariant() == 5) && !(this.getOverlayVariant() == 3)) {
				if (random.nextDouble() < 0.20) {
					this.spawnAtLocation(Items.GRAY_WOOL, 3);
				} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
					this.spawnAtLocation(Items.GRAY_WOOL, 2);
				} else if (random.nextDouble() > 0.50) {
					this.spawnAtLocation(Items.GRAY_WOOL, 1);
				}

				if (this.getOverlayVariant() == 0) {
					this.spawnAtLocation(Items.GRAY_WOOL, 1);
				}
			}

			if (this.getVariant() == 6 && !(this.getOverlayVariant() == 3)) {
				if (random.nextDouble() < 0.20) {
					this.spawnAtLocation(Items.LIGHT_GRAY_WOOL, 3);
				} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
					this.spawnAtLocation(Items.LIGHT_GRAY_WOOL, 2);
				} else if (random.nextDouble() > 0.50) {
					this.spawnAtLocation(Items.LIGHT_GRAY_WOOL, 1);
				}

				if (this.getOverlayVariant() == 0) {
					this.spawnAtLocation(Items.LIGHT_GRAY_WOOL, 1);
				}
			}

			if ((this.getVariant() == 4 || this.getVariant() == 9 || this.getVariant() == 10) && !(this.getOverlayVariant() == 3)) {
				if (random.nextDouble() < 0.20) {
					this.spawnAtLocation(Items.WHITE_WOOL, 3);
				} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
					this.spawnAtLocation(Items.WHITE_WOOL, 2);
				} else if (random.nextDouble() > 0.50) {
					this.spawnAtLocation(Items.WHITE_WOOL, 1);
				}

				if (this.getOverlayVariant() == 0) {
					this.spawnAtLocation(Items.WHITE_WOOL, 1);
				}
			}

			if (this.getOverlayVariant() == 1 || this.getOverlayVariant() == 7 || this.getOverlayVariant() == 9 ||
					this.getOverlayVariant() == 10  || this.getOverlayVariant() == 12 || this.getOverlayVariant() == 13 ||
					this.getOverlayVariant() == 14 || this.getOverlayVariant() == 15) {
				if (random.nextDouble() < 0.20) {
					this.spawnAtLocation(Items.WHITE_WOOL, 2);
				} else if (random.nextDouble() > 0.50) {
					this.spawnAtLocation(Items.WHITE_WOOL, 1);
				}
			}

			if (this.getOverlayVariant() == 4 || this.getOverlayVariant() == 5 || this.getOverlayVariant() == 6) {
				if (random.nextDouble() < 0.20) {
					this.spawnAtLocation(Items.BLACK_WOOL, 2);
				} else if (random.nextDouble() > 0.50) {
					this.spawnAtLocation(Items.BLACK_WOOL, 1);
				}
			}


			if (this.getOverlayVariant() == 3) { //pure white, only drop white wool
				if (random.nextDouble() < 0.20) {
					this.spawnAtLocation(Items.WHITE_WOOL, 3);
				} else if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
					this.spawnAtLocation(Items.WHITE_WOOL, 2);
				} else if (random.nextDouble() > 0.50) {
					this.spawnAtLocation(Items.WHITE_WOOL, 1);
				}

				this.spawnAtLocation(Items.WHITE_WOOL, 1);
			}
		}
	}

}