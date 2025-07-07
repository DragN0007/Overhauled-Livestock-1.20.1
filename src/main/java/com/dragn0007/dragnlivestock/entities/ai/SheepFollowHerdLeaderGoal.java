package com.dragn0007.dragnlivestock.entities.ai;

import com.dragn0007.dragnlivestock.entities.sheep.OSheep;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.mojang.datafixers.DataFixUtils;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.List;
import java.util.function.Predicate;

public class SheepFollowHerdLeaderGoal extends Goal {
   public static final int INTERVAL_TICKS = 200;
   public final OSheep mob;
   public int timeToRecalcPath;
   public int nextStartTick;

   public SheepFollowHerdLeaderGoal(OSheep p_25249_) {
      this.mob = p_25249_;
      this.nextStartTick = this.nextStartTick(p_25249_);
   }

   public int nextStartTick(OSheep cow) {
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
         Predicate<OSheep> predicate = (follower) -> {
            return follower.canBeFollowed() || !follower.isFollower();
         };
         List<? extends OSheep> list = this.mob.level().getEntitiesOfClass(this.mob.getClass(), this.mob.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), predicate);
         OSheep OSheep = DataFixUtils.orElse(list.stream().filter(com.dragn0007.dragnlivestock.entities.sheep.OSheep::canBeFollowed).findAny(), this.mob);
         OSheep.addFollowers(list.stream().filter((cow) -> {
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

         OSheep leader = this.mob.leader;
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