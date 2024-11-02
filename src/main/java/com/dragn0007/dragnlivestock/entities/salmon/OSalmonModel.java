package com.dragn0007.dragnlivestock.entities.salmon;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OSalmonModel extends GeoModel<OSalmon> {

    public enum Variant {
        NORMAL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/fish/salmon_normal.png")),
        SPAWNING(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/fish/salmon_spawning.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/overhauled_salmon.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_fish.animation.json");

    @Override
    public ResourceLocation getModelResource(OSalmon object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(OSalmon object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(OSalmon animatable) {
        return ANIMATION;
    }
}

