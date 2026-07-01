package com.syric.emissive_dragons.mixin;

import com.iafenvoy.iceandfire.data.DragonColor;
import com.iafenvoy.iceandfire.entity.EntityDragonBase;
import com.iafenvoy.iceandfire.render.entity.layer.LayerDragonEyes;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.syric.emissive_dragons.EDClientConfig;
import com.syric.emissive_dragons.EmissiveDragons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LayerDragonEyes.class)
public class MixinLayerDragonEyes {

    @WrapOperation(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILcom/iafenvoy/iceandfire/entity/EntityDragonBase;FFFFFF)V",
    at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderType;eyes(Lnet/minecraft/resources/ResourceLocation;)Lnet/minecraft/client/renderer/RenderType;"))
    private RenderType replaceEyes(ResourceLocation originalEyesResource, Operation<RenderType> original, @Local(argsOnly = true) EntityDragonBase dragon) {
        ResourceLocation emissive_dragons_male_version = ResourceLocation.fromNamespaceAndPath(
                EmissiveDragons.MODID,
                String.format("textures/models/%sdragon/%s_%d_eyes.png",
                        dragon.dragonType.getName(),
                        DragonColor.getById(dragon.getVariant()).name(),
                        dragon.getDragonStage())
        );

        ResourceLocation emissive_dragons_female_version = ResourceLocation.fromNamespaceAndPath(
                EmissiveDragons.MODID,
                String.format("textures/models/%sdragon/%s_%d_eyes_female.png",
                        dragon.dragonType.getName(),
                        DragonColor.getById(dragon.getVariant()).name(),
                        dragon.getDragonStage())
        );

        ResourceLocation version_to_use = (dragon.isMale() || Minecraft.getInstance().getResourceManager().getResource(emissive_dragons_female_version).isEmpty() || !EDClientConfig.DIMORPHIC_EYE_GLOW.get()) ? emissive_dragons_male_version : emissive_dragons_female_version;

        if (Minecraft.getInstance().getResourceManager().getResource(version_to_use).isEmpty()
                || !EDClientConfig.DRAGON_GLOW_GLOBAL_TOGGLE.get()
                //Insert condition here for "the dragon is ignored"
        ) {
            return original.call(originalEyesResource);
        } else {
            return original.call(version_to_use);
        }
    }
}
