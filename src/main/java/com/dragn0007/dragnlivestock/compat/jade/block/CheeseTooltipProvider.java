package com.dragn0007.dragnlivestock.compat.jade.block;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.blocks.custom.CheeseBase;
import com.dragn0007.dragnlivestock.blocks.custom.JerkyBase;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class CheeseTooltipProvider implements IBlockComponentProvider {

    public static final IntegerProperty AGE_TIME = CheeseBase.AGE_TIME;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (accessor.getBlockState().hasProperty(AGE_TIME)) {
            int time = accessor.getBlockState().getValue(AGE_TIME);
            if (accessor.getBlock() instanceof CheeseBase block) {
                int max = block.getMaxAgeTime();
                int timeFromMax = (max - time);
                int total = (max - timeFromMax);
                tooltip.add(Component.literal(total + "% Aged"));
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(LivestockOverhaul.MODID, "age_time");
    }
}
