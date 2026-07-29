package com.dragn0007.dragnlivestock.entities.sheep;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class OSheepRenderLayer extends GeoRenderLayer<OSheep> {
    public OSheepRenderLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OSheep animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        if (!animatable.isDyed()) {
            if (!animatable.isBaby() && animatable.isSheared() || animatable.getBreed() == 6 || animatable.isBaby()) return;
            RenderType renderMarkingType = RenderType.entityCutout(animatable.getWoolLocation());
            getRenderer().reRender(getDefaultBakedModel(animatable),
                    poseStack,
                    bufferSource,
                    animatable,
                    renderMarkingType,
                    bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);
            super.render(poseStack, animatable, bakedModel, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);
        } else {
            RenderType renderMarkingType = RenderType.entityCutout(animatable.getWoolDyeLocation());
            getRenderer().reRender(getDefaultBakedModel(animatable),
                    poseStack,
                    bufferSource,
                    animatable,
                    renderMarkingType,
                    bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);
            super.render(poseStack, animatable, bakedModel, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);
        }

        if (animatable.getOverlayVariant() == 0) {
            return;
        } else {
            if ((animatable.isDyed() && animatable.getOverlayVariant() == 3) || LivestockOverhaulClientConfig.SIMPLE_MODELS.get() || animatable.isBaby()) return;
            RenderType renderMarkingType = RenderType.entityCutout(animatable.getOverlayLocation());
            getRenderer().reRender(getDefaultBakedModel(animatable),
                    poseStack,
                    bufferSource,
                    animatable,
                    renderMarkingType,
                    bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);
            super.render(poseStack, animatable, bakedModel, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);
        }

        if (LivestockOverhaulClientConfig.SIMPLE_MODELS.get() || !animatable.isTagged() || !LivestockOverhaulClientConfig.RENDER_BRAND_TAGS.get()) return;
        if (animatable.isTagged()) {
            DyeColor dyeColor = animatable.getBrandTagColor();
            ResourceLocation resourceLocation = null;
            if (dyeColor != null) {
                resourceLocation = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/tag/" + dyeColor + ".png");
            }
            RenderType renderType1 = RenderType.entityCutout(resourceLocation);
            getRenderer().reRender(getDefaultBakedModel(animatable),
                    poseStack,
                    bufferSource,
                    animatable,
                    renderType1,
                    bufferSource.getBuffer(renderType1), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);
        }
    }

    public enum Marking {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/none.png")),
        BACKSPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/backsplash.png")),
        BLAZE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/blaze.png")),
        PURE_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/pure_white.png")),
        REVERSE_BACKSPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/reverse_backsplash.png")),
        REVERSE_SPLOTCHED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/reverse_splotched.png")),
        REVERSE_STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/reverse_stripe.png")),
        RINGNECK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/ringneck.png")),
        SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/socks.png")),
        SPLOTCHED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/splotched.png")),
        STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/stripe.png")),
        FANCY_LIGHT_SPLOTCHED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/fancy_light_splotched.png")),
        FANCY_MEDIUM_SPLOTCHED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/fancy_medium_splotched.png")),
        FANCY_SPLOTCHED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/fancy_splotched.png")),
        FANCY_SOCKS_BACK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/fancy_socks_back.png")),
        FANCY_SOCKS_FRONT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/fancy_socks_front.png")),
        ROAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/roan.png")),
        ;

        //Add new entries to bottom when mod is public, else sheep will change textures during update.

        public final ResourceLocation resourceLocation;
        Marking(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Marking overlayFromOrdinal(int overlay) { return Marking.values()[overlay % Marking.values().length];
        }
    }

    public static String default_path = "textures/entity/sheep/wool/";
    public static String config_simplified_path = "textures/entity/config_simplified/sheep/wool/";

    public enum WoolColor {
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, default_path + "black.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, default_path + "brown.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, default_path + "grey.png")),
        LIGHT_GREY(new ResourceLocation(LivestockOverhaul.MODID, default_path + "light_grey.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, default_path + "tan.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, default_path + "white.png")),
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/none.png")),
        ;

        public final ResourceLocation resourceLocation;
        WoolColor(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static WoolColor overlayFromOrdinal(int overlay) { return WoolColor.values()[overlay % WoolColor.values().length];
        }
    }

    public enum SWoolColor {
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "black.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "brown.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "grey.png")),
        LIGHT_GREY(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "light_grey.png")),
        TAN(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "tan.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, config_simplified_path + "white.png")),
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/none.png")),
        ;

        public final ResourceLocation resourceLocation;
        SWoolColor(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static SWoolColor overlayFromOrdinal(int overlay) { return SWoolColor.values()[overlay % SWoolColor.values().length];
        }
    }

    public static String dye_default_path = "textures/entity/sheep/wool/";
    public static String dye_config_simplified_path = "textures/entity/config_simplified/sheep/wool/";

    public enum DyeOverlay {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/none.png")),
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, dye_default_path + "black.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, dye_default_path + "blue.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, dye_default_path + "brown.png")),
        CYAN(new ResourceLocation(LivestockOverhaul.MODID, dye_default_path + "cyan.png")),
        GREEN(new ResourceLocation(LivestockOverhaul.MODID, dye_default_path + "green.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, dye_default_path + "grey.png")),
        LIGHT_BLUE(new ResourceLocation(LivestockOverhaul.MODID, dye_default_path + "light_blue.png")),
        LIGHT_GREY(new ResourceLocation(LivestockOverhaul.MODID, dye_default_path + "light_grey.png")),
        LIME(new ResourceLocation(LivestockOverhaul.MODID, dye_default_path + "lime.png")),
        MAGENTA(new ResourceLocation(LivestockOverhaul.MODID, dye_default_path + "magenta.png")),
        ORANGE(new ResourceLocation(LivestockOverhaul.MODID, dye_default_path + "orange.png")),
        PINK(new ResourceLocation(LivestockOverhaul.MODID, dye_default_path + "pink.png")),
        PURPLE(new ResourceLocation(LivestockOverhaul.MODID, dye_default_path + "purple.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, dye_default_path + "red.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, dye_default_path + "white.png")),
        YELLOW(new ResourceLocation(LivestockOverhaul.MODID, dye_default_path + "yellow.png")),
        ;

        public final ResourceLocation resourceLocation;
        DyeOverlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static DyeOverlay overlayFromOrdinal(int overlay) { return DyeOverlay.values()[overlay % DyeOverlay.values().length];
        }
    }

    public enum SDyeOverlay {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/none.png")),
        BLACK(new ResourceLocation(LivestockOverhaul.MODID, dye_config_simplified_path + "black.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, dye_config_simplified_path + "blue.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, dye_config_simplified_path + "brown.png")),
        CYAN(new ResourceLocation(LivestockOverhaul.MODID, dye_config_simplified_path + "cyan.png")),
        GREEN(new ResourceLocation(LivestockOverhaul.MODID, dye_config_simplified_path + "green.png")),
        GREY(new ResourceLocation(LivestockOverhaul.MODID, dye_config_simplified_path + "grey.png")),
        LIGHT_BLUE(new ResourceLocation(LivestockOverhaul.MODID, dye_config_simplified_path + "light_blue.png")),
        LIGHT_GREY(new ResourceLocation(LivestockOverhaul.MODID, dye_config_simplified_path + "light_grey.png")),
        LIME(new ResourceLocation(LivestockOverhaul.MODID, dye_config_simplified_path + "lime.png")),
        MAGENTA(new ResourceLocation(LivestockOverhaul.MODID, dye_config_simplified_path + "magenta.png")),
        ORANGE(new ResourceLocation(LivestockOverhaul.MODID, dye_config_simplified_path + "orange.png")),
        PINK(new ResourceLocation(LivestockOverhaul.MODID, dye_config_simplified_path + "pink.png")),
        PURPLE(new ResourceLocation(LivestockOverhaul.MODID, dye_config_simplified_path + "purple.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, dye_config_simplified_path + "red.png")),
        WHITE(new ResourceLocation(LivestockOverhaul.MODID, dye_config_simplified_path + "white.png")),
        YELLOW(new ResourceLocation(LivestockOverhaul.MODID, dye_config_simplified_path + "yellow.png")),
        ;

        public final ResourceLocation resourceLocation;
        SDyeOverlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static SDyeOverlay overlayFromOrdinal(int overlay) { return SDyeOverlay.values()[overlay % SDyeOverlay.values().length];
        }
    }

}
