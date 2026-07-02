package com.syric.emissive_dragons.dragon_ignoring;

import com.iafenvoy.iceandfire.entity.EntityDragonBase;
import com.syric.emissive_dragons.EmissiveDragons;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber(modid = EmissiveDragons.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DragonIgnoreDataProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<DragonIgnoreData> DRAGON_IGNORE_DATA = CapabilityManager.get(new CapabilityToken<>() {});
    public static final ResourceLocation DRAGON_IGNORE_CAPABILITY_RESOURCE_LOCATION = ResourceLocation.fromNamespaceAndPath(EmissiveDragons.MODID, "dragon_ignore_data_capability");


    private DragonIgnoreData data = null;
    private final LazyOptional<DragonIgnoreData> optional = LazyOptional.of(this::createDragonIgnoreData);

    private DragonIgnoreData createDragonIgnoreData() {
        if (this.data == null) {
            this.data = new DragonIgnoreData();
        }
        return this.data;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == DRAGON_IGNORE_DATA) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createDragonIgnoreData().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createDragonIgnoreData().loadNBTData(nbt);
    }

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityDragonBase && !event.getObject().getCapability(DRAGON_IGNORE_DATA).isPresent()) {
            event.addCapability(DRAGON_IGNORE_CAPABILITY_RESOURCE_LOCATION, new DragonIgnoreDataProvider());
        }
    }
}
