package com.dragn0007.dragnlivestock.blocks.custom;

import com.dragn0007.dragnlivestock.blocks.LOBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class RawCheese extends CheeseBase {

    public RawCheese() {
        super();
        this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeTimeProperty(), 0));
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {

        if (!level.isAreaLoaded(pos, 1)) return;

        int i = this.getAgeTime(state);

        if (i <= this.getMaxAgeTime()) {
            BlockState newState = this.getStateForAgeTime(i + 1);
            level.setBlockAndUpdate(pos, newState);
        }

        if (i >= this.getMaxAgeTime()) {
            level.setBlockAndUpdate(pos, LOBlocks.CHEESE.get().defaultBlockState());
        }

    }

}
