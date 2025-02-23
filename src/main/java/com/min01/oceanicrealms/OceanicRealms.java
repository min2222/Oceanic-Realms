package com.min01.oceanicrealms;

import com.min01.oceanicrealms.block.OceanicBlocks;
import com.min01.oceanicrealms.config.OceanicConfig;
import com.min01.oceanicrealms.entity.OceanicEntities;
import com.min01.oceanicrealms.item.OceanicItems;
import com.min01.oceanicrealms.misc.OceanicCreativeTabs;
import com.min01.oceanicrealms.network.OceanicNetwork;
import com.min01.oceanicrealms.world.OceanicFeatures;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(OceanicRealms.MODID)
public class OceanicRealms
{
	public static final String MODID = "oceanicrealms";
	
	public OceanicRealms() 
	{
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext ctx = ModLoadingContext.get();

		OceanicEntities.ENTITY_TYPES.register(bus);
		OceanicItems.ITEMS.register(bus);
		OceanicCreativeTabs.CREATIVE_MODE_TAB.register(bus);
		OceanicBlocks.BLOCKS.register(bus);
		OceanicBlocks.BLOCK_ENTITIES.register(bus);
		OceanicFeatures.FEATURES.register(bus);
		
		OceanicNetwork.registerMessages();
		ctx.registerConfig(Type.COMMON, OceanicConfig.CONFIG_SPEC, "oceanic-realms.toml");
	}
}
