package com.dragn0007.dragnlivestock.common.gui;

import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.util.LOTags;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class OxMenu extends AbstractContainerMenu {

    public Container container;
    public AbstractOMount ox;

    public OxMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory, new SimpleContainer(extraData.readInt()), (AbstractOMount) inventory.player.level().getEntity(extraData.readInt()));
    }

    public OxMenu(int containerId, Inventory inventory, Container container, AbstractOMount ox) {
        super(LOMenuTypes.OX_MENU.get(), containerId);
        this.container = container;
        this.ox = ox;

        int oxSlots = 0;
        this.addSlot(new Slot(this.container, oxSlots++, 8, 18) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return itemStack.is(Items.SADDLE) && !this.hasItem() && OxMenu.this.ox.isSaddleable();
            }

            @Override
            public boolean isActive() {
                return OxMenu.this.ox.isSaddleable();
            }
        });

        this.addSlot(new Slot(this.container, oxSlots++, 8, 18) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                if (itemStack.is(LOTags.Items.DECOR_FOR_O_MOUNTS)) {
                    return !this.hasItem() && OxMenu.this.ox.canWearArmor();
                }
                return false;
            }

            @Override
            public boolean isActive() {
                return OxMenu.this.ox.canWearArmor();
            }
        });

        if (this.ox.hasChest()) {
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 8; x++) {
                    this.addSlot(new Slot(this.container, oxSlots++, 26 + x * 18, 18 + y * 18));
                }
            }
        }

        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 9; x++) {
                this.addSlot(new Slot(inventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }

        for(int x = 0; x < 9; x++) {
            this.addSlot(new Slot(inventory, x, 8 + x * 18, 142));
        }
    }

        public boolean stillValid(Player player) {
        return !this.ox.hasInventoryChanged(this.container) && this.container.stillValid(player) && this.ox.isAlive() && this.ox.distanceTo(player) < 8.0F;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotId) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotId);
        if(slot.hasItem()) {
            itemStack = slot.getItem().copy();
            int containerSize = this.container.getContainerSize();

            if(slotId < containerSize) {
                if(!this.moveItemStackTo(itemStack, containerSize, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if(!this.moveItemStackTo(itemStack, 0, containerSize, false)) {
                return ItemStack.EMPTY;
            }

            if(itemStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return itemStack;
    }
}