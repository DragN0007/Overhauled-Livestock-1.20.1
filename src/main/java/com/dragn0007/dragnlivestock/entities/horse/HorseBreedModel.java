package com.dragn0007.dragnlivestock.entities.horse;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public enum HorseBreedModel {
    MUSTANG(new ResourceLocation(LivestockOverhaul.MODID, "geo/o_horse.geo.json")),
    ARDENNES(new ResourceLocation(LivestockOverhaul.MODID, "geo/ardennes.geo.json")),
    KLADRUBER(new ResourceLocation(LivestockOverhaul.MODID, "geo/kladruber.geo.json")),
    FJORD(new ResourceLocation(LivestockOverhaul.MODID, "geo/fjord.geo.json")),
    THOROUGHBRED(new ResourceLocation(LivestockOverhaul.MODID, "geo/thoroughbred.geo.json")),
    FRIESIAN(new ResourceLocation(LivestockOverhaul.MODID, "geo/friesian.geo.json")),
    IRISH_COB(new ResourceLocation(LivestockOverhaul.MODID, "geo/irish_cob.geo.json")),
    AMERICAN_QUARTER(new ResourceLocation(LivestockOverhaul.MODID, "geo/american_quarter.geo.json")),
    PERCHERON(new ResourceLocation(LivestockOverhaul.MODID, "geo/percheron.geo.json")),
    SELLE_FRANCAIS(new ResourceLocation(LivestockOverhaul.MODID, "geo/selle_francais.geo.json")),
    MARWARI(new ResourceLocation(LivestockOverhaul.MODID, "geo/marwari.geo.json")),
    MONGOLIAN(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse_mongolian.geo.json")),
    SHIRE(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse_shire.geo.json")),
    AKHAL_TEKE(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse_ahkal_teke.geo.json"));

    public final ResourceLocation resourceLocation;

    HorseBreedModel(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    public static HorseBreedModel breedFromOrdinal(int ordinal) {
        return HorseBreedModel.values()[ordinal % HorseBreedModel.values().length];
    }

}

