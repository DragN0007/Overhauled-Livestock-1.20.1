package com.dragn0007.dragnlivestock.blocks.client.render.entity;

import com.dragn0007.dragnlivestock.common.entities.OSalmon;
import com.dragn0007.dragnlivestock.blocks.client.render.model.entity.OSalmonModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OSalmonRenderer extends GeoEntityRenderer<OSalmon> {

    public OSalmonRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OSalmonModel());
    }

    @Override
    public void render(OSalmon entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if(entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}


