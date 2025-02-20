package com.min01.oceanicrealms.world;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.OceanicEntities;
import com.mojang.serialization.Codec;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OceanicBiomeModifier implements BiomeModifier
{
    private static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER = RegistryObject.create(new ResourceLocation(OceanicRealms.MODID, "oceanic_modifiers"), ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, OceanicRealms.MODID);
    
	@Override
	public void modify(Holder<Biome> biome, Phase phase, Builder builder)
	{
		MobSpawnSettingsBuilder mobSpawnSettings = builder.getMobSpawnSettings();
		if(biome.is(BiomeTags.IS_DEEP_OCEAN))
		{
			mobSpawnSettings.addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(OceanicEntities.GREAT_WHITE_SHARK.get(), 10, 1, 1));
		}
		if(biome.is(Biomes.BEACH) || biome.is(BiomeTags.IS_OCEAN) || biome.is(Tags.Biomes.IS_SWAMP))
		{
			mobSpawnSettings.addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(OceanicEntities.CRAB.get(), 20, 1, 4));
		}
		if(biome.is(Biomes.COLD_OCEAN) || biome.is(Biomes.DEEP_COLD_OCEAN))
		{
			mobSpawnSettings.addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(OceanicEntities.BULL_SHARK.get(), 20, 2, 4));
		}
	}

	@Override
	public Codec<? extends BiomeModifier> codec() 
	{
		return SERIALIZER.get();
	}
	
    public static Codec<OceanicBiomeModifier> makeCodec() 
    {
        return Codec.unit(OceanicBiomeModifier::new);
    }
}
