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

public class UnicornHornLayer extends GeoRenderLayer<Unicorn> {
    public UnicornHornLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, Unicorn animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType renderMarkingType = RenderType.entityCutout(((Unicorn)animatable).getOverlayLocation());
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
        END_CRYSTAL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_endcrystal.png")),
        ENDER_DRAGON(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_enderdragon.png")),
        ENDER_EYE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_endereye.png")),
        ENDER_PEARL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_enderpearl.png")),
        END_GATEWAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_endgateway.png")),
        END_ROD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_endrod.png")),
        PURPUR(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_purpur.png")),
        VOID(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_void.png")),

        FIRE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_fire.png")),
        GOLD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_gold.png")),
        MAHOGANY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_mahogany.png")),
        NETHERITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_netherite.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_red.png")),
        REDSTONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_redstone.png")),
        PURPLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_purple.png")),
        NAVY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_navy.png")),

        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_blue.png")),
        DIAMOND(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_diamond.png")),
        EMERALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_emerald.png")),
        GREEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_green.png")),
        LAPIS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_lapis.png")),
        PEARL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_pearl.png")),
        PINK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_pink.png")),
        YELLOW(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/overlay_horn_yellow.png"));

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
