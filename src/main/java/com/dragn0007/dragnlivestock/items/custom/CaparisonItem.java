package com.dragn0007.dragnlivestock.items.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CaparisonItem extends Item {

   public final DyeColor color;

   public CaparisonItem(DyeColor dyeColor, Properties properties) {
      super(properties);
      this.color = dyeColor;
   }

   public DyeColor getColor() {
      return this.color;
   }

   @Override
   public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
      pTooltipComponents.add(Component.translatable("tooltip.dragnlivestock.for_horses.tooltip").withStyle(ChatFormatting.GOLD));
   }
}