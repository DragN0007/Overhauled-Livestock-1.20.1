package com.dragn0007.dragnlivestock.entities.llama;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.Chestable;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.ai.LlamaFollowHerdLeaderGoal;
import com.dragn0007.dragnlivestock.entities.ai.OLlamaFollowCaravanGoal;
import com.dragn0007.dragnlivestock.entities.util.LOAnimations;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WoolCarpetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.items.wrapper.InvWrapper;
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
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class OLlama extends AbstractChestedHorse implements GeoEntity, Chestable, ContainerListener, RangedAttackMob {

	public static final Ingredient FOOD_ITEMS = Ingredient.of(LOTags.Items.O_LLAMA_EATS);
	public static final EntityDataAccessor<Integer> DATA_STRENGTH_ID = SynchedEntityData.defineId(OLlama.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_SWAG_ID = SynchedEntityData.defineId(OLlama.class, EntityDataSerializers.INT);
	boolean didSpit;
	@Nullable
	public OLlama caravanHead;
	@Nullable
	public OLlama caravanTail;

	public static final EntityDataAccessor<Boolean> CHESTED = SynchedEntityData.defineId(OLlama.class, EntityDataSerializers.BOOLEAN);

	public OLlama(EntityType<? extends OLlama> type, Level level) {
		super(type, level);
	}

	protected static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_llama");
	protected static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/llama");
	protected static final ResourceLocation TFC_LOOT_TABLE = new ResourceLocation("tfc", "entities/llama");
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
		return createBaseHorseAttributes()
				.add(Attributes.MOVEMENT_SPEED, (double)0.20F)
				.add(Attributes.JUMP_STRENGTH, 0.5D)
				.add(Attributes.FOLLOW_RANGE, 40.0D);
	}

	@Override
	public boolean canPerformRearing() {
		return false;
	}

	public void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new RunAroundLikeCrazyGoal(this, 1.2D));
		this.goalSelector.addGoal(3, new RangedAttackGoal(this, 1.25D, 40, 20.0F));
		this.goalSelector.addGoal(3, new PanicGoal(this, 1.2D));
		this.goalSelector.addGoal(4, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(5, new TemptGoal(this, 1.25D, Ingredient.of(Items.HAY_BLOCK), false));
		this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.7D));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new LlamaHurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new LlamaAttackWolfGoal(this));
		this.goalSelector.addGoal(3, new LlamaFollowHerdLeaderGoal(this));
		this.goalSelector.addGoal(2, new OLlamaFollowCaravanGoal(this, (double)2.1F));
	}

	public LazyOptional<?> itemHandler = null;
	public OLlama leader;
	public int herdSize = 1;

	protected final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	protected <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
		double movementSpeed = this.getAttributeBaseValue(Attributes.MOVEMENT_SPEED);
		double animationSpeed = Math.max(0.1, movementSpeed);
		double x = this.getX() - this.xo;
		double z = this.getZ() - this.zo;
		double currentSpeed = this.getDeltaMovement().lengthSqr();
		double speedThreshold = 0.015;

		boolean isMoving = (x * x + z * z) > 0.0001;

		AnimationController<T> controller = tAnimationState.getController();

		if (isMoving) {
			if (currentSpeed > speedThreshold) {
				controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(Math.max(0.1, 0.8 * controller.getAnimationSpeed() + animationSpeed));
			} else {
				controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(Math.max(0.1, 0.82 * controller.getAnimationSpeed() + animationSpeed));
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
		controllers.add(LOAnimations.genericAttackAnimation(this, LOAnimations.ATTACK));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}

	public boolean isFollower() {
		return this.leader != null && this.leader.isAlive();
	}

	public OLlama startFollowing(OLlama horse) {
		this.leader = horse;
		horse.addFollower();
		return horse;
	}

	public void stopFollowing() {
		if (this.leader != null) {
			this.leader.removeFollower();
			this.leader = null;
		}
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

	public int getMaxHerdSize() {
		return LivestockOverhaulCommonConfig.LLAMA_HERD_MAX.get();
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

	public void addFollowers(Stream<? extends OLlama> stream) {
		stream.limit((long)(this.getMaxHerdSize() - this.herdSize)).filter((horse) -> {
			return horse != this;
		}).forEach((horse) -> {
			horse.startFollowing(this);
		});
	}

	public int replenishMilkCounter = 0;
	public int regrowWoolCounter = 0;

	@Override
	public void tick() {
		super.tick();

		if (this.hasFollowers() && this.level().random.nextInt(200) == 1) {
			List<? extends OLlama> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
			if (list.size() <= 1) {
				this.herdSize = 1;
			}
		}

		regrowWoolCounter++;

		if (regrowWoolCounter >= LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get()) {
			this.setSheared(false);
		}

		if (this.getWooly() == 0) {
			this.setSheared(true);
		}

		replenishMilkCounter++;

		if (replenishMilkCounter >= LivestockOverhaulCommonConfig.MILKING_COOLDOWN.get()) {
			this.setMilked(false);
		}
	}

	@Override
	public void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
		if (this.hasPassenger(entity)) {
			double offsetX;
			double offsetY;
			double offsetZ;

			offsetX = 0;
			offsetY = 0.8;
			offsetZ = -0.2;

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

	public void doPlayerRide(Player p_30634_) {
		if (!this.level().isClientSide) {
			p_30634_.setYRot(this.getYRot());
			p_30634_.setXRot(this.getXRot());
			p_30634_.startRiding(this);
		}
	}

	public void updateInventory() {
		SimpleContainer tempInventory = this.inventory;
		this.inventory = new SimpleContainer(this.getInventorySize());

		if(tempInventory != null) {
			tempInventory.removeListener(this);
			int maxSize = Math.min(tempInventory.getContainerSize(), this.inventory.getContainerSize());

			for(int i = 0; i < maxSize; i++) {
				ItemStack itemStack = tempInventory.getItem(i);
				if(!itemStack.isEmpty()) {
					this.inventory.setItem(i, itemStack.copy());
				}
			}
		}
		this.inventory.addListener(this);
		this.itemHandler = LazyOptional.of(() -> new InvWrapper(this.inventory));
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		if(this.itemHandler != null) {
			LazyOptional<?> oldHandler = this.itemHandler;
			this.itemHandler = null;
			oldHandler.invalidate();
		}
	}

	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);

		if (itemstack.is(Items.SHEARS) && !player.isShiftKeyDown() && !this.isBaby() &&
				(!isSheared() || regrowWoolCounter >= LivestockOverhaulCommonConfig.SHEEP_WOOL_REGROWTH_TIME.get()) && this.getWooly() == 1) {
			player.playSound(SoundEvents.SHEEP_SHEAR, 1.0F, 1.0F);
			this.setSheared(true);
			this.dropWoolByColorAndMarking();
			regrowWoolCounter = 0;
			return InteractionResult.sidedSuccess(this.level().isClientSide);
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
			if (this.getOverlayVariant() > 0) {
				this.setOverlayVariant(this.getOverlayVariant() - 1);
				this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
				return InteractionResult.sidedSuccess(this.level().isClientSide);
			}
			this.setOverlayVariant(this.getOverlayVariant() + 1);
			this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
			return InteractionResult.sidedSuccess(this.level().isClientSide);
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

		if (itemstack.is(Items.BUCKET) && !this.isBaby()) {
			if (!wasMilked() || replenishMilkCounter >= LivestockOverhaulCommonConfig.MILKING_COOLDOWN.get()) {
				if ((!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get()) || (LivestockOverhaulCommonConfig.GENDERS_AFFECT_BIPRODUCTS.get() && this.isFemale())) {
					player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
					ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, LOItems.LLAMA_MILK_BUCKET.get().getDefaultInstance());
					player.setItemInHand(hand, itemstack1);
					replenishMilkCounter = 0;
					setMilked(true);
				}
				return InteractionResult.sidedSuccess(this.level().isClientSide);
			}
		}

		return super.mobInteract(player, hand);
	}

	@Override
	public void dropEquipment() {
		if(!this.level().isClientSide) {
			super.dropEquipment();
			if(this.isChested()) {
				this.spawnAtLocation(Items.CHEST);
			}
			Containers.dropContents(this.level(), this, this.inventory);
		}
	}

	// Generates the base texture
	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OLlama.class, EntityDataSerializers.INT);
	public ResourceLocation getTextureLocation() {
		return OLlamaModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
	}
	public int getVariant() {
		return this.entityData.get(VARIANT);
	}
	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
	}


	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(OLlama.class, EntityDataSerializers.INT);
	public ResourceLocation getOverlayLocation() {return OLlamaMarkingLayer.Overlay.overlayFromOrdinal(getOverlayVariant()).resourceLocation;}
	public int getOverlayVariant() {
		return this.entityData.get(OVERLAY);
	}
	public void setOverlayVariant(int overlayVariant) {
		this.entityData.set(OVERLAY, overlayVariant);
	}

	public static final EntityDataAccessor<Boolean> MILKED = SynchedEntityData.defineId(OLlama.class, EntityDataSerializers.BOOLEAN);
	public boolean wasMilked() {
		return this.entityData.get(MILKED);
	}
	public void setMilked(boolean milked) {
		this.entityData.set(MILKED, milked);
	}

	public enum Wooly {
		NONE,
		WOOLY
	}
	public static final EntityDataAccessor<Integer> WOOLY = SynchedEntityData.defineId(OLlama.class, EntityDataSerializers.INT);
	public int getWooly() {
		return this.entityData.get(WOOLY);
	}
	public void setWooly(int wooly) {
		this.entityData.set(WOOLY, wooly);
	}

	public static final EntityDataAccessor<Boolean> SHEARED = SynchedEntityData.defineId(OLlama.class, EntityDataSerializers.BOOLEAN);
	public boolean isSheared() {
		return this.entityData.get(SHEARED);
	}
	public void setSheared(boolean sheared) {
		this.entityData.set(SHEARED, sheared);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);

		if (tag.contains("Variant")) {
			setVariant(tag.getInt("Variant"));
		}

		if (tag.contains("Overlay")) {
			setOverlayVariant(tag.getInt("Overlay"));
		}

		if (tag.contains("Chested")) {
			this.setChested(tag.getBoolean("Chested"));
		}

		this.setStrength(tag.getInt("Strength"));

		if (tag.contains("DecorItem", 10)) {
			this.inventory.setItem(1, ItemStack.of(tag.getCompound("DecorItem")));
		}

		if (tag.contains("Gender")) {
			this.setGender(tag.getInt("Gender"));
		}

		if (tag.contains("Wooly")) {
			this.setWooly(tag.getInt("Wooly"));
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

		this.updateContainerEquipment();
		this.updateInventory();
		super.readAdditionalSaveData(tag);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Variant", getVariant());
		tag.putInt("Overlay", getOverlayVariant());
		tag.putBoolean("Chested", this.isChested());
		tag.putInt("Strength", this.getStrength());
		tag.putInt("Gender", this.getGender());
		tag.putInt("Wooly", this.getWooly());
		tag.putBoolean("Milked", this.wasMilked());
		tag.putInt("MilkedTime", this.replenishMilkCounter);
		tag.putBoolean("Sheared", this.isSheared());
		tag.putInt("ShearedTime", this.regrowWoolCounter);

		if (!this.inventory.getItem(1).isEmpty()) {
			tag.put("DecorItem", this.inventory.getItem(1).save(new CompoundTag()));
		}
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}
		Random random = new Random();

		this.spawnByBiome(this.level().getBiome(this.getOnPos()));
		setVariant(random.nextInt(OLlamaModel.Variant.values().length));
		setOverlayVariant(random.nextInt(OLlamaMarkingLayer.Overlay.values().length));
		setWooly(random.nextInt(Wooly.values().length));
		this.setGender(random.nextInt(OLlama.Gender.values().length));
		this.setRandomStrength();

		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	public void spawnByBiome(Holder<Biome> biome) {
		Random random = new Random();

		if (biome.is(Tags.Biomes.IS_HOT_OVERWORLD)) {
			if (random.nextDouble() < 0.20) {
				this.setWooly(1);
			} else {
				this.setWooly(0);
			}
		}

	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
		this.entityData.define(GENDER, 0);
		this.entityData.define(WOOLY, 0);
		this.entityData.define(CHESTED, false);
		this.entityData.define(DATA_STRENGTH_ID, 0);
		this.entityData.define(DATA_SWAG_ID, -1);
		this.entityData.define(SHEARED, false);
		this.entityData.define(MILKED, false);
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
	public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(OLlama.class, EntityDataSerializers.INT);
	public int getGender() {
		return this.entityData.get(GENDER);
	}
	public void setGender(int gender) {
		this.entityData.set(GENDER, gender);
	}

	public boolean canParent() {
		return !this.isVehicle() && !this.isPassenger() && !this.isBaby() && this.isInLove();
	}

	public boolean canMate(Animal animal) {
		if (animal == this) {
			return false;
		} else if (!(animal instanceof OLlama)) {
			return false;
		} else {
			if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
				return this.canParent() && ((OLlama) animal).canParent();
			} else {
				OLlama partner = (OLlama) animal;
				if (this.canParent() && partner.canParent() && this.getGender() != partner.getGender()) {
					return isFemale();
				}
			}
		}
		return false;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		OLlama oLlama = (OLlama) ageableMob;
		if (ageableMob instanceof OLlama) {
			OLlama oLlama1 = (OLlama) ageableMob;
			oLlama = EntityTypes.O_LLAMA_ENTITY.get().create(serverLevel);

			int i = this.random.nextInt(9);
			int variant;
			if (i < 4) {
				variant = this.getVariant();
			} else if (i < 8) {
				variant = oLlama1.getVariant();
			} else {
				int variantCount = OLlamaModel.Variant.values().length;
				variant = variantCount > 0 ? this.random.nextInt(variantCount) : 0;
			}

			int j = this.random.nextInt(5);
			int overlay;
			if (j < 2) {
				overlay = this.getOverlayVariant();
			} else if (j < 4) {
				overlay = oLlama1.getOverlayVariant();
			} else {
				int overlayCount = OLlamaMarkingLayer.Overlay.values().length;
				overlay = overlayCount > 0 ? this.random.nextInt(overlayCount) : 0;
			}

			int k = this.random.nextInt(Math.max(this.getStrength(), oLlama1.getStrength()) + 1);
			if (this.random.nextFloat() < 0.03F) {
				++k;
			}

			oLlama.setStrength(k);
			oLlama.setVariant(variant);
			oLlama.setOverlayVariant(overlay);
			oLlama.setGender(random.nextInt(Gender.values().length));
		}

		return oLlama;
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


		if (this.getWooly() == 1) {
			if (!LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
				if (this.getVariant() == 0) {
					if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(LOItems.BLACK_WOOL_STAPLE.get(), 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(LOItems.BLACK_WOOL_STAPLE.get(), 1);
					}
				}
				if (this.getVariant() == 1 || this.getVariant() == 3) {
					if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(LOItems.GREY_WOOL_STAPLE.get(), 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(LOItems.GREY_WOOL_STAPLE.get(), 1);
					}
				}
				if (this.getVariant() == 2 || this.getVariant() == 6 || this.getVariant() == 7) {
					if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(LOItems.BROWN_WOOL_STAPLE.get(), 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(LOItems.BROWN_WOOL_STAPLE.get(), 1);
					}
				}
				if (this.getVariant() == 4) {
					if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(LOItems.LIGHT_GREY_WOOL_STAPLE.get(), 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(LOItems.LIGHT_GREY_WOOL_STAPLE.get(), 1);
					}
				}
				if (this.getVariant() == 6 || this.getVariant() == 8) {
					if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(LOItems.WHITE_WOOL_STAPLE.get(), 1);
					}
				}
				//if vanilla loot
			} else {
				if (this.getVariant() == 0) {
					if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(Items.BLACK_WOOL, 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(Items.BLACK_WOOL, 1);
					}
				}
				if (this.getVariant() == 1 || this.getVariant() == 3) {
					if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(Items.GRAY_WOOL, 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(Items.GRAY_WOOL, 1);
					}
				}
				if (this.getVariant() == 2 || this.getVariant() == 6 || this.getVariant() == 7) {
					if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(Items.BROWN_WOOL, 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(Items.BROWN_WOOL, 1);
					}
				}
				if (this.getVariant() == 4) {
					if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(Items.LIGHT_GRAY_WOOL, 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(Items.LIGHT_GRAY_WOOL, 1);
					}
				}
				if (this.getVariant() == 6 || this.getVariant() == 8) {
					if (random.nextDouble() > 0.20 && random.nextDouble() < 0.50) {
						this.spawnAtLocation(Items.WHITE_WOOL, 2);
					} else if (random.nextDouble() > 0.50) {
						this.spawnAtLocation(Items.WHITE_WOOL, 1);
					}
				}
			}


		}

	}

	@Override
	public boolean isChestable() {
		return this.isAlive() && !this.isBaby();
	}

	@Override
	public void equipChest(@Nullable SoundSource soundSource) {
		if(soundSource != null) {
			this.level().playSound(null, this, SoundEvents.MULE_CHEST, soundSource, 0.5f, 1f);
		}
	}

	@Override
	public boolean isChested() {
		return this.entityData.get(CHESTED);
	}

	public void setChested(boolean chested) {
		this.entityData.set(CHESTED, chested);
	}

	public void setStrength(int p_30841_) {
		this.entityData.set(DATA_STRENGTH_ID, Math.max(1, Math.min(5, p_30841_)));
	}

	public void setRandomStrength() {
		int i = this.random.nextFloat() < 0.04F ? 5 : 3;
		this.setStrength(1 + this.random.nextInt(i));
	}

	public int getStrength() {
		return this.entityData.get(DATA_STRENGTH_ID);
	}

	public int getInventorySize() {
		return this.hasChest() ? 2 + 3 * this.getInventoryColumns() : super.getInventorySize();
	}

	public double getPassengersRidingOffset() {
		return (double)this.getBbHeight() * 0.6D;
	}

	public boolean isFood(ItemStack p_30832_) {
		return FOOD_ITEMS.test(p_30832_);
	}

	@Override
	public boolean handleEating(Player player, ItemStack stack) {
		int i = 0;
		int j = 0;
		float f = 0.0F;
		boolean flag = false;
		if (stack.is(LOTags.Items.O_LLAMA_EATS)) {
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


	public boolean isImmobile() {
		return this.isDeadOrDying() || this.isEating();
	}

	public SoundEvent getAngrySound() {
		return SoundEvents.LLAMA_ANGRY;
	}

	public SoundEvent getAmbientSound() {
		return SoundEvents.LLAMA_AMBIENT;
	}

	public SoundEvent getHurtSound(DamageSource p_30803_) {
		return SoundEvents.LLAMA_HURT;
	}

	public SoundEvent getDeathSound() {
		return SoundEvents.LLAMA_DEATH;
	}

	@Nullable
	public SoundEvent getEatingSound() {
		return SoundEvents.LLAMA_EAT;
	}

	public void playStepSound(BlockPos p_30790_, BlockState p_30791_) {
		this.playSound(SoundEvents.LLAMA_STEP, 0.15F, 1.0F);
	}

	public void playChestEquipsSound() {
		this.playSound(SoundEvents.LLAMA_CHEST, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
	}

	public void makeMad() {
		SoundEvent soundevent = this.getAngrySound();
		if (soundevent != null) {
			this.playSound(soundevent, this.getSoundVolume(), this.getVoicePitch());
		}

	}

	public int getInventoryColumns() {
		return this.getStrength();
	}

	public boolean canWearArmor() {
		return true;
	}

	public boolean isWearingArmor() {
		return !this.inventory.getItem(1).isEmpty();
	}

	public boolean isArmor(ItemStack p_30834_) {
		return p_30834_.is(ItemTags.WOOL_CARPETS);
	}

	public boolean isSaddleable() {
		return false;
	}

	public void containerChanged(Container p_30760_) {
		DyeColor dyecolor = this.getSwag();
		super.containerChanged(p_30760_);
		DyeColor dyecolor1 = this.getSwag();
		if (this.tickCount > 20 && dyecolor1 != null && dyecolor1 != dyecolor) {
			this.playSound(SoundEvents.LLAMA_SWAG, 0.5F, 1.0F);
		}

	}

	public void updateContainerEquipment() {
		if (!this.level().isClientSide) {
			super.updateContainerEquipment();
			this.setSwag(getDyeColor(this.inventory.getItem(1)));
		}
	}

	public void setSwag(@Nullable DyeColor p_30772_) {
		this.entityData.set(DATA_SWAG_ID, p_30772_ == null ? -1 : p_30772_.getId());
	}

	@Nullable
	public static DyeColor getDyeColor(ItemStack p_30836_) {
		Block block = Block.byItem(p_30836_.getItem());
		return block instanceof WoolCarpetBlock ? ((WoolCarpetBlock)block).getColor() : null;
	}

	@Nullable
	public DyeColor getSwag() {
		int i = this.entityData.get(DATA_SWAG_ID);
		return i == -1 ? null : DyeColor.byId(i);
	}

	public int getMaxTemper() {
		return 30;
	}
	public void spit(LivingEntity p_30828_) {
		OLlamaSpit llamaspit = new OLlamaSpit(this.level(), this);
		double d0 = p_30828_.getX() - this.getX();
		double d1 = p_30828_.getY(0.3333333333333333D) - llamaspit.getY();
		double d2 = p_30828_.getZ() - this.getZ();
		double d3 = Math.sqrt(d0 * d0 + d2 * d2) * (double)0.2F;
		llamaspit.shoot(d0, d1 + d3, d2, 1.5F, 10.0F);
		if (!this.isSilent()) {
			this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.LLAMA_SPIT, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
		}

		this.level().addFreshEntity(llamaspit);
		this.didSpit = true;
	}

	void setDidSpit(boolean p_30753_) {
		this.didSpit = p_30753_;
	}

	public boolean causeFallDamage(float p_149538_, float p_149539_, DamageSource p_149540_) {
		int i = this.calculateFallDamage(p_149538_, p_149539_);
		if (i <= 0) {
			return false;
		} else {
			if (p_149538_ >= 6.0F) {
				this.hurt(p_149540_, (float)i);
				if (this.isVehicle()) {
					for(Entity entity : this.getIndirectPassengers()) {
						entity.hurt(p_149540_, (float)i);
					}
				}
			}

			this.playBlockFallSound();
			return true;
		}
	}

	public void leaveCaravan() {
		if (this.caravanHead != null) {
			this.caravanHead.caravanTail = null;
		}

		this.caravanHead = null;
	}

	public void joinCaravan(OLlama p_30767_) {
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
	public OLlama getCaravanHead() {
		return this.caravanHead;
	}

	public double followLeashSpeed() {
		return 2.0D;
	}

	public void followMommy() {
		if (!this.inCaravan() && this.isBaby()) {
			super.followMommy();
		}

	}

	public boolean canEatGrass() {
		return false;
	}

	public void performRangedAttack(LivingEntity p_30762_, float p_30763_) {
		this.spit(p_30762_);
	}

	public Vec3 getLeashOffset() {
		return new Vec3(0.0D, 0.75D * (double)this.getEyeHeight(), (double)this.getBbWidth() * 0.5D);
	}

	static class LlamaAttackWolfGoal extends NearestAttackableTargetGoal<Wolf> {
		public LlamaAttackWolfGoal(OLlama p_30843_) {
			super(p_30843_, Wolf.class, 16, false, true, (p_30845_) -> {
				return !((Wolf)p_30845_).isTame();
			});
		}

		public double getFollowDistance() {
			return super.getFollowDistance() * 0.25D;
		}
	}

	static class LlamaHurtByTargetGoal extends HurtByTargetGoal {
		public LlamaHurtByTargetGoal(OLlama p_30854_) {
			super(p_30854_);
		}

		public boolean canContinueToUse() {
			if (this.mob instanceof OLlama) {
				OLlama llama = (OLlama)this.mob;
				if (llama.didSpit) {
					llama.setDidSpit(false);
					return false;
				}
			}

			return super.canContinueToUse();
		}
	}

	static class LlamaGroupData extends AgeableMobGroupData {
		public final int variant;

		LlamaGroupData(int p_30849_) {
			super(true);
			this.variant = p_30849_;
		}
	}
}
