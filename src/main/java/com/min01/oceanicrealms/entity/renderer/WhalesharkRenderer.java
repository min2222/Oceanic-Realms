package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityWhaleshark;
import com.min01.oceanicrealms.entity.model.ModelWhaleshark;
import com.min01.oceanicrealms.misc.WormChain.Worm;
import com.min01.oceanicrealms.util.OceanicClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class WhalesharkRenderer extends MobRenderer<EntityWhaleshark, ModelWhaleshark>
{
	public WhalesharkRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelWhaleshark(p_174304_.bakeLayer(ModelWhaleshark.LAYER_LOCATION)), 0.5F);
	}
	
	@Override
	public void render(EntityWhaleshark p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_) 
	{
		super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
		this.renderSegment(p_115458_, p_115459_, p_115460_, this.model.back_body, p_115455_, p_115455_.wormBack, p_115457_);
	}
	
	public void renderSegment(PoseStack stack, MultiBufferSource source, int packedLight, ModelPart part, EntityWhaleshark entity, Worm worm, float partialTicks)
	{
		stack.pushPose();
		Vec3 pos = worm.position().subtract(entity.position());
		Vec2 rot = worm.getRot(partialTicks);
		stack.scale(-1.0F, -1.0F, 1.0F);
		stack.translate(-pos.x, -pos.y, pos.z);
		OceanicClientUtil.animateHead(part, rot.y + 180.0F, rot.x);
		part.zRot += Math.toRadians(entity.getRollAngle());
		part.render(stack, source.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(entity))), packedLight, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
		stack.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(EntityWhaleshark p_115812_)
	{
		return new ResourceLocation(OceanicRealms.MODID, "textures/entity/whaleshark.png");
	}
}
