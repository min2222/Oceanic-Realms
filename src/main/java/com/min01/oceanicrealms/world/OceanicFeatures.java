package com.min01.oceanicrealms.world;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.world.feature.ClamFeature;
import com.min01.oceanicrealms.world.feature.GiantKelpFeature;
import com.min01.oceanicrealms.world.feature.ListFeatureConfiguration;
import com.min01.oceanicrealms.world.feature.RedAlgaeFeature;
import com.min01.oceanicrealms.world.feature.ReefRockBeachFeature;
import com.min01.oceanicrealms.world.feature.ReefRockDeepFeature;
import com.min01.oceanicrealms.world.feature.ReefRockShallowFeature;
import com.min01.oceanicrealms.world.feature.SandstoneArchFeature;
import com.min01.oceanicrealms.world.feature.SandstonePillarFeature;
import com.min01.oceanicrealms.world.feature.SeaAnemoneFeature;
import com.min01.oceanicrealms.world.feature.SeaUrchinFeature;
import com.min01.oceanicrealms.world.feature.StarfishFeature;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OceanicFeatures 
{
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, OceanicRealms.MODID);
	
    public static final RegistryObject<Feature<ListFeatureConfiguration>> SANDSTONE_PILLAR = FEATURES.register("sandstone_pillar", () -> new SandstonePillarFeature(ListFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<ListFeatureConfiguration>> SANDSTONE_ARCH = FEATURES.register("sandstone_arch", () -> new SandstoneArchFeature(ListFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<ListFeatureConfiguration>> REEF_ROCK_BEACH = FEATURES.register("reef_rock_beach", () -> new ReefRockBeachFeature(ListFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<ListFeatureConfiguration>> REEF_ROCK_SHALLOW = FEATURES.register("reef_rock_shallow", () -> new ReefRockShallowFeature(ListFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<ListFeatureConfiguration>> REEF_ROCK_DEEP = FEATURES.register("reef_rock_deep", () -> new ReefRockDeepFeature(ListFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SEA_URCHIN = FEATURES.register("sea_urchin", () -> new SeaUrchinFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CLAM = FEATURES.register("clam", () -> new ClamFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SEA_ANEMONE = FEATURES.register("sea_anemone", () -> new SeaAnemoneFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> STARFISH = FEATURES.register("starfish", () -> new StarfishFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> RED_ALGAE = FEATURES.register("red_algae", () -> new RedAlgaeFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> GIANT_KELP = FEATURES.register("giant_kelp", () -> new GiantKelpFeature(NoneFeatureConfiguration.CODEC));
}
