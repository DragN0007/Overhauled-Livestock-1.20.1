package com.dragn0007.dragnlivestock.entities.unicorn;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class UnicornRender extends GeoEntityRenderer<Unicorn> {

    public UnicornRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new UnicornModel());
        this.addRenderLayer(new UnicornMarkingLayer(this));
        this.addRenderLayer(new UnicornEyeLayer(this));
        this.addRenderLayer(new UnicornHornLayer(this));
        this.addRenderLayer(new UnicornCaparisonLayer(this));
        this.addRenderLayer(new UnicornSaddleLayer(this));
        this.addRenderLayer(new UnicornArmorLayer(this));
        this.addRenderLayer(new UnicornCarpetLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, Unicorn entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        if (!entity.isBaby()) {
            if (entity.hasChest()) {
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
            }

            if (entity.isSaddled()) {
                model.getBone("saddle").ifPresent(b -> b.setHidden(false));
                model.getBone("saddle2").ifPresent(b -> b.setHidden(false));
                model.getBone("front_right_shoe").ifPresent(b -> b.setHidden(false));
                model.getBone("front_left_shoe").ifPresent(b -> b.setHidden(false));
                model.getBone("back_right_shoe").ifPresent(b -> b.setHidden(false));
                model.getBone("back_left_shoe").ifPresent(b -> b.setHidden(false));
            } else if (entity.isSaddled()) {
                model.getBone("saddle").ifPresent(b -> b.setHidden(false));
                model.getBone("saddle2").ifPresent(b -> b.setHidden(false));
                model.getBone("front_right_shoe").ifPresent(b -> b.setHidden(false));
                model.getBone("front_left_shoe").ifPresent(b -> b.setHidden(false));
                model.getBone("back_right_shoe").ifPresent(b -> b.setHidden(false));
                model.getBone("back_left_shoe").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("saddle").ifPresent(b -> b.setHidden(true));
                model.getBone("saddle2").ifPresent(b -> b.setHidden(true));
                model.getBone("front_right_shoe").ifPresent(b -> b.setHidden(true));
                model.getBone("front_left_shoe").ifPresent(b -> b.setHidden(true));
                model.getBone("back_right_shoe").ifPresent(b -> b.setHidden(true));
                model.getBone("back_left_shoe").ifPresent(b -> b.setHidden(true));
            }

            if (animatable.isWearingPullingHarness()) {
                model.getBone("wagon_harness").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("wagon_harness").ifPresent(b -> b.setHidden(true));
            }
        }

        if (entity.getManeType() == 0) {
            model.getBone("roached").ifPresent(b -> b.setHidden(true));
            model.getBone("short").ifPresent(b -> b.setHidden(true));
            model.getBone("buttons").ifPresent(b -> b.setHidden(false));
            model.getBone("long").ifPresent(b -> b.setHidden(true));
        }

        if (entity.getManeType() == 1) {
            model.getBone("roached").ifPresent(b -> b.setHidden(true));
            model.getBone("short").ifPresent(b -> b.setHidden(true));
            model.getBone("buttons").ifPresent(b -> b.setHidden(true));
            model.getBone("long").ifPresent(b -> b.setHidden(false));
        }

        if (entity.getManeType() == 2) {
            model.getBone("roached").ifPresent(b -> b.setHidden(false));
            model.getBone("short").ifPresent(b -> b.setHidden(true));
            model.getBone("buttons").ifPresent(b -> b.setHidden(true));
            model.getBone("long").ifPresent(b -> b.setHidden(true));
            model.getBone("mane").ifPresent(b -> b.setScaleY(1.0F));
        }

        if (entity.getManeType() == 3) {
            model.getBone("roached").ifPresent(b -> b.setHidden(true));
            model.getBone("short").ifPresent(b -> b.setHidden(false));
            model.getBone("buttons").ifPresent(b -> b.setHidden(true));
            model.getBone("long").ifPresent(b -> b.setHidden(true));
        }

        if (entity.getManeType() == 4) {
            model.getBone("roached").ifPresent(b -> b.setHidden(true));
            model.getBone("short").ifPresent(b -> b.setHidden(true));
            model.getBone("buttons").ifPresent(b -> b.setHidden(true));
            model.getBone("long").ifPresent(b -> b.setHidden(true));
        }

        if (entity.getFeathering() == 0) {
            model.getBone("front_right_feathering").ifPresent(b -> b.setHidden(true));
            model.getBone("front_left_feathering").ifPresent(b -> b.setHidden(true));
            model.getBone("back_right_feathering").ifPresent(b -> b.setHidden(true));
            model.getBone("back_left_feathering").ifPresent(b -> b.setHidden(true));
        }

        if (entity.getFeathering() == 1) {
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

        if (entity.getFeathering() == 2) {
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


