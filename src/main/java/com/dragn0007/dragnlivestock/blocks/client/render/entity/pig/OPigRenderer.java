package com.dragn0007.dragnlivestock.blocks.client.render.entity.pig;

import com.dragn0007.dragnlivestock.common.entities.pig.OPig;
import com.dragn0007.dragnlivestock.blocks.client.render.model.entity.OPigModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OPigRenderer extends GeoEntityRenderer<OPig> {

    public OPigRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OPigModel());
        this.addRenderLayer(new OPigMarkingLayer(this));
        this.addRenderLayer(new OPigBrandTagLayer(this));
    }

    @Override
    public void render(OPig entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if(entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
            model.getBone("tusks").ifPresent(b -> b.setHidden(true));
        } else {
            if (entity.isMale()) {
                model.getBone("tusks").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("tusks").ifPresent(b -> b.setHidden(true));
            }

            if (entity.getBreed() == 0) {
                poseStack.scale(1.1F, 1.1F, 1.1F);
            } else if (entity.getBreed() == 1) {
                poseStack.scale(0.9F, 0.9F, 0.9F);
                model.getBone("belly").ifPresent(b -> b.setScaleY(2F));
            } else if (entity.getBreed() == 2) {
                poseStack.scale(0.9F, 0.9F, 0.9F);
            } else if (entity.getBreed() == 3) {
                poseStack.scale(1.0F, 1.0F, 1.0F);
            } else if (entity.getBreed() == 4) {
                poseStack.scale(1.2F, 1.2F, 1.2F);
            } else if (entity.getBreed() == 5) {
                poseStack.scale(1.0F, 1.0F, 1.0F);
            }
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}


