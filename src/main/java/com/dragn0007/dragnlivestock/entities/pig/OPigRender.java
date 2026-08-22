package com.dragn0007.dragnlivestock.entities.pig;

import com.dragn0007.dragnlivestock.util.LOUtils;
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

public class OPigRender extends GeoEntityRenderer<OPig> {

    public OPigRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OPigModel());
        this.addRenderLayer(new OPigRenderLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, OPig animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Optional<GeoBone> body = this.getGeoModel().getBone("body");
        Optional<GeoBone> tusks = this.getGeoModel().getBone("tusks");
        Optional<GeoBone> belly = this.getGeoModel().getBone("belly");
        Optional<GeoBone> tail = this.getGeoModel().getBone("tail");

        //culls unnecessary bones at a certain distance to improve performance
        Player player = Minecraft.getInstance().player;
        double distanceSq = this.animatable.distanceToSqr(player);
        boolean atCullDistance = distanceSq > LivestockOverhaulClientConfig.CULL_CUBES_DISTANCE.get();
        if (player != null) {
            if (LivestockOverhaulClientConfig.CULL_HIDDEN.get())
                if (body.isPresent()) {body.ifPresent(b -> b.setHidden(LOUtils.entityIsHidden(animatable)));}
            if (tusks.isPresent()) {tusks.ifPresent(b -> b.setHidden(atCullDistance));}
            if (belly.isPresent()) {belly.ifPresent(b -> b.setHidden(atCullDistance));}
            if (tail.isPresent()) {tail.ifPresent(b -> b.setHidden(atCullDistance));}
        }
        if (atCullDistance) return;

        if(animatable.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
            model.getBone("tusks").ifPresent(b -> b.setHidden(true));
        } else {
            if (animatable.isMale()) {
                model.getBone("tusks").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("tusks").ifPresent(b -> b.setHidden(true));
            }

            if (!isReRender) {
                if (animatable.getBreed() == 0) {
                    poseStack.scale(1.1F, 1.1F, 1.1F);
                } else if (animatable.getBreed() == 1) {
                    poseStack.scale(0.9F, 0.9F, 0.9F);
                    model.getBone("belly").ifPresent(b -> b.setScaleY(2F));
                } else if (animatable.getBreed() == 2) {
                    poseStack.scale(0.9F, 0.9F, 0.9F);
                } else if (animatable.getBreed() == 3) {
                    poseStack.scale(1.0F, 1.0F, 1.0F);
                } else if (animatable.getBreed() == 4) {
                    poseStack.scale(1.2F, 1.2F, 1.2F);
                } else if (animatable.getBreed() == 5) {
                    poseStack.scale(1.0F, 1.0F, 1.0F);
                }
            }
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}


