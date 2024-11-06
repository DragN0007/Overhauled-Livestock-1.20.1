package com.dragn0007.dragnlivestock.entities.sheep;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.rabbit.ORabbit;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class OSheepHornLayer extends GeoRenderLayer<OSheep> {
    public OSheepHornLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OSheep animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType renderMarkingType = RenderType.entityCutout(((OSheep)animatable).getHornsLocation());
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

    public enum HornOverlay {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/wool/none.png")),
        CURLY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/horns/overlay_horns_curly.png")),
        SHORT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/horns/overlay_horns_short.png")),
        LONG(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/horns/overlay_horns_long.png"));

        public final ResourceLocation resourceLocation;
        HornOverlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static HornOverlay hornOverlayFromOrdinal(int overlay) { return HornOverlay.values()[overlay % HornOverlay.values().length];
        }
    }

}
