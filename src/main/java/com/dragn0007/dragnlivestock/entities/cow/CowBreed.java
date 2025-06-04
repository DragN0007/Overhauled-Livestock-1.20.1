package com.dragn0007.dragnlivestock.entities.cow;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public class CowBreed {

    public enum Breed {
        ANGUS,
        LONGHORN,
        BRAHMAN,
        MINI,
        WATUSI,
        CORRIENTE,
        HOLSTEIN,
        JERSEY,
        HEREFORD;

        public static CowBreed.Breed breedFromOrdinal(int ordinal) {
            return CowBreed.Breed.values()[ordinal % CowBreed.Breed.values().length];
        }
    }

}
