package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityLionfish;
import com.min01.oceanicrealms.entity.model.ModelLionfish;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LionfishRenderer extends MobRenderer<EntityLionfish, ModelLionfish>
{
	public LionfishRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelLionfish(p_174304_.bakeLayer(ModelLionfish.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityLionfish p_115812_)
	{
		return new ResourceLocation(OceanicRealms.MODID, "textures/entity/lionfish.png");
	}
}
