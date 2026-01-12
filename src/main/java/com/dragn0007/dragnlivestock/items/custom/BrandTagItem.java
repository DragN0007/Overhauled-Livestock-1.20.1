package com.dragn0007.dragnlivestock.items.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BrandTagItem extends Item {

   public final DyeColor color;

   public BrandTagItem(DyeColor dyeColor, Properties properties) {
      super(properties);
      this.color = dyeColor;
   }

   @Override
   public int getEnchantmentValue(ItemStack stack) {
      return 0;
   }

   @Override
   public float getXpRepairRatio(ItemStack stack) {
      return 0F;
   }

   public DyeColor getColor() {
      return this.color;
   }

   public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pTarget, InteractionHand pHand) {
      if (pStack.hasCustomHoverName() && !(pTarget instanceof Player)) {
         if (!pPlayer.level().isClientSide && pTarget.isAlive()) {
            if (pStack.hasCustomHoverName()) {
               pTarget.setCustomName(pTarget.getCustomName().copy().append(Component.literal(" ")).append(pStack.getHoverName()));
            } else {
               pTarget.setCustomName(pStack.getHoverName());
            }
            if (pTarget instanceof Mob) {
               ((Mob)pTarget).setPersistenceRequired();
            }

            pStack.shrink(1);
         }

         return InteractionResult.sidedSuccess(pPlayer.level().isClientSide);
      } else {
         return InteractionResult.PASS;
      }
   }

   @Override
   public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
      pTooltipComponents.add(Component.translatable("tooltip.dragnlivestock.brand_tag.tooltip").withStyle(ChatFormatting.GOLD));
   }
}