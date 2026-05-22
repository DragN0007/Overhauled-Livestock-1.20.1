package com.dragn0007.dragnlivestock.items;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.compat.medievalembroidery.MECompatItems;
import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractWagon.Type;
import com.dragn0007.dragnlivestock.items.custom.WagonItem;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.time.LocalDate;
import java.time.Month;

public class LOItemGroup {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LivestockOverhaul.MODID);

    public static final RegistryObject<CreativeModeTab> LIVESTOCK_OVERHAUL_GROUP = CREATIVE_MODE_TABS.register("overhauled_livestock",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(LOItems.LIVESTOCK_OVERHAUL.get())).title(Component.translatable("itemGroup.overhauled_livestock")).withSearchBar()
                    .displayItems((displayParameters, output) -> {
                        LocalDate date = LocalDate.now();
                        Month month = date.getMonth();
                        int day = date.getDayOfMonth();

                        output.accept(LOItems.O_HORSE_SPAWN_EGG.get());
                        output.accept(LOItems.O_DONKEY_SPAWN_EGG.get());
                        output.accept(LOItems.O_MULE_SPAWN_EGG.get());
                        output.accept(LOItems.O_COW_SPAWN_EGG.get());
                        output.accept(LOItems.O_CHICKEN_SPAWN_EGG.get());
                        output.accept(LOItems.O_SALMON_SPAWN_EGG.get());
                        output.accept(LOItems.O_COD_SPAWN_EGG.get());
                        output.accept(LOItems.O_BEE_SPAWN_EGG.get());
                        output.accept(LOItems.O_RABBIT_SPAWN_EGG.get());
                        output.accept(LOItems.O_SHEEP_SPAWN_EGG.get());
                        output.accept(LOItems.O_LLAMA_SPAWN_EGG.get());
                        output.accept(LOItems.O_PIG_SPAWN_EGG.get());
                        output.accept(LOItems.O_MOOSHROOM_SPAWN_EGG.get());
                        output.accept(LOItems.O_CAMEL_SPAWN_EGG.get());
                        output.accept(LOItems.O_GOAT_SPAWN_EGG.get());
                        output.accept(LOItems.O_FROG_SPAWN_EGG.get());
                        output.accept(LOItems.FARM_GOAT_SPAWN_EGG.get());
                        output.accept(LOItems.GRUB_SPAWN_EGG.get());
                        output.accept(LOItems.WHEAT_MOOBLOOM_SPAWN_EGG.get());
                        output.accept(LOItems.SWEET_BERRY_MOOBLOOM_SPAWN_EGG.get());
                        output.accept(LOItems.PUMPKIN_MOOBLOOM_SPAWN_EGG.get());
                        output.accept(LOItems.POTATO_MOOBLOOM_SPAWN_EGG.get());
                        output.accept(LOItems.MELON_MOOBLOOM_SPAWN_EGG.get());
                        output.accept(LOItems.GLOW_BERRY_MOOBLOOM_SPAWN_EGG.get());
                        output.accept(LOItems.FLOWERING_MOOBLOOM_SPAWN_EGG.get());
                        output.accept(LOItems.CARROT_MOOBLOOM_SPAWN_EGG.get());
                        output.accept(LOItems.BEETROOT_MOOBLOOM_SPAWN_EGG.get());
                        output.accept(LOItems.AZALEA_MOOBLOOM_SPAWN_EGG.get());

                        if (ModList.get().isLoaded("thatsjustpeachy")) {
                            output.accept(LOItems.PEACH_MOOBLOOM_SPAWN_EGG.get());
                        }

                        output.accept(LOItems.CARIBOU_SPAWN_EGG.get());
                        output.accept(LOItems.UNICORN_SPAWN_EGG.get());

                        output.accept(LOItems.UTILITY_KNIFE.get());
                        output.accept(LOItems.SPINDLE.get());
                        output.accept(LOItems.MOUNT_KEY.get());
                        output.accept(LOItems.MOUNT_REGISTRY.get());
                        output.accept(LOItems.COAT_OSCILLATOR.get());
                        output.accept(LOItems.MARKING_OSCILLATOR.get());
                        output.accept(LOItems.BREED_OSCILLATOR.get());
                        output.accept(LOItems.MAGNIFYING_GLASS.get());

                        output.accept(LOItems.MANE_SCISSORS.get());
                        output.accept(LOItems.TAIL_SCISSORS.get());

                        output.accept(LOItems.LIGHT_HORSE_ARMOR_SMITHING_TEMPLATE.get());

                        output.accept(Items.LEATHER_HORSE_ARMOR);
                        output.accept(LOItems.CHAINMAIL_HORSE_ARMOR.get());
                        output.accept(LOItems.COPPER_HORSE_ARMOR.get());
                        output.accept(Items.IRON_HORSE_ARMOR);
                        output.accept(Items.GOLDEN_HORSE_ARMOR);
                        output.accept(LOItems.QUARTZ_HORSE_ARMOR.get());
                        output.accept(LOItems.EMERALD_HORSE_ARMOR.get());
                        output.accept(Items.DIAMOND_HORSE_ARMOR);
                        output.accept(LOItems.NETHERITE_HORSE_ARMOR.get());
                        if (ModList.get().isLoaded("medievalembroidery")) {
                            output.accept(LOItems.OBSIDIAN_HORSE_ARMOR.get());
                        }
                        if (ModList.get().isLoaded("deadlydinos")) {
                            output.accept(LOItems.RIOT_HORSE_ARMOR.get());
                        }

                        output.accept(LOItems.MINIMAL_LEATHER_HORSE_ARMOR.get());
                        output.accept(LOItems.MINIMAL_COPPER_HORSE_ARMOR.get());
                        output.accept(LOItems.MINIMAL_IRON_HORSE_ARMOR.get());
                        output.accept(LOItems.MINIMAL_GOLDEN_HORSE_ARMOR.get());
                        output.accept(LOItems.MINIMAL_QUARTZ_HORSE_ARMOR.get());
                        output.accept(LOItems.MINIMAL_EMERALD_HORSE_ARMOR.get());
                        output.accept(LOItems.MINIMAL_DIAMOND_HORSE_ARMOR.get());
                        output.accept(LOItems.MINIMAL_NETHERITE_HORSE_ARMOR.get());
                        if (ModList.get().isLoaded("medievalembroidery")) {
                            output.accept(LOItems.MINIMAL_OBSIDIAN_HORSE_ARMOR.get());
                        }

                        output.accept(LOItems.HEAVY_SADDLE.get());
                        output.accept(LOItems.BLACK_HEAVY_SADDLE.get());
                        output.accept(LOItems.WHITE_HEAVY_SADDLE.get());
                        output.accept(Items.SADDLE);
                        output.accept(LOItems.BLACK_SADDLE.get());
                        output.accept(LOItems.WHITE_SADDLE.get());
                        output.accept(LOItems.LIGHT_SADDLE.get());
                        output.accept(LOItems.BLACK_LIGHT_SADDLE.get());
                        output.accept(LOItems.WHITE_LIGHT_SADDLE.get());

                        output.accept(LOItems.RODEO_HARNESS.get());
                        output.accept(LOItems.WAGON_HARNESS.get());
                        output.accept(LOItems.BLACK_WAGON_HARNESS.get());

                        output.accept(LOItems.FERTILIZED_EGG.get());
                        output.accept(LOItems.FERTILIZED_AMERAUCANA_EGG.get());
                        output.accept(LOItems.FERTILIZED_CREAM_LEGBAR_EGG.get());
                        output.accept(LOItems.FERTILIZED_MARANS_EGG.get());
                        output.accept(LOItems.FERTILIZED_OLIVE_EGGER_EGG.get());
                        output.accept(LOItems.FERTILIZED_SUSSEX_SILKIE_EGG.get());
                        output.accept(LOItems.FERTILIZED_AYAM_CEMANI_EGG.get());
                        output.accept(LOItems.FERTILIZED_WYANDOTTE_EGG.get());
                        output.accept(LOItems.FERTILIZED_ORPINGTON_EGG.get());
                        output.accept(LOItems.FERTILIZED_POLISH_EGG.get());
                        output.accept(LOItems.FERTILIZED_BRAHMA_EGG.get());

                        output.accept(LOItems.RABBIT_POOP.get());

                        output.accept(LOItems.GENDER_TEST_STRIP.get());

                        output.accept(LOItems.OVERWORLD_UNICORN_HORN.get());
                        output.accept(LOItems.NETHER_UNICORN_HORN.get());
                        output.accept(LOItems.END_UNICORN_HORN.get());

                        output.accept(LOItems.WAGON_WHEEL_FRAME.get());
                        output.accept(LOItems.WAGON_WHEEL.get());
                        output.accept(LOItems.WAGON_AXEL.get());
                        output.accept(LOItems.WAGON_BODY.get());
                        output.accept(LOItems.WAGON_COVER.get());

                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.COVERED_WAGON.get()), Type.OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.COVERED_WAGON.get()), Type.ACACIA));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.COVERED_WAGON.get()), Type.BIRCH));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.COVERED_WAGON.get()), Type.CHERRY));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.COVERED_WAGON.get()), Type.CRIMSON));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.COVERED_WAGON.get()), Type.DARK_OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.COVERED_WAGON.get()), Type.JUNGLE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.COVERED_WAGON.get()), Type.MANGROVE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.COVERED_WAGON.get()), Type.SPRUCE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.COVERED_WAGON.get()), Type.WARPED));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.LIVESTOCK_WAGON.get()), Type.OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.LIVESTOCK_WAGON.get()), Type.ACACIA));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.LIVESTOCK_WAGON.get()), Type.BIRCH));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.LIVESTOCK_WAGON.get()), Type.CHERRY));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.LIVESTOCK_WAGON.get()), Type.CRIMSON));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.LIVESTOCK_WAGON.get()), Type.DARK_OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.LIVESTOCK_WAGON.get()), Type.JUNGLE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.LIVESTOCK_WAGON.get()), Type.MANGROVE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.LIVESTOCK_WAGON.get()), Type.SPRUCE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.LIVESTOCK_WAGON.get()), Type.WARPED));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.LUMBER_WAGON.get()), Type.OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.LUMBER_WAGON.get()), Type.ACACIA));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.LUMBER_WAGON.get()), Type.BIRCH));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.LUMBER_WAGON.get()), Type.CHERRY));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.LUMBER_WAGON.get()), Type.CRIMSON));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.LUMBER_WAGON.get()), Type.DARK_OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.LUMBER_WAGON.get()), Type.JUNGLE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.LUMBER_WAGON.get()), Type.MANGROVE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.LUMBER_WAGON.get()), Type.SPRUCE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.LUMBER_WAGON.get()), Type.WARPED));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.GOODS_CART.get()), Type.OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.GOODS_CART.get()), Type.ACACIA));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.GOODS_CART.get()), Type.BIRCH));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.GOODS_CART.get()), Type.CHERRY));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.GOODS_CART.get()), Type.CRIMSON));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.GOODS_CART.get()), Type.DARK_OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.GOODS_CART.get()), Type.JUNGLE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.GOODS_CART.get()), Type.MANGROVE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.GOODS_CART.get()), Type.SPRUCE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.GOODS_CART.get()), Type.WARPED));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.MINING_CART.get()), Type.OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.MINING_CART.get()), Type.ACACIA));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.MINING_CART.get()), Type.BIRCH));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.MINING_CART.get()), Type.CHERRY));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.MINING_CART.get()), Type.CRIMSON));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.MINING_CART.get()), Type.DARK_OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.MINING_CART.get()), Type.JUNGLE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.MINING_CART.get()), Type.MANGROVE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.MINING_CART.get()), Type.SPRUCE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.MINING_CART.get()), Type.WARPED));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.TRANSPORT_CART.get()), Type.OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.TRANSPORT_CART.get()), Type.ACACIA));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.TRANSPORT_CART.get()), Type.BIRCH));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.TRANSPORT_CART.get()), Type.CHERRY));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.TRANSPORT_CART.get()), Type.CRIMSON));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.TRANSPORT_CART.get()), Type.DARK_OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.TRANSPORT_CART.get()), Type.JUNGLE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.TRANSPORT_CART.get()), Type.MANGROVE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.TRANSPORT_CART.get()), Type.SPRUCE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.TRANSPORT_CART.get()), Type.WARPED));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.PLOW.get()), Type.OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.PLOW.get()), Type.ACACIA));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.PLOW.get()), Type.BIRCH));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.PLOW.get()), Type.CHERRY));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.PLOW.get()), Type.CRIMSON));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.PLOW.get()), Type.DARK_OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.PLOW.get()), Type.JUNGLE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.PLOW.get()), Type.MANGROVE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.PLOW.get()), Type.SPRUCE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.PLOW.get()), Type.WARPED));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.MOWER.get()), Type.OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.MOWER.get()), Type.ACACIA));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.MOWER.get()), Type.BIRCH));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.MOWER.get()), Type.CHERRY));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.MOWER.get()), Type.CRIMSON));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.MOWER.get()), Type.DARK_OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.MOWER.get()), Type.JUNGLE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.MOWER.get()), Type.MANGROVE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.MOWER.get()), Type.SPRUCE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.MOWER.get()), Type.WARPED));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.COUPE.get()), Type.OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.COUPE.get()), Type.ACACIA));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.COUPE.get()), Type.BIRCH));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.COUPE.get()), Type.CHERRY));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.COUPE.get()), Type.CRIMSON));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.COUPE.get()), Type.DARK_OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.COUPE.get()), Type.JUNGLE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.COUPE.get()), Type.MANGROVE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.COUPE.get()), Type.SPRUCE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.COUPE.get()), Type.WARPED));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.CABRIOLET.get()), Type.OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.CABRIOLET.get()), Type.ACACIA));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.CABRIOLET.get()), Type.BIRCH));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.CABRIOLET.get()), Type.CHERRY));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.CABRIOLET.get()), Type.CRIMSON));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.CABRIOLET.get()), Type.DARK_OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.CABRIOLET.get()), Type.JUNGLE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.CABRIOLET.get()), Type.MANGROVE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.CABRIOLET.get()), Type.SPRUCE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.CABRIOLET.get()), Type.WARPED));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.SLEIGH.get()), Type.OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.SLEIGH.get()), Type.ACACIA));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.SLEIGH.get()), Type.BIRCH));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.SLEIGH.get()), Type.CHERRY));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.SLEIGH.get()), Type.CRIMSON));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.SLEIGH.get()), Type.DARK_OAK));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.SLEIGH.get()), Type.JUNGLE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.SLEIGH.get()), Type.MANGROVE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.SLEIGH.get()), Type.SPRUCE));
                        output.accept(WagonItem.setupNbt(new ItemStack(LOItems.SLEIGH.get()), Type.WARPED));

                        for (DyeColor color : DyeColor.values()) {
                            output.accept(LOItems.BRAND_TAGS.get(color).get());
                        }

                        for (DyeColor color : DyeColor.values()) {
                            output.accept(LOItems.WOOL_STAPLES.get(color).get());
                        }

                        for (DyeColor color : DyeColor.values()) {
                            output.accept(LOItems.WOOL_DYE.get(color).get());
                        }

                        for (DyeColor color : DyeColor.values()) {
                            output.accept(LOItems.MEDIEVAL_BLANKETS.get(color).get());
                            output.accept(LOItems.MODERN_BLANKETS.get(color).get());
                            output.accept(LOItems.RACING_BLANKETS.get(color).get());
                            output.accept(LOItems.WESTERN_BLANKETS.get(color).get());
                        }

                        for (DyeColor color : DyeColor.values()) {
                            output.accept(LOItems.GRUB_SWEATERS.get(color).get());
                        }

                        for (DyeColor color : DyeColor.values()) {
                            output.accept(LOItems.ACCENTED_SADDLES.get(color).get());
                            output.accept(LOItems.ACCENTED_LIGHT_SADDLES.get(color).get());
                            output.accept(LOItems.ACCENTED_HEAVY_SADDLES.get(color).get());
                        }

                        for (DyeColor color : DyeColor.values()) {
                            if (color == DyeColor.BLACK)
                                continue;
                            output.accept(LOItems.ACCENTED_BLACK_SADDLES.get(color).get());
                            output.accept(LOItems.ACCENTED_BLACK_LIGHT_SADDLES.get(color).get());
                            output.accept(LOItems.ACCENTED_BLACK_HEAVY_SADDLES.get(color).get());
                        }

                        for (DyeColor color : DyeColor.values()) {
                            if (color == DyeColor.WHITE)
                                continue;
                            output.accept(LOItems.ACCENTED_WHITE_SADDLES.get(color).get());
                            output.accept(LOItems.ACCENTED_WHITE_LIGHT_SADDLES.get(color).get());
                            output.accept(LOItems.ACCENTED_WHITE_HEAVY_SADDLES.get(color).get());
                        }

                        output.accept(LOItems.AMERICAN_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.AMERICAN_MODERN_BLANKET.get());
                        output.accept(LOItems.AMERICAN_RACING_BLANKET.get());
                        output.accept(LOItems.AMERICAN_WESTERN_BLANKET.get());
                        output.accept(LOItems.AUTUMN_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.AUTUMN_MODERN_BLANKET.get());
                        output.accept(LOItems.AUTUMN_RACING_BLANKET.get());
                        output.accept(LOItems.AUTUMN_WESTERN_BLANKET.get());
                        output.accept(LOItems.ELDERBERRY_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.ELDERBERRY_MODERN_BLANKET.get());
                        output.accept(LOItems.ELDERBERRY_RACING_BLANKET.get());
                        output.accept(LOItems.ELDERBERRY_WESTERN_BLANKET.get());
                        output.accept(LOItems.PEACH_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.PEACH_MODERN_BLANKET.get());
                        output.accept(LOItems.PEACH_RACING_BLANKET.get());
                        output.accept(LOItems.PEACH_WESTERN_BLANKET.get());
                        output.accept(LOItems.SPRING_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.SPRING_MODERN_BLANKET.get());
                        output.accept(LOItems.SPRING_RACING_BLANKET.get());
                        output.accept(LOItems.SPRING_WESTERN_BLANKET.get());
                        output.accept(LOItems.SUMMER_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.SUMMER_MODERN_BLANKET.get());
                        output.accept(LOItems.SUMMER_RACING_BLANKET.get());
                        output.accept(LOItems.SUMMER_WESTERN_BLANKET.get());
                        output.accept(LOItems.WINTER_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.WINTER_MODERN_BLANKET.get());
                        output.accept(LOItems.WINTER_RACING_BLANKET.get());
                        output.accept(LOItems.WINTER_WESTERN_BLANKET.get());
                        output.accept(LOItems.PRIDE_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.PRIDE_MODERN_BLANKET.get());
                        output.accept(LOItems.PRIDE_RACING_BLANKET.get());
                        output.accept(LOItems.PRIDE_WESTERN_BLANKET.get());
                        output.accept(LOItems.LESBIAN_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.LESBIAN_MODERN_BLANKET.get());
                        output.accept(LOItems.LESBIAN_RACING_BLANKET.get());
                        output.accept(LOItems.LESBIAN_WESTERN_BLANKET.get());
                        output.accept(LOItems.BI_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.BI_MODERN_BLANKET.get());
                        output.accept(LOItems.BI_RACING_BLANKET.get());
                        output.accept(LOItems.BI_WESTERN_BLANKET.get());
                        output.accept(LOItems.NONBINARY_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.NONBINARY_MODERN_BLANKET.get());
                        output.accept(LOItems.NONBINARY_RACING_BLANKET.get());
                        output.accept(LOItems.NONBINARY_WESTERN_BLANKET.get());
                        output.accept(LOItems.TRANS_MEDIEVAL_BLANKET.get());
                        output.accept(LOItems.TRANS_MODERN_BLANKET.get());
                        output.accept(LOItems.TRANS_RACING_BLANKET.get());
                        output.accept(LOItems.TRANS_WESTERN_BLANKET.get());

                        if (ModList.get().isLoaded("medievalembroidery")) {

                            output.accept(MECompatItems.LEATHER_RUMP_STRAPS.get());
                            output.accept(MECompatItems.BLACK_RUMP_STRAPS.get());
                            output.accept(MECompatItems.WHITE_RUMP_STRAPS.get());

                            for (DyeColor color : DyeColor.values()) {
                                output.accept(MECompatItems.SOLID_CAPARISON_CAPES.get(color).get());
                                output.accept(MECompatItems.SOLID_CAPARISON_FULLS.get(color).get());
                                output.accept(MECompatItems.SOLID_CAPARISON_HALFS.get(color).get());
                                output.accept(MECompatItems.SOLID_CAPARISON_SHOULDERS.get(color).get());
                            }

                            for (DyeColor color : DyeColor.values()) {
                                if (color == DyeColor.BLACK)
                                    continue;
                                output.accept(MECompatItems.BLACK_CHECKER_CAPARISON_CAPES.get(color).get());
                                output.accept(MECompatItems.BLACK_CHECKER_CAPARISON_FULLS.get(color).get());
                                output.accept(MECompatItems.BLACK_CHECKER_CAPARISON_HALFS.get(color).get());
                                output.accept(MECompatItems.BLACK_CHECKER_CAPARISON_SHOULDERS.get(color).get());
                            }

                            for (DyeColor color : DyeColor.values()) {
                                if (color == DyeColor.WHITE)
                                    continue;
                                output.accept(MECompatItems.WHITE_CHECKER_CAPARISON_CAPES.get(color).get());
                                output.accept(MECompatItems.WHITE_CHECKER_CAPARISON_FULLS.get(color).get());
                                output.accept(MECompatItems.WHITE_CHECKER_CAPARISON_HALFS.get(color).get());
                                output.accept(MECompatItems.WHITE_CHECKER_CAPARISON_SHOULDERS.get(color).get());
                            }
                        }

                        if (LivestockOverhaulCommonConfig.ALLOW_HOLIDAY_EVENTS.get()) {
                            output.accept(LOItems.HOLIDAY_SADDLE.get());
                            output.accept(LOItems.HOLIDAY_LIGHT_SADDLE.get());
                            output.accept(LOItems.HOLIDAY_HEAVY_SADDLE.get());
                            output.accept(LOItems.HOLIDAY_WAGON_HARNESS.get());
                            output.accept(LOItems.RAINBOW_STRING_LIGHTS.get());
                            output.accept(LOItems.BLUE_STRING_LIGHTS.get());
                            output.accept(LOItems.RED_STRING_LIGHTS.get());
                            output.accept(LOItems.YELLOW_STRING_LIGHTS.get());
                            output.accept(LOItems.RED_NOSE.get());
                            output.accept(LOItems.HALLOW_HEART.get());
                        }
                    }).build());

    public static final RegistryObject<CreativeModeTab> LIVESTOCK_OVERHAUL_FOOD_GROUP = CREATIVE_MODE_TABS.register("overhauled_livestock_food",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(LOItems.LIVESTOCK_OVERHAUL_FOOD.get())).title(Component.translatable("itemGroup.overhauled_livestock_food"))
                    .displayItems((displayParameters, output) -> {

                        output.accept(Items.MILK_BUCKET);
                        output.accept(LOItems.SHEEP_MILK_BUCKET.get());
                        output.accept(LOItems.LLAMA_MILK_BUCKET.get());
                        output.accept(LOItems.GOAT_MILK_BUCKET.get());

                        output.accept(LOItems.COW_MILK_JUG.get());
                        output.accept(LOItems.SHEEP_MILK_JUG.get());
                        output.accept(LOItems.LLAMA_MILK_JUG.get());
                        output.accept(LOItems.GOAT_MILK_JUG.get());

                        output.accept(LOItems.RAW_CHEESE.get());
                        output.accept(LOItems.RAW_SHEEP_CHEESE.get());
                        output.accept(LOItems.RAW_LLAMA_CHEESE.get());
                        output.accept(LOItems.RAW_GOAT_CHEESE.get());

                        output.accept(LOItems.CHEESE.get());
                        output.accept(LOItems.SHEEP_CHEESE.get());
                        output.accept(LOItems.LLAMA_CHEESE.get());
                        output.accept(LOItems.GOAT_CHEESE.get());

                        output.accept(LOItems.EGG.get());
                        output.accept(LOItems.AMERAUCANA_EGG.get());
                        output.accept(LOItems.CREAM_LEGBAR_EGG.get());
                        output.accept(LOItems.MARANS_EGG.get());
                        output.accept(LOItems.OLIVE_EGGER_EGG.get());
                        output.accept(LOItems.SUSSEX_SILKIE_EGG.get());
                        output.accept(LOItems.AYAM_CEMANI_EGG.get());
                        output.accept(LOItems.WYANDOTTE_EGG.get());
                        output.accept(LOItems.ORPINGTON_EGG.get());
                        output.accept(LOItems.POLISH_EGG.get());
                        output.accept(LOItems.BRAHMA_EGG.get());

                        output.accept(LOItems.EGG_SALAD.get());
                        output.accept(LOItems.OMELETTE.get());
                        output.accept(LOItems.CHEESECAKE.get());

                        output.accept(LOItems.GRAIN_SOUP.get());
                        output.accept(LOItems.POTATO_SOUP.get());
                        output.accept(LOItems.GLOW_BERRY_SOUP.get());
                        output.accept(LOItems.PUMPKIN_SOUP.get());
                        output.accept(LOItems.CARROT_SOUP.get());
                        output.accept(LOItems.MELON_SOUP.get());
                        output.accept(LOItems.SWEET_BERRY_SOUP.get());

                        output.accept(LOItems.HORSE.get());
                        output.accept(LOItems.COOKED_HORSE.get());
                        output.accept(LOItems.LLAMA.get());
                        output.accept(LOItems.COOKED_LLAMA.get());
                        output.accept(LOItems.CAMEL.get());
                        output.accept(LOItems.COOKED_CAMEL.get());
                        output.accept(LOItems.CHEVON.get());
                        output.accept(LOItems.COOKED_CHEVON.get());
                        output.accept(LOItems.FROG.get());
                        output.accept(LOItems.COOKED_FROG.get());
                        output.accept(LOItems.GRUB.get());
                        output.accept(LOItems.COOKED_GRUB.get());
                        output.accept(LOItems.FISH_OIL.get());
                        output.accept(LOItems.ROE.get());
                        output.accept(LOItems.COD_ROE.get());
                        output.accept(LOItems.CARIBOU.get());
                        output.accept(LOItems.COOKED_CARIBOU.get());
                        output.accept(LOItems.UNICORN.get());
                        output.accept(LOItems.COOKED_UNICORN.get());

                        output.accept(LOItems.BEEF_STRIPS.get());
                        output.accept(LOItems.BEEF_JERKY.get());
                        output.accept(LOItems.CHICKEN_STRIPS.get());
                        output.accept(LOItems.CHICKEN_JERKY.get());
                        output.accept(LOItems.PORK_STRIPS.get());
                        output.accept(LOItems.PORK_JERKY.get());
                        output.accept(LOItems.MUTTON_STRIPS.get());
                        output.accept(LOItems.MUTTON_JERKY.get());
                        output.accept(LOItems.FISH_STRIPS.get());
                        output.accept(LOItems.FISH_JERKY.get());
                        output.accept(LOItems.GAME_STRIPS.get());
                        output.accept(LOItems.GAME_JERKY.get());
                        output.accept(LOItems.GENERIC_STRIPS.get());
                        output.accept(LOItems.GENERIC_JERKY.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
