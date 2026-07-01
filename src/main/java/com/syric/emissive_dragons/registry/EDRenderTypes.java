package com.syric.emissive_dragons.registry;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
public class EDRenderTypes extends RenderType {

    public static final Function<ResourceLocation, RenderType> DRAGON_GLOW = Util.memoize(
            (resourceLocation) -> {
                RenderStateShard.TextureStateShard renderstateshard$texturestateshard = new RenderStateShard.TextureStateShard(resourceLocation, false, false);
                return create(
                        "dragon_glow",
                        DefaultVertexFormat.NEW_ENTITY,
                        VertexFormat.Mode.QUADS,
                        256,
                        false,
                        true,
                        RenderType.CompositeState.builder()
                                .setShaderState(RenderStateShard.RENDERTYPE_EYES_SHADER)
                                .setCullState(RenderStateShard.NO_CULL)
                                .setTextureState(renderstateshard$texturestateshard)
                                .setTransparencyState(RenderStateShard.ADDITIVE_TRANSPARENCY)
                                .setWriteMaskState(COLOR_WRITE)
                                .createCompositeState(false)
                );
            }
    );

    public static RenderType dragonGlow(ResourceLocation resourceLocation) {
        return DRAGON_GLOW.apply(resourceLocation);
    }

    public EDRenderTypes(String p_173178_, VertexFormat p_173179_, VertexFormat.Mode p_173180_, int p_173181_, boolean p_173182_, boolean p_173183_, Runnable p_173184_, Runnable p_173185_) {
        super(p_173178_, p_173179_, p_173180_, p_173181_, p_173182_, p_173183_, p_173184_, p_173185_);
    }
}
