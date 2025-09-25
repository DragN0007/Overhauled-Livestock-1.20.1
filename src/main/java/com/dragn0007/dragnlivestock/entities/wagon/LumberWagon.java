package com.dragn0007.dragnlivestock.entities.wagon;

import com.dragn0007.dragnlivestock.common.gui.LumberWagonMenu;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractInventoryWagon;
import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractWagon;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class LumberWagon extends AbstractInventoryWagon {

    public static final Vec3[] RIDERS = new Vec3[] {
            new Vec3(0, 1.75D, 2.2D)
    };

    public static final Vec3[] ANIMALS = new Vec3[] {
            new Vec3(-0.7D, 0, 4.0D),
            new Vec3(0.7D, 0, 4.0D),
            new Vec3(-0.7D, 0, 8.0D),
            new Vec3(0.7D, 0, 8.0D)
    };

    public LumberWagon(EntityType<? extends AbstractWagon> type, Level level) {
        super(type, level, LivestockOverhaulCommonConfig.LUMBER_WAGON_SPEED_MULT.get(), 2.2D, 2.0F, 60, 54, ANIMALS, 1.25D, 1.4D, RIDERS);
    }

    @Override
    protected boolean tryHitching(Player player) {
        final Mob animal = level().getEntitiesOfClass(Mob.class, new AABB(
                                player.getX()-7, player.getY()-7, player.getZ()-7, player.getX()+7, player.getY()+7, player.getZ()+7),
                        mob -> mob.getLeashHolder() == player && mob.getType().is(LOTags.Entity_Types.LARGE_DRAUGHT_ANIMALS)).stream()
                .findFirst()
                .orElse(null);

        if(!level().isClientSide && animal != null) {
            for(int i = 0; i < animalPositions.length; i++) {
                if(getAnimal(i) == null) {
                    if (animal instanceof OCow cow) {
                        if (cow.getBreed() == 10) {
                            hitch(animal, i);
                        } else {
                            return false;
                        }
                    } else {
                        hitch(animal, i);
                        break;
                    }
                }
            }
            animal.dropLeash(true, !player.isCreative());
        }

        return animal != null;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new LumberWagonMenu(id, inventory, this);
    }

    public ItemStack getPickResult() {
        return LOItems.LUMBER_WAGON.get().getDefaultInstance();
    }

}
