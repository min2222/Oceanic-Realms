package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityDolphinfish;
import com.min01.oceanicrealms.entity.model.ModelBabyDolphinfish;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BabyDolphinfishRenderer extends MobRenderer<EntityDolphinfish, ModelBabyDolphinfish>
{
	public BabyDolphinfishRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelBabyDolphinfish(p_174304_.bakeLayer(ModelBabyDolphinfish.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityDolphinfish p_115812_)
	{
		return new ResourceLocation(OceanicRealms.MODID, "textures/entity/baby_dolphinfish.png");
	}
}
