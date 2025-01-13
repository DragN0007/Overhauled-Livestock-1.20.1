package com.dragn0007.dragnlivestock.entities.mule;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public enum MuleBreedModel {
    STOCK(new ResourceLocation(LivestockOverhaul.MODID, "geo/mule_overhauled.geo.json")),
    MINI(new ResourceLocation(LivestockOverhaul.MODID, "geo/mule_mini.geo.json")),
    DRAFT(new ResourceLocation(LivestockOverhaul.MODID, "geo/mule_draft.geo.json"));

    public final ResourceLocation resourceLocation;

    MuleBreedModel(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    public static MuleBreedModel breedFromOrdinal(int ordinal) {
        return MuleBreedModel.values()[ordinal % MuleBreedModel.values().length];
    }

}

