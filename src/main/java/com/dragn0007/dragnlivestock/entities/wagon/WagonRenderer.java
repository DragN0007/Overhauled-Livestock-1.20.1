package com.dragn0007.dragnlivestock.entities.wagon;

import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractWagon;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WagonRenderer<T extends AbstractWagon> extends GeoEntityRenderer<T> {

    public WagonRenderer(Context renderManager, String id) {
        super(renderManager, new WagonModel<>(id));
    }

    @Override
    public void preRender(PoseStack pose, T wagon, BakedGeoModel model, MultiBufferSource bufferSource,
                          VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay,
                          float red, float green, float blue, float alpha) {

        float pitch = wagon.getXRot(partialTick);
        float yaw = -Mth.rotLerp(partialTick, wagon.yRotO, wagon.getYRot());

        double wLength = wagon.getLength();

        Vec3 mid = new Vec3(0, 0, pitch < 0 ? -wLength : wLength); // Pivot point is different for positive vs negative rotation.

        pose.mulPose(Axis.YP.rotationDegrees(yaw));

        pose.translate(-mid.x, 0, -mid.z);
        pose.mulPose(Axis.XP.rotationDegrees(pitch));
        pose.translate(mid.x, 0, mid.z);

        super.preRender(pose, wagon, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

}
