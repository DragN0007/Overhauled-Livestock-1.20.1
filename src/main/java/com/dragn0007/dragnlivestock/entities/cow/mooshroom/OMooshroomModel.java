package com.dragn0007.dragnlivestock.entities.cow.mooshroom;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OMooshroomModel extends GeoModel<OMooshroom> {

    public enum Variant {
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/cow_white.png")),
        ROSE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/cow_rose.png")),
        PINK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/cow_pink.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/mooshroom/mooshroom_brown.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/mooshroom/mooshroom_red.png")),
        HIGHLAND_BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/mooshroom/highland_mooshroom_brown.png")),
        HIGHLAND_RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/mooshroom/highland_mooshroom_red.png"));

        //Add new entries to bottom when mod is public, else mooshrooms will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/mooshroom_overhaul.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_cow.animation.json");

    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/baby_o_cow.geo.json");
    @Override
    public ResourceLocation getModelResource(OMooshroom object) {
        if(object.isBaby())
            return BABY_MODEL;
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(OMooshroom object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(OMooshroom animatable) {
        return ANIMATION;
    }
}

