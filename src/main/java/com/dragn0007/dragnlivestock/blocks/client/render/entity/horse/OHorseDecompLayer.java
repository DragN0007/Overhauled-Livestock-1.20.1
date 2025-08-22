package com.dragn0007.dragnlivestock.blocks.client.render.entity.horse;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.horse.OHorse;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class OHorseDecompLayer extends GeoRenderLayer<OHorse> {
    public OHorseDecompLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OHorse animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

        UndeadStage overlay = UndeadStage.overlayFromOrdinal(animatable.getDecompVariant());
        RenderType renderMarkingType = RenderType.entityCutout(overlay.resourceLocation);

        getRenderer().reRender(getDefaultBakedModel(animatable),
                poseStack,
                bufferSource,
                animatable,
                renderMarkingType,
                bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                1, 1, 1, 1);
    }

    public enum UndeadStage {
        NONE(LivestockOverhaul.id("textures/entity/horse/overlay/none.png")),
        MINIMAL(LivestockOverhaul.id("textures/entity/horse/undead/undead_stage_1.png")),
        MODERATE(LivestockOverhaul.id("textures/entity/horse/undead/undead_stage_2.png")),
        EXTREME(LivestockOverhaul.id("textures/entity/horse/undead/undead_stage_3.png")),
        SKELETAL(LivestockOverhaul.id("textures/entity/horse/undead/undead_stage_4.png")),
        WITHER(LivestockOverhaul.id("textures/entity/horse/undead/wither.png")),
        STRAY(LivestockOverhaul.id("textures/entity/horse/undead/stray.png")),
        DROWNED(LivestockOverhaul.id("textures/entity/horse/undead/drowned.png")),
        HUSK(LivestockOverhaul.id("textures/entity/horse/undead/husk.png")),
        ;

        public final ResourceLocation resourceLocation;
        UndeadStage(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static UndeadStage overlayFromOrdinal(int overlay) { return UndeadStage.values()[overlay % values().length];
        }
    }

}
