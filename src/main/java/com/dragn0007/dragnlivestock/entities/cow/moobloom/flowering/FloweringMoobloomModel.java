package com.dragn0007.dragnlivestock.entities.cow.moobloom.flowering;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FloweringMoobloomModel extends GeoModel<FloweringMoobloom> {

    public enum Variant {
        ALLIUM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/moobloom_allium.png")),
        AZURE_BLUET(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/moobloom_azure_bluet.png")),
        BLUE_ORCHID(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/moobloom_blue_orchid.png")),
        CORNFLOWER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/moobloom_cornflower.png")),
        DANDELION(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/moobloom_dandelion.png")),
        LILY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/moobloom_lily_of_the_valley.png")),
        OXEYE_DAISY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/moobloom_oxeye_daisy.png")),
        POPPY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/moobloom_poppy.png")),
        TULIP(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/moobloom_tulip.png")),
        LILAC(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/moobloom_lilac.png")),
        PEONY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/moobloom_peony.png")),
        ROSE_BUSH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/moobloom_rose_bush.png")),
        SUNFLOWER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/moobloom/moobloom_sunflower.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/flowering_moobloom.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/cow_overhaul.animation.json");

    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/baby_cow_overhaul.geo.json");
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

