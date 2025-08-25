package com.dragn0007.dragnlivestock.common.gui;

import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.wagon.LumberWagon;
import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractInventoryWagon;
import com.dragn0007.dragnlivestock.util.LOTags;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.Tags;
import net.minecraftforge.items.SlotItemHandler;

public abstract class AbstractWagonMenu extends AbstractContainerMenu {

    protected final AbstractInventoryWagon wagon;

    public AbstractWagonMenu(MenuType<? extends AbstractWagonMenu> type, int id, Inventory inventory, AbstractInventoryWagon wagon,
                             int perRow, int xInv, int yInv) {
        super(type, id);
        this.wagon = wagon;

        for(int y = 0; y < wagon.getItems().getSlots() / perRow; y++) {
            for(int x = 0; x < perRow; x++) {
                addSlot(new SlotItemHandler(wagon.getItems(), x + y*perRow, 8 + x*18, 18 + y*18) {
                    @Override
                    public boolean mayPlace(ItemStack itemStack) {
                        if (wagon.getType().is(LOTags.Entity_Types.LUMBER_WAGON)) {
                            return itemStack.is(ItemTags.LOGS) || itemStack.is(ItemTags.PLANKS) || itemStack.getItem() instanceof AxeItem;
                        }
                        return true;
                    }
                });
            }
        }

        for(int x = 0; x < 9; x++) {
            addSlot(new Slot(inventory, x, xInv + x*18, yInv + 58));
        }

        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 9; x++) {
                addSlot(new Slot(inventory, 9 + x + y*9, xInv + x*18, yInv + y*18));
            }
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack copy = ItemStack.EMPTY;
        Slot slot = slots.get(index);

        if(slot.hasItem()) {
            ItemStack item = slot.getItem();
            copy = item.copy();

            int size = wagon.getItems().getSlots();
            if(index < size) { // If item is in wagon, move to player
                if(!moveItemStackTo(item, size, slots.size(), false))
                    return ItemStack.EMPTY;
            } else { // If not, move to wagon
                if(!moveItemStackTo(item, 0, size, false))
                    return ItemStack.EMPTY;
            }

            if(item.isEmpty())
                slot.setByPlayer(ItemStack.EMPTY);
            else
                slot.setChanged();
        }

        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        return wagon.isAlive() && wagon.distanceTo(player) < 8.0F;
    }

    public static AbstractInventoryWagon getWagon(Inventory inventory, FriendlyByteBuf data) {
        return inventory.player.level().getEntity(data.readInt()) instanceof AbstractInventoryWagon wagon ? wagon : null;
    }
}
