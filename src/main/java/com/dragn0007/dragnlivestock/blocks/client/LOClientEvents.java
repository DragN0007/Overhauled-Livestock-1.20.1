package com.dragn0007.dragnlivestock.blocks.client;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.network.LONetwork;
import com.dragn0007.dragnlivestock.common.network.packets.HandleHorseSpeedRequest;
import com.dragn0007.dragnlivestock.common.network.packets.PlayEmoteRequest;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = LivestockOverhaul.MODID, bus = Bus.FORGE, value = Dist.CLIENT)
public class LOClientEvents {

    @SubscribeEvent
    public static void onKeyPressEvent(InputEvent.Key event) {
        Player player = Minecraft.getInstance().player;

        if (player == null) return;

        if (event.getAction() == InputConstants.RELEASE && event.getKey() == LOKeyMappings.HORSE_SPEED_UP.getKey().getValue()) {
            LONetwork.INSTANCE.sendToServer(new HandleHorseSpeedRequest(1));
        }

        if (event.getAction() == InputConstants.RELEASE && event.getKey() == LOKeyMappings.HORSE_SLOW_DOWN.getKey().getValue()) {
            LONetwork.INSTANCE.sendToServer(new HandleHorseSpeedRequest(-1));
        }

        if (event.getAction() == InputConstants.RELEASE && event.getKey() == LOKeyMappings.HORSE_BOW.getKey().getValue()) {
            LONetwork.INSTANCE.sendToServer(new PlayEmoteRequest("bow", "play_once"));
        }

        if (event.getAction() == InputConstants.RELEASE && event.getKey() == LOKeyMappings.HORSE_PIAFFE.getKey().getValue()) {
            LONetwork.INSTANCE.sendToServer(new PlayEmoteRequest("piaffe", "loop"));
        }

        if (event.getAction() == InputConstants.RELEASE && event.getKey() == LOKeyMappings.HORSE_WAVE.getKey().getValue()) {
            LONetwork.INSTANCE.sendToServer(new PlayEmoteRequest("wave", "play_once"));
        }

        if (event.getAction() == InputConstants.RELEASE && event.getKey() == LOKeyMappings.HORSE_LEVADE.getKey().getValue()) {
            LONetwork.INSTANCE.sendToServer(new PlayEmoteRequest("levade", "play_once"));
        }
    }

}