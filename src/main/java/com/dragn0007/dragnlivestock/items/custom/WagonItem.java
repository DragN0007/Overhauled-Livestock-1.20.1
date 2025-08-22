package com.dragn0007.dragnlivestock.items.custom;

import com.dragn0007.dragnlivestock.client.WagonItemRenderer;
import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractWagon;
import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractWagon.Type;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class WagonItem extends Item {

    private final Supplier<EntityType<? extends AbstractWagon>> entityType;

    public WagonItem(Supplier<EntityType<? extends AbstractWagon>> entityType, Properties properties) {
        super(properties);
        this.entityType = entityType;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.dragnlivestock.for_horses.tooltip").withStyle(ChatFormatting.GOLD));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();

        if(context.getClickedFace() != Direction.UP)
            return InteractionResult.PASS;

        if(level.isClientSide)
            return InteractionResult.SUCCESS;


        AbstractWagon wagon = entityType.get().create(level);
        wagon.setPos(Vec3.atBottomCenterOf(context.getClickedPos().above()).add(0, 0.05D, 0));
        wagon.setYRot(context.getPlayer().getYHeadRot());
        wagon.owner = context.getPlayer().getUUID();

        Type type = Type.OAK;

        CompoundTag nbt = context.getItemInHand().getTag();
        if(nbt != null && nbt.contains("type"))
            type = Type.values()[nbt.getInt("type")];

        wagon.setWoodType(type);
        level.addFreshEntity(wagon);

        context.getItemInHand().shrink(1);
        return InteractionResult.CONSUME;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            private WagonItemRenderer renderer = null;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if(renderer == null)
                    renderer = new WagonItemRenderer(entityType.get());
                return renderer;
            }

        });
    }


    public static ItemStack setupNbt(ItemStack stack, Type type) {
        if(type != null) {
            CompoundTag nbt = stack.getOrCreateTag();
            nbt.putInt("type", type.ordinal());
            stack.setTag(nbt);
        }
        return stack;
    }

}
