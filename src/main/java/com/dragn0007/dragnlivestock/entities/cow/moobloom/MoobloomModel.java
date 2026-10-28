package com.dragn0007.dragnlivestock.entities.cow.moobloom;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class MoobloomModel extends DefaultedEntityGeoModel<Moobloom> {

    public MoobloomModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "moobloom"), true);
    }

    @Override
    public void setCustomAnimations(Moobloom animatable, long instanceId, AnimationState<Moobloom> animationState) {
        CoreGeoBone neck = getAnimationProcessor().getBone("neck");
        CoreGeoBone head = getAnimationProcessor().getBone("head");
        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        if (neck != null && animatable.onGround()) {
            neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            neck.setRotY(neck.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }

        if (head != null && animatable.onGround()) {
            head.setRotX(head.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            head.setRotY(head.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }
    }

    public static String default_path = "textures/entity/moobloom/";

    public enum Variant {
        ALLIUM(new ResourceLocation(LivestockOverhaul.MODID, default_path + "allium.png")),
        AZURE_BLUET(new ResourceLocation(LivestockOverhaul.MODID, default_path + "azure_bluet.png")),
        BLUE_ORCHID(new ResourceLocation(LivestockOverhaul.MODID, default_path + "blue_orchid.png")),
        CORNFLOWER(new ResourceLocation(LivestockOverhaul.MODID, default_path + "cornflower.png")),
        DANDELION(new ResourceLocation(LivestockOverhaul.MODID, default_path + "dandelion.png")),
        LILAC(new ResourceLocation(LivestockOverhaul.MODID, default_path + "lilac.png")),
        LILY_OF_THE_VALLEY(new ResourceLocation(LivestockOverhaul.MODID, default_path + "lily_of_the_valley.png")),
        OXEYE_DAISY(new ResourceLocation(LivestockOverhaul.MODID, default_path + "oxeye_daisy.png")),
        PEONY(new ResourceLocation(LivestockOverhaul.MODID, default_path + "peony.png")),
        POPPY(new ResourceLocation(LivestockOverhaul.MODID, default_path + "poppy.png")),
        ROSE_BUSH(new ResourceLocation(LivestockOverhaul.MODID, default_path + "rose_bush.png")),
        SUNFLOWER(new ResourceLocation(LivestockOverhaul.MODID, default_path + "sunflower.png")),
        TULIP(new ResourceLocation(LivestockOverhaul.MODID, default_path + "tulip.png")),
        BEETROOT(new ResourceLocation(LivestockOverhaul.MODID, default_path + "beetroot.png")),
        CARROT(new ResourceLocation(LivestockOverhaul.MODID, default_path + "carrot.png")),
        MELON(new ResourceLocation(LivestockOverhaul.MODID, default_path + "melon.png")),
        POTATO(new ResourceLocation(LivestockOverhaul.MODID, default_path + "potato.png")),
        PUMPKIN(new ResourceLocation(LivestockOverhaul.MODID, default_path + "pumpkin.png")),
        WHEAT(new ResourceLocation(LivestockOverhaul.MODID, default_path + "wheat.png")),
        AZALEA(new ResourceLocation(LivestockOverhaul.MODID, default_path + "azalea.png")),
        SWEET_BERRY(new ResourceLocation(LivestockOverhaul.MODID, default_path + "sweet_berry.png")),
        PEACH(new ResourceLocation(LivestockOverhaul.MODID, default_path + "peach.png"))
        ;

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {this.resourceLocation = resourceLocation;}
        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];}
    }

    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_cow.animation.json");

    @Override
    public ResourceLocation getModelResource(Moobloom object) {return MoobloomType.breedFromOrdinal(object.getBreed()).resourceLocation;}

    @Override
    public ResourceLocation getTextureResource(Moobloom object) {return object.getTextureLocation();}

    @Override
    public ResourceLocation getAnimationResource(Moobloom animatable) {return ANIMATION;}
}

