package com.dragn0007.dragnlivestock.items;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.blocks.LOBlocks;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.items.custom.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;
import java.util.Map;

public class LOItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, LivestockOverhaul.MODID);

    //Spawn Eggs
    public static final RegistryObject<Item> O_HORSE_SPAWN_EGG = ITEMS.register("o_horse_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_HORSE_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_COW_SPAWN_EGG = ITEMS.register("o_cow_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_COW_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_CHICKEN_SPAWN_EGG = ITEMS.register("o_chicken_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_CHICKEN_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_SALMON_SPAWN_EGG = ITEMS.register("o_salmon_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_SALMON_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_COD_SPAWN_EGG = ITEMS.register("o_cod_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_COD_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_BEE_SPAWN_EGG = ITEMS.register("o_bee_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_BEE_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_RABBIT_SPAWN_EGG = ITEMS.register("o_rabbit_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_RABBIT_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_SHEEP_SPAWN_EGG = ITEMS.register("o_sheep_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_SHEEP_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_LLAMA_SPAWN_EGG = ITEMS.register("o_llama_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_LLAMA_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_PIG_SPAWN_EGG = ITEMS.register("o_pig_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_PIG_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_DONKEY_SPAWN_EGG = ITEMS.register("o_donkey_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_DONKEY_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_MULE_SPAWN_EGG = ITEMS.register("o_mule_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_MULE_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_MOOSHROOM_SPAWN_EGG = ITEMS.register("o_mooshroom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_MOOSHROOM_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_CAMEL_SPAWN_EGG = ITEMS.register("o_camel_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_CAMEL_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_GOAT_SPAWN_EGG = ITEMS.register("o_goat_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_GOAT_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_FROG_SPAWN_EGG = ITEMS.register("o_frog_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_FROG_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> GRUB_SPAWN_EGG = ITEMS.register("grub_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.GRUB_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> FARM_GOAT_SPAWN_EGG = ITEMS.register("farm_goat_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.FARM_GOAT_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CARIBOU_SPAWN_EGG = ITEMS.register("caribou_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.CARIBOU_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> UNICORN_SPAWN_EGG = ITEMS.register("unicorn_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.UNICORN_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> WHEAT_MOOBLOOM_SPAWN_EGG = ITEMS.register("wheat_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.WHEAT_MOOBLOOM_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> SWEET_BERRY_MOOBLOOM_SPAWN_EGG = ITEMS.register("sweet_berry_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.SWEET_BERRY_MOOBLOOM_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> PUMPKIN_MOOBLOOM_SPAWN_EGG = ITEMS.register("pumpkin_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.PUMPKIN_MOOBLOOM_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> POTATO_MOOBLOOM_SPAWN_EGG = ITEMS.register("potato_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.POTATO_MOOBLOOM_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> MELON_MOOBLOOM_SPAWN_EGG = ITEMS.register("melon_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.MELON_MOOBLOOM_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> GLOW_BERRY_MOOBLOOM_SPAWN_EGG = ITEMS.register("glow_berry_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.GLOW_BERRY_MOOBLOOM_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> FLOWERING_MOOBLOOM_SPAWN_EGG = ITEMS.register("flowering_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.FLOWERING_MOOBLOOM_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CARROT_MOOBLOOM_SPAWN_EGG = ITEMS.register("carrot_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.CARROT_MOOBLOOM_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> BEETROOT_MOOBLOOM_SPAWN_EGG = ITEMS.register("beetroot_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.BEETROOT_MOOBLOOM_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> AZALEA_MOOBLOOM_SPAWN_EGG = ITEMS.register("azalea_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.AZALEA_MOOBLOOM_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> PEACH_MOOBLOOM_SPAWN_EGG = ITEMS.register("peach_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.PEACH_MOOBLOOM_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));

    //Misc
    public static final RegistryObject<Item> GENDER_TEST_STRIP = ITEMS.register("gender_test_strip",
            () -> new GenderTestKit(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> MALE_GENDER_TEST_STRIP = ITEMS.register("male_gender_test_strip",
            () -> new MaleGenderTestKit(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> FEMALE_GENDER_TEST_STRIP = ITEMS.register("female_gender_test_strip",
            () -> new FemaleGenderTestKit(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> UTILITY_KNIFE = ITEMS.register("utility_knife", UtilityKnifeItem::new);
    public static final RegistryObject<Item> SPINDLE = ITEMS.register("spindle", SpindleItem::new);
    public static final RegistryObject<Item> MOUNT_KEY = ITEMS.register("mount_key", KeyItem::new);
    public static final RegistryObject<Item> MOUNT_REGISTRY = ITEMS.register("mount_registry", MountRegistryItem::new);
    public static final RegistryObject<Item> COAT_OSCILLATOR = ITEMS.register("coat_oscillator", OscillatorItem::new);
    public static final RegistryObject<Item> MARKING_OSCILLATOR = ITEMS.register("marking_oscillator", OscillatorItem::new);
    public static final RegistryObject<Item> BREED_OSCILLATOR = ITEMS.register("breed_oscillator", OscillatorItem::new);

    public static final RegistryObject<Item> MANE_SCISSORS = ITEMS.register("mane_scissors",
            () -> new HairScissorItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> TAIL_SCISSORS = ITEMS.register("tail_scissors",
            () -> new HairScissorItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> LIGHT_HORSE_ARMOR_SMITHING_TEMPLATE = ITEMS.register("light_horse_armor_smithing_template",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHAINMAIL_HORSE_ARMOR = ITEMS.register("chainmail_horse_armor",
            () -> new HorseArmorItem(4, "chainmail", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> COPPER_HORSE_ARMOR = ITEMS.register("copper_horse_armor",
            () -> new HorseArmorItem(4, "copper", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> QUARTZ_HORSE_ARMOR = ITEMS.register("quartz_horse_armor",
            () -> new HorseArmorItem(10, "quartz", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> EMERALD_HORSE_ARMOR = ITEMS.register("emerald_horse_armor",
            () -> new HorseArmorItem(10, "emerald", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> NETHERITE_HORSE_ARMOR = ITEMS.register("netherite_horse_armor",
            () -> new HorseArmorItem(15, "netherite", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> OBSIDIAN_HORSE_ARMOR = ITEMS.register("obsidian_horse_armor",
            () -> new HorseArmorItem(18, "netherite", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> GRIFFITH_INSPIRED_HORSE_ARMOR = ITEMS.register("griffith_inspired_horse_armor",
            () -> new HorseArmorItem(15, "griffith", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> RIOT_HORSE_ARMOR = ITEMS.register("riot_horse_armor",
            () -> new LightHorseArmorItem(12, "riot", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> MINIMAL_LEATHER_HORSE_ARMOR = ITEMS.register("minimal_leather_horse_armor",
            () -> new LightHorseArmorItem(1, "light_leather", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> MINIMAL_COPPER_HORSE_ARMOR = ITEMS.register("minimal_copper_horse_armor",
            () -> new LightHorseArmorItem(2, "light_copper", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> MINIMAL_IRON_HORSE_ARMOR = ITEMS.register("minimal_iron_horse_armor",
            () -> new LightHorseArmorItem(3, "light_iron", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> MINIMAL_GOLDEN_HORSE_ARMOR = ITEMS.register("minimal_golden_horse_armor",
            () -> new LightHorseArmorItem(5, "light_golden", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> MINIMAL_QUARTZ_HORSE_ARMOR = ITEMS.register("minimal_quartz_horse_armor",
            () -> new LightHorseArmorItem(8, "light_quartz", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> MINIMAL_EMERALD_HORSE_ARMOR = ITEMS.register("minimal_emerald_horse_armor",
            () -> new LightHorseArmorItem(8, "light_emerald", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> MINIMAL_DIAMOND_HORSE_ARMOR = ITEMS.register("minimal_diamond_horse_armor",
            () -> new LightHorseArmorItem(9, "light_diamond", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> MINIMAL_NETHERITE_HORSE_ARMOR = ITEMS.register("minimal_netherite_horse_armor",
            () -> new LightHorseArmorItem(12, "light_netherite", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> MINIMAL_GRIFFITH_INSPIRED_HORSE_ARMOR = ITEMS.register("minimal_griffith_inspired_horse_armor",
            () -> new LightHorseArmorItem(12, "light_griffith", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> MINIMAL_OBSIDIAN_HORSE_ARMOR = ITEMS.register("minimal_obsidian_horse_armor",
            () -> new LightHorseArmorItem(14, "light_obsidian", (new Item.Properties()).stacksTo(1)));

    public static final RegistryObject<Item> BLACK_SADDLE = ITEMS.register("black_saddle",
            () -> new SaddleItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> WHITE_SADDLE = ITEMS.register("white_saddle",
            () -> new SaddleItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> LIGHT_SADDLE = ITEMS.register("light_saddle",
            () -> new SaddleItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BLACK_LIGHT_SADDLE = ITEMS.register("black_light_saddle",
            () -> new SaddleItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> WHITE_LIGHT_SADDLE = ITEMS.register("white_light_saddle",
            () -> new SaddleItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> HEAVY_SADDLE = ITEMS.register("heavy_saddle",
            () -> new SaddleItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BLACK_HEAVY_SADDLE = ITEMS.register("black_heavy_saddle",
            () -> new SaddleItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> WHITE_HEAVY_SADDLE = ITEMS.register("white_heavy_saddle",
            () -> new SaddleItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> RODEO_HARNESS = ITEMS.register("rodeo_harness",
            () -> new HarnessItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> WAGON_HARNESS = ITEMS.register("wagon_harness",
            () -> new HarnessItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BLACK_WAGON_HARNESS = ITEMS.register("black_wagon_harness",
            () -> new HarnessItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> FERTILIZED_EGG = ITEMS.register("fertilized_egg",
            () -> new FertilizedEggItem((new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> FERTILIZED_AMERAUCANA_EGG = ITEMS.register("fertilized_ameraucana_egg",
            () -> new FertilizedEggItem((new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> FERTILIZED_CREAM_LEGBAR_EGG = ITEMS.register("fertilized_cream_legbar_egg",
            () -> new FertilizedEggItem((new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> FERTILIZED_MARANS_EGG = ITEMS.register("fertilized_marans_egg",
            () -> new FertilizedEggItem((new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> FERTILIZED_OLIVE_EGGER_EGG = ITEMS.register("fertilized_olive_egger_egg",
            () -> new FertilizedEggItem((new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> FERTILIZED_SUSSEX_SILKIE_EGG = ITEMS.register("fertilized_sussex_silkie_egg",
            () -> new FertilizedEggItem((new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> FERTILIZED_AYAM_CEMANI_EGG = ITEMS.register("fertilized_ayam_cemani_egg",
            () -> new FertilizedEggItem((new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> FERTILIZED_ORPINGTON_EGG = ITEMS.register("fertilized_orpington_egg",
            () -> new FertilizedEggItem((new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> FERTILIZED_POLISH_EGG = ITEMS.register("fertilized_polish_egg",
            () -> new FertilizedEggItem((new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> FERTILIZED_WYANDOTTE_EGG = ITEMS.register("fertilized_wyandotte_egg",
            () -> new FertilizedEggItem((new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> FERTILIZED_BRAHMA_EGG = ITEMS.register("fertilized_brahma_egg",
            () -> new FertilizedEggItem((new Item.Properties()).stacksTo(1)));

    public static final RegistryObject<Item> EGG = ITEMS.register("egg",
             () -> new Item((new Item.Properties()).stacksTo(64)));
    public static final RegistryObject<Item> AMERAUCANA_EGG = ITEMS.register("ameraucana_egg",
             () -> new Item((new Item.Properties()).stacksTo(64)));
    public static final RegistryObject<Item> CREAM_LEGBAR_EGG = ITEMS.register("cream_legbar_egg",
             () -> new Item((new Item.Properties()).stacksTo(64)));
    public static final RegistryObject<Item> MARANS_EGG = ITEMS.register("marans_egg",
             () -> new Item((new Item.Properties()).stacksTo(64)));
    public static final RegistryObject<Item> OLIVE_EGGER_EGG = ITEMS.register("olive_egger_egg",
             () -> new Item((new Item.Properties()).stacksTo(64)));
    public static final RegistryObject<Item> SUSSEX_SILKIE_EGG = ITEMS.register("sussex_silkie_egg",
             () -> new Item((new Item.Properties()).stacksTo(64)));
    public static final RegistryObject<Item> AYAM_CEMANI_EGG = ITEMS.register("ayam_cemani_egg",
            () -> new Item((new Item.Properties()).stacksTo(64)));
    public static final RegistryObject<Item> ORPINGTON_EGG = ITEMS.register("orpington_egg",
            () -> new Item((new Item.Properties()).stacksTo(64)));
    public static final RegistryObject<Item> POLISH_EGG = ITEMS.register("polish_egg",
            () -> new Item((new Item.Properties()).stacksTo(64)));
    public static final RegistryObject<Item> WYANDOTTE_EGG = ITEMS.register("wyandotte_egg",
            () -> new Item((new Item.Properties()).stacksTo(64)));
    public static final RegistryObject<Item> BRAHMA_EGG = ITEMS.register("brahma_egg",
            () -> new Item((new Item.Properties()).stacksTo(64)));

    public static final RegistryObject<Item> RABBIT_POOP = ITEMS.register("rabbit_poop",
            () -> new RabbitPoopItem((new Item.Properties())));


    //Food/ Items
    public static final RegistryObject<Item> SHEEP_MILK_BUCKET = ITEMS.register("sheep_milk_bucket",
         () -> new MilkBucketItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(1).build()).craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LLAMA_MILK_BUCKET = ITEMS.register("llama_milk_bucket",
            () -> new MilkBucketItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(1).build()).craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> GOAT_MILK_BUCKET = ITEMS.register("goat_milk_bucket",
            () -> new MilkBucketItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(1).build()).craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> COW_MILK_JUG = ITEMS.register("cow_milk_jug",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SHEEP_MILK_JUG = ITEMS.register("sheep_milk_jug",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LLAMA_MILK_JUG = ITEMS.register("llama_milk_jug",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOAT_MILK_JUG = ITEMS.register("goat_milk_jug",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_CHEESE = ITEMS.register("raw_cheese",
            () -> new ItemNameBlockItem(LOBlocks.RAW_CHEESE.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(4).build())));
    public static final RegistryObject<Item> RAW_SHEEP_CHEESE = ITEMS.register("raw_sheep_cheese",
            () -> new ItemNameBlockItem(LOBlocks.RAW_SHEEP_CHEESE.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(4).build())));
    public static final RegistryObject<Item> RAW_LLAMA_CHEESE = ITEMS.register("raw_llama_cheese",
            () -> new ItemNameBlockItem(LOBlocks.RAW_LLAMA_CHEESE.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(4).build())));
    public static final RegistryObject<Item> RAW_GOAT_CHEESE = ITEMS.register("raw_goat_cheese",
            () -> new ItemNameBlockItem(LOBlocks.RAW_GOAT_CHEESE.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(4).build())));

    public static final RegistryObject<Item> CHEESE = ITEMS.register("cheese",
            () -> new ItemNameBlockItem(LOBlocks.CHEESE.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(1).effect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 0), 0.8F).build())));
    public static final RegistryObject<Item> SHEEP_CHEESE = ITEMS.register("sheep_cheese",
            () -> new ItemNameBlockItem(LOBlocks.SHEEP_CHEESE.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(1).effect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 0), 0.8F).build())));
    public static final RegistryObject<Item> LLAMA_CHEESE = ITEMS.register("llama_cheese",
            () -> new ItemNameBlockItem(LOBlocks.LLAMA_CHEESE.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(1).effect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 0), 0.8F).build())));
    public static final RegistryObject<Item> GOAT_CHEESE = ITEMS.register("goat_cheese",
            () -> new ItemNameBlockItem(LOBlocks.GOAT_CHEESE.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(1).effect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 0), 0.8F).build())));

    public static final RegistryObject<Item> EGG_SALAD = ITEMS.register("egg_salad",
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(1).build()).stacksTo(1).craftRemainder(Items.BOWL)));
    public static final RegistryObject<Item> OMELETTE = ITEMS.register("omelette",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(1).build())));
    public static final RegistryObject<Item> CHEESECAKE = ITEMS.register("cheesecake",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(12).saturationMod(1).build())));

    public static final RegistryObject<Item> BEEF_STRIPS = ITEMS.register("beef_strips",
            () -> new ItemNameBlockItem(LOBlocks.RAW_BEEF_JERKY_HANGING.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(2).build())));
    public static final RegistryObject<Item> BEEF_JERKY = ITEMS.register("beef_jerky",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(9).saturationMod(1).build())));
    public static final RegistryObject<Item> CHICKEN_STRIPS = ITEMS.register("chicken_strips",
            () -> new ItemNameBlockItem(LOBlocks.RAW_CHICKEN_JERKY_HANGING.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(2).build())));
    public static final RegistryObject<Item> CHICKEN_JERKY = ITEMS.register("chicken_jerky",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(1).build())));
    public static final RegistryObject<Item> PORK_STRIPS = ITEMS.register("pork_strips",
            () -> new ItemNameBlockItem(LOBlocks.RAW_PORK_JERKY_HANGING.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(2).build())));
    public static final RegistryObject<Item> PORK_JERKY = ITEMS.register("pork_jerky",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(9).saturationMod(1).build())));
    public static final RegistryObject<Item> MUTTON_STRIPS = ITEMS.register("mutton_strips",
            () -> new ItemNameBlockItem(LOBlocks.RAW_MUTTON_JERKY_HANGING.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(2).build())));
    public static final RegistryObject<Item> MUTTON_JERKY = ITEMS.register("mutton_jerky",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(1).build())));
    public static final RegistryObject<Item> FISH_STRIPS = ITEMS.register("fish_strips",
            () -> new ItemNameBlockItem(LOBlocks.RAW_FISH_JERKY_HANGING.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(2).build())));
    public static final RegistryObject<Item> FISH_JERKY = ITEMS.register("fish_jerky",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(1).build())));
    public static final RegistryObject<Item> GAME_STRIPS = ITEMS.register("game_strips",
            () -> new ItemNameBlockItem(LOBlocks.RAW_GAME_JERKY_HANGING.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(2).build())));
    public static final RegistryObject<Item> GAME_JERKY = ITEMS.register("game_jerky",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(9).saturationMod(1).build())));
    public static final RegistryObject<Item> GENERIC_STRIPS = ITEMS.register("generic_strips",
            () -> new ItemNameBlockItem(LOBlocks.RAW_GENERIC_JERKY_HANGING.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(2).build())));
    public static final RegistryObject<Item> GENERIC_JERKY = ITEMS.register("generic_jerky",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(9).saturationMod(1).build())));

    public static final RegistryObject<Item> HORSE = ITEMS.register("horse",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_HORSE = ITEMS.register("cooked_horse",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(1).build())));
    public static final RegistryObject<Item> LLAMA = ITEMS.register("llama",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_LLAMA = ITEMS.register("cooked_llama",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(1).build())));
    public static final RegistryObject<Item> CAMEL = ITEMS.register("camel",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_CAMEL = ITEMS.register("cooked_camel",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(9).saturationMod(1).build())));
    public static final RegistryObject<Item> CHEVON = ITEMS.register("chevon",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_CHEVON = ITEMS.register("cooked_chevon",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(1).build())));
    public static final RegistryObject<Item> FROG = ITEMS.register("frog",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_FROG = ITEMS.register("cooked_frog",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationMod(1).build())));
    public static final RegistryObject<Item> GRUB = ITEMS.register("grub",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).effect(new MobEffectInstance(MobEffects.CONFUSION, 600, 0), 1F).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_GRUB = ITEMS.register("cooked_grub",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> CARIBOU = ITEMS.register("caribou",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_CARIBOU = ITEMS.register("cooked_caribou",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(1).build())));
    public static final RegistryObject<Item> UNICORN = ITEMS.register("unicorn",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).effect(new MobEffectInstance(MobEffects.REGENERATION, 600, 0), 0.8F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), 0.8F).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_UNICORN = ITEMS.register("cooked_unicorn",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(1).effect(new MobEffectInstance(MobEffects.REGENERATION, 600, 0), 0.8F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), 0.8F).build())));

    public static final RegistryObject<Item> FISH_OIL = ITEMS.register("fish_oil",
            () -> new FishOilItem(
                    new MobEffectInstance(MobEffects.REGENERATION, 500, 0, true, false)
            ));
    public static final RegistryObject<Item> ROE = ITEMS.register("roe",
            () -> new SalmonRoeItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).build())));
    public static final RegistryObject<Item> COD_ROE = ITEMS.register("cod_roe",
            () -> new CodRoeItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).build())));

    public static final RegistryObject<Item> GRAIN_SOUP = ITEMS.register("grain_soup",
            () -> new BowlFoodItem(new Item.Properties().stacksTo(1).food(new FoodProperties.Builder().nutrition(6).build())));
    public static final RegistryObject<Item> PUMPKIN_SOUP = ITEMS.register("pumpkin_soup",
            () -> new BowlFoodItem(new Item.Properties().stacksTo(1).food(new FoodProperties.Builder().nutrition(6).build())));
    public static final RegistryObject<Item> CARROT_SOUP = ITEMS.register("carrot_soup",
            () -> new BowlFoodItem(new Item.Properties().stacksTo(1).food(new FoodProperties.Builder().nutrition(6).build())));
    public static final RegistryObject<Item> POTATO_SOUP = ITEMS.register("potato_soup",
            () -> new BowlFoodItem(new Item.Properties().stacksTo(1).food(new FoodProperties.Builder().nutrition(6).build())));
    public static final RegistryObject<Item> MELON_SOUP = ITEMS.register("melon_soup",
            () -> new BowlFoodItem(new Item.Properties().stacksTo(1).food(new FoodProperties.Builder().nutrition(6).build())));
    public static final RegistryObject<Item> GLOW_BERRY_SOUP = ITEMS.register("glow_berry_soup",
            () -> new BowlFoodItem(new Item.Properties().stacksTo(1).food(new FoodProperties.Builder().nutrition(6).build())));
    public static final RegistryObject<Item> SWEET_BERRY_SOUP = ITEMS.register("sweet_berry_soup",
            () -> new BowlFoodItem(new Item.Properties().stacksTo(1).food(new FoodProperties.Builder().nutrition(6).build())));

    public static final RegistryObject<Item> OVERWORLD_UNICORN_HORN = ITEMS.register("overworld_unicorn_horn",
            () -> new UnicornHornItem(
                    new MobEffectInstance(MobEffects.REGENERATION, 2880, 1, true, false),
                    new MobEffectInstance(MobEffects.LUCK, 2880, 2, true, false),
                    new MobEffectInstance(MobEffects.SLOW_FALLING, 2880, 1, true, false)
            ));

    public static final RegistryObject<Item> NETHER_UNICORN_HORN = ITEMS.register("nether_unicorn_horn",
            () -> new UnicornHornItem(
                    new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2880, 1, true, false),
                    new MobEffectInstance(MobEffects.ABSORPTION, 2880, 2, true, false),
                    new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2880, 1, true, false)
            ));

    public static final RegistryObject<Item> END_UNICORN_HORN = ITEMS.register("end_unicorn_horn",
            () -> new UnicornHornItem(
                    new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 2880, 1, true, false),
                    new MobEffectInstance(MobEffects.NIGHT_VISION, 2880, 1, true, false),
                    new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2880, 2, true, false)
            ));

    public static final RegistryObject<Item> MAGNIFYING_GLASS = ITEMS.register("magnifying_glass",
            () -> new Item(new Item.Properties()));

    //Special Carpets
    public static final RegistryObject<Item> AMERICAN_MEDIEVAL_BLANKET = ITEMS.register("american_medieval_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> AMERICAN_MODERN_BLANKET = ITEMS.register("american_modern_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> AMERICAN_RACING_BLANKET = ITEMS.register("american_racing_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> AMERICAN_WESTERN_BLANKET = ITEMS.register("american_western_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> AUTUMN_MEDIEVAL_BLANKET = ITEMS.register("autumn_medieval_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> AUTUMN_MODERN_BLANKET = ITEMS.register("autumn_modern_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> AUTUMN_RACING_BLANKET = ITEMS.register("autumn_racing_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> AUTUMN_WESTERN_BLANKET = ITEMS.register("autumn_western_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> ELDERBERRY_MEDIEVAL_BLANKET = ITEMS.register("elderberry_medieval_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> ELDERBERRY_MODERN_BLANKET = ITEMS.register("elderberry_modern_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> ELDERBERRY_RACING_BLANKET = ITEMS.register("elderberry_racing_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> ELDERBERRY_WESTERN_BLANKET = ITEMS.register("elderberry_western_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> PEACH_MEDIEVAL_BLANKET = ITEMS.register("peach_medieval_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> PEACH_MODERN_BLANKET = ITEMS.register("peach_modern_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> PEACH_RACING_BLANKET = ITEMS.register("peach_racing_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> PEACH_WESTERN_BLANKET = ITEMS.register("peach_western_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> SPRING_MEDIEVAL_BLANKET = ITEMS.register("spring_medieval_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> SPRING_MODERN_BLANKET = ITEMS.register("spring_modern_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> SPRING_RACING_BLANKET = ITEMS.register("spring_racing_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> SPRING_WESTERN_BLANKET = ITEMS.register("spring_western_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> SUMMER_MEDIEVAL_BLANKET = ITEMS.register("summer_medieval_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> SUMMER_MODERN_BLANKET = ITEMS.register("summer_modern_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> SUMMER_RACING_BLANKET = ITEMS.register("summer_racing_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> SUMMER_WESTERN_BLANKET = ITEMS.register("summer_western_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> WINTER_MEDIEVAL_BLANKET = ITEMS.register("winter_medieval_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> WINTER_MODERN_BLANKET = ITEMS.register("winter_modern_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> WINTER_RACING_BLANKET = ITEMS.register("winter_racing_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> WINTER_WESTERN_BLANKET = ITEMS.register("winter_western_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> PRIDE_MEDIEVAL_BLANKET = ITEMS.register("pride_medieval_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> PRIDE_MODERN_BLANKET = ITEMS.register("pride_modern_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> PRIDE_RACING_BLANKET = ITEMS.register("pride_racing_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> PRIDE_WESTERN_BLANKET = ITEMS.register("pride_western_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> LESBIAN_MEDIEVAL_BLANKET = ITEMS.register("lesbian_medieval_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> LESBIAN_MODERN_BLANKET = ITEMS.register("lesbian_modern_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> LESBIAN_RACING_BLANKET = ITEMS.register("lesbian_racing_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> LESBIAN_WESTERN_BLANKET = ITEMS.register("lesbian_western_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> BI_MEDIEVAL_BLANKET = ITEMS.register("bi_medieval_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> BI_MODERN_BLANKET = ITEMS.register("bi_modern_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> BI_RACING_BLANKET = ITEMS.register("bi_racing_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> BI_WESTERN_BLANKET = ITEMS.register("bi_western_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> NONBINARY_MEDIEVAL_BLANKET = ITEMS.register("nonbinary_medieval_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> NONBINARY_MODERN_BLANKET = ITEMS.register("nonbinary_modern_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> NONBINARY_RACING_BLANKET = ITEMS.register("nonbinary_racing_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> NONBINARY_WESTERN_BLANKET = ITEMS.register("nonbinary_western_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> TRANS_MEDIEVAL_BLANKET = ITEMS.register("trans_medieval_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> TRANS_MODERN_BLANKET = ITEMS.register("trans_modern_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> TRANS_RACING_BLANKET = ITEMS.register("trans_racing_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> TRANS_WESTERN_BLANKET = ITEMS.register("trans_western_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));

    //Dyed Stuff
    public static final Map<DyeColor, RegistryObject<Item>> BRAND_TAGS = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getName() + "_brand_tag";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new BrandTagItem(color, new Item.Properties()));
            BRAND_TAGS.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> WOOL_DYE = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getName() + "_wool_dye";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new WoolDyeItem(color, new Item.Properties()));
            WOOL_DYE.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> WOOL_STAPLES = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getName() + "_wool_staple";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new WoolStapleItem(color, new Item.Properties()));
            WOOL_STAPLES.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> MEDIEVAL_BLANKETS = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getName() + "_medieval_blanket";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new BlanketItem(color, new Item.Properties()));
            MEDIEVAL_BLANKETS.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> MODERN_BLANKETS  = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getName() + "_modern_blanket";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new BlanketItem(color, new Item.Properties()));
            MODERN_BLANKETS.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> RACING_BLANKETS  = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getName() + "_racing_blanket";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new BlanketItem(color, new Item.Properties()));
            RACING_BLANKETS.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> WESTERN_BLANKETS  = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getName() + "_western_blanket";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new BlanketItem(color, new Item.Properties()));
            WESTERN_BLANKETS.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> GRUB_SWEATERS  = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getName() + "_grub_sweater";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new GrubSweaterItem(color, new Item.Properties()));
            GRUB_SWEATERS.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> ACCENTED_BLACK_LIGHT_SADDLES  = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.BLACK)
                continue;
            String name = color.getName() + "_accented_black_light_saddle";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new SaddleItem(new Item.Properties()));
            ACCENTED_BLACK_LIGHT_SADDLES.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> ACCENTED_BLACK_SADDLES  = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.BLACK)
                continue;
            String name = color.getName() + "_accented_black_saddle";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new SaddleItem(new Item.Properties()));
            ACCENTED_BLACK_SADDLES.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> ACCENTED_BLACK_HEAVY_SADDLES  = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.BLACK)
                continue;
            String name = color.getName() + "_accented_black_heavy_saddle";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new SaddleItem(new Item.Properties()));
            ACCENTED_BLACK_HEAVY_SADDLES.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> ACCENTED_LIGHT_SADDLES  = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getName() + "_accented_light_saddle";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new SaddleItem(new Item.Properties()));
            ACCENTED_LIGHT_SADDLES.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> ACCENTED_SADDLES  = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getName() + "_accented_saddle";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new SaddleItem(new Item.Properties()));
            ACCENTED_SADDLES.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> ACCENTED_HEAVY_SADDLES  = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getName() + "_accented_heavy_saddle";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new SaddleItem(new Item.Properties()));
            ACCENTED_HEAVY_SADDLES.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> ACCENTED_WHITE_LIGHT_SADDLES  = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.WHITE)
                continue;
            String name = color.getName() + "_accented_white_light_saddle";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new SaddleItem(new Item.Properties()));
            ACCENTED_WHITE_LIGHT_SADDLES.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> ACCENTED_WHITE_SADDLES  = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.WHITE)
                continue;
            String name = color.getName() + "_accented_white_saddle";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new SaddleItem(new Item.Properties()));
            ACCENTED_WHITE_SADDLES.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> ACCENTED_WHITE_HEAVY_SADDLES  = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.WHITE)
                continue;
            String name = color.getName() + "_accented_white_heavy_saddle";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new SaddleItem(new Item.Properties()));
            ACCENTED_WHITE_HEAVY_SADDLES.put(color, item);
        }
    }

    public static final RegistryObject<Item> HOLIDAY_SADDLE = ITEMS.register("holiday_saddle",
            () -> new SaddleItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> HOLIDAY_LIGHT_SADDLE = ITEMS.register("holiday_light_saddle",
            () -> new SaddleItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> HOLIDAY_HEAVY_SADDLE = ITEMS.register("holiday_heavy_saddle",
            () -> new SaddleItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> HOLIDAY_WAGON_HARNESS = ITEMS.register("holiday_wagon_harness",
            () -> new HarnessItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> RED_NOSE = ITEMS.register("red_nose",
            () -> new CosmeticsItem(new Item.Properties()));
    public static final RegistryObject<Item> RAINBOW_STRING_LIGHTS = ITEMS.register("rainbow_string_lights",
            () -> new CosmeticsItem(new Item.Properties()));
    public static final RegistryObject<Item> BLUE_STRING_LIGHTS = ITEMS.register("blue_string_lights",
            () -> new CosmeticsItem(new Item.Properties()));
    public static final RegistryObject<Item> RED_STRING_LIGHTS = ITEMS.register("red_string_lights",
            () -> new CosmeticsItem(new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_STRING_LIGHTS = ITEMS.register("yellow_string_lights",
            () -> new CosmeticsItem(new Item.Properties()));

    public static final RegistryObject<Item> HALLOW_HEART = ITEMS.register("hallow_heart", HallowHeartItem::new);

    //Icons (UNOBTAINABLE)
    public static final RegistryObject<Item> LIVESTOCK_OVERHAUL = ITEMS.register("livestock_overhaul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LIVESTOCK_OVERHAUL_FOOD = ITEMS.register("livestock_overhaul_food",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COVERED_WAGON = ITEMS.register("covered_wagon",
            () -> new WagonItem(EntityTypes.COVERED_WAGON::get, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> LIVESTOCK_WAGON = ITEMS.register("livestock_wagon",
            () -> new WagonItem(EntityTypes.LIVESTOCK_WAGON::get, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> LUMBER_WAGON = ITEMS.register("lumber_wagon",
            () -> new WagonItem(EntityTypes.LUMBER_WAGON::get, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> GOODS_CART = ITEMS.register("goods_cart",
            () -> new WagonItem(EntityTypes.GOODS_CART::get, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> DOG_SLED = ITEMS.register("dog_sled",
            () -> new WagonItem(EntityTypes.DOG_SLED::get, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MINING_CART = ITEMS.register("mining_cart",
            () -> new WagonItem(EntityTypes.MINING_CART::get, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> TRANSPORT_CART = ITEMS.register("transport_cart",
            () -> new WagonItem(EntityTypes.TRANSPORT_CART::get, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> PLOW = ITEMS.register("plow",
            () -> new WagonItem(EntityTypes.PLOW::get, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MOWER = ITEMS.register("mower",
            () -> new WagonItem(EntityTypes.MOWER::get, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> COUPE = ITEMS.register("coupe",
            () -> new WagonItem(EntityTypes.COUPE::get, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CABRIOLET = ITEMS.register("cabriolet",
            () -> new WagonItem(EntityTypes.CABRIOLET::get, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SLEIGH = ITEMS.register("sleigh",
            () -> new WagonItem(EntityTypes.SLEIGH::get, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> WAGON_WHEEL_FRAME = ITEMS.register("wagon_wheel_frame",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WAGON_WHEEL = ITEMS.register("wagon_wheel",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WAGON_AXEL = ITEMS.register("wagon_axel",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WAGON_BODY = ITEMS.register("wagon_body",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WAGON_COVER = ITEMS.register("wagon_cover",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}