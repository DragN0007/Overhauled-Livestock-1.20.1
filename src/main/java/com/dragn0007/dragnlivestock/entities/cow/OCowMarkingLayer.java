package com.dragn0007.dragnlivestock.entities.cow;

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

public class OCowMarkingLayer extends GeoRenderLayer<OCow> {
    public OCowMarkingLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OCow animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType renderMarkingType = RenderType.entityCutout(((OCow)animatable).getOverlayLocation());
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
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/overlay_none.png")),
        HIGHLAND_DARK_BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/highland_dark_brown.png")),
        HIGHLAND_GOLD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/highland_gold.png")),
        HIGHLAND_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/highland_white.png")),
        BLACK_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/overlay_black_speckled.png")),
        BLAZE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/overlay_blaze.png")),
        DAIRY_PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/overlay_dairy_paint.png")),
        OVERO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/overlay_overo.png")),
        PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/overlay_paint.png")),
        REVERSE_PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/overlay_reverse_paint.png")),
        SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/overlay_socks.png")),
        SPLASH_STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/overlay_splash_stripe.png")),
        WHITE_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/overlay_white_speckled.png"));

        //Add new entries to bottom when mod is public, else cows will change textures during update.

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
