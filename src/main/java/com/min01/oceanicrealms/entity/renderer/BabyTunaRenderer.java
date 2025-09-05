package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityTuna;
import com.min01.oceanicrealms.entity.model.ModelBabyTuna;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BabyTunaRenderer extends MobRenderer<EntityTuna, ModelBabyTuna>
{
	public BabyTunaRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelBabyTuna(p_174304_.bakeLayer(ModelBabyTuna.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityTuna p_115812_)
	{
		return new ResourceLocation(String.format("%s:textures/entity/baby_tuna%d.png", OceanicRealms.MODID, p_115812_.getVariant()));
	}
}
