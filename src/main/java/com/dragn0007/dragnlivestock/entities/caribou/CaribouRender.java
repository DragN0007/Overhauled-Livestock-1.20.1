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
        this.addRenderLayer(new CaribouEyeLayer(this));
        this.addRenderLayer(new CaribouBrandTagLayer(this));
        this.addRenderLayer(new CaribouArmorLayer(this));
        this.addRenderLayer(new CaribouCarpetLayer(this));
        this.addRenderLayer(new CaribouSaddleLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, Caribou entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        model.getBone("wagon_harness").ifPresent(b -> b.setHidden(true));

        if (!animatable.isBaby()) {
            if (animatable.hasChest()) {
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
            }

            if (animatable.isSaddled()) {
                model.getBone("saddle").ifPresent(b -> b.setHidden(false));
                model.getBone("saddle2").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("saddle").ifPresent(b -> b.setHidden(true));
                model.getBone("saddle2").ifPresent(b -> b.setHidden(true));
            }

            if (entity.isFemale()) {
                model.getBone("right_antler").ifPresent(b -> b.setScaleX(0.6F));
                model.getBone("right_antler").ifPresent(b -> b.setScaleY(0.6F));
                model.getBone("right_antler").ifPresent(b -> b.setScaleZ(0.6F));
                model.getBone("left_antler").ifPresent(b -> b.setScaleX(0.6F));
                model.getBone("left_antler").ifPresent(b -> b.setScaleY(0.6F));
                model.getBone("left_antler").ifPresent(b -> b.setScaleZ(0.6F));
            }

            if (entity.isMale()) {
                model.getBone("right_antler").ifPresent(b -> b.setScaleX(1F));
                model.getBone("right_antler").ifPresent(b -> b.setScaleY(1F));
                model.getBone("right_antler").ifPresent(b -> b.setScaleZ(1F));
                model.getBone("left_antler").ifPresent(b -> b.setScaleX(1F));
                model.getBone("left_antler").ifPresent(b -> b.setScaleY(1F));
                model.getBone("left_antler").ifPresent(b -> b.setScaleZ(1F));
            }
        }

        if (animatable.getFeathering() == 0) {
            model.getBone("front_right_feathering").ifPresent(b -> b.setHidden(true));
            model.getBone("front_left_feathering").ifPresent(b -> b.setHidden(true));
            model.getBone("back_right_feathering").ifPresent(b -> b.setHidden(true));
            model.getBone("back_left_feathering").ifPresent(b -> b.setHidden(true));
        }

        if (animatable.getFeathering() == 1) {
            model.getBone("front_right_feathering").ifPresent(b -> b.setHidden(false));
            model.getBone("front_left_feathering").ifPresent(b -> b.setHidden(false));
            model.getBone("back_right_feathering").ifPresent(b -> b.setHidden(false));
            model.getBone("back_left_feathering").ifPresent(b -> b.setHidden(false));
            model.getBone("front_right_feathering").ifPresent(b -> b.setScaleY(0.6F));
            model.getBone("front_left_feathering").ifPresent(b -> b.setScaleY(0.6F));
            model.getBone("back_right_feathering").ifPresent(b -> b.setScaleY(0.6F));
            model.getBone("back_left_feathering").ifPresent(b -> b.setScaleY(0.6F));
            model.getBone("front_right_feathering").ifPresent(b -> b.setPosY(-3.5F));
            model.getBone("front_left_feathering").ifPresent(b -> b.setPosY(-3.5F));
            model.getBone("back_right_feathering").ifPresent(b -> b.setPosY(-3.5F));
            model.getBone("back_left_feathering").ifPresent(b -> b.setPosY(-3.5F));
            model.getBone("front_right_feathering").ifPresent(b -> b.setPosZ(-0.8F));
            model.getBone("front_left_feathering").ifPresent(b -> b.setPosZ(-0.8F));
        }

        if (animatable.getFeathering() == 2) {
            model.getBone("front_right_feathering").ifPresent(b -> b.setHidden(false));
            model.getBone("front_left_feathering").ifPresent(b -> b.setHidden(false));
            model.getBone("back_right_feathering").ifPresent(b -> b.setHidden(false));
            model.getBone("back_left_feathering").ifPresent(b -> b.setHidden(false));
            model.getBone("front_right_feathering").ifPresent(b -> b.setScaleY(1F));
            model.getBone("front_left_feathering").ifPresent(b -> b.setScaleY(1F));
            model.getBone("back_right_feathering").ifPresent(b -> b.setScaleY(1F));
            model.getBone("back_left_feathering").ifPresent(b -> b.setScaleY(1F));
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}