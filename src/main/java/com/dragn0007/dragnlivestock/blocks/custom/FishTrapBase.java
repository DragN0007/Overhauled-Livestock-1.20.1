package com.dragn0007.dragnlivestock.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FishTrapBase extends HorizontalDirectionalBlock {
    public VoxelShape NORTH;
    public VoxelShape EAST;
    public VoxelShape SOUTH;
    public VoxelShape WEST;

    public FishTrapBase(VoxelShape north, VoxelShape east, VoxelShape south, VoxelShape west) {
        super (Properties.copy(Blocks.SCAFFOLDING).sound(SoundType.SCAFFOLDING).noOcclusion());

        NORTH = north;
        EAST = east;
        SOUTH = south;
        WEST = west;

        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public VoxelShape getShape(BlockState state, BlockGetter blockReader, BlockPos pos, CollisionContext context) {
        switch(state.getValue(FACING)) {
            case NORTH:
                return NORTH;
            case EAST:
                return EAST;
            case SOUTH:
                return SOUTH;
            case WEST:
                return WEST;
            default:
                return NORTH;
        }

    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_60572_, BlockGetter p_60573_, BlockPos p_60574_, CollisionContext p_60575_) {
        return super.getCollisionShape(p_60572_, p_60573_, p_60574_, p_60575_);
    }
}
