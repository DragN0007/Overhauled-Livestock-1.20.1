package com.dragn0007.dragnlivestock.entities.sheep;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OSheepRender extends GeoEntityRenderer<OSheep> {

    public OSheepRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OSheepModel());
        this.addRenderLayer(new OSheepHornLayer(this));
    }

    @Override
    public void render(OSheep entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if(entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }

        if (!entity.isBaby() && entity.isSheared()) {
                model.getBone("Wool1").ifPresent(b -> b.setHidden(true));
                model.getBone("Wool2").ifPresent(b -> b.setHidden(true));
            } else {
                model.getBone("Wool1").ifPresent(b -> b.setHidden(false));
                model.getBone("Wool2").ifPresent(b -> b.setHidden(false));
            }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}


