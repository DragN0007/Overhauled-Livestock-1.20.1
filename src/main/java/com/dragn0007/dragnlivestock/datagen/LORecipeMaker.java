package com.dragn0007.dragnlivestock.datagen;

import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LOTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class LORecipeMaker extends RecipeProvider implements IConditionBuilder {
    public LORecipeMaker(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {

//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.BLACK_SADDLE.get())
//                .requires(Items.SADDLE)
//                .requires(Items.BLACK_DYE)
//                .unlockedBy("has_saddle", inventoryTrigger(ItemPredicate.Builder.item()
//                        .of(Items.SADDLE)
//                        .build()))
//                .save(pFinishedRecipeConsumer);
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.WHITE_SADDLE.get())
//                .requires(Items.SADDLE)
//                .requires(Items.WHITE_DYE)
//                .unlockedBy("has_saddle", inventoryTrigger(ItemPredicate.Builder.item()
//                        .of(Items.SADDLE)
//                        .build()))
//                .save(pFinishedRecipeConsumer);



        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.RAW_CHEESE.get())
                .requires(LOItems.COW_MILK_JUG.get())
                .requires(LOItems.COW_MILK_JUG.get())
                .unlockedBy("has_milk", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.MILK)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.RAW_SHEEP_CHEESE.get())
                .requires(LOItems.SHEEP_MILK_JUG.get())
                .requires(LOItems.SHEEP_MILK_JUG.get())
                .unlockedBy("has_milk", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.MILK)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.RAW_LLAMA_CHEESE.get())
                .requires(LOItems.LLAMA_MILK_JUG.get())
                .requires(LOItems.LLAMA_MILK_JUG.get())
                .unlockedBy("has_milk", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.MILK)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.RAW_GOAT_CHEESE.get())
                .requires(LOItems.GOAT_MILK_JUG.get())
                .requires(LOItems.GOAT_MILK_JUG.get())
                .unlockedBy("has_milk", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.MILK)
                        .build()))
                .save(pFinishedRecipeConsumer);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.BEEF_STRIPS.get(), 2)
                .requires(LOItems.UTILITY_KNIFE.get())
                .requires(LOTags.Items.MAKES_BEEF_JERKY)
                .unlockedBy("has_knife", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOItems.UTILITY_KNIFE.get())
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.CHICKEN_STRIPS.get(), 2)
                .requires(LOItems.UTILITY_KNIFE.get())
                .requires(LOTags.Items.MAKES_CHICKEN_JERKY)
                .unlockedBy("has_knife", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOItems.UTILITY_KNIFE.get())
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.PORK_STRIPS.get(), 2)
                .requires(LOItems.UTILITY_KNIFE.get())
                .requires(LOTags.Items.MAKES_PORK_JERKY)
                .unlockedBy("has_knife", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOItems.UTILITY_KNIFE.get())
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.MUTTON_STRIPS.get(), 2)
                .requires(LOItems.UTILITY_KNIFE.get())
                .requires(LOTags.Items.MAKES_MUTTON_JERKY)
                .unlockedBy("has_knife", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOItems.UTILITY_KNIFE.get())
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.FISH_STRIPS.get(), 2)
                .requires(LOItems.UTILITY_KNIFE.get())
                .requires(LOTags.Items.MAKES_FISH_JERKY)
                .unlockedBy("has_knife", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOItems.UTILITY_KNIFE.get())
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.GAME_STRIPS.get(), 2)
                .requires(LOItems.UTILITY_KNIFE.get())
                .requires(LOTags.Items.MAKES_GAME_JERKY)
                .unlockedBy("has_knife", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOItems.UTILITY_KNIFE.get())
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.GENERIC_STRIPS.get(), 2)
                .requires(LOItems.UTILITY_KNIFE.get())
                .requires(LOTags.Items.MAKES_GENERIC_JERKY)
                .unlockedBy("has_knife", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOItems.UTILITY_KNIFE.get())
                        .build()))
                .save(pFinishedRecipeConsumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.UTILITY_KNIFE.get())
                .define('A', Tags.Items.STONE)
                .define('B', Items.STICK)
                .pattern(" A")
                .pattern("B ")
                .unlockedBy("has_stone", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Tags.Items.STONE).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.SADDLE)
                .define('A', Items.LEATHER)
                .define('B', Items.IRON_NUGGET)
                .pattern("AAA")
                .pattern("A A")
                .pattern("B B")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.LEATHER_HORSE_ARMOR)
                .define('A', Items.LEATHER)
                .define('B', ItemTags.WOOL_CARPETS)
                .pattern("  A")
                .pattern("AAA")
                .pattern("ABA")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.CHAINMAIL_HORSE_ARMOR.get())
                .define('A', Items.LEATHER)
                .define('B', ItemTags.WOOL_CARPETS)
                .define('C', Items.CHAIN)
                .pattern("  C")
                .pattern("CCC")
                .pattern("ABA")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.IRON_HORSE_ARMOR)
                .define('A', Items.LEATHER)
                .define('B', ItemTags.WOOL_CARPETS)
                .define('C', Items.IRON_INGOT)
                .pattern("  C")
                .pattern("CCC")
                .pattern("ABA")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.GOLDEN_HORSE_ARMOR)
                .define('A', Items.LEATHER)
                .define('B', ItemTags.WOOL_CARPETS)
                .define('C', Items.GOLD_INGOT)
                .pattern("  C")
                .pattern("CCC")
                .pattern("ABA")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.DIAMOND_HORSE_ARMOR)
                .define('A', Items.LEATHER)
                .define('B', ItemTags.WOOL_CARPETS)
                .define('C', Items.DIAMOND)
                .pattern("  C")
                .pattern("CCC")
                .pattern("ABA")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.OMELETTE.get(), 1)
                .requires(LOTags.Items.EGG)
                .requires(LOTags.Items.EGG)
                .requires(LOTags.Items.VEGETABLES)
                .unlockedBy("has_egg", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.EGG)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.CHEESECAKE.get())
                .define('A', LOTags.Items.CHEESE)
                .define('B', LOTags.Items.MILK)
                .define('C', LOTags.Items.EGG)
                .define('D', Items.SUGAR)
                .pattern("BBB")
                .pattern("DCD")
                .pattern("BAB")
                .unlockedBy("has_milk", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.MILK).build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.BERRY_GLAZED_PORK_RIB_CHOP.get(), 1)
                .requires(LOItems.COOKED_PORK_RIB_CHOP.get())
                .requires(Items.SWEET_BERRIES)
                .unlockedBy("has_berries", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.SWEET_BERRIES)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.BERRY_GLAZED_MUTTON_RIB.get(), 1)
                .requires(LOItems.COOKED_MUTTON_RIB.get())
                .requires(Items.SWEET_BERRIES)
                .unlockedBy("has_berries", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.SWEET_BERRIES)
                        .build()))
                .save(pFinishedRecipeConsumer);




        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.GRUB_SWEATER.get())
                .requires(Items.RED_WOOL)
                .requires(Items.RED_WOOL)
                .requires(Items.RED_WOOL)
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.RED_WOOL)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.CARROT_SOUP.get())
                .requires(Items.CARROT)
                .requires(Items.CARROT)
                .requires(Items.CARROT)
                .requires(Items.CARROT)
                .requires(Items.CARROT)
                .requires(Items.CARROT)
                .requires(Items.BOWL)
                .unlockedBy("has_bowl", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BOWL)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.GLOW_BERRY_SOUP.get())
                .requires(Items.GLOW_BERRIES)
                .requires(Items.GLOW_BERRIES)
                .requires(Items.GLOW_BERRIES)
                .requires(Items.GLOW_BERRIES)
                .requires(Items.GLOW_BERRIES)
                .requires(Items.GLOW_BERRIES)
                .requires(Items.BOWL)
                .unlockedBy("has_bowl", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BOWL)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.MELON_SOUP.get())
                .requires(Items.MELON_SLICE)
                .requires(Items.MELON_SLICE)
                .requires(Items.MELON_SLICE)
                .requires(Items.MELON_SLICE)
                .requires(Items.MELON_SLICE)
                .requires(Items.MELON_SLICE)
                .requires(Items.BOWL)
                .unlockedBy("has_bowl", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BOWL)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.POTATO_SOUP.get())
                .requires(Items.POTATO)
                .requires(Items.POTATO)
                .requires(Items.POTATO)
                .requires(Items.POTATO)
                .requires(Items.POTATO)
                .requires(Items.POTATO)
                .requires(Items.BOWL)
                .unlockedBy("has_bowl", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BOWL)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.PUMPKIN_SOUP.get())
                .requires(Items.PUMPKIN)
                .requires(Items.PUMPKIN)
                .requires(Items.PUMPKIN)
                .requires(Items.BOWL)
                .unlockedBy("has_bowl", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BOWL)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.SWEET_BERRY_SOUP.get())
                .requires(Items.SWEET_BERRIES)
                .requires(Items.SWEET_BERRIES)
                .requires(Items.SWEET_BERRIES)
                .requires(Items.SWEET_BERRIES)
                .requires(Items.SWEET_BERRIES)
                .requires(Items.SWEET_BERRIES)
                .requires(Items.BOWL)
                .unlockedBy("has_bowl", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BOWL)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.GRAIN_SOUP.get())
                .requires(Items.WHEAT)
                .requires(Items.WHEAT)
                .requires(Items.WHEAT)
                .requires(Items.WHEAT)
                .requires(Items.WHEAT)
                .requires(LOTags.Items.MILK)
                .requires(Items.BOWL)
                .unlockedBy("has_bowl", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BOWL)
                        .build()))
                .save(pFinishedRecipeConsumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.MANE_SCISSORS.get())
                .define('A', Items.IRON_INGOT)
                .define('B', Items.IRON_NUGGET)
                .pattern("A A")
                .pattern("A A")
                .pattern("BAB")
                .unlockedBy("has_iron", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.IRON_INGOT).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.TAIL_SCISSORS.get())
                .define('A', Items.IRON_INGOT)
                .define('B', Items.IRON_NUGGET)
                .pattern("A A")
                .pattern("A A")
                .pattern("ABA")
                .unlockedBy("has_iron", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.IRON_INGOT).build()))
                .save(pFinishedRecipeConsumer);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.SALMON_FILLET.get(), 2)
                .requires(Items.SALMON)
                .unlockedBy("has_fish", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.SALMON)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.COD_FILLET.get(), 2)
                .requires(Items.COD)
                .unlockedBy("has_fish", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.COD)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.EGG)
                .requires(LOTags.Items.EGG)
                .unlockedBy("has_egg", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.EGG)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.GENDER_TEST_STRIP.get(), 4)
                .requires(Items.PAPER)
                .requires(Items.PAPER)
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.GENDER_TEST_STRIP.get(), 1)
                .requires(LOItems.MALE_GENDER_TEST_STRIP.get())
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER)
                        .build()))
                .save(pFinishedRecipeConsumer, "gender_test_strip_from_male");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.GENDER_TEST_STRIP.get(), 1)
                .requires(LOItems.FEMALE_GENDER_TEST_STRIP.get())
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER)
                        .build()))
                .save(pFinishedRecipeConsumer, "gender_test_strip_from_female");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.OVERWORLD_UNICORN_SPAWN_EGG.get())
                .define('A', LOTags.Items.RAW_HORSE)
                .define('B', Items.ENCHANTED_GOLDEN_APPLE)
                .define('C', Items.HEART_OF_THE_SEA)
                .define('D', Items.DIAMOND_BLOCK)
                .pattern("ADA")
                .pattern("BCB")
                .pattern("ADA")
                .unlockedBy("has_heart_of_sea", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.HEART_OF_THE_SEA).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.NETHER_UNICORN_SPAWN_EGG.get())
                .define('A', LOTags.Items.RAW_HORSE)
                .define('B', Items.NETHERITE_INGOT)
                .define('C', Items.NETHER_STAR)
                .define('D', Items.DIAMOND_BLOCK)
                .pattern("ADA")
                .pattern("BCB")
                .pattern("ADA")
                .unlockedBy("has_nether_star", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.NETHER_STAR).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.END_UNICORN_SPAWN_EGG.get())
                .define('A', LOTags.Items.RAW_HORSE)
                .define('B', Items.END_ROD)
                .define('C', Items.DRAGON_BREATH)
                .define('D', Items.DIAMOND_BLOCK)
                .pattern("ADA")
                .pattern("BCB")
                .pattern("ADA")
                .unlockedBy("has_dragon_breath", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.DRAGON_BREATH).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.CAKE)
                .define('A', LOTags.Items.MILK)
                .define('C', Items.SUGAR)
                .define('D', Items.WHEAT)
                .define('E', LOTags.Items.EGG)
                .pattern("AAA")
                .pattern("CEC")
                .pattern("DDD")
                .unlockedBy("has_wheat", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.WHEAT).build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.COW_MILK_JUG.get(), 3)
                .requires(Items.MILK_BUCKET)
                .unlockedBy("has_milk", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.MILK)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.SHEEP_MILK_JUG.get(), 3)
                .requires(LOItems.SHEEP_MILK_BUCKET.get())
                .unlockedBy("has_milk", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.MILK)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.LLAMA_MILK_JUG.get(), 3)
                .requires(LOItems.LLAMA_MILK_BUCKET.get())
                .unlockedBy("has_milk", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.MILK)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.GOAT_MILK_JUG.get(), 3)
                .requires(LOItems.GOAT_MILK_BUCKET.get())
                .unlockedBy("has_milk", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.MILK)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.EGG_SALAD.get(), 1)
                .requires(LOTags.Items.EGG)
                .requires(LOTags.Items.EGG)
                .requires(Items.BOWL)
                .unlockedBy("has_egg", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.EGG)
                        .build()))
                .save(pFinishedRecipeConsumer);

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.BEEF_RIB_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_BEEF_RIB_STEAK.get(), 0.35F, 100)
                .unlockedBy("has_beef_rib_steak", has(LOItems.BEEF_RIB_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_beef_rib_steak_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.BEEF_RIB_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_BEEF_RIB_STEAK.get(), 0.35F, 200)
                .unlockedBy("has_beef_rib_steak", has(LOItems.BEEF_RIB_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_beef_rib_steak_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.BEEF_RIB_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_BEEF_RIB_STEAK.get(), 0.35F, 600)
                .unlockedBy("has_beef_rib_steak", has(LOItems.BEEF_RIB_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_beef_rib_steak_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.BEEF_SIRLOIN_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_BEEF_SIRLOIN_STEAK.get(), 0.35F, 100)
                .unlockedBy("has_beef_sirloin_steak", has(LOItems.BEEF_SIRLOIN_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_beef_sirloin_steak_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.BEEF_SIRLOIN_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_BEEF_SIRLOIN_STEAK.get(), 0.35F, 200)
                .unlockedBy("has_beef_sirloin_steak", has(LOItems.BEEF_SIRLOIN_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_beef_sirloin_steak_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.BEEF_SIRLOIN_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_BEEF_SIRLOIN_STEAK.get(), 0.35F, 600)
                .unlockedBy("has_beef_sirloin_steak", has(LOItems.BEEF_SIRLOIN_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_beef_sirloin_steak_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.CHICKEN_THIGH.get()), RecipeCategory.MISC, LOItems.COOKED_CHICKEN_THIGH.get(), 0.35F, 100)
                .unlockedBy("has_chicken_thigh", has(LOItems.CHICKEN_THIGH.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_chicken_thigh_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.CHICKEN_THIGH.get()), RecipeCategory.MISC, LOItems.COOKED_CHICKEN_THIGH.get(), 0.35F, 200)
                .unlockedBy("has_chicken_thigh", has(LOItems.CHICKEN_THIGH.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_chicken_thigh_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.CHICKEN_THIGH.get()), RecipeCategory.MISC, LOItems.COOKED_CHICKEN_THIGH.get(), 0.35F, 600)
                .unlockedBy("has_chicken_thigh", has(LOItems.CHICKEN_THIGH.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_chicken_thigh_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.HORSE.get()), RecipeCategory.MISC, LOItems.COOKED_HORSE.get(), 0.35F, 100)
                .unlockedBy("has_horse", has(LOItems.HORSE.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_horse_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.HORSE.get()), RecipeCategory.MISC, LOItems.COOKED_HORSE.get(), 0.35F, 200)
                .unlockedBy("has_horse", has(LOItems.HORSE.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_horse_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.HORSE.get()), RecipeCategory.MISC, LOItems.COOKED_HORSE.get(), 0.35F, 600)
                .unlockedBy("has_horse", has(LOItems.HORSE.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_horse_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.HORSE_RIB_STEAK.get()),RecipeCategory.MISC,  LOItems.COOKED_HORSE_RIB_STEAK.get(), 0.35F, 100)
                .unlockedBy("has_horse_rib_steak", has(LOItems.HORSE_RIB_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_horse_rib_steak_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.HORSE_RIB_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_HORSE_RIB_STEAK.get(), 0.35F, 200)
                .unlockedBy("has_horse_rib_steak", has(LOItems.HORSE_RIB_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_horse_rib_steak_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.HORSE_RIB_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_HORSE_RIB_STEAK.get(), 0.35F, 600)
                .unlockedBy("has_horse_rib_steak", has(LOItems.HORSE_RIB_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_horse_rib_steak_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.HORSE_SIRLOIN_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_HORSE_SIRLOIN_STEAK.get(), 0.35F, 100)
                .unlockedBy("has_horse_sirloin_steak", has(LOItems.HORSE_SIRLOIN_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_horse_sirloin_steak_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.HORSE_SIRLOIN_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_HORSE_SIRLOIN_STEAK.get(), 0.35F, 200)
                .unlockedBy("has_horse_sirloin_steak", has(LOItems.HORSE_SIRLOIN_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_horse_sirloin_steak_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.HORSE_SIRLOIN_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_HORSE_SIRLOIN_STEAK.get(), 0.35F, 600)
                .unlockedBy("has_horse_sirloin_steak", has(LOItems.HORSE_SIRLOIN_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_horse_sirloin_steak_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.LLAMA.get()), RecipeCategory.MISC, LOItems.COOKED_LLAMA.get(), 0.35F, 100)
                .unlockedBy("has_llama", has(LOItems.LLAMA.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_llama_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.LLAMA.get()), RecipeCategory.MISC, LOItems.COOKED_LLAMA.get(), 0.35F, 200)
                .unlockedBy("has_llama", has(LOItems.LLAMA.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_llama_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.LLAMA.get()), RecipeCategory.MISC, LOItems.COOKED_LLAMA.get(), 0.35F, 600)
                .unlockedBy("has_llama", has(LOItems.LLAMA.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_llama_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.LLAMA_RIB.get()), RecipeCategory.MISC, LOItems.COOKED_LLAMA_RIB.get(), 0.35F, 100)
                .unlockedBy("has_llama_rib", has(LOItems.LLAMA_RIB.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_llama_rib_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.LLAMA_RIB.get()), RecipeCategory.MISC, LOItems.COOKED_LLAMA_RIB.get(), 0.35F, 200)
                .unlockedBy("has_llama_rib", has(LOItems.LLAMA_RIB.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_llama_rib_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.LLAMA_RIB.get()), RecipeCategory.MISC, LOItems.COOKED_LLAMA_RIB.get(), 0.35F, 600)
                .unlockedBy("has_llama_rib", has(LOItems.LLAMA_RIB.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_llama_rib_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.LLAMA_LOIN.get()), RecipeCategory.MISC, LOItems.COOKED_LLAMA_LOIN.get(), 0.35F, 100)
                .unlockedBy("has_llama_loin", has(LOItems.LLAMA_LOIN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_llama_loin_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.LLAMA_LOIN.get()), RecipeCategory.MISC, LOItems.COOKED_LLAMA_LOIN.get(), 0.35F, 200)
                .unlockedBy("has_llama_loin", has(LOItems.LLAMA_LOIN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_llama_loin_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.LLAMA_LOIN.get()), RecipeCategory.MISC, LOItems.COOKED_LLAMA_LOIN.get(), 0.35F, 600)
                .unlockedBy("has_llama_loin", has(LOItems.LLAMA_LOIN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_llama_loin_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.UNICORN.get()), RecipeCategory.MISC, LOItems.COOKED_UNICORN.get(), 0.35F, 100)
                .unlockedBy("has_unicorn", has(LOItems.UNICORN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_unicorn_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.UNICORN.get()), RecipeCategory.MISC, LOItems.COOKED_UNICORN.get(), 0.35F, 200)
                .unlockedBy("has_unicorn", has(LOItems.UNICORN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_unicorn_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.UNICORN.get()), RecipeCategory.MISC, LOItems.COOKED_UNICORN.get(), 0.35F, 600)
                .unlockedBy("has_unicorn", has(LOItems.UNICORN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_unicorn_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.UNICORN_RIB_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_UNICORN_RIB_STEAK.get(), 0.35F, 100)
                .unlockedBy("has_unicorn_rib_steak", has(LOItems.UNICORN_RIB_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_unicorn_rib_steak_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.UNICORN_RIB_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_UNICORN_RIB_STEAK.get(), 0.35F, 200)
                .unlockedBy("has_unicorn_rib_steak", has(LOItems.UNICORN_RIB_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_unicorn_rib_steak_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.UNICORN_RIB_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_UNICORN_RIB_STEAK.get(), 0.35F, 600)
                .unlockedBy("has_unicorn_rib_steak", has(LOItems.UNICORN_RIB_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_unicorn_rib_steak_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.UNICORN_SIRLOIN_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_UNICORN_SIRLOIN_STEAK.get(), 0.35F, 100)
                .unlockedBy("has_unicorn_sirloin_steak", has(LOItems.UNICORN_SIRLOIN_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_unicorn_sirloin_steak_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.UNICORN_SIRLOIN_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_UNICORN_SIRLOIN_STEAK.get(), 0.35F, 200)
                .unlockedBy("has_unicorn_sirloin_steak", has(LOItems.UNICORN_SIRLOIN_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_unicorn_sirloin_steak_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.UNICORN_SIRLOIN_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_UNICORN_SIRLOIN_STEAK.get(), 0.35F, 600)
                .unlockedBy("has_unicorn_sirloin_steak", has(LOItems.UNICORN_SIRLOIN_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_unicorn_sirloin_steak_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.MUTTON_RIB.get()), RecipeCategory.MISC, LOItems.COOKED_MUTTON_RIB.get(), 0.35F, 100)
                .unlockedBy("has_mutton_rib", has(LOItems.MUTTON_RIB.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_mutton_rib_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.MUTTON_RIB.get()), RecipeCategory.MISC, LOItems.COOKED_MUTTON_RIB.get(), 0.35F, 200)
                .unlockedBy("has_mutton_rib", has(LOItems.MUTTON_RIB.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_mutton_rib_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.MUTTON_RIB.get()), RecipeCategory.MISC, LOItems.COOKED_MUTTON_RIB.get(), 0.35F, 600)
                .unlockedBy("has_mutton_rib", has(LOItems.MUTTON_RIB.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_mutton_rib_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.MUTTON_LOIN.get()), RecipeCategory.MISC, LOItems.COOKED_MUTTON_LOIN.get(), 0.35F, 100)
                .unlockedBy("has_mutton_loin", has(LOItems.MUTTON_LOIN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_mutton_loin_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.MUTTON_LOIN.get()), RecipeCategory.MISC, LOItems.COOKED_MUTTON_LOIN.get(), 0.35F, 200)
                .unlockedBy("has_mutton_loin", has(LOItems.MUTTON_LOIN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_mutton_loin_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.MUTTON_LOIN.get()), RecipeCategory.MISC, LOItems.COOKED_MUTTON_LOIN.get(), 0.35F, 600)
                .unlockedBy("has_mutton_loin", has(LOItems.MUTTON_LOIN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_mutton_loin_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.CAMEL.get()), RecipeCategory.MISC, LOItems.COOKED_CAMEL.get(), 0.35F, 100)
                .unlockedBy("has_camel", has(LOItems.CAMEL.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_camel_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.CAMEL.get()), RecipeCategory.MISC, LOItems.COOKED_CAMEL.get(), 0.35F, 200)
                .unlockedBy("has_camel", has(LOItems.CAMEL.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_camel_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.CAMEL.get()), RecipeCategory.MISC, LOItems.COOKED_CAMEL.get(), 0.35F, 600)
                .unlockedBy("has_camel", has(LOItems.CAMEL.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_camel_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.CAMEL_RIB.get()), RecipeCategory.MISC, LOItems.COOKED_CAMEL_RIB.get(), 0.35F, 100)
                .unlockedBy("has_camel_rib", has(LOItems.CAMEL_RIB.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_camel_rib_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.CAMEL_RIB.get()), RecipeCategory.MISC, LOItems.COOKED_CAMEL_RIB.get(), 0.35F, 200)
                .unlockedBy("has_camel_rib", has(LOItems.CAMEL_RIB.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_camel_rib_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.CAMEL_RIB.get()), RecipeCategory.MISC, LOItems.COOKED_CAMEL_RIB.get(), 0.35F, 600)
                .unlockedBy("has_camel_rib", has(LOItems.CAMEL_RIB.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_camel_rib_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.CAMEL_LOIN.get()), RecipeCategory.MISC, LOItems.COOKED_CAMEL_LOIN.get(), 0.35F, 100)
                .unlockedBy("has_camel_loin", has(LOItems.CAMEL_LOIN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_camel_loin_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.CAMEL_LOIN.get()), RecipeCategory.MISC, LOItems.COOKED_CAMEL_LOIN.get(), 0.35F, 200)
                .unlockedBy("has_camel_loin", has(LOItems.CAMEL_LOIN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_camel_loin_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.CAMEL_LOIN.get()), RecipeCategory.MISC, LOItems.COOKED_CAMEL_LOIN.get(), 0.35F, 600)
                .unlockedBy("has_camel_loin", has(LOItems.CAMEL_LOIN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_camel_loin_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.CHEVON.get()), RecipeCategory.MISC, LOItems.COOKED_CHEVON.get(), 0.35F, 100)
                .unlockedBy("has_chevon", has(LOItems.CHEVON.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_chevon_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.CHEVON.get()), RecipeCategory.MISC, LOItems.COOKED_CHEVON.get(), 0.35F, 200)
                .unlockedBy("has_chevon", has(LOItems.CHEVON.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_chevon_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.CHEVON.get()), RecipeCategory.MISC, LOItems.COOKED_CHEVON.get(), 0.35F, 600)
                .unlockedBy("has_chevon", has(LOItems.CHEVON.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_chevon_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.CHEVON_RIB.get()), RecipeCategory.MISC, LOItems.COOKED_CHEVON_RIB.get(), 0.35F, 100)
                .unlockedBy("has_chevon_rib", has(LOItems.CHEVON_RIB.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_chevon_rib_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.CHEVON_RIB.get()), RecipeCategory.MISC, LOItems.COOKED_CHEVON_RIB.get(), 0.35F, 200)
                .unlockedBy("has_chevon_rib", has(LOItems.CHEVON_RIB.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_chevon_rib_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.CHEVON_RIB.get()), RecipeCategory.MISC, LOItems.COOKED_CHEVON_RIB.get(), 0.35F, 600)
                .unlockedBy("has_chevon_rib", has(LOItems.CHEVON_RIB.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_chevon_rib_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.CHEVON_LOIN.get()), RecipeCategory.MISC, LOItems.COOKED_CHEVON_LOIN.get(), 0.35F, 100)
                .unlockedBy("has_chevon_loin", has(LOItems.CHEVON_LOIN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_chevon_loin_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.CHEVON_LOIN.get()), RecipeCategory.MISC, LOItems.COOKED_CHEVON_LOIN.get(), 0.35F, 200)
                .unlockedBy("has_chevon_loin", has(LOItems.CHEVON_LOIN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_chevon_loin_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.CHEVON_LOIN.get()), RecipeCategory.MISC, LOItems.COOKED_CHEVON_LOIN.get(), 0.35F, 600)
                .unlockedBy("has_chevon_loin", has(LOItems.CHEVON_LOIN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_chevon_loin_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.RABBIT_THIGH.get()), RecipeCategory.MISC, LOItems.COOKED_RABBIT_THIGH.get(), 0.35F, 100)
                .unlockedBy("has_rabbit_thigh", has(LOItems.RABBIT_THIGH.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_rabbit_thigh_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.RABBIT_THIGH.get()), RecipeCategory.MISC, LOItems.COOKED_RABBIT_THIGH.get(), 0.35F, 200)
                .unlockedBy("has_rabbit_thigh", has(LOItems.RABBIT_THIGH.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_rabbit_thigh_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.RABBIT_THIGH.get()), RecipeCategory.MISC, LOItems.COOKED_RABBIT_THIGH.get(), 0.35F, 600)
                .unlockedBy("has_rabbit_thigh", has(LOItems.RABBIT_THIGH.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_rabbit_thigh_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.FROG.get()), RecipeCategory.MISC, LOItems.COOKED_FROG.get(), 0.35F, 100)
                .unlockedBy("has_frog", has(LOItems.FROG.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_frog_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.FROG.get()), RecipeCategory.MISC, LOItems.COOKED_FROG.get(), 0.35F, 200)
                .unlockedBy("has_frog", has(LOItems.FROG.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_frog_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.FROG.get()), RecipeCategory.MISC, LOItems.COOKED_FROG.get(), 0.35F, 600)
                .unlockedBy("has_frog", has(LOItems.FROG.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_frog_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.GRUB.get()), RecipeCategory.MISC, LOItems.COOKED_GRUB.get(), 0.35F, 100)
                .unlockedBy("has_grub", has(LOItems.GRUB.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_grub_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.GRUB.get()), RecipeCategory.MISC, LOItems.COOKED_GRUB.get(), 0.35F, 200)
                .unlockedBy("has_grub", has(LOItems.GRUB.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_grub_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.GRUB.get()), RecipeCategory.MISC, LOItems.COOKED_GRUB.get(), 0.35F, 600)
                .unlockedBy("has_grub", has(LOItems.GRUB.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_grub_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.PORK_RIB_CHOP.get()), RecipeCategory.MISC, LOItems.COOKED_PORK_RIB_CHOP.get(), 0.35F, 100)
                .unlockedBy("has_pork_rib_chop", has(LOItems.PORK_RIB_CHOP.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_pork_rib_chop_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.PORK_RIB_CHOP.get()), RecipeCategory.MISC, LOItems.COOKED_PORK_RIB_CHOP.get(), 0.35F, 200)
                .unlockedBy("has_pork_rib_chop", has(LOItems.PORK_RIB_CHOP.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_pork_rib_chop_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.PORK_RIB_CHOP.get()), RecipeCategory.MISC, LOItems.COOKED_PORK_RIB_CHOP.get(), 0.35F, 600)
                .unlockedBy("has_pork_rib_chop", has(LOItems.PORK_RIB_CHOP.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_pork_rib_chop_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.PORK_TENDERLOIN.get()), RecipeCategory.MISC, LOItems.COOKED_PORK_TENDERLOIN.get(), 0.35F, 100)
                .unlockedBy("has_pork_tenderloin", has(LOItems.PORK_TENDERLOIN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_pork_tenderloin_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.PORK_TENDERLOIN.get()), RecipeCategory.MISC, LOItems.COOKED_PORK_TENDERLOIN.get(), 0.35F, 200)
                .unlockedBy("has_pork_tenderloin", has(LOItems.PORK_TENDERLOIN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_pork_tenderloin_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.PORK_TENDERLOIN.get()), RecipeCategory.MISC, LOItems.COOKED_PORK_TENDERLOIN.get(), 0.35F, 600)
                .unlockedBy("has_pork_tenderloin", has(LOItems.PORK_TENDERLOIN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_pork_tenderloin_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.CARIBOU.get()), RecipeCategory.MISC, LOItems.COOKED_CARIBOU.get(), 0.35F, 100)
                .unlockedBy("has_caribou", has(LOItems.CARIBOU.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_caribou_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.CARIBOU.get()), RecipeCategory.MISC, LOItems.COOKED_CARIBOU.get(), 0.35F, 200)
                .unlockedBy("has_caribou", has(LOItems.CARIBOU.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_caribou_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.CARIBOU.get()), RecipeCategory.MISC, LOItems.COOKED_CARIBOU.get(), 0.35F, 600)
                .unlockedBy("has_caribou", has(LOItems.CARIBOU.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_caribou_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.CARIBOU_RIB_STEAK.get()),RecipeCategory.MISC,  LOItems.COOKED_CARIBOU_RIB_STEAK.get(), 0.35F, 100)
                .unlockedBy("has_caribou_rib_steak", has(LOItems.CARIBOU_RIB_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_caribou_rib_steak_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.CARIBOU_RIB_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_CARIBOU_RIB_STEAK.get(), 0.35F, 200)
                .unlockedBy("has_caribou_rib_steak", has(LOItems.CARIBOU_RIB_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_caribou_rib_steak_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.CARIBOU_RIB_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_CARIBOU_RIB_STEAK.get(), 0.35F, 600)
                .unlockedBy("has_caribou_rib_steak", has(LOItems.CARIBOU_RIB_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_caribou_rib_steak_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.CARIBOU_SIRLOIN_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_CARIBOU_SIRLOIN_STEAK.get(), 0.35F, 100)
                .unlockedBy("has_caribou_sirloin_steak", has(LOItems.CARIBOU_SIRLOIN_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_caribou_sirloin_steak_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.CARIBOU_SIRLOIN_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_CARIBOU_SIRLOIN_STEAK.get(), 0.35F, 200)
                .unlockedBy("has_caribou_sirloin_steak", has(LOItems.CARIBOU_SIRLOIN_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_caribou_sirloin_steak_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.CARIBOU_SIRLOIN_STEAK.get()), RecipeCategory.MISC, LOItems.COOKED_CARIBOU_SIRLOIN_STEAK.get(), 0.35F, 600)
                .unlockedBy("has_caribou_sirloin_steak", has(LOItems.CARIBOU_SIRLOIN_STEAK.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_caribou_sirloin_steak_campfire_cooking"));


        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.SALMON_FILLET.get()), RecipeCategory.MISC, LOItems.COOKED_SALMON_FILLET.get(), 0.35F, 100)
                .unlockedBy("has_salmon_fillet", has(LOItems.SALMON_FILLET.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_salmon_fillet_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.SALMON_FILLET.get()), RecipeCategory.MISC, LOItems.COOKED_SALMON_FILLET.get(), 0.35F, 200)
                .unlockedBy("has_salmon_fillet", has(LOItems.SALMON_FILLET.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_salmon_fillet_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.SALMON_FILLET.get()), RecipeCategory.MISC, LOItems.COOKED_SALMON_FILLET.get(), 0.35F, 600)
                .unlockedBy("has_salmon_fillet", has(LOItems.SALMON_FILLET.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_salmon_fillet_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.COD_FILLET.get()), RecipeCategory.MISC, LOItems.COOKED_COD_FILLET.get(), 0.35F, 100)
                .unlockedBy("has_cod_fillet", has(LOItems.COD_FILLET.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_cod_fillet_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.COD_FILLET.get()), RecipeCategory.MISC, LOItems.COOKED_COD_FILLET.get(), 0.35F, 200)
                .unlockedBy("has_cod_fillet", has(LOItems.COD_FILLET.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_cod_fillet_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.COD_FILLET.get()), RecipeCategory.MISC, LOItems.COOKED_COD_FILLET.get(), 0.35F, 600)
                .unlockedBy("has_cod_fillet", has(LOItems.COD_FILLET.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_cod_fillet_campfire_cooking"));
    }
}