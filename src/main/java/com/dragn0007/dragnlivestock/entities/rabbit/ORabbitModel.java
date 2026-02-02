package com.dragn0007.dragnlivestock.entities.rabbit;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import java.util.HashMap;
import java.util.Map;

public class ORabbitModel extends DefaultedEntityGeoModel<ORabbit> {

    public ORabbitModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o_rabbit"), true);
    }

    @Override
    public void setCustomAnimations(ORabbit animatable, long instanceId, AnimationState<ORabbit> animationState) {

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
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/black.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/blue.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/brown.png")),
        CHOCOLATE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/chocolate.png")),
        GOLD_RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/gold_red.png")),
        LILAC(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/lilac.png")),
        MAHOGANY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/mahogany.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/red.png")),
        SEAL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/seal.png")),
        SILVER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/silver.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/tan.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/white.png")),
        CLOVER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/clover_brown.png")),
        JACKIE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/jackie.png")),
        CREAM(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/cream.png")),
        BLUE_TORTISHELL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/blue_tortishell.png")),
        CHESTNUT_AGOUTI(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/chestnut_agouti.png")),
        CHOCOLATE_AGOUTI(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/chocolate_agouti.png")),
        CHOCOLATE_TORTISHELL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/chocolate_tortishell.png")),
        DARK_CHIN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/dark_chin.png")),
        FAWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/fawn.png")),
        GOLD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/gold.png")),
        GOLDEN_STEEL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/golden_steel.png")),
        LIGHT_CHIN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/light_chin.png")),
        LYNX(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/lynx.png")),
        SABLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/sable.png")),
        SALLANDER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/sallander.png")),
        SMOKE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/smoke.png")),
        STEEL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/steel.png")),
        TORTISHELL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/rabbit/tortishell.png")),
        ;

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_rabbit.animation.json");

    @Override
    public ResourceLocation getModelResource(ORabbit object) {
        return RabbitBreed.Breed.breedFromOrdinal(object.getBreed()).resourceLocation;
    }

    public static final Map<String, ResourceLocation> TEXTURE_CACHE = new HashMap<>();


    @Override
    public ResourceLocation getTextureResource(ORabbit object) {
        return TEXTURE_CACHE.computeIfAbsent(object.getTextureResource(), ResourceLocation::tryParse);
    }

    @Override
    public ResourceLocation getAnimationResource(ORabbit animatable) {
        return ANIMATION;
    }
}

