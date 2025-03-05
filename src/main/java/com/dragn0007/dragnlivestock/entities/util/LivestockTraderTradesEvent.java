package com.dragn0007.dragnlivestock.entities.util;

import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraftforge.eventbus.api.Event;

import java.util.List;

public class LivestockTraderTradesEvent extends Event
{
    protected List<ItemListing> generic;
    protected List<ItemListing> rare;

    public LivestockTraderTradesEvent(List<ItemListing> generic, List<ItemListing> rare)
    {
        this.generic = generic;
        this.rare = rare;
    }

    public List<ItemListing> getGenericTrades()
    {
        return generic;
    }

    public List<ItemListing> getRareTrades()
    {
        return rare;
    }
}