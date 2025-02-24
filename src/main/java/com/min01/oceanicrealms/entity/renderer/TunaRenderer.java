package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityTuna;
import com.min01.oceanicrealms.entity.model.ModelTuna;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TunaRenderer extends MobRenderer<EntityTuna, ModelTuna>
{
	public TunaRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelTuna(p_174304_.bakeLayer(ModelTuna.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityTuna p_115812_)
	{
		return new ResourceLocation(String.format("%s:textures/entity/tuna%d.png", OceanicRealms.MODID, p_115812_.getVariant()));
	}
}
