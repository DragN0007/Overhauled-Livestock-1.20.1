package com.dragn0007.dragnlivestock.entities.cow;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OCowModel extends DefaultedEntityGeoModel<OCow> {

    public OCowModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o_cow"), true);
    }

    @Override
    public void setCustomAnimations(OCow animatable, long instanceId, AnimationState<OCow> animationState) {

        CoreGeoBone neck = getAnimationProcessor().getBone("neck");
        CoreGeoBone head = getAnimationProcessor().getBone("head");
        CoreGeoBone left_ear = getAnimationProcessor().getBone("left_ear");
        CoreGeoBone right_ear = getAnimationProcessor().getBone("right_ear");

        if (neck != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            neck.setRotY(neck.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(head.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            head.setRotY(head.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }

        if (animatable.getBreed() == 2) {
            left_ear.setRotZ(-10);
            right_ear.setRotZ(10);
        }
    }

    public enum Variant {
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/black.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/blue.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/brown.png")),
        CHESTNUT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/chestnut.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/cream.png")),
        DARK_BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/dark_brown.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/grey.png")),
        STRAWBERRY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/strawberry.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/tan.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/white.png"));

        //Add new entries to bottom when mod is public, else cows will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation FEMALE = new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/o_cow.geo.json");
    public static final ResourceLocation MALE = new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/o_bull.geo.json");
    public static final ResourceLocation MEAT_FEMALE = new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/large_cow.geo.json");
    public static final ResourceLocation MEAT_MALE = new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/large_bull.geo.json");
    public static final ResourceLocation MINI_FEMALE = new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/mini_cow.geo.json");
    public static final ResourceLocation MINI_MALE = new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/mini_bull.geo.json");
    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/baby_o_cow.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_cow.animation.json");

    @Override
    public ResourceLocation getModelResource(OCow object) {
        if (object.isBaby()) {
            return BABY_MODEL;
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

