package com.dragn0007.dragnlivestock.blocks.custom;

import com.dragn0007.dragnlivestock.util.LOTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class RabbitHutchBase extends HorizontalDirectionalBlock {

    private boolean isHutch(BlockState state) {
        return state.is(LOTags.Blocks.RABBIT_HUTCHES);
    }

    public VoxelShape NORTH;
    public VoxelShape EAST;
    public VoxelShape SOUTH;
    public VoxelShape WEST;

    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;

    private final net.minecraft.sounds.SoundEvent openSound;
    private final net.minecraft.sounds.SoundEvent closeSound;

    public RabbitHutchBase(VoxelShape north, VoxelShape east, VoxelShape south, VoxelShape west, Properties properties, net.minecraft.sounds.SoundEvent openSound, net.minecraft.sounds.SoundEvent closeSound) {
        super (properties);
        NORTH = north;
        EAST = east;
        SOUTH = south;
        WEST = west;
        this.openSound = openSound;
        this.closeSound = closeSound;

        this.registerDefaultState(this.stateDefinition.any().setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH).setValue(OPEN, Boolean.valueOf(false)));
    }

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        boolean flag = level.hasNeighborSignal(blockpos);
        Direction direction = context.getHorizontalDirection();
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(OPEN, Boolean.valueOf(flag));
    }

    public boolean isPathfindable(BlockState p_53360_, BlockGetter p_53361_, BlockPos p_53362_, PathComputationType p_53363_) {
        switch (p_53363_) {
            case LAND:
                return p_53360_.getValue(OPEN);
            default:
                return false;
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_53389_) {
        p_53389_.add(FACING, OPEN);
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

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (state.getValue(OPEN)) {
            state = state.setValue(OPEN, Boolean.valueOf(false));
            level.setBlock(pos, state, 10);
        } else {
            state = state.setValue(OPEN, Boolean.valueOf(true));
            level.setBlock(pos, state, 10);
        }

        boolean flag = state.getValue(OPEN);
        level.playSound(player, pos, flag ? this.openSound : this.closeSound, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
        level.gameEvent(player, flag ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    public boolean connectsTo(BlockState state) {
        return !isExceptionForConnection(state) && this.isHutch(state);
    }

    public VoxelShape getCollisionShape(BlockState state, BlockGetter p_53397_, BlockPos p_53398_, CollisionContext p_53399_) {

        Direction facing = state.getValue(FACING);

        if (state.getValue(OPEN)) {
            switch (facing) {
                case NORTH:
                    return Stream.of(
                        Block.box(0, 15, 0, 16, 16, 16),
                        Block.box(14, 0, 1, 16, 16, 15),
                        Block.box(1, 0, 14, 15, 16, 16),
                        Block.box(0, 0, 1, 2, 16, 15),
                        Block.box(0, 0, 0, 16, 1, 16),
                        Block.box(14, 0, 14, 16, 16, 16),
                        Block.box(0, 0, 14, 2, 16, 16),
                        Block.box(0, 0, 0, 2, 16, 2),
                        Block.box(14, 0, 0, 16, 16, 2)
                    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                case EAST:
                    return Stream.of(
                        Block.box(0, 15, 0, 16, 16, 16),
                        Block.box(1, 0, 14, 15, 16, 16),
                        Block.box(0, 0, 1, 2, 16, 15),
                        Block.box(1, 0, 0, 15, 16, 2),
                        Block.box(0, 0, 0, 16, 1, 16),
                        Block.box(0, 0, 14, 2, 16, 16),
                        Block.box(0, 0, 0, 2, 16, 2),
                        Block.box(14, 0, 0, 16, 16, 2),
                        Block.box(14, 0, 14, 16, 16, 16)
                    ).reduce((v1, v2) -> Shapes.join(v1, v2,BooleanOp.OR)).get();
                case SOUTH:
                    return Stream.of(
                        Block.box(0, 15, 0, 16, 16, 16),
                        Block.box(0, 0, 1, 2, 16, 15),
                        Block.box(1, 0, 0, 15, 16, 2),
                        Block.box(14, 0, 1, 16, 16, 15),
                        Block.box(0, 0, 0, 16, 1, 16),
                        Block.box(0, 0, 0, 2, 16, 2),
                        Block.box(14, 0, 0, 16, 16, 2),
                        Block.box(14, 0, 14, 16, 16, 16),
                        Block.box(0, 0, 14, 2, 16, 16)
                    ).reduce((v1, v2) -> Shapes.join(v1, v2,BooleanOp.OR)).get();
                case WEST:
                    return Stream.of(
                        Block.box(0, 15, 0, 16, 16, 16),
                        Block.box(1, 0, 0, 15, 16, 2),
                        Block.box(14, 0, 1, 16, 16, 15),
                        Block.box(1, 0, 14, 15, 16, 16),
                        Block.box(0, 0, 0, 16, 1, 16),
                        Block.box(14, 0, 0, 16, 16, 2),
                        Block.box(14, 0, 14, 16, 16, 16),
                        Block.box(0, 0, 14, 2, 16, 16),
                        Block.box(0, 0, 0, 2, 16, 2)
                    ).reduce((v1, v2) -> Shapes.join(v1, v2,BooleanOp.OR)).get();
                default:
                    return Shapes.empty();
            }
        }

        switch (facing) {
            case NORTH:
                return NORTH;
            case EAST:
                return EAST;
            case SOUTH:
                return SOUTH;
            case WEST:
                return WEST;
            default:
                return Shapes.empty();
        }
    }

    @Override
    public boolean isCollisionShapeFullBlock(BlockState p_181242_, BlockGetter p_181243_, BlockPos p_181244_) {
        return true;
    }
}
