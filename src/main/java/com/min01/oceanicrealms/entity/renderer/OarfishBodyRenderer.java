package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityOarfishBody;
import com.min01.oceanicrealms.entity.model.ModelOarfishBody;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class OarfishBodyRenderer extends MobRenderer<EntityOarfishBody, ModelOarfishBody>
{
	public OarfishBodyRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelOarfishBody(p_174304_.bakeLayer(ModelOarfishBody.LAYER_LOCATION)), 0.0F);
	}
	
	@Override
	public void render(EntityOarfishBody p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_)
	{
		
	}

	@Override
	public ResourceLocation getTextureLocation(EntityOarfishBody p_115812_)
	{
		return new ResourceLocation(OceanicRealms.MODID, "textures/entity/oarfish_body.png");
	}
}
