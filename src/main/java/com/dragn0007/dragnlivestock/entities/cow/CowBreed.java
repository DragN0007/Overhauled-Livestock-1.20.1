package com.dragn0007.dragnlivestock.entities.cow;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public class CowBreed {

    public enum Breed {
        ANUGUS(new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/cow_large.geo.json")),
        LONGHORN(new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/cow_overhaul.geo.json")),
        BRAHMAN(new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/cow_large.geo.json")),
        MINI(new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/cow_mini.geo.json")),
        WATUSI(new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/cow_watusi.geo.json")),
        CORRIENTE(new ResourceLocation(LivestockOverhaul.MODID, "geo/cow/cow_corriente.geo.json"));

        public final ResourceLocation resourceLocation;

        Breed(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Breed breedFromOrdinal(int ordinal) {
            return Breed.values()[ordinal % Breed.values().length];
        }
    }

}
