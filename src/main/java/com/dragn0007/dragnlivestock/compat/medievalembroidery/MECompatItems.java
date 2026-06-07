package com.dragn0007.dragnlivestock.compat.medievalembroidery;

import com.dragn0007.dragnlivestock.items.custom.CaparisonItem;
import com.dragn0007.dragnlivestock.items.custom.RumpStrapItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;
import java.util.Map;

public class MECompatItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "medievalembroidery");

    public static final RegistryObject<Item> LEATHER_RUMP_STRAPS = ITEMS.register("leather_rump_straps",
            () -> new RumpStrapItem(new Item.Properties()));
    public static final RegistryObject<Item> BLACK_RUMP_STRAPS = ITEMS.register("black_rump_straps",
            () -> new RumpStrapItem(new Item.Properties()));
    public static final RegistryObject<Item> WHITE_RUMP_STRAPS = ITEMS.register("white_rump_straps",
            () -> new RumpStrapItem(new Item.Properties()));

    public static final Map<DyeColor, RegistryObject<Item>> SOLID_CAPARISON_CAPES = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getName() + "_caparison_cape";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new CaparisonItem(color, new Item.Properties()));
            SOLID_CAPARISON_CAPES.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> SOLID_CAPARISON_FULLS = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getName() + "_caparison_full";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new CaparisonItem(color, new Item.Properties()));
            SOLID_CAPARISON_FULLS.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> SOLID_CAPARISON_HALFS = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getName() + "_caparison_half";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new CaparisonItem(color, new Item.Properties()));
            SOLID_CAPARISON_HALFS.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> SOLID_CAPARISON_SHOULDERS = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getName() + "_caparison_shoulder";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new CaparisonItem(color, new Item.Properties()));
            SOLID_CAPARISON_SHOULDERS.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> BLACK_CHECKER_CAPARISON_CAPES = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.BLACK)
                continue;
            String name = "black_" + color.getName() + "_caparison_cape";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new CaparisonItem(color, new Item.Properties()));
            BLACK_CHECKER_CAPARISON_CAPES.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> BLACK_CHECKER_CAPARISON_FULLS = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.BLACK)
                continue;
            String name = "black_" + color.getName() + "_caparison_full";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new CaparisonItem(color, new Item.Properties()));
            BLACK_CHECKER_CAPARISON_FULLS.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> BLACK_CHECKER_CAPARISON_HALFS = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.BLACK)
                continue;
            String name = "black_" + color.getName() + "_caparison_half";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new CaparisonItem(color, new Item.Properties()));
            BLACK_CHECKER_CAPARISON_HALFS.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> BLACK_CHECKER_CAPARISON_SHOULDERS = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.BLACK)
                continue;
            String name = "black_" + color.getName() + "_caparison_shoulder";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new CaparisonItem(color, new Item.Properties()));
            BLACK_CHECKER_CAPARISON_SHOULDERS.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> WHITE_CHECKER_CAPARISON_CAPES = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.WHITE)
                continue;
            String name = "white_" + color.getName() + "_caparison_cape";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new CaparisonItem(color, new Item.Properties()));
            WHITE_CHECKER_CAPARISON_CAPES.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> WHITE_CHECKER_CAPARISON_FULLS = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.WHITE)
                continue;
            String name = "white_" + color.getName() + "_caparison_full";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new CaparisonItem(color, new Item.Properties()));
            WHITE_CHECKER_CAPARISON_FULLS.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> WHITE_CHECKER_CAPARISON_HALFS = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.WHITE)
                continue;
            String name = "white_" + color.getName() + "_caparison_half";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new CaparisonItem(color, new Item.Properties()));
            WHITE_CHECKER_CAPARISON_HALFS.put(color, item);
        }
    }

    public static final Map<DyeColor, RegistryObject<Item>> WHITE_CHECKER_CAPARISON_SHOULDERS = new EnumMap<>(DyeColor.class);
    static {
        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.WHITE)
                continue;
            String name = "white_" + color.getName() + "_caparison_shoulder";
            RegistryObject<Item> item = ITEMS.register(name,
                    () -> new CaparisonItem(color, new Item.Properties()));
            WHITE_CHECKER_CAPARISON_SHOULDERS.put(color, item);
        }
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}