package com.dragn0007.dragnlivestock.entities.camel;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OCamelModel extends DefaultedEntityGeoModel<OCamel> {

    public OCamelModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o_camel"), true);
    }

    @Override
    public void setCustomAnimations(OCamel animatable, long instanceId, AnimationState<OCamel> animationState) {

        CoreGeoBone neck = getAnimationProcessor().getBone("neck");

        if (neck != null && animatable.onGround()) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            neck.setRotY(neck.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }
    }

    public enum Variant {
        ASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/ash.png")),
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/black.png")),
        CHESTNUT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/chestnut.png")),
        CHOCOLATE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/chocolate.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/cream.png")),
        DESERT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/desert.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/grey.png")),
        LIGHT_GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/light_grey.png")),
        LIVER_CHESTNUT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/liver_chestnut.png")),
        MAHOGANY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/mahogany.png")),
        SANDY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/sandy.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/tan.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/white.png"));
        //Add new entries to bottom when mod is public, else camels will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_camel.animation.json");

    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/baby_o_camel.geo.json");
    @Override
    public ResourceLocation getModelResource(OCamel object) {
        if(object.isBaby() && object.getBreed() == 0)
            return BABY_MODEL;
        return CamelBreed.Breed.breedFromOrdinal(object.getBreed()).resourceLocation;
    }

    @Override
    public ResourceLocation getTextureResource(OCamel object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(OCamel animatable) {
        return ANIMATION;
    }
}

