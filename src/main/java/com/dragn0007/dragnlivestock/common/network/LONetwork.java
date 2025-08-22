package com.dragn0007.dragnlivestock.common.network;

import com.dragn0007.dragnlivestock.common.LivestockOverhaul;
import com.dragn0007.dragnlivestock.common.network.packets.*;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class LONetwork {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            LivestockOverhaul.id("main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        int id = -1;
        INSTANCE.registerMessage(++id, VehicleControlPacket.class, VehicleControlPacket::encode, VehicleControlPacket::decode, (p, ctx) -> p.handle(ctx.get()));
        INSTANCE.registerMessage(++id, HandleHorseSpeedRequest.class, HandleHorseSpeedRequest::encode, HandleHorseSpeedRequest::decode, (p, ctx) -> p.handle(ctx.get()));
        INSTANCE.registerMessage(++id, PlayEmoteRequest.class, PlayEmoteRequest::encode, PlayEmoteRequest::decode, (p, ctx) -> p.handle(ctx.get()));
        INSTANCE.registerMessage(++id, PlayEmoteResponse.class, PlayEmoteResponse::encode, PlayEmoteResponse::decode, (p, ctx) -> p.handle(ctx.get()));
    }

}
