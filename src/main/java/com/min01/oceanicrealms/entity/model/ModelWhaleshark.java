package com.min01.oceanicrealms.entity.model;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.animation.WhalesharkAnimation;
import com.min01.oceanicrealms.entity.living.EntityWhaleshark;
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

public class ModelWhaleshark extends HierarchicalModel<EntityWhaleshark>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OceanicRealms.MODID, "whaleshark"), "main");
	private final ModelPart root;
	public final ModelPart bone;
	public final ModelPart back;
	public final ModelPart back_body;

	public ModelWhaleshark(ModelPart root) 
	{
		this.root = root.getChild("root");
		this.bone = this.root.getChild("bone");
		this.back = this.root.getChild("back");
		this.back_body = this.back.getChild("back_body");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-24.0F, -18.0F, -22.0F, 48.0F, 36.0F, 45.0F, new CubeDeformation(0.0F))
		.texOffs(260, 84).addBox(-21.0F, -15.0F, -59.0F, 42.0F, 28.0F, 37.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -18.0F, -1.0F));

		bone.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(36, 289).addBox(0.0F, -2.0F, -7.0F, 40.0F, 3.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(24.0F, 11.0F, 4.0F, 0.1309F, -0.1309F, 0.4363F));

		bone.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(36, 289).mirror().addBox(-40.0F, -2.0F, -7.0F, 40.0F, 3.0F, 19.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-24.0F, 11.0F, 4.0F, 0.1309F, 0.1309F, -0.4363F));

		PartDefinition back = root.addOrReplaceChild("back", CubeListBuilder.create(), PartPose.offset(0.0F, -18.0F, -1.0F));

		PartDefinition back_body = back.addOrReplaceChild("back_body", CubeListBuilder.create(), PartPose.offset(0.0F, 10.0F, -32.0F));

		PartDefinition bone2 = back_body.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 142).addBox(-16.0F, -14.0F, 0.0F, 32.0F, 28.0F, 38.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 9.0F));

		bone2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 208).addBox(-2.0F, -23.0F, 1.0F, 4.0F, 22.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -13.0F, 20.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(141, 142).addBox(-8.0F, -11.0F, 0.0F, 16.0F, 19.0F, 46.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 38.0F));

		bone3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(158, 118).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 22.0F, 0.1745F, 0.0F, 0.0F));

		bone3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(158, 118).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, 20.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition bone4 = bone3.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 31.0F));

		bone4.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(158, 81).addBox(-2.0F, -14.0F, -20.0F, 4.0F, 14.0F, 23.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 26.8492F, 26.8492F, -0.7854F, 0.0F, 0.0F));

		bone4.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(186, 0).addBox(-2.0F, -53.0F, -17.0F, 4.0F, 53.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, 12.0F, -0.7854F, 0.0F, 0.0F));

		bone2.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(24, 358).addBox(-12.0F, -1.0F, -5.0F, 13.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.0F, 9.0F, 27.0F, 0.0F, 0.3927F, -0.5236F));

		bone2.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(24, 358).mirror().addBox(-1.0F, -1.0F, -5.0F, 13.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(17.0F, 9.0F, 27.0F, 0.0F, -0.3927F, 0.5236F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}

	@Override
	public void setupAnim(EntityWhaleshark entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		OceanicClientUtil.animateHead(this.bone, netHeadYaw, headPitch);
		//this.root.zRot += Math.toRadians(entity.getRollAngle());
		this.back.visible = false;
		this.animateWalk(WhalesharkAnimation.WHALESHARK_SWIM, limbSwing, limbSwingAmount, 2.5F, 2.5F);
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