package com.dragn0007.dragnlivestock.util;

import net.minecraftforge.common.ForgeConfigSpec;

public class LivestockOverhaulCommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;


    public static final ForgeConfigSpec.BooleanValue REPLACE_SPAWN_EGG_ANIMALS;
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
    public static final ForgeConfigSpec.BooleanValue REPLACE_UNDEAD_HORSES;
    public static final ForgeConfigSpec.BooleanValue SPAWN_MOOBLOOMS;
    public static final ForgeConfigSpec.BooleanValue SPAWN_UNICORNS;
    public static final ForgeConfigSpec.BooleanValue GENDERS_AFFECT_BIPRODUCTS;
    public static final ForgeConfigSpec.BooleanValue GENDERS_AFFECT_BREEDING;
    public static final ForgeConfigSpec.BooleanValue ANIMALS_HERDING_ENABLED;
    public static final ForgeConfigSpec.ConfigValue<Integer> SHEEP_HERD_MAX;
    public static final ForgeConfigSpec.ConfigValue<Integer> COW_HERD_MAX;
    public static final ForgeConfigSpec.ConfigValue<Integer> LLAMA_HERD_MAX;
    public static final ForgeConfigSpec.ConfigValue<Integer> HORSE_HERD_MAX;
    public static final ForgeConfigSpec.BooleanValue USE_VANILLA_LOOT;
    public static final ForgeConfigSpec.BooleanValue NATURAL_HORSE_BREEDS;
    public static final ForgeConfigSpec.ConfigValue<Integer> CHICKEN_EGG_LAY_TIME;
    public static final ForgeConfigSpec.ConfigValue<Integer> CHICKEN_EGG_LAY_AMOUNT;
    public static final ForgeConfigSpec.ConfigValue<Integer> MILKING_COOLDOWN;
    public static final ForgeConfigSpec.ConfigValue<Integer> DAIRY_MILKING_COOLDOWN;
    public static final ForgeConfigSpec.ConfigValue<Integer> SHEEP_WOOL_REGROWTH_TIME;
    public static final ForgeConfigSpec.BooleanValue HORSE_HAIR_GROWTH;
    public static final ForgeConfigSpec.ConfigValue<Integer> HORSE_HAIR_GROWTH_TIME;
    public static final ForgeConfigSpec.BooleanValue SPAWN_BY_BREED;
    public static final ForgeConfigSpec.BooleanValue EYES_BY_COLOR;
    public static final ForgeConfigSpec.BooleanValue OLD_HORSE_TURNING;
    public static final ForgeConfigSpec.BooleanValue UNICORN_BREEDING;
    public static final ForgeConfigSpec.BooleanValue UNDEAD_HORSE_DEATH;
    public static final ForgeConfigSpec.ConfigValue<Integer> DECOMPISITION_STAGE_TIME;
    public static final ForgeConfigSpec.BooleanValue GROUND_TIE;
    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_PIG_BABIES;
    public static final ForgeConfigSpec.ConfigValue<Integer> BASE_HORSE_SPRINT_TIME;
    public static final ForgeConfigSpec.BooleanValue COW_BELL_SOUND;
    public static final ForgeConfigSpec.BooleanValue HORSE_HERD_ANIMALS;
    public static final ForgeConfigSpec.BooleanValue ALLOW_SPECIAL_BLANKET_CRAFTING;
    public static final ForgeConfigSpec.BooleanValue FAILSAFE_REPLACER;

    static {
        BUILDER.push("Spawning");

        BUILDER.comment("Welcome to Livestock Overhaul! You can turn off any O-Animal from spawning in this config, among other things." +
                "\nPlease note that LO *DOES NOT CHANGE SPAWN RATES* because O-Animals simply replace existing vanilla ones. " +
                "If you have an issue with too many O-Animals spawning, check your other mods. LO does not have it's own spawn rates, " +
                "you'll have to change vanilla animal spawn rates to see differences in spawning.");

        REPLACE_SPAWN_EGG_ANIMALS = BUILDER.comment("Should vanilla animals spawned via Spawn Egg be replaced with O-Animals?" +
                        "\nNote that if this is turned on, all previously-spawned vanilla animals will be converted regardless of spawn type.")
                .define("Replace Spawn Egg Vanilla Animals", false);

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

        REPLACE_UNDEAD_HORSES = BUILDER.comment("Should undead horses be replaced by O-Undead Horses?")
                .define("Replace Undead Horses", true);

        SPAWN_MOOBLOOMS = BUILDER.comment("Should Mooblooms have a chance to spawn alongside O-Cows?")
                .define("Spawn Mooblooms", true);

        SPAWN_UNICORNS = BUILDER.comment("Should Unicorns have a chance to spawn in unique situations?")
                .define("Spawn Unicorns", true);

        BUILDER.pop();

        BUILDER.push("Husbandry");
        GENDERS_AFFECT_BIPRODUCTS = BUILDER.comment("Should an animal's gender affect the ability to get byproducts from it?")
                .define("Genders Affect Byproducts", true);

        GENDERS_AFFECT_BREEDING = BUILDER.comment("Should an animal's gender affect how it breeds?")
                .define("Genders Affect Breeding", true);

        CHICKEN_EGG_LAY_TIME = BUILDER.comment("Minimum amount of time, in ticks, that an O-Chicken can lay an unfertilized egg. Default is 12000 ticks, or 10 minutes. Vanilla is 6000 ticks, or 5 minutes.")
                .define("Chicken Egg Lay Cooldown", 12000);

        MILKING_COOLDOWN = BUILDER.comment("Amount of time, in ticks, that you must wait to milk a non-dairy-breed animal. Default is 48000 ticks, or 40 minutes.")
                .define("Average Milking Cooldown", 48000);

        DAIRY_MILKING_COOLDOWN = BUILDER.comment("Amount of time, in ticks, that you must wait to milk a dairy-breed animal. Default is 12000 ticks, or 10 minutes.")
                .define("Dairy Breed Milking Cooldown", 12000);

        SHEEP_WOOL_REGROWTH_TIME = BUILDER.comment("Amount of time, in ticks, that you must wait to shear an O-Sheep, Goat or Llama. Default is 24000 ticks, or 20 minutes.")
                .define("Wool Regrowth Time", 24000);

        CHICKEN_EGG_LAY_AMOUNT = BUILDER.comment("Amount of Fertilized Eggs a hen should lay after mating. Default is 3.")
                .define("Chicken Egg Lay Amount", 3);
        BUILDER.pop();

        BUILDER.push("Herding");
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

        BUILDER.push("QoL");
        USE_VANILLA_LOOT = BUILDER.comment("Should O-Animals use vanilla loot instead of the loot included in the mod?")
                .define("Use Vanilla Animal Loot", false);

        NATURAL_HORSE_BREEDS = BUILDER.comment("Should O-Horses be able to spawn with any breed naturally? (They spawn only as wild Mustangs by default)")
                .define("Naturally Spawning O-Horse Breeds", false);

        BASE_HORSE_SPRINT_TIME = BUILDER.comment("Base time an O-Horse can run for at full speed, in seconds. Default is 45s.")
                .define("Base Horse Sprint Time", 45);

        HORSE_HAIR_GROWTH = BUILDER.comment("Should O-Horses's manes and tails be able to grow over time?")
                .define("Horse Hair Growth Progression", true);

        HORSE_HAIR_GROWTH_TIME = BUILDER.comment("Amount of time, in ticks, it takes for a horse's mane or tail to grow to the next stage. Default is 72000.")
                .define("Horse Hair Growth Time", 72000);

        SPAWN_BY_BREED = BUILDER.comment("Should O-Animals' colors, markings, and other genetic attributes depend on their breed and gender, along with their breed depending on the biome?")
                .define("Spawn By Breed & Biome", true);

        EYES_BY_COLOR = BUILDER.comment("Should O-Animals' eye colors, if applicable, depend on their coat/ coloring?")
                .define("Eye Colors By Coat Color/ Markings", true);

        OLD_HORSE_TURNING = BUILDER.comment("Should O-Horses and other mounts turn their heads similarly to old 1.12 horses when ridden?" +
                        "\nTurning this off will make mounts use the post-1.12 turning, which is simply just pivoting in place.")
                .define("Advanced Horse Turning", true);

        COW_BELL_SOUND = BUILDER.comment("Should cow bells make noise/ ding?")
                .define("Cow Bell Ding", true);

        HORSE_HERD_ANIMALS = BUILDER.comment("Should O-Cows, O-Sheep, and wild O-Horses herd/ run away from mounted horses?")
                .define("Horses Scare Herd Animals", true);

        GROUND_TIE = BUILDER.comment("Should O-Mounts \"ground tie\", or stop moving around, when saddled & dismounted?")
                .define("Ground Tie When Dismounted", true);
        BUILDER.pop();

        BUILDER.push("Miscellaneous");

        UNICORN_BREEDING = BUILDER.comment("Should Unicorns be able to breed?")
                .define("Allow Unicorn Breeding", false);

        UNDEAD_HORSE_DEATH = BUILDER.comment("Should O-Horses have a small chance to turn Undead upon death?")
                .define("Occasional Horse Turns Undead", true);

        DECOMPISITION_STAGE_TIME = BUILDER.comment("Amount of time, in ticks, it takes for an Undead Horse to decompose to the next stage. Default is 72000.")
                .define("Undead Decomp Stage Time", 72000);

        ALLOW_SPECIAL_BLANKET_CRAFTING = BUILDER.comment("Should players be able to craft special event blankets? If this is false, servers can give out blankets as free event rewards instead (cannot be a real currency payment).")
                .define("Allow Special Event Blanket Crafting", true);

        MAX_PIG_BABIES = BUILDER.comment("NON FUNCTIONAL")
                .define("NON FUNCTIONAL", 3);
        BUILDER.pop();

        BUILDER.push("Uninstalling");
        FAILSAFE_REPLACER =
                BUILDER.comment("Should all O-Animals be converted back into a vanilla counterpart? " +
                                "\nWARNING: This should be used *sparingly*, and only when needed/ if this mod is about to be removed.")
                        .define("Failsafe O-Animal -> Vanilla Animal Converter", false);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
