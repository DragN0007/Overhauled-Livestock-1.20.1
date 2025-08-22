package com.dragn0007.dragnlivestock.blocks.client.render.entity.llama;

import com.dragn0007.dragnlivestock.common.entities.llama.OLlama;
import com.dragn0007.dragnlivestock.blocks.client.render.model.entity.OLlamaModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OLlamaRenderer extends GeoEntityRenderer<OLlama> {

    public OLlamaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OLlamaModel());
        this.addRenderLayer(new OLlamaMarkingLayer(this));
        this.addRenderLayer(new OLlamaCarpetLayer(this));
        this.addRenderLayer(new OLlamaChestLayer(this));
    }

    @Override
    public void render(OLlama entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (entity.hasChest()) {
            model.getBone("saddlebags").ifPresent(b -> b.setHidden(false));
        } else {
            model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
        }

        if(entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }

        if(entity.getWooly() == 0 || entity.isSheared()) {
            model.getBone("wool_body").ifPresent(b -> b.setHidden(true));
        } else {
            model.getBone("wool_body").ifPresent(b -> b.setHidden(false));
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}


