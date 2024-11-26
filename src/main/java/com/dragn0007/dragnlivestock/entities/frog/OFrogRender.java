package com.dragn0007.dragnlivestock.entities.frog;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OFrogRender extends GeoEntityRenderer<OFrog> {

    public OFrogRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OFrogModel());
        this.addRenderLayer(new OFrogMarkingLayer(this));
        this.addRenderLayer(new OFrogEyeLayer(this));
    }

    @Override
    public void render(OFrog entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}


