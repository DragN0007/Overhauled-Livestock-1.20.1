package com.dragn0007.dragnlivestock.entities.llama;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.sheep.OSheep;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OLlamaModel extends DefaultedEntityGeoModel<OLlama> {

    public OLlamaModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o_llama"), true);
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
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/black.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/blue.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/brown.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/grey.png")),
        LIGHT_GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/light_grey.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/red.png")),
        SANDY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/sandy.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/tan.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/white.png"));

        //Add new entries to bottom when mod is public, else llamas will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/o_llama.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_llama.animation.json");

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

