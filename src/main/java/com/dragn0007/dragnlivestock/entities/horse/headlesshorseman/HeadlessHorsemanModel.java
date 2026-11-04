package com.dragn0007.dragnlivestock.entities.horse.headlesshorseman;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import java.util.HashMap;
import java.util.Map;

public class HeadlessHorsemanModel extends DefaultedEntityGeoModel<HeadlessHorseman> {
    public HeadlessHorsemanModel() {super(new ResourceLocation(LivestockOverhaul.MODID, "headless_horseman"), true);}

    @Override
    public void setCustomAnimations(HeadlessHorseman animatable, long instanceId, AnimationState<HeadlessHorseman> animationState) {}

    public static final ResourceLocation TEXTURE = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/headless_horseman.png");
    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/headless_horseman.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/headless_horseman.animation.json");

    @Override
    public ResourceLocation getModelResource(HeadlessHorseman object) {return MODEL;}

    public static final Map<String, ResourceLocation> TEXTURE_CACHE = new HashMap<>();

    @Override
    public ResourceLocation getTextureResource(HeadlessHorseman object) {return TEXTURE;}

    @Override
    public ResourceLocation getAnimationResource(HeadlessHorseman animatable) {return ANIMATION;}
}

