package com.dragn0007.dragnlivestock.blocks.client.render.model.entity;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.OCod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OCodModel extends GeoModel<OCod> {

    public enum Variant {
        COD(LivestockOverhaul.id("textures/entity/fish/cod.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = LivestockOverhaul.id("geo/overhauled_cod.geo.json");
    public static final ResourceLocation ANIMATION = LivestockOverhaul.id("animations/o_fish.animation.json");

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

