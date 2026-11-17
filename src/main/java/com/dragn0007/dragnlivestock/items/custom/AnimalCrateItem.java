package com.dragn0007.dragnlivestock.items.custom;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.blocks.LOBlocks;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/*
This class falls under the MIT license, as some code was taken from
the Mob Capturing Tool repository, though has since been modified.
https://github.com/MatyrobbrtMods/MobCapturingTool/tree/1.20.x
https://github.com/MatyrobbrtMods/MobCapturingTool/blob/1.20.x/Common/src/main/java/com/matyrobbrt/mobcapturingtool/item/CapturingToolItem.java

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

public class AnimalCrateItem extends ItemNameBlockItem {
   public static final String ANIMAL = "Animal"; //changed name from CAPTURED_ENTITY_TAG
   public static final String ENTITY_TYPE = "Entity_Type"; //changed name from ENTITY_TYPE_TAG

   public AnimalCrateItem() {
      super(LOBlocks.ANIMAL_CRATE.get(), new Properties().stacksTo(1));
   }

//   @Override
//   public InteractionResult place(BlockPlaceContext context) {
//      BlockPos blockpos = context.getClickedPos();
//      Level level = context.getLevel();
//      if (context.getPlayer().isCrouching()) level.setBlock(blockpos, LOBlocks.ANIMAL_CRATE.get().defaultBlockState(), 2);
//      return super.place(context);
//   }

   @Override
   public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity interactionTarget, InteractionHand usedHand) {
      if (!player.isCrouching()) {
         if (LivestockOverhaulCommonConfig.ALLOW_CRATES.get()) {
            if (getFilledCrateAmount(player) < LivestockOverhaulCommonConfig.MAX_CRATES.get()) {
               if (!capture(stack, interactionTarget)) return InteractionResult.FAIL;
               player.swing(usedHand);
               player.setItemInHand(usedHand, stack);
               player.level().playSound(null, player, SoundEvents.BEEHIVE_ENTER, SoundSource.NEUTRAL, 1.0F, Mth.randomBetween(player.level().random, 0.8F, 1.2F));
               return InteractionResult.SUCCESS;
            } else {
               player.displayClientMessage(Component.translatable("tooltip.dragnlivestock.too_many_crates.tooltip").withStyle(ChatFormatting.RED), true);
               return InteractionResult.FAIL; //disallows more crates to be filled when the player has too many
            }
         } else {
            player.displayClientMessage(Component.translatable("tooltip.dragnlivestock.no_crates_allowed.tooltip").withStyle(ChatFormatting.RED), true);
            return InteractionResult.FAIL; //disallows crates to be filled if ALLOW_CRATES config is false
         }
      } else {
         return InteractionResult.FAIL;
      }
   }

   public static int getFilledCrateAmount(Player player) { //figures up how many filled crates are in the player's inv
      int fullCrates = 0;
      for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
         ItemStack stack = player.getInventory().getItem(i);
         if (!stack.isEmpty() && stack.is(LOItems.ANIMAL_CRATE.get())) {
            final var entity = getType(stack);
            if (entity != null) fullCrates += stack.getCount();
         }
      }
      return fullCrates;
   }

   public static boolean capture(ItemStack stack, LivingEntity target) { //modified
      if (target.level().isClientSide || getType(stack) != null) return false;
      if (target instanceof Player || !target.canChangeDimensions() || !target.isAlive()) return false;
      if (!target.getType().is(LOTags.Entity_Types.O_ANIMALS) && !LivestockOverhaulCommonConfig.ALLOW_ALL_IN_CRATES.get()) return false;
      if (!target.getType().is(LOTags.Entity_Types.SMALL_O_ANIMALS) && LivestockOverhaulCommonConfig.SMALL_ONLY_IN_CRATES.get()) return false;
      final var nbt = new CompoundTag();
      nbt.putString(ENTITY_TYPE, EntityType.getKey(target.getType()).toString());
      target.saveWithoutId(nbt);
      stack.getOrCreateTag().put(ANIMAL, nbt);
      target.remove(Entity.RemovalReason.KILLED);
      stack.getOrCreateTag().putBoolean(LivestockOverhaul.MODID + ":captured", true);
      return true;
   }

   @Override
   public InteractionResult useOn(UseOnContext context) { //unchanged
      if (!context.getPlayer().isCrouching()) {
         if (!release(context.getClickedPos(), context.getClickedFace(), context.getLevel(), context.getItemInHand()))
            return InteractionResult.FAIL;
         context.getPlayer().level().playSound(null, context.getPlayer(), SoundEvents.BEEHIVE_ENTER, SoundSource.NEUTRAL, 1.0F, Mth.randomBetween(context.getPlayer().level().random, 0.8F, 1.2F));
         return InteractionResult.SUCCESS;
      } else {
         return super.useOn(context);
      }
   }

   public static boolean release(BlockPos pos, Direction facing, Level level, ItemStack stack) { //modified
      if (level.isClientSide)
         return false;
      final var entityType = getType(stack);
      if (entityType == null)
         return false;
      final var entity = entityType.create(level);
      if (entity != null) {
         entity.load(stack.getOrCreateTagElement(ANIMAL));
         BlockPos blockPos = pos.relative(facing);
         entity.absMoveTo(blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5, 0, 0);
         stack.getOrCreateTag().remove(ANIMAL);
         level.addFreshEntity(entity);
         stack.getOrCreateTag().putBoolean(LivestockOverhaul.MODID + ":captured", false);
         return true;
      }
      return false;
   }

   @Nullable
   public static EntityType<?> getType(ItemStack stack) { //changed name from getEntityType
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
      final var tag = pStack.getOrCreateTagElement(ANIMAL);
      if (entity != null) {
         pTooltipComponents.add(Component.literal(BuiltInRegistries.ENTITY_TYPE.getKey(entity).toString()).withStyle(ChatFormatting.GOLD));
         if (entity.is(LOTags.Entity_Types.O_ANIMALS)) {
            pTooltipComponents.add(Component.translatable("Variant: " + tag.getInt("Variant")).withStyle(ChatFormatting.GRAY));
            pTooltipComponents.add(Component.translatable("Pattern: " + tag.getInt("Overlay_Variant")).withStyle(ChatFormatting.GRAY));
            if (tag.getInt("Quality") > 0)
               pTooltipComponents.add(Component.translatable("Quality: " + tag.getInt("Quality")).withStyle(ChatFormatting.GRAY));
         }
      } else {
         pTooltipComponents.add(Component.translatable("tooltip.dragnlivestock.animal_crate.tooltip").withStyle(ChatFormatting.GOLD));
      }
   }

   @Override
   public boolean canFitInsideContainerItems() {
       return !LivestockOverhaulCommonConfig.ALLOW_CRATES_IN_CONTAINTERS.get();
   }
}