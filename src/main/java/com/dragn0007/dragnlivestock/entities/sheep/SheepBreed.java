package com.dragn0007.dragnlivestock.entities.sheep;

public class SheepBreed {

    public enum Breed {
        GULF_COAST,
        MANX_LOAGHTAN,
        NORFOLK,
        DORSET,
        JACOB,
        CALIFORNIA_RED;

        public static Breed breedFromOrdinal(int ordinal) {
            return Breed.values()[ordinal % Breed.values().length];
        }
    }

}
