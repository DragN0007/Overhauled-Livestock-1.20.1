package com.dragn0007.dragnlivestock;

import com.dragn0007.dragnlivestock.blocks.LOBlocks;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.gui.LOMenuTypes;
import com.dragn0007.dragnlivestock.items.LOItemGroup;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.spawn.CreatureSpawnGeneration;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import software.bernie.geckolib.GeckoLib;


@Mod(LivestockOverhaul.MODID)
public class LivestockOverhaul
{
    public static final String MODID = "dragnlivestock";

    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MODID);
    public static final RegistryObject<Codec<CreatureSpawnGeneration>> SPAWN_CODEC = BIOME_MODIFIER_SERIALIZERS.register("spawn_biome_modifier",
            () -> RecordCodecBuilder.create(builder ->
                    builder.group(Biome.LIST_CODEC.fieldOf("biomes").forGetter(CreatureSpawnGeneration::biomes)).apply(builder, CreatureSpawnGeneration::new)));

    public LivestockOverhaul()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        LOItems.register(eventBus);
        LOItemGroup.register(eventBus);
        LOBlocks.register(eventBus);
        EntityTypes.ENTITY_TYPES.register(eventBus);
        LOMenuTypes.register(eventBus);
        BIOME_MODIFIER_SERIALIZERS.register(eventBus);

        GeckoLib.initialize();
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, LivestockOverhaulClientConfig.SPEC, "livestock-overhaul-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LivestockOverhaulCommonConfig.SPEC, "livestock-overhaul-common.toml");

        MinecraftForge.EVENT_BUS.register(this);

        System.out.println("[DragN's Livestock Overhaul!] Registered Livestock Overhaul.");
        System.out.println("[DragN's Livestock Overhaul!] Issues Page: https://github.com/DragN0007/Overhauled-Livestock/issues");
    }

    public static final EntityDataSerializer<ResourceLocation> RESOURCE_LOCATION = new EntityDataSerializer<>() {
        @Override
        public void write(FriendlyByteBuf buf, ResourceLocation resourceLocation) {
            buf.writeResourceLocation(resourceLocation);
        }

        @Override
        public ResourceLocation read(FriendlyByteBuf buf) {
            return buf.readResourceLocation();
        }

        @Override
        public ResourceLocation copy(ResourceLocation resourceLocation) {
            return resourceLocation;
        }
    };

    static {
        EntityDataSerializers.registerSerializer(RESOURCE_LOCATION);
    }
}