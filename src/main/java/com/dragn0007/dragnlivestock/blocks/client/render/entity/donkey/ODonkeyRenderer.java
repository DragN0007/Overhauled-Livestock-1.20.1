package com.dragn0007.dragnlivestock.blocks.client.render.entity.donkey;

import com.dragn0007.dragnlivestock.blocks.client.render.model.entity.ODonkeyModel;
import com.dragn0007.dragnlivestock.common.entities.ODonkey;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ODonkeyRenderer extends GeoEntityRenderer<ODonkey> {

    public ODonkeyRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ODonkeyModel());
        this.addRenderLayer(new ODonkeyMarkingLayer(this));
        this.addRenderLayer(new ODonkeyEyeLayer(this));
        this.addRenderLayer(new ODonkeyArmorLayer(this));
        this.addRenderLayer(new ODonkeyCarpetLayer(this));
        this.addRenderLayer(new ODonkeySaddleLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, ODonkey entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

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

            if (animatable.isWearingPullingHarness()) {
                model.getBone("wagon_harness").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("wagon_harness").ifPresent(b -> b.setHidden(true));
            }
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

}


