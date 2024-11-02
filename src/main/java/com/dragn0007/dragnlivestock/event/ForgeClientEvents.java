package com.dragn0007.dragnlivestock.event;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.util.LONetwork;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib.core.animation.Animation;

@Mod.EventBusSubscriber(modid = LivestockOverhaul.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeClientEvents {
    @SubscribeEvent
    public static void onKeyPressEvent(InputEvent.Key event) {
        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        if (LivestockOverhaulClientEvent.HORSE_SPEED_UP.isDown()) {
            LONetwork.INSTANCE.sendToServer(new LONetwork.HandleHorseSpeedRequest(1));
        }

        if (LivestockOverhaulClientEvent.HORSE_SLOW_DOWN.isDown()) {
            LONetwork.INSTANCE.sendToServer(new LONetwork.HandleHorseSpeedRequest(-1));
        }

        if (LivestockOverhaulClientEvent.HORSE_BOW.isDown()) {
            LONetwork.INSTANCE.sendToServer(new LONetwork.PlayEmoteRequest("bow", Animation.LoopType.PLAY_ONCE));
        }

        if (LivestockOverhaulClientEvent.HORSE_PIAFFE.isDown()) {
            LONetwork.INSTANCE.sendToServer(new LONetwork.PlayEmoteRequest("piaffe", Animation.LoopType.LOOP));
        }
    }
}