package com.dragn0007.dragnlivestock.blocks.client.render.model.entity;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.bee.OBee;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OBeeModel extends GeoModel<OBee> {

    public enum Variant {
        BEE(LivestockOverhaul.id("textures/entity/bee/bumble_bee.png")),
        ASHY_MINING_BEE(LivestockOverhaul.id("textures/entity/bee/ashy_mining_bee.png")),
        GARDEN_BEE(LivestockOverhaul.id("textures/entity/bee/garden_bumble_bee.png")),
        HONEY_BEE(LivestockOverhaul.id("textures/entity/bee/honey_bee.png")),
        RED_MASON_BEE(LivestockOverhaul.id("textures/entity/bee/red_mason_bee.png")),
        RED_TAILED_BEE(LivestockOverhaul.id("textures/entity/bee/red_tailed_bumble_bee.png")),
        TAWNY_MINING_BEE(LivestockOverhaul.id("textures/entity/bee/tawny_mining_bee.png")),
        TREE_BEE(LivestockOverhaul.id("textures/entity/bee/tree_bumble_bee.png")),
        ;

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) {
            return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = LivestockOverhaul.id("geo/overhauled_bee.geo.json");
    public static final ResourceLocation ANIMATION = LivestockOverhaul.id("animations/o_bee.animation.json");

    @Override
    public ResourceLocation getModelResource(OBee oBee) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(OBee oBee) {
        return oBee.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(OBee oBee) {
        return ANIMATION;
    }
}

