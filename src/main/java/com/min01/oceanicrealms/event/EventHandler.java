package com.min01.oceanicrealms.event;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.OceanicEntities;
import com.min01.oceanicrealms.entity.living.EntityCrab;
import com.min01.oceanicrealms.entity.living.EntityDolphinfish;
import com.min01.oceanicrealms.entity.living.EntityGreatWhiteShark;
import com.min01.oceanicrealms.entity.living.EntityHammerheadShark;
import com.min01.oceanicrealms.entity.living.EntityLionfish;
import com.min01.oceanicrealms.entity.living.EntityPorbeagleShark;
import com.min01.oceanicrealms.entity.living.EntityTuna;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent.Operation;
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
    	event.put(OceanicEntities.PORBEAGLE_SHARK.get(), EntityPorbeagleShark.createAttributes().build());
    	event.put(OceanicEntities.TUNA.get(), EntityTuna.createAttributes().build());
    	event.put(OceanicEntities.DOLPHINFISH.get(), EntityDolphinfish.createAttributes().build());
    	event.put(OceanicEntities.HAMMERHEAD_SHARK.get(), EntityHammerheadShark.createAttributes().build());
    	event.put(OceanicEntities.MACKEREL_FISH.get(), Salmon.createAttributes().build());
    	event.put(OceanicEntities.SILVER_POMFRET_FISH.get(), Salmon.createAttributes().build());
    	event.put(OceanicEntities.LIONFISH.get(), EntityLionfish.createAttributes().build());
    }
    
	@SubscribeEvent
	public static void onSpawnPlacementRegister(SpawnPlacementRegisterEvent event)
	{
    	event.register(OceanicEntities.GREAT_WHITE_SHARK.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityGreatWhiteShark::checkSharkSpawnRules, Operation.AND);
    	event.register(OceanicEntities.CRAB.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityCrab::checkCrabSpawnRules, Operation.AND);
    	event.register(OceanicEntities.PORBEAGLE_SHARK.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityPorbeagleShark::checkSharkSpawnRules, Operation.AND);
    	event.register(OceanicEntities.TUNA.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityTuna::checkFishSpawnRules, Operation.AND);
    	event.register(OceanicEntities.DOLPHINFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityDolphinfish::checkFishSpawnRules, Operation.AND);
    	event.register(OceanicEntities.HAMMERHEAD_SHARK.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityHammerheadShark::checkSharkSpawnRules, Operation.AND);
	}
}
