package com.min01.oceanicrealms.entity.ai.goal;

import com.min01.oceanicrealms.entity.AbstractOceanicShark;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;

public class SharkAttackGoal extends BasicAnimationSkillGoal<AbstractOceanicShark>
{
	public SharkAttackGoal(AbstractOceanicShark mob)
	{
		super(mob);
	}
	
	@Override
	public void start()
	{
		super.start();
		this.mob.setAnimationState(1);
		this.nextSkillTickCount = 0;
	}
	
	@Override
	public boolean canUse() 
	{
		boolean flag = this.mob.getTarget() != null && this.mob.getLastHurtByMob() != null && this.mob.getTarget() == this.mob.getLastHurtByMob() && !this.mob.isUsingSkill() && this.additionalStartCondition();
		return super.canUse() || flag;
	}
	
	@Override
	public boolean additionalStartCondition()
	{
		Vec3 lookPos = OceanicUtil.getLookPos(this.mob.getRotationVector(), this.mob.position(), 0.0F, 0.0F, this.mob.getHeadDistance());
		return lookPos.distanceTo(this.mob.getTarget().position()) <= 2.5F;
	}
	
	@Override
	public boolean stopMovingWhenStart() 
	{
		return false;
	}

	@Override
	protected void performSkill() 
	{
		Vec3 lookPos = OceanicUtil.getLookPos(this.mob.getRotationVector(), this.mob.position(), 0.0F, 0.0F, this.mob.getHeadDistance());
		if(this.mob.getTarget() != null)
		{
			if(lookPos.distanceTo(this.mob.getTarget().position()) <= 2.5F)
			{
				if(this.mob.getTarget().hurt(this.mob.damageSources().mobAttack(this.mob), (float) this.mob.getAttributeBaseValue(Attributes.ATTACK_DAMAGE)))
				{
					if(!this.mob.getTarget().isAlive())
					{
						this.mob.setHungerCooldown(this.getSkillUsingInterval());
				    	this.nextSkillTickCount = this.mob.tickCount + this.getSkillUsingInterval();
					}
				}
			}
		}
	}
	
	@Override
	public void stop()
	{
		super.stop();
		this.mob.setAnimationState(0);
		this.mob.setTarget(null);
	}

	@Override
	protected int getSkillUsingTime() 
	{
		return 20;
	}
	
	@Override
	protected int getSkillWarmupTime()
	{
		return 13;
	}

	@Override
	protected int getSkillUsingInterval()
	{
		return 3600;
	}
}
