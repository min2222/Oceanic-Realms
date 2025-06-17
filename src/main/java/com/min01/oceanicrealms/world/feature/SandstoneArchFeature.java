package com.min01.oceanicrealms.world.feature;

import com.min01.oceanicrealms.block.OceanicBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SandstoneArchFeature extends Feature<NoneFeatureConfiguration>
{
	public SandstoneArchFeature(Codec<NoneFeatureConfiguration> p_65786_) 
	{
		super(p_65786_);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
	    WorldGenLevel level = context.level();
	    BlockPos origin = context.origin();
	    BlockState state = OceanicBlocks.SEDIMENTARY_SANDSTONE.get().defaultBlockState();
	    RandomSource random = level.getRandom();

	    int archRadius = 4;
	    int archHalfWidth = 2;
	    int archThickness = 3;
	    int archLength = 15;

	    int cx = origin.getX();
	    int cy = level.getHeightmapPos(Heightmap.Types.OCEAN_FLOOR_WG, origin).getY();
	    int cz = origin.getZ();

	    double angle = random.nextDouble() * 2 * Math.PI;
	    double cos = Math.cos(angle);
	    double sin = Math.sin(angle);

	    for(double archStep = -archLength / 2.0; archStep <= archLength / 2.0; archStep += 0.25)
	    {
	        double normX = archStep * archRadius / (archLength / 2.0);
	        double archY = Math.sqrt(Math.max(0, archRadius * archRadius - normX * normX));
	        int y = cy + (int)Math.round(archY);
	        for(int dx = -archHalfWidth; dx <= archHalfWidth; dx++) 
	        {
	            for(int dy = 0; dy < archThickness; dy++) 
	            {
	                int worldX = cx + (int)Math.round(archStep * cos - dx * sin);
	                int worldZ = cz + (int)Math.round(archStep * sin + dx * cos);
	                int worldY = y + dy;
	                BlockPos pos = new BlockPos(worldX, worldY, worldZ);
	                if(level.getBlockState(pos).is(Blocks.WATER))
	                {
	                    level.setBlock(pos, state, 2);
	                }
	            }
	        }
	    }
	    return true;
	}
}
