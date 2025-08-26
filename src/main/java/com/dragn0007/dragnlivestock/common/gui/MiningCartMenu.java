package com.dragn0007.dragnlivestock.common.gui;

import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractInventoryWagon;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class MiningCartMenu extends AbstractWagonMenu {

    public MiningCartMenu(int id, Inventory inventory, AbstractInventoryWagon wagon) {
        super(LOMenuTypes.MINING_CART.get(), id, inventory, wagon, 9, 8, 139);
    }

    public MiningCartMenu(int id, Inventory inventory, FriendlyByteBuf data) {
        this(id, inventory, getWagon(inventory, data));
    }
}
