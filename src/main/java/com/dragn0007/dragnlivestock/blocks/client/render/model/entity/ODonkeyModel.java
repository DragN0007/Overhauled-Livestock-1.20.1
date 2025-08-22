package com.dragn0007.dragnlivestock.blocks.client.render.model.entity;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.ODonkey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ODonkeyModel extends DefaultedEntityGeoModel<ODonkey> {

    public ODonkeyModel() {
        super(LivestockOverhaul.id("o_donkey"), true);
    }

    @Override
    public void setCustomAnimations(ODonkey animatable, long instanceId, AnimationState<ODonkey> animationState) {

        CoreGeoBone neck = getAnimationProcessor().getBone("neck");

        if (neck != null && animatable.onGround()) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            neck.setRotY(neck.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }
    }

    public enum Variant {
        BROWN(LivestockOverhaul.id("textures/entity/donkey/brown.png")),
        BLACK(LivestockOverhaul.id("textures/entity/donkey/black.png")),
        CREAM(LivestockOverhaul.id("textures/entity/donkey/cream.png")),
        GREY(LivestockOverhaul.id("textures/entity/donkey/grey.png")),
        STRAWBERRY(LivestockOverhaul.id("textures/entity/donkey/strawberry.png")),
        WHITE(LivestockOverhaul.id("textures/entity/donkey/white.png"));

        //Add new entries to bottom when mod is public, else donkeys will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = LivestockOverhaul.id("geo/o_donkey.geo.json");
    public static final ResourceLocation ANIMATION = LivestockOverhaul.id("animations/o_horse.animation.json");

    public static final ResourceLocation BABY_MODEL = LivestockOverhaul.id("geo/baby_o_donkey.geo.json");

    @Override
    public ResourceLocation getModelResource(ODonkey object) {
        if(object.isBaby())
            return BABY_MODEL;
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(ODonkey object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(ODonkey animatable) {
        return ANIMATION;
    }
}

