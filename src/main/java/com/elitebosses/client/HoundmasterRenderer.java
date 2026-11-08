package com.elitebosses.client;

import com.elitebosses.EliteBosses;
import com.elitebosses.entity.HoundmasterEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;

public class HoundmasterRenderer extends MobRenderer<HoundmasterEntity, HoundmasterModel> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(EliteBosses.MODID, "textures/entity/houndmaster.png");
    private static final ResourceLocation EYES =
            new ResourceLocation(EliteBosses.MODID, "textures/entity/houndmaster_eyes.png");

    public HoundmasterRenderer(EntityRendererProvider.Context context) {
        super(context, new HoundmasterModel(context.bakeLayer(HoundmasterModel.LAYER)), 1.1F);
        this.addLayer(new EyesGlow(this));
    }

    @Override
    public ResourceLocation getTextureLocation(HoundmasterEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(HoundmasterEntity entity, PoseStack pose, float partialTick) {
        pose.scale(0.9F, 0.9F, 0.9F);
    }

    /** Ojos y boca que brillan en la oscuridad. */
    private static final class EyesGlow extends EyesLayer<HoundmasterEntity, HoundmasterModel> {
        EyesGlow(RenderLayerParent<HoundmasterEntity, HoundmasterModel> parent) {
            super(parent);
        }

        @Override
        public RenderType renderType() {
            return RenderType.eyes(EYES);
        }
    }
}
