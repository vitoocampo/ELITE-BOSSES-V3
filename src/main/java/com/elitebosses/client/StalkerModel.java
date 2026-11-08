package com.elitebosses.client;

import com.elitebosses.EliteBosses;
import com.elitebosses.entity.StalkerEntity;
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

/** Modelo 3D de la Krasue (cabeza flotante con entranas colgando; textura 128x128). */
public class StalkerModel extends EntityModel<StalkerEntity> {
    public static final ModelLayerLocation LAYER =
            new ModelLayerLocation(new ResourceLocation(EliteBosses.MODID, "stalker"), "main");

    private final ModelPart root;
    private final ModelPart torso, neck, head, jaw, hairBack, hairL, hairR;
    private final ModelPart[][] tentacles = new ModelPart[5][4];

    public StalkerModel(ModelPart root) {
        this.root = root;
        this.torso = root.getChild("torso");
        this.neck = torso.getChild("neck");
        this.head = neck.getChild("head");
        this.jaw = head.getChild("jaw");
        this.hairBack = head.getChild("hairBack");
        this.hairL = head.getChild("hairL");
        this.hairR = head.getChild("hairR");
        for (int i = 0; i < 5; i++) {
            ModelPart t = torso.getChild("t" + i);
            tentacles[i][0] = t;
            for (int s = 1; s <= 3; s++) {
                t = t.getChild("t" + i + "s" + s);
                tentacles[i][s] = t;
            }
        }
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition v_torso = root.addOrReplaceChild("torso", CubeListBuilder.create()
                        .texOffs(60, 0).addBox(-1F, -9F, -1F, 2F, 18F, 2F)
                        .texOffs(100, 0).addBox(2F, -8F, -2.5F, 5F, 8F, 4F)
                        .texOffs(0, 26).addBox(-7F, -8F, -2.5F, 5F, 8F, 4F)
                        .texOffs(0, 38).addBox(-2F, -5F, -2F, 4F, 4F, 3.5F)
                        .texOffs(54, 26).addBox(-3F, 1F, -2.5F, 6F, 5F, 5F),
                PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0F));
        PartDefinition v_neck = v_torso.addOrReplaceChild("neck", CubeListBuilder.create()
                        .texOffs(102, 26).addBox(-1.5F, -6F, -1.5F, 3F, 6F, 3F),
                PartPose.offsetAndRotation(0F, -9F, 0F, 0F, 0F, 0F));
        PartDefinition v_head = v_neck.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(68, 0).addBox(-4F, -8F, -4F, 8F, 8F, 8F)
                        .texOffs(12, 54).addBox(-2.5F, 0F, -4.3F, 1F, 1.6F, 1F)
                        .texOffs(16, 54).addBox(-0.5F, 0F, -4.3F, 1F, 1.4F, 1F)
                        .texOffs(20, 54).addBox(1.5F, 0F, -4.3F, 1F, 1.6F, 1F),
                PartPose.offsetAndRotation(0F, -6F, 0F, 0F, 0F, 0F));
        PartDefinition v_jaw = v_head.addOrReplaceChild("jaw", CubeListBuilder.create()
                        .texOffs(76, 26).addBox(-3F, 0F, -7F, 6F, 2.5F, 7F)
                        .texOffs(24, 54).addBox(-2F, -1.4F, -6.6F, 1F, 1.4F, 1F)
                        .texOffs(28, 54).addBox(1F, -1.4F, -6.6F, 1F, 1.4F, 1F),
                PartPose.offsetAndRotation(0F, -0.5F, 3F, 0.25F, 0F, 0F));
        PartDefinition v_hairBack = v_head.addOrReplaceChild("hairBack", CubeListBuilder.create()
                        .texOffs(36, 0).addBox(-5F, 0F, 0F, 10F, 22F, 1.5F)
                        .texOffs(18, 26).addBox(-4.5F, -1.2F, -8F, 9F, 1.5F, 9F),
                PartPose.offsetAndRotation(0F, -8.5F, 3.5F, 0.08F, 0F, 0F));
        PartDefinition v_hairL = v_head.addOrReplaceChild("hairL", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(0F, 0F, 0F, 1.2F, 19F, 7F),
                PartPose.offsetAndRotation(4F, -8F, -3F, 0F, 0F, -0.12F));
        PartDefinition v_hairR = v_head.addOrReplaceChild("hairR", CubeListBuilder.create()
                        .texOffs(18, 0).addBox(-1.2F, 0F, 0F, 1.2F, 19F, 7F),
                PartPose.offsetAndRotation(-4F, -8F, -3F, 0F, 0F, 0.12F));
        PartDefinition v_t0 = v_torso.addOrReplaceChild("t0", CubeListBuilder.create()
                        .texOffs(16, 38).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F),
                PartPose.offsetAndRotation(-5F, 5F, -1F, 0F, 0F, 0F));
        PartDefinition v_t0s1 = v_t0.addOrReplaceChild("t0s1", CubeListBuilder.create()
                        .texOffs(28, 38).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F),
                PartPose.offsetAndRotation(0F, 5F, 0F, 0F, 0F, 0F));
        PartDefinition v_t0s2 = v_t0s1.addOrReplaceChild("t0s2", CubeListBuilder.create()
                        .texOffs(40, 38).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F),
                PartPose.offsetAndRotation(0F, 5F, 0F, 0F, 0F, 0F));
        PartDefinition v_t0s3 = v_t0s2.addOrReplaceChild("t0s3", CubeListBuilder.create()
                        .texOffs(52, 38).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F),
                PartPose.offsetAndRotation(0F, 5F, 0F, 0F, 0F, 0F));
        PartDefinition v_t1 = v_torso.addOrReplaceChild("t1", CubeListBuilder.create()
                        .texOffs(64, 38).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F),
                PartPose.offsetAndRotation(-2.5F, 5F, 1.5F, 0F, 0F, 0F));
        PartDefinition v_t1s1 = v_t1.addOrReplaceChild("t1s1", CubeListBuilder.create()
                        .texOffs(76, 38).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F),
                PartPose.offsetAndRotation(0F, 5F, 0F, 0F, 0F, 0F));
        PartDefinition v_t1s2 = v_t1s1.addOrReplaceChild("t1s2", CubeListBuilder.create()
                        .texOffs(88, 38).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F),
                PartPose.offsetAndRotation(0F, 5F, 0F, 0F, 0F, 0F));
        PartDefinition v_t1s3 = v_t1s2.addOrReplaceChild("t1s3", CubeListBuilder.create()
                        .texOffs(100, 38).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F),
                PartPose.offsetAndRotation(0F, 5F, 0F, 0F, 0F, 0F));
        PartDefinition v_t2 = v_torso.addOrReplaceChild("t2", CubeListBuilder.create()
                        .texOffs(112, 38).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F),
                PartPose.offsetAndRotation(0F, 5F, -1.5F, 0F, 0F, 0F));
        PartDefinition v_t2s1 = v_t2.addOrReplaceChild("t2s1", CubeListBuilder.create()
                        .texOffs(0, 46).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F),
                PartPose.offsetAndRotation(0F, 5F, 0F, 0F, 0F, 0F));
        PartDefinition v_t2s2 = v_t2s1.addOrReplaceChild("t2s2", CubeListBuilder.create()
                        .texOffs(12, 46).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F),
                PartPose.offsetAndRotation(0F, 5F, 0F, 0F, 0F, 0F));
        PartDefinition v_t2s3 = v_t2s2.addOrReplaceChild("t2s3", CubeListBuilder.create()
                        .texOffs(24, 46).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F),
                PartPose.offsetAndRotation(0F, 5F, 0F, 0F, 0F, 0F));
        PartDefinition v_t3 = v_torso.addOrReplaceChild("t3", CubeListBuilder.create()
                        .texOffs(36, 46).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F),
                PartPose.offsetAndRotation(2.5F, 5F, 1.5F, 0F, 0F, 0F));
        PartDefinition v_t3s1 = v_t3.addOrReplaceChild("t3s1", CubeListBuilder.create()
                        .texOffs(48, 46).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F),
                PartPose.offsetAndRotation(0F, 5F, 0F, 0F, 0F, 0F));
        PartDefinition v_t3s2 = v_t3s1.addOrReplaceChild("t3s2", CubeListBuilder.create()
                        .texOffs(60, 46).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F),
                PartPose.offsetAndRotation(0F, 5F, 0F, 0F, 0F, 0F));
        PartDefinition v_t3s3 = v_t3s2.addOrReplaceChild("t3s3", CubeListBuilder.create()
                        .texOffs(72, 46).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F),
                PartPose.offsetAndRotation(0F, 5F, 0F, 0F, 0F, 0F));
        PartDefinition v_t4 = v_torso.addOrReplaceChild("t4", CubeListBuilder.create()
                        .texOffs(84, 46).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F),
                PartPose.offsetAndRotation(5F, 5F, -1F, 0F, 0F, 0F));
        PartDefinition v_t4s1 = v_t4.addOrReplaceChild("t4s1", CubeListBuilder.create()
                        .texOffs(96, 46).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F),
                PartPose.offsetAndRotation(0F, 5F, 0F, 0F, 0F, 0F));
        PartDefinition v_t4s2 = v_t4s1.addOrReplaceChild("t4s2", CubeListBuilder.create()
                        .texOffs(108, 46).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F),
                PartPose.offsetAndRotation(0F, 5F, 0F, 0F, 0F, 0F));
        PartDefinition v_t4s3 = v_t4s2.addOrReplaceChild("t4s3", CubeListBuilder.create()
                        .texOffs(0, 54).addBox(-1.5F, 0F, -1.5F, 3F, 5F, 3F),
                PartPose.offsetAndRotation(0F, 5F, 0F, 0F, 0F, 0F));
        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(StalkerEntity e, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        float t = ageInTicks;
        torso.xRot = Mth.sin(t * 0.05F) * 0.05F;
        torso.zRot = Mth.sin(t * 0.04F + 1.0F) * 0.05F;
        neck.xRot = Mth.sin(t * 0.07F) * 0.08F;
        head.yRot = Mth.clamp(netHeadYaw, -60.0F, 60.0F) * Mth.DEG_TO_RAD;
        head.xRot = headPitch * Mth.DEG_TO_RAD * 0.5F + Mth.sin(t * 0.06F) * 0.05F;
        jaw.xRot = 0.25F + (Mth.sin(t * 0.11F) * 0.5F + 0.5F) * 0.35F;
        hairBack.xRot = 0.08F + Mth.sin(t * 0.09F) * 0.08F;
        hairL.zRot = -0.12F - Mth.sin(t * 0.08F + 0.5F) * 0.08F;
        hairR.zRot = 0.12F + Mth.sin(t * 0.08F) * 0.08F;
        for (int i = 0; i < 5; i++) {
            for (int s = 0; s < 4; s++) {
                float ph = t * 0.12F - s * 0.7F + i * 1.3F;
                tentacles[i][s].xRot = Mth.sin(ph) * (0.18F + 0.07F * s);
                tentacles[i][s].zRot = Mth.cos(ph * 0.8F) * (0.14F + 0.05F * s);
            }
        }
    }

    @Override
    public void renderToBuffer(PoseStack pose, VertexConsumer buffer, int light, int overlay,
                               float r, float g, float b, float a) {
        root.render(pose, buffer, light, overlay, r, g, b, a);
    }
}
