package com.dragn0007.dragnlivestock.entities.cow;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.horse.BreedModel;
import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

import java.time.Month;

public class OCowModel extends GeoModel<OCow> {

    public enum Variant {
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/cow_black.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/cow_blue.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/cow_brown.png")),
        CHESTNUT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/cow_chestnut.png")),
        DARK_BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/cow_dark_brown.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/cow_grey.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/cow_white.png"));

        //Add new entries to bottom when mod is public, else cows will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

//    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/cow_overhaul.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/cow_overhaul.animation.json");

    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/baby_cow_overhaul.geo.json");

    @Override
    public ResourceLocation getModelResource(OCow object) {
        if (object.isBaby()) {
            return BABY_MODEL;
        }
        return OCow.Breed.breedFromOrdinal(object.getBreed()).resourceLocation;
    }

    @Override
    public ResourceLocation getTextureResource(OCow object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(OCow animatable) {
        return ANIMATION;
    }
}

