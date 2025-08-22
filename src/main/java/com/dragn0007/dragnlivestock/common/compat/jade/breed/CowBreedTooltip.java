package com.dragn0007.dragnlivestock.common.compat.jade.breed;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.cow.OCow;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class CowBreedTooltip implements IEntityComponentProvider {

    public CowBreedTooltip() {
    }

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor entityAccessor, IPluginConfig iPluginConfig) {
        if (entityAccessor.getEntity() instanceof OCow animal) {
            String breeds = getBreeds(animal.getBreed());
            tooltip.add(Component.translatable(breeds));
        }
    }

    private String getBreeds(int breed) {
        switch (breed) {
            case 0: return "Angus";
            case 1: return "Longhorn";
            case 2: return "Brahman";
            case 3: return "Mini";
            case 4: return "Watusi";
            case 5: return "Corriente";
            case 6: return "Holstein";
            case 7: return "Jersey";
            case 8: return "Hereford";
            case 9: return "Highland";
            case 10: return "Ox";
            default: return "Unknown";
        }
    }

    @Override
    public ResourceLocation getUid() {
        return LivestockOverhaul.id("o_tooltips");
    }
}
