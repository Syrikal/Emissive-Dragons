package com.syric.emissive_dragons.client;

import com.iafenvoy.iceandfire.data.DragonColor;
import com.iafenvoy.iceandfire.entity.EntityDragonBase;
import com.iafenvoy.iceandfire.render.entity.layer.LayerDragonEyes;
import com.iafenvoy.uranus.client.model.TabulaModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syric.emissive_dragons.EmissiveDragons;
import com.syric.emissive_dragons.registry.EDRenderTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class LayerDragonGlow extends LayerDragonEyes {
    public LayerDragonGlow(MobRenderer<EntityDragonBase, TabulaModel<EntityDragonBase>> renderIn) {
        super(renderIn);
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityDragonBase dragon, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ResourceLocation glowTexture = getGlowTexture(dragon);
        if (glowTexture == null || Minecraft.getInstance().getResourceManager().getResource(glowTexture).isEmpty()) return;
        RenderType eyes = EDRenderTypes.dragonGlow(glowTexture);
        VertexConsumer ivertexbuilder = bufferIn.getBuffer(eyes);

//        float glowIntensity = calculateGlowIntensity(dragon, partialTicks);
        float glowIntensity = 1;

        this.getParentModel().renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, glowIntensity);
    }

    @Nullable
    private ResourceLocation getGlowTexture(EntityDragonBase dragon) {
        if (dragon.isSleeping()) {
            return ResourceLocation.fromNamespaceAndPath(EmissiveDragons.MODID, String.format("textures/models/%sdragon/%s_%d_%s_glow_sleep.png", dragon.dragonType.getName(), DragonColor.getById(dragon.getVariant()).name(), dragon.getDragonStage(), dragon.isMale() ? "male" : "female"));
        } else {
            return ResourceLocation.fromNamespaceAndPath(EmissiveDragons.MODID, String.format("textures/models/%sdragon/%s_%d_%s_glow.png", dragon.dragonType.getName(), DragonColor.getById(dragon.getVariant()).name(), dragon.getDragonStage(), dragon.isMale() ? "male" : "female"));
        }
    }

//    private float calculateGlowIntensity(EntityDragonBase dragon, float partialTicks) {
//        if (!EDClientConfig.DRAGON_GLOW_BREATHING.get()) return 1;
//        double input = dragon.tickCount + partialTicks;
//        float output;
//        if (dragon.isSleeping()) {
//            output = (float) (EDClientConfig.AMPLITUDE_ASLEEP.get() * Math.sin(input / EDClientConfig.PERIOD_ASLEEP.get()) + EDClientConfig.MEAN_VALUE_ASLEEP.get());
//        } else {
//            output = (float) (EDClientConfig.AMPLITUDE_AWAKE.get() * Math.sin(input / EDClientConfig.PERIOD_AWAKE.get()) + EDClientConfig.MEAN_VALUE_AWAKE.get());
//        }
//        return Mth.clamp(output, 0, 1);
//    }

}
