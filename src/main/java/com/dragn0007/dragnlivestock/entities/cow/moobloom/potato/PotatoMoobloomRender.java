package com.dragn0007.dragnlivestock.entities.cow.moobloom.potato;

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

public class PotatoMoobloomRender extends GeoEntityRenderer<PotatoMoobloom> {

    public PotatoMoobloomRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PotatoMoobloomModel());
        this.addRenderLayer(new PotatoMoobloomBodyLayer(this));
        this.addRenderLayer(new PotatoMoobloomTackLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, PotatoMoobloom animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Optional<GeoBone> body = this.getGeoModel().getBone("body");
        Optional<GeoBone> body_fluff = this.getGeoModel().getBone("body_fluff");
        Optional<GeoBone> neck_fluff = this.getGeoModel().getBone("neck_fluff");
        Optional<GeoBone> head_fluff = this.getGeoModel().getBone("head_fluff");
        Optional<GeoBone> horn_connection = this.getGeoModel().getBone("horn_connection");
        Optional<GeoBone> horns_1 = this.getGeoModel().getBone("horns_1");
        Optional<GeoBone> horns_2 = this.getGeoModel().getBone("horns_2");
        Optional<GeoBone> horns_3 = this.getGeoModel().getBone("horns_3");
        Optional<GeoBone> horns_4 = this.getGeoModel().getBone("horns_4");
        Optional<GeoBone> horns_5 = this.getGeoModel().getBone("horns_5");
        Optional<GeoBone> horns_6 = this.getGeoModel().getBone("horns_6");
        Optional<GeoBone> horns_7 = this.getGeoModel().getBone("horns_7");
        Optional<GeoBone> horns_8 = this.getGeoModel().getBone("horns_8");
        Optional<GeoBone> horns_9 = this.getGeoModel().getBone("horns_9");
        Optional<GeoBone> horns_10 = this.getGeoModel().getBone("horns_10");
        Optional<GeoBone> tail = this.getGeoModel().getBone("tail");
        Optional<GeoBone> crest = this.getGeoModel().getBone("crest");
        Optional<GeoBone> hump = this.getGeoModel().getBone("hump");
        Optional<GeoBone> saddlebags = this.getGeoModel().getBone("saddlebags");
        Optional<GeoBone> udders = this.getGeoModel().getBone("utters");
        Optional<GeoBone> wagon_harness = this.getGeoModel().getBone("wagon_harness");
        Optional<GeoBone> plant = this.getGeoModel().getBone("plant");

        //culls unnecessary bones at a certain distance to improve performance
        Player player = Minecraft.getInstance().player;
        double distanceSq = animatable.distanceToSqr(player);
        boolean atCullDistance = distanceSq > LivestockOverhaulClientConfig.CULL_CUBES_DISTANCE.get();
        if (player != null) {
            if (LivestockOverhaulClientConfig.CULL_HIDDEN.get())
                if (body.isPresent()) {body.ifPresent(b -> b.setHidden(LOUtils.entityIsHidden(animatable)));}
            if (body_fluff.isPresent()) {body_fluff.ifPresent(b -> b.setHidden(atCullDistance));}
            if (neck_fluff.isPresent()) {neck_fluff.ifPresent(b -> b.setHidden(atCullDistance));}
            if (head_fluff.isPresent()) {head_fluff.ifPresent(b -> b.setHidden(atCullDistance));}
            if (horns_1.isPresent()) {horns_1.ifPresent(b -> b.setHidden(atCullDistance));}
            if (horns_2.isPresent()) {horns_2.ifPresent(b -> b.setHidden(atCullDistance));}
            if (horns_3.isPresent()) {horns_3.ifPresent(b -> b.setHidden(atCullDistance));}
            if (horns_4.isPresent()) {horns_4.ifPresent(b -> b.setHidden(atCullDistance));}
            if (horns_5.isPresent()) {horns_5.ifPresent(b -> b.setHidden(atCullDistance));}
            if (horns_6.isPresent()) {horns_6.ifPresent(b -> b.setHidden(atCullDistance));}
            if (horns_7.isPresent()) {horns_7.ifPresent(b -> b.setHidden(atCullDistance));}
            if (horns_8.isPresent()) {horns_8.ifPresent(b -> b.setHidden(atCullDistance));}
            if (horns_9.isPresent()) {horns_9.ifPresent(b -> b.setHidden(atCullDistance));}
            if (horns_10.isPresent()) {horns_10.ifPresent(b -> b.setHidden(atCullDistance));}
            if (udders.isPresent()) {udders.ifPresent(b -> b.setHidden(atCullDistance));}
            if (tail.isPresent()) {tail.ifPresent(b -> b.setHidden(atCullDistance));}
            if (saddlebags.isPresent()) {saddlebags.ifPresent(b -> b.setHidden(atCullDistance));}
            if (crest.isPresent()) {crest.ifPresent(b -> b.setHidden(atCullDistance));}
            if (hump.isPresent()) {hump.ifPresent(b -> b.setHidden(atCullDistance));}
            if (wagon_harness.isPresent()) {wagon_harness.ifPresent(b -> b.setHidden(atCullDistance));}
            if (plant.isPresent()) {plant.ifPresent(b -> b.setHidden(atCullDistance));}
        }
        if (atCullDistance) return;

        hump.ifPresent(b -> b.setHidden(true));
        body_fluff.ifPresent(b -> b.setHidden(true));
        neck_fluff.ifPresent(b -> b.setHidden(true));
        head_fluff.ifPresent(b -> b.setHidden(true));

        if (animatable.isSheared()) {
            plant.ifPresent(b -> b.setHidden(true));
        } else {
            plant.ifPresent(b -> b.setHidden(false));
        }

        horns_1.ifPresent(b -> b.setHidden(true));
        horns_2.ifPresent(b -> b.setHidden(true));
        horns_3.ifPresent(b -> b.setHidden(true));
        horns_4.ifPresent(b -> b.setHidden(true));
        horns_5.ifPresent(b -> b.setHidden(true));
        horns_6.ifPresent(b -> b.setHidden(true));
        horns_7.ifPresent(b -> b.setHidden(true));
        horns_8.ifPresent(b -> b.setHidden(true));
        horns_9.ifPresent(b -> b.setHidden(true));
        horns_10.ifPresent(b -> b.setHidden(true));

        if(animatable.isBaby()) {
            udders.ifPresent(b -> b.setHidden(true));
            horn_connection.ifPresent(b -> b.setHidden(true));
        } else {
            udders.ifPresent(b -> b.setScaleY(1.5F));
            udders.ifPresent(b -> b.setScaleX(1.5F));
            udders.ifPresent(b -> b.setScaleZ(1.5F));

            if (!(animatable.getHornVariant() == 0)) {
                horn_connection.ifPresent(b -> b.setScaleY(2.3F));
            } else {
                horn_connection.ifPresent(b -> b.setScaleY(1.0F));
            }

            if (animatable.getHornVariant() == 1) {
                horns_1.ifPresent(b -> b.setHidden(false));
            } else if (animatable.getHornVariant() == 2) {
                horns_2.ifPresent(b -> b.setHidden(false));
            } else if (animatable.getHornVariant() == 3) {
                horns_3.ifPresent(b -> b.setHidden(false));
            } else if (animatable.getHornVariant() == 4) {
                horns_4.ifPresent(b -> b.setHidden(false));
            } else if (animatable.getHornVariant() == 5) {
                horns_5.ifPresent(b -> b.setHidden(false));
            } else if (animatable.getHornVariant() == 6) {
                horns_6.ifPresent(b -> b.setHidden(false));
            } else if (animatable.getHornVariant() == 7) {
                horns_7.ifPresent(b -> b.setHidden(false));
            } else if (animatable.getHornVariant() == 8) {
                horns_8.ifPresent(b -> b.setHidden(false));
            } else if (animatable.getHornVariant() == 9) {
                horns_9.ifPresent(b -> b.setHidden(false));
            } else if (animatable.getHornVariant() == 10) {
                horns_10.ifPresent(b -> b.setHidden(false));
            }
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}


