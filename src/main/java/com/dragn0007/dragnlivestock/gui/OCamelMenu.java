package com.dragn0007.dragnlivestock.gui;

import com.dragn0007.dragnlivestock.entities.camel.OCamel;
import com.dragn0007.dragnlivestock.util.LOTags;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;

public class OCamelMenu extends AbstractContainerMenu {

    public Container container;
    public OCamel oCamel;

    public OCamelMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory, new SimpleContainer(extraData.readInt()), (OCamel) inventory.player.level().getEntity(extraData.readInt()));
    }

    public OCamelMenu(int containerId, Inventory inventory, Container container, OCamel abstractOMount) {
        super(LOMenuTypes.O_CAMEL_MENU.get(), containerId);
        this.container = container;
        this.oCamel = abstractOMount;

        int OCamelSlots = 0;
        this.addSlot(new Slot(this.container, OCamelSlots++, 8, 18) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return itemStack.is(LOTags.Items.SADDLE) && !this.hasItem() && OCamelMenu.this.oCamel.isSaddleable();
            }

            @Override
            public boolean isActive() {
                return OCamelMenu.this.oCamel.isSaddleable();
            }
        });

        this.addSlot(new Slot(this.container, OCamelSlots++, 8, 36) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                if (itemStack.getItem() instanceof HorseArmorItem) {
                    return !this.hasItem() && OCamelMenu.this.oCamel.canWearArmor();
                } else if (itemStack.is(LOTags.Items.CAN_PLACE_ON_O_MOUNTS)) {
                    return !this.hasItem() && OCamelMenu.this.oCamel.canWearArmor();
                }
                return false;
            }

            @Override
            public boolean isActive() {
                return OCamelMenu.this.oCamel.canWearArmor();
            }
        });

//        this.addSlot(new Slot(this.container, OCamelSlots++, 8, 54) {
//            @Override
//            public boolean mayPlace(ItemStack itemStack) {
//                if (itemStack.getItem() instanceof HorseShoeItem) {
//                    return !this.hasItem() && OCamelMenu.this.OCamel.canWearShoes();
//                }
//                return false;
//            }
//
//            @Override
//            public boolean isActive() {
//                return OCamelMenu.this.OCamel.canWearShoes();
//            }
//        });

        if(this.oCamel.hasChest()) {
            for(int y = 0; y < 3; y++) {
                for(int x = 0; x < 5; x++) {
                    this.addSlot(new Slot(this.container, OCamelSlots++, 80 + x * 18, 18 + y * 18));
                }
            }
        }

        int playerSlots = 0;
        for(int x = 0; x < 9; x++) {
            this.addSlot(new Slot(inventory, playerSlots++, 8 + x * 18, 142));
        }

        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 9; x++) {
                this.addSlot(new Slot(inventory, playerSlots++, 8 + x * 18, 84 + y * 18));
            }
        }
    }

    public boolean stillValid(Player player) {
        return !this.oCamel.hasInventoryChanged(this.container) && this.container.stillValid(player) && this.oCamel.isAlive() && this.oCamel.distanceTo(player) < 8.0F;
    }

//    public ItemStack quickMoveStack(Player player, int slotId) {
//        Slot slot = this.slots.get(slotId);
//        if(!slot.hasItem()) {
//            return ItemStack.EMPTY;
//        }
//
//        ItemStack itemStack = slot.getItem();
//        ItemStack itemStackCopy = itemStack.copy();
//        int containerSize = this.container.getContainerSize();
//
//        if(slotId < containerSize) {
//            if(!this.moveItemStackTo(itemStack, containerSize, containerSize + 36, true)) {
//                return ItemStack.EMPTY;
//            }
//        } else if(slotId < containerSize + 36) {
//            if(!this.moveItemStackTo(itemStack, 0, containerSize, false)) {
//                return ItemStack.EMPTY;
//            }
//        }
//
//        if(itemStack.getCount() == 0) {
//            slot.set(ItemStack.EMPTY);
//        }
//        slot.setChanged();
//        return itemStackCopy;
//    }

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