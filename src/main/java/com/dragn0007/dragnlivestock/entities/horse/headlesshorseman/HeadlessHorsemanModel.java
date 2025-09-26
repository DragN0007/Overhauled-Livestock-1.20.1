package com.dragn0007.dragnlivestock.entities.horse.headlesshorseman;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

import java.util.HashMap;
import java.util.Map;

public class HeadlessHorsemanModel extends GeoModel<HeadlessHorseman> {

    public enum Variant {
        HEADLESS_HORSEMAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horseman_horse.png"));

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


    public static final Map<String, ResourceLocation> TEXTURE_CACHE = new HashMap<>();

    @Override
    public ResourceLocation getTextureResource(HeadlessHorseman object) {
        return TEXTURE_CACHE.computeIfAbsent(object.getTextureResource(), ResourceLocation::tryParse);
    }

    @Override
    public ResourceLocation getAnimationResource(HeadlessHorseman animatable) {
        return ANIMATION;
    }
}

