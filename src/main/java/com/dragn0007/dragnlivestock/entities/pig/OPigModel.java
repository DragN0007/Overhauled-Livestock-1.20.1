package com.dragn0007.dragnlivestock.entities.pig;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OPigModel extends DefaultedEntityGeoModel<OPig> {

    public OPigModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o_pig"), true);
    }

    @Override
    public void setCustomAnimations(OPig animatable, long instanceId, AnimationState<OPig> animationState) {

        CoreGeoBone neck = getAnimationProcessor().getBone("neck");
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (neck != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -35.0f, 35.0f);
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
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/black.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/blue.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/brown.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/grey.png")),
        LIGHT_GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/light_grey.png")),
        PINK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/pink.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/red.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/white.png"));

        //Add new entries to bottom when mod is public, else pigs will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/pig/o_pig.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_pig.animation.json");

    @Override
    public ResourceLocation getModelResource(OPig object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(OPig object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(OPig animatable) {
        return ANIMATION;
    }
}

