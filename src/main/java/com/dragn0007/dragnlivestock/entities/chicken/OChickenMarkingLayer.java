package com.dragn0007.dragnlivestock.entities.chicken;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class OChickenMarkingLayer extends GeoRenderLayer<OChicken> {
    public OChickenMarkingLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OChicken animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType renderMarkingType = RenderType.entityCutout(((OChicken)animatable).getOverlayLocation());
        poseStack.pushPose();
        poseStack.scale(1.0f, 1.0f, 1.0f);
        poseStack.translate(0.0d, 0.0d, 0.0d);
        poseStack.popPose();
        getRenderer().reRender(getDefaultBakedModel(animatable),
                poseStack,
                bufferSource,
                animatable,
                renderMarkingType,
                bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                1, 1, 1, 1);
    }

    public enum Overlay {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/none.png")),
        BLACK_HEAD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/black_head.png")),
        BLACK_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/black_speckled.png")),
        BLACK_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/black_striped.png")),
        BLACK_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/black_tipped.png")),
        BLUE_HEAD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/blue_head.png")),
        BLUE_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/blue_speckled.png")),
        BLUE_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/blue_striped.png")),
        BLUE_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/blue_tipped.png")),
        BROWN_HEAD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/brown_head.png")),
        BROWN_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/brown_speckled.png")),
        BROWN_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/brown_striped.png")),
        BROWN_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/brown_tipped.png")),
        CHOCOLATE_HEAD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/chocolate_head.png")),
        CHOCOLATE_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/chocolate_speckled.png")),
        CHOCOLATE_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/chocolate_striped.png")),
        CHOCOLATE_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/chocolate_tipped.png")),
        CREAM_HEAD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/cream_head.png")),
        CREAM_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/cream_speckled.png")),
        CREAM_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/cream_striped.png")),
        CREAM_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/cream_tipped.png")),
        LILAC_HEAD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/lilac_head.png")),
        LILAC_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/lilac_speckled.png")),
        LILAC_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/lilac_striped.png")),
        LILAC_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/lilac_tipped.png")),
        MAHOGANY_HEAD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/mahogany_head.png")),
        MAHOGANY_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/mahogany_speckled.png")),
        MAHOGANY_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/mahogany_striped.png")),
        MAHOGANY_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/mahogany_tipped.png")),
        RED_HEAD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/red_head.png")),
        RED_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/red_speckled.png")),
        RED_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/red_striped.png")),
        RED_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/red_tipped.png")),
        SILVER_HEAD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/silver_head.png")),
        SILVER_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/silver_speckled.png")),
        SILVER_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/silver_striped.png")),
        SILVER_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/silver_tipped.png")),
        TAN_HEAD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/tan_head.png")),
        TAN_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/tan_speckled.png")),
        TAN_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/tan_striped.png")),
        TAN_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/tan_tipped.png")),
        WHITE_HEAD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/white_head.png")),
        WHITE_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/white_speckled.png")),
        WHITE_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/white_striped.png")),
        WHITE_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/white_tipped.png")),
        HOODED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/hooded.png")),
        NECKLACE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/necklace.png")),
        PURE_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/overlay/pure_white.png")),
        ;

        //Add new entries to bottom when mod is public, else chickens will change textures during update.

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
