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

public class LOItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, LivestockOverhaul.MODID);

    //Spawn Eggs
    public static final RegistryObject<Item> O_HORSE_SPAWN_EGG = ITEMS.register("o_horse_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_HORSE_ENTITY, 0x53250e, 0x281003, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_COW_SPAWN_EGG = ITEMS.register("o_cow_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_COW_ENTITY, 0x4f402e, 0xdbdbdb, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_CHICKEN_SPAWN_EGG = ITEMS.register("o_chicken_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_CHICKEN_ENTITY, 0xc8623d, 0x423434, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_SALMON_SPAWN_EGG = ITEMS.register("o_salmon_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_SALMON_ENTITY, 0xab3533, 0x5b511c, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_COD_SPAWN_EGG = ITEMS.register("o_cod_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_COD_ENTITY, 0x92715a, 0xb6966b, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_BEE_SPAWN_EGG = ITEMS.register("o_bee_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_BEE_ENTITY, 0xe4ae3b, 0xe4ae3b, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_RABBIT_SPAWN_EGG = ITEMS.register("o_rabbit_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_RABBIT_ENTITY, 0xa48d73, 0x524839, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_SHEEP_SPAWN_EGG = ITEMS.register("o_sheep_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_SHEEP_ENTITY, 0xc7c7c7, 0xdccbc2, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_LLAMA_SPAWN_EGG = ITEMS.register("o_llama_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_LLAMA_ENTITY, 0xccb37c, 0xfff3d8, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_PIG_SPAWN_EGG = ITEMS.register("o_pig_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_PIG_ENTITY, 0xb29595, 0xd3bbbb, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_DONKEY_SPAWN_EGG = ITEMS.register("o_donkey_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_DONKEY_ENTITY, 0x8b7867, 0x655749, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_MULE_SPAWN_EGG = ITEMS.register("o_mule_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_MULE_ENTITY, 0x502c1a, 0x381f17, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_MOOSHROOM_SPAWN_EGG = ITEMS.register("o_mooshroom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_MOOSHROOM_ENTITY, 0xbf2425, 0xcabcbc, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_CAMEL_SPAWN_EGG = ITEMS.register("o_camel_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_CAMEL_ENTITY, 0xdfb68a, 0xa47d53, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_GOAT_SPAWN_EGG = ITEMS.register("o_goat_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_GOAT_ENTITY, 0xfafafa, 0xeae7de, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_FROG_SPAWN_EGG = ITEMS.register("o_frog_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_FROG_ENTITY, 0x9cc15c, 0xc34f31, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> GRUB_SPAWN_EGG = ITEMS.register("grub_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.GRUB_ENTITY, 0xf0f1c3, 0xc9bb8d, new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> UNICORN_SPAWN_EGG = ITEMS.register("unicorn_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.UNICORN_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> WHEAT_MOOBLOOM_SPAWN_EGG = ITEMS.register("wheat_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.WHEAT_MOOBLOOM_ENTITY, 0xe3c16a, 0xa69553, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> SWEET_BERRY_MOOBLOOM_SPAWN_EGG = ITEMS.register("sweet_berry_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.SWEET_BERRY_MOOBLOOM_ENTITY, 0x295230, 0x691f21, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> PUMPKIN_MOOBLOOM_SPAWN_EGG = ITEMS.register("pumpkin_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.PUMPKIN_MOOBLOOM_ENTITY, 0xe38a1d, 0xa0560b, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> POTATO_MOOBLOOM_SPAWN_EGG = ITEMS.register("potato_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.POTATO_MOOBLOOM_ENTITY, 0xc8973a, 0xe9ba62, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> MELON_MOOBLOOM_SPAWN_EGG = ITEMS.register("melon_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.MELON_MOOBLOOM_ENTITY, 0xc94132, 0xaf160b, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> GLOW_BERRY_MOOBLOOM_SPAWN_EGG = ITEMS.register("glow_berry_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.GLOW_BERRY_MOOBLOOM_ENTITY, 0xeb8931, 0xf7e26b, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> FLOWERING_MOOBLOOM_SPAWN_EGG = ITEMS.register("flowering_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.FLOWERING_MOOBLOOM_ENTITY, 0xdfbbfd, 0x529a2e, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CARROT_MOOBLOOM_SPAWN_EGG = ITEMS.register("carrot_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.CARROT_MOOBLOOM_ENTITY, 0xab6112, 0xe38a1d, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> BEETROOT_MOOBLOOM_SPAWN_EGG = ITEMS.register("beetroot_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.BEETROOT_MOOBLOOM_ENTITY, 0xb6484c, 0x71160d, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> AZALEA_MOOBLOOM_SPAWN_EGG = ITEMS.register("azalea_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.AZALEA_MOOBLOOM_ENTITY, 0x6c8031, 0xd07be3, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> PEACH_MOOBLOOM_SPAWN_EGG = ITEMS.register("peach_moobloom_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.PEACH_MOOBLOOM_ENTITY, 0xffd6c3, 0xffe9dd, new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> FARM_GOAT_SPAWN_EGG = ITEMS.register("farm_goat_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.FARM_GOAT_ENTITY, 0xae6e40, 0x6f3e20, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CARIBOU_SPAWN_EGG = ITEMS.register("caribou_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.CARIBOU_ENTITY, 0x846957, 0xfff7ed, new Item.Properties().stacksTo(64)));


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
    public static final RegistryObject<Item> COAT_OSCILLATOR = ITEMS.register("coat_oscillator", OscillatorItem::new);
    public static final RegistryObject<Item> MARKING_OSCILLATOR = ITEMS.register("marking_oscillator", OscillatorItem::new);
    public static final RegistryObject<Item> BREED_OSCILLATOR = ITEMS.register("breed_oscillator", OscillatorItem::new);

    public static final RegistryObject<Item> MANE_SCISSORS = ITEMS.register("mane_scissors",
            () -> new HairScissorItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> TAIL_SCISSORS = ITEMS.register("tail_scissors",
            () -> new HairScissorItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> NETHERITE_HORSESHOE = ITEMS.register("netherite_horseshoe",
            () -> new HorseShoeItem(7, (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> DIAMOND_HORSESHOE = ITEMS.register("diamond_horseshoe",
            () -> new HorseShoeItem(5, (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> GOLD_HORSESHOE = ITEMS.register("gold_horseshoe",
            () -> new HorseShoeItem(4, (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> IRON_HORSESHOE = ITEMS.register("iron_horseshoe",
            () -> new HorseShoeItem(3, (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> STONE_HORSESHOE = ITEMS.register("stone_horseshoe",
            () -> new HorseShoeItem(2, (new Item.Properties()).stacksTo(1)));


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
            () -> new RodeoHarnessItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> WAGON_HARNESS = ITEMS.register("wagon_harness",
            () -> new WagonHarnessItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MARTINGALE_HARNESS = ITEMS.register("martingale_harness",
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
    public static final RegistryObject<Item> BERRY_GLAZED_PORK_RIB_CHOP = ITEMS.register("berry_glazed_pork_rib_chop",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(12).saturationMod(1).build())));
    public static final RegistryObject<Item> BERRY_GLAZED_MUTTON_RIB = ITEMS.register("berry_glazed_mutton_rib",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(10).saturationMod(1).build())));

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

    public static final RegistryObject<Item> BEEF_RIB_STEAK = ITEMS.register("beef_rib_steak",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(1).build())));
    public static final RegistryObject<Item> BEEF_SIRLOIN_STEAK = ITEMS.register("beef_sirloin_steak",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_BEEF_RIB_STEAK = ITEMS.register("cooked_beef_rib_steak",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(9).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_BEEF_SIRLOIN_STEAK = ITEMS.register("cooked_beef_sirloin_steak",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(9).saturationMod(1).build())));

    public static final RegistryObject<Item> HORSE = ITEMS.register("horse",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> HORSE_RIB_STEAK = ITEMS.register("horse_rib_steak",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> HORSE_SIRLOIN_STEAK = ITEMS.register("horse_sirloin_steak",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_HORSE = ITEMS.register("cooked_horse",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_HORSE_RIB_STEAK = ITEMS.register("cooked_horse_rib_steak",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(10).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_HORSE_SIRLOIN_STEAK = ITEMS.register("cooked_horse_sirloin_steak",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(10).saturationMod(1).build())));

    public static final RegistryObject<Item> LLAMA = ITEMS.register("llama",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> LLAMA_RIB = ITEMS.register("llama_rib",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> LLAMA_LOIN = ITEMS.register("llama_loin",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_LLAMA = ITEMS.register("cooked_llama",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_LLAMA_RIB = ITEMS.register("cooked_llama_rib",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(9).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_LLAMA_LOIN = ITEMS.register("cooked_llama_loin",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(9).saturationMod(1).build())));

    public static final RegistryObject<Item> MUTTON_RIB = ITEMS.register("mutton_rib",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> MUTTON_LOIN = ITEMS.register("mutton_loin",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_MUTTON_RIB = ITEMS.register("cooked_mutton_rib",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_MUTTON_LOIN = ITEMS.register("cooked_mutton_loin",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(1).build())));

    public static final RegistryObject<Item> PORK_RIB_CHOP = ITEMS.register("pork_rib_chop",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> PORK_TENDERLOIN = ITEMS.register("pork_tenderloin",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_PORK_RIB_CHOP = ITEMS.register("cooked_pork_rib_chop",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(10).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_PORK_TENDERLOIN = ITEMS.register("cooked_pork_tenderloin",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(10).saturationMod(1).build())));

    public static final RegistryObject<Item> CAMEL = ITEMS.register("camel",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> CAMEL_RIB = ITEMS.register("camel_rib",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> CAMEL_LOIN = ITEMS.register("camel_loin",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_CAMEL = ITEMS.register("cooked_camel",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(9).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_CAMEL_RIB = ITEMS.register("cooked_camel_rib",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(12).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_CAMEL_LOIN = ITEMS.register("cooked_camel_loin",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(12).saturationMod(1).build())));

    public static final RegistryObject<Item> CHICKEN_THIGH = ITEMS.register("chicken_thigh",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_CHICKEN_THIGH = ITEMS.register("cooked_chicken_thigh",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(1).build())));

    public static final RegistryObject<Item> RABBIT_THIGH = ITEMS.register("rabbit_thigh",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_RABBIT_THIGH = ITEMS.register("cooked_rabbit_thigh",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(1).build())));

    public static final RegistryObject<Item> CHEVON = ITEMS.register("chevon",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(1).build())));
    public static final RegistryObject<Item> CHEVON_RIB = ITEMS.register("chevon_rib",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> CHEVON_LOIN = ITEMS.register("chevon_loin",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_CHEVON = ITEMS.register("cooked_chevon",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_CHEVON_RIB = ITEMS.register("cooked_chevon_rib",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_CHEVON_LOIN = ITEMS.register("cooked_chevon_loin",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(1).build())));

    public static final RegistryObject<Item> FROG = ITEMS.register("frog",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_FROG = ITEMS.register("cooked_frog",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationMod(1).build())));

    public static final RegistryObject<Item> GRUB = ITEMS.register("grub",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).effect(new MobEffectInstance(MobEffects.CONFUSION, 600, 0), 1F).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_GRUB = ITEMS.register("cooked_grub",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));

    public static final RegistryObject<Item> FISH_OIL = ITEMS.register("fish_oil",
            () -> new FishOilItem(
                    new MobEffectInstance(MobEffects.REGENERATION, 500, 0, true, false)
            ));
    public static final RegistryObject<Item> ROE = ITEMS.register("roe",
            () -> new SalmonRoeItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).build())));
    public static final RegistryObject<Item> COD_ROE = ITEMS.register("cod_roe",
            () -> new CodRoeItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).build())));

    public static final RegistryObject<Item> SALMON_FILLET = ITEMS.register("salmon_fillet",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).build())));
    public static final RegistryObject<Item> COD_FILLET = ITEMS.register("cod_fillet",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).build())));
    public static final RegistryObject<Item> COOKED_SALMON_FILLET = ITEMS.register("cooked_salmon_fillet",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).build())));
    public static final RegistryObject<Item> COOKED_COD_FILLET = ITEMS.register("cooked_cod_fillet",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).build())));

    public static final RegistryObject<Item> CARIBOU = ITEMS.register("caribou",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> CARIBOU_RIB_STEAK = ITEMS.register("caribou_rib_steak",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> CARIBOU_SIRLOIN_STEAK = ITEMS.register("caribou_sirloin_steak",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_CARIBOU = ITEMS.register("cooked_caribou",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_CARIBOU_RIB_STEAK = ITEMS.register("cooked_caribou_rib_steak",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(10).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_CARIBOU_SIRLOIN_STEAK = ITEMS.register("cooked_caribou_sirloin_steak",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(10).saturationMod(1).build())));

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

    public static final RegistryObject<Item> UNICORN = ITEMS.register("unicorn",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).effect(new MobEffectInstance(MobEffects.REGENERATION, 600, 0), 0.8F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), 0.8F).saturationMod(1).build())));
    public static final RegistryObject<Item> UNICORN_RIB_STEAK = ITEMS.register("unicorn_rib_steak",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).effect(new MobEffectInstance(MobEffects.REGENERATION, 600, 0), 0.8F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), 0.8F).build())));
    public static final RegistryObject<Item> UNICORN_SIRLOIN_STEAK = ITEMS.register("unicorn_sirloin_steak",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).effect(new MobEffectInstance(MobEffects.REGENERATION, 600, 0), 0.8F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), 0.8F).build())));
    public static final RegistryObject<Item> COOKED_UNICORN = ITEMS.register("cooked_unicorn",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(1).effect(new MobEffectInstance(MobEffects.REGENERATION, 600, 0), 0.8F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), 0.8F).build())));
    public static final RegistryObject<Item> COOKED_UNICORN_RIB_STEAK = ITEMS.register("cooked_unicorn_rib_steak",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(10).saturationMod(1).effect(new MobEffectInstance(MobEffects.REGENERATION, 600, 0), 0.8F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), 0.8F).build())));
    public static final RegistryObject<Item> COOKED_UNICORN_SIRLOIN_STEAK = ITEMS.register("cooked_unicorn_sirloin_steak",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(10).saturationMod(1).effect(new MobEffectInstance(MobEffects.REGENERATION, 600, 0), 0.8F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), 0.8F).build())));

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
    public static final RegistryObject<Item> BLACK_BRAND_TAG = ITEMS.register("black_brand_tag",
            () -> new BrandTagItem(DyeColor.BLACK, new Item.Properties()));
    public static final RegistryObject<Item> BLUE_BRAND_TAG = ITEMS.register("blue_brand_tag",
            () -> new BrandTagItem(DyeColor.BLUE, new Item.Properties()));
    public static final RegistryObject<Item> BROWN_BRAND_TAG = ITEMS.register("brown_brand_tag",
            () -> new BrandTagItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> CYAN_BRAND_TAG = ITEMS.register("cyan_brand_tag",
            () -> new BrandTagItem(DyeColor.CYAN, new Item.Properties()));
    public static final RegistryObject<Item> GREEN_BRAND_TAG = ITEMS.register("green_brand_tag",
            () -> new BrandTagItem(DyeColor.GREEN, new Item.Properties()));
    public static final RegistryObject<Item> GREY_BRAND_TAG = ITEMS.register("grey_brand_tag",
            () -> new BrandTagItem(DyeColor.GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_BRAND_TAG = ITEMS.register("light_blue_brand_tag",
            () -> new BrandTagItem(DyeColor.LIGHT_BLUE, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_BRAND_TAG = ITEMS.register("light_grey_brand_tag",
            () -> new BrandTagItem(DyeColor.LIGHT_GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIME_BRAND_TAG = ITEMS.register("lime_brand_tag",
            () -> new BrandTagItem(DyeColor.LIME, new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_BRAND_TAG = ITEMS.register("magenta_brand_tag",
            () -> new BrandTagItem(DyeColor.MAGENTA, new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_BRAND_TAG = ITEMS.register("orange_brand_tag",
            () -> new BrandTagItem(DyeColor.ORANGE, new Item.Properties()));
    public static final RegistryObject<Item> PINK_BRAND_TAG = ITEMS.register("pink_brand_tag",
            () -> new BrandTagItem(DyeColor.PINK, new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_BRAND_TAG = ITEMS.register("purple_brand_tag",
            () -> new BrandTagItem(DyeColor.PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> RED_BRAND_TAG = ITEMS.register("red_brand_tag",
            () -> new BrandTagItem(DyeColor.RED, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_BRAND_TAG = ITEMS.register("white_brand_tag",
            () -> new BrandTagItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_BRAND_TAG = ITEMS.register("yellow_brand_tag",
            () -> new BrandTagItem(DyeColor.YELLOW, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_WOOL_DYE = ITEMS.register("black_wool_dye",
            () -> new WoolDyeItem(DyeColor.BLACK, new Item.Properties()));
    public static final RegistryObject<Item> BLUE_WOOL_DYE = ITEMS.register("blue_wool_dye",
            () -> new WoolDyeItem(DyeColor.BLUE, new Item.Properties()));
    public static final RegistryObject<Item> BROWN_WOOL_DYE = ITEMS.register("brown_wool_dye",
            () -> new WoolDyeItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> CYAN_WOOL_DYE = ITEMS.register("cyan_wool_dye",
            () -> new WoolDyeItem(DyeColor.CYAN, new Item.Properties()));
    public static final RegistryObject<Item> GREEN_WOOL_DYE = ITEMS.register("green_wool_dye",
            () -> new WoolDyeItem(DyeColor.GREEN, new Item.Properties()));
    public static final RegistryObject<Item> GREY_WOOL_DYE = ITEMS.register("grey_wool_dye",
            () -> new WoolDyeItem(DyeColor.GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_WOOL_DYE = ITEMS.register("light_blue_wool_dye",
            () -> new WoolDyeItem(DyeColor.LIGHT_BLUE, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_WOOL_DYE = ITEMS.register("light_grey_wool_dye",
            () -> new WoolDyeItem(DyeColor.LIGHT_GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIME_WOOL_DYE = ITEMS.register("lime_wool_dye",
            () -> new WoolDyeItem(DyeColor.LIME, new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_WOOL_DYE = ITEMS.register("magenta_wool_dye",
            () -> new WoolDyeItem(DyeColor.MAGENTA, new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_WOOL_DYE = ITEMS.register("orange_wool_dye",
            () -> new WoolDyeItem(DyeColor.ORANGE, new Item.Properties()));
    public static final RegistryObject<Item> PINK_WOOL_DYE = ITEMS.register("pink_wool_dye",
            () -> new WoolDyeItem(DyeColor.PINK, new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_WOOL_DYE = ITEMS.register("purple_wool_dye",
            () -> new WoolDyeItem(DyeColor.PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> RED_WOOL_DYE = ITEMS.register("red_wool_dye",
            () -> new WoolDyeItem(DyeColor.RED, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_WOOL_DYE = ITEMS.register("white_wool_dye",
            () -> new WoolDyeItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_WOOL_DYE = ITEMS.register("yellow_wool_dye",
            () -> new WoolDyeItem(DyeColor.YELLOW, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_WOOL_STAPLE = ITEMS.register("black_wool_staple",
            () -> new WoolStapleItem(DyeColor.BLACK, new Item.Properties()));
    public static final RegistryObject<Item> BLUE_WOOL_STAPLE = ITEMS.register("blue_wool_staple",
            () -> new WoolStapleItem(DyeColor.BLUE, new Item.Properties()));
    public static final RegistryObject<Item> BROWN_WOOL_STAPLE = ITEMS.register("brown_wool_staple",
            () -> new WoolStapleItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> CYAN_WOOL_STAPLE = ITEMS.register("cyan_wool_staple",
            () -> new WoolStapleItem(DyeColor.CYAN, new Item.Properties()));
    public static final RegistryObject<Item> GREEN_WOOL_STAPLE = ITEMS.register("green_wool_staple",
            () -> new WoolStapleItem(DyeColor.GREEN, new Item.Properties()));
    public static final RegistryObject<Item> GREY_WOOL_STAPLE = ITEMS.register("grey_wool_staple",
            () -> new WoolStapleItem(DyeColor.GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_WOOL_STAPLE = ITEMS.register("light_blue_wool_staple",
            () -> new WoolStapleItem(DyeColor.LIGHT_BLUE, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_WOOL_STAPLE = ITEMS.register("light_grey_wool_staple",
            () -> new WoolStapleItem(DyeColor.LIGHT_GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIME_WOOL_STAPLE = ITEMS.register("lime_wool_staple",
            () -> new WoolStapleItem(DyeColor.LIME, new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_WOOL_STAPLE = ITEMS.register("magenta_wool_staple",
            () -> new WoolStapleItem(DyeColor.MAGENTA, new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_WOOL_STAPLE = ITEMS.register("orange_wool_staple",
            () -> new WoolStapleItem(DyeColor.ORANGE, new Item.Properties()));
    public static final RegistryObject<Item> PINK_WOOL_STAPLE = ITEMS.register("pink_wool_staple",
            () -> new WoolStapleItem(DyeColor.PINK, new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_WOOL_STAPLE = ITEMS.register("purple_wool_staple",
            () -> new WoolStapleItem(DyeColor.PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> RED_WOOL_STAPLE = ITEMS.register("red_wool_staple",
            () -> new WoolStapleItem(DyeColor.RED, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_WOOL_STAPLE = ITEMS.register("white_wool_staple",
            () -> new WoolStapleItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_WOOL_STAPLE = ITEMS.register("yellow_wool_staple",
            () -> new WoolStapleItem(DyeColor.YELLOW, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_MEDIEVAL_BLANKET = ITEMS.register("black_medieval_blanket",
            () -> new BlanketItem(DyeColor.BLACK, new Item.Properties()));
    public static final RegistryObject<Item> BLUE_MEDIEVAL_BLANKET = ITEMS.register("blue_medieval_blanket",
            () -> new BlanketItem(DyeColor.BLUE, new Item.Properties()));
    public static final RegistryObject<Item> BROWN_MEDIEVAL_BLANKET = ITEMS.register("brown_medieval_blanket",
            () -> new BlanketItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> CYAN_MEDIEVAL_BLANKET = ITEMS.register("cyan_medieval_blanket",
            () -> new BlanketItem(DyeColor.CYAN, new Item.Properties()));
    public static final RegistryObject<Item> GREEN_MEDIEVAL_BLANKET = ITEMS.register("green_medieval_blanket",
            () -> new BlanketItem(DyeColor.GREEN, new Item.Properties()));
    public static final RegistryObject<Item> GREY_MEDIEVAL_BLANKET = ITEMS.register("grey_medieval_blanket",
            () -> new BlanketItem(DyeColor.GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_MEDIEVAL_BLANKET = ITEMS.register("light_blue_medieval_blanket",
            () -> new BlanketItem(DyeColor.LIGHT_BLUE, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_MEDIEVAL_BLANKET = ITEMS.register("light_grey_medieval_blanket",
            () -> new BlanketItem(DyeColor.LIGHT_GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIME_MEDIEVAL_BLANKET = ITEMS.register("lime_medieval_blanket",
            () -> new BlanketItem(DyeColor.LIME, new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_MEDIEVAL_BLANKET = ITEMS.register("magenta_medieval_blanket",
            () -> new BlanketItem(DyeColor.MAGENTA, new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_MEDIEVAL_BLANKET = ITEMS.register("orange_medieval_blanket",
            () -> new BlanketItem(DyeColor.ORANGE, new Item.Properties()));
    public static final RegistryObject<Item> PINK_MEDIEVAL_BLANKET = ITEMS.register("pink_medieval_blanket",
            () -> new BlanketItem(DyeColor.PINK, new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_MEDIEVAL_BLANKET = ITEMS.register("purple_medieval_blanket",
            () -> new BlanketItem(DyeColor.PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> RED_MEDIEVAL_BLANKET = ITEMS.register("red_medieval_blanket",
            () -> new BlanketItem(DyeColor.RED, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_MEDIEVAL_BLANKET = ITEMS.register("white_medieval_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_MEDIEVAL_BLANKET = ITEMS.register("yellow_medieval_blanket",
            () -> new BlanketItem(DyeColor.YELLOW, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_MODERN_BLANKET = ITEMS.register("black_modern_blanket",
            () -> new BlanketItem(DyeColor.BLACK, new Item.Properties()));
    public static final RegistryObject<Item> BLUE_MODERN_BLANKET = ITEMS.register("blue_modern_blanket",
            () -> new BlanketItem(DyeColor.BLUE, new Item.Properties()));
    public static final RegistryObject<Item> BROWN_MODERN_BLANKET = ITEMS.register("brown_modern_blanket",
            () -> new BlanketItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> CYAN_MODERN_BLANKET = ITEMS.register("cyan_modern_blanket",
            () -> new BlanketItem(DyeColor.CYAN, new Item.Properties()));
    public static final RegistryObject<Item> GREEN_MODERN_BLANKET = ITEMS.register("green_modern_blanket",
            () -> new BlanketItem(DyeColor.GREEN, new Item.Properties()));
    public static final RegistryObject<Item> GREY_MODERN_BLANKET = ITEMS.register("grey_modern_blanket",
            () -> new BlanketItem(DyeColor.GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_MODERN_BLANKET = ITEMS.register("light_blue_modern_blanket",
            () -> new BlanketItem(DyeColor.LIGHT_BLUE, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_MODERN_BLANKET = ITEMS.register("light_grey_modern_blanket",
            () -> new BlanketItem(DyeColor.LIGHT_GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIME_MODERN_BLANKET = ITEMS.register("lime_modern_blanket",
            () -> new BlanketItem(DyeColor.LIME, new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_MODERN_BLANKET = ITEMS.register("magenta_modern_blanket",
            () -> new BlanketItem(DyeColor.MAGENTA, new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_MODERN_BLANKET = ITEMS.register("orange_modern_blanket",
            () -> new BlanketItem(DyeColor.ORANGE, new Item.Properties()));
    public static final RegistryObject<Item> PINK_MODERN_BLANKET = ITEMS.register("pink_modern_blanket",
            () -> new BlanketItem(DyeColor.PINK, new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_MODERN_BLANKET = ITEMS.register("purple_modern_blanket",
            () -> new BlanketItem(DyeColor.PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> RED_MODERN_BLANKET = ITEMS.register("red_modern_blanket",
            () -> new BlanketItem(DyeColor.RED, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_MODERN_BLANKET = ITEMS.register("white_modern_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_MODERN_BLANKET = ITEMS.register("yellow_modern_blanket",
            () -> new BlanketItem(DyeColor.YELLOW, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_RACING_BLANKET = ITEMS.register("black_racing_blanket",
            () -> new BlanketItem(DyeColor.BLACK, new Item.Properties()));
    public static final RegistryObject<Item> BLUE_RACING_BLANKET = ITEMS.register("blue_racing_blanket",
            () -> new BlanketItem(DyeColor.BLUE, new Item.Properties()));
    public static final RegistryObject<Item> BROWN_RACING_BLANKET = ITEMS.register("brown_racing_blanket",
            () -> new BlanketItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> CYAN_RACING_BLANKET = ITEMS.register("cyan_racing_blanket",
            () -> new BlanketItem(DyeColor.CYAN, new Item.Properties()));
    public static final RegistryObject<Item> GREEN_RACING_BLANKET = ITEMS.register("green_racing_blanket",
            () -> new BlanketItem(DyeColor.GREEN, new Item.Properties()));
    public static final RegistryObject<Item> GREY_RACING_BLANKET = ITEMS.register("grey_racing_blanket",
            () -> new BlanketItem(DyeColor.GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_RACING_BLANKET = ITEMS.register("light_blue_racing_blanket",
            () -> new BlanketItem(DyeColor.LIGHT_BLUE, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_RACING_BLANKET = ITEMS.register("light_grey_racing_blanket",
            () -> new BlanketItem(DyeColor.LIGHT_GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIME_RACING_BLANKET = ITEMS.register("lime_racing_blanket",
            () -> new BlanketItem(DyeColor.LIME, new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_RACING_BLANKET = ITEMS.register("magenta_racing_blanket",
            () -> new BlanketItem(DyeColor.MAGENTA, new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_RACING_BLANKET = ITEMS.register("orange_racing_blanket",
            () -> new BlanketItem(DyeColor.ORANGE, new Item.Properties()));
    public static final RegistryObject<Item> PINK_RACING_BLANKET = ITEMS.register("pink_racing_blanket",
            () -> new BlanketItem(DyeColor.PINK, new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_RACING_BLANKET = ITEMS.register("purple_racing_blanket",
            () -> new BlanketItem(DyeColor.PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> RED_RACING_BLANKET = ITEMS.register("red_racing_blanket",
            () -> new BlanketItem(DyeColor.RED, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_RACING_BLANKET = ITEMS.register("white_racing_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_RACING_BLANKET = ITEMS.register("yellow_racing_blanket",
            () -> new BlanketItem(DyeColor.YELLOW, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_WESTERN_BLANKET = ITEMS.register("black_western_blanket",
            () -> new BlanketItem(DyeColor.BLACK, new Item.Properties()));
    public static final RegistryObject<Item> BLUE_WESTERN_BLANKET = ITEMS.register("blue_western_blanket",
            () -> new BlanketItem(DyeColor.BLUE, new Item.Properties()));
    public static final RegistryObject<Item> BROWN_WESTERN_BLANKET = ITEMS.register("brown_western_blanket",
            () -> new BlanketItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> CYAN_WESTERN_BLANKET = ITEMS.register("cyan_western_blanket",
            () -> new BlanketItem(DyeColor.CYAN, new Item.Properties()));
    public static final RegistryObject<Item> GREEN_WESTERN_BLANKET = ITEMS.register("green_western_blanket",
            () -> new BlanketItem(DyeColor.GREEN, new Item.Properties()));
    public static final RegistryObject<Item> GREY_WESTERN_BLANKET = ITEMS.register("grey_western_blanket",
            () -> new BlanketItem(DyeColor.GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_WESTERN_BLANKET = ITEMS.register("light_blue_western_blanket",
            () -> new BlanketItem(DyeColor.LIGHT_BLUE, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_WESTERN_BLANKET = ITEMS.register("light_grey_western_blanket",
            () -> new BlanketItem(DyeColor.LIGHT_GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIME_WESTERN_BLANKET = ITEMS.register("lime_western_blanket",
            () -> new BlanketItem(DyeColor.LIME, new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_WESTERN_BLANKET = ITEMS.register("magenta_western_blanket",
            () -> new BlanketItem(DyeColor.MAGENTA, new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_WESTERN_BLANKET = ITEMS.register("orange_western_blanket",
            () -> new BlanketItem(DyeColor.ORANGE, new Item.Properties()));
    public static final RegistryObject<Item> PINK_WESTERN_BLANKET = ITEMS.register("pink_western_blanket",
            () -> new BlanketItem(DyeColor.PINK, new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_WESTERN_BLANKET = ITEMS.register("purple_western_blanket",
            () -> new BlanketItem(DyeColor.PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> RED_WESTERN_BLANKET = ITEMS.register("red_western_blanket",
            () -> new BlanketItem(DyeColor.RED, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_WESTERN_BLANKET = ITEMS.register("white_western_blanket",
            () -> new BlanketItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_WESTERN_BLANKET = ITEMS.register("yellow_western_blanket",
            () -> new BlanketItem(DyeColor.YELLOW, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_GRUB_SWEATER = ITEMS.register("black_grub_sweater",
            () -> new GrubSweaterItem(DyeColor.BLACK, new Item.Properties()));
    public static final RegistryObject<Item> BLUE_GRUB_SWEATER = ITEMS.register("blue_grub_sweater",
            () -> new GrubSweaterItem(DyeColor.BLUE, new Item.Properties()));
    public static final RegistryObject<Item> BROWN_GRUB_SWEATER = ITEMS.register("brown_grub_sweater",
            () -> new GrubSweaterItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> CYAN_GRUB_SWEATER = ITEMS.register("cyan_grub_sweater",
            () -> new GrubSweaterItem(DyeColor.CYAN, new Item.Properties()));
    public static final RegistryObject<Item> GREEN_GRUB_SWEATER = ITEMS.register("green_grub_sweater",
            () -> new GrubSweaterItem(DyeColor.GREEN, new Item.Properties()));
    public static final RegistryObject<Item> GREY_GRUB_SWEATER = ITEMS.register("grey_grub_sweater",
            () -> new GrubSweaterItem(DyeColor.GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_GRUB_SWEATER = ITEMS.register("light_blue_grub_sweater",
            () -> new GrubSweaterItem(DyeColor.LIGHT_BLUE, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_GRUB_SWEATER = ITEMS.register("light_grey_grub_sweater",
            () -> new GrubSweaterItem(DyeColor.LIGHT_GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIME_GRUB_SWEATER = ITEMS.register("lime_grub_sweater",
            () -> new GrubSweaterItem(DyeColor.LIME, new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_GRUB_SWEATER = ITEMS.register("magenta_grub_sweater",
            () -> new GrubSweaterItem(DyeColor.MAGENTA, new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_GRUB_SWEATER = ITEMS.register("orange_grub_sweater",
            () -> new GrubSweaterItem(DyeColor.ORANGE, new Item.Properties()));
    public static final RegistryObject<Item> PINK_GRUB_SWEATER = ITEMS.register("pink_grub_sweater",
            () -> new GrubSweaterItem(DyeColor.PINK, new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_GRUB_SWEATER = ITEMS.register("purple_grub_sweater",
            () -> new GrubSweaterItem(DyeColor.PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> RED_GRUB_SWEATER = ITEMS.register("red_grub_sweater",
            () -> new GrubSweaterItem(DyeColor.RED, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_GRUB_SWEATER = ITEMS.register("white_grub_sweater",
            () -> new GrubSweaterItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_GRUB_SWEATER = ITEMS.register("yellow_grub_sweater",
            () -> new GrubSweaterItem(DyeColor.YELLOW, new Item.Properties()));

    public static final RegistryObject<Item> BLUE_ACCENTED_BLACK_LIGHT_SADDLE = ITEMS.register("blue_accented_black_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> BROWN_ACCENTED_BLACK_LIGHT_SADDLE = ITEMS.register("brown_accented_black_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> CYAN_ACCENTED_BLACK_LIGHT_SADDLE = ITEMS.register("cyan_accented_black_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> GREEN_ACCENTED_BLACK_LIGHT_SADDLE = ITEMS.register("green_accented_black_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> GREY_ACCENTED_BLACK_LIGHT_SADDLE = ITEMS.register("grey_accented_black_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_ACCENTED_BLACK_LIGHT_SADDLE = ITEMS.register("light_blue_accented_black_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_ACCENTED_BLACK_LIGHT_SADDLE = ITEMS.register("light_grey_accented_black_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIME_ACCENTED_BLACK_LIGHT_SADDLE = ITEMS.register("lime_accented_black_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_ACCENTED_BLACK_LIGHT_SADDLE = ITEMS.register("magenta_accented_black_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_ACCENTED_BLACK_LIGHT_SADDLE = ITEMS.register("orange_accented_black_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> PINK_ACCENTED_BLACK_LIGHT_SADDLE = ITEMS.register("pink_accented_black_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_ACCENTED_BLACK_LIGHT_SADDLE = ITEMS.register("purple_accented_black_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> RED_ACCENTED_BLACK_LIGHT_SADDLE = ITEMS.register("red_accented_black_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> WHITE_ACCENTED_BLACK_LIGHT_SADDLE = ITEMS.register("white_accented_black_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_ACCENTED_BLACK_LIGHT_SADDLE = ITEMS.register("yellow_accented_black_light_saddle",
            () -> new SaddleItem(new Item.Properties()));

    public static final RegistryObject<Item> BLUE_ACCENTED_BLACK_SADDLE = ITEMS.register("blue_accented_black_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> BROWN_ACCENTED_BLACK_SADDLE = ITEMS.register("brown_accented_black_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> CYAN_ACCENTED_BLACK_SADDLE = ITEMS.register("cyan_accented_black_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> GREEN_ACCENTED_BLACK_SADDLE = ITEMS.register("green_accented_black_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> GREY_ACCENTED_BLACK_SADDLE = ITEMS.register("grey_accented_black_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_ACCENTED_BLACK_SADDLE = ITEMS.register("light_blue_accented_black_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_ACCENTED_BLACK_SADDLE = ITEMS.register("light_grey_accented_black_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIME_ACCENTED_BLACK_SADDLE = ITEMS.register("lime_accented_black_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_ACCENTED_BLACK_SADDLE = ITEMS.register("magenta_accented_black_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_ACCENTED_BLACK_SADDLE = ITEMS.register("orange_accented_black_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> PINK_ACCENTED_BLACK_SADDLE = ITEMS.register("pink_accented_black_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_ACCENTED_BLACK_SADDLE = ITEMS.register("purple_accented_black_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> RED_ACCENTED_BLACK_SADDLE = ITEMS.register("red_accented_black_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> WHITE_ACCENTED_BLACK_SADDLE = ITEMS.register("white_accented_black_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_ACCENTED_BLACK_SADDLE = ITEMS.register("yellow_accented_black_saddle",
            () -> new SaddleItem(new Item.Properties()));

    public static final RegistryObject<Item> BLUE_ACCENTED_BLACK_HEAVY_SADDLE = ITEMS.register("blue_accented_black_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> BROWN_ACCENTED_BLACK_HEAVY_SADDLE = ITEMS.register("brown_accented_black_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> CYAN_ACCENTED_BLACK_HEAVY_SADDLE = ITEMS.register("cyan_accented_black_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> GREEN_ACCENTED_BLACK_HEAVY_SADDLE = ITEMS.register("green_accented_black_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> GREY_ACCENTED_BLACK_HEAVY_SADDLE = ITEMS.register("grey_accented_black_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_ACCENTED_BLACK_HEAVY_SADDLE = ITEMS.register("light_blue_accented_black_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_ACCENTED_BLACK_HEAVY_SADDLE = ITEMS.register("light_grey_accented_black_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIME_ACCENTED_BLACK_HEAVY_SADDLE = ITEMS.register("lime_accented_black_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_ACCENTED_BLACK_HEAVY_SADDLE = ITEMS.register("magenta_accented_black_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_ACCENTED_BLACK_HEAVY_SADDLE = ITEMS.register("orange_accented_black_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> PINK_ACCENTED_BLACK_HEAVY_SADDLE = ITEMS.register("pink_accented_black_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_ACCENTED_BLACK_HEAVY_SADDLE = ITEMS.register("purple_accented_black_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> RED_ACCENTED_BLACK_HEAVY_SADDLE = ITEMS.register("red_accented_black_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> WHITE_ACCENTED_BLACK_HEAVY_SADDLE = ITEMS.register("white_accented_black_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_ACCENTED_BLACK_HEAVY_SADDLE = ITEMS.register("yellow_accented_black_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));

    public static final RegistryObject<Item> BLACK_ACCENTED_LIGHT_SADDLE = ITEMS.register("black_accented_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> BLUE_ACCENTED_LIGHT_SADDLE = ITEMS.register("blue_accented_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> BROWN_ACCENTED_LIGHT_SADDLE = ITEMS.register("brown_accented_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> CYAN_ACCENTED_LIGHT_SADDLE = ITEMS.register("cyan_accented_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> GREEN_ACCENTED_LIGHT_SADDLE = ITEMS.register("green_accented_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> GREY_ACCENTED_LIGHT_SADDLE = ITEMS.register("grey_accented_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_ACCENTED_LIGHT_SADDLE = ITEMS.register("light_blue_accented_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_ACCENTED_LIGHT_SADDLE = ITEMS.register("light_grey_accented_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIME_ACCENTED_LIGHT_SADDLE = ITEMS.register("lime_accented_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_ACCENTED_LIGHT_SADDLE = ITEMS.register("magenta_accented_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_ACCENTED_LIGHT_SADDLE = ITEMS.register("orange_accented_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> PINK_ACCENTED_LIGHT_SADDLE = ITEMS.register("pink_accented_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_ACCENTED_LIGHT_SADDLE = ITEMS.register("purple_accented_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> RED_ACCENTED_LIGHT_SADDLE = ITEMS.register("red_accented_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> WHITE_ACCENTED_LIGHT_SADDLE = ITEMS.register("white_accented_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_ACCENTED_LIGHT_SADDLE = ITEMS.register("yellow_accented_light_saddle",
            () -> new SaddleItem(new Item.Properties()));

    public static final RegistryObject<Item> BLACK_ACCENTED_SADDLE = ITEMS.register("black_accented_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> BLUE_ACCENTED_SADDLE = ITEMS.register("blue_accented_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> BROWN_ACCENTED_SADDLE = ITEMS.register("brown_accented_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> CYAN_ACCENTED_SADDLE = ITEMS.register("cyan_accented_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> GREEN_ACCENTED_SADDLE = ITEMS.register("green_accented_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> GREY_ACCENTED_SADDLE = ITEMS.register("grey_accented_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_ACCENTED_SADDLE = ITEMS.register("light_blue_accented_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_ACCENTED_SADDLE = ITEMS.register("light_grey_accented_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIME_ACCENTED_SADDLE = ITEMS.register("lime_accented_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_ACCENTED_SADDLE = ITEMS.register("magenta_accented_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_ACCENTED_SADDLE = ITEMS.register("orange_accented_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> PINK_ACCENTED_SADDLE = ITEMS.register("pink_accented_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_ACCENTED_SADDLE = ITEMS.register("purple_accented_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> RED_ACCENTED_SADDLE = ITEMS.register("red_accented_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> WHITE_ACCENTED_SADDLE = ITEMS.register("white_accented_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_ACCENTED_SADDLE = ITEMS.register("yellow_accented_saddle",
            () -> new SaddleItem(new Item.Properties()));

    public static final RegistryObject<Item> BLACK_ACCENTED_HEAVY_SADDLE = ITEMS.register("black_accented_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> BLUE_ACCENTED_HEAVY_SADDLE = ITEMS.register("blue_accented_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> BROWN_ACCENTED_HEAVY_SADDLE = ITEMS.register("brown_accented_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> CYAN_ACCENTED_HEAVY_SADDLE = ITEMS.register("cyan_accented_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> GREEN_ACCENTED_HEAVY_SADDLE = ITEMS.register("green_accented_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> GREY_ACCENTED_HEAVY_SADDLE = ITEMS.register("grey_accented_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_ACCENTED_HEAVY_SADDLE = ITEMS.register("light_blue_accented_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_ACCENTED_HEAVY_SADDLE = ITEMS.register("light_grey_accented_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIME_ACCENTED_HEAVY_SADDLE = ITEMS.register("lime_accented_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_ACCENTED_HEAVY_SADDLE = ITEMS.register("magenta_accented_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_ACCENTED_HEAVY_SADDLE = ITEMS.register("orange_accented_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> PINK_ACCENTED_HEAVY_SADDLE = ITEMS.register("pink_accented_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_ACCENTED_HEAVY_SADDLE = ITEMS.register("purple_accented_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> RED_ACCENTED_HEAVY_SADDLE = ITEMS.register("red_accented_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> WHITE_ACCENTED_HEAVY_SADDLE = ITEMS.register("white_accented_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_ACCENTED_HEAVY_SADDLE = ITEMS.register("yellow_accented_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));

    public static final RegistryObject<Item> BLACK_ACCENTED_WHITE_LIGHT_SADDLE = ITEMS.register("black_accented_white_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> BLUE_ACCENTED_WHITE_LIGHT_SADDLE = ITEMS.register("blue_accented_white_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> BROWN_ACCENTED_WHITE_LIGHT_SADDLE = ITEMS.register("brown_accented_white_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> CYAN_ACCENTED_WHITE_LIGHT_SADDLE = ITEMS.register("cyan_accented_white_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> GREEN_ACCENTED_WHITE_LIGHT_SADDLE = ITEMS.register("green_accented_white_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> GREY_ACCENTED_WHITE_LIGHT_SADDLE = ITEMS.register("grey_accented_white_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_ACCENTED_WHITE_LIGHT_SADDLE = ITEMS.register("light_blue_accented_white_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_ACCENTED_WHITE_LIGHT_SADDLE = ITEMS.register("light_grey_accented_white_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIME_ACCENTED_WHITE_LIGHT_SADDLE = ITEMS.register("lime_accented_white_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_ACCENTED_WHITE_LIGHT_SADDLE = ITEMS.register("magenta_accented_white_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_ACCENTED_WHITE_LIGHT_SADDLE = ITEMS.register("orange_accented_white_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> PINK_ACCENTED_WHITE_LIGHT_SADDLE = ITEMS.register("pink_accented_white_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_ACCENTED_WHITE_LIGHT_SADDLE = ITEMS.register("purple_accented_white_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> RED_ACCENTED_WHITE_LIGHT_SADDLE = ITEMS.register("red_accented_white_light_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_ACCENTED_WHITE_LIGHT_SADDLE = ITEMS.register("yellow_accented_white_light_saddle",
            () -> new SaddleItem(new Item.Properties()));

    public static final RegistryObject<Item> BLACK_ACCENTED_WHITE_SADDLE = ITEMS.register("black_accented_white_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> BLUE_ACCENTED_WHITE_SADDLE = ITEMS.register("blue_accented_white_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> BROWN_ACCENTED_WHITE_SADDLE = ITEMS.register("brown_accented_white_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> CYAN_ACCENTED_WHITE_SADDLE = ITEMS.register("cyan_accented_white_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> GREEN_ACCENTED_WHITE_SADDLE = ITEMS.register("green_accented_white_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> GREY_ACCENTED_WHITE_SADDLE = ITEMS.register("grey_accented_white_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_ACCENTED_WHITE_SADDLE = ITEMS.register("light_blue_accented_white_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_ACCENTED_WHITE_SADDLE = ITEMS.register("light_grey_accented_white_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIME_ACCENTED_WHITE_SADDLE = ITEMS.register("lime_accented_white_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_ACCENTED_WHITE_SADDLE = ITEMS.register("magenta_accented_white_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_ACCENTED_WHITE_SADDLE = ITEMS.register("orange_accented_white_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> PINK_ACCENTED_WHITE_SADDLE = ITEMS.register("pink_accented_white_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_ACCENTED_WHITE_SADDLE = ITEMS.register("purple_accented_white_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> RED_ACCENTED_WHITE_SADDLE = ITEMS.register("red_accented_white_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_ACCENTED_WHITE_SADDLE = ITEMS.register("yellow_accented_white_saddle",
            () -> new SaddleItem(new Item.Properties()));

    public static final RegistryObject<Item> BLACK_ACCENTED_WHITE_HEAVY_SADDLE = ITEMS.register("black_accented_white_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> BLUE_ACCENTED_WHITE_HEAVY_SADDLE = ITEMS.register("blue_accented_white_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> BROWN_ACCENTED_WHITE_HEAVY_SADDLE = ITEMS.register("brown_accented_white_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> CYAN_ACCENTED_WHITE_HEAVY_SADDLE = ITEMS.register("cyan_accented_white_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> GREEN_ACCENTED_WHITE_HEAVY_SADDLE = ITEMS.register("green_accented_white_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> GREY_ACCENTED_WHITE_HEAVY_SADDLE = ITEMS.register("grey_accented_white_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_ACCENTED_WHITE_HEAVY_SADDLE = ITEMS.register("light_blue_accented_white_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_ACCENTED_WHITE_HEAVY_SADDLE = ITEMS.register("light_grey_accented_white_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> LIME_ACCENTED_WHITE_HEAVY_SADDLE = ITEMS.register("lime_accented_white_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_ACCENTED_WHITE_HEAVY_SADDLE = ITEMS.register("magenta_accented_white_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_ACCENTED_WHITE_HEAVY_SADDLE = ITEMS.register("orange_accented_white_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> PINK_ACCENTED_WHITE_HEAVY_SADDLE = ITEMS.register("pink_accented_white_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_ACCENTED_WHITE_HEAVY_SADDLE = ITEMS.register("purple_accented_white_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> RED_ACCENTED_WHITE_HEAVY_SADDLE = ITEMS.register("red_accented_white_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_ACCENTED_WHITE_HEAVY_SADDLE = ITEMS.register("yellow_accented_white_heavy_saddle",
            () -> new SaddleItem(new Item.Properties()));

    public static final RegistryObject<Item> HALLOW_HEART = ITEMS.register("hallow_heart", HallowHeartItem::new);

    //Icons (UNOBTAINABLE)
    public static final RegistryObject<Item> LIVESTOCK_OVERHAUL = ITEMS.register("livestock_overhaul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LIVESTOCK_OVERHAUL_FOOD = ITEMS.register("livestock_overhaul_food",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HORSE_COLORS = ITEMS.register("horse_colors",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HORSE_MARKINGS = ITEMS.register("horse_markings",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HORSE_BREEDS = ITEMS.register("horse_breeds",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HORSE_EYES = ITEMS.register("horse_eyes",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COW_COLORS = ITEMS.register("cow_colors",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COW_MARKINGS = ITEMS.register("cow_markings",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COW_BREEDS = ITEMS.register("cow_breeds",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> UNICORN_COLORS = ITEMS.register("unicorn_colors",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> UNICORN_MARKINGS = ITEMS.register("unicorn_markings",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> UNICORN_BREEDS = ITEMS.register("unicorn_breeds",
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