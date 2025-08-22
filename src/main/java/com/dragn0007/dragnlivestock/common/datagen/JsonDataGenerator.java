package com.dragn0007.dragnlivestock.common.datagen;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.datagen.biglooter.LOLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = LivestockOverhaul.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JsonDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new LORecipeMaker(packOutput));
        generator.addProvider(event.includeClient(), new LOItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new MECompatItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeServer(), LOLootTableProvider.create(packOutput));
        generator.addProvider(event.includeServer(), new LOPoiTags(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new LOWorldGenerator(packOutput, lookupProvider));
    }
}
