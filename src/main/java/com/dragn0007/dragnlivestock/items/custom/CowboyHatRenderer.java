package com.dragn0007.dragnlivestock.items.custom;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;


public final class CowboyHatRenderer extends GeoArmorRenderer<CowboyHatItem> {

	public CowboyHatRenderer() {

		super(new GeoModel<>() {
			@Override
			public ResourceLocation getModelResource(CowboyHatItem animatable) {
				return new ResourceLocation(LivestockOverhaul.MODID, "geo/cowboy_hat.geo.json");
			}

			@Override
			public ResourceLocation getTextureResource(CowboyHatItem animatable) {
				return new ResourceLocation(LivestockOverhaul.MODID, "textures/hat/cowboy_hat.png");
			}

			@Override
			public ResourceLocation getAnimationResource(CowboyHatItem animatable) {
				return null;
			}
		});
	}
}
