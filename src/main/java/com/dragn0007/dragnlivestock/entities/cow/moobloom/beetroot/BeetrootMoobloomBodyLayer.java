package com.dragn0007.dragnlivestock.entities.cow.moobloom.beetroot;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.util.marking_layer.BovineMarkingOverlay;
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

public class BeetrootMoobloomBodyLayer extends GeoRenderLayer<BeetrootMoobloom> {
    public BeetrootMoobloomBodyLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, BeetrootMoobloom animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        Player player = Minecraft.getInstance().player;
        double distanceSq = animatable.distanceToSqr(player);
        boolean atCullDistance = distanceSq > LivestockOverhaulClientConfig.CULL_LAYERS_DISTANCE.get();
        if (atCullDistance) return;

        if (animatable.getOverlayVariant() != 0) {
            BovineMarkingOverlay overlay = BovineMarkingOverlay.overlayFromOrdinal(animatable.getOverlayVariant());
            RenderType renderMarkingType = RenderType.entityCutout(overlay.resourceLocation);
            getRenderer().reRender(getDefaultBakedModel(animatable),
                    poseStack,
                    bufferSource,
                    animatable,
                    renderMarkingType,
                    bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);
        }

        if (!animatable.isTagged() || !LivestockOverhaulClientConfig.RENDER_BRAND_TAGS.get())
            return;
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

}
