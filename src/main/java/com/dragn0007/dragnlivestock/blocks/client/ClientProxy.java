package com.dragn0007.dragnlivestock.blocks.client;

import com.dragn0007.dragnlivestock.blocks.client.sounds.WagonSoundInstance;
import com.dragn0007.dragnlivestock.common.entities.wagon.base.AbstractGeckolibVehicle;
import com.dragn0007.dragnlivestock.common.entities.wagon.base.AbstractWagon;
import com.dragn0007.dragnlivestock.common.network.LONetwork;
import com.dragn0007.dragnlivestock.common.network.packets.VehicleControlPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class ClientProxy {

    public static void controlVehicleLocal(AbstractGeckolibVehicle vehicle) {
        LocalPlayer player = Minecraft.getInstance().player;
        if(player == null)
            return;

        float forward = 0;
        float left = 0;

        if(player.input.left)
            left++;
        if(player.input.right)
            left--;

        if(player.input.up)
            forward++;
        if(player.input.down)
            forward--;

        LONetwork.INSTANCE.sendToServer(new VehicleControlPacket(vehicle.getId(), forward, left));
    }

    public static void createWagonSound(AbstractWagon wagon) {
        Minecraft.getInstance().getSoundManager().play(new WagonSoundInstance(wagon));
    }

}
