package com.dragn0007.dragnlivestock.entities.wagon.base;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.client.ClientProxy;
import com.dragn0007.dragnlivestock.util.LOTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
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

    private static final EntityDataAccessor<Float> DATA_HEALTH = SynchedEntityData.defineId(AbstractWagon.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> DATA_TYPE = SynchedEntityData.defineId(AbstractWagon.class, EntityDataSerializers.INT);

    private static final double GRAVITY = 0.04D;
    private static final double HORIZONTAL_TETHER = 0.5D * 0.5D; // Use sqr for performance

    private final double maxSpeed;
    private final double acceleration;
    private final float turnRate;
    private final int maxHealth;

    private double speed = 0;
    public UUID owner;

    protected final Vec3[] animalPositions;
    private final Mob[] animals;
    private final UUID[] animalUuids; // Only used for first-tick save behaviour.

    public AbstractWagon(EntityType<? extends AbstractWagon> type, Level level, double maxSpeed, double acceleration,
                         float turnRate, int maxHealth, Vec3[] animalPositions, double wheelWidth, double wheelLength, Vec3[] riders) {
        super(type, level, wheelWidth, wheelLength, riders);
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.turnRate = turnRate;
        this.maxHealth = maxHealth;
        this.animalPositions = animalPositions;
        this.animals = new Mob[animalPositions.length];
        this.animalUuids = new UUID[animals.length];
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
            for(int i = 0; i < animals.length; i++) {
                Mob animal = tryGetDraught(i);
                if(animal != null)
                    setDraught(animal, i);
            }
        }
        for(int i = 0; i < animals.length; i++) {
            if(!isAnimalInRange(i))
                setDraught(null, i);
        }
    }

    @Override
    public void tickRidden() {
        handleSteering();
        handleAcceleration();

        final Vec3 forward = getForward().multiply(1, 0, 1);
        final Vec3 velocity = forward.scale(speed);

        Vec3 move = collide(velocity);

        for(int i = 0; i < animals.length; i++) { // Get the smallest distance in case there's blocking
            Mob animal = getAnimal(i);
            if(animal != null) {
                Vec3 m = animal.collide(velocity);
                if(m.horizontalDistanceSqr() < move.horizontalDistanceSqr())
                    move = m;
            }
        }

        move = move.multiply(1, 0, 1); // "Flatten" the move vector so entities can individually handle their step heights.

        if(!isNoGravity())
            move = move.add(0, -GRAVITY, 0);

        for(int i = 0; i < animals.length; i++) {
            Mob animal = getAnimal(i);
            if(animal != null) {
                animal.setDeltaMovement(move.add(0, animal.getDeltaMovement().y, 0));
                animal.move(MoverType.SELF, animal.getDeltaMovement());
            }
        }

        setDeltaMovement(move.add(0, getDeltaMovement().y, 0));
        move(MoverType.SELF, getDeltaMovement());
        setXRot(rotlerp(getXRot(), calculateTargetXRot(), 3)); // 3 is just a magic scaling number for smoothing.
    }

    private void handleAcceleration() {
        float forward = getForwardImpulse();

        boolean isFull = true;
        for(int i = 0; i < animals.length; i++) {
            if(getAnimal(i) == null) {
                isFull = false;
                break;
            }
        }

        double targetSpeed = isFull ? forward * maxSpeed : 0;
        double diff = targetSpeed - speed;
        double accel = maxSpeed / (acceleration * 20);

        speed += Mth.sign(forward == 0 ? diff : forward) * Math.min(Math.abs(accel), Math.abs(diff));
    }

    private void handleSteering() {
        float f = (float)(speed / maxSpeed);
        float steer = -getLeftImpulse() * turnRate * f;

        if(steer == 0)
            return;

        Vec3[] displacements = new Vec3[animals.length];

        for(int i = 0; i < animals.length; i++) {
            displacements[i] = collideSteering(i, steer);
            if(displacements[i].equals(Vec3.ZERO))
                return;
        }

        setYRot(Mth.wrapDegrees(getYRot() + steer));
        float yRot = getYRot();

        for(int i = 0; i < animals.length; i++) {
            Mob animal = getAnimal(i);
            setAnimalPos(animal, animal.position().add(displacements[i]), yRot);
        }
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

    protected boolean tryHitching(Player player) {
        final Mob animal = level().getEntitiesOfClass(Mob.class, new AABB(
                player.getX()-7, player.getY()-7, player.getZ()-7, player.getX()+7, player.getY()+7, player.getZ()+7),
                        h -> h.getLeashHolder() == player && h.getType().is(LOTags.Entity_Types.DRAUGHT_ANIMALS)).stream()
                .findFirst()
                .orElse(null);

        if(!level().isClientSide && animal != null) {
            for(int i = 0; i < animalPositions.length; i++) {
                if(getAnimal(i) == null) {
                    hitch(animal, i);
                    break;
                }
            }
            animal.dropLeash(true, !player.isCreative());
        }

        return animal != null;
    }

    private boolean tryMountMob(Player player) {
        Mob mob = level().getEntitiesOfClass(Mob.class, new AABB(
                        player.getX()-7, player.getY()-7, player.getZ()-7,
                        player.getX()+7, player.getY()+7, player.getZ()+7
                ), h -> h.getLeashHolder() == player && !h.getType().is(LOTags.Entity_Types.CANNOT_MOUNT_WAGON)).stream()
                .findFirst().orElse(null);

        if(mob != null && !level().isClientSide && canAddPassenger(mob))
            mob.startRiding(this);

        return mob != null;
    }

    protected void hitch(Mob animal, int index) {
        setDraught(animal, index);
        initDraught(animal, index);
    }

    private void initDraught(Mob animal, int index) {
        if(animal == null)
            return;
        animal.ejectPassengers();
        animal.setDeltaMovement(Vec3.ZERO);
        float rot = getYRot();
        setAnimalPos(animal, rotateY(animalPositions[index], rot).add(position()), rot);
    }

    private boolean isAnimalInRange(int index) {
        final Mob animal = getAnimal(index);
        if(animal == null)
            return false;
        return animal.isAlive() && animal.position().subtract(rotateY(animalPositions[index], getYRot()).add(position())).horizontalDistanceSqr() < HORIZONTAL_TETHER;
    }

    private void setAnimalPos(Mob animal, Vec3 pos, float rot) {
        if(animal == null)
            return;
        animal.setYRot(rot);
        animal.setYBodyRot(rot);
        animal.setYHeadRot(rot);
        animal.setPos(pos);
    }

    private Vec3 collideSteering(int index, float angle) {
        Mob animal = getAnimal(index);
        if(animal == null)
            return Vec3.ZERO;

        Vec3 desired = rotateY(animalPositions[index], getYRot() + angle); // Local post-rotation position
        Vec3 local = animal.position().subtract(position()).multiply(1, 0, 1); // Local starting position.

        Vec3 displacement = desired.subtract(local);
        Vec3 collidedDisplacement = animal.collide(displacement);

        if(!displacement.equals(collidedDisplacement.multiply(1, 0, 1)))
            return Vec3.ZERO;

        return collidedDisplacement;
    }

    @Nullable
    protected Mob getAnimal(int index) {
        final UUID uuid = animalUuids[index];
        Mob animal = animals[index];

        if(uuid == null)
            return null;

        if(animal == null && level().getEntities().get(uuid) instanceof Mob mob)
            animal = mob;

        return animal != null && animal.isAlive() ? animal : null;
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

    private Mob tryGetDraught(int index) {
        final UUID uuid = animalUuids[index];
        if(uuid == null)
            return null;

        final Mob animal = getAnimal(index);
        if(animal != null)
            return animal;

        return level().getEntitiesOfClass(Mob.class, getBoundingBox().inflate(5))
                .stream()
                .filter(h -> h.getUUID().equals(uuid))
                .findFirst()
                .orElse(null);
    }

    private void setDraught(Mob animal, int index) {
        Mob old = getAnimal(index);
        if(old != null)
            old.setNoAi(false);
        if(animal != null)
            animal.setNoAi(true);

        animals[index] = animal;
        animalUuids[index] = animal != null ? animal.getUUID() : null;
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

        for(Mob animal : animals) {
            if(animal != null)
                animal.setNoAi(false);
        }

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
        if(owner != null)
            tag.putUUID("owner", owner);

        ListTag animals = new ListTag();

        for(int i = 0; i < this.animals.length; i++) {
            Mob animal = this.animals[i];
            if(animal != null) {
                CompoundTag t = new CompoundTag();
                t.putUUID("uuid", animal.getUUID());
                animals.add(t);
            }
        }

        tag.put("draughtAnimals", animals);
        tag.putInt("woodType", getWoodType().ordinal());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        ListTag animals = tag.getList("draughtAnimals", Tag.TAG_COMPOUND);

        int i = 0;
        for(Tag t : animals) {
            CompoundTag compound = (CompoundTag)t;
            animalUuids[i] = compound.getUUID("uuid");
        }

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
