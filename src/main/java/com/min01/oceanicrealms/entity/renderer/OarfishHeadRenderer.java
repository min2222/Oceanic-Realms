package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityOarfishHead;
import com.min01.oceanicrealms.entity.model.ModelOarfishBody;
import com.min01.oceanicrealms.entity.model.ModelOarfishHead;
import com.min01.oceanicrealms.entity.model.ModelOarfishTail;
import com.min01.oceanicrealms.misc.WormChain.Worm;
import com.min01.oceanicrealms.util.OceanicClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
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
	public void render(EntityOarfishHead p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_)
	{
		super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
		if(p_115455_.worms != null)
		{
			for(int i = 0; i < p_115455_.worms.length; i++)
			{
				Worm worm = p_115455_.worms[i];
				if(worm != null)
				{
					if(i == p_115455_.worms.length - 1)
					{
						ModelOarfishTail tail = new ModelOarfishTail(OceanicClientUtil.MC.getEntityModels().bakeLayer(ModelOarfishTail.LAYER_LOCATION));
						OceanicClientUtil.renderWormSegment(p_115458_, p_115459_, p_115460_, tail.root(), p_115455_, worm, p_115457_, new ResourceLocation(OceanicRealms.MODID, "textures/entity/oarfish_tail.png"));
					}
					else
					{
						ModelOarfishBody body = new ModelOarfishBody(OceanicClientUtil.MC.getEntityModels().bakeLayer(ModelOarfishBody.LAYER_LOCATION));
						OceanicClientUtil.renderWormSegment(p_115458_, p_115459_, p_115460_, body.root(), p_115455_, worm, p_115457_, new ResourceLocation(OceanicRealms.MODID, "textures/entity/oarfish_body.png"));
					}
				}
			}
		}
	}

	@Override
	public ResourceLocation getTextureLocation(EntityOarfishHead p_115812_)
	{
		return new ResourceLocation(OceanicRealms.MODID, "textures/entity/oarfish_head.png");
	}
}
