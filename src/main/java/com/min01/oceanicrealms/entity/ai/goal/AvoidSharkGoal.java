package com.min01.oceanicrealms.entity.ai.goal;

import java.util.EnumSet;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.min01.oceanicrealms.entity.AbstractOceanicShark;

import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

public class AvoidSharkGoal extends Goal
{
	protected final PathfinderMob mob;
	private final double speedModifier;
	@Nullable
	protected AbstractOceanicShark toAvoid;
	protected final float maxDist;
	@Nullable
	protected Path path;
	protected final PathNavigation pathNav;
	protected final Predicate<LivingEntity> avoidPredicate;
	protected final Predicate<LivingEntity> predicateOnAvoidEntity;
	private final TargetingConditions avoidEntityTargeting;

	public AvoidSharkGoal(PathfinderMob p_25027_, float p_25029_, double p_25030_)
	{
		this(p_25027_, (p_25052_) -> 
		{
			return true;
		}, p_25029_, p_25030_, EntitySelector.NO_CREATIVE_OR_SPECTATOR::test);
	}

	public AvoidSharkGoal(PathfinderMob p_25040_, Predicate<LivingEntity> p_25042_, float p_25043_, double p_25044_, Predicate<LivingEntity> p_25046_)
	{
		this.mob = p_25040_;
		this.avoidPredicate = p_25042_;
		this.maxDist = p_25043_;
		this.speedModifier = p_25044_;
		this.predicateOnAvoidEntity = p_25046_;
		this.pathNav = p_25040_.getNavigation();
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		this.avoidEntityTargeting = TargetingConditions.forCombat().range((double) p_25043_) .selector(p_25046_.and(p_25042_));
	}

	public AvoidSharkGoal(PathfinderMob p_25033_, float p_25035_, double p_25036_, Predicate<LivingEntity> p_25038_) 
	{
		this(p_25033_, (p_25049_) -> 
		{
			return true;
		}, p_25035_, p_25036_, p_25038_);
	}

	@Override
	public boolean canUse() 
	{
		this.toAvoid = this.mob.level.getNearestEntity(this.mob.level.getEntitiesOfClass(AbstractOceanicShark.class, this.mob.getBoundingBox().inflate((double) this.maxDist), (p_148078_) -> 
		{
			return true;
		}), this.avoidEntityTargeting, this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ());
		if(this.toAvoid == null) 
		{
			return false;
		} 
		else 
		{
			Vec3 vec3 = DefaultRandomPos.getPosAway(this.mob, 16, 7, this.toAvoid.position());
			if(vec3 == null)
			{
				return false;
			} 
			else if (Math.sqrt(this.toAvoid.distanceToSqr(vec3.x, vec3.y, vec3.z)) < Math.sqrt(this.toAvoid.distanceToSqr(this.mob))) 
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

	@Override
	public boolean canContinueToUse()
	{
		return !this.pathNav.isDone();
	}

	@Override
	public void start() 
	{
		this.pathNav.moveTo(this.path, this.speedModifier);
	}

	@Override
	public void stop()
	{
		this.toAvoid = null;
	}

	@Override
	public void tick()
	{
		this.mob.getNavigation().setSpeedModifier(this.speedModifier);
	}
}