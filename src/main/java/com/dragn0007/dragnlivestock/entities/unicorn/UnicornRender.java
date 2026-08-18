package com.dragn0007.dragnlivestock.entities.unicorn;

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

public class UnicornRender extends GeoEntityRenderer<Unicorn> {

    public UnicornRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new UnicornModel());
        this.addRenderLayer(new UnicornBodyLayer(this));
        this.addRenderLayer(new UnicornTackLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, Unicorn animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Optional<GeoBone> body = this.getGeoModel().getBone("body");
        Optional<GeoBone> body_armor = this.getGeoModel().getBone("body_armor");
        Optional<GeoBone> neck_armor = this.getGeoModel().getBone("neck_armor");
        Optional<GeoBone> wagon_harness = this.getGeoModel().getBone("wagon_harness");
        Optional<GeoBone> saddlebags = this.getGeoModel().getBone("saddlebags");
        Optional<GeoBone> saddle = this.getGeoModel().getBone("saddle");
        Optional<GeoBone> reins = this.getGeoModel().getBone("saddle2");
        Optional<GeoBone> shoe_fr = this.getGeoModel().getBone("front_right_shoe");
        Optional<GeoBone> shoe_fl = this.getGeoModel().getBone("front_left_shoe");
        Optional<GeoBone> shoe_br = this.getGeoModel().getBone("back_right_shoe");
        Optional<GeoBone> shoe_bl = this.getGeoModel().getBone("back_left_shoe");
        Optional<GeoBone> mane = this.getGeoModel().getBone("mane");
        Optional<GeoBone> mane_roached = this.getGeoModel().getBone("roached");
        Optional<GeoBone> mane_buttons = this.getGeoModel().getBone("buttons");
        Optional<GeoBone> mane_short = this.getGeoModel().getBone("short");
        Optional<GeoBone> mane_long = this.getGeoModel().getBone("long");
        Optional<GeoBone> tail = this.getGeoModel().getBone("tail");
        Optional<GeoBone> tail_end = this.getGeoModel().getBone("tail_2");
        Optional<GeoBone> feathering_fr = this.getGeoModel().getBone("front_right_feathering");
        Optional<GeoBone> feathering_fl = this.getGeoModel().getBone("front_left_feathering");
        Optional<GeoBone> feathering_br = this.getGeoModel().getBone("back_right_feathering");
        Optional<GeoBone> feathering_bl = this.getGeoModel().getBone("back_left_feathering");

        //culls unnecessary bones at a certain distance to improve performance
        Player player = Minecraft.getInstance().player;
        double distanceSq = animatable.distanceToSqr(player);
        boolean atCullDistance = distanceSq > LivestockOverhaulClientConfig.CULL_CUBES_DISTANCE.get();
        if (player != null) {
            if (LivestockOverhaulClientConfig.CULL_HIDDEN.get())
                if (body.isPresent()) {body.ifPresent(b -> b.setHidden(LOUtils.entityIsHidden(animatable)));}
            if (saddle.isPresent()) {saddle.ifPresent(b -> b.setHidden(atCullDistance));}
            if (reins.isPresent()) {reins.ifPresent(b -> b.setHidden(atCullDistance));}
            if (saddlebags.isPresent()) {saddlebags.ifPresent(b -> b.setHidden(atCullDistance));}
            if (wagon_harness.isPresent()) {wagon_harness.ifPresent(b -> b.setHidden(atCullDistance));}
            if (body_armor.isPresent()) {body_armor.ifPresent(b -> b.setHidden(atCullDistance));}
            if (neck_armor.isPresent()) {neck_armor.ifPresent(b -> b.setHidden(atCullDistance));}
            if (mane.isPresent()) {mane.ifPresent(b -> b.setHidden(atCullDistance));}
            if (tail.isPresent()) {tail.ifPresent(b -> b.setHidden(atCullDistance));}
            if (shoe_fr.isPresent()) {shoe_fr.ifPresent(b -> b.setHidden(atCullDistance));}
            if (shoe_fl.isPresent()) {shoe_fl.ifPresent(b -> b.setHidden(atCullDistance));}
            if (shoe_br.isPresent()) {shoe_br.ifPresent(b -> b.setHidden(atCullDistance));}
            if (shoe_bl.isPresent()) {shoe_bl.ifPresent(b -> b.setHidden(atCullDistance));}
            if (feathering_fr.isPresent()) {feathering_fr.ifPresent(b -> b.setHidden(atCullDistance));}
            if (feathering_fl.isPresent()) {feathering_fl.ifPresent(b -> b.setHidden(atCullDistance));}
            if (feathering_br.isPresent()) {feathering_br.ifPresent(b -> b.setHidden(atCullDistance));}
            if (feathering_bl.isPresent()) {feathering_bl.ifPresent(b -> b.setHidden(atCullDistance));}
        }
        if (atCullDistance) return;

        if (!animatable.isBaby()) {
            if (animatable.hasChest()) {
                saddlebags.ifPresent(b -> b.setHidden(false));
            } else {
                saddlebags.ifPresent(b -> b.setHidden(true));
            }

            if (animatable.isSaddled()) {
                saddle.ifPresent(b -> b.setHidden(false));
                reins.ifPresent(b -> b.setHidden(false));
                shoe_fr.ifPresent(b -> b.setHidden(false));
                shoe_fl.ifPresent(b -> b.setHidden(false));
                shoe_br.ifPresent(b -> b.setHidden(false));
                shoe_bl.ifPresent(b -> b.setHidden(false));
            } else {
                saddle.ifPresent(b -> b.setHidden(true));
                reins.ifPresent(b -> b.setHidden(true));
                shoe_fr.ifPresent(b -> b.setHidden(true));
                shoe_fl.ifPresent(b -> b.setHidden(true));
                shoe_br.ifPresent(b -> b.setHidden(true));
                shoe_bl.ifPresent(b -> b.setHidden(true));
            }

            if (animatable.isWearingPullingHarness()) {
                wagon_harness.ifPresent(b -> b.setHidden(false));
            } else {
                wagon_harness.ifPresent(b -> b.setHidden(true));
            }

            if (!animatable.getArmor().isEmpty() || !animatable.getDecorItem().isEmpty()) {
                body_armor.ifPresent(b -> b.setHidden(false));
                neck_armor.ifPresent(b -> b.setHidden(false));
            } else {
                body_armor.ifPresent(b -> b.setHidden(true));
                neck_armor.ifPresent(b -> b.setHidden(true));
            }
        } else {
            saddlebags.ifPresent(b -> b.setHidden(true));
            saddle.ifPresent(b -> b.setHidden(true));
            reins.ifPresent(b -> b.setHidden(true));
            wagon_harness.ifPresent(b -> b.setHidden(true));
            body_armor.ifPresent(b -> b.setHidden(true));
            neck_armor.ifPresent(b -> b.setHidden(true));
        }

        if (LivestockOverhaulClientConfig.SIMPLE_MODELS.get() && animatable.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        }

        if (!LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
            if (animatable.getManeType() == 0) {
                mane_roached.ifPresent(b -> b.setHidden(true));
                mane_short.ifPresent(b -> b.setHidden(true));
                mane_buttons.ifPresent(b -> b.setHidden(false));
                mane_long.ifPresent(b -> b.setHidden(true));
            } else if (animatable.getManeType() == 1) {
                mane_roached.ifPresent(b -> b.setHidden(true));
                mane_short.ifPresent(b -> b.setHidden(true));
                mane_buttons.ifPresent(b -> b.setHidden(true));
                mane_long.ifPresent(b -> b.setHidden(false));
            } else if (animatable.getManeType() == 2) {
                mane_roached.ifPresent(b -> b.setHidden(false));
                mane_short.ifPresent(b -> b.setHidden(true));
                mane_buttons.ifPresent(b -> b.setHidden(true));
                mane_long.ifPresent(b -> b.setHidden(true));
                mane.ifPresent(b -> b.setScaleY(1.0F));
            } else if (animatable.getManeType() == 3) {
                mane_roached.ifPresent(b -> b.setHidden(true));
                mane_short.ifPresent(b -> b.setHidden(false));
                mane_buttons.ifPresent(b -> b.setHidden(true));
                mane_long.ifPresent(b -> b.setHidden(true));
            } else if (animatable.getManeType() == 4) {
                mane.ifPresent(b -> b.setHidden(true));
            }

            if (animatable.getTailType() == 0) {
                tail.ifPresent(b -> b.setScaleY(0.9F));
                tail.ifPresent(b -> b.setScaleX(0.7F));
                tail.ifPresent(b -> b.setScaleZ(0.7F));
                tail_end.ifPresent(b -> b.setHidden(true));
            } else if (animatable.getTailType() == 1) {
                tail.ifPresent(b -> b.setScaleX(1.0F));
                tail.ifPresent(b -> b.setScaleY(1.3F));
                tail.ifPresent(b -> b.setScaleZ(1.0F));
                tail_end.ifPresent(b -> b.setHidden(false));
            } else if (animatable.getTailType() == 2) {
                tail.ifPresent(b -> b.setScaleX(1.0F));
                tail.ifPresent(b -> b.setScaleY(1.0F));
                tail.ifPresent(b -> b.setScaleZ(1.0F));
                tail_end.ifPresent(b -> b.setHidden(false));
            } else if (animatable.getTailType() == 3) {
                tail.ifPresent(b -> b.setScaleX(1.0F));
                tail.ifPresent(b -> b.setScaleY(0.6F));
                tail.ifPresent(b -> b.setScaleZ(1.0F));
                tail_end.ifPresent(b -> b.setHidden(false));
            } else if (animatable.getTailType() == 4) {
                tail.ifPresent(b -> b.setScaleY(0.7F));
                tail.ifPresent(b -> b.setScaleX(1.1F));
                tail.ifPresent(b -> b.setScaleZ(1.0F));
                tail_end.ifPresent(b -> b.setHidden(true));
            }

            if (animatable.getFeathering() == 0) {
                feathering_fr.ifPresent(b -> b.setHidden(true));
                feathering_fl.ifPresent(b -> b.setHidden(true));
                feathering_br.ifPresent(b -> b.setHidden(true));
                feathering_bl.ifPresent(b -> b.setHidden(true));
            } else if (animatable.getFeathering() == 1) {
                feathering_fr.ifPresent(b -> b.setHidden(false));
                feathering_fl.ifPresent(b -> b.setHidden(false));
                feathering_br.ifPresent(b -> b.setHidden(false));
                feathering_bl.ifPresent(b -> b.setHidden(false));
                feathering_fr.ifPresent(b -> b.setScaleY(0.6F));
                feathering_fl.ifPresent(b -> b.setScaleY(0.6F));
                feathering_br.ifPresent(b -> b.setScaleY(0.6F));
                feathering_bl.ifPresent(b -> b.setScaleY(0.6F));
                feathering_fr.ifPresent(b -> b.setPosY(-3.5F));
                feathering_fl.ifPresent(b -> b.setPosY(-3.5F));
                feathering_br.ifPresent(b -> b.setPosY(-3.5F));
                feathering_bl.ifPresent(b -> b.setPosY(-3.5F));
                feathering_fr.ifPresent(b -> b.setPosZ(-0.8F));
                feathering_fl.ifPresent(b -> b.setPosZ(-0.8F));
            } else if (animatable.getFeathering() == 2) {
                feathering_fr.ifPresent(b -> b.setHidden(false));
                feathering_fl.ifPresent(b -> b.setHidden(false));
                feathering_br.ifPresent(b -> b.setHidden(false));
                feathering_bl.ifPresent(b -> b.setHidden(false));
                feathering_fr.ifPresent(b -> b.setScaleY(1F));
                feathering_fl.ifPresent(b -> b.setScaleY(1F));
                feathering_br.ifPresent(b -> b.setScaleY(1F));
                feathering_bl.ifPresent(b -> b.setScaleY(1F));
            }
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}


