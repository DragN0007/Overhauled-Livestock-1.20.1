package com.dragn0007.dragnlivestock.entities.horse.headlesshorseman;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HeadlessHorsemanModel extends GeoModel<HeadlessHorseman> {

    public enum Variant {
        HEADLESS_HORSEMAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_headless_horseman.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/headless_horseman.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/headless_horseman.animation.json");
    @Override
    public ResourceLocation getModelResource(HeadlessHorseman object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(HeadlessHorseman object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(HeadlessHorseman animatable) {
        return ANIMATION;
    }
}

