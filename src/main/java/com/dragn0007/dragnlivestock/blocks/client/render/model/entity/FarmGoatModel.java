package com.dragn0007.dragnlivestock.blocks.client.render.model.entity;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.FarmGoat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class FarmGoatModel extends DefaultedEntityGeoModel<FarmGoat> {

    public FarmGoatModel() {
        super(LivestockOverhaul.id("o_goat"), true);
    }

    @Override
    public void setCustomAnimations(FarmGoat animatable, long instanceId, AnimationState<FarmGoat> animationState) {

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
        BLACK(LivestockOverhaul.id("textures/entity/goat/black.png")),
        BLUE(LivestockOverhaul.id("textures/entity/goat/blue.png")),
        BROWN(LivestockOverhaul.id("textures/entity/goat/brown.png")),
        CHOCOLATE(LivestockOverhaul.id("textures/entity/goat/chocolate.png")),
        CREAM(LivestockOverhaul.id("textures/entity/goat/cream.png")),
        GREY(LivestockOverhaul.id("textures/entity/goat/grey.png")),
        LIGHT_GREY(LivestockOverhaul.id("textures/entity/goat/light_grey.png")),
        MAHOGANY(LivestockOverhaul.id("textures/entity/goat/mahogany.png")),
        RED(LivestockOverhaul.id("textures/entity/goat/red.png")),
        TAN(LivestockOverhaul.id("textures/entity/goat/tan.png")),
        WHITE(LivestockOverhaul.id("textures/entity/goat/white.png")),
        ;

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }
    public static final ResourceLocation FEMALE = LivestockOverhaul.id("geo/goat/doe.geo.json");
    public static final ResourceLocation MALE = LivestockOverhaul.id("geo/goat/buck.geo.json");
    public static final ResourceLocation MEAT_FEMALE = LivestockOverhaul.id("geo/goat/meat_doe.geo.json");
    public static final ResourceLocation MEAT_MALE = LivestockOverhaul.id("geo/goat/meat_buck.geo.json");
    public static final ResourceLocation NUBIAN_FEMALE = LivestockOverhaul.id("geo/goat/nubian_doe.geo.json");
    public static final ResourceLocation NUBIAN_MALE = LivestockOverhaul.id("geo/goat/nubian_buck.geo.json");
    public static final ResourceLocation WARM_FEMALE = LivestockOverhaul.id("geo/goat/warm_doe.geo.json");
    public static final ResourceLocation WARM_MALE = LivestockOverhaul.id("geo/goat/warm_buck.geo.json");

    public static final ResourceLocation ANIMATION = LivestockOverhaul.id("animations/o_goat.animation.json");

    @Override
    public ResourceLocation getModelResource(FarmGoat object) {
        return FEMALE;
    }

    public ResourceLocation getTextureResource(FarmGoat object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(FarmGoat animatable) {
        return ANIMATION;
    }
}

