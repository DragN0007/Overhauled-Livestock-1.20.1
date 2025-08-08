package com.dragn0007.dragnlivestock.entities.ai;

import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.farmgoat.FarmGoat;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.List;

public class FarmGoatFollowCaravanGoal extends Goal {
   public final FarmGoat goat;
   private double speedModifier;
   private static final int CARAVAN_LIMIT = 8;
   private int distCheckCounter;

   public FarmGoatFollowCaravanGoal(FarmGoat goat, double speedMod) {
      this.goat = goat;
      this.speedModifier = speedMod;
      this.setFlags(EnumSet.of(Flag.MOVE));
   }

   public boolean canUse() {
      if (!this.goat.isLeashed() && !this.goat.inCaravan()) {
         List<Entity> list = this.goat.level().getEntities(this.goat, this.goat.getBoundingBox().inflate(9.0D, 4.0D, 9.0D), (p_25505_) -> {
            EntityType<?> entitytype = p_25505_.getType();
            return entitytype == EntityTypes.O_GOAT_ENTITY.get();
         });
         FarmGoat llama = null;
         double d0 = Double.MAX_VALUE;

         for(Entity entity : list) {
            FarmGoat llama1 = (FarmGoat)entity;
            if (llama1.inCaravan() && !llama1.hasCaravanTail()) {
               double d1 = this.goat.distanceToSqr(llama1);
               if (!(d1 > d0)) {
                  d0 = d1;
                  llama = llama1;
               }
            }
         }

         if (llama == null) {
            for(Entity entity1 : list) {
               FarmGoat llama2 = (FarmGoat)entity1;
               if (llama2.isLeashed() && !llama2.hasCaravanTail()) {
                  double d2 = this.goat.distanceToSqr(llama2);
                  if (!(d2 > d0)) {
                     d0 = d2;
                     llama = llama2;
                  }
               }
            }
         }

         if (llama == null) {
            return false;
         } else if (d0 < 4.0D) {
            return false;
         } else if (!llama.isLeashed() && !this.firstIsLeashed(llama, 1)) {
            return false;
         } else {
            this.goat.joinCaravan(llama);
            return true;
         }
      } else {
         return false;
      }
   }

   public boolean canContinueToUse() {
      if (this.goat.inCaravan() && this.goat.getCaravanHead().isAlive() && this.firstIsLeashed(this.goat, 0)) {
         double d0 = this.goat.distanceToSqr(this.goat.getCaravanHead());
         if (d0 > 676.0D) {
            if (this.speedModifier <= 3.0D) {
               this.speedModifier *= 1.2D;
               this.distCheckCounter = reducedTickDelay(40);
               return true;
            }

            if (this.distCheckCounter == 0) {
               return false;
            }
         }

         if (this.distCheckCounter > 0) {
            --this.distCheckCounter;
         }

         return true;
      } else {
         return false;
      }
   }

   public void stop() {
      this.goat.leaveCaravan();
      this.speedModifier = 2.1D;
   }

   public void tick() {
      if (this.goat.inCaravan()) {
         if (!(this.goat.getLeashHolder() instanceof LeashFenceKnotEntity)) {
            FarmGoat llama = this.goat.getCaravanHead();
            double d0 = (double)this.goat.distanceTo(llama);
            float f = 2.0F;
            Vec3 vec3 = (new Vec3(llama.getX() - this.goat.getX(), llama.getY() - this.goat.getY(), llama.getZ() - this.goat.getZ())).normalize().scale(Math.max(d0 - 2.0D, 0.0D));
            this.goat.getNavigation().moveTo(this.goat.getX() + vec3.x, this.goat.getY() + vec3.y, this.goat.getZ() + vec3.z, this.speedModifier);
         }
      }
   }

   private boolean firstIsLeashed(FarmGoat p_25507_, int p_25508_) {
      if (p_25508_ > 8) {
         return false;
      } else if (p_25507_.inCaravan()) {
         if (p_25507_.getCaravanHead().isLeashed()) {
            return true;
         } else {
            FarmGoat llama = p_25507_.getCaravanHead();
            ++p_25508_;
            return this.firstIsLeashed(llama, p_25508_);
         }
      } else {
         return false;
      }
   }
}