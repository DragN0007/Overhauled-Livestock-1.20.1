package com.dragn0007.dragnlivestock.entities.horse.headlesshorseman;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HeadlessHorsemanRender extends GeoEntityRenderer<HeadlessHorseman> {

    public HeadlessHorsemanRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new HeadlessHorsemanModel());
    }
}


