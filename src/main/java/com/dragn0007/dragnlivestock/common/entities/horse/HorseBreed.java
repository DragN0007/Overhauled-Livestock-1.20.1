package com.dragn0007.dragnlivestock.common.entities.horse;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public enum HorseBreed {
    MUSTANG(LivestockOverhaul.id("geo/horse/o_horse.geo.json")),
    ARDENNES(LivestockOverhaul.id("geo/horse/ardennes.geo.json")),
    KLADRUBER(LivestockOverhaul.id("geo/horse/kladruber.geo.json")),
    FJORD(LivestockOverhaul.id("geo/horse/fjord.geo.json")),
    THOROUGHBRED(LivestockOverhaul.id("geo/horse/thoroughbred.geo.json")),
    FRIESIAN(LivestockOverhaul.id("geo/horse/friesian.geo.json")),
    IRISH_COB(LivestockOverhaul.id("geo/horse/irish_cob.geo.json")),
    AMERICAN_QUARTER(LivestockOverhaul.id("geo/horse/american_quarter.geo.json")),
    PERCHERON(LivestockOverhaul.id("geo/horse/percheron.geo.json")),
    SELLE_FRANCAIS(LivestockOverhaul.id("geo/horse/selle_francais.geo.json")),
    MARWARI(LivestockOverhaul.id("geo/horse/marwari.geo.json")),
    MONGOLIAN(LivestockOverhaul.id("geo/horse/mongolian.geo.json")),
    SHIRE(LivestockOverhaul.id("geo/horse/shire.geo.json")),
    AKHAL_TEKE(LivestockOverhaul.id("geo/horse/akhal_teke.geo.json")),
    AMERICAN_SOLDIER(LivestockOverhaul.id("geo/horse/american_soldier.geo.json"));

    public final ResourceLocation resourceLocation;

    HorseBreed(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    public static HorseBreed breedFromOrdinal(int ordinal) {
        return HorseBreed.values()[ordinal % HorseBreed.values().length];
    }

    public HorseBreed next() {
        return HorseBreed.values()[(this.ordinal() + 1) % HorseBreed.values().length];
    }

}

