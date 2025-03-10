package com.dragn0007.dragnlivestock.items;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
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
                        output.accept(LOItems.O_UNDEAD_HORSE_SPAWN_EGG.get());
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
                        output.accept(LOItems.OX_SPAWN_EGG.get());
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
                        output.accept(LOItems.OVERWORLD_UNICORN_SPAWN_EGG.get());
                        output.accept(LOItems.NETHER_UNICORN_SPAWN_EGG.get());
                        output.accept(LOItems.END_UNICORN_SPAWN_EGG.get());

//                        output.accept(LOItems.NETHERITE_HORSESHOE.get());
//                        output.accept(LOItems.DIAMOND_HORSESHOE.get());
//                        output.accept(LOItems.GOLD_HORSESHOE.get());
//                        output.accept(LOItems.IRON_HORSESHOE.get());
//                        output.accept(LOItems.STONE_HORSESHOE.get());

                        output.accept(LOItems.UTILITY_KNIFE.get());

                        output.accept(LOItems.GRUB_SWEATER.get());

                        output.accept(LOItems.MANE_SCISSORS.get());
                        output.accept(LOItems.TAIL_SCISSORS.get());

                        output.accept(LOItems.CHAINMAIL_HORSE_ARMOR.get());
                        output.accept(LOItems.NETHERITE_HORSE_ARMOR.get());
//                        output.accept(LOItems.BLACK_SADDLE.get());
//                        output.accept(LOItems.WHITE_SADDLE.get());

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
