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
    protected void registerModels() {
        simpleItem(LOItems.LIVESTOCK_OVERHAUL);

        simpleItem(LOItems.OVERWORLD_UNICORN_SPAWN_EGG);
        simpleItem(LOItems.NETHER_UNICORN_SPAWN_EGG);
        simpleItem(LOItems.END_UNICORN_SPAWN_EGG);

        simpleItem(LOItems.BRAND_TAG);
        simpleItem(LOItems.NETHERITE_HORSE_ARMOR);
        simpleItem(LOItems.GRIFFITH_INSPIRED_HORSE_ARMOR);

        simpleItem(LOItems.SHEEP_MILK_BUCKET);
        simpleItem(LOItems.LLAMA_MILK_BUCKET);

        simpleItem(LOItems.CHEESE);
        simpleItem(LOItems.SHEEP_CHEESE);
        simpleItem(LOItems.LLAMA_CHEESE);

        simpleItem(LOItems.COW_MILK_JUG);
        simpleItem(LOItems.SHEEP_MILK_JUG);
        simpleItem(LOItems.LLAMA_MILK_JUG);

        simpleItem(LOItems.EGG_SALAD);

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

        simpleItem(LOItems.PORK_TENDERLOIN);
        simpleItem(LOItems.PORK_RIB_CHOP);
        simpleItem(LOItems.COOKED_PORK_TENDERLOIN);
        simpleItem(LOItems.COOKED_PORK_RIB_CHOP);

        simpleItem(LOItems.CHICKEN_THIGH);
        simpleItem(LOItems.COOKED_CHICKEN_THIGH);

        simpleItem(LOItems.RABBIT_THIGH);
        simpleItem(LOItems.COOKED_RABBIT_THIGH);

        simpleItem(LOItems.FISH_OIL);
        simpleItem(LOItems.ROE);

        simpleItem(LOItems.UNICORN);
        simpleItem(LOItems.UNICORN_RIB_STEAK);
        simpleItem(LOItems.UNICORN_SIRLOIN_STEAK);
        simpleItem(LOItems.COOKED_UNICORN);
        simpleItem(LOItems.COOKED_UNICORN_RIB_STEAK);
        simpleItem(LOItems.COOKED_UNICORN_SIRLOIN_STEAK);

        simpleItem(LOItems.OVERWORLD_UNICORN_HORN);
        simpleItem(LOItems.NETHER_UNICORN_HORN);
        simpleItem(LOItems.END_UNICORN_HORN);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(LivestockOverhaul.MODID,"item/" + item.getId().getPath()));
    }
}