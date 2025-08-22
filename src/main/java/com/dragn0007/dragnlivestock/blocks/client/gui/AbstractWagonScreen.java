package com.dragn0007.dragnlivestock.blocks.client.gui;

import com.dragn0007.dragnlivestock.common.menus.AbstractWagonMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public abstract class AbstractWagonScreen<T extends AbstractWagonMenu> extends AbstractContainerScreen<T> {

    private final ResourceLocation texture;
    private final int texWidth;
    private final int texHeight;

    public AbstractWagonScreen(T menu, Inventory inventory, Component title, ResourceLocation texture, int texWidth, int texHeight) {
        super(menu, inventory, title);
        this.texture = texture;
        this.texWidth = texWidth;
        this.texHeight = texHeight;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);
        renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.blit(texture, leftPos, topPos, 0, 0, imageWidth, imageHeight, texWidth, texHeight);
    }

}
