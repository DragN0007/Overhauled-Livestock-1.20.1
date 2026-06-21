package com.dragn0007.dragnlivestock.compat.jade.breed;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class HorseBreedTooltip implements IEntityComponentProvider {

    public HorseBreedTooltip() {
    }

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor entityAccessor, IPluginConfig iPluginConfig) {
        if (entityAccessor.getEntity() instanceof OHorse animal && entityAccessor.getEntity().getClass() == OHorse.class) {
            String breeds = getBreeds(animal.getBreed());
            tooltip.add(Component.translatable(breeds));
        }
    }

    private String getBreeds(int breed) {
        return switch (breed) {
            case 0 -> "Mustang";
            case 1 -> "Ardennes";
            case 2 -> "Kladruber";
            case 3 -> "Fjord";
            case 4 -> "Thoroughbred";
            case 5 -> "Friesian";
            case 6 -> "Irish Cob";
            case 7 -> "American Quarter";
            case 8 -> "Percheron";
            case 9 -> "Selle Francais";
            case 10 -> "Marwari";
            case 11 -> "Mongolian";
            case 12 -> "Shire";
            case 13 -> "Akhal-Teke";
            case 14 -> "American Soldier";
            case 15 -> "Welsh Pony";
            case 16 -> "Connemara";
            case 17 -> "Haflinger";
            case 18 -> "Oldenburger";
            case 19 -> "Shetland";
            case 20 -> "Standardbred";
            case 21 -> "Trakehner";
            case 22 -> "Boulonnais";
            default -> "Unknown";
        };
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(LivestockOverhaul.MODID, "o_tooltips");
    }
}
