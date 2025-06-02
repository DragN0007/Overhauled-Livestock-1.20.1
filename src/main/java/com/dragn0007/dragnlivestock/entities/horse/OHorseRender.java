package com.dragn0007.dragnlivestock.entities.horse;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OHorseRender extends GeoEntityRenderer<OHorse> {

    public OHorseRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OHorseModel());
        this.addRenderLayer(new OHorseMarkingLayer(this));
        this.addRenderLayer(new OHorseEyeLayer(this));
        this.addRenderLayer(new OHorseDecompLayer(this));
        this.addRenderLayer(new OHorseArmorLayer(this));
        this.addRenderLayer(new OHorseCarpetLayer(this));
        this.addRenderLayer(new OHorseSaddleLayer(this));
        this.addRenderLayer(new OHorseShoeLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, OHorse animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

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
        }

        if (animatable.getManeType() == 0) {
            model.getBone("roached").ifPresent(b -> b.setHidden(true));
            model.getBone("short").ifPresent(b -> b.setHidden(true));
            model.getBone("buttons").ifPresent(b -> b.setHidden(false));
            model.getBone("long").ifPresent(b -> b.setHidden(true));
        }

        if (animatable.getManeType() == 1) {
            model.getBone("roached").ifPresent(b -> b.setHidden(true));
            model.getBone("short").ifPresent(b -> b.setHidden(true));
            model.getBone("buttons").ifPresent(b -> b.setHidden(true));
            model.getBone("long").ifPresent(b -> b.setHidden(false));
        }

        if (animatable.getManeType() == 2) {
            model.getBone("roached").ifPresent(b -> b.setHidden(false));
            model.getBone("short").ifPresent(b -> b.setHidden(true));
            model.getBone("buttons").ifPresent(b -> b.setHidden(true));
            model.getBone("long").ifPresent(b -> b.setHidden(true));
            model.getBone("mane").ifPresent(b -> b.setScaleY(1.0F));
        }

        if (animatable.getManeType() == 3) {
            model.getBone("roached").ifPresent(b -> b.setHidden(true));
            model.getBone("short").ifPresent(b -> b.setHidden(false));
            model.getBone("buttons").ifPresent(b -> b.setHidden(true));
            model.getBone("long").ifPresent(b -> b.setHidden(true));
        }

        if (animatable.getManeType() == 4) {
            model.getBone("roached").ifPresent(b -> b.setHidden(true));
            model.getBone("short").ifPresent(b -> b.setHidden(true));
            model.getBone("buttons").ifPresent(b -> b.setHidden(true));
            model.getBone("long").ifPresent(b -> b.setHidden(true));
        }

        if (animatable.getTailType() == 0) {
            model.getBone("tail").ifPresent(b -> b.setScaleY(0.9F));
            model.getBone("tail").ifPresent(b -> b.setScaleX(0.7F));
            model.getBone("tail").ifPresent(b -> b.setScaleZ(0.7F));
            model.getBone("tail_2").ifPresent(b -> b.setHidden(true));
        }

        if (animatable.getTailType() == 1) {
            model.getBone("tail").ifPresent(b -> b.setScaleX(1.0F));
            model.getBone("tail").ifPresent(b -> b.setScaleY(1.3F));
            model.getBone("tail").ifPresent(b -> b.setScaleZ(1.0F));
            model.getBone("tail_2").ifPresent(b -> b.setHidden(false));
        }

        if (animatable.getTailType() == 2) {
            model.getBone("tail").ifPresent(b -> b.setScaleX(1.0F));
            model.getBone("tail").ifPresent(b -> b.setScaleY(1.0F));
            model.getBone("tail").ifPresent(b -> b.setScaleZ(1.0F));
            model.getBone("tail_2").ifPresent(b -> b.setHidden(false));
        }

        if (animatable.getTailType() == 3) {
            model.getBone("tail").ifPresent(b -> b.setScaleX(1.0F));
            model.getBone("tail").ifPresent(b -> b.setScaleY(0.6F));
            model.getBone("tail").ifPresent(b -> b.setScaleZ(1.0F));
            model.getBone("tail_2").ifPresent(b -> b.setHidden(false));
        }

        if (animatable.getTailType() == 4) {
            model.getBone("tail").ifPresent(b -> b.setScaleY(0.7F));
            model.getBone("tail").ifPresent(b -> b.setScaleX(1.1F));
            model.getBone("tail").ifPresent(b -> b.setScaleZ(1.0F));
            model.getBone("tail_2").ifPresent(b -> b.setHidden(true));
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

        super.preRender(poseStack, this.animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}