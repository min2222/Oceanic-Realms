package com.min01.oceanicrealms.entity.ai.goal;

import java.util.EnumSet;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;

public class AvoidEntitySwimmingGoal<T extends LivingEntity> extends Goal 
{
	protected final PathfinderMob mob;
	private final double walkSpeedModifier;
	@Nullable
	protected T toAvoid;
	protected final float maxDist;
	protected final Class<T> avoidClass;

	public AvoidEntitySwimmingGoal(PathfinderMob p_25040_, Class<T> p_25041_, float p_25043_, double p_25044_)
	{
		this.mob = p_25040_;
		this.avoidClass = p_25041_;
		this.maxDist = p_25043_;
		this.walkSpeedModifier = p_25044_;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() 
	{
		this.toAvoid = this.mob.level.getNearestEntity(this.mob.level.getEntitiesOfClass(this.avoidClass, this.mob.getBoundingBox().inflate(this.maxDist)), TargetingConditions.forCombat().range(this.maxDist), this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ());
		if(this.toAvoid == null)
		{
			return false;
		}
		return this.toAvoid != null;
	}
	
	@Override
	public void start() 
	{
		Vec3 vec3 = this.getRandomSwimmablePosAway(this.mob, 16, 7, this.toAvoid.position());
		if(vec3 != null) 
		{
	        this.mob.getNavigation().moveTo(vec3.x, vec3.y, vec3.z, this.walkSpeedModifier);
		}
	}

	@Override
	public void stop() 
	{
		this.toAvoid = null;
	}
	
	public Vec3 getRandomSwimmablePosAway(PathfinderMob p_147445_, int p_147446_, int p_147447_, Vec3 pos)
	{
		Vec3 vec3 = DefaultRandomPos.getPosAway(p_147445_, p_147446_, p_147447_, pos);

		for(int i = 0; vec3 != null && !p_147445_.level.getBlockState(BlockPos.containing(vec3)).isPathfindable(p_147445_.level, BlockPos.containing(vec3), PathComputationType.WATER) && i++ < 10; vec3 = DefaultRandomPos.getPosAway(p_147445_, p_147446_, p_147447_, pos))
		{
			
		}
		return vec3;
	}
}	
