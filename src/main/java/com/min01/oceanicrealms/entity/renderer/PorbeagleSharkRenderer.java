package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityPorbeagleShark;
import com.min01.oceanicrealms.entity.model.ModelPorbeagleShark;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PorbeagleSharkRenderer extends MobRenderer<EntityPorbeagleShark, ModelPorbeagleShark>
{
	public PorbeagleSharkRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelPorbeagleShark(p_174304_.bakeLayer(ModelPorbeagleShark.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityPorbeagleShark p_115812_)
	{
		return new ResourceLocation(String.format("%s:textures/entity/porbeagle_shark%d.png", OceanicRealms.MODID, p_115812_.getVariant()));
	}
}
