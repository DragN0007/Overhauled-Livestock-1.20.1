package com.dragn0007.dragnlivestock.items.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class UtilityKnifeItem extends Item {
    public UtilityKnifeItem() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public boolean hasCraftingRemainingItem() {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return itemStack.copy();
    }
}