package com.dragn0007.dragnlivestock.common.items.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlanketItem extends DyeItem {

   public BlanketItem(DyeColor dyeColor, Properties properties) {
      super(dyeColor, properties);
   }

   @Override
   public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
      pTooltipComponents.add(Component.translatable("tooltip.dragnlivestock.for_equines.tooltip").withStyle(ChatFormatting.GOLD));
   }
}