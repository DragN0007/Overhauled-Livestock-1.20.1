package com.dragn0007.dragnlivestock.util;

import net.minecraftforge.common.ForgeConfigSpec;

public class LivestockOverhaulClientConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue HORSE_COAT_GUI;
    public static final ForgeConfigSpec.BooleanValue HORSE_SPRINT_TIMER;
    public static final ForgeConfigSpec.BooleanValue HORSE_SPRINT_HUD;
    public static final ForgeConfigSpec.BooleanValue RENDER_BRAND_TAGS;
    public static final ForgeConfigSpec.BooleanValue CHICKEN_LEG_BAND;
    public static final ForgeConfigSpec.BooleanValue CHICKEN_NECK_TAG;
    public static final ForgeConfigSpec.BooleanValue ACCESSIBILITY_GENDER_IDENTIFIER;

    static {
        BUILDER.push("Client");

        HORSE_COAT_GUI = BUILDER.comment("Should coats, markings, speed, jump strength and health show up on certain O-Mount GUIs?")
                .define("Mount GUI Extras", true);

        HORSE_SPRINT_TIMER = BUILDER.comment("Should the sprint timer render on equines? *Note that this does not disable the mechanic itself, just the visual timer.")
                .define("Visual Horse Sprint Timer", false);

        HORSE_SPRINT_HUD = BUILDER.comment("Should the sprint HUD render on equines? *Note that this does not disable the mechanic itself, just the visual bar.")
                .define("Visual Horse Sprint HUD", true);

        RENDER_BRAND_TAGS = BUILDER.comment("Should Brand Tags render on animals that have been tagged?")
                .define("Render Brand Tags", true);

        CHICKEN_LEG_BAND = BUILDER.comment("Should Leg Bands render on Chickens that have been tagged?")
                .define("Render Chicken Leg Bands", true);

        CHICKEN_NECK_TAG = BUILDER.comment("Should Neck Tags render on Chickens that have been tagged? Might be good for those who have trouble seeing leg bands.")
                .define("Render Chicken Neck Tags", false);

        ACCESSIBILITY_GENDER_IDENTIFIER = BUILDER.comment("Should text that states the gender of an O-Mount render along with the colored dot?")
                .define("Visual Accessibility Gender Text", false);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
