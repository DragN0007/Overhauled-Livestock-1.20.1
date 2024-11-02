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
        RenderType renderMarkingType = RenderType.entityCutout(((ORabbit)animatable).getOverlayLocation());
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
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/overlay_none.png")),
        BLOTCHED_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/overlay_blotched_black.png")),
        BLOTCHED_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/overlay_blotched_white.png")),
        DALMATION(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/overlay_dalmation.png")),
        HALF_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/overlay_half_black.png")),
        HALF_WHTIE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/overlay_half_black.png")),
        ENDED_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/overlay_ended_black.png")),
        ENDED_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/overlay_ended_white.png")),
        PINTO_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/overlay_pinto_black.png")),
        PINTO_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/overlay_pinto_white.png")),
        SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/overlay_speckled.png")),
        STRIPED_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/overlay_striped_black.png")),
        STRIPED_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/overlay_striped_white.png")),
        TIPPED_BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/overlay_tipped_black.png")),
        TIPPED_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/overlay_tipped_white.png"));

        //Add new entries to bottom when mod is public, else rabbits will change textures during update.

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
