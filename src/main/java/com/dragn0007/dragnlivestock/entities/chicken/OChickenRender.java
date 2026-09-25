package com.dragn0007.dragnlivestock.entities.chicken;

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

public class OChickenRender extends GeoEntityRenderer<OChicken> {

    public OChickenRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OChickenModel());
        this.addRenderLayer(new OChickenRenderLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, OChicken entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Optional<GeoBone> body = this.getGeoModel().getBone("body");
        Optional<GeoBone> tail = this.getGeoModel().getBone("tail");
        Optional<GeoBone> tail2 = this.getGeoModel().getBone("tail2");
        Optional<GeoBone> gizzard = this.getGeoModel().getBone("gizzard");
        Optional<GeoBone> neck_tag = this.getGeoModel().getBone("neck_tag");
        Optional<GeoBone> comb = this.getGeoModel().getBone("comb");

        //culls unnecessary bones at a certain distance to improve performance
        Player player = Minecraft.getInstance().player;
        double distanceSq = this.animatable.distanceToSqr(player);
        boolean atCullDistance = distanceSq > LivestockOverhaulClientConfig.CULL_CUBES_DISTANCE.get();
        if (player != null) {
            if (LivestockOverhaulClientConfig.CULL_HIDDEN.get())
                if (body.isPresent()) {body.ifPresent(b -> b.setHidden(LOUtils.entityIsHidden(animatable)));}
            if (tail.isPresent()) {tail.ifPresent(b -> b.setHidden(atCullDistance));}
            if (gizzard.isPresent()) {gizzard.ifPresent(b -> b.setHidden(atCullDistance));}
            if (neck_tag.isPresent()) {neck_tag.ifPresent(b -> b.setHidden(atCullDistance));}
            if (comb.isPresent()) {comb.ifPresent(b -> b.setHidden(atCullDistance));}
        }
        if (atCullDistance) return;

        if (!entity.isBaby()) {
            poseStack.scale(1F, 1F, 1F);
            if (entity.isMale()) {
                tail2.ifPresent(b -> b.setHidden(false));
                comb.ifPresent(b -> b.setHidden(false));
                gizzard.ifPresent(b -> b.setScaleX(1.0F));
                gizzard.ifPresent(b -> b.setScaleY(1.0F));
                gizzard.ifPresent(b -> b.setScaleZ(1.0F));
            } else {
                tail2.ifPresent(b -> b.setHidden(true));
                comb.ifPresent(b -> b.setHidden(true));
                gizzard.ifPresent(b -> b.setScaleX(0.6F));
                gizzard.ifPresent(b -> b.setScaleY(0.6F));
                gizzard.ifPresent(b -> b.setScaleZ(0.6F));
            }
        } else if (entity.isBaby()) {
            tail2.ifPresent(b -> b.setHidden(true));
            comb.ifPresent(b -> b.setHidden(true));
            gizzard.ifPresent(b -> b.setScaleX(0.2F));
            gizzard.ifPresent(b -> b.setScaleY(0.2F));
            gizzard.ifPresent(b -> b.setScaleZ(0.2F));
            poseStack.scale(0.5F, 0.5F, 0.5F);
        }

        if (entity.isTagged()) {
            neck_tag.ifPresent(b -> b.setHidden(false));
        } else {
            neck_tag.ifPresent(b -> b.setHidden(true));
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}


