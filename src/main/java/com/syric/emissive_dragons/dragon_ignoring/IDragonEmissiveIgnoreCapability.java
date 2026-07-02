package com.syric.emissive_dragons.dragon_ignoring;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface IDragonEmissiveIgnoreCapability {

    boolean doEmissiveRendering();

    void setEmissiveRendering(boolean in);
}
