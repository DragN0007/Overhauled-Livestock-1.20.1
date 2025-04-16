package com.dragn0007.dragnlivestock.entities.goat;

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
public class OGoatBrandTagLayer extends GeoRenderLayer<OGoat> {
    public static final ResourceLocation[] TEXTURE_LOCATION = new ResourceLocation[]{
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/white_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/orange_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/magenta_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/light_blue_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/yellow_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/lime_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/pink_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/grey_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/light_grey_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/cyan_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/purple_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/blue_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/brown_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/green_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/red_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/black_brand_tag.png")
    };

    public OGoatBrandTagLayer(GeoRenderer<OGoat> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OGoat animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        DyeColor dyeColor = animatable.getBrandTagColor();
        ResourceLocation resourceLocation = null;

        if (dyeColor != null) {
            resourceLocation = TEXTURE_LOCATION[dyeColor.getId()];
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
