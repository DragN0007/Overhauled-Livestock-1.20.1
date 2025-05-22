package com.dragn0007.dragnlivestock.entities.llama;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OLlamaModel extends GeoModel<OLlama> {

    public enum Variant {
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/black.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/blue.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/brown.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/grey.png")),
        LIGHT_GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/light_grey.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/red.png")),
        SANDY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/sandy.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/tan.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/white.png"));

        //Add new entries to bottom when mod is public, else llamas will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/o_llama.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_llama.animation.json");

    @Override
    public ResourceLocation getModelResource(OLlama object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(OLlama object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(OLlama animatable) {
        return ANIMATION;
    }
}

