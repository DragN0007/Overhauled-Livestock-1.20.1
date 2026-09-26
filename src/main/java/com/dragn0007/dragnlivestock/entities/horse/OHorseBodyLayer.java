package com.dragn0007.dragnlivestock.entities.horse;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.util.marking_layer.EquineEyeColorOverlay;
import com.dragn0007.dragnlivestock.entities.util.marking_layer.EquineMarkingOverlay;
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

public class OHorseBodyLayer extends GeoRenderLayer<OHorse> {
    public OHorseBodyLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    public static final Map<String, ResourceLocation> TEXTURE_CACHE = new HashMap<>();
    public ResourceLocation getTexture(OHorse animatable) {
        return TEXTURE_CACHE.computeIfAbsent(animatable.getOverlayLocation(), ResourceLocation::tryParse);
    }

    @Override
    public void render(PoseStack poseStack, OHorse animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        Player player = Minecraft.getInstance().player;
        double distanceSq = animatable.distanceToSqr(player);
        boolean atCullDistance = distanceSq > LivestockOverhaulClientConfig.CULL_LAYERS_DISTANCE.get();
        if (atCullDistance || LivestockOverhaulClientConfig.DISABLE_MARKINGS.get()) return;

        if (animatable.getDecompVariant() != 4 && animatable.getDecompVariant() != 5 && animatable.getDecompVariant() != 6) {
            if (animatable.getOverlayVariant() == 0) return;
            if (animatable.getOverlayVariant() != 0) {
                EquineMarkingOverlay overlay = EquineMarkingOverlay.overlayFromOrdinal(animatable.getOverlayVariant());
                RenderType renderMarkingType = RenderType.entityCutout(overlay.resourceLocation);
                getRenderer().reRender(getDefaultBakedModel(animatable),
                        poseStack,
                        bufferSource,
                        animatable,
                        renderMarkingType,
                        bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                        1, 1, 1, 1);

                RenderType renderMarkingTypeDynamic = RenderType.entityCutout(this.getTexture(animatable));
                getRenderer().reRender(getDefaultBakedModel(animatable),
                        poseStack,
                        bufferSource,
                        animatable,
                        renderMarkingTypeDynamic,
                        bufferSource.getBuffer(renderMarkingTypeDynamic), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                        1, 1, 1, 1);
            }

            if (!(animatable.getDecompVariant() > 0)) {
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
        }

        if (!animatable.isUndead()) return;
        if (animatable.isUndead() && !animatable.isHallow()) {
            UndeadStage overlay = UndeadStage.overlayFromOrdinal(animatable.getDecompVariant());
            RenderType renderMarkingType = RenderType.entityCutout(overlay.resourceLocation);
            getRenderer().reRender(getDefaultBakedModel(animatable),
                    poseStack,
                    bufferSource,
                    animatable,
                    renderMarkingType,
                    bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);
        }
    }

    public enum UndeadStage {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/none.png")),
        MINIMAL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/undead/undead_stage_1.png")),
        MODERATE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/undead/undead_stage_2.png")),
        EXTREME(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/undead/undead_stage_3.png")),
        SKELETAL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/undead/undead_stage_4.png")),
        WITHER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/undead/wither.png")),
        STRAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/undead/stray.png")),
        DROWNED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/undead/drowned.png")),
        HUSK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/undead/husk.png")),
        ;

        public final ResourceLocation resourceLocation;
        UndeadStage(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static UndeadStage overlayFromOrdinal(int overlay) { return UndeadStage.values()[overlay % values().length];
        }
    }

}
