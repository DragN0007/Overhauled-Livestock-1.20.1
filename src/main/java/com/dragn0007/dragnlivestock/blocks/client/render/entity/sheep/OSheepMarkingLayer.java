package com.dragn0007.dragnlivestock.blocks.client.render.entity.sheep;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.sheep.OSheep;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class OSheepMarkingLayer extends GeoRenderLayer<OSheep> {
    public OSheepMarkingLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OSheep animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        if (!animatable.isBaby()) {
            RenderType renderMarkingType = RenderType.entityCutout(((OSheep) animatable).getOverlayLocation());
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
        NONE(LivestockOverhaul.id("textures/entity/sheep/overlay/none.png")),
        BACKSPLASH(LivestockOverhaul.id("textures/entity/sheep/overlay/backsplash.png")),
        BLAZE(LivestockOverhaul.id("textures/entity/sheep/overlay/blaze.png")),
        PURE_WHITE(LivestockOverhaul.id("textures/entity/sheep/overlay/pure_white.png")),
        REVERSE_BACKSPLASH(LivestockOverhaul.id("textures/entity/sheep/overlay/reverse_backsplash.png")),
        REVERSE_SPLOTCHED(LivestockOverhaul.id("textures/entity/sheep/overlay/reverse_splotched.png")),
        REVERSE_STRIPE(LivestockOverhaul.id("textures/entity/sheep/overlay/reverse_stripe.png")),
        RINGNECK(LivestockOverhaul.id("textures/entity/sheep/overlay/ringneck.png")),
        SOCKS(LivestockOverhaul.id("textures/entity/sheep/overlay/socks.png")),
        SPLOTCHED(LivestockOverhaul.id("textures/entity/sheep/overlay/splotched.png")),
        STRIPE(LivestockOverhaul.id("textures/entity/sheep/overlay/stripe.png")),
        FANCY_LIGHT_SPLOTCHED(LivestockOverhaul.id("textures/entity/sheep/overlay/fancy_light_splotched.png")),
        FANCY_MEDIUM_SPLOTCHED(LivestockOverhaul.id("textures/entity/sheep/overlay/fancy_medium_splotched.png")),
        FANCY_SPLOTCHED(LivestockOverhaul.id("textures/entity/sheep/overlay/fancy_splotched.png")),
        FANCY_SOCKS_BACK(LivestockOverhaul.id("textures/entity/sheep/overlay/fancy_socks_back.png")),
        FANCY_SOCKS_FRONT(LivestockOverhaul.id("textures/entity/sheep/overlay/fancy_socks_front.png")),
        ROAN(LivestockOverhaul.id("textures/entity/sheep/overlay/roan.png")),
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
