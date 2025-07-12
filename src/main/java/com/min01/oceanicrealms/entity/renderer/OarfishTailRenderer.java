package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityOarfishTail;
import com.min01.oceanicrealms.entity.model.ModelOarfishTail;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class OarfishTailRenderer extends MobRenderer<EntityOarfishTail, ModelOarfishTail>
{
	public OarfishTailRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelOarfishTail(p_174304_.bakeLayer(ModelOarfishTail.LAYER_LOCATION)), 0.0F);
	}
	
	@Override
	public void render(EntityOarfishTail p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_) 
	{
		
	}

	@Override
	public ResourceLocation getTextureLocation(EntityOarfishTail p_115812_)
	{
		return new ResourceLocation(OceanicRealms.MODID, "textures/entity/oarfish_tail.png");
	}
}
