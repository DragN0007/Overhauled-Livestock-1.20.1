package com.dragn0007.dragnlivestock.entities.chicken;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

@OnlyIn(Dist.CLIENT)
public class OChickenBrandTagLayer extends GeoRenderLayer<OChicken> {
    public static final ResourceLocation[] TEXTURE_LOCATION = new ResourceLocation[]{
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/chicken/white_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/chicken/orange_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/chicken/magenta_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/chicken/light_blue_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/chicken/yellow_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/chicken/lime_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/chicken/pink_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/chicken/grey_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/chicken/light_grey_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/chicken/cyan_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/chicken/purple_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/chicken/blue_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/chicken/brown_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/chicken/green_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/chicken/red_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/chicken/black_brand_tag.png")
    };

    public OChickenBrandTagLayer(GeoRenderer<OChicken> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OChicken animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        DyeColor dyeColor = animatable.getBrandTagColor();
        ResourceLocation resourceLocation = null;

        if (dyeColor != null) {
            resourceLocation = TEXTURE_LOCATION[dyeColor.getId()];
        }

        if (LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
            return;
        }

        if (resourceLocation == null) {
            return;
        }

        if (!animatable.isTagged() || !LivestockOverhaulClientConfig.RENDER_BRAND_TAGS.get()) {
            return;
        }

        RenderType renderType1 = RenderType.entityCutout(resourceLocation);
        poseStack.pushPose();
        poseStack.scale(1.0f, 1.0f, 1.0f);
        poseStack.translate(0.0d, 0.0d, 0.0d);
        poseStack.popPose();
        getRenderer().reRender(getDefaultBakedModel(animatable),
                poseStack,
                bufferSource,
                animatable,
                renderType1,
                bufferSource.getBuffer(renderType1), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                1, 1, 1, 1);
    }
}
