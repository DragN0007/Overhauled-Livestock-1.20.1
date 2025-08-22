package com.dragn0007.dragnlivestock.blocks.client.render.entity.frog;

import com.dragn0007.dragnlivestock.blocks.client.render.model.entity.GrubModel;
import com.dragn0007.dragnlivestock.common.entities.frog.Grub;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GrubRenderer extends GeoEntityRenderer<Grub> {

    public GrubRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GrubModel());
        this.addRenderLayer(new GrubSweaterLayer(this));
    }

    @Override
    public void render(Grub entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if(entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}


