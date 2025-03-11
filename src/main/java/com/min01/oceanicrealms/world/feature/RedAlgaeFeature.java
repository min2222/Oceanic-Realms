package com.min01.oceanicrealms.world.feature;

import com.min01.oceanicrealms.block.OceanicBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class RedAlgaeFeature extends Feature<NoneFeatureConfiguration>
{
	public RedAlgaeFeature(Codec<NoneFeatureConfiguration> p_65786_)
	{
		super(p_65786_);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> p_159749_) 
	{
		RandomSource random = p_159749_.random();
		WorldGenLevel level = p_159749_.level();
		BlockPos pos = p_159749_.origin();
		BlockState state = level.getBlockState(pos.above(2));
		BlockState state2 = level.getBlockState(pos.above(3));
		if(random.nextFloat() <= this.getChance(state, state2))
		{
			if(level.getBlockState(pos.below()).isCollisionShapeFullBlock(level, pos.below()))
			{
				level.setBlock(pos, OceanicBlocks.RED_ALGAE.get().defaultBlockState(), 2);
				return true;
			}
		}
		return false;
	}
	
	public float getChance(BlockState state, BlockState state2)
	{
		if(state.isAir() || state2.isAir())
		{
			return 0.5F;
		}
		return 0.3F;
	}
}
