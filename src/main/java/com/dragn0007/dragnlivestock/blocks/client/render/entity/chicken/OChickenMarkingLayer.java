package com.dragn0007.dragnlivestock.blocks.client.render.entity.chicken;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.chicken.OChicken;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class OChickenMarkingLayer extends GeoRenderLayer<OChicken> {
    public OChickenMarkingLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OChicken animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType renderMarkingType = RenderType.entityCutout(((OChicken)animatable).getOverlayLocation());
        poseStack.pushPose();
        poseStack.scale(1.0f, 1.0f, 1.0f);
        poseStack.translate(0.0d, 0.0d, 0.0d);
        poseStack.popPose();
        getRenderer().reRender(getDefaultBakedModel(animatable),
                poseStack,
                bufferSource,
                animatable,
                renderMarkingType,
                bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                1, 1, 1, 1);
    }

    public enum Overlay {
        NONE(LivestockOverhaul.id("textures/entity/chicken/overlay/none.png")),
        BLACK_ROOSTER(LivestockOverhaul.id("textures/entity/chicken/overlay/overlay_black_rooster.png")),
        BLUE_ROOSTER(LivestockOverhaul.id("textures/entity/chicken/overlay/overlay_blue_rooster.png")),
        HEAD_BLACK(LivestockOverhaul.id("textures/entity/chicken/overlay/overlay_head_black.png")),
        HEAD_WHITE(LivestockOverhaul.id("textures/entity/chicken/overlay/overlay_head_white.png")),
        SPECKLED_BLACK(LivestockOverhaul.id("textures/entity/chicken/overlay/overlay_speckled_black.png")),
        SPECKLED_BROWN(LivestockOverhaul.id("textures/entity/chicken/overlay/overlay_speckled_brown.png")),
        SPECKLED_WHITE(LivestockOverhaul.id("textures/entity/chicken/overlay/overlay_speckled_white.png"));

        //Add new entries to bottom when mod is public, else chickens will change textures during update.

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
