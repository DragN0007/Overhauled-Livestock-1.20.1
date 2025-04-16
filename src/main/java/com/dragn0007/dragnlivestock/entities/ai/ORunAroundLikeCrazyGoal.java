package com.dragn0007.dragnlivestock.entities.ai;

import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class ORunAroundLikeCrazyGoal extends Goal {
   private final AbstractOMount horse;
   private final double speedModifier;
   private double posX;
   private double posY;
   private double posZ;

   public ORunAroundLikeCrazyGoal(AbstractOMount p_25890_, double p_25891_) {
      this.horse = p_25890_;
      this.speedModifier = p_25891_;
      this.setFlags(EnumSet.of(Goal.Flag.MOVE));
   }

   public boolean canUse() {
      if ((!this.horse.isTamed() && this.horse.isVehicle()) || (this.horse.isWearingHarness() && this.horse.isVehicle())) {
         Vec3 vec3 = DefaultRandomPos.getPos(this.horse, 5, 4);
         if (vec3 == null) {
            return false;
         } else {
            this.posX = vec3.x;
            this.posY = vec3.y;
            this.posZ = vec3.z;
            return true;
         }
      } else {
         return false;
      }
   }

   public void start() {
      this.horse.getNavigation().moveTo(this.posX, this.posY, this.posZ, this.speedModifier);
   }

   public boolean canContinueToUse() {
      return !this.horse.isTamed() && !this.horse.getNavigation().isDone() && this.horse.isVehicle();
   }

   public int secondsStayedOnTick = 0;

   public void tick() {
      if (this.horse.isWearingHarness() && this.horse.isVehicle()) {
         if (this.horse.getRandom().nextInt(this.adjustedTickDelay(90)) == 10) {

            Entity entity = this.horse.getPassengers().get(0);
            if (entity == null) {
               return;
            }

            this.horse.ejectPassengers();
            this.horse.makeMad();
            secondsStayedOnTick = 0;
            this.horse.level().broadcastEntityEvent(this.horse, (byte)6);
         }

         secondsStayedOnTick++;

         Entity passenger = this.horse.getFirstPassenger();
         Player player = (Player) passenger;
         float stayedOnInSeconds = secondsStayedOnTick / 5f;

         if (passenger instanceof Player && !(secondsStayedOnTick <= 0)) {
            player.displayClientMessage(Component.translatable("Stayed On: " + stayedOnInSeconds + "s").withStyle(ChatFormatting.GOLD), true);
         }
      }

      if (!this.horse.isTamed() && this.horse.getRandom().nextInt(this.adjustedTickDelay(50)) == 0) {
         Entity entity = this.horse.getPassengers().get(0);
         if (entity == null) {
            return;
         }

         if (entity instanceof Player) {
            int i = this.horse.getTemper();
            int j = this.horse.getMaxTemper();
            if (j > 0 && this.horse.getRandom().nextInt(j) < i && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(horse, (Player)entity)) {
               this.horse.tameWithName((Player)entity);
               return;
            }

            this.horse.modifyTemper(5);
         }

         this.horse.ejectPassengers();
         this.horse.makeMad();
         this.horse.level().broadcastEntityEvent(this.horse, (byte)6);
      }

   }
}