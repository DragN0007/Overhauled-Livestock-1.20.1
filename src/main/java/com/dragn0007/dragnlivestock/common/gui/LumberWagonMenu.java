package com.dragn0007.dragnlivestock.common.gui;

import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractInventoryWagon;
import com.dragn0007.dragnlivestock.util.LOTags;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class LumberWagonMenu extends AbstractWagonMenu {

    public LumberWagonMenu(int id, Inventory inventory, AbstractInventoryWagon wagon) {
        super(LOMenuTypes.LUMBER_WAGON.get(), id, inventory, wagon, 9, 8, 139);
    }

    public LumberWagonMenu(int id, Inventory inventory, FriendlyByteBuf data) {
        this(id, inventory, getWagon(inventory, data));
    }
}
