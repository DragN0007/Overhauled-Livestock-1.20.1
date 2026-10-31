package com.dragn0007.dragnlivestock.entities.frog;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OFrogModel extends GeoModel<OFrog> {

    public enum Variant {
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/black.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/blue.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/brown.png")),
        CYAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/cyan.png")),
        GRAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/gray.png")),
        GREEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/green.png")),
        LIGHT_BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/light_blue.png")),
        LIGHT_GRAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/light_gray.png")),
        LIME(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/lime.png")),
        ORANGE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/orange.png")),
        MAGENTA(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/magenta.png")),
        PINK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/pink.png")),
        PURPLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/purple.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/red.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/white.png")),
        YELLOW(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/yellow.png"));
        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {this.resourceLocation = resourceLocation;}
        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];}
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/o_frog.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_frog.animation.json");

    @Override
    public ResourceLocation getModelResource(OFrog object) {return MODEL;}

    @Override
    public ResourceLocation getTextureResource(OFrog object) {return object.getTextureLocation();}

    @Override
    public ResourceLocation getAnimationResource(OFrog animatable) {return ANIMATION;}
}

