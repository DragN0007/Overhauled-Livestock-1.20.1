package com.dragn0007.dragnlivestock.items.custom;

//public class CoveredWagonItem extends Item {
//
//    public CoveredWagonItem() {
//        super(new Properties());
//    }
//
//    @Override
//    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
//        ItemStack itemStack = player.getItemInHand(hand);
//        BlockHitResult blockHitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
//
//        if(blockHitResult.getType() != HitResult.Type.BLOCK) {
//            return InteractionResultHolder.pass(itemStack);
//        } else if (level.isClientSide) {
//            return InteractionResultHolder.success(itemStack);
//        } else {
//            BlockPos pos = blockHitResult.getBlockPos();
//            if(level.getBlockState(pos).getBlock() instanceof LiquidBlock) {
//                return InteractionResultHolder.pass(itemStack);
//            } else if (level.mayInteract(player, pos) && player.mayUseItemAt(pos, blockHitResult.getDirection(), itemStack)) {
//                CoveredWagon coveredWagon = EntityTypes.COVERED_WAGON_ENTITY.get().spawn((ServerLevel) level, itemStack, player, pos.above(), MobSpawnType.SPAWN_EGG, false, false);
//                if(coveredWagon == null) {
//                    return InteractionResultHolder.pass(itemStack);
//                } else {
//                    if(!player.getAbilities().instabuild) {
//                        itemStack.shrink(1);
//                    }
//
//                    player.awardStat(Stats.ITEM_USED.get(this));
//                    level.gameEvent(player, GameEvent.ENTITY_PLACE, coveredWagon.position());
//                    return InteractionResultHolder.consume(itemStack);
//                }
//            } else {
//                return InteractionResultHolder.fail(itemStack);
//            }
//        }
//    }
//
//    @Override
//    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
//        pTooltipComponents.add(Component.translatable("tooltip.dragnlivestock.not_functional.tooltip").withStyle(ChatFormatting.GRAY));
//    }
//}
