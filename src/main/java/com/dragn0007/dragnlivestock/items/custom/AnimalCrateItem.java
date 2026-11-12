package com.dragn0007.dragnlivestock.items.custom;

import com.mojang.math.Constants;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/*
This class falls under the MIT license, as some code was referenced from
the Mob Capturing Tool repository, though has since been heavily modified.
https://github.com/MatyrobbrtMods/MobCapturingTool/tree/1.20.x

MIT License
Copyright (c) 2022 matyrobbrt

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

public class AnimalCrateItem extends Item {
   public static final String ANIMAL = "Animal";
   public static final String ENTITY_TYPE = "Entity_Type";

   public AnimalCrateItem(Properties properties) {
      super(properties);
   }

   @Override
   public InteractionResult useOn(UseOnContext context) {
      //TODO
      return InteractionResult.SUCCESS;
   }

   @Override
   public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity livingEntity, InteractionHand hand) {
      //TODO
      player.swing(hand);
      player.setItemInHand(hand, stack);
      return InteractionResult.SUCCESS;
   }

   @Nullable
   public static EntityType<?> getType(ItemStack stack) {
      if (stack.getOrCreateTag().contains(ANIMAL, Tag.TAG_COMPOUND)) {
         final var typeStr = stack.getOrCreateTagElement(ANIMAL).getString(ENTITY_TYPE);
         final var rl = new ResourceLocation(typeStr);
         return BuiltInRegistries.ENTITY_TYPE.getOptional(rl).orElse(null);
      }
      return null;
   }

   @Override
   public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
      final var entity = getType(pStack);
      pTooltipComponents.add(Component.translatable("tooltip.dragnlivestock.animal_crate.tooltip").withStyle(ChatFormatting.GOLD));
      if (entity != null) {
         final var tag = pStack.getOrCreateTagElement(ANIMAL);
         pTooltipComponents.add(Component.translatable("tooltip.dragnlivestock.animal.tooltip", BuiltInRegistries.ENTITY_TYPE.getKey(entity).toString()).withStyle(ChatFormatting.GRAY));
         if (!String.valueOf(tag.getDouble("Variant")).isEmpty())
          pTooltipComponents.add(Component.translatable("tooltip.dragnlivestock.coat.tooltip", Component.literal(String.valueOf(tag.getDouble("Variant")))).withStyle(ChatFormatting.GRAY));
         if (!String.valueOf(tag.getDouble("Overlay_Variant")).isEmpty())
            pTooltipComponents.add(Component.translatable("tooltip.dragnlivestock.marking.tooltip", Component.literal(String.valueOf(tag.getDouble("Overlay_Variant")))).withStyle(ChatFormatting.GRAY));
      }
   }
}