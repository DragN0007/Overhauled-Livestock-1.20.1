package com.dragn0007.dragnlivestock.blocks.client.render.entity.pig;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.pig.OPig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class OPigMarkingLayer extends GeoRenderLayer<OPig> {
    public OPigMarkingLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OPig animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        if (!animatable.isBaby()) {
            RenderType renderMarkingType = RenderType.entityCutout(((OPig) animatable).getOverlayLocation());
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
    }

    public enum Overlay {
        NONE(LivestockOverhaul.id("textures/entity/pig/overlay/none.png")),
        BLACK_STRIPE(LivestockOverhaul.id("textures/entity/pig/overlay/black_striped.png")),
        BLUE_STRIPE(LivestockOverhaul.id("textures/entity/pig/overlay/blue_striped.png")),
        PINK_STRIPE(LivestockOverhaul.id("textures/entity/pig/overlay/pink_striped.png")),
        WHITE_STRIPE(LivestockOverhaul.id("textures/entity/pig/overlay/white_striped.png")),
        BLACK_SPOTTED(LivestockOverhaul.id("textures/entity/pig/overlay/black_spotted.png")),
        BLUE_SPOTTED(LivestockOverhaul.id("textures/entity/pig/overlay/blue_spotted.png")),
        BROWN_SPOTTED(LivestockOverhaul.id("textures/entity/pig/overlay/brown_spotted.png")),
        PINK_SPOTTED(LivestockOverhaul.id("textures/entity/pig/overlay/pink_spotted.png")),
        WHITE_SPOTTED(LivestockOverhaul.id("textures/entity/pig/overlay/white_spotted.png")),
        BLACK_SPLOTCHED(LivestockOverhaul.id("textures/entity/pig/overlay/black_splotched.png")),
        BROWN_SPLOTCHED(LivestockOverhaul.id("textures/entity/pig/overlay/brown_splotched.png")),
        WHITE_SPLOTCHED(LivestockOverhaul.id("textures/entity/pig/overlay/white_splotched.png")),
        BLACK_SPECKLED(LivestockOverhaul.id("textures/entity/pig/overlay/black_speckled.png")),
        WHITE_SPECKLED(LivestockOverhaul.id("textures/entity/pig/overlay/white_speckled.png")),
        WHITE_SOCKS(LivestockOverhaul.id("textures/entity/pig/overlay/white_socks.png")),
        ;

        //Add new entries to bottom when mod is public, else pigs will change textures during update.

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
