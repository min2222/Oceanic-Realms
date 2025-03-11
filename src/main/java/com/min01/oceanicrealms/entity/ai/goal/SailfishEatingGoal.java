package com.min01.oceanicrealms.entity.ai.goal;

import java.util.List;

import com.min01.oceanicrealms.entity.living.EntitySailfish;

import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;

public class SailfishEatingGoal extends Goal
{
	private final EntitySailfish mob;
	
	private ItemEntity item;
	
	public SailfishEatingGoal(EntitySailfish mob) 
	{
		this.mob = mob;
	}
	
	@Override
	public boolean canUse()
	{
		List<ItemEntity> list = this.mob.level.getEntitiesOfClass(ItemEntity.class, this.mob.getBoundingBox().inflate(5.0F), item -> 
		{
			boolean flag = item.getItem().getFoodProperties(this.mob) != null && item.getItem().getFoodProperties(this.mob).isMeat();
			boolean flag2 = item.getItem().is(ItemTags.FISHES) || flag;
			return item.isInWater() && flag2;
		});
		if(!list.isEmpty())
		{
			this.item = list.get(0);
		}
		return !this.mob.isUsingSkill() && this.item != null;
	}

	@Override
	public void start() 
	{
		this.mob.setEat(true);
	}
	
	@Override
	public boolean canContinueToUse() 
	{
		return this.canUse() && this.mob.isEat() && this.item.isAlive();
	}
	
	@Override
	public void tick() 
	{
		this.mob.getNavigation().moveTo(this.item, this.mob.getAttributeBaseValue(Attributes.MOVEMENT_SPEED));
		this.mob.lookAt(Anchor.EYES, this.item.getEyePosition());
	}
	
	@Override
	public void stop() 
	{
		this.mob.getNavigation().stop();
		this.mob.setEat(false);
		this.item = null;
	}
}
