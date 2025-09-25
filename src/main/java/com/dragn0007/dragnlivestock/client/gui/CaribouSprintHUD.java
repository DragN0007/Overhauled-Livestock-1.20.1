package com.dragn0007.dragnlivestock.client.gui;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.caribou.Caribou;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LivestockOverhaul.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CaribouSprintHUD {

    @SubscribeEvent
    public static void onFMLClientSetupEvent(RegisterGuiOverlaysEvent event) {

        event.registerAbove(VanillaGuiOverlay.HOTBAR.id(), "caribou_sprint_hud", (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
            Minecraft minecraft = Minecraft.getInstance();
            LivingEntity livingEntity = (LivingEntity) minecraft.getCameraEntity();

            if(!minecraft.options.hideGui && livingEntity.getVehicle() instanceof Caribou caribou) {
                if (caribou.getControllingPassenger() instanceof LocalPlayer && livingEntity instanceof LocalPlayer) {
                    int x = (screenWidth / 2) + LivestockOverhaulClientConfig.SPRINT_X.get();
                    int y = screenHeight - LivestockOverhaulClientConfig.SPRINT_Y.get();

                    if (caribou.sprintTick >= (caribou.maxSprint * 0.90)) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_full.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if ((caribou.sprintTick < (caribou.maxSprint * 0.90) && (caribou.sprintTick > (caribou.maxSprint * 0.65)))) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_quarter_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if ((caribou.sprintTick < (caribou.maxSprint * 0.65) && (caribou.sprintTick > (caribou.maxSprint * 0.35)))) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_half_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if ((caribou.sprintTick < (caribou.maxSprint * 0.35) && (caribou.sprintTick > (caribou.maxSprint * 0.05)))) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_3_quarters_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    } else if (caribou.sprintTick < (caribou.maxSprint * 0.05)) {
                        ResourceLocation location = new ResourceLocation(LivestockOverhaul.MODID, "textures/gui/horse_stamina_bar_empty.png");
                        guiGraphics.blit(location, x, y, 0, 0, 32, 50);
                    }

                    gui.setupOverlayRenderState(true, false);


                }
            }
        });

    }
}