package com.min01.oceanicrealms.block.model;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.block.animation.StarfishAnimation;
import com.min01.oceanicrealms.blockentity.AnimatableBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class ModelStarfish extends HierarchicalBlockModel<AnimatableBlockEntity>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OceanicRealms.MODID, "starfish"), "main");
	private final ModelPart root;

	public ModelStarfish(ModelPart root) 
	{
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(18, 11).addBox(-0.5F, -3.0F, -1.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 5).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(0.0F, -2.0F, -5.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(18, 15).addBox(0.5F, -3.0F, -1.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(18, 4).addBox(-3.0F, -1.0F, -2.5F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.01F))
		.texOffs(18, 7).addBox(-4.0F, -2.0F, -1.5F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.0F, 1.5F, 0.0F, -0.3927F, 0.0F));

		bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(12, 0).addBox(-1.2F, -1.0F, 0.8F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.01F))
		.texOffs(10, 5).addBox(-0.2F, -2.0F, 0.8F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.5F, 0.0F, -0.3927F, 0.0F));

		bone.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(10, 11).addBox(0.2F, -1.0F, 0.8F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 9).addBox(-0.8F, 0.0F, 0.8F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.5F, -1.0F, 0.5F, 0.0F, 0.3927F, 0.0F));

		bone.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(18, 9).addBox(0.0F, -1.0F, -1.5F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(8, 17).addBox(0.0F, 0.0F, -2.5F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(1.5F, -1.0F, 1.5F, 0.0F, 0.3927F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(AnimatableBlockEntity block, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(block.idleAnimationState, StarfishAnimation.STARFISH_IDLE, ageInTicks);
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