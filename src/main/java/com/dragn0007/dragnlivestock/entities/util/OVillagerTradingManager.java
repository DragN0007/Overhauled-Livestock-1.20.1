package com.dragn0007.dragnlivestock.entities.util;

//import com.dragn0007.dragnlivestock.entities.villager.LivestockTrader;
//import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
//import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
//import net.minecraft.core.NonNullList;
//import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
//import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.common.VillagerTradingManager;
//import net.minecraftforge.event.server.ServerAboutToStartEvent;
//import net.minecraftforge.eventbus.api.IEventBus;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class OVillagerTradingManager extends VillagerTradingManager
//{
//    private static final Int2ObjectMap<ItemListing[]> LIVESTOCK_TRADES = new Int2ObjectOpenHashMap<>();
//
//    static
//    {
//        LivestockTrader.LIVESTOCK_TRADER_TRADES.int2ObjectEntrySet().forEach(e -> LIVESTOCK_TRADES.put(e.getIntKey(), Arrays.copyOf(e.getValue(), e.getValue().length)));
//    }
//
//    static void loadTrades(ServerAboutToStartEvent e) {
//        postLivestockTraderEvent();
//    }
//
//    public static void register(IEventBus eventBus) {
//        eventBus.addListener(OVillagerTradingManager::onServerAboutToStart);
//    }
//
//    private static void onServerAboutToStart(ServerAboutToStartEvent event) {
//        loadTrades(event);
//    }
//
//    private static void postLivestockTraderEvent()
//    {
//        List<ItemListing> generic = NonNullList.create();
//        List<ItemListing> rare = NonNullList.create();
//        Arrays.stream(LIVESTOCK_TRADES.get(1)).forEach(generic::add);
//        Arrays.stream(LIVESTOCK_TRADES.get(2)).forEach(rare::add);
//        MinecraftForge.EVENT_BUS.post(new LivestockTraderTradesEvent(generic, rare));
//        LivestockTrader.LIVESTOCK_TRADER_TRADES.put(1, generic.toArray(new ItemListing[0]));
//        LivestockTrader.LIVESTOCK_TRADER_TRADES.put(2, rare.toArray(new ItemListing[0]));
//    }
//
//}
