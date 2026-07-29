package com.dragn0007.dragnlivestock.entities.unicorn;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

@OnlyIn(Dist.CLIENT)
public class UnicornCarpetLayer extends GeoRenderLayer<Unicorn> {

    public UnicornCarpetLayer(GeoRenderer<Unicorn> entityRendererIn) {
        super(entityRendererIn);
    }

//    @Override
//    public void render(PoseStack poseStack, Unicorn animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
//        ItemStack itemStack = animatable.getDecorItem();
//        List<ItemStack> armorSlots = (List<ItemStack>) animatable.getArmorSlots();
//        ItemStack armorItemStack = armorSlots.get(2);
//
//        ResourceLocation resourceLocation = null;
//
//        if (!itemStack.isEmpty()) {
//            if (!armorItemStack.isEmpty() &&
//                    !(itemStack.getItem() instanceof CaparisonItem) &&
//                    !(itemStack.getItem() instanceof RumpStrapItem) &&
//                    !(armorItemStack.getItem() instanceof CaparisonItem) &&
//                    !(armorItemStack.getItem() instanceof RumpStrapItem) && !(itemStack.getItem() instanceof CosmeticsItem)) {
//                if (!(armorItemStack.getItem() == LOItems.RIOT_HORSE_ARMOR.get()) && !animatable.isWearingHarness()) {
//
//                    if (armorItemStack.getItem() == Items.LEATHER_HORSE_ARMOR) {
//                        if (itemStack.is(LOTags.Items.CARPET_BLANKETS)) {
//                            resourceLocation = OHorseCarpetLayer.LEATHER_ARMOR_COLOR[((WoolCarpetBlock) Block.byItem(itemStack.getItem())).getColor().getId()];
//                        } else {
//                            if (itemStack.getItem() instanceof BlanketItem) {
//                                resourceLocation = OHorseCarpetLayer.LEATHER_ARMOR_COLOR[((BlanketItem) itemStack.getItem()).getColor().getId()];
//                            }
//                        }
//                    } else if (armorItemStack.getItem() == LOItems.MINIMAL_LEATHER_HORSE_ARMOR.get()) {
//                        if (itemStack.is(LOTags.Items.CARPET_BLANKETS)) {
//                            resourceLocation = OHorseCarpetLayer.MINIMAL_LEATHER_ARMOR_COLOR[((WoolCarpetBlock) Block.byItem(itemStack.getItem())).getColor().getId()];
//                        } else {
//                            if (itemStack.getItem() instanceof BlanketItem) {
//                                resourceLocation = OHorseCarpetLayer.MINIMAL_LEATHER_ARMOR_COLOR[((BlanketItem) itemStack.getItem()).getColor().getId()];
//                            }
//                        }
//                    } else if (itemStack.is(LOTags.Items.CARPET_BLANKETS)) {
//                        resourceLocation = OHorseCarpetLayer.ARMOR_COLOR[((WoolCarpetBlock) Block.byItem(itemStack.getItem())).getColor().getId()];
//                    } else if (itemStack.is(LOTags.Items.MEDIEVAL_BLANKETS) || itemStack.is(LOTags.Items.MODERN_BLANKETS) ||
//                            itemStack.is(LOTags.Items.RACING_BLANKETS) || itemStack.is(LOTags.Items.WESTERN_BLANKETS)) {
//                        resourceLocation = OHorseCarpetLayer.ARMOR_COLOR[((BlanketItem) itemStack.getItem()).getColor().getId()];
//                    } else if (itemStack.getItem() instanceof BlanketItem blanketItem) {
//                        String name = blanketItem.toString();
//                        String noSuffix = name.replaceAll("_.+", "");
//                        resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/carpet/special/" + noSuffix + "_armor_blanket.png");
//                    }
//                }
//            }
//
//            if ((armorItemStack.isEmpty() && !(itemStack.getItem() instanceof CaparisonItem)) ||
//                    (((armorItemStack.getItem() instanceof CaparisonItem) || (armorItemStack.getItem() instanceof RumpStrapItem)))) {
//                if (itemStack.is(LOTags.Items.CARPET_BLANKETS)) {
//                    resourceLocation = OHorseCarpetLayer.CARPET_COLOR[((WoolCarpetBlock) Block.byItem(itemStack.getItem())).getColor().getId()];
//                } else if (itemStack.is(LOTags.Items.MEDIEVAL_BLANKETS)) {
//                    resourceLocation = OHorseCarpetLayer.MEDIEVAL_COLOR[((BlanketItem) itemStack.getItem()).getColor().getId()];
//                } else if (itemStack.is(LOTags.Items.MODERN_BLANKETS)) {
//                    resourceLocation = OHorseCarpetLayer.MODERN_COLOR[((BlanketItem) itemStack.getItem()).getColor().getId()];
//                } else if (itemStack.is(LOTags.Items.RACING_BLANKETS)) {
//                    resourceLocation = OHorseCarpetLayer.RACING_COLOR[((BlanketItem) itemStack.getItem()).getColor().getId()];
//                } else if (itemStack.is(LOTags.Items.WESTERN_BLANKETS)) {
//                    resourceLocation = OHorseCarpetLayer.WESTERN_COLOR[((BlanketItem) itemStack.getItem()).getColor().getId()];
//                } else if (itemStack.is(LOTags.Items.SPECIAL_BLANKETS)) {
//                    String blanketItem = itemStack.getItem().toString();
//                    resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/special/" + blanketItem + ".png");
//                }
//            }
//
//            if (resourceLocation != null) {
//                RenderType renderType1 = RenderType.entityCutout(resourceLocation);
//                poseStack.pushPose();
//                poseStack.scale(1.0f, 1.0f, 1.0f);
//                poseStack.translate(0.0d, 0.0d, 0.0d);
//                poseStack.popPose();
//                getRenderer().reRender(getDefaultBakedModel(animatable),
//                        poseStack,
//                        bufferSource,
//                        animatable,
//                        renderType1,
//                        bufferSource.getBuffer(renderType1), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
//                        1, 1, 1, 1);
//            }
//        }
//
//
//        if (animatable.getFlowerItem() != null && animatable.getFlowerItem().is(LOTags.Items.HAIR_FLOWERS)) {
//            if (animatable.getFlowerType() == 0) {
//                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/decor/" + animatable.getFlowerItem().getItem() + "_mane.png");
//                RenderType renderType1 = RenderType.entityCutout(resourceLocation);
//                poseStack.pushPose();
//                poseStack.scale(1.0f, 1.0f, 1.0f);
//                poseStack.translate(0.0d, 0.0d, 0.0d);
//                poseStack.popPose();
//                getRenderer().reRender(getDefaultBakedModel(animatable),
//                        poseStack,
//                        bufferSource,
//                        animatable,
//                        renderType1,
//                        bufferSource.getBuffer(renderType1), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
//                        1, 1, 1, 1);
//            }
//
//            if (animatable.getFlowerType() == 1) {
//                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/decor/" + animatable.getFlowerItem().getItem() + "_tail.png");
//                RenderType renderType1 = RenderType.entityCutout(resourceLocation);
//                poseStack.pushPose();
//                poseStack.scale(1.0f, 1.0f, 1.0f);
//                poseStack.translate(0.0d, 0.0d, 0.0d);
//                poseStack.popPose();
//                getRenderer().reRender(getDefaultBakedModel(animatable),
//                        poseStack,
//                        bufferSource,
//                        animatable,
//                        renderType1,
//                        bufferSource.getBuffer(renderType1), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
//                        1, 1, 1, 1);
//            }
//
//            if (animatable.getFlowerType() == 2) {
//                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/decor/" + animatable.getFlowerItem().getItem() + "_tail.png");
//                RenderType renderType1 = RenderType.entityCutout(resourceLocation);
//                poseStack.pushPose();
//                poseStack.scale(1.0f, 1.0f, 1.0f);
//                poseStack.translate(0.0d, 0.0d, 0.0d);
//                poseStack.popPose();
//                getRenderer().reRender(getDefaultBakedModel(animatable),
//                        poseStack,
//                        bufferSource,
//                        animatable,
//                        renderType1,
//                        bufferSource.getBuffer(renderType1), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
//                        1, 1, 1, 1);
//
//                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/decor/" + animatable.getFlowerItem().getItem() + "_mane.png");
//                RenderType renderType2 = RenderType.entityCutout(resourceLocation);
//                poseStack.pushPose();
//                poseStack.scale(1.0f, 1.0f, 1.0f);
//                poseStack.translate(0.0d, 0.0d, 0.0d);
//                poseStack.popPose();
//                getRenderer().reRender(getDefaultBakedModel(animatable),
//                        poseStack,
//                        bufferSource,
//                        animatable,
//                        renderType2,
//                        bufferSource.getBuffer(renderType2), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
//                        1, 1, 1, 1);
//            }
//        }
//    }
}
