package com.dragn0007.dragnlivestock.entities.horse;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.items.custom.CaparisonItem;
import com.dragn0007.dragnlivestock.items.custom.LightHorseArmorItem;
import com.dragn0007.dragnlivestock.items.custom.RumpStrapItem;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
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
public class OHorseArmorLayer extends GeoRenderLayer<OHorse> {
    public OHorseArmorLayer(GeoRenderer<OHorse> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OHorse animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        List<ItemStack> armorSlots = (List<ItemStack>) animatable.getArmorSlots();
        ItemStack armorItemStack = armorSlots.get(2);

        if (armorItemStack.isEmpty()) {
            return;
        }

        ResourceLocation resourceLocation = null;

        String path;
        if (LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) {
            path = "textures/entity/config_simplified/horse/armor/";
        } else {
            path = "textures/entity/horse/armor/";
        }

        if (!armorItemStack.isEmpty()) {
            if (armorItemStack.getItem() == LOItems.OBSIDIAN_HORSE_ARMOR.get()) {
                resourceLocation = new ResourceLocation("medievalembroidery", "textures/entity/horse/armor/obsidian_horse_armor.png");
            } else if (armorItemStack.getItem() == LOItems.MINIMAL_OBSIDIAN_HORSE_ARMOR.get()) {
                resourceLocation = new ResourceLocation("medievalembroidery", "textures/entity/horse/armor/minimal_obsidian_horse_armor.png");
            } else if (armorItemStack.getItem() == LOItems.RIOT_HORSE_ARMOR.get()) {
                resourceLocation = new ResourceLocation("deadlydinos", "textures/entity/horse/armor/riot_horse_armor.png");
            } else if (armorItemStack.getItem() == LOItems.RODEO_HARNESS.get()) {
                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/tack/rodeo_harness.png");
            } else if (armorItemStack.getItem() == LOItems.WAGON_HARNESS.get()) {
                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/tack/wagon_harness.png");

                // if youre another modder looking to add new armor, use this pathway v
                // it'll find the name for you so long as your registry item is named the same as your texture AND it's a HorseAmorItem or LightHorseArmorItem
                // this works for all equines and caribou too, no extra steps required
            } else if (armorItemStack.getItem() instanceof HorseArmorItem horseArmorItem) {
                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/" + horseArmorItem + ".png");
            } else if (armorItemStack.getItem() instanceof LightHorseArmorItem horseArmorItem) {
                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/armor/" + horseArmorItem + ".png");
            } else if ((armorItemStack.getItem() instanceof RumpStrapItem rumpStrapItem) && !armorItemStack.isEmpty()) {
                resourceLocation = new ResourceLocation("medievalembroidery", "textures/entity/horse/caparison/" + rumpStrapItem + ".png");
            } else if ((armorItemStack.getItem() instanceof CaparisonItem caparisonItem) && !armorItemStack.isEmpty()) {
                resourceLocation = new ResourceLocation("medievalembroidery", "textures/entity/horse/caparison/" + caparisonItem + ".png");
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
