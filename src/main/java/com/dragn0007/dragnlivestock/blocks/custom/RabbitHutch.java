package com.dragn0007.dragnlivestock.blocks.custom;

import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class RabbitHutch extends RabbitHutchBase {

    public static final VoxelShape NORTH = Stream.of(
            box(0, 15, 0, 16, 16, 16),
            box(1, 0, 0, 15, 16, 2),
            box(14, 0, 1, 16, 16, 15),
            box(1, 0, 14, 15, 16, 16),
            box(0, 0, 1, 2, 16, 15),
            box(0, 0, 0, 16, 1, 16),
            box(14, 0, 14, 16, 16, 16),
            box(0, 0, 14, 2, 16, 16),
            box(0, 0, 0, 2, 16, 2),
            box(14, 0, 0, 16, 16, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST = Stream.of(
            box(0, 15, 0, 16, 16, 16),
            box(1, 0, 0, 15, 16, 2),
            box(14, 0, 1, 16, 16, 15),
            box(1, 0, 14, 15, 16, 16),
            box(0, 0, 1, 2, 16, 15),
            box(0, 0, 0, 16, 1, 16),
            box(14, 0, 14, 16, 16, 16),
            box(0, 0, 14, 2, 16, 16),
            box(0, 0, 0, 2, 16, 2),
            box(14, 0, 0, 16, 16, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2,BooleanOp.OR)).get();

    public static final VoxelShape SOUTH = Stream.of(
            box(0, 15, 0, 16, 16, 16),
            box(1, 0, 0, 15, 16, 2),
            box(14, 0, 1, 16, 16, 15),
            box(1, 0, 14, 15, 16, 16),
            box(0, 0, 1, 2, 16, 15),
            box(0, 0, 0, 16, 1, 16),
            box(14, 0, 14, 16, 16, 16),
            box(0, 0, 14, 2, 16, 16),
            box(0, 0, 0, 2, 16, 2),
            box(14, 0, 0, 16, 16, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2,BooleanOp.OR)).get();

    public static final VoxelShape WEST = Stream.of(
            box(0, 15, 0, 16, 16, 16),
            box(1, 0, 0, 15, 16, 2),
            box(14, 0, 1, 16, 16, 15),
            box(1, 0, 14, 15, 16, 16),
            box(0, 0, 1, 2, 16, 15),
            box(0, 0, 0, 16, 1, 16),
            box(14, 0, 14, 16, 16, 16),
            box(0, 0, 14, 2, 16, 16),
            box(0, 0, 0, 2, 16, 2),
            box(14, 0, 0, 16, 16, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2,BooleanOp.OR)).get();

    public RabbitHutch() {
        super(NORTH, EAST, SOUTH, WEST, Properties.copy(Blocks.OAK_PLANKS).noOcclusion().pushReaction(PushReaction.IGNORE), SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE, SoundEvents.CHERRY_WOOD_FENCE_GATE_OPEN);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

}
