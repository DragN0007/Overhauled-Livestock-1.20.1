package com.dragn0007.dragnlivestock.blocks.client.render.model.entity;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.frog.ReplacedTadpole;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class ReplacedTadpoleModel extends DefaultedEntityGeoModel<ReplacedTadpole> {
	public ReplacedTadpoleModel() {
		super(LivestockOverhaul.id("tadpole"));
	}

	public static final ResourceLocation ANIMATION = LivestockOverhaul.id("animations/tadpole.animation.json");

	@Override
	public ResourceLocation getAnimationResource(ReplacedTadpole animatable) {
		return ANIMATION;
	}
}
