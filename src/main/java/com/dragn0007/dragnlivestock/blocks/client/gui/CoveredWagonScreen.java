package com.dragn0007.dragnlivestock.blocks.client.gui;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.menus.CoveredWagonMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CoveredWagonScreen extends AbstractWagonScreen<CoveredWagonMenu> {

    public CoveredWagonScreen(CoveredWagonMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, LivestockOverhaul.id("textures/gui/covered_wagon.png"), 256, 256);
        this.imageWidth = 176;
        this.imageHeight = 186;
        this.inventoryLabelX = 8;
        this.inventoryLabelY = 91;
    }

}
