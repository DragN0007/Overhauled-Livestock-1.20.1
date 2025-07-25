package com.dragn0007.dragnlivestock.entities.horse;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.items.custom.BlanketItem;
import com.dragn0007.dragnlivestock.items.custom.CaparisonItem;
import com.dragn0007.dragnlivestock.items.custom.RumpStrapItem;
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
public class OHorseCarpetLayer extends GeoRenderLayer<OHorse> {
    public static final ResourceLocation[] CARPET_COLOR = new ResourceLocation[]{
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/classic/white.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/classic/orange.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/classic/magenta.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/classic/light_blue.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/classic/yellow.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/classic/lime.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/classic/pink.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/classic/grey.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/classic/light_grey.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/classic/cyan.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/classic/purple.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/classic/blue.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/classic/brown.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/classic/green.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/classic/red.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/classic/black.png")
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

    public static final ResourceLocation[] ARMOR_COLOR = new ResourceLocation[]{
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/carpet/white.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/carpet/orange.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/carpet/magenta.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/carpet/light_blue.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/carpet/yellow.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/carpet/lime.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/carpet/pink.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/carpet/grey.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/carpet/light_grey.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/carpet/cyan.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/carpet/purple.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/carpet/blue.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/carpet/brown.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/carpet/green.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/carpet/red.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/carpet/black.png")
    };

    public static final ResourceLocation[] LEATHER_ARMOR_COLOR = new ResourceLocation[]{
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/white.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/orange.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/magenta.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/light_blue.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/yellow.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/lime.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/pink.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/grey.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/light_grey.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/cyan.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/purple.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/blue.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/brown.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/green.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/red.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/black.png")
    };

    public static final ResourceLocation[] MINIMAL_LEATHER_ARMOR_COLOR = new ResourceLocation[]{
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/white_minimal.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/orange_minimal.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/magenta_minimal.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/light_blue_minimal.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/yellow_minimal.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/lime_minimal.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/pink_minimal.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/grey_minimal.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/light_grey_minimal.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/cyan_minimal.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/purple_minimal.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/blue_minimal.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/brown_minimal.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/green_minimal.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/red_minimal.png"),
            new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/leather/black_minimal.png")
    };

    public OHorseCarpetLayer(GeoRenderer<OHorse> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OHorse animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        ItemStack itemStack = animatable.getDecorItem();
        List<ItemStack> armorSlots = (List<ItemStack>) animatable.getArmorSlots();
        ItemStack armorItemStack = armorSlots.get(2);

        ResourceLocation resourceLocation = null;

        if (!itemStack.isEmpty()) {
            if (!armorItemStack.isEmpty() &&
                    !(itemStack.getItem() instanceof CaparisonItem) &&
                    !(itemStack.getItem() instanceof RumpStrapItem) &&
                    !(armorItemStack.getItem() instanceof CaparisonItem) &&
                    !(armorItemStack.getItem() instanceof RumpStrapItem)) {
                if (!(armorItemStack.getItem() == LOItems.RIOT_HORSE_ARMOR.get()) && !animatable.isWearingHarness()) {

                    if (armorItemStack.getItem() == Items.LEATHER_HORSE_ARMOR) {
                        if (itemStack.is(LOTags.Items.CARPET_BLANKETS)) {
                            resourceLocation = LEATHER_ARMOR_COLOR[((WoolCarpetBlock) Block.byItem(itemStack.getItem())).getColor().getId()];
                        } else {
                            resourceLocation = LEATHER_ARMOR_COLOR[((DyeItem) itemStack.getItem()).getDyeColor().getId()];
                        }
                    } else if (armorItemStack.getItem() == LOItems.MINIMAL_LEATHER_HORSE_ARMOR.get()) {
                        if (itemStack.is(LOTags.Items.CARPET_BLANKETS)) {
                            resourceLocation = MINIMAL_LEATHER_ARMOR_COLOR[((WoolCarpetBlock) Block.byItem(itemStack.getItem())).getColor().getId()];
                        } else {
                            resourceLocation = MINIMAL_LEATHER_ARMOR_COLOR[((DyeItem) itemStack.getItem()).getDyeColor().getId()];
                        }
                    } else if (itemStack.is(LOTags.Items.CARPET_BLANKETS)) {
                        resourceLocation = ARMOR_COLOR[((WoolCarpetBlock) Block.byItem(itemStack.getItem())).getColor().getId()];
                    } else if (itemStack.is(LOTags.Items.MEDIEVAL_BLANKETS) || itemStack.is(LOTags.Items.MODERN_BLANKETS) ||
                            itemStack.is(LOTags.Items.RACING_BLANKETS) || itemStack.is(LOTags.Items.WESTERN_BLANKETS)) {
                        resourceLocation = ARMOR_COLOR[((DyeItem) itemStack.getItem()).getDyeColor().getId()];
                    } else if (itemStack.getItem() instanceof BlanketItem blanketItem) {
                        String name = blanketItem.toString();
                        String noSuffix = name.replaceAll("_.+", "");
                        // ^ if youre another modder adding new blankets, make sure your blanket name is just one word
                        // (i.e american_western_blanket.png)
                        // in this case, "american" is your one word. if you do multiple words, the code will snip out any past the first one and
                        // the armor variant of your carpet may not work. To get your armor variant for your carpet, just copy what
                        // ive done in textures/entity/horse/armor/carpet/special
                        resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/carpet/special/" + noSuffix + "_armor_blanket.png");
                    }
                }
            }

            if ((armorItemStack.isEmpty() && !(itemStack.getItem() instanceof CaparisonItem)) ||
                    (((armorItemStack.getItem() instanceof CaparisonItem) || (armorItemStack.getItem() instanceof RumpStrapItem)))) {
                if (itemStack.is(LOTags.Items.CARPET_BLANKETS)) {
                    resourceLocation = CARPET_COLOR[((WoolCarpetBlock) Block.byItem(itemStack.getItem())).getColor().getId()];
                } else if (itemStack.is(LOTags.Items.MEDIEVAL_BLANKETS)) {
                    resourceLocation = MEDIEVAL_COLOR[((DyeItem) itemStack.getItem()).getDyeColor().getId()];
                } else if (itemStack.is(LOTags.Items.MODERN_BLANKETS)) {
                    resourceLocation = MODERN_COLOR[((DyeItem) itemStack.getItem()).getDyeColor().getId()];
                } else if (itemStack.is(LOTags.Items.RACING_BLANKETS)) {
                    resourceLocation = RACING_COLOR[((DyeItem) itemStack.getItem()).getDyeColor().getId()];
                } else if (itemStack.is(LOTags.Items.WESTERN_BLANKETS)) {
                    resourceLocation = WESTERN_COLOR[((DyeItem) itemStack.getItem()).getDyeColor().getId()];

                    // if youre another modder looking to add new blankets, use this pathway v
                    // it'll find the name for you so long as your registry item is named the same as your texture AND it's a BlanketItem
                    // make sure to put your blanket in the dragnlivestock:special_blankets tag so you can actually put it in the slot
                    // this works for all equines and caribou too, no extra steps required
                } else if (itemStack.getItem() instanceof BlanketItem blanketItem) {
                    resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/carpet/special/" + blanketItem + ".png");
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
                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/decor/" + animatable.getFlowerItem().getItem() + "_mane.png");
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
                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/decor/" + animatable.getFlowerItem().getItem() + "_tail.png");
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
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/decor/" + animatable.getFlowerItem().getItem() + "_tail.png");
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

            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/decor/" + animatable.getFlowerItem().getItem() + "_mane.png");
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
