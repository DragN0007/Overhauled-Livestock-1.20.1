package com.dragn0007.dragnlivestock.entities.unicorn;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.horse.OHorseModel;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class UnicornModel extends GeoModel<Unicorn> {

    public enum Variant {
        BAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/bay.png")),
        BAY_ROAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/bay_roan.png")),
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/black.png")),
        BLOOD_BAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/blood_bay.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/blue.png")),
        BLUE_ROAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/blue_roan.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/brown.png")),
        BUCKSKIN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/buckskin.png")),
        CHAMPAGNE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/champagne.png")),
        CHOCOLATE_ROAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/chocolate_roan.png")),
        CHESTNUT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/chestnut.png")),
        CREAMY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/creamy.png")),
        DARK_BAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/dark_bay.png")),
        DARK_BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/dark_brown.png")),
        FJORD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/fjord.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/grey.png")),
        IVORY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/ivory.png")),
        LIVER_CHESTNUT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/liver_chestnut.png")),
        PALAMINO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/palamino.png")),
        PALAMINO_ORANGE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/palamino_orange.png")),
        SEAL_BAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/seal_bay.png")),
        STRAWBERRY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/strawberry.png")),
        WARM_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/warm_black.png")),
        WARM_GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/warm_grey.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/white.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/cream.png")),
        RED_DUN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/red_dun.png")),
        BAY_DUN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/bay_dun.png")),
        GRULLA(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/grulla.png")),
        BLUE_DUN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/blue_dun.png")),
        CINNAMON(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/cinnamon.png")),
        END(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/end.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static UnicornModel.Variant variantFromOrdinal(int variant) { return UnicornModel.Variant.values()[variant % OHorseModel.Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/unicorn.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_horse.animation.json");
    @Override
    public ResourceLocation getModelResource(Unicorn object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Unicorn object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(Unicorn animatable) {
        return ANIMATION;
    }
}

