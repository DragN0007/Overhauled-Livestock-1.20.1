package com.dragn0007.dragnlivestock.util;

import net.minecraftforge.common.ForgeConfigSpec;

public class LivestockOverhaulClientConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Double> CULL_CUBES_DISTANCE;
    public static final ForgeConfigSpec.ConfigValue<Double> CULL_LAYERS_DISTANCE;
    public static final ForgeConfigSpec.BooleanValue CULL_HIDDEN;
    public static final ForgeConfigSpec.BooleanValue DISABLE_MARKINGS;
    public static final ForgeConfigSpec.BooleanValue DISABLE_TACK;
    public static final ForgeConfigSpec.BooleanValue HORSE_COAT_GUI;
    public static final ForgeConfigSpec.BooleanValue HORSE_SPRINT_TIMER;
    public static final ForgeConfigSpec.BooleanValue HORSE_SPRINT_HUD;
    public static final ForgeConfigSpec.ConfigValue<Integer> SPRINT_X;
    public static final ForgeConfigSpec.ConfigValue<Integer> SPRINT_Y;
    public static final ForgeConfigSpec.BooleanValue ACCESSIBILITY_GENDER_IDENTIFIER;
    public static final ForgeConfigSpec.BooleanValue RENDER_BRAND_TAGS;

    static {
        BUILDER.push("Performance");
        BUILDER.comment("Looking for the Simple/ Performance Models Config? This config was removed in 4.0, and is now replaced by an official resource pack. You can find it under the name \"Livestock Overhaul: Performance Pack\" by DragN0007.");
        CULL_CUBES_DISTANCE = BUILDER.comment("The distance at which unnecessary bones are un-rendered from O-Animals to improve performance.")
                .define("Unnecessary Bone Cull Distance", 1024.0);
        CULL_LAYERS_DISTANCE = BUILDER.comment("The distance at which unnecessary layers are un-rendered from O-Animals to improve performance.")
                .define("Unnecessary Layer Cull Distance", 2048.0);
        CULL_HIDDEN = BUILDER.comment("Should animal rendering/ cubes be hidden when they're behind solid walls/ unseen? Does not affect actual mechanics/ entity loading.")
                .define("Cull Bodies When Hidden", true);
        BUILDER.comment("If you're having a lot of trouble running O-Animals, even after trying the \"Livestock Overhaul: Performance Pack\", you can use these layer disabler configs as a last resort to gain more FPS.");
        DISABLE_MARKINGS = BUILDER.comment("Disable animal marking/ body layers?")
                .define("Disable Marking Layers", false);
        DISABLE_TACK = BUILDER.comment("Disable animal tack, cosmetic & armor layers?")
                .define("Disable Tack & Cosmetic Layers", false);
        BUILDER.pop();

        BUILDER.push("GUI");
        HORSE_COAT_GUI = BUILDER.comment("Should coats, markings, speed, jump strength and health show up on certain O-Mount GUIs?")
                .define("Mount GUI Extras", true);
        HORSE_SPRINT_TIMER = BUILDER.comment("Should the sprint timer render on equines? *Note that this does not disable the mechanic itself, just the visual timer.")
                .define("Visual Horse Sprint Timer", false);
        HORSE_SPRINT_HUD = BUILDER.comment("Should the sprint HUD render on equines? *Note that this does not disable the mechanic itself, just the visual bar.")
                .define("Visual Horse Sprint HUD", true);
        SPRINT_X = BUILDER.comment("X location value for the Sprint HUD. Default is 92.")
                .define("Horse Sprint HUD X Location", 92);
        SPRINT_Y = BUILDER.comment("Y location value for the Sprint HUD. Default is 52.")
                .define("Horse Sprint HUD Y Location", 52);
        ACCESSIBILITY_GENDER_IDENTIFIER = BUILDER.comment("Should text that states the gender of an O-Mount render along with the colored dot?")
                .define("Visual Accessibility Gender Text", false);
        BUILDER.pop();

        BUILDER.push("Cosmetic Preferences");
        RENDER_BRAND_TAGS = BUILDER.comment("Should Brand Tags render on animals that have been tagged?")
                .define("Render Brand Tags", true);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
