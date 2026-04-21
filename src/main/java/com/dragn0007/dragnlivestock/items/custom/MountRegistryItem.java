package com.dragn0007.dragnlivestock.items.custom;

import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MountRegistryItem extends Item {

   public MountRegistryItem() {
      super(new Properties().stacksTo(1));
   }

   @Override
   public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
      ItemStack stack = pPlayer.getItemInHand(pUsedHand);
      CompoundTag tag = stack.getOrCreateTag();
      String name = tag.getString("mount_name");
      tag.putString("mount_name", name);
      boolean hasMount = tag.getBoolean("has_mount");
      tag.putBoolean("has_mount", hasMount);
      String ownerUUID = tag.getString("ownerUUID");
      tag.putString("ownerUUID", ownerUUID);
      String owner_name = tag.getString("owner_name");
      tag.putString("owner_name", owner_name);

      if (pInteractionTarget instanceof AbstractOMount entity && entity.getOwner() == pPlayer && !hasMount) {
         tag.putString("mount_name", entity.getName().getString());
         tag.putBoolean("has_mount", true);
         tag.putString("ownerUUID", pPlayer.getUUID().toString());
         tag.putString("owner_name", pPlayer.getName().getString());
         pPlayer.displayClientMessage(Component.translatable(name + " has been registered in your name!").withStyle(ChatFormatting.GOLD), true);
      }

      return super.interactLivingEntity(pStack, pPlayer, pInteractionTarget, pUsedHand);
   }

   @Override
   public Rarity getRarity(ItemStack pStack) {
      if (pStack.hasTag() && pStack.getTag().contains("has_mount")) {
         boolean hasMount = pStack.getTag().getBoolean("has_mount");
         if (hasMount) {
            return Rarity.RARE;
         } else {
            return Rarity.COMMON;
         }
      } else {
         return super.getRarity(pStack);
      }
   }

   @Override
   public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
      if (pStack.hasTag() && pStack.getTag().contains("mount_name") && pStack.getTag().contains("owner_name")) {
         String name = pStack.getTag().getString("mount_name");
         String owner_name = pStack.getTag().getString("owner_name");
         pTooltipComponents.add(Component.translatable(name).withStyle(ChatFormatting.GOLD));
         pTooltipComponents.add(Component.translatable(owner_name).withStyle(ChatFormatting.GRAY));
      } else {
         pTooltipComponents.add(Component.literal("No mount registered.").withStyle(ChatFormatting.GRAY));
      }
   }

}