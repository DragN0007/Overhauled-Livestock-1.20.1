package com.dragn0007.dragnlivestock.entities.wagon.base;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;

import javax.annotation.Nullable;
import java.util.UUID;

public abstract class AbstractWagon extends AbstractGeckolibVehicle {

    private static final EntityDataAccessor<Integer> DATA_DRAUGHT_LEFT = SynchedEntityData.defineId(AbstractWagon.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_DRAUGHT_RIGHT = SynchedEntityData.defineId(AbstractWagon.class, EntityDataSerializers.INT);

    protected static final double GRAVITY = 0.04D;

    private final double maxSpeed;
    private final double acceleration;
    private final float turnRate;

    private final double draughtX;
    private final double draughtY;

    private double speed = 0;

    private final Mob[] draughtAnimals = new Mob[2];
    private final UUID[] draughtAnimalUuids = new UUID[2]; // Only used for first-tick save behaviour.

    public AbstractWagon(EntityType<? extends AbstractWagon> type, Level level, double maxSpeed, double acceleration,
                         float turnRate, double draughtX, double draughtY, double wheelWidth, double wheelLength, Vec3[] riders) {
        super(type, level, wheelWidth, wheelLength, riders);
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.turnRate = turnRate;
        this.draughtX = draughtX;
        this.draughtY = draughtY;
    }

    @Override
    public void tickRidden() {

        handleSteering();
        handleAcceleration();

        Vec3 forward = getForward().multiply(1, 0, 1);
        Vec3 velocity = forward.scale(speed);

        Vec3 move = collide(velocity);

        move = move.multiply(1, 0, 1); // "Flatten" the move vector so entities can individually handle their step heights.

        if(!isNoGravity())
            move = move.add(0, -GRAVITY, 0);


        setDeltaMovement(move.add(0, getDeltaMovement().y, 0));
        move(MoverType.SELF, getDeltaMovement());
        setXRot(rotlerp(getXRot(), calculateTargetXRot(), 2)); // 9 is just a magic scaling number for smoothing.
//        setXRot(rotlerp(getXRot(), calculateTargetXRot(), (float)(9 * speed / maxSpeed))); // 9 is just a magic scaling number for smoothing.
    }

    private void handleAcceleration() {
        float forward = getForwardImpulse();

        double targetSpeed = forward * maxSpeed;
        double diff = targetSpeed - speed;
        double accel = maxSpeed / (acceleration * 20);

        speed += Mth.sign(forward == 0 ? diff : forward) * Math.min(Math.abs(accel), Math.abs(diff));
    }

    private void handleSteering() {

    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if(!isAlive())
            return InteractionResult.PASS;

        final boolean isClientSide = level().isClientSide;

        if(player.isSecondaryUseActive()) {
            ItemStack stack = player.getItemInHand(hand);

            if(tryHitching(player))
                return InteractionResult.sidedSuccess(isClientSide);
        }

        return super.interact(player, hand);
    }

    private boolean tryHitching(Player player) {
        final Mob animal = level().getEntitiesOfClass(Mob.class, new AABB(
                player.getX()-7, player.getY()-7, player.getZ()-7, player.getX()+7, player.getY()+7, player.getZ()+7),
                        h -> h.getLeashHolder() == player && h.getType().is(LivestockOverhaul.DRAUGHT_ANIMALS)).stream()
                .findFirst()
                .orElse(null);

        if(!level().isClientSide && animal != null) {
            if(getDraught(Side.LEFT) == null)
                hitch(animal, Side.LEFT);
            else if(getDraught(Side.RIGHT) == null)
                hitch(animal, Side.RIGHT);

            animal.dropLeash(true, !player.isCreative());
        }

        return animal != null;
    }

    private void hitch(Mob animal, Side side) {
        setDraught(animal, side);
        initDraught(animal, side);
    }

    private void initDraught(Mob animal, Side side) {
        if(animal == null)
            return;
        animal.ejectPassengers();
        animal.setDeltaMovement(Vec3.ZERO);
        setAnimalPos(animal, getAnimalPos(side), getYRot());
    }

    private void setAnimalPos(Mob animal, Vec3 pos, float rot) {
        if(animal == null)
            return;
        animal.setYRot(rot);
        animal.setYBodyRot(rot);
        animal.setYHeadRot(rot);
        animal.setPos(pos);
    }

    private Vec3 getAnimalPos(Side side) {
        return position().add(rotateY(new Vec3(side == Side.LEFT ? -draughtX : draughtX, 0, draughtY), getYRot()));
    }

    @Nullable
    private Mob getDraught(Side side) {
        final int id = side == Side.LEFT ? entityData.get(DATA_DRAUGHT_LEFT) : entityData.get(DATA_DRAUGHT_RIGHT);
        final int index = side.ordinal();

        Mob animal = draughtAnimals[index];
        if(animal != null && animal.getId() == id && animal.isAlive())
            return animal;

        if(id != -1) {
            animal = (Mob)level().getEntity(id);
        }
        else {
            UUID uuid = getDraughtUuid(side);
            if(uuid != null)
                animal = (Mob)level().getEntities().get(uuid);
        }

        if(animal != null && animal.isAlive()) {
            draughtAnimals[index] = animal;
            return animal;
        }

        return null;
    }

    private void setDraught(Mob animal, Side side) {
        Mob old = getDraught(side);
        if(old != null)
            old.setNoAi(false);

        int id = -1;
        if(animal != null) {
            id = animal.getId();
            animal.setNoAi(true);
        }

        draughtAnimals[side.ordinal()] = animal;
        entityData.set(side == Side.LEFT ? DATA_DRAUGHT_LEFT : DATA_DRAUGHT_RIGHT, id);
    }

    @Nullable
    private UUID getDraughtUuid(Side side) {
        return draughtAnimalUuids[side.ordinal()];
    }

    private void setDraughtUuid(Side side, UUID value) {
        draughtAnimalUuids[side.ordinal()] = value;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        Mob left = getDraught(Side.LEFT);
        Mob right = getDraught(Side.RIGHT);

        if(left != null)
            tag.putUUID("leftDraughtAnimal", left.getUUID());
        if(right != null)
            tag.putUUID("rightDraughtAnimal", right.getUUID());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if(tag.contains("leftDraughtAnimal"))
            setDraughtUuid(Side.LEFT, tag.getUUID("leftDraughtAnimal"));
        if(tag.contains("rightDraughtAnimal"))
            setDraughtUuid(Side.RIGHT, tag.getUUID("rightDraughtAnimal"));
    }

    @Override
    public boolean isControlledByLocalInstance() {
        return super.isControlledByLocalInstance();
    }

    @Override
    protected void defineSynchedData() {
        entityData.define(DATA_DRAUGHT_LEFT, -1);
        entityData.define(DATA_DRAUGHT_RIGHT, -1);
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source) {
        return !getType().is(EntityTypeTags.FALL_DAMAGE_IMMUNE);
    }

    @Override
    public void registerControllers(ControllerRegistrar controllers) {


    }

    private enum Side {
        LEFT, RIGHT
    }

}
