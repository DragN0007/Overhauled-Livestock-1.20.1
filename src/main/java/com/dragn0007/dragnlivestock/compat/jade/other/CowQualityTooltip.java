package com.dragn0007.dragnlivestock.compat.jade.other;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class CowQualityTooltip implements IEntityComponentProvider {

    public CowQualityTooltip() {
    }

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor entityAccessor, IPluginConfig iPluginConfig) {
        if (LivestockOverhaulCommonConfig.QUALITY.get()) {
            if (entityAccessor.getEntity() instanceof OCow animal) {
                String quality = Integer.toString(animal.getQuality());
                tooltip.add(Component.translatable("Quality: " + quality + "%"));
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(LivestockOverhaul.MODID, "o_tooltips");
    }
}
