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
            poseStack.scale(2.0F, 2.0F, 2.0F);
            poseStack.translate(0.0d, 0.0d, 0.0d);
        }

        RenderType renderMarkingType = RenderType.entityCutout(((OSheep)animatable).getWoolLocation());
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

    public enum Overlay {
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/black.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/brown.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/grey.png")),
        LIGHT_GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/light_grey.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/tan.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/white.png")),
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/none.png")),
        ;

        //Add new entries to bottom when mod is public, else sheep will change textures during update.

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
