package com.dragn0007.dragnlivestock.entities.mule;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.items.custom.*;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SaddleItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WoolCarpetBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class OMuleTackLayer extends GeoRenderLayer<OMule> {
    public OMuleTackLayer(GeoRenderer<OMule> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OMule animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        Player player = Minecraft.getInstance().player;
        double distanceSq = animatable.distanceToSqr(player);
        boolean atCullDistance = distanceSq > LivestockOverhaulClientConfig.CULL_LAYERS_DISTANCE.get();
        if (atCullDistance && !animatable.isVehicle()) return;

        ItemStack saddleStack = animatable.getSaddleItem();
        ItemStack decorStack = animatable.getDecorItem();
        List<ItemStack> armorSlots = (List<ItemStack>) animatable.getArmorSlots();
        ItemStack armorItemStack = armorSlots.get(2);

        ResourceLocation resourceLocation = null;
        if (saddleStack.isEmpty() && armorItemStack.isEmpty() && decorStack.isEmpty()) return;

        if(!saddleStack.isEmpty()) {
            if (saddleStack.getItem() instanceof SaddleItem saddleItem && !animatable.isWearingHarness()) {
                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/tack/" + saddleItem + ".png");
                if (resourceLocation != null) {
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
            if (animatable.isSaddled()) {
                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/tack/stone_horseshoes.png");
                if (resourceLocation != null) {
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

        if (!armorItemStack.isEmpty()) {
            String armorpath;
            armorpath = "textures/entity/horse/armor/";
            if (!armorItemStack.isEmpty()) {
                if (armorItemStack.getItem() == LOItems.OBSIDIAN_HORSE_ARMOR.get()) {
                    resourceLocation = new ResourceLocation("medievalembroidery", armorpath + "obsidian_horse_armor.png");
                } else if (armorItemStack.getItem() == LOItems.MINIMAL_OBSIDIAN_HORSE_ARMOR.get()) {
                    resourceLocation = new ResourceLocation("medievalembroidery", armorpath + "minimal_obsidian_horse_armor.png");
                } else if (armorItemStack.getItem() == LOItems.RIOT_HORSE_ARMOR.get()) {
                    resourceLocation = new ResourceLocation("deadlydinos", "textures/entity/horse/armor/riot_horse_armor.png");
                } else if (armorItemStack.getItem() instanceof HorseArmorItem horseArmorItem) {
                    resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, armorpath + horseArmorItem + ".png");
                } else if (armorItemStack.getItem() instanceof LightHorseArmorItem horseArmorItem) {
                    resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, armorpath + horseArmorItem + ".png");
                } else if ((armorItemStack.getItem() instanceof RumpStrapItem rumpStrapItem) && !armorItemStack.isEmpty()) {
                    resourceLocation = new ResourceLocation("medievalembroidery", "textures/entity/horse/caparison/" + rumpStrapItem + ".png");
                } else if ((armorItemStack.getItem() instanceof CaparisonItem caparisonItem) && !armorItemStack.isEmpty()) {
                    resourceLocation = new ResourceLocation("medievalembroidery", "textures/entity/horse/caparison/" + caparisonItem + ".png");
                } else if (armorItemStack.is(LOTags.Items.COSMETICS)) {
                    String item = armorItemStack.getItem().toString();
                    resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/tack/" + item + ".png");
                } else if (armorItemStack.getItem() instanceof HarnessItem item) {
                    resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/tack/" + item + ".png");
                } else {
                    return;
                }
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

        //anything below this point is not included in simple models
        if (!decorStack.isEmpty() && !(decorStack.getItem() instanceof CaparisonItem)) {
            String color = "";
            if (decorStack.getItem() instanceof BlanketItem blanketItem) {
                color = blanketItem.getColor().toString();
            }
            if (decorStack.is(LOTags.Items.CARPET_BLANKETS)) {
                color = ((WoolCarpetBlock) Block.byItem(decorStack.getItem())).getColor().toString();
            }
            if (!decorStack.isEmpty()) {
                if (!armorItemStack.isEmpty() &&
                        !(decorStack.getItem() instanceof CaparisonItem) && !(decorStack.getItem() instanceof RumpStrapItem) &&
                        !(armorItemStack.getItem() instanceof CaparisonItem) && !(armorItemStack.getItem() instanceof RumpStrapItem) &&
                        !(decorStack.getItem() instanceof CosmeticsItem)) {
                    if (!(armorItemStack.getItem() == LOItems.RIOT_HORSE_ARMOR.get()) && !animatable.isWearingHarness()) {
                        if (armorItemStack.getItem() == Items.LEATHER_HORSE_ARMOR) {
                            if (decorStack.getItem() instanceof BlanketItem || decorStack.is(LOTags.Items.CARPET_BLANKETS))
                            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/" + color + ".png");
                        } else if (armorItemStack.getItem() == LOItems.MINIMAL_LEATHER_HORSE_ARMOR.get()) {
                            if (decorStack.getItem() instanceof BlanketItem || decorStack.is(LOTags.Items.CARPET_BLANKETS))
                            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/" + color + "_minimal.png");
                        } else if (decorStack.is(LOTags.Items.CARPET_BLANKETS) || decorStack.is(LOTags.Items.MEDIEVAL_BLANKETS) || decorStack.is(LOTags.Items.MODERN_BLANKETS) || decorStack.is(LOTags.Items.RACING_BLANKETS) || decorStack.is(LOTags.Items.WESTERN_BLANKETS)) {
                            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/carpet/" + color + ".png");
                        } else if (decorStack.getItem() instanceof BlanketItem blanketItem) {
                            String name = blanketItem.toString();
                            String noSuffix = name.replaceAll("_.+", "");
                            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/carpet/special/" + noSuffix + "_armor_blanket.png");
                        }
                    }
                }

                if (armorItemStack.isEmpty()) {
                    if (decorStack.is(LOTags.Items.CARPET_BLANKETS)) {
                        resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/classic/" +
                                ((WoolCarpetBlock) Block.byItem(decorStack.getItem())).getColor() + ".png");
                    } else if (decorStack.is(LOTags.Items.MEDIEVAL_BLANKETS)) {
                        resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/medieval/" + color + ".png");
                    } else if (decorStack.is(LOTags.Items.MODERN_BLANKETS)) {
                        resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/modern/" + color + ".png");
                    } else if (decorStack.is(LOTags.Items.RACING_BLANKETS)) {
                        resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/racing/" + color + ".png");
                    } else if (decorStack.is(LOTags.Items.WESTERN_BLANKETS)) {
                        resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/western/" + color + ".png");
                    } else if (decorStack.is(LOTags.Items.SPECIAL_BLANKETS)) {
                        String blanketItem = decorStack.getItem().toString();
                        resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/special/" + blanketItem + ".png");
                    }
                }

                if (resourceLocation != null) {
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
    }
}
