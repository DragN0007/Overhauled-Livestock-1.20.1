package com.dragn0007.dragnlivestock.entities.goat;

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

public class OGoatRender extends GeoEntityRenderer<OGoat> {

    public OGoatRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OGoatModel());
        this.addRenderLayer(new OGoatBodyLayer(this));
        this.addRenderLayer(new OGoatTackLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, OGoat animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Optional<GeoBone> wool_body = this.getGeoModel().getBone("wool_body");
        Optional<GeoBone> wool_neck = this.getGeoModel().getBone("wool_neck");
        Optional<GeoBone> wool_rt = this.getGeoModel().getBone("wool_rt");
        Optional<GeoBone> wool_lt = this.getGeoModel().getBone("left_thigh_wool");
        Optional<GeoBone> male_horns = this.getGeoModel().getBone("male_horns");
        Optional<GeoBone> female_horns = this.getGeoModel().getBone("female_horns");
        Optional<GeoBone> tail = this.getGeoModel().getBone("tail");
        Optional<GeoBone> saddlebags = this.getGeoModel().getBone("saddlebags");
        Optional<GeoBone> carpet = this.getGeoModel().getBone("carpet");

        //culls unnecessary bones at a certain distance to improve performance
        Player player = Minecraft.getInstance().player;
        double distanceSq = animatable.distanceToSqr(player);
        boolean atCullDistance = distanceSq > LivestockOverhaulClientConfig.CULL_CUBES_DISTANCE.get();
        if (player != null) {
            if (wool_neck.isPresent()) {wool_neck.ifPresent(b -> b.setHidden(atCullDistance));}
            if (wool_rt.isPresent()) {wool_rt.ifPresent(b -> b.setHidden(atCullDistance));}
            if (wool_lt.isPresent()) {wool_lt.ifPresent(b -> b.setHidden(atCullDistance));}
            if (male_horns.isPresent()) {male_horns.ifPresent(b -> b.setHidden(atCullDistance));}
            if (female_horns.isPresent()) {female_horns.ifPresent(b -> b.setHidden(atCullDistance));}
            if (tail.isPresent()) {tail.ifPresent(b -> b.setHidden(atCullDistance));}
            if (saddlebags.isPresent()) {saddlebags.ifPresent(b -> b.setHidden(atCullDistance));}
            if (carpet.isPresent()) {carpet.ifPresent(b -> b.setHidden(atCullDistance));}
        }
        if (atCullDistance) return;

        if(animatable.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
            male_horns.ifPresent(b -> b.setHidden(true));
            female_horns.ifPresent(b -> b.setHidden(true));
        } else {
            poseStack.scale(1F, 1F, 1F);

            if (animatable.hasChest()) {
                saddlebags.ifPresent(b -> b.setHidden(false));
            } else {
                saddlebags.ifPresent(b -> b.setHidden(true));
            }

            if (!animatable.getDecorItem().isEmpty()) {
                carpet.ifPresent(b -> b.setHidden(false));
            } else {
                carpet.ifPresent(b -> b.setHidden(true));
            }

            if(animatable.isMale()) {
                male_horns.ifPresent(b -> b.setHidden(false));
            } else {
                male_horns.ifPresent(b -> b.setHidden(true));
            }

            if(animatable.isFemale()) {
                female_horns.ifPresent(b -> b.setHidden(false));
            } else {
                female_horns.ifPresent(b -> b.setHidden(true));
            }

            if (animatable.isSheared()) {
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
        }
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

}


