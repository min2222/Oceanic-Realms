package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntitySailfish;
import com.min01.oceanicrealms.entity.model.ModelBabySailfish;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BabySailfishRenderer extends MobRenderer<EntitySailfish, ModelBabySailfish>
{
	public BabySailfishRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelBabySailfish(p_174304_.bakeLayer(ModelBabySailfish.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntitySailfish p_115812_)
	{
		return new ResourceLocation(String.format("%s:textures/entity/baby_sailfish%d.png", OceanicRealms.MODID, p_115812_.getVariant()));
	}
}
