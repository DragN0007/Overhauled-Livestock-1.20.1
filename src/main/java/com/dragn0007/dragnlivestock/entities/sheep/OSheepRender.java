package com.dragn0007.dragnlivestock.entities.sheep;

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

public class OSheepRender extends GeoEntityRenderer<OSheep> {

    public OSheepRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OSheepModel());
        this.addRenderLayer(new OSheepRenderLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, OSheep entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Optional<GeoBone> body = this.getGeoModel().getBone("body");
        Optional<GeoBone> wool_body = this.getGeoModel().getBone("wool_body");
        Optional<GeoBone> wool_neck = this.getGeoModel().getBone("wool_neck");
        Optional<GeoBone> wool_rt = this.getGeoModel().getBone("right_thigh_wool");
        Optional<GeoBone> wool_lt = this.getGeoModel().getBone("left_thigh_wool");
        Optional<GeoBone> horns = this.getGeoModel().getBone("horns");
        Optional<GeoBone> gulf_coast_horns = this.getGeoModel().getBone("gulf_coast_horns");
        Optional<GeoBone> norfolk_horns = this.getGeoModel().getBone("norfolk_horns");
        Optional<GeoBone> dorset_horns = this.getGeoModel().getBone("dorset_horns");
        Optional<GeoBone> jacob_horns = this.getGeoModel().getBone("jacob_horns");
        Optional<GeoBone> racka_horns = this.getGeoModel().getBone("racka_horns");
        Optional<GeoBone> mane = this.getGeoModel().getBone("mane");
        Optional<GeoBone> tail = this.getGeoModel().getBone("tail");
        Optional<GeoBone> tail_double = this.getGeoModel().getBone("tail2");

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
            if (mane.isPresent()) {mane.ifPresent(b -> b.setHidden(atCullDistance));}
            if (tail.isPresent()) {tail.ifPresent(b -> b.setHidden(atCullDistance));}
            if (tail_double.isPresent()) {tail_double.ifPresent(b -> b.setHidden(atCullDistance));}
        }
        if (atCullDistance) return;
        
        if (entity.getBreed() == 6) {
            if (entity.isFemale()) {
                mane.ifPresent(b -> b.setHidden(true));
            } else if (entity.isMale() && !entity.isBaby()) {
                mane.ifPresent(b -> b.setHidden(false));
            }
            tail.ifPresent(b -> b.setScaleY(2F));
            tail.ifPresent(b -> b.setScaleX(1.0F));
            tail.ifPresent(b -> b.setScaleZ(1.0F));
        } else {
            mane.ifPresent(b -> b.setHidden(true));
            tail.ifPresent(b -> b.setScaleY(1.0F));
            tail.ifPresent(b -> b.setScaleX(1.0F));
            tail.ifPresent(b -> b.setScaleZ(1.0F));
        }

        if (entity.getBreed() == 8) {
            tail_double.ifPresent(b -> b.setHidden(false));
        } else {
            tail_double.ifPresent(b -> b.setHidden(true));
        }

        if (entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
            gulf_coast_horns.ifPresent(b -> b.setHidden(true));
            norfolk_horns.ifPresent(b -> b.setHidden(true));
            dorset_horns.ifPresent(b -> b.setHidden(true));
            jacob_horns.ifPresent(b -> b.setHidden(true));
            racka_horns.ifPresent(b -> b.setHidden(true));

        } else {
            poseStack.scale(1.0F, 1.0F, 1.0F);

            if (!entity.isBaby() && entity.isSheared()) {
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

            if (entity.getHornVariant() == 0) {
                horns.ifPresent(b -> b.setHidden(true));
            } else if (entity.getHornVariant() == 1) {
                gulf_coast_horns.ifPresent(b -> b.setHidden(false));
                norfolk_horns.ifPresent(b -> b.setHidden(true));
                dorset_horns.ifPresent(b -> b.setHidden(true));
                jacob_horns.ifPresent(b -> b.setHidden(true));
                racka_horns.ifPresent(b -> b.setHidden(true));
            } else if (entity.getHornVariant() == 2) {
                gulf_coast_horns.ifPresent(b -> b.setHidden(true));
                norfolk_horns.ifPresent(b -> b.setHidden(false));
                dorset_horns.ifPresent(b -> b.setHidden(true));
                jacob_horns.ifPresent(b -> b.setHidden(true));
                racka_horns.ifPresent(b -> b.setHidden(true));
            } else if (entity.getHornVariant() == 3) {
                gulf_coast_horns.ifPresent(b -> b.setHidden(true));
                norfolk_horns.ifPresent(b -> b.setHidden(true));
                dorset_horns.ifPresent(b -> b.setHidden(false));
                jacob_horns.ifPresent(b -> b.setHidden(true));
                racka_horns.ifPresent(b -> b.setHidden(true));
            } else if (entity.getHornVariant() == 4) {
                gulf_coast_horns.ifPresent(b -> b.setHidden(true));
                norfolk_horns.ifPresent(b -> b.setHidden(true));
                dorset_horns.ifPresent(b -> b.setHidden(true));
                jacob_horns.ifPresent(b -> b.setHidden(false));
                racka_horns.ifPresent(b -> b.setHidden(true));
            } else if (entity.getHornVariant() == 5) {
                gulf_coast_horns.ifPresent(b -> b.setHidden(true));
                norfolk_horns.ifPresent(b -> b.setHidden(true));
                dorset_horns.ifPresent(b -> b.setHidden(true));
                jacob_horns.ifPresent(b -> b.setHidden(true));
                racka_horns.ifPresent(b -> b.setHidden(false));
            }

            if (entity.getBreed() == 2 || entity.getBreed() == 4) {
                wool_body.ifPresent(b -> b.setScaleY(1.1F));
                wool_body.ifPresent(b -> b.setScaleX(1.1F));
                wool_body.ifPresent(b -> b.setScaleZ(1.1F));
                wool_neck.ifPresent(b -> b.setScaleY(1.1F));
                wool_neck.ifPresent(b -> b.setScaleX(1.1F));
                wool_neck.ifPresent(b -> b.setScaleZ(1.1F));
                wool_rt.ifPresent(b -> b.setScaleY(1.1F));
                wool_rt.ifPresent(b -> b.setScaleX(1.1F));
                wool_rt.ifPresent(b -> b.setScaleZ(1.1F));
                wool_lt.ifPresent(b -> b.setScaleY(1.1F));
                wool_lt.ifPresent(b -> b.setScaleX(1.1F));
                wool_lt.ifPresent(b -> b.setScaleZ(1.1F));
                tail.ifPresent(b -> b.setScaleY(1.1F));
                tail.ifPresent(b -> b.setScaleX(1.1F));
                tail.ifPresent(b -> b.setScaleZ(1.1F));
            } else if (!(entity.getBreed() == 6)) {
                wool_body.ifPresent(b -> b.setScaleY(1.0F));
                wool_body.ifPresent(b -> b.setScaleX(1.0F));
                wool_body.ifPresent(b -> b.setScaleZ(1.0F));
                wool_neck.ifPresent(b -> b.setScaleY(1.0F));
                wool_neck.ifPresent(b -> b.setScaleX(1.0F));
                wool_neck.ifPresent(b -> b.setScaleZ(1.0F));
                wool_rt.ifPresent(b -> b.setScaleY(1.0F));
                wool_rt.ifPresent(b -> b.setScaleX(1.0F));
                wool_rt.ifPresent(b -> b.setScaleZ(1.0F));
                wool_lt.ifPresent(b -> b.setScaleY(1.0F));
                wool_lt.ifPresent(b -> b.setScaleX(1.0F));
                wool_lt.ifPresent(b -> b.setScaleZ(1.0F));
                tail.ifPresent(b -> b.setScaleY(1.0F));
                tail.ifPresent(b -> b.setScaleX(1.0F));
                tail.ifPresent(b -> b.setScaleZ(1.0F));
            }

        }

        super.preRender(poseStack, this.animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

}


