package com.min01.oceanicrealms.misc;

import com.min01.oceanicrealms.OceanicRealms;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class OceanicTags
{
	public static class OceanicBiomes
	{
		public static final TagKey<Biome> CRAB_SPAWN_BIOMES = create("crab_spawn_biomes");
		public static final TagKey<Biome> COLD_OCEANS = create("cold_oceans");
		public static final TagKey<Biome> WARM_OCEANS = create("warm_oceans");
		
		private static TagKey<Biome> create(String p_203849_) 
		{
			return TagKey.create(Registries.BIOME, new ResourceLocation(OceanicRealms.MODID, p_203849_));
		}
	}
}
