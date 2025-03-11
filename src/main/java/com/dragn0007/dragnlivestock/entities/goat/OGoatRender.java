package com.dragn0007.dragnlivestock.entities.goat;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OGoatRender extends GeoEntityRenderer<OGoat> {

    public OGoatRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OGoatModel());
        this.addRenderLayer(new OGoatCarpetLayer(this));
        this.addRenderLayer(new OGoatBrandTagLayer(this));
    }

    @Override
    public void render(OGoat entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if(entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
            model.getBone("Horns1").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns2").ifPresent(b -> b.setHidden(true));
        } else {
            poseStack.scale(1F, 1F, 1F);
            model.getBone("Horns1").ifPresent(b -> b.setHidden(false));
            model.getBone("Horns2").ifPresent(b -> b.setHidden(false));
        }

        if (!entity.isBaby()) {
            if (entity.hasChest()) {
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
            }
        }

        if(entity.isMale() || entity.isBaby()) {
            model.getBone("Horns2").ifPresent(b -> b.setHidden(true));
        } else {
            model.getBone("Horns2").ifPresent(b -> b.setHidden(false));
        }

        if(entity.isFemale() || entity.isBaby()) {
            model.getBone("Horns1").ifPresent(b -> b.setHidden(true));
        } else {
            model.getBone("Horns1").ifPresent(b -> b.setHidden(false));
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}


