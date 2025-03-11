package com.min01.oceanicrealms.entity.ai.goal;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;

public class AvoidEntitySwimmingGoal<T extends LivingEntity> extends AvoidEntityGoal<T>
{
	private final TargetingConditions avoidEntityTargeting;
	
	public AvoidEntitySwimmingGoal(PathfinderMob p_25027_, Class<T> p_25028_, float p_25029_, double p_25030_, double p_25031_) 
	{
		super(p_25027_, p_25028_, p_25029_, p_25030_, p_25031_);
		this.avoidEntityTargeting = TargetingConditions.forCombat().range((double)p_25029_).selector(EntitySelector.NO_CREATIVE_OR_SPECTATOR::test);
	}
	
	@Override
	public boolean canUse() 
	{
		this.toAvoid = this.mob.level.getNearestEntity(this.mob.level.getEntitiesOfClass(this.avoidClass, this.mob.getBoundingBox().inflate((double)this.maxDist, 3.0D, (double)this.maxDist), (p_148078_) -> 
		{
			return true;
		}), this.avoidEntityTargeting, this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ());
		if(this.toAvoid == null)
		{
			return false;
		}
		else 
		{
			Vec3 vec3 = getRandomSwimmablePosAway(this.mob, 16, 7, this.toAvoid.position());
			if(vec3 == null)
			{
				return false;
			}
			else if(this.toAvoid.distanceToSqr(vec3.x, vec3.y, vec3.z) < this.toAvoid.distanceToSqr(this.mob))
			{
				return false;
			} 
			else 
			{
				this.path = this.pathNav.createPath(vec3.x, vec3.y, vec3.z, 0);
				return this.path != null;
			}
		}
	}
	
	@Nullable
	public static Vec3 getRandomSwimmablePosAway(PathfinderMob p_147445_, int p_147446_, int p_147447_, Vec3 pos)
	{
		Vec3 vec3 = DefaultRandomPos.getPosAway(p_147445_, p_147446_, p_147447_, pos);
		for(int i = 0; vec3 != null && p_147445_.level.getFluidState(BlockPos.containing(vec3)).getFluidType() != ForgeMod.WATER_TYPE.get() && i++ < 10; vec3 = DefaultRandomPos.getPosAway(p_147445_, p_147446_, p_147447_, pos))
		{
			
		}
		return vec3;
	}
}
