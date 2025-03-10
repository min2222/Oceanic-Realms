package com.min01.oceanicrealms.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.KelpBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GiantKelpBlock extends KelpBlock
{
	public GiantKelpBlock(Properties p_54300_) 
	{
		super(p_54300_);
	}
	
	@Override
	public VoxelShape getShape(BlockState p_53880_, BlockGetter p_53881_, BlockPos p_53882_, CollisionContext p_53883_) 
	{
		return Shapes.block();
	}
	
	@Override
	protected Block getBodyBlock() 
	{
		return OceanicBlocks.GIANT_KELP_PLANT.get();
	}
}
