package com.dragn0007.dragnlivestock.datagen;

import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LOTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class LORecipeMaker extends RecipeProvider implements IConditionBuilder {
    public LORecipeMaker(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {

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

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.COW_MILK_JUG.get()), RecipeCategory.MISC, LOItems.CHEESE.get(), 0.35F, 200)
                .unlockedBy("has_milk", has(LOItems.COW_MILK_JUG.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cheese_smelting"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.SHEEP_MILK_JUG.get()), RecipeCategory.MISC, LOItems.SHEEP_CHEESE.get(), 0.35F, 200)
                .unlockedBy("has_sheep_milk", has(LOItems.SHEEP_MILK_JUG.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "sheep_cheese_smelting"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.LLAMA_MILK_JUG.get()), RecipeCategory.MISC, LOItems.LLAMA_CHEESE.get(), 0.35F, 200)
                .unlockedBy("has_llama_milk", has(LOItems.LLAMA_MILK_JUG.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "llama_cheese_smelting"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.GOAT_MILK_JUG.get()), RecipeCategory.MISC, LOItems.GOAT_CHEESE.get(), 0.35F, 200)
                .unlockedBy("has_goat_milk", has(LOItems.GOAT_MILK_JUG.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "goat_cheese_smelting"));

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
    }
}