package com.dragn0007.dragnlivestock.entities.frog;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class OFrogRenderLayer extends GeoRenderLayer<OFrog> {
    public OFrogRenderLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OFrog animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        Player player = Minecraft.getInstance().player;
        double distanceSq = animatable.distanceToSqr(player);
        boolean atCullDistance = distanceSq > LivestockOverhaulClientConfig.CULL_LAYERS_DISTANCE.get();
        if (atCullDistance) return;

        if (animatable.getOverlayVariant() != 0) {
            RenderType renderMarkingType = RenderType.entityCutout(animatable.getOverlayLocation());
            getRenderer().reRender(getDefaultBakedModel(animatable),
                    poseStack,
                    bufferSource,
                    animatable,
                    renderMarkingType,
                    bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);
        }

        RenderType renderMarkingType = RenderType.entityCutout(animatable.getEyesLocation());
        getRenderer().reRender(getDefaultBakedModel(animatable),
                poseStack,
                bufferSource,
                animatable,
                renderMarkingType,
                bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                1, 1, 1, 1);
    }

    public enum Marking {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/none.png")),
        DART(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/dart.png")),
        STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/striped.png")),
        BLACK_LEGS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/black_legs.png")),
        BLUE_LEGS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/blue_legs.png")),
        BROWN_LEGS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/brown_legs.png")),
        CYAN_LEGS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/cyan_legs.png")),
        GRAY_LEGS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/gray_legs.png")),
        GREEN_LEGS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/green_legs.png")),
        LIGHT_BLUE_LEGS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/light_blue_legs.png")),
        LIGHT_GRAY_LEGS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/light_gray_legs.png")),
        LIME_LEGS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/lime_legs.png")),
        ORANGE_LEGS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/orange_legs.png")),
        MAGENTA_LEGS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/magenta_legs.png")),
        PINK_LEGS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/pink_legs.png")),
        PURPLE_LEGS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/purple_legs.png")),
        RED_LEGS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/red_legs.png")),
        WHITE_LEGS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/white_legs.png")),
        YELLOW_LEGS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/yellow_legs.png"));

        //Add new entries to bottom when mod is public, else frogs will change textures during update.
        public final ResourceLocation resourceLocation;
        Marking(ResourceLocation resourceLocation) {this.resourceLocation = resourceLocation;}
        public static Marking overlayFromOrdinal(int overlay) { return Marking.values()[overlay % Marking.values().length];}
    }

    public enum EyeColor {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/none.png")),
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/eyes/black.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/eyes/blue.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/eyes/brown.png")),
        CYAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/eyes/cyan.png")),
        GRAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/eyes/gray.png")),
        GREEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/eyes/green.png")),
        LIGHT_BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/eyes/light_blue.png")),
        LIGHT_GRAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/eyes/light_gray.png")),
        LIME(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/eyes/lime.png")),
        ORANGE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/eyes/orange.png")),
        MAGENTA(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/eyes/magenta.png")),
        PINK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/eyes/pink.png")),
        PURPLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/eyes/purple.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/eyes/red.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/eyes/white.png")),
        YELLOW(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/eyes/yellow.png"));

        //Add new entries to bottom when mod is public, else frogs will change textures during update.
        public final ResourceLocation resourceLocation;
        EyeColor(ResourceLocation resourceLocation) {this.resourceLocation = resourceLocation;}
        public static EyeColor overlayFromOrdinal(int overlay) { return EyeColor.values()[overlay % EyeColor.values().length];}
    }
}
