package com.dragn0007.dragnlivestock.blocks.custom;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class RabbitHutch extends RabbitHutchBase {

    public static final VoxelShape NORTH = Stream.of(
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(1, 0, 1, 15, 15.999999999999998, 1),
            Block.box(15, 0, 1, 15, 15.999999999999998, 15),
            Block.box(1, 0, 15, 15, 15.999999999999998, 15),
            Block.box(1, 0, 1, 1, 15.999999999999998, 15),
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(14, 0, 14, 16, 16, 16),
            Block.box(0, 0, 14, 2, 16, 16),
            Block.box(0, 0, 0, 2, 16, 2),
            Block.box(14, 0, 0, 16, 16, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(1, 0, 1, 15, 15.999999999999998, 1),
            Block.box(15, 0, 1, 15, 15.999999999999998, 15),
            Block.box(1, 0, 15, 15, 15.999999999999998, 15),
            Block.box(1, 0, 1, 1, 15.999999999999998, 15),
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(14, 0, 14, 16, 16, 16),
            Block.box(0, 0, 14, 2, 16, 16),
            Block.box(0, 0, 0, 2, 16, 2),
            Block.box(14, 0, 0, 16, 16, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2,BooleanOp.OR)).get();

    public static final VoxelShape SOUTH = Stream.of(
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(1, 0, 1, 15, 15.999999999999998, 1),
            Block.box(15, 0, 1, 15, 15.999999999999998, 15),
            Block.box(1, 0, 15, 15, 15.999999999999998, 15),
            Block.box(1, 0, 1, 1, 15.999999999999998, 15),
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(14, 0, 14, 16, 16, 16),
            Block.box(0, 0, 14, 2, 16, 16),
            Block.box(0, 0, 0, 2, 16, 2),
            Block.box(14, 0, 0, 16, 16, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2,BooleanOp.OR)).get();

    public static final VoxelShape WEST = Stream.of(
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(1, 0, 1, 15, 15.999999999999998, 1),
            Block.box(15, 0, 1, 15, 15.999999999999998, 15),
            Block.box(1, 0, 15, 15, 15.999999999999998, 15),
            Block.box(1, 0, 1, 1, 15.999999999999998, 15),
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(14, 0, 14, 16, 16, 16),
            Block.box(0, 0, 14, 2, 16, 16),
            Block.box(0, 0, 0, 2, 16, 2),
            Block.box(14, 0, 0, 16, 16, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2,BooleanOp.OR)).get();

    public RabbitHutch() {
        super(NORTH, EAST, SOUTH, WEST,
                Properties.copy(Blocks.OAK_PLANKS).noOcclusion().pushReaction(PushReaction.IGNORE));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

}
