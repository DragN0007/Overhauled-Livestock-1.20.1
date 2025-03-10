package com.dragn0007.dragnlivestock.util;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class LOTags {

    public static class Items {

        public static final TagKey<Item> O_BEE_EATS = tag("o_bee_eats");
        public static final TagKey<Item> O_CAMEL_EATS = tag("o_camel_eats");
        public static final TagKey<Item> CARIBOU_EATS = tag("caribou_eats");
        public static final TagKey<Item> O_CHICKEN_EATS = tag("o_chicken_eats");
        public static final TagKey<Item> O_COW_EATS = tag("o_cow_eats");
        public static final TagKey<Item> O_FROG_EATS = tag("o_frog_eats");
        public static final TagKey<Item> O_GOAT_EATS = tag("o_goat_eats");
        public static final TagKey<Item> O_HORSE_EATS = tag("o_horse_eats");
        public static final TagKey<Item> O_LLAMA_EATS = tag("o_llama_eats");
        public static final TagKey<Item> O_PIG_EATS = tag("o_pig_eats");
        public static final TagKey<Item> O_RABBIT_EATS = tag("o_rabbit_eats");
        public static final TagKey<Item> O_SHEEP_EATS = tag("o_sheep_eats");
        public static final TagKey<Item> GRUB_EATS = tag("grub_eats");

        public static final TagKey<Item> RAW_CHICKEN = forgeTag("raw_chicken");
        public static final TagKey<Item> RAW_PORK = forgeTag("raw_pork");
        public static final TagKey<Item> RAW_MUTTON = forgeTag("raw_mutton");
        public static final TagKey<Item> RAW_BEEF = forgeTag("raw_beef");
        public static final TagKey<Item> RAW_RABBIT = forgeTag("raw_rabbit");
        public static final TagKey<Item> COOKED_CHICKEN = forgeTag("cooked_chicken");
        public static final TagKey<Item> COOKED_PORK = forgeTag("cooked_pork");
        public static final TagKey<Item> COOKED_MUTTON = forgeTag("cooked_mutton");
        public static final TagKey<Item> COOKED_BEEF = forgeTag("cooked_beef");
        public static final TagKey<Item> COOKED_RABBIT = forgeTag("cooked_rabbit");

        public static final TagKey<Item> RAW_HORSE = forgeTag("raw_horse");
        public static final TagKey<Item> RAW_LLAMA = forgeTag("raw_llama");
        public static final TagKey<Item> RAW_UNICORN = forgeTag("raw_unicorn");
        public static final TagKey<Item> COOKED_HORSE = forgeTag("cooked_horse");
        public static final TagKey<Item> COOKED_LLAMA = forgeTag("cooked_llama");
        public static final TagKey<Item> COOKED_UNICORN = forgeTag("cooked_unicorn");

        public static final TagKey<Item> MAKES_BEEF_JERKY = tag("makes_beef_jerky");
        public static final TagKey<Item> MAKES_CHICKEN_JERKY = tag("makes_chicken_jerky");
        public static final TagKey<Item> MAKES_PORK_JERKY = tag("makes_pork_jerky");
        public static final TagKey<Item> MAKES_MUTTON_JERKY = tag("makes_mutton_jerky");
        public static final TagKey<Item> MAKES_FISH_JERKY = tag("makes_fish_jerky");
        public static final TagKey<Item> MAKES_GAME_JERKY = tag("makes_game_jerky");
        public static final TagKey<Item> MAKES_GENERIC_JERKY = tag("makes_generic_jerky");

        public static final TagKey<Item> RAW_MEATS = forgeTag("cooked_fishes");
        public static final TagKey<Item> COOKED_MEATS = forgeTag("cooked_meats");

        public static final TagKey<Item> RAW_FISHES = forgeTag("raw_fishes");
        public static final TagKey<Item> COOKED_FISHES = forgeTag("cooked_fishes");

        public static final TagKey<Item> ROE = forgeTag("roe");
        public static final TagKey<Item> CHEESE = forgeTag("cheese");
        public static final TagKey<Item> MILK = forgeTag("milk");
        public static final TagKey<Item> EGG = forgeTag("egg");
        public static final TagKey<Item> VEGETABLES = forgeTag("vegetables");

        public static final TagKey<Item> SADDLE = tag("saddle");

        public static final TagKey<Item> SWEM_CANTAZARITE_POTION = forgeTag("swem_cantazarite_potion");

        public static final TagKey<Item> CAN_PLACE_ON_O_MOUNTS = tag("can_place_on_o_mounts");

        public static TagKey<Item> forgeTag (String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
        public static TagKey<Item> tag (String name) {
            return ItemTags.create(new ResourceLocation(LivestockOverhaul.MODID, name));
        }
    }

    public static class Entity_Types {
        public static final TagKey<EntityType<?>> HORSES = forgeTag("horses");
        public static final TagKey<EntityType<?>> WOLVES = forgeTag("wolves");
        public static final TagKey<EntityType<?>> FOXES = forgeTag("foxes");
        public static final TagKey<EntityType<?>> CATS = forgeTag("cats");
        public static final TagKey<EntityType<?>> HERDING_DOGS = forgeTag("herding_dogs");
        public static final TagKey<EntityType<?>> DOGS = forgeTag("dogs");
        public static final TagKey<EntityType<?>> HUNTING_DOGS = forgeTag("hunting_dogs");

        public static TagKey<EntityType<?>> forgeTag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("forge", name));
        }
    }

    public static class Blocks {

        public static final TagKey<Block> SAND = forgeTag("sand");
        public static final TagKey<Block> DIRT = forgeTag("dirt");
        public static final TagKey<Block> RABBIT_HUTCHES = forgeTag("rabbit_hutches");

        public static TagKey<Block> forgeTag (String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

}
