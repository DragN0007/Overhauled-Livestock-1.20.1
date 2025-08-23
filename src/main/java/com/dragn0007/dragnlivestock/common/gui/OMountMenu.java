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
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;

public class OMountMenu extends AbstractContainerMenu {

    public Container container;
    public AbstractOMount oMount;

    public OMountMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory, new SimpleContainer(extraData.readInt()), (AbstractOMount) inventory.player.level().getEntity(extraData.readInt()));
    }

    public OMountMenu(int containerId, Inventory inventory, Container container, AbstractOMount abstractOMount) {
        super(LOMenuTypes.O_MOUNT_MENU.get(), containerId);
        this.container = container;
        this.oMount = abstractOMount;

        int oMountSlots = 0;
        this.addSlot(new Slot(this.container, oMountSlots++, 8, 18) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return itemStack.is(LOTags.Items.SADDLE) && !this.hasItem() && OMountMenu.this.oMount.isSaddleable();
            }

            @Override
            public boolean isActive() {
                return OMountMenu.this.oMount.isSaddleable();
            }
        });

        this.addSlot(new Slot(this.container, oMountSlots++, 8, 36) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                if (itemStack.getItem() instanceof HorseArmorItem) {
                    return !this.hasItem() && OMountMenu.this.oMount.canWearArmor();
                } else if (itemStack.is(LOTags.Items.DECOR_FOR_O_MOUNTS)) {
                    return !this.hasItem() && OMountMenu.this.oMount.canWearArmor();
                }
                return false;
            }

            @Override
            public boolean isActive() {
                return OMountMenu.this.oMount.canWearArmor();
            }
        });

        if(this.oMount.hasChest()) {
            for(int y = 0; y < 3; y++) {
                for(int x = 0; x < 5; x++) {
                    this.addSlot(new Slot(this.container, oMountSlots++, 80 + x * 18, 18 + y * 18));
                }
            }
        }

        // NOTE(EVNGLX): you MUST BE THIS WAY, I had the whole thing backwards before. sorry my bad
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
        return !this.oMount.hasInventoryChanged(this.container) && this.container.stillValid(player) && this.oMount.isAlive() && this.oMount.distanceTo(player) < 8.0F;
    }

    public ItemStack quickMoveStack(Player player, int slotId) {
        Slot slot = this.slots.get(slotId);
        if(!slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack itemStack = slot.getItem();
        ItemStack itemStackCopy = itemStack.copy();
        int containerSize = this.container.getContainerSize();

        if(slotId < containerSize) {
            if(!this.moveItemStackTo(itemStack, containerSize, containerSize + 36, true)) {
                return ItemStack.EMPTY;
            }
        } else if(slotId < containerSize + 36) {
            if(!this.moveItemStackTo(itemStack, 0, containerSize, false)) {
                return ItemStack.EMPTY;
            }
        }

        if(itemStack.getCount() == 0) {
            slot.set(ItemStack.EMPTY);
        }
        slot.setChanged();
        return itemStackCopy;
    }
}