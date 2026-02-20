package com.dragn0007.dragnlivestock.entities.caribou;

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

public class CaribouModel extends DefaultedEntityGeoModel<Caribou> {

    public CaribouModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "caribou"), true);
    }

    @Override
    public void setCustomAnimations(Caribou animatable, long instanceId, AnimationState<Caribou> animationState) {

        CoreGeoBone neck = getAnimationProcessor().getBone("neck");

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        float targetYaw = entityData.netHeadYaw();

        if (neck != null) {
            if (!animatable.onGround() || animatable.isJumping()) {
                targetYaw = Mth.clamp(targetYaw, -25.0f, 25.0f);
            }
            neck.setRotY(targetYaw * Mth.DEG_TO_RAD);
            neck.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
        }
    }

    public enum Variant {
        BAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/bay.png")),
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/black.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/blue.png")),
        CHAMPAGNE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/champagne.png")),
        CHESTNUT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/chestnut.png")),
        CHOCOLATE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/chocolate.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/cream.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/grey.png")),
        IVORY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/ivory.png")),
        LIGHT_GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/light_grey.png")),
        LIVER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/liver_chestnut.png")),
        PALAMINO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/palamino.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/red.png")),
        SEAL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/seal.png")),
        STRAWBERRY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/strawberry.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/tan.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/reindeer/white.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_horse.animation.json");
    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/baby_caribou.geo.json");
    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/caribou.geo.json");

    @Override
    public ResourceLocation getModelResource(Caribou object) {
        if (object.isBaby()) {
            return BABY_MODEL;
        }
        return MODEL;
    }

    public static final Map<String, ResourceLocation> TEXTURE_CACHE = new HashMap<>();

    @Override
    public ResourceLocation getTextureResource(Caribou object) {
        return TEXTURE_CACHE.computeIfAbsent(object.getTextureResource(), ResourceLocation::tryParse);
    }

    @Override
    public ResourceLocation getAnimationResource(Caribou animatable) {
        return ANIMATION;
    }
}

