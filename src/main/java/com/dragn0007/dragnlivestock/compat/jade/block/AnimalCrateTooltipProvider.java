package com.dragn0007.dragnlivestock.compat.jade.block;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.blocks.custom.AnimalCrateBlockEntity;
import com.dragn0007.dragnlivestock.blocks.custom.JerkyBase;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class AnimalCrateTooltipProvider implements IBlockComponentProvider {

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (accessor.getBlockEntity() instanceof AnimalCrateBlockEntity entity) {
            CompoundTag blockNbt = entity.saveWithoutMetadata();
            if (blockNbt != null) tooltip.add(Component.literal("Occupied."));
            else tooltip.add(Component.literal("Empty."));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(LivestockOverhaul.MODID, "dry_time");
    }
}
