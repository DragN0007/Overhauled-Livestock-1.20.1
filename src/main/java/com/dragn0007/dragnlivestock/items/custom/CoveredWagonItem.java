package com.dragn0007.dragnlivestock.items.custom;

import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.wagons.covered.CoveredWagon;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CoveredWagonItem extends Item {

    public CoveredWagonItem() {
        super(new Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        BlockHitResult blockHitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);

        if(blockHitResult.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemStack);
        } else if (level.isClientSide) {
            return InteractionResultHolder.success(itemStack);
        } else {
            BlockPos pos = blockHitResult.getBlockPos();
            if(level.getBlockState(pos).getBlock() instanceof LiquidBlock) {
                return InteractionResultHolder.pass(itemStack);
            } else if (level.mayInteract(player, pos) && player.mayUseItemAt(pos, blockHitResult.getDirection(), itemStack)) {
                CoveredWagon coveredWagon = EntityTypes.COVERED_WAGON_ENTITY.get().spawn((ServerLevel) level, itemStack, player, pos.above(), MobSpawnType.SPAWN_EGG, false, false);
                if(coveredWagon == null) {
                    return InteractionResultHolder.pass(itemStack);
                } else {
                    if(!player.getAbilities().instabuild) {
                        itemStack.shrink(1);
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                    level.gameEvent(player, GameEvent.ENTITY_PLACE, coveredWagon.position());
                    return InteractionResultHolder.consume(itemStack);
                }
            } else {
                return InteractionResultHolder.fail(itemStack);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.dragnlivestock.not_functional.tooltip").withStyle(ChatFormatting.GRAY));
    }
}
