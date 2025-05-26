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
        RenderType renderMarkingType = RenderType.entityCutout(((Unicorn)animatable).getHornTextureResource());
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

        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/blue.png")),
        DIAMOND(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/diamond.png")),
        EMERALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/emerald.png")),
        GREEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/green.png")),
        LAPIS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/lapis.png")),
        PEARL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/pearl.png")),
        PINK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/pink.png")),
        YELLOW(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/yellow.png")),

        FIRE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/fire.png")),
        GOLD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/gold.png")),
        MAHOGANY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/mahogany.png")),
        NETHERITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/netherite.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/red.png")),
        REDSTONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/redstone.png")),
        PURPLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/purple.png")),
        NAVY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/navy.png")),

        END_CRYSTAL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/endcrystal.png")),
        ENDER_DRAGON(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/enderdragon.png")),
        ENDER_EYE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/endereye.png")),
        ENDER_PEARL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/enderpearl.png")),
        END_GATEWAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/endgateway.png")),
        END_ROD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/endrod.png")),
        PURPUR(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/purpur.png")),
        VOID(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/void.png"));

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
