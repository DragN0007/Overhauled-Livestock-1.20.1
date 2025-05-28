package com.dragn0007.dragnlivestock.entities.horse;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.cow.OCowMarkingLayer;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.Overlay;
import net.minecraft.resources.ResourceLocation;

public enum EquineMarkingOverlay {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/none.png")),
        APPALOOSA(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/appaloosa.png")),
        BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/bald.png")),
        BLANKET_APPALOOSA(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/blanket_appaloosa.png")),
        BLAZE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/blaze.png")),
        FLEABITTEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/fleabitten.png")),
        FULL_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/full_socks.png")),
        HALF_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/half_socks.png")),
        OVERO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/overo.png")),
        OVERO_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/overo_splash.png")),
        PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/paint.png")),
        REVERSED_HALF_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/reversed_half_socks.png")),
        REVERSED_FULL_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/reversed_full_socks.png")),
        ROAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/roan.png")),
        SNIP(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/snip.png")),
        SPLASH_OVERO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/splash_overo.png")),
        SPLASHED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/splashed.png")),
        SPLASHED_PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/splashed_paint.png")),
        SPOTTED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/spotted.png")),
        STAR(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/star.png")),
        TOBIANO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/tobiano.png")),
        HALF_SILVER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/half_silver.png")),
        FULL_SILVER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/full_silver.png")),
        CORONET(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/coronet.png")),
        FEW_SPOT_LEOPARD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/few_spot_leopard.png")),
        LEOPARD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/leopard.png")),
        PURE_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/pure_white.png")),
        RABICANO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/rabicano.png")),
        SNOWCAP(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/snowcap.png")),
        HALF_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/half_bald.png")),
        DAPPLES(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/dapples.png")),
        PINK_NOSE_APPALOOSA(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/pink_appaloosa.png")),
        PINK_NOSE_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/pink_bald.png")),
        PINK_NOSE_BLAZE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/pink_blaze.png")),
        PINK_NOSE_FEW_SPOT_LEOPARD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/pink_few_spot_leopard.png")),
        PINK_NOSE_HALF_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/pink_half_bald.png")),
        PINK_NOSE_LEOPARD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/pink_leopard.png")),
        PINK_NOSE_OVERO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/pink_overo.png")),
        PINK_NOSE_PURE_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/pink_pure_white.png")),
        PINK_NOSE_SNIP(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/pink_snip.png")),
        PINK_NOSE_SPLASH_OVERO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/pink_splash_overo.png")),
        PINK_NOSE_SPOTTED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/pink_spotted.png")),
        REVERSE_HALF_SILVER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/reverse_half_silver.png")),
        REVERSE_FULL_SILVER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/reverse_full_silver.png"));

        public final ResourceLocation resourceLocation;
        EquineMarkingOverlay(ResourceLocation resourceLocation) {
                this.resourceLocation = resourceLocation;
        }

        public static EquineMarkingOverlay overlayFromOrdinal(int overlay) { return EquineMarkingOverlay.values()[overlay % EquineMarkingOverlay.values().length];
        }
}
