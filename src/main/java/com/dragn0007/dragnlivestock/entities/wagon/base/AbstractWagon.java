package com.dragn0007.dragnlivestock.entities.wagon.base;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.client.ClientProxy;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.UUID;

public abstract class AbstractWagon extends AbstractGeckolibVehicle {

    private static final Random RANDOM = new Random();

    private static final EntityDataAccessor<Integer> DATA_DRAUGHT_LEFT = SynchedEntityData.defineId(AbstractWagon.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_DRAUGHT_RIGHT = SynchedEntityData.defineId(AbstractWagon.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> DATA_HEALTH = SynchedEntityData.defineId(AbstractWagon.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> DATA_TYPE = SynchedEntityData.defineId(AbstractWagon.class, EntityDataSerializers.INT);


    private static final double GRAVITY = 0.04D;
    private static final double HORIZONTAL_TETHER = 0.5D * 0.5D; // Use sqr for performance

    private final double maxSpeed;
    private final double acceleration;
    private final float turnRate;
    private final int maxHealth;

    private final double draughtX;
    private final double draughtY;

    private double speed = 0;
    public UUID owner;

    private final Mob[] draughtAnimals = new Mob[2];
    private final UUID[] draughtAnimalUuids = new UUID[2]; // Only used for first-tick save behaviour.

    public AbstractWagon(EntityType<? extends AbstractWagon> type, Level level, double maxSpeed, double acceleration,
                         float turnRate, int maxHealth, double draughtX, double draughtY, double wheelWidth, double wheelLength, Vec3[] riders) {
        super(type, level, wheelWidth, wheelLength, riders);
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.turnRate = turnRate;
        this.maxHealth = maxHealth;
        this.draughtX = draughtX;
        this.draughtY = draughtY;
    }

    @Override
    public void tick() {
        if(firstTick && level().isClientSide)
            ClientProxy.createWagonSound(this);

        if(!level().isClientSide)
            validateDraughtAnimals();

        super.tick();
    }

    private void validateDraughtAnimals() {
        if(firstTick || level().getGameTime() % 100 == 0) { // Repeated checks help resolve chunk loading issues.
            Mob animal = tryGetDraught(Side.LEFT);
            if(animal != null)
                setDraught(animal, Side.LEFT);

            animal = tryGetDraught(Side.RIGHT);
            if(animal != null)
                setDraught(animal, Side.RIGHT);
        }

        if(!isAnimalInRange(Side.LEFT))
            setDraught(null, Side.LEFT);
        if(!isAnimalInRange(Side.LEFT))
            setDraught(null, Side.RIGHT);
    }

    @Override
    public void tickRidden() {
        final Mob left = getDraught(Side.LEFT);
        final Mob right = getDraught(Side.RIGHT);

        handleSteering();
        handleAcceleration(left, right);

        final Vec3 forward = getForward().multiply(1, 0, 1);
        final Vec3 velocity = forward.scale(speed);

        final Vec3 leftMove = left != null ? left.collide(velocity) : null;
        final Vec3 rightMove = right != null ? right.collide(velocity) : null;
        Vec3 move = collide(velocity);

        if(leftMove != null && leftMove.horizontalDistanceSqr() < move.horizontalDistanceSqr()) // Get the smallest distance in case there's blocking
            move = leftMove;
        if(rightMove != null && rightMove.horizontalDistanceSqr() < move.horizontalDistanceSqr())
            move = rightMove;

        move = move.multiply(1, 0, 1); // "Flatten" the move vector so entities can individually handle their step heights.

        if(!isNoGravity())
            move = move.add(0, -GRAVITY, 0);

        if(left != null) {
            left.setDeltaMovement(move.add(0, left.getDeltaMovement().y, 0));
            left.move(MoverType.SELF, left.getDeltaMovement());
        }
        if(right != null) {
            right.setDeltaMovement(move.add(0, right.getDeltaMovement().y, 0));
            right.move(MoverType.SELF, right.getDeltaMovement());
        }

        setDeltaMovement(move.add(0, getDeltaMovement().y, 0));
        move(MoverType.SELF, getDeltaMovement());
        setXRot(rotlerp(getXRot(), calculateTargetXRot(), 3)); // 3 is just a magic scaling number for smoothing.
    }

    private void handleAcceleration(Mob left, Mob right) {
        float forward = getForwardImpulse();

        double targetSpeed = left != null && right != null ? forward * maxSpeed : 0;
        double diff = targetSpeed - speed;
        double accel = maxSpeed / (acceleration * 20);

        speed += Mth.sign(forward == 0 ? diff : forward) * Math.min(Math.abs(accel), Math.abs(diff));
    }

    private void handleSteering() {
        float f = (float)(speed / maxSpeed);
        float steer = -getLeftImpulse() * turnRate * f;

        if(steer == 0)
            return;

        Vec3 leftDisplace = collideSteering(Side.LEFT, steer);
        Vec3 rightDisplace = collideSteering(Side.RIGHT, steer);

        if(leftDisplace.equals(Vec3.ZERO) || rightDisplace.equals(Vec3.ZERO))
            return;

        final Mob left = getDraught(Side.LEFT);
        final Mob right = getDraught(Side.RIGHT);

        setYRot(Mth.wrapDegrees(getYRot() + steer));
        float yRot = getYRot();
        setAnimalPos(left, left.position().add(leftDisplace), yRot);
        setAnimalPos(right, right.position().add(rightDisplace), yRot);
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if(!isAlive())
            return InteractionResult.PASS;

        final boolean isClientSide = level().isClientSide;

        if(player.isSecondaryUseActive()) {
            ItemStack stack = player.getItemInHand(hand);

            if(stack.is(ItemTags.AXES)) {
                if(!isClientSide && player.getUUID().equals(owner))
                    onDestroyed(true);
                return InteractionResult.sidedSuccess(isClientSide);
            }

            if(tryHitching(player) || tryMountMob(player))
                return InteractionResult.sidedSuccess(isClientSide);

            if(stack.is(Items.LEAD)) {
                if(isClientSide)
                    return InteractionResult.SUCCESS;

                for(Entity passenger : getPassengers()) {
                    if(passenger instanceof Mob mob) {
                        mob.stopRiding();
                        player.getItemInHand(hand).shrink(1);
                        if(mob.canBeLeashed(player))
                            mob.setLeashedTo(player, true);
                        break;
                    }
                }
                return InteractionResult.CONSUME;
            }
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

    private boolean tryMountMob(Player player) {
        Mob mob = level().getEntitiesOfClass(Mob.class, new AABB(
                        player.getX()-7, player.getY()-7, player.getZ()-7,
                        player.getX()+7, player.getY()+7, player.getZ()+7
                ), h -> h.getLeashHolder() == player && !h.getType().is(LivestockOverhaul.CANNOT_MOUNT_WAGON)).stream()
                .findFirst().orElse(null);

        if(mob != null && !level().isClientSide && canAddPassenger(mob))
            mob.startRiding(this);

        return mob != null;
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

    private boolean isAnimalInRange(Side side) {
        final Mob animal = getDraught(side);
        if(animal == null)
            return false;
        return animal.isAlive() && animal.position().subtract(getAnimalPos(side)).horizontalDistanceSqr() < HORIZONTAL_TETHER;
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

    private Vec3 collideSteering(Side side, float angle) {
        Mob animal = getDraught(side);
        if(animal == null)
            return Vec3.ZERO;

        Vec3 desired = rotateY(new Vec3(side == Side.LEFT ? -draughtX : draughtX, 0, draughtY), getYRot() + angle); // Local post-rotation position
        Vec3 local = animal.position().subtract(position()).multiply(1, 0, 1); // Local starting position.

        Vec3 displacement = desired.subtract(local);
        Vec3 collidedDisplacement = animal.collide(displacement);

        if(!displacement.equals(collidedDisplacement.multiply(1, 0, 1)))
            return Vec3.ZERO;

        return collidedDisplacement;
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

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity passenger) {
        final double width = getBbWidth();
        double offset = Math.sqrt(width * width + width * width) / 2 + 0.05D;

        double pWidth = passenger.getBbWidth();
        double pOffset = Math.sqrt(pWidth * pWidth + pWidth * pWidth) / 2;

        float yRot = getYRot();

        pWidth = pWidth * 0.8F;

        Vec3 wOffset = new Vec3(0, passenger.getEyeHeight(), 0);

        Vec3 pos = position().add(rotateY(new Vec3(offset + pOffset, 0, 0), yRot)); // Left
        if(isEmpty(AABB.ofSize(pos.add(wOffset), pWidth, 1.0E-6D, pWidth)))
            return pos;

        pos = position().add(rotateY(new Vec3(-offset - pOffset, 0, 0), yRot)); // Right
        if(isEmpty(AABB.ofSize(pos.add(wOffset), pWidth, 1.0E-6D, pWidth)))
            return pos;

        pos = position().add(rotateY(new Vec3(0, 0, offset + pOffset), yRot)); // Front
        if(isEmpty(AABB.ofSize(pos.add(wOffset), pWidth, 1.0E-6D, pWidth)))
            return pos;

        pos = position().add(rotateY(new Vec3(0, 0, -offset - pOffset), yRot)); // Back
        if(isEmpty(AABB.ofSize(pos.add(wOffset), pWidth, 1.0E-6D, pWidth)))
            return pos;

        return super.getDismountLocationForPassenger(passenger);
    }

    public boolean isEmpty(AABB aabb) {
        return BlockPos.betweenClosedStream(aabb).noneMatch(pos -> {
            BlockState state = level().getBlockState(pos);
            return !state.isAir() && state.isSuffocating(level(), pos) &&
                    Shapes.joinIsNotEmpty(state.getCollisionShape(level(), pos).move(pos.getX(), pos.getY(), pos.getZ()), Shapes.create(aabb), BooleanOp.AND);
        });
    }

    private Mob tryGetDraught(Side side) {
        final UUID uuid = getDraughtUuid(side);
        if(uuid == null)
            return null;

        final Mob animal = getDraught(side);
        if(animal != null)
            return animal;

        return level().getEntitiesOfClass(Mob.class, getBoundingBox().inflate(5))
                .stream()
                .filter(h -> h.getUUID().equals(uuid))
                .findFirst()
                .orElse(null);
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
    public boolean hurt(DamageSource source, float amount) {
        if(isInvulnerableTo(source))
            return false;

        if(level().isClientSide || isRemoved())
            return true;

        level().playSound(null, blockPosition(), SoundEvents.WOOD_BREAK, SoundSource.MASTER);
        double w = getBbWidth();
        double h = getBbHeight();
        ((ServerLevel)level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.SPRUCE_PLANKS.defaultBlockState()),
                getX(), getY(), getZ(), 100, w, h, w, 0);

        setHealth(getHealth() - amount);
        gameEvent(GameEvent.ENTITY_DAMAGE, source.getEntity());

        if(getHealth() <= 0)
            onDestroyed(false);

        return true;
    }

    protected void onDestroyed(boolean dropItem) {
        if(level().isClientSide)
            return;

        Mob animal = getDraught(Side.LEFT);
        if(animal != null)
            animal.setNoAi(false);

        animal = getDraught(Side.RIGHT);
        if(animal != null)
            animal.setNoAi(false);

        ejectPassengers();
        discard();

        if(!level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS))
            return;

        if(dropItem) {
            ItemStack pickResult = getPickResult();
            if(pickResult != null)
                spawnAtLocation(pickResult);
        }
        else {
            spawnAtLocation(new ItemStack(getWoodType().planks.asItem(), RANDOM.nextInt(7)));
            spawnAtLocation(new ItemStack(Items.IRON_INGOT, RANDOM.nextInt(3)));
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        Mob left = getDraught(Side.LEFT);
        Mob right = getDraught(Side.RIGHT);

        if(owner != null)
            tag.putUUID("owner", owner);
        if(left != null)
            tag.putUUID("leftDraughtAnimal", left.getUUID());
        if(right != null)
            tag.putUUID("rightDraughtAnimal", right.getUUID());
        tag.putInt("woodType", getWoodType().ordinal());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if(tag.contains("leftDraughtAnimal"))
            setDraughtUuid(Side.LEFT, tag.getUUID("leftDraughtAnimal"));
        if(tag.contains("rightDraughtAnimal"))
            setDraughtUuid(Side.RIGHT, tag.getUUID("rightDraughtAnimal"));
        if(tag.contains("owner"))
            owner = tag.getUUID("owner");
        setWoodType(Type.values()[tag.getInt("woodtype")]);
    }

    public float getHealth() {
        return entityData.get(DATA_HEALTH);
    }

    protected void setHealth(float health) {
        entityData.set(DATA_HEALTH, Mth.clamp(health, 0, maxHealth));
    }

    public Type getWoodType() {
        return Type.values()[entityData.get(DATA_TYPE)];
    }

    public void setWoodType(Type type) {
        entityData.set(DATA_TYPE, type.ordinal());
    }

    @Override
    protected void defineSynchedData() {
        entityData.define(DATA_DRAUGHT_LEFT, -1);
        entityData.define(DATA_DRAUGHT_RIGHT, -1);
        entityData.define(DATA_TYPE, 0);
        entityData.define(DATA_HEALTH, (float)maxHealth);
    }

    @Override
    public boolean isControlledByLocalInstance() {
        return isEffectiveAi();
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source) {
        return !getType().is(EntityTypeTags.FALL_DAMAGE_IMMUNE);
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    private enum Side {
        LEFT, RIGHT
    }

    public enum Type {
        OAK(Blocks.OAK_PLANKS),
        SPRUCE(Blocks.SPRUCE_PLANKS),
        BIRCH(Blocks.BIRCH_PLANKS),
        JUNGLE(Blocks.JUNGLE_PLANKS),
        ACACIA(Blocks.ACACIA_PLANKS),
        CHERRY(Blocks.CHERRY_PLANKS),
        DARK_OAK(Blocks.DARK_OAK_PLANKS),
        MANGROVE(Blocks.MANGROVE_PLANKS);

        private final Block planks;

        Type(Block planks) {
            this.planks = planks;
        }

        public Block getPlanks() {
            return this.planks;
        }

    }

}
