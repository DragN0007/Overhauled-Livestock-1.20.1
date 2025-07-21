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
        switch (breed) {
            case 0: return "Mustang";
            case 1: return "Ardennes";
            case 2: return "Kladruber";
            case 3: return "Fjord";
            case 4: return "Thoroughbred";
            case 5: return "Friesian";
            case 6: return "Irish Cob";
            case 7: return "American Quarter";
            case 8: return "Percheron";
            case 9: return "Selle Francais";
            case 10: return "Marwari";
            case 11: return "Mongolian";
            case 12: return "Shire";
            case 13: return "Akhal-Teke";
            case 14: return "American Soldier";
            default: return "Unknown";
        }
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(LivestockOverhaul.MODID, "o_tooltips");
    }
}
