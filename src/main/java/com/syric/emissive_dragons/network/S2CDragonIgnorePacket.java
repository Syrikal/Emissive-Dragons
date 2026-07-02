package com.syric.emissive_dragons.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class S2CDragonIgnorePacket {
    final int dragonID;
    final boolean emissiveRendering;

    public S2CDragonIgnorePacket(int id, boolean emissiveRendering) {
        this.dragonID = id;
        this.emissiveRendering = emissiveRendering;
    }

    public S2CDragonIgnorePacket(FriendlyByteBuf buf) {
        this(buf.readInt(), buf.readBoolean());
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.dragonID);
        buf.writeBoolean(this.emissiveRendering);
    }


    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
//        EmissiveDragons.LOGGER.debug("Running S2CDragonIgnorePacket.handle()");
        contextSupplier.get().enqueueWork(() ->
                // Make sure it's only executed on the physical client
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handleS2CDragonIgnorePacket(this))
        );
        contextSupplier.get().setPacketHandled(true);

    }

}
