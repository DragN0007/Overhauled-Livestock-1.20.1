package com.dragn0007.dragnlivestock.entities.donkey;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.horse.OHorseCarpetLayer;
import com.dragn0007.dragnlivestock.entities.mule.OMule;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WoolCarpetBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ODonkeyCarpetLayer extends GeoRenderLayer<ODonkey> {

    public ODonkeyCarpetLayer(GeoRenderer<ODonkey> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, ODonkey animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        ItemStack itemStack = animatable.getDecorItem();
        List<ItemStack> armorSlots = (List<ItemStack>) animatable.getArmorSlots();
        ItemStack armorItemStack = armorSlots.get(2);

        ResourceLocation resourceLocation = null;

        if (!armorItemStack.isEmpty() && !itemStack.isEmpty()) {
            if (!(armorItemStack.getItem() == LOItems.RIOT_HORSE_ARMOR.get()) && !(armorItemStack.getItem() == LOItems.RODEO_HARNESS.get())) {

                if (armorItemStack.getItem() == Items.LEATHER_HORSE_ARMOR) {
                    if (itemStack.is(LOTags.Items.CARPET_BLANKETS)) {
                        resourceLocation = OHorseCarpetLayer.LEATHER_ARMOR_COLOR[((WoolCarpetBlock) Block.byItem(itemStack.getItem())).getColor().getId()];
                    } else {
                        resourceLocation = OHorseCarpetLayer.LEATHER_ARMOR_COLOR[((DyeItem) itemStack.getItem()).getDyeColor().getId()];
                    }
                } else if (armorItemStack.getItem() == LOItems.MINIMAL_LEATHER_HORSE_ARMOR.get()) {
                    if (itemStack.is(LOTags.Items.CARPET_BLANKETS)) {
                        resourceLocation = OHorseCarpetLayer.MINIMAL_LEATHER_ARMOR_COLOR[((WoolCarpetBlock) Block.byItem(itemStack.getItem())).getColor().getId()];
                    } else {
                        resourceLocation = OHorseCarpetLayer.MINIMAL_LEATHER_ARMOR_COLOR[((DyeItem) itemStack.getItem()).getDyeColor().getId()];
                    }
                } else if (itemStack.is(LOTags.Items.CARPET_BLANKETS)) {
                    resourceLocation = OHorseCarpetLayer.ARMOR_COLOR[((WoolCarpetBlock) Block.byItem(itemStack.getItem())).getColor().getId()];
                } else {
                    resourceLocation = OHorseCarpetLayer.ARMOR_COLOR[((DyeItem) itemStack.getItem()).getDyeColor().getId()];
                }
            }
        }

        if(!itemStack.isEmpty() && armorItemStack.isEmpty()) {
            if (itemStack.is(LOTags.Items.CARPET_BLANKETS)) {
                resourceLocation = OHorseCarpetLayer.CARPET_COLOR[((WoolCarpetBlock) Block.byItem(itemStack.getItem())).getColor().getId()];
            } else if (itemStack.is(LOTags.Items.MEDIEVAL_BLANKETS)) {
                resourceLocation = OHorseCarpetLayer.MEDIEVAL_COLOR[((DyeItem) itemStack.getItem()).getDyeColor().getId()];
            } else if (itemStack.is(LOTags.Items.MODERN_BLANKETS)) {
                resourceLocation = OHorseCarpetLayer.MODERN_COLOR[((DyeItem) itemStack.getItem()).getDyeColor().getId()];
            } else if (itemStack.is(LOTags.Items.RACING_BLANKETS)) {
                resourceLocation = OHorseCarpetLayer.RACING_COLOR[((DyeItem) itemStack.getItem()).getDyeColor().getId()];
            } else if (itemStack.is(LOTags.Items.WESTERN_BLANKETS)) {
                resourceLocation = OHorseCarpetLayer.WESTERN_COLOR[((DyeItem) itemStack.getItem()).getDyeColor().getId()];
            }
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
