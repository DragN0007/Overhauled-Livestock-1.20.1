package com.dragn0007.dragnlivestock.entities.sheep;

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

public class OSheepWoolLayer extends GeoRenderLayer<OSheep> {
    public OSheepWoolLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OSheep animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

        if (!animatable.isBaby() && animatable.isSheared()) {
            return;
        }

        if (animatable.getBreed() == 6) {
            return;
        }

        if (animatable.isBaby()) {
            return;
        }

        if (!animatable.isDyed()) {
            RenderType renderMarkingType = RenderType.entityCutout(animatable.getWoolLocation());
            poseStack.pushPose();
            poseStack.scale(1.0F, 1.0F, 1.0F);
            poseStack.translate(0.0d, 0.0d, 0.0d);
            poseStack.popPose();
            getRenderer().reRender(getDefaultBakedModel(animatable),
                    poseStack,
                    bufferSource,
                    animatable,
                    renderMarkingType,
                    bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);
            super.render(poseStack, animatable, bakedModel, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);
        } else {
            RenderType renderMarkingType = RenderType.entityCutout(animatable.getWoolDyeLocation());
            poseStack.pushPose();
            poseStack.scale(1.0F, 1.0F, 1.0F);
            poseStack.translate(0.0d, 0.0d, 0.0d);
            poseStack.popPose();
            getRenderer().reRender(getDefaultBakedModel(animatable),
                    poseStack,
                    bufferSource,
                    animatable,
                    renderMarkingType,
                    bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);
            super.render(poseStack, animatable, bakedModel, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);
        }
    }

    public enum Overlay {
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/black.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/brown.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/grey.png")),
        LIGHT_GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/light_grey.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/tan.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/white.png")),
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/none.png")),
        ;

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

    public enum DyeOverlay {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/none.png")),
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/black.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/blue.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/brown.png")),
        CYAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/cyan.png")),
        GREEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/green.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/grey.png")),
        LIGHT_BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/light_blue.png")),
        LIGHT_GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/light_grey.png")),
        LIME(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/lime.png")),
        MAGENTA(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/magenta.png")),
        ORANGE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/orange.png")),
        PINK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/pink.png")),
        PURPLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/purple.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/red.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/white.png")),
        YELLOW(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/yellow.png")),
        ;

        public final ResourceLocation resourceLocation;
        DyeOverlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static DyeOverlay overlayFromOrdinal(int overlay) { return DyeOverlay.values()[overlay % DyeOverlay.values().length];
        }
    }

}
