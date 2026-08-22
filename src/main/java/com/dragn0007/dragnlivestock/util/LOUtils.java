package com.dragn0007.dragnlivestock.util;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class LOUtils {

    public static boolean entityIsHidden(Entity entity) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return false;
        Vec3 start = mc.player.getEyePosition(1.0F);
        Vec3 end = entity.getEyePosition(1.0F);

        BlockHitResult result = entity.level().clip(new ClipContext(
                start,
                end,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                entity
        ));

        if (result.getType() == HitResult.Type.BLOCK) {
            BlockPos hitPos = result.getBlockPos();
            BlockState hitState = entity.level().getBlockState(hitPos);
            if (hitState.getBlock() instanceof FenceBlock ||
                    hitState.getBlock() instanceof FenceGateBlock ||
                    hitState.getBlock() instanceof IronBarsBlock ||
                    hitState.getBlock() instanceof HalfTransparentBlock ||
                    hitState.getBlock() instanceof LeavesBlock ||
                    hitState.getBlock() instanceof StainedGlassBlock) {
                return false;
            }
            return true;
        }
        return false;
    }

}
