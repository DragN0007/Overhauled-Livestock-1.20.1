package com.dragn0007.dragnlivestock.common.entities.chicken;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public class ChickenBreed {

    public enum Breed {
        LEGHORN(LivestockOverhaul.id("geo/chicken/o_chicken.geo.json")),
        AMERAUCANA(LivestockOverhaul.id("geo/chicken/ameraucana.geo.json")),
        CREAM_LEGBAR(LivestockOverhaul.id("geo/chicken/cream_legbar.geo.json")),
        MARANS(LivestockOverhaul.id("geo/chicken/marans.geo.json")),
        OLIVE_EGGER(LivestockOverhaul.id("geo/chicken/olive_egger.geo.json")),
        SUSSEX_SILKIE(LivestockOverhaul.id("geo/chicken/sussex_silkie.geo.json")),
        AYAM_CEMANI(LivestockOverhaul.id("geo/chicken/ameraucana.geo.json"));

        public final ResourceLocation resourceLocation;

        Breed(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Breed breedFromOrdinal(int ordinal) {
            return Breed.values()[ordinal % Breed.values().length];
        }
    }

}
