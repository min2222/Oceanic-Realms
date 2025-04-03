package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityWhaleshark;
import com.min01.oceanicrealms.entity.model.ModelWhaleshark;

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
	public ResourceLocation getTextureLocation(EntityWhaleshark p_115812_)
	{
		return new ResourceLocation(OceanicRealms.MODID, "textures/entity/whaleshark.png");
	}
}
