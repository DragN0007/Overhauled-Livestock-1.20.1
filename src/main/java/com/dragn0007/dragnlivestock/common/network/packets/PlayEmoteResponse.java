package com.dragn0007.dragnlivestock.common.network.packets;

import com.dragn0007.dragnlivestock.common.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.common.network.LOPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent.Context;

public record PlayEmoteResponse(int id, String emoteName, String loopType) implements LOPacket {

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(id);
        buffer.writeUtf(emoteName);
        buffer.writeUtf(loopType);
    }

    public static PlayEmoteResponse decode(FriendlyByteBuf buffer) {
        int id = buffer.readInt();
        String emoteName = buffer.readUtf();
        String loopType = buffer.readUtf();
        return new PlayEmoteResponse(id, emoteName, loopType);
    }

    @Override
    public void handle(Context ctx) {
        ctx.enqueueWork(() -> {
            ClientLevel level = Minecraft.getInstance().level;
            if(level != null) {
                Entity entity = level.getEntity(id);
                if(entity instanceof AbstractOMount oHorse) {
                    oHorse.playEmote(emoteName, loopType);
                }
            }
        });
        ctx.setPacketHandled(true);
    }

}