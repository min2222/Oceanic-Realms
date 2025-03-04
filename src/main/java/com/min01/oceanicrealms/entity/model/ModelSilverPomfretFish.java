package com.min01.oceanicrealms.entity.model;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.animation.SilverPomfretFishAnimation;
import com.min01.oceanicrealms.entity.living.EntitySilverPomfretFish;
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

public class ModelSilverPomfretFish extends HierarchicalModel<EntitySilverPomfretFish>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OceanicRealms.MODID, "silverpomfret_fish"), "main");
	private final ModelPart root;

	public ModelSilverPomfretFish(ModelPart root)
	{
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(2, 14).addBox(0.0F, 3.0F, -1.0F, 0.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(2, 12).addBox(0.0F, -5.0F, -1.0F, 0.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.0F, -3.0F, -5.0F, 2.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

		bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(20, -5).addBox(0.0F, -3.0F, 2.0F, 0.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		bone.addOrReplaceChild("right", CubeListBuilder.create().texOffs(22, 19).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.0F, -1.0F, 0.2618F, 0.2618F, 0.0F));

		bone.addOrReplaceChild("left", CubeListBuilder.create().texOffs(22, 19).mirror().addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 1.0F, -1.0F, 0.2618F, -0.2618F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(EntitySilverPomfretFish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		OceanicClientUtil.animateHead(this.root, netHeadYaw, headPitch);
		this.root.zRot += Math.toRadians(entity.getRollAngle());
		this.animate(entity.dryAnimationState, SilverPomfretFishAnimation.SILVER_POMFRET_FISH_DRY, ageInTicks);
		this.animateWalk(SilverPomfretFishAnimation.SILVER_POMFRET_FISH_SWIM, limbSwing, limbSwingAmount, 3.5F, 2.5F);
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