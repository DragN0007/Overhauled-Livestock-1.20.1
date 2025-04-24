package com.dragn0007.dragnlivestock.entities.horse;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
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
public class OHorseSaddleLayer extends GeoRenderLayer<OHorse> {
    public OHorseSaddleLayer(GeoRenderer<OHorse> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OHorse animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

        ResourceLocation resourceLocation = null;

        if (animatable.isSaddled() && LivestockOverhaulClientConfig.HORSE_SADDLE_EXTRAS.get()) {
            resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/tack/horse_saddle.png");
        } else {
            return;
        }

//        SimpleContainer inventory = animatable.getInventory();
//        ItemStack itemStack = inventory.getItem(0);
//
//        if (inventory.isEmpty()) {
//            return;
//        }
//
//        if (animatable.isSaddled() && LivestockOverhaulClientConfig.HORSE_SADDLE_EXTRAS.get() && !LivestockOverhaulClientConfig.LEGACY_HORSE_SADDLES.get()) {
//            if (animatable.getInventory().getItem(0).is(Items.SADDLE)) {
//                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/tack/horse_saddle.png");
//            } else if (animatable.getInventory().getItem(0).is(LOItems.BLACK_SADDLE.get())) {
//                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/tack/black_horse_saddle.png");
//            } else if (animatable.getInventory().getItem(0).is(LOItems.WHITE_SADDLE.get())) {
//                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/tack/black_horse_saddle.png");
//            } else {
//                return;
//            }
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
