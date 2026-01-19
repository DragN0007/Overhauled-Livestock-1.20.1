package com.dragn0007.dragnlivestock.entities.mule;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import java.util.HashMap;
import java.util.Map;

public class OMuleModel extends DefaultedEntityGeoModel<OMule> {

    public OMuleModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o_mule"), true);
    }

    @Override
    public void setCustomAnimations(OMule animatable, long instanceId, AnimationState<OMule> animationState) {

        CoreGeoBone neck = getAnimationProcessor().getBone("neck");

        if (neck != null && animatable.onGround()) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            neck.setRotY(neck.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }
    }

    public static String default_path = "textures/entity/mule/";
    public static String config_simplified_path = "textures/entity/config_simplified/mule/";

    public enum Variant {
        RUST(new ResourceLocation(LivestockOverhaul.MODID, default_path + "rust.png")),
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, default_path + "black.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, default_path + "blue.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, default_path + "brown.png")),
        CHESTNUT(new ResourceLocation(LivestockOverhaul.MODID, default_path + "chestnut.png")),
        CINNAMON(new ResourceLocation(LivestockOverhaul.MODID, default_path + "cinnamon.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, default_path + "cream.png")),
        CREAMY(new ResourceLocation(LivestockOverhaul.MODID, default_path + "creamy.png")),
        DARK_BROWN(new ResourceLocation(LivestockOverhaul.MODID, default_path + "dark_brown.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, default_path + "grey.png")),
        LIVER_CHESTNUT(new ResourceLocation(LivestockOverhaul.MODID, default_path + "liver_chestnut.png")),
        PALAMINO(new ResourceLocation(LivestockOverhaul.MODID, default_path + "palamino.png")),
        STRAWBERRY(new ResourceLocation(LivestockOverhaul.MODID, default_path + "strawberry.png")),
        WARM_BLACK(new ResourceLocation(LivestockOverhaul.MODID, default_path + "warm_black.png")),
        WARM_GREY(new ResourceLocation(LivestockOverhaul.MODID, default_path + "warm_grey.png")),
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
        RUST(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "rust.png")),
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "black.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "blue.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "brown.png")),
        CHESTNUT(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "chestnut.png")),
        CINNAMON(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "cinnamon.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "cream.png")),
        CREAMY(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "creamy.png")),
        DARK_BROWN(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "dark_brown.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "grey.png")),
        LIVER_CHESTNUT(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "liver_chestnut.png")),
        PALAMINO(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "palamino.png")),
        STRAWBERRY(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "strawberry.png")),
        WARM_BLACK(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "warm_black.png")),
        WARM_GREY(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "warm_grey.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "white.png")),
        ;

        public final ResourceLocation resourceLocation;
        SVariant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static SVariant variantFromOrdinal(int variant) { return SVariant.values()[variant % SVariant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/o_mule.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_horse.animation.json");
    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/baby_o_donkey.geo.json");
    public static final ResourceLocation SIMPLIFIED_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/config_simplified/mule.geo.json");
    public static final ResourceLocation SIMPLIFIED_ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/config_simplified/horse.animation.json");

    @Override
    public ResourceLocation getModelResource(OMule object) {
        if (!LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
            if (object.isBaby()) {
                return BABY_MODEL;
            }
            return MuleBreed.breedFromOrdinal(object.getBreed()).resourceLocation;
        } else {
            return SIMPLIFIED_MODEL;
        }
    }

    public static final Map<String, ResourceLocation> TEXTURE_CACHE = new HashMap<>();

    @Override
    public ResourceLocation getTextureResource(OMule object) {
        if (!LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
            return TEXTURE_CACHE.computeIfAbsent(object.getTextureResource(), ResourceLocation::tryParse);
        } else {
            return object.getSimplifiedVariantTextureResource();
        }
    }

    @Override
    public ResourceLocation getAnimationResource(OMule animatable) {
        if (!LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
            return ANIMATION;
        } else {
            return SIMPLIFIED_ANIMATION;
        }
    }
}

