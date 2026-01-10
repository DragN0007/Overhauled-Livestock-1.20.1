package com.dragn0007.dragnlivestock.entities.cow;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OCowModel extends DefaultedEntityGeoModel<OCow> {

    public OCowModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o_cow"), true);
    }

    @Override
    public void setCustomAnimations(OCow animatable, long instanceId, AnimationState<OCow> animationState) {

        CoreGeoBone neck = getAnimationProcessor().getBone("neck");
        CoreGeoBone head = getAnimationProcessor().getBone("head");
        CoreGeoBone left_ear = getAnimationProcessor().getBone("left_ear");
        CoreGeoBone right_ear = getAnimationProcessor().getBone("right_ear");
        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        if (neck != null && animatable.onGround()) {
            neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            neck.setRotY(neck.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }

        if (head != null && animatable.onGround()) {
            head.setRotX(head.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            head.setRotY(head.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }

        if (!LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
            if (animatable.getBreed() == 2) {
                left_ear.setRotZ(-10);
                right_ear.setRotZ(10);
            } else {
                left_ear.setRotZ(-5);
                right_ear.setRotZ(5);
            }
        }
    }

    public static String default_path = "textures/entity/cow/";
    public static String config_simplified_path = "textures/entity/config_simplified/cow/";

    public enum Variant {
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, default_path + "black.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, default_path + "blue.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, default_path + "brown.png")),
        CHESTNUT(new ResourceLocation(LivestockOverhaul.MODID, default_path + "chestnut.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, default_path + "cream.png")),
        DARK_BROWN(new ResourceLocation(LivestockOverhaul.MODID, default_path + "dark_brown.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, default_path + "grey.png")),
        STRAWBERRY(new ResourceLocation(LivestockOverhaul.MODID, default_path + "strawberry.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, default_path + "tan.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, default_path + "white.png")),
        CHOCOLATE(new ResourceLocation(LivestockOverhaul.MODID, default_path + "chocolate.png")),
        GOLD(new ResourceLocation(LivestockOverhaul.MODID, default_path + "gold.png")),
        MAHOGANY(new ResourceLocation(LivestockOverhaul.MODID, default_path + "mahogany.png")),
        SILVER(new ResourceLocation(LivestockOverhaul.MODID, default_path + "silver.png"))
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
        CHESTNUT(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "chestnut.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "cream.png")),
        DARK_BROWN(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "dark_brown.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "grey.png")),
        STRAWBERRY(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "strawberry.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "tan.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "white.png")),
        CHOCOLATE(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "chocolate.png")),
        GOLD(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "gold.png")),
        MAHOGANY(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "mahogany.png")),
        SILVER(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "silver.png"))
        ;

        public final ResourceLocation resourceLocation;
        SVariant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static SVariant variantFromOrdinal(int variant) { return SVariant.values()[variant % SVariant.values().length];
        }
    }

    public static final ResourceLocation FEMALE = new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/o_cow.geo.json");
    public static final ResourceLocation MALE = new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/o_bull.geo.json");
    public static final ResourceLocation OX = new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/ox.geo.json");
    public static final ResourceLocation MEAT_FEMALE = new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/large_cow.geo.json");
    public static final ResourceLocation MEAT_MALE = new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/large_bull.geo.json");
    public static final ResourceLocation MINI_FEMALE = new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/mini_cow.geo.json");
    public static final ResourceLocation MINI_MALE = new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/mini_bull.geo.json");
    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/baby_o_cow.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_cow.animation.json");
    public static final ResourceLocation SIMPLIFIED_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/config_simplified/cow.geo.json");
    public static final ResourceLocation SIMPLIFIED_ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/config_simplified/cow.animation.json");

    @Override
    public ResourceLocation getModelResource(OCow object) {
        if (!LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
            if (object.isBaby()) {
                return BABY_MODEL;
            } else if (object.getBreed() == 10) {
                return OX;
            } else if (object.isMeatBreed()) {
                if (object.isMale()) {
                    return MEAT_MALE;
                } else {
                    return MEAT_FEMALE;
                }
            } else if (object.isMiniBreed()) {
                if (object.isMale()) {
                    return MINI_MALE;
                } else {
                    return MINI_FEMALE;
                }
            } else {
                if (object.isMale()) {
                    return MALE;
                } else {
                    return FEMALE;
                }
            }
        } else {
            return SIMPLIFIED_MODEL;
        }
    }

    @Override
    public ResourceLocation getTextureResource(OCow object) {
        if (!LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
            return object.getTextureLocation();
        } else {
            return object.getSimplifiedVariantTextureResource();
        }
    }

    @Override
    public ResourceLocation getAnimationResource(OCow animatable) {
        if (!LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
            return ANIMATION;
        } else {
            return SIMPLIFIED_ANIMATION;
        }
    }
}

