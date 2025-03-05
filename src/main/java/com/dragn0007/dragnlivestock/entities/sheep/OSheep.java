package com.dragn0007.dragnlivestock.entities.sheep;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.ai.SheepFollowHerdLeaderGoal;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Stream;

public class OSheep extends Animal implements Shearable, net.minecraftforge.common.IForgeShearable, GeoEntity {

	public static final int EAT_ANIMATION_TICKS = 100;

	public static final EntityDataAccessor<Byte> DATA_WOOL_ID = SynchedEntityData.defineId(OSheep.class, EntityDataSerializers.BYTE);

	public static final Map<DyeColor, ItemLike> ITEM_BY_DYE = Util.make(Maps.newEnumMap(DyeColor.class), (p_29841_) -> {
		p_29841_.put(DyeColor.WHITE, Blocks.WHITE_WOOL);
	});

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0D, (double)this.getEyeHeight() * 1F, (double)(this.getBbWidth() * 1F));
		//              ^ Side offset                      ^ Height offset                   ^ Length offset
	}

	public OSheep(EntityType<? extends OSheep> type, Level level) {
		super(type, level);
	}

	private static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_sheep");
	private static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/sheep");
	private static final ResourceLocation TFC_LOOT_TABLE = new ResourceLocation("tfc", "entities/sheep");
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
				.add(Attributes.MAX_HEALTH, 8.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.18F);
	}

	public static final Ingredient FOOD_ITEMS = Ingredient.of(LOTags.Items.O_SHEEP_EATS);

	public void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.8F));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(8, new EatGrassGoal(this));
		this.goalSelector.addGoal(3, new SheepFollowHerdLeaderGoal(this));

		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity -> {
			boolean isHorse = livingEntity.getType().is(LOTags.Entity_Types.HORSES);
			boolean isRHGHorse = livingEntity.getType().is(LOTags.Entity_Types.RHG_HORSES);
			boolean isSWEMHorse = livingEntity.getType().is(LOTags.Entity_Types.SWEM_HORSES);
			boolean isOWolf = livingEntity.getType().is(LOTags.Entity_Types.O_WOLVES);
			boolean isHerdingDog = livingEntity.getType().is(LOTags.Entity_Types.HERDING_DOGS);
			boolean isWolf = livingEntity instanceof Wolf;
			return isHorse || isRHGHorse || isSWEMHorse || isOWolf || isWolf || isHerdingDog;
		}));
	}

	public OSheep leader;
	public int herdSize = 1;

	public boolean isFollower() {
		return this.leader != null && this.leader.isAlive();
	}

	public OSheep startFollowing(OSheep oSheep) {
		this.leader = oSheep;
		oSheep.addFollower();
		return oSheep;
	}

	public void stopFollowing() {
		this.leader.removeFollower();
		this.leader = null;
	}

	public void addFollower() {
		++this.herdSize;
	}

	public void removeFollower() {
		--this.herdSize;
	}

	public boolean canBeFollowed() {
		return this.hasFollowers() && this.herdSize < this.getMaxHerdSize();
	}

	public int replenishMilkCounter = 0;

	private boolean milked = false;

	public boolean wasMilked() {
		return this.milked;
	}

	public void tick() {
		super.tick();
		if (this.hasFollowers() && this.level().random.nextInt(200) == 1) {
			List<? extends OSheep> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
			if (list.size() <= 1) {
				this.herdSize = 1;
			}
		}

		replenishMilkCounter++;

		if (replenishMilkCounter >= LivestockOverhaulCommonConfig.MILKING_COOLDOWN.get()) {
			this.setMilked(false);
		}
	}

	public int getMaxHerdSize() {
		return LivestockOverhaulCommonConfig.SHEEP_HERD_MAX.get();
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

	public void addFollowers(Stream<? extends OSheep> p_27534_) {
		p_27534_.limit((long)(this.getMaxHerdSize() - this.herdSize)).filter((cow) -> {
			return cow != this;
		}).forEach((cow) -> {
			cow.startFollowing(this);
		});
	}

	public class EatGrassGoal extends Goal {
		public final OSheep sheep;
		public int eatAnimationTick;

		public EatGrassGoal(OSheep sheep) {
			this.sheep = sheep;
			this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
		}

		@Override
		public boolean canUse() {
			return sheep.onGround() && sheep.getRandom().nextInt(100) < 10;
		}

		@Override
		public void start() {
			eatAnimationTick = 0;
			sheep.getNavigation().stop();
		}

		@Override
		public void tick() {
			eatAnimationTick++;
			if (eatAnimationTick >= EAT_ANIMATION_TICKS) {
				sheep.eatGrass();
				eatAnimationTick = 0;
			}
		}

		@Override
		public boolean canContinueToUse() {
			return eatAnimationTick < EAT_ANIMATION_TICKS;
		}
	}

	public void eatGrass() {
		if (this.isBaby()) {
			this.ageUp(60);
		}
		this.setSheared(false);
	}

	public boolean isShearable(@Nonnull ItemStack item, Level world, BlockPos pos) {
		return readyForShearing();
	}

	@Override
	@Nonnull
	public List<ItemStack> onSheared(@Nullable Player player, @Nonnull ItemStack item, Level world, BlockPos pos, int fortune) {
		world.playSound(null, this, SoundEvents.SHEEP_SHEAR, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
		this.gameEvent(GameEvent.SHEAR, player);
		if (!world.isClientSide) {
			this.setSheared(true);
			int i = 1 + this.random.nextInt(3);

			List<ItemStack> items = new ArrayList<>();
			for (int j = 0; j < i; ++j) {
				items.add(new ItemStack(ITEM_BY_DYE.get(this.getColor())));
			}
			return items;
		}
		return Collections.emptyList();
	}

	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);

		if (itemstack.getItem() == Items.SHEARS) {
			if (!this.level().isClientSide && this.readyForShearing()) {
				List<ItemStack> drops = this.onSheared(player, itemstack, this.level(), this.blockPosition(), 0);
				for (ItemStack drop : drops) {
					this.spawnAtLocation(drop);
				}
				itemstack.hurtAndBreak(1, player, (p_29923_) -> {
					p_29923_.broadcastBreakEvent(hand);
				});
				return InteractionResult.SUCCESS;
			} else {
				return InteractionResult.CONSUME;
			}
		}

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

		if (itemstack.is(Items.BUCKET) && !this.isBaby() && (!wasMilked() || replenishMilkCounter >= LivestockOverhaulCommonConfig.MILKING_COOLDOWN.get()) &&
				(!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() ||
						(LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() && this.isFemale()))) {
			player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
			ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, LOItems.SHEEP_MILK_BUCKET.get().getDefaultInstance());
			player.setItemInHand(hand, itemstack1);
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		} else {
			return super.mobInteract(player, hand);
		}
	}

	public void shear(SoundSource p_29819_) {
		this.level().playSound((Player)null, this, SoundEvents.SHEEP_SHEAR, p_29819_, 1.0F, 1.0F);
		this.setSheared(true);
		int i = 1 + this.random.nextInt(3);

		for(int j = 0; j < i; ++j) {
			ItemEntity itementity = this.spawnAtLocation(ITEM_BY_DYE.get(this.getColor()), 1);
			if (itementity != null) {
				itementity.setDeltaMovement(itementity.getDeltaMovement().add((double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F), (double)(this.random.nextFloat() * 0.05F), (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)));
			}
		}
	}

	public boolean readyForShearing() {
		return this.isAlive() && !this.isSheared() && !this.isBaby();
	}

	@Override
	public float getStepHeight() {
		return 1F;
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
				controller.setAnimationSpeed(1.4);
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

	public SoundEvent getAmbientSound() {
		return SoundEvents.SHEEP_AMBIENT;
	}

	public SoundEvent getDeathSound() {
		return SoundEvents.SHEEP_DEATH;
	}

	public SoundEvent getHurtSound(DamageSource p_30720_) {
		return SoundEvents.SHEEP_HURT;
	}

	public void playStepSound(BlockPos p_28254_, BlockState p_28255_) {
		this.playSound(SoundEvents.SHEEP_STEP, 0.15F, 1.0F);
	}

	public boolean isFood(ItemStack p_28271_) {
		return FOOD_ITEMS.test(p_28271_);
	}


	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OSheep.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> BREED = SynchedEntityData.defineId(OSheep.class, EntityDataSerializers.INT);

	public ResourceLocation getTextureLocation() {
		return OSheepModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
	}

	public int getBreedLocation() {
		return Breed.values().length;
	}

	public int getVariant() {
		return this.entityData.get(VARIANT);
	}

	public int getBreed() {
		return this.entityData.get(BREED);
	}

	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
	}

	public void setBreed(int breed) {
		this.entityData.set(BREED, breed);
	}

	public boolean getMilked() {
		return this.milked;
	}

	public void setMilked(boolean milked) {
		this.milked = milked;
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		if (tag.contains("Variant")) {
			setVariant(tag.getInt("Variant"));
		}

		if (tag.contains("Breed")) {
			setBreed(tag.getInt("Breed"));
		}

		if (tag.contains("Gender")) {
			this.setGender(tag.getInt("Gender"));
		}

		if (tag.contains("Milked")) {
			setMilked(tag.getBoolean("Milked"));
		}

		this.setSheared(tag.getBoolean("Sheared"));

		this.setColor(DyeColor.byId(tag.getByte("Color")));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Variant", getVariant());
		tag.putInt("Breed", getBreed());
		tag.putBoolean("Sheared", this.isSheared());
		tag.putByte("Color", (byte)this.getColor().getId());
		tag.putInt("Gender", this.getGender());
		tag.putBoolean("Milked", getMilked());
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, 0);
		this.entityData.define(DATA_WOOL_ID, (byte)0);
		this.entityData.define(BREED, 0);
		this.entityData.define(GENDER, 0);
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}
		Random random = new Random();
		setVariant(random.nextInt(OSheepModel.Variant.values().length));
		setBreed(random.nextInt(OSheep.Breed.values().length));
		this.setGender(random.nextInt(OSheep.Gender.values().length));

		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
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

	public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(OSheep.class, EntityDataSerializers.INT);

	public int getGender() {
		return this.entityData.get(GENDER);
	}

	public void setGender(int gender) {
		this.entityData.set(GENDER, gender);
	}

	public boolean canParent() {
		return !this.isBaby() && this.isInLove();
	}

	public boolean canMate(Animal animal) {
		if (animal == this) {
			return false;
		} else if (!(animal instanceof OSheep)) {
			return false;
		} else {
			if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
				return this.canParent() && ((OSheep) animal).canParent();
			} else {
				OSheep partner = (OSheep) animal;
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
		OSheep oSheep = (OSheep) ageableMob;
		if (ageableMob instanceof OSheep) {
			OSheep oSheep1 = (OSheep) ageableMob;
			oSheep = EntityTypes.O_SHEEP_ENTITY.get().create(serverLevel);

			int i = this.random.nextInt(9);
			int variant;
			if (i < 4) {
				variant = this.getVariant();
			} else if (i < 8) {
				variant = oSheep1.getVariant();
			} else {
				variant = this.random.nextInt(OSheepModel.Variant.values().length);
			}

			int j = this.random.nextInt(9);
			int breed;
			if (j < 4) {
				breed = this.getBreedLocation();
			} else if (j < 8) {
				breed = oSheep1.getBreedLocation();
			} else {
				breed = this.random.nextInt(OSheep.Breed.values().length);
			}

			int gender;
			gender = this.random.nextInt(OSheep.Gender.values().length);

			oSheep.setVariant(variant);
			oSheep.setBreed(breed);
			oSheep.setGender(gender);
		}

		return oSheep;
	}

	public enum Breed {
		DEFAULT,
		RACKA,
		JACOB,
		DORPER;
	}

	public DyeColor getColor() {
		return DyeColor.byId(this.entityData.get(DATA_WOOL_ID) & 15);
	}

	public void setColor(DyeColor color) {
		byte b0 = this.entityData.get(DATA_WOOL_ID);
		this.entityData.set(DATA_WOOL_ID, (byte)(b0 & 240 | color.getId() & 15));
	}

	public boolean isSheared() {
		return (this.entityData.get(DATA_WOOL_ID) & 16) != 0;
	}

	public void setSheared(boolean p_29879_) {
		byte b0 = this.entityData.get(DATA_WOOL_ID);
		if (p_29879_) {
			this.entityData.set(DATA_WOOL_ID, (byte)(b0 | 16));
		} else {
			this.entityData.set(DATA_WOOL_ID, (byte)(b0 & -17));
		}
	}

	public void ate() {
		this.setSheared(false);
		if (this.isBaby()) {
			this.ageUp(60);
		}
	}

}
