package com.dragn0007.dragnlivestock;

import com.dragn0007.dragnlivestock.blocks.LOBlocks;
import com.dragn0007.dragnlivestock.common.gui.LOMenuTypes;
import com.dragn0007.dragnlivestock.common.network.LOPackets;
import com.dragn0007.dragnlivestock.compat.medievalembroidery.MECompatItems;
import com.dragn0007.dragnlivestock.datagen.conditions.BlanketConfigCondition;
import com.dragn0007.dragnlivestock.datagen.conditions.HolidayConfigCondition;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.items.LOItemGroup;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

import java.time.LocalDate;
import java.time.Month;

@Mod(LivestockOverhaul.MODID)
public class LivestockOverhaul {

    public static final String MODID = "dragnlivestock";
    public static final Logger LOGGER = LogUtils.getLogger();

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
        CraftingHelper.register(new HolidayConfigCondition.Serializer(new ResourceLocation(MODID, "holiday_config_condition")));

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

        LocalDate date = LocalDate.now();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();

        if ((month == Month.OCTOBER && (day == 31)) || (month == Month.NOVEMBER && (day == 1 || day == 2))) {
            entity.displayClientMessage(Component.empty().append
                    (Component.literal("[DragN's Livestock Overhaul!] Spooky souls fill the air, defeat the Headless Horseman to make your horse his steed's heir...")
                            .withStyle(ChatFormatting.DARK_RED)), false);
        }

        if (LivestockOverhaulCommonConfig.DEBUG_LOGS.get()) {
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
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, path);
    }
}