package com.min01.oceanicrealms.entity.ai.goal;

import com.min01.oceanicrealms.entity.ai.control.BoidMoveControl;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.item.crafting.Ingredient;

public class FishTemptGoal extends TemptGoal
{
	public FishTemptGoal(PathfinderMob p_25939_, double p_25940_, Ingredient p_25941_, boolean p_25942_) 
	{
		super(p_25939_, p_25940_, p_25941_, p_25942_);
	}
	
	@Override
	public void tick()
	{
		((BoidMoveControl) this.mob.getMoveControl()).setForceTarget(this.player.position());
	}
	
	@Override
	public void stop() 
	{
		super.stop();
		((BoidMoveControl) this.mob.getMoveControl()).setForceTarget(false);
	}
}
