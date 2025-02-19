package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityCrab;
import com.min01.oceanicrealms.entity.model.ModelCrab;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CrabRenderer extends MobRenderer<EntityCrab, ModelCrab>
{
	public CrabRenderer(Context p_174304_) 
	{
		super(p_174304_, new ModelCrab(p_174304_.bakeLayer(ModelCrab.LAYER_LOCATION)), 0.3F);
	}
	
	@Override
	protected void scale(EntityCrab p_115314_, PoseStack p_115315_, float p_115316_) 
	{
		this.shadowRadius = 0.3F * p_115314_.getSize();
		p_115315_.scale(p_115314_.getSize(), p_115314_.getSize(), p_115314_.getSize());
	}
	
	@Override
	protected int getBlockLightLevel(EntityCrab p_114496_, BlockPos p_114497_) 
	{
		return p_114496_.isInWall() ? 15 :  super.getBlockLightLevel(p_114496_, p_114497_);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityCrab p_114482_) 
	{
		return new ResourceLocation(String.format("%s:textures/entity/crab%d.png", OceanicRealms.MODID, p_114482_.getVariant()));
	}
}
