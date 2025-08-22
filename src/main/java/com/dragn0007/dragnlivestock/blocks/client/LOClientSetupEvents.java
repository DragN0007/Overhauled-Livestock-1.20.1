package com.dragn0007.dragnlivestock.blocks.client;

import com.dragn0007.dragnlivestock.blocks.client.gui.*;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.OCodRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.OSalmonRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.WagonRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.bee.OBeeRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.camel.OCamelRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.caribou.CaribouRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.chicken.OChickenRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.cow.OCowRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.donkey.ODonkeyRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.frog.GrubRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.frog.OFrogRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.frog.ReplacedTadpoleRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.goat.OGoatGoatRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.horse.HeadlessHorsemanRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.horse.OHorseRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.llama.OLlamaRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.moobloom.azalea.AzaleaMoobloomRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.moobloom.beetroot.BeetrootMoobloomRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.moobloom.carrot.CarrotMoobloomRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.moobloom.flowering.FloweringMoobloomRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.moobloom.glow_berry.GlowBerryMoobloomRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.moobloom.melon.MelonMoobloomRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.moobloom.peach.PeachMoobloomRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.moobloom.potato.PotatoMoobloomRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.moobloom.pumpkin.PumpkinMoobloomRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.moobloom.sweet_berry.SweetBerryMoobloomRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.moobloom.wheat.WheatMoobloomRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.mooshroom.OMooshroomRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.mule.OMuleRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.pig.OPigRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.rabbit.ORabbitRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.sheep.OSheepRenderer;
import com.dragn0007.dragnlivestock.blocks.client.render.entity.unicorn.UnicornRenderer;
import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.entities.EntityTypes;
import com.dragn0007.dragnlivestock.common.menus.LOMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = LivestockOverhaul.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class LOClientSetupEvents {

    @SubscribeEvent
    public static void clientSetupEvent(FMLClientSetupEvent event) {
        MenuScreens.register(LOMenuTypes.O_HORSE_MENU.get(), OHorseScreen::new);
        MenuScreens.register(LOMenuTypes.OX_MENU.get(), OxScreen::new);
        MenuScreens.register(LOMenuTypes.O_MOUNT_MENU.get(), OMountScreen::new);
        MenuScreens.register(LOMenuTypes.O_MULE_MENU.get(), OMuleScreen::new);
        MenuScreens.register(LOMenuTypes.O_DONKEY_MENU.get(), ODonkeyScreen::new);
        MenuScreens.register(LOMenuTypes.O_CAMEL_MENU.get(), OCamelScreen::new);
        MenuScreens.register(LOMenuTypes.O_CARIBOU_MENU.get(), CaribouScreen::new);
        MenuScreens.register(LOMenuTypes.UNICORN_MENU.get(), UnicornScreen::new);

        MenuScreens.register(LOMenuTypes.COVERED_WAGON.get(), CoveredWagonScreen::new);
    }
    
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityTypes.O_HORSE_ENTITY.get(), OHorseRenderer::new);
        event.registerEntityRenderer(EntityTypes.O_COW_ENTITY.get(), OCowRenderer::new);
        event.registerEntityRenderer(EntityTypes.O_CHICKEN_ENTITY.get(), OChickenRenderer::new);
        event.registerEntityRenderer(EntityTypes.O_SALMON_ENTITY.get(), OSalmonRenderer::new);
        event.registerEntityRenderer(EntityTypes.O_COD_ENTITY.get(), OCodRenderer::new);
        event.registerEntityRenderer(EntityTypes.O_BEE_ENTITY.get(), OBeeRenderer::new);
        event.registerEntityRenderer(EntityTypes.O_RABBIT_ENTITY.get(), ORabbitRenderer::new);
        event.registerEntityRenderer(EntityTypes.O_SHEEP_ENTITY.get(), OSheepRenderer::new);
        event.registerEntityRenderer(EntityTypes.O_LLAMA_ENTITY.get(), OLlamaRenderer::new);
        event.registerEntityRenderer(EntityTypes.O_PIG_ENTITY.get(), OPigRenderer::new);
        event.registerEntityRenderer(EntityTypes.O_DONKEY_ENTITY.get(), ODonkeyRenderer::new);
        event.registerEntityRenderer(EntityTypes.O_MULE_ENTITY.get(), OMuleRenderer::new);
        event.registerEntityRenderer(EntityTypes.O_MOOSHROOM_ENTITY.get(), OMooshroomRenderer::new);
        event.registerEntityRenderer(EntityTypes.O_CAMEL_ENTITY.get(), OCamelRenderer::new);
        event.registerEntityRenderer(EntityTypes.O_GOAT_ENTITY.get(), OGoatGoatRenderer::new);
        event.registerEntityRenderer(EntityTypes.O_FROG_ENTITY.get(), OFrogRenderer::new);

        event.registerEntityRenderer(EntityTypes.CARIBOU_ENTITY.get(), CaribouRenderer::new);

        event.registerEntityRenderer(EntityTypes.GRUB_ENTITY.get(), GrubRenderer::new);
        event.registerEntityRenderer(EntityTypes.UNICORN_ENTITY.get(), UnicornRenderer::new);
        event.registerEntityRenderer(EntityTypes.HEADLESS_HORSEMAN_ENTITY.get(), HeadlessHorsemanRenderer::new);

        event.registerEntityRenderer(EntityTypes.WHEAT_MOOBLOOM_ENTITY.get(), WheatMoobloomRenderer::new);
        event.registerEntityRenderer(EntityTypes.SWEET_BERRY_MOOBLOOM_ENTITY.get(), SweetBerryMoobloomRenderer::new);
        event.registerEntityRenderer(EntityTypes.PUMPKIN_MOOBLOOM_ENTITY.get(), PumpkinMoobloomRenderer::new);
        event.registerEntityRenderer(EntityTypes.POTATO_MOOBLOOM_ENTITY.get(), PotatoMoobloomRenderer::new);
        event.registerEntityRenderer(EntityTypes.MELON_MOOBLOOM_ENTITY.get(), MelonMoobloomRenderer::new);
        event.registerEntityRenderer(EntityTypes.GLOW_BERRY_MOOBLOOM_ENTITY.get(), GlowBerryMoobloomRenderer::new);
        event.registerEntityRenderer(EntityTypes.FLOWERING_MOOBLOOM_ENTITY.get(), FloweringMoobloomRenderer::new);
        event.registerEntityRenderer(EntityTypes.CARROT_MOOBLOOM_ENTITY.get(), CarrotMoobloomRenderer::new);
        event.registerEntityRenderer(EntityTypes.BEETROOT_MOOBLOOM_ENTITY.get(), BeetrootMoobloomRenderer::new);
        event.registerEntityRenderer(EntityTypes.AZALEA_MOOBLOOM_ENTITY.get(), AzaleaMoobloomRenderer::new);
        event.registerEntityRenderer(EntityTypes.PEACH_MOOBLOOM_ENTITY.get(), PeachMoobloomRenderer::new);
        event.registerEntityRenderer(EntityTypes.COVERED_WAGON.get(), c -> new WagonRenderer<>(c, "covered_wagon"));

        // Vanilla replacements
        event.registerEntityRenderer(EntityType.TADPOLE, ReplacedTadpoleRenderer::new);

    }

}
