package com.dragn0007.dragnlivestock.entities.donkey;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ODonkeyModel extends GeoModel<ODonkey> {

    public enum Variant {
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/donkey/brown.png")),
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/donkey/black.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/donkey/cream.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/donkey/grey.png")),
        STRAWBERRY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/donkey/strawberry.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/donkey/white.png"));

        //Add new entries to bottom when mod is public, else donkeys will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/o_donkey.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_horse.animation.json");

    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/baby_o_donkey.geo.json");

    @Override
    public ResourceLocation getModelResource(ODonkey object) {
        if(object.isBaby())
            return BABY_MODEL;
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(ODonkey object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(ODonkey animatable) {
        return ANIMATION;
    }
}

