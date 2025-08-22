package com.dragn0007.dragnlivestock.blocks.client.render.entity.mule;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.mule.OMule;
import com.dragn0007.dragnlivestock.common.items.LOItems;
import com.dragn0007.dragnlivestock.common.items.custom.LightHorseArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class OMuleArmorLayer extends GeoRenderLayer<OMule> {
    public OMuleArmorLayer(GeoRenderer<OMule> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OMule animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        List<ItemStack> armorSlots = (List<ItemStack>) animatable.getArmorSlots();
        ItemStack armorItemStack = armorSlots.get(2);

        if (armorItemStack.isEmpty()) {
            return;
        }

        ResourceLocation resourceLocation = null;

        if (!armorItemStack.isEmpty()) {
            if (armorItemStack.getItem() == LOItems.OBSIDIAN_HORSE_ARMOR.get()) {
                resourceLocation = new ResourceLocation("medievalembroidery", "textures/entity/horse/armor/obsidian_horse_armor.png");
            } else if (armorItemStack.getItem() == LOItems.MINIMAL_OBSIDIAN_HORSE_ARMOR.get()) {
                resourceLocation = new ResourceLocation("medievalembroidery", "textures/entity/horse/armor/minimal_obsidian_horse_armor.png");
            } else if (armorItemStack.getItem() == LOItems.RIOT_HORSE_ARMOR.get()) {
                resourceLocation = new ResourceLocation("deadlydinos", "textures/entity/horse/armor/riot_horse_armor.png");
            } else if (armorItemStack.getItem() == LOItems.RODEO_HARNESS.get()) {
                resourceLocation = LivestockOverhaul.id("textures/entity/horse/tack/rodeo_harness.png");
            } else if (armorItemStack.getItem() == LOItems.WAGON_HARNESS.get()) {
                resourceLocation = LivestockOverhaul.id("textures/entity/horse/tack/wagon_harness.png");
            } else if (armorItemStack.getItem() instanceof HorseArmorItem horseArmorItem) {
                resourceLocation = LivestockOverhaul.id("textures/entity/horse/armor/" + horseArmorItem + ".png");
            } else if (armorItemStack.getItem() instanceof LightHorseArmorItem horseArmorItem) {
                resourceLocation = LivestockOverhaul.id("textures/entity/horse/armor/" + horseArmorItem + ".png");
            } else {
                return;
            }
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