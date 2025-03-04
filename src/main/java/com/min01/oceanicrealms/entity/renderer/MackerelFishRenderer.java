package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityMackerelFish;
import com.min01.oceanicrealms.entity.model.ModelMackerelFish;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MackerelFishRenderer extends MobRenderer<EntityMackerelFish, ModelMackerelFish>
{
	public MackerelFishRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelMackerelFish(p_174304_.bakeLayer(ModelMackerelFish.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityMackerelFish p_115812_)
	{
		return new ResourceLocation(OceanicRealms.MODID, "textures/entity/mackerel_fish.png");
	}
}
