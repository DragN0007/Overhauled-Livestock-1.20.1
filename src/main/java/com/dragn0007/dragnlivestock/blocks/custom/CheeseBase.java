package com.dragn0007.dragnlivestock.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CheeseBase extends HorizontalDirectionalBlock {

    public static final IntegerProperty AGE_TIME = IntegerProperty.create("age_time", 0, 13);

    protected IntegerProperty getAgeTimeProperty() {
        return AGE_TIME;
    }

    public int getMaxAgeTime() {
        return 12;
    }

    public int getAgeTime(BlockState state) {
        return state.getValue(this.getAgeTimeProperty());
    }

    public BlockState getStateForAgeTime(int i) {
        return this.defaultBlockState().setValue(this.getAgeTimeProperty(), Integer.valueOf(i));
    }

    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE_TIME);
    }
    public CheeseBase() {
        super (Properties.copy(Blocks.OAK_PLANKS).strength(0.3F).noOcclusion().pushReaction(PushReaction.IGNORE));
    }

    public VoxelShape getShape(BlockState state, BlockGetter blockReader, BlockPos pos, CollisionContext context) {
        return Block.box(2, 0, 2, 14, 6, 14);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_60572_, BlockGetter p_60573_, BlockPos p_60574_, CollisionContext p_60575_) {
        return super.getCollisionShape(p_60572_, p_60573_, p_60574_, p_60575_);
    }
}
