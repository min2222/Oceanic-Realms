package com.min01.oceanicrealms.world.feature;

import com.min01.oceanicrealms.block.ClamBlock;
import com.min01.oceanicrealms.block.OceanicBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class ClamFeature extends Feature<NoneFeatureConfiguration>
{
	public ClamFeature(Codec<NoneFeatureConfiguration> p_65786_)
	{
		super(p_65786_);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> p_159749_) 
	{
		RandomSource random = p_159749_.random();
		WorldGenLevel level = p_159749_.level();
		BlockPos pos = p_159749_.origin();
		BlockPos below = pos.below();
		BlockPos north = pos.north();
		BlockPos south = pos.south();
		BlockPos west = pos.west();
		BlockPos east = pos.east();
		BlockState state = null;
		if(random.nextFloat() <= 0.8F)
		{
			if(level.getBlockState(below).is(OceanicBlocks.REEF_ROCK.get()))
			{
				state = OceanicBlocks.CLAM.get().defaultBlockState().setValue(ClamBlock.VARIANT, random.nextInt(1, 5));
			}
			else if(level.getBlockState(north).is(OceanicBlocks.REEF_ROCK.get()))
			{
				state = OceanicBlocks.CLAM.get().defaultBlockState().setValue(ClamBlock.VARIANT, random.nextInt(1, 5)).setValue(ClamBlock.FACE, AttachFace.WALL).setValue(ClamBlock.FACING, Direction.NORTH.getOpposite());
			}
			else if(level.getBlockState(south).is(OceanicBlocks.REEF_ROCK.get()))
			{
				state = OceanicBlocks.CLAM.get().defaultBlockState().setValue(ClamBlock.VARIANT, random.nextInt(1, 5)).setValue(ClamBlock.FACE, AttachFace.WALL).setValue(ClamBlock.FACING, Direction.SOUTH.getOpposite());
			}
			else if(level.getBlockState(west).is(OceanicBlocks.REEF_ROCK.get()))
			{
				state = OceanicBlocks.CLAM.get().defaultBlockState().setValue(ClamBlock.VARIANT, random.nextInt(1, 5)).setValue(ClamBlock.FACE, AttachFace.WALL).setValue(ClamBlock.FACING, Direction.WEST.getOpposite());
			}
			else if(level.getBlockState(east).is(OceanicBlocks.REEF_ROCK.get()))
			{
				state = OceanicBlocks.CLAM.get().defaultBlockState().setValue(ClamBlock.VARIANT, random.nextInt(1, 5)).setValue(ClamBlock.FACE, AttachFace.WALL).setValue(ClamBlock.FACING, Direction.EAST.getOpposite());
			}
		}
		if(state != null)
		{
			level.setBlock(pos, state, 2);
			return true;
		}
		return false;
	}
}
