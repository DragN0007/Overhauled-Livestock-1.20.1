package com.dragn0007.dragnlivestock.entities.cow;

import com.dragn0007.dragnlivestock.entities.cow.ox.OCowBellLayer;
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
        this.addRenderLayer(new OCowHarnessLayer(this));
        this.addRenderLayer(new OCowBellLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, OCow entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        if (entity.getBreed() == 9) {
            model.getBone("body_fluff").ifPresent(b -> b.setHidden(false));
            model.getBone("neck_fluff").ifPresent(b -> b.setHidden(false));
            model.getBone("head_fluff").ifPresent(b -> b.setHidden(false));
        } else {
            model.getBone("body_fluff").ifPresent(b -> b.setHidden(true));
            model.getBone("neck_fluff").ifPresent(b -> b.setHidden(true));
            model.getBone("head_fluff").ifPresent(b -> b.setHidden(true));
        }

        if (entity.getBreed() == 2) {
            model.getBone("crest").ifPresent(b -> b.setHidden(true));
            model.getBone("hump").ifPresent(b -> b.setHidden(false));
            model.getBone("left_ear").ifPresent(b -> b.setScaleY(2.0F));
            model.getBone("right_ear").ifPresent(b -> b.setScaleY(2.0F));
        } else {
            model.getBone("crest").ifPresent(b -> b.setHidden(false));
            model.getBone("hump").ifPresent(b -> b.setHidden(true));
            model.getBone("left_ear").ifPresent(b -> b.setScaleY(1.0F));
            model.getBone("right_ear").ifPresent(b -> b.setScaleY(1.0F));
        }

        if(entity.isBaby()) {
            model.getBone("utters").ifPresent(b -> b.setHidden(true));
            model.getBone("horns_1").ifPresent(b -> b.setHidden(true));
            model.getBone("horns_2").ifPresent(b -> b.setHidden(true));
            model.getBone("horns_3").ifPresent(b -> b.setHidden(true));
            model.getBone("horns_4").ifPresent(b -> b.setHidden(true));
            model.getBone("horns_5").ifPresent(b -> b.setHidden(true));
            model.getBone("horns_6").ifPresent(b -> b.setHidden(true));
            model.getBone("horns_7").ifPresent(b -> b.setHidden(true));
            model.getBone("horns_8").ifPresent(b -> b.setHidden(true));
            model.getBone("horns_9").ifPresent(b -> b.setHidden(true));
            model.getBone("horns_10").ifPresent(b -> b.setHidden(true));
            model.getBone("horn_connection").ifPresent(b -> b.setHidden(true));
        } else {

            if (entity.isDairyBreed()) {
                model.getBone("utters").ifPresent(b -> b.setScaleY(1.5F));
                model.getBone("utters").ifPresent(b -> b.setScaleX(1.5F));
                model.getBone("utters").ifPresent(b -> b.setScaleZ(1.5F));
            } else {
                model.getBone("utters").ifPresent(b -> b.setScaleY(1.0F));
                model.getBone("utters").ifPresent(b -> b.setScaleX(1.0F));
                model.getBone("utters").ifPresent(b -> b.setScaleZ(1.0F));
            }

            if (!(entity.getHornVariant() == 0)) {
                model.getBone("horn_connection").ifPresent(b -> b.setScaleY(2.0F));
            } else {
                model.getBone("horn_connection").ifPresent(b -> b.setScaleY(1.0F));
            }

            if (entity.getHornVariant() == 0) {
                model.getBone("horns_1").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_2").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_3").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_4").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_5").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_6").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_7").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_8").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_9").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_10").ifPresent(b -> b.setHidden(true));
            } else if (entity.getHornVariant() == 1) {
                model.getBone("horns_1").ifPresent(b -> b.setHidden(false));
                model.getBone("horns_2").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_3").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_4").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_5").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_6").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_7").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_8").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_9").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_10").ifPresent(b -> b.setHidden(true));
            } else if (entity.getHornVariant() == 2) {
                model.getBone("horns_1").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_2").ifPresent(b -> b.setHidden(false));
                model.getBone("horns_3").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_4").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_5").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_6").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_7").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_8").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_9").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_10").ifPresent(b -> b.setHidden(true));
            } else if (entity.getHornVariant() == 3) {
                model.getBone("horns_1").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_2").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_3").ifPresent(b -> b.setHidden(false));
                model.getBone("horns_4").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_5").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_6").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_7").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_8").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_9").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_10").ifPresent(b -> b.setHidden(true));
            } else if (entity.getHornVariant() == 4) {
                model.getBone("horns_1").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_2").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_3").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_4").ifPresent(b -> b.setHidden(false));
                model.getBone("horns_5").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_6").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_7").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_8").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_9").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_10").ifPresent(b -> b.setHidden(true));
            } else if (entity.getHornVariant() == 5) {
                model.getBone("horns_1").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_2").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_3").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_4").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_5").ifPresent(b -> b.setHidden(false));
                model.getBone("horns_6").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_7").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_8").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_9").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_10").ifPresent(b -> b.setHidden(true));
            } else if (entity.getHornVariant() == 6) {
                model.getBone("horns_1").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_2").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_3").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_4").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_5").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_6").ifPresent(b -> b.setHidden(false));
                model.getBone("horns_7").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_8").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_9").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_10").ifPresent(b -> b.setHidden(true));
            } else if (entity.getHornVariant() == 7) {
                model.getBone("horns_1").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_2").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_3").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_4").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_5").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_6").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_7").ifPresent(b -> b.setHidden(false));
                model.getBone("horns_8").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_9").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_10").ifPresent(b -> b.setHidden(true));
            } else if (entity.getHornVariant() == 8) {
                model.getBone("horns_1").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_2").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_3").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_4").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_5").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_6").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_7").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_8").ifPresent(b -> b.setHidden(false));
                model.getBone("horns_9").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_10").ifPresent(b -> b.setHidden(true));
            } else if (entity.getHornVariant() == 9) {
                model.getBone("horns_1").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_2").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_3").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_4").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_5").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_6").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_7").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_8").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_9").ifPresent(b -> b.setHidden(false));
                model.getBone("horns_10").ifPresent(b -> b.setHidden(true));
            } else if (entity.getHornVariant() == 10) {
                model.getBone("horns_1").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_2").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_3").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_4").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_5").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_6").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_7").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_8").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_9").ifPresent(b -> b.setHidden(true));
                model.getBone("horns_10").ifPresent(b -> b.setHidden(false));
            }

        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}


