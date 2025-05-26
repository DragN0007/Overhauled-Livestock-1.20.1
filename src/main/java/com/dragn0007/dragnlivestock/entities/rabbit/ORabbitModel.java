package com.dragn0007.dragnlivestock.entities.rabbit;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ORabbitModel extends DefaultedEntityGeoModel<ORabbit> {

    public ORabbitModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o_rabbit"), true);
    }

    @Override
    public void setCustomAnimations(ORabbit animatable, long instanceId, AnimationState<ORabbit> animationState) {

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
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/rabbit_black.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/rabbit_brown.png")),
        GOLD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/rabbit_gold.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/rabbit_grey.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/rabbit_red.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/rabbit_tan.png")),
        WARM_GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/rabbit_warm_grey.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/rabbit_white.png")),
        CLOVER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/rabbit_clover_brown.png")),
        JACKRABBIT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/rabbit_jackrabbit_tan.png"));

        //Add new entries to bottom when mod is public, else rabbits will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

//    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/rabbit_overhauled.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/rabbit_overhauled.animation.json");

    @Override
    public ResourceLocation getModelResource(ORabbit object) {
        return RabbitBreed.Breed.breedFromOrdinal(object.getBreed()).resourceLocation;
    }

    @Override
    public ResourceLocation getTextureResource(ORabbit object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(ORabbit animatable) {
        return ANIMATION;
    }
}

