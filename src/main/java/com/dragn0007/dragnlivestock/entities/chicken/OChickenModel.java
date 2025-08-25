package com.dragn0007.dragnlivestock.entities.chicken;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
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

    public enum Variant {
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/black.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/blue.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/brown.png")),
        CHOCOLATE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/chocolate.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/cream.png")),
        GOLD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/gold.png")),
        LILAC(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/lilac.png")),
        MAHOGANY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/mahogany.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/red.png")),
        SILVER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/silver.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/tan.png")),
        VOID(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/void.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/white.png")),
        ;

        //Add new entries to bottom when mod is public, else chickens will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_chicken.animation.json");
    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/baby_o_chicken.geo.json");
    public static final ResourceLocation BABY_TEXTURE = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/cream.png");

    @Override
    public ResourceLocation getModelResource(OChicken object) {
        if(object.isBaby())
            return BABY_MODEL;
        return ChickenBreed.Breed.breedFromOrdinal(object.getBreed()).resourceLocation;
    }

    @Override
    public ResourceLocation getTextureResource(OChicken object) {
        if(object.isBaby())
            return BABY_TEXTURE;
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(OChicken animatable) {
        return ANIMATION;
    }
}

