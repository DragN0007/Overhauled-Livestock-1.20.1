package com.dragn0007.dragnlivestock.entities.cow.moobloom.azalea;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AzaleaMoobloomModel extends GeoModel<AzaleaMoobloom> {

    public enum Variant {
        DEFAULT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/moobloom_azalea.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/azalea_moobloom.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/cow_overhaul.animation.json");

    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/baby_cow_overhaul.geo.json");
    @Override
    public ResourceLocation getModelResource(AzaleaMoobloom object) {
        if(object.isBaby())
            return BABY_MODEL;
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(AzaleaMoobloom object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(AzaleaMoobloom animatable) {
        return ANIMATION;
    }
}

