package com.dragn0007.dragnlivestock.entities.cow.moobloom.flowering;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class FloweringMoobloomModel extends DefaultedEntityGeoModel<FloweringMoobloom> {

    public FloweringMoobloomModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "flowering_moobloom"), true);
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
        ALLIUM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/allium.png")),
        AZURE_BLUET(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/azure_bluet.png")),
        BLUE_ORCHID(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/blue_orchid.png")),
        CORNFLOWER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/cornflower.png")),
        DANDELION(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/dandelion.png")),
        LILY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/lily_of_the_valley.png")),
        OXEYE_DAISY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/oxeye_daisy.png")),
        POPPY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/poppy.png")),
        TULIP(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/tulip.png")),
        LILAC(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/lilac.png")),
        PEONY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/peony.png")),
        ROSE_BUSH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/rose_bush.png")),
        SUNFLOWER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/sunflower.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/moobloom.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_cow.animation.json");

    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/baby_o_cow.geo.json");
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

