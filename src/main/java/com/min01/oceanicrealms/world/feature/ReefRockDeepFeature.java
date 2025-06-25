package com.min01.oceanicrealms.world.feature;

import java.util.List;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.BulkSectionAccess;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class ReefRockDeepFeature extends Feature<ListFeatureConfiguration>
{
	public ReefRockDeepFeature(Codec<ListFeatureConfiguration> p_65786_) 
	{
		super(p_65786_);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean place(FeaturePlaceContext<ListFeatureConfiguration> p_159749_)
	{
		WorldGenLevel level = p_159749_.level();
		BlockPos pos = p_159749_.origin();
		RandomSource random = p_159749_.random();
		//TODO
		if(level.getBlockState(pos.above(8)).isAir() && level.getBlockState(pos).is(Blocks.WATER) && level.getBlockState(pos.below()).isCollisionShapeFullBlock(level, pos))
		{
			if(random.nextFloat() <= 0.3F)
			{
			    try(BulkSectionAccess bulk = new BulkSectionAccess(level))
			    {
					ResourceLocation location = p_159749_.config().structures.get(random.nextInt(4));
					StructureTemplateManager manager = level.getLevel().getStructureManager();
					StructureTemplate template = manager.getOrCreate(location);
			    	StructurePlaceSettings settings = (new StructurePlaceSettings()).setMirror(Mirror.values()[random.nextInt(2)]).setRotation(Rotation.getRandom(random));
    		    	List<StructureTemplate.StructureBlockInfo> list = settings.getRandomPalette(template.palettes, pos).blocks();
			    	
    		    	for(StructureTemplate.StructureBlockInfo info : StructureTemplate.processBlockInfos(level, pos, pos, settings, list, template)) 
    		    	{
    		    	    if(info.nbt() != null) 
    		    	    {
    		    	        continue;
    		    	    }
    		    	    BlockPos blockPos = info.pos();
    		    	    BlockState state = info.state().mirror(settings.getMirror()).rotate(settings.getRotation());
    		    	    LevelChunkSection section = bulk.getSection(blockPos);

                        int x = SectionPos.sectionRelative(blockPos.getX());
                        int y = SectionPos.sectionRelative(blockPos.getY());
                        int z = SectionPos.sectionRelative(blockPos.getZ());
                        
    		    	    if(section != null)
    		    	    {
    		    	    	section.setBlockState(x, y, z, state, false);
    		    	    }
    		    	}
    				return true;
			    }
			}
		}
		return false;
	}
}
