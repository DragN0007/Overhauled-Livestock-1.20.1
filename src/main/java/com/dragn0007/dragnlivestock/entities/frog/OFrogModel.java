package com.dragn0007.dragnlivestock.entities.frog;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OFrogModel extends GeoModel<OFrog> {

    public enum Variant {
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/frog_black.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/frog_brown.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/frog_blue.png")),
        DARK_BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/frog_dark_blue.png")),
        DARK_GREEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/frog_dark_green.png")),
        GREEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/frog_green.png")),
        PINK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/frog_pink.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/frog_red.png")),
        ULTRA_ORANGE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/frog_ultra_purple.png")),
        ULTRA_PURPLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/frog_ultra_purple.png")),
        YELLOW(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/frog_yellow.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/frog_white.png"));

        //Add new entries to bottom when mod is public, else frogs will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/frog.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/frog.animation.json");

    @Override
    public ResourceLocation getModelResource(OFrog object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(OFrog object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(OFrog animatable) {
        return ANIMATION;
    }
}

