package com.dragn0007.dragnlivestock.entities.mule;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public enum MuleBreed {
    STOCK(new ResourceLocation(LivestockOverhaul.MODID, "geo/mule/mule_overhauled.geo.json")),
    MINI(new ResourceLocation(LivestockOverhaul.MODID, "geo/mule/mule_mini.geo.json")),
    DRAFT(new ResourceLocation(LivestockOverhaul.MODID, "geo/mule/mule_draft.geo.json"));

    public final ResourceLocation resourceLocation;

    MuleBreed(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    public static MuleBreed breedFromOrdinal(int ordinal) {
        return MuleBreed.values()[ordinal % MuleBreed.values().length];
    }

}

