package com.dragn0007.dragnlivestock.util;

import net.minecraftforge.common.ForgeConfigSpec;

public class LivestockOverhaulClientConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue SIMPLE_MODELS;
    public static final ForgeConfigSpec.BooleanValue HORSE_COAT_GUI;
    public static final ForgeConfigSpec.BooleanValue HORSE_SPRINT_TIMER;
    public static final ForgeConfigSpec.BooleanValue HORSE_SPRINT_HUD;
    public static final ForgeConfigSpec.BooleanValue RENDER_BRAND_TAGS;
    public static final ForgeConfigSpec.ConfigValue<Integer> SPRINT_X;
    public static final ForgeConfigSpec.ConfigValue<Integer> SPRINT_Y;
    public static final ForgeConfigSpec.BooleanValue ACCESSIBILITY_GENDER_IDENTIFIER;

    static {
        BUILDER.push("Client");

        SIMPLE_MODELS = BUILDER.comment("Should O-Animals run simplified, vanilla-styled models? " +
                        "This option may be good for those on lower-end computers/ those having performance issues with the complex LO models." +
                        "\nThis option significantly \"dumbs down\" LO animal models to allow for better performance. " +
                        "For this reason, animal markings, visual cosmetics, and breed dimorphism cannot render on these simpler models. " +
                        "They are almost purely vanilla, only taking on genetic base colors. \nRestart your game after changing for best results.")
                .define("Performance Models [Experimental]", false);

        HORSE_COAT_GUI = BUILDER.comment("Should coats, markings, speed, jump strength and health show up on certain O-Mount GUIs?")
                .define("Mount GUI Extras", true);

        HORSE_SPRINT_TIMER = BUILDER.comment("Should the sprint timer render on equines? *Note that this does not disable the mechanic itself, just the visual timer.")
                .define("Visual Horse Sprint Timer", false);

        HORSE_SPRINT_HUD = BUILDER.comment("Should the sprint HUD render on equines? *Note that this does not disable the mechanic itself, just the visual bar.")
                .define("Visual Horse Sprint HUD", true);

        RENDER_BRAND_TAGS = BUILDER.comment("Should Brand Tags render on animals that have been tagged?")
                .define("Render Brand Tags", true);

        SPRINT_X = BUILDER.comment("X location value for the Sprint HUD. Default is 92.")
                .define("Horse Sprint HUD X Location", 92);

        SPRINT_Y = BUILDER.comment("Y location value for the Sprint HUD. Default is 52.")
                .define("Horse Sprint HUD Y Location", 52);

        ACCESSIBILITY_GENDER_IDENTIFIER = BUILDER.comment("Should text that states the gender of an O-Mount render along with the colored dot?")
                .define("Visual Accessibility Gender Text", false);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
