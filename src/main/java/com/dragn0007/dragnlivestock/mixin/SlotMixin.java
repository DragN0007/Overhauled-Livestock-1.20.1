package com.dragn0007.dragnlivestock.mixin;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.items.custom.AnimalCrateItem;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Slot.class)
public abstract class SlotMixin {
    @Shadow @Final public net.minecraft.world.Container container;

    @Inject(method = "mayPlace", at = @At("HEAD"), cancellable = true)
    private void preventRestrictedItemInContainers(ItemStack stack, CallbackInfoReturnable<Boolean> ci) {
        if (stack.getItem() instanceof AnimalCrateItem crateItem && !LivestockOverhaulCommonConfig.ALLOW_CRATES_IN_CONTAINTERS.get()) {
            if ((!(this.container instanceof Inventory)) && stack.hasTag() && stack.getTag().getBoolean(LivestockOverhaul.MODID + ":captured"))
                ci.setReturnValue(false);
        }
    }
}