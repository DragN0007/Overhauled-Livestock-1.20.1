package com.dragn0007.dragnlivestock.util;

import net.minecraftforge.common.ForgeConfigSpec;

public class LivestockOverhaulClientConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue MINIMAL_HORSE_ARMOR;
    public static final ForgeConfigSpec.BooleanValue HORSE_COAT_GUI;

    static {
        BUILDER.push("Client");

        MINIMAL_HORSE_ARMOR = BUILDER.comment("Should horse armor be 'lighter' visually? This doesn't change the protection the armor gives.")
                .define("Minimal Horse Armor", false);

        HORSE_COAT_GUI = BUILDER.comment("Should coats, markings, speed, jump strength and health show up in some O-Mount GUIs?")
                .define("Mount GUI Extras", true);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
