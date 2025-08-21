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
//        DyeColor color = wagon.getColor();
//        return new ResourceLocation(LivestockOverhaul.MODID, texturePathSuffix + (color != null ? color.getName() : "none") + ".png");
        return new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/wagon/oak_covered_wagon.png");
    }

    @Override
    public void setCustomAnimations(T wagon, long instanceId, AnimationState<T> state) {
        super.setCustomAnimations(wagon, instanceId, state);

        AnimationProcessor<T> processor = getAnimationProcessor();
        float partialTick = (float)state.animationTick - (int)state.animationTick;
//        float rot = wagon.getWheelRot(partialTick);
//
//        processor.getBone("FrontWheels").setRotX(-rot);
//        processor.getBone("RearWheels").setRotX(-rot);
//
//        if(!wagon.level().getEntitiesOfClass(Mob.class, wagon.getBoundingBox().inflate(10), Mob::isNoAi).isEmpty()) {
//            processor.getBone("Tongue").setRotX(wagon.getShaftAngle() * Mth.DEG_TO_RAD);
//        }
    }

}