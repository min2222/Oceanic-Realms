package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityGreatHammerheadShark;
import com.min01.oceanicrealms.entity.model.ModelGreatHammerheadShark;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GreatHammerheadSharkRenderer extends MobRenderer<EntityGreatHammerheadShark, ModelGreatHammerheadShark>
{
	public GreatHammerheadSharkRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelGreatHammerheadShark(p_174304_.bakeLayer(ModelGreatHammerheadShark.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityGreatHammerheadShark p_115812_)
	{
		return new ResourceLocation(OceanicRealms.MODID, "textures/entity/great_hammerhead_shark.png");
	}
}
