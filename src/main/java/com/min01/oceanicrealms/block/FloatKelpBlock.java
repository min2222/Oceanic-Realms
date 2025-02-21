package com.min01.oceanicrealms.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.KelpBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FloatKelpBlock extends KelpBlock
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	protected static final VoxelShape AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D);
	
	public FloatKelpBlock(Properties p_54300_) 
	{
		super(p_54300_);
	}
	
	@Override
	protected Block getBodyBlock() 
	{
		return OceanicBlocks.FLOAT_KELP_PLANT.get();
	}
	
	@Override
	public VoxelShape getShape(BlockState p_53880_, BlockGetter p_53881_, BlockPos p_53882_, CollisionContext p_53883_) 
	{
		return AABB;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext p_54302_)
	{
		FluidState fluidState = p_54302_.getLevel().getFluidState(p_54302_.getClickedPos());
		BlockState state = p_54302_.getLevel().getBlockState(p_54302_.getClickedPos());
		boolean flag = fluidState.is(FluidTags.WATER) && fluidState.getAmount() == 8;
		boolean flag2 = state.isAir() && p_54302_.getLevel().getFluidState(p_54302_.getClickedPos().below()).is(FluidTags.WATER);
		return flag || flag2 ? this.getStateForPlant(p_54302_).setValue(WATERLOGGED, fluidState.is(FluidTags.WATER)) : null;
	}
	
	public BlockState getStateForPlant(BlockPlaceContext p_53868_)
	{
		BlockState blockstate = p_53868_.getLevel().getBlockState(p_53868_.getClickedPos().relative(this.growthDirection));
		return !blockstate.is(this.getHeadBlock()) && !blockstate.is(this.getBodyBlock()) ? this.getStateForPlacement(p_53868_.getLevel()) : this.getBodyBlock().defaultBlockState();
	}
	
	@Override
	protected boolean canGrowInto(BlockState p_54321_)
	{
		return p_54321_.is(Blocks.WATER);
	}
	
    @Override
    public FluidState getFluidState(BlockState p_152045_)
    {
    	return p_152045_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : Fluids.EMPTY.defaultFluidState();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_152043_)
    {
    	super.createBlockStateDefinition(p_152043_);
    	p_152043_.add(WATERLOGGED);
    }
}
