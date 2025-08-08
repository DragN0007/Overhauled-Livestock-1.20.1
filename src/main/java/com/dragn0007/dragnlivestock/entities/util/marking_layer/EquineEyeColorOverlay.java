package com.dragn0007.dragnlivestock.entities.util.marking_layer;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public enum EquineEyeColorOverlay {
        //from most common to least common
        DARK_BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/eyes/dark_brown.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/eyes/brown.png")),
        AMBER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/eyes/amber.png")),
        GOLD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/eyes/gold.png")),
        DARK_BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/eyes/dark_blue.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/eyes/blue.png")),
        GREEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/eyes/green.png")),
        BLUE_GOLD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/eyes/heterochromic_blue_and_gold.png")),
        BROWN_GREEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/eyes/heterochromic_brown_and_green.png")),
        BROWN_BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/eyes/heterochromic_dark_brown_and_blue.png"));

        public final ResourceLocation resourceLocation;
        EquineEyeColorOverlay(ResourceLocation resourceLocation) {
                this.resourceLocation = resourceLocation;
        }

        public static EquineEyeColorOverlay eyesFromOrdinal(int eyes) { return EquineEyeColorOverlay.values()[eyes % EquineEyeColorOverlay.values().length];
        }
}
