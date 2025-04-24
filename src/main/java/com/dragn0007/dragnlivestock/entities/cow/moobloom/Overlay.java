package com.dragn0007.dragnlivestock.entities.cow.moobloom;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public enum Overlay {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/none.png")),
        BLACK_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/overlay_black_speckled.png")),
        BLAZE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/overlay_blaze.png")),
        DAIRY_PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/overlay_dairy_paint.png")),
        OVERO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/overlay_overo.png")),
        PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/overlay_paint.png")),
        REVERSE_PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/overlay_reverse_paint.png")),
        SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/overlay_socks.png")),
        SPLASH_STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/overlay_splash_stripe.png")),
        WHITE_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/overlay_white_speckled.png"));

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
}
