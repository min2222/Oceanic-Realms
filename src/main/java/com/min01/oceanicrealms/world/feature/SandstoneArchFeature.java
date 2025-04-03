package com.min01.oceanicrealms.world.feature;

import com.min01.oceanicrealms.block.OceanicBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class SandstoneArchFeature extends Feature<ListFeatureConfiguration>
{
	public SandstoneArchFeature(Codec<ListFeatureConfiguration> p_65786_) 
	{
		super(p_65786_);
	}

	@Override
	public boolean place(FeaturePlaceContext<ListFeatureConfiguration> p_159749_)
	{
		WorldGenLevel level = p_159749_.level();
		BlockPos pos = p_159749_.origin();
		RandomSource random = p_159749_.random();
		if(level.getBlockState(pos.below()).is(OceanicBlocks.SEDIMENTARY_SANDSTONE.get()))
		{
			if(random.nextFloat() <= 0.3F)
			{
		    	if(!level.hasChunk(SectionPos.blockToSectionCoord(pos.getX()), SectionPos.blockToSectionCoord(pos.getZ()))) 
		    	{
		    	    return false;
		    	}
				ResourceLocation location = p_159749_.config().structures.get(random.nextInt(3));
				StructureTemplateManager manager = level.getLevel().getStructureManager();
				StructureTemplate template = manager.getOrCreate(location);
		    	StructurePlaceSettings settings = (new StructurePlaceSettings()).setMirror(Mirror.values()[random.nextInt(2)]).setRotation(Rotation.getRandom(random));
		    	template.placeInWorld(level, pos, pos, settings, random, 3);
				return true;
			}
		}
		return false;
	}
}
