package com.dragn0007.dragnlivestock.entities.pig;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public class PigBreed {

    public enum Breed {
        YORKSHIRE(new ResourceLocation(LivestockOverhaul.MODID, "geo/pig/o_pig.geo.json")),
        POT_BELLY(new ResourceLocation(LivestockOverhaul.MODID, "geo/pig/pot_belly.geo.json")),
        GUINEA_HOG(new ResourceLocation(LivestockOverhaul.MODID, "geo/pig/o_pig.geo.json")),
        KUNEKUNE(new ResourceLocation(LivestockOverhaul.MODID, "geo/pig/kunekune.geo.json")),
        POLAND_CHINA(new ResourceLocation(LivestockOverhaul.MODID, "geo/pig/o_pig.geo.json")),
        BERKSHIRE(new ResourceLocation(LivestockOverhaul.MODID, "geo/pig/o_pig.geo.json"));

        public final ResourceLocation resourceLocation;

        Breed(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Breed breedFromOrdinal(int ordinal) {
            return Breed.values()[ordinal % Breed.values().length];
        }
    }

}
