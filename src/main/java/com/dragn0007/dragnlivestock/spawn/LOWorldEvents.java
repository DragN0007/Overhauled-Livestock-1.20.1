package com.dragn0007.dragnlivestock.spawn;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.event.level.SaplingGrowTreeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(modid = LivestockOverhaul.MODID)
public class LOWorldEvents {
    @SubscribeEvent
    public static void onSaplingGrowTreeEvent(SaplingGrowTreeEvent event) {
        if(LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get()) {
            return;
        }

        Holder<ConfiguredFeature<?, ?>> holder = event.getFeature();
        if(holder != null) {
            ConfiguredFeature<?, ?> configuredFeature = holder.get();

            if(configuredFeature.config() instanceof TreeConfiguration treeConfiguration) {
                float probability = (0.002f + 0.01f + 0.02f + 0.05f + 1f) / 5f;
                Optional<ResourceKey<ConfiguredFeature<?, ?>>> optional = holder.unwrapKey();

                if(optional.isPresent()) {
                    ResourceKey<ConfiguredFeature<?, ?>> key = optional.get();
                    if (key.equals(TreeFeatures.SUPER_BIRCH_BEES_0002) || key.equals(TreeFeatures.OAK_BEES_0002) || key.equals(TreeFeatures.BIRCH_BEES_0002) || key.equals(TreeFeatures.FANCY_OAK_BEES_0002)) {
                        probability = 0.002f;
                    } else if(key.equals(TreeFeatures.MANGROVE) || key.equals(TreeFeatures.TALL_MANGROVE)) {
                        probability = 0.01f;
                    } else if(key.equals(TreeFeatures.OAK_BEES_002) || key.equals(TreeFeatures.BIRCH_BEES_002) || key.equals(TreeFeatures.FANCY_OAK_BEES_002)) {
                        probability = 0.02f;
                    } else if(key.equals(TreeFeatures.CHERRY_BEES_005) || key.equals(TreeFeatures.OAK_BEES_005) || key.equals(TreeFeatures.BIRCH_BEES_005) || key.equals(TreeFeatures.FANCY_OAK_BEES_005)) {
                        probability = 0.05f;
                    } else if(key.equals(TreeFeatures.SUPER_BIRCH_BEES) || key.equals(TreeFeatures.FANCY_OAK_BEES)) {
                        probability = 1f;
                    }
                }

                List<TreeDecorator> replacedDecorators = new ArrayList<>();
                for(TreeDecorator treeDecorator : treeConfiguration.decorators) {
                    if(treeDecorator instanceof BeehiveDecorator) {
                        float finalProbability = probability;
                        replacedDecorators.add(new BeehiveDecorator(finalProbability) {
                            private static final Direction[] SPAWN_DIRECTIONS = Direction.Plane.HORIZONTAL.stream().filter((direction) -> direction != Direction.SOUTH).toArray(Direction[]::new);

                            @Override
                            public void place(Context context) {
                                RandomSource randomSource = context.random();
                                if(!(randomSource.nextFloat() >= finalProbability)) {
                                    List<BlockPos> leaves = context.leaves();
                                    List<BlockPos> logs = context.logs();

                                    int y = !leaves.isEmpty() ? Math.max(leaves.get(0).getY() - 1, logs.get(0).getY() + 1) : Math.min(logs.get(0).getY() + 1 + randomSource.nextInt(3), logs.get(logs.size() - 1).getY());
                                    List<BlockPos> placementPositions = new ArrayList<>((logs.stream().filter((blockPos) -> blockPos.getY() == y).flatMap((blockPos) -> Stream.of(SPAWN_DIRECTIONS).map(blockPos::relative)).toList()));

                                    if(!placementPositions.isEmpty()) {
                                        Collections.shuffle(placementPositions);
                                        Optional<BlockPos> optional = placementPositions.stream().filter((blockPos) -> context.isAir(blockPos) && context.isAir(blockPos.relative(Direction.SOUTH))).findFirst();
                                        if(optional.isPresent()) {
                                            context.setBlock(optional.get(), Blocks.BEE_NEST.defaultBlockState().setValue(BeehiveBlock.FACING, Direction.SOUTH));
                                            context.level().getBlockEntity(optional.get(), BlockEntityType.BEEHIVE).ifPresent((beehiveBlockEntity) -> {
                                                int numBees = 2 + randomSource.nextInt(2);

                                                for(int i = 0; i < numBees; i++) {
                                                    CompoundTag compoundTag = new CompoundTag();
                                                    compoundTag.putString("id", EntityTypes.O_BEE_ENTITY.getId().toString());
                                                    beehiveBlockEntity.storeBee(compoundTag, randomSource.nextInt(599), false);
                                                }
                                            });
                                        }
                                    }
                                }
                            }
                        });
                    } else {
                        replacedDecorators.add(treeDecorator);
                    }
                }

                TreeConfiguration.TreeConfigurationBuilder replacedTreeConfigurationBuilder = new TreeConfiguration.TreeConfigurationBuilder(
                        treeConfiguration.trunkProvider,
                        treeConfiguration.trunkPlacer,
                        treeConfiguration.foliageProvider,
                        treeConfiguration.foliagePlacer,
                        treeConfiguration.rootPlacer,
                        treeConfiguration.minimumSize
                ).dirt(treeConfiguration.dirtProvider).decorators(replacedDecorators);

                if(treeConfiguration.forceDirt) {
                    replacedTreeConfigurationBuilder = replacedTreeConfigurationBuilder.forceDirt();
                }

                if(treeConfiguration.ignoreVines) {
                    replacedTreeConfigurationBuilder = replacedTreeConfigurationBuilder.ignoreVines();
                }

                ConfiguredFeature<?, ?> replacedConfiguredFeature = new ConfiguredFeature<>(Feature.TREE, replacedTreeConfigurationBuilder.build());
                Holder<ConfiguredFeature<?, ?>> replacedHolder = new Holder.Direct<>(replacedConfiguredFeature);
                event.setFeature(replacedHolder);
            }
        }
    }


    @SubscribeEvent
    public static void onBlockPlaceEvent(BlockEvent.EntityPlaceEvent event) {
        if(event.getPlacedBlock().getBlock() instanceof BeehiveBlock) {
            BlockEntity blockEntity = event.getLevel().getBlockEntity(event.getPos());
            if(blockEntity instanceof BeehiveBlockEntity beehiveBlockEntity) {
                CompoundTag tag = beehiveBlockEntity.serializeNBT();
                if(tag.contains("Replaced") && tag.getBoolean("Replaced")) {
                    return;
                }

                BeehiveBlockEntity newBeehiveBlockEntity = new BeehiveBlockEntity(event.getPos(), beehiveBlockEntity.getBlockState());
                CompoundTag nbt = tag.copy();
                nbt.putBoolean("Replaced", true);

                ListTag bees = nbt.getList("Bees", 10);
                ListTag oBees = new ListTag();

                for(int i = 0; i < bees.size(); i++) {
                    CompoundTag oldTag = bees.getCompound(i);
                    CompoundTag newTag = new CompoundTag();

                    CompoundTag idTag = new CompoundTag();
                    idTag.putString("id", EntityTypes.O_BEE_ENTITY.getId().toString());

                    newTag.put("EntityData", idTag);
                    newTag.putInt("TicksInHive", oldTag.getInt("TicksInHive"));
                    newTag.putInt("MinOccupationTicks", oldTag.getInt("MinOccupationTicks"));
                    oBees.add(newTag);
                }

                nbt.put("Bees", oBees);
                newBeehiveBlockEntity.deserializeNBT(nbt);
                ((LevelChunk)event.getLevel().getChunk(event.getPos())).addAndRegisterBlockEntity(newBeehiveBlockEntity);
            }
        }
    }

    @SubscribeEvent
    public static void onChunkLoadEvent(ChunkEvent.Load event) {
        if(LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get()) {
            return;
        }

        Set<BlockPos> positions = event.getChunk().getBlockEntitiesPos();
        for(BlockPos pos : positions) {
            BlockEntity blockEntity = event.getChunk().getBlockEntity(pos);
            if(blockEntity instanceof BeehiveBlockEntity beehiveBlockEntity) {
                CompoundTag tag = beehiveBlockEntity.serializeNBT();
                if(tag.contains("Replaced") && tag.getBoolean("Replaced")) {
                    return;
                }

                BeehiveBlockEntity newBeehiveBlockEntity = new BeehiveBlockEntity(pos, beehiveBlockEntity.getBlockState());
                CompoundTag nbt = tag.copy();
                nbt.putBoolean("Replaced", true);

                ListTag bees = nbt.getList("Bees", 10);
                ListTag oBees = new ListTag();

                for(int i = 0; i < bees.size(); i++) {
                    CompoundTag oldTag = bees.getCompound(i);
                    CompoundTag newTag = new CompoundTag();

                    CompoundTag idTag = new CompoundTag();
                    idTag.putString("id", EntityTypes.O_BEE_ENTITY.getId().toString());

                    newTag.put("EntityData", idTag);
                    newTag.putInt("TicksInHive", oldTag.getInt("TicksInHive"));
                    newTag.putInt("MinOccupationTicks", oldTag.getInt("MinOccupationTicks"));
                    oBees.add(newTag);
                }

                nbt.put("Bees", oBees);
                newBeehiveBlockEntity.deserializeNBT(nbt);
                ((LevelChunk)event.getChunk()).addAndRegisterBlockEntity(newBeehiveBlockEntity);
            }
        }
    }

    @SubscribeEvent
    public Codec<? extends BiomeModifier> codec() {
        return LivestockOverhaul.SPAWN_CODEC.get();
    }
}
