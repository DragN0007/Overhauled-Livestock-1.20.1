package com.dragn0007.dragnlivestock.entities.frog;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class ReplacedTadpoleModel extends DefaultedEntityGeoModel<ReplacedTadpole> {
	public ReplacedTadpoleModel() {
		super(new ResourceLocation(LivestockOverhaul.MODID, "tadpole"));
	}

	public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/tadpole.animation.json");

	@Override
	public ResourceLocation getAnimationResource(ReplacedTadpole animatable) {
		return ANIMATION;
	}
}
