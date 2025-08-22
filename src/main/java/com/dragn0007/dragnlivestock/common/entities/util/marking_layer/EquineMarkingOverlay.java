package com.dragn0007.dragnlivestock.common.entities.util.marking_layer;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public enum EquineMarkingOverlay {
        NONE(LivestockOverhaul.id("textures/entity/horse/overlay/none.png")),
        APPALOOSA(LivestockOverhaul.id("textures/entity/horse/overlay/appaloosa.png")),
        BALD(LivestockOverhaul.id("textures/entity/horse/overlay/bald.png")),
        BLANKET_APPALOOSA(LivestockOverhaul.id("textures/entity/horse/overlay/blanket_appaloosa.png")),
        BLAZE(LivestockOverhaul.id("textures/entity/horse/overlay/blaze.png")),
        FLEABITTEN(LivestockOverhaul.id("textures/entity/horse/overlay/fleabitten.png")),
        FULL_SOCKS(LivestockOverhaul.id("textures/entity/horse/overlay/full_socks.png")),
        HALF_SOCKS(LivestockOverhaul.id("textures/entity/horse/overlay/half_socks.png")),
        OVERO(LivestockOverhaul.id("textures/entity/horse/overlay/overo.png")),
        OVERO_SPLASH(LivestockOverhaul.id("textures/entity/horse/overlay/overo_splash.png")),
        PAINT(LivestockOverhaul.id("textures/entity/horse/overlay/paint.png")),
        REVERSED_HALF_SOCKS(LivestockOverhaul.id("textures/entity/horse/overlay/reverse_half_socks.png")),
        REVERSED_FULL_SOCKS(LivestockOverhaul.id("textures/entity/horse/overlay/reverse_full_socks.png")),
        ROAN(LivestockOverhaul.id("textures/entity/horse/overlay/roan.png")),
        SNIP(LivestockOverhaul.id("textures/entity/horse/overlay/snip.png")),
        SPLASH_OVERO(LivestockOverhaul.id("textures/entity/horse/overlay/splash_overo.png")),
        SPLASHED(LivestockOverhaul.id("textures/entity/horse/overlay/splashed.png")),
        SPLASHED_PAINT(LivestockOverhaul.id("textures/entity/horse/overlay/splashed_paint.png")),
        SPOTTED(LivestockOverhaul.id("textures/entity/horse/overlay/spotted.png")),
        STAR(LivestockOverhaul.id("textures/entity/horse/overlay/star.png")),
        TOBAINO(LivestockOverhaul.id("textures/entity/horse/overlay/tobaino.png")),
        HALF_SILVER(LivestockOverhaul.id("textures/entity/horse/overlay/half_silver.png")),
        FULL_SILVER(LivestockOverhaul.id("textures/entity/horse/overlay/full_silver.png")),
        CORONET(LivestockOverhaul.id("textures/entity/horse/overlay/coronet.png")),
        FEW_SPOT_LEOPARD(LivestockOverhaul.id("textures/entity/horse/overlay/few_spot_leopard.png")),
        LEOPARD(LivestockOverhaul.id("textures/entity/horse/overlay/leopard.png")),
        PURE_WHITE(LivestockOverhaul.id("textures/entity/horse/overlay/pure_white.png")),
        RABICANO(LivestockOverhaul.id("textures/entity/horse/overlay/rabicano.png")),
        SNOWCAP(LivestockOverhaul.id("textures/entity/horse/overlay/snowcap.png")),
        HALF_BALD(LivestockOverhaul.id("textures/entity/horse/overlay/half_bald.png")),
        DAPPLES(LivestockOverhaul.id("textures/entity/horse/overlay/dapples.png")),
        PINK_NOSE_APPALOOSA(LivestockOverhaul.id("textures/entity/horse/overlay/pink_appaloosa.png")),
        PINK_NOSE_BALD(LivestockOverhaul.id("textures/entity/horse/overlay/pink_bald.png")),
        PINK_NOSE_BLAZE(LivestockOverhaul.id("textures/entity/horse/overlay/pink_blaze.png")),
        PINK_NOSE_FEW_SPOT_LEOPARD(LivestockOverhaul.id("textures/entity/horse/overlay/pink_few_spot_leopard.png")),
        PINK_NOSE_HALF_BALD(LivestockOverhaul.id("textures/entity/horse/overlay/pink_half_bald.png")),
        PINK_NOSE_LEOPARD(LivestockOverhaul.id("textures/entity/horse/overlay/pink_leopard.png")),
        PINK_NOSE_OVERO(LivestockOverhaul.id("textures/entity/horse/overlay/pink_overo.png")),
        PINK_NOSE_PURE_WHITE(LivestockOverhaul.id("textures/entity/horse/overlay/pink_pure_white.png")),
        PINK_NOSE_SNIP(LivestockOverhaul.id("textures/entity/horse/overlay/pink_snip.png")),
        PINK_NOSE_SPLASH_OVERO(LivestockOverhaul.id("textures/entity/horse/overlay/pink_splash_overo.png")),
        PINK_NOSE_SPOTTED(LivestockOverhaul.id("textures/entity/horse/overlay/pink_spotted.png")),
        REVERSE_HALF_SILVER(LivestockOverhaul.id("textures/entity/horse/overlay/reverse_half_silver.png")),
        REVERSE_FULL_SILVER(LivestockOverhaul.id("textures/entity/horse/overlay/reverse_full_silver.png"));

        public final ResourceLocation resourceLocation;
        EquineMarkingOverlay(ResourceLocation resourceLocation) {
                this.resourceLocation = resourceLocation;
        }

        public static EquineMarkingOverlay overlayFromOrdinal(int overlay) { return EquineMarkingOverlay.values()[overlay % EquineMarkingOverlay.values().length];
        }
}
