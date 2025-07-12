package com.min01.oceanicrealms.entity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityOarfishHead;
import com.min01.oceanicrealms.entity.model.ModelOarfishBody;
import com.min01.oceanicrealms.entity.model.ModelOarfishHead;
import com.min01.oceanicrealms.entity.model.ModelOarfishTail;
import com.min01.oceanicrealms.misc.KinematicChain.ChainSegment;
import com.min01.oceanicrealms.util.OceanicClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class OarfishHeadRenderer extends MobRenderer<EntityOarfishHead, ModelOarfishHead>
{
	public final ModelOarfishBody bodyModel;
	public final ModelOarfishTail tailModel;
	public OarfishHeadRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelOarfishHead(p_174304_.bakeLayer(ModelOarfishHead.LAYER_LOCATION)), 0.0F);
		this.bodyModel = new ModelOarfishBody(p_174304_.bakeLayer(ModelOarfishBody.LAYER_LOCATION));
		this.tailModel = new ModelOarfishTail(p_174304_.bakeLayer(ModelOarfishTail.LAYER_LOCATION));
	}
	
	@Override
	public void render(EntityOarfishHead p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_) 
	{
		super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
		ResourceLocation texture = new ResourceLocation(OceanicRealms.MODID, "textures/entity/oarfish_body.png");
		ResourceLocation texture2 = new ResourceLocation(OceanicRealms.MODID, "textures/entity/oarfish_tail.png");
		for(int i = 0; i < p_115455_.getMaxLength(); i++)
		{
			if(p_115455_.chain != null)
			{
		    	Vec3 camPos = OceanicClientUtil.MC.gameRenderer.getMainCamera().getPosition();
		        double x = Mth.lerp((double)p_115457_, p_115455_.xOld, p_115455_.getX());
		        double y = Mth.lerp((double)p_115457_, p_115455_.yOld, p_115455_.getY());
		        double z = Mth.lerp((double)p_115457_, p_115455_.zOld, p_115455_.getZ());
				ChainSegment segment = p_115455_.chain.getSegments()[Math.max(p_115455_.chain.getSegments().length - (i + 2), 0)];
				Vec3 pos = segment.position(p_115457_);
				Vec2 rot = segment.getRot(p_115457_);
				p_115458_.pushPose();
				p_115458_.translate(-(x - camPos.x), -(y - camPos.y), -(z - camPos.z));
	            p_115458_.translate(pos.x - camPos.x, pos.y - camPos.y, pos.z - camPos.z);
	            p_115458_.mulPose(Axis.ZP.rotationDegrees(180.0F));
	            p_115458_.mulPose(Axis.YP.rotationDegrees(180.0F + rot.y));
	            p_115458_.mulPose(Axis.XP.rotationDegrees(rot.x));
	            p_115458_.translate(0, -1.5F, 0);
	            if(i == p_115455_.getMaxLength() - 1)
	            {
		            this.tailModel.renderToBuffer(p_115458_, p_115459_.getBuffer(RenderType.entityCutoutNoCull(texture2)), p_115460_, getOverlayCoords(p_115455_, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
	            }
	            else
	            {
		            this.bodyModel.renderToBuffer(p_115458_, p_115459_.getBuffer(RenderType.entityCutoutNoCull(texture)), p_115460_, getOverlayCoords(p_115455_, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
	            }
				p_115458_.popPose();
			}
		}
	}

	@Override
	public ResourceLocation getTextureLocation(EntityOarfishHead p_115812_)
	{
		return new ResourceLocation(OceanicRealms.MODID, "textures/entity/oarfish_head.png");
	}
}
