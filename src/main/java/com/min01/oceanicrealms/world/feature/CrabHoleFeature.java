package com.min01.oceanicrealms.world.feature;

import com.min01.oceanicrealms.block.OceanicBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class CrabHoleFeature extends Feature<NoneFeatureConfiguration>
{
	public CrabHoleFeature(Codec<NoneFeatureConfiguration> p_65786_)
	{
		super(p_65786_);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> p_159749_) 
	{
		RandomSource random = p_159749_.random();
		WorldGenLevel level = p_159749_.level();
		BlockPos pos = p_159749_.origin();
		if(random.nextBoolean() && level.getBlockState(pos.below()).is(Blocks.SAND))
		{
			level.setBlock(pos.below(), OceanicBlocks.CRAB_HOLE.get().defaultBlockState(), 2);
			return true;
		}
		return false;
	}
}
