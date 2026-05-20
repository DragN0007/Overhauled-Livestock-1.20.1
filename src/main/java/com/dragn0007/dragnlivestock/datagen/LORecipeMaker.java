package com.dragn0007.dragnlivestock.datagen;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.compat.medievalembroidery.MECompatItems;
import com.dragn0007.dragnlivestock.datagen.conditions.BlanketConfigCondition;
import com.dragn0007.dragnlivestock.datagen.conditions.HolidayConfigCondition;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LOTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;

import java.util.function.Consumer;

public class LORecipeMaker extends RecipeProvider implements IConditionBuilder {
    public LORecipeMaker(PackOutput pOutput) {
        super(pOutput);
    }

    public void buildRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        buildCommonRecipes(pFinishedRecipeConsumer);
        buildDyeRecipes(pFinishedRecipeConsumer);
    }

    public void buildCommonRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.MOUNT_REGISTRY.get())
                .requires(Items.BOOK)
                .requires(LOItems.MOUNT_KEY.get())
                .unlockedBy("has_book", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BOOK).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LIGHT_HORSE_ARMOR_SMITHING_TEMPLATE.get(), 3)
                .define('A', Items.LEATHER)
                .define('B', Items.PAPER)
                .define('C', LOTags.Items.CRAFTING_METALS)
                .pattern("BAB")
                .pattern("ACA")
                .pattern("CBC")
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER).build()))
                .save(pFinishedRecipeConsumer);


        ConditionalRecipe.builder()
                .addCondition(new HolidayConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "holiday_config_condition")))
                .addRecipe(
                        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.HOLIDAY_WAGON_HARNESS.get())
                                .requires(LOItems.WAGON_HARNESS.get())
                                .requires(Items.RED_DYE)
                                .requires(LOTags.Items.GOLD_METALS)
                                .unlockedBy("has_harness", inventoryTrigger(ItemPredicate.Builder.item()
                                        .of(LOItems.WAGON_HARNESS.get()).build()))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.HOLIDAY_WAGON_HARNESS.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new HolidayConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "holiday_config_condition")))
                .addRecipe(
                        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.HOLIDAY_SADDLE.get())
                                .requires(Items.SADDLE)
                                .requires(Items.RED_DYE)
                                .requires(LOTags.Items.GOLD_METALS)
                                .unlockedBy("has_saddle", inventoryTrigger(ItemPredicate.Builder.item()
                                        .of(Items.SADDLE).build()))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.HOLIDAY_SADDLE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new HolidayConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "holiday_config_condition")))
                .addRecipe(
                        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.HOLIDAY_LIGHT_SADDLE.get())
                                .requires(LOItems.LIGHT_SADDLE.get())
                                .requires(Items.RED_DYE)
                                .requires(LOTags.Items.GOLD_METALS)
                                .unlockedBy("has_saddle", inventoryTrigger(ItemPredicate.Builder.item()
                                        .of(Items.SADDLE).build()))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.HOLIDAY_LIGHT_SADDLE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new HolidayConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "holiday_config_condition")))
                .addRecipe(
                        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.HOLIDAY_HEAVY_SADDLE.get())
                                .requires(LOItems.HEAVY_SADDLE.get())
                                .requires(Items.RED_DYE)
                                .requires(LOTags.Items.GOLD_METALS)
                                .unlockedBy("has_saddle", inventoryTrigger(ItemPredicate.Builder.item()
                                        .of(Items.SADDLE).build()))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.HOLIDAY_HEAVY_SADDLE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new HolidayConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "holiday_config_condition")))
                .addRecipe(
                        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.RED_NOSE.get())
                                .requires(Items.RED_DYE)
                                .requires(Items.RED_DYE)
                                .requires(Items.RED_DYE)
                                .requires(Items.RED_DYE)
                                .unlockedBy("has_red_dye", inventoryTrigger(ItemPredicate.Builder.item()
                                        .of(Items.RED_DYE).build()))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.RED_NOSE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new HolidayConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "holiday_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.RAINBOW_STRING_LIGHTS.get())
                                .define('A', LOTags.Items.STRING)
                                .define('B', Items.RED_DYE)
                                .define('C', Items.YELLOW_DYE)
                                .define('D', Items.BLUE_DYE)
                                .pattern("B D")
                                .pattern(" A ")
                                .pattern("D C")
                                .unlockedBy("has_string", inventoryTrigger(ItemPredicate.Builder.item()
                                        .of(LOTags.Items.STRING).build()))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.RAINBOW_STRING_LIGHTS.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new HolidayConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "holiday_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.YELLOW_STRING_LIGHTS.get())
                                .define('A', LOTags.Items.STRING)
                                .define('B', Items.YELLOW_DYE)
                                .pattern("B B")
                                .pattern(" A ")
                                .pattern("B B")
                                .unlockedBy("has_string", inventoryTrigger(ItemPredicate.Builder.item()
                                        .of(LOTags.Items.STRING).build()))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.YELLOW_STRING_LIGHTS.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new HolidayConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "holiday_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.RED_STRING_LIGHTS.get())
                                .define('A', LOTags.Items.STRING)
                                .define('B', Items.RED_DYE)
                                .pattern("B B")
                                .pattern(" A ")
                                .pattern("B B")
                                .unlockedBy("has_string", inventoryTrigger(ItemPredicate.Builder.item()
                                        .of(LOTags.Items.STRING).build()))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.RED_STRING_LIGHTS.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new HolidayConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "holiday_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.BLUE_STRING_LIGHTS.get())
                                .define('A', LOTags.Items.STRING)
                                .define('B', Items.BLUE_DYE)
                                .pattern("B B")
                                .pattern(" A ")
                                .pattern("B B")
                                .unlockedBy("has_string", inventoryTrigger(ItemPredicate.Builder.item()
                                        .of(LOTags.Items.STRING).build()))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.BLUE_STRING_LIGHTS.get().toString()));




        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.MAGNIFYING_GLASS.get())
                .define('A', Items.GLASS)
                .define('B', Items.STICK)
                .pattern("  A")
                .pattern(" B ")
                .pattern("B  ")
                .unlockedBy("has_glass", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.GLASS).build()))
                .save(pFinishedRecipeConsumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.WAGON_WHEEL_FRAME.get())
                .define('A', ItemTags.PLANKS)
                .define('B', Items.STICK)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_wood", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.PLANKS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.WAGON_BODY.get())
                .define('A', ItemTags.PLANKS)
                .define('B', Items.STICK)
                .define('C', LOTags.Items.CRAFTING_METALS)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("BCB")
                .unlockedBy("has_wood", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.PLANKS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.WAGON_COVER.get())
                .define('A', Items.LEATHER)
                .define('B', ItemTags.WOOL)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("A A")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.COVERED_WAGON.get())
                .define('#', ItemTags.PLANKS)
                .define('A', LOItems.WAGON_BODY.get())
                .define('B', LOItems.WAGON_AXEL.get())
                .define('C', LOItems.WAGON_COVER.get())
                .pattern(" C ")
                .pattern("#A#")
                .pattern("B B")
                .unlockedBy("has_wood", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.PLANKS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LIVESTOCK_WAGON.get())
                .define('#', Items.STICK)
                .define('A', LOItems.WAGON_BODY.get())
                .define('B', LOItems.WAGON_AXEL.get())
                .pattern("###")
                .pattern("#A#")
                .pattern("B B")
                .unlockedBy("has_wood", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.PLANKS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LUMBER_WAGON.get())
                .define('#', ItemTags.LOGS)
                .define('A', LOItems.WAGON_BODY.get())
                .define('B', LOItems.WAGON_AXEL.get())
                .pattern("#A#")
                .pattern("B B")
                .unlockedBy("has_wood", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.PLANKS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.GOODS_CART.get())
                .define('#', ItemTags.PLANKS)
                .define('A', LOItems.WAGON_BODY.get())
                .define('B', LOItems.WAGON_AXEL.get())
                .define('C', LOItems.WAGON_COVER.get())
                .pattern(" C ")
                .pattern("#A#")
                .pattern(" B ")
                .unlockedBy("has_wood", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.PLANKS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.MINING_CART.get())
                .define('#', Tags.Items.STONE)
                .define('A', LOItems.WAGON_BODY.get())
                .define('B', LOItems.WAGON_AXEL.get())
                .pattern("#A#")
                .pattern(" B ")
                .unlockedBy("has_wood", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.PLANKS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.TRANSPORT_CART.get())
                .define('#', Items.CHEST)
                .define('A', LOItems.WAGON_BODY.get())
                .define('B', LOItems.WAGON_AXEL.get())
                .pattern(" A#")
                .pattern(" B ")
                .unlockedBy("has_wood", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.PLANKS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.PLOW.get())
                .define('#', ItemTags.HOES)
                .define('A', LOItems.WAGON_BODY.get())
                .define('B', LOItems.WAGON_AXEL.get())
                .pattern(" # ")
                .pattern("#A#")
                .pattern(" B ")
                .unlockedBy("has_wood", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.PLANKS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.MOWER.get())
                .define('#', ItemTags.SHOVELS)
                .define('A', LOItems.WAGON_BODY.get())
                .define('B', LOItems.WAGON_AXEL.get())
                .pattern(" # ")
                .pattern("#A#")
                .pattern(" B ")
                .unlockedBy("has_wood", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.PLANKS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.SLEIGH.get())
                .define('#', LOTags.Items.CRAFTING_METALS)
                .define('A', LOItems.WAGON_BODY.get())
                .define('C', Items.CHEST)
                .pattern("#  ")
                .pattern("#AC")
                .pattern("###")
                .unlockedBy("has_wood", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.PLANKS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.COUPE.get())
                .define('#', ItemTags.PLANKS)
                .define('A', LOItems.WAGON_BODY.get())
                .define('B', LOItems.WAGON_AXEL.get())
                .define('C', LOItems.WAGON_COVER.get())
                .pattern("#C#")
                .pattern("#A#")
                .pattern("B B")
                .unlockedBy("has_wood", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.PLANKS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.CABRIOLET.get())
                .define('#', ItemTags.PLANKS)
                .define('A', LOItems.WAGON_BODY.get())
                .define('B', LOItems.WAGON_AXEL.get())
                .define('C', LOItems.WAGON_COVER.get())
                .pattern(" C ")
                .pattern("#A#")
                .pattern(" B ")
                .unlockedBy("has_wood", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.PLANKS).build()))
                .save(pFinishedRecipeConsumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.MOUNT_KEY.get())
                .define('A', LOTags.Items.GOLD_METAL_NUGGETS)
                .pattern(" A")
                .pattern("A ")
                .unlockedBy("has_gold", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.GOLD_METAL_NUGGETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.SPINDLE.get())
                .define('A', Items.STICK)
                .define('B', LOTags.Items.CRAFTING_METAL_NUGGETS)
                .pattern("BAB")
                .pattern(" A ")
                .pattern(" A ")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.STICK).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.RODEO_HARNESS.get())
                .define('A', Items.LEATHER)
                .define('B', LOTags.Items.CRAFTING_METAL_NUGGETS)
                .pattern("A A")
                .pattern("BAB")
                .pattern("A A")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.WAGON_HARNESS.get())
                .define('A', Items.LEATHER)
                .define('B', LOTags.Items.CRAFTING_METAL_NUGGETS)
                .pattern("AAA")
                .pattern("BAB")
                .pattern("A A")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);



        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.AMERICAN_MEDIEVAL_BLANKET.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('B', Items.RED_CARPET)
                                .define('C', Items.WHITE_CARPET)
                                .define('D', LOTags.Items.GOLD_METAL_NUGGETS)
                                .pattern("ABB")
                                .pattern("CDC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.AMERICAN_MEDIEVAL_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.AMERICAN_MODERN_BLANKET.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('B', Items.RED_CARPET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("ABB")
                                .pattern("CC ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.AMERICAN_MODERN_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.AMERICAN_RACING_BLANKET.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('B', Items.RED_CARPET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("ABB")
                                .pattern("C  ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.AMERICAN_RACING_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.AMERICAN_WESTERN_BLANKET.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('B', Items.RED_CARPET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("ABB")
                                .pattern("CCC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.AMERICAN_WESTERN_BLANKET.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.AUTUMN_MEDIEVAL_BLANKET.get())
                                .define('A', Items.PURPLE_CARPET)
                                .define('B', Items.ORANGE_CARPET)
                                .define('C', Items.BROWN_CARPET)
                                .define('D', LOTags.Items.GOLD_METAL_NUGGETS)
                                .pattern("ABB")
                                .pattern("CDC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.AUTUMN_MEDIEVAL_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.AUTUMN_MODERN_BLANKET.get())
                                .define('A', Items.PURPLE_CARPET)
                                .define('B', Items.ORANGE_CARPET)
                                .define('C', Items.BROWN_CARPET)
                                .pattern("ABB")
                                .pattern("CC ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.AUTUMN_MODERN_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.AUTUMN_RACING_BLANKET.get())
                                .define('A', Items.PURPLE_CARPET)
                                .define('B', Items.ORANGE_CARPET)
                                .define('C', Items.BROWN_CARPET)
                                .pattern("ABB")
                                .pattern("C  ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.AUTUMN_RACING_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.AUTUMN_WESTERN_BLANKET.get())
                                .define('A', Items.PURPLE_CARPET)
                                .define('B', Items.ORANGE_CARPET)
                                .define('C', Items.BROWN_CARPET)
                                .pattern("ABB")
                                .pattern("CCC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.AUTUMN_WESTERN_BLANKET.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.ELDERBERRY_MEDIEVAL_BLANKET.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('D', LOTags.Items.GOLD_METAL_NUGGETS)
                                .pattern("DAD")
                                .pattern("DDD")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.ELDERBERRY_MEDIEVAL_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.ELDERBERRY_MODERN_BLANKET.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('C', LOTags.Items.GOLD_METAL_NUGGETS)
                                .pattern("AAA")
                                .pattern("CC ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.ELDERBERRY_MODERN_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.ELDERBERRY_RACING_BLANKET.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('C', LOTags.Items.GOLD_METAL_NUGGETS)
                                .pattern("AAA")
                                .pattern("C  ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.ELDERBERRY_RACING_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.ELDERBERRY_WESTERN_BLANKET.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('C', LOTags.Items.GOLD_METAL_NUGGETS)
                                .pattern("AAA")
                                .pattern("CCC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.ELDERBERRY_WESTERN_BLANKET.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.PEACH_MEDIEVAL_BLANKET.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.RED_CARPET)
                                .define('C', Items.WHITE_CARPET)
                                .define('D', LOTags.Items.GOLD_METAL_NUGGETS)
                                .pattern("ABB")
                                .pattern("CDC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.PEACH_MEDIEVAL_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.PEACH_MODERN_BLANKET.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.RED_CARPET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("ABB")
                                .pattern("CC ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.PEACH_MODERN_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.PEACH_RACING_BLANKET.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.RED_CARPET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("ABB")
                                .pattern("C  ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.PEACH_RACING_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.PEACH_WESTERN_BLANKET.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.RED_CARPET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("ABB")
                                .pattern("CCC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.PEACH_WESTERN_BLANKET.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.SPRING_MEDIEVAL_BLANKET.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.LIGHT_BLUE_CARPET)
                                .define('C', Items.YELLOW_CARPET)
                                .define('D', LOTags.Items.GOLD_METAL_NUGGETS)
                                .pattern("ABB")
                                .pattern("CDC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.SPRING_MEDIEVAL_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.SPRING_MODERN_BLANKET.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.LIGHT_BLUE_CARPET)
                                .define('C', Items.YELLOW_CARPET)
                                .pattern("ABB")
                                .pattern("CC ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.SPRING_MODERN_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.SPRING_RACING_BLANKET.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.LIGHT_BLUE_CARPET)
                                .define('C', Items.YELLOW_CARPET)
                                .pattern("ABB")
                                .pattern("C  ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.SPRING_RACING_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.SPRING_WESTERN_BLANKET.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.LIGHT_BLUE_CARPET)
                                .define('C', Items.YELLOW_CARPET)
                                .pattern("ABB")
                                .pattern("CCC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.SPRING_WESTERN_BLANKET.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.SUMMER_MEDIEVAL_BLANKET.get())
                                .define('A', Items.ORANGE_CARPET)
                                .define('B', Items.RED_CARPET)
                                .define('C', Items.YELLOW_CARPET)
                                .define('D', LOTags.Items.GOLD_METAL_NUGGETS)
                                .pattern("ABB")
                                .pattern("CDC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.SUMMER_MEDIEVAL_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.SUMMER_MODERN_BLANKET.get())
                                .define('A', Items.ORANGE_CARPET)
                                .define('B', Items.RED_CARPET)
                                .define('C', Items.YELLOW_CARPET)
                                .pattern("ABB")
                                .pattern("CC ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.SUMMER_MODERN_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.SUMMER_RACING_BLANKET.get())
                                .define('A', Items.ORANGE_CARPET)
                                .define('B', Items.RED_CARPET)
                                .define('C', Items.YELLOW_CARPET)
                                .pattern("ABB")
                                .pattern("C  ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.SUMMER_RACING_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.SUMMER_WESTERN_BLANKET.get())
                                .define('A', Items.ORANGE_CARPET)
                                .define('B', Items.RED_CARPET)
                                .define('C', Items.YELLOW_CARPET)
                                .pattern("ABB")
                                .pattern("CCC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.SUMMER_WESTERN_BLANKET.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.WINTER_MEDIEVAL_BLANKET.get())
                                .define('A', Items.LIGHT_BLUE_CARPET)
                                .define('B', Items.WHITE_CARPET)
                                .define('C', Items.BROWN_CARPET)
                                .define('D', LOTags.Items.GOLD_METAL_NUGGETS)
                                .pattern("ABB")
                                .pattern("CDC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.WINTER_MEDIEVAL_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.WINTER_MODERN_BLANKET.get())
                                .define('A', Items.LIGHT_BLUE_CARPET)
                                .define('B', Items.WHITE_CARPET)
                                .define('C', Items.BROWN_CARPET)
                                .pattern("ABB")
                                .pattern("CC ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.WINTER_MODERN_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.WINTER_RACING_BLANKET.get())
                                .define('A', Items.LIGHT_BLUE_CARPET)
                                .define('B', Items.WHITE_CARPET)
                                .define('C', Items.BROWN_CARPET)
                                .pattern("ABB")
                                .pattern("C  ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.WINTER_RACING_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.WINTER_WESTERN_BLANKET.get())
                                .define('A', Items.LIGHT_BLUE_CARPET)
                                .define('B', Items.WHITE_CARPET)
                                .define('C', Items.BROWN_CARPET)
                                .pattern("ABB")
                                .pattern("CCC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.WINTER_WESTERN_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.PRIDE_MEDIEVAL_BLANKET.get())
                                .define('A', Items.RED_CARPET)
                                .define('B', Items.YELLOW_CARPET)
                                .define('C', Items.BLUE_CARPET)
                                .define('D', Items.PURPLE_CARPET)
                                .pattern("ABB")
                                .pattern("CDC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.PRIDE_MEDIEVAL_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.PRIDE_MODERN_BLANKET.get())
                                .define('A', Items.RED_CARPET)
                                .define('B', Items.YELLOW_CARPET)
                                .define('C', Items.BLUE_CARPET)
                                .pattern("ABB")
                                .pattern("CC ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.PRIDE_MODERN_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.PRIDE_RACING_BLANKET.get())
                                .define('A', Items.RED_CARPET)
                                .define('B', Items.YELLOW_CARPET)
                                .define('C', Items.BLUE_CARPET)
                                .pattern("ABB")
                                .pattern("C  ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.PRIDE_RACING_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.PRIDE_WESTERN_BLANKET.get())
                                .define('A', Items.RED_CARPET)
                                .define('B', Items.YELLOW_CARPET)
                                .define('C', Items.BLUE_CARPET)
                                .pattern("ABB")
                                .pattern("CCC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.PRIDE_WESTERN_BLANKET.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LESBIAN_MEDIEVAL_BLANKET.get())
                                .define('A', Items.ORANGE_CARPET)
                                .define('B', Items.WHITE_CARPET)
                                .define('C', Items.PINK_CARPET)
                                .pattern("ABB")
                                .pattern("CCC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.LESBIAN_MEDIEVAL_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LESBIAN_MODERN_BLANKET.get())
                                .define('A', Items.ORANGE_CARPET)
                                .define('B', Items.WHITE_CARPET)
                                .define('C', Items.PINK_CARPET)
                                .pattern("ABB")
                                .pattern("CC ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.LESBIAN_MODERN_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LESBIAN_RACING_BLANKET.get())
                                .define('A', Items.ORANGE_CARPET)
                                .define('B', Items.WHITE_CARPET)
                                .define('C', Items.PINK_CARPET)
                                .pattern("ABB")
                                .pattern("C  ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.LESBIAN_RACING_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LESBIAN_WESTERN_BLANKET.get())
                                .define('A', Items.ORANGE_CARPET)
                                .define('B', Items.WHITE_CARPET)
                                .define('C', Items.PINK_CARPET)
                                .pattern("ABB")
                                .pattern("CCC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.LESBIAN_WESTERN_BLANKET.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.NONBINARY_MEDIEVAL_BLANKET.get())
                                .define('A', Items.YELLOW_CARPET)
                                .define('B', Items.WHITE_CARPET)
                                .define('C', Items.PURPLE_CARPET)
                                .pattern("ABB")
                                .pattern("CCC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.NONBINARY_MEDIEVAL_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.NONBINARY_MODERN_BLANKET.get())
                                .define('A', Items.YELLOW_CARPET)
                                .define('B', Items.WHITE_CARPET)
                                .define('C', Items.PURPLE_CARPET)
                                .pattern("ABB")
                                .pattern("CC ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.NONBINARY_MODERN_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.NONBINARY_RACING_BLANKET.get())
                                .define('A', Items.YELLOW_CARPET)
                                .define('B', Items.WHITE_CARPET)
                                .define('C', Items.PURPLE_CARPET)
                                .pattern("ABB")
                                .pattern("C  ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.NONBINARY_RACING_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.NONBINARY_WESTERN_BLANKET.get())
                                .define('A', Items.YELLOW_CARPET)
                                .define('B', Items.WHITE_CARPET)
                                .define('C', Items.PURPLE_CARPET)
                                .pattern("ABB")
                                .pattern("CCC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.NONBINARY_WESTERN_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.BI_MEDIEVAL_BLANKET.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.PURPLE_CARPET)
                                .define('C', Items.BLUE_CARPET)
                                .pattern("ABB")
                                .pattern("CCC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.BI_MEDIEVAL_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.BI_MODERN_BLANKET.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.PURPLE_CARPET)
                                .define('C', Items.BLUE_CARPET)
                                .pattern("ABB")
                                .pattern("CC ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.BI_MODERN_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.BI_RACING_BLANKET.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.PURPLE_CARPET)
                                .define('C', Items.BLUE_CARPET)
                                .pattern("ABB")
                                .pattern("C  ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.BI_RACING_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.BI_WESTERN_BLANKET.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.PURPLE_CARPET)
                                .define('C', Items.BLUE_CARPET)
                                .pattern("ABB")
                                .pattern("CCC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.BI_WESTERN_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.TRANS_MEDIEVAL_BLANKET.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.WHITE_CARPET)
                                .define('C', Items.BLUE_CARPET)
                                .pattern("ABB")
                                .pattern("CCC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.TRANS_MEDIEVAL_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.TRANS_MODERN_BLANKET.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.WHITE_CARPET)
                                .define('C', Items.BLUE_CARPET)
                                .pattern("ABB")
                                .pattern("CC ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.TRANS_MODERN_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.TRANS_RACING_BLANKET.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.WHITE_CARPET)
                                .define('C', Items.BLUE_CARPET)
                                .pattern("ABB")
                                .pattern("C  ")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.TRANS_RACING_BLANKET.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.TRANS_WESTERN_BLANKET.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.WHITE_CARPET)
                                .define('C', Items.BLUE_CARPET)
                                .pattern("ABB")
                                .pattern("CCC")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, LOItems.TRANS_WESTERN_BLANKET.get().toString()));


//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOBlocks.ACACIA_RABBIT_HUTCH.get(), 4)
//                .define('A', Blocks.ACACIA_LOG)
//                .define('B', Blocks.ACACIA_PLANKS)
//                .define('C', Items.STICK)
//                .pattern("AAA")
//                .pattern("BCB")
//                .pattern("BBB")
//                .unlockedBy("has_log", inventoryTrigger(ItemPredicate.Builder.item()
//                        .of(Blocks.ACACIA_LOG).build()))
//                .save(pFinishedRecipeConsumer);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOBlocks.BAMBOO_RABBIT_HUTCH.get(), 4)
//                .define('A', Blocks.BAMBOO_BLOCK)
//                .define('B', Blocks.BAMBOO_PLANKS)
//                .define('C', Items.STICK)
//                .pattern("AAA")
//                .pattern("BCB")
//                .pattern("BBB")
//                .unlockedBy("has_log", inventoryTrigger(ItemPredicate.Builder.item()
//                        .of(Blocks.BAMBOO_BLOCK).build()))
//                .save(pFinishedRecipeConsumer);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOBlocks.BIRCH_RABBIT_HUTCH.get(), 4)
//                .define('A', Blocks.BIRCH_LOG)
//                .define('B', Blocks.BIRCH_PLANKS)
//                .define('C', Items.STICK)
//                .pattern("AAA")
//                .pattern("BCB")
//                .pattern("BBB")
//                .unlockedBy("has_log", inventoryTrigger(ItemPredicate.Builder.item()
//                        .of(Blocks.BIRCH_LOG).build()))
//                .save(pFinishedRecipeConsumer);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOBlocks.CHERRY_RABBIT_HUTCH.get(), 4)
//                .define('A', Blocks.CHERRY_LOG)
//                .define('B', Blocks.CHERRY_PLANKS)
//                .define('C', Items.STICK)
//                .pattern("AAA")
//                .pattern("BCB")
//                .pattern("BBB")
//                .unlockedBy("has_log", inventoryTrigger(ItemPredicate.Builder.item()
//                        .of(Blocks.CHERRY_LOG).build()))
//                .save(pFinishedRecipeConsumer);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOBlocks.CRIMSON_RABBIT_HUTCH.get(), 4)
//                .define('A', Blocks.CRIMSON_STEM)
//                .define('B', Blocks.CRIMSON_PLANKS)
//                .define('C', Items.STICK)
//                .pattern("AAA")
//                .pattern("BCB")
//                .pattern("BBB")
//                .unlockedBy("has_log", inventoryTrigger(ItemPredicate.Builder.item()
//                        .of(Blocks.CRIMSON_STEM).build()))
//                .save(pFinishedRecipeConsumer);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOBlocks.DARK_OAK_RABBIT_HUTCH.get(), 4)
//                .define('A', Blocks.DARK_OAK_LOG)
//                .define('B', Blocks.DARK_OAK_PLANKS)
//                .define('C', Items.STICK)
//                .pattern("AAA")
//                .pattern("BCB")
//                .pattern("BBB")
//                .unlockedBy("has_log", inventoryTrigger(ItemPredicate.Builder.item()
//                        .of(Blocks.DARK_OAK_LOG).build()))
//                .save(pFinishedRecipeConsumer);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOBlocks.JUNGLE_RABBIT_HUTCH.get(), 4)
//                .define('A', Blocks.JUNGLE_LOG)
//                .define('B', Blocks.JUNGLE_PLANKS)
//                .define('C', Items.STICK)
//                .pattern("AAA")
//                .pattern("BCB")
//                .pattern("BBB")
//                .unlockedBy("has_log", inventoryTrigger(ItemPredicate.Builder.item()
//                        .of(Blocks.JUNGLE_LOG).build()))
//                .save(pFinishedRecipeConsumer);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOBlocks.MANGROVE_RABBIT_HUTCH.get(), 4)
//                .define('A', Blocks.MANGROVE_LOG)
//                .define('B', Blocks.MANGROVE_PLANKS)
//                .define('C', Items.STICK)
//                .pattern("AAA")
//                .pattern("BCB")
//                .pattern("BBB")
//                .unlockedBy("has_log", inventoryTrigger(ItemPredicate.Builder.item()
//                        .of(Blocks.MANGROVE_LOG).build()))
//                .save(pFinishedRecipeConsumer);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOBlocks.OAK_RABBIT_HUTCH.get(), 4)
//                .define('A', Blocks.OAK_LOG)
//                .define('B', Blocks.OAK_PLANKS)
//                .define('C', Items.STICK)
//                .pattern("AAA")
//                .pattern("BCB")
//                .pattern("BBB")
//                .unlockedBy("has_log", inventoryTrigger(ItemPredicate.Builder.item()
//                        .of(Blocks.OAK_LOG).build()))
//                .save(pFinishedRecipeConsumer);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOBlocks.SPRUCE_RABBIT_HUTCH.get(), 4)
//                .define('A', Blocks.SPRUCE_LOG)
//                .define('B', Blocks.SPRUCE_PLANKS)
//                .define('C', Items.STICK)
//                .pattern("AAA")
//                .pattern("BCB")
//                .pattern("BBB")
//                .unlockedBy("has_log", inventoryTrigger(ItemPredicate.Builder.item()
//                        .of(Blocks.SPRUCE_LOG).build()))
//                .save(pFinishedRecipeConsumer);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOBlocks.WARPED_RABBIT_HUTCH.get(), 4)
//                .define('A', Blocks.WARPED_STEM)
//                .define('B', Blocks.WARPED_PLANKS)
//                .define('C', Items.STICK)
//                .pattern("AAA")
//                .pattern("BCB")
//                .pattern("BBB")
//                .unlockedBy("has_log", inventoryTrigger(ItemPredicate.Builder.item()
//                        .of(Blocks.WARPED_STEM).build()))
//                .save(pFinishedRecipeConsumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.SADDLE)
                .define('A', Items.LEATHER)
                .define('B', LOTags.Items.CRAFTING_METAL_NUGGETS)
                .pattern("AAA")
                .pattern("A A")
                .pattern("B B")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.BLACK_SADDLE.get())
                .requires(Items.SADDLE)
                .requires(Items.BLACK_DYE)
                .unlockedBy("has_saddle", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.SADDLE)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.WHITE_SADDLE.get())
                .requires(Items.SADDLE)
                .requires(Items.WHITE_DYE)
                .unlockedBy("has_saddle", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.SADDLE)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LIGHT_SADDLE.get())
                .define('A', Items.LEATHER)
                .define('B', LOTags.Items.CRAFTING_METAL_NUGGETS)
                .pattern("AAA")
                .pattern("B B")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.BLACK_LIGHT_SADDLE.get())
                .requires(LOItems.LIGHT_SADDLE.get())
                .requires(Items.BLACK_DYE)
                .unlockedBy("has_saddle", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.SADDLE)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.WHITE_LIGHT_SADDLE.get())
                .requires(LOItems.LIGHT_SADDLE.get())
                .requires(Items.WHITE_DYE)
                .unlockedBy("has_saddle", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.SADDLE)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.HEAVY_SADDLE.get())
                .define('A', Items.LEATHER)
                .define('B', LOTags.Items.CRAFTING_METAL_NUGGETS)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("B B")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.BLACK_HEAVY_SADDLE.get())
                .requires(LOItems.HEAVY_SADDLE.get())
                .requires(Items.BLACK_DYE)
                .unlockedBy("has_saddle", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.SADDLE)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.WHITE_HEAVY_SADDLE.get())
                .requires(LOItems.HEAVY_SADDLE.get())
                .requires(Items.WHITE_DYE)
                .unlockedBy("has_saddle", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.SADDLE)
                        .build()))
                .save(pFinishedRecipeConsumer);



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
                .define('C', LOTags.Items.GOLD_METALS)
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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.EMERALD_HORSE_ARMOR.get())
                .define('A', Items.LEATHER)
                .define('B', ItemTags.WOOL_CARPETS)
                .define('C', Items.EMERALD)
                .pattern("  C")
                .pattern("CCC")
                .pattern("ABA")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.QUARTZ_HORSE_ARMOR.get())
                .define('A', Items.LEATHER)
                .define('B', ItemTags.WOOL_CARPETS)
                .define('C', Items.QUARTZ)
                .pattern("  C")
                .pattern("CCC")
                .pattern("ABA")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.COPPER_HORSE_ARMOR.get())
                .define('A', Items.LEATHER)
                .define('B', ItemTags.WOOL_CARPETS)
                .define('C', Items.COPPER_INGOT)
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
                .define('A', LOTags.Items.CRAFTING_METALS)
                .define('B', LOTags.Items.CRAFTING_METAL_NUGGETS)
                .pattern("A A")
                .pattern("A A")
                .pattern("BAB")
                .unlockedBy("has_iron", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.CRAFTING_METALS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.TAIL_SCISSORS.get())
                .define('A', LOTags.Items.CRAFTING_METALS)
                .define('B', LOTags.Items.CRAFTING_METAL_NUGGETS)
                .pattern("A A")
                .pattern("A A")
                .pattern("ABA")
                .unlockedBy("has_iron", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.CRAFTING_METALS).build()))
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

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.HORSE.get()), RecipeCategory.MISC, LOItems.COOKED_HORSE.get(), 0.35F, 100)
                .unlockedBy("has_horse", has(LOItems.HORSE.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_horse_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.HORSE.get()), RecipeCategory.MISC, LOItems.COOKED_HORSE.get(), 0.35F, 200)
                .unlockedBy("has_horse", has(LOItems.HORSE.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_horse_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.HORSE.get()), RecipeCategory.MISC, LOItems.COOKED_HORSE.get(), 0.35F, 600)
                .unlockedBy("has_horse", has(LOItems.HORSE.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_horse_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.LLAMA.get()), RecipeCategory.MISC, LOItems.COOKED_LLAMA.get(), 0.35F, 100)
                .unlockedBy("has_llama", has(LOItems.LLAMA.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_llama_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.LLAMA.get()), RecipeCategory.MISC, LOItems.COOKED_LLAMA.get(), 0.35F, 200)
                .unlockedBy("has_llama", has(LOItems.LLAMA.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_llama_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.LLAMA.get()), RecipeCategory.MISC, LOItems.COOKED_LLAMA.get(), 0.35F, 600)
                .unlockedBy("has_llama", has(LOItems.LLAMA.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_llama_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.UNICORN.get()), RecipeCategory.MISC, LOItems.COOKED_UNICORN.get(), 0.35F, 100)
                .unlockedBy("has_unicorn", has(LOItems.UNICORN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_unicorn_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.UNICORN.get()), RecipeCategory.MISC, LOItems.COOKED_UNICORN.get(), 0.35F, 200)
                .unlockedBy("has_unicorn", has(LOItems.UNICORN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_unicorn_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.UNICORN.get()), RecipeCategory.MISC, LOItems.COOKED_UNICORN.get(), 0.35F, 600)
                .unlockedBy("has_unicorn", has(LOItems.UNICORN.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_unicorn_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.CAMEL.get()), RecipeCategory.MISC, LOItems.COOKED_CAMEL.get(), 0.35F, 100)
                .unlockedBy("has_camel", has(LOItems.CAMEL.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_camel_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.CAMEL.get()), RecipeCategory.MISC, LOItems.COOKED_CAMEL.get(), 0.35F, 200)
                .unlockedBy("has_camel", has(LOItems.CAMEL.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_camel_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.CAMEL.get()), RecipeCategory.MISC, LOItems.COOKED_CAMEL.get(), 0.35F, 600)
                .unlockedBy("has_camel", has(LOItems.CAMEL.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_camel_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.CHEVON.get()), RecipeCategory.MISC, LOItems.COOKED_CHEVON.get(), 0.35F, 100)
                .unlockedBy("has_chevon", has(LOItems.CHEVON.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_chevon_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.CHEVON.get()), RecipeCategory.MISC, LOItems.COOKED_CHEVON.get(), 0.35F, 200)
                .unlockedBy("has_chevon", has(LOItems.CHEVON.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_chevon_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.CHEVON.get()), RecipeCategory.MISC, LOItems.COOKED_CHEVON.get(), 0.35F, 600)
                .unlockedBy("has_chevon", has(LOItems.CHEVON.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_chevon_campfire_cooking"));

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

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(LOItems.CARIBOU.get()), RecipeCategory.MISC, LOItems.COOKED_CARIBOU.get(), 0.35F, 100)
                .unlockedBy("has_caribou", has(LOItems.CARIBOU.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_caribou_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LOItems.CARIBOU.get()), RecipeCategory.MISC, LOItems.COOKED_CARIBOU.get(), 0.35F, 200)
                .unlockedBy("has_caribou", has(LOItems.CARIBOU.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_caribou_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(LOItems.CARIBOU.get()), RecipeCategory.MISC, LOItems.COOKED_CARIBOU.get(), 0.35F, 600)
                .unlockedBy("has_caribou", has(LOItems.CARIBOU.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnlivestock", "cooked_caribou_campfire_cooking"));
        }

    public void buildDyeRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.LEATHER_RUMP_STRAPS.get())
                                .define('A', Items.LEATHER)
                                .define('B', LOTags.Items.CRAFTING_METAL_NUGGETS)
                                .pattern("BAB")
                                .pattern("A A")
                                .pattern("A A")
                                .unlockedBy("has_iron", has(LOTags.Items.CRAFTING_METAL_NUGGETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.LEATHER_RUMP_STRAPS.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MECompatItems.BLACK_RUMP_STRAPS.get())
                                .requires(MECompatItems.LEATHER_RUMP_STRAPS.get())
                                .requires(Items.BLACK_DYE)
                                .unlockedBy("has_rump_straps", has(MECompatItems.LEATHER_RUMP_STRAPS.get()))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_RUMP_STRAPS.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MECompatItems.WHITE_RUMP_STRAPS.get())
                                .requires(MECompatItems.LEATHER_RUMP_STRAPS.get())
                                .requires(Items.WHITE_DYE)
                                .unlockedBy("has_rump_straps", has(MECompatItems.LEATHER_RUMP_STRAPS.get()))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_RUMP_STRAPS.get().toString()));


        for (DyeColor color : DyeColor.values()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.MODERN_BLANKETS.get(color).get())
                    .define('A', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_carpet")))
                    .pattern("AAA")
                    .pattern("AA ")
                    .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                            .of(ItemTags.WOOL_CARPETS).build()))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, color.getName() + "_modern_blanket"));

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.RACING_BLANKETS.get(color).get())
                    .define('A', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_carpet")))
                    .pattern("AAA")
                    .pattern("A  ")
                    .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                            .of(ItemTags.WOOL_CARPETS).build()))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, color.getName() + "_racing_blanket"));

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.WESTERN_BLANKETS.get(color).get())
                    .define('A', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_carpet")))
                    .pattern("AAA")
                    .pattern("AAA")
                    .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                            .of(ItemTags.WOOL_CARPETS).build()))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, color.getName() + "_western_blanket"));

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.MEDIEVAL_BLANKETS.get(color).get())
                    .define('A', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_carpet")))
                    .define('B', LOTags.Items.GOLD_METAL_NUGGETS)
                    .pattern("AAA")
                    .pattern("BAB")
                    .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                            .of(ItemTags.WOOL_CARPETS).build()))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, color.getName() + "_medieval_blanket"));

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_wool")), 8)
                    .define('A', LOItems.WOOL_DYE.get(color).get())
                    .define('B', ItemTags.WOOL)
                    .pattern("BBB")
                    .pattern("BAB")
                    .pattern("BBB")
                    .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                            .of(ItemTags.WOOL).build()))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, color.getName() + "_wool_from_wool_dye_8"));

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_wool")), 2)
                    .requires(LOTags.Items.WOOL_STAPLES)
                    .requires(LOTags.Items.SPINDLE)
                    .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                            .of(LOTags.Items.WOOL_STAPLES)
                            .build()))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID,color.getName() + "_wool_from_staple"));

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.WOOL_STAPLES.get(color).get())
                    .requires(LOTags.Items.WOOL_STAPLES)
                    .requires(LOItems.WOOL_DYE.get(color).get())
                    .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                            .of(LOTags.Items.WOOL_STAPLES)
                            .build()))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, color.getName() + "_wool_staple"));

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.WOOL_STAPLES.get(color).get(), 8)
                    .define('A', LOItems.WOOL_DYE.get(color).get())
                    .define('B', LOTags.Items.WOOL_STAPLES)
                    .pattern("BBB")
                    .pattern("BAB")
                    .pattern("BBB")
                    .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                            .of(LOTags.Items.WOOL_STAPLES).build()))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, color.getName() + "_wool_staple_8"));

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.WOOL_DYE.get(color).get(), 8)
                    .requires(DyeItem.byColor(color))
                    .requires(DyeItem.byColor(color))
                    .unlockedBy("has_dye", inventoryTrigger(ItemPredicate.Builder.item()
                            .of(Tags.Items.DYES)
                            .build()))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, color.getName() + "_wool_dye"));

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.BRAND_TAGS.get(color).get(), 12)
                    .requires(LOTags.Items.STRING)
                    .requires(Items.PAPER)
                    .requires(DyeItem.byColor(color))
                    .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                            .of(Items.PAPER)
                            .build()))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, color.getName() + "_brand_tag"));

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.GRUB_SWEATERS.get(color).get())
                    .define('B', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_carpet")))
                    .pattern("BB")
                    .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                            .of(ItemTags.WOOL_CARPETS).build()))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, color.getName() + "_grub_sweater"));

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.ACCENTED_SADDLES.get(color).get())
                    .define('A', Items.SADDLE)
                    .define('B', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_wool")))
                    .pattern("AB")
                    .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                            .of(ItemTags.WOOL).build()))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, color.getName() + "_accented_saddle"));

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.ACCENTED_LIGHT_SADDLES.get(color).get())
                    .define('A', LOItems.LIGHT_SADDLE.get())
                    .define('B', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_wool")))
                    .pattern("AB")
                    .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                            .of(ItemTags.WOOL).build()))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, color.getName() + "_accented_light_saddle"));

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.ACCENTED_HEAVY_SADDLES.get(color).get())
                    .define('A', LOItems.HEAVY_SADDLE.get())
                    .define('B', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_wool")))
                    .pattern("AB")
                    .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                            .of(ItemTags.WOOL).build()))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, color.getName() + "_accented_heavy_saddle"));


            ConditionalRecipe.builder()
                    .addCondition(new ModLoadedCondition("medievalembroidery"))
                    .addRecipe(
                            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.SOLID_CAPARISON_CAPES.get(color).get())
                                    .define('A', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_carpet")))
                                    .define('B', LOTags.Items.GOLD_METAL_NUGGETS)
                                    .pattern("AA")
                                    .pattern("BA")
                                    .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))::save).build
                    (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", color.getName() + "_caparison_cape"));

            ConditionalRecipe.builder()
                    .addCondition(new ModLoadedCondition("medievalembroidery"))
                    .addRecipe(
                            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.SOLID_CAPARISON_FULLS.get(color).get())
                                    .define('A', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_carpet")))
                                    .define('B', LOTags.Items.GOLD_METAL_NUGGETS)
                                    .pattern("  A")
                                    .pattern("AAA")
                                    .pattern("BAB")
                                    .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))::save).build
                            (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", color.getName() + "_caparison_full"));

            ConditionalRecipe.builder()
                    .addCondition(new ModLoadedCondition("medievalembroidery"))
                    .addRecipe(
                            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.SOLID_CAPARISON_HALFS.get(color).get())
                                    .define('A', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_carpet")))
                                    .define('B', LOTags.Items.GOLD_METAL_NUGGETS)
                                    .pattern("AA ")
                                    .pattern("BAB")
                                    .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))::save).build
                            (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", color.getName() + "_caparison_half"));

            ConditionalRecipe.builder()
                    .addCondition(new ModLoadedCondition("medievalembroidery"))
                    .addRecipe(
                            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.SOLID_CAPARISON_SHOULDERS.get(color).get())
                                    .define('A', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_carpet")))
                                    .define('B', LOTags.Items.GOLD_METAL_NUGGETS)
                                    .pattern(" A")
                                    .pattern("AA")
                                    .pattern("AB")
                                    .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))::save).build
                            (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", color.getName() + "_caparison_shoulder"));
        }

        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.BLACK)
                continue;
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.ACCENTED_BLACK_SADDLES.get(color).get())
                    .define('A', LOItems.BLACK_SADDLE.get())
                    .define('B', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_wool")))
                    .pattern("AB")
                    .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                            .of(ItemTags.WOOL).build()))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, color.getName() + "_accented_black_saddle"));

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.ACCENTED_BLACK_LIGHT_SADDLES.get(color).get())
                    .define('A', LOItems.BLACK_LIGHT_SADDLE.get())
                    .define('B', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_wool")))
                    .pattern("AB")
                    .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                            .of(ItemTags.WOOL).build()))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, color.getName() + "_accented_black_light_saddle"));

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.ACCENTED_BLACK_HEAVY_SADDLES.get(color).get())
                    .define('A', LOItems.BLACK_HEAVY_SADDLE.get())
                    .define('B', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_wool")))
                    .pattern("AB")
                    .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                            .of(ItemTags.WOOL).build()))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, color.getName() + "_accented_black_heavy_saddle"));


            ConditionalRecipe.builder()
                    .addCondition(new ModLoadedCondition("medievalembroidery"))
                    .addRecipe(
                            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_CHECKER_CAPARISON_CAPES.get(color).get())
                                    .define('A', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_carpet")))
                                    .define('B', LOTags.Items.GOLD_METAL_NUGGETS)
                                    .define('C', Items.BLACK_CARPET)
                                    .pattern("CA")
                                    .pattern("BA")
                                    .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))::save).build
                            (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", "black_" + color.getName() + "_caparison_cape"));

            ConditionalRecipe.builder()
                    .addCondition(new ModLoadedCondition("medievalembroidery"))
                    .addRecipe(
                            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_CHECKER_CAPARISON_FULLS.get(color).get())
                                    .define('A', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_carpet")))
                                    .define('B', LOTags.Items.GOLD_METAL_NUGGETS)
                                    .define('C', Items.BLACK_CARPET)
                                    .pattern("  C")
                                    .pattern("ACA")
                                    .pattern("BAB")
                                    .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))::save).build
                            (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", "black_" + color.getName() + "_caparison_full"));

            ConditionalRecipe.builder()
                    .addCondition(new ModLoadedCondition("medievalembroidery"))
                    .addRecipe(
                            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_CHECKER_CAPARISON_HALFS.get(color).get())
                                    .define('A', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_carpet")))
                                    .define('B', LOTags.Items.GOLD_METAL_NUGGETS)
                                    .define('C', Items.BLACK_CARPET)
                                    .pattern("AC ")
                                    .pattern("BAB")
                                    .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))::save).build
                            (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", "black_" + color.getName() + "_caparison_half"));

            ConditionalRecipe.builder()
                    .addCondition(new ModLoadedCondition("medievalembroidery"))
                    .addRecipe(
                            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_CHECKER_CAPARISON_SHOULDERS.get(color).get())
                                    .define('A', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_carpet")))
                                    .define('B', LOTags.Items.GOLD_METAL_NUGGETS)
                                    .define('C', Items.BLACK_CARPET)
                                    .pattern(" C")
                                    .pattern("CA")
                                    .pattern("AB")
                                    .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))::save).build
                            (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", "black_" + color.getName() + "_caparison_shoulder"));
        }

        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.WHITE)
                continue;
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.ACCENTED_WHITE_SADDLES.get(color).get())
                    .define('A', LOItems.WHITE_SADDLE.get())
                    .define('B', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_wool")))
                    .pattern("AB")
                    .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                            .of(ItemTags.WOOL).build()))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, color.getName() + "_accented_white_saddle"));

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.ACCENTED_WHITE_LIGHT_SADDLES.get(color).get())
                    .define('A', LOItems.WHITE_LIGHT_SADDLE.get())
                    .define('B', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_wool")))
                    .pattern("AB")
                    .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                            .of(ItemTags.WOOL).build()))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, color.getName() + "_accented_white_light_saddle"));

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.ACCENTED_WHITE_HEAVY_SADDLES.get(color).get())
                    .define('A', LOItems.WHITE_HEAVY_SADDLE.get())
                    .define('B', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_wool")))
                    .pattern("AB")
                    .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                            .of(ItemTags.WOOL).build()))
                    .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, color.getName() + "_accented_white_heavy_saddle"));


            ConditionalRecipe.builder()
                    .addCondition(new ModLoadedCondition("medievalembroidery"))
                    .addRecipe(
                            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_CHECKER_CAPARISON_CAPES.get(color).get())
                                    .define('A', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_carpet")))
                                    .define('B', LOTags.Items.GOLD_METAL_NUGGETS)
                                    .define('C', Items.WHITE_CARPET)
                                    .pattern("CA")
                                    .pattern("BA")
                                    .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))::save).build
                            (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", "white_" + color.getName() + "_caparison_cape"));

            ConditionalRecipe.builder()
                    .addCondition(new ModLoadedCondition("medievalembroidery"))
                    .addRecipe(
                            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_CHECKER_CAPARISON_FULLS.get(color).get())
                                    .define('A', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_carpet")))
                                    .define('B', LOTags.Items.GOLD_METAL_NUGGETS)
                                    .define('C', Items.WHITE_CARPET)
                                    .pattern("  C")
                                    .pattern("ACA")
                                    .pattern("BAB")
                                    .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))::save).build
                            (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", "white_" + color.getName() + "_caparison_full"));

            ConditionalRecipe.builder()
                    .addCondition(new ModLoadedCondition("medievalembroidery"))
                    .addRecipe(
                            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_CHECKER_CAPARISON_HALFS.get(color).get())
                                    .define('A', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_carpet")))
                                    .define('B', LOTags.Items.GOLD_METAL_NUGGETS)
                                    .define('C', Items.WHITE_CARPET)
                                    .pattern("AC ")
                                    .pattern("BAB")
                                    .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))::save).build
                            (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", "white_" + color.getName() + "_caparison_half"));

            ConditionalRecipe.builder()
                    .addCondition(new ModLoadedCondition("medievalembroidery"))
                    .addRecipe(
                            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_CHECKER_CAPARISON_SHOULDERS.get(color).get())
                                    .define('A', BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", color.getName() + "_carpet")))
                                    .define('B', LOTags.Items.GOLD_METAL_NUGGETS)
                                    .define('C', Items.WHITE_CARPET)
                                    .pattern(" C")
                                    .pattern("CA")
                                    .pattern("AB")
                                    .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))::save).build
                            (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", "white_" + color.getName() + "_caparison_shoulder"));

        }
    }
}