package com.dragn0007.dragnlivestock.common.entities.util.marking_layer;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public enum EquineEyeColorOverlay {
        //from most common to least common
        DARK_BROWN(LivestockOverhaul.id("textures/entity/horse/eyes/dark_brown.png")),
        BROWN(LivestockOverhaul.id("textures/entity/horse/eyes/brown.png")),
        AMBER(LivestockOverhaul.id("textures/entity/horse/eyes/amber.png")),
        GOLD(LivestockOverhaul.id("textures/entity/horse/eyes/gold.png")),
        DARK_BLUE(LivestockOverhaul.id("textures/entity/horse/eyes/dark_blue.png")),
        BLUE(LivestockOverhaul.id("textures/entity/horse/eyes/blue.png")),
        GREEN(LivestockOverhaul.id("textures/entity/horse/eyes/green.png")),
        BLUE_GOLD(LivestockOverhaul.id("textures/entity/horse/eyes/heterochromic_blue_and_gold.png")),
        BROWN_GREEN(LivestockOverhaul.id("textures/entity/horse/eyes/heterochromic_brown_and_green.png")),
        BROWN_BLUE(LivestockOverhaul.id("textures/entity/horse/eyes/heterochromic_dark_brown_and_blue.png"));

        public final ResourceLocation resourceLocation;
        EquineEyeColorOverlay(ResourceLocation resourceLocation) {
                this.resourceLocation = resourceLocation;
        }

        public static EquineEyeColorOverlay eyesFromOrdinal(int eyes) { return EquineEyeColorOverlay.values()[eyes % EquineEyeColorOverlay.values().length];
        }
}
