package com.dragn0007.dragnlivestock.entities.horse.undead_horse;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OUndeadHorseRender extends GeoEntityRenderer<OUndeadHorse> {

    public OUndeadHorseRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OUndeadHorseModel());
        this.addRenderLayer(new OUndeadHorseSkeletalLayer(this));
        this.addRenderLayer(new OUndeadHorseCarpetLayer(this));
        this.addRenderLayer(new OUndeadHorseArmorLayer(this));
    }

    @Override
    public void preRender(PoseStack poseStack, OUndeadHorse entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        if (!entity.isBaby()) {
            if (entity.hasChest()) {
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("saddlebags").ifPresent(b -> b.setHidden(true));
            }

            if (entity.isSaddled()) {
                model.getBone("saddle").ifPresent(b -> b.setHidden(false));
                model.getBone("saddle2").ifPresent(b -> b.setHidden(false));
                model.getBone("front_right_shoe").ifPresent(b -> b.setHidden(false));
                model.getBone("front_left_shoe").ifPresent(b -> b.setHidden(false));
                model.getBone("back_right_shoe").ifPresent(b -> b.setHidden(false));
                model.getBone("back_left_shoe").ifPresent(b -> b.setHidden(false));
            } else if (entity.isSaddled()) {
                model.getBone("saddle").ifPresent(b -> b.setHidden(false));
                model.getBone("saddle2").ifPresent(b -> b.setHidden(false));
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
            } else {
                model.getBone("body_armor").ifPresent(b -> b.setHidden(true));
                model.getBone("neck_armor").ifPresent(b -> b.setHidden(true));
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
        }

        if (entity.getManeType() == 0) {
            model.getBone("roached").ifPresent(b -> b.setHidden(false));
            model.getBone("buttons").ifPresent(b -> b.setHidden(true));
            model.getBone("long").ifPresent(b -> b.setHidden(true));
        }

        if (entity.getManeType() == 1) {
            model.getBone("roached").ifPresent(b -> b.setHidden(true));
            model.getBone("buttons").ifPresent(b -> b.setHidden(false));
            model.getBone("long").ifPresent(b -> b.setHidden(true));
        }

        if (entity.getManeType() == 2) {
            model.getBone("roached").ifPresent(b -> b.setHidden(false));
            model.getBone("buttons").ifPresent(b -> b.setHidden(true));
            model.getBone("long").ifPresent(b -> b.setHidden(true));
            model.getBone("mane").ifPresent(b -> b.setScaleZ(0.5F));
        }

        if (entity.getManeType() == 3) {
            model.getBone("roached").ifPresent(b -> b.setHidden(true));
            model.getBone("buttons").ifPresent(b -> b.setHidden(true));
            model.getBone("long").ifPresent(b -> b.setHidden(false));
        }
        if (entity.getManeType() == 4) {
            model.getBone("roached").ifPresent(b -> b.setHidden(true));
            model.getBone("buttons").ifPresent(b -> b.setHidden(true));
            model.getBone("long").ifPresent(b -> b.setHidden(true));
        }

        if (entity.getTailType() == 0) {
            model.getBone("tail").ifPresent(b -> b.setScaleX(1.0F));
            model.getBone("tail").ifPresent(b -> b.setScaleY(1.0F));
            model.getBone("tail").ifPresent(b -> b.setScaleZ(1.0F));
            model.getBone("tail_2").ifPresent(b -> b.setHidden(false));
        }

        if (entity.getTailType() == 1) {
            model.getBone("tail").ifPresent(b -> b.setScaleX(1.0F));
            model.getBone("tail").ifPresent(b -> b.setScaleY(1.3F));
            model.getBone("tail").ifPresent(b -> b.setScaleZ(1.0F));
            model.getBone("tail_2").ifPresent(b -> b.setHidden(false));
        }

        if (entity.getTailType() == 2) {
            model.getBone("tail").ifPresent(b -> b.setScaleX(1.0F));
            model.getBone("tail").ifPresent(b -> b.setScaleY(0.6F));
            model.getBone("tail").ifPresent(b -> b.setScaleZ(1.0F));
            model.getBone("tail_2").ifPresent(b -> b.setHidden(false));
        }

        if (entity.getTailType() == 3) {
            model.getBone("tail").ifPresent(b -> b.setScaleY(0.7F));
            model.getBone("tail").ifPresent(b -> b.setScaleX(1.1F));
            model.getBone("tail").ifPresent(b -> b.setScaleZ(1.0F));
            model.getBone("tail_2").ifPresent(b -> b.setHidden(true));
        }

        if (entity.getTailType() == 4) {
            model.getBone("tail").ifPresent(b -> b.setScaleY(0.9F));
            model.getBone("tail").ifPresent(b -> b.setScaleX(0.7F));
            model.getBone("tail").ifPresent(b -> b.setScaleZ(0.7F));
            model.getBone("tail_2").ifPresent(b -> b.setHidden(true));
        }

        if (entity.getFeathering() == 0) {
            model.getBone("front_right_feathering").ifPresent(b -> b.setHidden(true));
            model.getBone("front_left_feathering").ifPresent(b -> b.setHidden(true));
            model.getBone("back_right_feathering").ifPresent(b -> b.setHidden(true));
            model.getBone("back_left_feathering").ifPresent(b -> b.setHidden(true));
        }

        if (entity.getFeathering() == 1) {
            model.getBone("front_right_feathering").ifPresent(b -> b.setHidden(false));
            model.getBone("front_left_feathering").ifPresent(b -> b.setHidden(false));
            model.getBone("back_right_feathering").ifPresent(b -> b.setHidden(false));
            model.getBone("back_left_feathering").ifPresent(b -> b.setHidden(false));
            model.getBone("front_right_feathering").ifPresent(b -> b.setScaleY(0.5F));
            model.getBone("front_left_feathering").ifPresent(b -> b.setScaleY(0.5F));
            model.getBone("back_left_feathering").ifPresent(b -> b.setScaleY(0.5F));
            model.getBone("back_left_feathering").ifPresent(b -> b.setScaleY(0.5F));
        }

        if (entity.getFeathering() == 2) {
            model.getBone("front_right_feathering").ifPresent(b -> b.setHidden(false));
            model.getBone("front_left_feathering").ifPresent(b -> b.setHidden(false));
            model.getBone("back_right_feathering").ifPresent(b -> b.setHidden(false));
            model.getBone("back_left_feathering").ifPresent(b -> b.setHidden(false));
            model.getBone("front_right_feathering").ifPresent(b -> b.setScaleY(1F));
            model.getBone("front_left_feathering").ifPresent(b -> b.setScaleY(1F));
            model.getBone("back_left_feathering").ifPresent(b -> b.setScaleY(1F));
            model.getBone("back_left_feathering").ifPresent(b -> b.setScaleY(1F));
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}