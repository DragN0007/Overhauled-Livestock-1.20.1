package com.dragn0007.dragnlivestock.entities.mule;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.util.LOTags;
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
public class OMuleCarpetLayer extends GeoRenderLayer<OMule> {
    public static final ResourceLocation[] CARPET_COLOR = new ResourceLocation[]{
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/white.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/orange.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/magenta.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/light_blue.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/yellow.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/lime.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/pink.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/grey.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/light_grey.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/cyan.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/purple.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/blue.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/brown.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/green.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/red.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/black.png")
    };

    public static final ResourceLocation[] MEDIEVAL_COLOR = new ResourceLocation[]{
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/medieval/white.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/medieval/orange.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/medieval/magenta.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/medieval/light_blue.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/medieval/yellow.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/medieval/lime.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/medieval/pink.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/medieval/grey.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/medieval/light_grey.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/medieval/cyan.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/medieval/purple.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/medieval/blue.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/medieval/brown.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/medieval/green.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/medieval/red.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/medieval/black.png")
    };

    public static final ResourceLocation[] MODERN_COLOR = new ResourceLocation[]{
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/modern/white.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/modern/orange.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/modern/magenta.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/modern/light_blue.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/modern/yellow.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/modern/lime.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/modern/pink.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/modern/grey.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/modern/light_grey.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/modern/cyan.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/modern/purple.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/modern/blue.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/modern/brown.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/modern/green.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/modern/red.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/modern/black.png")
    };

    public static final ResourceLocation[] RACING_COLOR = new ResourceLocation[]{
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/racing/white.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/racing/orange.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/racing/magenta.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/racing/light_blue.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/racing/yellow.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/racing/lime.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/racing/pink.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/racing/grey.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/racing/light_grey.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/racing/cyan.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/racing/purple.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/racing/blue.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/racing/brown.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/racing/green.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/racing/red.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/racing/black.png")
    };

    public static final ResourceLocation[] WESTERN_COLOR = new ResourceLocation[]{
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/western/white.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/western/orange.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/western/magenta.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/western/light_blue.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/western/yellow.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/western/lime.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/western/pink.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/western/grey.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/western/light_grey.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/western/cyan.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/western/purple.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/western/blue.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/western/brown.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/western/green.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/western/red.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/western/black.png")
    };

    public OMuleCarpetLayer(GeoRenderer<OMule> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OMule animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        DyeColor dyeColor = animatable.getCarpet();
        ResourceLocation resourceLocation = null;

        if (dyeColor != null) {
            resourceLocation = MEDIEVAL_COLOR[dyeColor.getId()];

//            if (animatable.getCarpet().getTag() == LOTags.Items.CARPET_BLANKETS) {
//                resourceLocation = CARPET_COLOR[dyeColor.getId()];
//            }
//            if (animatable.getCarpet().getTag() == LOTags.Items.MEDIEVAL_BLANKETS) {
//                resourceLocation = MEDIEVAL_COLOR[dyeColor.getId()];
//            }
//            if (animatable.getCarpet().getTag() == LOTags.Items.MODERN_BLANKETS) {
//                resourceLocation = MODERN_COLOR[dyeColor.getId()];
//            }
//            if (animatable.getCarpet().getTag() == LOTags.Items.RACING_BLANKETS) {
//                resourceLocation = RACING_COLOR[dyeColor.getId()];
//            }
//            if (animatable.getCarpet().getTag() == LOTags.Items.WESTERN_BLANKETS) {
//                resourceLocation = WESTERN_COLOR[dyeColor.getId()];
//            }
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
