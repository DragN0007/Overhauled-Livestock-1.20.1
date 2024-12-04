package com.dragn0007.dragnlivestock.gui;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.horse.OHorse;
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
    protected int breedLabelX = 235;
    protected int breedLabelY = 75;

    public OHorseScreen(OHorseMenu oHorseMenu, Inventory inventory, Component component) {
        super(oHorseMenu, inventory, component);
        this.oHorse = oHorseMenu.oHorse;
    }

    public void renderBg(GuiGraphics graphics, float f, int i, int j) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, HORSE_INVENTORY_LOCATION);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        graphics.blit(HORSE_INVENTORY_LOCATION, x, y, 0, 0, this.imageWidth, this.imageHeight);


        if (this.oHorse.hasChest() && (this.oHorse.getBreed() == 0 || this.oHorse.getBreed() == 2 || this.oHorse.getBreed() == 7 || this.oHorse.getBreed() == 9)) { //stock or warmblood
            graphics.blit(HORSE_INVENTORY_LOCATION, x + 79, y + 17, 0, this.imageHeight, 54, 54);
        }

        if (this.oHorse.hasChest() && (this.oHorse.getBreed() == 1 || this.oHorse.getBreed() == 5 || this.oHorse.getBreed() == 8)) { //draft or coldblood
            graphics.blit(HORSE_INVENTORY_LOCATION, x + 79, y + 17, 0, this.imageHeight, 90, 54);
        }

        if (this.oHorse.hasChest() && (this.oHorse.getBreed() == 3 || this.oHorse.getBreed() == 6)) { //pony
            graphics.blit(HORSE_INVENTORY_LOCATION, x + 79, y + 17, 0, this.imageHeight, 72, 54);
        }

        if (this.oHorse.hasChest() && (this.oHorse.getBreed() == 4)) { //racer
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
    }

    @Override
    public void render(GuiGraphics graphics, int i, int i1, float v) {
        this.renderBackground(graphics);
        super.render(graphics, i, i1, v);
        this.renderTooltip(graphics, i, i1);
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
            case 9: return "Selle Francias (Warmblood)";
            default: return "Unknown";
        }
    }
}


