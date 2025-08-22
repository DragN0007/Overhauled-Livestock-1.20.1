package com.dragn0007.dragnlivestock.blocks.client.render.model.entity;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.mule.MuleBreed;
import com.dragn0007.dragnlivestock.common.entities.mule.OMule;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OMuleModel extends DefaultedEntityGeoModel<OMule> {

    public OMuleModel() {
        super(LivestockOverhaul.id("o_mule"), true);
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

    public enum Variant {
        RUST(LivestockOverhaul.id("textures/entity/mule/rust.png")),
        BLACK(LivestockOverhaul.id("textures/entity/mule/black.png")),
        BLUE(LivestockOverhaul.id("textures/entity/mule/blue.png")),
        BROWN(LivestockOverhaul.id("textures/entity/mule/brown.png")),
        CHESTNUT(LivestockOverhaul.id("textures/entity/mule/chestnut.png")),
        CINNAMON(LivestockOverhaul.id("textures/entity/mule/cinnamon.png")),
        CREAM(LivestockOverhaul.id("textures/entity/mule/cream.png")),
        CREAMY(LivestockOverhaul.id("textures/entity/mule/creamy.png")),
        DARK_BROWN(LivestockOverhaul.id("textures/entity/mule/dark_brown.png")),
        GREY(LivestockOverhaul.id("textures/entity/mule/grey.png")),
        LIVER_CHESTNUT(LivestockOverhaul.id("textures/entity/mule/liver_chestnut.png")),
        PALAMINO(LivestockOverhaul.id("textures/entity/mule/palamino.png")),
        STRAWBERRY(LivestockOverhaul.id("textures/entity/mule/strawberry.png")),
        WARM_BLACK(LivestockOverhaul.id("textures/entity/mule/warm_black.png")),
        WARM_GREY(LivestockOverhaul.id("textures/entity/mule/warm_grey.png")),
        WHITE(LivestockOverhaul.id("textures/entity/mule/white.png")),
        ;

        //Add new entries to bottom when mod is public, else mules will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = LivestockOverhaul.id("geo/o_mule.geo.json");
    public static final ResourceLocation ANIMATION = LivestockOverhaul.id("animations/o_horse.animation.json");

    public static final ResourceLocation BABY_MODEL = LivestockOverhaul.id("geo/baby_o_donkey.geo.json");

    @Override
    public ResourceLocation getModelResource(OMule object) {
        if (object.isBaby()) {
            return BABY_MODEL;
        }
        return MuleBreed.breedFromOrdinal(object.getBreed()).resourceLocation;
    }

    @Override
    public ResourceLocation getTextureResource(OMule object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(OMule animatable) {
        return ANIMATION;
    }
}

