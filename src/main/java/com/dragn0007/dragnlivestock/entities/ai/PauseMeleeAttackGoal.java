package com.dragn0007.dragnlivestock.entities.ai;

import com.dragn0007.dragnlivestock.entities.cow.OCow;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Path;

import java.util.EnumSet;

public class PauseMeleeAttackGoal extends Goal {
   public final OCow mob;
   public final double speedModifier;
   public final boolean followingTargetEvenIfNotSeen;
   public Path path;
   public double pathedTargetX;
   public double pathedTargetY;
   public double pathedTargetZ;
   public int ticksUntilNextPathRecalculation;
   public int ticksUntilNextAttack;
   public long lastCanUseCheck;
   public int failedPathFindingPenalty = 0;
   public boolean canPenalize = false;
   public int stalkTimer = 60;
   public static final double STALK_SPEED = 0.0D;

   public PauseMeleeAttackGoal(OCow cow, double speedMod, boolean followingTargetEvenIfNotSeen) {
      this.mob = cow;
      this.speedModifier = speedMod;
      this.followingTargetEvenIfNotSeen = followingTargetEvenIfNotSeen;
      this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
   }

   public boolean canUse() {
      long i = this.mob.level().getGameTime();
      if (i - this.lastCanUseCheck < 20L) {
         return false;
      } else {
         this.lastCanUseCheck = i;
         LivingEntity livingentity = this.mob.getTarget();
         if (livingentity == null) {
            return false;
         } else if (!livingentity.isAlive()) {
            return false;
         } else if (!mob.isMale()) {
            return false;
         } else {
           if (canPenalize) {
               if (--this.ticksUntilNextPathRecalculation <= 0) {
                  this.path = this.mob.getNavigation().createPath(livingentity, 0);
                  this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
                  return this.path != null;
               } else {
                  return true;
               }
            }
            this.path = this.mob.getNavigation().createPath(livingentity, 0);
            if (this.path != null) {
               return true;
            } else {
               return this.getAttackReachSqr(livingentity) >= this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
            }
         }
      }
   }

   public boolean canContinueToUse() {
      LivingEntity targetEntity = this.mob.getTarget();
      if (targetEntity == null) {
         return false;
      } else if (!targetEntity.isAlive()) {
         return false;
      } else if (!this.followingTargetEvenIfNotSeen) {
         return !this.mob.getNavigation().isDone();
      } else if (!this.mob.isWithinRestriction(targetEntity.blockPosition())) {
         return false;
      } else {
         return !(targetEntity instanceof Player) || !targetEntity.isSpectator() && !((Player)targetEntity).isCreative();
      }
   }

   public void start() {
      this.mob.getNavigation().moveTo(this.path, this.speedModifier);
      this.mob.setAggressive(true);
      this.ticksUntilNextPathRecalculation = 0;
      this.ticksUntilNextAttack = 0;
   }

   public void stop() {
      LivingEntity livingentity = this.mob.getTarget();
      if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
         this.mob.setTarget(null);
      }

      this.mob.setAggressive(false);
      this.mob.getNavigation().stop();
   }

   public boolean requiresUpdateEveryTick() {
      return true;
   }

   public void tick() {
      LivingEntity targetEntity = this.mob.getTarget();

      if (targetEntity != null) {
         this.mob.getLookControl().setLookAt(targetEntity, 30.0F, 30.0F);
         double d0 = this.mob.getPerceivedTargetDistanceSquareForMeleeAttack(targetEntity);
         this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
         if ((this.followingTargetEvenIfNotSeen || this.mob.getSensing().hasLineOfSight(targetEntity)) && this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || targetEntity.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || this.mob.getRandom().nextFloat() < 0.05F)) {
            this.pathedTargetX = targetEntity.getX();
            this.pathedTargetY = targetEntity.getY();
            this.pathedTargetZ = targetEntity.getZ();
            this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
         if (this.canPenalize) {
            this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
            if (this.mob.getNavigation().getPath() != null) {
               net.minecraft.world.level.pathfinder.Node finalPathPoint = this.mob.getNavigation().getPath().getEndNode();
               if (finalPathPoint != null && targetEntity.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
                  failedPathFindingPenalty = 0;
               else
                  failedPathFindingPenalty += 10;
            } else {
               failedPathFindingPenalty += 10;
            }
         }
            if (d0 > 1024.0D) {
               this.ticksUntilNextPathRecalculation += 10;
            } else if (d0 > 256.0D) {
               this.ticksUntilNextPathRecalculation += 5;
            }

            if (!this.mob.getNavigation().moveTo(targetEntity, this.speedModifier)) {
               this.ticksUntilNextPathRecalculation += 15;
            }

            this.ticksUntilNextPathRecalculation = this.adjustedTickDelay(this.ticksUntilNextPathRecalculation);
         }

         this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
         this.checkAndPerformAttack(targetEntity, d0);
      }
   }

   public void checkAndPerformAttack(LivingEntity targetEntity, double num) {
      double d0 = this.getAttackReachSqr(targetEntity);

      if (stalkTimer > 0 && num >= d0) {
         mob.getNavigation().setSpeedModifier(STALK_SPEED);
         stalkTimer--;
         this.mob.setDoneWaiting(false);
      }

      if (this.ticksUntilNextAttack <= 0 && num <= d0) {
         mob.getNavigation().setSpeedModifier(speedModifier);
         this.resetAttackCooldown();
         this.mob.swing(InteractionHand.MAIN_HAND);
         this.mob.doHurtTarget(targetEntity);
         this.mob.setDoneWaiting(true);
         stalkTimer = 60;
      }

   }

   public void resetAttackCooldown() {
      this.ticksUntilNextAttack = this.adjustedTickDelay(20);
   }

   public double getAttackReachSqr(LivingEntity p_25556_) {
      return this.mob.getBbWidth() * 3.0F * this.mob.getBbWidth() * 3.0F + p_25556_.getBbWidth();
   }
}