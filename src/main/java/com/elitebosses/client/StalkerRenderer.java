package com.elitebosses.client;

import com.elitebosses.EliteBosses;
import com.elitebosses.entity.StalkerEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class StalkerRenderer extends MobRenderer<StalkerEntity, StalkerModel> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(EliteBosses.MODID, "textures/entity/stalker.png");
    private static final ResourceLocation EYES =
            new ResourceLocation(EliteBosses.MODID, "textures/entity/stalker_eyes.png");

    public StalkerRenderer(EntityRendererProvider.Context context) {
        super(context, new StalkerModel(context.bakeLayer(StalkerModel.LAYER)), 0.0F);
        this.addLayer(new EyesGlow(this));
    }

    @Override
    public ResourceLocation getTextureLocation(StalkerEntity entity) {
        return TEXTURE;
    }

    /** Etapa 1: tamano de Allay (0.2 del modelo); etapa 2: tamano completo (3 bloques). */
    @Override
    protected void scale(StalkerEntity entity, PoseStack pose, float partialTick) {
        float s = entity.getStage() == 2 ? 1.0F : 0.2F;
        pose.scale(s, s, s);
    }

    private static final class EyesGlow extends EyesLayer<StalkerEntity, StalkerModel> {
        EyesGlow(RenderLayerParent<StalkerEntity, StalkerModel> parent) {
            super(parent);
        }

        @Override
        public RenderType renderType() {
            return RenderType.eyes(EYES);
        }
    }
}
