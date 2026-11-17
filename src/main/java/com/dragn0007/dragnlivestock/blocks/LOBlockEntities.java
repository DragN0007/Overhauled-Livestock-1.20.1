package com.dragn0007.dragnlivestock.blocks;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.blocks.custom.AnimalCrateBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LOBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, LivestockOverhaul.MODID);

    public static final RegistryObject<BlockEntityType<AnimalCrateBlockEntity>> ANIMAL_CRATE =
            BLOCK_ENTITIES.register("animal_crate_entity", () ->
                    BlockEntityType.Builder.of(AnimalCrateBlockEntity::new,
                            LOBlocks.ANIMAL_CRATE.get()
                    ).build(null));

    public static void register(IEventBus eventBus) {BLOCK_ENTITIES.register(eventBus);}
}