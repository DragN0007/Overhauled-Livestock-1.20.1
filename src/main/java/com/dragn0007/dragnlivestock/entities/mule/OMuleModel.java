package com.dragn0007.dragnlivestock.entities.mule;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
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

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        float targetYaw = entityData.netHeadYaw();

        if (neck != null) {
            if (animatable.isVehicle()) {
                if (!animatable.onGround() || animatable.isJumping()) {
                    targetYaw = Mth.clamp(targetYaw, -25.0f, 25.0f);
                }
                neck.setRotY(targetYaw * Mth.DEG_TO_RAD);
                neck.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            } else {
                neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
                float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
                neck.setRotY(neck.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
            }
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/o_mule.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_horse.animation.json");
    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/baby_o_donkey.geo.json");

    @Override
    public ResourceLocation getModelResource(OMule object) {
        if (object.isBaby()) return BABY_MODEL;
        return MuleBreed.breedFromOrdinal(object.getBreed()).resourceLocation;
    }

    public static final Map<String, ResourceLocation> TEXTURE_CACHE = new HashMap<>();

    @Override
    public ResourceLocation getTextureResource(OMule object) {
        return TEXTURE_CACHE.computeIfAbsent(object.getTextureResource(), ResourceLocation::tryParse);
    }

    @Override
    public ResourceLocation getAnimationResource(OMule animatable) {return ANIMATION;}
}

