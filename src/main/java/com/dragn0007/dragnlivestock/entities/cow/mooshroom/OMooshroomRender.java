package com.dragn0007.dragnlivestock.entities.cow.mooshroom;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OMooshroomRender extends GeoEntityRenderer<OMooshroom> {

    public OMooshroomRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OMooshroomModel());
        this.addRenderLayer(new OMooshroomHornLayer(this));
        this.addRenderLayer(new OMooshroomMarkingLayer(this));
        this.addRenderLayer(new OMooshroomMushroomLayer(this));
        this.addRenderLayer(new OMooshroomUdderLayer(this));
    }

    @Override
    public void render(OMooshroom entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if (entity.isChested()) {
            model.getBone("saddlebags").ifPresent(b -> b.setHidden(false));
            model.getBone("halter").ifPresent(b -> b.setHidden(false));
        } else {
            model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
            model.getBone("halter").ifPresent(b -> b.setHidden(true));
        }

        if(entity.isBaby()) {
            model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
            model.getBone("halter").ifPresent(b -> b.setHidden(true));
            model.getBone("utters").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns1").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns2").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns3").ifPresent(b -> b.setHidden(true));
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}


