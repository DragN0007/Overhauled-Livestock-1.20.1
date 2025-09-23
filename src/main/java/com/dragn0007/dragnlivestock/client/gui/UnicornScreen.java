package com.dragn0007.dragnlivestock.client.gui;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.gui.UnicornMenu;
import com.dragn0007.dragnlivestock.entities.unicorn.Unicorn;
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

public class UnicornScreen extends AbstractContainerScreen<UnicornMenu> {

    public static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/o_horse.png");

    public final Unicorn unicorn;
    public int breedLabelX;
    public int breedLabelY;
    public int baseColorLabelX;
    public int baseColorLabelY;
    public int markingLabelX;
    public int markingLabelY;
    public int hornLabelX;
    public int hornLabelY;
    public int speedLabelX;
    public int speedLabelY;
    public int jumpStrengthLabelX;
    public int jumpStrengthLabelY;
    public int healthLabelX;
    public int healthLabelY;
    public int genderFLabelX;
    public int genderMLabelX;
    public int genderLabelY;

    public UnicornScreen(UnicornMenu unicornMenu, Inventory inventory, Component component) {
        super(unicornMenu, inventory, component);
        this.unicorn = unicornMenu.unicorn;
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

        hornLabelX = leftPos + 1;
        hornLabelY = topPos + 190;

        jumpStrengthLabelX = leftPos + 1;
        jumpStrengthLabelY = topPos + 200;

        speedLabelX = leftPos + 1;
        speedLabelY = topPos + 210;

        healthLabelX = leftPos + 1;
        healthLabelY = topPos + 220;
    }

    public void renderBg(GuiGraphics graphics, float f, int i, int j) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, RESOURCE_LOCATION);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        graphics.blit(RESOURCE_LOCATION, x, y, 0, 0, this.imageWidth, this.imageHeight);


        if (this.unicorn.hasChest() && (this.unicorn.isStockBreed() || this.unicorn.isWarmbloodedBreed())) { //stock or warmblood
            graphics.blit(RESOURCE_LOCATION, x + 79, y + 17, 0, this.imageHeight, 54, 54);
        }

        if (this.unicorn.hasChest()) {
            graphics.blit(RESOURCE_LOCATION, x + 79, y + 17, 0, this.imageHeight, 90, 54);
        }


        if (this.unicorn.isSaddleable()) {
            graphics.blit(RESOURCE_LOCATION, x + 7, y + 17, 18, this.imageHeight + 54, 18, 18);
        }

        if (this.unicorn.canWearArmor()) {
            graphics.blit(RESOURCE_LOCATION, x + 7, y + 35, 0, this.imageHeight + 54, 18, 18);
            graphics.blit(RESOURCE_LOCATION, x + 7, y + 53, 36, this.imageHeight + 54, 18, 18);
        }

        if (this.unicorn.isFemale()) {
            if (this.unicorn.isSnipped()) {
                graphics.blit(RESOURCE_LOCATION, x + 161, y + 9, 107, this.imageHeight + 54, 8, 8);
            } else {
                graphics.blit(RESOURCE_LOCATION, x + 161, y + 9, 90, this.imageHeight + 54, 8, 8);
            }
        }

        if (this.unicorn.isMale()) {
            if (this.unicorn.isSnipped()) {
                graphics.blit(RESOURCE_LOCATION, x + 161, y + 9, 115, this.imageHeight + 54, 8, 8);
            } else {
                graphics.blit(RESOURCE_LOCATION, x + 161, y + 9, 98, this.imageHeight + 54, 8, 8);
            }
        }

        InventoryScreen.renderEntityInInventoryFollowsMouse(graphics, x + 51, y + 60, 17, (float)(x + 51), (float)(y + 75 - 50), this.unicorn);

        renderBreedLabel(graphics);

        if (LivestockOverhaulClientConfig.HORSE_COAT_GUI.get()) {
            renderBaseCoatLabel(graphics);
            renderMarkingLabel(graphics);
            renderHornLabel(graphics);
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
    }

    private void renderBreedLabel(GuiGraphics graphics) {
        String breedText = getBreedText(this.unicorn.getSpecies());
        String labelText = "Species: " + breedText;

        graphics.drawString(this.font, labelText, breedLabelX, breedLabelY, 0xFFFFFF, false);
    }

    private String getBreedText(int breed) {
        switch (breed) {
            case 0: return "Overworld";
            case 1: return "Nether";
            case 2: return "End";
            default: return "Unknown";
        }
    }

    private void renderBaseCoatLabel(GuiGraphics graphics) {
        String text = this.unicorn.getTextureResource().toString(); //texture name
        String noFillerText = text.replaceAll(".+unicorn/", ""); //remove 'horse_' and anything before it
        String noUnderscoresText = noFillerText.replaceAll("_", " "); //replace any underscores with spaces
        String noPNGText = noUnderscoresText.replace(".png", ""); //remove '.png'
        String labelText = "Base Coat: " + noPNGText.toUpperCase(); //print just the coat name

        String noTextureText = "Base Coat: " + "No Coat Found.";

        if (this.unicorn.getTextureResource() == null) {
            graphics.drawString(this.font, noTextureText, baseColorLabelX, baseColorLabelY, 0xFFFFFF, false);
        } else {
            graphics.drawString(this.font, labelText, baseColorLabelX, baseColorLabelY, 0xFFFFFF, false);
        }
    }

    private void renderMarkingLabel(GuiGraphics graphics) {
        String text = this.unicorn.getOverlayLocation().toString();
        String noFillerText = text.replaceAll(".+overlay/", "");
        String noUnderscoresText = noFillerText.replaceAll("_", " ");
        String noPNGText = noUnderscoresText.replace(".png", "");
        String addPinkNoseText = noPNGText.replace("pink", "pink-nosed");
        String labelText = "Marking(s): " + addPinkNoseText.toUpperCase();

        String noTextureText = "Marking(s): " + "No Marking Found.";

        if (this.unicorn.getOverlayLocation() == null) {
            graphics.drawString(this.font, noTextureText, markingLabelX, markingLabelY, 0xFFFFFF, false);
        } else {
            graphics.drawString(this.font, labelText, markingLabelX, markingLabelY, 0xFFFFFF, false);
        }
    }

    private void renderHornLabel(GuiGraphics graphics) {
        String text = this.unicorn.getHornTextureResource().toString();
        String noFillerText = text.replaceAll(".+horn/", "");
        String noUnderscoresText = noFillerText.replaceAll("_", " ");
        String noPNGText = noUnderscoresText.replace(".png", "");
        String labelText = "Horn: " + noPNGText.toUpperCase();

        String noTextureText = "Horn: " + "No Marking Found.";

        if (this.unicorn.getHornTextureResource() == null) {
            graphics.drawString(this.font, noTextureText, hornLabelX, hornLabelY, 0xFFFFFF, false);
        } else {
            graphics.drawString(this.font, labelText, hornLabelX, hornLabelY, 0xFFFFFF, false);
        }
    }

    private void renderGenderLabel(GuiGraphics graphics) {
        String female = "FEMALE";
        String male = "MALE";
        String error = "NBT Error";

        if (this.unicorn.getGender() == 0) {
            graphics.drawString(this.font, female, genderFLabelX, genderLabelY, 0xFFFFFF, false);
        } else if (this.unicorn.getGender() == 1) {
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

        double speed = unicorn.getAttributeBaseValue(Attributes.MOVEMENT_SPEED) * 42.16;

        DecimalFormat limitDec = new DecimalFormat("#.###");
        String num = limitDec.format(speed);
        String labelText = "Speed: " + num;

        graphics.drawString(this.font, labelText, speedLabelX, speedLabelY, 0xFFFFFF, false);
    }

    //This code is slightly altered to fit as a label rather than a tooltip
    private void renderJumpStrengthLabel(GuiGraphics graphics) {

        unicorn.getAttributes().hasAttribute(Attributes.JUMP_STRENGTH);
        double jumpStrength = unicorn.getAttributeBaseValue(Attributes.JUMP_STRENGTH);
        double jumpHeight = getJumpHeight(jumpStrength);

        DecimalFormat limitDec = new DecimalFormat("#.###");
        String num = limitDec.format(jumpHeight);
        String labelText = "Jump Strength: " + num;

        graphics.drawString(this.font, labelText, jumpStrengthLabelX, jumpStrengthLabelY, 0xFFFFFF, false);
    }
    //End of CC-Licensed code ^


    private void renderHealthLabel(GuiGraphics graphics) {
        String text = String.valueOf(this.unicorn.getMaxHealth());
        String labelText = "Max Health: " + text;

        graphics.drawString(this.font, labelText, healthLabelX, healthLabelY, 0xFFFFFF, false);
    }

}


