package com.dragn0007.dragnlivestock.entities.unicorn;

import com.dragn0007.dragnlivestock.entities.horse.HorseBreed;

public enum UnicornSpecies {
    OVERWORLD,
    NETHER,
    END;

    public static UnicornSpecies breedFromOrdinal(int ordinal) {
        return UnicornSpecies.values()[ordinal % UnicornSpecies.values().length];
    }

    public UnicornSpecies next() {
        return UnicornSpecies.values()[(this.ordinal() + 1) % UnicornSpecies.values().length];
    }

}
