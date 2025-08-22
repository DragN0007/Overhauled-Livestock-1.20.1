package com.dragn0007.dragnlivestock.blocks.client.render.model.entity;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.llama.OLlama;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OLlamaModel extends DefaultedEntityGeoModel<OLlama> {

    public OLlamaModel() {
        super(LivestockOverhaul.id("o_llama"), true);
    }

    @Override
    public void setCustomAnimations(OLlama animatable, long instanceId, AnimationState<OLlama> animationState) {

        CoreGeoBone neck = getAnimationProcessor().getBone("neck");
        CoreGeoBone head = getAnimationProcessor().getBone("head");

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
    }

    public enum Variant {
        BLACK(LivestockOverhaul.id("textures/entity/llama/black.png")),
        BLUE(LivestockOverhaul.id("textures/entity/llama/blue.png")),
        BROWN(LivestockOverhaul.id("textures/entity/llama/brown.png")),
        GREY(LivestockOverhaul.id("textures/entity/llama/grey.png")),
        LIGHT_GREY(LivestockOverhaul.id("textures/entity/llama/light_grey.png")),
        RED(LivestockOverhaul.id("textures/entity/llama/red.png")),
        SANDY(LivestockOverhaul.id("textures/entity/llama/sandy.png")),
        TAN(LivestockOverhaul.id("textures/entity/llama/tan.png")),
        WHITE(LivestockOverhaul.id("textures/entity/llama/white.png"));

        //Add new entries to bottom when mod is public, else llamas will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = LivestockOverhaul.id("geo/o_llama.geo.json");
    public static final ResourceLocation ANIMATION = LivestockOverhaul.id("animations/o_llama.animation.json");

    @Override
    public ResourceLocation getModelResource(OLlama object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(OLlama object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(OLlama animatable) {
        return ANIMATION;
    }
}

