package com.dragn0007.dragnlivestock.entities.rabbit;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.ai.OAvoidEntityGoal;
import com.dragn0007.dragnlivestock.entities.util.LOAnimations;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarrotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;
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

public class ORabbit extends TamableAnimal implements GeoEntity {

	public ORabbit(EntityType<? extends ORabbit> type, Level level) {
		super(type, level);
		setSheared(false);
	}

	public int poopTime = this.random.nextInt(LivestockOverhaulCommonConfig.RABBIT_POOP_TIME.get()) + 6000;

	protected static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_rabbit");
	protected static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/rabbit");
	protected static final ResourceLocation TFC_LOOT_TABLE = new ResourceLocation("tfc", "entities/rabbit");
	@Override
	public @NotNull ResourceLocation getDefaultLootTable() {
		if (LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
			return VANILLA_LOOT_TABLE;
		}
		if (!ModList.get().isLoaded("tfc") && !LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
			return LOOT_TABLE;
		}
		if (ModList.get().isLoaded("tfc")) {
			return TFC_LOOT_TABLE;
		}
		return LOOT_TABLE;
	}

	public boolean isMeatBreed() {
		return this.getBreed() == 1;
	}

	public boolean isNormalBreed() {
		return this.getBreed() == 0 || this.getBreed() == 3;
	}

	public boolean isMiniBreed() {
		return this.getBreed() == 2;
	}

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0D, (double)this.getEyeHeight() * 0.6F, (double)(this.getBbWidth() * 0.6F));
		//              ^ Side offset                      ^ Height offset                   ^ Length offset
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 3.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25F);
	}

	public static final Ingredient FOOD_ITEMS = Ingredient.of(LOTags.Items.O_RABBIT_EATS);

	public void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.8F));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
		this.goalSelector.addGoal(4, new RabbitOAvoidEntityGoal<>(this, Player.class, 8.0F, 2.2D, 2.2D));
		this.goalSelector.addGoal(4, new RabbitOAvoidEntityGoal<>(this, Wolf.class, 10.0F, 2.2D, 2.2D));
		this.goalSelector.addGoal(4, new RabbitOAvoidEntityGoal<>(this, Monster.class, 4.0F, 2.2D, 2.2D));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(5, new RaidGardenGoal(this));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));

		this.goalSelector.addGoal(1, new OAvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity ->
				livingEntity.getType().is(LOTags.Entity_Types.WOLVES) && (livingEntity instanceof TamableAnimal && !((TamableAnimal) livingEntity).isTame()) && !this.isTame()
		));

		this.goalSelector.addGoal(1, new OAvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity ->
				livingEntity.getType().is(LOTags.Entity_Types.CATS) && (livingEntity instanceof TamableAnimal && !((TamableAnimal) livingEntity).isTame()) && !this.isTame()
		));

		this.goalSelector.addGoal(1, new OAvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity ->
				livingEntity.getType().is(LOTags.Entity_Types.DOGS) && (livingEntity instanceof TamableAnimal && !((TamableAnimal) livingEntity).isTame()) && !this.isTame()
		));

		this.goalSelector.addGoal(1, new OAvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity ->
				livingEntity.getType().is(LOTags.Entity_Types.FOXES) && (livingEntity instanceof TamableAnimal && !((TamableAnimal) livingEntity).isTame()) && !this.isTame()
		));
	}

	static class RabbitOAvoidEntityGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
		public final ORabbit rabbit;

		public RabbitOAvoidEntityGoal(ORabbit oRabbit, Class<T> tClass, float v, double v1, double v2) {
			super(oRabbit, tClass, v, v1, v2);
			this.rabbit = oRabbit;
		}

		@Override
		public boolean canUse() {
			if (rabbit.isTame()) {
				return false;
			}
			return super.canUse();
		}
	}

	public void aiStep() {
		super.aiStep();

		if (!this.level().isClientSide && LivestockOverhaulCommonConfig.RABBITS_POOP.get() && this.isAlive() && !this.isBaby() && --this.poopTime <= 0) {
			if (LivestockOverhaulCommonConfig.QUALITY.get()) {
				if (this.isGreatQuality() && random.nextDouble() <= 15) {
					this.spawnAtLocation(LOItems.RABBIT_POOP.get());
				} else if (this.isFantasticQuality() && random.nextDouble() <= 20) {
					this.spawnAtLocation(LOItems.RABBIT_POOP.get());
				} else if (this.isExquisiteQuality() && random.nextDouble() <= 25) {
					this.spawnAtLocation(LOItems.RABBIT_POOP.get());
					this.spawnAtLocation(LOItems.RABBIT_POOP.get());
				}
			}
			this.spawnAtLocation(LOItems.RABBIT_POOP.get());
			this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
			this.poopTime = this.random.nextInt(LivestockOverhaulCommonConfig.RABBIT_POOP_TIME.get()) + 6000;
		}

	}

	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		Item item = itemstack.getItem();

		if (itemstack.is(LOItems.GENDER_TEST_STRIP.get()) && this.isFemale()) {
			player.playSound(SoundEvents.BEEHIVE_EXIT, 1.0F, 1.0F);
			ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, LOItems.FEMALE_GENDER_TEST_STRIP.get().getDefaultInstance());
			player.setItemInHand(hand, itemstack1);
			return InteractionResult.SUCCESS;
		}

		if (itemstack.is(LOItems.GENDER_TEST_STRIP.get()) && this.isMale()) {
			player.playSound(SoundEvents.BEEHIVE_EXIT, 1.0F, 1.0F);
			ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, LOItems.MALE_GENDER_TEST_STRIP.get().getDefaultInstance());
			player.setItemInHand(hand, itemstack1);
			return InteractionResult.SUCCESS;
		}

		if (itemstack.is(LOItems.COAT_OSCILLATOR.get()) && player.getAbilities().instabuild) {
			if (player.isShiftKeyDown()) {
				if (this.getVariant() > 0) {
					this.setVariant(this.getVariant() - 1);
					this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
					return InteractionResult.SUCCESS;
				}
			}
			this.setVariant(this.getVariant() + 1);
			this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		} else if (itemstack.is(LOItems.MARKING_OSCILLATOR.get()) && player.getAbilities().instabuild) {
			if (player.isShiftKeyDown()) {
				if (this.getOverlayVariant() > 0) {
					this.setOverlayVariant(this.getOverlayVariant() - 1);
					this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
					return InteractionResult.sidedSuccess(this.level().isClientSide);
				}
			}
			this.setOverlayVariant(this.getOverlayVariant() + 1);
			this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		} else if (itemstack.is(LOItems.BREED_OSCILLATOR.get()) && player.getAbilities().instabuild) {
			if (player.isShiftKeyDown()) {
				if (this.getBreed() > 0) {
					this.setBreed(this.getBreed() - 1);
					this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
					return InteractionResult.SUCCESS;
				}
			}
			RabbitBreed.Breed currentBreed = RabbitBreed.Breed.values()[this.getBreed()];
			RabbitBreed.Breed nextBreed = currentBreed.next();
			this.setBreed(nextBreed.ordinal());
			this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
			return InteractionResult.SUCCESS;
		}

		if (itemstack.is(Items.SHEARS) && !player.isShiftKeyDown() &&
				(!isSheared() || regrowWoolCounter >= LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get()) && this.canBeSheared()) {
			player.playSound(SoundEvents.SHEEP_SHEAR, 1.0F, 1.0F);
			this.setSheared(true);
			if (random.nextDouble() <= 0.20) {
				this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get());
				this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get());
			} else if (random.nextDouble() > 0.20) {
				this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get());
			}
			regrowWoolCounter = 0;
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		}

		if (this.level().isClientSide) {
			boolean flag = this.isOwnedBy(player) || this.isTame() || this.isFood(itemstack) && !this.isTame();
			return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
		} else {
			if (this.isTame()) {
				if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
					this.heal((float)itemstack.getFoodProperties(this).getNutrition());
					if (!player.getAbilities().instabuild) {
						itemstack.shrink(1);
					}

					this.gameEvent(GameEvent.ENTITY_INTERACT);
					return InteractionResult.SUCCESS;
				}

				if (!(item instanceof DyeItem)) {
					InteractionResult interactionresult = super.mobInteract(player, hand);
					if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(player)) {
						this.setOrderedToSit(!this.isOrderedToSit());
						this.jumping = false;
						this.navigation.stop();
						this.setTarget(null);
						return InteractionResult.SUCCESS;
					}

					return interactionresult;
				}

			} else if (this.isFood(itemstack)) {
				if (!player.getAbilities().instabuild) {
					itemstack.shrink(1);
				}

				if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
					this.tame(player);
					this.navigation.stop();
					this.setTarget(null);
					this.setOrderedToSit(true);
					this.level().broadcastEntityEvent(this, (byte)7);
				} else {
					this.level().broadcastEntityEvent(this, (byte)6);
				}

				return InteractionResult.SUCCESS;
			}

			return super.mobInteract(player, hand);
		}
	}

	@Override
	public float getStepHeight() {
		return 1F;
	}

	protected final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	protected <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
		double currentSpeed = this.getDeltaMovement().lengthSqr();
		double speedThreshold = 0.01;

		AnimationController<T> controller = tAnimationState.getController();

		if (tAnimationState.isMoving()) {
			if (currentSpeed > speedThreshold) {
				controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(2.2);
			} else {
				controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(1.7);
			}
		} else if (this.isInSittingPose()) {
			controller.setAnimation(RawAnimation.begin().then("sit", Animation.LoopType.LOOP));
		} else {
			controller.setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
		}

		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "controller", 2, this::predicate));
		controllers.add(LOAnimations.genericAttackAnimation(this, LOAnimations.ATTACK));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}

	int moreCarrotTicks;

	boolean wantsMoreFood() {
		return this.moreCarrotTicks == 0;
	}

	static class RaidGardenGoal extends MoveToBlockGoal {
		public final ORabbit rabbit;
		public boolean wantsToRaid;
		public boolean canRaid;

		public RaidGardenGoal(ORabbit p_29782_) {
			super(p_29782_, (double)0.7F, 16);
			this.rabbit = p_29782_;
		}

		public boolean canUse() {
			if (this.nextStartTick <= 0) {
				if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.rabbit.level(), this.rabbit)) {
					return false;
				}

				this.canRaid = false;
				this.wantsToRaid = this.rabbit.wantsMoreFood();
				this.wantsToRaid = true;
			}

			return super.canUse();
		}

		public boolean canContinueToUse() {
			return this.canRaid && super.canContinueToUse();
		}

		public void tick() {
			super.tick();
			this.rabbit.getLookControl().setLookAt((double)this.blockPos.getX() + 0.5D, (double)(this.blockPos.getY() + 1), (double)this.blockPos.getZ() + 0.5D, 10.0F, (float)this.rabbit.getMaxHeadXRot());
			if (this.isReachedTarget()) {
				Level level = this.rabbit.level();
				BlockPos blockpos = this.blockPos.above();
				BlockState blockstate = level.getBlockState(blockpos);
				Block block = blockstate.getBlock();
				if (this.canRaid && block instanceof CarrotBlock) {
					int i = blockstate.getValue(CarrotBlock.AGE);
					if (i == 0) {
						level.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 2);
						level.destroyBlock(blockpos, true, this.rabbit);
					} else {
						level.setBlock(blockpos, blockstate.setValue(CarrotBlock.AGE, Integer.valueOf(i - 1)), 2);
						level.levelEvent(2001, blockpos, Block.getId(blockstate));
					}

					this.rabbit.moreCarrotTicks = 40;
				}

				this.canRaid = false;
				this.nextStartTick = 10;
			}

		}

		public boolean isValidTarget(LevelReader p_29785_, BlockPos p_29786_) {
			BlockState blockstate = p_29785_.getBlockState(p_29786_);
			if (blockstate.is(Blocks.FARMLAND) && this.wantsToRaid && !this.canRaid) {
				blockstate = p_29785_.getBlockState(p_29786_.above());
				if (blockstate.getBlock() instanceof CarrotBlock && ((CarrotBlock)blockstate.getBlock()).isMaxAge(blockstate)) {
					this.canRaid = true;
					return true;
				}
			}

			return false;
		}
	}

	public SoundEvent getAmbientSound() {
		super.getAmbientSound();
		return SoundEvents.RABBIT_AMBIENT;
	}

	public SoundEvent getDeathSound() {
		super.getDeathSound();
		return SoundEvents.RABBIT_DEATH;
	}

	public SoundEvent getHurtSound(DamageSource p_30720_) {
		super.getHurtSound(p_30720_);
		return SoundEvents.RABBIT_HURT;
	}

	public void playStepSound(BlockPos p_28254_, BlockState p_28255_) {
		this.playSound(SoundEvents.RABBIT_JUMP, 0.15F, 1.0F);
	}

	public boolean causeFallDamage(float p_148875_, float p_148876_, DamageSource p_148877_) {
		return false;
	}

	public boolean isFood(ItemStack p_28271_) {
		return FOOD_ITEMS.test(p_28271_);
	}


	// Generates the base texture
	public static final EntityDataAccessor<Integer> BREED = SynchedEntityData.defineId(ORabbit.class, EntityDataSerializers.INT);
	public ResourceLocation getModelResource() {
		return RabbitBreed.Breed.breedFromOrdinal(getBreed()).resourceLocation;
	}
	public int getBreed() {
		return this.entityData.get(BREED);
	}
	public void setBreed(int breed) {
		this.entityData.set(BREED, breed);
	}

	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(ORabbit.class, EntityDataSerializers.INT);
	public int getVariant() {
		return this.entityData.get(VARIANT);
	}
	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
		this.entityData.set(VARIANT_TEXTURE, ORabbitModel.Variant.variantFromOrdinal(variant).resourceLocation.toString());
	}
	public static final EntityDataAccessor<String> VARIANT_TEXTURE = SynchedEntityData.defineId(ORabbit.class, EntityDataSerializers.STRING);
	public String getTextureResource() {
		return this.entityData.get(VARIANT_TEXTURE);
	}
	public void setVariantTexture(String variant) {
		this.entityData.set(VARIANT_TEXTURE, variant);
	}

	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(ORabbit.class, EntityDataSerializers.INT);
	public int getOverlayVariant() {
		return this.entityData.get(OVERLAY);
	}
	public void setOverlayVariant(int variant) {
		this.entityData.set(OVERLAY, variant);
		this.entityData.set(OVERLAY_TEXTURE, ORabbitMarkingLayer.Overlay.overlayFromOrdinal(variant).resourceLocation.toString());
	}
	public static final EntityDataAccessor<String> OVERLAY_TEXTURE = SynchedEntityData.defineId(ORabbit.class, EntityDataSerializers.STRING);
	public String getOverlayLocation() {
		return this.entityData.get(OVERLAY_TEXTURE);
	}
	public void setOverlayVariantTexture(String variant) {
		this.entityData.set(OVERLAY_TEXTURE, variant);
	}

	public static final EntityDataAccessor<Integer> DEWLAP = SynchedEntityData.defineId(ORabbit.class, EntityDataSerializers.INT);
	public int getDewlap() {
		return this.entityData.get(DEWLAP);
	}
	public void setDewlap(int dewlap) {
		this.entityData.set(DEWLAP, dewlap);
	}

	public static final EntityDataAccessor<Boolean> SHEARED = SynchedEntityData.defineId(ORabbit.class, EntityDataSerializers.BOOLEAN);
	public boolean isSheared() {
		return this.entityData.get(SHEARED);
	}
	public void setSheared(boolean sheared) {
		this.entityData.set(SHEARED, sheared);
	}
	public boolean canBeSheared() {
		return this.getBreed() == 4 && !this.isBaby() && !this.isSheared();
	}

	public static final EntityDataAccessor<Integer> QUALITY = SynchedEntityData.defineId(ORabbit.class, EntityDataSerializers.INT);
	public int getQuality() {
		return this.entityData.get(QUALITY);
	}
	public void setQuality(int i) {
		this.entityData.set(QUALITY, i);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		if(tag.contains("Quality")) {
			this.setQuality(tag.getInt("Quality"));
		}

		if (tag.contains("Breed")) {
			this.setBreed(tag.getInt("Breed"));
		}

		if (tag.contains("Variant")) {
			setVariant(tag.getInt("Variant"));
		}

		if (tag.contains("Overlay")) {
			setOverlayVariant(tag.getInt("Overlay"));
		}

		if (tag.contains("Variant_Texture")) {
			this.setVariantTexture(tag.getString("Variant_Texture"));
		}

		if (tag.contains("Overlay_Texture")) {
			this.setOverlayVariantTexture(tag.getString("Overlay_Texture"));
		}

		if (tag.contains("Dewlap")) {
			this.setDewlap(tag.getInt("Dewlap"));
		}

		if (tag.contains("Gender")) {
			this.setGender(tag.getInt("Gender"));
		}

		if (tag.contains("PoopTime")) {
			this.poopTime = tag.getInt("PoopTime");
		}

		if (tag.contains("Sheared")) {
			this.setSheared(tag.getBoolean("Sheared"));
		}

		if (tag.contains("ShearedTime")) {
			this.regrowWoolCounter = tag.getInt("ShearedTime");
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Quality", this.getQuality());
		tag.putInt("Breed", this.getBreed());
		tag.putInt("Variant", getVariant());
		tag.putInt("Overlay", getOverlayVariant());
		tag.putString("Variant_Texture", this.getTextureResource().toString());
		tag.putString("Overlay_Texture", this.getOverlayLocation().toString());
		tag.putInt("Dewlap", this.getDewlap());
		tag.putInt("Gender", this.getGender());
		tag.putInt("PoopTime", this.poopTime);
		tag.putBoolean("Sheared", this.isSheared());
		tag.putInt("ShearedTime", this.regrowWoolCounter);
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}
		Random random = new Random();
		this.setGender(random.nextInt(ORabbit.Gender.values().length));
		this.setDewlapByGender();

		if (LivestockOverhaulCommonConfig.QUALITY.get()) {
			this.setQuality(random.nextInt(30));
		}

		if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
			this.setColorByWildStatus();
			this.setMarkingByWildStatus();
		} else {
			this.setVariant(random.nextInt(ORabbitModel.Variant.values().length));
			this.setOverlayVariant(random.nextInt(ORabbitMarkingLayer.Overlay.values().length));
			this.setBreed(random.nextInt(RabbitBreed.Breed.values().length));
		}

		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(QUALITY, 0);
		this.entityData.define(BREED, 0);
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
		this.entityData.define(VARIANT_TEXTURE, ORabbitModel.Variant.BLACK.resourceLocation.toString());
		this.entityData.define(OVERLAY_TEXTURE, ORabbitMarkingLayer.Overlay.NONE.resourceLocation.toString());
		this.entityData.define(GENDER, 0);
		this.entityData.define(DEWLAP, 0);
		this.entityData.define(SHEARED, false);
	}

	public enum Gender {
		FEMALE,
		MALE
	}
	public boolean isFemale() {
		return this.getGender() == 0;
	}
	public boolean isMale() {
		return this.getGender() == 1;
	}
	public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(ORabbit.class, EntityDataSerializers.INT);
	public int getGender() {
		return this.entityData.get(GENDER);
	}
	public void setGender(int gender) {
		this.entityData.set(GENDER, gender);
	}

	public boolean canParent() {
		return !this.isBaby() && this.isInLove();
	}

	@Override
	public void finalizeSpawnChildFromBreeding(ServerLevel pLevel, Animal pAnimal, @org.jetbrains.annotations.Nullable AgeableMob pBaby) {
		super.finalizeSpawnChildFromBreeding(pLevel, pAnimal, pBaby);
		if (pAnimal instanceof ORabbit partner) {
			if (LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
				if (this.isMale()) {
					this.setAge(LivestockOverhaulCommonConfig.MALE_COOLDOWN.get());
				} else {
					this.setAge(LivestockOverhaulCommonConfig.FEMALE_COOLDOWN.get());
				}
				if (partner.isMale()) {
					partner.setAge(LivestockOverhaulCommonConfig.MALE_COOLDOWN.get());
				} else {
					partner.setAge(LivestockOverhaulCommonConfig.FEMALE_COOLDOWN.get());
				}
			} else {
				this.setAge(LivestockOverhaulCommonConfig.FEMALE_COOLDOWN.get());
				partner.setAge(LivestockOverhaulCommonConfig.FEMALE_COOLDOWN.get());
			}
		}
	}

	public boolean canMate(Animal animal) {
		if (animal == this) {
			return false;
		} else if (!(animal instanceof ORabbit)) {
			return false;
		} else {
			if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
				return this.canParent() && ((ORabbit) animal).canParent();
			} else {
				ORabbit partner = (ORabbit) animal;
				if (this.canParent() && partner.canParent() && this.getGender() != partner.getGender()) {
					return isFemale();
				}
			}
		}
		return false;
	}

	public int maxBabyAmount = LivestockOverhaulCommonConfig.MAX_RABBIT_BABIES.get();
	public int babiesBirthed = 0;
	public int babyCooldown = 0;
	public int regrowWoolCounter = 0;

	public void tick() {
		super.tick();

		if (babiesBirthed > 0) {
			babyCooldown++;
		}

		if (babiesBirthed >= maxBabyAmount && babyCooldown >= 20) {
			babiesBirthed = 0;
			babyCooldown = 0;
		}

		if (babyCooldown >= 20) {
			babyCooldown = 0;
		}

		if (this.getBreed() == 4 && !this.isBaby()) {
			regrowWoolCounter++;
		}

		if (LivestockOverhaulCommonConfig.QUALITY.get()) {
			if (this.isFineQuality()) {
				if (regrowWoolCounter >= LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get()) {
					this.setSheared(false);
				}
			} else if (this.isGreatQuality()) {
				if (regrowWoolCounter >= (LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get() / 1.3)) {
					this.setSheared(false);
				}
			} else if (this.isFantasticQuality()) {
				if (regrowWoolCounter >= (LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get() / 2)) {
					this.setSheared(false);
				}
			} else if (this.isExquisiteQuality()) {
				if (regrowWoolCounter >= (LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get() / 2.5)) {
					this.setSheared(false);
				}
			}
		} else {
			if (regrowWoolCounter >= LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get()) {
				this.setSheared(false);
			}
		}
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		ORabbit kit;
		ORabbit partner = (ORabbit) ageableMob;
		kit = EntityTypes.O_RABBIT_ENTITY.get().create(serverLevel);

		int breedChance = this.random.nextInt(5);
		int breed;
		if (breedChance == 0) {
			int[] breeds = {0, 1, 2, 3, 4, 5, 6, 7, 8};
			int randomIndex = new Random().nextInt(breeds.length);
			breed = (breeds[randomIndex]);
		} else {
			breed = (this.random.nextInt(2) == 0) ? this.getBreed() : partner.getBreed();
		}
		kit.setBreed(breed);

		if (!(breedChance == 0) && random.nextDouble() > 0.5) {
			int variantChance = this.random.nextInt(14);
			int variant;
			if (variantChance < 6) {
				variant = this.getVariant();
			} else if (variantChance < 12) {
				variant = partner.getVariant();
			} else {
				variant = this.random.nextInt(ORabbitModel.Variant.values().length);
			}
			kit.setVariant(variant);
		}

		if (!(breedChance == 0) && random.nextDouble() > 0.5) {
			int overlayChance = this.random.nextInt(10);
			int overlay;
			if (overlayChance < 4) {
				overlay = this.getOverlayVariant();
			} else if (overlayChance < 8) {
				overlay = partner.getOverlayVariant();
			} else {
				overlay = this.random.nextInt(ORabbitMarkingLayer.Overlay.values().length);
			}
			kit.setOverlayVariant(overlay);
		}

		kit.setGender(random.nextInt(Gender.values().length));

		kit.setDewlapByGender();

		if (LivestockOverhaulCommonConfig.QUALITY.get()) {
			int qual_avg = (this.getQuality() + partner.getQuality()) / 2;
			if (random.nextDouble() <= 0.05) {
				kit.setQuality(qual_avg + random.nextInt(50));
			} else if (random.nextDouble() >= 0.05 && random.nextDouble() <= 0.25) {
				kit.setQuality(qual_avg + random.nextInt(25));
			} else if (random.nextDouble() >= 0.25 && random.nextDouble() <= 0.60) {
				kit.setQuality(qual_avg + random.nextInt(10));
			} else {
				kit.setQuality(qual_avg + random.nextInt(5));
			}
		}

		babiesBirthed++;

		if (babiesBirthed < maxBabyAmount && this.isInLove()) {
			if (random.nextDouble() <= 0.25) {
				spawnChildFromBreeding(serverLevel, partner);
				babiesBirthed++;
			} else if (random.nextDouble() <= 0.50 && babiesBirthed < (maxBabyAmount * 0.50)) {
				spawnChildFromBreeding(serverLevel, partner);
				babiesBirthed++;
			}
		}

		if (kit.getQuality() > 100) {
			kit.setQuality(100);
		}

		return kit;
	}

	public boolean isFineQuality() {
		return this.getQuality() <= 25;
	}

	public boolean isGreatQuality() {
		return this.getQuality() > 25 && this.getQuality() <= 50;
	}

	public boolean isFantasticQuality() {
		return this.getQuality() > 50 && this.getQuality() <= 75;
	}

	public boolean isExquisiteQuality() {
		return this.getQuality() > 75 && this.getQuality() <= 100;
	}

	@Override
	public void dropCustomDeathLoot(DamageSource p_33574_, int p_33575_, boolean p_33576_) {
		super.dropCustomDeathLoot(p_33574_, p_33575_, p_33576_);
		Random random = new Random();

		if (!LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get() || !ModList.get().isLoaded("tfc")) {
			if (this.isMeatBreed()) {
				if (random.nextDouble() < 0.40) {
					this.spawnAtLocation(Items.RABBIT, 2);
					this.spawnAtLocation(LOItems.RABBIT_THIGH.get(), 2);
					this.spawnAtLocation(Items.RABBIT_HIDE, 2);
				} else if (random.nextDouble() > 0.40) {
					this.spawnAtLocation(Items.RABBIT);
					this.spawnAtLocation(LOItems.RABBIT_THIGH.get());
					this.spawnAtLocation(Items.RABBIT_HIDE);
				}
			}

			if (this.isNormalBreed()) {
				if (random.nextDouble() < 0.15) {
					this.spawnAtLocation(Items.RABBIT);
					this.spawnAtLocation(LOItems.RABBIT_THIGH.get());
					this.spawnAtLocation(Items.RABBIT_HIDE);
				}
			}

			if (LivestockOverhaulCommonConfig.QUALITY.get()) {
				if (this.isExquisiteQuality()) {
					this.spawnAtLocation(Items.RABBIT, 3);
					this.spawnAtLocation(Items.RABBIT_HIDE, 3);
				} else if (this.isFantasticQuality()) {
					this.spawnAtLocation(Items.RABBIT, 2);
					this.spawnAtLocation(Items.RABBIT_HIDE, 2);
				} else if (this.isGreatQuality()) {
					this.spawnAtLocation(Items.RABBIT);
					this.spawnAtLocation(Items.RABBIT_HIDE);
				}
			}

		}
	}

	public void setDewlapByGender() {

		if (this.isFemale()) {
			if (random.nextDouble() < 0.15) {
				this.setDewlap(0);
			} else if (random.nextDouble() > 0.15 && random.nextDouble() < 0.50) {
				this.setDewlap(1);
			} else if (random.nextDouble() > 0.50) {
				this.setDewlap(2);
			}
		}

		if (this.isMale()) {
			if (random.nextDouble() < 0.15) {
				this.setDewlap(2);
			} else if (random.nextDouble() > 0.15 && random.nextDouble() < 0.50) {
				this.setDewlap(1);
			} else if (random.nextDouble() > 0.50) {
				this.setDewlap(0);
			}
		}

		if (this.getBreed() == 7) {
			if (random.nextDouble() < 0.15) {
				this.setDewlap(0);
			} else if (random.nextDouble() > 0.15 && random.nextDouble() < 0.50) {
				this.setDewlap(1);
			} else if (random.nextDouble() > 0.50) {
				this.setDewlap(2);
			}
		}

	}

	public void setColorByWildStatus() {
		if (getBreed() != 4) {
			if (random.nextDouble() < 0.05) {
				this.setVariant(random.nextInt(ORabbitModel.Variant.values().length));
			} else if (random.nextDouble() > 0.05) {
				int[] variants = {0, 2, 3, 8, 10, 11, 12};
				int randomIndex = new Random().nextInt(variants.length);
				this.setVariant(variants[randomIndex]);
			}
		} else if (getBreed() == 4) {
			this.setVariant(11);
		}
	}

	public void setMarkingByWildStatus() {
		if (getBreed() != 4) {
			if (random.nextDouble() < 0.10) {
				this.setOverlayVariant(random.nextInt(ORabbitMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.10) {
				this.setOverlayVariant(0);
			}
		} else if (getBreed() == 4) {
			if (random.nextDouble() < 0.05) {
				this.setOverlayVariant(random.nextInt(ORabbitMarkingLayer.Overlay.values().length));
			} else if (random.nextDouble() > 0.05) {
				this.setOverlayVariant(0);
			}
		}
	}

	public void setBreedByWildStatus() {
		if (random.nextDouble() < 0.01) {
			this.setBreed(9);
		} else if (random.nextDouble() > 0.01) {
			this.setBreed(0);
		}
	}

}
