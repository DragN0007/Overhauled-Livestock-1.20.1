package com.dragn0007.dragnlivestock.common.compat.jade.breed;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.pig.OPig;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class PigBreedTooltip implements IEntityComponentProvider {

    public PigBreedTooltip() {
    }

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor entityAccessor, IPluginConfig iPluginConfig) {
        if (entityAccessor.getEntity() instanceof OPig animal) {
            String breeds = getBreeds(animal.getBreed());
            tooltip.add(Component.translatable(breeds));
        }
    }

    private String getBreeds(int breed) {
        switch (breed) {
            case 0: return "Yorkshire";
            case 1: return "Pot-Belly";
            case 2: return "Guinea Hog";
            case 3: return "Kunekune";
            case 4: return "Poland-China";
            case 5: return "Berkshire";
            default: return "Unknown";
        }
    }

    @Override
    public ResourceLocation getUid() {
        return LivestockOverhaul.id("o_tooltips");
    }
}
