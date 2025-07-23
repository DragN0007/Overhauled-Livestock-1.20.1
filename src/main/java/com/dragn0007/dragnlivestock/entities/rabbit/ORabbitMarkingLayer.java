package com.dragn0007.dragnlivestock.entities.rabbit;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class ORabbitMarkingLayer extends GeoRenderLayer<ORabbit> {
    public ORabbitMarkingLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, ORabbit animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        if (!animatable.isBaby()) {
            RenderType renderMarkingType = RenderType.entityCutout(((ORabbit) animatable).getOverlayLocation());
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
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/none.png")),
        BLACK_BACK_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/black_back_splash.png")),
        BLACK_FRONT_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/black_front_splash.png")),
        BLACK_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/black_speckled.png")),
        BLACK_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/black_striped.png")),
        BLACK_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/black_tipped.png")),
        BLUE_BACK_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/blue_back_splash.png")),
        BLUE_FRONT_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/blue_front_splash.png")),
        BLUE_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/blue_speckled.png")),
        BLUE_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/blue_striped.png")),
        BLUE_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/blue_tipped.png")),
        CREAM_BACK_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/cream_back_splash.png")),
        CREAM_FRONT_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/cream_front_splash.png")),
        CREAM_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/cream_speckled.png")),
        CREAM_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/cream_striped.png")),
        CREAM_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/cream_tipped.png")),
        RED_BACK_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/red_back_splash.png")),
        RED_FRONT_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/red_front_splash.png")),
        RED_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/red_speckled.png")),
        RED_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/red_striped.png")),
        RED_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/red_tipped.png")),
        WHITE_BACK_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/white_back_splash.png")),
        WHITE_FRONT_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/white_front_splash.png")),
        WHITE_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/white_speckled.png")),
        WHITE_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/white_striped.png")),
        WHITE_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/white_tipped.png")),
        PURE_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/pure_white.png")),
        CLOVER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/clover.png"));

        //Add new entries to bottom when mod is public, else rabbits will change textures during update.

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
