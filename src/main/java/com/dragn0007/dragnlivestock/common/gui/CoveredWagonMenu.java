package com.dragn0007.dragnlivestock.common.gui;

import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractInventoryWagon;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class CoveredWagonMenu extends AbstractWagonMenu {

    public CoveredWagonMenu(int id, Inventory inventory, AbstractInventoryWagon wagon) {
        super(LOMenuTypes.COVERED_WAGON.get(), id, inventory, wagon, 9, 8, 104);
    }

    public CoveredWagonMenu(int id, Inventory inventory, FriendlyByteBuf data) {
        this(id, inventory, getWagon(inventory, data));
    }
}
