package com.dragn0007.dragnlivestock.entities.salmon;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OSalmonRender extends GeoEntityRenderer<OSalmon> {

    public OSalmonRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OSalmonModel());
    }

}


