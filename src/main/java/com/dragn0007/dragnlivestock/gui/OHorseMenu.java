package com.dragn0007.dragnlivestock.gui;

import com.dragn0007.dragnlivestock.entities.horse.OHorse;
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

public class OHorseMenu extends AbstractContainerMenu {

    public Container container;
    public OHorse oHorse;

    public OHorseMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory, new SimpleContainer(extraData.readInt()), (OHorse) inventory.player.level().getEntity(extraData.readInt()));
    }

    public OHorseMenu(int containerId, Inventory inventory, Container container, OHorse abstractOMount) {
        super(LOMenuTypes.O_HORSE_MENU.get(), containerId);
        this.container = container;
        this.oHorse = abstractOMount;

        int oHorseSlots = 0;
        this.addSlot(new Slot(this.container, oHorseSlots++, 8, 18) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return itemStack.is(LOTags.Items.SADDLE) && !this.hasItem() && OHorseMenu.this.oHorse.isSaddleable();
            }

            @Override
            public boolean isActive() {
                return OHorseMenu.this.oHorse.isSaddleable();
            }
        });

        this.addSlot(new Slot(this.container, oHorseSlots++, 8, 36) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                if (itemStack.getItem() instanceof HorseArmorItem) {
                    return !this.hasItem() && OHorseMenu.this.oHorse.canWearArmor();
                } else if (itemStack.is(LOTags.Items.CAN_PLACE_ON_O_MOUNTS)) {
                    return !this.hasItem() && OHorseMenu.this.oHorse.canWearArmor();
                }
                return false;
            }

            @Override
            public boolean isActive() {
                return OHorseMenu.this.oHorse.canWearArmor();
            }
        });

//        this.addSlot(new Slot(this.container, oHorseSlots++, 8, 54) {
//            @Override
//            public boolean mayPlace(ItemStack itemStack) {
//                if (itemStack.getItem() instanceof HorseShoeItem) {
//                    return !this.hasItem() && OHorseMenu.this.oHorse.canWearShoes();
//                }
//                return false;
//            }
//
//            @Override
//            public boolean isActive() {
//                return OHorseMenu.this.oHorse.canWearShoes();
//            }
//        });

        if(this.oHorse.hasChest() && (this.oHorse.isStockBreed() || this.oHorse.isWarmbloodedBreed())) { //stock or warmblood
            for(int y = 0; y < 3; y++) {
                for(int x = 0; x < 3; x++) {
                    this.addSlot(new Slot(this.container, oHorseSlots++, 80 + x * 18, 18 + y * 18));
                }
            }
        }

        if(this.oHorse.hasChest() && this.oHorse.isDraftBreed()) { //draft or coldblood
            for(int y = 0; y < 3; y++) {
                for(int x = 0; x < 5; x++) {
                    this.addSlot(new Slot(this.container, oHorseSlots++, 80 + x * 18, 18 + y * 18));
                }
            }
        }

        if(this.oHorse.hasChest() && this.oHorse.isPonyBreed()) { //pony
            for(int y = 0; y < 3; y++) {
                for(int x = 0; x < 4; x++) {
                    this.addSlot(new Slot(this.container, oHorseSlots++, 80 + x * 18, 18 + y * 18));
                }
            }
        }

        if(this.oHorse.hasChest() && this.oHorse.isRacingBreed()) { //racer
            for(int y = 0; y < 3; y++) {
                for(int x = 0; x < 1; x++) {
                    this.addSlot(new Slot(this.container, oHorseSlots++, 80 + x * 18, 18 + y * 18));
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
        return !this.oHorse.hasInventoryChanged(this.container) && this.container.stillValid(player) && this.oHorse.isAlive() && this.oHorse.distanceTo(player) < 8.0F;
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