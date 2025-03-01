package com.dragn0007.dragnlivestock.blocks.custom;

import com.dragn0007.dragnlivestock.blocks.LOBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class RawGenericJerky extends JerkyBase {

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

    public RawGenericJerky() {
        super(NORTH, EAST, SOUTH, WEST,
                Properties.of().sound(SoundType.FUNGUS).strength(0.2F).pushReaction(PushReaction.DESTROY).noOcclusion().noCollission().randomTicks());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        this.tick(state, level, pos, source);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        level.scheduleTick(pos, this, dryTime);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {

        if (dryTime > 0) {
            level.scheduleTick(pos, this, 1);
        }

        --dryTime;

        if (--dryTime <= 0) {
            BlockState blockState = level.getBlockState(pos);
            Direction facingDirection = blockState.getValue(BlockStateProperties.HORIZONTAL_FACING);
            BlockState state1 = LOBlocks.GENERIC_JERKY_HANGING.get().defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, facingDirection);
            level.setBlockAndUpdate(pos, state1);
        }

    }

}
