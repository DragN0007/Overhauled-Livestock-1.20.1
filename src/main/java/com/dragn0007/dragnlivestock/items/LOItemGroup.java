package com.dragn0007.dragnlivestock.items;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class LOItemGroup {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LivestockOverhaul.MODID);

    public static final RegistryObject<CreativeModeTab> LIVESTOCK_OVERHAUL_GROUP = CREATIVE_MODE_TABS.register("overhauled_livestock",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(LOItems.LIVESTOCK_OVERHAUL.get())).title(Component.translatable("itemGroup.overhauled_livestock"))
                    .displayItems((displayParameters, output) -> {

                        output.accept(LOItems.O_HORSE_SPAWN_EGG.get());
                        output.accept(LOItems.O_COW_SPAWN_EGG.get());
                        output.accept(LOItems.O_CHICKEN_SPAWN_EGG.get());
                        output.accept(LOItems.O_SALMON_SPAWN_EGG.get());
                        output.accept(LOItems.O_COD_SPAWN_EGG.get());
                        output.accept(LOItems.O_BEE_SPAWN_EGG.get());
                        output.accept(LOItems.O_RABBIT_SPAWN_EGG.get());
                        output.accept(LOItems.O_SHEEP_SPAWN_EGG.get());
                        output.accept(LOItems.O_LLAMA_SPAWN_EGG.get());
                        output.accept(LOItems.O_PIG_SPAWN_EGG.get());
                        output.accept(LOItems.O_DONKEY_SPAWN_EGG.get());
                        output.accept(LOItems.O_MULE_SPAWN_EGG.get());
                        output.accept(LOItems.O_MOOSHROOM_SPAWN_EGG.get());
                        output.accept(LOItems.O_CAMEL_SPAWN_EGG.get());
                        output.accept(LOItems.OX_SPAWN_EGG.get());
                        output.accept(LOItems.OVERWORLD_UNICORN_SPAWN_EGG.get());
                        output.accept(LOItems.NETHER_UNICORN_SPAWN_EGG.get());
                        output.accept(LOItems.END_UNICORN_SPAWN_EGG.get());

                        output.accept(LOItems.NETHERITE_HORSE_ARMOR.get());

//                        output.accept(LOItems.COVERED_WAGON.get());

                        output.accept(LOItems.FERTILIZED_EGG.get());
                        output.accept(LOItems.EGG.get());
                        output.accept(LOItems.GENDER_TEST_STRIP.get());

                        output.accept(Items.MILK_BUCKET);
                        output.accept(LOItems.SHEEP_MILK_BUCKET.get());
                        output.accept(LOItems.LLAMA_MILK_BUCKET.get());
                        output.accept(LOItems.COW_MILK_JUG.get());
                        output.accept(LOItems.SHEEP_MILK_JUG.get());
                        output.accept(LOItems.LLAMA_MILK_JUG.get());
                        
                        output.accept(LOItems.EGG_SALAD.get());

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

                        output.accept(LOItems.CHICKEN_THIGH.get());
                        output.accept(LOItems.COOKED_CHICKEN_THIGH.get());

                        output.accept(LOItems.RABBIT_THIGH.get());
                        output.accept(LOItems.COOKED_RABBIT_THIGH.get());

                        output.accept(LOItems.FISH_OIL.get());
                        output.accept(LOItems.ROE.get());

                        output.accept(LOItems.UNICORN.get());
                        output.accept(LOItems.UNICORN_RIB_STEAK.get());
                        output.accept(LOItems.UNICORN_SIRLOIN_STEAK.get());
                        output.accept(LOItems.COOKED_UNICORN.get());
                        output.accept(LOItems.COOKED_UNICORN_RIB_STEAK.get());
                        output.accept(LOItems.COOKED_UNICORN_SIRLOIN_STEAK.get());

                        output.accept(LOItems.OVERWORLD_UNICORN_HORN.get());
                        output.accept(LOItems.NETHER_UNICORN_HORN.get());
                        output.accept(LOItems.END_UNICORN_HORN.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
