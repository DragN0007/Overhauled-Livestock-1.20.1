package com.dragn0007.dragnlivestock.entities.unicorn;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EndUnicornModel extends GeoModel<EndUnicorn> {

    public enum Variant {
        END(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/unicorn_end.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/unicorn.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/horse_overhaul.animation.json");
    @Override
    public ResourceLocation getModelResource(EndUnicorn object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(EndUnicorn object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(EndUnicorn animatable) {
        return ANIMATION;
    }
}

