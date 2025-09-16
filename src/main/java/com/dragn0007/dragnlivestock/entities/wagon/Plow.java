package com.dragn0007.dragnlivestock.entities.wagon;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.gui.DefaultWagonMenu;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractInventoryWagon;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LONetwork;
import com.dragn0007.dragnlivestock.util.LOTags;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class Plow extends AbstractInventoryWagon {

    public static final Vec3[] RIDERS = new Vec3[] {
            new Vec3(0, 1.8D, -0.5D)
    };

    public static final Vec3[] ANIMALS = new Vec3[] {
            new Vec3(-0.7D, 0, 2.5D),
            new Vec3(0.7D, 0, 2.5D)
    };

    public Plow(EntityType<? extends Plow> type, Level level) {
        super(type, level, 0.1D, 2.0D, 3.0F, 20, 36, ANIMALS, 1.25D, 1.25D, RIDERS);
    }

    @Override
    protected boolean tryHitching(Player player) {
        final Mob animal = level().getEntitiesOfClass(Mob.class, new AABB(
                                player.getX()-7, player.getY()-7, player.getZ()-7, player.getX()+7, player.getY()+7, player.getZ()+7),
                        h -> h.getLeashHolder() == player && h.getType().is(LOTags.Entity_Types.MEDIUM_PLUS_DRAUGHT_ANIMALS)).stream()
                .findFirst()
                .orElse(null);

        if(!level().isClientSide && animal != null) {
            for(int i = 0; i < animalPositions.length; i++) {
                if(getAnimal(i) == null) {
                    if (animal instanceof OCow cow) {
                        if (cow.getBreed() == 10) {
                            hitch(animal, i);
                        } else {
                            return false;
                        }
                    } else {
                        hitch(animal, i);
                        break;
                    }
                }
            }
            animal.dropLeash(true, !player.isCreative());
        }

        return animal != null;
    }

    public ItemStack getPickResult() {
        return LOItems.PLOW.get().getDefaultInstance();
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new DefaultWagonMenu(id, inventory, this);
    }

    public static final EntityDataAccessor<Mode> MODE = SynchedEntityData.defineId(Plow.class, LivestockOverhaul.MODE);

    public Vec3 lastClientPos = Vec3.ZERO;
    public Vec3 lastServerPos = Vec3.ZERO;

    public enum Mode {
        TILL(new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/tillmode.png")),
        HARVEST(new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/harvestmode.png")),
        NO(new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/nomode.png"));

        public final ResourceLocation texture;

        Mode(ResourceLocation texture) {
            this.texture = texture;
        }

        public Mode next() {
            return Mode.values()[(this.ordinal() + 1) % Mode.values().length];
        }
    }

    public Mode mode() {
        return this.entityData.get(MODE);
    }

    public void cycleMode() {
        this.entityData.set(MODE, this.entityData.get(MODE).next());
    }

    protected Vec3 calcOffset(double x, double y, double z) {
        double rad = this.getYRot() * Math.PI / 180;

        double xOffset = this.position().x + (x * Math.cos(rad) - z * Math.sin(rad));
        double yOffset = this.position().y + y;
        double zOffset = this.position().z + (x * Math.sin(rad) + z * Math.cos(rad));

        return new Vec3(xOffset, yOffset, zOffset);
    }

    protected void harvestCrop(BlockPos pos) {
        if(this.level().getBlockState(pos).getBlock() instanceof CropBlock cropBlock) {
            BlockState blockState = this.level().getBlockState(pos);

            if(cropBlock.isMaxAge(blockState)) {
                List<ItemStack> drops = Block.getDrops(blockState, (ServerLevel) this.level(), pos, null);
                drops.remove(new ItemStack(cropBlock.asItem()));
                drops.forEach(itemStack -> {
                    boolean added = false;

                    for (int i = 0; i < inventory.size(); i++) {
                        ItemStack slotStack = inventory.get(i);

                        if (slotStack.isEmpty()) {
                            inventory.set(i, itemStack.copy());
                            added = true;
                            break;
                        } else if (ItemStack.isSameItemSameTags(slotStack, itemStack)
                                && slotStack.getCount() < slotStack.getMaxStackSize()) {

                            int transferable = Math.min(itemStack.getCount(), slotStack.getMaxStackSize() - slotStack.getCount());
                            slotStack.grow(transferable);
                            itemStack.shrink(transferable);

                            if (itemStack.isEmpty()) {
                                added = true;
                                break;
                            }
                        }
                    }

                    if (!added && !itemStack.isEmpty()) {
                        this.spawnAtLocation(itemStack);
                    }
                });

                this.level().setBlockAndUpdate(pos, cropBlock.getStateForAge(0));
            }
        }
    }

    protected void tillNewFarmland(BlockPos pos) {
        pos = pos.below();
        BlockState blockState = this.level().getBlockState(pos);
        if (blockState.is(Blocks.DIRT) || blockState.is(Blocks.MYCELIUM) || blockState.is(Blocks.GRASS_BLOCK) || blockState.is(Blocks.PODZOL)) {
            this.level().setBlockAndUpdate(pos, Blocks.FARMLAND.defaultBlockState());
        }
    }

    public void harvest() {
        Vec3 left = this.calcOffset(-1, 0.2, -1.65);
        Vec3 mid = this.calcOffset(0, 0.2, -1.65);
        Vec3 right = this.calcOffset(1, 0.2, -1.65);

        BlockPos leftPos = new BlockPos((int)Math.floor(left.x), (int)Math.floor(left.y), (int)Math.floor(left.z));
        BlockPos midPos = new BlockPos((int)Math.floor(mid.x), (int)Math.floor(mid.y), (int)Math.floor(mid.z));
        BlockPos rightPos = new BlockPos((int)Math.floor(right.x), (int)Math.floor(right.y), (int)Math.floor(right.z));

        this.harvestCrop(leftPos);
        this.harvestCrop(midPos);
        this.harvestCrop(rightPos);
    }

    public void till() {
        Vec3 left = this.calcOffset(-1, 0.2, -1.65);
        Vec3 mid = this.calcOffset(0, 0.2, -1.65);
        Vec3 right = this.calcOffset(1, 0.2, -1.65);

        BlockPos leftPos = new BlockPos((int) Math.floor(left.x), (int) Math.floor(left.y), (int) Math.floor(left.z));
        BlockPos midPos = new BlockPos((int) Math.floor(mid.x), (int) Math.floor(mid.y), (int) Math.floor(mid.z));
        BlockPos rightPos = new BlockPos((int) Math.floor(right.x), (int) Math.floor(right.y), (int) Math.floor(right.z));

        this.tillNewFarmland(leftPos);
        this.tillNewFarmland(midPos);
        this.tillNewFarmland(rightPos);
    }

    @Override
    public void tick() {
        super.tick();
        this.lastClientPos = this.position();

        if(!this.level().isClientSide) {
            Vec3 diff = this.lastServerPos.subtract(this.position());
            this.lastServerPos = this.position();
            if(this.isVehicle() && diff.length() != 0) {
                if(this.entityData.get(MODE) == Mode.TILL) {
                    this.till();
                } else if(this.entityData.get(MODE) == Mode.HARVEST) {
                    this.harvest();
                }
            }
        }

    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(MODE, Mode.NO);
        super.defineSynchedData();
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        this.entityData.set(MODE, Mode.values()[compoundTag.getInt("Mode")]);
        super.readAdditionalSaveData(compoundTag);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putInt("Mode", this.entityData.get(MODE).ordinal());
        super.addAdditionalSaveData(compoundTag);
    }

}
