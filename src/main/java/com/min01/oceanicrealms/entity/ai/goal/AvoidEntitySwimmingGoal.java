package com.min01.oceanicrealms.entity.ai.goal;

import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

public class AvoidEntitySwimmingGoal<T extends LivingEntity> extends AvoidEntityGoal<T>
{
	private final TargetingConditions avoidEntityTargeting;
    private int timeToFindNearbyEntities;
	
	public AvoidEntitySwimmingGoal(PathfinderMob p_25027_, Class<T> p_25028_, float p_25029_, double p_25030_, double p_25031_) 
	{
		super(p_25027_, p_25028_, p_25029_, p_25030_, p_25031_);
		this.avoidEntityTargeting = TargetingConditions.forCombat().range((double)p_25029_).selector(EntitySelector.NO_CREATIVE_OR_SPECTATOR::test);
	}
	
	@Override
	public boolean canUse() 
	{
        if(--this.timeToFindNearbyEntities <= 0)
        {
            this.timeToFindNearbyEntities = this.adjustedTickDelay(40);
    		this.toAvoid = this.mob.level.getNearestEntity(this.mob.level.getEntitiesOfClass(this.avoidClass, this.mob.getBoundingBox().inflate(this.maxDist), (p_148078_) -> 
    		{
    			return true;
    		}), this.avoidEntityTargeting, this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ());
        }
        else if(this.toAvoid != null)
		{
			OceanicUtil.fishPanic(this.mob, this.toAvoid.position(), 0.8F);
			this.toAvoid = null;
		}
		return false;
	}
}
