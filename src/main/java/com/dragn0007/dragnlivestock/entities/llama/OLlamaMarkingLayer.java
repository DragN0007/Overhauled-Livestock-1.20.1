package com.dragn0007.dragnlivestock.entities.llama;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class OLlamaMarkingLayer extends GeoRenderLayer<OLlama> {
    public OLlamaMarkingLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OLlama animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        Player player = Minecraft.getInstance().player;
        double distanceSq = animatable.distanceToSqr(player);
        boolean atCullDistance = distanceSq > LivestockOverhaulClientConfig.CULL_LAYERS_DISTANCE.get();
        if (atCullDistance) return;
        if (!animatable.isBaby() && animatable.getOverlayVariant() != 0) {
            RenderType renderMarkingType = RenderType.entityCutout(animatable.getOverlayLocation());
            getRenderer().reRender(getDefaultBakedModel(animatable),
                    poseStack,
                    bufferSource,
                    animatable,
                    renderMarkingType,
                    bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);
        }
    }

    public enum Overlay {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/none.png")),
        BLACK_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/black_bald.png")),
        BLACK_HALF(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/black_half.png")),
        BLACK_NECK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/black_neck.png")),
        BLACK_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/black_socks.png")),
        BLACK_TAILED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/black_tailed.png")),
        BLUE_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/blue_bald.png")),
        BLUE_HALF(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/blue_half.png")),
        BLUE_NECK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/blue_neck.png")),
        BLUE_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/blue_socks.png")),
        BLUE_TAILED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/blue_tailed.png")),
        BROWN_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/brown_bald.png")),
        BROWN_HALF(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/brown_half.png")),
        BROWN_NECK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/brown_neck.png")),
        BROWN_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/brown_socks.png")),
        BROWN_TAILED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/brown_tailed.png")),
        CHOCOLATE_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/chocolate_bald.png")),
        CHOCOLATE_HALF(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/chocolate_half.png")),
        CHOCOLATE_NECK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/chocolate_neck.png")),
        CHOCOLATE_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/chocolate_socks.png")),
        CHOCOLATE_TAILED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/chocolate_tailed.png")),
        CREAM_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/cream_bald.png")),
        CREAM_HALF(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/cream_half.png")),
        CREAM_NECK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/cream_neck.png")),
        CREAM_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/cream_socks.png")),
        CREAM_TAILED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/cream_tailed.png")),
        MAHOGANY_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/mahogany_bald.png")),
        MAHOGANY_HALF(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/mahogany_half.png")),
        MAHOGANY_NECK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/mahogany_neck.png")),
        MAHOGANY_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/mahogany_socks.png")),
        MAHOGANY_TAILED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/mahogany_tailed.png")),
        RED_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/red_bald.png")),
        RED_HALF(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/red_half.png")),
        RED_NECK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/red_neck.png")),
        RED_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/red_socks.png")),
        RED_TAILED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/red_tailed.png")),
        SEAL_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/seal_bald.png")),
        SEAL_HALF(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/seal_half.png")),
        SEAL_NECK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/seal_neck.png")),
        SEAL_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/seal_socks.png")),
        SEAL_TAILED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/seal_tailed.png")),
        SILVER_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/silver_bald.png")),
        SILVER_HALF(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/silver_half.png")),
        SILVER_NECK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/silver_neck.png")),
        SILVER_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/silver_socks.png")),
        SILVER_TAILED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/silver_tailed.png")),
        TAN_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/tan_bald.png")),
        TAN_HALF(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/tan_half.png")),
        TAN_NECK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/tan_neck.png")),
        TAN_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/tan_socks.png")),
        TAN_TAILED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/tan_tailed.png")),
        WHITE_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/white_bald.png")),
        WHITE_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/white_black.png")),
        WHITE_NECK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/white_neck.png")),
        WHITE_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/white_socks.png")),
        WHITE_TAILED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/white_tailed.png")),
        FEW_SPOT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/few_spot.png")),
        LARGE_SPOT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/large_spot.png"));
        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {this.resourceLocation = resourceLocation;}
        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];}
    }

}
