package com.dragn0007.dragnlivestock.entities.camel;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public class CamelBreed {

    public enum Breed {
        BACTRIAN(new ResourceLocation(LivestockOverhaul.MODID, "geo/camel/o_camel.geo.json")),
        DROMEDARY(new ResourceLocation(LivestockOverhaul.MODID, "geo/camel/dromedary.geo.json"));

        public final ResourceLocation resourceLocation;

        Breed(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Breed breedFromOrdinal(int ordinal) {
            return Breed.values()[ordinal % Breed.values().length];
        }

        public CamelBreed.Breed next() {
            return CamelBreed.Breed.values()[(this.ordinal() + 1) % CamelBreed.Breed.values().length];
        }
    }

}
