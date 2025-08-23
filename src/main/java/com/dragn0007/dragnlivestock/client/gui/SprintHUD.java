package com.dragn0007.dragnlivestock.client.gui;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LivestockOverhaul.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SprintHUD {

    @SubscribeEvent
    public static void onFMLClientSetupEvent(RegisterGuiOverlaysEvent event) {

        event.registerAbove(VanillaGuiOverlay.HOTBAR.id(), "sprint_hud", (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
            Minecraft minecraft = Minecraft.getInstance();
            Player player = (Player) minecraft.getCameraEntity();

            if(!minecraft.options.hideGui && player instanceof LocalPlayer && player.getVehicle() instanceof OHorse oHorse) {
                int x = (screenWidth / 2) + LivestockOverhaulClientConfig.SPRINT_X.get();
                int y = screenHeight - LivestockOverhaulClientConfig.SPRINT_Y.get();

                if (oHorse.isWarmbloodedBreed()) {
                    if (oHorse.sprintTick >= ((oHorse.maxSprint + oHorse.warmbloodSprintAddition) * 0.90)) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_full.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if ((oHorse.sprintTick < ((oHorse.maxSprint + oHorse.warmbloodSprintAddition) * 0.90)) && (oHorse.sprintTick > ((oHorse.maxSprint + oHorse.warmbloodSprintAddition) * 0.65))) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_quarter_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if ((oHorse.sprintTick < ((oHorse.maxSprint + oHorse.warmbloodSprintAddition) * 0.65)) && (oHorse.sprintTick > ((oHorse.maxSprint + oHorse.warmbloodSprintAddition) * 0.35))) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_half_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if ((oHorse.sprintTick < ((oHorse.maxSprint + oHorse.warmbloodSprintAddition) * 0.35)) && (oHorse.sprintTick > ((oHorse.maxSprint + oHorse.warmbloodSprintAddition) * 0.05))) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_3_quarters_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if (oHorse.sprintTick < ((oHorse.maxSprint + oHorse.warmbloodSprintAddition) * 0.05)) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    }
                }

                if (oHorse.isStockBreed()) {
                    if (oHorse.sprintTick >= ((oHorse.maxSprint + oHorse.stockSprintAddition) * 0.90)) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_full.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if ((oHorse.sprintTick < ((oHorse.maxSprint + oHorse.stockSprintAddition) * 0.90)) && (oHorse.sprintTick > ((oHorse.maxSprint + oHorse.stockSprintAddition) * 0.65))) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_quarter_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if ((oHorse.sprintTick < ((oHorse.maxSprint + oHorse.stockSprintAddition) * 0.65)) && (oHorse.sprintTick > ((oHorse.maxSprint + oHorse.stockSprintAddition) * 0.35))) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_half_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if ((oHorse.sprintTick < ((oHorse.maxSprint + oHorse.stockSprintAddition) * 0.35)) && (oHorse.sprintTick > ((oHorse.maxSprint + oHorse.stockSprintAddition) * 0.05))) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_3_quarters_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if (oHorse.sprintTick < ((oHorse.maxSprint + oHorse.stockSprintAddition) * 0.05)) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    }
                }

                if (oHorse.isDraftBreed()) {
                    if (oHorse.sprintTick >= ((oHorse.maxSprint + oHorse.draftSprintAddition) * 0.90)) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_full.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if ((oHorse.sprintTick < ((oHorse.maxSprint + oHorse.draftSprintAddition) * 0.90)) && (oHorse.sprintTick > ((oHorse.maxSprint + oHorse.draftSprintAddition) * 0.65))) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_quarter_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if ((oHorse.sprintTick < ((oHorse.maxSprint + oHorse.draftSprintAddition) * 0.65)) && (oHorse.sprintTick > ((oHorse.maxSprint + oHorse.draftSprintAddition) * 0.35))) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_half_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if ((oHorse.sprintTick < ((oHorse.maxSprint + oHorse.draftSprintAddition) * 0.35)) && (oHorse.sprintTick > ((oHorse.maxSprint + oHorse.draftSprintAddition) * 0.05))) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_3_quarters_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if (oHorse.sprintTick < ((oHorse.maxSprint + oHorse.draftSprintAddition) * 0.05)) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    }
                }

                if (oHorse.isPonyBreed()) {
                    if (oHorse.sprintTick >= ((oHorse.maxSprint + oHorse.ponySprintAddition) * 0.90)) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_full.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if ((oHorse.sprintTick < ((oHorse.maxSprint + oHorse.ponySprintAddition) * 0.90)) && (oHorse.sprintTick > ((oHorse.maxSprint + oHorse.ponySprintAddition) * 0.65))) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_quarter_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if ((oHorse.sprintTick < ((oHorse.maxSprint + oHorse.ponySprintAddition) * 0.65)) && (oHorse.sprintTick > ((oHorse.maxSprint + oHorse.ponySprintAddition) * 0.35))) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_half_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if ((oHorse.sprintTick < ((oHorse.maxSprint + oHorse.ponySprintAddition) * 0.35)) && (oHorse.sprintTick > ((oHorse.maxSprint + oHorse.ponySprintAddition) * 0.05))) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_3_quarters_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if (oHorse.sprintTick < ((oHorse.maxSprint + oHorse.ponySprintAddition) * 0.05)) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    }
                }

                if (oHorse.isRacingBreed()) {
                    if (oHorse.sprintTick >= (oHorse.maxSprint * 0.90)) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_full.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if ((oHorse.sprintTick < (oHorse.maxSprint * 0.90) && (oHorse.sprintTick > (oHorse.maxSprint * 0.65)))) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_quarter_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if ((oHorse.sprintTick < (oHorse.maxSprint * 0.65) && (oHorse.sprintTick > (oHorse.maxSprint * 0.35)))) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_half_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if ((oHorse.sprintTick < (oHorse.maxSprint * 0.35) && (oHorse.sprintTick > (oHorse.maxSprint * 0.05)))) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_3_quarters_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if (oHorse.sprintTick < (oHorse.maxSprint * 0.05)) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    }
                }

                gui.setupOverlayRenderState(true, false);


            }
        });

    }
}