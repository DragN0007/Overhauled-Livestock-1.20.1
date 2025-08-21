package com.dragn0007.dragnlivestock.client;

import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractGeckolibVehicle;
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

        vehicle.setImpulses(forward, left);
    }

}
