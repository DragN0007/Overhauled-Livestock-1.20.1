package com.dragn0007.dragnlivestock.entities.ai;

import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class SleepyGoal extends Goal {
   private final AbstractOMount mob;

   public SleepyGoal(AbstractOMount oMount) {
      this.mob = oMount;
      this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
   }

   public boolean canContinueToUse() {
      return this.mob.shouldSleep();
   }

   public boolean canUse() {
      if (!this.mob.shouldSleep() || this.mob.isVehicle()) {
         return false;
      } else if (this.mob.isInWaterOrBubble()) {
         return false;
      } else if (!this.mob.onGround()) {
         return false;
      } else if (this.mob.shouldSleep()) {
         return true;
      } else {
         return false;
      }
   }

   public void start() {
      this.mob.getNavigation().stop();
      this.mob.setSleeping(true);
   }

   public void stop() {
      this.mob.setSleeping(false);
   }
}