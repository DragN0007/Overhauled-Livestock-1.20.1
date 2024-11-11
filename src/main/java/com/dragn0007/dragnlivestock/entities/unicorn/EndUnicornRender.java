package com.dragn0007.dragnlivestock.entities.unicorn;

import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EndUnicornRender extends GeoEntityRenderer<EndUnicorn> {

    public EndUnicornRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EndUnicornModel());
        this.addRenderLayer(new EndUnicornHornLayer(this));
        this.addRenderLayer(new EndUnicornCarpetLayer(this));
        this.addRenderLayer(new EndUnicornArmorLayer(this));
    }

    @Override
    public void render(EndUnicorn entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if (!entity.isBaby()) {

            if (entity.hasChest() && LivestockOverhaulCommonConfig.HORSE_SADDLEBAG_RENDER.get()) {
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
            } else {
                model.getBone("saddle").ifPresent(b -> b.setHidden(true));
                model.getBone("saddle2").ifPresent(b -> b.setHidden(true));
                model.getBone("front_right_shoe").ifPresent(b -> b.setHidden(true));
                model.getBone("front_left_shoe").ifPresent(b -> b.setHidden(true));
                model.getBone("back_right_shoe").ifPresent(b -> b.setHidden(true));
                model.getBone("back_left_shoe").ifPresent(b -> b.setHidden(true));
            }

            if (entity.isWearingArmor()) {
                model.getBone("body_armor").ifPresent(b -> b.setHidden(false));
                model.getBone("neck_armor").ifPresent(b -> b.setHidden(false));
                model.getBone("head_armor").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("body_armor").ifPresent(b -> b.setHidden(true));
                model.getBone("neck_armor").ifPresent(b -> b.setHidden(true));
                model.getBone("head_armor").ifPresent(b -> b.setHidden(true));
            }

            if (entity.isLeashed()) {
                model.getBone("saddle2").ifPresent(b -> b.setHidden(false));
                model.getBone("Reins").ifPresent(b -> b.setHidden(true));
            } else {
                model.getBone("saddle2").ifPresent(b -> b.setHidden(false));
                model.getBone("Reins").ifPresent(b -> b.setHidden(false));
            }
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}


