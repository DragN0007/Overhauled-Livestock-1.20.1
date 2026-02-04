package com.dragn0007.dragnlivestock.entities.rabbit;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ORabbitRender extends GeoEntityRenderer<ORabbit> {

    public ORabbitRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ORabbitModel());
        this.addRenderLayer(new ORabbitMarkingLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, ORabbit animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        if(animatable.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
            if (animatable.getBreed() == 9) {
                model.getBone("right_antler").ifPresent(b -> b.setHidden(true));
                model.getBone("left_antler").ifPresent(b -> b.setHidden(true));
            }
        } else {
            poseStack.scale(1F, 1F, 1F);

            if (!animatable.isBaby() && animatable.isSheared() && animatable.getBreed() == 4) {
                model.getBone("wool").ifPresent(b -> b.setHidden(true));
            } else {
                model.getBone("wool").ifPresent(b -> b.setHidden(false));
            }

            if (animatable.getBreed() == 9) {
                model.getBone("right_antler").ifPresent(b -> b.setHidden(false));
                model.getBone("left_antler").ifPresent(b -> b.setHidden(false));
                if (animatable.isFemale()) {
                    model.getBone("right_antler").ifPresent(b -> b.setScaleX(0.8F));
                    model.getBone("right_antler").ifPresent(b -> b.setScaleY(0.8F));
                    model.getBone("right_antler").ifPresent(b -> b.setScaleZ(0.8F));
                    model.getBone("left_antler").ifPresent(b -> b.setScaleX(0.8F));
                    model.getBone("left_antler").ifPresent(b -> b.setScaleY(0.8F));
                    model.getBone("left_antler").ifPresent(b -> b.setScaleZ(0.8F));
                } else if (animatable.isMale()) {
                    model.getBone("right_antler").ifPresent(b -> b.setScaleX(1.0F));
                    model.getBone("right_antler").ifPresent(b -> b.setScaleY(1.0F));
                    model.getBone("right_antler").ifPresent(b -> b.setScaleZ(1.0F));
                    model.getBone("left_antler").ifPresent(b -> b.setScaleX(1.0F));
                    model.getBone("left_antler").ifPresent(b -> b.setScaleY(1.0F));
                    model.getBone("left_antler").ifPresent(b -> b.setScaleZ(1.0F));
                }
            }

            if (animatable.getDewlap() == 0) {
                model.getBone("dewlap").ifPresent(b -> b.setHidden(true));
            } else if (animatable.getDewlap() == 1) {
                model.getBone("dewlap").ifPresent(b -> b.setScaleX(0.8F));
                model.getBone("dewlap").ifPresent(b -> b.setScaleY(0.8F));
                model.getBone("dewlap").ifPresent(b -> b.setScaleZ(0.8F));
                model.getBone("dewlap").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("dewlap").ifPresent(b -> b.setScaleX(1.0F));
                model.getBone("dewlap").ifPresent(b -> b.setScaleY(1.0F));
                model.getBone("dewlap").ifPresent(b -> b.setScaleZ(1.0F));
                model.getBone("dewlap").ifPresent(b -> b.setHidden(false));
            }
        }

        super.preRender(poseStack, this.animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

}


