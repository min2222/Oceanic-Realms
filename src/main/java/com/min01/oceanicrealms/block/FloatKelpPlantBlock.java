package com.min01.oceanicrealms.block;

import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.KelpPlantBlock;

public class FloatKelpPlantBlock extends KelpPlantBlock
{
	public FloatKelpPlantBlock(Properties p_54300_) 
	{
		super(p_54300_);
	}
	
	@Override
	protected GrowingPlantHeadBlock getHeadBlock() 
	{
		return (GrowingPlantHeadBlock) OceanicBlocks.FLOAT_KELP.get();
	}
}
