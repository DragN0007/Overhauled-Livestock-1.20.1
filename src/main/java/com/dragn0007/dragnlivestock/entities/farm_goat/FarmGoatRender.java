package com.dragn0007.dragnlivestock.entities.farm_goat;

import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FarmGoatRender extends GeoEntityRenderer<FarmGoat> {

    public FarmGoatRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FarmGoatModel());
        this.addRenderLayer(new FarmGoatMarkingLayer(this));
        this.addRenderLayer(new FarmGoatFaceMarkingLayer(this));
        this.addRenderLayer(new FarmGoatEyeLayer(this));
        this.addRenderLayer(new FarmGoatChestLayer(this));
        this.addRenderLayer(new FarmGoatBrandTagLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, FarmGoat entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        if(entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
            model.getBone("horns").ifPresent(b -> b.setHidden(true));
        } else {
            poseStack.scale(1F, 1F, 1F);

            if (entity.hasChest()) {
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
            }

            if (entity.isSheared() || !(entity.getBreed() == 4)) {
                model.getBone("wool_body").ifPresent(b -> b.setHidden(true));
                model.getBone("wool_neck").ifPresent(b -> b.setHidden(true));
                model.getBone("right_thigh_wool").ifPresent(b -> b.setHidden(true));
                model.getBone("left_thigh_wool").ifPresent(b -> b.setHidden(true));
            } else {
                model.getBone("wool_body").ifPresent(b -> b.setHidden(false));
                model.getBone("wool_neck").ifPresent(b -> b.setHidden(false));
                model.getBone("right_thigh_wool").ifPresent(b -> b.setHidden(false));
                model.getBone("left_thigh_wool").ifPresent(b -> b.setHidden(false));
            }

            if (entity.getBreed() == 5) {
                model.getBone("left_ear").ifPresent(b -> b.setHidden(true));
                model.getBone("right_ear").ifPresent(b -> b.setHidden(true));
            } else {
                model.getBone("left_ear").ifPresent(b -> b.setHidden(false));
                model.getBone("right_ear").ifPresent(b -> b.setHidden(false));
            }

            if (entity.wasMilked()) {
                if (entity.getBreed() == 5) {
                    model.getBone("utters").ifPresent(b -> b.setScaleY(1.2F));
                    model.getBone("utters").ifPresent(b -> b.setScaleX(1.2F));
                    model.getBone("utters").ifPresent(b -> b.setScaleZ(1.2F));
                } else {
                    model.getBone("utters").ifPresent(b -> b.setScaleY(0.8F));
                    model.getBone("utters").ifPresent(b -> b.setScaleX(0.8F));
                    model.getBone("utters").ifPresent(b -> b.setScaleZ(0.8F));
                }
            } else if (!entity.wasMilked()) {
                if (entity.getBreed() == 5) {
                    model.getBone("utters").ifPresent(b -> b.setScaleY(1.5F));
                    model.getBone("utters").ifPresent(b -> b.setScaleX(1.5F));
                    model.getBone("utters").ifPresent(b -> b.setScaleZ(1.5F));
                } else {
                    model.getBone("utters").ifPresent(b -> b.setScaleY(1.0F));
                    model.getBone("utters").ifPresent(b -> b.setScaleX(1.0F));
                    model.getBone("utters").ifPresent(b -> b.setScaleZ(1.0F));
                }
            }
        }

        if (entity.getHornVariant() == 0) {
            model.getBone("backwards_curl_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("small_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("polycerate_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("upwards_curl_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("corkscrew_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("outward_fanning_horns").ifPresent(b -> b.setHidden(true));
        } else if (entity.getHornVariant() == 1) {
            model.getBone("backwards_curl_horns").ifPresent(b -> b.setHidden(false));
            model.getBone("small_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("polycerate_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("upwards_curl_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("corkscrew_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("outward_fanning_horns").ifPresent(b -> b.setHidden(true));
        } else if (entity.getHornVariant() == 2) {
            model.getBone("backwards_curl_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("small_horns").ifPresent(b -> b.setHidden(false));
            model.getBone("polycerate_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("upwards_curl_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("corkscrew_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("outward_fanning_horns").ifPresent(b -> b.setHidden(true));
        } else if (entity.getHornVariant() == 3) {
            model.getBone("backwards_curl_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("small_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("polycerate_horns").ifPresent(b -> b.setHidden(false));
            model.getBone("upwards_curl_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("corkscrew_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("outward_fanning_horns").ifPresent(b -> b.setHidden(true));
        } else if (entity.getHornVariant() == 4) {
            model.getBone("backwards_curl_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("small_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("polycerate_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("upwards_curl_horns").ifPresent(b -> b.setHidden(false));
            model.getBone("corkscrew_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("outward_fanning_horns").ifPresent(b -> b.setHidden(true));
        } else if (entity.getHornVariant() == 5) {
            model.getBone("backwards_curl_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("small_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("polycerate_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("upwards_curl_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("corkscrew_horns").ifPresent(b -> b.setHidden(false));
            model.getBone("outward_fanning_horns").ifPresent(b -> b.setHidden(true));
        } else if (entity.getHornVariant() == 6) {
            model.getBone("backwards_curl_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("small_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("polycerate_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("upwards_curl_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("corkscrew_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("outward_fanning_horns").ifPresent(b -> b.setHidden(false));
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

}


