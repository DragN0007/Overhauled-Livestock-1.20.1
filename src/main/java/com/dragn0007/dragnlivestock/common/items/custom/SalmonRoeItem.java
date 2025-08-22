package com.dragn0007.dragnlivestock.common.items.custom;

import com.dragn0007.dragnlivestock.common.entities.EntityTypes;
import com.dragn0007.dragnlivestock.common.entities.OSalmon;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class SalmonRoeItem extends Item {

   public SalmonRoeItem(Properties properties) {
      super(properties);
   }

   @Override
   public InteractionResult useOn(UseOnContext p_43223_) {
      Level level = p_43223_.getLevel();
      if (!(level instanceof ServerLevel)) {
         return InteractionResult.SUCCESS;
      } else {
         ItemStack itemstack = p_43223_.getItemInHand();
         Player player = p_43223_.getPlayer();
         Vec3 targetPos = player.getEyePosition().add(player.getLookAngle().scale(3));

         spawnFishFry(player, null, EntityTypes.O_SALMON_ENTITY.get(), (ServerLevel) level, targetPos, itemstack);

         itemstack.shrink(1);
         return InteractionResult.CONSUME;
      }
   }

   public Optional<Mob> spawnFishFry(Player player, Mob mob1, EntityType<? extends Mob> p_43218_, ServerLevel level, Vec3 vec3, ItemStack itemStack) {
      OSalmon oSalmon = EntityTypes.O_SALMON_ENTITY.get().create(level);
      if (oSalmon == null) return Optional.empty();

      oSalmon.setAge(-24000);

      oSalmon.moveTo(vec3.x(), vec3.y(), vec3.z(), 0.0F, 0.0F);

      level.addFreshEntity(oSalmon);

      if (!player.getAbilities().instabuild) {
         itemStack.shrink(1);
      }

      return Optional.of(oSalmon);
   }
}