package com.min01.oceanicrealms.blockentity.renderer;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.block.OceanicBlocks;
import com.min01.oceanicrealms.block.SeaAnemoneBlock;
import com.min01.oceanicrealms.block.StarfishBlock;
import com.min01.oceanicrealms.block.model.ModelSeaAnemone;
import com.min01.oceanicrealms.block.model.ModelStarfish;
import com.min01.oceanicrealms.blockentity.AnimatableBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;

public class AnimatableBlockRenderer implements BlockEntityRenderer<AnimatableBlockEntity>
{
    private final ModelSeaAnemone anemoneModel;
    private final ModelStarfish starfishModel;
    
	public AnimatableBlockRenderer(BlockEntityRendererProvider.Context p_172550_)
	{
		this.anemoneModel = new ModelSeaAnemone(p_172550_.bakeLayer(ModelSeaAnemone.LAYER_LOCATION));
		this.starfishModel = new ModelStarfish(p_172550_.bakeLayer(ModelStarfish.LAYER_LOCATION));
	}
	
	@Override
	public void render(AnimatableBlockEntity p_112307_, float p_112308_, PoseStack p_112309_, MultiBufferSource p_112310_, int p_112311_, int p_112312_) 
	{
		BlockState blockState = p_112307_.getBlockState();
		if(blockState.is(OceanicBlocks.SEA_ANEMONE.get()))
		{
			p_112309_.pushPose();
			p_112309_.translate(0.5F, 0.5F, 0.5F);
			this.rotate(blockState.getValue(SeaAnemoneBlock.FACE), blockState.getValue(SeaAnemoneBlock.FACING), p_112309_);
			p_112309_.scale(-1.0F, -1.0F, 1.0F);
			p_112309_.translate(0.0F, -1.0F, 0.0F);
			ResourceLocation texture = new ResourceLocation(String.format("%s:textures/block/sea_anemone%d.png", OceanicRealms.MODID, blockState.getValue(SeaAnemoneBlock.VARIANT)));
			if(blockState.getValue(SeaAnemoneBlock.IS_WARM))
			{
				texture = new ResourceLocation(String.format("%s:textures/block/warm_sea_anemone%d.png", OceanicRealms.MODID, blockState.getValue(SeaAnemoneBlock.VARIANT)));
			}
			VertexConsumer consumer = p_112310_.getBuffer(RenderType.entityCutoutNoCull(texture));
			this.anemoneModel.setupAnim(p_112307_, 0, 0, p_112308_ + p_112307_.tickCount, 0, 0);
			this.anemoneModel.renderToBuffer(p_112309_, consumer, p_112311_, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
			p_112309_.popPose();
		}
		if(blockState.is(OceanicBlocks.STARFISH.get()))
		{
			p_112309_.pushPose();
			p_112309_.translate(0.5F, 0.5F, 0.5F);
			this.rotate(blockState.getValue(StarfishBlock.FACE), blockState.getValue(StarfishBlock.FACING), p_112309_);
			p_112309_.scale(-1.0F, -1.0F, 1.0F);
			p_112309_.translate(0.0F, -1.0F, 0.0F);
			ResourceLocation texture = new ResourceLocation(String.format("%s:textures/block/starfish%d.png", OceanicRealms.MODID, blockState.getValue(StarfishBlock.VARIANT)));
			VertexConsumer consumer = p_112310_.getBuffer(RenderType.entityCutoutNoCull(texture));
			this.starfishModel.setupAnim(p_112307_, 0, 0, p_112308_ + p_112307_.tickCount, 0, 0);
			this.starfishModel.renderToBuffer(p_112309_, consumer, p_112311_, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
			p_112309_.popPose();
		}
	}
	
	public void rotate(AttachFace face, Direction direction, PoseStack poseStack)
	{
		switch(face)
		{
		case CEILING:
			switch(direction)
			{
			case DOWN:
				break;
			case EAST:
				poseStack.mulPose(Axis.XP.rotationDegrees(180));
				poseStack.mulPose(Axis.YP.rotationDegrees(90));
				break;
			case NORTH:
				poseStack.mulPose(Axis.XP.rotationDegrees(180));
				break;
			case SOUTH:
				poseStack.mulPose(Axis.XP.rotationDegrees(180));
				poseStack.mulPose(Axis.YP.rotationDegrees(180));
				break;
			case UP:
				break;
			case WEST:
				poseStack.mulPose(Axis.XP.rotationDegrees(180));
				poseStack.mulPose(Axis.YP.rotationDegrees(-90));
				break;
			default:
				break;
			}
			break;
		case FLOOR:
			switch(direction)
			{
			case DOWN:
				break;
			case EAST:
				poseStack.mulPose(Axis.YP.rotationDegrees(90));
				break;
			case NORTH:
				break;
			case SOUTH:
				poseStack.mulPose(Axis.YP.rotationDegrees(180));
				break;
			case UP:
				break;
			case WEST:
				poseStack.mulPose(Axis.YP.rotationDegrees(270));
				break;
			default:
				break;
			}
			break;
		case WALL:
			switch(direction)
			{
			case DOWN:
				break;
			case EAST:
				poseStack.mulPose(Axis.XP.rotationDegrees(90));
				poseStack.mulPose(Axis.ZP.rotationDegrees(-90));
				break;
			case NORTH:
				poseStack.mulPose(Axis.XP.rotationDegrees(-90));
				poseStack.mulPose(Axis.YP.rotationDegrees(180));
				break;
			case SOUTH:
				poseStack.mulPose(Axis.XP.rotationDegrees(90));
				break;
			case UP:
				break;
			case WEST:
				poseStack.mulPose(Axis.XP.rotationDegrees(90));
				poseStack.mulPose(Axis.ZP.rotationDegrees(90));
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}
}
