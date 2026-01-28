package com.dragn0007.dragnlivestock.entities.chicken;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OChickenModel extends DefaultedEntityGeoModel<OChicken> {

    public OChickenModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o_chicken"), true);
    }

    @Override
    public void setCustomAnimations(OChicken animatable, long instanceId, AnimationState<OChicken> animationState) {

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

    public static String default_path = "textures/entity/chicken/";
    public static String config_simplified_path = "textures/entity/config_simplified/chicken/";

    public enum Variant {
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, default_path + "black.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, default_path + "blue.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, default_path + "brown.png")),
        CHOCOLATE(new ResourceLocation(LivestockOverhaul.MODID, default_path + "chocolate.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, default_path + "cream.png")),
        GOLD(new ResourceLocation(LivestockOverhaul.MODID, default_path + "gold.png")),
        LILAC(new ResourceLocation(LivestockOverhaul.MODID, default_path + "lilac.png")),
        MAHOGANY(new ResourceLocation(LivestockOverhaul.MODID, default_path + "mahogany.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, default_path + "red.png")),
        SILVER(new ResourceLocation(LivestockOverhaul.MODID, default_path + "silver.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, default_path + "tan.png")),
        VOID(new ResourceLocation(LivestockOverhaul.MODID, default_path + "void.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, default_path + "white.png")),
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
        CHOCOLATE(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "chocolate.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "cream.png")),
        GOLD(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "gold.png")),
        LILAC(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "lilac.png")),
        MAHOGANY(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "mahogany.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "red.png")),
        SILVER(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "silver.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "tan.png")),
        VOID(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "void.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "white.png")),
        ;

        public final ResourceLocation resourceLocation;
        SVariant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static SVariant variantFromOrdinal(int variant) { return SVariant.values()[variant % SVariant.values().length];
        }
    }

    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_chicken.animation.json");
    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/baby_o_chicken.geo.json");
    public static final ResourceLocation BABY_TEXTURE = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/cream.png");
    public static final ResourceLocation SIMPLIFIED_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/config_simplified/chicken.geo.json");
    public static final ResourceLocation SIMPLIFIED_ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/config_simplified/chicken.animation.json");

    @Override
    public ResourceLocation getModelResource(OChicken object) {
        if (!LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
            return ChickenBreed.Breed.breedFromOrdinal(object.getBreed()).resourceLocation;
        } else {
            return SIMPLIFIED_MODEL;
        }
    }

    @Override
    public ResourceLocation getTextureResource(OChicken object) {
        if(object.isBaby() && !LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
            return BABY_TEXTURE;
        } else {
            return object.getTextureResource();
        }
    }

    @Override
    public ResourceLocation getAnimationResource(OChicken animatable) {
        if (!LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
            return ANIMATION;
        } else {
            return SIMPLIFIED_ANIMATION;
        }
    }
}

