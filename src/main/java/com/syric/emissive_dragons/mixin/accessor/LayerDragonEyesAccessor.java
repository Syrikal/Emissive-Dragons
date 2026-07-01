package com.syric.emissive_dragons.mixin.accessor;

import com.iafenvoy.iceandfire.entity.EntityDragonBase;
import com.iafenvoy.iceandfire.render.entity.layer.LayerDragonEyes;
import com.iafenvoy.uranus.client.model.TabulaModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LayerDragonEyes.class)
public interface LayerDragonEyesAccessor {

    @Accessor("fireHead")
    TabulaModel<EntityDragonBase> emissive_dragons$getFireHead();

    @Accessor("iceHead")
    TabulaModel<EntityDragonBase> emissive_dragons$getIceHead();

    @Accessor("lightningHead")
    TabulaModel<EntityDragonBase> emissive_dragons$getLightningHead();
}
