package com.dragn0007.dragnlivestock.entities.camel;

import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OCamelRender extends GeoEntityRenderer<OCamel> {

    public OCamelRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OCamelModel());
        this.addRenderLayer(new OCamelMarkingLayer(this));
        this.addRenderLayer(new OCamelCarpetLayer(this));
    }

    @Override
    public void render(OCamel entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if (!entity.isBaby()) {

            if (entity.hasChest() && LivestockOverhaulCommonConfig.HORSE_SADDLEBAG_RENDER.get()) {
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
            }

            if (entity.isSaddled()) {
                model.getBone("halter").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("halter").ifPresent(b -> b.setHidden(true));
            }

        }

        if(entity.isBaby()) {
            model.getBone("halter").ifPresent(b -> b.setHidden(true));
            model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
            model.getBone("carpet").ifPresent(b -> b.setHidden(true));
            model.getBone("carpet2").ifPresent(b -> b.setHidden(true));
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}


