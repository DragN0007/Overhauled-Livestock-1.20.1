package com.dragn0007.dragnlivestock.common.menus;

import com.dragn0007.dragnlivestock.common.entities.mule.OMule;
import com.dragn0007.dragnlivestock.common.items.custom.LightHorseArmorItem;
import com.dragn0007.dragnlivestock.common.LOTags;
import net.minecraft.network.FriendlyByteBuf;
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
                if (itemStack.getItem() instanceof HorseArmorItem || itemStack.getItem() instanceof LightHorseArmorItem) {
                    return !this.hasItem() && OMuleMenu.this.oMule.canWearArmor();
                }
                if (itemStack.is(LOTags.Items.ARMOR_FOR_O_MOUNTS)) {
                    return !this.hasItem() && OMuleMenu.this.oMule.canWearArmor();
                }
                return false;
            }

            @Override
            public boolean isActive() {
                return OMuleMenu.this.oMule.canWearArmor();
            }
        });

        this.addSlot(new Slot(this.container, OMuleSlots++, 8, 54) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                if (itemStack.is(LOTags.Items.DECOR_FOR_O_MOUNTS)) {
                    return !this.hasItem() && OMuleMenu.this.oMule.canWearArmor();
                }
                return false;
            }

            @Override
            public boolean isActive() {
                return OMuleMenu.this.oMule.canWearArmor();
            }
        });

        if(this.oMule.hasChest()) {
            for(int y = 0; y < 3; y++) {
                for(int x = 0; x < 5; x++) {
                    this.addSlot(new Slot(this.container, OMuleSlots++, 80 + x * 18, 18 + y * 18));
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
        return !this.oMule.hasInventoryChanged(this.container) && this.container.stillValid(player) && this.oMule.isAlive() && this.oMule.distanceTo(player) < 8.0F;
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