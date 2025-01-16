package com.dragn0007.dragnlivestock.entities.goat;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OGoatModel extends GeoModel<OGoat> {

    public enum Variant {
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/goat/goat.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/goat/goat_grey.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/goat/goat_red.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/goat/goat_brown.png")),
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/goat/goat_black.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/goat_overhaul.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/goat_overhaul.animation.json");

    @Override
    public ResourceLocation getModelResource(OGoat object) {
        return MODEL;
    }

    public ResourceLocation getTextureResource(OGoat object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(OGoat animatable) {
        return ANIMATION;
    }
}

