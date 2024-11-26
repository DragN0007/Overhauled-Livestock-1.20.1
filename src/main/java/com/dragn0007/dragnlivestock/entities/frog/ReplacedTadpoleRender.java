package com.dragn0007.dragnlivestock.entities.frog;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.animal.frog.Tadpole;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoReplacedEntityRenderer;

public class ReplacedTadpoleRender extends GeoReplacedEntityRenderer<Tadpole, ReplacedTadpole> {
	public ReplacedTadpoleRender(EntityRendererProvider.Context renderManager) {
		super(renderManager, new ReplacedTadpoleModel(), new ReplacedTadpole());
	}

	@Override
	public void preRender(PoseStack poseStack, ReplacedTadpole animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
