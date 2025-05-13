package com.dragn0007.dragnlivestock.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class JerkyBase extends HorizontalDirectionalBlock {

    public static final IntegerProperty DRY_TIME = IntegerProperty.create("dry_time", 0, 17);

    protected IntegerProperty getDryTimeProperty() {
        return DRY_TIME;
    }

    public int getMaxDryTime() {
        return 16;
    }

    public int getDryTime(BlockState state) {
        return state.getValue(this.getDryTimeProperty());
    }

    public BlockState getStateForDryTime(int i) {
        return this.defaultBlockState().setValue(this.getDryTimeProperty(), Integer.valueOf(i));
    }

    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DRY_TIME).add(HorizontalDirectionalBlock.FACING);
    }

    public VoxelShape NORTH;
    public VoxelShape EAST;
    public VoxelShape SOUTH;
    public VoxelShape WEST;

    public JerkyBase(VoxelShape north, VoxelShape east, VoxelShape south, VoxelShape west, Properties properties) {
        super (properties);

        NORTH = north;
        EAST = east;
        SOUTH = south;
        WEST = west;

        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
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
