package com.dragn0007.dragnlivestock.entities.cod;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OCodRender extends GeoEntityRenderer<OCod> {

    public OCodRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OCodModel());
    }

}


