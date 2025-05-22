package com.dragn0007.dragnlivestock.entities.sheep;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OSheepModel extends DefaultedEntityGeoModel<OSheep> {

    public OSheepModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o_sheep"), true);
    }

    @Override
    public void setCustomAnimations(OSheep animatable, long instanceId, AnimationState<OSheep> animationState) {

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
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/black.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/blue.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/brown.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/red.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/tan.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/white.png")),
        CHOCOLATE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/chocolate.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/cream.png")),
        DOBERMAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/doberman.png")),
        LIGHT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/light.png")),
        MAHOGANY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/mahogany.png")),
        DARK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/dark.png")),
        ;

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/sheep/o_sheep.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_sheep.animation.json");

    @Override
    public ResourceLocation getModelResource(OSheep object) {
        return MODEL;
    }

    public ResourceLocation getTextureResource(OSheep object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(OSheep animatable) {
        return ANIMATION;
    }
}

