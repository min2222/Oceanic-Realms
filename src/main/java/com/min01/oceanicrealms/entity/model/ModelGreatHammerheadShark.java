package com.min01.oceanicrealms.entity.model;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.animation.GreatHammerheadSharkAnimation;
import com.min01.oceanicrealms.entity.living.EntityGreatHammerheadShark;
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

public class ModelGreatHammerheadShark extends HierarchicalModel<EntityGreatHammerheadShark>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OceanicRealms.MODID, "great_hammerhead_shark"), "main");
	private final ModelPart root;

	public ModelGreatHammerheadShark(ModelPart root) 
	{
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, 0.0F));

		PartDefinition bone8 = bone.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(0, 60).addBox(-9.0F, -8.0F, 0.0F, 18.0F, 17.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 18.0F));

		bone8.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(6, 157).addBox(-1.0F, -18.0F, 11.0F, 2.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, 9.0F, 0.1745F, 0.0F, 0.0F));

		bone8.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(126, 129).addBox(-1.0F, -18.0F, 11.0F, 2.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 4.0F, -0.1745F, 0.0F, 0.0F));

		bone8.addOrReplaceChild("left2", CubeListBuilder.create().texOffs(118, 52).addBox(0.0F, -2.0F, -4.0F, 11.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 7.0F, 10.0F, 0.0F, -0.3927F, 0.5236F));

		bone8.addOrReplaceChild("right2", CubeListBuilder.create().texOffs(118, 52).mirror().addBox(-11.0F, -2.0F, -4.0F, 11.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-8.0F, 7.0F, 10.0F, 0.0F, 0.3927F, -0.5236F));

		PartDefinition bone11 = bone8.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(60, 98).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 7.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 16.0F));

		bone11.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(126, 129).addBox(-1.0F, -18.0F, 11.0F, 2.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, -12.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition bone12 = bone11.addOrReplaceChild("bone12", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 13.0F));

		bone12.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(99, 130).addBox(-1.0F, -27.0F, -14.0F, 2.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.364F, 8.364F, -0.7854F, 0.0F, 0.0F));

		bone12.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(119, 17).addBox(-1.0F, -22.0F, -5.0F, 2.0F, 27.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, -0.7854F, 0.0F, 0.0F));

		bone12.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 121).addBox(-1.0F, -33.0F, 4.0F, 2.0F, 8.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.5061F, -19.9203F, -0.7854F, 0.0F, 0.0F));

		PartDefinition bone5 = bone.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(0, 93).addBox(-10.0F, -10.0F, -9.0F, 20.0F, 19.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -15.0F));

		bone5.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(68, 60).addBox(-9.0F, -9.0F, -17.0F, 18.0F, 5.0F, 19.0F, new CubeDeformation(0.0F))
		.texOffs(110, 96).addBox(-7.0F, -3.99F, -11.0F, 14.0F, 3.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(118, 0).addBox(-6.0F, -1.0F, -10.0F, 12.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -11.0F));

		bone5.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(110, 112).addBox(-6.0F, 0.0F, -13.0F, 12.0F, 5.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(60, 121).addBox(-5.0F, -3.0F, -12.0F, 10.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -7.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 0).addBox(-13.0F, -15.0F, -15.0F, 26.0F, 27.0F, 33.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		bone2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(34, 121).addBox(-1.0F, -34.0F, 4.0F, 2.0F, 19.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		bone2.addOrReplaceChild("right", CubeListBuilder.create().texOffs(68, 84).mirror().addBox(-27.0F, -2.0F, -3.0F, 27.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-12.0F, 9.0F, -5.0F, 0.1309F, 0.1309F, -0.4363F));

		bone2.addOrReplaceChild("left", CubeListBuilder.create().texOffs(68, 84).addBox(0.0F, -2.0F, -3.0F, 27.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.0F, 9.0F, -5.0F, 0.1309F, -0.1309F, 0.4363F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(EntityGreatHammerheadShark entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		OceanicClientUtil.animateHead(this.root, netHeadYaw, headPitch);
		this.root.zRot += Math.toRadians(entity.getRollAngle());
		this.animateWalk(GreatHammerheadSharkAnimation.GREAT_HAMMERHEAD_SHARK_SWIM, limbSwing, limbSwingAmount, 2.5F, 2.5F);
		this.animate(entity.attackAnimationState, GreatHammerheadSharkAnimation.GREAT_HAMMERHEAD_SHARK_ATTACK, ageInTicks);
		this.animate(entity.eatingAnimationState, GreatHammerheadSharkAnimation.GREAT_HAMMERHEAD_SHARK_EATING, ageInTicks);
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