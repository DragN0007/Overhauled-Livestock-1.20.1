package com.dragn0007.dragnlivestock.entities.rabbit;

import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import java.util.Optional;

public class ORabbitRender extends GeoEntityRenderer<ORabbit> {

    public ORabbitRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ORabbitModel());
        this.addRenderLayer(new ORabbitMarkingLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, ORabbit animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Optional<GeoBone> right_antler = this.getGeoModel().getBone("right_antler");
        Optional<GeoBone> left_antler = this.getGeoModel().getBone("left_antler");
        Optional<GeoBone> wool = this.getGeoModel().getBone("wool");
        Optional<GeoBone> dewlap = this.getGeoModel().getBone("dewlap");
        Optional<GeoBone> tail = this.getGeoModel().getBone("tail");

        //culls unnecessary bones at a certain distance to improve performance
        Player player = Minecraft.getInstance().player;
        double distanceSq = animatable.distanceToSqr(player);
        boolean atCullDistance = distanceSq > LivestockOverhaulClientConfig.CULL_CUBES_DISTANCE.get();
        if (player != null) {
            if (right_antler.isPresent()) {right_antler.ifPresent(b -> b.setHidden(atCullDistance));}
            if (left_antler.isPresent()) {left_antler.ifPresent(b -> b.setHidden(atCullDistance));}
            if (wool.isPresent()) {wool.ifPresent(b -> b.setHidden(atCullDistance));}
            if (dewlap.isPresent()) {dewlap.ifPresent(b -> b.setHidden(atCullDistance));}
            if (tail.isPresent()) {tail.ifPresent(b -> b.setHidden(atCullDistance));}
        }
        if (atCullDistance) return;
        
        if(animatable.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
            if (animatable.getBreed() == 9) {
                right_antler.ifPresent(b -> b.setHidden(true));
                left_antler.ifPresent(b -> b.setHidden(true));
            }
        } else {
            poseStack.scale(1F, 1F, 1F);

            if (!animatable.isBaby() && animatable.isSheared() && animatable.getBreed() == 4) {
                wool.ifPresent(b -> b.setHidden(true));
            } else {
                wool.ifPresent(b -> b.setHidden(false));
            }

            if (animatable.getBreed() == 9) {
                right_antler.ifPresent(b -> b.setHidden(false));
                left_antler.ifPresent(b -> b.setHidden(false));
                if (animatable.isFemale()) {
                    right_antler.ifPresent(b -> b.setScaleX(0.8F));
                    right_antler.ifPresent(b -> b.setScaleY(0.8F));
                    right_antler.ifPresent(b -> b.setScaleZ(0.8F));
                    left_antler.ifPresent(b -> b.setScaleX(0.8F));
                    left_antler.ifPresent(b -> b.setScaleY(0.8F));
                    left_antler.ifPresent(b -> b.setScaleZ(0.8F));
                } else if (animatable.isMale()) {
                    right_antler.ifPresent(b -> b.setScaleX(1.0F));
                    right_antler.ifPresent(b -> b.setScaleY(1.0F));
                    right_antler.ifPresent(b -> b.setScaleZ(1.0F));
                    left_antler.ifPresent(b -> b.setScaleX(1.0F));
                    left_antler.ifPresent(b -> b.setScaleY(1.0F));
                    left_antler.ifPresent(b -> b.setScaleZ(1.0F));
                }
            }

            if (animatable.getDewlap() == 0) {
                dewlap.ifPresent(b -> b.setHidden(true));
            } else if (animatable.getDewlap() == 1) {
                dewlap.ifPresent(b -> b.setScaleX(0.8F));
                dewlap.ifPresent(b -> b.setScaleY(0.8F));
                dewlap.ifPresent(b -> b.setScaleZ(0.8F));
                dewlap.ifPresent(b -> b.setHidden(false));
            } else {
                dewlap.ifPresent(b -> b.setScaleX(1.0F));
                dewlap.ifPresent(b -> b.setScaleY(1.0F));
                dewlap.ifPresent(b -> b.setScaleZ(1.0F));
                dewlap.ifPresent(b -> b.setHidden(false));
            }
        }

        super.preRender(poseStack, this.animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

}


