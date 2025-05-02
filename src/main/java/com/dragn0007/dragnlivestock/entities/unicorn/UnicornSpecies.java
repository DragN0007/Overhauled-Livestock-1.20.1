package com.dragn0007.dragnlivestock.entities.unicorn;

public class UnicornSpecies {

    public enum Breed {
        OVERWORLD,
        NETHER,
        END;

        public static Breed breedFromOrdinal(int ordinal) {
            return Breed.values()[ordinal % Breed.values().length];
        }
    }

}
