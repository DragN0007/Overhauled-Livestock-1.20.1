package com.dragn0007.dragnlivestock.entities.camel;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.client.event.LivestockOverhaulClientEvent;
import com.dragn0007.dragnlivestock.common.gui.OCamelMenu;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.ai.GroundTieGoal;
import com.dragn0007.dragnlivestock.entities.ai.OCamelFollowCaravanGoal;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.entities.util.LOAnimations;
import com.dragn0007.dragnlivestock.entities.util.Taggable;
import com.dragn0007.dragnlivestock.items.custom.BrandTagItem;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
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

public class OCamel extends AbstractOMount implements GeoEntity, Taggable {

	public OCamel(EntityType<? extends OCamel> type, Level level) {
		super(type, level);
	}

	protected static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_camel");
	protected static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/camel");
	protected static final ResourceLocation TFC_LOOT_TABLE = new ResourceLocation("tfc", "entities/camel");
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
				return new OCamelMenu(containerId, inventory, this.inventory, this);
			}, this.getDisplayName()), (data) -> {
				data.writeInt(this.getInventorySize());
				data.writeInt(this.getId());
			});
		}
	}

	@Override
	public int getMaxHeadXRot() {
		return 40;
	}

	@Override
	public int getMaxHeadYRot() {
		return 60;
	}

	@Nullable
	public OCamel caravanHead;
	@Nullable
	public OCamel caravanTail;

	public void leaveCaravan() {
		if (this.caravanHead != null) {
			this.caravanHead.caravanTail = null;
		}

		this.caravanHead = null;
	}

	public void joinCaravan(OCamel p_30767_) {
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
	public OCamel getCaravanHead() {
		return this.caravanHead;
	}

	public double followLeashSpeed() {
		return 1.6D;
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

	public static final Ingredient FOOD_ITEMS = Ingredient.of(Items.DEAD_BUSH, Items.CACTUS, Blocks.HAY_BLOCK.asItem());

	@Override
	public boolean isFood(ItemStack stack) {
		return FOOD_ITEMS.test(stack);
	}

	@Override
	public boolean canPerformRearing() {
		return false;
	}

	@Override
	public boolean canJump() {
		return false;
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
		this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D, OCamel.class));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(5, new TemptGoal(this, 1.25D, Ingredient.of(Items.CACTUS), false));
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Wolf.class, false));
		this.goalSelector.addGoal(2, new OCamelFollowCaravanGoal(this, (double)1.5F));

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
		return 25.0F + (float)this.random.nextInt(8) + (float)this.random.nextInt(9);
	}

	public double generateRandomJumpStrength() {
		return (double)0.0F + this.random.nextDouble() * 0.1D + this.random.nextDouble() * 0.1D + this.random.nextDouble() * 0.1D;
	}

	public double generateRandomSpeed() {
		double randomSpeed = (0.20 + this.random.nextDouble() * 0.1D + this.random.nextDouble() * 0.1D + this.random.nextDouble() * 0.1D) * 0.25D;
		return Math.max(randomSpeed, 0.20);
	}

	protected final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	protected <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
		double currentSpeed = this.getDeltaMovement().lengthSqr();
		double speedThreshold = 0.02;

		double x = this.getX() - this.xo;
		double z = this.getZ() - this.zo;

		boolean isMoving = (x * x + z * z) > 0.0001;

		double movementSpeed = this.getAttributeBaseValue(Attributes.MOVEMENT_SPEED);
		double animationSpeed = Math.max(0.1, movementSpeed);

		AnimationController<T> controller = tAnimationState.getController();

		if (isMoving) {
			if (LivestockOverhaulClientEvent.HORSE_WALK_BACKWARDS.isDown()) {
				if (this.isAggressive() || (this.isVehicle() && this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD)) || (!this.isVehicle() && currentSpeed > speedThreshold)) {
					controller.setAnimation(RawAnimation.begin().then("trot_sprint", Animation.LoopType.LOOP));
					controller.setAnimationSpeed(Math.max(0.1, 0.84 * controller.getAnimationSpeed() + animationSpeed));

				} else if (this.isVehicle() && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(WALK_SPEED_MOD) && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD)) {
					controller.setAnimation(RawAnimation.begin().then("trot", Animation.LoopType.LOOP));
					controller.setAnimationSpeed(Math.max(0.1, 0.78 * controller.getAnimationSpeed() + animationSpeed));

				} else if (this.isOnSand() && this.isVehicle() && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(WALK_SPEED_MOD) && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPRINT_SPEED_MOD)) {
					controller.setAnimation(RawAnimation.begin().then("trot", Animation.LoopType.LOOP));
					controller.setAnimationSpeed(Math.max(0.1, 0.88 * controller.getAnimationSpeed() + animationSpeed));

				} else if (this.isVehicle() && this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(WALK_SPEED_MOD)) {
					controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
					controller.setAnimationSpeed(Math.max(0.1, 0.82 * controller.getAnimationSpeed() + animationSpeed));

				} else if (this.isOnSand() && this.isVehicle() && this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(WALK_SPEED_MOD)) {
					controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
					controller.setAnimationSpeed(Math.max(0.1, 0.88 * controller.getAnimationSpeed() + animationSpeed));

				} else {
					controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
					controller.setAnimationSpeed(Math.max(0.1, 0.82 * controller.getAnimationSpeed() + animationSpeed));
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
			if (this.isGroundTied()) {
				controller.setAnimation(RawAnimation.begin().then("ground_tie", Animation.LoopType.LOOP));
			} else {
				controller.setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
			}
			controller.setAnimationSpeed(1.0);
		}

		return PlayState.CONTINUE;
	}


	protected <T extends GeoAnimatable> PlayState emotePredicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
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

	public int filledHumpsTime = this.random.nextInt(6000) + 6000;

	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		Item item = itemstack.getItem();

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

		if (itemstack.is(Items.SHEARS) && player.isShiftKeyDown()) {
			if (this.isTagged()) {
				this.setTagged(false);
				this.playSound(SoundEvents.SHEEP_SHEAR, 0.5f, 1f);
				return InteractionResult.sidedSuccess(this.level().isClientSide);
			}
		}

		if (itemstack.isEmpty() && this.canAddPassenger(this) && !player.isCrouching()) {
			this.doPlayerRide(player);
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		}

		if(this.isVehicle()) {
			if (this.canAddPassenger(this)) {
				this.doPlayerRide(player);
				return InteractionResult.sidedSuccess(this.level().isClientSide);
			}
			return super.mobInteract(player, hand);
		}

		if (itemstack.is(Items.BUCKET) && !this.isBaby() && --this.filledHumpsTime <= 0) {
			player.playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);
			ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, Items.WATER_BUCKET.getDefaultInstance());
			player.setItemInHand(hand, itemstack1);
			this.filledHumpsTime = this.random.nextInt(6000) + 6000;
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		}
		return super.mobInteract(player, hand);
	}

	@Override
	public boolean handleEating(Player player, ItemStack stack) {
		int i = 0;
		int j = 0;
		float f = 0.0F;
		boolean flag = false;
		if (stack.is(LOTags.Items.O_CAMEL_EATS)) {
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

	@Override
	public boolean isArmor(ItemStack itemStack) {
		return itemStack.is(ItemTags.WOOL_CARPETS) || itemStack.is(LOTags.Items.CAMEL_ARMOR);
	}

	@Override
	public boolean hurt(DamageSource damageSource, float v) {
		if (damageSource.is(DamageTypes.CACTUS)) {
			return false;
		}
		return super.hurt(damageSource, v);
	}

	@Override
	public void tick() {
		super.tick();

		if (this.isOnSand()) {
			if (!this.hasSpeedEffect()) {
				this.applySpeedEffect();
			}
		} else {
			if (this.hasSpeedEffect()) {
				this.removeSpeedEffect();
			}
		}

		if (this.isOnGrass()) {
			if (!this.hasSlownessEffect()) {
				this.applySlownessEffect();
			}
		} else {
			if (this.hasSlownessEffect()) {
				this.removeSlownessEffect();
			}
		}
	}

	public boolean isOnSand() {
		BlockState blockState = this.level().getBlockState(this.blockPosition().below());
		return blockState.is(LOTags.Blocks.SAND) || blockState.is(Blocks.COARSE_DIRT) || blockState.is(Blocks.GRAVEL);
	}

	protected void applySpeedEffect() {
		MobEffectInstance speedEffectInstance = new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0, false, false);
		this.addEffect(speedEffectInstance);
	}

	protected boolean hasSpeedEffect() {
		return this.hasEffect(MobEffects.MOVEMENT_SPEED);
	}

	protected void removeSpeedEffect() {
		this.removeEffect(MobEffects.MOVEMENT_SPEED);
	}

	public boolean isOnGrass() {
		BlockState blockState = this.level().getBlockState(this.blockPosition().below());
		return blockState.is(LOTags.Blocks.DIRT);
	}

	public void applySlownessEffect() {
		MobEffectInstance slownessEffectInstance = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1, false, false);
		this.addEffect(slownessEffectInstance);
	}

	public boolean hasSlownessEffect() {
		return this.hasEffect(MobEffects.MOVEMENT_SLOWDOWN);
	}

	public void removeSlownessEffect() {
		this.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
	}

	public Vec3 calcOffset ( double x, double y, double z){
		double rad = this.getYRot() * Math.PI / 180;

		double xOffset = this.position().x + (x * Math.cos(rad) - z * Math.sin(rad));
		double yOffset = this.position().y + y;
		double zOffset = this.position().z + (x * Math.sin(rad) + z * Math.cos(rad));

		return new Vec3(xOffset, yOffset, zOffset);
	}

	@Override
	public boolean canAddPassenger(Entity entity) {
		if (this.getBreed() == 0) {
			return this.getPassengers().size() < 1;
		} else if (this.getBreed() == 1) {
			return this.getPassengers().size() < 2;
		} else {
			return false;
		}
	}

	@Override
	public void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
		int i = this.getPassengers().indexOf(entity);
		if(getBreed() == 0) {
			switch (i) {
				case 0:
					entity.setPos(this.calcOffset(0, 1.7, -0.1));
					break;
			}
		}

		if(getBreed() == 1) {
			switch (i) {
				case 0:
					entity.setPos(this.calcOffset(0, 2.25, -0.2));
					break;
				case 1:
					entity.setPos(this.calcOffset(0, 1.65, -1.0));
					break;
			}
		}
	}

	@Override
	public LivingEntity getControllingPassenger() {
		if (this.isTamed() && this.isSaddled()) {
			return (LivingEntity) this.getFirstPassenger();
		}
		return null;
	}

	@Override
	public SoundEvent getAmbientSound() {
		super.getAmbientSound();
		return SoundEvents.CAMEL_AMBIENT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return SoundEvents.CAMEL_DEATH;
	}

	@Nullable
	@Override
	public SoundEvent getEatingSound() {
		return SoundEvents.CAMEL_EAT;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource damageSource) {
		super.getHurtSound(damageSource);
		return SoundEvents.CAMEL_HURT;
	}

	@Override
	public SoundEvent getAngrySound() {
		super.getAngrySound();
		return SoundEvents.CAMEL_HURT;
	}


	public static final EntityDataAccessor<Integer> BREED = SynchedEntityData.defineId(OCamel.class, EntityDataSerializers.INT);
	public ResourceLocation getModelResource() {
		return CamelBreed.Breed.breedFromOrdinal(getBreed()).resourceLocation;
	}
	public int getBreed() {
		return this.entityData.get(BREED);
	}
	public void setBreed(int breed) {
		this.entityData.set(BREED, breed);
	}


	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OCamel.class, EntityDataSerializers.INT);
	public int getVariant() {
		return this.entityData.get(VARIANT);
	}
	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
		this.entityData.set(VARIANT_TEXTURE, OCamelModel.Variant.variantFromOrdinal(variant).resourceLocation.toString());
	}
	public static final EntityDataAccessor<String> VARIANT_TEXTURE = SynchedEntityData.defineId(OCamel.class, EntityDataSerializers.STRING);
	public String getTextureResource() {
		return this.entityData.get(VARIANT_TEXTURE);
	}
	public void setVariantTexture(String variant) {
		this.entityData.set(VARIANT_TEXTURE, variant);
	}

	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(OCamel.class, EntityDataSerializers.INT);
	public int getOverlayVariant() {
		return this.entityData.get(OVERLAY);
	}
	public void setOverlayVariant(int variant) {
		this.entityData.set(OVERLAY, variant);
		this.entityData.set(OVERLAY_TEXTURE, OCamelMarkingLayer.Overlay.overlayFromOrdinal(variant).resourceLocation.toString());
	}
	public static final EntityDataAccessor<String> OVERLAY_TEXTURE = SynchedEntityData.defineId(OCamel.class, EntityDataSerializers.STRING);
	public String getOverlayLocation() {
		return this.entityData.get(OVERLAY_TEXTURE);
	}
	public void setOverlayVariantTexture(String variant) {
		this.entityData.set(OVERLAY_TEXTURE, variant);
	}

	public enum Mane {
		NONE,
		HALF,
		FULL;
		public OCamel.Mane next() {
			return OCamel.Mane.values()[(this.ordinal() + 1) % OCamel.Mane.values().length];
		}
	}
	public static final EntityDataAccessor<Integer> MANE = SynchedEntityData.defineId(OCamel.class, EntityDataSerializers.INT);
	public int getMane() {
		return this.entityData.get(MANE);
	}
	public void setMane(int feathering) {
		this.entityData.set(MANE, feathering);
	}

	protected static final EntityDataAccessor<Integer> BRAND_TAG_COLOR = SynchedEntityData.defineId(OCamel.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> TAGGED = SynchedEntityData.defineId(OCamel.class, EntityDataSerializers.BOOLEAN);
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
	public void equipTag(@javax.annotation.Nullable SoundSource soundSource) {
		if(soundSource != null) {
			this.level().playSound(null, this, SoundEvents.BOOK_PAGE_TURN, soundSource, 0.5f, 1f);
		}
	}

	@Override
	public void updateContainerEquipment() {
		if(!this.level().isClientSide) {
			this.setSaddleItem(this.inventory.getItem(this.saddleSlot()));
			this.setArmorEquipment(this.inventory.getItem(this.armorSlot()));
			this.setDecorItem(this.inventory.getItem(this.decorSlot()));
		}
	}

	@Override
	public int decorSlot() {
		return 1;
	}

	@Override
	public int armorSlot() {
		return 1;
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

		if (tag.contains("Variant_Texture")) {
			this.setVariantTexture(tag.getString("Variant_Texture"));
		}

		if (tag.contains("Overlay_Texture")) {
			this.setOverlayVariantTexture(tag.getString("Overlay_Texture"));
		}

		if (tag.contains("FilledHumpsTime")) {
			this.filledHumpsTime = tag.getInt("FilledHumpsTime");
		}

		if (tag.contains("Gender")) {
			this.setGender(tag.getInt("Gender"));
		}

		if(tag.contains("Tagged")) {
			this.setTagged(tag.getBoolean("Tagged"));
		}

		if (tag.contains("Mane")) {
			this.setMane(tag.getInt("Mane"));
		}


		this.setBrandTagColor(DyeColor.byId(tag.getInt("BrandTagColor")));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Breed", this.getBreed());
		tag.putInt("Variant", this.getVariant());
		tag.putInt("Overlay", this.getOverlayVariant());
		tag.putString("Variant_Texture", this.getTextureResource().toString());
		tag.putString("Overlay_Texture", this.getOverlayLocation().toString());
		tag.putInt("FilledHumpsTime", this.filledHumpsTime);
		tag.putInt("Gender", this.getGender());
		tag.putInt("Mane", this.getMane());
		tag.putBoolean("Tagged", this.isTagged());
		tag.putByte("BrandTagColor", (byte)this.getBrandTagColor().getId());
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}
		Random random = new Random();
		this.setVariant(random.nextInt(OCamelModel.Variant.values().length));
		this.setOverlayVariant(random.nextInt(OCamelMarkingLayer.Overlay.values().length));
		this.setGender(random.nextInt(Gender.values().length));
		this.setBreed(random.nextInt(CamelBreed.Breed.values().length));

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
		this.entityData.define(BREED, 0);
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
		this.entityData.define(VARIANT_TEXTURE, OCamelModel.Variant.DESERT.resourceLocation.toString());
		this.entityData.define(OVERLAY_TEXTURE, OCamelMarkingLayer.Overlay.NONE.resourceLocation.toString());
		this.entityData.define(GENDER, 0);
		this.entityData.define(MANE, 0);
		this.entityData.define(BRAND_TAG_COLOR, DyeColor.YELLOW.getId());
		this.entityData.define(TAGGED, false);
	}

	public boolean canMate(Animal animal) {
		if (animal == this) {
			return false;
		} else if (!(animal instanceof OCamel)) {
			return false;
		} else {
			if (!this.isSnipped() && !((AbstractOMount) animal).isSnipped()) {
				if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
					return this.canParent() && ((AbstractOMount) animal).canParent();
				} else {
					AbstractOMount partner = (AbstractOMount) animal;
					if (this.canParent() && partner.canParent() && this.getGender() != partner.getGender()) {
						return this.isFemale();
					}
				}
			}
		}
		return false;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		OCamel calf = (OCamel) ageableMob;
		if (ageableMob instanceof OCamel) {
			OCamel partnerCamel = (OCamel) ageableMob;
			calf = EntityTypes.O_CAMEL_ENTITY.get().create(serverLevel);

			int i = this.random.nextInt(9);
			int variant;
			if (i < 4) {
				variant = this.getVariant();
			} else if (i < 8) {
				variant = partnerCamel.getVariant();
			} else {
				variant = this.random.nextInt(OCamelModel.Variant.values().length);
			}

			int j = this.random.nextInt(5);
			int overlay;
			if (j < 2) {
				overlay = this.getOverlayVariant();
			} else if (j < 4) {
				overlay = partnerCamel.getOverlayVariant();
			} else {
				overlay = this.random.nextInt(OCamelMarkingLayer.Overlay.values().length);
			}

			int k = this.random.nextInt(5);
			int breed;
			if (k < 2) {
				breed = this.getBreed();
			} else if (k < 4) {
				breed = partnerCamel.getBreed();
			} else {
				breed = this.random.nextInt(CamelBreed.Breed.values().length);
			}

			calf.setVariant(variant);
			calf.setOverlayVariant(overlay);
			calf.setGender(random.nextInt(Gender.values().length));
			calf.setBreed(breed);
		}

		return calf;
	}

}
