package com.dragn0007.dragnlivestock.event;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.bee.OBee;
import com.dragn0007.dragnlivestock.entities.bee.OBeeRenderer;
import com.dragn0007.dragnlivestock.entities.camel.OCamel;
import com.dragn0007.dragnlivestock.entities.camel.OCamelRender;
import com.dragn0007.dragnlivestock.entities.caribou.Caribou;
import com.dragn0007.dragnlivestock.entities.caribou.CaribouRender;
import com.dragn0007.dragnlivestock.entities.chicken.OChicken;
import com.dragn0007.dragnlivestock.entities.chicken.OChickenRender;
import com.dragn0007.dragnlivestock.entities.cod.OCod;
import com.dragn0007.dragnlivestock.entities.cod.OCodRender;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.entities.cow.OCowRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.azalea.AzaleaMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.azalea.AzaleaMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.beetroot.BeetrootMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.beetroot.BeetrootMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.carrot.CarrotMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.carrot.CarrotMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.flowering.FloweringMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.flowering.FloweringMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.glow_berry.GlowBerryMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.glow_berry.GlowBerryMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.melon.MelonMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.melon.MelonMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.peach.PeachMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.peach.PeachMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.potato.PotatoMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.potato.PotatoMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.pumpkin.PumpkinMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.pumpkin.PumpkinMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.sweet_berry.SweetBerryMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.sweet_berry.SweetBerryMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.wheat.WheatMoobloom;
import com.dragn0007.dragnlivestock.entities.cow.moobloom.wheat.WheatMoobloomRender;
import com.dragn0007.dragnlivestock.entities.cow.mooshroom.OMooshroom;
import com.dragn0007.dragnlivestock.entities.cow.mooshroom.OMooshroomRender;
import com.dragn0007.dragnlivestock.entities.donkey.ODonkey;
import com.dragn0007.dragnlivestock.entities.donkey.ODonkeyRender;
import com.dragn0007.dragnlivestock.entities.frog.OFrog;
import com.dragn0007.dragnlivestock.entities.frog.OFrogRender;
import com.dragn0007.dragnlivestock.entities.frog.ReplacedTadpoleRender;
import com.dragn0007.dragnlivestock.entities.frog.food.Grub;
import com.dragn0007.dragnlivestock.entities.frog.food.GrubRender;
import com.dragn0007.dragnlivestock.entities.goat.OGoat;
import com.dragn0007.dragnlivestock.entities.goat.OGoatGoatRender;
import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.entities.horse.OHorseRender;
import com.dragn0007.dragnlivestock.entities.horse.headlesshorseman.HeadlessHorseman;
import com.dragn0007.dragnlivestock.entities.horse.headlesshorseman.HeadlessHorsemanRender;
import com.dragn0007.dragnlivestock.entities.llama.OLlama;
import com.dragn0007.dragnlivestock.entities.llama.OLlamaRender;
import com.dragn0007.dragnlivestock.entities.mule.OMule;
import com.dragn0007.dragnlivestock.entities.mule.OMuleRender;
import com.dragn0007.dragnlivestock.entities.pig.OPig;
import com.dragn0007.dragnlivestock.entities.pig.OPigRender;
import com.dragn0007.dragnlivestock.entities.rabbit.ORabbit;
import com.dragn0007.dragnlivestock.entities.rabbit.ORabbitRender;
import com.dragn0007.dragnlivestock.entities.salmon.OSalmon;
import com.dragn0007.dragnlivestock.entities.salmon.OSalmonRender;
import com.dragn0007.dragnlivestock.entities.sheep.OSheep;
import com.dragn0007.dragnlivestock.entities.sheep.OSheepRender;
import com.dragn0007.dragnlivestock.entities.unicorn.Unicorn;
import com.dragn0007.dragnlivestock.entities.unicorn.UnicornRender;
import com.dragn0007.dragnlivestock.entities.wagon.WagonRenderer;
import com.dragn0007.dragnlivestock.gui.*;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = LivestockOverhaul.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LivestockOverhaulEvent {

    @SubscribeEvent
    public static void entityAttrbiuteCreationEvent(EntityAttributeCreationEvent event) {
        event.put(EntityTypes.O_HORSE_ENTITY.get(), OHorse.createBaseOHorseAttributes().build());
        event.put(EntityTypes.O_COW_ENTITY.get(), OCow.createAttributes().build());
        event.put(EntityTypes.O_CHICKEN_ENTITY.get(), OChicken.createAttributes().build());
        event.put(EntityTypes.O_SALMON_ENTITY.get(), OSalmon.createAttributes().build());
        event.put(EntityTypes.O_COD_ENTITY.get(), OCod.createAttributes().build());
        event.put(EntityTypes.O_BEE_ENTITY.get(), OBee.createAttributes().build());
        event.put(EntityTypes.O_RABBIT_ENTITY.get(), ORabbit.createAttributes().build());
        event.put(EntityTypes.O_SHEEP_ENTITY.get(), OSheep.createAttributes().build());
        event.put(EntityTypes.O_LLAMA_ENTITY.get(), OLlama.createAttributes().build());
        event.put(EntityTypes.O_PIG_ENTITY.get(), OPig.createAttributes().build());
        event.put(EntityTypes.O_DONKEY_ENTITY.get(), ODonkey.createBaseHorseAttributes().build());
        event.put(EntityTypes.O_MULE_ENTITY.get(), OMule.createBaseHorseAttributes().build());
        event.put(EntityTypes.O_MOOSHROOM_ENTITY.get(), OMooshroom.createAttributes().build());
        event.put(EntityTypes.O_CAMEL_ENTITY.get(), OCamel.createBaseHorseAttributes().build());
        event.put(EntityTypes.O_GOAT_ENTITY.get(), OGoat.createAttributes().build());
        event.put(EntityTypes.O_FROG_ENTITY.get(), OFrog.createAttributes().build());

        event.put(EntityTypes.CARIBOU_ENTITY.get(), Caribou.createBaseHorseAttributes().build());

        event.put(EntityTypes.GRUB_ENTITY.get(), Grub.createAttributes().build());

        event.put(EntityTypes.HEADLESS_HORSEMAN_ENTITY.get(), HeadlessHorseman.createBaseHorseAttributes().build());

        event.put(EntityTypes.UNICORN_ENTITY.get(), Unicorn.createUnicornAttributes().build());

        event.put(EntityTypes.WHEAT_MOOBLOOM_ENTITY.get(), WheatMoobloom.createAttributes().build());
        event.put(EntityTypes.SWEET_BERRY_MOOBLOOM_ENTITY.get(), SweetBerryMoobloom.createAttributes().build());
        event.put(EntityTypes.PUMPKIN_MOOBLOOM_ENTITY.get(), PumpkinMoobloom.createAttributes().build());
        event.put(EntityTypes.POTATO_MOOBLOOM_ENTITY.get(), PotatoMoobloom.createAttributes().build());
        event.put(EntityTypes.MELON_MOOBLOOM_ENTITY.get(), MelonMoobloom.createAttributes().build());
        event.put(EntityTypes.GLOW_BERRY_MOOBLOOM_ENTITY.get(), GlowBerryMoobloom.createAttributes().build());
        event.put(EntityTypes.FLOWERING_MOOBLOOM_ENTITY.get(), FloweringMoobloom.createAttributes().build());
        event.put(EntityTypes.CARROT_MOOBLOOM_ENTITY.get(), CarrotMoobloom.createAttributes().build());
        event.put(EntityTypes.BEETROOT_MOOBLOOM_ENTITY.get(), BeetrootMoobloom.createAttributes().build());
        event.put(EntityTypes.AZALEA_MOOBLOOM_ENTITY.get(), AzaleaMoobloom.createAttributes().build());
        event.put(EntityTypes.PEACH_MOOBLOOM_ENTITY.get(), PeachMoobloom.createAttributes().build());
    }

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

        MenuScreens.register(LOMenuTypes.O_HORSE_MENU.get(), OHorseScreen::new);
        MenuScreens.register(LOMenuTypes.OX_MENU.get(), OxScreen::new);
        MenuScreens.register(LOMenuTypes.O_MOUNT_MENU.get(), OMountScreen::new);
        MenuScreens.register(LOMenuTypes.O_MULE_MENU.get(), OMuleScreen::new);
        MenuScreens.register(LOMenuTypes.O_DONKEY_MENU.get(), ODonkeyScreen::new);
        MenuScreens.register(LOMenuTypes.O_CAMEL_MENU.get(), OCamelScreen::new);
        MenuScreens.register(LOMenuTypes.O_CARIBOU_MENU.get(), CaribouScreen::new);
        MenuScreens.register(LOMenuTypes.UNICORN_MENU.get(), UnicornScreen::new);

        MenuScreens.register(LOMenuTypes.COVERED_WAGON.get(), CoveredWagonScreen::new);

        //Vanilla Replacers
        EntityRenderers.register(EntityType.TADPOLE, ReplacedTadpoleRender::new);
    }

    @SubscribeEvent
    public static void spawnPlacementRegisterEvent(SpawnPlacementRegisterEvent event) {
        event.register(EntityTypes.CARIBOU_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        event.register(EntityTypes.GRUB_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
    }

}