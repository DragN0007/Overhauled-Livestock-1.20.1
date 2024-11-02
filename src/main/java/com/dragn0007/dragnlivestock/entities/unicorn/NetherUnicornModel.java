package com.dragn0007.dragnlivestock.entities.unicorn;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class NetherUnicornModel extends GeoModel<NetherUnicorn> {

    public enum Variant {
        NETHER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/unicorn_nether.png"));

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
    public ResourceLocation getModelResource(NetherUnicorn object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(NetherUnicorn object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(NetherUnicorn animatable) {
        return ANIMATION;
    }
}

