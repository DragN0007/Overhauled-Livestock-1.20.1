package com.dragn0007.dragnlivestock.util;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.entities.wagon.Mower;
import com.dragn0007.dragnlivestock.entities.wagon.Plow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = LivestockOverhaul.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LONetwork {

    public static class HandleHorseSpeedRequest {
        private final int speedMod;
        public HandleHorseSpeedRequest(int speedMod) {this.speedMod = speedMod;}
        public static void encode(HandleHorseSpeedRequest msg, FriendlyByteBuf buffer) {buffer.writeInt(msg.speedMod);}
        public static HandleHorseSpeedRequest decode(FriendlyByteBuf buffer) {return new HandleHorseSpeedRequest(buffer.readInt());}
        public static void handle(HandleHorseSpeedRequest msg, Supplier<NetworkEvent.Context> context) {
            NetworkEvent.Context ctx = context.get();
            ctx.enqueueWork(() -> {
                ServerPlayer player = ctx.getSender();
                if(player != null) {
                    if(player.getVehicle() instanceof AbstractOMount oHorse) {
                        oHorse.handleSpeedRequest(msg.speedMod);
                    }
                }
            });
            ctx.setPacketHandled(true);
        }
    }

    public static class PlayEmoteRequest {
        public final String emoteName;
        public final String loopType;

        public PlayEmoteRequest(String emoteName, String loopType) {
            this.emoteName = emoteName;
            this.loopType = loopType;
        }

        public static void encode(PlayEmoteRequest msg, FriendlyByteBuf buffer) {
            buffer.writeUtf(msg.emoteName);
            buffer.writeUtf(msg.loopType);
        }

        public static PlayEmoteRequest decode(FriendlyByteBuf buffer) {
            String emoteName = buffer.readUtf();
            String loopType = buffer.readUtf();
            return new PlayEmoteRequest(emoteName, loopType);
        }

        public static void handle(PlayEmoteRequest msg, Supplier<NetworkEvent.Context> context) {
            NetworkEvent.Context ctx = context.get();
            ctx.enqueueWork(() -> {
                ServerPlayer player = ctx.getSender();
                if(player != null) {
                    if(player.getVehicle() instanceof AbstractOMount oHorse) {
                        int id = oHorse.getId();
                        INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> oHorse), new PlayEmoteResponse(id, msg.emoteName, msg.loopType));
                    }
                }
            });
            ctx.setPacketHandled(true);
        }
    }

    public static class PlayEmoteResponse {
        public final int id;
        public final String emoteName;
        public final String loopType;

        public PlayEmoteResponse(int id, String emoteName, String loopType) {
            this.id = id;
            this.emoteName = emoteName;
            this.loopType = loopType;
        }

        public static void encode(PlayEmoteResponse msg, FriendlyByteBuf buffer) {
            buffer.writeInt(msg.id);
            buffer.writeUtf(msg.emoteName);
            buffer.writeUtf(msg.loopType);
        }

        public static PlayEmoteResponse decode(FriendlyByteBuf buffer) {
            int id = buffer.readInt();
            String emoteName = buffer.readUtf();
            String loopType = buffer.readUtf();
            return new PlayEmoteResponse(id, emoteName, loopType);
        }

        public static void handle(PlayEmoteResponse msg, Supplier<NetworkEvent.Context> context) {
            NetworkEvent.Context ctx = context.get();
            ctx.enqueueWork(() -> {
                ClientLevel level = Minecraft.getInstance().level;
                if(level != null) {
                    Entity entity = level.getEntity(msg.id);
                    if(entity instanceof AbstractOMount oHorse) {
                        oHorse.playEmote(msg.emoteName, msg.loopType);
                    }
                }
            });
            ctx.setPacketHandled(true);
        }
    }

    public static class ToggleTillerPowerRequest {
        private final int id;
        public ToggleTillerPowerRequest(int id) {this.id = id;}
        public static void encode(ToggleTillerPowerRequest msg, FriendlyByteBuf buffer) {buffer.writeInt(msg.id);}
        public static ToggleTillerPowerRequest decode(FriendlyByteBuf buffer) {return new ToggleTillerPowerRequest(buffer.readInt());}
        public static void handle(ToggleTillerPowerRequest msg, Supplier<NetworkEvent.Context> context) {
            ServerPlayer player = context.get().getSender();
            if(player != null) {
                if(player.level().getEntity(msg.id) instanceof Plow plow) {plow.cycleMode();}
                if(player.level().getEntity(msg.id) instanceof Mower mower) {mower.cycleMode();}
            }
        }
    }

    public static class ApplyTexture {
        public final int id;
        public final ResourceLocation coat;

        public ApplyTexture(int id, ResourceLocation coat) {
            this.id = id;
            this.coat = coat;
        }

        public static void encode(ApplyTexture msg, FriendlyByteBuf buf) {
            buf.writeInt(msg.id);
            buf.writeResourceLocation(msg.coat);
        }

        public static ApplyTexture decode(FriendlyByteBuf buf) {
            int id = buf.readInt();
            ResourceLocation coat = buf.readResourceLocation();

            return new ApplyTexture(id, coat);
        }

        public static void handle(ApplyTexture msg, Supplier<NetworkEvent.Context> contextSupplier) {
            NetworkEvent.Context ctx = contextSupplier.get();
            ctx.enqueueWork(() -> {
                ServerPlayer serverPlayer = ctx.getSender();
                if(serverPlayer != null) {
                    Entity entity = serverPlayer.level().getEntity(msg.id);
                    if(entity instanceof OHorse abstractSteed) {
                        abstractSteed.setVariantTexture(String.valueOf(msg.coat));
                    }
                }
            });
            ctx.setPacketHandled(true);
        }
    }

    public static class TextureRequest {
        public final int id;
        public final String folderName;
        public final boolean useServerCoats;

        public TextureRequest(int id, String folderName, boolean useServerCoats) {
            this.id = id;
            this.folderName = folderName;
            this.useServerCoats = useServerCoats;
        }

        public static void encode(TextureRequest msg, FriendlyByteBuf buf) {
            buf.writeInt(msg.id);
            buf.writeUtf(msg.folderName);
            buf.writeBoolean(msg.useServerCoats);
        }

        public static TextureRequest decode(FriendlyByteBuf buf) {
            int id = buf.readInt();
            String folderName = buf.readUtf();
            boolean useServerCoats = buf.readBoolean();

            return new TextureRequest(id, folderName, useServerCoats);
        }

        public static void handle(TextureRequest msg, Supplier<NetworkEvent.Context> contextSupplier) {
            NetworkEvent.Context ctx = contextSupplier.get();
            ctx.enqueueWork(() -> {
                LocalPlayer player = Minecraft.getInstance().player;
                if(player != null) {
                    ResourceLocation coat = msg.useServerCoats ? LOUtils.getRandomServerCoat(msg.folderName) : LOUtils.getRandomClientCoat(msg.folderName);
                    INSTANCE.sendToServer(new ApplyTexture(msg.id, coat));
                }
            });
            ctx.setPacketHandled(true);
        }
    }

    public static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(LivestockOverhaul.MODID, "lo_network"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    @SubscribeEvent
    public static void commonSetupEvent(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            INSTANCE.registerMessage(0, HandleHorseSpeedRequest.class, HandleHorseSpeedRequest::encode, HandleHorseSpeedRequest::decode, HandleHorseSpeedRequest::handle);
            INSTANCE.registerMessage(1, PlayEmoteRequest.class, PlayEmoteRequest::encode, PlayEmoteRequest::decode, PlayEmoteRequest::handle);
            INSTANCE.registerMessage(2, PlayEmoteResponse.class, PlayEmoteResponse::encode, PlayEmoteResponse::decode, PlayEmoteResponse::handle);
            INSTANCE.registerMessage(3, ToggleTillerPowerRequest.class, ToggleTillerPowerRequest::encode, ToggleTillerPowerRequest::decode, ToggleTillerPowerRequest::handle);
            INSTANCE.registerMessage(4, ApplyTexture.class, ApplyTexture::encode, ApplyTexture::decode, ApplyTexture::handle);
            INSTANCE.registerMessage(5, TextureRequest.class, TextureRequest::encode, TextureRequest::decode, TextureRequest::handle);
        });
    }
}
