package com.dragn0007.dragnlivestock.gui;

import com.dragn0007.dragnlivestock.entities.util.AbstractOHorse;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class OHorseScreen extends AbstractContainerScreen<OHorseMenu> {

    public static final ResourceLocation HORSE_INVENTORY_LOCATION = new ResourceLocation("textures/gui/container/horse.png");
    public final AbstractOHorse oHorse;

    public OHorseScreen(OHorseMenu oHorseMenu, Inventory inventory, Component component) {
        super(oHorseMenu, inventory, component);
        this.oHorse = oHorseMenu.oHorse;
    }

    public void renderBg(GuiGraphics graphics, float p_282998_, int p_282929_, int p_283133_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, HORSE_INVENTORY_LOCATION);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        graphics.blit(HORSE_INVENTORY_LOCATION, x, y, 0, 0, this.imageWidth, this.imageHeight);

        if (this.oHorse.hasChest()) {
            graphics.blit(HORSE_INVENTORY_LOCATION,x + 79, y + 17, 0, this.imageHeight, 90, 54);
        }

        if (this.oHorse.isSaddleable()) {
            graphics.blit(HORSE_INVENTORY_LOCATION,x + 7, y + 17, 18, this.imageHeight + 54, 18, 18);
        }

        if (this.oHorse.canWearArmor()) {
            graphics.blit(HORSE_INVENTORY_LOCATION,x + 7, y + 35, 0, this.imageHeight + 54, 18, 18);
        }

        InventoryScreen.renderEntityInInventoryFollowsMouse(graphics, x + 51, y + 60, 17, (float)(x + 51), (float)(y + 75 - 50), this.oHorse);
    }

    @Override
    public void render(GuiGraphics p_281697_, int p_282103_, int p_283529_, float p_283079_) {
        this.renderBackground(p_281697_);
        super.render(p_281697_, p_282103_, p_283529_, p_283079_);
        this.renderTooltip(p_281697_, p_282103_, p_283529_);
    }
}
