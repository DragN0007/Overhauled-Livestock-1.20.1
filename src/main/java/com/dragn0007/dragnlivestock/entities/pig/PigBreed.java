package com.dragn0007.dragnlivestock.entities.pig;

public class PigBreed {
    public enum Breed {
        YORKSHIRE,
        POT_BELLY,
        GUINEA_HOG,
        KUNEKUNE,
        POLAND_CHINA,
        BERKSHIRE;

        public static PigBreed.Breed breedFromOrdinal(int ordinal) {
            return PigBreed.Breed.values()[ordinal % PigBreed.Breed.values().length];
        }

        public PigBreed.Breed next() {
            return PigBreed.Breed.values()[(this.ordinal() + 1) % PigBreed.Breed.values().length];
        }
    }


}
