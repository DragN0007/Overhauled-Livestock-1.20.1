package com.dragn0007.dragnlivestock.entities.goat;

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

public class OGoatModel extends DefaultedEntityGeoModel<OGoat> {

    public OGoatModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o_goat"), true);
    }

    @Override
    public void setCustomAnimations(OGoat animatable, long instanceId, AnimationState<OGoat> animationState) {

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
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/goat/goat.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/goat/goat_grey.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/goat/goat_red.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/goat/goat_brown.png")),
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/goat/goat_black.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/goat_overhaul.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/goat_overhaul.animation.json");

    @Override
    public ResourceLocation getModelResource(OGoat object) {
        return MODEL;
    }

    public ResourceLocation getTextureResource(OGoat object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(OGoat animatable) {
        return ANIMATION;
    }
}

