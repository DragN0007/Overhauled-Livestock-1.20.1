package com.dragn0007.dragnlivestock.entities.llama;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

@OnlyIn(Dist.CLIENT)
public class OLlamaTackLayer extends GeoRenderLayer<OLlama> {
    public static final ResourceLocation[] TEXTURE_LOCATION = new ResourceLocation[]{
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/carpet/white.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/carpet/orange.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/carpet/magenta.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/carpet/light_blue.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/carpet/yellow.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/carpet/lime.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/carpet/pink.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/carpet/grey.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/carpet/light_grey.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/carpet/cyan.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/carpet/purple.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/carpet/blue.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/carpet/brown.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/carpet/green.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/carpet/red.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/carpet/black.png")
    };

    public OLlamaTackLayer(GeoRenderer<OLlama> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OLlama animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        Player player = Minecraft.getInstance().player;
        double distanceSq = animatable.distanceToSqr(player);
        boolean atCullDistance = distanceSq > LivestockOverhaulClientConfig.CULL_LAYERS_DISTANCE.get();
        if (atCullDistance && !animatable.isVehicle()) return;

        DyeColor dyeColor = animatable.getSwag();
        ResourceLocation resourceLocation = null;

        if (dyeColor != null) {
            resourceLocation = TEXTURE_LOCATION[dyeColor.getId()];
        }

        if (animatable.hasChest()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/llama/tack/saddlebags.png");
        }

        if (resourceLocation == null) {
            return;
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