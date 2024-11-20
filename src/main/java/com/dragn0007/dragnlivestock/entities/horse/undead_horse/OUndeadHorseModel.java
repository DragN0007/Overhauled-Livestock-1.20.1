package com.dragn0007.dragnlivestock.entities.horse.undead_horse;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.horse.BreedModel;
import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.entities.horse.OHorseModel;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OUndeadHorseModel extends GeoModel<OUndeadHorse> {

    public enum Variant {
        BAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_bay.png")),
        BAY_ROAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_bay_roan.png")),
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_black.png")),
        BLOOD_BAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_blood_bay.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_blue.png")),
        BLUE_ROAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_blue_roan.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_brown.png")),
        BUCKSKIN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_buckskin.png")),
        CHAMPAGNE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_champagne.png")),
        CHOCOLATE_ROAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_chocolate_roan.png")),
        CHESTNUT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_chestnut.png")),
        CREAMY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_creamy.png")),
        DARK_BAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_dark_bay.png")),
        DARK_BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_darkbrown.png")),
        FJORD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_fjord.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_grey.png")),
        GRULLO_DUN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_grullo_dun.png")),
        IVORY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_ivory.png")),
        LIVER_CHESTNUT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_liverchestnut.png")),
        PALAMINO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_palamino.png")),
        PALAMINO_ORANGE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_palamino_orange.png")),
        SEAL_BAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_seal_bay.png")),
        STRAWBERRY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_strawberry.png")),
        WARM_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_warmblack.png")),
        WARM_GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_warmgrey.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_white.png")),
        DAPPLE_BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_dapple_blue.png")),
        DAPPLE_BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_dapple_brown.png")),
        DAPPLE_GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_dapple_grey.png"));

        //Add new entries to bottom when mod is public, else horses will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static OHorseModel.Variant variantFromOrdinal(int variant) { return OHorseModel.Variant.values()[variant % OHorseModel.Variant.values().length];
        }
    }

    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/horse_overhaul.animation.json");
    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/horse_overhauled.geo.json");

    @Override
    public ResourceLocation getModelResource(OUndeadHorse object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(OUndeadHorse object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(OUndeadHorse animatable) {
        return ANIMATION;
    }
}

