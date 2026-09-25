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
        BLUE_LEGS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/frog_blue_legs.png")),
        DART(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/frog_dart.png")),
        DART_PURPLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/frog_dart_purple.png")),
        DART_YELLOW(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/frog_dart_yellow.png")),
        STRIPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/frog_striped.png"));

        //Add new entries to bottom when mod is public, else frogs will change textures during update.

        public final ResourceLocation resourceLocation;
        Marking(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Marking overlayFromOrdinal(int overlay) { return Marking.values()[overlay % Marking.values().length];
        }
    }

    public enum EyeColor {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/none.png")),
        BLUE_EYES(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/frog_eyes_blue.png")),
        BLACK_EYES(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/frog_eyes_black.png")),
        GREEN_EYES(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/frog_eyes_green.png")),
        ORANGE_EYES(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/frog_eyes_orange.png")),
        RED_EYES(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/frog_eyes_red.png")),
        PURPLE_EYES(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/frog/overlay/frog_eyes_purple.png"));

        //Add new entries to bottom when mod is public, else frogs will change textures during update.

        public final ResourceLocation resourceLocation;
        EyeColor(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static EyeColor overlayFromOrdinal(int overlay) { return EyeColor.values()[overlay % EyeColor.values().length];
        }
    }
}
