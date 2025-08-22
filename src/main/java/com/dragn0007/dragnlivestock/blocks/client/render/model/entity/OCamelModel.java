package com.dragn0007.dragnlivestock.blocks.client.render.model.entity;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.camel.CamelBreed;
import com.dragn0007.dragnlivestock.common.entities.camel.OCamel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OCamelModel extends DefaultedEntityGeoModel<OCamel> {

    public OCamelModel() {
        super(LivestockOverhaul.id("o_camel"), true);
    }

    @Override
    public void setCustomAnimations(OCamel animatable, long instanceId, AnimationState<OCamel> animationState) {

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        CoreGeoBone neck = getAnimationProcessor().getBone("neck");

        if (neck != null && animatable.onGround()) {
            neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            neck.setRotY(neck.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }

    }

    public enum Variant {
        ASH(LivestockOverhaul.id("textures/entity/camel/ash.png")),
        BLACK(LivestockOverhaul.id("textures/entity/camel/black.png")),
        CHESTNUT(LivestockOverhaul.id("textures/entity/camel/chestnut.png")),
        CHOCOLATE(LivestockOverhaul.id("textures/entity/camel/chocolate.png")),
        CREAM(LivestockOverhaul.id("textures/entity/camel/cream.png")),
        DESERT(LivestockOverhaul.id("textures/entity/camel/desert.png")),
        GREY(LivestockOverhaul.id("textures/entity/camel/grey.png")),
        LIGHT_GREY(LivestockOverhaul.id("textures/entity/camel/light_grey.png")),
        LIVER_CHESTNUT(LivestockOverhaul.id("textures/entity/camel/liver_chestnut.png")),
        MAHOGANY(LivestockOverhaul.id("textures/entity/camel/mahogany.png")),
        SANDY(LivestockOverhaul.id("textures/entity/camel/sandy.png")),
        TAN(LivestockOverhaul.id("textures/entity/camel/tan.png")),
        WHITE(LivestockOverhaul.id("textures/entity/camel/white.png"));
        //Add new entries to bottom when mod is public, else camels will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation ANIMATION = LivestockOverhaul.id("animations/o_camel.animation.json");
    @Override
    public ResourceLocation getModelResource(OCamel object) {
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

