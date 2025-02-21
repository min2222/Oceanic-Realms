package com.min01.oceanicrealms.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.KelpPlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FloatKelpPlantBlock extends KelpPlantBlock
{
	public static final BooleanProperty IS_TOP = BooleanProperty.create("is_top");
	protected static final VoxelShape AABB = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	
	public FloatKelpPlantBlock(Properties p_54300_) 
	{
		super(p_54300_);
	}
	
	@Override
	protected GrowingPlantHeadBlock getHeadBlock() 
	{
		return (GrowingPlantHeadBlock) OceanicBlocks.FLOAT_KELP.get();
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext p_53868_)
	{
		BlockState blockState = p_53868_.getLevel().getBlockState(p_53868_.getClickedPos().relative(this.growthDirection));
		return super.getStateForPlacement(p_53868_).setValue(IS_TOP, blockState.is(OceanicBlocks.FLOAT_KELP.get()));
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> p_49915_) 
	{
		p_49915_.add(IS_TOP);
	}
	
	@Override
	public VoxelShape getShape(BlockState p_53880_, BlockGetter p_53881_, BlockPos p_53882_, CollisionContext p_53883_) 
	{
		return AABB;
	}
}
