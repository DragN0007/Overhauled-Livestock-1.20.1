package com.dragn0007.dragnlivestock.common.network.packets;

import com.dragn0007.dragnlivestock.common.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.common.network.LOPacket;
import com.dragn0007.dragnlivestock.common.network.LONetwork;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent.Context;
import net.minecraftforge.network.PacketDistributor;

public record PlayEmoteRequest(String emoteName, String loopType) implements LOPacket {

    public static PlayEmoteRequest decode(FriendlyByteBuf buffer) {
        return new PlayEmoteRequest(buffer.readUtf(), buffer.readUtf());
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeUtf(emoteName);
        buffer.writeUtf(loopType);
    }

    @Override
    public void handle(Context ctx) {
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if(player != null) {
                if(player.getVehicle() instanceof AbstractOMount oHorse) {
                    int id = oHorse.getId();
                    LONetwork.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> oHorse), new PlayEmoteResponse(id, emoteName, loopType));
                }
            }
        });
        ctx.setPacketHandled(true);
    }

}