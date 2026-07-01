package com.syric.emissive_dragons;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EmissiveDragons.MODID)
public class EmissiveDragons {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "emissive_dragons";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public EmissiveDragons(FMLJavaModLoadingContext context) {
        context.registerConfig(ModConfig.Type.CLIENT, EDClientConfig.SPEC, "emissive-dragons.toml");
    }

}
