package com.dragn0007.dragnlivestock.entities.horse;

import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OHorseRender extends GeoEntityRenderer<OHorse> {

    public OHorseRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OHorseModel());
        this.addRenderLayer(new OHorseMarkingLayer(this));
        this.addRenderLayer(new OHorseCarpetLayer(this));
        this.addRenderLayer(new OHorseArmorLayer(this));
        this.addRenderLayer(new OHorseSaddleLayer(this));
        this.addRenderLayer(new OHorseShoeLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, OHorse entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        if (!entity.isBaby()) {
            if (entity.hasChest()) {
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
            }

            if (entity.isSaddled() && (LivestockOverhaulClientConfig.HORSE_SADDLE_EXTRAS.get() && LivestockOverhaulClientConfig.LEGACY_HORSE_SADDLES.get())) {
                model.getBone("saddle").ifPresent(b -> b.setHidden(false));
                model.getBone("saddle2").ifPresent(b -> b.setHidden(false));
                model.getBone("extras").ifPresent(b -> b.setHidden(false));
                model.getBone("front_right_shoe").ifPresent(b -> b.setHidden(false));
                model.getBone("front_left_shoe").ifPresent(b -> b.setHidden(false));
                model.getBone("back_right_shoe").ifPresent(b -> b.setHidden(false));
                model.getBone("back_left_shoe").ifPresent(b -> b.setHidden(false));
            } else if (entity.isSaddled() && (!LivestockOverhaulClientConfig.HORSE_SADDLE_EXTRAS.get() || !LivestockOverhaulClientConfig.LEGACY_HORSE_SADDLES.get())) {
                model.getBone("saddle").ifPresent(b -> b.setHidden(false));
                model.getBone("saddle2").ifPresent(b -> b.setHidden(false));
                model.getBone("extras").ifPresent(b -> b.setHidden(true));
                model.getBone("front_right_shoe").ifPresent(b -> b.setHidden(false));
                model.getBone("front_left_shoe").ifPresent(b -> b.setHidden(false));
                model.getBone("back_right_shoe").ifPresent(b -> b.setHidden(false));
                model.getBone("back_left_shoe").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("saddle").ifPresent(b -> b.setHidden(true));
                model.getBone("saddle2").ifPresent(b -> b.setHidden(true));
                model.getBone("extras").ifPresent(b -> b.setHidden(true));
                model.getBone("front_right_shoe").ifPresent(b -> b.setHidden(true));
                model.getBone("front_left_shoe").ifPresent(b -> b.setHidden(true));
                model.getBone("back_right_shoe").ifPresent(b -> b.setHidden(true));
                model.getBone("back_left_shoe").ifPresent(b -> b.setHidden(true));
            }

            if (entity.isWearingArmor()) {
                model.getBone("body_armor").ifPresent(b -> b.setHidden(false));
                model.getBone("neck_armor").ifPresent(b -> b.setHidden(false));
                model.getBone("head_armor").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("body_armor").ifPresent(b -> b.setHidden(true));
                model.getBone("neck_armor").ifPresent(b -> b.setHidden(true));
                model.getBone("head_armor").ifPresent(b -> b.setHidden(true));
            }

//            if (entity.isWearingShoes()) {
//                model.getBone("front_right_shoe").ifPresent(b -> b.setHidden(false));
//                model.getBone("front_left_shoe").ifPresent(b -> b.setHidden(false));
//                model.getBone("back_right_shoe").ifPresent(b -> b.setHidden(false));
//                model.getBone("back_left_shoe").ifPresent(b -> b.setHidden(false));
//            } else {
//                model.getBone("front_right_shoe").ifPresent(b -> b.setHidden(true));
//                model.getBone("front_left_shoe").ifPresent(b -> b.setHidden(true));
//                model.getBone("back_right_shoe").ifPresent(b -> b.setHidden(true));
//                model.getBone("back_left_shoe").ifPresent(b -> b.setHidden(true));
//            }
        }

        if (entity.isBaby()) {
            model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
            model.getBone("saddle").ifPresent(b -> b.setHidden(true));
            model.getBone("saddle2").ifPresent(b -> b.setHidden(true));
            model.getBone("front_right_shoe").ifPresent(b -> b.setHidden(true));
            model.getBone("front_left_shoe").ifPresent(b -> b.setHidden(true));
            model.getBone("back_right_shoe").ifPresent(b -> b.setHidden(true));
            model.getBone("back_left_shoe").ifPresent(b -> b.setHidden(true));
            model.getBone("body_armor").ifPresent(b -> b.setHidden(true));
            model.getBone("neck_armor").ifPresent(b -> b.setHidden(true));
            model.getBone("head_armor").ifPresent(b -> b.setHidden(true));
        }

        if (entity.hasDefaultMane()) {
            model.getBone("roached_mane").ifPresent(b -> b.setHidden(false));
            model.getBone("button_mane").ifPresent(b -> b.setHidden(true));
        }

        if (entity.hasButtonMane()) {
            model.getBone("roached_mane").ifPresent(b -> b.setHidden(true));
            model.getBone("button_mane").ifPresent(b -> b.setHidden(false));
        }

        if (entity.hasShortMane()) {
            model.getBone("roached_mane").ifPresent(b -> b.setHidden(false));
            model.getBone("button_mane").ifPresent(b -> b.setHidden(true));
            model.getBone("mane").ifPresent(b -> b.setScaleZ(0.5F));
        }

        if (entity.hasDefaultTail()) {
            model.getBone("tail").ifPresent(b -> b.setScaleX(1.0F));
            model.getBone("tail").ifPresent(b -> b.setScaleY(1.0F));
            model.getBone("tail").ifPresent(b -> b.setScaleZ(1.0F));
            model.getBone("tail_bottom").ifPresent(b -> b.setHidden(false));
        }

        if (entity.hasLongTail()) {
            model.getBone("tail").ifPresent(b -> b.setScaleX(1.0F));
            model.getBone("tail").ifPresent(b -> b.setScaleY(1.3F));
            model.getBone("tail").ifPresent(b -> b.setScaleZ(1.0F));
            model.getBone("tail_bottom").ifPresent(b -> b.setHidden(false));
        }

        if (entity.hasShortTail()) {
            model.getBone("tail").ifPresent(b -> b.setScaleX(1.0F));
            model.getBone("tail").ifPresent(b -> b.setScaleY(0.6F));
            model.getBone("tail").ifPresent(b -> b.setScaleZ(1.0F));
            model.getBone("tail_bottom").ifPresent(b -> b.setHidden(false));
        }

        if (entity.hasTuckedTail()) {
            model.getBone("tail").ifPresent(b -> b.setScaleY(0.7F));
            model.getBone("tail").ifPresent(b -> b.setScaleX(1.1F));
            model.getBone("tail").ifPresent(b -> b.setScaleZ(1.0F));
            model.getBone("tail_bottom").ifPresent(b -> b.setHidden(true));
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}