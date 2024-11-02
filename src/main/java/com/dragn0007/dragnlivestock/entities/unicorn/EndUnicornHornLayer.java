package com.dragn0007.dragnlivestock.entities.unicorn;

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

public class EndUnicornHornLayer extends GeoRenderLayer<EndUnicorn> {
    public EndUnicornHornLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, EndUnicorn animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType renderMarkingType = RenderType.entityCutout(((EndUnicorn)animatable).getOverlayLocation());
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
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_none.png")),
        END_CRYSTAL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_endcrystal.png")),
        ENDER_DRAGON(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_enderdragon.png")),
        ENDER_EYE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_endereye.png")),
        ENDER_PEARL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_enderpearl.png")),
        END_GATEWAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_endgateway.png")),
        END_ROD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_endrod.png")),
        PURPUR(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_purpur.png")),
        VOID(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_void.png"));

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
