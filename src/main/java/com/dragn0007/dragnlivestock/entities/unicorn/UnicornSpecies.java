package com.dragn0007.dragnlivestock.entities.unicorn;

public class UnicornSpecies {

    public enum Breed {
        OVERWORLD,
        NETHER,
        END;

        public static UnicornSpecies.Breed breedFromOrdinal(int ordinal) {
            return UnicornSpecies.Breed.values()[ordinal % UnicornSpecies.Breed.values().length];
        }
    }

}
