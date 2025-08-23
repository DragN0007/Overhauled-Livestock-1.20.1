package com.dragn0007.dragnlivestock;

import com.dragn0007.dragnlivestock.blocks.LOBlocks;
import com.dragn0007.dragnlivestock.common.gui.LOMenuTypes;
import com.dragn0007.dragnlivestock.common.network.LOPackets;
import com.dragn0007.dragnlivestock.compat.medievalembroidery.MECompatItems;
import com.dragn0007.dragnlivestock.datagen.conditions.BlanketConfigCondition;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.items.LOItemGroup;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib.GeckoLib;

import java.util.Optional;

@Mod(LivestockOverhaul.MODID)
public class LivestockOverhaul {

    public static final String MODID = "dragnlivestock";

    public LivestockOverhaul() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        LOItems.register(eventBus);
        LOItemGroup.register(eventBus);
        LOBlocks.register(eventBus);
        EntityTypes.ENTITY_TYPES.register(eventBus);
        LOMenuTypes.register(eventBus);
        LOSoundEvents.REGISTRY.register(eventBus);
        LOPackets.register();

        CraftingHelper.register(new BlanketConfigCondition.Serializer(new ResourceLocation(MODID, "blanket_config_condition")));

        if (ModList.get().isLoaded("medievalembroidery")) {
            MECompatItems.register(eventBus);
        }

        GeckoLib.initialize();
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, LivestockOverhaulClientConfig.SPEC, "livestock-overhaul-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LivestockOverhaulCommonConfig.SPEC, "livestock-overhaul-common.toml");

        MinecraftForge.EVENT_BUS.register(this);



        System.out.println("[DragN's Livestock Overhaul!] Registered Livestock Overhaul.");
        System.out.println("[DragN's Livestock Overhaul!] Do not remove this mod without running the Failsafe Config!");

        //debug
        if (ModList.get().isLoaded("jade")) {
            System.out.println("[DragN's Livestock Overhaul!] Jade is installed! We're compatible!");
        } else {
            System.out.println("[DragN's Livestock Overhaul!] Jade is not installed.");
        }

        if (ModList.get().isLoaded("deadlydinos")) {
            System.out.println("[DragN's Livestock Overhaul!] Deadly Dinos is installed! We're compatible!");
        } else {
            System.out.println("[DragN's Livestock Overhaul!] Deadly Dinos is not installed.");
        }

        if (ModList.get().isLoaded("medievalembroidery")) {
            System.out.println("[DragN's Livestock Overhaul!] Medieval Embroidery is installed! We're compatible!");
        } else {
            System.out.println("[DragN's Livestock Overhaul!] Medieval Embroidery is not installed.");
        }

        if (ModList.get().isLoaded("tfc")) {
            System.out.println("[DragN's Livestock Overhaul!] TerraFirmaCraft is installed! We're compatible!");
        } else {
            System.out.println("[DragN's Livestock Overhaul!] TerraFirmaCraft is not installed.");
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
//        LivestockTrader.registerTrades();
//        OVillagerTradingManager.register(FMLJavaModLoadingContext.get().getModEventBus());
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
    public static final EntityDataSerializer<Optional<DyeColor>> DYE_COLOR = EntityDataSerializer.optional(FriendlyByteBuf::writeEnum, (friendlyByteBuf) -> friendlyByteBuf.readEnum(DyeColor.class));


    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, path);
    }

    static {
        EntityDataSerializers.registerSerializer(RESOURCE_LOCATION);
        EntityDataSerializers.registerSerializer(DYE_COLOR);
    }
}