package com.dragn0007.dragnlivestock.entities.wagon;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractWagon;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LONetwork;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class Mower extends AbstractWagon {

    public static final Vec3[] RIDERS = new Vec3[] {
            new Vec3(0, 1.8D, -0.5D)
    };

    public static final Vec3[] ANIMALS = new Vec3[] {
            new Vec3(-0.7D, 0, 2.5D),
            new Vec3(0.7D, 0, 2.5D)
    };

    public Mower(EntityType<? extends Mower> type, Level level) {
        super(type, level, LivestockOverhaulCommonConfig.MOWER_SPEED_MULT.get(), 2.0D, 3.0F, 80, ANIMALS, 1.25D, 1.25D, RIDERS);
    }

    @Override
    protected boolean tryHitching(Player player) {
        final Mob animal = level().getEntitiesOfClass(Mob.class, new AABB(
                                player.getX()-7, player.getY()-7, player.getZ()-7, player.getX()+7, player.getY()+7, player.getZ()+7),
                        h -> h.getLeashHolder() == player && h.getType().is(LOTags.Entity_Types.MEDIUM_PLUS_DRAUGHT_ANIMALS)).stream()
                .findFirst()
                .orElse(null);

        if (!level().isClientSide && animal != null) {
            if (animal instanceof OCow entity && entity.getBreed() != 10) {
                tryMountMob(player);
            } else if (!(animal instanceof OCow) || (animal instanceof OCow entity && entity.getBreed() == 10)) {
                for (int i = 0; i < animalPositions.length; i++) {
                    if (getAnimal(i) == null) {
                        hitch(animal, i);
                        break;
                    }
                }
                animal.dropLeash(true, !player.isCreative());
            }
        }

        return animal != null;
    }

    public ItemStack getPickResult() {
        return LOItems.MOWER.get().getDefaultInstance();
    }

    public static final EntityDataAccessor<Integer> MODE = SynchedEntityData.defineId(Mower.class, EntityDataSerializers.INT);

    public Vec3 lastClientPos = Vec3.ZERO;
    public Vec3 lastServerPos = Vec3.ZERO;

    public enum Mode {
        NO(new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/nomode.png")),
        MOW(new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/mowmode.png")),
        PATH(new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/pathmode.png"));

        public final ResourceLocation texture;

        Mode(ResourceLocation texture) {
            this.texture = texture;
        }

        public Mode next() {
            return Mode.values()[(this.ordinal() + 1) % Mode.values().length];
        }
    }

    public int mode() {
        return this.entityData.get(MODE);
    }

    public void cycleMode() {
        this.entityData.set(MODE, (this.entityData.get(MODE) +1) % 3);
    }

    protected Vec3 calcOffset(double x, double y, double z) {
        double rad = this.getYRot() * Math.PI / 180;

        double xOffset = this.position().x + (x * Math.cos(rad) - z * Math.sin(rad));
        double yOffset = this.position().y + y;
        double zOffset = this.position().z + (x * Math.sin(rad) + z * Math.cos(rad));

        return new Vec3(xOffset, yOffset, zOffset);
    }

    protected void tillNewPath(BlockPos pos) {
        pos = pos.below();
        BlockState blockState = this.level().getBlockState(pos);
        if (blockState.is(Blocks.DIRT) || blockState.is(Blocks.MYCELIUM) || blockState.is(Blocks.GRASS_BLOCK) || blockState.is(Blocks.PODZOL)) {
            this.level().setBlockAndUpdate(pos, Blocks.DIRT_PATH.defaultBlockState());
        }
    }

    protected void destroyFoliage(BlockPos pos) {
        BlockState blockState = this.level().getBlockState(pos);
        if (blockState.is(Blocks.GRASS) || blockState.is(Blocks.TALL_GRASS) ||
                blockState.is(Blocks.DEAD_BUSH) || blockState.is(Blocks.FERN) || blockState.is(Blocks.LARGE_FERN)
                || blockState.is(BlockTags.FLOWERS) || blockState.is(BlockTags.SNOW)) {
            blockState.getBlock().getDrops(blockState, (ServerLevel) level(), pos, null).forEach
                    (stack -> level().addFreshEntity(new ItemEntity(level(),
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5, stack)));
            this.level().removeBlock(pos, false);
        }
    }

    public void mow() {
        Vec3 left = this.calcOffset(-1, 0.2, -1.65);
        Vec3 mid = this.calcOffset(0, 0.2, -1.65);
        Vec3 right = this.calcOffset(1, 0.2, -1.65);

        BlockPos leftPos = new BlockPos((int)Math.floor(left.x), (int)Math.floor(left.y), (int)Math.floor(left.z));
        BlockPos midPos = new BlockPos((int)Math.floor(mid.x), (int)Math.floor(mid.y), (int)Math.floor(mid.z));
        BlockPos rightPos = new BlockPos((int)Math.floor(right.x), (int)Math.floor(right.y), (int)Math.floor(right.z));

        this.destroyFoliage(leftPos);
        this.destroyFoliage(midPos);
        this.destroyFoliage(rightPos);
    }

    public void till() {
        Vec3 left = this.calcOffset(-1, 0.2, -1.65);
        Vec3 mid = this.calcOffset(0, 0.2, -1.65);
        Vec3 right = this.calcOffset(1, 0.2, -1.65);
        Vec3 upLeft = this.calcOffset(-1, 0.5, -1.65);
        Vec3 upMid = this.calcOffset(0, 0.5, -1.65);
        Vec3 upRight = this.calcOffset(1, 0.5, -1.65);

        BlockPos leftPos = new BlockPos((int) Math.floor(left.x), (int) Math.floor(left.y), (int) Math.floor(left.z));
        BlockPos midPos = new BlockPos((int) Math.floor(mid.x), (int) Math.floor(mid.y), (int) Math.floor(mid.z));
        BlockPos rightPos = new BlockPos((int) Math.floor(right.x), (int) Math.floor(right.y), (int) Math.floor(right.z));

        BlockPos upLeftPos = new BlockPos((int) Math.floor(upLeft.x), (int) Math.floor(upLeft.y), (int) Math.floor(upLeft.z));
        BlockPos upMidPos = new BlockPos((int) Math.floor(upMid.x), (int) Math.floor(upMid.y), (int) Math.floor(upMid.z));
        BlockPos upRightPos = new BlockPos((int) Math.floor(upRight.x), (int) Math.floor(upRight.y), (int) Math.floor(upRight.z));

        this.tillNewPath(leftPos);
        this.tillNewPath(midPos);
        this.tillNewPath(rightPos);

        this.destroyFoliage(upLeftPos);
        this.destroyFoliage(upMidPos);
        this.destroyFoliage(upRightPos);
    }


    public int tillerCooldown = 0;

    public void handleInput(Input input) {
        this.tillerCooldown = Math.max(this.tillerCooldown - 1, 0);
        if(input.jumping && this.tillerCooldown == 0) {
            LONetwork.INSTANCE.sendToServer(new LONetwork.ToggleTillerPowerRequest(this.getId()));
            this.tillerCooldown = 10;
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.lastClientPos = this.position();

        if(!this.level().isClientSide) {
            Vec3 diff = this.lastServerPos.subtract(this.position());
            this.lastServerPos = this.position();
            if(this.isVehicle() && diff.length() != 2) {
                if(this.entityData.get(MODE) == 1) {
                    this.mow();
                } else if(this.entityData.get(MODE) == 2) {
                    this.till();
                }
            }
        } else {
            if(this.getControllingPassenger() instanceof LocalPlayer player) {
                this.handleInput(player.input);
            }
        }

    }

    protected static final EntityDataAccessor<Float> DATA_HEALTH = SynchedEntityData.defineId(Mower.class, EntityDataSerializers.FLOAT);
    protected static final EntityDataAccessor<Integer> DATA_TYPE = SynchedEntityData.defineId(Mower.class, EntityDataSerializers.INT);

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MODE, 0);
        entityData.define(DATA_TYPE, 0);
        entityData.define(DATA_HEALTH, (float)maxHealth);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.entityData.set(MODE, compoundTag.getInt("Mode"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("Mode", this.entityData.get(MODE));
    }

}
