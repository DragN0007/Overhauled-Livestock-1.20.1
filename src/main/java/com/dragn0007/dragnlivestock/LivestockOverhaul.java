package com.dragn0007.dragnlivestock;

import com.dragn0007.dragnlivestock.blocks.LOBlocks;
import com.dragn0007.dragnlivestock.common.gui.LOMenuTypes;
import com.dragn0007.dragnlivestock.common.network.LOPackets;
import com.dragn0007.dragnlivestock.compat.medievalembroidery.MECompatItems;
import com.dragn0007.dragnlivestock.datagen.conditions.BlanketConfigCondition;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.wagon.LargePlow;
import com.dragn0007.dragnlivestock.entities.wagon.Plow;
import com.dragn0007.dragnlivestock.items.LOItemGroup;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.entity.player.PlayerEvent;
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

        LOItems.register(eventBus);
        LOItemGroup.register(eventBus);
        LOBlocks.register(eventBus);
        EntityTypes.ENTITY_TYPES.register(eventBus);
        LOMenuTypes.register(eventBus);
        LOSoundEvents.REGISTRY.register(eventBus);
        LOPackets.register();
        MinecraftForge.EVENT_BUS.addListener((PlayerEvent.PlayerLoggedInEvent warn) -> warn(warn.getEntity()));

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
    }

    public static void warn(Player entity){

        if (!LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
            return;
        }

        entity.displayClientMessage(Component.empty().append
                (Component.literal(
                                "[DragN's Livestock Overhaul!] Debug Logs are turned on! You can disable this message by switching Debug Logs to False in the livestock-overhaul-common.toml." +
                                        "\nChecking for compatible mods...")
                        .withStyle(ChatFormatting.GOLD)), false);

        if (ModList.get().isLoaded("deadlydinos") || ModList.get().isLoaded("medievalembroidery") ||
                ModList.get().isLoaded("tfc") || ModList.get().isLoaded("jade")) {
            if (ModList.get().isLoaded("deadlydinos")) {
                entity.displayClientMessage(Component.empty().append
                        (Component.literal(
                                        "[DragN's Livestock Overhaul!] Found DragN's Deadly Dinos!")
                                .withStyle(ChatFormatting.AQUA)), false);
            }

            if (ModList.get().isLoaded("medievalembroidery")) {
                entity.displayClientMessage(Component.empty().append
                        (Component.literal(
                                        "[DragN's Livestock Overhaul!] Found Medieval Embroidery!")
                                .withStyle(ChatFormatting.AQUA)), false);
            }

            if (ModList.get().isLoaded("tfc")) {
                entity.displayClientMessage(Component.empty().append
                        (Component.literal(
                                        "[DragN's Livestock Overhaul!] Found TerraFirmaCraft!")
                                .withStyle(ChatFormatting.AQUA)), false);
            }

            if (ModList.get().isLoaded("jade")) {
                entity.displayClientMessage(Component.empty().append
                        (Component.literal(
                                        "[DragN's Livestock Overhaul!] Found Jade!")
                                .withStyle(ChatFormatting.AQUA)), false);
            }

            entity.displayClientMessage(Component.empty().append
                    (Component.literal(
                                    "Found directly-compatible mods!")
                            .withStyle(ChatFormatting.GOLD)), false);
        } else {
            entity.displayClientMessage(Component.empty().append
                    (Component.literal(
                                    "Found no directly-compatible mods.")
                            .withStyle(ChatFormatting.GOLD)), false);
        }
    }

    public static final EntityDataSerializer<Plow.Mode> MODE = EntityDataSerializer.simpleEnum(Plow.Mode.class);
    public static final EntityDataSerializer<LargePlow.Mode> MODE_2 = EntityDataSerializer.simpleEnum(LargePlow.Mode.class);

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

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, path);
    }

    static {
        EntityDataSerializers.registerSerializer(RESOURCE_LOCATION);
        EntityDataSerializers.registerSerializer(MODE);
        EntityDataSerializers.registerSerializer(MODE_2);
    }

    public static float mod(float n, float m) {
        while(n < 0) {
            n += m;
        }
        return n % m;
    }
}