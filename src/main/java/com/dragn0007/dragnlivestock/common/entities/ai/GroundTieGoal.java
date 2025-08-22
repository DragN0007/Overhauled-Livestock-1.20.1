package com.dragn0007.dragnlivestock.common.entities.ai;

import com.dragn0007.dragnlivestock.common.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.common.LivestockOverhaulCommonConfig;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class GroundTieGoal extends Goal {
   private final AbstractOMount mob;

   public GroundTieGoal(AbstractOMount oMount) {
      this.mob = oMount;
      this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
   }

   public boolean canContinueToUse() {
      return this.mob.isSaddled() && !this.mob.isVehicle() && LivestockOverhaulCommonConfig.GROUND_TIE.get() || this.mob.isLeashed();
   }

   public boolean canUse() {
      if (!LivestockOverhaulCommonConfig.GROUND_TIE.get() || !this.mob.isTamed()) {
         return false;
      } else if (this.mob.isInWaterOrBubble()) {
         return false;
      } else if (!this.mob.onGround()) {
         return false;
      } else if (this.mob.isSaddled() && !this.mob.isVehicle() && LivestockOverhaulCommonConfig.GROUND_TIE.get() || this.mob.isLeashed()) {
         return true;
      } else {
         return false;
      }
   }

   public void start() {
      this.mob.getNavigation().stop();
   }

   public void stop() {
   }
}