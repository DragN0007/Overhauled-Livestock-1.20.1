package com.dragn0007.dragnlivestock.entities.caribou;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CaribouModel extends GeoModel<Caribou> {

    public enum Variant {
        BAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/reindeer_bay.png")),
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/reindeer_black.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/reindeer_brown.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/reindeer_cream.png")),
        LIGHT_GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/reindeer_light_grey.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/reindeer_tan.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/reindeer_white.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_horse.animation.json");
    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/baby_reindeer.geo.json");
    public static final ResourceLocation REINDEER_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/reindeer.geo.json");

    @Override
    public ResourceLocation getModelResource(Caribou object) {
        if (object.isBaby()) {
            return BABY_MODEL;
        }
        return REINDEER_MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Caribou object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(Caribou animatable) {
        return ANIMATION;
    }
}

