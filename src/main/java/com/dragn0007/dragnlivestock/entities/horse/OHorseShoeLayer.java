package com.dragn0007.dragnlivestock.entities.horse;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

@OnlyIn(Dist.CLIENT)
public class OHorseShoeLayer extends GeoRenderLayer<OHorse> {
    public OHorseShoeLayer(GeoRenderer<OHorse> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OHorse animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
//        List<ItemStack> armorSlots = (List<ItemStack>) animatable.getArmorSlots();
//        if (armorSlots.size() <= 2) {
//            return;
//        }
//
//        ItemStack armorItemStack = armorSlots.get(2);
//
//        if (armorItemStack.isEmpty() || !(armorItemStack.getItem() instanceof HorseShoeItem)) {
//            return;
//        }

        ResourceLocation resourceLocation = null;

        if (animatable.isSaddled()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/tack/stone_horseshoes.png");
        } else {
            return;
        }

//        if (armorItemStack.getItem() == LOItems.STONE_HORSESHOE.get()) {
//            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/tack/stone_horseshoes.png");
//        } else if (armorItemStack.getItem() == LOItems.IRON_HORSESHOE.get()) {
//            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/tack/iron_horseshoes.png");
//        } else if (armorItemStack.getItem() == LOItems.GOLD_HORSESHOE.get()) {
//            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/tack/gold_horseshoes.png");
//        } else if (armorItemStack.getItem() == LOItems.DIAMOND_HORSESHOE.get()) {
//            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/tack/diamond_horseshoes.png");
//        } else if (armorItemStack.getItem() == LOItems.NETHERITE_HORSE_ARMOR.get()) {
//            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/tack/netherite_horseshoes.png");
//        } else {
//            return;
//        }

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
