package com.dragn0007.dragnlivestock.blocks.client.render.model.entity;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.cow.OCow;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OCowModel extends DefaultedEntityGeoModel<OCow> {

    public OCowModel() {
        super(LivestockOverhaul.id("o_cow"), true);
    }

    @Override
    public void setCustomAnimations(OCow animatable, long instanceId, AnimationState<OCow> animationState) {

        CoreGeoBone neck = getAnimationProcessor().getBone("neck");
        CoreGeoBone head = getAnimationProcessor().getBone("head");
        CoreGeoBone left_ear = getAnimationProcessor().getBone("left_ear");
        CoreGeoBone right_ear = getAnimationProcessor().getBone("right_ear");
        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        if (neck != null && animatable.onGround()) {
            neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            neck.setRotY(neck.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }

        if (head != null && animatable.onGround()) {
            head.setRotX(head.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            head.setRotY(head.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }

        if (animatable.getBreed() == 2) {
            left_ear.setRotZ(-10);
            right_ear.setRotZ(10);
        } else {
            left_ear.setRotZ(-5);
            right_ear.setRotZ(5);
        }
    }

    public enum Variant {
        BLACK(LivestockOverhaul.id("textures/entity/cow/black.png")),
        BLUE(LivestockOverhaul.id("textures/entity/cow/blue.png")),
        BROWN(LivestockOverhaul.id("textures/entity/cow/brown.png")),
        CHESTNUT(LivestockOverhaul.id("textures/entity/cow/chestnut.png")),
        CREAM(LivestockOverhaul.id("textures/entity/cow/cream.png")),
        DARK_BROWN(LivestockOverhaul.id("textures/entity/cow/dark_brown.png")),
        GREY(LivestockOverhaul.id("textures/entity/cow/grey.png")),
        STRAWBERRY(LivestockOverhaul.id("textures/entity/cow/strawberry.png")),
        TAN(LivestockOverhaul.id("textures/entity/cow/tan.png")),
        WHITE(LivestockOverhaul.id("textures/entity/cow/white.png"));

        //Add new entries to bottom when mod is public, else cows will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation FEMALE = LivestockOverhaul.id("geo/cow/o_cow.geo.json");
    public static final ResourceLocation MALE = LivestockOverhaul.id("geo/cow/o_bull.geo.json");
    public static final ResourceLocation OX = LivestockOverhaul.id("geo/cow/ox.geo.json");
    public static final ResourceLocation MEAT_FEMALE = LivestockOverhaul.id("geo/cow/large_cow.geo.json");
    public static final ResourceLocation MEAT_MALE = LivestockOverhaul.id("geo/cow/large_bull.geo.json");
    public static final ResourceLocation MINI_FEMALE = LivestockOverhaul.id("geo/cow/mini_cow.geo.json");
    public static final ResourceLocation MINI_MALE = LivestockOverhaul.id("geo/cow/mini_bull.geo.json");
    public static final ResourceLocation BABY_MODEL = LivestockOverhaul.id("geo/cow/baby_o_cow.geo.json");
    public static final ResourceLocation ANIMATION = LivestockOverhaul.id("animations/o_cow.animation.json");

    @Override
    public ResourceLocation getModelResource(OCow object) {
        if (object.isBaby()) {
            return BABY_MODEL;
        } else if (object.getBreed() == 10) {
            return OX;
        } else if (object.isMeatBreed()) {
            if (object.isMale()) {
                return MEAT_MALE;
            } else {
                return MEAT_FEMALE;
            }
        } else if (object.isMiniBreed()) {
            if (object.isMale()) {
                return MINI_MALE;
            } else {
                return MINI_FEMALE;
            }
        } else {
            if (object.isMale()) {
                return MALE;
            } else {
                return FEMALE;
            }
        }
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

