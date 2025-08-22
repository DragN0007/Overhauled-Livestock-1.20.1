package com.dragn0007.dragnlivestock.blocks.client.render.model.entity;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.horse.HeadlessHorseman;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HeadlessHorsemanModel extends GeoModel<HeadlessHorseman> {

    public enum Variant {
        HEADLESS_HORSEMAN(LivestockOverhaul.id("textures/entity/horse/horseman_horse.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = LivestockOverhaul.id("geo/headless_horseman.geo.json");
    public static final ResourceLocation ANIMATION = LivestockOverhaul.id("animations/headless_horseman.animation.json");
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

