package com.dragn0007.dragnlivestock.compat.jade.breed;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.sheep.OSheep;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class SheepBreedTooltip implements IEntityComponentProvider {

    public SheepBreedTooltip() {
    }

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor entityAccessor, IPluginConfig iPluginConfig) {
        if (entityAccessor.getEntity() instanceof OSheep animal) {
            String breeds = getBreeds(animal.getBreed());
            tooltip.add(Component.translatable(breeds));
        }
    }

    private String getBreeds(int breed) {
        return switch (breed) {
            case 0 -> "Gulf Coast";
            case 1 -> "Norfolk";
            case 2 -> "Dorset";
            case 3 -> "Jacob";
            case 4 -> "Racka";
            case 5 -> "California Red";
            case 6 -> "Hair";
            case 7 -> "Border Leicester";
            case 8 -> "Fat-Tailed";
            default -> "Unknown";
        };
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(LivestockOverhaul.MODID, "o_tooltips");
    }
}
