package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityOarfishHead;
import com.min01.oceanicrealms.entity.model.ModelOarfishHead;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class OarfishHeadRenderer extends MobRenderer<EntityOarfishHead, ModelOarfishHead>
{
	public OarfishHeadRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelOarfishHead(p_174304_.bakeLayer(ModelOarfishHead.LAYER_LOCATION)), 0.0F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityOarfishHead p_115812_)
	{
		return new ResourceLocation(OceanicRealms.MODID, "textures/entity/oarfish_head.png");
	}
}
