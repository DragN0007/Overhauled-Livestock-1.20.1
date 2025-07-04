package com.dragn0007.dragnlivestock.entities.ai;

import com.dragn0007.dragnlivestock.entities.frog.OFrog;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class FrogSitOnBlockGoal extends MoveToBlockGoal {
   private final OFrog frog;

   public FrogSitOnBlockGoal(OFrog p_25149_, double p_25150_) {
      super(p_25149_, p_25150_, 8);
      this.frog = p_25149_;
   }

   public boolean canUse() {
      return super.canUse();
   }

   public void start() {
      super.start();
      frog.getNavigation().stop();
   }

   public void stop() {
      super.stop();
      frog.getNavigation().isDone();
   }

   public void tick() {
      super.tick();
   }

   public boolean isValidTarget(LevelReader blockState, BlockPos pos) {
      if (!blockState.isEmptyBlock(pos.above())) {
         return false;
      } else {
         BlockState blockstate = blockState.getBlockState(pos);
            return blockstate.is(Blocks.LILY_PAD);
         }
      }


}