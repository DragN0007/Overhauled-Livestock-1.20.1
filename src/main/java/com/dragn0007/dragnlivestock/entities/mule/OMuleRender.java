package com.dragn0007.dragnlivestock.entities.mule;

import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OMuleRender extends GeoEntityRenderer<OMule> {

    public OMuleRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OMuleModel());
        this.addRenderLayer(new OMuleMarkingLayer(this));
        this.addRenderLayer(new OMuleCarpetLayer(this));
        this.addRenderLayer(new OMuleArmorLayer(this));
    }

    @Override
    public void render(OMule entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if (!animatable.isBaby()) {

            if (animatable.hasChest() && LivestockOverhaulCommonConfig.HORSE_SADDLEBAG_RENDER.get()) {
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

            if (animatable.isWearingArmor()) {
                model.getBone("body_armor").ifPresent(b -> b.setHidden(false));
                model.getBone("neck_armor").ifPresent(b -> b.setHidden(false));
                model.getBone("head_armor").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("body_armor").ifPresent(b -> b.setHidden(true));
                model.getBone("neck_armor").ifPresent(b -> b.setHidden(true));
                model.getBone("head_armor").ifPresent(b -> b.setHidden(true));
            }
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}


