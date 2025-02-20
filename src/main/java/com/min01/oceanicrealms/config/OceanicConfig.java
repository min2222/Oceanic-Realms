package com.min01.oceanicrealms.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;

public class OceanicConfig 
{
	public static final OceanicConfig CONFIG;
	public static final ForgeConfigSpec CONFIG_SPEC;

	public static ForgeConfigSpec.BooleanValue spawnCrabs;
	
    static 
    {
    	Pair<OceanicConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(OceanicConfig::new);
    	CONFIG = pair.getLeft();
    	CONFIG_SPEC = pair.getRight();
    }
	
    public OceanicConfig(ForgeConfigSpec.Builder config) 
    {
    	config.push("Spawning Settings");
    	OceanicConfig.spawnCrabs = config.comment("disable/enable natural spawning of crabs").define("spawnCrabs", true);
        config.pop();
    }
}
