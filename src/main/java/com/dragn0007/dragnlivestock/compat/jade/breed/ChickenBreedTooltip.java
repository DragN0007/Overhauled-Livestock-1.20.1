package com.dragn0007.dragnlivestock.compat.jade.breed;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.chicken.OChicken;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class ChickenBreedTooltip implements IEntityComponentProvider {

    public ChickenBreedTooltip() {
    }

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor entityAccessor, IPluginConfig iPluginConfig) {
        if (entityAccessor.getEntity() instanceof OChicken animal) {
            String breeds = getBreeds(animal.getBreed());
            tooltip.add(Component.translatable(breeds));
        }
    }

    private String getBreeds(int breed) {
        switch (breed) {
            case 0: return "Leghorn";
            case 1: return "Ameraucana";
            case 2: return "Cream Legbar";
            case 3: return "Marans";
            case 4: return "Olive Egger";
            case 5: return "Sussex Silkie";
            case 6: return "Ayam Cemani";
            case 7: return "Orpington";
            case 8: return "Polish";
            case 9: return "Wyandotte";
            default: return "Unknown";
        }
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(LivestockOverhaul.MODID, "o_tooltips");
    }
}
