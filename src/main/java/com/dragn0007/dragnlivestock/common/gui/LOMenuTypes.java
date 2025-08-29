package com.dragn0007.dragnlivestock.common.gui;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LOMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, LivestockOverhaul.MODID);
    public static final RegistryObject<MenuType<OHorseMenu>> O_HORSE_MENU = registerMenuType("ohorse_menu", OHorseMenu::new);
    public static final RegistryObject<MenuType<OMountMenu>> O_MOUNT_MENU = registerMenuType("omount_menu", OMountMenu::new);
    public static final RegistryObject<MenuType<OMuleMenu>> O_MULE_MENU = registerMenuType("omule_menu", OMuleMenu::new);
    public static final RegistryObject<MenuType<ODonkeyMenu>> O_DONKEY_MENU = registerMenuType("odonkey_menu", ODonkeyMenu::new);
    public static final RegistryObject<MenuType<OCamelMenu>> O_CAMEL_MENU = registerMenuType("ocamel_menu", OCamelMenu::new);
    public static final RegistryObject<MenuType<CaribouMenu>> O_CARIBOU_MENU = registerMenuType("ocaribou_menu", CaribouMenu::new);
    public static final RegistryObject<MenuType<OxMenu>> OX_MENU = registerMenuType("ox_menu", OxMenu::new);
    public static final RegistryObject<MenuType<UnicornMenu>> UNICORN_MENU = registerMenuType("unicorn_menu", UnicornMenu::new);

    public static final RegistryObject<MenuType<HugeWagonMenu>> HUGE_INVENTORY_WAGON = registerMenuType("huge_wagon", HugeWagonMenu::new); //104
    public static final RegistryObject<MenuType<LargeWagonMenu>> LARGE_INVENTORY_WAGON = registerMenuType("large_wagon", LargeWagonMenu::new); //54
    public static final RegistryObject<MenuType<DefaultWagonMenu>> DEFAULT_INVENTORY_WAGON = registerMenuType("default_wagon", DefaultWagonMenu::new); //36
    public static final RegistryObject<MenuType<SmallWagonMenu>> SMALL_INVENTORY_WAGON = registerMenuType("small_wagon", SmallWagonMenu::new); //18
    public static final RegistryObject<MenuType<TinyWagonMenu>> TINY_INVENTORY_WAGON = registerMenuType("tiny_wagon", TinyWagonMenu::new); //9
    public static final RegistryObject<MenuType<LumberWagonMenu>> LUMBER_WAGON = registerMenuType("lumber_wagon", LumberWagonMenu::new);
    public static final RegistryObject<MenuType<MiningCartMenu>> MINING_CART = registerMenuType("mining_cart", MiningCartMenu::new);

    public static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENU_TYPES.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }
}
