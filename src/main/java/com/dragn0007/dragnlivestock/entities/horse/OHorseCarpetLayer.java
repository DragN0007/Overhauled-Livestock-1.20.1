package com.dragn0007.dragnlivestock.entities.horse;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.items.custom.CaparisonItem;
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

        if (!armorItemStack.isEmpty() && !itemStack.isEmpty() && !(itemStack.getItem() instanceof CaparisonItem)) {
            if (!(armorItemStack.getItem() == LOItems.RIOT_HORSE_ARMOR.get()) && !(armorItemStack.getItem() == LOItems.RODEO_HARNESS.get())) {

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
                } else {
                    resourceLocation = ARMOR_COLOR[((DyeItem) itemStack.getItem()).getDyeColor().getId()];
                }
            }
        }

        if(!itemStack.isEmpty() && armorItemStack.isEmpty() && !(itemStack.getItem() instanceof CaparisonItem)) {
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
