package com.dragn0007.dragnlivestock.common.network.packets;

import com.dragn0007.dragnlivestock.common.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.common.network.LOPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent.Context;

public record HandleHorseSpeedRequest(int speedMod) implements LOPacket {

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(speedMod);
    }

    public static HandleHorseSpeedRequest decode(FriendlyByteBuf buffer) {
        return new HandleHorseSpeedRequest(buffer.readInt());
    }

    @Override
    public void handle(Context ctx) {
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if(player != null) {
                if(player.getVehicle() instanceof AbstractOMount oHorse) {
                    oHorse.handleSpeedRequest(speedMod);
                }
            }
        });
        ctx.setPacketHandled(true);
    }

}