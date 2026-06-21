package com.dragn0007.dragnlivestock.compat.jade.breed;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
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
        return switch (breed) {
            case 0 -> "Angus";
            case 1 -> "Longhorn";
            case 2 -> "Brahman";
            case 3 -> "Mini";
            case 4 -> "Watusi";
            case 5 -> "Corriente";
            case 6 -> "Holstein";
            case 7 -> "Jersey";
            case 8 -> "Hereford";
            case 9 -> "Highland";
            case 10 -> "Ox";
            case 11 -> "Spanish Fighting";
            default -> "Unknown";
        };
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(LivestockOverhaul.MODID, "o_tooltips");
    }
}
