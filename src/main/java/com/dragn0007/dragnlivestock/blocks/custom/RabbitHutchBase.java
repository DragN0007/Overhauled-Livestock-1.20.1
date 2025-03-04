package com.dragn0007.dragnlivestock.blocks.custom;

import com.dragn0007.dragnlivestock.util.LOTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RabbitHutchBase extends HorizontalDirectionalBlock {

    private boolean isHutch(BlockState state) {
        return state.is(LOTags.Blocks.RABBIT_HUTCHES);
    }

    public VoxelShape NORTH;
    public VoxelShape EAST;
    public VoxelShape SOUTH;
    public VoxelShape WEST;

    public RabbitHutchBase(VoxelShape north, VoxelShape east, VoxelShape south, VoxelShape west, Properties properties) {
        super (properties);

        NORTH = north;
        EAST = east;
        SOUTH = south;
        WEST = west;

        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

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

    public boolean connectsTo(BlockState state, boolean b, Direction direction) {
        Block block = state.getBlock();
        boolean flag = this.isHutch(state);
        boolean flag1 = block instanceof FenceGateBlock && FenceGateBlock.connectsToDirection(state, direction);
        return !isExceptionForConnection(state) && b || flag || flag1;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_60572_, BlockGetter p_60573_, BlockPos p_60574_, CollisionContext p_60575_) {
        return super.getCollisionShape(p_60572_, p_60573_, p_60574_, p_60575_);
    }
}
