package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityWhaleshark;
import com.min01.oceanicrealms.entity.model.ModelWhaleshark;
import com.min01.oceanicrealms.util.OceanicClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

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
		ResourceLocation texture = this.getTextureLocation(p_115455_);
		OceanicClientUtil.renderWormSegment(p_115458_, p_115459_, p_115460_, this.model.back_body, p_115455_, p_115455_.worm, p_115457_, texture);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityWhaleshark p_115812_)
	{
		return new ResourceLocation(OceanicRealms.MODID, "textures/entity/whaleshark.png");
	}
}
