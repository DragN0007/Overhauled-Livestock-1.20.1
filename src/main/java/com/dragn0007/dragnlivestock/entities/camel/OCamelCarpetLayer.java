package com.dragn0007.dragnlivestock.entities.camel;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
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
public class OCamelCarpetLayer extends GeoRenderLayer<OCamel> {
    public static final ResourceLocation[] TEXTURE_LOCATION = new ResourceLocation[]{
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/carpet/white.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/carpet/orange.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/carpet/magenta.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/carpet/light_blue.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/carpet/yellow.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/carpet/lime.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/carpet/pink.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/carpet/grey.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/carpet/light_grey.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/carpet/cyan.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/carpet/purple.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/carpet/blue.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/carpet/brown.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/carpet/green.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/carpet/red.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/carpet/black.png")
    };

    public OCamelCarpetLayer(GeoRenderer<OCamel> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OCamel animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        DyeColor dyeColor = animatable.getCarpet();
        ResourceLocation resourceLocation = null;

        if (dyeColor != null) {
            resourceLocation = TEXTURE_LOCATION[dyeColor.getId()];
        }

        if (resourceLocation == null) {
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
