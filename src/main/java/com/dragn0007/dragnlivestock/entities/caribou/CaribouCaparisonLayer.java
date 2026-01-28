package com.dragn0007.dragnlivestock.entities.caribou;

import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.items.custom.CaparisonItem;
import com.dragn0007.dragnlivestock.items.custom.RumpStrapItem;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

@OnlyIn(Dist.CLIENT)
public class CaribouCaparisonLayer extends GeoRenderLayer<Caribou> {

    public CaribouCaparisonLayer(GeoRenderer<Caribou> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, Caribou animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        ItemStack itemStack = animatable.getDecorItem();

        if (LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
            return;
        }

        ResourceLocation resourceLocation = null;

        if ((itemStack.getItem() instanceof CaparisonItem caparisonItem) && !itemStack.isEmpty()) {
            resourceLocation = new ResourceLocation("medievalembroidery", "textures/entity/horse/caparison/" + caparisonItem + ".png");
        } else if ((itemStack.getItem() instanceof RumpStrapItem rumpStrapItem) && !itemStack.isEmpty()) {
            resourceLocation = new ResourceLocation("medievalembroidery", "textures/entity/horse/caparison/" + rumpStrapItem + ".png");
        }

        if(resourceLocation != null) {
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
}
