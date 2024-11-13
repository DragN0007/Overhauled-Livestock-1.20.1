package com.dragn0007.dragnlivestock.entities.wagons.covered;

import com.dragn0007.dragnlivestock.items.LOItems;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class CoveredWagon extends Entity implements GeoEntity, ContainerListener {

	//To other devs looking in this Github:
	//This feature is not functional yet, as this code is unfinished.

	public CoveredWagon(EntityType<? extends CoveredWagon> type, Level level) {
		super(type, level);
		this.createInventory();
	}

	public static final EntityDataAccessor<Boolean> HITCHED = SynchedEntityData.defineId(CoveredWagon.class, EntityDataSerializers.BOOLEAN);

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
		AnimationController<T> controller = tAnimationState.getController();

		double xVelocity = this.getDeltaMovement().x;
		double zVelocity = this.getDeltaMovement().z;

		if (Math.abs(xVelocity) > 0.01 || Math.abs(zVelocity) > 0.01) {
			controller.setAnimation(RawAnimation.begin().then("drive", Animation.LoopType.LOOP));
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

	private AbstractHorse hitchedHorse;

	public boolean isHitchedToWagon() {
		return this.hitchedHorse != null;
	}

	public void hitchToHorse(AbstractHorse horse) {
		this.hitchedHorse = horse;
		this.entityData.set(HITCHED, true);
	}

	public void unHitch() {
		this.hitchedHorse = null;
		this.entityData.set(HITCHED, false);
	}

	@Override
	public void tick() {
		super.tick();

		if (this.isHitchedToWagon() && this.hitchedHorse != null) {
			float horseYaw = this.hitchedHorse.getYRot();
			this.setYRot(horseYaw);
			this.setYHeadRot(horseYaw);

			Vec3 horsePosition = this.hitchedHorse.position();
			double offsetX = 0.0;
			double offsetZ = -4.0;
			this.setPos(horsePosition.x + offsetX, horsePosition.y, horsePosition.z + offsetZ);

			if (this.getControllingPassenger() instanceof Player player) {
				if (player.isControlledByLocalInstance()) {
					this.hitchedHorse.setYRot(player.getYRot());
					Vec3 movement = new Vec3(player.xxa, 0, player.zza);
					this.hitchedHorse.move(MoverType.SELF, movement);
				}
			}
		}
	}

	private AbstractHorse findNearbyHorse(Player player) {
		AABB searchArea = new AABB(player.position().subtract(5, 5, 5), player.position().add(5, 5, 5));

		return this.level().getEntitiesOfClass(AbstractHorse.class, searchArea, EntitySelector.NO_SPECTATORS)
				.stream().filter(horse -> !horse.isPassenger()).findFirst().orElse(null);
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		ItemStack itemStack = player.getItemInHand(hand);

		if (!this.level().isClientSide) {
			if (itemStack.getItem() == Items.LEAD) {
				if (this.isHitchedToWagon()) {
					this.unHitch();
					return InteractionResult.CONSUME;
				} else {
					AbstractHorse horse = findNearbyHorse(player);
					if (horse != null) {
						this.hitchToHorse(horse);
						return InteractionResult.CONSUME;
					}
				}
			}

			if (player.isShiftKeyDown() && hand == InteractionHand.MAIN_HAND) {
				NetworkHooks.openScreen((ServerPlayer) player, new SimpleMenuProvider(
						(containerId, inventory, serverPlayer) -> new ChestMenu(MenuType.GENERIC_9x4, containerId, inventory, this.inventory, 4),
						this.getDisplayName()));
				return InteractionResult.CONSUME;
			}
		} else {
			return player.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.PASS;
		}

		return super.interact(player, hand);
	}


	// Generates the base texture
	public ResourceLocation getTextureResource() {
		return CoveredWagonModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
	}

	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(CoveredWagon.class, EntityDataSerializers.INT);

	public int getVariant() {
		return this.entityData.get(VARIANT);
	}

	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
	}

	@Override
	protected boolean canAddPassenger(Entity entity) {
		return this.getPassengers().size() < 4;
	}

	@Override
	public void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
		int i = this.getPassengers().indexOf(entity);
		switch (i) {
			case 0:
				entity.setPos(this.calcOffset(0.0, 0.8, 1.2));
				break;
			case 1:
				entity.setPos(this.calcOffset(-0.6, 0.3, -0.2));
				break;
			case 2:
				entity.setPos(this.calcOffset(0.6, 0.3, -2.0));
				break;
			case 3:
				entity.setPos(this.calcOffset(-0.6, 0.3, -2.0));
				break;
		}
	}
	// 0 = Driver

	// 0 | 1
	// 2 | 3


	private static final EntityDataAccessor<Float> HEALTH = SynchedEntityData.defineId(CoveredWagon.class, EntityDataSerializers.FLOAT);

	@Override
	public boolean hurt(DamageSource damageSource, float damage) {
		if(!this.level().isClientSide && !this.isRemoved()) {
			this.markHurt();
			this.gameEvent(GameEvent.ENTITY_DAMAGE);
			float health = this.entityData.get(HEALTH) - damage;
			this.entityData.set(HEALTH, health);

			if(health < 0) {
				Containers.dropContents(this.level(), this, this.inventory);
				this.spawnAtLocation(LOItems.COVERED_WAGON.get());
				this.kill();
			}
		}
		return true;
	}

	@Override
	public LivingEntity getControllingPassenger() {
		return (LivingEntity) this.getFirstPassenger();
	}

	private Vec3 calcOffset(double x, double y, double z) {
		double rad = this.getYRot() * Math.PI / 180;

		double xOffset = this.position().x + (x * Math.cos(rad) - z * Math.sin(rad));
		double yOffset = this.position().y + y;
		double zOffset = this.position().z + (x * Math.sin(rad) + z * Math.cos(rad));

		return new Vec3(xOffset, yOffset, zOffset);
	}

	@NotNull
	@Override
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		if(this.isAlive() && cap == ForgeCapabilities.ITEM_HANDLER && this.itemHandler != null && this.isAlive()) {
			return itemHandler.cast();
		}
		return super.getCapability(cap, side);
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

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean isPickable() {
		return true;
	}

	public SimpleContainer inventory;
	private LazyOptional<?> itemHandler;

	private void createInventory() {
		this.inventory = new SimpleContainer(36);
		this.inventory.addListener(this);
		this.itemHandler = LazyOptional.of(() -> new InvWrapper(this.inventory));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		if (tag.contains("Variant")) {
			setVariant(tag.getInt("Variant"));
		}

		this.createInventory();
		ListTag listTag = tag.getList("Items", Tag.TAG_COMPOUND);
		for(int i = 0; i < listTag.size(); i++) {
			CompoundTag compoundTag = listTag.getCompound(i);
			int j = compoundTag.getByte("Slot") & 255;
			if(j < this.inventory.getContainerSize()) {
				this.inventory.setItem(j, ItemStack.of(compoundTag));
			}
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		tag.putInt("Variant", getVariant());

		ListTag listTag = new ListTag();
		for(int i = 0; i < this.inventory.getContainerSize(); i++) {
			ItemStack itemStack = this.inventory.getItem(i);
			if(!itemStack.isEmpty()) {
				CompoundTag compoundTag = new CompoundTag();
				compoundTag.putByte("Slot", (byte)i);
				itemStack.save(compoundTag);
				listTag.add(compoundTag);
			}
		}
		tag.put("Items", listTag);
	}

	@Override
	public void defineSynchedData() {
		this.entityData.define(VARIANT, 0);
		this.entityData.define(HITCHED, false);
	}

	@Override
	public void containerChanged(Container container) {
	}
}
