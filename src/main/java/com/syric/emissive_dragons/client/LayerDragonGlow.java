package com.syric.emissive_dragons.client;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syric.emissive_dragons.EDClientConfig;
import com.syric.emissive_dragons.EmissiveDragons;
import com.syric.emissive_dragons.dragon_ignoring.DragonIgnoreDataProvider;
import com.syric.emissive_dragons.registry.EDRenderTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.concurrent.atomic.AtomicBoolean;

@OnlyIn(Dist.CLIENT)
public class LayerDragonGlow extends RenderLayer<EntityDragonBase, AdvancedEntityModel<EntityDragonBase>> {

    public LayerDragonGlow(MobRenderer<EntityDragonBase, AdvancedEntityModel<EntityDragonBase>> renderIn) {
        super(renderIn);
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityDragonBase dragon, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
//        EmissiveDragons.LOGGER.debug("Rendering a {} dragon", dragon.dragonType.getName());

//        EmissiveDragons.LOGGER.debug("{} dragon {} capability on the {} side",
//                dragon.dragonType.getName(),
//                dragon.getCapability(DragonIgnoreDataProvider.DRAGON_IGNORE_DATA).isPresent() ? "has" : "does not have",
//                ((Entity) dragon).level().isClientSide() ? "client" : "server");

        AtomicBoolean ignore = new AtomicBoolean(false);
        dragon.getCapability(DragonIgnoreDataProvider.DRAGON_IGNORE_DATA).ifPresent(data -> {
            if (!data.doEmissiveRendering()) ignore.set(true);
//            EmissiveDragons.LOGGER.debug("Dragon capability has doEmissiveRendering set to {}", data.doEmissiveRendering());
        });

        ResourceLocation glowTexture = getGlowTexture(dragon);
        if (Minecraft.getInstance().getResourceManager().getResource(glowTexture).isEmpty()
                || !EDClientConfig.DRAGON_GLOW_GLOBAL_TOGGLE.get()
                || ignore.get()
                || (dragon.isModelDead() && !dragon.isSkeletal() && !EDClientConfig.DEAD_DRAGONS_GLOW.get())
                || (dragon.isSkeletal() && !EDClientConfig.SKELETAL_DRAGONS_GLOW.get())
        ) return;

        RenderType glow = EDRenderTypes.dragonGlow(glowTexture);
        VertexConsumer ivertexbuilder = bufferIn.getBuffer(glow);
//        float glowIntensity = calculateGlowIntensity(dragon, partialTicks);
        float glowIntensity = 1;

        this.getParentModel().renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, glowIntensity);
    }

    private ResourceLocation getGlowTexture(EntityDragonBase dragon) {

        ResourceLocation male_sleep = ResourceLocation.fromNamespaceAndPath(
                EmissiveDragons.MODID,
                String.format("textures/models/%sdragon/%s%d_male_glow_sleep.png",
                        dragon.dragonType.getName(),
                        dragon.getVariantName(dragon.getVariant()),
                        dragon.getDragonStage())
        );
        ResourceLocation female_sleep = ResourceLocation.fromNamespaceAndPath(
                EmissiveDragons.MODID,
                String.format("textures/models/%sdragon/%s%d_female_glow_sleep.png",
                        dragon.dragonType.getName(),
                        dragon.getVariantName(dragon.getVariant()),
                        dragon.getDragonStage())
        );
        ResourceLocation male_awake = ResourceLocation.fromNamespaceAndPath(
                EmissiveDragons.MODID,
                String.format("textures/models/%sdragon/%s%d_male_glow.png",
                        dragon.dragonType.getName(),
                        dragon.getVariantName(dragon.getVariant()),
                        dragon.getDragonStage())
        );
        ResourceLocation female_awake = ResourceLocation.fromNamespaceAndPath(
                EmissiveDragons.MODID,
                String.format("textures/models/%sdragon/%s%d_female_glow.png",
                        dragon.dragonType.getName(),
                        dragon.getVariantName(dragon.getVariant()),
                        dragon.getDragonStage())
        );

        boolean male_sleep_present = Minecraft.getInstance().getResourceManager().getResource(male_sleep).isPresent();
        boolean female_sleep_present = Minecraft.getInstance().getResourceManager().getResource(female_sleep).isPresent();
        boolean female_awake_present = Minecraft.getInstance().getResourceManager().getResource(female_awake).isPresent();

        if (dragon.isSleeping() || dragon.isModelDead() && EDClientConfig.DIFFERENT_SLEEP_GLOW.get()) {
            if (!dragon.isMale() && EDClientConfig.DIMORPHIC_GLOW.get()) {
                if (female_sleep_present) {
                    return female_sleep;
                }
                if (female_awake_present) {
                    return female_awake;
                }
            }
            if (male_sleep_present) {
                return male_sleep;
            }
        } else {
            if (!dragon.isMale() && EDClientConfig.DIMORPHIC_GLOW.get()) {
                if (female_awake_present) {
                    return female_awake;
                }
            }
        }
        return male_awake;
    }

    //Removed method for making the glow 'breathe'
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
