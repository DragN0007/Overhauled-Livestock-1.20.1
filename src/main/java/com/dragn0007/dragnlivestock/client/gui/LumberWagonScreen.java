package com.dragn0007.dragnlivestock.client.gui;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.gui.GoodsCartMenu;
import com.dragn0007.dragnlivestock.common.gui.LumberWagonMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class LumberWagonScreen extends AbstractWagonScreen<LumberWagonMenu> {

    public LumberWagonScreen(LumberWagonMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, LivestockOverhaul.id("textures/gui/lumber_wagon.png"), 256, 256);
        this.imageWidth = 176;
        this.imageHeight = 221;
        this.inventoryLabelX = 8;
        this.inventoryLabelY = 126;
    }

}
