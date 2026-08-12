package com.dragn0007.dragnlivestock.entities.unicorn;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.util.marking_layer.EquineEyeColorOverlay;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import java.util.HashMap;
import java.util.Map;

public class UnicornBodyLayer extends GeoRenderLayer<Unicorn> {
    public UnicornBodyLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    public static final Map<String, ResourceLocation> TEXTURE_CACHE = new HashMap<>();
    public ResourceLocation getTexture(Unicorn animatable) {
        return TEXTURE_CACHE.computeIfAbsent(animatable.getOverlayLocation(), ResourceLocation::tryParse);
    }

    @Override
    public void render(PoseStack poseStack, Unicorn animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        Player player = Minecraft.getInstance().player;
        double distanceSq = animatable.distanceToSqr(player);
        boolean atCullDistance = distanceSq > LivestockOverhaulClientConfig.CULL_LAYERS_DISTANCE.get();
        if (atCullDistance) return;
        if (LivestockOverhaulClientConfig.SIMPLE_MODELS.get()) return;

        if (animatable.getOverlayVariant() != 0) {
            RenderType renderMarkingType = RenderType.entityCutout(this.getTexture(animatable));
            getRenderer().reRender(getDefaultBakedModel(animatable),
                    poseStack,
                    bufferSource,
                    animatable,
                    renderMarkingType,
                    bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);
        }

        RenderType renderMarkingType = RenderType.entityCutout(animatable.getHornTextureResource());
        getRenderer().reRender(getDefaultBakedModel(animatable),
                poseStack,
                bufferSource,
                animatable,
                renderMarkingType,
                bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                1, 1, 1, 1);

        EquineEyeColorOverlay eyes = EquineEyeColorOverlay.eyesFromOrdinal(animatable.getEyeVariant());
        RenderType renderEyeType = RenderType.entityCutout(eyes.resourceLocation);
        getRenderer().reRender(getDefaultBakedModel(animatable),
                poseStack,
                bufferSource,
                animatable,
                renderEyeType,
                bufferSource.getBuffer(renderEyeType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                1, 1, 1, 1);
    }

    public enum Marking {
        NONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/horse/overlay/none.png")),
        APPALOOSA(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/appaloosa.png")),
        BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/bald.png")),
        BLANKET_APPALOOSA(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/blanket_appaloosa.png")),
        BLAZE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/blaze.png")),
        FLEABITTEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/fleabitten.png")),
        FULL_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/full_socks.png")),
        HALF_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/half_socks.png")),
        OVERO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/overo.png")),
        OVERO_SPLASH(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/overo_splash.png")),
        PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/paint.png")),
        REVERSED_HALF_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/reverse_half_socks.png")),
        REVERSED_FULL_SOCKS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/reverse_full_socks.png")),
        ROAN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/roan.png")),
        SNIP(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/snip.png")),
        SPLASH_OVERO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/splash_overo.png")),
        SPLASHED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/splashed.png")),
        SPLASHED_PAINT(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/splashed_paint.png")),
        SPOTTED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/spotted.png")),
        STAR(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/star.png")),
        TOBAINO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/tobaino.png")),
        HALF_SILVER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/half_silver.png")),
        FULL_SILVER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/full_silver.png")),
        CORONET(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/coronet.png")),
        FEW_SPOT_LEOPARD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/few_spot_leopard.png")),
        LEOPARD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/leopard.png")),
        PURE_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/pure_white.png")),
        RABICANO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/rabicano.png")),
        SNOWCAP(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/snowcap.png")),
        HALF_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/half_bald.png")),
        DAPPLES(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/dapples.png")),
        PINK_NOSE_APPALOOSA(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/pink_appaloosa.png")),
        PINK_NOSE_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/pink_bald.png")),
        PINK_NOSE_BLAZE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/pink_blaze.png")),
        PINK_NOSE_FEW_SPOT_LEOPARD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/pink_few_spot_leopard.png")),
        PINK_NOSE_HALF_BALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/pink_half_bald.png")),
        PINK_NOSE_LEOPARD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/pink_leopard.png")),
        PINK_NOSE_OVERO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/pink_overo.png")),
        PINK_NOSE_PURE_WHITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/pink_pure_white.png")),
        PINK_NOSE_SNIP(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/pink_snip.png")),
        PINK_NOSE_SPLASH_OVERO(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/pink_splash_overo.png")),
        PINK_NOSE_SPOTTED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/pink_spotted.png")),
        REVERSE_HALF_SILVER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/reverse_half_silver.png")),
        REVERSE_FULL_SILVER(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/overlay/reverse_full_silver.png"));

        public final ResourceLocation resourceLocation;

        Marking(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Marking overlayFromOrdinal(int overlay) {
            return Marking.values()[overlay % Marking.values().length];
        }
    }

    public enum HornType {
        BLUE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/blue.png")),
        DIAMOND(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/diamond.png")),
        EMERALD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/emerald.png")),
        GREEN(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/green.png")),
        LAPIS(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/lapis.png")),
        PEARL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/pearl.png")),
        PINK(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/pink.png")),
        YELLOW(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/yellow.png")),

        FIRE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/fire.png")),
        GOLD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/gold.png")),
        MANGROVE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/mangrove.png")),
        NETHERITE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/netherite.png")),
        RED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/red.png")),
        REDSTONE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/redstone.png")),
        WARPED(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/warped.png")),
        NAVY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/navy.png")),

        END_CRYSTAL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/end_crystal.png")),
        ENDER_DRAGON(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/ender_dragon.png")),
        ENDER_EYE(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/ender_eye.png")),
        ENDER_PEARL(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/ender_pearl.png")),
        END_GATEWAY(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/end_gateway.png")),
        END_ROD(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/end_rod.png")),
        PURPUR(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/purpur.png")),
        VOID(new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/unicorn/horn/void.png"));

        public final ResourceLocation resourceLocation;
        HornType(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static HornType overlayFromOrdinal(int overlay) { return HornType.values()[overlay % HornType.values().length];
        }
    }
}
