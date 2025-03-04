package com.dragn0007.dragnlivestock.entities.camel;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OCamelModel extends GeoModel<OCamel> {

    public enum Variant {
        ASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/camel_ash.png")),
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/camel_black.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/camel_brown.png")),
        CHESTNUT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/camel_chestnut.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/camel_cream.png")),
        DESERT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/camel_desert.png")),
        TERRACOTTA(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/camel_terracotta.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/camel_white.png"));
        //Add new entries to bottom when mod is public, else camels will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/camel.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/camel.animation.json");

    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/baby_camel.geo.json");
    @Override
    public ResourceLocation getModelResource(OCamel object) {
        if(object.isBaby() && object.getBreed() == 0)
            return BABY_MODEL;
        return OCamel.Breed.breedFromOrdinal(object.getBreed()).resourceLocation;
    }

    @Override
    public ResourceLocation getTextureResource(OCamel object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(OCamel animatable) {
        return ANIMATION;
    }
}

