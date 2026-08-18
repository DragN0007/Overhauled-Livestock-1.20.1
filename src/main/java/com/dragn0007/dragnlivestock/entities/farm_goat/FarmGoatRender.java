package com.dragn0007.dragnlivestock.entities.farm_goat;

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

public class FarmGoatRender extends GeoEntityRenderer<FarmGoat> {

    public FarmGoatRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FarmGoatModel());
        this.addRenderLayer(new FarmGoatBodyLayer(this));
        this.addRenderLayer(new FarmGoatTackLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, FarmGoat entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Optional<GeoBone> body = this.getGeoModel().getBone("body");
        Optional<GeoBone> wool_body = this.getGeoModel().getBone("wool_body");
        Optional<GeoBone> wool_neck = this.getGeoModel().getBone("wool_neck");
        Optional<GeoBone> wool_rt = this.getGeoModel().getBone("wool_rt");
        Optional<GeoBone> wool_lt = this.getGeoModel().getBone("left_thigh_wool");
        Optional<GeoBone> left_ear = this.getGeoModel().getBone("left_ear");
        Optional<GeoBone> right_ear = this.getGeoModel().getBone("right_ear");
        Optional<GeoBone> horns = this.getGeoModel().getBone("horns");
        Optional<GeoBone> backwards_curl_horns = this.getGeoModel().getBone("backwards_curl_horns");
        Optional<GeoBone> small_horns = this.getGeoModel().getBone("small_horns");
        Optional<GeoBone> polycerate_horns = this.getGeoModel().getBone("polycerate_horns");
        Optional<GeoBone> upwards_curl_horns = this.getGeoModel().getBone("upwards_curl_horns");
        Optional<GeoBone> corkscrew_horns = this.getGeoModel().getBone("corkscrew_horns");
        Optional<GeoBone> outward_fanning_horns = this.getGeoModel().getBone("outward_fanning_horns");
        Optional<GeoBone> tail = this.getGeoModel().getBone("tail");
        Optional<GeoBone> saddlebags = this.getGeoModel().getBone("saddlebags");
        Optional<GeoBone> udders = this.getGeoModel().getBone("utters");

        //culls unnecessary bones at a certain distance to improve performance
        Player player = Minecraft.getInstance().player;
        double distanceSq = animatable.distanceToSqr(player);
        boolean atCullDistance = distanceSq > LivestockOverhaulClientConfig.CULL_CUBES_DISTANCE.get();
        if (player != null) {
            if (LivestockOverhaulClientConfig.CULL_HIDDEN.get())
                if (body.isPresent()) {body.ifPresent(b -> b.setHidden(LOUtils.entityIsHidden(animatable)));}
            if (wool_neck.isPresent()) {wool_neck.ifPresent(b -> b.setHidden(atCullDistance));}
            if (wool_rt.isPresent()) {wool_rt.ifPresent(b -> b.setHidden(atCullDistance));}
            if (wool_lt.isPresent()) {wool_lt.ifPresent(b -> b.setHidden(atCullDistance));}
            if (horns.isPresent()) {horns.ifPresent(b -> b.setHidden(atCullDistance));}
            if (udders.isPresent()) {udders.ifPresent(b -> b.setHidden(atCullDistance));}
            if (tail.isPresent()) {tail.ifPresent(b -> b.setHidden(atCullDistance));}
            if (saddlebags.isPresent()) {saddlebags.ifPresent(b -> b.setHidden(atCullDistance));}
        }
        if (atCullDistance) return;
        
        if(entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
            horns.ifPresent(b -> b.setHidden(true));
        } else {
            poseStack.scale(1F, 1F, 1F);

            if (entity.hasChest()) {
                saddlebags.ifPresent(b -> b.setHidden(false));
            } else {
                saddlebags.ifPresent(b -> b.setHidden(true));
            }

            if (entity.isSheared() || !(entity.getBreed() == 4)) {
                wool_body.ifPresent(b -> b.setHidden(true));
                wool_neck.ifPresent(b -> b.setHidden(true));
                wool_rt.ifPresent(b -> b.setHidden(true));
                wool_lt.ifPresent(b -> b.setHidden(true));
            } else {
                wool_body.ifPresent(b -> b.setHidden(false));
                wool_neck.ifPresent(b -> b.setHidden(false));
                wool_rt.ifPresent(b -> b.setHidden(false));
                wool_lt.ifPresent(b -> b.setHidden(false));
            }

            if (entity.getBreed() == 5) {
                left_ear.ifPresent(b -> b.setHidden(true));
                right_ear.ifPresent(b -> b.setHidden(true));
            } else {
                left_ear.ifPresent(b -> b.setHidden(false));
                right_ear.ifPresent(b -> b.setHidden(false));
            }

            if (entity.wasMilked()) {
                if (entity.getBreed() == 5) {
                    udders.ifPresent(b -> b.setScaleY(1.2F));
                    udders.ifPresent(b -> b.setScaleX(1.2F));
                    udders.ifPresent(b -> b.setScaleZ(1.2F));
                } else {
                    udders.ifPresent(b -> b.setScaleY(0.8F));
                    udders.ifPresent(b -> b.setScaleX(0.8F));
                    udders.ifPresent(b -> b.setScaleZ(0.8F));
                }
            } else if (!entity.wasMilked()) {
                if (entity.getBreed() == 5) {
                    udders.ifPresent(b -> b.setScaleY(1.5F));
                    udders.ifPresent(b -> b.setScaleX(1.5F));
                    udders.ifPresent(b -> b.setScaleZ(1.5F));
                } else {
                    udders.ifPresent(b -> b.setScaleY(1.0F));
                    udders.ifPresent(b -> b.setScaleX(1.0F));
                    udders.ifPresent(b -> b.setScaleZ(1.0F));
                }
            }
        }

        if (entity.getHornVariant() == 0) {
            backwards_curl_horns.ifPresent(b -> b.setHidden(true));
            small_horns.ifPresent(b -> b.setHidden(true));
            polycerate_horns.ifPresent(b -> b.setHidden(true));
            upwards_curl_horns.ifPresent(b -> b.setHidden(true));
            corkscrew_horns.ifPresent(b -> b.setHidden(true));
            outward_fanning_horns.ifPresent(b -> b.setHidden(true));
        } else if (entity.getHornVariant() == 1) {
            backwards_curl_horns.ifPresent(b -> b.setHidden(false));
            small_horns.ifPresent(b -> b.setHidden(true));
            polycerate_horns.ifPresent(b -> b.setHidden(true));
            upwards_curl_horns.ifPresent(b -> b.setHidden(true));
            corkscrew_horns.ifPresent(b -> b.setHidden(true));
            outward_fanning_horns.ifPresent(b -> b.setHidden(true));
        } else if (entity.getHornVariant() == 2) {
            backwards_curl_horns.ifPresent(b -> b.setHidden(true));
            small_horns.ifPresent(b -> b.setHidden(false));
            polycerate_horns.ifPresent(b -> b.setHidden(true));
            upwards_curl_horns.ifPresent(b -> b.setHidden(true));
            corkscrew_horns.ifPresent(b -> b.setHidden(true));
            outward_fanning_horns.ifPresent(b -> b.setHidden(true));
        } else if (entity.getHornVariant() == 3) {
            backwards_curl_horns.ifPresent(b -> b.setHidden(true));
            small_horns.ifPresent(b -> b.setHidden(true));
            polycerate_horns.ifPresent(b -> b.setHidden(false));
            upwards_curl_horns.ifPresent(b -> b.setHidden(true));
            corkscrew_horns.ifPresent(b -> b.setHidden(true));
            outward_fanning_horns.ifPresent(b -> b.setHidden(true));
        } else if (entity.getHornVariant() == 4) {
            backwards_curl_horns.ifPresent(b -> b.setHidden(true));
            small_horns.ifPresent(b -> b.setHidden(true));
            polycerate_horns.ifPresent(b -> b.setHidden(true));
            upwards_curl_horns.ifPresent(b -> b.setHidden(false));
            corkscrew_horns.ifPresent(b -> b.setHidden(true));
            outward_fanning_horns.ifPresent(b -> b.setHidden(true));
        } else if (entity.getHornVariant() == 5) {
            backwards_curl_horns.ifPresent(b -> b.setHidden(true));
            small_horns.ifPresent(b -> b.setHidden(true));
            polycerate_horns.ifPresent(b -> b.setHidden(true));
            upwards_curl_horns.ifPresent(b -> b.setHidden(true));
            corkscrew_horns.ifPresent(b -> b.setHidden(false));
            outward_fanning_horns.ifPresent(b -> b.setHidden(true));
        } else if (entity.getHornVariant() == 6) {
            backwards_curl_horns.ifPresent(b -> b.setHidden(true));
            small_horns.ifPresent(b -> b.setHidden(true));
            polycerate_horns.ifPresent(b -> b.setHidden(true));
            upwards_curl_horns.ifPresent(b -> b.setHidden(true));
            corkscrew_horns.ifPresent(b -> b.setHidden(true));
            outward_fanning_horns.ifPresent(b -> b.setHidden(false));
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

}


