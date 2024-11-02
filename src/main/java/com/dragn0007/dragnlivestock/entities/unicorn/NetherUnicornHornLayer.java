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

public class NetherUnicornHornLayer extends GeoRenderLayer<NetherUnicorn> {
    public NetherUnicornHornLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, NetherUnicorn animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType renderMarkingType = RenderType.entityCutout(((NetherUnicorn)animatable).getOverlayLocation());
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
        FIRE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_fire.png")),
        GOLD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_gold.png")),
        MAHOGANY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_mahogany.png")),
        NETHERITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_netherite.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_red.png")),
        REDSTONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_redstone.png")),
        PURPLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_purple.png")),
        NAVY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_navy.png"));

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
