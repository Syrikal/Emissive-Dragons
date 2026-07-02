package com.syric.emissive_dragons.dragon_ignoring;

import com.iafenvoy.iceandfire.entity.EntityDragonBase;
import com.syric.emissive_dragons.EmissiveDragons;
import com.syric.emissive_dragons.network.EDPacketHandler;
import com.syric.emissive_dragons.network.S2CDragonIgnorePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EmissiveDragons.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DataManagementEvents {

    //Checks if a dragon has tags and sends that info to their capability
    @SubscribeEvent
    public static void checkData(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof EntityDragonBase dragon
                && !event.getEntity().level().isClientSide()
                && !event.getEntity().getTags().isEmpty()
                && (event.getEntity().getTags().contains("emissive_dragons_ignore") || event.getEntity().getTags().contains("emissive_dragons_dont_ignore"))) {
            dragon.getCapability(DragonIgnoreDataProvider.DRAGON_IGNORE_DATA).ifPresent(data -> {
//            EmissiveDragons.LOGGER.debug("Emissive Dragons detected capability");
                if (dragon.getTags().contains("emissive_dragons_ignore")) {
//                EmissiveDragons.LOGGER.debug("Setting {} dragon to not render glow", dragon.dragonType.getName());
                    dragon.removeTag("emissive_dragons_ignore");
                    data.setEmissiveRendering(false);
                    EDPacketHandler.sendWithEntity(new S2CDragonIgnorePacket(dragon.getId(), false), dragon);
                }
                if (dragon.getTags().contains("emissive_dragons_dont_ignore")) {
//                EmissiveDragons.LOGGER.debug("Setting {} dragon to render glow", dragon.dragonType.getName());
                    dragon.removeTag("emissive_dragons_dont_ignore");
                    data.setEmissiveRendering(true);
                    EDPacketHandler.sendWithEntity(new S2CDragonIgnorePacket(dragon.getId(), true), dragon);
                }
            });
        }
    }

    //Sends capability data to the client whenever a player loads a dragon
    @SubscribeEvent
    public static void clientSync(PlayerEvent.StartTracking event) {
        if (event.getTarget() instanceof EntityDragonBase dragon && event.getEntity() instanceof ServerPlayer serverPlayer) {
//            EmissiveDragons.LOGGER.debug("Attempting to synchronize client");
            dragon.getCapability(DragonIgnoreDataProvider.DRAGON_IGNORE_DATA).ifPresent(data -> EDPacketHandler.sendToPlayer(new S2CDragonIgnorePacket(dragon.getId(), data.doEmissiveRendering()), serverPlayer));
        }
    }

//    @SubscribeEvent
//    public static void report(LivingEvent.LivingTickEvent event) {
//        if (event.getEntity() instanceof EntityDragonBase dragon && dragon.tickCount % 10 == 0) {
//            boolean clientSide = ((Entity) dragon).level().isClientSide();
//            AtomicBoolean renderEmissive = new AtomicBoolean();
//            dragon.getCapability(DragonIgnoreDataProvider.DRAGON_IGNORE_DATA).ifPresent(data -> {renderEmissive.set(data.doEmissiveRendering());});
//            EmissiveDragons.LOGGER.debug("Dragon ticked on the {} side. Its renderEmissive is {}", clientSide ? "client" : "server", renderEmissive.get());
//        }
//    }

}
