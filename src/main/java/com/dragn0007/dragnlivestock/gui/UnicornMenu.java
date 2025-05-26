package com.dragn0007.dragnlivestock.gui;

import com.dragn0007.dragnlivestock.entities.unicorn.Unicorn;
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

public class UnicornMenu extends AbstractContainerMenu {

    public Container container;
    public Unicorn unicorn;

    public UnicornMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory, new SimpleContainer(extraData.readInt()), (Unicorn) inventory.player.level().getEntity(extraData.readInt()));
    }

    public UnicornMenu(int containerId, Inventory inventory, Container container, Unicorn abstractOMount) {
        super(LOMenuTypes.UNICORN_MENU.get(), containerId);
        this.container = container;
        this.unicorn = abstractOMount;

        int unicornSlots = 0;
        this.addSlot(new Slot(this.container, unicornSlots++, 8, 18) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return itemStack.is(LOTags.Items.SADDLE) && !this.hasItem() && UnicornMenu.this.unicorn.isSaddleable();
            }

            @Override
            public boolean isActive() {
                return UnicornMenu.this.unicorn.isSaddleable();
            }
        });

        this.addSlot(new Slot(this.container, unicornSlots++, 8, 36) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                if (itemStack.getItem() instanceof HorseArmorItem) {
                    return !this.hasItem() && UnicornMenu.this.unicorn.canWearArmor();
                }
                if (itemStack.is(LOTags.Items.ARMOR_FOR_O_MOUNTS)) {
                    return !this.hasItem() && UnicornMenu.this.unicorn.canWearArmor();
                }
                return false;
            }

            @Override
            public boolean isActive() {
                return UnicornMenu.this.unicorn.canWearArmor();
            }
        });

        this.addSlot(new Slot(this.container, unicornSlots++, 8, 54) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                if (itemStack.is(LOTags.Items.DECOR_FOR_O_MOUNTS)) {
                    return !this.hasItem() && UnicornMenu.this.unicorn.canWearArmor();
                }
                return false;
            }

            @Override
            public boolean isActive() {
                return UnicornMenu.this.unicorn.canWearArmor();
            }
        });

        if(this.unicorn.hasChest()) {
            for(int y = 0; y < 3; y++) {
                for(int x = 0; x < 5; x++) {
                    this.addSlot(new Slot(this.container, unicornSlots++, 80 + x * 18, 18 + y * 18));
                }
            }
        }

        if(this.unicorn.hasChest() && this.unicorn.isRacingBreed()) { //racer
            for(int y = 0; y < 3; y++) {
                for(int x = 0; x < 1; x++) {
                    this.addSlot(new Slot(this.container, unicornSlots++, 80 + x * 18, 18 + y * 18));
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
        return !this.unicorn.hasInventoryChanged(this.container) && this.container.stillValid(player) && this.unicorn.isAlive() && this.unicorn.distanceTo(player) < 8.0F;
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