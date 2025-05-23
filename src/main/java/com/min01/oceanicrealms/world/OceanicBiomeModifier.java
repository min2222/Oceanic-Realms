package com.min01.oceanicrealms.world;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.OceanicEntities;
import com.min01.oceanicrealms.misc.OceanicTags;
import com.mojang.serialization.Codec;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
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
		MobSpawnSettingsBuilder mobSpawnSettings = builder.getMobSpawnSettings();
		if(phase == Phase.ADD)
		{
			if(biome.is(OceanicTags.OceanicBiomes.CRAB_SPAWN_BIOMES))
			{
				mobSpawnSettings.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(OceanicEntities.CRAB.get(), 90, 2, 5));
			}
			if(biome.is(OceanicTags.OceanicBiomes.WARM_OCEANS))
			{
				mobSpawnSettings.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(OceanicEntities.DOLPHINFISH.get(), 20, 5, 7));
				mobSpawnSettings.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(OceanicEntities.HAMMERHEAD_SHARK.get(), 50, 1, 4));
				mobSpawnSettings.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(OceanicEntities.MACKEREL_FISH.get(), 35, 8, 12));
				mobSpawnSettings.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(OceanicEntities.SAILFISH.get(), 20, 1, 1));
				mobSpawnSettings.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(OceanicEntities.SILVER_POMFRET_FISH.get(), 35, 8, 12));
				mobSpawnSettings.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(OceanicEntities.WHALESHARK.get(), 10, 1, 1));
			}
			if(biome.is(OceanicTags.OceanicBiomes.COLD_OCEANS))
			{
				mobSpawnSettings.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(OceanicEntities.PORBEAGLE_SHARK.get(), 50, 1, 1));
				mobSpawnSettings.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(OceanicEntities.SAILFISH.get(), 10, 1, 1));
			}
			if(biome.is(Biomes.DEEP_LUKEWARM_OCEAN) || biome.is(OceanicBiomes.SANDSTONE_OCEAN))
			{
				mobSpawnSettings.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(OceanicEntities.GREAT_HAMMERHEAD_SHARK.get(), 10, 1, 1));
			}
			if(biome.is(Biomes.WARM_OCEAN) || biome.is(OceanicBiomes.SANDSTONE_OCEAN))
			{
				mobSpawnSettings.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(OceanicEntities.LIONFISH.get(), 50, 1, 1));
			}
			if(biome.is(BiomeTags.IS_DEEP_OCEAN))
			{
				mobSpawnSettings.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(OceanicEntities.GREAT_WHITE_SHARK.get(), 10, 1, 1));
			}
			if(biome.is(OceanicTags.OceanicBiomes.WARM_AND_COLD_OCEANS))
			{
				mobSpawnSettings.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(OceanicEntities.TUNA.get(), 20, 5, 7));
			}
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
