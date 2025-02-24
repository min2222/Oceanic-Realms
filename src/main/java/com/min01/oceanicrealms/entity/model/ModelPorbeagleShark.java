package com.min01.oceanicrealms.entity.model;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.animation.PorbeagleSharkAnimation;
import com.min01.oceanicrealms.entity.living.EntityPorbeagleShark;
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

public class ModelPorbeagleShark extends HierarchicalModel<EntityPorbeagleShark>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OceanicRealms.MODID, "porbeagle_shark"), "main");
	private final ModelPart root;

	public ModelPorbeagleShark(ModelPart root) 
	{
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -7.0F, -10.0F, 12.0F, 14.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, -3.0F));

		bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(54, 66).addBox(-1.0F, -9.0F, 0.0F, 2.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 1.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(38, 32).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 8.0F));

		bone2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(70, 71).addBox(-1.5F, -4.0F, 0.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -4.0F, 4.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(2, 59).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 7.0F));

		PartDefinition bone11 = bone3.addOrReplaceChild("bone11", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 6.0F));

		bone11.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(60, 20).addBox(-1.5F, 0.0F, 0.0F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 5.6568F, -0.7854F, 0.0F, 0.0F));

		bone11.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(60, 0).addBox(-0.5F, -16.0F, -1.0F, 1.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.7071F, 0.7071F, -0.7854F, 0.0F, 0.0F));

		bone2.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(71, 67).addBox(-1.0F, 0.0F, -1.0F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 4.0F, 5.0F, 0.0F, -0.3927F, 0.5236F));

		bone2.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(31, 72).addBox(-4.0F, 0.0F, -1.0F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 4.0F, 5.0F, 0.0F, 0.3927F, -0.5236F));

		PartDefinition bone4 = bone.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(28, 57).addBox(-5.0F, -5.0F, -3.0F, 10.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -10.0F));

		bone4.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(0, 32).addBox(-5.0F, -5.0F, -9.0F, 10.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(77, 1).addBox(-3.0F, 0.0F, -6.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.0F));

		bone4.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(54, 57).addBox(-3.5F, -1.0F, -6.0F, 7.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(1, 78).addBox(-2.5F, -3.0F, -5.5F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -2.0F, -0.1309F, 0.0F, 0.0F));

		bone.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(0, 49).addBox(-1.0F, -1.0F, -3.0F, 14.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 6.0F, -3.0F, 0.1309F, -0.1309F, 0.4363F));

		bone.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(42, 49).addBox(-13.0F, -1.0F, -3.0F, 14.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 6.0F, -3.0F, 0.1309F, 0.1309F, -0.4363F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(EntityPorbeagleShark entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		OceanicClientUtil.animateHead(this.root, netHeadYaw, headPitch);
		this.root.zRot += Math.toRadians(entity.getRollAngle());
		this.animateWalk(PorbeagleSharkAnimation.PORBEAGLE_SHARK_SWIM, limbSwing, limbSwingAmount, 2.5F, 2.5F);
		this.animate(entity.attackAnimationState, PorbeagleSharkAnimation.PORBEAGLE_SHARK_ATTACK, ageInTicks);
		this.animate(entity.eatingAnimationState, PorbeagleSharkAnimation.PORBEAGLE_SHARK_EATING, ageInTicks);
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