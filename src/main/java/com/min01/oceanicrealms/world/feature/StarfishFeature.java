package com.min01.oceanicrealms.world.feature;

import com.min01.oceanicrealms.block.OceanicBlocks;
import com.min01.oceanicrealms.block.StarfishBlock;
import com.min01.oceanicrealms.misc.OceanicTags;
import com.min01.oceanicrealms.world.OceanicBiomes;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class StarfishFeature extends Feature<NoneFeatureConfiguration>
{
	public StarfishFeature(Codec<NoneFeatureConfiguration> p_65786_)
	{
		super(p_65786_);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> p_159749_) 
	{
		RandomSource random = p_159749_.random();
		WorldGenLevel level = p_159749_.level();
		BlockPos pos = p_159749_.origin();
		Holder<Biome> biome = level.getBiome(pos);
		BlockState state = level.getBlockState(pos.below());
		if(random.nextFloat() <= this.getChance(biome, state))
		{
			if(state.isCollisionShapeFullBlock(level, pos.below()))
			{
				level.setBlock(pos, OceanicBlocks.STARFISH.get().defaultBlockState().setValue(StarfishBlock.WATERLOGGED, false).setValue(StarfishBlock.VARIANT, random.nextInt(1, 10)), 2);
				return true;
			}
		}
		return false;
	}
	
	public float getChance(Holder<Biome> biome, BlockState state)
	{
		if(biome.is(OceanicTags.OceanicBiomes.COLD_OCEANS))
		{
			return 0.1F;
		}
		else if(biome.is(OceanicTags.OceanicBiomes.WARM_OCEANS))
		{
			if(biome.is(OceanicBiomes.SANDSTONE_OCEAN) || state.is(BlockTags.CORAL_BLOCKS))
			{
				return 0.5F;
			}
			return 0.3F;
		}
		else if(biome.is(Biomes.BEACH))
		{
			return 0.4F;
		}
		return 0.2F;
	}
}
