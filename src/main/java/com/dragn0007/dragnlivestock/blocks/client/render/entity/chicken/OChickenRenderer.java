package com.dragn0007.dragnlivestock.blocks.client.render.entity.chicken;

import com.dragn0007.dragnlivestock.common.entities.chicken.OChicken;
import com.dragn0007.dragnlivestock.blocks.client.render.model.entity.OChickenModel;
import com.dragn0007.dragnlivestock.blocks.client.LivestockOverhaulClientConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OChickenRenderer extends GeoEntityRenderer<OChicken> {

    public OChickenRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OChickenModel());
        this.addRenderLayer(new OChickenMarkingLayer(this));
        this.addRenderLayer(new OChickenBrandTagLayer(this));
    }

    @Override
    public void render(OChicken entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if (entity.isTagged() && LivestockOverhaulClientConfig.CHICKEN_NECK_TAG.get()) {
            model.getBone("neck_tag").ifPresent(b -> b.setHidden(false));
        } else {
            model.getBone("neck_tag").ifPresent(b -> b.setHidden(true));
        }

        if (entity.isTagged() && LivestockOverhaulClientConfig.CHICKEN_LEG_BAND.get()) {
            model.getBone("leg_band").ifPresent(b -> b.setHidden(false));
        } else {
            model.getBone("leg_band").ifPresent(b -> b.setHidden(true));
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}


