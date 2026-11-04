package com.dragn0007.dragnlivestock.compat.jade.breed;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.Moobloom;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class MoobloomBreedTooltip implements IEntityComponentProvider {

    public MoobloomBreedTooltip() {
    }

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor entityAccessor, IPluginConfig iPluginConfig) {
        if (entityAccessor.getEntity() instanceof Moobloom animal) {
            String breeds = getBreeds(animal.getPlantType());
            tooltip.add(Component.translatable(breeds));
        }
    }

    private String getBreeds(int breed) {
        return switch (breed) {
            case 0 -> "Allium";
            case 1 -> "Azure Bluet";
            case 2 -> "Blue Orchid";
            case 3 -> "Cornflower";
            case 4 -> "Dandelion";
            case 5 -> "Lilac";
            case 6 -> "Lily of the Valley";
            case 7 -> "Oxeye Daisy";
            case 8 -> "Peony";
            case 9 -> "Poppy";
            case 10 -> "Rose Bush";
            case 11 -> "Sunflower";
            case 12 -> "Pink Tulip";
            case 13 -> "Beetroot";
            case 14 -> "Carrot";
            case 15 -> "Melon Slice";
            case 16 -> "Potato";
            case 17 -> "Pumpkin";
            case 18 -> "Wheat";
            case 19 -> "Azalea";
            case 20 -> "Sweet Berry";
            case 21 -> "Peach";
            default -> "Unknown";
        };
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(LivestockOverhaul.MODID, "o_tooltips");
    }
}
