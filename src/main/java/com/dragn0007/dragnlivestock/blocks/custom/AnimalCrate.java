package com.dragn0007.dragnlivestock.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.Nullable;

public class AnimalCrate extends BaseEntityBlock {
    public AnimalCrate() {super(Properties.copy(Blocks.OAK_PLANKS).strength(0.2F).pushReaction(PushReaction.IGNORE).noLootTable());}

//    @Override
//    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
//        super.setPlacedBy(level, pos, state, placer, stack);
//        if (!level.isClientSide) {
//            BlockEntity blockEntity = level.getBlockEntity(pos);
//            CompoundTag tag = stack.getTag();
//
//            if (blockEntity != null && tag != null) {
//                if (blockEntity instanceof AnimalCrateBlockEntity crateBlockEntity) {
//                    CompoundTag blockNbt = crateBlockEntity.saveWithoutMetadata();
//                    crateBlockEntity.saveAdditional(tag);
//                    crateBlockEntity.load(blockNbt);
//                    crateBlockEntity.setChanged();
//                }
//            }
//        }
//    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new AnimalCrateBlockEntity(pPos, pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {return RenderShape.MODEL;}
}
