package com.dragn0007.dragnlivestock.blocks.client.gui;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.common.menus.OxMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class OxScreen extends AbstractContainerScreen<OxMenu> {
    public static final ResourceLocation OX_INVENTORY_LOCATION = LivestockOverhaul.id("textures/gui/ox.png");
    public final AbstractOMount ox;

    public OxScreen(OxMenu oxMenu, Inventory inventory, Component component) {
        super(oxMenu, inventory, component);
        this.ox = oxMenu.ox;
    }

    public void renderBg(GuiGraphics graphics, float p_282998_, int p_282929_, int p_283133_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, OX_INVENTORY_LOCATION);

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        graphics.blit(OX_INVENTORY_LOCATION, x, y, 0, 0, this.imageWidth, this.imageHeight);

        if (this.ox.hasChest()) {
            graphics.blit(OX_INVENTORY_LOCATION, x + 25, y + 17, 0, this.imageHeight, 145, 54);
        }

        if (this.ox.isSaddleable()) {
            graphics.blit(OX_INVENTORY_LOCATION, x + 7, y + 17, 18, this.imageHeight + 54, 18, 18);
        }

        if (this.ox.canWearArmor()) {
            graphics.blit(OX_INVENTORY_LOCATION, x + 7, y + 17, 36, this.imageHeight + 54, 18, 18);
        }
    }

    @Override
    public void render(GuiGraphics p_281697_, int p_282103_, int p_283529_, float p_283079_) {
        this.renderBackground(p_281697_);
        super.render(p_281697_, p_282103_, p_283529_, p_283079_);
        this.renderTooltip(p_281697_, p_282103_, p_283529_);
    }
}

