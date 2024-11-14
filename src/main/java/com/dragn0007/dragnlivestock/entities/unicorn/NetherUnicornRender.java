package com.dragn0007.dragnlivestock.entities.unicorn;

import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NetherUnicornRender extends GeoEntityRenderer<NetherUnicorn> {

    public NetherUnicornRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NetherUnicornModel());
        this.addRenderLayer(new NetherUnicornHornLayer(this));
        this.addRenderLayer(new NetherUnicornCarpetLayer(this));
        this.addRenderLayer(new NetherUnicornArmorLayer(this));
    }

    @Override
    public void render(NetherUnicorn entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

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
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}


