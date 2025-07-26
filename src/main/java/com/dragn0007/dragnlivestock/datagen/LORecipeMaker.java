package com.dragn0007.dragnlivestock.datagen;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.compat.medievalembroidery.MECompatItems;
import com.dragn0007.dragnlivestock.datagen.conditions.BlanketConfigCondition;
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
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;

import java.util.function.Consumer;

public class LORecipeMaker extends RecipeProvider implements IConditionBuilder {
    public LORecipeMaker(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.MOUNT_KEY.get())
                .define('A', Items.GOLD_NUGGET)
                .pattern(" A")
                .pattern("A ")
                .unlockedBy("has_gold", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.GOLD_NUGGET).build()))
                .save(pFinishedRecipeConsumer);



        ConditionalRecipe.builder()
                .addCondition(new BlanketConfigCondition(new ResourceLocation(LivestockOverhaul.MODID, "blanket_config_condition")))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.AMERICAN_MEDIEVAL_BLANKET.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('B', Items.RED_CARPET)
                                .define('C', Items.WHITE_CARPET)
                                .define('D', Items.GOLD_NUGGET)
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
                                .define('D', Items.GOLD_NUGGET)
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
                                .define('D', Items.GOLD_NUGGET)
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
                                .define('C', Items.GOLD_NUGGET)
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
                                .define('C', Items.GOLD_NUGGET)
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
                                .define('C', Items.GOLD_NUGGET)
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
                                .define('D', Items.GOLD_NUGGET)
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
                                .define('D', Items.GOLD_NUGGET)
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
                                .define('D', Items.GOLD_NUGGET)
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
                                .define('D', Items.GOLD_NUGGET)
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
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.LEATHER_RUMP_STRAPS.get())
                                .define('A', Items.LEATHER)
                                .define('B', Items.IRON_NUGGET)
                                .pattern("BAB")
                                .pattern("A A")
                                .pattern("A A")
                                .unlockedBy("has_iron", has(Items.IRON_NUGGET))
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


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_CAPARISON_CAPE.get())
                            .define('A', Items.BLACK_CARPET)
                            .define('B', Items.GOLD_NUGGET)
                            .pattern("AA")
                            .pattern("BA")
                            .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                            ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_CAPARISON_HALF.get())
                                .define('A', Items.BLACK_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_CAPARISON_FULL.get())
                                .define('A', Items.BLACK_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("  A")
                                .pattern("AAA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_CAPARISON_SHOULDER.get())
                                .define('A', Items.BLACK_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern(" A")
                                .pattern("AA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_BLACK_CAPARISON_CAPE.get())
                                .define('A', Items.BLACK_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_BLACK_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_BLACK_CAPARISON_HALF.get())
                                .define('A', Items.BLACK_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_BLACK_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_BLACK_CAPARISON_FULL.get())
                                .define('A', Items.BLACK_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_BLACK_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_BLACK_CAPARISON_SHOULDER.get())
                                .define('A', Items.BLACK_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_BLACK_CAPARISON_SHOULDER.get().toString()));



        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLUE_CAPARISON_CAPE.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLUE_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLUE_CAPARISON_HALF.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLUE_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLUE_CAPARISON_FULL.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("  A")
                                .pattern("AAA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLUE_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLUE_CAPARISON_SHOULDER.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern(" A")
                                .pattern("AA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLUE_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_BLUE_CAPARISON_CAPE.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_BLUE_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_BLUE_CAPARISON_HALF.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_BLUE_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_BLUE_CAPARISON_FULL.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_BLUE_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_BLUE_CAPARISON_SHOULDER.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_BLUE_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_BLUE_CAPARISON_CAPE.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_BLUE_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_BLUE_CAPARISON_HALF.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_BLUE_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_BLUE_CAPARISON_FULL.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_BLUE_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_BLUE_CAPARISON_SHOULDER.get())
                                .define('A', Items.BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_BLUE_CAPARISON_SHOULDER.get().toString()));



        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BROWN_CAPARISON_CAPE.get())
                                .define('A', Items.BROWN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BROWN_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BROWN_CAPARISON_HALF.get())
                                .define('A', Items.BROWN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BROWN_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BROWN_CAPARISON_FULL.get())
                                .define('A', Items.BROWN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("  A")
                                .pattern("AAA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BROWN_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BROWN_CAPARISON_SHOULDER.get())
                                .define('A', Items.BROWN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern(" A")
                                .pattern("AA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BROWN_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_BROWN_CAPARISON_CAPE.get())
                                .define('A', Items.BROWN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_BROWN_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_BROWN_CAPARISON_HALF.get())
                                .define('A', Items.BROWN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_BROWN_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_BROWN_CAPARISON_FULL.get())
                                .define('A', Items.BROWN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_BROWN_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_BROWN_CAPARISON_SHOULDER.get())
                                .define('A', Items.BROWN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_BROWN_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_BROWN_CAPARISON_CAPE.get())
                                .define('A', Items.BROWN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_BROWN_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_BROWN_CAPARISON_HALF.get())
                                .define('A', Items.BROWN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_BROWN_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_BROWN_CAPARISON_FULL.get())
                                .define('A', Items.BROWN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_BROWN_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_BROWN_CAPARISON_SHOULDER.get())
                                .define('A', Items.BROWN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_BROWN_CAPARISON_SHOULDER.get().toString()));



        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.CYAN_CAPARISON_CAPE.get())
                                .define('A', Items.CYAN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.CYAN_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.CYAN_CAPARISON_HALF.get())
                                .define('A', Items.CYAN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.CYAN_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.CYAN_CAPARISON_FULL.get())
                                .define('A', Items.CYAN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("  A")
                                .pattern("AAA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.CYAN_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.CYAN_CAPARISON_SHOULDER.get())
                                .define('A', Items.CYAN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern(" A")
                                .pattern("AA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.CYAN_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_CYAN_CAPARISON_CAPE.get())
                                .define('A', Items.CYAN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_CYAN_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_CYAN_CAPARISON_HALF.get())
                                .define('A', Items.CYAN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_CYAN_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_CYAN_CAPARISON_FULL.get())
                                .define('A', Items.CYAN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_CYAN_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_CYAN_CAPARISON_SHOULDER.get())
                                .define('A', Items.CYAN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_CYAN_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_CYAN_CAPARISON_CAPE.get())
                                .define('A', Items.CYAN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_CYAN_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_CYAN_CAPARISON_HALF.get())
                                .define('A', Items.CYAN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_CYAN_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_CYAN_CAPARISON_FULL.get())
                                .define('A', Items.CYAN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_CYAN_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_CYAN_CAPARISON_SHOULDER.get())
                                .define('A', Items.CYAN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_CYAN_CAPARISON_SHOULDER.get().toString()));



        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.GREEN_CAPARISON_CAPE.get())
                                .define('A', Items.GREEN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.GREEN_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.GREEN_CAPARISON_HALF.get())
                                .define('A', Items.GREEN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.GREEN_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.GREEN_CAPARISON_FULL.get())
                                .define('A', Items.GREEN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("  A")
                                .pattern("AAA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.GREEN_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.GREEN_CAPARISON_SHOULDER.get())
                                .define('A', Items.GREEN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern(" A")
                                .pattern("AA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.GREEN_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_GREEN_CAPARISON_CAPE.get())
                                .define('A', Items.GREEN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_GREEN_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_GREEN_CAPARISON_HALF.get())
                                .define('A', Items.GREEN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_GREEN_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_GREEN_CAPARISON_FULL.get())
                                .define('A', Items.GREEN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_GREEN_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_GREEN_CAPARISON_SHOULDER.get())
                                .define('A', Items.GREEN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_GREEN_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_GREEN_CAPARISON_CAPE.get())
                                .define('A', Items.GREEN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_GREEN_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_GREEN_CAPARISON_HALF.get())
                                .define('A', Items.GREEN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_GREEN_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_GREEN_CAPARISON_FULL.get())
                                .define('A', Items.GREEN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_GREEN_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_GREEN_CAPARISON_SHOULDER.get())
                                .define('A', Items.GREEN_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_GREEN_CAPARISON_SHOULDER.get().toString()));



        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.GREY_CAPARISON_CAPE.get())
                                .define('A', Items.GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.GREY_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.GREY_CAPARISON_HALF.get())
                                .define('A', Items.GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.GREY_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.GREY_CAPARISON_FULL.get())
                                .define('A', Items.GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("  A")
                                .pattern("AAA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.GREY_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.GREY_CAPARISON_SHOULDER.get())
                                .define('A', Items.GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern(" A")
                                .pattern("AA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.GREY_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_GREY_CAPARISON_CAPE.get())
                                .define('A', Items.GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_GREY_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_GREY_CAPARISON_HALF.get())
                                .define('A', Items.GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_GREY_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_GREY_CAPARISON_FULL.get())
                                .define('A', Items.GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_GREY_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_GREY_CAPARISON_SHOULDER.get())
                                .define('A', Items.GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_GREY_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_GREY_CAPARISON_CAPE.get())
                                .define('A', Items.GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_GREY_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_GREY_CAPARISON_HALF.get())
                                .define('A', Items.GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_GREY_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_GREY_CAPARISON_FULL.get())
                                .define('A', Items.GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_GREY_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_GREY_CAPARISON_SHOULDER.get())
                                .define('A', Items.GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_GREY_CAPARISON_SHOULDER.get().toString()));



        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.LIGHT_BLUE_CAPARISON_CAPE.get())
                                .define('A', Items.LIGHT_BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.LIGHT_BLUE_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.LIGHT_BLUE_CAPARISON_HALF.get())
                                .define('A', Items.LIGHT_BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.LIGHT_BLUE_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.LIGHT_BLUE_CAPARISON_FULL.get())
                                .define('A', Items.LIGHT_BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("  A")
                                .pattern("AAA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.LIGHT_BLUE_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.LIGHT_BLUE_CAPARISON_SHOULDER.get())
                                .define('A', Items.LIGHT_BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern(" A")
                                .pattern("AA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.LIGHT_BLUE_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_LIGHT_BLUE_CAPARISON_CAPE.get())
                                .define('A', Items.LIGHT_BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_LIGHT_BLUE_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_LIGHT_BLUE_CAPARISON_HALF.get())
                                .define('A', Items.LIGHT_BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_LIGHT_BLUE_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_LIGHT_BLUE_CAPARISON_FULL.get())
                                .define('A', Items.LIGHT_BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_LIGHT_BLUE_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_LIGHT_BLUE_CAPARISON_SHOULDER.get())
                                .define('A', Items.LIGHT_BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_LIGHT_BLUE_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_LIGHT_BLUE_CAPARISON_CAPE.get())
                                .define('A', Items.LIGHT_BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_LIGHT_BLUE_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_LIGHT_BLUE_CAPARISON_HALF.get())
                                .define('A', Items.LIGHT_BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_LIGHT_BLUE_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_LIGHT_BLUE_CAPARISON_FULL.get())
                                .define('A', Items.LIGHT_BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_LIGHT_BLUE_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_LIGHT_BLUE_CAPARISON_SHOULDER.get())
                                .define('A', Items.LIGHT_BLUE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_LIGHT_BLUE_CAPARISON_SHOULDER.get().toString()));



        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.LIGHT_GREY_CAPARISON_CAPE.get())
                                .define('A', Items.LIGHT_GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.LIGHT_GREY_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.LIGHT_GREY_CAPARISON_HALF.get())
                                .define('A', Items.LIGHT_GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.LIGHT_GREY_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.LIGHT_GREY_CAPARISON_FULL.get())
                                .define('A', Items.LIGHT_GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("  A")
                                .pattern("AAA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.LIGHT_GREY_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.LIGHT_GREY_CAPARISON_SHOULDER.get())
                                .define('A', Items.LIGHT_GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern(" A")
                                .pattern("AA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.LIGHT_GREY_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_LIGHT_GREY_CAPARISON_CAPE.get())
                                .define('A', Items.LIGHT_GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_LIGHT_GREY_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_LIGHT_GREY_CAPARISON_HALF.get())
                                .define('A', Items.LIGHT_GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_LIGHT_GREY_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_LIGHT_GREY_CAPARISON_FULL.get())
                                .define('A', Items.LIGHT_GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_LIGHT_GREY_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_LIGHT_GREY_CAPARISON_SHOULDER.get())
                                .define('A', Items.LIGHT_GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_LIGHT_GREY_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_LIGHT_GREY_CAPARISON_CAPE.get())
                                .define('A', Items.LIGHT_GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_LIGHT_GREY_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_LIGHT_GREY_CAPARISON_HALF.get())
                                .define('A', Items.LIGHT_GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_LIGHT_GREY_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_LIGHT_GREY_CAPARISON_FULL.get())
                                .define('A', Items.LIGHT_GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_LIGHT_GREY_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_LIGHT_GREY_CAPARISON_SHOULDER.get())
                                .define('A', Items.LIGHT_GRAY_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_LIGHT_GREY_CAPARISON_SHOULDER.get().toString()));



        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.LIME_CAPARISON_CAPE.get())
                                .define('A', Items.LIME_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.LIME_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.LIME_CAPARISON_HALF.get())
                                .define('A', Items.LIME_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.LIME_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.LIME_CAPARISON_FULL.get())
                                .define('A', Items.LIME_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("  A")
                                .pattern("AAA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.LIME_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.LIME_CAPARISON_SHOULDER.get())
                                .define('A', Items.LIME_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern(" A")
                                .pattern("AA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.LIME_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_LIME_CAPARISON_CAPE.get())
                                .define('A', Items.LIME_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_LIME_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_LIME_CAPARISON_HALF.get())
                                .define('A', Items.LIME_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_LIME_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_LIME_CAPARISON_FULL.get())
                                .define('A', Items.LIME_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_LIME_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_LIME_CAPARISON_SHOULDER.get())
                                .define('A', Items.LIME_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_LIME_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_LIME_CAPARISON_CAPE.get())
                                .define('A', Items.LIME_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_LIME_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_LIME_CAPARISON_HALF.get())
                                .define('A', Items.LIME_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_LIME_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_LIME_CAPARISON_FULL.get())
                                .define('A', Items.LIME_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_LIME_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_LIME_CAPARISON_SHOULDER.get())
                                .define('A', Items.LIME_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_LIME_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.MAGENTA_CAPARISON_CAPE.get())
                                .define('A', Items.MAGENTA_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.MAGENTA_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.MAGENTA_CAPARISON_HALF.get())
                                .define('A', Items.MAGENTA_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.MAGENTA_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.MAGENTA_CAPARISON_FULL.get())
                                .define('A', Items.MAGENTA_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("  A")
                                .pattern("AAA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.MAGENTA_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.MAGENTA_CAPARISON_SHOULDER.get())
                                .define('A', Items.MAGENTA_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern(" A")
                                .pattern("AA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.MAGENTA_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_MAGENTA_CAPARISON_CAPE.get())
                                .define('A', Items.MAGENTA_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_MAGENTA_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_MAGENTA_CAPARISON_HALF.get())
                                .define('A', Items.MAGENTA_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_MAGENTA_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_MAGENTA_CAPARISON_FULL.get())
                                .define('A', Items.MAGENTA_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_MAGENTA_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_MAGENTA_CAPARISON_SHOULDER.get())
                                .define('A', Items.MAGENTA_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_MAGENTA_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_MAGENTA_CAPARISON_CAPE.get())
                                .define('A', Items.MAGENTA_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_MAGENTA_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_MAGENTA_CAPARISON_HALF.get())
                                .define('A', Items.MAGENTA_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_MAGENTA_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_MAGENTA_CAPARISON_FULL.get())
                                .define('A', Items.MAGENTA_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_MAGENTA_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_MAGENTA_CAPARISON_SHOULDER.get())
                                .define('A', Items.MAGENTA_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_MAGENTA_CAPARISON_SHOULDER.get().toString()));



        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.ORANGE_CAPARISON_CAPE.get())
                                .define('A', Items.ORANGE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.ORANGE_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.ORANGE_CAPARISON_HALF.get())
                                .define('A', Items.ORANGE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.ORANGE_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.ORANGE_CAPARISON_FULL.get())
                                .define('A', Items.ORANGE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("  A")
                                .pattern("AAA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.ORANGE_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.ORANGE_CAPARISON_SHOULDER.get())
                                .define('A', Items.ORANGE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern(" A")
                                .pattern("AA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.ORANGE_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_ORANGE_CAPARISON_CAPE.get())
                                .define('A', Items.ORANGE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_ORANGE_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_ORANGE_CAPARISON_HALF.get())
                                .define('A', Items.ORANGE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_ORANGE_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_ORANGE_CAPARISON_FULL.get())
                                .define('A', Items.ORANGE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_ORANGE_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_ORANGE_CAPARISON_SHOULDER.get())
                                .define('A', Items.ORANGE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_ORANGE_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_ORANGE_CAPARISON_CAPE.get())
                                .define('A', Items.ORANGE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_ORANGE_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_ORANGE_CAPARISON_HALF.get())
                                .define('A', Items.ORANGE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_ORANGE_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_ORANGE_CAPARISON_FULL.get())
                                .define('A', Items.ORANGE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_ORANGE_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_ORANGE_CAPARISON_SHOULDER.get())
                                .define('A', Items.ORANGE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_ORANGE_CAPARISON_SHOULDER.get().toString()));



        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.PINK_CAPARISON_CAPE.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.PINK_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.PINK_CAPARISON_HALF.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.PINK_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.PINK_CAPARISON_FULL.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("  A")
                                .pattern("AAA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.PINK_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.PINK_CAPARISON_SHOULDER.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern(" A")
                                .pattern("AA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.PINK_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_PINK_CAPARISON_CAPE.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_PINK_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_PINK_CAPARISON_HALF.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_PINK_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_PINK_CAPARISON_FULL.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_PINK_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_PINK_CAPARISON_SHOULDER.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_PINK_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_PINK_CAPARISON_CAPE.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_PINK_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_PINK_CAPARISON_HALF.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_PINK_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_PINK_CAPARISON_FULL.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_PINK_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_PINK_CAPARISON_SHOULDER.get())
                                .define('A', Items.PINK_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_PINK_CAPARISON_SHOULDER.get().toString()));



        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.PURPLE_CAPARISON_CAPE.get())
                                .define('A', Items.PURPLE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.PURPLE_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.PURPLE_CAPARISON_HALF.get())
                                .define('A', Items.PURPLE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.PURPLE_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.PURPLE_CAPARISON_FULL.get())
                                .define('A', Items.PURPLE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("  A")
                                .pattern("AAA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.PURPLE_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.PURPLE_CAPARISON_SHOULDER.get())
                                .define('A', Items.PURPLE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern(" A")
                                .pattern("AA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.PURPLE_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_PURPLE_CAPARISON_CAPE.get())
                                .define('A', Items.PURPLE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_PURPLE_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_PURPLE_CAPARISON_HALF.get())
                                .define('A', Items.PURPLE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_PURPLE_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_PURPLE_CAPARISON_FULL.get())
                                .define('A', Items.PURPLE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_PURPLE_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_PURPLE_CAPARISON_SHOULDER.get())
                                .define('A', Items.PURPLE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_PURPLE_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_PURPLE_CAPARISON_CAPE.get())
                                .define('A', Items.PURPLE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_PURPLE_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_PURPLE_CAPARISON_HALF.get())
                                .define('A', Items.PURPLE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_PURPLE_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_PURPLE_CAPARISON_FULL.get())
                                .define('A', Items.PURPLE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_PURPLE_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_PURPLE_CAPARISON_SHOULDER.get())
                                .define('A', Items.PURPLE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_PURPLE_CAPARISON_SHOULDER.get().toString()));



        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.RED_CAPARISON_CAPE.get())
                                .define('A', Items.RED_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.RED_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.RED_CAPARISON_HALF.get())
                                .define('A', Items.RED_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.RED_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.RED_CAPARISON_FULL.get())
                                .define('A', Items.RED_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("  A")
                                .pattern("AAA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.RED_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.RED_CAPARISON_SHOULDER.get())
                                .define('A', Items.RED_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern(" A")
                                .pattern("AA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.RED_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_RED_CAPARISON_CAPE.get())
                                .define('A', Items.RED_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_RED_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_RED_CAPARISON_HALF.get())
                                .define('A', Items.RED_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_RED_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_RED_CAPARISON_FULL.get())
                                .define('A', Items.RED_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_RED_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_RED_CAPARISON_SHOULDER.get())
                                .define('A', Items.RED_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_RED_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_RED_CAPARISON_CAPE.get())
                                .define('A', Items.RED_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_RED_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_RED_CAPARISON_HALF.get())
                                .define('A', Items.RED_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_RED_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_RED_CAPARISON_FULL.get())
                                .define('A', Items.RED_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_RED_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_RED_CAPARISON_SHOULDER.get())
                                .define('A', Items.RED_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_RED_CAPARISON_SHOULDER.get().toString()));



        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_CAPARISON_CAPE.get())
                                .define('A', Items.WHITE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_CAPARISON_HALF.get())
                                .define('A', Items.WHITE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_CAPARISON_FULL.get())
                                .define('A', Items.WHITE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("  A")
                                .pattern("AAA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_CAPARISON_SHOULDER.get())
                                .define('A', Items.WHITE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern(" A")
                                .pattern("AA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_WHITE_CAPARISON_CAPE.get())
                                .define('A', Items.WHITE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_WHITE_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_WHITE_CAPARISON_HALF.get())
                                .define('A', Items.WHITE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_WHITE_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_WHITE_CAPARISON_FULL.get())
                                .define('A', Items.WHITE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_WHITE_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_WHITE_CAPARISON_SHOULDER.get())
                                .define('A', Items.WHITE_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_WHITE_CAPARISON_SHOULDER.get().toString()));



        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.YELLOW_CAPARISON_CAPE.get())
                                .define('A', Items.YELLOW_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.YELLOW_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.YELLOW_CAPARISON_HALF.get())
                                .define('A', Items.YELLOW_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("AA ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.YELLOW_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.YELLOW_CAPARISON_FULL.get())
                                .define('A', Items.YELLOW_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern("  A")
                                .pattern("AAA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.YELLOW_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.YELLOW_CAPARISON_SHOULDER.get())
                                .define('A', Items.YELLOW_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .pattern(" A")
                                .pattern("AA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.YELLOW_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_YELLOW_CAPARISON_CAPE.get())
                                .define('A', Items.YELLOW_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_YELLOW_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_YELLOW_CAPARISON_HALF.get())
                                .define('A', Items.YELLOW_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_YELLOW_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_YELLOW_CAPARISON_FULL.get())
                                .define('A', Items.YELLOW_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_YELLOW_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.BLACK_YELLOW_CAPARISON_SHOULDER.get())
                                .define('A', Items.YELLOW_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.BLACK_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.BLACK_YELLOW_CAPARISON_SHOULDER.get().toString()));


        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_YELLOW_CAPARISON_CAPE.get())
                                .define('A', Items.YELLOW_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("CA")
                                .pattern("BA")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_YELLOW_CAPARISON_CAPE.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_YELLOW_CAPARISON_HALF.get())
                                .define('A', Items.YELLOW_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("AC ")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_YELLOW_CAPARISON_HALF.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_YELLOW_CAPARISON_FULL.get())
                                .define('A', Items.YELLOW_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern("  C")
                                .pattern("ACA")
                                .pattern("BAB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_YELLOW_CAPARISON_FULL.get().toString()));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("medievalembroidery"))
                .addRecipe(
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MECompatItems.WHITE_YELLOW_CAPARISON_SHOULDER.get())
                                .define('A', Items.YELLOW_CARPET)
                                .define('B', Items.GOLD_NUGGET)
                                .define('C', Items.WHITE_CARPET)
                                .pattern(" C")
                                .pattern("CA")
                                .pattern("AB")
                                .unlockedBy("has_carpet", has(ItemTags.WOOL_CARPETS))
                                ::save).build
                        (pFinishedRecipeConsumer, new ResourceLocation("medievalembroidery", MECompatItems.WHITE_YELLOW_CAPARISON_SHOULDER.get().toString()));



        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.BLACK_MODERN_BLANKET.get())
                .define('A', Items.BLACK_CARPET)
                .pattern("AAA")
                .pattern("AA ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.BLACK_RACING_BLANKET.get())
                .define('A', Items.BLACK_CARPET)
                .pattern("AAA")
                .pattern("A  ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.BLACK_WESTERN_BLANKET.get())
                .define('A', Items.BLACK_CARPET)
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.BLACK_MEDIEVAL_BLANKET.get())
                .define('A', Items.BLACK_CARPET)
                .define('B', Items.GOLD_NUGGET)
                .pattern("AAA")
                .pattern("BAB")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.BLUE_MODERN_BLANKET.get())
                .define('A', Items.BLUE_CARPET)
                .pattern("AAA")
                .pattern("AA ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.BLUE_RACING_BLANKET.get())
                .define('A', Items.BLUE_CARPET)
                .pattern("AAA")
                .pattern("A  ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.BLUE_WESTERN_BLANKET.get())
                .define('A', Items.BLUE_CARPET)
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.BLUE_MEDIEVAL_BLANKET.get())
                .define('A', Items.BLUE_CARPET)
                .define('B', Items.GOLD_NUGGET)
                .pattern("AAA")
                .pattern("BAB")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.BROWN_MODERN_BLANKET.get())
                .define('A', Items.BROWN_CARPET)
                .pattern("AAA")
                .pattern("AA ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.BROWN_RACING_BLANKET.get())
                .define('A', Items.BROWN_CARPET)
                .pattern("AAA")
                .pattern("A  ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.BROWN_WESTERN_BLANKET.get())
                .define('A', Items.BROWN_CARPET)
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.BROWN_MEDIEVAL_BLANKET.get())
                .define('A', Items.BROWN_CARPET)
                .define('B', Items.GOLD_NUGGET)
                .pattern("AAA")
                .pattern("BAB")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.CYAN_MODERN_BLANKET.get())
                .define('A', Items.CYAN_CARPET)
                .pattern("AAA")
                .pattern("AA ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.CYAN_RACING_BLANKET.get())
                .define('A', Items.CYAN_CARPET)
                .pattern("AAA")
                .pattern("A  ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.CYAN_WESTERN_BLANKET.get())
                .define('A', Items.CYAN_CARPET)
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.CYAN_MEDIEVAL_BLANKET.get())
                .define('A', Items.CYAN_CARPET)
                .define('B', Items.GOLD_NUGGET)
                .pattern("AAA")
                .pattern("BAB")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.GREEN_MODERN_BLANKET.get())
                .define('A', Items.GREEN_CARPET)
                .pattern("AAA")
                .pattern("AA ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.GREEN_RACING_BLANKET.get())
                .define('A', Items.GREEN_CARPET)
                .pattern("AAA")
                .pattern("A  ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.GREEN_WESTERN_BLANKET.get())
                .define('A', Items.GREEN_CARPET)
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.GREEN_MEDIEVAL_BLANKET.get())
                .define('A', Items.GREEN_CARPET)
                .define('B', Items.GOLD_NUGGET)
                .pattern("AAA")
                .pattern("BAB")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.GREY_MODERN_BLANKET.get())
                .define('A', Items.GRAY_CARPET)
                .pattern("AAA")
                .pattern("AA ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.GREY_RACING_BLANKET.get())
                .define('A', Items.GRAY_CARPET)
                .pattern("AAA")
                .pattern("A  ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.GREY_WESTERN_BLANKET.get())
                .define('A', Items.GRAY_CARPET)
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.GREY_MEDIEVAL_BLANKET.get())
                .define('A', Items.GRAY_CARPET)
                .define('B', Items.GOLD_NUGGET)
                .pattern("AAA")
                .pattern("BAB")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LIGHT_BLUE_MODERN_BLANKET.get())
                .define('A', Items.LIGHT_BLUE_CARPET)
                .pattern("AAA")
                .pattern("AA ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LIGHT_BLUE_RACING_BLANKET.get())
                .define('A', Items.LIGHT_BLUE_CARPET)
                .pattern("AAA")
                .pattern("A  ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LIGHT_BLUE_WESTERN_BLANKET.get())
                .define('A', Items.LIGHT_BLUE_CARPET)
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LIGHT_BLUE_MEDIEVAL_BLANKET.get())
                .define('A', Items.LIGHT_BLUE_CARPET)
                .define('B', Items.GOLD_NUGGET)
                .pattern("AAA")
                .pattern("BAB")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LIGHT_GREY_MODERN_BLANKET.get())
                .define('A', Items.LIGHT_GRAY_CARPET)
                .pattern("AAA")
                .pattern("AA ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LIGHT_GREY_RACING_BLANKET.get())
                .define('A', Items.LIGHT_GRAY_CARPET)
                .pattern("AAA")
                .pattern("A  ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LIGHT_GREY_WESTERN_BLANKET.get())
                .define('A', Items.LIGHT_GRAY_CARPET)
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LIGHT_GREY_MEDIEVAL_BLANKET.get())
                .define('A', Items.LIGHT_GRAY_CARPET)
                .define('B', Items.GOLD_NUGGET)
                .pattern("AAA")
                .pattern("BAB")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LIME_MODERN_BLANKET.get())
                .define('A', Items.LIME_CARPET)
                .pattern("AAA")
                .pattern("AA ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LIME_RACING_BLANKET.get())
                .define('A', Items.LIME_CARPET)
                .pattern("AAA")
                .pattern("A  ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LIME_WESTERN_BLANKET.get())
                .define('A', Items.LIME_CARPET)
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LIME_MEDIEVAL_BLANKET.get())
                .define('A', Items.LIME_CARPET)
                .define('B', Items.GOLD_NUGGET)
                .pattern("AAA")
                .pattern("BAB")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.MAGENTA_MODERN_BLANKET.get())
                .define('A', Items.MAGENTA_CARPET)
                .pattern("AAA")
                .pattern("AA ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.MAGENTA_RACING_BLANKET.get())
                .define('A', Items.MAGENTA_CARPET)
                .pattern("AAA")
                .pattern("A  ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.MAGENTA_WESTERN_BLANKET.get())
                .define('A', Items.MAGENTA_CARPET)
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.MAGENTA_MEDIEVAL_BLANKET.get())
                .define('A', Items.MAGENTA_CARPET)
                .define('B', Items.GOLD_NUGGET)
                .pattern("AAA")
                .pattern("BAB")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.ORANGE_MODERN_BLANKET.get())
                .define('A', Items.ORANGE_CARPET)
                .pattern("AAA")
                .pattern("AA ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.ORANGE_RACING_BLANKET.get())
                .define('A', Items.ORANGE_CARPET)
                .pattern("AAA")
                .pattern("A  ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.ORANGE_WESTERN_BLANKET.get())
                .define('A', Items.ORANGE_CARPET)
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.ORANGE_MEDIEVAL_BLANKET.get())
                .define('A', Items.ORANGE_CARPET)
                .define('B', Items.GOLD_NUGGET)
                .pattern("AAA")
                .pattern("BAB")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.PINK_MODERN_BLANKET.get())
                .define('A', Items.PINK_CARPET)
                .pattern("AAA")
                .pattern("AA ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.PINK_RACING_BLANKET.get())
                .define('A', Items.PINK_CARPET)
                .pattern("AAA")
                .pattern("A  ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.PINK_WESTERN_BLANKET.get())
                .define('A', Items.PINK_CARPET)
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.PINK_MEDIEVAL_BLANKET.get())
                .define('A', Items.PINK_CARPET)
                .define('B', Items.GOLD_NUGGET)
                .pattern("AAA")
                .pattern("BAB")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.PURPLE_MODERN_BLANKET.get())
                .define('A', Items.PURPLE_CARPET)
                .pattern("AAA")
                .pattern("AA ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.PURPLE_RACING_BLANKET.get())
                .define('A', Items.PURPLE_CARPET)
                .pattern("AAA")
                .pattern("A  ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.PURPLE_WESTERN_BLANKET.get())
                .define('A', Items.PURPLE_CARPET)
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.PURPLE_MEDIEVAL_BLANKET.get())
                .define('A', Items.PURPLE_CARPET)
                .define('B', Items.GOLD_NUGGET)
                .pattern("AAA")
                .pattern("BAB")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.RED_MODERN_BLANKET.get())
                .define('A', Items.RED_CARPET)
                .pattern("AAA")
                .pattern("AA ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.RED_RACING_BLANKET.get())
                .define('A', Items.RED_CARPET)
                .pattern("AAA")
                .pattern("A  ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.RED_WESTERN_BLANKET.get())
                .define('A', Items.RED_CARPET)
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.RED_MEDIEVAL_BLANKET.get())
                .define('A', Items.RED_CARPET)
                .define('B', Items.GOLD_NUGGET)
                .pattern("AAA")
                .pattern("BAB")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.WHITE_MODERN_BLANKET.get())
                .define('A', Items.WHITE_CARPET)
                .pattern("AAA")
                .pattern("AA ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.WHITE_RACING_BLANKET.get())
                .define('A', Items.WHITE_CARPET)
                .pattern("AAA")
                .pattern("A  ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.WHITE_WESTERN_BLANKET.get())
                .define('A', Items.WHITE_CARPET)
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.WHITE_MEDIEVAL_BLANKET.get())
                .define('A', Items.WHITE_CARPET)
                .define('B', Items.GOLD_NUGGET)
                .pattern("AAA")
                .pattern("BAB")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.YELLOW_MODERN_BLANKET.get())
                .define('A', Items.YELLOW_CARPET)
                .pattern("AAA")
                .pattern("AA ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.YELLOW_RACING_BLANKET.get())
                .define('A', Items.YELLOW_CARPET)
                .pattern("AAA")
                .pattern("A  ")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.YELLOW_WESTERN_BLANKET.get())
                .define('A', Items.YELLOW_CARPET)
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.YELLOW_MEDIEVAL_BLANKET.get())
                .define('A', Items.YELLOW_CARPET)
                .define('B', Items.GOLD_NUGGET)
                .pattern("AAA")
                .pattern("BAB")
                .unlockedBy("has_carpet", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL_CARPETS).build()))
                .save(pFinishedRecipeConsumer);




        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LIGHT_HORSE_ARMOR_SMITHING_TEMPLATE.get(), 3)
                .define('A', Items.LEATHER)
                .define('B', Items.PAPER)
                .define('C', Items.IRON_INGOT)
                .pattern("BAB")
                .pattern("ACA")
                .pattern("CBC")
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER).build()))
                .save(pFinishedRecipeConsumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.BLACK_WOOL, 8)
                .define('A', LOItems.BLACK_WOOL_DYE.get())
                .define('B', ItemTags.WOOL)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "black_wool_from_wool_dye_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.BLUE_WOOL, 8)
                .define('A', LOItems.BLUE_WOOL_DYE.get())
                .define('B', ItemTags.WOOL)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "blue_wool_from_wool_dye_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.BROWN_WOOL, 8)
                .define('A', LOItems.BROWN_WOOL_DYE.get())
                .define('B', ItemTags.WOOL)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "brown_wool_from_wool_dye_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.CYAN_WOOL, 8)
                .define('A', LOItems.CYAN_WOOL_DYE.get())
                .define('B', ItemTags.WOOL)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "cyan_wool_from_wool_dye_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.GREEN_WOOL, 8)
                .define('A', LOItems.GREEN_WOOL_DYE.get())
                .define('B', ItemTags.WOOL)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "green_wool_from_wool_dye_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.GRAY_WOOL, 8)
                .define('A', LOItems.GREY_WOOL_DYE.get())
                .define('B', ItemTags.WOOL)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "gray_wool_from_wool_dye_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.LIGHT_BLUE_WOOL, 8)
                .define('A', LOItems.LIGHT_BLUE_WOOL_DYE.get())
                .define('B', ItemTags.WOOL)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "light_blue_wool_from_wool_dye_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.LIGHT_GRAY_WOOL, 8)
                .define('A', LOItems.LIGHT_GREY_WOOL_DYE.get())
                .define('B', ItemTags.WOOL)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "light_grey_wool_from_wool_dye_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.LIME_WOOL, 8)
                .define('A', LOItems.LIME_WOOL_DYE.get())
                .define('B', ItemTags.WOOL)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "lime_wool_from_wool_dye_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.MAGENTA_WOOL, 8)
                .define('A', LOItems.MAGENTA_WOOL_DYE.get())
                .define('B', ItemTags.WOOL)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "magenta_wool_from_wool_dye_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.ORANGE_WOOL, 8)
                .define('A', LOItems.ORANGE_WOOL_DYE.get())
                .define('B', ItemTags.WOOL)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "orange_wool_from_wool_dye_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.PINK_WOOL, 8)
                .define('A', LOItems.PINK_WOOL_DYE.get())
                .define('B', ItemTags.WOOL)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "pink_wool_from_wool_dye_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.PURPLE_WOOL, 8)
                .define('A', LOItems.PURPLE_WOOL_DYE.get())
                .define('B', ItemTags.WOOL)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "purple_wool_from_wool_dye_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.RED_WOOL, 8)
                .define('A', LOItems.RED_WOOL_DYE.get())
                .define('B', ItemTags.WOOL)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "red_wool_from_wool_dye_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.WHITE_WOOL, 8)
                .define('A', LOItems.WHITE_WOOL_DYE.get())
                .define('B', ItemTags.WOOL)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "white_wool_from_wool_dye_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.YELLOW_WOOL, 8)
                .define('A', LOItems.YELLOW_WOOL_DYE.get())
                .define('B', ItemTags.WOOL)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "yellow_wool_from_wool_dye_8"));




        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BLACK_WOOL, 2)
                .requires(LOItems.BLACK_WOOL_STAPLE.get())
                .requires(LOItems.SPINDLE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID,"black_wool_from_staple"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BLUE_WOOL, 2)
                .requires(LOItems.BLUE_WOOL_STAPLE.get())
                .requires(LOItems.SPINDLE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID,"blue_wool_from_staple"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BROWN_WOOL, 2)
                .requires(LOItems.BROWN_WOOL_STAPLE.get())
                .requires(LOItems.SPINDLE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID,"brown_wool_from_staple"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.CYAN_WOOL, 2)
                .requires(LOItems.CYAN_WOOL_STAPLE.get())
                .requires(LOItems.SPINDLE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID,"cyan_wool_from_staple"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GREEN_WOOL, 2)
                .requires(LOItems.GREEN_WOOL_STAPLE.get())
                .requires(LOItems.SPINDLE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID,"green_wool_from_staple"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GRAY_WOOL, 2)
                .requires(LOItems.GREY_WOOL_STAPLE.get())
                .requires(LOItems.SPINDLE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID,"gray_wool_from_staple"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.LIGHT_BLUE_WOOL, 2)
                .requires(LOItems.LIGHT_BLUE_WOOL_STAPLE.get())
                .requires(LOItems.SPINDLE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID,"light_blue_wool_from_staple"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.LIGHT_GRAY_WOOL, 2)
                .requires(LOItems.LIGHT_GREY_WOOL_STAPLE.get())
                .requires(LOItems.SPINDLE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID,"light_gray_wool_from_staple"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.LIME_WOOL, 2)
                .requires(LOItems.LIME_WOOL_STAPLE.get())
                .requires(LOItems.SPINDLE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID,"lime_wool_from_staple"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.MAGENTA_WOOL, 2)
                .requires(LOItems.MAGENTA_WOOL_STAPLE.get())
                .requires(LOItems.SPINDLE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID,"magenta_wool_from_staple"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.ORANGE_WOOL, 2)
                .requires(LOItems.ORANGE_WOOL_STAPLE.get())
                .requires(LOItems.SPINDLE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID,"orange_wool_from_staple"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.PINK_WOOL, 2)
                .requires(LOItems.PINK_WOOL_STAPLE.get())
                .requires(LOItems.SPINDLE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID,"pink_wool_from_staple"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.PURPLE_WOOL, 2)
                .requires(LOItems.PURPLE_WOOL_STAPLE.get())
                .requires(LOItems.SPINDLE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID,"purple_wool_from_staple"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.RED_WOOL, 2)
                .requires(LOItems.RED_WOOL_STAPLE.get())
                .requires(LOItems.SPINDLE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID,"red_wool_from_staple"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.WHITE_WOOL, 2)
                .requires(LOItems.WHITE_WOOL_STAPLE.get())
                .requires(LOItems.SPINDLE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID,"white_wool_from_staple"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.YELLOW_WOOL, 2)
                .requires(LOItems.YELLOW_WOOL_STAPLE.get())
                .requires(LOItems.SPINDLE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID,"yellow_wool_from_staple"));



        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.BLACK_WOOL_STAPLE.get())
                .requires(LOTags.Items.WOOL_STAPLES)
                .requires(LOItems.BLACK_WOOL_DYE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.BLUE_WOOL_STAPLE.get())
                .requires(LOTags.Items.WOOL_STAPLES)
                .requires(LOItems.BLUE_WOOL_DYE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.BROWN_WOOL_STAPLE.get())
                .requires(LOTags.Items.WOOL_STAPLES)
                .requires(LOItems.BROWN_WOOL_DYE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.CYAN_WOOL_STAPLE.get())
                .requires(LOTags.Items.WOOL_STAPLES)
                .requires(LOItems.CYAN_WOOL_DYE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.GREEN_WOOL_STAPLE.get())
                .requires(LOTags.Items.WOOL_STAPLES)
                .requires(LOItems.GREEN_WOOL_DYE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.GREY_WOOL_STAPLE.get())
                .requires(LOTags.Items.WOOL_STAPLES)
                .requires(LOItems.GREY_WOOL_DYE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.LIGHT_BLUE_WOOL_STAPLE.get())
                .requires(LOTags.Items.WOOL_STAPLES)
                .requires(LOItems.LIGHT_BLUE_WOOL_DYE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.LIGHT_GREY_WOOL_STAPLE.get())
                .requires(LOTags.Items.WOOL_STAPLES)
                .requires(LOItems.LIGHT_GREY_WOOL_DYE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.LIME_WOOL_STAPLE.get())
                .requires(LOTags.Items.WOOL_STAPLES)
                .requires(LOItems.LIME_WOOL_DYE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.MAGENTA_WOOL_STAPLE.get())
                .requires(LOTags.Items.WOOL_STAPLES)
                .requires(LOItems.MAGENTA_WOOL_DYE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.ORANGE_WOOL_STAPLE.get())
                .requires(LOTags.Items.WOOL_STAPLES)
                .requires(LOItems.ORANGE_WOOL_DYE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.PINK_WOOL_STAPLE.get())
                .requires(LOTags.Items.WOOL_STAPLES)
                .requires(LOItems.PINK_WOOL_DYE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.PURPLE_WOOL_STAPLE.get())
                .requires(LOTags.Items.WOOL_STAPLES)
                .requires(LOItems.PURPLE_WOOL_DYE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.RED_WOOL_STAPLE.get())
                .requires(LOTags.Items.WOOL_STAPLES)
                .requires(LOItems.RED_WOOL_DYE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.WHITE_WOOL_STAPLE.get())
                .requires(LOTags.Items.WOOL_STAPLES)
                .requires(LOItems.WHITE_WOOL_DYE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.YELLOW_WOOL_STAPLE.get())
                .requires(LOTags.Items.WOOL_STAPLES)
                .requires(LOItems.YELLOW_WOOL_DYE.get())
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES)
                        .build()))
                .save(pFinishedRecipeConsumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.BLACK_WOOL_STAPLE.get(), 8)
                .define('A', LOItems.BLACK_WOOL_DYE.get())
                .define('B', LOTags.Items.WOOL_STAPLES)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "black_wool_staple_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.BLUE_WOOL_STAPLE.get(), 8)
                .define('A', LOItems.BLUE_WOOL_DYE.get())
                .define('B', LOTags.Items.WOOL_STAPLES)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "blue_wool_staple_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.BROWN_WOOL_STAPLE.get(), 8)
                .define('A', LOItems.BROWN_WOOL_DYE.get())
                .define('B', LOTags.Items.WOOL_STAPLES)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "brown_wool_staple_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.CYAN_WOOL_STAPLE.get(), 8)
                .define('A', LOItems.CYAN_WOOL_DYE.get())
                .define('B', LOTags.Items.WOOL_STAPLES)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "cyan_wool_staple_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.GREEN_WOOL_STAPLE.get(), 8)
                .define('A', LOItems.GREEN_WOOL_DYE.get())
                .define('B', LOTags.Items.WOOL_STAPLES)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "green_wool_staple_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.GREY_WOOL_STAPLE.get(), 8)
                .define('A', LOItems.GREY_WOOL_DYE.get())
                .define('B', LOTags.Items.WOOL_STAPLES)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "grey_wool_staple_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LIGHT_BLUE_WOOL_STAPLE.get(), 8)
                .define('A', LOItems.LIGHT_BLUE_WOOL_DYE.get())
                .define('B', LOTags.Items.WOOL_STAPLES)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "light_blue_wool_staple_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LIGHT_GREY_WOOL_STAPLE.get(), 8)
                .define('A', LOItems.LIGHT_GREY_WOOL_DYE.get())
                .define('B', LOTags.Items.WOOL_STAPLES)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "light_grey_wool_staple_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.LIME_WOOL_STAPLE.get(), 8)
                .define('A', LOItems.LIME_WOOL_DYE.get())
                .define('B', LOTags.Items.WOOL_STAPLES)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "lime_wool_staple_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.MAGENTA_WOOL_STAPLE.get(), 8)
                .define('A', LOItems.MAGENTA_WOOL_DYE.get())
                .define('B', LOTags.Items.WOOL_STAPLES)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "magenta_wool_staple_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.ORANGE_WOOL_STAPLE.get(), 8)
                .define('A', LOItems.ORANGE_WOOL_DYE.get())
                .define('B', LOTags.Items.WOOL_STAPLES)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "orange_wool_staple_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.PINK_WOOL_STAPLE.get(), 8)
                .define('A', LOItems.PINK_WOOL_DYE.get())
                .define('B', LOTags.Items.WOOL_STAPLES)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "pink_wool_staple_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.PURPLE_WOOL_STAPLE.get(), 8)
                .define('A', LOItems.PURPLE_WOOL_DYE.get())
                .define('B', LOTags.Items.WOOL_STAPLES)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "purple_wool_staple_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.RED_WOOL_STAPLE.get(), 8)
                .define('A', LOItems.RED_WOOL_DYE.get())
                .define('B', LOTags.Items.WOOL_STAPLES)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "red_wool_staple_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.WHITE_WOOL_STAPLE.get(), 8)
                .define('A', LOItems.WHITE_WOOL_DYE.get())
                .define('B', LOTags.Items.WOOL_STAPLES)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "white_wool_staple_8"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.YELLOW_WOOL_STAPLE.get(), 8)
                .define('A', LOItems.YELLOW_WOOL_DYE.get())
                .define('B', LOTags.Items.WOOL_STAPLES)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .unlockedBy("has_staple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(LOTags.Items.WOOL_STAPLES).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(LivestockOverhaul.MODID, "yellow_wool_staple_8"));



        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.BLACK_WOOL_DYE.get(), 8)
                .requires(Items.BLACK_DYE)
                .requires(Items.BLACK_DYE)
                .unlockedBy("has_dye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BLACK_DYE)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.BLUE_WOOL_DYE.get(), 8)
                .requires(Items.BLUE_DYE)
                .requires(Items.BLUE_DYE)
                .unlockedBy("has_dye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BLUE_DYE)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.BROWN_WOOL_DYE.get(), 8)
                .requires(Items.BROWN_DYE)
                .requires(Items.BROWN_DYE)
                .unlockedBy("has_dye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.BROWN_DYE)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.CYAN_WOOL_DYE.get(), 8)
                .requires(Items.CYAN_DYE)
                .requires(Items.CYAN_DYE)
                .unlockedBy("has_dye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.CYAN_DYE)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.GREEN_WOOL_DYE.get(), 8)
                .requires(Items.GREEN_DYE)
                .requires(Items.GREEN_DYE)
                .unlockedBy("has_dye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.GREEN_DYE)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.GREY_WOOL_DYE.get(), 8)
                .requires(Items.GRAY_DYE)
                .requires(Items.GRAY_DYE)
                .unlockedBy("has_dye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.GRAY_DYE)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.LIGHT_BLUE_WOOL_DYE.get(), 8)
                .requires(Items.LIGHT_BLUE_DYE)
                .requires(Items.LIGHT_BLUE_DYE)
                .unlockedBy("has_dye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LIGHT_BLUE_DYE)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.LIGHT_GREY_WOOL_DYE.get(), 8)
                .requires(Items.LIGHT_GRAY_DYE)
                .requires(Items.LIGHT_GRAY_DYE)
                .unlockedBy("has_dye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LIGHT_GRAY_DYE)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.LIME_WOOL_DYE.get(), 8)
                .requires(Items.LIME_DYE)
                .requires(Items.LIME_DYE)
                .unlockedBy("has_dye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LIME_DYE)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.MAGENTA_WOOL_DYE.get(), 8)
                .requires(Items.MAGENTA_DYE)
                .requires(Items.MAGENTA_DYE)
                .unlockedBy("has_dye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.MAGENTA_DYE)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.ORANGE_WOOL_DYE.get(), 8)
                .requires(Items.ORANGE_DYE)
                .requires(Items.ORANGE_DYE)
                .unlockedBy("has_dye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.ORANGE_DYE)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.PINK_WOOL_DYE.get(), 8)
                .requires(Items.PINK_DYE)
                .requires(Items.PINK_DYE)
                .unlockedBy("has_dye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PINK_DYE)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.PURPLE_WOOL_DYE.get(), 8)
                .requires(Items.PURPLE_DYE)
                .requires(Items.PURPLE_DYE)
                .unlockedBy("has_dye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PURPLE_DYE)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.RED_WOOL_DYE.get(), 8)
                .requires(Items.RED_DYE)
                .requires(Items.RED_DYE)
                .unlockedBy("has_dye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.RED_DYE)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.WHITE_WOOL_DYE.get(), 8)
                .requires(Items.WHITE_DYE)
                .requires(Items.WHITE_DYE)
                .unlockedBy("has_dye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.WHITE_DYE)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.YELLOW_WOOL_DYE.get(), 8)
                .requires(Items.YELLOW_DYE)
                .requires(Items.YELLOW_DYE)
                .unlockedBy("has_dye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.YELLOW_DYE)
                        .build()))
                .save(pFinishedRecipeConsumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.SPINDLE.get())
                .define('A', Items.STICK)
                .define('B', Items.IRON_NUGGET)
                .pattern("BAB")
                .pattern(" A ")
                .pattern(" A ")
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.STICK).build()))
                .save(pFinishedRecipeConsumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.RODEO_HARNESS.get())
                .define('A', Items.LEATHER)
                .define('B', Items.IRON_NUGGET)
                .pattern("A A")
                .pattern("BAB")
                .pattern("A A")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.WAGON_HARNESS.get())
                .define('A', Items.LEATHER)
                .define('B', Items.IRON_NUGGET)
                .pattern("AAA")
                .pattern("BAB")
                .pattern("A A")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.MARTINGALE_HARNESS.get())
                .define('A', Items.LEATHER)
                .pattern("A  ")
                .pattern(" A ")
                .pattern("A A")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);


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



        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.BLACK_BRAND_TAG.get(), 12)
                .requires(Items.STRING)
                .requires(Items.PAPER)
                .requires(Items.BLACK_DYE)
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.BLUE_BRAND_TAG.get(), 12)
                .requires(Items.STRING)
                .requires(Items.PAPER)
                .requires(Items.BLUE_DYE)
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.BROWN_BRAND_TAG.get(), 12)
                .requires(Items.STRING)
                .requires(Items.PAPER)
                .requires(Items.BROWN_DYE)
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.CYAN_BRAND_TAG.get(), 12)
                .requires(Items.STRING)
                .requires(Items.PAPER)
                .requires(Items.CYAN_DYE)
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.GREEN_BRAND_TAG.get(), 12)
                .requires(Items.STRING)
                .requires(Items.PAPER)
                .requires(Items.GREEN_DYE)
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.GREY_BRAND_TAG.get(), 12)
                .requires(Items.STRING)
                .requires(Items.PAPER)
                .requires(Items.GRAY_DYE)
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.LIGHT_BLUE_BRAND_TAG.get(), 12)
                .requires(Items.STRING)
                .requires(Items.PAPER)
                .requires(Items.LIGHT_BLUE_DYE)
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.LIGHT_GREY_BRAND_TAG.get(), 12)
                .requires(Items.STRING)
                .requires(Items.PAPER)
                .requires(Items.LIGHT_GRAY_DYE)
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.LIME_BRAND_TAG.get(), 12)
                .requires(Items.STRING)
                .requires(Items.PAPER)
                .requires(Items.LIME_DYE)
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.MAGENTA_BRAND_TAG.get(), 12)
                .requires(Items.STRING)
                .requires(Items.PAPER)
                .requires(Items.MAGENTA_DYE)
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.ORANGE_BRAND_TAG.get(), 12)
                .requires(Items.STRING)
                .requires(Items.PAPER)
                .requires(Items.ORANGE_DYE)
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.PINK_BRAND_TAG.get(), 12)
                .requires(Items.STRING)
                .requires(Items.PAPER)
                .requires(Items.PINK_DYE)
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.PURPLE_BRAND_TAG.get(), 12)
                .requires(Items.STRING)
                .requires(Items.PAPER)
                .requires(Items.PURPLE_DYE)
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.RED_BRAND_TAG.get(), 12)
                .requires(Items.STRING)
                .requires(Items.PAPER)
                .requires(Items.RED_DYE)
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.WHITE_BRAND_TAG.get(), 12)
                .requires(Items.STRING)
                .requires(Items.PAPER)
                .requires(Items.WHITE_DYE)
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER)
                        .build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, LOItems.YELLOW_BRAND_TAG.get(), 12)
                .requires(Items.STRING)
                .requires(Items.PAPER)
                .requires(Items.YELLOW_DYE)
                .unlockedBy("has_paper", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.PAPER)
                        .build()))
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
                .define('B', Items.IRON_NUGGET)
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
                .define('B', Items.IRON_NUGGET)
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