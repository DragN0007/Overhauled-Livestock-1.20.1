package com.dragn0007.dragnlivestock.client.event;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.client.entities.wagon.WagonRenderer;
import com.dragn0007.dragnlivestock.client.gui.*;
import com.dragn0007.dragnlivestock.common.gui.LOMenuTypes;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.bee.OBeeRenderer;
import com.dragn0007.dragnlivestock.entities.camel.OCamelRender;
import com.dragn0007.dragnlivestock.entities.caribou.CaribouRender;
import com.dragn0007.dragnlivestock.entities.chicken.OChickenRender;
import com.dragn0007.dragnlivestock.entities.cod.OCodRender;
import com.dragn0007.dragnlivestock.entities.cow.OCowRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.azalea.AzaleaMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.beetroot.BeetrootMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.carrot.CarrotMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.flowering.FloweringMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.glow_berry.GlowBerryMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.melon.MelonMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.peach.PeachMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.potato.PotatoMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.pumpkin.PumpkinMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.sweet_berry.SweetBerryMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.wheat.WheatMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.mooshroom.OMooshroomRender;
import com.dragn0007.dragnlivestock.entities.donkey.ODonkeyRender;
import com.dragn0007.dragnlivestock.entities.frog.OFrogRender;
import com.dragn0007.dragnlivestock.entities.frog.ReplacedTadpoleRender;
import com.dragn0007.dragnlivestock.entities.frog.food.GrubRender;
import com.dragn0007.dragnlivestock.entities.goat.OGoatGoatRender;
import com.dragn0007.dragnlivestock.entities.horse.OHorseRender;
import com.dragn0007.dragnlivestock.entities.horse.headlesshorseman.HeadlessHorsemanRender;
import com.dragn0007.dragnlivestock.entities.llama.OLlamaRender;
import com.dragn0007.dragnlivestock.entities.mule.OMuleRender;
import com.dragn0007.dragnlivestock.entities.pig.OPigRender;
import com.dragn0007.dragnlivestock.entities.rabbit.ORabbitRender;
import com.dragn0007.dragnlivestock.entities.salmon.OSalmonRender;
import com.dragn0007.dragnlivestock.entities.sheep.OSheepRender;
import com.dragn0007.dragnlivestock.entities.unicorn.UnicornRender;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = LivestockOverhaul.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class LivestockOverhaulClientEvent {

    @SubscribeEvent
    public static void clientSetupEvent(FMLClientSetupEvent event) {
        EntityRenderers.register(EntityTypes.O_HORSE_ENTITY.get(), OHorseRender::new);
        EntityRenderers.register(EntityTypes.O_COW_ENTITY.get(), OCowRender::new);
        EntityRenderers.register(EntityTypes.O_CHICKEN_ENTITY.get(), OChickenRender::new);
        EntityRenderers.register(EntityTypes.O_SALMON_ENTITY.get(), OSalmonRender::new);
        EntityRenderers.register(EntityTypes.O_COD_ENTITY.get(), OCodRender::new);
        EntityRenderers.register(EntityTypes.O_BEE_ENTITY.get(), OBeeRenderer::new);
        EntityRenderers.register(EntityTypes.O_RABBIT_ENTITY.get(), ORabbitRender::new);
        EntityRenderers.register(EntityTypes.O_SHEEP_ENTITY.get(), OSheepRender::new);
        EntityRenderers.register(EntityTypes.O_LLAMA_ENTITY.get(), OLlamaRender::new);
        EntityRenderers.register(EntityTypes.O_PIG_ENTITY.get(), OPigRender::new);
        EntityRenderers.register(EntityTypes.O_DONKEY_ENTITY.get(), ODonkeyRender::new);
        EntityRenderers.register(EntityTypes.O_MULE_ENTITY.get(), OMuleRender::new);
        EntityRenderers.register(EntityTypes.O_MOOSHROOM_ENTITY.get(), OMooshroomRender::new);
        EntityRenderers.register(EntityTypes.O_CAMEL_ENTITY.get(), OCamelRender::new);
        EntityRenderers.register(EntityTypes.O_GOAT_ENTITY.get(), OGoatGoatRender::new);
        EntityRenderers.register(EntityTypes.O_FROG_ENTITY.get(), OFrogRender::new);

        EntityRenderers.register(EntityTypes.CARIBOU_ENTITY.get(), CaribouRender::new);
        EntityRenderers.register(EntityTypes.GRUB_ENTITY.get(), GrubRender::new);
        EntityRenderers.register(EntityTypes.UNICORN_ENTITY.get(), UnicornRender::new);
        EntityRenderers.register(EntityTypes.HEADLESS_HORSEMAN_ENTITY.get(), HeadlessHorsemanRender::new);

        EntityRenderers.register(EntityTypes.WHEAT_MOOBLOOM_ENTITY.get(), WheatMoobloomRender::new);
        EntityRenderers.register(EntityTypes.SWEET_BERRY_MOOBLOOM_ENTITY.get(), SweetBerryMoobloomRender::new);
        EntityRenderers.register(EntityTypes.PUMPKIN_MOOBLOOM_ENTITY.get(), PumpkinMoobloomRender::new);
        EntityRenderers.register(EntityTypes.POTATO_MOOBLOOM_ENTITY.get(), PotatoMoobloomRender::new);
        EntityRenderers.register(EntityTypes.MELON_MOOBLOOM_ENTITY.get(), MelonMoobloomRender::new);
        EntityRenderers.register(EntityTypes.GLOW_BERRY_MOOBLOOM_ENTITY.get(), GlowBerryMoobloomRender::new);
        EntityRenderers.register(EntityTypes.FLOWERING_MOOBLOOM_ENTITY.get(), FloweringMoobloomRender::new);
        EntityRenderers.register(EntityTypes.CARROT_MOOBLOOM_ENTITY.get(), CarrotMoobloomRender::new);
        EntityRenderers.register(EntityTypes.BEETROOT_MOOBLOOM_ENTITY.get(), BeetrootMoobloomRender::new);
        EntityRenderers.register(EntityTypes.AZALEA_MOOBLOOM_ENTITY.get(), AzaleaMoobloomRender::new);
        EntityRenderers.register(EntityTypes.PEACH_MOOBLOOM_ENTITY.get(), PeachMoobloomRender::new);

        EntityRenderers.register(EntityTypes.COVERED_WAGON.get(), c -> new WagonRenderer<>(c, "covered_wagon"));
        EntityRenderers.register(EntityTypes.LIVESTOCK_WAGON.get(), c -> new WagonRenderer<>(c, "livestock_wagon"));
        EntityRenderers.register(EntityTypes.LUMBER_WAGON.get(), c -> new WagonRenderer<>(c, "lumber_wagon"));
        EntityRenderers.register(EntityTypes.GOODS_CART.get(), c -> new WagonRenderer<>(c, "goods_cart"));
        EntityRenderers.register(EntityTypes.MINING_CART.get(), c -> new WagonRenderer<>(c, "mining_cart"));
        EntityRenderers.register(EntityTypes.TRANSPORT_CART.get(), c -> new WagonRenderer<>(c, "transport_cart"));
        EntityRenderers.register(EntityTypes.PLOW.get(), c -> new WagonRenderer<>(c, "plow"));

        MenuScreens.register(LOMenuTypes.O_HORSE_MENU.get(), OHorseScreen::new);
        MenuScreens.register(LOMenuTypes.OX_MENU.get(), OxScreen::new);
        MenuScreens.register(LOMenuTypes.O_MOUNT_MENU.get(), OMountScreen::new);
        MenuScreens.register(LOMenuTypes.O_MULE_MENU.get(), OMuleScreen::new);
        MenuScreens.register(LOMenuTypes.O_DONKEY_MENU.get(), ODonkeyScreen::new);
        MenuScreens.register(LOMenuTypes.O_CAMEL_MENU.get(), OCamelScreen::new);
        MenuScreens.register(LOMenuTypes.O_CARIBOU_MENU.get(), CaribouScreen::new);
        MenuScreens.register(LOMenuTypes.UNICORN_MENU.get(), UnicornScreen::new);

        MenuScreens.register(LOMenuTypes.COVERED_WAGON.get(), CoveredWagonScreen::new);
        MenuScreens.register(LOMenuTypes.LIVESTOCK_WAGON.get(), LivestockWagonScreen::new);
        MenuScreens.register(LOMenuTypes.LUMBER_WAGON.get(), LumberWagonScreen::new);
        MenuScreens.register(LOMenuTypes.GOODS_CART.get(), GoodsCartScreen::new);
        MenuScreens.register(LOMenuTypes.MINING_CART.get(), MiningCartScreen::new);
        MenuScreens.register(LOMenuTypes.TRANSPORT_CART.get(), TransportCartScreen::new);

        //Vanilla Replacers
        EntityRenderers.register(EntityType.TADPOLE, ReplacedTadpoleRender::new);
    }

    public static final KeyMapping HORSE_SPEED_UP = new KeyMapping("key.dragnlivestock.horse_speed_up", InputConstants.KEY_LCONTROL, "key.dragnlivestock.categories.dragnlivestock");
    public static final KeyMapping HORSE_SLOW_DOWN = new KeyMapping("key.dragnlivestock.horse_slow_down", InputConstants.KEY_LALT, "key.dragnlivestock.categories.dragnlivestock");
    public static final KeyMapping HORSE_BOW = new KeyMapping("key.dragnlivestock.horse_bow", InputConstants.KEY_B, "key.dragnlivestock.categories.dragnlivestock");
    public static final KeyMapping HORSE_PIAFFE = new KeyMapping("key.dragnlivestock.horse_piaffe", InputConstants.KEY_P, "key.dragnlivestock.categories.dragnlivestock");
    public static final KeyMapping HORSE_SPANISH_WALK_TOGGLE = new KeyMapping("key.dragnlivestock.horse_spanish_walk_toggle", InputConstants.KEY_DOWN, "key.dragnlivestock.categories.dragnlivestock");
    public static final KeyMapping HORSE_WAVE = new KeyMapping("key.dragnlivestock.horse_wave", InputConstants.KEY_G, "key.dragnlivestock.categories.dragnlivestock");
    public static final KeyMapping HORSE_LEVADE = new KeyMapping("key.dragnlivestock.horse_levade", InputConstants.KEY_L, "key.dragnlivestock.categories.dragnlivestock");

    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        KeyMapping[] keyMappings = {HORSE_SPEED_UP, HORSE_SLOW_DOWN, HORSE_BOW, HORSE_PIAFFE, HORSE_SPANISH_WALK_TOGGLE, HORSE_WAVE, HORSE_LEVADE};
        for (KeyMapping keyMapping : keyMappings) {
            event.register(keyMapping);
        }
    }

}