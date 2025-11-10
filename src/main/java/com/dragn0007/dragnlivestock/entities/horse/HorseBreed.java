package com.dragn0007.dragnlivestock.entities.horse;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public enum HorseBreed {
    MUSTANG(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/o_horse.geo.json")),
    ARDENNES(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/ardennes.geo.json")),
    KLADRUBER(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/kladruber.geo.json")),
    FJORD(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/fjord.geo.json")),
    THOROUGHBRED(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/thoroughbred.geo.json")),
    FRIESIAN(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/friesian.geo.json")),
    IRISH_COB(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/irish_cob.geo.json")),
    AMERICAN_QUARTER(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/american_quarter.geo.json")),
    PERCHERON(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/percheron.geo.json")),
    SELLE_FRANCAIS(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/selle_francais.geo.json")),
    MARWARI(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/marwari.geo.json")),
    MONGOLIAN(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/mongolian.geo.json")),
    SHIRE(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/shire.geo.json")),
    AKHAL_TEKE(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/akhal_teke.geo.json")),
    AMERICAN_SOLDIER(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/american_soldier.geo.json")),
    WELSH(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/welsh.geo.json")),
    CONNEMARA(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/connemara.geo.json")),
    HAFLINGER(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/haflinger.geo.json")),
    OLDENBURGER(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/oldenburger.geo.json")),
    SHETLAND(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/shetland.geo.json")),
    STANDARDBRED(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/standardbred.geo.json")),
    TRAKEHNER(new ResourceLocation(LivestockOverhaul.MODID, "geo/horse/trakehner.geo.json")),
    ;

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

