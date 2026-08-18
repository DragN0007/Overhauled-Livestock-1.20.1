package com.dragn0007.dragnlivestock.entities.camel;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WoolCarpetBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

@OnlyIn(Dist.CLIENT)
public class OCamelTackLayer extends GeoRenderLayer<OCamel> {
    public OCamelTackLayer(GeoRenderer<OCamel> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OCamel animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        ResourceLocation resourceLocation = null;
        ItemStack itemStack = animatable.getDecorItem();

        if (animatable.isSaddled()) {
            if (!LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/tack/saddle.png");
            } else {
                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/config_simplified/camel/tack/saddle.png");
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

        if (animatable.hasChest()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/tack/saddlebags.png");
            RenderType renderType1 = RenderType.entityCutout(resourceLocation);
            getRenderer().reRender(getDefaultBakedModel(animatable),
                    poseStack,
                    bufferSource,
                    animatable,
                    renderType1,
                    bufferSource.getBuffer(renderType1), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);
        }

        if (LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) return;

        if(!itemStack.isEmpty()) {
            if (!itemStack.is(LOTags.Items.CAMEL_ARMOR) && !(itemStack.getItem() instanceof HorseArmorItem)) {
                if (itemStack.is(LOTags.Items.CARPET_BLANKETS)) {
                    DyeColor color = ((WoolCarpetBlock) Block.byItem(itemStack.getItem())).getColor();
                    resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/carpet/" + color + ".png");
                } else {
                    DyeColor color = ((DyeItem) itemStack.getItem()).getDyeColor();
                    resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/carpet/" + color + ".png");
                }
            } else if (itemStack.is(LOTags.Items.CAMEL_ARMOR) && itemStack.getItem() instanceof HorseArmorItem horseArmorItem && !LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/camel/armor/" + horseArmorItem + ".png");
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
