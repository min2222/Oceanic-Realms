package com.min01.oceanicrealms.event;

import java.util.Map;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.IBoid;
import com.min01.oceanicrealms.misc.Boid;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.world.entity.LivingEntity;
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
}
