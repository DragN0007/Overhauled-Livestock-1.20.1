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
        this.addRenderLayer(new OCowHornLayer(this));
        this.addRenderLayer(new OCowBrandTagLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, OCow entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        if(entity.isBaby()) {
            model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
            model.getBone("halter").ifPresent(b -> b.setHidden(true));
            model.getBone("utters").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns1").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns2").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns3").ifPresent(b -> b.setHidden(true));
        } else {
            model.getBone("saddlebags").ifPresent(b -> b.setHidden(false));
            model.getBone("halter").ifPresent(b -> b.setHidden(false));
            model.getBone("utters").ifPresent(b -> b.setHidden(false));
            model.getBone("Horns1").ifPresent(b -> b.setHidden(false));
            model.getBone("Horns2").ifPresent(b -> b.setHidden(false));
            model.getBone("Horns3").ifPresent(b -> b.setHidden(false));
        }

        if(entity.isMale() || entity.isBaby()) {
            model.getBone("utters").ifPresent(b -> b.setHidden(true));
        } else {
            model.getBone("utters").ifPresent(b -> b.setHidden(false));
        }

        if (entity.getBreed() == 0) {
            model.getBone("fluff").ifPresent(b -> b.setHidden(false));
            model.getBone("fluff2").ifPresent(b -> b.setHidden(false));
            model.getBone("fluff3").ifPresent(b -> b.setHidden(false));
            model.getBone("hump").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns1").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns2").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns3").ifPresent(b -> b.setHidden(true));
        }

        if (entity.getBreed() == 1) {
            model.getBone("fluff").ifPresent(b -> b.setHidden(true));
            model.getBone("fluff2").ifPresent(b -> b.setHidden(true));
            model.getBone("fluff3").ifPresent(b -> b.setHidden(true));
            model.getBone("hump").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns1").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns2").ifPresent(b -> b.setHidden(false));
            model.getBone("Horns3").ifPresent(b -> b.setHidden(false));
        }

        if (entity.getBreed() == 2) {
            model.getBone("fluff").ifPresent(b -> b.setHidden(true));
            model.getBone("fluff2").ifPresent(b -> b.setHidden(true));
            model.getBone("fluff3").ifPresent(b -> b.setHidden(true));
            model.getBone("hump").ifPresent(b -> b.setHidden(false));
            model.getBone("Horns1").ifPresent(b -> b.setHidden(false));
            model.getBone("Horns2").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns3").ifPresent(b -> b.setHidden(true));
        }

        if (entity.getBreed() == 3) {
            model.getBone("fluff").ifPresent(b -> b.setHidden(false));
            model.getBone("fluff2").ifPresent(b -> b.setHidden(false));
            model.getBone("fluff3").ifPresent(b -> b.setHidden(false));
            model.getBone("hump").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns1").ifPresent(b -> b.setHidden(false));
            model.getBone("Horns2").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns3").ifPresent(b -> b.setHidden(true));
        }

        if (entity.getBreed() == 4) {
            model.getBone("fluff").ifPresent(b -> b.setHidden(true));
            model.getBone("fluff2").ifPresent(b -> b.setHidden(true));
            model.getBone("fluff3").ifPresent(b -> b.setHidden(true));
            model.getBone("hump").ifPresent(b -> b.setHidden(true));

            if (entity.getHornType() == 0) {
                model.getBone("Horns1").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("Horns1").ifPresent(b -> b.setHidden(true));
            }

            if (entity.getHornType() == 1) {
                model.getBone("Horns2").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("Horns2").ifPresent(b -> b.setHidden(true));
            }

            if (entity.getHornType() == 2) {
                model.getBone("Horns3").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("Horns3").ifPresent(b -> b.setHidden(true));
            }

        }

        if (entity.getBreed() == 5) {
            model.getBone("fluff").ifPresent(b -> b.setHidden(true));
            model.getBone("fluff2").ifPresent(b -> b.setHidden(true));
            model.getBone("fluff3").ifPresent(b -> b.setHidden(true));
            model.getBone("hump").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns1").ifPresent(b -> b.setHidden(false));
            model.getBone("Horns2").ifPresent(b -> b.setHidden(false));
            model.getBone("Horns3").ifPresent(b -> b.setHidden(false));
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}


