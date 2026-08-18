package com.dragn0007.dragnlivestock.entities.donkey;

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

public class ODonkeyRender extends GeoEntityRenderer<ODonkey> {

    public ODonkeyRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ODonkeyModel());
        this.addRenderLayer(new ODonkeyBodyLayer(this));
        this.addRenderLayer(new ODonkeyTackLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, ODonkey animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
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
        Optional<GeoBone> tail = this.getGeoModel().getBone("tail");

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

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

}


