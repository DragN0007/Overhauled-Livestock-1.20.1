package com.dragn0007.dragnlivestock.entities.wagon;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractWagon;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animation.AnimationProcessor;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class WagonModel<T extends AbstractWagon> extends DefaultedEntityGeoModel<T> {

    private final String subpath;

    public WagonModel(String subpath) {
        super(new ResourceLocation(LivestockOverhaul.MODID, "wagon/" + subpath), false);
        this.subpath = subpath;
    }

    @Override
    public ResourceLocation getTextureResource(T wagon) {
        return LivestockOverhaul.id("textures/entity/wagon/" + wagon.getWoodType().toString().toLowerCase() + "_" + subpath + ".png");
    }

    @Override
    public void setCustomAnimations(T wagon, long instanceId, AnimationState<T> state) {
        super.setCustomAnimations(wagon, instanceId, state);

        AnimationProcessor<T> processor = getAnimationProcessor();
        float partialTick = (float)state.animationTick - (int)state.animationTick;
        float rot = wagon.getWheelRot(partialTick);

        processor.getBone("FrontWheels").setRotX(-rot);
        processor.getBone("BackWheels").setRotX(-rot);
    }


}