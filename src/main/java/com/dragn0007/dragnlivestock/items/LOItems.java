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
    public static final RegistryObject<Item> OX_SPAWN_EGG = ITEMS.register("ox_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.OX_ENTITY, 0xa19477, 0x826b54, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_GOAT_SPAWN_EGG = ITEMS.register("o_goat_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_GOAT_ENTITY, 0xfafafa, 0xeae7de, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_UNDEAD_HORSE_SPAWN_EGG = ITEMS.register("o_undead_horse_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_UNDEAD_HORSE_ENTITY, 0xdbdbdb, 0x868686, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_FROG_SPAWN_EGG = ITEMS.register("o_frog_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_FROG_ENTITY, 0x9cc15c, 0xc34f31, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> GRUB_SPAWN_EGG = ITEMS.register("grub_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.GRUB_ENTITY, 0xf0f1c3, 0xc9bb8d, new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> OVERWORLD_UNICORN_SPAWN_EGG = ITEMS.register("overworld_unicorn_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.OVERWORLD_UNICORN_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> NETHER_UNICORN_SPAWN_EGG = ITEMS.register("nether_unicorn_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.NETHER_UNICORN_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> END_UNICORN_SPAWN_EGG = ITEMS.register("end_unicorn_spawn_egg",
         () -> new ForgeSpawnEggItem(EntityTypes.END_UNICORN_ENTITY, 0x00FFFFFF, 0x00FFFFFF, new Item.Properties().stacksTo(64)));

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

    public static final RegistryObject<Item> CARIBOU_SPAWN_EGG = ITEMS.register("caribou_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.CARIBOU_ENTITY, 0x846957, 0xfff7ed, new Item.Properties().stacksTo(64)));


    //Misc
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


    public static final RegistryObject<Item> GENDER_TEST_STRIP = ITEMS.register("gender_test_strip",
            () -> new GenderTestKit(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> MALE_GENDER_TEST_STRIP = ITEMS.register("male_gender_test_strip",
            () -> new MaleGenderTestKit(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> FEMALE_GENDER_TEST_STRIP = ITEMS.register("female_gender_test_strip",
            () -> new FemaleGenderTestKit(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> UTILITY_KNIFE = ITEMS.register("utility_knife",
            () -> new UtilityKnifeItem());

    public static final RegistryObject<Item> GRUB_SWEATER = ITEMS.register("grub_sweater",
            () -> new GrubSweaterItem(new Item.Properties()));

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

    public static final RegistryObject<Item> NETHERITE_HORSE_ARMOR = ITEMS.register("netherite_horse_armor",
            () -> new HorseArmorItem(15, "netherite", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> GRIFFITH_INSPIRED_HORSE_ARMOR = ITEMS.register("griffith_inspired_horse_armor",
            () -> new HorseArmorItem(15, "griffth", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> CHAINMAIL_HORSE_ARMOR = ITEMS.register("chainmail_horse_armor",
            () -> new HorseArmorItem(4, "chainmail", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> BLACK_SADDLE = ITEMS.register("black_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> WHITE_SADDLE = ITEMS.register("white_saddle",
            () -> new SaddleItem(new Item.Properties()));
    public static final RegistryObject<Item> RODEO_HARNESS = ITEMS.register("rodeo_harness",
            () -> new RodeoHarnessItem(new Item.Properties()));

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
            () -> new Item((new Item.Properties()).stacksTo(1)));

//    public static final RegistryObject<Item> COVERED_WAGON = ITEMS.register("covered_wagon", CoveredWagonItem::new);


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


    //Mod Item Tab Icon (UNOBTAINABLE)
    public static final RegistryObject<Item> LIVESTOCK_OVERHAUL = ITEMS.register("livestock_overhaul",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LIVESTOCK_OVERHAUL_FOOD = ITEMS.register("livestock_overhaul_food",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}