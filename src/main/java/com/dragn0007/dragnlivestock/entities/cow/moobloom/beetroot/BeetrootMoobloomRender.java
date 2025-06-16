package com.dragn0007.dragnlivestock.entities.cow.moobloom.beetroot;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BeetrootMoobloomRender extends GeoEntityRenderer<BeetrootMoobloom> {

    public BeetrootMoobloomRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BeetrootMoobloomModel());
        this.addRenderLayer(new BeetrootMoobloomMarkingLayer(this));
        this.addRenderLayer(new BeetrootMoobloomBrandTagLayer(this));
        this.addRenderLayer(new BeetrootMoobloomHarnessLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, BeetrootMoobloom entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        if (entity.isSheared()) {
            model.getBone("plant").ifPresent(b -> b.setHidden(true));
        } else {
            model.getBone("plant").ifPresent(b -> b.setHidden(false));
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
            model.getBone("hump").ifPresent(b -> b.setHidden(true));
            model.getBone("body_fluff").ifPresent(b -> b.setHidden(true));
            model.getBone("neck_fluff").ifPresent(b -> b.setHidden(true));
            model.getBone("head_fluff").ifPresent(b -> b.setHidden(true));
            model.getBone("wheat").ifPresent(b -> b.setHidden(true));
            model.getBone("melon").ifPresent(b -> b.setHidden(true));

            model.getBone("utters").ifPresent(b -> b.setScaleY(1.5F));
            model.getBone("utters").ifPresent(b -> b.setScaleX(1.5F));
            model.getBone("utters").ifPresent(b -> b.setScaleZ(1.5F));

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


