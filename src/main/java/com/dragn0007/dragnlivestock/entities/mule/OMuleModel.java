package com.dragn0007.dragnlivestock.entities.mule;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OMuleModel extends DefaultedEntityGeoModel<OMule> {

    public OMuleModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o_mule"), true);
    }

    @Override
    public void setCustomAnimations(OMule animatable, long instanceId, AnimationState<OMule> animationState) {

        CoreGeoBone neck = getAnimationProcessor().getBone("neck");

        if (neck != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            neck.setRotY(neck.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }
    }

    public enum Variant {
        RUST(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/mule/rust.png")),
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/mule/black.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/mule/blue.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/mule/brown.png")),
        CHESTNUT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/mule/chestnut.png")),
        CINNAMON(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/mule/cinnamon.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/mule/cream.png")),
        CREAMY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/mule/creamy.png")),
        DARK_BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/mule/dark_brown.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/mule/grey.png")),
        LIVER_CHESTNUT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/mule/liver_chestnut.png")),
        PALAMINO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/mule/palamino.png")),
        STRAWBERRY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/mule/strawberry.png")),
        WARM_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/mule/warm_black.png")),
        WARM_GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/mule/warm_grey.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/mule/white.png")),
        ;

        //Add new entries to bottom when mod is public, else mules will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/o_mule.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_horse.animation.json");

    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/baby_donkey.geo.json");

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

