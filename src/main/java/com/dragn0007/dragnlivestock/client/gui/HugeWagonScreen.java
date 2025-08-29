package com.dragn0007.dragnlivestock.client.gui;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.gui.DefaultWagonMenu;
import com.dragn0007.dragnlivestock.common.gui.HugeWagonMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class HugeWagonScreen extends AbstractWagonScreen<HugeWagonMenu> {

    public HugeWagonScreen(HugeWagonMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, LivestockOverhaul.id("textures/gui/huge_wagon.png"), 256, 256);
        this.imageWidth = 248;
        this.imageHeight = 256;
        this.inventoryLabelX = 43;
        this.inventoryLabelY = 161;
    }

}
