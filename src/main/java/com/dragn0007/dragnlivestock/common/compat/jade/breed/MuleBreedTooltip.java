package com.dragn0007.dragnlivestock.common.compat.jade.breed;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.mule.OMule;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class MuleBreedTooltip implements IEntityComponentProvider {

    public MuleBreedTooltip() {
    }

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor entityAccessor, IPluginConfig iPluginConfig) {
        if (entityAccessor.getEntity() instanceof OMule animal) {
            String breeds = getBreeds(animal.getBreed());
            tooltip.add(Component.translatable(breeds));
        }
    }

    private String getBreeds(int breed) {
        switch (breed) {
            case 0: return "Stock";
            case 1: return "Mini";
            case 2: return "Draft";
            default: return "Unknown";
        }
    }

    @Override
    public ResourceLocation getUid() {
        return LivestockOverhaul.id("o_tooltips");
    }
}
