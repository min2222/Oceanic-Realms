package com.min01.oceanicrealms.entity.model;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.animation.DolphinfishAnimation;
import com.min01.oceanicrealms.entity.living.EntityDolphinfish;
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

public class ModelDolphinfish extends HierarchicalModel<EntityDolphinfish>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OceanicRealms.MODID, "dolphinfish"), "main");
	private final ModelPart root;

	public ModelDolphinfish(ModelPart root) 
	{
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -6.0F, -11.0F, 8.0F, 11.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(0, 42).addBox(0.0F, -13.0F, -12.0F, 0.0F, 8.0F, 23.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, -3).mirror().addBox(0.0F, 0.0F, -2.0F, 0.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 5.0F, 2.0F, -0.3491F, 0.0F, -0.2618F));

		bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, -3).addBox(0.0F, 0.0F, -2.0F, 0.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 5.0F, 2.0F, -0.3491F, 0.0F, 0.2618F));

		PartDefinition bone6 = bone.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(27, 37).addBox(-3.0F, -4.0F, -14.0F, 6.0F, 9.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(0, 61).addBox(0.0F, 3.0F, -15.0F, 0.0F, 6.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -11.0F));

		PartDefinition bone2 = bone6.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(5, 40).addBox(-2.0F, -2.0F, -6.0F, 4.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -14.0F));

		bone2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 40).addBox(0.0F, -5.0F, -13.0F, 0.0F, 11.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.0F));

		bone.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(45, 1).addBox(0.0F, -1.0F, -6.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 1.0F, 2.0F, -0.2182F, 0.0873F, 0.0F));

		bone.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(45, 1).mirror().addBox(0.0F, -1.0F, -6.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, 1.0F, 2.0F, -0.2182F, -0.0873F, 0.0F));

		return LayerDefinition.create(meshdefinition, 100, 100);
	}

	@Override
	public void setupAnim(EntityDolphinfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		OceanicClientUtil.animateHead(this.root, netHeadYaw, headPitch);
		this.root.zRot += Math.toRadians(entity.getRollAngle());
		this.animate(entity.dryAnimationState, DolphinfishAnimation.DOLPHINFISH_DRY, ageInTicks);
		this.animateWalk(DolphinfishAnimation.DOLPHINFISH_SWIM, limbSwing, limbSwingAmount, 3.5F, 2.5F);
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