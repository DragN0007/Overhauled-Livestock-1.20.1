package com.dragn0007.dragnlivestock.entities.farm_goat;

public class GoatBreed {

    public enum Breed {
        CLASSIC,
        MEAT,
        NUBIAN,
        WARM,
        WOOLLY,
        DAIRY;

        public static GoatBreed.Breed breedFromOrdinal(int ordinal) {
            return GoatBreed.Breed.values()[ordinal % GoatBreed.Breed.values().length];
        }

        public GoatBreed.Breed next() {
            return GoatBreed.Breed.values()[(this.ordinal() + 1) % GoatBreed.Breed.values().length];
        }
    }

}
