package com.dragn0007.dragnlivestock.util;

import net.minecraftforge.common.ForgeConfigSpec;

public class LivestockOverhaulClientConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue MINIMAL_HORSE_ARMOR;
    public static final ForgeConfigSpec.BooleanValue HORSE_COAT_GUI;
    public static final ForgeConfigSpec.BooleanValue HORSE_SADDLE_EXTRAS;
    public static final ForgeConfigSpec.BooleanValue LEGACY_HORSE_SADDLES;

    static {
        BUILDER.push("Client");

        MINIMAL_HORSE_ARMOR = BUILDER.comment("Should horse armor be 'lighter' visually? This doesn't change the protection the armor gives.")
                .define("Minimal Horse Armor", false);

        HORSE_COAT_GUI = BUILDER.comment("Should coats, markings, speed, jump strength and health show up on certain O-Mount GUIs?")
                .define("Mount GUI Extras", true);

        HORSE_SADDLE_EXTRAS = BUILDER.comment("Should horse saddles include a breast collar & hip straps?")
                .define("Horse Saddle Extras", true);

        LEGACY_HORSE_SADDLES = BUILDER.comment("Should horse saddles take on the old, 'legacy' design? (Pre-2.1)")
                .define("Legacy Horse Saddles", false);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
