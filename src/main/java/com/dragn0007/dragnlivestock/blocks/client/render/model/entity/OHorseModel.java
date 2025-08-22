package com.dragn0007.dragnlivestock.blocks.client.render.model.entity;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.horse.HorseBreed;
import com.dragn0007.dragnlivestock.common.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.horse.OHorseDecompLayer;
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
        super(LivestockOverhaul.id("o_horse"), true);
    }

    @Override
    public void setCustomAnimations(OHorse animatable, long instanceId, AnimationState<OHorse> animationState) {

        CoreGeoBone neck = getAnimationProcessor().getBone("neck");
        CoreGeoBone head = getAnimationProcessor().getBone("head");
        CoreGeoBone reins = getAnimationProcessor().getBone("reins");

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        if (animatable.isWearingMartingale() && animatable.isSaddled()) {
            neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD - 0.2f));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            neck.setRotY(neck.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        } else if (animatable.onGround()) {
            neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            neck.setRotY(neck.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }

        if (animatable.isWearingMartingale() && animatable.isSaddled()) {
            head.setRotX(head.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD - 0.2f));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            head.setRotY(head.getRotY() + (maxYaw * Mth.DEG_TO_RAD));

            reins.setRotX(Mth.DEG_TO_RAD + 0.6f);
        }
    }

    public enum Variant {
        BAY(LivestockOverhaul.id("textures/entity/horse/bay.png")),
        BAY_ROAN(LivestockOverhaul.id("textures/entity/horse/bay_roan.png")),
        BLACK(LivestockOverhaul.id("textures/entity/horse/black.png")),
        BLOOD_BAY(LivestockOverhaul.id("textures/entity/horse/blood_bay.png")),
        BLUE(LivestockOverhaul.id("textures/entity/horse/blue.png")),
        BLUE_ROAN(LivestockOverhaul.id("textures/entity/horse/blue_roan.png")),
        BROWN(LivestockOverhaul.id("textures/entity/horse/brown.png")),
        BUCKSKIN(LivestockOverhaul.id("textures/entity/horse/buckskin.png")),
        CHAMPAGNE(LivestockOverhaul.id("textures/entity/horse/champagne.png")),
        CHOCOLATE_ROAN(LivestockOverhaul.id("textures/entity/horse/chocolate_roan.png")),
        CHESTNUT(LivestockOverhaul.id("textures/entity/horse/chestnut.png")),
        CREAMY(LivestockOverhaul.id("textures/entity/horse/creamy.png")),
        DARK_BAY(LivestockOverhaul.id("textures/entity/horse/dark_bay.png")),
        DARK_BROWN(LivestockOverhaul.id("textures/entity/horse/dark_brown.png")),
        FJORD(LivestockOverhaul.id("textures/entity/horse/fjord.png")),
        GREY(LivestockOverhaul.id("textures/entity/horse/grey.png")),
        IVORY(LivestockOverhaul.id("textures/entity/horse/ivory.png")),
        LIVER_CHESTNUT(LivestockOverhaul.id("textures/entity/horse/liver_chestnut.png")),
        PALAMINO(LivestockOverhaul.id("textures/entity/horse/palamino.png")),
        PALAMINO_ORANGE(LivestockOverhaul.id("textures/entity/horse/palamino_orange.png")),
        SEAL_BAY(LivestockOverhaul.id("textures/entity/horse/seal_bay.png")),
        STRAWBERRY(LivestockOverhaul.id("textures/entity/horse/strawberry.png")),
        WARM_BLACK(LivestockOverhaul.id("textures/entity/horse/warm_black.png")),
        WARM_GREY(LivestockOverhaul.id("textures/entity/horse/warm_grey.png")),
        WHITE(LivestockOverhaul.id("textures/entity/horse/white.png")),
        CREAM(LivestockOverhaul.id("textures/entity/horse/cream.png")),
        RED_DUN(LivestockOverhaul.id("textures/entity/horse/red_dun.png")),
        BAY_DUN(LivestockOverhaul.id("textures/entity/horse/bay_dun.png")),
        GRULLA(LivestockOverhaul.id("textures/entity/horse/grulla.png")),
        BLUE_DUN(LivestockOverhaul.id("textures/entity/horse/blue_dun.png")),
        CINNAMON(LivestockOverhaul.id("textures/entity/horse/cinnamon.png")),
        STRAWBERRY_ROAN(LivestockOverhaul.id("textures/entity/horse/strawberry_roan.png"));

        //Add new entries to bottom when mod is public, else horses will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public enum ReindeerVariant {
        BAY(LivestockOverhaul.id("textures/entity/reindeer/bay.png")),
        BLACK(LivestockOverhaul.id("textures/entity/reindeer/black.png")),
        BLUE(LivestockOverhaul.id("textures/entity/reindeer/blue.png")),
        CHAMPAGNE(LivestockOverhaul.id("textures/entity/reindeer/champagne.png")),
        CHESTNUT(LivestockOverhaul.id("textures/entity/reindeer/chestnut.png")),
        CHOCOLATE(LivestockOverhaul.id("textures/entity/reindeer/chocolate.png")),
        CREAM(LivestockOverhaul.id("textures/entity/reindeer/cream.png")),
        GREY(LivestockOverhaul.id("textures/entity/reindeer/grey.png")),
        IVORY(LivestockOverhaul.id("textures/entity/reindeer/ivory.png")),
        LIGHT_GREY(LivestockOverhaul.id("textures/entity/reindeer/light_grey.png")),
        LIVER(LivestockOverhaul.id("textures/entity/reindeer/liver_chestnut.png")),
        PALAMINO(LivestockOverhaul.id("textures/entity/reindeer/palamino.png")),
        RED(LivestockOverhaul.id("textures/entity/reindeer/red.png")),
        SEAL(LivestockOverhaul.id("textures/entity/reindeer/seal.png")),
        STRAWBERRY(LivestockOverhaul.id("textures/entity/reindeer/strawberry.png")),
        TAN(LivestockOverhaul.id("textures/entity/reindeer/tan.png")),
        WHITE(LivestockOverhaul.id("textures/entity/reindeer/white.png"));

        public final ResourceLocation resourceLocation;
        ReindeerVariant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static ReindeerVariant reindeerVariantFromOrdinal(int christmasVariant) { return ReindeerVariant.values()[christmasVariant % ReindeerVariant.values().length];
        }
    }

    public static final ResourceLocation ANIMATION = LivestockOverhaul.id("animations/o_horse.animation.json");
    public static final ResourceLocation BABY_MODEL = LivestockOverhaul.id("geo/horse/baby_o_horse.geo.json");
    public static final ResourceLocation REINDEER_MODEL = LivestockOverhaul.id("geo/caribou.geo.json");

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
        if (!object.isUndead() && !(object.getDecompVariant() >= 3)) {
            if (month == Month.DECEMBER && (day == 24 || day == 25)) {
                return object.getReindeerTextureResource();
            }
            return object.getTextureResource();
        } else if (object.getDecompVariant() == 4) {
            return OHorseDecompLayer.UndeadStage.SKELETAL.resourceLocation;
        } else if (object.getDecompVariant() == 5) {
            return OHorseDecompLayer.UndeadStage.WITHER.resourceLocation;
        } else if (object.getDecompVariant() == 6) {
            return OHorseDecompLayer.UndeadStage.STRAY.resourceLocation;
        }
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(OHorse animatable) {
        return ANIMATION;
    }
}

