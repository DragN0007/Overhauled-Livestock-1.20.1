package com.dragn0007.dragnlivestock.entities.ai;

import com.dragn0007.dragnlivestock.entities.util.AbstractSchoolingOFish;
import com.mojang.datafixers.DataFixUtils;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.List;
import java.util.function.Predicate;

public class OFollowFlockLeaderGoal extends Goal {
   private static final int INTERVAL_TICKS = 200;
   private final AbstractSchoolingOFish mob;
   private int timeToRecalcPath;
   private int nextStartTick;

   public OFollowFlockLeaderGoal(AbstractSchoolingOFish p_25249_) {
      this.mob = p_25249_;
      this.nextStartTick = this.nextStartTick(p_25249_);
   }

   public int nextStartTick(AbstractSchoolingOFish p_25252_) {
      return reducedTickDelay(200 + p_25252_.getRandom().nextInt(200) % 20);
   }

   public boolean canUse() {
      if (this.mob.hasFollowers()) {
         return false;
      } else if (this.mob.isFollower()) {
         return true;
      } else if (this.nextStartTick > 0) {
         --this.nextStartTick;
         return false;
      } else {
         this.nextStartTick = this.nextStartTick(this.mob);
         Predicate<AbstractSchoolingOFish> predicate = (p_25258_) -> {
            return p_25258_.canBeFollowed() || !p_25258_.isFollower();
         };
         List<? extends AbstractSchoolingOFish> list = this.mob.level().getEntitiesOfClass(this.mob.getClass(), this.mob.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), predicate);
         AbstractSchoolingOFish abstractschoolingfish = DataFixUtils.orElse(list.stream().filter(AbstractSchoolingOFish::canBeFollowed).findAny(), this.mob);
         abstractschoolingfish.addFollowers(list.stream().filter((p_25255_) -> {
            return !p_25255_.isFollower();
         }));
         return this.mob.isFollower();
      }
   }

   public boolean canContinueToUse() {
      return this.mob.isFollower() && this.mob.inRangeOfLeader();
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