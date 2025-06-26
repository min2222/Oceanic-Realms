package com.min01.oceanicrealms.world.feature;

import java.util.List;

import com.min01.oceanicrealms.block.OceanicBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
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

public class SandstonePillarFeature extends Feature<ListFeatureConfiguration>
{
	public SandstonePillarFeature(Codec<ListFeatureConfiguration> p_65786_) 
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
		if(level.getBlockState(pos.below()).is(OceanicBlocks.SEDIMENTARY_SANDSTONE.get()))
		{
			if(random.nextFloat() <= 0.5F)
			{
			    try(BulkSectionAccess bulk = new BulkSectionAccess(level))
			    {
					ResourceLocation location = p_159749_.config().structures.get(random.nextInt(13));
					StructureTemplateManager manager = level.getLevel().getStructureManager();
					StructureTemplate template = manager.getOrCreate(location);
			    	StructurePlaceSettings settings = (new StructurePlaceSettings()).setMirror(Mirror.values()[random.nextInt(2)]).setRotation(Rotation.getRandom(random));
    		    	List<StructureTemplate.StructureBlockInfo> list = settings.getRandomPalette(template.palettes, pos).blocks();
    		    	
    		    	for(StructureTemplate.StructureBlockInfo info : StructureTemplate.processBlockInfos(level, pos, pos, settings, list, template)) 
    		    	{
    		    	    BlockPos blockPos = info.pos();
    		    	    BlockState state = info.state().mirror(settings.getMirror()).rotate(settings.getRotation());
    		    	    LevelChunkSection section = bulk.getSection(blockPos);

                        int x = SectionPos.sectionRelative(blockPos.getX());
                        int y = SectionPos.sectionRelative(blockPos.getY());
                        int z = SectionPos.sectionRelative(blockPos.getZ());
                        
    		    	    if(section != null && level.getBlockEntity(blockPos) == null)
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
