package com.min01.oceanicrealms.event;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.OceanicEntities;
import com.min01.oceanicrealms.entity.living.EntityCrab;
import com.min01.oceanicrealms.entity.living.EntityGreatWhiteShark;
import com.min01.oceanicrealms.item.OceanicItems;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OceanicRealms.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler
{
    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) 
    {
    	event.put(OceanicEntities.GREAT_WHITE_SHARK.get(), EntityGreatWhiteShark.createAttributes().build());
    	event.put(OceanicEntities.CRAB.get(), EntityCrab.createAttributes().build());
    }
    
	@SubscribeEvent
	public static void onSpawnPlacementRegister(SpawnPlacementRegisterEvent event)
	{
		
	}
	
    @SubscribeEvent
    public static void onBuildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event)
    {
    	if(event.getTabKey() == CreativeModeTabs.SPAWN_EGGS)
    	{
    		event.accept(OceanicItems.GREAT_WHITE_SHARK_SPAWN_EGG.get());
    		event.accept(OceanicItems.CRAB_SPAWN_EGG.get());
    	}
    }
}
