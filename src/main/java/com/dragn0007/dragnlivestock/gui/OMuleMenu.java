package com.dragn0007.dragnlivestock.gui;

import com.dragn0007.dragnlivestock.entities.mule.OMule;
import com.dragn0007.dragnlivestock.util.LOTags;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;

public class OMuleMenu extends AbstractContainerMenu {

    public Container container;
    public OMule oMule;

    public OMuleMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory, new SimpleContainer(extraData.readInt()), (OMule) inventory.player.level().getEntity(extraData.readInt()));
    }

    public OMuleMenu(int containerId, Inventory inventory, Container container, OMule abstractOMount) {
        super(LOMenuTypes.O_MULE_MENU.get(), containerId);
        this.container = container;
        this.oMule = abstractOMount;

        int OMuleSlots = 0;
        this.addSlot(new Slot(this.container, OMuleSlots++, 8, 18) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return itemStack.is(LOTags.Items.SADDLE) && !this.hasItem() && OMuleMenu.this.oMule.isSaddleable();
            }

            @Override
            public boolean isActive() {
                return OMuleMenu.this.oMule.isSaddleable();
            }
        });

        this.addSlot(new Slot(this.container, OMuleSlots++, 8, 36) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                if (itemStack.getItem() instanceof HorseArmorItem) {
                    return !this.hasItem() && OMuleMenu.this.oMule.canWearArmor();
                } else if (itemStack.is(ItemTags.WOOL_CARPETS)) {
                    return !this.hasItem() && OMuleMenu.this.oMule.canWearArmor();
                }
                return false;
            }

            @Override
            public boolean isActive() {
                return OMuleMenu.this.oMule.canWearArmor();
            }
        });

//        this.addSlot(new Slot(this.container, OMuleSlots++, 8, 54) {
//            @Override
//            public boolean mayPlace(ItemStack itemStack) {
//                if (itemStack.getItem() instanceof HorseShoeItem) {
//                    return !this.hasItem() && OMuleMenu.this.OMule.canWearShoes();
//                }
//                return false;
//            }
//
//            @Override
//            public boolean isActive() {
//                return OMuleMenu.this.OMule.canWearShoes();
//            }
//        });

        if(this.oMule.hasChest()) {
            for(int y = 0; y < 3; y++) {
                for(int x = 0; x < 5; x++) {
                    this.addSlot(new Slot(this.container, OMuleSlots++, 80 + x * 18, 18 + y * 18));
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
        return !this.oMule.hasInventoryChanged(this.container) && this.container.stillValid(player) && this.oMule.isAlive() && this.oMule.distanceTo(player) < 8.0F;
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