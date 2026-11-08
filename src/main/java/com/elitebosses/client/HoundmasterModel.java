package com.elitebosses.client;

import com.elitebosses.EliteBosses;
import com.elitebosses.entity.HoundmasterEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/** Modelo 3D del Sabueso (generado a partir del diseno; textura 128x128). */
public class HoundmasterModel extends EntityModel<HoundmasterEntity> {
    public static final ModelLayerLocation LAYER =
            new ModelLayerLocation(new ResourceLocation(EliteBosses.MODID, "houndmaster"), "main");

    static final float BODY_X = 0F, BODY_Y = 5F, BODY_Z = 0F;
    static final float NECK_X = 0.5F, NECK_Y = -4F, NECK_Z = 0F;
    static final float HEAD_X = -0.5F, HEAD_Y = -8F, HEAD_Z = 0F;
    static final float JAW_X = 0F, JAW_Y = 4F, JAW_Z = 0F;
    static final float EARL_X = 0F, EARL_Y = -5F, EARL_Z = 0.28F;
    static final float EARR_X = 0F, EARR_Y = -5F, EARR_Z = -0.28F;
    static final float TAIL_X = 0.8F, TAIL_Y = -3F, TAIL_Z = 0F;
    static final float LEGFL_X = 0F, LEGFL_Y = 6F, LEGFL_Z = 0F;
    static final float LEGFR_X = 0F, LEGFR_Y = 6F, LEGFR_Z = 0F;
    static final float LEGBL_X = 0F, LEGBL_Y = 6F, LEGBL_Z = 0F;
    static final float LEGBR_X = 0F, LEGBR_Y = 6F, LEGBR_Z = 0F;

    private final ModelPart root;
    private final ModelPart body, neck, head, jaw, earL, earR, tail, legFL, legFR, legBL, legBR;

    public HoundmasterModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        this.neck = body.getChild("neck");
        this.head = neck.getChild("head");
        this.jaw = head.getChild("jaw");
        this.earL = head.getChild("earL");
        this.earR = head.getChild("earR");
        this.tail = body.getChild("tail");
        this.legFL = body.getChild("legFL");
        this.legFR = body.getChild("legFR");
        this.legBL = body.getChild("legBL");
        this.legBR = body.getChild("legBR");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition v_body = root.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-6.5F, -6F, -13F, 13F, 12F, 26F)
                        .texOffs(36, 38).addBox(-7.5F, -7.5F, -14F, 15F, 6F, 11F),
                PartPose.offsetAndRotation(0F, 5F, 0F, 0F, 0F, 0F));
        PartDefinition v_neck = v_body.addOrReplaceChild("neck", CubeListBuilder.create()
                        .texOffs(88, 38).addBox(-3F, -9F, -3F, 6F, 10F, 6F)
                        .texOffs(24, 72).addBox(-4F, -1F, -4F, 8F, 3F, 8F),
                PartPose.offsetAndRotation(0F, -4F, -11F, 0.5F, 0F, 0F));
        PartDefinition v_collar_spikes = v_neck.addOrReplaceChild("collar_spikes", CubeListBuilder.create()
                        .texOffs(8, 93).addBox(-0.5F, -1.5F, -5.5F, 1F, 2.5F, 1.6F)
                        .texOffs(14, 93).addBox(-0.5F, -1.5F, 3.9F, 1F, 2.5F, 1.6F)
                        .texOffs(20, 93).addBox(-5.5F, -1.5F, -0.5F, 1.6F, 2.5F, 1F)
                        .texOffs(26, 93).addBox(3.9F, -1.5F, -0.5F, 1.6F, 2.5F, 1F),
                PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0F));
        PartDefinition v_head = v_neck.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(78, 0).addBox(-5F, -5F, -8F, 10F, 9F, 9F)
                        .texOffs(0, 72).addBox(-2.5F, -0.5F, -15F, 5F, 4.5F, 7F)
                        .texOffs(32, 93).addBox(-2.3F, 4F, -14.2F, 1F, 2.2F, 1F)
                        .texOffs(36, 93).addBox(1.3F, 4F, -14.2F, 1F, 2.2F, 1F)
                        .texOffs(40, 93).addBox(-2.3F, 4F, -10.5F, 1F, 1.4F, 1F)
                        .texOffs(44, 93).addBox(1.3F, 4F, -10.5F, 1F, 1.4F, 1F),
                PartPose.offsetAndRotation(0F, -8F, 0F, -0.5F, 0F, 0F));
        PartDefinition v_jaw = v_head.addOrReplaceChild("jaw", CubeListBuilder.create()
                        .texOffs(80, 56).addBox(-2.4F, 0F, -12F, 4.8F, 1.8F, 12F)
                        .texOffs(48, 93).addBox(-2.2F, -2F, -11.2F, 1F, 2F, 1F)
                        .texOffs(52, 93).addBox(1.2F, -2F, -11.2F, 1F, 2F, 1F),
                PartPose.offsetAndRotation(0F, 4F, -2F, 0F, 0F, 0F));
        PartDefinition v_earL = v_head.addOrReplaceChild("earL", CubeListBuilder.create()
                        .texOffs(52, 84).addBox(-1.5F, -6F, -0.5F, 3F, 6F, 1.2F),
                PartPose.offsetAndRotation(3.4F, -5F, -1F, 0F, 0F, 0.28F));
        PartDefinition v_earR = v_head.addOrReplaceChild("earR", CubeListBuilder.create()
                        .texOffs(61, 84).addBox(-1.5F, -6F, -0.5F, 3F, 6F, 1.2F),
                PartPose.offsetAndRotation(-3.4F, -5F, -1F, 0F, 0F, -0.28F));
        PartDefinition v_shoulderL = v_body.addOrReplaceChild("shoulderL", CubeListBuilder.create()
                        .texOffs(70, 84).addBox(-1F, -5F, -1F, 2F, 5F, 2F),
                PartPose.offsetAndRotation(7F, -7F, -9F, 0F, 0F, 0.6F));
        PartDefinition v_shoulderR = v_body.addOrReplaceChild("shoulderR", CubeListBuilder.create()
                        .texOffs(78, 84).addBox(-1F, -5F, -1F, 2F, 5F, 2F),
                PartPose.offsetAndRotation(-7F, -7F, -9F, 0F, 0F, -0.6F));
        PartDefinition v_spike0 = v_body.addOrReplaceChild("spike0", CubeListBuilder.create()
                        .texOffs(100, 84).addBox(-1F, -4F, -1F, 2F, 4F, 2F),
                PartPose.offsetAndRotation(0F, -6.2F, -4F, -0.35F, 0F, 0F));
        PartDefinition v_spike1 = v_body.addOrReplaceChild("spike1", CubeListBuilder.create()
                        .texOffs(108, 84).addBox(-1F, -4F, -1F, 2F, 4F, 2F),
                PartPose.offsetAndRotation(0F, -6.2F, 1F, -0.35F, 0F, 0F));
        PartDefinition v_spike2 = v_body.addOrReplaceChild("spike2", CubeListBuilder.create()
                        .texOffs(116, 84).addBox(-1F, -4F, -1F, 2F, 4F, 2F),
                PartPose.offsetAndRotation(0F, -6.2F, 6F, -0.35F, 0F, 0F));
        PartDefinition v_spike3 = v_body.addOrReplaceChild("spike3", CubeListBuilder.create()
                        .texOffs(0, 93).addBox(-1F, -4F, -1F, 2F, 4F, 2F),
                PartPose.offsetAndRotation(0F, -6.2F, 11F, -0.35F, 0F, 0F));
        PartDefinition v_tail = v_body.addOrReplaceChild("tail", CubeListBuilder.create()
                        .texOffs(0, 38).addBox(-1.5F, -1.5F, 0F, 3F, 3F, 15F)
                        .texOffs(86, 84).addBox(-1.5F, -1.5F, 15F, 3F, 3F, 4F),
                PartPose.offsetAndRotation(0F, -3F, 13F, 0.8F, 0F, 0F));
        PartDefinition v_legFL = v_body.addOrReplaceChild("legFL", CubeListBuilder.create()
                        .texOffs(0, 56).addBox(-2.5F, 0F, -2.5F, 5F, 11F, 5F)
                        .texOffs(56, 72).addBox(-3F, 11F, -3.5F, 6F, 2F, 7F)
                        .texOffs(56, 93).addBox(-2.5F, 11.4F, -4.6F, 5F, 1.4F, 1.1F),
                PartPose.offsetAndRotation(4.5F, 6F, -9F, 0F, 0F, 0F));
        PartDefinition v_legFR = v_body.addOrReplaceChild("legFR", CubeListBuilder.create()
                        .texOffs(20, 56).addBox(-2.5F, 0F, -2.5F, 5F, 11F, 5F)
                        .texOffs(82, 72).addBox(-3F, 11F, -3.5F, 6F, 2F, 7F)
                        .texOffs(69, 93).addBox(-2.5F, 11.4F, -4.6F, 5F, 1.4F, 1.1F),
                PartPose.offsetAndRotation(-4.5F, 6F, -9F, 0F, 0F, 0F));
        PartDefinition v_legBL = v_body.addOrReplaceChild("legBL", CubeListBuilder.create()
                        .texOffs(40, 56).addBox(-2.5F, 0F, -2.5F, 5F, 11F, 5F)
                        .texOffs(0, 84).addBox(-3F, 11F, -3.5F, 6F, 2F, 7F)
                        .texOffs(82, 93).addBox(-2.5F, 11.4F, -4.6F, 5F, 1.4F, 1.1F),
                PartPose.offsetAndRotation(4.5F, 6F, 9F, 0F, 0F, 0F));
        PartDefinition v_legBR = v_body.addOrReplaceChild("legBR", CubeListBuilder.create()
                        .texOffs(60, 56).addBox(-2.5F, 0F, -2.5F, 5F, 11F, 5F)
                        .texOffs(26, 84).addBox(-3F, 11F, -3.5F, 6F, 2F, 7F)
                        .texOffs(95, 93).addBox(-2.5F, 11.4F, -4.6F, 5F, 1.4F, 1.1F),
                PartPose.offsetAndRotation(-4.5F, 6F, 9F, 0F, 0F, 0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(HoundmasterEntity e, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        int st = e.getHoundState();
        float swing = limbSwing * 0.6662F;
        float amp = Math.min(1.0F, limbSwingAmount);
        float gallop = 1.1F + amp * 0.4F;

        // estado base de cada parte
        float neckX = NECK_X, headX = HEAD_X, jawX = 0.04F + Mth.sin(ageInTicks * 0.09F) * 0.03F;
        float flX = 0, frX = 0, blX = 0, brX = 0;
        float bodyY = BODY_Y;
        float tailX = TAIL_X, tailY = Mth.sin(ageInTicks * 0.12F) * 0.18F;

        if (amp > 0.02F) {
            flX = Mth.cos(swing) * gallop * amp;
            brX = Mth.cos(swing) * gallop * amp;
            frX = Mth.cos(swing + Mth.PI) * gallop * amp;
            blX = Mth.cos(swing + Mth.PI) * gallop * amp;
            bodyY = BODY_Y + Mth.sin(swing * 2.0F) * amp * 0.9F;
            tailX = TAIL_X - 0.5F * amp;
            tailY *= (1.0F - amp);
        }

        switch (st) {
            case HoundmasterEntity.STATE_POUNCE -> {
                flX = frX = -1.25F; blX = brX = 0.95F;
                jawX = 0.85F; neckX = NECK_X + 0.3F; tailX = TAIL_X - 0.5F;
            }
            case HoundmasterEntity.STATE_GROWL -> {
                neckX = NECK_X + 0.55F; headX = HEAD_X + 0.1F;
                jawX = 0.3F + Mth.sin(ageInTicks * 1.6F) * 0.06F;
                flX = -0.25F; frX = -0.25F; bodyY = BODY_Y + 1.5F;
            }
            case HoundmasterEntity.STATE_GRAB -> {
                jawX = 0.9F; neckX = NECK_X + 0.3F; headX = HEAD_X + 0.15F;
                flX = frX = -0.5F;
            }
            case HoundmasterEntity.STATE_HOWL -> {
                neckX = NECK_X - 0.9F; headX = HEAD_X - 0.75F;
                jawX = 0.55F; flX = frX = -0.1F;
            }
            case HoundmasterEntity.STATE_CHARGE -> {
                neckX = NECK_X + 0.7F; headX = HEAD_X + 0.2F; jawX = 0.45F;
                flX = frX = -0.9F; blX = brX = 0.7F; bodyY = BODY_Y + 2.0F;
            }
            case HoundmasterEntity.STATE_DIG -> {
                neckX = NECK_X + 1.0F; headX = HEAD_X + 0.7F; jawX = 0.15F;
                bodyY = BODY_Y + 2.5F;
                flX = -0.9F + Mth.sin(ageInTicks * 1.4F) * 0.9F;
                frX = -0.9F - Mth.sin(ageInTicks * 1.4F) * 0.9F;
            }
            case HoundmasterEntity.STATE_STUN -> {
                neckX = NECK_X + 0.9F; headX = HEAD_X + 0.3F; jawX = 0.1F;
                bodyY = BODY_Y + 3.0F; flX = frX = 0.3F; blX = brX = 0.3F;
            }
            default -> { }
        }

        body.y = bodyY;
        body.xRot = BODY_X;
        neck.xRot = neckX;
        head.xRot = headX + headPitch * Mth.DEG_TO_RAD * 0.7F;
        head.yRot = Mth.clamp(netHeadYaw, -45.0F, 45.0F) * Mth.DEG_TO_RAD;
        jaw.xRot = jawX;
        earL.zRot = EARL_Z + Mth.sin(ageInTicks * 0.07F) * 0.05F;
        earR.zRot = EARR_Z - Mth.sin(ageInTicks * 0.07F) * 0.05F;
        tail.xRot = tailX;
        tail.yRot = tailY;
        legFL.xRot = flX;
        legFR.xRot = frX;
        legBL.xRot = blX;
        legBR.xRot = brX;
    }

    @Override
    public void renderToBuffer(PoseStack pose, VertexConsumer buffer, int light, int overlay,
                               float r, float g, float b, float a) {
        root.render(pose, buffer, light, overlay, r, g, b, a);
    }
}
