package com.dragn0007.dragnlivestock.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.HopperMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class FishTrapBlockEntity extends BaseContainerBlockEntity implements MenuProvider {

    public NonNullList<ItemStack> items = NonNullList.withSize(5, ItemStack.EMPTY);

    public FishTrapBlockEntity(BlockEntityType<?> entityType, BlockPos blockPos, BlockState state) {
        super(entityType, blockPos, state);
    }

    public int catchTime = 200;

    @Override
    public Component getDefaultName() {
        return Component.translatable("container.dragnlivestock.fish_trap");
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new HopperMenu(containerId, inventory, this);
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemStack : this.items) {
            if (!itemStack.isEmpty()) {
                return false;
            }
        }
        return true;
    }



    @Override
    public ItemStack getItem(int p_18941_) {
        return null;
    }

    @Override
    public ItemStack removeItem(int p_18942_, int p_18943_) {
        return null;
    }

    @Override
    public ItemStack removeItemNoUpdate(int p_18951_) {
        return null;
    }

    @Override
    public void setItem(int p_18944_, ItemStack p_18945_) {

    }

    @Override
    public boolean stillValid(Player p_18946_) {
        return true;
    }

    @Override
    public void clearContent() {

    }
}
