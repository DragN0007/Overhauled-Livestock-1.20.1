package com.dragn0007.dragnlivestock.util;

import net.minecraftforge.common.ForgeConfigSpec;

public class LivestockOverhaulCommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue REPLACE_HORSES;
    public static final ForgeConfigSpec.BooleanValue REPLACE_MULES;
    public static final ForgeConfigSpec.BooleanValue REPLACE_DONKEYS;
    public static final ForgeConfigSpec.BooleanValue REPLACE_COWS;
    public static final ForgeConfigSpec.BooleanValue REPLACE_CHICKENS;
    public static final ForgeConfigSpec.BooleanValue REPLACE_SHEEP;
    public static final ForgeConfigSpec.BooleanValue REPLACE_PIGS;
    public static final ForgeConfigSpec.BooleanValue REPLACE_LLAMAS;
    public static final ForgeConfigSpec.BooleanValue REPLACE_RABBITS;
    public static final ForgeConfigSpec.BooleanValue REPLACE_SALMON;
    public static final ForgeConfigSpec.BooleanValue REPLACE_COD;
    public static final ForgeConfigSpec.BooleanValue REPLACE_BEES;
    public static final ForgeConfigSpec.BooleanValue REPLACE_CAMELS;
    public static final ForgeConfigSpec.BooleanValue REPLACE_GOATS;
    public static final ForgeConfigSpec.BooleanValue REPLACE_FROGS;
    public static final ForgeConfigSpec.BooleanValue SPAWN_GRUBS;
    public static final ForgeConfigSpec.BooleanValue GENDERS_AFFECT_BIPRODUCTS;
    public static final ForgeConfigSpec.BooleanValue GENDERS_AFFECT_BREEDING;
    public static final ForgeConfigSpec.BooleanValue ANIMALS_HERDING_ENABLED;
    public static final ForgeConfigSpec.ConfigValue<Integer> SHEEP_HERD_MAX;
    public static final ForgeConfigSpec.ConfigValue<Integer> COW_HERD_MAX;
    public static final ForgeConfigSpec.ConfigValue<Integer> LLAMA_HERD_MAX;
    public static final ForgeConfigSpec.ConfigValue<Integer> HORSE_HERD_MAX;
    public static final ForgeConfigSpec.BooleanValue FAILSAFE_REPLACER;

    static {
        BUILDER.push("Spawning");

        REPLACE_HORSES = BUILDER.comment("Should horses be replaced by O-Horses?")
                .define("Replace Horses", true);

        REPLACE_MULES = BUILDER.comment("Should mules be replaced by O-Mules?")
                .define("Replace Mules", true);

        REPLACE_DONKEYS = BUILDER.comment("Should donkeys be replaced by O-Donkeys?")
                .define("Replace Donkeys", true);

        REPLACE_COWS = BUILDER.comment("Should cows be replaced by O-Cows?")
                .define("Replace Cows", true);

        REPLACE_CHICKENS = BUILDER.comment("Should chickens be replaced by O-Chickens?")
                .define("Replace Chickens", true);

        REPLACE_SHEEP = BUILDER.comment("Should sheep be replaced by O-Sheep?")
                .define("Replace Sheep", true);

        REPLACE_PIGS = BUILDER.comment("Should pigs be replaced by O-Pigs?")
                .define("Replace Pigs", true);

        REPLACE_LLAMAS = BUILDER.comment("Should llamas be replaced by O-Llamas?")
                .define("Replace Llamas", true);

        REPLACE_RABBITS = BUILDER.comment("Should rabbits be replaced by O-Rabbits?")
                .define("Replace Rabbits", true);

        REPLACE_SALMON = BUILDER.comment("Should  be replaced by O-Salmon?")
                .define("Replace Salmon", true);

        REPLACE_COD = BUILDER.comment("Should cod be replaced by O-Cod?")
                .define("Replace Cod", true);

        REPLACE_BEES = BUILDER.comment("Should bees be replaced by O-Bees?")
                .define("Replace Bees", true);

        REPLACE_CAMELS = BUILDER.comment("Should camels be replaced by O-Camels?")
                .define("Replace Camels", true);

        REPLACE_GOATS = BUILDER.comment("Should goats be replaced by O-Goats?")
                .define("Replace Goats", true);

        REPLACE_FROGS = BUILDER.comment("Should frogs be replaced by O-Frogs?")
                .define("Replace Frogs", true);

        SPAWN_GRUBS = BUILDER.comment("Should Grubs spawn?")
                .define("Spawn Grubs", true);
        BUILDER.pop();

        BUILDER.push("Miscellaneous");

        GENDERS_AFFECT_BIPRODUCTS = BUILDER.comment("Should an animal's gender affect the ability to get bi-products from it?")
                .define("Genders Affect Bi-Products", true);

        GENDERS_AFFECT_BREEDING = BUILDER.comment("Should an animal's gender affect how it breeds?")
                .define("Genders Affect Breeding", true);

        ANIMALS_HERDING_ENABLED = BUILDER.comment("Should animals, like cows, herd together?")
                .define("Animals Herd Together", true);

        COW_HERD_MAX = BUILDER.comment("Maximum amount of O-Cows that can herd together. Default is 8.")
                .define("Cow Herd Maximum", 8);

        HORSE_HERD_MAX = BUILDER.comment("Maximum amount of O-Horses that can herd together. Default is 3.")
                .define("Horse Herd Maximum", 3);

        SHEEP_HERD_MAX = BUILDER.comment("Maximum amount of O-Sheep that can herd together. Default is 8.")
                .define("Sheep Herd Maximum", 8);

        LLAMA_HERD_MAX = BUILDER.comment("Maximum amount of O-Llamas that can herd together. Default is 3.")
                .define("Llama Herd Maximum", 3);
        BUILDER.pop();

        BUILDER.push("Uninstalling");
        FAILSAFE_REPLACER =
                BUILDER.comment("Should all O-Animals be converted back into a vanilla counterpart? " +
                                "WARNING: This should be used *sparingly*, and only when needed/ if this mod is about to be removed.")
                        .define("Failsafe O-Animal -> Vanilla Animal Converter", false);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
