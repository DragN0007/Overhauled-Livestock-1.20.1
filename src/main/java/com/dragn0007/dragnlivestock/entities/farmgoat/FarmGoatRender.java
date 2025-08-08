package com.dragn0007.dragnlivestock.entities.farmgoat;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FarmGoatRender extends GeoEntityRenderer<FarmGoat> {

    public FarmGoatRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FarmGoatModel());
        this.addRenderLayer(new FarmGoatMarkingLayer(this));
        this.addRenderLayer(new FarmGoatCarpetLayer(this));
        this.addRenderLayer(new FarmGoatChestLayer(this));
        this.addRenderLayer(new FarmGoatBrandTagLayer(this));
    }

    @Override
    public void render(FarmGoat entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if(entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
            model.getBone("male_horns").ifPresent(b -> b.setHidden(true));
            model.getBone("female_horns").ifPresent(b -> b.setHidden(true));
        } else {
            poseStack.scale(1F, 1F, 1F);

            if (entity.hasChest()) {
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
            }

            if(entity.isMale()) {
                model.getBone("male_horns").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("male_horns").ifPresent(b -> b.setHidden(true));
            }

            if(entity.isFemale()) {
                model.getBone("female_horns").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("female_horns").ifPresent(b -> b.setHidden(true));
            }

            if (entity.isSheared()) {
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
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}


