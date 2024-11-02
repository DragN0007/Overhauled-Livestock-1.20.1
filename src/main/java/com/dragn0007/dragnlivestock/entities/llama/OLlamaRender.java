package com.dragn0007.dragnlivestock.entities.llama;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OLlamaRender extends GeoEntityRenderer<OLlama> {

    public OLlamaRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OLlamaModel());
        this.addRenderLayer(new OLlamaMarkingLayer(this));
        this.addRenderLayer(new OLlamaCarpetLayer(this));
    }

    @Override
    public void render(OLlama entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (animatable.hasChest()) {
            model.getBone("saddlebags").ifPresent(b -> b.setHidden(false));
            model.getBone("halter").ifPresent(b -> b.setHidden(false));
        } else {
            model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
            model.getBone("halter").ifPresent(b -> b.setHidden(true));
        }

        if(animatable.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}


