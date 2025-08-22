package com.dragn0007.dragnlivestock.blocks.client.render.model.entity;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.cow.FloweringMoobloom;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class FloweringMoobloomModel extends DefaultedEntityGeoModel<FloweringMoobloom> {

    public FloweringMoobloomModel() {
        super(LivestockOverhaul.id("flowering_moobloom"), true);
    }

    @Override
    public void setCustomAnimations(FloweringMoobloom animatable, long instanceId, AnimationState<FloweringMoobloom> animationState) {

        CoreGeoBone neck = getAnimationProcessor().getBone("neck");
        CoreGeoBone head = getAnimationProcessor().getBone("head");
        CoreGeoBone left_ear = getAnimationProcessor().getBone("left_ear");
        CoreGeoBone right_ear = getAnimationProcessor().getBone("right_ear");
        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        if (neck != null) {
            neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            neck.setRotY(neck.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }

        if (head != null) {
            head.setRotX(head.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            head.setRotY(head.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }

        
    }

    public enum Variant {
        ALLIUM(LivestockOverhaul.id("textures/entity/moobloom/allium.png")),
        AZURE_BLUET(LivestockOverhaul.id("textures/entity/moobloom/azure_bluet.png")),
        BLUE_ORCHID(LivestockOverhaul.id("textures/entity/moobloom/blue_orchid.png")),
        CORNFLOWER(LivestockOverhaul.id("textures/entity/moobloom/cornflower.png")),
        DANDELION(LivestockOverhaul.id("textures/entity/moobloom/dandelion.png")),
        LILY(LivestockOverhaul.id("textures/entity/moobloom/lily_of_the_valley.png")),
        OXEYE_DAISY(LivestockOverhaul.id("textures/entity/moobloom/oxeye_daisy.png")),
        POPPY(LivestockOverhaul.id("textures/entity/moobloom/poppy.png")),
        TULIP(LivestockOverhaul.id("textures/entity/moobloom/tulip.png")),
        LILAC(LivestockOverhaul.id("textures/entity/moobloom/lilac.png")),
        PEONY(LivestockOverhaul.id("textures/entity/moobloom/peony.png")),
        ROSE_BUSH(LivestockOverhaul.id("textures/entity/moobloom/rose_bush.png")),
        SUNFLOWER(LivestockOverhaul.id("textures/entity/moobloom/sunflower.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = LivestockOverhaul.id("geo/moobloom/moobloom.geo.json");
    public static final ResourceLocation ANIMATION = LivestockOverhaul.id("animations/o_cow.animation.json");

    public static final ResourceLocation BABY_MODEL = LivestockOverhaul.id("geo/baby_o_cow.geo.json");
    @Override
    public ResourceLocation getModelResource(FloweringMoobloom object) {
        if(object.isBaby())
            return BABY_MODEL;
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(FloweringMoobloom object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(FloweringMoobloom animatable) {
        return ANIMATION;
    }
}

