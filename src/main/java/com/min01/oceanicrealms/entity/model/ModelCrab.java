package com.min01.oceanicrealms.entity.model;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.animation.CrabAnimation;
import com.min01.oceanicrealms.entity.living.EntityCrab;
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

public class ModelCrab extends HierarchicalModel<EntityCrab>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OceanicRealms.MODID, "crab"), "main");
	private final ModelPart root;

	public ModelCrab(ModelPart root)
	{
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone4 = root.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition bone = bone4.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -2.0F, -3.0F, 8.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(8, 19).mirror().addBox(0.1F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.0F, -3.0F, -0.1745F, 0.0F, 0.1309F));

		bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(8, 19).addBox(-1.1F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -3.0F, -0.1745F, 0.0F, -0.1309F));

		bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(4, 20).mirror().addBox(-1.3F, -1.5F, -0.1F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, -2.0F, -3.0F));

		bone.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(4, 20).addBox(-0.7F, -1.5F, -0.1F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -2.0F, -3.0F));

		bone.addOrReplaceChild("right", CubeListBuilder.create().texOffs(0, 10).addBox(0.0F, -1.5F, -4.5F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, -2.0F, 0.4363F, 0.6981F, 0.0F));

		bone.addOrReplaceChild("left", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-2.0F, -1.5F, -4.5F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 0.0F, -2.0F, 0.4363F, -0.6981F, 0.0F));

		bone4.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(0, 17).addBox(-0.2F, 0.0F, 0.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 1.0F, -2.0F, 0.0873F, 0.1222F, 0.7854F));

		bone4.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(16, 17).addBox(0.0F, 0.0F, -1.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.6981F));

		bone4.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(8, 18).addBox(-0.2F, 0.0F, -1.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 1.0F, 3.0F, -0.0873F, -0.1309F, 0.7854F));

		bone4.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(8, 18).mirror().addBox(-2.8F, 0.0F, -1.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 1.0F, 3.0F, -0.0873F, 0.1309F, -0.7854F));

		bone4.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(-2.8F, 0.0F, 0.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 1.0F, -2.0F, 0.0873F, -0.1222F, -0.7854F));

		bone4.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(16, 17).mirror().addBox(-3.0F, 0.0F, -1.0F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 1.0F, 1.0F, 0.0F, 0.0F, -0.6981F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(EntityCrab entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.root.yRot += Math.toRadians(90.0F);
		this.animateWalk(CrabAnimation.CRAB_WALK, limbSwing, limbSwingAmount, 2.5F, 2.5F);
		this.animate(entity.idleAnimationState, CrabAnimation.CRAB_IDLE, ageInTicks);
		this.animate(entity.danceAnimationState, CrabAnimation.CRAB_DANCE, ageInTicks);
		this.animate(entity.eatingAnimationState, CrabAnimation.CRAB_EATING, ageInTicks);
		this.animate(entity.digAnimationState, CrabAnimation.CRAB_DIG, ageInTicks);
		this.animate(entity.digOutAnimationState, CrabAnimation.CRAB_DIG_OUT, ageInTicks);
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