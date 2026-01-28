package com.dragn0007.dragnlivestock.items.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RabbitPoopItem extends BoneMealItem {

    public RabbitPoopItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.dragnlivestock.rabbit_poop.tooltip").withStyle(ChatFormatting.GOLD));
    }

    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockPos blockpos1 = blockpos.relative(pContext.getClickedFace());
        if (applyBonemeal(pContext.getItemInHand(), level, blockpos, pContext.getPlayer())) {
            if (!level.isClientSide) {
                level.levelEvent(1505, blockpos, 0);
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            BlockState blockstate = level.getBlockState(blockpos);
            boolean flag = blockstate.isFaceSturdy(level, blockpos, pContext.getClickedFace());
            if (flag && growWaterPlant(pContext.getItemInHand(), level, blockpos1, pContext.getClickedFace())) {
                if (!level.isClientSide) {
                    level.levelEvent(1505, blockpos1, 0);
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        }
    }

    public static boolean applyBonemeal(ItemStack stack, Level level, BlockPos pos, Player player) {
        BlockState state = level.getBlockState(pos);

        if (state.getBlock() instanceof BonemealableBlock bonemealable) {
            if (bonemealable.isValidBonemealTarget(level, pos, state, level.isClientSide())) {
                if (!level.isClientSide) {

                    for (int i = 0; i < 2; i++) {
                        if (bonemealable.isValidBonemealTarget(level, pos, state, false)) {
                            bonemealable.performBonemeal((ServerLevel) level, level.random, pos, state);
                            state = level.getBlockState(pos);
                        }
                    }
                    stack.shrink(1);
                }
            }
        }
        return true;
    }
}
