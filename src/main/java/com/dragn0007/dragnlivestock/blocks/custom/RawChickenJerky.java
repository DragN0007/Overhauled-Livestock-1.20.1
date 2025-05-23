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

public class RawChickenJerky extends JerkyBase {

    public static final VoxelShape NORTH = Stream.of(
            Block.box(3, 0, 14, 13, 14, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 0, 3, 2, 14, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2,BooleanOp.OR)).get();

    public static final VoxelShape SOUTH = Stream.of(
            Block.box(3, 0, 0, 13, 14, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2,BooleanOp.OR)).get();

    public static final VoxelShape WEST = Stream.of(
            Block.box(14, 0, 3, 16, 14, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2,BooleanOp.OR)).get();

    public RawChickenJerky() {
        super(NORTH, EAST, SOUTH, WEST,
                Properties.of().sound(SoundType.FUNGUS).strength(0.2F).pushReaction(PushReaction.DESTROY).noOcclusion().noCollission().randomTicks());
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(this.getDryTimeProperty(), 0));
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {

        if (!level.isAreaLoaded(pos, 1)) return;

        int i = this.getDryTime(state);

        if (i <= this.getMaxDryTime()) {
            BlockState blockState = level.getBlockState(pos);
            Direction facingDirection = blockState.getValue(BlockStateProperties.HORIZONTAL_FACING);
            BlockState newState = this.getStateForDryTime(i + 1).setValue(BlockStateProperties.HORIZONTAL_FACING, facingDirection);
            level.setBlockAndUpdate(pos, newState);

        }

        if (i >= this.getMaxDryTime()) {
            BlockState blockState = level.getBlockState(pos);
            Direction facingDirection = blockState.getValue(BlockStateProperties.HORIZONTAL_FACING);
            BlockState state1 = LOBlocks.CHICKEN_JERKY_HANGING.get().defaultBlockState()
                    .setValue(BlockStateProperties.HORIZONTAL_FACING, facingDirection)
                    .setValue(this.getDryTimeProperty(), 0);
            level.setBlockAndUpdate(pos, state1);
        }

    }

}
