package com.dragn0007.dragnlivestock.entities.pig;

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
import net.minecraft.world.item.DyeColor;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class OPigRenderLayer extends GeoRenderLayer<OPig> {
    public OPigRenderLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OPig animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        Player player = Minecraft.getInstance().player;
        double distanceSq = animatable.distanceToSqr(player);
        boolean atCullDistance = distanceSq > LivestockOverhaulClientConfig.CULL_LAYERS_DISTANCE.get();
        if (atCullDistance) return;
        if (animatable.isBaby() || (animatable.getOverlayVariant() == 0 && !animatable.isTagged())) return;

        if (animatable.getOverlayVariant() != 0) {
            RenderType renderMarkingType = RenderType.entityCutout(animatable.getOverlayLocation());
            getRenderer().reRender(
                    getDefaultBakedModel(animatable),
                    poseStack,
                    bufferSource,
                    animatable,
                    renderMarkingType,
                    bufferSource.getBuffer(renderMarkingType),
                    partialTick,
                    packedLight,
                    OverlayTexture.NO_OVERLAY,
                    1.0F, 1.0F, 1.0F, 1.0F
            );
        }

        if (!animatable.isTagged() || !LivestockOverhaulClientConfig.RENDER_BRAND_TAGS.get()) return;
        if (animatable.isTagged()) {
            DyeColor dyeColor = animatable.getBrandTagColor();
            ResourceLocation resourceLocation = null;
            if (dyeColor != null) {
                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/" + dyeColor + ".png");
            }
            RenderType renderType1 = RenderType.entityCutout(resourceLocation);
            getRenderer().reRender(getDefaultBakedModel(animatable),
                    poseStack,
                    bufferSource,
                    animatable,
                    renderType1,
                    bufferSource.getBuffer(renderType1), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);
        }
    }

    public enum Overlay {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/none.png")),
        BLACK_STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/black_striped.png")),
        BLUE_STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/blue_striped.png")),
        PINK_STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/pink_striped.png")),
        WHITE_STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/white_striped.png")),
        BLACK_SPOTTED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/black_spotted.png")),
        BLUE_SPOTTED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/blue_spotted.png")),
        BROWN_SPOTTED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/brown_spotted.png")),
        PINK_SPOTTED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/pink_spotted.png")),
        WHITE_SPOTTED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/white_spotted.png")),
        BLACK_SPLOTCHED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/black_splotched.png")),
        BROWN_SPLOTCHED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/brown_splotched.png")),
        WHITE_SPLOTCHED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/white_splotched.png")),
        BLACK_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/black_speckled.png")),
        WHITE_SPECKLED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/white_speckled.png")),
        WHITE_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/pig/overlay/white_socks.png")),
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
