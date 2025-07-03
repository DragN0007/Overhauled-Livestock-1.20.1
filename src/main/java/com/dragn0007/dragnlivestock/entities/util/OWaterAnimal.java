package com.dragn0007.dragnlivestock.entities.util;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

public abstract class OWaterAnimal extends AgeableMob {
   public OWaterAnimal(EntityType<? extends OWaterAnimal> p_30341_, Level p_30342_) {
      super(p_30341_, p_30342_);
      this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
   }

   public boolean canBreatheUnderwater() {
      return true;
   }

   public MobType getMobType() {
      return MobType.WATER;
   }

   public boolean checkSpawnObstruction(LevelReader p_30348_) {
      return p_30348_.isUnobstructed(this);
   }

   public int getAmbientSoundInterval() {
      return 120;
   }

   public int getExperienceReward() {
      return 1 + this.level().random.nextInt(3);
   }

   public void handleAirSupply(int p_30344_) {
      if (this.isAlive() && !this.isInWaterOrBubble()) {
         this.setAirSupply(p_30344_ - 1);
         if (this.getAirSupply() == -20) {
            this.setAirSupply(0);
            this.hurt(this.damageSources().drown(), 2.0F);
         }
      } else {
         this.setAirSupply(300);
      }

   }

   public void baseTick() {
      int i = this.getAirSupply();
      super.baseTick();
      this.handleAirSupply(i);
   }

   public boolean isPushedByFluid() {
      return false;
   }

   public boolean canBeLeashed(Player p_30346_) {
      return false;
   }
}