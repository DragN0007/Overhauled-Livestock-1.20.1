package com.dragn0007.dragnlivestock.entities.camel;

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

public class OCamelRender extends GeoEntityRenderer<OCamel> {

    public OCamelRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OCamelModel());
        this.addRenderLayer(new OCamelBodyLayer(this));
        this.addRenderLayer(new OCamelTackLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, OCamel entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Optional<GeoBone> body = this.getGeoModel().getBone("body");
        Optional<GeoBone> saddlebags = this.getGeoModel().getBone("saddlebags");
        Optional<GeoBone> halter = this.getGeoModel().getBone("halter");
        Optional<GeoBone> tail = this.getGeoModel().getBone("tail");
        Optional<GeoBone> carpet = this.getGeoModel().getBone("carpet");
        Optional<GeoBone> carpet2 = this.getGeoModel().getBone("carpet2");

        //culls unnecessary bones at a certain distance to improve performance
        Player player = Minecraft.getInstance().player;
        double distanceSq = animatable.distanceToSqr(player);
        boolean atCullDistance = distanceSq > LivestockOverhaulClientConfig.CULL_CUBES_DISTANCE.get();
        if (player != null) {
            if (LivestockOverhaulClientConfig.CULL_HIDDEN.get())
                if (body.isPresent()) {body.ifPresent(b -> b.setHidden(LOUtils.entityIsHidden(animatable)));}
            if (saddlebags.isPresent()) {saddlebags.ifPresent(b -> b.setHidden(atCullDistance));}
            if (halter.isPresent()) {halter.ifPresent(b -> b.setHidden(atCullDistance));}
            if (tail.isPresent()) {tail.ifPresent(b -> b.setHidden(atCullDistance));}
            if (carpet.isPresent()) {carpet.ifPresent(b -> b.setHidden(atCullDistance));}
            if (carpet2.isPresent()) {carpet2.ifPresent(b -> b.setHidden(atCullDistance));}
        }
        if (atCullDistance) return;

        if (!entity.isBaby()) {
            if (entity.hasChest()) {
                saddlebags.ifPresent(b -> b.setHidden(false));
            } else {
                saddlebags.ifPresent(b -> b.setHidden(true));
            }

            if (entity.isSaddled()) {
                halter.ifPresent(b -> b.setHidden(false));
            } else {
                halter.ifPresent(b -> b.setHidden(true));
            }
        }

        if (entity.isBaby()) {
            halter.ifPresent(b -> b.setHidden(true));
            saddlebags.ifPresent(b -> b.setHidden(true));
            carpet.ifPresent(b -> b.setHidden(true));
            carpet2.ifPresent(b -> b.setHidden(true));
            poseStack.scale(0.5F, 0.5F, 0.5F);
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}


