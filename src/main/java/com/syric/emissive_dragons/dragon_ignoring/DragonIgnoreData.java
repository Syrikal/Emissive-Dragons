package com.syric.emissive_dragons.dragon_ignoring;

import net.minecraft.nbt.CompoundTag;

public class DragonIgnoreData implements IDragonEmissiveIgnoreCapability {
    private boolean doEmissiveRendering;

    public DragonIgnoreData() {
        doEmissiveRendering = true;
    }

    public boolean doEmissiveRendering() {
        return doEmissiveRendering;
    }

    public void setEmissiveRendering(boolean input) {
        doEmissiveRendering = input;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putBoolean("emissive_dragon_rendering", doEmissiveRendering);
    }

    public void loadNBTData(CompoundTag nbt) {
        doEmissiveRendering = nbt.getBoolean("emissive_dragon_rendering");
    }

}
