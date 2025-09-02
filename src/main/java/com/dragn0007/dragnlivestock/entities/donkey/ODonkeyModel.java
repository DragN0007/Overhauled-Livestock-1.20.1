package com.dragn0007.dragnlivestock.entities.donkey;

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

public class ODonkeyModel extends DefaultedEntityGeoModel<ODonkey> {

    public ODonkeyModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o_donkey"), true);
    }

    @Override
    public void setCustomAnimations(ODonkey animatable, long instanceId, AnimationState<ODonkey> animationState) {

        CoreGeoBone neck = getAnimationProcessor().getBone("neck");

        if (neck != null && animatable.onGround()) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            neck.setRotY(neck.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }
    }

    public enum Variant {
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/donkey/brown.png")),
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/donkey/black.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/donkey/cream.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/donkey/grey.png")),
        STRAWBERRY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/donkey/strawberry.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/donkey/white.png"));

        //Add new entries to bottom when mod is public, else donkeys will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/o_donkey.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_horse.animation.json");

    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/baby_o_donkey.geo.json");

    @Override
    public ResourceLocation getModelResource(ODonkey object) {
        if(object.isBaby())
            return BABY_MODEL;
        return MODEL;
    }

    public static final Map<String, ResourceLocation> TEXTURE_CACHE = new HashMap<>();


    @Override
    public ResourceLocation getTextureResource(ODonkey object) {
        return TEXTURE_CACHE.computeIfAbsent(object.getOverlayLocation(), ResourceLocation::tryParse);
    }

    @Override
    public ResourceLocation getAnimationResource(ODonkey animatable) {
        return ANIMATION;
    }
}

