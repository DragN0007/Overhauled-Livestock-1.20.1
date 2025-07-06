package com.dragn0007.dragnlivestock.compat;

import com.dragn0007.dragnlivestock.items.custom.CaparisonItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MECompatItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "medievalembroidery");

    public static final RegistryObject<Item> LEATHER_RUMP_STRAPS = ITEMS.register("leather_rump_straps",
            () -> new CaparisonItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_RUMP_STRAPS = ITEMS.register("black_rump_straps",
            () -> new CaparisonItem(DyeColor.BLACK, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_RUMP_STRAPS = ITEMS.register("white_rump_straps",
            () -> new CaparisonItem(DyeColor.WHITE, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_CAPARISON_CAPE = ITEMS.register("black_caparison_cape",
            () -> new CaparisonItem(DyeColor.BLACK, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_CAPARISON_FULL = ITEMS.register("black_caparison_full",
            () -> new CaparisonItem(DyeColor.BLACK, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_CAPARISON_HALF = ITEMS.register("black_caparison_half",
            () -> new CaparisonItem(DyeColor.BLACK, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_CAPARISON_SHOULDER = ITEMS.register("black_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.BLACK, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_BLACK_CAPARISON_CAPE = ITEMS.register("white_black_caparison_cape",
            () -> new CaparisonItem(DyeColor.BLACK, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_BLACK_CAPARISON_FULL = ITEMS.register("white_black_caparison_full",
            () -> new CaparisonItem(DyeColor.BLACK, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_BLACK_CAPARISON_HALF = ITEMS.register("white_black_caparison_half",
            () -> new CaparisonItem(DyeColor.BLACK, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_BLACK_CAPARISON_SHOULDER = ITEMS.register("white_black_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.BLACK, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_BLUE_CAPARISON_CAPE = ITEMS.register("black_blue_caparison_cape",
            () -> new CaparisonItem(DyeColor.BLUE, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_BLUE_CAPARISON_FULL = ITEMS.register("black_blue_caparison_full",
            () -> new CaparisonItem(DyeColor.BLUE, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_BLUE_CAPARISON_HALF = ITEMS.register("black_blue_caparison_half",
            () -> new CaparisonItem(DyeColor.BLUE, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_BLUE_CAPARISON_SHOULDER = ITEMS.register("black_blue_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.BLUE, new Item.Properties()));
    public static final RegistryObject<Item> BLUE_CAPARISON_CAPE = ITEMS.register("blue_caparison_cape",
            () -> new CaparisonItem(DyeColor.BLUE, new Item.Properties()));
    public static final RegistryObject<Item> BLUE_CAPARISON_FULL = ITEMS.register("blue_caparison_full",
            () -> new CaparisonItem(DyeColor.BLUE, new Item.Properties()));
    public static final RegistryObject<Item> BLUE_CAPARISON_HALF = ITEMS.register("blue_caparison_half",
            () -> new CaparisonItem(DyeColor.BLUE, new Item.Properties()));
    public static final RegistryObject<Item> BLUE_CAPARISON_SHOULDER = ITEMS.register("blue_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.BLUE, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_BLUE_CAPARISON_CAPE = ITEMS.register("white_blue_caparison_cape",
            () -> new CaparisonItem(DyeColor.BLUE, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_BLUE_CAPARISON_FULL = ITEMS.register("white_blue_caparison_full",
            () -> new CaparisonItem(DyeColor.BLUE, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_BLUE_CAPARISON_HALF = ITEMS.register("white_blue_caparison_half",
            () -> new CaparisonItem(DyeColor.BLUE, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_BLUE_CAPARISON_SHOULDER = ITEMS.register("white_blue_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.BLUE, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_BROWN_CAPARISON_CAPE = ITEMS.register("black_brown_caparison_cape",
            () -> new CaparisonItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_BROWN_CAPARISON_FULL = ITEMS.register("black_brown_caparison_full",
            () -> new CaparisonItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_BROWN_CAPARISON_HALF = ITEMS.register("black_brown_caparison_half",
            () -> new CaparisonItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_BROWN_CAPARISON_SHOULDER = ITEMS.register("black_brown_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> BROWN_CAPARISON_CAPE = ITEMS.register("brown_caparison_cape",
            () -> new CaparisonItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> BROWN_CAPARISON_FULL = ITEMS.register("brown_caparison_full",
            () -> new CaparisonItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> BROWN_CAPARISON_HALF = ITEMS.register("brown_caparison_half",
            () -> new CaparisonItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> BROWN_CAPARISON_SHOULDER = ITEMS.register("brown_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_BROWN_CAPARISON_CAPE = ITEMS.register("white_brown_caparison_cape",
            () -> new CaparisonItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_BROWN_CAPARISON_FULL = ITEMS.register("white_brown_caparison_full",
            () -> new CaparisonItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_BROWN_CAPARISON_HALF = ITEMS.register("white_brown_caparison_half",
            () -> new CaparisonItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_BROWN_CAPARISON_SHOULDER = ITEMS.register("white_brown_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.BROWN, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_CYAN_CAPARISON_CAPE = ITEMS.register("black_cyan_caparison_cape",
            () -> new CaparisonItem(DyeColor.CYAN, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_CYAN_CAPARISON_FULL = ITEMS.register("black_cyan_caparison_full",
            () -> new CaparisonItem(DyeColor.CYAN, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_CYAN_CAPARISON_HALF = ITEMS.register("black_cyan_caparison_half",
            () -> new CaparisonItem(DyeColor.CYAN, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_CYAN_CAPARISON_SHOULDER = ITEMS.register("black_cyan_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.CYAN, new Item.Properties()));
    public static final RegistryObject<Item> CYAN_CAPARISON_CAPE = ITEMS.register("cyan_caparison_cape",
            () -> new CaparisonItem(DyeColor.CYAN, new Item.Properties()));
    public static final RegistryObject<Item> CYAN_CAPARISON_FULL = ITEMS.register("cyan_caparison_full",
            () -> new CaparisonItem(DyeColor.CYAN, new Item.Properties()));
    public static final RegistryObject<Item> CYAN_CAPARISON_HALF = ITEMS.register("cyan_caparison_half",
            () -> new CaparisonItem(DyeColor.CYAN, new Item.Properties()));
    public static final RegistryObject<Item> CYAN_CAPARISON_SHOULDER = ITEMS.register("cyan_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.CYAN, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_CYAN_CAPARISON_CAPE = ITEMS.register("white_cyan_caparison_cape",
            () -> new CaparisonItem(DyeColor.CYAN, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_CYAN_CAPARISON_FULL = ITEMS.register("white_cyan_caparison_full",
            () -> new CaparisonItem(DyeColor.CYAN, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_CYAN_CAPARISON_HALF = ITEMS.register("white_cyan_caparison_half",
            () -> new CaparisonItem(DyeColor.CYAN, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_CYAN_CAPARISON_SHOULDER = ITEMS.register("white_cyan_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.CYAN, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_GREEN_CAPARISON_CAPE = ITEMS.register("black_green_caparison_cape",
            () -> new CaparisonItem(DyeColor.GREEN, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_GREEN_CAPARISON_FULL = ITEMS.register("black_green_caparison_full",
            () -> new CaparisonItem(DyeColor.GREEN, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_GREEN_CAPARISON_HALF = ITEMS.register("black_green_caparison_half",
            () -> new CaparisonItem(DyeColor.GREEN, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_GREEN_CAPARISON_SHOULDER = ITEMS.register("black_green_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.GREEN, new Item.Properties()));
    public static final RegistryObject<Item> GREEN_CAPARISON_CAPE = ITEMS.register("green_caparison_cape",
            () -> new CaparisonItem(DyeColor.GREEN, new Item.Properties()));
    public static final RegistryObject<Item> GREEN_CAPARISON_FULL = ITEMS.register("green_caparison_full",
            () -> new CaparisonItem(DyeColor.GREEN, new Item.Properties()));
    public static final RegistryObject<Item> GREEN_CAPARISON_HALF = ITEMS.register("green_caparison_half",
            () -> new CaparisonItem(DyeColor.GREEN, new Item.Properties()));
    public static final RegistryObject<Item> GREEN_CAPARISON_SHOULDER = ITEMS.register("green_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.GREEN, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_GREEN_CAPARISON_CAPE = ITEMS.register("white_green_caparison_cape",
            () -> new CaparisonItem(DyeColor.GREEN, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_GREEN_CAPARISON_FULL = ITEMS.register("white_green_caparison_full",
            () -> new CaparisonItem(DyeColor.GREEN, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_GREEN_CAPARISON_HALF = ITEMS.register("white_green_caparison_half",
            () -> new CaparisonItem(DyeColor.GREEN, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_GREEN_CAPARISON_SHOULDER = ITEMS.register("white_green_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.GREEN, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_GREY_CAPARISON_CAPE = ITEMS.register("black_grey_caparison_cape",
            () -> new CaparisonItem(DyeColor.GRAY, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_GREY_CAPARISON_FULL = ITEMS.register("black_grey_caparison_full",
            () -> new CaparisonItem(DyeColor.GRAY, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_GREY_CAPARISON_HALF = ITEMS.register("black_grey_caparison_half",
            () -> new CaparisonItem(DyeColor.GRAY, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_GREY_CAPARISON_SHOULDER = ITEMS.register("black_grey_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.GRAY, new Item.Properties()));
    public static final RegistryObject<Item> GREY_CAPARISON_CAPE = ITEMS.register("grey_caparison_cape",
            () -> new CaparisonItem(DyeColor.GRAY, new Item.Properties()));
    public static final RegistryObject<Item> GREY_CAPARISON_FULL = ITEMS.register("grey_caparison_full",
            () -> new CaparisonItem(DyeColor.GRAY, new Item.Properties()));
    public static final RegistryObject<Item> GREY_CAPARISON_HALF = ITEMS.register("grey_caparison_half",
            () -> new CaparisonItem(DyeColor.GRAY, new Item.Properties()));
    public static final RegistryObject<Item> GREY_CAPARISON_SHOULDER = ITEMS.register("grey_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.GRAY, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_GREY_CAPARISON_CAPE = ITEMS.register("white_grey_caparison_cape",
            () -> new CaparisonItem(DyeColor.GRAY, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_GREY_CAPARISON_FULL = ITEMS.register("white_grey_caparison_full",
            () -> new CaparisonItem(DyeColor.GRAY, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_GREY_CAPARISON_HALF = ITEMS.register("white_grey_caparison_half",
            () -> new CaparisonItem(DyeColor.GRAY, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_GREY_CAPARISON_SHOULDER = ITEMS.register("white_grey_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.GRAY, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_LIGHT_BLUE_CAPARISON_CAPE = ITEMS.register("black_light_blue_caparison_cape",
            () -> new CaparisonItem(DyeColor.LIGHT_BLUE, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_LIGHT_BLUE_CAPARISON_FULL = ITEMS.register("black_light_blue_caparison_full",
            () -> new CaparisonItem(DyeColor.LIGHT_BLUE, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_LIGHT_BLUE_CAPARISON_HALF = ITEMS.register("black_light_blue_caparison_half",
            () -> new CaparisonItem(DyeColor.LIGHT_BLUE, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_LIGHT_BLUE_CAPARISON_SHOULDER = ITEMS.register("black_light_blue_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.LIGHT_BLUE, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_CAPARISON_CAPE = ITEMS.register("light_blue_caparison_cape",
            () -> new CaparisonItem(DyeColor.LIGHT_BLUE, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_CAPARISON_FULL = ITEMS.register("light_blue_caparison_full",
            () -> new CaparisonItem(DyeColor.LIGHT_BLUE, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_CAPARISON_HALF = ITEMS.register("light_blue_caparison_half",
            () -> new CaparisonItem(DyeColor.LIGHT_BLUE, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_CAPARISON_SHOULDER = ITEMS.register("light_blue_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.LIGHT_BLUE, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_LIGHT_BLUE_CAPARISON_CAPE = ITEMS.register("white_light_blue_caparison_cape",
            () -> new CaparisonItem(DyeColor.LIGHT_BLUE, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_LIGHT_BLUE_CAPARISON_FULL = ITEMS.register("white_light_blue_caparison_full",
            () -> new CaparisonItem(DyeColor.LIGHT_BLUE, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_LIGHT_BLUE_CAPARISON_HALF = ITEMS.register("white_light_blue_caparison_half",
            () -> new CaparisonItem(DyeColor.LIGHT_BLUE, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_LIGHT_BLUE_CAPARISON_SHOULDER = ITEMS.register("white_light_blue_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.LIGHT_BLUE, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_LIGHT_GREY_CAPARISON_CAPE = ITEMS.register("black_light_grey_caparison_cape",
            () -> new CaparisonItem(DyeColor.LIGHT_GRAY, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_LIGHT_GREY_CAPARISON_FULL = ITEMS.register("black_light_grey_caparison_full",
            () -> new CaparisonItem(DyeColor.LIGHT_GRAY, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_LIGHT_GREY_CAPARISON_HALF = ITEMS.register("black_light_grey_caparison_half",
            () -> new CaparisonItem(DyeColor.LIGHT_GRAY, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_LIGHT_GREY_CAPARISON_SHOULDER = ITEMS.register("black_light_grey_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.LIGHT_GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_CAPARISON_CAPE = ITEMS.register("light_grey_caparison_cape",
            () -> new CaparisonItem(DyeColor.LIGHT_GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_CAPARISON_FULL = ITEMS.register("light_grey_caparison_full",
            () -> new CaparisonItem(DyeColor.LIGHT_GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_CAPARISON_HALF = ITEMS.register("light_grey_caparison_half",
            () -> new CaparisonItem(DyeColor.LIGHT_GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_CAPARISON_SHOULDER = ITEMS.register("light_grey_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.LIGHT_GRAY, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_LIGHT_GREY_CAPARISON_CAPE = ITEMS.register("white_light_grey_caparison_cape",
            () -> new CaparisonItem(DyeColor.LIGHT_GRAY, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_LIGHT_GREY_CAPARISON_FULL = ITEMS.register("white_light_grey_caparison_full",
            () -> new CaparisonItem(DyeColor.LIGHT_GRAY, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_LIGHT_GREY_CAPARISON_HALF = ITEMS.register("white_light_grey_caparison_half",
            () -> new CaparisonItem(DyeColor.LIGHT_GRAY, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_LIGHT_GREY_CAPARISON_SHOULDER = ITEMS.register("white_light_grey_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.LIGHT_GRAY, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_LIME_CAPARISON_CAPE = ITEMS.register("black_lime_caparison_cape",
            () -> new CaparisonItem(DyeColor.LIME, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_LIME_CAPARISON_FULL = ITEMS.register("black_lime_caparison_full",
            () -> new CaparisonItem(DyeColor.LIME, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_LIME_CAPARISON_HALF = ITEMS.register("black_lime_caparison_half",
            () -> new CaparisonItem(DyeColor.LIME, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_LIME_CAPARISON_SHOULDER = ITEMS.register("black_lime_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.LIME, new Item.Properties()));
    public static final RegistryObject<Item> LIME_CAPARISON_CAPE = ITEMS.register("lime_caparison_cape",
            () -> new CaparisonItem(DyeColor.LIME, new Item.Properties()));
    public static final RegistryObject<Item> LIME_CAPARISON_FULL = ITEMS.register("lime_caparison_full",
            () -> new CaparisonItem(DyeColor.LIME, new Item.Properties()));
    public static final RegistryObject<Item> LIME_CAPARISON_HALF = ITEMS.register("lime_caparison_half",
            () -> new CaparisonItem(DyeColor.LIME, new Item.Properties()));
    public static final RegistryObject<Item> LIME_CAPARISON_SHOULDER = ITEMS.register("lime_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.LIME, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_LIME_CAPARISON_CAPE = ITEMS.register("white_lime_caparison_cape",
            () -> new CaparisonItem(DyeColor.LIME, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_LIME_CAPARISON_FULL = ITEMS.register("white_lime_caparison_full",
            () -> new CaparisonItem(DyeColor.LIME, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_LIME_CAPARISON_HALF = ITEMS.register("white_lime_caparison_half",
            () -> new CaparisonItem(DyeColor.LIME, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_LIME_CAPARISON_SHOULDER = ITEMS.register("white_lime_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.LIME, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_MAGENTA_CAPARISON_CAPE = ITEMS.register("black_magenta_caparison_cape",
            () -> new CaparisonItem(DyeColor.MAGENTA, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_MAGENTA_CAPARISON_FULL = ITEMS.register("black_magenta_caparison_full",
            () -> new CaparisonItem(DyeColor.MAGENTA, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_MAGENTA_CAPARISON_HALF = ITEMS.register("black_magenta_caparison_half",
            () -> new CaparisonItem(DyeColor.MAGENTA, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_MAGENTA_CAPARISON_SHOULDER = ITEMS.register("black_magenta_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.MAGENTA, new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_CAPARISON_CAPE = ITEMS.register("magenta_caparison_cape",
            () -> new CaparisonItem(DyeColor.MAGENTA, new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_CAPARISON_FULL = ITEMS.register("magenta_caparison_full",
            () -> new CaparisonItem(DyeColor.MAGENTA, new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_CAPARISON_HALF = ITEMS.register("magenta_caparison_half",
            () -> new CaparisonItem(DyeColor.MAGENTA, new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_CAPARISON_SHOULDER = ITEMS.register("magenta_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.MAGENTA, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_MAGENTA_CAPARISON_CAPE = ITEMS.register("white_magenta_caparison_cape",
            () -> new CaparisonItem(DyeColor.MAGENTA, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_MAGENTA_CAPARISON_FULL = ITEMS.register("white_magenta_caparison_full",
            () -> new CaparisonItem(DyeColor.MAGENTA, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_MAGENTA_CAPARISON_HALF = ITEMS.register("white_magenta_caparison_half",
            () -> new CaparisonItem(DyeColor.MAGENTA, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_MAGENTA_CAPARISON_SHOULDER = ITEMS.register("white_magenta_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.MAGENTA, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_ORANGE_CAPARISON_CAPE = ITEMS.register("black_orange_caparison_cape",
            () -> new CaparisonItem(DyeColor.ORANGE, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_ORANGE_CAPARISON_FULL = ITEMS.register("black_orange_caparison_full",
            () -> new CaparisonItem(DyeColor.ORANGE, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_ORANGE_CAPARISON_HALF = ITEMS.register("black_orange_caparison_half",
            () -> new CaparisonItem(DyeColor.ORANGE, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_ORANGE_CAPARISON_SHOULDER = ITEMS.register("black_orange_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.ORANGE, new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_CAPARISON_CAPE = ITEMS.register("orange_caparison_cape",
            () -> new CaparisonItem(DyeColor.ORANGE, new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_CAPARISON_FULL = ITEMS.register("orange_caparison_full",
            () -> new CaparisonItem(DyeColor.ORANGE, new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_CAPARISON_HALF = ITEMS.register("orange_caparison_half",
            () -> new CaparisonItem(DyeColor.ORANGE, new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_CAPARISON_SHOULDER = ITEMS.register("orange_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.ORANGE, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_ORANGE_CAPARISON_CAPE = ITEMS.register("white_orange_caparison_cape",
            () -> new CaparisonItem(DyeColor.ORANGE, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_ORANGE_CAPARISON_FULL = ITEMS.register("white_orange_caparison_full",
            () -> new CaparisonItem(DyeColor.ORANGE, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_ORANGE_CAPARISON_HALF = ITEMS.register("white_orange_caparison_half",
            () -> new CaparisonItem(DyeColor.ORANGE, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_ORANGE_CAPARISON_SHOULDER = ITEMS.register("white_orange_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.ORANGE, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_PINK_CAPARISON_CAPE = ITEMS.register("black_pink_caparison_cape",
            () -> new CaparisonItem(DyeColor.PINK, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_PINK_CAPARISON_FULL = ITEMS.register("black_pink_caparison_full",
            () -> new CaparisonItem(DyeColor.PINK, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_PINK_CAPARISON_HALF = ITEMS.register("black_pink_caparison_half",
            () -> new CaparisonItem(DyeColor.PINK, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_PINK_CAPARISON_SHOULDER = ITEMS.register("black_pink_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.PINK, new Item.Properties()));
    public static final RegistryObject<Item> PINK_CAPARISON_CAPE = ITEMS.register("pink_caparison_cape",
            () -> new CaparisonItem(DyeColor.PINK, new Item.Properties()));
    public static final RegistryObject<Item> PINK_CAPARISON_FULL = ITEMS.register("pink_caparison_full",
            () -> new CaparisonItem(DyeColor.PINK, new Item.Properties()));
    public static final RegistryObject<Item> PINK_CAPARISON_HALF = ITEMS.register("pink_caparison_half",
            () -> new CaparisonItem(DyeColor.PINK, new Item.Properties()));
    public static final RegistryObject<Item> PINK_CAPARISON_SHOULDER = ITEMS.register("pink_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.PINK, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_PINK_CAPARISON_CAPE = ITEMS.register("white_pink_caparison_cape",
            () -> new CaparisonItem(DyeColor.PINK, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_PINK_CAPARISON_FULL = ITEMS.register("white_pink_caparison_full",
            () -> new CaparisonItem(DyeColor.PINK, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_PINK_CAPARISON_HALF = ITEMS.register("white_pink_caparison_half",
            () -> new CaparisonItem(DyeColor.PINK, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_PINK_CAPARISON_SHOULDER = ITEMS.register("white_pink_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.PINK, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_PURPLE_CAPARISON_CAPE = ITEMS.register("black_purple_caparison_cape",
            () -> new CaparisonItem(DyeColor.PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_PURPLE_CAPARISON_FULL = ITEMS.register("black_purple_caparison_full",
            () -> new CaparisonItem(DyeColor.PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_PURPLE_CAPARISON_HALF = ITEMS.register("black_purple_caparison_half",
            () -> new CaparisonItem(DyeColor.PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_PURPLE_CAPARISON_SHOULDER = ITEMS.register("black_purple_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_CAPARISON_CAPE = ITEMS.register("purple_caparison_cape",
            () -> new CaparisonItem(DyeColor.PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_CAPARISON_FULL = ITEMS.register("purple_caparison_full",
            () -> new CaparisonItem(DyeColor.PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_CAPARISON_HALF = ITEMS.register("purple_caparison_half",
            () -> new CaparisonItem(DyeColor.PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_CAPARISON_SHOULDER = ITEMS.register("purple_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_PURPLE_CAPARISON_CAPE = ITEMS.register("white_purple_caparison_cape",
            () -> new CaparisonItem(DyeColor.PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_PURPLE_CAPARISON_FULL = ITEMS.register("white_purple_caparison_full",
            () -> new CaparisonItem(DyeColor.PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_PURPLE_CAPARISON_HALF = ITEMS.register("white_purple_caparison_half",
            () -> new CaparisonItem(DyeColor.PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_PURPLE_CAPARISON_SHOULDER = ITEMS.register("white_purple_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.PURPLE, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_RED_CAPARISON_CAPE = ITEMS.register("black_red_caparison_cape",
            () -> new CaparisonItem(DyeColor.RED, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_RED_CAPARISON_FULL = ITEMS.register("black_red_caparison_full",
            () -> new CaparisonItem(DyeColor.RED, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_RED_CAPARISON_HALF = ITEMS.register("black_red_caparison_half",
            () -> new CaparisonItem(DyeColor.RED, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_RED_CAPARISON_SHOULDER = ITEMS.register("black_red_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.RED, new Item.Properties()));
    public static final RegistryObject<Item> RED_CAPARISON_CAPE = ITEMS.register("red_caparison_cape",
            () -> new CaparisonItem(DyeColor.RED, new Item.Properties()));
    public static final RegistryObject<Item> RED_CAPARISON_FULL = ITEMS.register("red_caparison_full",
            () -> new CaparisonItem(DyeColor.RED, new Item.Properties()));
    public static final RegistryObject<Item> RED_CAPARISON_HALF = ITEMS.register("red_caparison_half",
            () -> new CaparisonItem(DyeColor.RED, new Item.Properties()));
    public static final RegistryObject<Item> RED_CAPARISON_SHOULDER = ITEMS.register("red_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.RED, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_RED_CAPARISON_CAPE = ITEMS.register("white_red_caparison_cape",
            () -> new CaparisonItem(DyeColor.RED, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_RED_CAPARISON_FULL = ITEMS.register("white_red_caparison_full",
            () -> new CaparisonItem(DyeColor.RED, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_RED_CAPARISON_HALF = ITEMS.register("white_red_caparison_half",
            () -> new CaparisonItem(DyeColor.RED, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_RED_CAPARISON_SHOULDER = ITEMS.register("white_red_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.RED, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_WHITE_CAPARISON_CAPE = ITEMS.register("black_white_caparison_cape",
            () -> new CaparisonItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_WHITE_CAPARISON_FULL = ITEMS.register("black_white_caparison_full",
            () -> new CaparisonItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_WHITE_CAPARISON_HALF = ITEMS.register("black_white_caparison_half",
            () -> new CaparisonItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_WHITE_CAPARISON_SHOULDER = ITEMS.register("black_white_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_CAPARISON_CAPE = ITEMS.register("white_caparison_cape",
            () -> new CaparisonItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_CAPARISON_FULL = ITEMS.register("white_caparison_full",
            () -> new CaparisonItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_CAPARISON_HALF = ITEMS.register("white_caparison_half",
            () -> new CaparisonItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_CAPARISON_SHOULDER = ITEMS.register("white_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.WHITE, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_YELLOW_CAPARISON_CAPE = ITEMS.register("black_yellow_caparison_cape",
            () -> new CaparisonItem(DyeColor.YELLOW, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_YELLOW_CAPARISON_FULL = ITEMS.register("black_yellow_caparison_full",
            () -> new CaparisonItem(DyeColor.YELLOW, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_YELLOW_CAPARISON_HALF = ITEMS.register("black_yellow_caparison_half",
            () -> new CaparisonItem(DyeColor.YELLOW, new Item.Properties()));
    public static final RegistryObject<Item> BLACK_YELLOW_CAPARISON_SHOULDER = ITEMS.register("black_yellow_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.YELLOW, new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_CAPARISON_CAPE = ITEMS.register("yellow_caparison_cape",
            () -> new CaparisonItem(DyeColor.YELLOW, new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_CAPARISON_FULL = ITEMS.register("yellow_caparison_full",
            () -> new CaparisonItem(DyeColor.YELLOW, new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_CAPARISON_HALF = ITEMS.register("yellow_caparison_half",
            () -> new CaparisonItem(DyeColor.YELLOW, new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_CAPARISON_SHOULDER = ITEMS.register("yellow_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.YELLOW, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_YELLOW_CAPARISON_CAPE = ITEMS.register("white_yellow_caparison_cape",
            () -> new CaparisonItem(DyeColor.YELLOW, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_YELLOW_CAPARISON_FULL = ITEMS.register("white_yellow_caparison_full",
            () -> new CaparisonItem(DyeColor.YELLOW, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_YELLOW_CAPARISON_HALF = ITEMS.register("white_yellow_caparison_half",
            () -> new CaparisonItem(DyeColor.YELLOW, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_YELLOW_CAPARISON_SHOULDER = ITEMS.register("white_yellow_caparison_shoulder",
            () -> new CaparisonItem(DyeColor.YELLOW, new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}