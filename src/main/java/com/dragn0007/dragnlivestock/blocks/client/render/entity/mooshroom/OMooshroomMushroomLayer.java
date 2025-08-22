package com.dragn0007.dragnlivestock.blocks.client.render.entity.mooshroom;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.cow.OMooshroom;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class OMooshroomMushroomLayer extends GeoRenderLayer<OMooshroom> {
    public OMooshroomMushroomLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OMooshroom animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType renderMarkingType = RenderType.entityCutout(animatable.getMushroomLocation());
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
    public enum MushroomOverlay {
        BROWN_FULL(LivestockOverhaul.id("textures/entity/moobloom/mushroom/brown_mushroom.png")),
        RED_FULL(LivestockOverhaul.id("textures/entity/moobloom/mushroom/red_mushroom.png"));

        //Add new entries to bottom when mod is public, else mooshrooms will change textures during update.

        public final ResourceLocation resourceLocation;
        MushroomOverlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static MushroomOverlay overlayFromOrdinal(int overlay) { return MushroomOverlay.values()[overlay % MushroomOverlay.values().length];
        }
    }

}
