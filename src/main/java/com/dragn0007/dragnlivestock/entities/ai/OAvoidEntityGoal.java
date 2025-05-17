package com.dragn0007.dragnlivestock.entities.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.function.Predicate;

public class OAvoidEntityGoal<T extends LivingEntity> extends Goal {
   protected final PathfinderMob mob;
   private final double walkSpeedModifier;
   private final double sprintSpeedModifier;
   @Nullable
   protected T toAvoid;
   protected final float maxDist;
   @Nullable
   protected Path path;
   protected final PathNavigation pathNav;
   protected final Class<T> avoidClass;
   protected final Predicate<LivingEntity> avoidPredicate;
   protected final Predicate<LivingEntity> predicateOnAvoidEntity;
   private final TargetingConditions avoidEntityTargeting;

   public OAvoidEntityGoal(PathfinderMob mob, Class<T> avoid, Predicate<LivingEntity> livingEntityPredicate, float maxDist, double walkSpeed, double sprintSpeed, Predicate<LivingEntity> entityPredicate) {
      this.mob = mob;
      this.avoidClass = avoid;
      this.avoidPredicate = livingEntityPredicate;
      this.maxDist = maxDist;
      this.walkSpeedModifier = walkSpeed;
      this.sprintSpeedModifier = sprintSpeed;
      this.predicateOnAvoidEntity = entityPredicate;
      this.pathNav = mob.getNavigation();
      this.setFlags(EnumSet.of(Goal.Flag.MOVE));
      this.avoidEntityTargeting = TargetingConditions.forCombat().range((double)maxDist).selector(entityPredicate.and(livingEntityPredicate));
   }

   public OAvoidEntityGoal(PathfinderMob mob, Class<T> avoid, float maxDist, double walkSpeed, double sprintSpeed, Predicate<LivingEntity> predicate) {
      this(mob, avoid, (p_25049_) -> {
         return true;
      }, maxDist, walkSpeed, sprintSpeed, predicate);
   }

   public boolean canUse() {
      this.toAvoid = this.mob.level().getNearestEntity(this.mob.level().getEntitiesOfClass(this.avoidClass, this.mob.getBoundingBox().inflate((double)this.maxDist, 3.0D, (double)this.maxDist), (p_148078_) -> {
         return true;
      }), this.avoidEntityTargeting, this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ());
      if (this.toAvoid == null) {
         return false;
      } else {
         Vec3 vec3 = DefaultRandomPos.getPosAway(this.mob, 24, 8, this.toAvoid.position());
         if (vec3 == null) {
            return false;
         } else if (this.toAvoid.distanceToSqr(vec3.x, vec3.y, vec3.z) < this.toAvoid.distanceToSqr(this.mob)) {
            return false;
         } else {
            this.path = this.pathNav.createPath(vec3.x, vec3.y, vec3.z, 0);
            return this.path != null;
         }
      }
   }

   public boolean canContinueToUse() {
      return !this.pathNav.isDone();
   }

   public void start() {
      this.pathNav.moveTo(this.path, this.walkSpeedModifier);
   }

   public void stop() {
      this.toAvoid = null;
   }

   public void tick() {
      if (this.mob.distanceToSqr(this.toAvoid) < 49.0D) {
         this.mob.getNavigation().setSpeedModifier(this.sprintSpeedModifier);
      } else {
         this.mob.getNavigation().setSpeedModifier(this.walkSpeedModifier);
      }

   }
}