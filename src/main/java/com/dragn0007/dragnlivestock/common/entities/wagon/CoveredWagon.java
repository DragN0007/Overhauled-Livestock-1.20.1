package com.dragn0007.dragnlivestock.common.entities.wagon;

import com.dragn0007.dragnlivestock.common.entities.wagon.base.AbstractInventoryWagon;
import com.dragn0007.dragnlivestock.common.entities.wagon.base.AbstractWagon;
import com.dragn0007.dragnlivestock.common.menus.CoveredWagonMenu;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CoveredWagon extends AbstractInventoryWagon {

    public static final Vec3[] RIDERS = new Vec3[] {
            new Vec3(0, 1.75D, 2.4D),
            new Vec3(-0.43D, 1.25D, 0D),
            new Vec3(0, 1D, -2.25D)
    };

    public static final Vec3[] ANIMALS = new Vec3[] {
            new Vec3(-0.5D, 0, 4.75D),
            new Vec3(0.5D, 0, 4.75D)
    };

    public CoveredWagon(EntityType<? extends AbstractWagon> type, Level level) {
        super(type, level, 0.1D, 2.0D, 2.0F, 60, 36, ANIMALS, 1.25D, 1.4D, RIDERS);
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new CoveredWagonMenu(id, inventory, this);
    }

}
