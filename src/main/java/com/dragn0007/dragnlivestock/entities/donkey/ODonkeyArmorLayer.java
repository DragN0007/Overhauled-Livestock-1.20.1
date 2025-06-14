package com.dragn0007.dragnlivestock.entities.donkey;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ODonkeyArmorLayer extends GeoRenderLayer<ODonkey> {
    public ODonkeyArmorLayer(GeoRenderer<ODonkey> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, ODonkey animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        List<ItemStack> armorSlots = (List<ItemStack>) animatable.getArmorSlots();
        if (armorSlots.size() <= 2) {
            return;
        }

        ItemStack armorItemStack = armorSlots.get(2);

        if (armorItemStack.isEmpty()) {
            return;
        }

        ResourceLocation resourceLocation = null;

        if (armorItemStack.getItem() == Items.LEATHER_HORSE_ARMOR) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/horse_armor_leather.png");
        } else if (armorItemStack.getItem() == LOItems.CHAINMAIL_HORSE_ARMOR.get()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/horse_armor_chainmail.png");
        } else if (armorItemStack.getItem() == LOItems.COPPER_HORSE_ARMOR.get()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/horse_armor_copper.png");
        } else if (armorItemStack.getItem() == Items.IRON_HORSE_ARMOR) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/horse_armor_iron.png");
        } else if (armorItemStack.getItem() == Items.GOLDEN_HORSE_ARMOR) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/horse_armor_gold.png");
        } else if (armorItemStack.getItem() == LOItems.QUARTZ_HORSE_ARMOR.get()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/horse_armor_quartz.png");
        } else if (armorItemStack.getItem() == LOItems.EMERALD_HORSE_ARMOR.get()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/horse_armor_emerald.png");
        } else if (armorItemStack.getItem() == Items.DIAMOND_HORSE_ARMOR) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/horse_armor_diamond.png");
        } else if (armorItemStack.getItem() == LOItems.NETHERITE_HORSE_ARMOR.get()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/horse_armor_netherite.png");
        } else if (armorItemStack.getItem() == LOItems.GRIFFITH_INSPIRED_HORSE_ARMOR.get()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/horse_armor_griffith.png");
        } else if (armorItemStack.getItem() == LOItems.OBSIDIAN_HORSE_ARMOR.get()) {
            resourceLocation = new ResourceLocation("medievalembroidery", "textures/entity/horse/armor/horse_armor_obsidian.png");

        } else if (armorItemStack.getItem() == LOItems.MINIMAL_LEATHER_HORSE_ARMOR.get()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/horse_armor_minimal_leather.png");
        } else if (armorItemStack.getItem() == LOItems.MINIMAL_COPPER_HORSE_ARMOR.get()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/horse_armor_minimal_copper.png");
        } else if (armorItemStack.getItem() == LOItems.MINIMAL_IRON_HORSE_ARMOR.get()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/horse_armor_minimal_iron.png");
        } else if (armorItemStack.getItem() == LOItems.MINIMAL_GOLDEN_HORSE_ARMOR.get()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/horse_armor_minimal_gold.png");
        } else if (armorItemStack.getItem() == LOItems.MINIMAL_QUARTZ_HORSE_ARMOR.get()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/horse_armor_minimal_quartz.png");
        } else if (armorItemStack.getItem() == LOItems.MINIMAL_EMERALD_HORSE_ARMOR.get()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/horse_armor_minimal_emerald.png");
        } else if (armorItemStack.getItem() == LOItems.MINIMAL_DIAMOND_HORSE_ARMOR.get()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/horse_armor_minimal_diamond.png");
        } else if (armorItemStack.getItem() == LOItems.MINIMAL_NETHERITE_HORSE_ARMOR.get()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/horse_armor_minimal_netherite.png");
        } else if (armorItemStack.getItem() == LOItems.MINIMAL_GRIFFITH_INSPIRED_HORSE_ARMOR.get()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/horse_armor_minimal_griffith.png");
        } else if (armorItemStack.getItem() == LOItems.MINIMAL_OBSIDIAN_HORSE_ARMOR.get()) {
            resourceLocation = new ResourceLocation("medievalembroidery", "textures/entity/horse/armor/horse_armor_minimal_obsidian.png");

        } else if (armorItemStack.getItem() == LOItems.RIOT_HORSE_ARMOR.get()) {
            resourceLocation = new ResourceLocation("deadlydinos", "textures/entity/horse/armor/horse_armor_riot.png");

        } else if (armorItemStack.getItem() == LOItems.RODEO_HARNESS.get()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/tack/rodeo_harness.png");

        } else {
            return;
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
