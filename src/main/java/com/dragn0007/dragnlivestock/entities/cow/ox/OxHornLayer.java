package com.dragn0007.dragnlivestock.entities.cow.ox;

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

public class OxHornLayer extends GeoRenderLayer<Ox> {
    public OxHornLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, Ox animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType renderMarkingType = RenderType.entityCutout(((Ox)animatable).getHornsLocation());
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
        MEDIUM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/horn_overlay/overlay_medium_horns.png")),
        LONG(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/horn_overlay/overlay_long_horns.png"));

        //Add new entries to bottom when mod is public, else oxen will change textures during update.

        public final ResourceLocation resourceLocation;
        HornOverlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static HornOverlay hornOverlayFromOrdinal(int hornOverlay) { return HornOverlay.values()[hornOverlay % HornOverlay.values().length];
        }
    }

}