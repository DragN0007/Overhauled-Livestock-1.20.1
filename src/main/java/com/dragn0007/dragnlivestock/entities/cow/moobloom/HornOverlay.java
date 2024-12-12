package com.dragn0007.dragnlivestock.entities.cow.moobloom;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public enum HornOverlay {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/overlay_none.png")),
        SHORT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/horn_overlay/overlay_short_horns.png")),
        MEDIUM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/horn_overlay/overlay_medium_horns.png")),
        LONG(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/horn_overlay/overlay_long_horns.png"));

        public final ResourceLocation resourceLocation;
        HornOverlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static HornOverlay hornOverlayFromOrdinal(int hornOverlay) { return HornOverlay.values()[hornOverlay % HornOverlay.values().length];
        }
    }