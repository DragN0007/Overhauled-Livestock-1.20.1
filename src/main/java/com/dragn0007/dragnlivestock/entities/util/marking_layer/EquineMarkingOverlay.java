package com.dragn0007.dragnlivestock.entities.util.marking_layer;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public enum EquineMarkingOverlay {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/none.png")),
        BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/bald.png")),
        BLANKET_APPALOOSA(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/blanket_appaloosa.png")),
        BLAZE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/blaze.png")),
        FLEABITTEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/fleabitten.png")),
        FULL_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/full_socks.png")),
        HALF_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/half_socks.png")),
        OVERO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/overo.png")),
        PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/paint.png")),
        REVERSED_HALF_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/reverse_half_socks.png")),
        REVERSED_FULL_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/reverse_full_socks.png")),
        SNIP(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/snip.png")),
        SPLASHED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/splashed.png")),
        STAR(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/star.png")),
        TOBAINO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/tobaino.png")),
        FULL_SILVER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/full_silver.png")),
        CORONET(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/coronet.png")),
        FEW_SPOT_LEOPARD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/few_spot_leopard.png")),
        LEOPARD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/leopard.png")),
        PURE_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/pure_white.png")),
        RABICANO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/rabicano.png")),
        SPOTTED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/spotted.png")),
        SNOWCAP(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/snowcap.png")),
        HALF_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/half_bald.png")),
        DAPPLES(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/dapples.png")),
        REVERSE_FULL_SILVER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/reverse_full_silver.png")),
        MINIMAL_SABINO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/minimal_sabino.png")),
        LIGHT_MARKER_OVERO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/light_marker_overo.png"));

        public final ResourceLocation resourceLocation;
        EquineMarkingOverlay(ResourceLocation resourceLocation) {this.resourceLocation = resourceLocation;}
        public static EquineMarkingOverlay overlayFromOrdinal(int overlay) { return EquineMarkingOverlay.values()[overlay % EquineMarkingOverlay.values().length];}
}
