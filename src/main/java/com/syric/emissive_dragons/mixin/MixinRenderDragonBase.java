package com.syric.emissive_dragons.mixin;

import com.iafenvoy.iceandfire.entity.EntityDragonBase;
import com.iafenvoy.iceandfire.render.entity.RenderDragonBase;
import com.iafenvoy.uranus.client.model.TabulaModel;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.syric.emissive_dragons.client.LayerDragonEyesOnly;
import com.syric.emissive_dragons.client.LayerDragonGlow;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RenderDragonBase.class)
public class MixinRenderDragonBase {

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/iafenvoy/iceandfire/render/entity/RenderDragonBase;addLayer(Lnet/minecraft/client/renderer/entity/layers/RenderLayer;)Z", ordinal = 1))
    public boolean wrapLayerAddition(RenderDragonBase dragonRenderer, RenderLayer<EntityDragonBase, TabulaModel<EntityDragonBase>> eyesLayer, Operation<Boolean> original) {

        original.call(dragonRenderer, new LayerDragonGlow((RenderDragonBase) (Object) this));
        original.call(dragonRenderer, new LayerDragonEyesOnly((RenderDragonBase) (Object) this));
        return false;
    }

}
