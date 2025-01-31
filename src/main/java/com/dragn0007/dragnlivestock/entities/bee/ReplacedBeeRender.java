package com.dragn0007.dragnlivestock.entities.bee;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.Level;
import software.bernie.example.client.model.entity.ReplacedCreeperModel;
import software.bernie.example.entity.ReplacedCreeperEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoReplacedEntityRenderer;

public class ReplacedBeeRender extends GeoReplacedEntityRenderer<Bee, ReplacedBee> {

    public ReplacedBeeRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ReplacedBeeModel(), new ReplacedBee());
    }

    @Override
    public void preRender(PoseStack poseStack, ReplacedBee animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);

//        if (animatable.isBaby()) {
//            poseStack.scale(0.5F, 0.5F, 0.5F);
//        } else {
//            poseStack.scale(1F, 1F, 1F);
//        }
//
//        if(!animatable.isBaby()) {
//
//            if (animatable.getVariant() == 0) {
//                poseStack.scale(1F, 1F, 1F);
//            }
//
//            if (animatable.getVariant() == 1) {
//                poseStack.scale(0.6F, 0.6F, 0.6F);
//            }
//
//            if (animatable.getVariant() == 2) {
//                poseStack.scale(1F, 1F, 1F);
//            }
//
//            if (animatable.getVariant() == 3) {
//                poseStack.scale(0.6F, 0.6F, 0.6F);
//            }
//
//            if (animatable.getVariant() == 4) {
//                poseStack.scale(0.6F, 0.6F, 0.6F);
//            }
//
//            if (animatable.getVariant() == 5) {
//                poseStack.scale(0.8F, 0.8F, 0.8F);
//            }
//
//            if (animatable.getVariant() == 6) {
//                poseStack.scale(0.6F, 0.6F, 0.6F);
//            }
//
//            if (animatable.getVariant() == 7) {
//                poseStack.scale(1.1F, 1.1F, 1.1F);
//            }
//        }

    }
}


