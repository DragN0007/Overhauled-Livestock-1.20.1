package com.dragn0007.dragnlivestock.compat.jade.gender;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.llama.OLlama;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class LlamaGenderTooltip implements IEntityComponentProvider {

    public LlamaGenderTooltip() {
    }

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor entityAccessor, IPluginConfig iPluginConfig) {
        OLlama animal = (OLlama)entityAccessor.getEntity();
        if (animal.isFemale()) {
            tooltip.add(Component.translatable("tooltip.dragnlivestock.jade.female.tooltip"));
        } else if (animal.isMale()) {
            tooltip.add(Component.translatable("tooltip.dragnlivestock.jade.male.tooltip"));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(LivestockOverhaul.MODID, "o_tooltips");
    }

}
