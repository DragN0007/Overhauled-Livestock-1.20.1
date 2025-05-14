package com.dragn0007.dragnlivestock.entities.horse;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import java.time.LocalDate;
import java.time.Month;

public class OHorseModel extends DefaultedEntityGeoModel<OHorse> {

    public OHorseModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o_horse"), true);
    }

    @Override
    public void setCustomAnimations(OHorse animatable, long instanceId, AnimationState<OHorse> animationState) {

        CoreGeoBone neck = getAnimationProcessor().getBone("neck");

        if (neck != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            neck.setRotY(neck.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }
    }

    public enum Variant {
        BAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/bay.png")),
        BAY_ROAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/bay_roan.png")),
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/black.png")),
        BLOOD_BAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/blood_bay.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/blue.png")),
        BLUE_ROAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/blue_roan.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/brown.png")),
        BUCKSKIN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/buckskin.png")),
        CHAMPAGNE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/champagne.png")),
        CHOCOLATE_ROAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/chocolate_roan.png")),
        CHESTNUT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/chestnut.png")),
        CREAMY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/creamy.png")),
        DARK_BAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/dark_bay.png")),
        DARK_BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/dark_brown.png")),
        FJORD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/fjord.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/grey.png")),
        IVORY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/ivory.png")),
        LIVER_CHESTNUT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/liver_chestnut.png")),
        PALAMINO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/palamino.png")),
        PALAMINO_ORANGE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/palamino_orange.png")),
        SEAL_BAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/seal_bay.png")),
        STRAWBERRY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/strawberry.png")),
        WARM_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/warm_black.png")),
        WARM_GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/warm_grey.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/white.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/cream.png")),
        RED_DUN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/red_dun.png")),
        BAY_DUN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/bay_dun.png")),
        GRULLA(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/grulla.png")),
        BLUE_DUN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/blue_dun.png")),
        CINNAMON(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/cinnamon.png"));

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

    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_horse.animation.json");
    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/baby_o_horse.geo.json");
    public static final ResourceLocation REINDEER_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/caribou.geo.json");

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
        return HorseBreed.breedFromOrdinal(object.getBreed()).resourceLocation;
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

