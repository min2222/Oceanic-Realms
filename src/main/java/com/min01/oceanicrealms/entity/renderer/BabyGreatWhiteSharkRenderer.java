package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityGreatWhiteShark;
import com.min01.oceanicrealms.entity.model.ModelBabyGreatWhiteShark;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BabyGreatWhiteSharkRenderer extends MobRenderer<EntityGreatWhiteShark, ModelBabyGreatWhiteShark>
{
	public BabyGreatWhiteSharkRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelBabyGreatWhiteShark(p_174304_.bakeLayer(ModelBabyGreatWhiteShark.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityGreatWhiteShark p_115812_)
	{
		return new ResourceLocation(OceanicRealms.MODID, "textures/entity/baby_great_white_shark.png");
	}
}
