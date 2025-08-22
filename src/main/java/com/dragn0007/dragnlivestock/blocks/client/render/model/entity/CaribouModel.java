package com.dragn0007.dragnlivestock.blocks.client.render.model.entity;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.Caribou;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class CaribouModel extends DefaultedEntityGeoModel<Caribou> {

    public CaribouModel() {
        super(LivestockOverhaul.id("caribou"), true);
    }

    @Override
    public void setCustomAnimations(Caribou animatable, long instanceId, AnimationState<Caribou> animationState) {

        CoreGeoBone neck = getAnimationProcessor().getBone("neck");

        if (neck != null && animatable.onGround()) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            neck.setRotY(neck.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }
    }

    public enum Variant {
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
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation ANIMATION = LivestockOverhaul.id("animations/o_horse.animation.json");
    public static final ResourceLocation BABY_MODEL = LivestockOverhaul.id("geo/baby_caribou.geo.json");
    public static final ResourceLocation MODEL = LivestockOverhaul.id("geo/caribou.geo.json");

    @Override
    public ResourceLocation getModelResource(Caribou object) {
        if (object.isBaby()) {
            return BABY_MODEL;
        }
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Caribou object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(Caribou animatable) {
        return ANIMATION;
    }
}

