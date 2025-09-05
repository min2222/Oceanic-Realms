package com.min01.oceanicrealms.entity.model;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.animation.BabyGreatWhiteSharkAnimation;
import com.min01.oceanicrealms.entity.living.EntityGreatWhiteShark;
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

public class ModelBabyGreatWhiteShark extends HierarchicalModel<EntityGreatWhiteShark>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OceanicRealms.MODID, "baby_great_white_shark"), "main");
	private final ModelPart root;
	
	public ModelBabyGreatWhiteShark(ModelPart root)
	{
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone2 = root.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(26, 41).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -1.0F));

		bone2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 30).addBox(-1.5F, -3.0F, 0.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -3.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		bone2.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(12, 33).mirror().addBox(0.0F, -0.5F, -1.5F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 2.0F, 0.0F, 0.1309F, -0.1309F, 0.4363F));

		bone2.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(12, 33).addBox(-5.0F, -0.5F, -1.5F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 2.0F, 0.0F, 0.1309F, 0.1309F, -0.4363F));

		PartDefinition bone5 = bone2.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(0, 37).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 4.0F));

		bone5.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(8, 46).addBox(-1.5F, -1.1F, -0.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 2.5F, 2.0F, 0.1745F, 0.0F, 0.0F));

		bone5.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(1, 46).addBox(-1.5F, -0.8F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.5F, 2.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition bone6 = bone5.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 3.0F));

		bone6.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(1, 52).addBox(-1.5F, -4.0F, 0.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.5F, 1.0F, -0.7854F, 0.0F, 0.0F));

		bone6.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(6, 57).addBox(-2.0F, -6.0F, 0.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 2.7426F, -3.2426F, -0.7854F, 0.0F, 0.0F));

		bone2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 12).addBox(-3.0F, -3.0F, -5.0F, 6.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 19).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 24).addBox(-2.0F, 0.0F, -4.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.0F));

		bone2.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(30, 18).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(-0.01F))
		.texOffs(18, 24).addBox(-1.5F, -1.99F, -4.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -2.0F, -0.2618F, 0.0F, 0.0F));

		bone2.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(4, 4).mirror().addBox(-0.5F, -0.5F, -1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 1.0F, 4.0F, 0.0F, -0.6981F, 0.5236F));

		bone2.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(4, 4).addBox(-1.5F, -0.5F, -1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 1.0F, 4.0F, 0.0F, 0.6981F, -0.5236F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(EntityGreatWhiteShark entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		OceanicClientUtil.animateHead(this.root, netHeadYaw, headPitch);
		this.root.zRot += Math.toRadians(entity.getRollAngle());
		this.animateWalk(BabyGreatWhiteSharkAnimation.GREAT_WHITE_SHARK_SWIM, limbSwing, limbSwingAmount, 1.5F, 1.5F);
		entity.attackAnimationState.animate(this, BabyGreatWhiteSharkAnimation.GREAT_WHITE_SHARK_ATTACK, ageInTicks);
		entity.eatingAnimationState.animate(this, BabyGreatWhiteSharkAnimation.GREAT_WHITE_SHARK_EATING, ageInTicks);
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