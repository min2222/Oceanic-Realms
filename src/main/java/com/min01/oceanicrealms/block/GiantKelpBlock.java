package com.min01.oceanicrealms.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.KelpBlock;

public class GiantKelpBlock extends KelpBlock
{
	public GiantKelpBlock(Properties p_54300_) 
	{
		super(p_54300_);
	}
	
	@Override
	protected Block getBodyBlock() 
	{
		return OceanicBlocks.GIANT_KELP_PLANT.get();
	}
}
