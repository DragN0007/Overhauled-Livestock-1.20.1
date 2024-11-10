package com.dragn0007.dragnlivestock.entities.cow;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OCowRender extends GeoEntityRenderer<OCow> {

    public OCowRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OCowModel());
        this.addRenderLayer(new OCowMarkingLayer(this));
        this.addRenderLayer(new OCowHornLayer(this));
        this.addRenderLayer(new OCowUdderLayer(this));
    }

    @Override
    public void render(OCow entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

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


