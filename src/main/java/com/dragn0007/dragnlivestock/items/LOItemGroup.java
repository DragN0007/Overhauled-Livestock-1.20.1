package com.dragn0007.dragnlivestock.items;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.compat.medievalembroidery.MECompatItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class LOItemGroup {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LivestockOverhaul.MODID);

    public static final RegistryObject<CreativeModeTab> LIVESTOCK_OVERHAUL_GROUP = CREATIVE_MODE_TABS.register("overhauled_livestock",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(LOItems.LIVESTOCK_OVERHAUL.get())).title(Component.translatable("itemGroup.overhauled_livestock"))
                    .displayItems((displayParameters, output) -> {

                        output.accept(LOItems.O_HORSE_SPAWN_EGG.get());
                        output.accept(LOItems.O_DONKEY_SPAWN_EGG.get());
                        output.accept(LOItems.O_MULE_SPAWN_EGG.get());
                        output.accept(LOItems.O_COW_SPAWN_EGG.get());
                        output.accept(LOItems.O_CHICKEN_SPAWN_EGG.get());
                        output.accept(LOItems.O_SALMON_SPAWN_EGG.get());
                        output.accept(LOItems.O_COD_SPAWN_EGG.get());
                        output.accept(LOItems.O_BEE_SPAWN_EGG.get());
                        output.accept(LOItems.O_RABBIT_SPAWN_EGG.get());
                        output.accept(LOItems.O_SHEEP_SPAWN_EGG.get());
                        output.accept(LOItems.O_LLAMA_SPAWN_EGG.get());
                        output.accept(LOItems.O_PIG_SPAWN_EGG.get());
                        output.accept(LOItems.O_MOOSHROOM_SPAWN_EGG.get());
                        output.accept(LOItems.O_CAMEL_SPAWN_EGG.get());
                        output.accept(LOItems.O_GOAT_SPAWN_EGG.get());
                        output.accept(LOItems.O_FROG_SPAWN_EGG.get());
                        output.accept(LOItems.GRUB_SPAWN_EGG.get());
                        output.accept(LOItems.WHEAT_MOOBLOOM_SPAWN_EGG.get());
                        output.accept(LOItems.SWEET_BERRY_MOOBLOOM_SPAWN_EGG.get());
                        output.accept(LOItems.PUMPKIN_MOOBLOOM_SPAWN_EGG.get());
                        output.accept(LOItems.POTATO_MOOBLOOM_SPAWN_EGG.get());
                        output.accept(LOItems.MELON_MOOBLOOM_SPAWN_EGG.get());
                        output.accept(LOItems.GLOW_BERRY_MOOBLOOM_SPAWN_EGG.get());
                        output.accept(LOItems.FLOWERING_MOOBLOOM_SPAWN_EGG.get());
                        output.accept(LOItems.CARROT_MOOBLOOM_SPAWN_EGG.get());
                        output.accept(LOItems.BEETROOT_MOOBLOOM_SPAWN_EGG.get());
                        output.accept(LOItems.AZALEA_MOOBLOOM_SPAWN_EGG.get());

                        if (ModList.get().isLoaded("thatsjustpeachy")) {
                            output.accept(LOItems.PEACH_MOOBLOOM_SPAWN_EGG.get());
                        }

                        output.accept(LOItems.CARIBOU_SPAWN_EGG.get());
                        output.accept(LOItems.UNICORN_SPAWN_EGG.get());

//                        output.accept(LOItems.NETHERITE_HORSESHOE.get());
//                        output.accept(LOItems.DIAMOND_HORSESHOE.get());
//                        output.accept(LOItems.GOLD_HORSESHOE.get());
//                        output.accept(LOItems.IRON_HORSESHOE.get());
//                        output.accept(LOItems.STONE_HORSESHOE.get());

                        output.accept(LOItems.UTILITY_KNIFE.get());
                        output.accept(LOItems.SPINDLE.get());
                        output.accept(LOItems.MOUNT_KEY.get());
                        output.accept(LOItems.COAT_OSCILLATOR.get());
                        output.accept(LOItems.MARKING_OSCILLATOR.get());
                        output.accept(LOItems.BREED_OSCILLATOR.get());

                        output.accept(LOItems.GRUB_SWEATER.get());

                        output.accept(LOItems.MANE_SCISSORS.get());
                        output.accept(LOItems.TAIL_SCISSORS.get());

//                        output.accept(LOBlocks.ACACIA_RABBIT_HUTCH.get());
//                        output.accept(LOBlocks.BAMBOO_RABBIT_HUTCH.get());
//                        output.accept(LOBlocks.BIRCH_RABBIT_HUTCH.get());
//                        output.accept(LOBlocks.CHERRY_RABBIT_HUTCH.get());
//                        output.accept(LOBlocks.CRIMSON_RABBIT_HUTCH.get());
//                        output.accept(LOBlocks.DARK_OAK_RABBIT_HUTCH.get());
//                        output.accept(LOBlocks.JUNGLE_RABBIT_HUTCH.get());
//                        output.accept(LOBlocks.MANGROVE_RABBIT_HUTCH.get());
//                        output.accept(LOBlocks.OAK_RABBIT_HUTCH.get());
//                        output.accept(LOBlocks.SPRUCE_RABBIT_HUTCH.get());
//                        output.accept(LOBlocks.WARPED_RABBIT_HUTCH.get());

                        output.accept(LOItems.LIGHT_HORSE_ARMOR_SMITHING_TEMPLATE.get());

                        output.accept(Items.LEATHER_HORSE_ARMOR);
                        output.accept(LOItems.CHAINMAIL_HORSE_ARMOR.get());
                        output.accept(LOItems.COPPER_HORSE_ARMOR.get());
                        output.accept(Items.IRON_HORSE_ARMOR);
                        output.accept(Items.GOLDEN_HORSE_ARMOR);
                        output.accept(LOItems.QUARTZ_HORSE_ARMOR.get());
                        output.accept(LOItems.EMERALD_HORSE_ARMOR.get());
                        output.accept(Items.DIAMOND_HORSE_ARMOR);
                        output.accept(LOItems.NETHERITE_HORSE_ARMOR.get());
                        if (ModList.get().isLoaded("medievalembroidery")) {
                            output.accept(LOItems.OBSIDIAN_HORSE_ARMOR.get());
                        }
                        if (ModList.get().isLoaded("deadlydinos")) {
                            output.accept(LOItems.RIOT_HORSE_ARMOR.get());
                        }

                        output.accept(LOItems.MINIMAL_LEATHER_HORSE_ARMOR.get());
                        output.accept(LOItems.MINIMAL_COPPER_HORSE_ARMOR.get());
                        output.accept(LOItems.MINIMAL_IRON_HORSE_ARMOR.get());
                        output.accept(LOItems.MINIMAL_GOLDEN_HORSE_ARMOR.get());
                        output.accept(LOItems.MINIMAL_QUARTZ_HORSE_ARMOR.get());
                        output.accept(LOItems.MINIMAL_EMERALD_HORSE_ARMOR.get());
                        output.accept(LOItems.MINIMAL_DIAMOND_HORSE_ARMOR.get());
                        output.accept(LOItems.MINIMAL_NETHERITE_HORSE_ARMOR.get());
                        if (ModList.get().isLoaded("medievalembroidery")) {
                            output.accept(LOItems.MINIMAL_OBSIDIAN_HORSE_ARMOR.get());
                        }

                        output.accept(LOItems.HEAVY_SADDLE.get());
                        output.accept(LOItems.BLACK_HEAVY_SADDLE.get());
                        output.accept(LOItems.WHITE_HEAVY_SADDLE.get());
                        output.accept(Items.SADDLE);
                        output.accept(LOItems.BLACK_SADDLE.get());
                        output.accept(LOItems.WHITE_SADDLE.get());
                        output.accept(LOItems.LIGHT_SADDLE.get());
                        output.accept(LOItems.BLACK_LIGHT_SADDLE.get());
                        output.accept(LOItems.WHITE_LIGHT_SADDLE.get());

                        output.accept(LOItems.RODEO_HARNESS.get());
                        output.accept(LOItems.WAGON_HARNESS.get());
                        output.accept(LOItems.MARTINGALE_HARNESS.get());

//                        output.accept(LOItems.COVERED_WAGON.get());

                        output.accept(LOItems.FERTILIZED_EGG.get());
                        output.accept(LOItems.FERTILIZED_AMERAUCANA_EGG.get());
                        output.accept(LOItems.FERTILIZED_CREAM_LEGBAR_EGG.get());
                        output.accept(LOItems.FERTILIZED_MARANS_EGG.get());
                        output.accept(LOItems.FERTILIZED_OLIVE_EGGER_EGG.get());
                        output.accept(LOItems.FERTILIZED_SUSSEX_SILKIE_EGG.get());
                        output.accept(LOItems.FERTILIZED_AYAM_CEMANI_EGG.get());

                        output.accept(LOItems.GENDER_TEST_STRIP.get());

                        output.accept(LOItems.OVERWORLD_UNICORN_HORN.get());
                        output.accept(LOItems.NETHER_UNICORN_HORN.get());
                        output.accept(LOItems.END_UNICORN_HORN.get());

                        output.accept(LOItems.BLACK_WOOL_STAPLE.get());
                        output.accept(LOItems.BLUE_WOOL_STAPLE.get());
                        output.accept(LOItems.BROWN_WOOL_STAPLE.get());
                        output.accept(LOItems.CYAN_WOOL_STAPLE.get());
                        output.accept(LOItems.GREEN_WOOL_STAPLE.get());
                        output.accept(LOItems.GREY_WOOL_STAPLE.get());
                        output.accept(LOItems.LIGHT_BLUE_WOOL_STAPLE.get());
                        output.accept(LOItems.LIGHT_GREY_WOOL_STAPLE.get());
                        output.accept(LOItems.LIME_WOOL_STAPLE.get());
                        output.accept(LOItems.MAGENTA_WOOL_STAPLE.get());
                        output.accept(LOItems.ORANGE_WOOL_STAPLE.get());
                        output.accept(LOItems.PINK_WOOL_STAPLE.get());
                        output.accept(LOItems.PURPLE_WOOL_STAPLE.get());
                        output.accept(LOItems.RED_WOOL_STAPLE.get());
                        output.accept(LOItems.WHITE_WOOL_STAPLE.get());
                        output.accept(LOItems.YELLOW_WOOL_STAPLE.get());

                        output.accept(LOItems.BLACK_WOOL_DYE.get());
                        output.accept(LOItems.BLUE_WOOL_DYE.get());
                        output.accept(LOItems.BROWN_WOOL_DYE.get());
                        output.accept(LOItems.CYAN_WOOL_DYE.get());
                        output.accept(LOItems.GREEN_WOOL_DYE.get());
                        output.accept(LOItems.GREY_WOOL_DYE.get());
                        output.accept(LOItems.LIGHT_BLUE_WOOL_DYE.get());
                        output.accept(LOItems.LIGHT_GREY_WOOL_DYE.get());
                        output.accept(LOItems.LIME_WOOL_DYE.get());
                        output.accept(LOItems.MAGENTA_WOOL_DYE.get());
                        output.accept(LOItems.ORANGE_WOOL_DYE.get());
                        output.accept(LOItems.PINK_WOOL_DYE.get());
                        output.accept(LOItems.PURPLE_WOOL_DYE.get());
                        output.accept(LOItems.RED_WOOL_DYE.get());
                        output.accept(LOItems.WHITE_WOOL_DYE.get());
                        output.accept(LOItems.YELLOW_WOOL_DYE.get());

                        output.accept(LOItems.BLACK_BRAND_TAG.get());
                        output.accept(LOItems.BLUE_BRAND_TAG.get());
                        output.accept(LOItems.BROWN_BRAND_TAG.get());
                        output.accept(LOItems.CYAN_BRAND_TAG.get());
                        output.accept(LOItems.GREEN_BRAND_TAG.get());
                        output.accept(LOItems.GREY_BRAND_TAG.get());
                        output.accept(LOItems.LIGHT_BLUE_BRAND_TAG.get());
                        output.accept(LOItems.LIGHT_GREY_BRAND_TAG.get());
                        output.accept(LOItems.LIME_BRAND_TAG.get());
                        output.accept(LOItems.MAGENTA_BRAND_TAG.get());
                        output.accept(LOItems.ORANGE_BRAND_TAG.get());
                        output.accept(LOItems.PINK_BRAND_TAG.get());
                        output.accept(LOItems.PURPLE_BRAND_TAG.get());
                        output.accept(LOItems.RED_BRAND_TAG.get());
                        output.accept(LOItems.WHITE_BRAND_TAG.get());
                        output.accept(LOItems.YELLOW_BRAND_TAG.get());

                        output.accept(LOItems.BLACK_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.BLUE_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.BROWN_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.CYAN_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.GREEN_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.GREY_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.LIGHT_BLUE_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.LIGHT_GREY_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.LIME_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.MAGENTA_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.ORANGE_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.PINK_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.PURPLE_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.RED_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.WHITE_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.YELLOW_MEDIEVAL_BLANKET.get());

                        output.accept(LOItems.BLACK_MODERN_BLANKET.get());
                        output.accept(LOItems.BLUE_MODERN_BLANKET.get());
                        output.accept(LOItems.BROWN_MODERN_BLANKET.get());
                        output.accept(LOItems.CYAN_MODERN_BLANKET.get());
                        output.accept(LOItems.GREEN_MODERN_BLANKET.get());
                        output.accept(LOItems.GREY_MODERN_BLANKET.get());
                        output.accept(LOItems.LIGHT_BLUE_MODERN_BLANKET.get());
                        output.accept(LOItems.LIGHT_GREY_MODERN_BLANKET.get());
                        output.accept(LOItems.LIME_MODERN_BLANKET.get());
                        output.accept(LOItems.MAGENTA_MODERN_BLANKET.get());
                        output.accept(LOItems.ORANGE_MODERN_BLANKET.get());
                        output.accept(LOItems.PINK_MODERN_BLANKET.get());
                        output.accept(LOItems.PURPLE_MODERN_BLANKET.get());
                        output.accept(LOItems.RED_MODERN_BLANKET.get());
                        output.accept(LOItems.WHITE_MODERN_BLANKET.get());
                        output.accept(LOItems.YELLOW_MODERN_BLANKET.get());

                        output.accept(LOItems.BLACK_RACING_BLANKET.get());
                        output.accept(LOItems.BLUE_RACING_BLANKET.get());
                        output.accept(LOItems.BROWN_RACING_BLANKET.get());
                        output.accept(LOItems.CYAN_RACING_BLANKET.get());
                        output.accept(LOItems.GREEN_RACING_BLANKET.get());
                        output.accept(LOItems.GREY_RACING_BLANKET.get());
                        output.accept(LOItems.LIGHT_BLUE_RACING_BLANKET.get());
                        output.accept(LOItems.LIGHT_GREY_RACING_BLANKET.get());
                        output.accept(LOItems.LIME_RACING_BLANKET.get());
                        output.accept(LOItems.MAGENTA_RACING_BLANKET.get());
                        output.accept(LOItems.ORANGE_RACING_BLANKET.get());
                        output.accept(LOItems.PINK_RACING_BLANKET.get());
                        output.accept(LOItems.PURPLE_RACING_BLANKET.get());
                        output.accept(LOItems.RED_RACING_BLANKET.get());
                        output.accept(LOItems.WHITE_RACING_BLANKET.get());
                        output.accept(LOItems.YELLOW_RACING_BLANKET.get());

                        output.accept(LOItems.BLACK_WESTERN_BLANKET.get());
                        output.accept(LOItems.BLUE_WESTERN_BLANKET.get());
                        output.accept(LOItems.BROWN_WESTERN_BLANKET.get());
                        output.accept(LOItems.CYAN_WESTERN_BLANKET.get());
                        output.accept(LOItems.GREEN_WESTERN_BLANKET.get());
                        output.accept(LOItems.GREY_WESTERN_BLANKET.get());
                        output.accept(LOItems.LIGHT_BLUE_WESTERN_BLANKET.get());
                        output.accept(LOItems.LIGHT_GREY_WESTERN_BLANKET.get());
                        output.accept(LOItems.LIME_WESTERN_BLANKET.get());
                        output.accept(LOItems.MAGENTA_WESTERN_BLANKET.get());
                        output.accept(LOItems.ORANGE_WESTERN_BLANKET.get());
                        output.accept(LOItems.PINK_WESTERN_BLANKET.get());
                        output.accept(LOItems.PURPLE_WESTERN_BLANKET.get());
                        output.accept(LOItems.RED_WESTERN_BLANKET.get());
                        output.accept(LOItems.WHITE_WESTERN_BLANKET.get());
                        output.accept(LOItems.YELLOW_WESTERN_BLANKET.get());

                        output.accept(LOItems.AMERICAN_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.AMERICAN_MODERN_BLANKET.get());
                        output.accept(LOItems.AMERICAN_RACING_BLANKET.get());
                        output.accept(LOItems.AMERICAN_WESTERN_BLANKET.get());
                        output.accept(LOItems.AUTUMN_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.AUTUMN_MODERN_BLANKET.get());
                        output.accept(LOItems.AUTUMN_RACING_BLANKET.get());
                        output.accept(LOItems.AUTUMN_WESTERN_BLANKET.get());
                        output.accept(LOItems.ELDERBERRY_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.ELDERBERRY_MODERN_BLANKET.get());
                        output.accept(LOItems.ELDERBERRY_RACING_BLANKET.get());
                        output.accept(LOItems.ELDERBERRY_WESTERN_BLANKET.get());
                        output.accept(LOItems.PEACH_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.PEACH_MODERN_BLANKET.get());
                        output.accept(LOItems.PEACH_RACING_BLANKET.get());
                        output.accept(LOItems.PEACH_WESTERN_BLANKET.get());
                        output.accept(LOItems.SPRING_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.SPRING_MODERN_BLANKET.get());
                        output.accept(LOItems.SPRING_RACING_BLANKET.get());
                        output.accept(LOItems.SPRING_WESTERN_BLANKET.get());
                        output.accept(LOItems.SUMMER_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.SUMMER_MODERN_BLANKET.get());
                        output.accept(LOItems.SUMMER_RACING_BLANKET.get());
                        output.accept(LOItems.SUMMER_WESTERN_BLANKET.get());
                        output.accept(LOItems.WINTER_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.WINTER_MODERN_BLANKET.get());
                        output.accept(LOItems.WINTER_RACING_BLANKET.get());
                        output.accept(LOItems.WINTER_WESTERN_BLANKET.get());


                        if (ModList.get().isLoaded("medievalembroidery")) {

                            output.accept(MECompatItems.LEATHER_RUMP_STRAPS.get());
                            output.accept(MECompatItems.BLACK_RUMP_STRAPS.get());
                            output.accept(MECompatItems.WHITE_RUMP_STRAPS.get());

                            output.accept(MECompatItems.BLACK_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.BLACK_CAPARISON_FULL.get());
                            output.accept(MECompatItems.BLACK_CAPARISON_HALF.get());
                            output.accept(MECompatItems.BLACK_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.WHITE_BLACK_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.WHITE_BLACK_CAPARISON_FULL.get());
                            output.accept(MECompatItems.WHITE_BLACK_CAPARISON_HALF.get());
                            output.accept(MECompatItems.WHITE_BLACK_CAPARISON_SHOULDER.get());

                            output.accept(MECompatItems.BLACK_BLUE_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.BLACK_BLUE_CAPARISON_FULL.get());
                            output.accept(MECompatItems.BLACK_BLUE_CAPARISON_HALF.get());
                            output.accept(MECompatItems.BLACK_BLUE_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.BLUE_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.BLUE_CAPARISON_FULL.get());
                            output.accept(MECompatItems.BLUE_CAPARISON_HALF.get());
                            output.accept(MECompatItems.BLUE_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.WHITE_BLUE_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.WHITE_BLUE_CAPARISON_FULL.get());
                            output.accept(MECompatItems.WHITE_BLUE_CAPARISON_HALF.get());
                            output.accept(MECompatItems.WHITE_BLUE_CAPARISON_SHOULDER.get());

                            output.accept(MECompatItems.BLACK_BROWN_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.BLACK_BROWN_CAPARISON_FULL.get());
                            output.accept(MECompatItems.BLACK_BROWN_CAPARISON_HALF.get());
                            output.accept(MECompatItems.BLACK_BROWN_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.BROWN_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.BROWN_CAPARISON_FULL.get());
                            output.accept(MECompatItems.BROWN_CAPARISON_HALF.get());
                            output.accept(MECompatItems.BROWN_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.WHITE_BROWN_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.WHITE_BROWN_CAPARISON_FULL.get());
                            output.accept(MECompatItems.WHITE_BROWN_CAPARISON_HALF.get());
                            output.accept(MECompatItems.WHITE_BROWN_CAPARISON_SHOULDER.get());

                            output.accept(MECompatItems.BLACK_CYAN_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.BLACK_CYAN_CAPARISON_FULL.get());
                            output.accept(MECompatItems.BLACK_CYAN_CAPARISON_HALF.get());
                            output.accept(MECompatItems.BLACK_CYAN_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.CYAN_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.CYAN_CAPARISON_FULL.get());
                            output.accept(MECompatItems.CYAN_CAPARISON_HALF.get());
                            output.accept(MECompatItems.CYAN_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.WHITE_CYAN_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.WHITE_CYAN_CAPARISON_FULL.get());
                            output.accept(MECompatItems.WHITE_CYAN_CAPARISON_HALF.get());
                            output.accept(MECompatItems.WHITE_CYAN_CAPARISON_SHOULDER.get());

                            output.accept(MECompatItems.BLACK_GREEN_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.BLACK_GREEN_CAPARISON_FULL.get());
                            output.accept(MECompatItems.BLACK_GREEN_CAPARISON_HALF.get());
                            output.accept(MECompatItems.BLACK_GREEN_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.GREEN_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.GREEN_CAPARISON_FULL.get());
                            output.accept(MECompatItems.GREEN_CAPARISON_HALF.get());
                            output.accept(MECompatItems.GREEN_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.WHITE_GREEN_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.WHITE_GREEN_CAPARISON_FULL.get());
                            output.accept(MECompatItems.WHITE_GREEN_CAPARISON_HALF.get());
                            output.accept(MECompatItems.WHITE_GREEN_CAPARISON_SHOULDER.get());

                            output.accept(MECompatItems.BLACK_GREY_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.BLACK_GREY_CAPARISON_FULL.get());
                            output.accept(MECompatItems.BLACK_GREY_CAPARISON_HALF.get());
                            output.accept(MECompatItems.BLACK_GREY_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.GREY_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.GREY_CAPARISON_FULL.get());
                            output.accept(MECompatItems.GREY_CAPARISON_HALF.get());
                            output.accept(MECompatItems.GREY_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.WHITE_GREY_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.WHITE_GREY_CAPARISON_FULL.get());
                            output.accept(MECompatItems.WHITE_GREY_CAPARISON_HALF.get());
                            output.accept(MECompatItems.WHITE_GREY_CAPARISON_SHOULDER.get());

                            output.accept(MECompatItems.BLACK_LIGHT_BLUE_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.BLACK_LIGHT_BLUE_CAPARISON_FULL.get());
                            output.accept(MECompatItems.BLACK_LIGHT_BLUE_CAPARISON_HALF.get());
                            output.accept(MECompatItems.BLACK_LIGHT_BLUE_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.LIGHT_BLUE_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.LIGHT_BLUE_CAPARISON_FULL.get());
                            output.accept(MECompatItems.LIGHT_BLUE_CAPARISON_HALF.get());
                            output.accept(MECompatItems.LIGHT_BLUE_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.WHITE_LIGHT_BLUE_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.WHITE_LIGHT_BLUE_CAPARISON_FULL.get());
                            output.accept(MECompatItems.WHITE_LIGHT_BLUE_CAPARISON_HALF.get());
                            output.accept(MECompatItems.WHITE_LIGHT_BLUE_CAPARISON_SHOULDER.get());

                            output.accept(MECompatItems.BLACK_LIGHT_GREY_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.BLACK_LIGHT_GREY_CAPARISON_FULL.get());
                            output.accept(MECompatItems.BLACK_LIGHT_GREY_CAPARISON_HALF.get());
                            output.accept(MECompatItems.BLACK_LIGHT_GREY_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.LIGHT_GREY_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.LIGHT_GREY_CAPARISON_FULL.get());
                            output.accept(MECompatItems.LIGHT_GREY_CAPARISON_HALF.get());
                            output.accept(MECompatItems.LIGHT_GREY_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.WHITE_LIGHT_GREY_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.WHITE_LIGHT_GREY_CAPARISON_FULL.get());
                            output.accept(MECompatItems.WHITE_LIGHT_GREY_CAPARISON_HALF.get());
                            output.accept(MECompatItems.WHITE_LIGHT_GREY_CAPARISON_SHOULDER.get());

                            output.accept(MECompatItems.BLACK_LIME_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.BLACK_LIME_CAPARISON_FULL.get());
                            output.accept(MECompatItems.BLACK_LIME_CAPARISON_HALF.get());
                            output.accept(MECompatItems.BLACK_LIME_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.LIME_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.LIME_CAPARISON_FULL.get());
                            output.accept(MECompatItems.LIME_CAPARISON_HALF.get());
                            output.accept(MECompatItems.LIME_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.WHITE_LIME_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.WHITE_LIME_CAPARISON_FULL.get());
                            output.accept(MECompatItems.WHITE_LIME_CAPARISON_HALF.get());
                            output.accept(MECompatItems.WHITE_LIME_CAPARISON_SHOULDER.get());

                            output.accept(MECompatItems.BLACK_MAGENTA_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.BLACK_MAGENTA_CAPARISON_FULL.get());
                            output.accept(MECompatItems.BLACK_MAGENTA_CAPARISON_HALF.get());
                            output.accept(MECompatItems.BLACK_MAGENTA_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.MAGENTA_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.MAGENTA_CAPARISON_FULL.get());
                            output.accept(MECompatItems.MAGENTA_CAPARISON_HALF.get());
                            output.accept(MECompatItems.MAGENTA_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.WHITE_MAGENTA_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.WHITE_MAGENTA_CAPARISON_FULL.get());
                            output.accept(MECompatItems.WHITE_MAGENTA_CAPARISON_HALF.get());
                            output.accept(MECompatItems.WHITE_MAGENTA_CAPARISON_SHOULDER.get());

                            output.accept(MECompatItems.BLACK_ORANGE_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.BLACK_ORANGE_CAPARISON_FULL.get());
                            output.accept(MECompatItems.BLACK_ORANGE_CAPARISON_HALF.get());
                            output.accept(MECompatItems.BLACK_ORANGE_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.ORANGE_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.ORANGE_CAPARISON_FULL.get());
                            output.accept(MECompatItems.ORANGE_CAPARISON_HALF.get());
                            output.accept(MECompatItems.ORANGE_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.WHITE_ORANGE_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.WHITE_ORANGE_CAPARISON_FULL.get());
                            output.accept(MECompatItems.WHITE_ORANGE_CAPARISON_HALF.get());
                            output.accept(MECompatItems.WHITE_ORANGE_CAPARISON_SHOULDER.get());

                            output.accept(MECompatItems.BLACK_PINK_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.BLACK_PINK_CAPARISON_FULL.get());
                            output.accept(MECompatItems.BLACK_PINK_CAPARISON_HALF.get());
                            output.accept(MECompatItems.BLACK_PINK_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.PINK_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.PINK_CAPARISON_FULL.get());
                            output.accept(MECompatItems.PINK_CAPARISON_HALF.get());
                            output.accept(MECompatItems.PINK_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.WHITE_PINK_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.WHITE_PINK_CAPARISON_FULL.get());
                            output.accept(MECompatItems.WHITE_PINK_CAPARISON_HALF.get());
                            output.accept(MECompatItems.WHITE_PINK_CAPARISON_SHOULDER.get());

                            output.accept(MECompatItems.BLACK_PURPLE_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.BLACK_PURPLE_CAPARISON_FULL.get());
                            output.accept(MECompatItems.BLACK_PURPLE_CAPARISON_HALF.get());
                            output.accept(MECompatItems.BLACK_PURPLE_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.PURPLE_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.PURPLE_CAPARISON_FULL.get());
                            output.accept(MECompatItems.PURPLE_CAPARISON_HALF.get());
                            output.accept(MECompatItems.PURPLE_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.WHITE_PURPLE_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.WHITE_PURPLE_CAPARISON_FULL.get());
                            output.accept(MECompatItems.WHITE_PURPLE_CAPARISON_HALF.get());
                            output.accept(MECompatItems.WHITE_PURPLE_CAPARISON_SHOULDER.get());

                            output.accept(MECompatItems.BLACK_RED_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.BLACK_RED_CAPARISON_FULL.get());
                            output.accept(MECompatItems.BLACK_RED_CAPARISON_HALF.get());
                            output.accept(MECompatItems.BLACK_RED_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.RED_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.RED_CAPARISON_FULL.get());
                            output.accept(MECompatItems.RED_CAPARISON_HALF.get());
                            output.accept(MECompatItems.RED_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.WHITE_RED_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.WHITE_RED_CAPARISON_FULL.get());
                            output.accept(MECompatItems.WHITE_RED_CAPARISON_HALF.get());
                            output.accept(MECompatItems.WHITE_RED_CAPARISON_SHOULDER.get());

                            output.accept(MECompatItems.BLACK_WHITE_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.BLACK_WHITE_CAPARISON_FULL.get());
                            output.accept(MECompatItems.BLACK_WHITE_CAPARISON_HALF.get());
                            output.accept(MECompatItems.BLACK_WHITE_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.WHITE_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.WHITE_CAPARISON_FULL.get());
                            output.accept(MECompatItems.WHITE_CAPARISON_HALF.get());
                            output.accept(MECompatItems.WHITE_CAPARISON_SHOULDER.get());

                            output.accept(MECompatItems.BLACK_YELLOW_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.BLACK_YELLOW_CAPARISON_FULL.get());
                            output.accept(MECompatItems.BLACK_YELLOW_CAPARISON_HALF.get());
                            output.accept(MECompatItems.BLACK_YELLOW_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.YELLOW_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.YELLOW_CAPARISON_FULL.get());
                            output.accept(MECompatItems.YELLOW_CAPARISON_HALF.get());
                            output.accept(MECompatItems.YELLOW_CAPARISON_SHOULDER.get());
                            output.accept(MECompatItems.WHITE_YELLOW_CAPARISON_CAPE.get());
                            output.accept(MECompatItems.WHITE_YELLOW_CAPARISON_FULL.get());
                            output.accept(MECompatItems.WHITE_YELLOW_CAPARISON_HALF.get());
                            output.accept(MECompatItems.WHITE_YELLOW_CAPARISON_SHOULDER.get());
                        }
                    }).build());

    public static final RegistryObject<CreativeModeTab> LIVESTOCK_OVERHAUL_FOOD_GROUP = CREATIVE_MODE_TABS.register("overhauled_livestock_food",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(LOItems.LIVESTOCK_OVERHAUL_FOOD.get())).title(Component.translatable("itemGroup.overhauled_livestock_food"))
                    .displayItems((displayParameters, output) -> {

                        output.accept(Items.MILK_BUCKET);
                        output.accept(LOItems.SHEEP_MILK_BUCKET.get());
                        output.accept(LOItems.LLAMA_MILK_BUCKET.get());
                        output.accept(LOItems.GOAT_MILK_BUCKET.get());

                        output.accept(LOItems.COW_MILK_JUG.get());
                        output.accept(LOItems.SHEEP_MILK_JUG.get());
                        output.accept(LOItems.LLAMA_MILK_JUG.get());
                        output.accept(LOItems.GOAT_MILK_JUG.get());

                        output.accept(LOItems.RAW_CHEESE.get());
                        output.accept(LOItems.RAW_SHEEP_CHEESE.get());
                        output.accept(LOItems.RAW_LLAMA_CHEESE.get());
                        output.accept(LOItems.RAW_GOAT_CHEESE.get());

                        output.accept(LOItems.CHEESE.get());
                        output.accept(LOItems.SHEEP_CHEESE.get());
                        output.accept(LOItems.LLAMA_CHEESE.get());
                        output.accept(LOItems.GOAT_CHEESE.get());

                        output.accept(LOItems.EGG.get());
                        output.accept(LOItems.AMERAUCANA_EGG.get());
                        output.accept(LOItems.CREAM_LEGBAR_EGG.get());
                        output.accept(LOItems.MARANS_EGG.get());
                        output.accept(LOItems.OLIVE_EGGER_EGG.get());
                        output.accept(LOItems.SUSSEX_SILKIE_EGG.get());
                        output.accept(LOItems.AYAM_CEMANI_EGG.get());

                        output.accept(LOItems.EGG_SALAD.get());
                        output.accept(LOItems.OMELETTE.get());
                        output.accept(LOItems.CHEESECAKE.get());
                        output.accept(LOItems.BERRY_GLAZED_PORK_RIB_CHOP.get());
                        output.accept(LOItems.BERRY_GLAZED_MUTTON_RIB.get());

                        output.accept(LOItems.GRAIN_SOUP.get());
                        output.accept(LOItems.POTATO_SOUP.get());
                        output.accept(LOItems.GLOW_BERRY_SOUP.get());
                        output.accept(LOItems.PUMPKIN_SOUP.get());
                        output.accept(LOItems.CARROT_SOUP.get());
                        output.accept(LOItems.MELON_SOUP.get());
                        output.accept(LOItems.SWEET_BERRY_SOUP.get());

                        output.accept(LOItems.BEEF_RIB_STEAK.get());
                        output.accept(LOItems.BEEF_SIRLOIN_STEAK.get());
                        output.accept(LOItems.COOKED_BEEF_RIB_STEAK.get());
                        output.accept(LOItems.COOKED_BEEF_SIRLOIN_STEAK.get());

                        output.accept(LOItems.HORSE.get());
                        output.accept(LOItems.HORSE_RIB_STEAK.get());
                        output.accept(LOItems.HORSE_SIRLOIN_STEAK.get());
                        output.accept(LOItems.COOKED_HORSE.get());
                        output.accept(LOItems.COOKED_HORSE_RIB_STEAK.get());
                        output.accept(LOItems.COOKED_HORSE_SIRLOIN_STEAK.get());

                        output.accept(LOItems.LLAMA.get());
                        output.accept(LOItems.LLAMA_RIB.get());
                        output.accept(LOItems.LLAMA_LOIN.get());
                        output.accept(LOItems.COOKED_LLAMA.get());
                        output.accept(LOItems.COOKED_LLAMA_RIB.get());
                        output.accept(LOItems.COOKED_LLAMA_LOIN.get());

                        output.accept(LOItems.MUTTON_LOIN.get());
                        output.accept(LOItems.MUTTON_RIB.get());
                        output.accept(LOItems.COOKED_MUTTON_LOIN.get());
                        output.accept(LOItems.COOKED_MUTTON_RIB.get());

                        output.accept(LOItems.PORK_TENDERLOIN.get());
                        output.accept(LOItems.PORK_RIB_CHOP.get());
                        output.accept(LOItems.COOKED_PORK_TENDERLOIN.get());
                        output.accept(LOItems.COOKED_PORK_RIB_CHOP.get());

                        output.accept(LOItems.CAMEL.get());
                        output.accept(LOItems.CAMEL_RIB.get());
                        output.accept(LOItems.CAMEL_LOIN.get());
                        output.accept(LOItems.COOKED_CAMEL.get());
                        output.accept(LOItems.COOKED_CAMEL_RIB.get());
                        output.accept(LOItems.COOKED_CAMEL_LOIN.get());

                        output.accept(LOItems.CHEVON.get());
                        output.accept(LOItems.CHEVON_RIB.get());
                        output.accept(LOItems.CHEVON_LOIN.get());
                        output.accept(LOItems.COOKED_CHEVON.get());
                        output.accept(LOItems.COOKED_CHEVON_RIB.get());
                        output.accept(LOItems.COOKED_CHEVON_LOIN.get());

                        output.accept(LOItems.CHICKEN_THIGH.get());
                        output.accept(LOItems.COOKED_CHICKEN_THIGH.get());

                        output.accept(LOItems.RABBIT_THIGH.get());
                        output.accept(LOItems.COOKED_RABBIT_THIGH.get());

                        output.accept(LOItems.FROG.get());
                        output.accept(LOItems.COOKED_FROG.get());

                        output.accept(LOItems.GRUB.get());
                        output.accept(LOItems.COOKED_GRUB.get());

                        output.accept(LOItems.FISH_OIL.get());
                        output.accept(LOItems.ROE.get());
                        output.accept(LOItems.COD_ROE.get());

                        output.accept(LOItems.SALMON_FILLET.get());
                        output.accept(LOItems.COD_FILLET.get());
                        output.accept(LOItems.COOKED_SALMON_FILLET.get());
                        output.accept(LOItems.COOKED_COD_FILLET.get());

                        output.accept(LOItems.CARIBOU.get());
                        output.accept(LOItems.CARIBOU_RIB_STEAK.get());
                        output.accept(LOItems.CARIBOU_SIRLOIN_STEAK.get());
                        output.accept(LOItems.COOKED_CARIBOU.get());
                        output.accept(LOItems.COOKED_CARIBOU_RIB_STEAK.get());
                        output.accept(LOItems.COOKED_CARIBOU_SIRLOIN_STEAK.get());

                        output.accept(LOItems.UNICORN.get());
                        output.accept(LOItems.UNICORN_RIB_STEAK.get());
                        output.accept(LOItems.UNICORN_SIRLOIN_STEAK.get());
                        output.accept(LOItems.COOKED_UNICORN.get());
                        output.accept(LOItems.COOKED_UNICORN_RIB_STEAK.get());
                        output.accept(LOItems.COOKED_UNICORN_SIRLOIN_STEAK.get());

                        output.accept(LOItems.BEEF_STRIPS.get());
                        output.accept(LOItems.BEEF_JERKY.get());
                        output.accept(LOItems.CHICKEN_STRIPS.get());
                        output.accept(LOItems.CHICKEN_JERKY.get());
                        output.accept(LOItems.PORK_STRIPS.get());
                        output.accept(LOItems.PORK_JERKY.get());
                        output.accept(LOItems.MUTTON_STRIPS.get());
                        output.accept(LOItems.MUTTON_JERKY.get());
                        output.accept(LOItems.FISH_STRIPS.get());
                        output.accept(LOItems.FISH_JERKY.get());
                        output.accept(LOItems.GAME_STRIPS.get());
                        output.accept(LOItems.GAME_JERKY.get());
                        output.accept(LOItems.GENERIC_STRIPS.get());
                        output.accept(LOItems.GENERIC_JERKY.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
