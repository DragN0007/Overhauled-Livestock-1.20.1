package com.dragn0007.dragnlivestock.blocks.client.render.entity.mule;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.horse.OHorseCarpetLayer;
import com.dragn0007.dragnlivestock.common.entities.mule.OMule;
import com.dragn0007.dragnlivestock.common.items.LOItems;
import com.dragn0007.dragnlivestock.common.items.custom.BlanketItem;
import com.dragn0007.dragnlivestock.common.LOTags;
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
public class OMuleCarpetLayer extends GeoRenderLayer<OMule> {

    public OMuleCarpetLayer(GeoRenderer<OMule> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OMule animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        ItemStack itemStack = animatable.getDecorItem();
        List<ItemStack> armorSlots = (List<ItemStack>) animatable.getArmorSlots();
        ItemStack armorItemStack = armorSlots.get(2);

        ResourceLocation resourceLocation = null;

        if (!itemStack.isEmpty()) {
            if (!armorItemStack.isEmpty()) {
                if (!(armorItemStack.getItem() == LOItems.RIOT_HORSE_ARMOR.get()) && !animatable.isWearingHarness()) {

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
                    } else if (itemStack.is(LOTags.Items.MEDIEVAL_BLANKETS) || itemStack.is(LOTags.Items.MODERN_BLANKETS) ||
                            itemStack.is(LOTags.Items.RACING_BLANKETS) || itemStack.is(LOTags.Items.WESTERN_BLANKETS)) {
                        resourceLocation = OHorseCarpetLayer.ARMOR_COLOR[((DyeItem) itemStack.getItem()).getDyeColor().getId()];
                    } else if (itemStack.getItem() instanceof BlanketItem blanketItem) {
                        String name = blanketItem.toString();
                        String noSuffix = name.replaceAll("_.+", "");
                        resourceLocation = LivestockOverhaul.id("textures/entity/horse/armor/carpet/special/" + noSuffix + "_armor_blanket.png");
                    }
                }
            }

            if (armorItemStack.isEmpty()) {
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
                } else if (itemStack.getItem() instanceof BlanketItem blanketItem) {
                    resourceLocation = LivestockOverhaul.id("textures/entity/horse/carpet/special/" + blanketItem + ".png");
                }
            }

            if (resourceLocation != null) {
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


        if (animatable.getFlowerItem() != null && animatable.getFlowerItem().is(LOTags.Items.HAIR_FLOWERS)) {
            if (animatable.getFlowerType() == 0) {
                resourceLocation = LivestockOverhaul.id("textures/entity/horse/decor/" + animatable.getFlowerItem().getItem() + "_mane.png");
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

            if (animatable.getFlowerType() == 1) {
                resourceLocation = LivestockOverhaul.id("textures/entity/horse/decor/" + animatable.getFlowerItem().getItem() + "_tail.png");
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

            if (animatable.getFlowerType() == 2) {
                resourceLocation = LivestockOverhaul.id("textures/entity/horse/decor/" + animatable.getFlowerItem().getItem() + "_tail.png");
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

                resourceLocation = LivestockOverhaul.id("textures/entity/horse/decor/" + animatable.getFlowerItem().getItem() + "_mane.png");
                RenderType renderType2 = RenderType.entityCutout(resourceLocation);
                poseStack.pushPose();
                poseStack.scale(1.0f, 1.0f, 1.0f);
                poseStack.translate(0.0d, 0.0d, 0.0d);
                poseStack.popPose();
                getRenderer().reRender(getDefaultBakedModel(animatable),
                        poseStack,
                        bufferSource,
                        animatable,
                        renderType2,
                        bufferSource.getBuffer(renderType2), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                        1, 1, 1, 1);
            }
        }
    }
}
