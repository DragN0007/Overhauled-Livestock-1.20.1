package com.dragn0007.dragnlivestock.entities.pig;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OPigRender extends GeoEntityRenderer<OPig> {

    public OPigRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OPigModel());
        this.addRenderLayer(new OPigMarkingLayer(this));
        this.addRenderLayer(new OPigTuskLayer(this));
    }

    @Override
    public void render(OPig entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if(entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
            model.getBone("tusks").ifPresent(b -> b.setHidden(true));
        } else {
            poseStack.scale(1F, 1F, 1F);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}


