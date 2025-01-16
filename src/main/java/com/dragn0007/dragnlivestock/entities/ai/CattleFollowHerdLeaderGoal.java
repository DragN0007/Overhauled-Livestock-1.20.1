package com.dragn0007.dragnlivestock.entities.ai;

import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.mojang.datafixers.DataFixUtils;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;

import java.util.List;
import java.util.function.Predicate;

public class CattleFollowHerdLeaderGoal extends Goal {
   public final OCow mob;
   public int timeToRecalcPath;
   public int nextStartTick;
   public final float stopDistance;
   private final PathNavigation navigation;

   public CattleFollowHerdLeaderGoal(OCow oCow, float stopDist) {
      this.mob = oCow;
      this.stopDistance = stopDist;
      this.navigation = mob.getNavigation();
      this.nextStartTick = this.nextStartTick(oCow);
   }

   public int nextStartTick(OCow cow) {
      return reducedTickDelay(100 + cow.getRandom().nextInt(100) % 20);
   }

   public boolean canUse() {
      if (this.mob.hasFollowers() || !LivestockOverhaulCommonConfig.ANIMALS_HERDING_ENABLED.get()) {
         return false;
      } else if (this.mob.isFollower()) {
         return true;
      } else if (this.nextStartTick > 0) {
         --this.nextStartTick;
         return false;
      } else {
         this.nextStartTick = this.nextStartTick(this.mob);
         Predicate<OCow> predicate = (follower) -> {
            return follower.canBeFollowed() || !follower.isFollower();
         };
         List<? extends OCow> list = this.mob.level().getEntitiesOfClass(this.mob.getClass(), this.mob.getBoundingBox().inflate(40D, 40D, 40D), predicate);
         OCow OCow = DataFixUtils.orElse(list.stream().filter(com.dragn0007.dragnlivestock.entities.cow.OCow::canBeFollowed).findAny(), this.mob);
         OCow.addFollowers(list.stream().filter((cow) -> {
            return !cow.isFollower();
         }));
         return this.mob.isFollower();
      }
   }

   public boolean canContinueToUse() {
      return this.mob.isFollower() && this.mob.inRangeOfLeader() && LivestockOverhaulCommonConfig.ANIMALS_HERDING_ENABLED.get() && this.mob.distanceToSqr(this.mob.leader) > (double)(this.stopDistance * this.stopDistance);
   }

   public void start() {
      this.timeToRecalcPath = 0;
   }

   public void stop() {
      this.mob.stopFollowing();
   }

   public void tick() {
      if (--this.timeToRecalcPath <= 0) {
         this.timeToRecalcPath = this.adjustedTickDelay(10);
         this.mob.pathToLeader();
      }
   }

}