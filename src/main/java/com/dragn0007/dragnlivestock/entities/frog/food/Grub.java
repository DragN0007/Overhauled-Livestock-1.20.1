package com.dragn0007.dragnlivestock.entities.frog.food;

import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.items.custom.GrubSweaterItem;
import com.dragn0007.dragnlivestock.util.LOTags;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
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

public class Grub extends Animal implements GeoEntity {

	public Grub(EntityType<? extends Grub> type, Level level) {
		super(type, level);
	}

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0D, (double)this.getEyeHeight() * 0.8F, (double)(this.getBbWidth() * 0.9F));
		//              ^ Side offset                      ^ Height offset                   ^ Length offset
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 2.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.14D);
	}

	protected boolean hasSweater = false;

	public boolean hasSweater() {
		return this.hasSweater;
	}

	public boolean removeWhenFarAway(double v) {
		return !this.hasSweater();
	}

	@Override
	public @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand hand) {
		ItemStack itemStack = player.getItemInHand(hand);
		Item item = itemStack.getItem();

		if (itemStack.is(LOItems.COAT_OSCILLATOR.get()) && player.getAbilities().instabuild) {
			if (player.isShiftKeyDown()) {
				if (this.getVariant() > 0) {
					this.setVariant(this.getVariant() - 1);
					this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
					return InteractionResult.SUCCESS;
				}
			}
			this.setVariant(this.getVariant() + 1);
			this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
			return InteractionResult.SUCCESS;
		}

		if (item instanceof GrubSweaterItem && !this.isSweatered()) {
			GrubSweaterItem dyeitem = (GrubSweaterItem) item;
			this.setSweatered(true);
			DyeColor dyecolor = dyeitem.getDyeColor();
			if (dyecolor != this.getSweaterColor()) {
				this.setSweaterColor(dyecolor);
				if (!player.getAbilities().instabuild) {
					itemStack.shrink(1);
				}
				this.playSound(SoundEvents.WOOL_BREAK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
				return InteractionResult.SUCCESS;
			}
			return super.mobInteract(player, hand);
		}

		if (itemStack.is(Items.SHEARS) && this.isSweatered()) {
			this.setSweatered(false);
			this.playSound(SoundEvents.SHEEP_SHEAR, 0.5f, 1f);
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		}

		return super.mobInteract(player, hand);
	}

	public static final Ingredient FOOD_ITEMS = Ingredient.of(LOTags.Items.GRUB_EATS);

	public void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, FOOD_ITEMS, false));
		this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
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
				controller.setAnimation(RawAnimation.begin().then("move", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(2.5);
			} else {
				controller.setAnimation(RawAnimation.begin().then("move", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(1.5);
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

	public SoundEvent getDeathSound() {
		super.getDeathSound();
		return SoundEvents.TROPICAL_FISH_FLOP;
	}

	public SoundEvent getHurtSound(DamageSource p_30720_) {
		super.getHurtSound(p_30720_);
		return SoundEvents.TROPICAL_FISH_FLOP;
	}

	public void playStepSound(BlockPos p_28254_, BlockState p_28255_) {
		this.playSound(SoundEvents.TROPICAL_FISH_FLOP, 0.15F, 1.0F);
	}

	public boolean isFood(ItemStack p_28271_) {
		return FOOD_ITEMS.test(p_28271_);
	}

	// Generates the base texture
	public ResourceLocation getTextureLocation() {
		return GrubModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
	}

	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Grub.class, EntityDataSerializers.INT);

	public int getVariant() {
		return this.entityData.get(VARIANT);
	}

	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
	}

	public static final EntityDataAccessor<Integer> DATA_SWEATER_COLOR = SynchedEntityData.defineId(Grub.class, EntityDataSerializers.INT);
	public DyeColor getSweaterColor() {
		return DyeColor.byId(this.entityData.get(DATA_SWEATER_COLOR));
	}
	public void setSweaterColor(DyeColor color) {
		this.entityData.set(DATA_SWEATER_COLOR, color.getId());
	}
	public static final EntityDataAccessor<Boolean> SWEATERED = SynchedEntityData.defineId(Grub.class, EntityDataSerializers.BOOLEAN);
	public boolean isSweatered() {
		return this.entityData.get(SWEATERED);
	}
	public void setSweatered(boolean collared) {
		this.entityData.set(SWEATERED, collared);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);

		if (tag.contains("Variant")) {
			setVariant(tag.getInt("Variant"));
		}

		if (tag.contains("SweaterColor", 99)) {
			this.setSweaterColor(DyeColor.byId(tag.getInt("SweaterColor")));
		}

		if(tag.contains("Sweatered")) {
			this.setSweatered(tag.getBoolean("Sweatered"));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Variant", getVariant());
		tag.putByte("SweaterColor", (byte)this.getSweaterColor().getId());
		tag.putBoolean("Sweatered", this.isSweatered());
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}
		Random random = new Random();
		setVariant(random.nextInt(GrubModel.Variant.values().length));

		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, 0);
		this.entityData.define(DATA_SWEATER_COLOR, DyeColor.RED.getId());
		this.entityData.define(SWEATERED, false);
	}

	public boolean canParent() {
		return !this.isBaby() && this.isInLove();
	}

	public boolean canMate(Animal animal) {
		if (animal == this) {
			return false;
		} else
			return animal instanceof Grub;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		Grub grub = (Grub) ageableMob;
		if (ageableMob instanceof Grub) {
			Grub grub1 = (Grub) ageableMob;
			grub = EntityTypes.GRUB_ENTITY.get().create(serverLevel);

			int i = this.random.nextInt(9);
			int variant;
			if (i < 4) {
				variant = this.getVariant();
			} else if (i < 8) {
				variant = grub1.getVariant();
			} else {
				variant = this.random.nextInt(GrubModel.Variant.values().length);
			}

			grub.setVariant(variant);
		}

		return grub;
	}

}
