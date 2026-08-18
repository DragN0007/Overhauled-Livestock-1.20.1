package com.dragn0007.dragnlivestock.entities.llama;

import com.dragn0007.dragnlivestock.util.LOUtils;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import java.util.Optional;

public class OLlamaRender extends GeoEntityRenderer<OLlama> {

    public OLlamaRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OLlamaModel());
        this.addRenderLayer(new OLlamaMarkingLayer(this));
        this.addRenderLayer(new OLlamaTackLayer(this));
    }

    @Override
    public void render(OLlama entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        Optional<GeoBone> body = this.getGeoModel().getBone("body");
        Optional<GeoBone> saddlebags = this.getGeoModel().getBone("saddlebags");
        Optional<GeoBone> wool_body = this.getGeoModel().getBone("wool_body");
        Optional<GeoBone> blanket = this.getGeoModel().getBone("blanket");

        //culls unnecessary bones at a certain distance to improve performance
        Player player = Minecraft.getInstance().player;
        double distanceSq = this.animatable.distanceToSqr(player);
        boolean atCullDistance = distanceSq > LivestockOverhaulClientConfig.CULL_CUBES_DISTANCE.get();
        if (player != null) {
            if (LivestockOverhaulClientConfig.CULL_HIDDEN.get())
                if (body.isPresent()) {body.ifPresent(b -> b.setHidden(LOUtils.entityIsHidden(animatable)));}
            if (saddlebags.isPresent()) {saddlebags.ifPresent(b -> b.setHidden(atCullDistance));}
            if (wool_body.isPresent()) {wool_body.ifPresent(b -> b.setHidden(atCullDistance));}
            if (blanket.isPresent()) {blanket.ifPresent(b -> b.setHidden(atCullDistance));}
        }
        if (atCullDistance) return;

        if (entity.hasChest()) {
            saddlebags.ifPresent(b -> b.setHidden(false));
        } else {
            saddlebags.ifPresent(b -> b.setHidden(true));
        }

        if (entity.getSwag() != null) {
            blanket.ifPresent(b -> b.setHidden(false));
        } else {
            blanket.ifPresent(b -> b.setHidden(true));
        }

        if(entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }

        if(entity.getWooly() == 0 || entity.isSheared()) {
            blanket.ifPresent(b -> b.setHidden(true));
        } else {
            blanket.ifPresent(b -> b.setHidden(false));
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}


