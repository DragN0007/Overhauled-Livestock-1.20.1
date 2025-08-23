package com.dragn0007.dragnlivestock.client.gui;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.gui.OMountMenu;
import com.dragn0007.dragnlivestock.entities.camel.OCamel;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class OMountScreen extends AbstractContainerScreen<OMountMenu> {

    public static final ResourceLocation MOUNT_INVENTORY_LOCATION = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/o_mount.png");
    public final AbstractOMount oMount;

    public OMountScreen(OMountMenu oMountMenu, Inventory inventory, Component component) {
        super(oMountMenu, inventory, component);
        this.oMount = oMountMenu.oMount;
    }

    @Override
    public void init() {
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;
    }

    public void renderBg(GuiGraphics graphics, float f, int i, int j) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, MOUNT_INVENTORY_LOCATION);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        graphics.blit(MOUNT_INVENTORY_LOCATION, x, y, 0, 0, this.imageWidth, this.imageHeight);

        if (this.oMount.hasChest()) {
            graphics.blit(MOUNT_INVENTORY_LOCATION,x + 79, y + 17, 0, this.imageHeight, 90, 54);
        }

        if (this.oMount.isSaddleable()) {
            graphics.blit(MOUNT_INVENTORY_LOCATION,x + 7, y + 17, 18, this.imageHeight + 54, 18, 18);
        }

        if (this.oMount.canWearArmor()) {
            graphics.blit(MOUNT_INVENTORY_LOCATION,x + 7, y + 35, 0, this.imageHeight + 54, 18, 18);
        }

        if (this.oMount.canWearArmor()) {
            if (this.oMount instanceof OCamel) {
                graphics.blit(MOUNT_INVENTORY_LOCATION, x + 7, y + 35, 36, this.imageHeight + 54, 18, 18);
            } else {
                graphics.blit(MOUNT_INVENTORY_LOCATION, x + 7, y + 35, 0, this.imageHeight + 54, 18, 18);
            }
        }

        if (this.oMount.isFemale()) {
            graphics.blit(MOUNT_INVENTORY_LOCATION, x + 161, y + 9, 90, this.imageHeight + 54, 8, 8);
        }

        if (this.oMount.isMale()) {
            graphics.blit(MOUNT_INVENTORY_LOCATION, x + 161, y + 9, 98, this.imageHeight + 54, 8, 8);
        }

        InventoryScreen.renderEntityInInventoryFollowsMouse(graphics, x + 51, y + 60, 17, (float)(x + 51), (float)(y + 75 - 50), this.oMount);
    }

    @Override
    public void render(GuiGraphics graphics, int i, int i1, float v) {
        this.renderBackground(graphics);
        super.render(graphics, i, i1, v);
        this.renderTooltip(graphics, i, i1);
    }

}

