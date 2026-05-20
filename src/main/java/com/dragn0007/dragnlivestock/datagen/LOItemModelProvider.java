package com.dragn0007.dragnlivestock.datagen;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.items.LOItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
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

        simpleItem(LOItems.O_HORSE_SPAWN_EGG);
        simpleItem(LOItems.O_MULE_SPAWN_EGG);
        simpleItem(LOItems.O_DONKEY_SPAWN_EGG);
        simpleItem(LOItems.O_CAMEL_SPAWN_EGG);
        simpleItem(LOItems.O_PIG_SPAWN_EGG);
        simpleItem(LOItems.O_COW_SPAWN_EGG);
        simpleItem(LOItems.O_CHICKEN_SPAWN_EGG);
        simpleItem(LOItems.O_SHEEP_SPAWN_EGG);
        simpleItem(LOItems.O_LLAMA_SPAWN_EGG);
        simpleItem(LOItems.O_GOAT_SPAWN_EGG);
        simpleItem(LOItems.O_RABBIT_SPAWN_EGG);
        simpleItem(LOItems.O_FROG_SPAWN_EGG);
        simpleItem(LOItems.O_MOOSHROOM_SPAWN_EGG);
        simpleItem(LOItems.O_SALMON_SPAWN_EGG);
        simpleItem(LOItems.O_COD_SPAWN_EGG);
        simpleItem(LOItems.O_BEE_SPAWN_EGG);
        simpleItem(LOItems.AZALEA_MOOBLOOM_SPAWN_EGG);
        simpleItem(LOItems.BEETROOT_MOOBLOOM_SPAWN_EGG);
        simpleItem(LOItems.CARROT_MOOBLOOM_SPAWN_EGG);
        simpleItem(LOItems.FLOWERING_MOOBLOOM_SPAWN_EGG);
        simpleItem(LOItems.GLOW_BERRY_MOOBLOOM_SPAWN_EGG);
        simpleItem(LOItems.MELON_MOOBLOOM_SPAWN_EGG);
        simpleItem(LOItems.PEACH_MOOBLOOM_SPAWN_EGG);
        simpleItem(LOItems.POTATO_MOOBLOOM_SPAWN_EGG);
        simpleItem(LOItems.PUMPKIN_MOOBLOOM_SPAWN_EGG);
        simpleItem(LOItems.SWEET_BERRY_MOOBLOOM_SPAWN_EGG);
        simpleItem(LOItems.WHEAT_MOOBLOOM_SPAWN_EGG);
        simpleItem(LOItems.GRUB_SPAWN_EGG);
        simpleItem(LOItems.FARM_GOAT_SPAWN_EGG);
        simpleItem(LOItems.CARIBOU_SPAWN_EGG);
        simpleItem(LOItems.UNICORN_SPAWN_EGG);

        simpleItem(LOItems.MAGNIFYING_GLASS);
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

        simpleItem(LOItems.UTILITY_KNIFE);
        simpleItem(LOItems.SPINDLE);
        simpleItem(LOItems.MOUNT_KEY);
        simpleItem(LOItems.MOUNT_REGISTRY);
        simpleItem(LOItems.COAT_OSCILLATOR);
        simpleItem(LOItems.MARKING_OSCILLATOR);
        simpleItem(LOItems.BREED_OSCILLATOR);

        simpleItem(LOItems.WAGON_WHEEL_FRAME);
        simpleItem(LOItems.WAGON_WHEEL);
        simpleItem(LOItems.WAGON_AXEL);
        simpleItem(LOItems.WAGON_BODY);
        simpleItem(LOItems.WAGON_COVER);

        for (DyeColor color : DyeColor.values()) {
            simpleItem(LOItems.WOOL_STAPLES.get(color));
            simpleItem(LOItems.WOOL_DYE.get(color));
            simpleItem(LOItems.BRAND_TAGS.get(color));
            simpleItem(LOItems.GRUB_SWEATERS.get(color));
            simpleItem(LOItems.MEDIEVAL_BLANKETS.get(color));
            simpleItem(LOItems.MODERN_BLANKETS.get(color));
            simpleItem(LOItems.RACING_BLANKETS.get(color));
            simpleItem(LOItems.WESTERN_BLANKETS.get(color));
            simpleItem(LOItems.ACCENTED_SADDLES.get(color));
            simpleItem(LOItems.ACCENTED_LIGHT_SADDLES.get(color));
            simpleItem(LOItems.ACCENTED_HEAVY_SADDLES.get(color));
        }

        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.BLACK)
                continue;
            simpleItem(LOItems.ACCENTED_BLACK_SADDLES.get(color));
            simpleItem(LOItems.ACCENTED_BLACK_LIGHT_SADDLES.get(color));
            simpleItem(LOItems.ACCENTED_BLACK_HEAVY_SADDLES.get(color));
        }

        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.WHITE)
                continue;
            simpleItem(LOItems.ACCENTED_WHITE_SADDLES.get(color));
            simpleItem(LOItems.ACCENTED_WHITE_LIGHT_SADDLES.get(color));
            simpleItem(LOItems.ACCENTED_WHITE_HEAVY_SADDLES.get(color));
        }

        simpleItem(LOItems.TAIL_SCISSORS);
        simpleItem(LOItems.MANE_SCISSORS);

        simpleItem(LOItems.RABBIT_POOP);

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
        simpleItem(LOItems.FERTILIZED_ORPINGTON_EGG);
        simpleItem(LOItems.FERTILIZED_POLISH_EGG);
        simpleItem(LOItems.FERTILIZED_WYANDOTTE_EGG);
        simpleItem(LOItems.FERTILIZED_BRAHMA_EGG);

        advancedItem(LOItems.EGG, "fertilized_egg");
        advancedItem(LOItems.AMERAUCANA_EGG, "fertilized_ameraucana_egg");
        advancedItem(LOItems.CREAM_LEGBAR_EGG, "fertilized_cream_legbar_egg");
        advancedItem(LOItems.MARANS_EGG, "fertilized_marans_egg");
        advancedItem(LOItems.OLIVE_EGGER_EGG, "fertilized_olive_egger_egg");
        advancedItem(LOItems.SUSSEX_SILKIE_EGG, "fertilized_sussex_silkie_egg");
        advancedItem(LOItems.AYAM_CEMANI_EGG, "fertilized_ayam_cemani_egg");
        advancedItem(LOItems.ORPINGTON_EGG, "fertilized_orpington_egg");
        advancedItem(LOItems.POLISH_EGG, "fertilized_polish_egg");
        advancedItem(LOItems.WYANDOTTE_EGG, "fertilized_wyandotte_egg");
        advancedItem(LOItems.BRAHMA_EGG, "fertilized_brahma_egg");

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

        simpleItem(LOItems.HORSE);
        simpleItem(LOItems.COOKED_HORSE);
        simpleItem(LOItems.LLAMA);
        simpleItem(LOItems.COOKED_LLAMA);
        simpleItem(LOItems.CHEVON);
        simpleItem(LOItems.COOKED_CHEVON);
        simpleItem(LOItems.CAMEL);
        simpleItem(LOItems.COOKED_CAMEL);
        simpleItem(LOItems.FROG);
        simpleItem(LOItems.COOKED_FROG);
        simpleItem(LOItems.GRUB);
        simpleItem(LOItems.COOKED_GRUB);
        advancedItem(LOItems.CARIBOU, "horse");
        advancedItem(LOItems.COOKED_CARIBOU, "cooked_horse");
        simpleItem(LOItems.UNICORN);
        simpleItem(LOItems.COOKED_UNICORN);

        simpleItem(LOItems.FISH_OIL);
        simpleItem(LOItems.ROE);
        simpleItem(LOItems.COD_ROE);

        simpleItem(LOItems.OVERWORLD_UNICORN_HORN);
        simpleItem(LOItems.NETHER_UNICORN_HORN);
        simpleItem(LOItems.END_UNICORN_HORN);

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
        simpleItem(LOItems.PRIDE_MEDIEVAL_BLANKET);
        simpleItem(LOItems.PRIDE_MODERN_BLANKET);
        simpleItem(LOItems.PRIDE_RACING_BLANKET);
        simpleItem(LOItems.PRIDE_WESTERN_BLANKET);
        simpleItem(LOItems.LESBIAN_MEDIEVAL_BLANKET);
        simpleItem(LOItems.LESBIAN_MODERN_BLANKET);
        simpleItem(LOItems.LESBIAN_RACING_BLANKET);
        simpleItem(LOItems.LESBIAN_WESTERN_BLANKET);
        simpleItem(LOItems.BI_MEDIEVAL_BLANKET);
        simpleItem(LOItems.BI_MODERN_BLANKET);
        simpleItem(LOItems.BI_RACING_BLANKET);
        simpleItem(LOItems.BI_WESTERN_BLANKET);
        simpleItem(LOItems.NONBINARY_MEDIEVAL_BLANKET);
        simpleItem(LOItems.NONBINARY_MODERN_BLANKET);
        simpleItem(LOItems.NONBINARY_RACING_BLANKET);
        simpleItem(LOItems.NONBINARY_WESTERN_BLANKET);
        simpleItem(LOItems.TRANS_MEDIEVAL_BLANKET);
        simpleItem(LOItems.TRANS_MODERN_BLANKET);
        simpleItem(LOItems.TRANS_RACING_BLANKET);
        simpleItem(LOItems.TRANS_WESTERN_BLANKET);

        simpleItem(LOItems.HOLIDAY_SADDLE);
        simpleItem(LOItems.HOLIDAY_LIGHT_SADDLE);
        simpleItem(LOItems.HOLIDAY_HEAVY_SADDLE);
        simpleItem(LOItems.HOLIDAY_WAGON_HARNESS);
        simpleItem(LOItems.RAINBOW_STRING_LIGHTS);
        simpleItem(LOItems.BLUE_STRING_LIGHTS);
        simpleItem(LOItems.RED_STRING_LIGHTS);
        simpleItem(LOItems.YELLOW_STRING_LIGHTS);
        simpleItem(LOItems.RED_NOSE);
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