package com.dragn0007.dragnlivestock.entities.frog;

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

public class OFrogEyeLayer extends GeoRenderLayer<OFrog> {
    public OFrogEyeLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OFrog animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType renderMarkingType = RenderType.entityCutout(((OFrog)animatable).getEyesLocation());
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
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/none.png")),
        BLUE_EYES(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/frog_eyes_blue.png")),
        BLACK_EYES(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/frog_eyes_black.png")),
        GREEN_EYES(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/frog_eyes_green.png")),
        ORANGE_EYES(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/frog_eyes_orange.png")),
        RED_EYES(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/frog_eyes_red.png")),
        PURPLE_EYES(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/frog_eyes_purple.png"));

        //Add new entries to bottom when mod is public, else frogs will change textures during update.

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
