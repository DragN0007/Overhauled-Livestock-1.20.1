package com.dragn0007.dragnlivestock.entities.mule;

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

public class OMuleEyeLayer extends GeoRenderLayer<OMule> {
    public OMuleEyeLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OMule animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

        RenderType renderMarkingType = RenderType.entityCutout(((OMule)animatable).getEyeTextureResource());
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

    public enum EyeOverlay {
        //from most common to least common
        DARK_BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/eyes/dark_brown.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/eyes/brown.png")),
        AMBER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/eyes/amber.png")),
        GOLD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/eyes/gold.png")),
        DARK_BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/eyes/dark_blue.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/eyes/blue.png")),
        GREEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/eyes/green.png")),
        BLUE_GOLD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/eyes/heterochromic_blue_and_gold.png")),
        BROWN_GREEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/eyes/heterochromic_brown_and_green.png")),
        BROWN_BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/eyes/heterochromic_dark_brown_and_blue.png"));

        public final ResourceLocation resourceLocation;
        EyeOverlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static EyeOverlay eyesFromOrdinal(int eyes) { return EyeOverlay.values()[eyes % EyeOverlay.values().length];
        }
    }

}
