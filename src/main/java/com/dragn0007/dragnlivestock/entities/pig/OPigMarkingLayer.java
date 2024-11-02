package com.dragn0007.dragnlivestock.entities.pig;

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

public class OPigMarkingLayer extends GeoRenderLayer<OPig> {
    public OPigMarkingLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OPig animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType renderMarkingType = RenderType.entityCutout(((OPig)animatable).getOverlayLocation());
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
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/none.png")),
        PINK_STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/overlay_pink_stripe.png")),
        SPOTTED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/overlay_spotted.png")),
        STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/overlay_stripe.png")),
        STRIPE_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/overlay_stripe_black.png"));

        //Add new entries to bottom when mod is public, else pigs will change textures during update.

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
