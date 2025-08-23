package com.dragn0007.dragnlivestock.client.gui;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.gui.GoodsCartMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class GoodsCartScreen extends AbstractWagonScreen<GoodsCartMenu> {

    public GoodsCartScreen(GoodsCartMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, LivestockOverhaul.id("textures/gui/goods_cart.png"), 256, 256);
        this.imageWidth = 176;
        this.imageHeight = 186;
        this.inventoryLabelX = 8;
        this.inventoryLabelY = 91;
    }

}
