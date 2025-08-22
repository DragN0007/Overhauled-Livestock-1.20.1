package com.dragn0007.dragnlivestock.blocks.client.render.model.entity;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.frog.OFrog;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OFrogModel extends GeoModel<OFrog> {

    public enum Variant {
        BLACK(LivestockOverhaul.id("textures/entity/frog/frog_black.png")),
        BROWN(LivestockOverhaul.id("textures/entity/frog/frog_brown.png")),
        BLUE(LivestockOverhaul.id("textures/entity/frog/frog_blue.png")),
        DARK_BLUE(LivestockOverhaul.id("textures/entity/frog/frog_dark_blue.png")),
        DARK_GREEN(LivestockOverhaul.id("textures/entity/frog/frog_dark_green.png")),
        GREEN(LivestockOverhaul.id("textures/entity/frog/frog_green.png")),
        PINK(LivestockOverhaul.id("textures/entity/frog/frog_pink.png")),
        RED(LivestockOverhaul.id("textures/entity/frog/frog_red.png")),
        ULTRA_ORANGE(LivestockOverhaul.id("textures/entity/frog/frog_ultra_purple.png")),
        ULTRA_PURPLE(LivestockOverhaul.id("textures/entity/frog/frog_ultra_purple.png")),
        YELLOW(LivestockOverhaul.id("textures/entity/frog/frog_yellow.png")),
        WHITE(LivestockOverhaul.id("textures/entity/frog/frog_white.png"));

        //Add new entries to bottom when mod is public, else frogs will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = LivestockOverhaul.id("geo/frog.geo.json");
    public static final ResourceLocation ANIMATION = LivestockOverhaul.id("animations/frog.animation.json");

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

