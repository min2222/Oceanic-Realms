package com.min01.oceanicrealms.event;

import java.util.Map;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.AbstractOceanicShark;
import com.min01.oceanicrealms.entity.IBoid;
import com.min01.oceanicrealms.entity.living.EntityGreatWhiteShark;
import com.min01.oceanicrealms.entity.living.EntityLionfish;
import com.min01.oceanicrealms.entity.living.EntitySailfish;
import com.min01.oceanicrealms.entity.living.EntityWhaleshark;
import com.min01.oceanicrealms.misc.Boid;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OceanicRealms.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandlerForge
{
	@SuppressWarnings("unchecked")
	@SubscribeEvent
	public static <T extends AbstractOceanicCreature & IBoid<T>> void onLivingTick(LivingTickEvent event)
	{
		LivingEntity entity = event.getEntity();
		if(entity instanceof IBoid<?> boid)
		{
			T fish = (T) entity;
			OceanicUtil.loadBoid(fish);
			if(boid.isLeader() && entity.isInWater())
			{
				if(entity.tickCount % 60 == 0)
				{
					OceanicUtil.recreateBounds(fish, 8);
				}
				OceanicUtil.tickBoid(fish, boid.getBounds(), (Map<T, Boid>) boid.getBoid());
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@SubscribeEvent
	public static <T extends AbstractOceanicCreature & IBoid<T>> void onLivingDeath(LivingDeathEvent event)
	{
		LivingEntity entity = event.getEntity();
		if(entity instanceof IBoid<?>)
		{
			T fish = (T) entity;
			OceanicUtil.transferLeader(fish);
		}
	}
	
	@SubscribeEvent
	public static void onEntityJoinLevel(EntityJoinLevelEvent event)
	{
		Entity entity = event.getEntity();
		if(entity instanceof Mob mob)
		{
			if(mob instanceof WaterAnimal waterAnimal)
			{
				if(!(waterAnimal instanceof IBoid<?>))
				{
					waterAnimal.goalSelector.addGoal(0, new AvoidEntityGoal<>(waterAnimal, EntityLionfish.class, 8.0F, 1.0D, 1.0D));
					if(!(waterAnimal instanceof EntityGreatWhiteShark) && !(waterAnimal instanceof EntityWhaleshark))
					{
						waterAnimal.goalSelector.addGoal(0, new AvoidEntityGoal<>(waterAnimal, EntityGreatWhiteShark.class, 8.0F, 1.0D, 1.0D));
						waterAnimal.goalSelector.addGoal(0, new AvoidEntityGoal<>(waterAnimal, EntitySailfish.class, 8.0F, 1.0D, 1.0D));
						if(!(waterAnimal instanceof AbstractOceanicShark))
						{
							waterAnimal.goalSelector.addGoal(0, new AvoidEntityGoal<>(waterAnimal, AbstractOceanicShark.class, 8.0F, 1.0D, 1.0D));
						}
					}
				}
			}
		}
	}
}
