package com.dragn0007.dragnlivestock.gui;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class OHorseScreen extends AbstractContainerScreen<OHorseMenu> {

    public static final ResourceLocation HORSE_INVENTORY_LOCATION = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/o_horse.png");
    public final OHorse oHorse;
    protected int breedLabelX;
    protected int breedLabelY;
    protected int baseColorLabelX;
    protected int baseColorLabelY;
    protected int markingLabelX;
    protected int markingLabelY;

    public OHorseScreen(OHorseMenu oHorseMenu, Inventory inventory, Component component) {
        super(oHorseMenu, inventory, component);
        this.oHorse = oHorseMenu.oHorse;
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
        RenderSystem.setShaderTexture(0, HORSE_INVENTORY_LOCATION);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        graphics.blit(HORSE_INVENTORY_LOCATION, x, y, 0, 0, this.imageWidth, this.imageHeight);


        if (this.oHorse.hasChest() && (this.oHorse.getBreed() == 0 || this.oHorse.getBreed() == 2 || this.oHorse.getBreed() == 7 || this.oHorse.getBreed() == 9 || this.oHorse.getBreed() == 10)) { //stock or warmblood
            graphics.blit(HORSE_INVENTORY_LOCATION, x + 79, y + 17, 0, this.imageHeight, 54, 54);
        }

        if (this.oHorse.hasChest() && (this.oHorse.getBreed() == 1 || this.oHorse.getBreed() == 5 || this.oHorse.getBreed() == 8 || this.oHorse.getBreed() == 12)) { //draft or coldblood
            graphics.blit(HORSE_INVENTORY_LOCATION, x + 79, y + 17, 0, this.imageHeight, 90, 54);
        }

        if (this.oHorse.hasChest() && (this.oHorse.getBreed() == 3 || this.oHorse.getBreed() == 6 || this.oHorse.getBreed() == 11)) { //pony
            graphics.blit(HORSE_INVENTORY_LOCATION, x + 79, y + 17, 0, this.imageHeight, 72, 54);
        }

        if (this.oHorse.hasChest() && (this.oHorse.getBreed() == 4 || this.oHorse.getBreed() == 13)) { //racer
            graphics.blit(HORSE_INVENTORY_LOCATION, x + 79, y + 17, 0, this.imageHeight, 18, 54);
        }


        if (this.oHorse.isSaddleable()) {
            graphics.blit(HORSE_INVENTORY_LOCATION, x + 7, y + 17, 18, this.imageHeight + 54, 18, 18);
        }

        if (this.oHorse.canWearArmor()) {
            graphics.blit(HORSE_INVENTORY_LOCATION, x + 7, y + 35, 0, this.imageHeight + 54, 18, 18);
        }

//        if (this.oHorse.canWearShoes()) {
//            graphics.blit(HORSE_INVENTORY_LOCATION, x + 7, y + 53, 54, this.imageHeight + 54, 18, 18);
//        }

        if (this.oHorse.isFemale()) {
            graphics.blit(HORSE_INVENTORY_LOCATION, x + 161, y + 9, 90, this.imageHeight + 54, 8, 8);
        }

        if (this.oHorse.isMale()) {
            graphics.blit(HORSE_INVENTORY_LOCATION, x + 161, y + 9, 98, this.imageHeight + 54, 8, 8);
        }

        InventoryScreen.renderEntityInInventoryFollowsMouse(graphics, x + 51, y + 60, 17, (float)(x + 51), (float)(y + 75 - 50), this.oHorse);

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
        String breedText = getBreedText(this.oHorse.getBreed());
        String labelText = "Breed: " + breedText;

        graphics.drawString(this.font, labelText, breedLabelX, breedLabelY, 0xFFFFFF, false);
    }

    private String getBreedText(int breed) {
        switch (breed) {
            case 0: return "Mustang (Stock)";
            case 1: return "Ardennes (Draft)";
            case 2: return "Kladruber (Warmblood)";
            case 3: return "Fjord (Pony)";
            case 4: return "Thoroughbred (Racer)";
            case 5: return "Friesian (Coldblood)";
            case 6: return "Irish Cob (Draft)";
            case 7: return "American Quarter (Stock)";
            case 8: return "Percheron (Draft)";
            case 9: return "Selle Francais (Warmblood)";
            case 10: return "Marwari (Warmblood)";
            case 11: return "Mongolian (Pony)";
            case 12: return "Shire (Draft)";
            case 13: return "Akhal-Teke (Racer)";
            default: return "Unknown";
        }
    }

    private void renderBaseCoatLabel(GuiGraphics graphics) {
        String text = this.oHorse.getTextureResource().toString(); //texture name
        String noFillerText = text.replaceAll(".+horse_", ""); //remove 'horse_' and anything before it
        String noUnderscoresText = noFillerText.replaceAll("_", " "); //replace any underscores with spaces
        String noPNGText = noUnderscoresText.replace(".png", ""); //remove '.png'
        String addSpacesWhenApplicableText = noPNGText.replace("warm", "warm ");
        String labelText = "Base Coat: " + addSpacesWhenApplicableText.toUpperCase(); //print just the coat name

        String noTextureText = "Base Coat: " + "No Coat Found.";

        if (this.oHorse.getTextureResource() == null) {
            graphics.drawString(this.font, noTextureText, baseColorLabelX, baseColorLabelY, 0xFFFFFF, false);
        } else {
            graphics.drawString(this.font, labelText, baseColorLabelX, baseColorLabelY, 0xFFFFFF, false);
        }
    }

    private void renderMarkingLabel(GuiGraphics graphics) {
        String text = this.oHorse.getOverlayLocation().toString();
        String noFillerText = text.replaceAll(".+overlay_", "");
        String noUnderscoresText = noFillerText.replaceAll("_", " ");
        String noPNGText = noUnderscoresText.replace(".png", "");
        String labelText = "Marking(s): " + noPNGText.toUpperCase();

        String noTextureText = "Marking(s): " + "No Marking Found.";

        if (this.oHorse.getTextureResource() == null) {
            graphics.drawString(this.font, noTextureText, markingLabelX, markingLabelY, 0xFFFFFF, false);
        } else {
            graphics.drawString(this.font, labelText, markingLabelX, markingLabelY, 0xFFFFFF, false);
        }
    }

}


