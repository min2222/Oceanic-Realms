package com.min01.oceanicrealms.entity.model;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.animation.BabySailfishAnimation;
import com.min01.oceanicrealms.entity.living.EntitySailfish;
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

public class ModelBabySailfish extends HierarchicalModel<EntitySailfish>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OceanicRealms.MODID, "baby_sailfish"), "main");
	private final ModelPart root;

	public ModelBabySailfish(ModelPart root)
	{
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.5F, -7.0F, 4.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(36, -5).addBox(0.0F, -7.5F, -7.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(12, 20).addBox(-2.0F, -1.5F, -9.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 6).addBox(-1.0F, -1.5F, -11.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 0).addBox(0.0F, -1.5F, -16.0F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.5F, 1.0F));

		bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(34, 9).addBox(0.0F, 0.0F, -1.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 2.5F, -3.0F, 0.4363F, 0.0F, -0.2618F));

		bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(34, 9).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 2.5F, -3.0F, 0.4363F, 0.0F, 0.2618F));

		bone.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(24, 9).addBox(-1.0F, -0.7F, -1.7F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -9.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(12, 13).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(1, 26).addBox(0.0F, -3.5F, 0.0F, 0.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));

		bone2.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(1, 14).addBox(0.0F, -4.5F, 0.0F, 0.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		bone.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(25, 20).addBox(0.0F, 0.0F, -1.0F, 5.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 1.5F, -2.0F));

		bone.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(25, 20).mirror().addBox(-5.0F, 0.0F, -1.0F, 5.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 1.5F, -2.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(EntitySailfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		OceanicClientUtil.animateHead(this.root, netHeadYaw, headPitch);
		this.root.zRot += Math.toRadians(entity.getRollAngle());
		this.animate(entity.dryAnimationState, BabySailfishAnimation.SAILFISH_DRY, ageInTicks);
		entity.eatingAnimationState.animate(this, BabySailfishAnimation.SAILFISH_EATING, ageInTicks);
		this.animateWalk(BabySailfishAnimation.SAILFISH_SWIM, limbSwing, limbSwingAmount, 1.5F, 1.5F);
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