package com.dragn0007.dragnlivestock.entities.unicorn;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.util.marking_layer.EquineEyeColorOverlay;
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

import java.util.HashMap;
import java.util.Map;

public class UnicornBodyLayer extends GeoRenderLayer<Unicorn> {
    public UnicornBodyLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    public static final Map<String, ResourceLocation> TEXTURE_CACHE = new HashMap<>();
    public ResourceLocation getTexture(Unicorn animatable) {
        return TEXTURE_CACHE.computeIfAbsent(animatable.getOverlayLocation(), ResourceLocation::tryParse);
    }

    @Override
    public void render(PoseStack poseStack, Unicorn animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        Player player = Minecraft.getInstance().player;
        double distanceSq = animatable.distanceToSqr(player);
        boolean atCullDistance = distanceSq > LivestockOverhaulClientConfig.CULL_LAYERS_DISTANCE.get();
        if (atCullDistance) return;
        if (animatable.getOverlayVariant() != 0) {
            RenderType renderMarkingType = RenderType.entityCutout(this.getTexture(animatable));
            getRenderer().reRender(getDefaultBakedModel(animatable),
                    poseStack,
                    bufferSource,
                    animatable,
                    renderMarkingType,
                    bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);
        }

        RenderType renderMarkingType = RenderType.entityCutout(animatable.getHornTextureResource());
        getRenderer().reRender(getDefaultBakedModel(animatable),
                poseStack,
                bufferSource,
                animatable,
                renderMarkingType,
                bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                1, 1, 1, 1);

        EquineEyeColorOverlay eyes = EquineEyeColorOverlay.eyesFromOrdinal(animatable.getEyeVariant());
        RenderType renderEyeType = RenderType.entityCutout(eyes.resourceLocation);
        getRenderer().reRender(getDefaultBakedModel(animatable),
                poseStack,
                bufferSource,
                animatable,
                renderEyeType,
                bufferSource.getBuffer(renderEyeType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                1, 1, 1, 1);
    }

    public enum HornType {
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
        MANGROVE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/mangrove.png")),
        NETHERITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/netherite.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/red.png")),
        REDSTONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/redstone.png")),
        WARPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/warped.png")),
        NAVY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/navy.png")),

        END_CRYSTAL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/end_crystal.png")),
        ENDER_DRAGON(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/ender_dragon.png")),
        ENDER_EYE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/ender_eye.png")),
        ENDER_PEARL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/ender_pearl.png")),
        END_GATEWAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/end_gateway.png")),
        END_ROD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/end_rod.png")),
        PURPUR(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/purpur.png")),
        VOID(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/void.png"));

        public final ResourceLocation resourceLocation;
        HornType(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static HornType overlayFromOrdinal(int overlay) { return HornType.values()[overlay % HornType.values().length];
        }
    }
}
