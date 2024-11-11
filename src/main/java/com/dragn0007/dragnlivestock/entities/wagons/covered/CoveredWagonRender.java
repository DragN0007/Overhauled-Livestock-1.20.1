package com.dragn0007.dragnlivestock.entities.wagons.covered;

import com.dragn0007.dragnlivestock.entities.unicorn.*;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CoveredWagonRender extends GeoEntityRenderer<CoveredWagon> {

    public CoveredWagonRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CoveredWagonModel());
    }

    @Override
    public void render(CoveredWagon entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}


