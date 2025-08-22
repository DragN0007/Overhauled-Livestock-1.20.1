package com.dragn0007.dragnlivestock.blocks.client.render.entity.unicorn;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.unicorn.Unicorn;
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

        BLUE(LivestockOverhaul.id("textures/entity/unicorn/horn/blue.png")),
        DIAMOND(LivestockOverhaul.id("textures/entity/unicorn/horn/diamond.png")),
        EMERALD(LivestockOverhaul.id("textures/entity/unicorn/horn/emerald.png")),
        GREEN(LivestockOverhaul.id("textures/entity/unicorn/horn/green.png")),
        LAPIS(LivestockOverhaul.id("textures/entity/unicorn/horn/lapis.png")),
        PEARL(LivestockOverhaul.id("textures/entity/unicorn/horn/pearl.png")),
        PINK(LivestockOverhaul.id("textures/entity/unicorn/horn/pink.png")),
        YELLOW(LivestockOverhaul.id("textures/entity/unicorn/horn/yellow.png")),

        FIRE(LivestockOverhaul.id("textures/entity/unicorn/horn/fire.png")),
        GOLD(LivestockOverhaul.id("textures/entity/unicorn/horn/gold.png")),
        MANGROVE(LivestockOverhaul.id("textures/entity/unicorn/horn/mangrove.png")),
        NETHERITE(LivestockOverhaul.id("textures/entity/unicorn/horn/netherite.png")),
        RED(LivestockOverhaul.id("textures/entity/unicorn/horn/red.png")),
        REDSTONE(LivestockOverhaul.id("textures/entity/unicorn/horn/redstone.png")),
        WARPED(LivestockOverhaul.id("textures/entity/unicorn/horn/warped.png")),
        NAVY(LivestockOverhaul.id("textures/entity/unicorn/horn/navy.png")),

        END_CRYSTAL(LivestockOverhaul.id("textures/entity/unicorn/horn/end_crystal.png")),
        ENDER_DRAGON(LivestockOverhaul.id("textures/entity/unicorn/horn/ender_dragon.png")),
        ENDER_EYE(LivestockOverhaul.id("textures/entity/unicorn/horn/ender_eye.png")),
        ENDER_PEARL(LivestockOverhaul.id("textures/entity/unicorn/horn/ender_pearl.png")),
        END_GATEWAY(LivestockOverhaul.id("textures/entity/unicorn/horn/end_gateway.png")),
        END_ROD(LivestockOverhaul.id("textures/entity/unicorn/horn/end_rod.png")),
        PURPUR(LivestockOverhaul.id("textures/entity/unicorn/horn/purpur.png")),
        VOID(LivestockOverhaul.id("textures/entity/unicorn/horn/void.png"));

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
