package com.syric.emissive_dragons.network;

import com.syric.emissive_dragons.dragon_ignoring.DragonIgnoreDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class ClientPacketHandler {
    public static void handleS2CDragonIgnorePacket(S2CDragonIgnorePacket packet) {
        Level level = Minecraft.getInstance().level;
//        EmissiveDragons.LOGGER.debug("Handling S2CDragonIgnorePacket with doEmissiveRendering = {}", packet.emissiveRendering);
        if (level != null) {
            Entity dragon = level.getEntity(packet.dragonID);
            if (dragon != null) {
                dragon.getCapability(DragonIgnoreDataProvider.DRAGON_IGNORE_DATA).ifPresent(data -> {
                    data.setEmissiveRendering(packet.emissiveRendering);
//                    EmissiveDragons.LOGGER.debug("Found dragon with capability, altering data: set emissiveRendering to {}", packet.emissiveRendering);
                });
            }
        }
    }
}
