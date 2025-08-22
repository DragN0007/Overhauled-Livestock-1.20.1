package com.dragn0007.dragnlivestock.blocks.client.render.entity.camel;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.camel.OCamel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class OCamelMarkingLayer extends GeoRenderLayer<OCamel> {
    public OCamelMarkingLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OCamel animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        if (!animatable.isBaby()) {
            RenderType renderMarkingType = RenderType.entityCutout(animatable.getOverlayLocation());
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
    }

    public enum Overlay {
        NONE(LivestockOverhaul.id("textures/entity/camel/overlay/none.png")),
        BALD(LivestockOverhaul.id("textures/entity/camel/overlay/bald.png")),
        BLACK_BELLY_SPLASH(LivestockOverhaul.id("textures/entity/camel/overlay/black_belly_splash.png")),
        BLACK_HALF_SPLASH(LivestockOverhaul.id("textures/entity/camel/overlay/black_half_splash.png")),
        BLACK_PIEBALD(LivestockOverhaul.id("textures/entity/camel/overlay/black_piebald.png")),
        BLACK_SPECKLED(LivestockOverhaul.id("textures/entity/camel/overlay/black_speckled.png")),
        BLAZE(LivestockOverhaul.id("textures/entity/camel/overlay/blaze.png")),
        BLUE_BELLY_SPLASH(LivestockOverhaul.id("textures/entity/camel/overlay/blue_belly_splash.png")),
        BLUE_HALF_SPLASH(LivestockOverhaul.id("textures/entity/camel/overlay/blue_half_splash.png")),
        BLUE_PIEBALD(LivestockOverhaul.id("textures/entity/camel/overlay/blue_piebald.png")),
        BLUE_SPECKLED(LivestockOverhaul.id("textures/entity/camel/overlay/blue_speckled.png")),
        CREAM_BELLY_SPLASH(LivestockOverhaul.id("textures/entity/camel/overlay/cream_belly_splash.png")),
        CREAM_HALF_SPLASH(LivestockOverhaul.id("textures/entity/camel/overlay/cream_half_splash.png")),
        CREAM_PIEBALD(LivestockOverhaul.id("textures/entity/camel/overlay/cream_piebald.png")),
        CREAM_SPECKLED(LivestockOverhaul.id("textures/entity/camel/overlay/cream_speckled.png")),
        FULL_SOCKS(LivestockOverhaul.id("textures/entity/camel/overlay/full_socks.png")),
        HALF_SOCKS(LivestockOverhaul.id("textures/entity/camel/overlay/half_socks.png")),
        RED_BELLY_SPLASH(LivestockOverhaul.id("textures/entity/camel/overlay/red_belly_splash.png")),
        RED_HALF_SPLASH(LivestockOverhaul.id("textures/entity/camel/overlay/red_half_splash.png")),
        RED_PIEBALD(LivestockOverhaul.id("textures/entity/camel/overlay/red_piebald.png")),
        RED_SPECKLED(LivestockOverhaul.id("textures/entity/camel/overlay/red_speckled.png")),
        WHITE_BELLY_SPLASH(LivestockOverhaul.id("textures/entity/camel/overlay/white_belly_splash.png")),
        WHITE_HALF_SPLASH(LivestockOverhaul.id("textures/entity/camel/overlay/white_half_splash.png")),
        WHITE_PIEBALD(LivestockOverhaul.id("textures/entity/camel/overlay/white_piebald.png")),
        WHITE_SPECKLED(LivestockOverhaul.id("textures/entity/camel/overlay/white_speckled.png")),
        ;

        //Add new entries to bottom when mod is public, else camels will change textures during update.

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
