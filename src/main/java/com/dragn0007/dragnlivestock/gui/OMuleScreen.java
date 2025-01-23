package com.dragn0007.dragnlivestock.gui;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.mule.OMule;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class OMuleScreen extends AbstractContainerScreen<OMuleMenu> {

    public static final ResourceLocation MULE_INVENTORY_LOCATION = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/o_horse.png");
    public final OMule OMule;
    protected int breedLabelX;
    protected int breedLabelY;
    protected int baseColorLabelX;
    protected int baseColorLabelY;
    protected int markingLabelX;
    protected int markingLabelY;

    public OMuleScreen(OMuleMenu oMuleMenu, Inventory inventory, Component component) {
        super(oMuleMenu, inventory, component);
        this.OMule = oMuleMenu.oMule;
    }

    @Override
    protected void init() {
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        breedLabelX = leftPos + 1;
        breedLabelY = topPos - 8;

        baseColorLabelX = leftPos + 1;
        baseColorLabelY = topPos + 170;

        markingLabelX = leftPos + 1;
        markingLabelY = topPos + 180;
    }

    public void renderBg(GuiGraphics graphics, float f, int i, int j) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, MULE_INVENTORY_LOCATION);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        graphics.blit(MULE_INVENTORY_LOCATION, x, y, 0, 0, this.imageWidth, this.imageHeight);


        if (this.OMule.isSaddleable()) {
            graphics.blit(MULE_INVENTORY_LOCATION, x + 7, y + 17, 18, this.imageHeight + 54, 18, 18);
        }

        if (this.OMule.canWearArmor()) {
            graphics.blit(MULE_INVENTORY_LOCATION, x + 7, y + 35, 0, this.imageHeight + 54, 18, 18);
        }

//        if (this.OMule.canWearShoes()) {
//            graphics.blit(HORSE_INVENTORY_LOCATION, x + 7, y + 53, 54, this.imageHeight + 54, 18, 18);
//        }

        if (this.OMule.isFemale()) {
            graphics.blit(MULE_INVENTORY_LOCATION, x + 161, y + 9, 90, this.imageHeight + 54, 8, 8);
        }

        if (this.OMule.isMale()) {
            graphics.blit(MULE_INVENTORY_LOCATION, x + 161, y + 9, 98, this.imageHeight + 54, 8, 8);
        }

        InventoryScreen.renderEntityInInventoryFollowsMouse(graphics, x + 51, y + 60, 17, (float)(x + 51), (float)(y + 75 - 50), this.OMule);

        renderBreedLabel(graphics);

        if (LivestockOverhaulClientConfig.HORSE_COAT_GUI.get()) {
            renderBaseCoatLabel(graphics);
            renderMarkingLabel(graphics);
        }
    }

    @Override
    public void render(GuiGraphics graphics, int i, int i1, float v) {
        this.renderBackground(graphics);
        super.render(graphics, i, i1, v);
        this.renderTooltip(graphics, i, i1);
        this.renderBreedLabel(graphics);
    }

    private void renderBreedLabel(GuiGraphics graphics) {
        String breedText = getBreedText(this.OMule.getBreed());
        String labelText = "Body Type: " + breedText;

        graphics.drawString(this.font, labelText, breedLabelX, breedLabelY, 0xFFFFFF, false);
    }

    private String getBreedText(int breed) {
        switch (breed) {
            case 0: return "Stock";
            case 1: return "Miniature";
            case 2: return "Draft";
            default: return "Unknown";
        }
    }

    private void renderBaseCoatLabel(GuiGraphics graphics) {
        String text = this.OMule.getTextureResource().toString(); //texture name
        String noFillerText = text.replaceAll(".+mule_", ""); //remove 'mule_' and anything before it
        String noUnderscoresText = noFillerText.replaceAll("_", " "); //replace any underscores with spaces
        String noPNGText = noUnderscoresText.replace(".png", ""); //remove '.png'
        String replaceDefault = noPNGText.replace("default", "red");
        String labelText = "Base Coat: " + replaceDefault.toUpperCase(); //print just the coat name

        String noTextureText = "Base Coat: " + "No Coat Found.";

        if (this.OMule.getTextureResource() == null) {
            graphics.drawString(this.font, noTextureText, baseColorLabelX, baseColorLabelY, 0xFFFFFF, false);
        } else {
            graphics.drawString(this.font, labelText, baseColorLabelX, baseColorLabelY, 0xFFFFFF, false);
        }
    }

    private void renderMarkingLabel(GuiGraphics graphics) {
        String text = this.OMule.getOverlayLocation().toString();
        String noFillerText = text.replaceAll(".+overlay_", "");
        String noUnderscoresText = noFillerText.replaceAll("_", " ");
        String noPNGText = noUnderscoresText.replace(".png", "");
        String addPinkNoseText = noPNGText.replace("pink", "pink-nosed");
        String labelText = "Marking(s): " + addPinkNoseText.toUpperCase();

        String noTextureText = "Marking(s): " + "No Marking Found.";

        if (this.OMule.getTextureResource() == null) {
            graphics.drawString(this.font, noTextureText, markingLabelX, markingLabelY, 0xFFFFFF, false);
        } else {
            graphics.drawString(this.font, labelText, markingLabelX, markingLabelY, 0xFFFFFF, false);
        }
    }

}


