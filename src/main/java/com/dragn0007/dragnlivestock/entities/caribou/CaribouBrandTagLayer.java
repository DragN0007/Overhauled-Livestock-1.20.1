package com.dragn0007.dragnlivestock.entities.caribou;

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
public class CaribouBrandTagLayer extends GeoRenderLayer<Caribou> {
    public static final ResourceLocation[] TEXTURE_LOCATION = new ResourceLocation[]{
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/caribou/white_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/caribou/orange_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/caribou/magenta_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/caribou/light_blue_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/caribou/yellow_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/caribou/lime_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/caribou/pink_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/caribou/grey_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/caribou/light_grey_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/caribou/cyan_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/caribou/purple_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/caribou/blue_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/caribou/brown_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/caribou/green_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/caribou/red_brand_tag.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/caribou/black_brand_tag.png")
    };

    public CaribouBrandTagLayer(GeoRenderer<Caribou> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, Caribou animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
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
