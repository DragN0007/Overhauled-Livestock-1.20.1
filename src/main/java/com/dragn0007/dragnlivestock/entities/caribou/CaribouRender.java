package com.dragn0007.dragnlivestock.entities.caribou;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CaribouRender extends GeoEntityRenderer<Caribou> {

    public CaribouRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CaribouModel());
        this.addRenderLayer(new CaribouMarkingLayer(this));
        this.addRenderLayer(new CaribouBrandTagLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, Caribou entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        if (!entity.isBaby()) {
            if (entity.hasChest()) {
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
            }

            if (entity.isSaddled()) {
                model.getBone("saddle").ifPresent(b -> b.setHidden(false));
                model.getBone("saddle2").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("saddle").ifPresent(b -> b.setHidden(true));
                model.getBone("saddle2").ifPresent(b -> b.setHidden(true));
            }

        }

        if (entity.isBaby()) {
            model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
            model.getBone("saddle").ifPresent(b -> b.setHidden(true));
            model.getBone("saddle2").ifPresent(b -> b.setHidden(true));
            model.getBone("front_right_shoe").ifPresent(b -> b.setHidden(true));
            model.getBone("front_left_shoe").ifPresent(b -> b.setHidden(true));
            model.getBone("back_right_shoe").ifPresent(b -> b.setHidden(true));
            model.getBone("back_left_shoe").ifPresent(b -> b.setHidden(true));
            model.getBone("body_armor").ifPresent(b -> b.setHidden(true));
            model.getBone("neck_armor").ifPresent(b -> b.setHidden(true));
            model.getBone("head_armor").ifPresent(b -> b.setHidden(true));
            model.getBone("AntlersRight").ifPresent(b -> b.setHidden(true));
            model.getBone("AntlersLeft").ifPresent(b -> b.setHidden(true));
        }

        if (entity.isFemale()) {
            model.getBone("AntlersRight").ifPresent(b -> b.setScaleX(0.6F));
            model.getBone("AntlersRight").ifPresent(b -> b.setScaleY(0.6F));
            model.getBone("AntlersRight").ifPresent(b -> b.setScaleZ(0.6F));
            model.getBone("AntlersLeft").ifPresent(b -> b.setScaleX(0.6F));
            model.getBone("AntlersLeft").ifPresent(b -> b.setScaleY(0.6F));
            model.getBone("AntlersLeft").ifPresent(b -> b.setScaleZ(0.6F));
        }

        if (entity.isMale()) {
            model.getBone("AntlersRight").ifPresent(b -> b.setScaleX(1F));
            model.getBone("AntlersRight").ifPresent(b -> b.setScaleY(1F));
            model.getBone("AntlersRight").ifPresent(b -> b.setScaleZ(1F));
            model.getBone("AntlersLeft").ifPresent(b -> b.setScaleX(1F));
            model.getBone("AntlersLeft").ifPresent(b -> b.setScaleY(1F));
            model.getBone("AntlersLeft").ifPresent(b -> b.setScaleZ(1F));
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}