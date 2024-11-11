package com.dragn0007.dragnlivestock.entities.wagons.covered;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.unicorn.EndUnicorn;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CoveredWagonModel extends GeoModel<CoveredWagon> {

    public enum Variant {
        WAGON(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/wagon/covered_wagon.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/covered_wagon.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/wagon.animation.json");
    @Override
    public ResourceLocation getModelResource(CoveredWagon object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(CoveredWagon object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(CoveredWagon animatable) {
        return ANIMATION;
    }
}

