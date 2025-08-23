package com.dragn0007.dragnlivestock.client.gui;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.gui.OMuleMenu;
import com.dragn0007.dragnlivestock.entities.mule.OMule;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;

import java.text.DecimalFormat;

public class OMuleScreen extends AbstractContainerScreen<OMuleMenu> {

    public static final ResourceLocation MULE_INVENTORY_LOCATION = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/o_horse.png");
    public final OMule oMule;
    public int breedLabelX;
    public int breedLabelY;
    public int baseColorLabelX;
    public int baseColorLabelY;
    public int markingLabelX;
    public int markingLabelY;
    public int speedLabelX;
    public int speedLabelY;
    public int jumpStrengthLabelX;
    public int jumpStrengthLabelY;
    public int healthLabelX;
    public int healthLabelY;
    public int genderFLabelX;
    public int genderMLabelX;
    public int genderLabelY;

    public OMuleScreen(OMuleMenu oMuleMenu, Inventory inventory, Component component) {
        super(oMuleMenu, inventory, component);
        this.oMule = oMuleMenu.oMule;
    }

    @Override
    public void init() {
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        genderFLabelX = leftPos + 140;
        genderMLabelX = leftPos + 152;
        genderLabelY = topPos - 8;

        breedLabelX = leftPos + 1;
        breedLabelY = topPos - 8;

        baseColorLabelX = leftPos + 1;
        baseColorLabelY = topPos + 170;

        markingLabelX = leftPos + 1;
        markingLabelY = topPos + 180;

        jumpStrengthLabelX = leftPos + 1;
        jumpStrengthLabelY = topPos + 190;

        speedLabelX = leftPos + 1;
        speedLabelY = topPos + 200;

        healthLabelX = leftPos + 1;
        healthLabelY = topPos + 210;
    }

    public void renderBg(GuiGraphics graphics, float f, int i, int j) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, MULE_INVENTORY_LOCATION);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        graphics.blit(MULE_INVENTORY_LOCATION, x, y, 0, 0, this.imageWidth, this.imageHeight);


        if (this.oMule.isSaddleable()) {
            graphics.blit(MULE_INVENTORY_LOCATION, x + 7, y + 17, 18, this.imageHeight + 54, 18, 18);
        }

        if (this.oMule.canWearArmor()) {
            graphics.blit(MULE_INVENTORY_LOCATION, x + 7, y + 35, 0, this.imageHeight + 54, 18, 18);
            graphics.blit(MULE_INVENTORY_LOCATION, x + 7, y + 53, 36, this.imageHeight + 54, 18, 18);
        }

        if (this.oMule.hasChest()) {
            graphics.blit(MULE_INVENTORY_LOCATION, x + 79, y + 17, 0, this.imageHeight, 90, 54);
        }

//        if (this.OMule.canWearShoes()) {
//            graphics.blit(HORSE_INVENTORY_LOCATION, x + 7, y + 53, 54, this.imageHeight + 54, 18, 18);
//        }

        if (this.oMule.isFemale()) {
            graphics.blit(MULE_INVENTORY_LOCATION, x + 161, y + 9, 90, this.imageHeight + 54, 8, 8);
        }

        if (this.oMule.isMale()) {
            graphics.blit(MULE_INVENTORY_LOCATION, x + 161, y + 9, 98, this.imageHeight + 54, 8, 8);
        }

        InventoryScreen.renderEntityInInventoryFollowsMouse(graphics, x + 51, y + 60, 17, (float)(x + 51), (float)(y + 75 - 50), this.oMule);

        renderBreedLabel(graphics);

        if (LivestockOverhaulClientConfig.HORSE_COAT_GUI.get()) {
            renderBaseCoatLabel(graphics);
            renderMarkingLabel(graphics);
            renderSpeedLabel(graphics);
            renderJumpStrengthLabel(graphics);
            renderHealthLabel(graphics);
        }

        if (LivestockOverhaulClientConfig.ACCESSIBILITY_GENDER_IDENTIFIER.get()) {
            renderGenderLabel(graphics);
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
        String breedText = getBreedText(this.oMule.getBreed());
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
        String text = this.oMule.getTextureResource().toString(); //texture name
        String noFillerText = text.replaceAll(".+mule/", ""); //remove 'mule_' and anything before it
        String noUnderscoresText = noFillerText.replaceAll("_", " "); //replace any underscores with spaces
        String noPNGText = noUnderscoresText.replace(".png", ""); //remove '.png'
        String replaceDefault = noPNGText.replace("default", "red");
        String labelText = "Base Coat: " + replaceDefault.toUpperCase(); //print just the coat name

        String noTextureText = "Base Coat: " + "No Coat Found.";

        if (this.oMule.getTextureResource() == null) {
            graphics.drawString(this.font, noTextureText, baseColorLabelX, baseColorLabelY, 0xFFFFFF, false);
        } else {
            graphics.drawString(this.font, labelText, baseColorLabelX, baseColorLabelY, 0xFFFFFF, false);
        }
    }

    private void renderMarkingLabel(GuiGraphics graphics) {
        String text = this.oMule.getOverlayLocation().toString();
        String noFillerText = text.replaceAll(".+overlay/", "");
        String noUnderscoresText = noFillerText.replaceAll("_", " ");
        String noPNGText = noUnderscoresText.replace(".png", "");
        String addPinkNoseText = noPNGText.replace("pink", "pink-nosed");
        String labelText = "Marking(s): " + addPinkNoseText.toUpperCase();

        String noTextureText = "Marking(s): " + "No Marking Found.";

        if (this.oMule.getTextureResource() == null) {
            graphics.drawString(this.font, noTextureText, markingLabelX, markingLabelY, 0xFFFFFF, false);
        } else {
            graphics.drawString(this.font, labelText, markingLabelX, markingLabelY, 0xFFFFFF, false);
        }
    }

    private void renderGenderLabel(GuiGraphics graphics) {
        String female = "FEMALE";
        String male = "MALE";
        String error = "NBT Error";

        if (this.oMule.getGender() == 0) {
            graphics.drawString(this.font, female, genderFLabelX, genderLabelY, 0xFFFFFF, false);
        } else if (this.oMule.getGender() == 1) {
            graphics.drawString(this.font, male, genderMLabelX, genderLabelY, 0xFFFFFF, false);
        } else {
            graphics.drawString(this.font, error, genderFLabelX, genderLabelY, 0xFFFFFF, false);
        }
    }


    //Code & Calculations from Jade, by Snowee, under the Creative Commons License (https://github.com/Snownee/Jade/tree/1.20-forge) v
    //https://github.com/Snownee/Jade/blob/1.20-forge/src/main/java/snownee/jade/addon/vanilla/HorseStatsProvider.java#L51
    //These calculations are placed in this manner so that the numbers will match up with Jade's tooltip.
    //If use of this code and/ or these calculations are no longer permitted, for any reason, please contact me
    // at DragN0007 on Curseforge or dragn0007.jar on Discord. I will remove them, no questions asked. :)
    public static double getJumpHeight(double jump) {
        return -0.1817584952 * jump * jump * jump + 3.689713992 * jump * jump + 2.128599134 * jump - 0.343930367;
    }

    //This code is slightly altered to fit as a label rather than a tooltip
    private void renderSpeedLabel(GuiGraphics graphics) {

        double speed = oMule.getAttributeBaseValue(Attributes.MOVEMENT_SPEED) * 42.16;

        DecimalFormat limitDec = new DecimalFormat("#.###");
        String num = limitDec.format(speed);
        String labelText = "Speed: " + num;

        graphics.drawString(this.font, labelText, speedLabelX, speedLabelY, 0xFFFFFF, false);
    }

    //This code is slightly altered to fit as a label rather than a tooltip
    private void renderJumpStrengthLabel(GuiGraphics graphics) {

        oMule.getAttributes().hasAttribute(Attributes.JUMP_STRENGTH);
        double jumpStrength = oMule.getAttributeBaseValue(Attributes.JUMP_STRENGTH);
        double jumpHeight = getJumpHeight(jumpStrength);

        DecimalFormat limitDec = new DecimalFormat("#.###");
        String num = limitDec.format(jumpHeight);
        String labelText = "Jump Strength: " + num;

        graphics.drawString(this.font, labelText, jumpStrengthLabelX, jumpStrengthLabelY, 0xFFFFFF, false);
    }
    //End of CC-Licensed code ^


    private void renderHealthLabel(GuiGraphics graphics) {
        String text = String.valueOf(this.oMule.getMaxHealth());
        String labelText = "Max Health: " + text;

        graphics.drawString(this.font, labelText, healthLabelX, healthLabelY, 0xFFFFFF, false);
    }

}


