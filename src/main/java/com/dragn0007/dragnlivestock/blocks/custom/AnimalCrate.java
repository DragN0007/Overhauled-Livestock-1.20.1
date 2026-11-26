package com.dragn0007.dragnlivestock.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.Nullable;

public class AnimalCrate extends BaseEntityBlock {
    public AnimalCrate() {super(Properties.copy(Blocks.OAK_PLANKS).strength(0.2F).pushReaction(PushReaction.IGNORE).noLootTable());}

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new AnimalCrateBlockEntity(pPos, pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {return RenderShape.MODEL;}
}
