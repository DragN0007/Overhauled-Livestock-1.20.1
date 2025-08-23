package com.dragn0007.dragnlivestock.client.gui;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.gui.TransportCartMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class TransportCartScreen extends AbstractWagonScreen<TransportCartMenu> {

    public TransportCartScreen(TransportCartMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, LivestockOverhaul.id("textures/gui/transport_cart.png"), 256, 256);
        this.imageWidth = 176;
        this.imageHeight = 186;
        this.inventoryLabelX = 8;
        this.inventoryLabelY = 91;
    }

}
