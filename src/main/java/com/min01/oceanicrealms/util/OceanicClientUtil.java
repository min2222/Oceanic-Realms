package com.min01.oceanicrealms.util;

import com.min01.oceanicrealms.misc.WormChain.Worm;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class OceanicClientUtil 
{
	public static final Minecraft MC = Minecraft.getInstance();
	
	public static void renderWormSegment(PoseStack stack, MultiBufferSource source, int packedLight, ModelPart part, LivingEntity entity, Worm worm, float partialTicks, ResourceLocation texture)
	{
		stack.pushPose();
		Vec3 pos = worm.position(partialTicks);
		Vec2 rot = worm.getRot(partialTicks);
		stack.scale(-1.0F, -1.0F, 1.0F);
		stack.translate(-pos.x, -pos.y, pos.z);
		animateHead(part, rot.y + 180.0F, rot.x);
		part.render(stack, source.getBuffer(RenderType.entityCutoutNoCull(texture)), packedLight, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
		stack.popPose();
	}
	
	public static void animateHead(ModelPart head, float netHeadYaw, float headPitch)
	{
		head.yRot += Math.toRadians(netHeadYaw);
		head.xRot += Math.toRadians(headPitch);
	}
}
