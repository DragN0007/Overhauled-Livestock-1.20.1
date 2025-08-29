package com.dragn0007.dragnlivestock.entities.farm_goat;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class FarmGoatModel extends DefaultedEntityGeoModel<FarmGoat> {

    public FarmGoatModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o_goat"), true);
    }

    @Override
    public void setCustomAnimations(FarmGoat animatable, long instanceId, AnimationState<FarmGoat> animationState) {

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
        BAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/bay.png")),
        BEIGE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/beige.png")),
        BEZOAR(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/bezoar.png")),
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/black.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/brown.png")),
        CARAMEL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/caramel.png")),
        CHAMOISEE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/chamoisee.png")),
        CHOCOLATE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/chocolate.png")),
        COU_BLANC(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/cou_blanc.png")),
        COU_NOIR(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/cou_noir.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/cream.png")),
        GOLD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/gold.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/grey.png")),
        LILAC(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/lilac.png")),
        MAHOGANY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/mahogany.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/red.png")),
        SILVER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/silver.png")),
        SILVER_BEZOAR(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/silver_bezoar.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/tan.png")),
        TWO_TONED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/two_toned.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/white.png")),
        ;

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_goat.animation.json");

    public static final ResourceLocation FEMALE = new ResourceLocation(LivestockOverhaul.MODID, "geo/farm_goat/o_doe.geo.json");
    public static final ResourceLocation MALE = new ResourceLocation(LivestockOverhaul.MODID, "geo/farm_goat/o_buck.geo.json");
    public static final ResourceLocation MEAT_FEMALE = new ResourceLocation(LivestockOverhaul.MODID, "geo/farm_goat/meat_doe.geo.json");
    public static final ResourceLocation MEAT_MALE = new ResourceLocation(LivestockOverhaul.MODID, "geo/farm_goat/meat_buck.geo.json");
    public static final ResourceLocation NUBIAN_FEMALE = new ResourceLocation(LivestockOverhaul.MODID, "geo/farm_goat/nubian_doe.geo.json");
    public static final ResourceLocation NUBIAN_MALE = new ResourceLocation(LivestockOverhaul.MODID, "geo/farm_goat/nubian_buck.geo.json");
    public static final ResourceLocation WARM_FEMALE = new ResourceLocation(LivestockOverhaul.MODID, "geo/farm_goat/warm_doe.geo.json");
    public static final ResourceLocation WARM_MALE = new ResourceLocation(LivestockOverhaul.MODID, "geo/farm_goat/warm_buck.geo.json");

    @Override
    public ResourceLocation getModelResource(FarmGoat object) {
        if (object.getBreed() == 1) {
            if (object.isMale()) {
                return MEAT_MALE;
            } else {
                return MEAT_FEMALE;
            }
        } else if (object.getBreed() == 2) {
            if (object.isMale()) {
                return NUBIAN_MALE;
            } else {
                return NUBIAN_FEMALE;
            }
        } else if (object.getBreed() == 3) {
            if (object.isMale()) {
                return WARM_MALE;
            } else {
                return WARM_FEMALE;
            }
        } else {
            if (object.isMale()) {
                return MALE;
            } else {
                return FEMALE;
            }
        }
    }

    public ResourceLocation getTextureResource(FarmGoat object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(FarmGoat animatable) {
        return ANIMATION;
    }
}

