package com.dragn0007.dragnlivestock.entities.cow;

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

public class OCowMarkingLayer extends GeoRenderLayer<OCow> {
    public OCowMarkingLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OCow animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType renderMarkingType = RenderType.entityCutout(animatable.getOverlayLocation());

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
    }

    public enum Overlay {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/none.png")),
        BLACK_FEW_SPECKLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/black_few_speckle.png")),
        BLACK_LEOPARD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/black_leopard.png")),
        BLACK_PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/black_paint.png")),
        BLACK_STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/black_stripe.png")),
        BLUE_FEW_SPECKLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/blue_few_speckle.png")),
        BLUE_LEOPARD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/blue_leopard.png")),
        BLUE_PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/blue_paint.png")),
        BLUE_STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/blue_stripe.png")),
        CREAM_FEW_SPECKLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/cream_few_speckle.png")),
        CREAM_LEOPARD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/cream_leopard.png")),
        CREAM_PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/cream_paint.png")),
        CREAM_STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/cream_stripe.png")),
        RED_FEW_SPECKLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/red_few_speckle.png")),
        RED_LEOPARD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/red_leopard.png")),
        RED_PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/red_paint.png")),
        RED_STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/red_stripe.png")),
        WHITE_FEW_SPECKLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/white_few_speckle.png")),
        WHITE_LEOPARD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/white_leopard.png")),
        WHITE_PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/white_paint.png")),
        WHITE_STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/white_stripe.png")),
        BLAZE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/blaze.png")),
        SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/socks.png")),
        FULL_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/full_socks.png")),
        PURE_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/pure_white.png")),
        HEREFORD_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/cow/overlay/hereford_splash.png"));

        //Add new entries to bottom when mod is public, else cows will change textures during update.

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
