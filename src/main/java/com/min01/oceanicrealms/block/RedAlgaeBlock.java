package com.min01.oceanicrealms.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseCoralPlantTypeBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RedAlgaeBlock extends BaseCoralPlantTypeBlock 
{
	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 15.0D, 14.0D);
	public RedAlgaeBlock(BlockBehaviour.Properties p_52176_) 
	{
		super(p_52176_);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext p_49163_) 
	{
		FluidState fluidstate = p_49163_.getLevel().getFluidState(p_49163_.getClickedPos());
		return fluidstate.is(FluidTags.WATER) ? super.getStateForPlacement(p_49163_) : null;
	}

	@Override
	public BlockState updateShape(BlockState p_52183_, Direction p_52184_, BlockState p_52185_, LevelAccessor p_52186_, BlockPos p_52187_, BlockPos p_52188_) 
	{
		if(p_52184_ == Direction.DOWN && !p_52183_.canSurvive(p_52186_, p_52187_)) 
		{
			return Blocks.AIR.defaultBlockState();
		} 
		else 
		{
			this.tryScheduleDieTick(p_52183_, p_52186_, p_52187_);
			if(p_52183_.getValue(WATERLOGGED))
			{
				p_52186_.scheduleTick(p_52187_, Fluids.WATER, Fluids.WATER.getTickDelay(p_52186_));
			}
			return super.updateShape(p_52183_, p_52184_, p_52185_, p_52186_, p_52187_, p_52188_);
		}
	}

	@Override
	public VoxelShape getShape(BlockState p_52190_, BlockGetter p_52191_, BlockPos p_52192_, CollisionContext p_52193_) 
	{
		return SHAPE;
	}
}
