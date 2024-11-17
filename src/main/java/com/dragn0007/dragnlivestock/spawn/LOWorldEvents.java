package com.dragn0007.dragnlivestock.spawn;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.event.level.SaplingGrowTreeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = LivestockOverhaul.MODID)
public class LOWorldEvents {
    @SubscribeEvent
    public static void onSaplingGrowTreeEvent(SaplingGrowTreeEvent event) {
        event.getFeature();
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
