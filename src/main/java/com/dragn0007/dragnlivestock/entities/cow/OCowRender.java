package com.dragn0007.dragnlivestock.entities.cow;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OCowRender extends GeoEntityRenderer<OCow> {

    public OCowRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OCowModel());
        this.addRenderLayer(new OCowMarkingLayer(this));
        this.addRenderLayer(new OCowBrandTagLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, OCow entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        if(entity.isBaby()) {
            model.getBone("utters").ifPresent(b -> b.setHidden(true));
            model.getBone("horns1").ifPresent(b -> b.setHidden(true));
            model.getBone("horns2").ifPresent(b -> b.setHidden(true));
            model.getBone("horns3").ifPresent(b -> b.setHidden(true));
            model.getBone("horns4").ifPresent(b -> b.setHidden(true));
            model.getBone("horns5").ifPresent(b -> b.setHidden(true));
            model.getBone("horns6").ifPresent(b -> b.setHidden(true));
            model.getBone("horns7").ifPresent(b -> b.setHidden(true));
            model.getBone("horns8").ifPresent(b -> b.setHidden(true));
            model.getBone("horns9").ifPresent(b -> b.setHidden(true));
            model.getBone("horns10").ifPresent(b -> b.setHidden(true));
        } else {



        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}


