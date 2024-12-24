package com.dragn0007.dragnlivestock.entities.horse;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

import java.time.LocalDate;
import java.time.Month;

public class OHorseModel extends GeoModel<OHorse> {

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
        DAPPLE_GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_dapple_grey.png")),

        DAPPLE_RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_dapple_red.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_cream.png")),
        RED_DUN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_red_dun.png")),
        BAY_DUN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_bay_dun.png")),
        GRULLA(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_grulla.png")),
        BLUE_DUN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/horse_blue_dun.png"));

        //Add new entries to bottom when mod is public, else horses will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public enum ReindeerVariant {
        BAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/reindeer_bay.png")),
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/reindeer_black.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/reindeer_brown.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/reindeer_cream.png")),
        LIGHT_GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/reindeer_light_grey.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/reindeer_tan.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/reindeer_white.png"));

        public final ResourceLocation resourceLocation;
        ReindeerVariant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static ReindeerVariant reindeerVariantFromOrdinal(int christmasVariant) { return ReindeerVariant.values()[christmasVariant % ReindeerVariant.values().length];
        }
    }

    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/horse_overhaul.animation.json");
    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/baby_horse_overhauled.geo.json");
    public static final ResourceLocation REINDEER_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/reindeer.geo.json");

    LocalDate date = LocalDate.now();
    Month month = date.getMonth();
    int day = date.getDayOfMonth();

    @Override
    public ResourceLocation getModelResource(OHorse object) {
        if (object.isBaby()) {
            return BABY_MODEL;
        } else if (!object.isBaby() && month == Month.DECEMBER && (day == 24 || day == 25)) {
            return REINDEER_MODEL;
        }
        return BreedModel.breedFromOrdinal(object.getBreed()).resourceLocation;
    }

    @Override
    public ResourceLocation getTextureResource(OHorse object) {
        if (month == Month.DECEMBER && (day == 24 || day == 25)) {
            return object.getReindeerTextureResource();
        }
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(OHorse animatable) {
        return ANIMATION;
    }
}

