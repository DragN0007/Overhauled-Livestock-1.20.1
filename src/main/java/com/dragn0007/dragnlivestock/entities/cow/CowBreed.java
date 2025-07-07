package com.dragn0007.dragnlivestock.entities.cow;

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
        HEREFORD,
        HIGHLAND,
        OX;

        public static CowBreed.Breed breedFromOrdinal(int ordinal) {
            return CowBreed.Breed.values()[ordinal % CowBreed.Breed.values().length];
        }
    }

}
