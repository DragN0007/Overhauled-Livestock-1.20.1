package com.dragn0007.dragnlivestock.entities.util.marking_layer;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

// these markings are used by multiple entites, such as cows, oxen, and mooblooms,
// so theres no reason to keep copying them every single time.
// all of them just use this one big enum

// a lot of animals use different, unique markings, so they just use their own enum

public enum BovineMarkingOverlay {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/none.png")),
        BLACK_FEW_SPECKLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/black_few_speckle.png")),
        BLACK_LEOPARD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/black_leopard.png")),
        BLACK_PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/black_paint.png")),
        BLACK_STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/black_stripe.png")),
        BLUE_FEW_SPECKLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/blue_few_speckle.png")),
        BLUE_LEOPARD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/blue_leopard.png")),
        BLUE_PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/blue_paint.png")),
        BLUE_STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/blue_stripe.png")),
        CREAM_FEW_SPECKLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/cream_few_speckle.png")),
        CREAM_LEOPARD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/cream_leopard.png")),
        CREAM_PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/cream_paint.png")),
        CREAM_STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/cream_stripe.png")),
        RED_FEW_SPECKLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/red_few_speckle.png")),
        RED_LEOPARD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/red_leopard.png")),
        RED_PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/red_paint.png")),
        RED_STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/red_stripe.png")),
        WHITE_FEW_SPECKLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/white_few_speckle.png")),
        WHITE_LEOPARD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/white_leopard.png")),
        WHITE_PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/white_paint.png")),
        WHITE_STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/white_stripe.png")),
        BLAZE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/blaze.png")),
        SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/socks.png")),
        FULL_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/full_socks.png")),
        PURE_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/pure_white.png")),
        HEREFORD_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/hereford_splash.png"));

        //Add new entries to bottom when mod is public, else cows will change textures during update.

        public final ResourceLocation resourceLocation;
        BovineMarkingOverlay(ResourceLocation resourceLocation) {
                this.resourceLocation = resourceLocation;
        }

        public static BovineMarkingOverlay overlayFromOrdinal(int overlay) { return BovineMarkingOverlay.values()[overlay % BovineMarkingOverlay.values().length];
        }
}
