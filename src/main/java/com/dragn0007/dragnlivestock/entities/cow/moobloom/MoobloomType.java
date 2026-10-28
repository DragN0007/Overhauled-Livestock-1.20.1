package com.dragn0007.dragnlivestock.entities.cow.moobloom;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.resources.ResourceLocation;

public enum MoobloomType {
    ALLIUM(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/moobloom.geo.json")),
    AZURE_BLUET(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/moobloom.geo.json")),
    BLUE_ORCHID(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/moobloom.geo.json")),
    CORNFLOWER(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/moobloom.geo.json")),
    DANDELION(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/moobloom.geo.json")),
    LILAC(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/moobloom.geo.json")),
    LILY_OF_THE_VALLEY(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/moobloom.geo.json")),
    OXEYE_DAISY(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/moobloom.geo.json")),
    PEONY(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/moobloom.geo.json")),
    POPPY(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/moobloom.geo.json")),
    ROSE_BUSH(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/moobloom.geo.json")),
    SUNFLOWER(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/moobloom.geo.json")),
    TULIP(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/moobloom.geo.json")),
    BEETROOT(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/crop_moobloom.geo.json")),
    CARROT(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/crop_moobloom.geo.json")),
    MELON(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/crop_moobloom.geo.json")),
    POTATO(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/crop_moobloom.geo.json")),
    PUMPKIN(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/crop_moobloom.geo.json")),
    WHEAT(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/crop_moobloom.geo.json")),
    AZALEA(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/moobloom.geo.json")),
    SWEET_BERRY(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/moobloom.geo.json")),
    PEACH(new ResourceLocation(LivestockOverhaul.MODID, "geo/moobloom/moobloom.geo.json"))
    ;

    public final ResourceLocation resourceLocation;
    MoobloomType(ResourceLocation resourceLocation) {this.resourceLocation = resourceLocation;}
    public static MoobloomType breedFromOrdinal(int ordinal) {return MoobloomType.values()[ordinal % MoobloomType.values().length];}
    public MoobloomType next() {return MoobloomType.values()[(this.ordinal() + 1) % MoobloomType.values().length];}
}
