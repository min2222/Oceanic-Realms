package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityDolphinfish;
import com.min01.oceanicrealms.entity.model.ModelDolphinfish;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DolphinfishRenderer extends MobRenderer<EntityDolphinfish, ModelDolphinfish>
{
	private final BabyDolphinfishRenderer babyRenderer;
	
	public DolphinfishRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelDolphinfish(p_174304_.bakeLayer(ModelDolphinfish.LAYER_LOCATION)), 0.5F);
		this.babyRenderer = new BabyDolphinfishRenderer(p_174304_);
	}
	
	@Override
	public void render(EntityDolphinfish p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_)
	{
		if(p_115455_.isBaby())
		{
			this.babyRenderer.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
		}
		else
		{
			super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
		}
	}

	@Override
	public ResourceLocation getTextureLocation(EntityDolphinfish p_115812_)
	{
		return new ResourceLocation(OceanicRealms.MODID, "textures/entity/dolphinfish.png");
	}
}
