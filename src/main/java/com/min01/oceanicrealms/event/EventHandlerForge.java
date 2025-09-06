package com.min01.oceanicrealms.event;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.AbstractOceanicShark;
import com.min01.oceanicrealms.entity.AgeableWaterAnimal;
import com.min01.oceanicrealms.entity.ai.goal.AvoidEntitySwimmingGoal;
import com.min01.oceanicrealms.entity.living.EntityGreatHammerheadShark;
import com.min01.oceanicrealms.entity.living.EntityGreatWhiteShark;
import com.min01.oceanicrealms.entity.living.EntityLionfish;
import com.min01.oceanicrealms.entity.living.EntitySailfish;
import com.min01.oceanicrealms.entity.living.EntityWhaleshark;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
		Entity entity = event.getEntity();
		if(entity instanceof Mob mob)
		{
			if(mob instanceof AgeableWaterAnimal waterAnimal)
			{
				double speed = waterAnimal.getAttributeBaseValue(Attributes.MOVEMENT_SPEED);
				if(!(waterAnimal instanceof EntityLionfish))
				{
					waterAnimal.goalSelector.addGoal(0, new AvoidEntitySwimmingGoal<>(waterAnimal, EntityLionfish.class, 8.0F, speed, speed));
					if(!(waterAnimal instanceof EntityGreatWhiteShark) && !(waterAnimal instanceof EntityGreatHammerheadShark) && !(waterAnimal instanceof EntityWhaleshark))
					{
						waterAnimal.goalSelector.addGoal(0, new AvoidEntitySwimmingGoal<>(waterAnimal, EntityGreatWhiteShark.class, 8.0F, speed, speed));
						waterAnimal.goalSelector.addGoal(0, new AvoidEntitySwimmingGoal<>(waterAnimal, EntityGreatHammerheadShark.class, 8.0F, speed, speed));
						if(!(waterAnimal instanceof EntitySailfish))
						{
							waterAnimal.goalSelector.addGoal(0, new AvoidEntitySwimmingGoal<>(waterAnimal, EntitySailfish.class, 8.0F, speed, speed));
						}
						if(!(waterAnimal instanceof AbstractOceanicShark))
						{
							waterAnimal.goalSelector.addGoal(0, new AvoidEntitySwimmingGoal<>(waterAnimal, AbstractOceanicShark.class, 8.0F, speed, speed));
						}
					}
				}
			}
			else if(mob instanceof WaterAnimal waterAnimal)
			{
				double speed = waterAnimal.getAttributeBaseValue(Attributes.MOVEMENT_SPEED);
				waterAnimal.goalSelector.addGoal(0, new AvoidEntitySwimmingGoal<>(waterAnimal, EntityLionfish.class, 8.0F, speed, speed));
				waterAnimal.goalSelector.addGoal(0, new AvoidEntitySwimmingGoal<>(waterAnimal, EntitySailfish.class, 8.0F, speed, speed));
				waterAnimal.goalSelector.addGoal(0, new AvoidEntitySwimmingGoal<>(waterAnimal, AbstractOceanicShark.class, 8.0F, speed, speed));
			}
		}
	}
}
