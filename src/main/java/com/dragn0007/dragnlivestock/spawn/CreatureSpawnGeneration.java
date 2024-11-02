package com.dragn0007.dragnlivestock.spawn;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
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

    public static List<MobSpawnSettings.SpawnerData> OVERWORLD_UNICORN_SPAWNS = List.of(
            new MobSpawnSettings.SpawnerData(EntityTypes.OVERWORLD_UNICORN_ENTITY.get(), 1, 1, 1)
    );

    public static List<MobSpawnSettings.SpawnerData> NETHER_UNICORN_SPAWNS = List.of(
            new MobSpawnSettings.SpawnerData(EntityTypes.NETHER_UNICORN_ENTITY.get(), 1, 1, 1)
    );

    public static List<MobSpawnSettings.SpawnerData> END_UNICORN_SPAWNS = List.of(
            new MobSpawnSettings.SpawnerData(EntityTypes.END_UNICORN_ENTITY.get(), 1, 1, 1)
    );

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if(phase == Phase.ADD && biomes.contains(biome)) {
            List<MobSpawnSettings.SpawnerData> spawner = builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE);

            if(biome.is(Biomes.MEADOW)) {
                spawner.addAll(OVERWORLD_UNICORN_SPAWNS);
            }

            if(biome.is(Biomes.BASALT_DELTAS)) {
                spawner.addAll(NETHER_UNICORN_SPAWNS);
            }

            if(biome.is(Biomes.END_HIGHLANDS)) {
                spawner.addAll(END_UNICORN_SPAWNS);
            }
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return LivestockOverhaul.SPAWN_CODEC.get();
    }
}
