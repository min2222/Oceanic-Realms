package com.min01.oceanicrealms;

import com.min01.oceanicrealms.entity.OceanicEntities;
import com.min01.oceanicrealms.item.OceanicItems;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(OceanicRealms.MODID)
public class OceanicRealms
{
	public static final String MODID = "oceanicrealms";
	
	public OceanicRealms() 
	{
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		OceanicEntities.ENTITY_TYPES.register(bus);
		OceanicItems.ITEMS.register(bus);
	}
}
