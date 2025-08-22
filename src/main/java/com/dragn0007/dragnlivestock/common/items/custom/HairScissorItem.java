package com.dragn0007.dragnlivestock.common.items.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HairScissorItem extends Item {

   public HairScissorItem(Properties properties) {
      super(new Item.Properties().durability(35));
   }

   @Override
   public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
      pTooltipComponents.add(Component.translatable("tooltip.dragnlivestock.for_horses.tooltip").withStyle(ChatFormatting.GOLD));
   }
}