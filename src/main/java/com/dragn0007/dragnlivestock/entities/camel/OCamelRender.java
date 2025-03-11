package com.dragn0007.dragnlivestock.entities.camel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OCamelRender extends GeoEntityRenderer<OCamel> {

    public OCamelRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OCamelModel());
        this.addRenderLayer(new OCamelMarkingLayer(this));
        this.addRenderLayer(new OCamelCarpetLayer(this));
        this.addRenderLayer(new OCamelBrandTagLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, OCamel entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        if (!entity.isBaby()) {

            if (entity.hasChest()) {
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
            }

            if (entity.isSaddled()) {
                model.getBone("halter").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("halter").ifPresent(b -> b.setHidden(true));
            }

        }

        if (entity.isBaby()) {
            if (entity.getBreed() == 1) {
                model.getBone("halter").ifPresent(b -> b.setHidden(true));
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
                model.getBone("carpet").ifPresent(b -> b.setHidden(true));
                model.getBone("carpet2").ifPresent(b -> b.setHidden(true));
                poseStack.scale(0.5F, 0.5F, 0.5F);
            } else if (entity.getBreed() == 0) {
                model.getBone("halter").ifPresent(b -> b.setHidden(true));
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
                model.getBone("carpet").ifPresent(b -> b.setHidden(true));
                model.getBone("carpet2").ifPresent(b -> b.setHidden(true));
                poseStack.scale(1F, 1F, 1F);
            }
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}


