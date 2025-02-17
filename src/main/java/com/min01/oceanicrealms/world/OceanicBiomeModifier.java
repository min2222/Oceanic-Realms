package com.min01.oceanicrealms.world;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.OceanicEntities;
import com.mojang.serialization.Codec;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
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
		if(biome.is(Biomes.DEEP_OCEAN))
		{
			MobSpawnSettingsBuilder mobSpawnSettings = builder.getMobSpawnSettings();
			mobSpawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(OceanicEntities.GREAT_WHITE_SHARK.get(), 5, 1, 1));
		}
		if(biome.is(Biomes.BEACH))
		{
			MobSpawnSettingsBuilder mobSpawnSettings = builder.getMobSpawnSettings();
			mobSpawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(OceanicEntities.CRAB.get(), 5, 2, 6));
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
