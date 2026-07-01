package com.syric.emissive_dragons.client;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.data.DragonColor;
import com.iafenvoy.iceandfire.entity.EntityDragonBase;
import com.iafenvoy.iceandfire.entity.EntityIceDragon;
import com.iafenvoy.iceandfire.entity.EntityLightningDragon;
import com.iafenvoy.uranus.client.model.AdvancedModelBox;
import com.iafenvoy.uranus.client.model.TabulaModel;
import com.iafenvoy.uranus.client.model.util.TabulaModelHandlerHelper;
import com.iafenvoy.uranus.util.function.MemorizeSupplier;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syric.emissive_dragons.EmissiveDragons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class LayerDragonEyesOnly extends RenderLayer<EntityDragonBase, TabulaModel<EntityDragonBase>> {
    private TabulaModel<EntityDragonBase> fireHead;
    private TabulaModel<EntityDragonBase> iceHead;
    private TabulaModel<EntityDragonBase> lightningHead;

    public LayerDragonEyesOnly(MobRenderer<EntityDragonBase, TabulaModel<EntityDragonBase>> renderIn) {
        super(renderIn);

        try {
            this.fireHead = this.onlyKeepCubes(TabulaModelHandlerHelper.getModel(ResourceLocation.tryBuild("iceandfire", "firedragon/firedragon_ground"), (MemorizeSupplier)null), Collections.singletonList("HeadFront"));
            this.iceHead = this.onlyKeepCubes(TabulaModelHandlerHelper.getModel(ResourceLocation.tryBuild("iceandfire", "icedragon/icedragon_ground"), (MemorizeSupplier)null), Collections.singletonList("HeadFront"));
            this.lightningHead = this.onlyKeepCubes(TabulaModelHandlerHelper.getModel(ResourceLocation.tryBuild("iceandfire", "lightningdragon/lightningdragon_ground"), (MemorizeSupplier)null), Collections.singletonList("HeadFront"));
        } catch (Exception e) {
            IceAndFire.LOGGER.error(e);
        }

    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityDragonBase dragon, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (dragon.shouldRenderEyes()) {
            ResourceLocation eyesTexture = getEyesTexture(dragon);
            if (eyesTexture == null || Minecraft.getInstance().getResourceManager().getResource(eyesTexture).isEmpty()) return;
            RenderType eyes = RenderType.eyes(eyesTexture);
            VertexConsumer ivertexbuilder = bufferIn.getBuffer(eyes);
            if (dragon instanceof EntityLightningDragon && this.lightningHead != null) {
                this.copyPositions(this.lightningHead, (TabulaModel)this.getParentModel());
                this.lightningHead.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            } else if (dragon instanceof EntityIceDragon && this.iceHead != null) {
                this.copyPositions(this.iceHead, (TabulaModel)this.getParentModel());
                this.iceHead.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            } else if (this.fireHead != null) {
                this.copyPositions(this.fireHead, (TabulaModel)this.getParentModel());
                this.fireHead.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            } else {
                ((TabulaModel)this.getParentModel()).renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            }
        }

    }

    protected ResourceLocation getTexture(EntityDragonBase entityIn) {
        return null;
    }

    private TabulaModel<EntityDragonBase> onlyKeepCubes(TabulaModel<EntityDragonBase> model, List<String> strings) {
        List<AdvancedModelBox> keepCubes = new ArrayList();

        for(String str : strings) {
            AdvancedModelBox cube = model.getCube(str);
            keepCubes.add(cube);

            while(cube.getParent() != null) {
                keepCubes.add(cube.getParent());
                cube = cube.getParent();
            }
        }

        this.removeChildren(model, keepCubes);
        model.getCubes().values().removeIf((advancedModelBox) -> !keepCubes.contains(advancedModelBox));
        return model;
    }

    private void removeChildren(TabulaModel<EntityDragonBase> model, List<AdvancedModelBox> keepCubes) {
        model.getRootBox().forEach((modelRenderer) -> {
            modelRenderer.childModels.removeIf((child) -> !keepCubes.contains(child));
            modelRenderer.childModels.forEach((childModel) -> this.removeChildren((AdvancedModelBox)childModel, keepCubes));
        });
    }

    private void removeChildren(AdvancedModelBox modelBox, List<AdvancedModelBox> keepCubes) {
        modelBox.childModels.removeIf((modelRenderer) -> !keepCubes.contains(modelRenderer));
        modelBox.childModels.forEach((modelRenderer) -> this.removeChildren((AdvancedModelBox)modelRenderer, keepCubes));
    }

    public boolean isAngleEqual(AdvancedModelBox original, AdvancedModelBox pose) {
        return pose != null && pose.rotateAngleX == original.rotateAngleX && pose.rotateAngleY == original.rotateAngleY && pose.rotateAngleZ == original.rotateAngleZ;
    }

    public boolean isPositionEqual(AdvancedModelBox original, AdvancedModelBox pose) {
        return pose.rotationPointX == original.rotationPointX && pose.rotationPointY == original.rotationPointY && pose.rotationPointZ == original.rotationPointZ;
    }

    public void copyPositions(TabulaModel<EntityDragonBase> model, TabulaModel<EntityDragonBase> modelTo) {
        for(AdvancedModelBox cube : model.getCubes().values()) {
            AdvancedModelBox modelToCube = modelTo.getCube(cube.boxName);
            if (!this.isAngleEqual(cube, modelToCube)) {
                cube.rotateAngleX = modelToCube.rotateAngleX;
                cube.rotateAngleY = modelToCube.rotateAngleY;
                cube.rotateAngleZ = modelToCube.rotateAngleZ;
            }

            if (!this.isPositionEqual(cube, modelToCube)) {
                cube.rotationPointX = modelToCube.rotationPointX;
                cube.rotationPointY = modelToCube.rotationPointY;
                cube.rotationPointZ = modelToCube.rotationPointZ;
            }
        }
    }

    @Nullable
    private ResourceLocation getEyesTexture(EntityDragonBase dragon) {
        ResourceLocation emissive_dragons_version = ResourceLocation.fromNamespaceAndPath(EmissiveDragons.MODID, String.format("textures/models/%sdragon/%s_%d_eyes.png", dragon.dragonType.getName(), DragonColor.getById(dragon.getVariant()).name(), dragon.getDragonStage()));
        if (Minecraft.getInstance().getResourceManager().getResource(emissive_dragons_version).isEmpty()) {
            return DragonColor.getById(dragon.getVariant()).getEyesTexture(dragon.getDragonStage());
        } else {
            return emissive_dragons_version;
        }
    }
}
