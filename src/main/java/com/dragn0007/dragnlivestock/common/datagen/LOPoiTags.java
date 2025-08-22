package com.dragn0007.dragnlivestock.common.datagen;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.tags.PoiTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class LOPoiTags extends PoiTypeTagsProvider {
    public LOPoiTags(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, LivestockOverhaul.MODID, existingFileHelper);
    }

    @Override
    public void addTags(HolderLookup.Provider pProvider) {
        tag(PoiTypeTags.ACQUIRABLE_JOB_SITE)
                .addOptional(LivestockOverhaul.id("livestock_trader_poi"));
    }
}
