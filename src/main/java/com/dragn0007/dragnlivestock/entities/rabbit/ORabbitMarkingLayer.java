package com.dragn0007.dragnlivestock.entities.rabbit;

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

import java.util.HashMap;
import java.util.Map;

public class ORabbitMarkingLayer extends GeoRenderLayer<ORabbit> {
    public ORabbitMarkingLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    public static final Map<String, ResourceLocation> TEXTURE_CACHE = new HashMap<>();
    public ResourceLocation getTexture(ORabbit animatable) {
        return TEXTURE_CACHE.computeIfAbsent(animatable.getOverlayLocation(), ResourceLocation::tryParse);
    }

    @Override
    public void render(PoseStack poseStack, ORabbit animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        if (animatable.getBreed() == 9) {
            ResourceLocation resourceLocation = null;

            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/antlers.png");

            RenderType renderType1 = RenderType.entityCutout(resourceLocation);
            poseStack.pushPose();
            poseStack.scale(1.0f, 1.0f, 1.0f);
            poseStack.translate(0.0d, 0.0d, 0.0d);
            poseStack.popPose();
            getRenderer().reRender(getDefaultBakedModel(animatable),
                    poseStack,
                    bufferSource,
                    animatable,
                    renderType1,
                    bufferSource.getBuffer(renderType1), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);
        }

        if (!animatable.isBaby()) {
            RenderType renderMarkingType = RenderType.entityCutout(this.getTexture(animatable));
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
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/none.png")),
        BLACK_BACK_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/black_back_splash.png")),
        BLACK_FRONT_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/black_front_splash.png")),
        BLACK_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/black_speckled.png")),
        BLACK_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/black_striped.png")),
        BLACK_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/black_tipped.png")),
        BLUE_BACK_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/blue_back_splash.png")),
        BLUE_FRONT_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/blue_front_splash.png")),
        BLUE_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/blue_speckled.png")),
        BLUE_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/blue_striped.png")),
        BLUE_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/blue_tipped.png")),
        CREAM_BACK_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/cream_back_splash.png")),
        CREAM_FRONT_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/cream_front_splash.png")),
        CREAM_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/cream_speckled.png")),
        CREAM_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/cream_striped.png")),
        CREAM_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/cream_tipped.png")),
        RED_BACK_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/red_back_splash.png")),
        RED_FRONT_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/red_front_splash.png")),
        RED_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/red_speckled.png")),
        RED_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/red_striped.png")),
        RED_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/red_tipped.png")),
        WHITE_BACK_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/white_back_splash.png")),
        WHITE_FRONT_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/white_front_splash.png")),
        WHITE_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/white_speckled.png")),
        WHITE_STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/white_striped.png")),
        WHITE_TIPPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/white_tipped.png")),
        PURE_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/pure_white.png")),
        CLOVER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/clover.png")),
        BLACK_HARLEQUIN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/black_harlequin.png")),
        BLUE_HARLEQUIN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/blue_harlequin.png")),
        BOOTS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/boots.png")),
        BRINDLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/brindle.png")),
        CHOCOLATE_HARLEQUIN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/chocolate_harlequin.png")),
        CREAM_HARLEQUIN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/cream_harlequin.png")),
        DOVE_LUTINO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/dove_lutino.png")),
        DUTCH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/dutch.png")),
        DUTCH_VIENNA(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/dutch_vienna.png")),
        ENGLISH_SPOT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/english_spot.png")),
        EXTREME_BROKEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/extreme_broken.png")),
        GOLD_HARLEQUIN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/gold_harlequin.png")),
        HEAVY_BROKEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/heavy_broken.png")),
        HIMALAYAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/himalayan.png")),
        LILAC_HARLEQUIN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/lilac_harlequin.png")),
        LUTINO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/lutino.png")),
        MINIMAL_BROKEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/minimal_broken.png")),
        MODERATE_BROKEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/moderate_broken.png")),
        OTTER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/otter.png")),
        PINK_REW(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/pink_rew.png")),
        POINTED_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/pointed_white.png")),
        RED_HARLEQUIN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/red_harlequin.png")),
        REVERSE_EAR(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/reverse_ear.png")),
        SILVER_MARTEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/silver_marten.png")),
        SOLID_VIENNA(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/solid_vienna.png")),
        TAN_BELLY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/tan_belly.png")),
        TRICOLOR(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/tricolor.png")),
        VIENNA(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/vienna.png")),
        WHITE_EAR(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/overlay/white_ear.png")),
        ;

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
