package com.dragn0007.dragnlivestock.entities.llama;

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

public class OLlamaMarkingLayer extends GeoRenderLayer<OLlama> {
    public OLlamaMarkingLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OLlama animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType renderMarkingType = RenderType.entityCutout(((OLlama)animatable).getOverlayLocation());
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
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/none.png")),
        BLACK_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/black_bald.png")),
        BLACK_NECK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/black_neck.png")),
        BLACK_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/black_socks.png")),
        BLACK_TAILED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/black_tailed.png")),
        BLUE_NECK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/blue_neck.png")),
        FEW_SPOT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/few_spot.png")),
        HALF_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/half_black.png")),
        LARGE_SPOT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/large_spot.png")),
        RED_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/red_bald.png")),
        RED_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/red_black.png")),
        SANDY_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/sandy_bald.png")),
        SANDY_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/sandy_socks.png")),
        SANDY_TAILED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/sandy_tailed.png")),
        TAN_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/tan_black.png")),
        WHITE_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/white_bald.png")),
        WHITE_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/white_black.png")),
        WHITE_NECK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/white_neck.png")),
        WHITE_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/white_socks.png")),
        WHITE_TAILED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/white_tailed.png")),
        ;

        //Add new entries to bottom when mod is public, else llamas will change textures during update.

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
