package com.min01.oceanicrealms.world;

import com.min01.oceanicrealms.OceanicRealms;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class OceanicBiomes 
{
	public static final ResourceKey<Biome> SANDSTONE_OCEAN = register("sandstone_ocean");
	   
	public static ResourceKey<Biome> register(String name) 
	{
		return ResourceKey.create(Registries.BIOME, new ResourceLocation(OceanicRealms.MODID, name));
	}
}
