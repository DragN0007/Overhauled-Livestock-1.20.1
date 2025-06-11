package com.dragn0007.dragnlivestock.entities.rabbit;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ORabbitRender extends GeoEntityRenderer<ORabbit> {

    public ORabbitRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ORabbitModel());
        this.addRenderLayer(new ORabbitMarkingLayer(this));
    }

    @Override
    public void render(ORabbit entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if(entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        } else {
            poseStack.scale(1F, 1F, 1F);

            if (entity.getDewlap() == 0) {
                model.getBone("dewlap").ifPresent(b -> b.setHidden(true));
            } else {
                model.getBone("dewlap").ifPresent(b -> b.setHidden(false));
            }

            if (entity.getDewlap() == 1) {
                model.getBone("dewlap").ifPresent(b -> b.setScaleX(0.8F));
                model.getBone("dewlap").ifPresent(b -> b.setScaleY(0.8F));
                model.getBone("dewlap").ifPresent(b -> b.setScaleZ(0.8F));
            } else {
                model.getBone("dewlap").ifPresent(b -> b.setScaleX(1.0F));
                model.getBone("dewlap").ifPresent(b -> b.setScaleY(1.0F));
                model.getBone("dewlap").ifPresent(b -> b.setScaleZ(1.0F));
            }
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}


