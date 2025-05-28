package com.dragn0007.dragnlivestock.gui;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.donkey.ODonkey;
import com.dragn0007.dragnlivestock.entities.mule.OMule;
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
public class DonkeySprintHUD {

    @SubscribeEvent
    public static void onFMLClientSetupEvent(RegisterGuiOverlaysEvent event) {

        event.registerAbove(VanillaGuiOverlay.HOTBAR.id(), "donkey_sprint_hud", (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
            Minecraft minecraft = Minecraft.getInstance();
            Player player = (Player) minecraft.getCameraEntity();

            if(!minecraft.options.hideGui && player instanceof LocalPlayer && player.getVehicle() instanceof ODonkey donkey) {
                int x = (screenWidth / 2) + 92;
                int y = screenHeight - 52;

                if (donkey.sprintTick >= (donkey.maxSprint * 0.90)) {
                    ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_full.png");
                    guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                } else if ((donkey.sprintTick < (donkey.maxSprint * 0.90) && (donkey.sprintTick > (donkey.maxSprint * 0.65)))) {
                    ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_quarter_empty.png");
                    guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                } else if ((donkey.sprintTick < (donkey.maxSprint * 0.65) && (donkey.sprintTick > (donkey.maxSprint * 0.35)))) {
                    ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_half_empty.png");
                    guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                } else if ((donkey.sprintTick < (donkey.maxSprint * 0.35) && (donkey.sprintTick > (donkey.maxSprint * 0.05)))) {
                    ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_3_quarters_empty.png");
                    guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                } else if (donkey.sprintTick < (donkey.maxSprint * 0.05)) {
                    ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_empty.png");
                    guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                }

                gui.setupOverlayRenderState(true, false);


            }
        });

    }
}