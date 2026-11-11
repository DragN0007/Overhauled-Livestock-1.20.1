package com.dragn0007.dragnlivestock.compat.jade;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007_evangelix.medievalembroidery.MedievalEmbroidery;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class HoldShiftTooltip implements IEntityComponentProvider {
    public HoldShiftTooltip() {}

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor entityAccessor, IPluginConfig iPluginConfig) {
        if (!entityAccessor.getPlayer().isShiftKeyDown()) tooltip.add(Component.translatable("tooltip.dragnlivestock.shift.tooltip"));
    }

    @Override
    public ResourceLocation getUid() {return new ResourceLocation(LivestockOverhaul.MODID, "o_tooltips");}
}
