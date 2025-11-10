package com.dragn0007.dragnlivestock.datagen;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.items.LOItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class LOItemModelProvider extends ItemModelProvider {
    public LOItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, LivestockOverhaul.MODID, existingFileHelper);
    }

    @Override
    public void registerModels() {
        simpleItem(LOItems.LIVESTOCK_OVERHAUL);
        simpleItem(LOItems.LIVESTOCK_OVERHAUL_FOOD);
        simpleItem(LOItems.HORSE_COLORS);
        simpleItem(LOItems.HORSE_MARKINGS);
        simpleItem(LOItems.HORSE_BREEDS);
        simpleItem(LOItems.HORSE_EYES);
        simpleItem(LOItems.COW_COLORS);
        simpleItem(LOItems.COW_MARKINGS);
        simpleItem(LOItems.COW_BREEDS);
        simpleItem(LOItems.UNICORN_COLORS);
        simpleItem(LOItems.UNICORN_MARKINGS);
        simpleItem(LOItems.UNICORN_BREEDS);

        simpleItem(LOItems.UNICORN_SPAWN_EGG);

        simpleItem(LOItems.LIGHT_HORSE_ARMOR_SMITHING_TEMPLATE);

        simpleItem(LOItems.CHAINMAIL_HORSE_ARMOR);
        simpleItem(LOItems.COPPER_HORSE_ARMOR);
        simpleItem(LOItems.QUARTZ_HORSE_ARMOR);
        simpleItem(LOItems.EMERALD_HORSE_ARMOR);
        simpleItem(LOItems.NETHERITE_HORSE_ARMOR);
        simpleItem(LOItems.OBSIDIAN_HORSE_ARMOR);
        simpleItem(LOItems.GRIFFITH_INSPIRED_HORSE_ARMOR);
        simpleItem(LOItems.RIOT_HORSE_ARMOR);
        simpleItem(LOItems.MINIMAL_LEATHER_HORSE_ARMOR);
        simpleItem(LOItems.MINIMAL_COPPER_HORSE_ARMOR);
        simpleItem(LOItems.MINIMAL_IRON_HORSE_ARMOR);
        simpleItem(LOItems.MINIMAL_GOLDEN_HORSE_ARMOR);
        simpleItem(LOItems.MINIMAL_QUARTZ_HORSE_ARMOR);
        simpleItem(LOItems.MINIMAL_EMERALD_HORSE_ARMOR);
        simpleItem(LOItems.MINIMAL_DIAMOND_HORSE_ARMOR);
        simpleItem(LOItems.MINIMAL_NETHERITE_HORSE_ARMOR);
        simpleItem(LOItems.MINIMAL_OBSIDIAN_HORSE_ARMOR);
        simpleItem(LOItems.MINIMAL_GRIFFITH_INSPIRED_HORSE_ARMOR);
        simpleItem(LOItems.BLACK_SADDLE);
        simpleItem(LOItems.WHITE_SADDLE);
        simpleItem(LOItems.LIGHT_SADDLE);
        simpleItem(LOItems.BLACK_LIGHT_SADDLE);
        simpleItem(LOItems.WHITE_LIGHT_SADDLE);
        simpleItem(LOItems.HEAVY_SADDLE);
        simpleItem(LOItems.BLACK_HEAVY_SADDLE);
        simpleItem(LOItems.WHITE_HEAVY_SADDLE);
        simpleItem(LOItems.RODEO_HARNESS);
        simpleItem(LOItems.WAGON_HARNESS);
        simpleItem(LOItems.MARTINGALE_HARNESS);

        simpleItem(LOItems.UTILITY_KNIFE);
        simpleItem(LOItems.SPINDLE);
        simpleItem(LOItems.MOUNT_KEY);
        simpleItem(LOItems.COAT_OSCILLATOR);
        simpleItem(LOItems.MARKING_OSCILLATOR);
        simpleItem(LOItems.BREED_OSCILLATOR);

        simpleItem(LOItems.WAGON_WHEEL_FRAME);
        simpleItem(LOItems.WAGON_WHEEL);
        simpleItem(LOItems.WAGON_AXEL);
        simpleItem(LOItems.WAGON_BODY);
        simpleItem(LOItems.WAGON_COVER);

        simpleItem(LOItems.BLACK_BRAND_TAG);
        simpleItem(LOItems.BLUE_BRAND_TAG);
        simpleItem(LOItems.BROWN_BRAND_TAG);
        simpleItem(LOItems.CYAN_BRAND_TAG);
        simpleItem(LOItems.GREEN_BRAND_TAG);
        simpleItem(LOItems.GREY_BRAND_TAG);
        simpleItem(LOItems.LIGHT_BLUE_BRAND_TAG);
        simpleItem(LOItems.LIGHT_GREY_BRAND_TAG);
        simpleItem(LOItems.LIME_BRAND_TAG);
        simpleItem(LOItems.MAGENTA_BRAND_TAG);
        simpleItem(LOItems.ORANGE_BRAND_TAG);
        simpleItem(LOItems.PINK_BRAND_TAG);
        simpleItem(LOItems.PURPLE_BRAND_TAG);
        simpleItem(LOItems.RED_BRAND_TAG);
        simpleItem(LOItems.WHITE_BRAND_TAG);
        simpleItem(LOItems.YELLOW_BRAND_TAG);

        simpleItem(LOItems.BLACK_WOOL_STAPLE);
        simpleItem(LOItems.BLUE_WOOL_STAPLE);
        simpleItem(LOItems.BROWN_WOOL_STAPLE);
        simpleItem(LOItems.CYAN_WOOL_STAPLE);
        simpleItem(LOItems.GREEN_WOOL_STAPLE);
        simpleItem(LOItems.GREY_WOOL_STAPLE);
        simpleItem(LOItems.LIGHT_BLUE_WOOL_STAPLE);
        simpleItem(LOItems.LIGHT_GREY_WOOL_STAPLE);
        simpleItem(LOItems.LIME_WOOL_STAPLE);
        simpleItem(LOItems.MAGENTA_WOOL_STAPLE);
        simpleItem(LOItems.ORANGE_WOOL_STAPLE);
        simpleItem(LOItems.PINK_WOOL_STAPLE);
        simpleItem(LOItems.PURPLE_WOOL_STAPLE);
        simpleItem(LOItems.RED_WOOL_STAPLE);
        simpleItem(LOItems.WHITE_WOOL_STAPLE);
        simpleItem(LOItems.YELLOW_WOOL_STAPLE);

        simpleItem(LOItems.BLACK_WOOL_DYE);
        simpleItem(LOItems.BLUE_WOOL_DYE);
        simpleItem(LOItems.BROWN_WOOL_DYE);
        simpleItem(LOItems.CYAN_WOOL_DYE);
        simpleItem(LOItems.GREEN_WOOL_DYE);
        simpleItem(LOItems.GREY_WOOL_DYE);
        simpleItem(LOItems.LIGHT_BLUE_WOOL_DYE);
        simpleItem(LOItems.LIGHT_GREY_WOOL_DYE);
        simpleItem(LOItems.LIME_WOOL_DYE);
        simpleItem(LOItems.MAGENTA_WOOL_DYE);
        simpleItem(LOItems.ORANGE_WOOL_DYE);
        simpleItem(LOItems.PINK_WOOL_DYE);
        simpleItem(LOItems.PURPLE_WOOL_DYE);
        simpleItem(LOItems.RED_WOOL_DYE);
        simpleItem(LOItems.WHITE_WOOL_DYE);
        simpleItem(LOItems.YELLOW_WOOL_DYE);

        simpleItem(LOItems.NETHERITE_HORSESHOE);
        simpleItem(LOItems.DIAMOND_HORSESHOE);
        simpleItem(LOItems.GOLD_HORSESHOE);
        simpleItem(LOItems.IRON_HORSESHOE);
        simpleItem(LOItems.STONE_HORSESHOE);

        simpleItem(LOItems.TAIL_SCISSORS);
        simpleItem(LOItems.MANE_SCISSORS);

        simpleItem(LOItems.GENDER_TEST_STRIP);
        simpleItem(LOItems.MALE_GENDER_TEST_STRIP);
        simpleItem(LOItems.FEMALE_GENDER_TEST_STRIP);

        simpleItem(LOItems.FERTILIZED_EGG);
        simpleItem(LOItems.FERTILIZED_AMERAUCANA_EGG);
        simpleItem(LOItems.FERTILIZED_CREAM_LEGBAR_EGG);
        simpleItem(LOItems.FERTILIZED_MARANS_EGG);
        simpleItem(LOItems.FERTILIZED_OLIVE_EGGER_EGG);
        simpleItem(LOItems.FERTILIZED_SUSSEX_SILKIE_EGG);
        simpleItem(LOItems.FERTILIZED_AYAM_CEMANI_EGG);

        advancedItem(LOItems.EGG, "fertilized_egg");
        advancedItem(LOItems.AMERAUCANA_EGG, "fertilized_ameraucana_egg");
        advancedItem(LOItems.CREAM_LEGBAR_EGG, "fertilized_cream_legbar_egg");
        advancedItem(LOItems.MARANS_EGG, "fertilized_marans_egg");
        advancedItem(LOItems.OLIVE_EGGER_EGG, "fertilized_olive_egger_egg");
        advancedItem(LOItems.SUSSEX_SILKIE_EGG, "fertilized_sussex_silkie_egg");
        advancedItem(LOItems.AYAM_CEMANI_EGG, "fertilized_ayam_cemani_egg");

//        simpleItem(LOItems.COVERED_WAGON);

        simpleItem(LOItems.GLOW_BERRY_SOUP);
        simpleItem(LOItems.GRAIN_SOUP);
        simpleItem(LOItems.CARROT_SOUP);
        simpleItem(LOItems.SWEET_BERRY_SOUP);
        simpleItem(LOItems.POTATO_SOUP);
        simpleItem(LOItems.PUMPKIN_SOUP);
        simpleItem(LOItems.MELON_SOUP);

        simpleItem(LOItems.SHEEP_MILK_BUCKET);
        simpleItem(LOItems.LLAMA_MILK_BUCKET);
        simpleItem(LOItems.GOAT_MILK_BUCKET);

        simpleItem(LOItems.RAW_CHEESE);
        simpleItem(LOItems.RAW_SHEEP_CHEESE);
        simpleItem(LOItems.RAW_LLAMA_CHEESE);
        simpleItem(LOItems.RAW_GOAT_CHEESE);

        simpleItem(LOItems.CHEESE);
        simpleItem(LOItems.SHEEP_CHEESE);
        simpleItem(LOItems.LLAMA_CHEESE);
        simpleItem(LOItems.GOAT_CHEESE);

        simpleItem(LOItems.COW_MILK_JUG);
        simpleItem(LOItems.SHEEP_MILK_JUG);
        simpleItem(LOItems.LLAMA_MILK_JUG);
        simpleItem(LOItems.GOAT_MILK_JUG);

        simpleItem(LOItems.EGG_SALAD);
        simpleItem(LOItems.OMELETTE);
        simpleItem(LOItems.CHEESECAKE);
        simpleItem(LOItems.BERRY_GLAZED_PORK_RIB_CHOP);
        simpleItem(LOItems.BERRY_GLAZED_MUTTON_RIB);

        simpleItem(LOItems.BEEF_STRIPS);
        simpleItem(LOItems.BEEF_JERKY);
        simpleItem(LOItems.CHICKEN_STRIPS);
        simpleItem(LOItems.CHICKEN_JERKY);
        simpleItem(LOItems.PORK_STRIPS);
        simpleItem(LOItems.PORK_JERKY);
        simpleItem(LOItems.MUTTON_STRIPS);
        simpleItem(LOItems.MUTTON_JERKY);
        simpleItem(LOItems.FISH_STRIPS);
        simpleItem(LOItems.FISH_JERKY);
        simpleItem(LOItems.GAME_STRIPS);
        simpleItem(LOItems.GAME_JERKY);
        simpleItem(LOItems.GENERIC_STRIPS);
        simpleItem(LOItems.GENERIC_JERKY);

        simpleItem(LOItems.BEEF_RIB_STEAK);
        simpleItem(LOItems.BEEF_SIRLOIN_STEAK);
        simpleItem(LOItems.COOKED_BEEF_RIB_STEAK);
        simpleItem(LOItems.COOKED_BEEF_SIRLOIN_STEAK);

        simpleItem(LOItems.HORSE);
        simpleItem(LOItems.HORSE_RIB_STEAK);
        simpleItem(LOItems.HORSE_SIRLOIN_STEAK);
        simpleItem(LOItems.COOKED_HORSE);
        simpleItem(LOItems.COOKED_HORSE_RIB_STEAK);
        simpleItem(LOItems.COOKED_HORSE_SIRLOIN_STEAK);

        simpleItem(LOItems.LLAMA);
        simpleItem(LOItems.LLAMA_RIB);
        simpleItem(LOItems.LLAMA_LOIN);
        simpleItem(LOItems.COOKED_LLAMA);
        simpleItem(LOItems.COOKED_LLAMA_RIB);
        simpleItem(LOItems.COOKED_LLAMA_LOIN);

        simpleItem(LOItems.MUTTON_LOIN);
        simpleItem(LOItems.MUTTON_RIB);
        simpleItem(LOItems.COOKED_MUTTON_LOIN);
        simpleItem(LOItems.COOKED_MUTTON_RIB);

        simpleItem(LOItems.CHEVON);
        simpleItem(LOItems.CHEVON_RIB);
        simpleItem(LOItems.CHEVON_LOIN);
        simpleItem(LOItems.COOKED_CHEVON);
        simpleItem(LOItems.COOKED_CHEVON_LOIN);
        simpleItem(LOItems.COOKED_CHEVON_RIB);

        simpleItem(LOItems.PORK_TENDERLOIN);
        simpleItem(LOItems.PORK_RIB_CHOP);
        simpleItem(LOItems.COOKED_PORK_TENDERLOIN);
        simpleItem(LOItems.COOKED_PORK_RIB_CHOP);

        simpleItem(LOItems.CAMEL);
        simpleItem(LOItems.CAMEL_RIB);
        simpleItem(LOItems.CAMEL_LOIN);
        simpleItem(LOItems.COOKED_CAMEL);
        simpleItem(LOItems.COOKED_CAMEL_RIB);
        simpleItem(LOItems.COOKED_CAMEL_LOIN);

        simpleItem(LOItems.CHICKEN_THIGH);
        simpleItem(LOItems.COOKED_CHICKEN_THIGH);

        simpleItem(LOItems.RABBIT_THIGH);
        simpleItem(LOItems.COOKED_RABBIT_THIGH);

        simpleItem(LOItems.FROG);
        simpleItem(LOItems.COOKED_FROG);

        simpleItem(LOItems.GRUB);
        simpleItem(LOItems.COOKED_GRUB);

        simpleItem(LOItems.FISH_OIL);
        simpleItem(LOItems.ROE);
        simpleItem(LOItems.COD_ROE);

        simpleItem(LOItems.SALMON_FILLET);
        simpleItem(LOItems.COD_FILLET);
        simpleItem(LOItems.COOKED_SALMON_FILLET);
        simpleItem(LOItems.COOKED_COD_FILLET);

        advancedItem(LOItems.CARIBOU, "horse");
        advancedItem(LOItems.CARIBOU_RIB_STEAK, "horse_rib_steak");
        advancedItem(LOItems.CARIBOU_SIRLOIN_STEAK, "horse_sirloin_steak");
        advancedItem(LOItems.COOKED_CARIBOU, "cooked_horse");
        advancedItem(LOItems.COOKED_CARIBOU_RIB_STEAK, "cooked_horse_rib_steak");
        advancedItem(LOItems.COOKED_CARIBOU_SIRLOIN_STEAK, "cooked_horse_sirloin_steak");

        simpleItem(LOItems.UNICORN);
        simpleItem(LOItems.UNICORN_RIB_STEAK);
        simpleItem(LOItems.UNICORN_SIRLOIN_STEAK);
        simpleItem(LOItems.COOKED_UNICORN);
        simpleItem(LOItems.COOKED_UNICORN_RIB_STEAK);
        simpleItem(LOItems.COOKED_UNICORN_SIRLOIN_STEAK);

        simpleItem(LOItems.OVERWORLD_UNICORN_HORN);
        simpleItem(LOItems.NETHER_UNICORN_HORN);
        simpleItem(LOItems.END_UNICORN_HORN);

        simpleItem(LOItems.BLACK_MEDIEVAL_BLANKET);
        simpleItem(LOItems.BLUE_MEDIEVAL_BLANKET);
        simpleItem(LOItems.BROWN_MEDIEVAL_BLANKET);
        simpleItem(LOItems.CYAN_MEDIEVAL_BLANKET);
        simpleItem(LOItems.GREEN_MEDIEVAL_BLANKET);
        simpleItem(LOItems.GREY_MEDIEVAL_BLANKET);
        simpleItem(LOItems.LIGHT_BLUE_MEDIEVAL_BLANKET);
        simpleItem(LOItems.LIGHT_GREY_MEDIEVAL_BLANKET);
        simpleItem(LOItems.LIME_MEDIEVAL_BLANKET);
        simpleItem(LOItems.MAGENTA_MEDIEVAL_BLANKET);
        simpleItem(LOItems.ORANGE_MEDIEVAL_BLANKET);
        simpleItem(LOItems.PINK_MEDIEVAL_BLANKET);
        simpleItem(LOItems.PURPLE_MEDIEVAL_BLANKET);
        simpleItem(LOItems.RED_MEDIEVAL_BLANKET);
        simpleItem(LOItems.WHITE_MEDIEVAL_BLANKET);
        simpleItem(LOItems.YELLOW_MEDIEVAL_BLANKET);

        simpleItem(LOItems.BLACK_MODERN_BLANKET);
        simpleItem(LOItems.BLUE_MODERN_BLANKET);
        simpleItem(LOItems.BROWN_MODERN_BLANKET);
        simpleItem(LOItems.CYAN_MODERN_BLANKET);
        simpleItem(LOItems.GREEN_MODERN_BLANKET);
        simpleItem(LOItems.GREY_MODERN_BLANKET);
        simpleItem(LOItems.LIGHT_BLUE_MODERN_BLANKET);
        simpleItem(LOItems.LIGHT_GREY_MODERN_BLANKET);
        simpleItem(LOItems.LIME_MODERN_BLANKET);
        simpleItem(LOItems.MAGENTA_MODERN_BLANKET);
        simpleItem(LOItems.ORANGE_MODERN_BLANKET);
        simpleItem(LOItems.PINK_MODERN_BLANKET);
        simpleItem(LOItems.PURPLE_MODERN_BLANKET);
        simpleItem(LOItems.RED_MODERN_BLANKET);
        simpleItem(LOItems.WHITE_MODERN_BLANKET);
        simpleItem(LOItems.YELLOW_MODERN_BLANKET);

        simpleItem(LOItems.BLACK_RACING_BLANKET);
        simpleItem(LOItems.BLUE_RACING_BLANKET);
        simpleItem(LOItems.BROWN_RACING_BLANKET);
        simpleItem(LOItems.CYAN_RACING_BLANKET);
        simpleItem(LOItems.GREEN_RACING_BLANKET);
        simpleItem(LOItems.GREY_RACING_BLANKET);
        simpleItem(LOItems.LIGHT_BLUE_RACING_BLANKET);
        simpleItem(LOItems.LIGHT_GREY_RACING_BLANKET);
        simpleItem(LOItems.LIME_RACING_BLANKET);
        simpleItem(LOItems.MAGENTA_RACING_BLANKET);
        simpleItem(LOItems.ORANGE_RACING_BLANKET);
        simpleItem(LOItems.PINK_RACING_BLANKET);
        simpleItem(LOItems.PURPLE_RACING_BLANKET);
        simpleItem(LOItems.RED_RACING_BLANKET);
        simpleItem(LOItems.WHITE_RACING_BLANKET);
        simpleItem(LOItems.YELLOW_RACING_BLANKET);

        simpleItem(LOItems.BLACK_WESTERN_BLANKET);
        simpleItem(LOItems.BLUE_WESTERN_BLANKET);
        simpleItem(LOItems.BROWN_WESTERN_BLANKET);
        simpleItem(LOItems.CYAN_WESTERN_BLANKET);
        simpleItem(LOItems.GREEN_WESTERN_BLANKET);
        simpleItem(LOItems.GREY_WESTERN_BLANKET);
        simpleItem(LOItems.LIGHT_BLUE_WESTERN_BLANKET);
        simpleItem(LOItems.LIGHT_GREY_WESTERN_BLANKET);
        simpleItem(LOItems.LIME_WESTERN_BLANKET);
        simpleItem(LOItems.MAGENTA_WESTERN_BLANKET);
        simpleItem(LOItems.ORANGE_WESTERN_BLANKET);
        simpleItem(LOItems.PINK_WESTERN_BLANKET);
        simpleItem(LOItems.PURPLE_WESTERN_BLANKET);
        simpleItem(LOItems.RED_WESTERN_BLANKET);
        simpleItem(LOItems.WHITE_WESTERN_BLANKET);
        simpleItem(LOItems.YELLOW_WESTERN_BLANKET);

        simpleItem(LOItems.AMERICAN_MEDIEVAL_BLANKET);
        simpleItem(LOItems.AMERICAN_MODERN_BLANKET);
        simpleItem(LOItems.AMERICAN_RACING_BLANKET);
        simpleItem(LOItems.AMERICAN_WESTERN_BLANKET);
        simpleItem(LOItems.AUTUMN_MEDIEVAL_BLANKET);
        simpleItem(LOItems.AUTUMN_MODERN_BLANKET);
        simpleItem(LOItems.AUTUMN_RACING_BLANKET);
        simpleItem(LOItems.AUTUMN_WESTERN_BLANKET);
        simpleItem(LOItems.ELDERBERRY_MEDIEVAL_BLANKET);
        simpleItem(LOItems.ELDERBERRY_MODERN_BLANKET);
        simpleItem(LOItems.ELDERBERRY_RACING_BLANKET);
        simpleItem(LOItems.ELDERBERRY_WESTERN_BLANKET);
        simpleItem(LOItems.PEACH_MEDIEVAL_BLANKET);
        simpleItem(LOItems.PEACH_MODERN_BLANKET);
        simpleItem(LOItems.PEACH_RACING_BLANKET);
        simpleItem(LOItems.PEACH_WESTERN_BLANKET);
        simpleItem(LOItems.SPRING_MEDIEVAL_BLANKET);
        simpleItem(LOItems.SPRING_MODERN_BLANKET);
        simpleItem(LOItems.SPRING_RACING_BLANKET);
        simpleItem(LOItems.SPRING_WESTERN_BLANKET);
        simpleItem(LOItems.SUMMER_MEDIEVAL_BLANKET);
        simpleItem(LOItems.SUMMER_MODERN_BLANKET);
        simpleItem(LOItems.SUMMER_RACING_BLANKET);
        simpleItem(LOItems.SUMMER_WESTERN_BLANKET);
        simpleItem(LOItems.WINTER_MEDIEVAL_BLANKET);
        simpleItem(LOItems.WINTER_MODERN_BLANKET);
        simpleItem(LOItems.WINTER_RACING_BLANKET);
        simpleItem(LOItems.WINTER_WESTERN_BLANKET);

        simpleItem(LOItems.BLACK_GRUB_SWEATER);
        simpleItem(LOItems.BLUE_GRUB_SWEATER);
        simpleItem(LOItems.BROWN_GRUB_SWEATER);
        simpleItem(LOItems.CYAN_GRUB_SWEATER);
        simpleItem(LOItems.GREEN_GRUB_SWEATER);
        simpleItem(LOItems.GREY_GRUB_SWEATER);
        simpleItem(LOItems.LIGHT_BLUE_GRUB_SWEATER);
        simpleItem(LOItems.LIGHT_GREY_GRUB_SWEATER);
        simpleItem(LOItems.LIME_GRUB_SWEATER);
        simpleItem(LOItems.MAGENTA_GRUB_SWEATER);
        simpleItem(LOItems.ORANGE_GRUB_SWEATER);
        simpleItem(LOItems.PINK_GRUB_SWEATER);
        simpleItem(LOItems.PURPLE_GRUB_SWEATER);
        simpleItem(LOItems.RED_GRUB_SWEATER);
        simpleItem(LOItems.WHITE_GRUB_SWEATER);
        simpleItem(LOItems.YELLOW_GRUB_SWEATER);

        simpleItem(LOItems.HALLOW_HEART);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(LivestockOverhaul.MODID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder advancedItem(RegistryObject<Item> item, String getTextureName) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(LivestockOverhaul.MODID,"item/" + getTextureName));
    }

    private ItemModelBuilder simpleMedievalItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation("medievalembroidery","item/" + item.getId().getPath()));
    }
}