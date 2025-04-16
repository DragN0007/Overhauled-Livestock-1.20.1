package com.dragn0007.dragnlivestock.entities.sheep;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OSheepRender extends GeoEntityRenderer<OSheep> {

    public OSheepRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OSheepModel());
        this.addRenderLayer(new OSheepBrandTagLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, OSheep entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        if (entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
            model.getBone("Horns1").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns2").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns3").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns4").ifPresent(b -> b.setHidden(true));
        } else {
            model.getBone("Horns1").ifPresent(b -> b.setHidden(false));
            model.getBone("Horns2").ifPresent(b -> b.setHidden(false));
            model.getBone("Horns3").ifPresent(b -> b.setHidden(false));
            model.getBone("Horns4").ifPresent(b -> b.setHidden(false));
        }

        if (!entity.isBaby() && entity.isSheared()) {
            model.getBone("Wool1").ifPresent(b -> b.setHidden(true));
            model.getBone("Wool2").ifPresent(b -> b.setHidden(true));
        } else {
            model.getBone("Wool1").ifPresent(b -> b.setHidden(false));
            model.getBone("Wool2").ifPresent(b -> b.setHidden(false));
        }

        if (entity.getBreed() == 0 && entity.isMale()) {
            poseStack.scale(1F, 1F, 1F);
            model.getBone("Horns1").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns2").ifPresent(b -> b.setHidden(false));
            model.getBone("Horns3").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns4").ifPresent(b -> b.setHidden(true));
        }

        if (entity.getBreed() == 0 && entity.isFemale()) {
            poseStack.scale(1F, 1F, 1F);
            model.getBone("Horns1").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns2").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns3").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns4").ifPresent(b -> b.setHidden(true));
        }

        if (entity.getBreed() == 1) {
            poseStack.scale(0.95F, 0.95F, 0.95F);
            model.getBone("Wool2").ifPresent(b -> b.setScaleY(1.1F));
            model.getBone("Wool2").ifPresent(b -> b.setScaleX(1.1F));
            model.getBone("Wool2").ifPresent(b -> b.setScaleY(1.1F));
            model.getBone("Wool2").ifPresent(b -> b.setScaleX(1.1F));
            model.getBone("Horns1").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns2").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns3").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns4").ifPresent(b -> b.setHidden(false));
        }

        if (entity.getBreed() == 2) {
            poseStack.scale(0.95F, 0.95F, 0.95F);
            model.getBone("Horns1").ifPresent(b -> b.setHidden(false));
            model.getBone("Horns2").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns3").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns4").ifPresent(b -> b.setHidden(false));
        }

        if (entity.getBreed() == 3) {
            poseStack.scale(1.1F, 1.1F, 1.1F);
            model.getBone("Wool1").ifPresent(b -> b.setScaleY(0.9F));
            model.getBone("Wool2").ifPresent(b -> b.setScaleY(0.9F));
            model.getBone("Wool2").ifPresent(b -> b.setScaleX(0.9F));
            model.getBone("Horns1").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns2").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns3").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns4").ifPresent(b -> b.setHidden(true));
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

}


