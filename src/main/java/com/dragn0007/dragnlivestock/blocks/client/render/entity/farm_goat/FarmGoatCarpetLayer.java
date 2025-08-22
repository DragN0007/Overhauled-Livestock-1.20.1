package com.dragn0007.dragnlivestock.blocks.client.render.entity.farm_goat;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.FarmGoat;
import com.dragn0007.dragnlivestock.common.LOTags;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WoolCarpetBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

@OnlyIn(Dist.CLIENT)
public class FarmGoatCarpetLayer extends GeoRenderLayer<FarmGoat> {
    public static final ResourceLocation[] TEXTURE_LOCATION = new ResourceLocation[]{
            LivestockOverhaul.id("textures/entity/goat/carpet/white.png"),
            LivestockOverhaul.id("textures/entity/goat/carpet/orange.png"),
            LivestockOverhaul.id("textures/entity/goat/carpet/magenta.png"),
            LivestockOverhaul.id("textures/entity/goat/carpet/light_blue.png"),
            LivestockOverhaul.id("textures/entity/goat/carpet/yellow.png"),
            LivestockOverhaul.id("textures/entity/goat/carpet/lime.png"),
            LivestockOverhaul.id("textures/entity/goat/carpet/pink.png"),
            LivestockOverhaul.id("textures/entity/goat/carpet/grey.png"),
            LivestockOverhaul.id("textures/entity/goat/carpet/light_grey.png"),
            LivestockOverhaul.id("textures/entity/goat/carpet/cyan.png"),
            LivestockOverhaul.id("textures/entity/goat/carpet/purple.png"),
            LivestockOverhaul.id("textures/entity/goat/carpet/blue.png"),
            LivestockOverhaul.id("textures/entity/goat/carpet/brown.png"),
            LivestockOverhaul.id("textures/entity/goat/carpet/green.png"),
            LivestockOverhaul.id("textures/entity/goat/carpet/red.png"),
            LivestockOverhaul.id("textures/entity/goat/carpet/black.png")
    };

    public FarmGoatCarpetLayer(GeoRenderer<FarmGoat> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, FarmGoat animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        ItemStack itemStack = animatable.getDecorItem();
        if(!itemStack.isEmpty()) {
            ResourceLocation resourceLocation;
            if(itemStack.is(LOTags.Items.CARPET_BLANKETS)) {
                DyeColor dyeColor = ((WoolCarpetBlock) Block.byItem(itemStack.getItem())).getColor();
                resourceLocation = TEXTURE_LOCATION[dyeColor.getId()];
            } else {
                DyeColor dyeColor = ((DyeItem)itemStack.getItem()).getDyeColor();
                resourceLocation = TEXTURE_LOCATION[dyeColor.getId()];
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
}
