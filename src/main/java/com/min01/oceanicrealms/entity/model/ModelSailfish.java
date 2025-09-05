package com.min01.oceanicrealms.entity.model;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.animation.SailfishAnimation;
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

public class ModelSailfish extends HierarchicalModel<EntitySailfish>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OceanicRealms.MODID, "sailfish"), "main");
	private final ModelPart root;

	public ModelSailfish(ModelPart root) 
	{
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -7.0F, -12.0F, 10.0F, 13.0F, 26.0F, new CubeDeformation(0.0F))
		.texOffs(5, 89).addBox(0.0F, -20.0F, -12.0F, 0.0F, 13.0F, 26.0F, new CubeDeformation(0.0F))
		.texOffs(28, 59).addBox(-5.0F, -3.0F, -17.0F, 10.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(66, 39).addBox(-2.0F, -3.0F, -22.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(58, 59).addBox(0.0F, -3.0F, -35.0F, 0.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 2.0F));

		bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(-1, -1).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 6.0F, -6.0F, 0.7854F, 0.0F, 0.2618F));

		bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, -3).addBox(0.0F, 0.0F, 0.0F, 0.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 6.0F, -6.0F, 0.7854F, 0.0F, -0.2618F));

		bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(66, 47).addBox(-2.0F, 0.0F, -4.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -17.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition bone3 = bone.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(28, 39).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 7.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(52, -13).addBox(0.0F, -7.0F, 0.0F, 0.0F, 15.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 14.0F));

		bone3.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(0, 39).addBox(0.0F, -12.0F, -1.0F, 0.0F, 24.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 12.0F));

		bone.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(90, 41).addBox(0.0F, 0.0F, -1.0F, 12.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, -5.0F));

		bone.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(90, 41).mirror().addBox(-12.0F, 0.0F, -1.0F, 12.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, 2.0F, -5.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(EntitySailfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		OceanicClientUtil.animateHead(this.root, netHeadYaw, headPitch);
		this.root.zRot += Math.toRadians(entity.getRollAngle());
		this.animate(entity.dryAnimationState, SailfishAnimation.SAILFISH_DRY, ageInTicks);
		entity.eatingAnimationState.animate(this, SailfishAnimation.SAILFISH_EATING, ageInTicks);
		this.animateWalk(SailfishAnimation.SAILFISH_SWIM, limbSwing, limbSwingAmount, 3.5F, 2.5F);
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