package com.dragn0007.dragnlivestock.blocks.client.render.entity.cow;

import com.dragn0007.dragnlivestock.common.entities.cow.OCow;
import com.dragn0007.dragnlivestock.common.entities.util.marking_layer.BovineMarkingOverlay;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class OCowMarkingLayer extends GeoRenderLayer<OCow> {
    public OCowMarkingLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OCow animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        BovineMarkingOverlay overlay = BovineMarkingOverlay.overlayFromOrdinal(animatable.getOverlayVariant());
        RenderType renderMarkingType = RenderType.entityCutout(overlay.resourceLocation);
        poseStack.pushPose();
        poseStack.scale(1.0F, 1.0F, 1.0F);
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

}
