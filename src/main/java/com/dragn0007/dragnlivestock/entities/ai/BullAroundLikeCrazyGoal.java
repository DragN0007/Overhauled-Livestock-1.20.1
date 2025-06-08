package com.dragn0007.dragnlivestock.entities.ai;

import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class BullAroundLikeCrazyGoal extends Goal {
   private final OCow mount;
   private final double speedModifier;
   private double posX;
   private double posY;
   private double posZ;

   public BullAroundLikeCrazyGoal(OCow p_25890_, double p_25891_) {
      this.mount = p_25890_;
      this.speedModifier = p_25891_;
      this.setFlags(EnumSet.of(Flag.MOVE));
   }

   public boolean canUse() {
      if ((!this.mount.isTamed() && this.mount.isVehicle()) || (this.mount.isHarnessed() && this.mount.isVehicle())) {
         Vec3 vec3 = DefaultRandomPos.getPos(this.mount, 5, 4);
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
      this.mount.getNavigation().moveTo(this.posX, this.posY, this.posZ, this.speedModifier);
   }

   public boolean canContinueToUse() {
      return !this.mount.isTamed() && !this.mount.getNavigation().isDone() && this.mount.isVehicle();
   }

   public int secondsStayedOnTick = 0;

   public void tick() {
      if (this.mount.isHarnessed() && this.mount.isVehicle()) {
         if (this.mount.getRandom().nextInt(this.adjustedTickDelay(90)) == 10) {

            Entity entity = this.mount.getPassengers().getFirst();
            if (entity == null) {
               return;
            }

            this.mount.ejectPassengers();
            this.mount.makeMad();
            secondsStayedOnTick = 0;
            this.mount.level().broadcastEntityEvent(this.mount, (byte)6);
         }

         secondsStayedOnTick++;

         Entity passenger = this.mount.getFirstPassenger();
         Player player = (Player) passenger;
         float stayedOnInSeconds = secondsStayedOnTick / 5f;

         if (passenger instanceof Player && !(secondsStayedOnTick <= 0)) {
            player.displayClientMessage(Component.translatable("Stayed On: " + stayedOnInSeconds + "s").withStyle(ChatFormatting.GOLD), true);
         }
      }
   }

}