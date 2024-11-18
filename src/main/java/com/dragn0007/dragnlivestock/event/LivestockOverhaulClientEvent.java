package com.dragn0007.dragnlivestock.event;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = LivestockOverhaul.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class LivestockOverhaulClientEvent {

    public static final KeyMapping CLEAR = new KeyMapping("key.dragnlivestock.clear", InputConstants.KEY_MINUS, "key.dragnlivestock.categories.dragnlivestock");
    public static final KeyMapping HORSE_SPEED_UP = new KeyMapping("key.dragnlivestock.horse_speed_up", InputConstants.KEY_LCONTROL, "key.dragnlivestock.categories.dragnlivestock");
    public static final KeyMapping HORSE_SLOW_DOWN = new KeyMapping("key.dragnlivestock.horse_slow_down", InputConstants.KEY_LALT, "key.dragnlivestock.categories.dragnlivestock");
    public static final KeyMapping HORSE_BOW = new KeyMapping("key.dragnlivestock.horse_bow", InputConstants.KEY_B, "key.dragnlivestock.categories.dragnlivestock");
    public static final KeyMapping HORSE_PIAFFE = new KeyMapping("key.dragnlivestock.horse_piaffe", InputConstants.KEY_P, "key.dragnlivestock.categories.dragnlivestock");
    public static final KeyMapping HORSE_SPANISH_WALK_TOGGLE = new KeyMapping("key.dragnlivestock.horse_spanish_walk_toggle", InputConstants.KEY_DOWN, "key.dragnlivestock.categories.dragnlivestock");
    public static final KeyMapping HORSE_WAVE = new KeyMapping("key.dragnlivestock.horse_wave", InputConstants.KEY_G, "key.dragnlivestock.categories.dragnlivestock");
    public static final KeyMapping HORSE_LEVADE = new KeyMapping("key.dragnlivestock.horse_levade", InputConstants.KEY_L, "key.dragnlivestock.categories.dragnlivestock");
//    public static final KeyMapping HORSE_REINING_TOGGLE = new KeyMapping("key.dragnlivestock.horse_reining_toggle", InputConstants.KEY_UP, "key.dragnlivestock.categories.dragnlivestock");

    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        KeyMapping[] keyMappings = {CLEAR, HORSE_SPEED_UP, HORSE_SLOW_DOWN, HORSE_BOW, HORSE_PIAFFE, HORSE_SPANISH_WALK_TOGGLE, HORSE_WAVE, HORSE_LEVADE};
        for (KeyMapping keyMapping : keyMappings) {
            event.register(keyMapping);
        }
    }
}