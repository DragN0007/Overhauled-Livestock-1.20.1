package com.dragn0007.dragnlivestock.entities.wagon;

import com.dragn0007.dragnlivestock.common.gui.TransportCartMenu;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractInventoryWagon;
import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractWagon;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LOTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class TransportCart extends AbstractInventoryWagon {

    public static final Vec3[] RIDERS = new Vec3[] {
            new Vec3(0, 1.2D, 0.1D)
    };

    public static final Vec3[] ANIMALS = new Vec3[] {
            new Vec3(0.0D, 0, 2.0D)
    };

    public TransportCart(EntityType<? extends AbstractWagon> type, Level level) {
        super(type, level, 0.3D, 2.0D, 3.0F, 20, 18, ANIMALS, 1.25D, 1.25D, RIDERS);
    }

    @Override
    protected boolean tryHitching(Player player) {
        final Mob animal = level().getEntitiesOfClass(Mob.class, new AABB(
                                player.getX()-7, player.getY()-7, player.getZ()-7, player.getX()+7, player.getY()+7, player.getZ()+7),
                        h -> h.getLeashHolder() == player && h.getType().is(LOTags.Entity_Types.SMALL_DRAUGHT_ANIMALS)).stream()
                .findFirst()
                .orElse(null);

        if(!level().isClientSide && animal != null) {
            for(int i = 0; i < animalPositions.length; i++) {
                if((getAnimal(i) == null) || ((getAnimal(i) == null) && animal instanceof OCow cow && cow.getBreed() == 10)) {
                    hitch(animal, i);
                    break;
                }
            }
            animal.dropLeash(true, !player.isCreative());
        }

        return animal != null;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new TransportCartMenu(id, inventory, this);
    }

    public ItemStack getPickResult() {
        return LOItems.TRANSPORT_CART.get().getDefaultInstance();
    }

}
