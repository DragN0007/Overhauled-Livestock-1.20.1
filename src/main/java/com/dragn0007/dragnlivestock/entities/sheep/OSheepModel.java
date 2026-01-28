package com.dragn0007.dragnlivestock.entities.sheep;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
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

        if (!LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
            if (animatable.getBreed() == 7) {
                left_ear.setRotZ(-0);
                right_ear.setRotZ(0);
                left_ear.setScaleY(1.5F);
                right_ear.setScaleY(1.5F);
            } else {
                left_ear.setRotZ(7.5F);
                right_ear.setRotZ(-7.5F);
                left_ear.setScaleY(1);
                right_ear.setScaleY(1);
            }
        }
    }

    public static String default_path = "textures/entity/sheep/";
    public static String config_simplified_path = "textures/entity/config_simplified/sheep/";

    public enum Variant {
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, default_path + "black.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, default_path + "blue.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, default_path + "brown.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, default_path + "red.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, default_path + "tan.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, default_path + "white.png")),
        CHOCOLATE(new ResourceLocation(LivestockOverhaul.MODID, default_path + "chocolate.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, default_path + "cream.png")),
        DOBERMAN(new ResourceLocation(LivestockOverhaul.MODID, default_path + "doberman.png")),
        LIGHT(new ResourceLocation(LivestockOverhaul.MODID, default_path + "light.png")),
        MAHOGANY(new ResourceLocation(LivestockOverhaul.MODID, default_path + "mahogany.png")),
        DARK(new ResourceLocation(LivestockOverhaul.MODID, default_path + "dark.png")),
        ;

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public enum SVariant {
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "black.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "blue.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "brown.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "red.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "tan.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "white.png")),
        CHOCOLATE(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "chocolate.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "cream.png")),
        DOBERMAN(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "doberman.png")),
        LIGHT(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "light.png")),
        MAHOGANY(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "mahogany.png")),
        DARK(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "dark.png")),
        ;

        public final ResourceLocation resourceLocation;
        SVariant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static SVariant variantFromOrdinal(int variant) { return SVariant.values()[variant % SVariant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/sheep/o_sheep.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_sheep.animation.json");
    public static final ResourceLocation SIMPLIFIED_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/config_simplified/sheep.geo.json");
    public static final ResourceLocation SIMPLIFIED_ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/config_simplified/sheep.animation.json");

    @Override
    public ResourceLocation getModelResource(OSheep object) {
        if (!LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
            return MODEL;
        } else {
            return SIMPLIFIED_MODEL;
        }
    }

    public ResourceLocation getTextureResource(OSheep object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(OSheep animatable) {
        if (!LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
            return ANIMATION;
        } else {
            return SIMPLIFIED_ANIMATION;
        }
    }
}

