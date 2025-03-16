package com.min01.oceanicrealms.entity.model;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.animation.OarfishAnimation;
import com.min01.oceanicrealms.entity.living.EntityOarfishHead;
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

public class ModelOarfishHead extends HierarchicalModel<EntityOarfishHead>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OceanicRealms.MODID, "oarfish_head"), "main");
	private final ModelPart root;

	public ModelOarfishHead(ModelPart root) 
	{
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -8.4507F, -16.5943F, 10.0F, 14.0F, 21.0F, new CubeDeformation(0.0F))
		.texOffs(0, -4).addBox(0.0F, -40.4507F, -16.5943F, 0.0F, 32.0F, 39.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.0F, -2.4507F, -18.5943F, 6.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.5493F, 5.5943F));

		bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 40).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 20.0F, 33.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 5.5493F, -7.5943F, 0.1745F, 0.0F, -0.1745F));

		bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(32, 40).addBox(0.0F, 0.0F, 0.0F, 0.0F, 20.0F, 33.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 5.5493F, -7.5943F, 0.1745F, 0.0F, 0.1745F));

		bone.addOrReplaceChild("right", CubeListBuilder.create().texOffs(-5, 74).mirror().addBox(0.0F, 0.0F, -2.0F, 9.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 4.5493F, 0.4057F));

		bone.addOrReplaceChild("left", CubeListBuilder.create().texOffs(-5, 74).addBox(-9.0F, 0.0F, -2.0F, 9.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 4.5493F, 0.4057F));

		return LayerDefinition.create(meshdefinition, 100, 100);
	}

	@Override
	public void setupAnim(EntityOarfishHead entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		OceanicClientUtil.animateHead(this.root, netHeadYaw, headPitch);
		this.animateWalk(OarfishAnimation.OARFISH_SWIM, limbSwing, limbSwingAmount, 5.5F, 2.5F);
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