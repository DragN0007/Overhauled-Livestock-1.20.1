package com.dragn0007.dragnlivestock.entities.frog.food;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GrubModel extends GeoModel<Grub> {

    public enum Variant {
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/grub/grub_black.png")),
        GREEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/grub/grub_green.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/grub/grub_red.png")),
        GRUB(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/grub/grub.png"));

        //Add new entries to bottom when mod is public, else grubs will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/grub.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/grub.animation.json");

    @Override
    public ResourceLocation getModelResource(Grub object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Grub object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(Grub animatable) {
        return ANIMATION;
    }
}

