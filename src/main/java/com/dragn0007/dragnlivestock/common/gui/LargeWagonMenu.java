package com.dragn0007.dragnlivestock.common.gui;

import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractInventoryWagon;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class LargeWagonMenu extends AbstractWagonMenu {

    public LargeWagonMenu(int id, Inventory inventory, AbstractInventoryWagon wagon) {
        super(LOMenuTypes.LARGE_INVENTORY_WAGON.get(), id, inventory, wagon, 9, 8, 139);
    }

    public LargeWagonMenu(int id, Inventory inventory, FriendlyByteBuf data) {
        this(id, inventory, getWagon(inventory, data));
    }
}
