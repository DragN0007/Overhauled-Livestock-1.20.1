package com.dragn0007.dragnlivestock.entities.camel;

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

public class OCamelMarkingLayer extends GeoRenderLayer<OCamel> {
    public OCamelMarkingLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OCamel animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
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

    public enum Overlay {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/overlay/none.png")),
        BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/overlay/overlay_bald.png")),
        BLOTCHED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/overlay/overlay_blotched.png")),
        HALF_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/overlay/overlay_half_splash.png")),
        HALVED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/overlay/overlay_halved.png")),
        HIGH_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/overlay/overlay_high_socks.png")),
        PIEBALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/overlay/overlay_piebald.png")),
        SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/overlay/overlay_socks.png")),
        SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/overlay/overlay_speckled.png")),
        SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/overlay/overlay_splash.png")),
        SPOT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/overlay/overlay_spot.png"));

        //Add new entries to bottom when mod is public, else camels will change textures during update.

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
