package com.dragn0007.dragnlivestock.blocks.client.render.entity.horse;

import com.dragn0007.dragnlivestock.blocks.client.render.model.entity.HeadlessHorsemanModel;
import com.dragn0007.dragnlivestock.common.entities.horse.HeadlessHorseman;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HeadlessHorsemanRenderer extends GeoEntityRenderer<HeadlessHorseman> {

    public HeadlessHorsemanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new HeadlessHorsemanModel());
    }
}


