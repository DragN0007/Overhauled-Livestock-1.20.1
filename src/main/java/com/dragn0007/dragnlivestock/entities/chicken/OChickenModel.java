package com.dragn0007.dragnlivestock.entities.chicken;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OChickenModel extends DefaultedEntityGeoModel<OChicken> {

    public OChickenModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o_chicken"), true);
    }

    @Override
    public void setCustomAnimations(OChicken animatable, long instanceId, AnimationState<OChicken> animationState) {

        CoreGeoBone neck = getAnimationProcessor().getBone("neck");
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (neck != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            neck.setRotY(neck.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(head.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            head.setRotY(head.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }
    }

    public enum Variant {
        LEGHORN_ROOSTER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/chicken_leghorn_rooster.png")),
        AMERICAUNA_ROOSTER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/chicken_ameraucana_rooster.png")),
        CREAM_LEGBAR_ROOSTER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/chicken_cream_legbar_rooster.png")),
        MARANS_ROOSTER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/chicken_marans_rooster.png")),
        OLIVE_EGGER_ROOSTER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/chicken_olive_egger_rooster.png")),
        SUSSEX_SILKIE_ROOSTER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/chicken_sussex_silkie_rooster.png")),

        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/chicken_black.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/chicken_brown.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/chicken_tan.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/chicken_white.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/chicken_cream.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/chicken_grey.png")),
        SILVER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/chicken_silver.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/chicken_blue.png")),
        DARK_BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/chicken_dark_brown.png")),
        AYAM_CEMANI_ROOSTER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/chicken_ayam_cemani_rooster.png")),
        AYAM_CEMANI(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/chicken_ayam_cemani.png")),
        ;

        //Add new entries to bottom when mod is public, else chickens will change textures during update.

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

//    public static final ResourceLocation MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/o_chicken.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/chicken_overhauled.animation.json");
    public static final ResourceLocation BABY_MODEL = new ResourceLocation(LivestockOverhaul.MODID, "geo/baby_chicken_overhauled.geo.json");
    public static final ResourceLocation BABY_TEXTURE = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/chicken/baby_chicken.png");

    @Override
    public ResourceLocation getModelResource(OChicken object) {
        if(object.isBaby())
            return BABY_MODEL;
        return ChickenBreed.Breed.breedFromOrdinal(object.getBreed()).resourceLocation;
    }

    @Override
    public ResourceLocation getTextureResource(OChicken object) {
        if(object.isBaby())
            return BABY_TEXTURE;
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(OChicken animatable) {
        return ANIMATION;
    }
}

