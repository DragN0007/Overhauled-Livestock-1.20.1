package com.dragn0007.dragnlivestock.blocks.client.render.entity.camel;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.camel.OCamel;
import com.dragn0007.dragnlivestock.common.LOTags;
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
public class OCamelCarpetLayer extends GeoRenderLayer<OCamel> {
    public static final ResourceLocation[] TEXTURE_LOCATION = new ResourceLocation[]{
            LivestockOverhaul.id("textures/entity/camel/carpet/white.png"),
            LivestockOverhaul.id("textures/entity/camel/carpet/orange.png"),
            LivestockOverhaul.id("textures/entity/camel/carpet/magenta.png"),
            LivestockOverhaul.id("textures/entity/camel/carpet/light_blue.png"),
            LivestockOverhaul.id("textures/entity/camel/carpet/yellow.png"),
            LivestockOverhaul.id("textures/entity/camel/carpet/lime.png"),
            LivestockOverhaul.id("textures/entity/camel/carpet/pink.png"),
            LivestockOverhaul.id("textures/entity/camel/carpet/grey.png"),
            LivestockOverhaul.id("textures/entity/camel/carpet/light_grey.png"),
            LivestockOverhaul.id("textures/entity/camel/carpet/cyan.png"),
            LivestockOverhaul.id("textures/entity/camel/carpet/purple.png"),
            LivestockOverhaul.id("textures/entity/camel/carpet/blue.png"),
            LivestockOverhaul.id("textures/entity/camel/carpet/brown.png"),
            LivestockOverhaul.id("textures/entity/camel/carpet/green.png"),
            LivestockOverhaul.id("textures/entity/camel/carpet/red.png"),
            LivestockOverhaul.id("textures/entity/camel/carpet/black.png")
    };

    public OCamelCarpetLayer(GeoRenderer<OCamel> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OCamel animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        ItemStack itemStack = animatable.getDecorItem();

        if(!itemStack.isEmpty()) {
            ResourceLocation resourceLocation = null;
            if (!itemStack.is(LOTags.Items.CAMEL_ARMOR) && !(itemStack.getItem() instanceof HorseArmorItem)) {
                if (itemStack.is(LOTags.Items.CARPET_BLANKETS)) {
                    DyeColor dyeColor = ((WoolCarpetBlock) Block.byItem(itemStack.getItem())).getColor();
                    resourceLocation = TEXTURE_LOCATION[dyeColor.getId()];
                } else {
                    DyeColor dyeColor = ((DyeItem) itemStack.getItem()).getDyeColor();
                    resourceLocation = TEXTURE_LOCATION[dyeColor.getId()];
                }
            } else if (itemStack.is(LOTags.Items.CAMEL_ARMOR) && itemStack.getItem() instanceof HorseArmorItem horseArmorItem) {
                resourceLocation = LivestockOverhaul.id("textures/entity/camel/armor/" + horseArmorItem + ".png");
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
