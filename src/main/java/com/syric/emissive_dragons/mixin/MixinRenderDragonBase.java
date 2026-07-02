package com.syric.emissive_dragons.mixin;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.iceandfire.client.render.entity.RenderDragonBase;
import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.syric.emissive_dragons.client.LayerDragonGlow;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RenderDragonBase.class)
public class MixinRenderDragonBase {

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/github/alexthe666/iceandfire/client/render/entity/RenderDragonBase;addLayer(Lnet/minecraft/client/renderer/entity/layers/RenderLayer;)Z", ordinal = 0))
    public boolean wrapLayerAddition(RenderDragonBase dragonRenderer, RenderLayer<EntityDragonBase, AdvancedEntityModel<EntityDragonBase>> eyesLayer, Operation<Boolean> original) {
        original.call(dragonRenderer, new LayerDragonGlow((RenderDragonBase) (Object) this));
        original.call(dragonRenderer, eyesLayer);
        return false;
    }

}
