package com.dragn0007.dragnlivestock.entities.chicken;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OChickenRender extends GeoEntityRenderer<OChicken> {

    public OChickenRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OChickenModel());
        this.addRenderLayer(new OChickenMarkingLayer(this));
    }

    @Override
    public void render(OChicken entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}


