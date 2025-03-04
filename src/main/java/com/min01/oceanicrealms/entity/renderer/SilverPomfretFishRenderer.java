package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntitySilverPomfretFish;
import com.min01.oceanicrealms.entity.model.ModelSilverPomfretFish;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SilverPomfretFishRenderer extends MobRenderer<EntitySilverPomfretFish, ModelSilverPomfretFish>
{
	public SilverPomfretFishRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelSilverPomfretFish(p_174304_.bakeLayer(ModelSilverPomfretFish.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntitySilverPomfretFish p_115812_)
	{
		return new ResourceLocation(String.format("%s:textures/entity/silver_pomfret_fish%d.png", OceanicRealms.MODID, p_115812_.getVariant()));
	}
}
