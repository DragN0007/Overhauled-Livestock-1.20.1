package com.dragn0007.dragnlivestock.entities.chicken;

import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OChickenRender extends GeoEntityRenderer<OChicken> {

    public OChickenRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OChickenModel());
        this.addRenderLayer(new OChickenMarkingLayer(this));
        this.addRenderLayer(new OChickenBrandTagLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, OChicken entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        if (!entity.isBaby()) {
            if (entity.isMale()) {
                model.getBone("tail2").ifPresent(b -> b.setHidden(false));
                model.getBone("comb").ifPresent(b -> b.setHidden(false));
                model.getBone("gizzard").ifPresent(b -> b.setScaleX(1.0F));
                model.getBone("gizzard").ifPresent(b -> b.setScaleY(1.0F));
                model.getBone("gizzard").ifPresent(b -> b.setScaleZ(1.0F));
            } else {
                model.getBone("tail2").ifPresent(b -> b.setHidden(true));
                model.getBone("comb").ifPresent(b -> b.setHidden(true));
                model.getBone("gizzard").ifPresent(b -> b.setScaleX(0.6F));
                model.getBone("gizzard").ifPresent(b -> b.setScaleY(0.6F));
                model.getBone("gizzard").ifPresent(b -> b.setScaleZ(0.6F));
            }
        } else if (entity.isBaby()) {
            model.getBone("tail2").ifPresent(b -> b.setHidden(true));
            model.getBone("comb").ifPresent(b -> b.setHidden(true));
            model.getBone("gizzard").ifPresent(b -> b.setScaleX(0.2F));
            model.getBone("gizzard").ifPresent(b -> b.setScaleY(0.2F));
            model.getBone("gizzard").ifPresent(b -> b.setScaleZ(0.2F));
        }

        if (entity.isTagged()) {
            model.getBone("neck_tag").ifPresent(b -> b.setHidden(false));
        } else {
            model.getBone("neck_tag").ifPresent(b -> b.setHidden(true));
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}


