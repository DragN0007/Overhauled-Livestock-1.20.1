package com.dragn0007.dragnlivestock.items.custom;

import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.sk89q.worldedit.world.entity.EntityType;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class MountRegistryItem extends Item {

   public MountRegistryItem(Properties properties) {
      super(properties);
   }

   @Override
   public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
      if (entity instanceof AbstractOMount mount) {
         UUID mountUUID = entity.getUUID();
         String mountName = mount.getDisplayName().getString();
         UUID ownerUUID = mount.getOwnerUUID();
         String ownerName = player.getDisplayName().getString();
         if (mount.isOwnedBy(player)) {

         }
      } else if (entity instanceof Player newOwner) {

      }
    return super.interactLivingEntity(stack, player, entity, hand);
   }
}