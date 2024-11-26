package com.dragn0007.dragnlivestock.spawn;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

import java.util.List;

public record CreatureSpawnGeneration(HolderSet<Biome> biomes) implements BiomeModifier {

    public static List<MobSpawnSettings.SpawnerData> GRUB_SPAWNS = List.of(
            new MobSpawnSettings.SpawnerData(EntityTypes.GRUB_ENTITY.get(), 10, 1, 2)
    );

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if(phase == Phase.ADD && biomes.contains(biome)) {
            List<MobSpawnSettings.SpawnerData> spawner = builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE);

            if(LivestockOverhaulCommonConfig.SPAWN_GRUBS.get() &&
                    (biome.is(Biomes.FLOWER_FOREST) || biome.is(Biomes.JUNGLE) || biome.is(Biomes.LUSH_CAVES) || biome.is(Biomes.SWAMP) || biome.is(Biomes.MANGROVE_SWAMP))) {
                spawner.addAll(GRUB_SPAWNS);
            }
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return LivestockOverhaul.SPAWN_CODEC.get();
    }
}
