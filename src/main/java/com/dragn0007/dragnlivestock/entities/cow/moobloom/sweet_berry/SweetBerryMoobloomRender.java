package com.dragn0007.dragnlivestock.entities.cow.moobloom.sweet_berry;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SweetBerryMoobloomRender extends GeoEntityRenderer<SweetBerryMoobloom> {

    public SweetBerryMoobloomRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SweetBerryMoobloomModel());
        this.addRenderLayer(new SweetBerryMoobloomMarkingLayer(this));
        this.addRenderLayer(new SweetBerryMoobloomHornLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, SweetBerryMoobloom entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        if(entity.isBaby()) {
            model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
            model.getBone("halter").ifPresent(b -> b.setHidden(true));
            model.getBone("utters").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns1").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns2").ifPresent(b -> b.setHidden(true));
            model.getBone("Horns3").ifPresent(b -> b.setHidden(true));
        } else {
            model.getBone("saddlebags").ifPresent(b -> b.setHidden(false));
            model.getBone("halter").ifPresent(b -> b.setHidden(false));
            model.getBone("utters").ifPresent(b -> b.setHidden(false));
            model.getBone("Horns1").ifPresent(b -> b.setHidden(false));
            model.getBone("Horns2").ifPresent(b -> b.setHidden(false));
            model.getBone("Horns3").ifPresent(b -> b.setHidden(false));
        }

        if(entity.isPlantsSheared()) {
            model.getBone("plant").ifPresent(b -> b.setHidden(true));
            model.getBone("plant2").ifPresent(b -> b.setHidden(true));
        } else {
            model.getBone("plant").ifPresent(b -> b.setHidden(false));
            model.getBone("plant2").ifPresent(b -> b.setHidden(true));
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}


