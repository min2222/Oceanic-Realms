package com.min01.oceanicrealms.world;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.world.feature.ClamFeature;
import com.min01.oceanicrealms.world.feature.CrabHoleFeature;
import com.min01.oceanicrealms.world.feature.ListFeatureConfiguration;
import com.min01.oceanicrealms.world.feature.ReefRockBeachFeature;
import com.min01.oceanicrealms.world.feature.ReefRockDeepFeature;
import com.min01.oceanicrealms.world.feature.ReefRockShallowFeature;
import com.min01.oceanicrealms.world.feature.SandstoneArchFeature;
import com.min01.oceanicrealms.world.feature.SandstonePillarFeature;
import com.min01.oceanicrealms.world.feature.SeaUrchinFeature;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OceanicFeatures 
{
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, OceanicRealms.MODID);
	
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CRAB_HOLE = FEATURES.register("crab_hole", () -> new CrabHoleFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<ListFeatureConfiguration>> SANDSTONE_PILLAR = FEATURES.register("sandstone_pillar", () -> new SandstonePillarFeature(ListFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<ListFeatureConfiguration>> SANDSTONE_ARCH = FEATURES.register("sandstone_arch", () -> new SandstoneArchFeature(ListFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<ListFeatureConfiguration>> REEF_ROCK_BEACH = FEATURES.register("reef_rock_beach", () -> new ReefRockBeachFeature(ListFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<ListFeatureConfiguration>> REEF_ROCK_SHALLOW = FEATURES.register("reef_rock_shallow", () -> new ReefRockShallowFeature(ListFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<ListFeatureConfiguration>> REEF_ROCK_DEEP = FEATURES.register("reef_rock_deep", () -> new ReefRockDeepFeature(ListFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SEA_URCHIN = FEATURES.register("sea_urchin", () -> new SeaUrchinFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CLAM = FEATURES.register("clam", () -> new ClamFeature(NoneFeatureConfiguration.CODEC));
}
