package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityCrab;
import com.min01.oceanicrealms.entity.model.ModelCrab;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CrabRenderer extends MobRenderer<EntityCrab, ModelCrab>
{
	public CrabRenderer(Context p_174304_) 
	{
		super(p_174304_, new ModelCrab(p_174304_.bakeLayer(ModelCrab.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityCrab p_114482_) 
	{
		return new ResourceLocation(String.format("%s:textures/entity/crab%d.png", OceanicRealms.MODID, p_114482_.getVariant()));
	}
}
