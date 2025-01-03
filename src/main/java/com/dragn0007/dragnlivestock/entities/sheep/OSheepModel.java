package com.dragn0007.dragnlivestock.entities.sheep;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OSheepModel extends GeoModel<OSheep> {

    public enum Variant {
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/sheep_black.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/sheep_brown.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/sheep_white.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/sheep_overhaul.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/sheep_overhaul.animation.json");

    @Override
    public ResourceLocation getModelResource(OSheep object) {
        return MODEL;
    }

    public ResourceLocation getTextureResource(OSheep object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(OSheep animatable) {
        return ANIMATION;
    }
}

