package com.dragn0007.dragnlivestock.datagen.biglooter;

import com.dragn0007.dragnlivestock.blocks.LOBlocks;
import com.dragn0007.dragnlivestock.items.LOItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class LOBlockLoot extends BlockLootSubProvider {
    public LOBlockLoot() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropOther(LOBlocks.RAW_BEEF_JERKY_HANGING.get(), LOItems.BEEF_STRIPS.get());
        dropOther(LOBlocks.BEEF_JERKY_HANGING.get(), LOItems.BEEF_JERKY.get());
        dropOther(LOBlocks.RAW_CHICKEN_JERKY_HANGING.get(), LOItems.BEEF_STRIPS.get());
        dropOther(LOBlocks.CHICKEN_JERKY_HANGING.get(), LOItems.BEEF_JERKY.get());
        dropOther(LOBlocks.RAW_PORK_JERKY_HANGING.get(), LOItems.PORK_STRIPS.get());
        dropOther(LOBlocks.PORK_JERKY_HANGING.get(), LOItems.PORK_JERKY.get());
        dropOther(LOBlocks.RAW_MUTTON_JERKY_HANGING.get(), LOItems.MUTTON_STRIPS.get());
        dropOther(LOBlocks.MUTTON_JERKY_HANGING.get(), LOItems.MUTTON_JERKY.get());
        dropOther(LOBlocks.RAW_FISH_JERKY_HANGING.get(), LOItems.FISH_STRIPS.get());
        dropOther(LOBlocks.FISH_JERKY_HANGING.get(), LOItems.FISH_JERKY.get());
        dropOther(LOBlocks.RAW_GAME_JERKY_HANGING.get(),  LOItems.GAME_STRIPS.get());
        dropOther(LOBlocks.GAME_JERKY_HANGING.get(), LOItems.GAME_JERKY.get());
        dropOther(LOBlocks.RAW_GENERIC_JERKY_HANGING.get(), LOItems.GENERIC_STRIPS.get());
        dropOther(LOBlocks.GENERIC_JERKY_HANGING.get(), LOItems.GENERIC_JERKY.get());

        dropOther(LOBlocks.RAW_CHEESE.get(), LOItems.RAW_CHEESE.get());
        dropOther(LOBlocks.CHEESE.get(), LOItems.CHEESE.get());
        dropOther(LOBlocks.RAW_SHEEP_CHEESE.get(), LOItems.RAW_SHEEP_CHEESE.get());
        dropOther(LOBlocks.SHEEP_CHEESE.get(), LOItems.SHEEP_CHEESE.get());
        dropOther(LOBlocks.RAW_LLAMA_CHEESE.get(), LOItems.RAW_LLAMA_CHEESE.get());
        dropOther(LOBlocks.LLAMA_CHEESE.get(), LOItems.LLAMA_CHEESE.get());
        dropOther(LOBlocks.RAW_GOAT_CHEESE.get(), LOItems.RAW_GOAT_CHEESE.get());
        dropOther(LOBlocks.GOAT_CHEESE.get(), LOItems.GOAT_CHEESE.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return LOBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
