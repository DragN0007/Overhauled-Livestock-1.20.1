package com.dragn0007.dragnlivestock.entities.sheep;

public class SheepBreed {

    public enum Breed {
        GULF_COAST,
        NORFOLK,
        DORSET,
        JACOB,
        RACKA,
        CALIFORNIA_RED,
        HAIR,
        BUNNY;

        public static Breed breedFromOrdinal(int ordinal) {
            return Breed.values()[ordinal % Breed.values().length];
        }

        public SheepBreed.Breed next() {
            return SheepBreed.Breed.values()[(this.ordinal() + 1) % SheepBreed.Breed.values().length];
        }
    }

}
