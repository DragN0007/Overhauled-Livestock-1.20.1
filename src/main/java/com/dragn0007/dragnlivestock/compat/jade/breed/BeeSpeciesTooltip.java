package com.dragn0007.dragnlivestock.compat.jade.breed;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.bee.OBee;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class BeeSpeciesTooltip implements IEntityComponentProvider {

    public BeeSpeciesTooltip() {
    }

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor entityAccessor, IPluginConfig iPluginConfig) {
        if (entityAccessor.getEntity() instanceof OBee animal) {
            String breeds = getBreeds(animal.getVariant());
            tooltip.add(Component.translatable(breeds));
        }
    }

    private String getBreeds(int breed) {
        switch (breed) {
            case 0: return "Bumble Bee";
            case 1: return "Ashy Mining Bee";
            case 2: return "Garden Bee";
            case 3: return "Honey Bee";
            case 4: return "Red Mason Bee";
            case 5: return "Red-Tailed Bee";
            case 6: return "Tawny Mining Bee";
            case 7: return "Tree Bumble Bee";
            default: return "Unknown";
        }
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(LivestockOverhaul.MODID, "o_tooltips");
    }
}
