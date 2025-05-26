package com.dragn0007.dragnlivestock.gui;

import com.dragn0007.dragnlivestock.entities.caribou.Caribou;
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

public class CaribouMenu extends AbstractContainerMenu {

    public Container container;
    public Caribou caribou;

    public CaribouMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory, new SimpleContainer(extraData.readInt()), (Caribou) inventory.player.level().getEntity(extraData.readInt()));
    }

    public CaribouMenu(int containerId, Inventory inventory, Container container, Caribou abstractOMount) {
        super(LOMenuTypes.O_CARIBOU_MENU.get(), containerId);
        this.container = container;
        this.caribou = abstractOMount;

        int CaribouSlots = 0;
        this.addSlot(new Slot(this.container, CaribouSlots++, 8, 18) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return itemStack.is(LOTags.Items.SADDLE) && !this.hasItem() && CaribouMenu.this.caribou.isSaddleable();
            }

            @Override
            public boolean isActive() {
                return CaribouMenu.this.caribou.isSaddleable();
            }
        });

        this.addSlot(new Slot(this.container, CaribouSlots++, 8, 36) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                if (itemStack.getItem() instanceof HorseArmorItem) {
                    return !this.hasItem() && CaribouMenu.this.caribou.canWearArmor();
                } else if (itemStack.is(LOTags.Items.DECOR_FOR_O_MOUNTS)) {
                    return !this.hasItem() && CaribouMenu.this.caribou.canWearArmor();
                }
                return false;
            }

            @Override
            public boolean isActive() {
                return CaribouMenu.this.caribou.canWearArmor();
            }
        });

//        this.addSlot(new Slot(this.container, CaribouSlots++, 8, 54) {
//            @Override
//            public boolean mayPlace(ItemStack itemStack) {
//                if (itemStack.getItem() instanceof HorseShoeItem) {
//                    return !this.hasItem() && CaribouMenu.this.Caribou.canWearShoes();
//                }
//                return false;
//            }
//
//            @Override
//            public boolean isActive() {
//                return CaribouMenu.this.Caribou.canWearShoes();
//            }
//        });

        if(this.caribou.hasChest()) {
            for(int y = 0; y < 3; y++) {
                for(int x = 0; x < 5; x++) {
                    this.addSlot(new Slot(this.container, CaribouSlots++, 80 + x * 18, 18 + y * 18));
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
        return !this.caribou.hasInventoryChanged(this.container) && this.container.stillValid(player) && this.caribou.isAlive() && this.caribou.distanceTo(player) < 8.0F;
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