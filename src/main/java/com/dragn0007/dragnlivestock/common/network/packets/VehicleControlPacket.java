package com.dragn0007.dragnlivestock.common.network.packets;

import com.dragn0007.dragnlivestock.common.network.LivestockOverhaulPacket;
import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractGeckolibVehicle;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;
import net.minecraftforge.network.NetworkEvent.Context;

public record VehicleControlPacket(int id, float forwardImpulse, float leftImpulse) implements LivestockOverhaulPacket {

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(id);
        buffer.writeFloat(forwardImpulse);
        buffer.writeFloat(leftImpulse);
    }

    public static VehicleControlPacket decode(FriendlyByteBuf buffer) {
        return new VehicleControlPacket(buffer.readInt(), buffer.readFloat(), buffer.readFloat());
    }

    @Override
    public void handle(Context ctx) {
        ctx.enqueueWork(() -> {
            if(ctx.getSender().level().getEntity(id) instanceof AbstractGeckolibVehicle vehicle) {
                if(ctx.getSender() != vehicle.getControllingPassenger())
                    return;
                vehicle.setImpulses(Mth.clamp(forwardImpulse, -1, 1), Mth.clamp(leftImpulse, -1, 1));
            }
        });
        ctx.setPacketHandled(true);
    }

}
