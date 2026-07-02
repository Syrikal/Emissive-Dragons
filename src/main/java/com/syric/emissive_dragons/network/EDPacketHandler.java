package com.syric.emissive_dragons.network;

import com.syric.emissive_dragons.EmissiveDragons;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class EDPacketHandler {

    private static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(
                    ResourceLocation.fromNamespaceAndPath(EmissiveDragons.MODID, "main"))
            .serverAcceptedVersions((version) -> true)
            .clientAcceptedVersions(version -> true)
            .networkProtocolVersion(() -> "1")
            .simpleChannel();

    public static void register() {

        INSTANCE.messageBuilder(S2CDragonIgnorePacket.class, NetworkDirection.PLAY_TO_CLIENT.ordinal())
                .encoder(S2CDragonIgnorePacket::encode)
                .decoder(S2CDragonIgnorePacket::new)
                .consumerMainThread(S2CDragonIgnorePacket::handle)
                .add();
    }

    public static void sendWithEntity(Object packet, Entity entity) { INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), packet); }
    public static void sendToPlayer(Object packet, ServerPlayer player) { INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), packet); }

}
