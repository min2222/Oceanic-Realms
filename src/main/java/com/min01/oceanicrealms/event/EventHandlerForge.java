package com.min01.oceanicrealms.event;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityGreatWhiteShark;

import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OceanicRealms.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandlerForge
{
	@SubscribeEvent
	public static void onEntityJoinLevel(EntityJoinLevelEvent event)
	{
		if(event.getEntity() instanceof WaterAnimal animal)
		{
			animal.goalSelector.addGoal(2, new AvoidEntityGoal<>(animal, EntityGreatWhiteShark.class, 15.0F, 1.6D, 1.4D));
		}
	}
}
