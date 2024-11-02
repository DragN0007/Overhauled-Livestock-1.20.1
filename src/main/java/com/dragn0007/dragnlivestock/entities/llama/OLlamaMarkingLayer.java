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
        BUTT_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/overlay_butt_black.png")),
        BUTT_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/overlay_butt_white.png")),
        FRONT_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/overlay_front_black.png")),
        FRONT_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/overlay_front_white.png")),
        HALVED_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/overlay_halved_black.png")),
        HALVED_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/overlay_halved_white.png")),
        SPLASH_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/overlay_splash_black.png")),
        SPLASH_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/overlay_splash_white.png")),
        SPOT_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/overlay_spot_black.png")),
        SPOT_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/overlay/overlay_spot_white.png"));

        //Add new entries to bottom when mod is public, else llamas will change textures during update.

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
