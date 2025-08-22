package com.dragn0007.dragnlivestock;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LOSoundEvents {

    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, LivestockOverhaul.MODID);

    public static final RegistryObject<SoundEvent> WAGON = REGISTRY.register("wagon", () -> SoundEvent.createVariableRangeEvent(LivestockOverhaul.id("wagon")));

}