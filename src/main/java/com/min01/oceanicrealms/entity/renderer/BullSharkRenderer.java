package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityBullShark;
import com.min01.oceanicrealms.entity.model.ModelBullShark;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BullSharkRenderer extends MobRenderer<EntityBullShark, ModelBullShark>
{
	public BullSharkRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelBullShark(p_174304_.bakeLayer(ModelBullShark.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityBullShark p_115812_)
	{
		return new ResourceLocation(String.format("%s:textures/entity/bull_shark%d.png", OceanicRealms.MODID, p_115812_.getVariant()));
	}
}
