package com.min01.oceanicrealms.world;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.world.feature.CrabHoleFeature;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OceanicFeatures 
{
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, OceanicRealms.MODID);
	
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CRAB_HOLE = FEATURES.register("crab_hole", () -> new CrabHoleFeature(NoneFeatureConfiguration.CODEC));
}
