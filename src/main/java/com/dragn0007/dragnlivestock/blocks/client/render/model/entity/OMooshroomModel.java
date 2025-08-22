package com.dragn0007.dragnlivestock.blocks.client.render.model.entity;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.cow.OMooshroom;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OMooshroomModel extends DefaultedEntityGeoModel<OMooshroom> {

    public OMooshroomModel() {
        super(LivestockOverhaul.id("o_mooshroom"), true);
    }

    @Override
    public void setCustomAnimations(OMooshroom animatable, long instanceId, AnimationState<OMooshroom> animationState) {

        CoreGeoBone neck = getAnimationProcessor().getBone("neck");
        CoreGeoBone head = getAnimationProcessor().getBone("head");
        CoreGeoBone left_ear = getAnimationProcessor().getBone("left_ear");
        CoreGeoBone right_ear = getAnimationProcessor().getBone("right_ear");
        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        if (neck != null) {
            neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            neck.setRotY(neck.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }

        if (head != null) {
            head.setRotX(head.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            head.setRotY(head.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
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
        WHITE(LivestockOverhaul.id("textures/entity/cow/white.png")),
        BROWN_MUSHROOM(LivestockOverhaul.id("textures/entity/moobloom/brown_mushroom.png")),
        RED_MUSHROOM(LivestockOverhaul.id("textures/entity/moobloom/red_mushroom.png"));

        //Add new entries to bottom when mod is public, else mooshrooms will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation FEMALE = LivestockOverhaul.id("geo/moobloom/o_mooshroom.geo.json");
    public static final ResourceLocation MALE = LivestockOverhaul.id("geo/moobloom/mooshroom_bull.geo.json");
    public static final ResourceLocation BABY_MODEL = LivestockOverhaul.id("geo/cow/baby_o_cow.geo.json");
    public static final ResourceLocation ANIMATION = LivestockOverhaul.id("animations/o_cow.animation.json");

    @Override
    public ResourceLocation getModelResource(OMooshroom object) {
        if (object.isBaby()) {
            return BABY_MODEL;
        } else {
            if (object.isMale()) {
                return MALE;
            } else {
                return FEMALE;
            }
        }
    }

    @Override
    public ResourceLocation getTextureResource(OMooshroom object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(OMooshroom animatable) {
        return ANIMATION;
    }
}

