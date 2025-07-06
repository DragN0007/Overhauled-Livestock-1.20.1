package com.dragn0007.dragnlivestock.entities.horse;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.items.custom.BlanketItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SaddleItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

@OnlyIn(Dist.CLIENT)
public class OHorseSaddleLayer extends GeoRenderLayer<OHorse> {
    public OHorseSaddleLayer(GeoRenderer<OHorse> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OHorse animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        ItemStack itemStack = animatable.getSaddleItem();
        if(!itemStack.isEmpty()) {
            ResourceLocation resourceLocation = null;

            // if youre another modder looking to add new saddles, use this pathway v
            // it'll find the name for you so long as your registry item is named the same as your texture AND it's a SaddleItem
            // make sure to put your saddle in the dragnlivestock:saddle tag so you can actually put it in the slot
            // this works for all equines and caribou too, no extra steps required
            if (itemStack.getItem() instanceof SaddleItem saddleItem) {
                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/tack/" + saddleItem + ".png");
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

}
