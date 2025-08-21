package com.dragn0007.dragnlivestock.entities.wagon;

import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractWagon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CoveredWagon extends AbstractWagon {

    public static final Vec3[] RIDERS = new Vec3[] {
            new Vec3(0, 1.25D, 2.1D),
            new Vec3(-0.4375D, 1.18D, 1.1D),
            new Vec3(0.4375D, 1.18D, 1.1D),
            new Vec3(-0.4375D, 1.18D, 0.1D),
            new Vec3(0.4375D, 1.18D, 0.1D),
            new Vec3(-0, 1.18D, -1.81D)
    };

    public CoveredWagon(EntityType<? extends AbstractWagon> type, Level level) {
        super(type, level, 0.1D, 2.0D, 2.0F, 0.6D, 3.6D, 1.25D, 1.4D, RIDERS);
    }

}
