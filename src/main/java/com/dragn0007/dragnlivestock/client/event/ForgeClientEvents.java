package com.dragn0007.dragnlivestock.client.event;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.items.custom.LightHorseArmorItem;
import com.dragn0007.dragnlivestock.util.LONetwork;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = LivestockOverhaul.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeClientEvents {

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        List<Component> tooltip = event.getToolTip();

        if (event.getItemStack().getItem() instanceof HorseArmorItem item) {
            tooltip.add(Component.translatable("Protection: " + item.getProtection()).withStyle(ChatFormatting.GOLD));
        }

        if (event.getItemStack().getItem() instanceof LightHorseArmorItem item) {
            tooltip.add(Component.translatable("Protection: " + item.getProtection()).withStyle(ChatFormatting.GOLD));
        }
    }

    @SubscribeEvent
    public static void onKeyPressEvent(InputEvent.Key event) {
        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        if (event.getAction() == InputConstants.RELEASE && event.getKey() == LivestockOverhaulClientEvent.HORSE_SPEED_UP.getKey().getValue()) {
            LONetwork.INSTANCE.sendToServer(new LONetwork.HandleHorseSpeedRequest(1));
        }

        if (event.getAction() == InputConstants.RELEASE && event.getKey() == LivestockOverhaulClientEvent.HORSE_SLOW_DOWN.getKey().getValue()) {
            LONetwork.INSTANCE.sendToServer(new LONetwork.HandleHorseSpeedRequest(-1));
        }

        if (event.getAction() == InputConstants.RELEASE && event.getKey() == LivestockOverhaulClientEvent.HORSE_BOW.getKey().getValue()) {
            LONetwork.INSTANCE.sendToServer(new LONetwork.PlayEmoteRequest("bow", "play_once"));
        }

        if (event.getAction() == InputConstants.RELEASE && event.getKey() == LivestockOverhaulClientEvent.HORSE_PIAFFE.getKey().getValue()) {
            LONetwork.INSTANCE.sendToServer(new LONetwork.PlayEmoteRequest("piaffe", "loop"));
        }

        if (event.getAction() == InputConstants.RELEASE && event.getKey() == LivestockOverhaulClientEvent.HORSE_WAVE.getKey().getValue()) {
            LONetwork.INSTANCE.sendToServer(new LONetwork.PlayEmoteRequest("wave", "play_once"));
        }

        if (event.getAction() == InputConstants.RELEASE && event.getKey() == LivestockOverhaulClientEvent.HORSE_LEVADE.getKey().getValue()) {
            LONetwork.INSTANCE.sendToServer(new LONetwork.PlayEmoteRequest("levade", "play_once"));
        }
    }
}