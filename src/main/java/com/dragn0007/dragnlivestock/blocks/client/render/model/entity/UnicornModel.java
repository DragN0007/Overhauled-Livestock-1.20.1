package com.dragn0007.dragnlivestock.blocks.client.render.model.entity;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.unicorn.Unicorn;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class UnicornModel extends DefaultedEntityGeoModel<Unicorn> {

    public UnicornModel() {
        super(LivestockOverhaul.id("unicorn"), true);
    }

    @Override
    public void setCustomAnimations(Unicorn animatable, long instanceId, AnimationState<Unicorn> animationState) {

        CoreGeoBone neck = getAnimationProcessor().getBone("neck");

        if (neck != null && animatable.onGround()) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            neck.setRotY(neck.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }
    }

    public enum Variant {
        BAY(LivestockOverhaul.id("textures/entity/unicorn/bay.png")),
        BAY_ROAN(LivestockOverhaul.id("textures/entity/unicorn/bay_roan.png")),
        BLACK(LivestockOverhaul.id("textures/entity/unicorn/black.png")),
        BLOOD_BAY(LivestockOverhaul.id("textures/entity/unicorn/blood_bay.png")),
        BLUE(LivestockOverhaul.id("textures/entity/unicorn/blue.png")),
        BLUE_ROAN(LivestockOverhaul.id("textures/entity/unicorn/blue_roan.png")),
        BROWN(LivestockOverhaul.id("textures/entity/unicorn/brown.png")),
        BUCKSKIN(LivestockOverhaul.id("textures/entity/unicorn/buckskin.png")),
        CHAMPAGNE(LivestockOverhaul.id("textures/entity/unicorn/champagne.png")),
        CHOCOLATE_ROAN(LivestockOverhaul.id("textures/entity/unicorn/chocolate_roan.png")),
        CHESTNUT(LivestockOverhaul.id("textures/entity/unicorn/chestnut.png")),
        CREAMY(LivestockOverhaul.id("textures/entity/unicorn/creamy.png")),
        DARK_BAY(LivestockOverhaul.id("textures/entity/unicorn/dark_bay.png")),
        DARK_BROWN(LivestockOverhaul.id("textures/entity/unicorn/dark_brown.png")),
        FJORD(LivestockOverhaul.id("textures/entity/unicorn/fjord.png")),
        GREY(LivestockOverhaul.id("textures/entity/unicorn/grey.png")),
        IVORY(LivestockOverhaul.id("textures/entity/unicorn/ivory.png")),
        LIVER_CHESTNUT(LivestockOverhaul.id("textures/entity/unicorn/liver_chestnut.png")),
        PALAMINO(LivestockOverhaul.id("textures/entity/unicorn/palamino.png")),
        PALAMINO_ORANGE(LivestockOverhaul.id("textures/entity/unicorn/palamino_orange.png")),
        SEAL_BAY(LivestockOverhaul.id("textures/entity/unicorn/seal_bay.png")),
        STRAWBERRY(LivestockOverhaul.id("textures/entity/unicorn/strawberry.png")),
        WARM_BLACK(LivestockOverhaul.id("textures/entity/unicorn/warm_black.png")),
        WARM_GREY(LivestockOverhaul.id("textures/entity/unicorn/warm_grey.png")),
        WHITE(LivestockOverhaul.id("textures/entity/unicorn/white.png")),
        CREAM(LivestockOverhaul.id("textures/entity/unicorn/cream.png")),
        RED_DUN(LivestockOverhaul.id("textures/entity/unicorn/red_dun.png")),
        BAY_DUN(LivestockOverhaul.id("textures/entity/unicorn/bay_dun.png")),
        GRULLA(LivestockOverhaul.id("textures/entity/unicorn/grulla.png")),
        BLUE_DUN(LivestockOverhaul.id("textures/entity/unicorn/blue_dun.png")),
        CINNAMON(LivestockOverhaul.id("textures/entity/unicorn/cinnamon.png")),
        END(LivestockOverhaul.id("textures/entity/unicorn/end.png")),
        STRAWBERRY_ROAN(LivestockOverhaul.id("textures/entity/unicorn/strawberry_roan.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static UnicornModel.Variant variantFromOrdinal(int variant) { return UnicornModel.Variant.values()[variant % OHorseModel.Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = LivestockOverhaul.id("geo/unicorn.geo.json");
    public static final ResourceLocation ANIMATION = LivestockOverhaul.id("animations/o_horse.animation.json");
    public static final ResourceLocation BABY = LivestockOverhaul.id("geo/horse/baby_o_horse.geo.json");
    @Override
    public ResourceLocation getModelResource(Unicorn object) {
        if (object.isBaby()) {
            return BABY;
        }
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Unicorn object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(Unicorn animatable) {
        return ANIMATION;
    }
}

