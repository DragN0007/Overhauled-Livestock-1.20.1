package com.dragn0007.dragnlivestock.common.entities.ai;

import com.dragn0007.dragnlivestock.common.entities.cow.OCow;
import com.dragn0007.dragnlivestock.common.LivestockOverhaulCommonConfig;
import com.mojang.datafixers.DataFixUtils;
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
      return reducedTickDelay(200 + cow.getRandom().nextInt(200) % 20);
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
         List<? extends OCow> list = this.mob.level().getEntitiesOfClass(this.mob.getClass(), this.mob.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), predicate);
         OCow OCow = DataFixUtils.orElse(list.stream().filter(com.dragn0007.dragnlivestock.common.entities.cow.OCow::canBeFollowed).findAny(), this.mob);
         OCow.addFollowers(list.stream().filter((cow) -> {
            return !cow.isFollower();
         }));
         return this.mob.isFollower();
      }
   }

   public boolean canContinueToUse() {
      return this.mob.isFollower() && this.mob.inRangeOfLeader() && LivestockOverhaulCommonConfig.ANIMALS_HERDING_ENABLED.get();
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

         OCow leader = this.mob.leader;
         if (mob.goalSelector.getRunningGoals().noneMatch(goal -> goal.getGoal() instanceof OAvoidEntityGoal<?>)) {
            if (leader != null) {
               double distanceSq = this.mob.distanceToSqr(leader);
               double minDistanceSq = 3.0D * 3.0D;

               if (distanceSq > minDistanceSq) {
                  this.mob.pathToLeader();
               } else {
                  this.mob.getNavigation().stop();
               }
            }
         }
      }
   }

}