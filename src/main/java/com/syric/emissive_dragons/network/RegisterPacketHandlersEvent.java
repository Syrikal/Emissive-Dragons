package com.syric.emissive_dragons.network;

import com.syric.emissive_dragons.EmissiveDragons;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = EmissiveDragons.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterPacketHandlersEvent {

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(EDPacketHandler::register);
    }

}
