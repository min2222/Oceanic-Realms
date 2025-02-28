package com.min01.oceanicrealms.entity.model;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.animation.TunaAnimation;
import com.min01.oceanicrealms.entity.living.EntityTuna;
import com.min01.oceanicrealms.util.OceanicClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class ModelTuna extends HierarchicalModel<EntityTuna>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OceanicRealms.MODID, "tuna"), "main");
	private final ModelPart root;

	public ModelTuna(ModelPart root) 
	{
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -8.0F, -20.0F, 10.0F, 15.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(0, 46).addBox(0.0F, -16.0F, -11.0F, 0.0F, 8.0F, 23.0F, new CubeDeformation(0.0F))
		.texOffs(0, 77).addBox(0.0F, 7.0F, 7.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 2.0F));

		bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(10, 77).mirror().addBox(0.0F, 0.0F, -3.0F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 7.0F, -7.0F, 0.0F, 0.0F, -0.3927F));

		bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(10, 77).addBox(0.0F, 0.0F, -3.0F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 7.0F, -7.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(46, 46).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 6.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(6, 92).addBox(0.0F, -4.0F, 0.0F, 0.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 11.0F));

		bone2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(76, 64).addBox(0.0F, -9.0F, -1.0F, 0.0F, 18.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 11.0F));

		bone.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(46, 64).addBox(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 2.0F, -10.0F, 0.2182F, -0.0873F, 0.0F));

		bone.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(46, 64).mirror().addBox(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 2.0F, -10.0F, 0.2182F, 0.0873F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(EntityTuna entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		OceanicClientUtil.animateHead(this.root, netHeadYaw, headPitch);
		this.root.zRot += Math.toRadians(entity.getRollAngle());
		this.animate(entity.dryAnimationState, TunaAnimation.TUNA_DRY, ageInTicks);
		this.animateWalk(TunaAnimation.TUNA_SWIM, limbSwing, limbSwingAmount, 3.5F, 2.5F);
	}
	
	@Override
	public ModelPart root() 
	{
		return this.root;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}