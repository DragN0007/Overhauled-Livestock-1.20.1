package com.dragn0007.dragnlivestock.entities.llama;

import com.dragn0007.dragnlivestock.entities.Chestable;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.ai.LlamaFollowHerdLeaderGoal;
import com.dragn0007.dragnlivestock.entities.util.LOAnimations;
import com.dragn0007.dragnlivestock.items.LOItems;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WoolCarpetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.wrapper.InvWrapper;
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

	public static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT, Blocks.HAY_BLOCK.asItem());
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
		this.noCulling = true;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return createBaseHorseAttributes()
				.add(Attributes.MOVEMENT_SPEED, (double)0.20F)
				.add(Attributes.JUMP_STRENGTH, 0.5D)
				.add(Attributes.FOLLOW_RANGE, 40.0D);
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
	}

	public LazyOptional<?> itemHandler = null;
	public OLlama leader;
	public int herdSize = 1;

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	private <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
		double movementSpeed = this.getAttributeBaseValue(Attributes.MOVEMENT_SPEED);
		double animationSpeed = Math.max(0.1, movementSpeed);

		AnimationController<T> controller = tAnimationState.getController();

		if (tAnimationState.isMoving()) {
			if (isVehicle() || isAggressive() || isSprinting() || isSwimming()) {
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

	public int getMaxHerdSize() {
		return 3;
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

	@Override
	public void tick() {
		super.tick();

		if (this.hasFollowers() && this.level().random.nextInt(200) == 1) {
			List<? extends OLlama> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
			if (list.size() <= 1) {
				this.herdSize = 1;
			}
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
		if (itemstack.is(Items.BUCKET) && !this.isBaby()) {
			player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
			ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, LOItems.LLAMA_MILK_BUCKET.get().getDefaultInstance());
			player.setItemInHand(hand, itemstack1);
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		} else {
			return super.mobInteract(player, hand);
		}
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
	public ResourceLocation getTextureResource() {
		return OLlamaModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
	}

	public ResourceLocation getOverlayLocation() {
		return OLlamaMarkingLayer.Overlay.overlayFromOrdinal(getOverlayVariant()).resourceLocation;
	}

	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OLlama.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(OLlama.class, EntityDataSerializers.INT);

	public int getVariant() {
		return this.entityData.get(VARIANT);
	}

	public int getOverlayVariant() {
		return this.entityData.get(OVERLAY);
	}

	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
	}

	public void setOverlayVariant(int variant) {
		this.entityData.set(OVERLAY, variant);
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

		if (!this.inventory.getItem(1).isEmpty()) {
			tag.put("DecorItem", this.inventory.getItem(1).save(new CompoundTag()));
		}
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		this.setRandomStrength();
		int i;
		if (data instanceof LlamaGroupData) {
			i = ((LlamaGroupData)data).variant;
		} else {
			i = this.random.nextInt(8);
			data = new LlamaGroupData(i);
		}

		this.setVariant(i);

		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}
		Random random = new Random();
		setVariant(random.nextInt(OLlamaModel.Variant.values().length));
		setOverlayVariant(random.nextInt(OLlamaMarkingLayer.Overlay.values().length));

		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
		this.entityData.define(CHESTED, false);
		this.entityData.define(DATA_STRENGTH_ID, 0);
		this.entityData.define(DATA_SWAG_ID, -1);
	}

	public boolean canParent() {
		return !this.isVehicle() && !this.isPassenger() && !this.isBaby() && this.getHealth() >= this.getMaxHealth() && this.isInLove();
	}

	public boolean canMate(Animal animal) {
		if (animal == this) {
			return false;
		} else {
			return this.canParent() && ((OLlama) animal).canParent();
		}
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
				variant = this.random.nextInt(OLlamaModel.Variant.values().length);
			}

			int j = this.random.nextInt(5);
			int overlay;
			if (j < 2) {
				overlay = this.getOverlayVariant();
			} else if (j < 4) {
				overlay = oLlama1.getOverlayVariant();
			} else {
				overlay = this.random.nextInt(OLlamaMarkingLayer.Overlay.values().length);
			}

			int k = this.random.nextInt(Math.max(this.getStrength(), oLlama1.getStrength())) + 1;
			if (this.random.nextFloat() < 0.03F) {
				++k;
			}

			((OLlama) oLlama).setStrength(k);
			((OLlama) oLlama).setVariant(variant);
			((OLlama) oLlama).setOverlayVariant(overlay);
		}

		return oLlama;
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

	public boolean handleEating(Player p_30796_, ItemStack p_30797_) {
		int i = 0;
		int j = 0;
		float f = 0.0F;
		boolean flag = false;
		if (p_30797_.is(Items.WHEAT)) {
			i = 10;
			j = 3;
			f = 2.0F;
		} else if (p_30797_.is(Blocks.HAY_BLOCK.asItem())) {
			i = 90;
			j = 6;
			f = 10.0F;
			if (this.isTamed() && this.getAge() == 0 && this.canFallInLove()) {
				flag = true;
				this.setInLove(p_30796_);
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
					this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), this.getEatingSound(), this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
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
