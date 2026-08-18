package com.dragn0007.dragnlivestock.entities.farm_goat;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import java.util.HashMap;
import java.util.Map;

public class FarmGoatBodyLayer extends GeoRenderLayer<FarmGoat> {
    public FarmGoatBodyLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    public static final Map<String, ResourceLocation> TEXTURE_CACHE = new HashMap<>();
    public ResourceLocation getTexture(FarmGoat animatable) {
        return TEXTURE_CACHE.computeIfAbsent(animatable.getOverlayLocation(), ResourceLocation::tryParse);
    }

    @Override
    public void render(PoseStack poseStack, FarmGoat animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        Player player = Minecraft.getInstance().player;
        double distanceSq = animatable.distanceToSqr(player);
        boolean atCullDistance = distanceSq > LivestockOverhaulClientConfig.CULL_LAYERS_DISTANCE.get();
        if (atCullDistance) return;
        
        if (!animatable.isBaby()) {
            if (animatable.getOverlayVariant() != 0) {
                RenderType renderMarkingType = RenderType.entityCutout(this.getTexture(animatable));
                getRenderer().reRender(getDefaultBakedModel(animatable),
                        poseStack,
                        bufferSource,
                        animatable,
                        renderMarkingType,
                        bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                        1, 1, 1, 1);
                super.render(poseStack, animatable, bakedModel, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);
            }

            if (animatable.getFaceOverlayVariant() != 0) {
                RenderType renderFaceMarkingType = RenderType.entityCutout(animatable.getFaceOverlayLocation());
                getRenderer().reRender(getDefaultBakedModel(animatable),
                        poseStack,
                        bufferSource,
                        animatable,
                        renderFaceMarkingType,
                        bufferSource.getBuffer(renderFaceMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                        1, 1, 1, 1);
                super.render(poseStack, animatable, bakedModel, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);
            }

            RenderType renderMarkingType = RenderType.entityCutout(animatable.getEyeLocation());
            getRenderer().reRender(getDefaultBakedModel(animatable),
                    poseStack,
                    bufferSource,
                    animatable,
                    renderMarkingType,
                    bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);
            super.render(poseStack, animatable, bakedModel, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);
        }

        if (LivestockOverhaulClientConfig.SIMPLE_MODELS.get() || !animatable.isTagged() || !LivestockOverhaulClientConfig.RENDER_BRAND_TAGS.get())
            return;
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
        BELLY_PINTO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/belly_pinto.png")),
        BROKEN_PINTO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/broken_pinto.png")),
        DAPPLE_PINTO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/dapple_pinto.png")),
        FEW_SPOT_PINTO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/few_spot_pinto.png")),
        HALF_PINTO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/half_pinto.png")),
        ROUND_PINTO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/round_pinto.png")),
        SPOTTY_PINTO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/spotty_pinto.png")),
        BELT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/belt.png")),
        BIG_DAPPLES(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/big_dapples.png")),
        DAPPLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/dapple.png")),
        EXTREME_DAPPLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/extreme_dapple.png")),
        MINIMAL_DAPPLE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/minimal_dapple.png")),
        BLACK_BADGER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/black_badger.png")),
        CHOCOLATE_BADGER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/chocolate_badger.png")),
        RED_BADGER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/red_badger.png")),
        BLACK_MOONSPOTS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/black_moonspots.png")),
        CHOCOLATE_MOONSPOTS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/chocolate_moonspots.png")),
        CREAM_MOONSPOTS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/cream_moonspots.png")),
        SILVER_MOONSPOTS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/silver_moonspots.png")),
        BROKEN_BELT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/broken_belt.png")),
        FLOWERY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/flowery.png")),
        FULL_SOLID(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/full_solid.png")),
        ROAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/roan.png")),
        SWISS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/swiss.png")),
        TAN_MARKED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/swiss.png")),
        PURE_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/overlay/pure_white.png")),
        ;

        //Add new entries to bottom when mod is public, else goats will change textures during update.

        public final ResourceLocation resourceLocation;
        Marking(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Marking overlayFromOrdinal(int overlay) { return Marking.values()[overlay % Marking.values().length];
        }
    }

    public enum Face_Marking {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/face_overlay/none.png")),
        BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/face_overlay/bald.png")),
        BIG_BLAZE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/face_overlay/big_blaze.png")),
        BLAZE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/face_overlay/blaze.png")),
        BOER_STRIPE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/face_overlay/boer_stripe.png")),
        BROCKLES(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/face_overlay/brockles.png")),
        FROSTED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/face_overlay/frosted.png")),
        LIPSTICK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/face_overlay/lipstick.png")),
        LIPSTICK_BROCKLES(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/face_overlay/lipstick_brockles.png")),
        POLL_SPOT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/face_overlay/poll_spot.png")),
        RED_CHEEK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/face_overlay/red_cheek.png")),
        SNIP(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/face_overlay/snip.png")),
        STAR(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/face_overlay/star.png")),
        ;

        //Add new entries to bottom when mod is public, else goats will change textures during update.

        public final ResourceLocation resourceLocation;
        Face_Marking(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Face_Marking overlayFromOrdinal(int overlay) { return Face_Marking.values()[overlay % Face_Marking.values().length];
        }
    }

    public enum EyeColor {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/sheep/overlay/none.png")),
        BROWN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/eye/brown.png")),
        AMBER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/eye/amber.png")),
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/farm_goat/eye/blue.png")),
        ;

        //Add new entries to bottom when mod is public, else goats will change textures during update.

        public final ResourceLocation resourceLocation;
        EyeColor(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static EyeColor overlayFromOrdinal(int overlay) { return EyeColor.values()[overlay % EyeColor.values().length];
        }
    }

}
