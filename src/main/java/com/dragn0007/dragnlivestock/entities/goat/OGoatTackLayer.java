package com.dragn0007.dragnlivestock.entities.goat;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WoolCarpetBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

@OnlyIn(Dist.CLIENT)
public class OGoatTackLayer extends GeoRenderLayer<OGoat> {
    public OGoatTackLayer(GeoRenderer<OGoat> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OGoat animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        ResourceLocation resourceLocation = null;

        if (animatable.hasChest()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/goat/tack/saddlebags.png");
            RenderType renderType1 = RenderType.entityCutout(resourceLocation);
            getRenderer().reRender(getDefaultBakedModel(animatable),
                    poseStack,
                    bufferSource,
                    animatable,
                    renderType1,
                    bufferSource.getBuffer(renderType1), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);
        }

        ItemStack itemStack = animatable.getDecorItem();
        if(!itemStack.isEmpty()) {
            if(itemStack.is(LOTags.Items.CARPET_BLANKETS)) {
                String color = ((WoolCarpetBlock) Block.byItem(itemStack.getItem())).getColor().toString();
                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/goat/carpet/" + color + ".png");
            } else {
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
}
