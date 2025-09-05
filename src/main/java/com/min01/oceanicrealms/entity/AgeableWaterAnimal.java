package com.min01.oceanicrealms.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

public abstract class AgeableWaterAnimal extends Animal
{
	public AgeableWaterAnimal(EntityType<? extends Animal> p_27557_, Level p_27558_)
	{
		super(p_27557_, p_27558_);
		this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
	}

	@Override
	public boolean canBreatheUnderwater() 
	{
		return true;
	}

	@Override
	public MobType getMobType() 
	{
		return MobType.WATER;
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader p_30348_) 
	{
		return p_30348_.isUnobstructed(this);
	}

	@Override
	public int getAmbientSoundInterval() 
	{
		return 120;
	}

	@Override
	public int getExperienceReward() 
	{
		return 1 + this.level().random.nextInt(3);
	}

	protected void handleAirSupply(int p_30344_) 
	{
		if(this.isAlive() && !this.isInWaterOrBubble()) 
		{
			this.setAirSupply(p_30344_ - 1);
			if(this.getAirSupply() == -20) 
			{
				this.setAirSupply(0);
				this.hurt(this.damageSources().drown(), 2.0F);
			}
		}
		else 
		{
			this.setAirSupply(300);
		}
	}

	@Override
	public void baseTick() 
	{
		super.baseTick();
		this.handleAirSupply(this.getAirSupply());
	}
	
	@Override
	public boolean isPushedByFluid()
	{
		return false;
	}
	
	@Override
	public boolean canBeLeashed(Player p_30346_)
	{
		return false;
	}
}