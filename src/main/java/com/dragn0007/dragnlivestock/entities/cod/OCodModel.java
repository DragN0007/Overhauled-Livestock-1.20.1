package com.dragn0007.dragnlivestock.entities.cod;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OCodModel extends GeoModel<OCod> {

    public enum Variant {
        COD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/fish/cod.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/overhauled_cod.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_fish.animation.json");

    @Override
    public ResourceLocation getModelResource(OCod object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(OCod object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(OCod animatable) {
        return ANIMATION;
    }
}

