package com.dragn0007.dragnlivestock.entities.sheep;

import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
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
        this.addRenderLayer(new OSheepWoolLayer(this));
        this.addRenderLayer(new OSheepMarkingLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, OSheep entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        if (entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
            model.getBone("horns").ifPresent(b -> b.setHidden(true));
        }

        if (!entity.isBaby() && entity.isSheared()) {
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

        if (entity.getHornVariant() == 0) {
            model.getBone("gulf_coast_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("norfolk_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("dorset_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("jacob_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("racka_horns").ifPresent(b -> b.setHidden(true));
        } else if (entity.getHornVariant() == 1) {
            model.getBone("gulf_coast_horns").ifPresent(b -> b.setHidden(false));
            model.getBone("norfolk_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("dorset_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("jacob_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("racka_horns").ifPresent(b -> b.setHidden(true));
        } else if (entity.getHornVariant() == 2) {
            model.getBone("gulf_coast_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("norfolk_horns").ifPresent(b -> b.setHidden(false));
            model.getBone("dorset_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("jacob_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("racka_horns").ifPresent(b -> b.setHidden(true));
        } else if (entity.getHornVariant() == 3) {
            model.getBone("gulf_coast_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("norfolk_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("dorset_horns").ifPresent(b -> b.setHidden(false));
            model.getBone("jacob_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("racka_horns").ifPresent(b -> b.setHidden(true));
        } else if (entity.getHornVariant() == 4) {
            model.getBone("gulf_coast_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("norfolk_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("dorset_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("jacob_horns").ifPresent(b -> b.setHidden(false));
            model.getBone("racka_horns").ifPresent(b -> b.setHidden(true));
        } else if (entity.getHornVariant() == 5) {
            model.getBone("gulf_coast_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("norfolk_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("dorset_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("jacob_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("racka_horns").ifPresent(b -> b.setHidden(false));
        }

        if (entity.getBreed() == 2 || entity.getBreed() == 4) {
            model.getBone("wool_body").ifPresent(b -> b.setScaleY(1.1F));
            model.getBone("wool_body").ifPresent(b -> b.setScaleX(1.1F));
            model.getBone("wool_body").ifPresent(b -> b.setScaleZ(1.1F));
            model.getBone("wool_neck").ifPresent(b -> b.setScaleY(1.1F));
            model.getBone("wool_neck").ifPresent(b -> b.setScaleX(1.1F));
            model.getBone("wool_neck").ifPresent(b -> b.setScaleZ(1.1F));
            model.getBone("right_thigh_wool").ifPresent(b -> b.setScaleY(1.1F));
            model.getBone("right_thigh_wool").ifPresent(b -> b.setScaleX(1.1F));
            model.getBone("right_thigh_wool").ifPresent(b -> b.setScaleZ(1.1F));
            model.getBone("left_thigh_wool").ifPresent(b -> b.setScaleY(1.1F));
            model.getBone("left_thigh_wool").ifPresent(b -> b.setScaleX(1.1F));
            model.getBone("left_thigh_wool").ifPresent(b -> b.setScaleZ(1.1F));
            model.getBone("tail").ifPresent(b -> b.setScaleY(1.1F));
            model.getBone("tail").ifPresent(b -> b.setScaleX(1.1F));
            model.getBone("tail").ifPresent(b -> b.setScaleZ(1.1F));
        } else {
            model.getBone("wool_body").ifPresent(b -> b.setScaleY(1.0F));
            model.getBone("wool_body").ifPresent(b -> b.setScaleX(1.0F));
            model.getBone("wool_body").ifPresent(b -> b.setScaleZ(1.0F));
            model.getBone("wool_neck").ifPresent(b -> b.setScaleY(1.0F));
            model.getBone("wool_neck").ifPresent(b -> b.setScaleX(1.0F));
            model.getBone("wool_neck").ifPresent(b -> b.setScaleZ(1.0F));
            model.getBone("right_thigh_wool").ifPresent(b -> b.setScaleY(1.0F));
            model.getBone("right_thigh_wool").ifPresent(b -> b.setScaleX(1.0F));
            model.getBone("right_thigh_wool").ifPresent(b -> b.setScaleZ(1.0F));
            model.getBone("left_thigh_wool").ifPresent(b -> b.setScaleY(1.0F));
            model.getBone("left_thigh_wool").ifPresent(b -> b.setScaleX(1.0F));
            model.getBone("left_thigh_wool").ifPresent(b -> b.setScaleZ(1.0F));
            model.getBone("tail").ifPresent(b -> b.setScaleY(1.0F));
            model.getBone("tail").ifPresent(b -> b.setScaleX(1.0F));
            model.getBone("tail").ifPresent(b -> b.setScaleZ(1.0F));
        }

        super.preRender(poseStack, this.animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

}


