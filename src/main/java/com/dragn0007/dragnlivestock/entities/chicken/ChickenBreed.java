package com.dragn0007.dragnlivestock.entities.chicken;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public class ChickenBreed {

    public enum Breed {
        LEGHORN(new ResourceLocation(LivestockOverhaul.MODID, "geo/chicken/o_chicken.geo.json")),
        AMERAUCANA(new ResourceLocation(LivestockOverhaul.MODID, "geo/chicken/ameraucana.geo.json")),
        CREAM_LEGBAR(new ResourceLocation(LivestockOverhaul.MODID, "geo/chicken/cream_legbar.geo.json")),
        MARANS(new ResourceLocation(LivestockOverhaul.MODID, "geo/chicken/marans.geo.json")),
        OLIVE_EGGER(new ResourceLocation(LivestockOverhaul.MODID, "geo/chicken/olive_egger.geo.json")),
        SUSSEX_SILKIE(new ResourceLocation(LivestockOverhaul.MODID, "geo/chicken/sussex_silkie.geo.json")),
        AYAM_CEMANI(new ResourceLocation(LivestockOverhaul.MODID, "geo/chicken/ameraucana.geo.json"));

        public final ResourceLocation resourceLocation;

        Breed(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Breed breedFromOrdinal(int ordinal) {
            return Breed.values()[ordinal % Breed.values().length];
        }
    }

}
