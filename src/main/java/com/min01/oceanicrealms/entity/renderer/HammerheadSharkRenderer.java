package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityHammerheadShark;
import com.min01.oceanicrealms.entity.model.ModelHammerheadShark;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HammerheadSharkRenderer extends MobRenderer<EntityHammerheadShark, ModelHammerheadShark>
{
	public HammerheadSharkRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelHammerheadShark(p_174304_.bakeLayer(ModelHammerheadShark.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityHammerheadShark p_115812_)
	{
		return new ResourceLocation(OceanicRealms.MODID, "textures/entity/hammerhead_shark.png");
	}
}
