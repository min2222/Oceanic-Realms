package com.min01.oceanicrealms.misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.IAvoid;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult.Type;
import net.minecraft.world.phys.Vec3;

//https://github.com/TheCymaera/minecraft-boids/tree/master
public class Boid
{
	public Bounds bounds;
	public final Mob mob;
	
	public Boid(Mob mob)
	{
		this.mob = mob;
		this.bounds = Bounds.fromCenter(this.mob.position(), ((AbstractOceanicCreature) this.mob).getSwimRadius());
	}

	public void update(List<? extends Mob> boids, Collection<Boid.Obstacle> obstacles, boolean avoidance, boolean alignment, boolean cohesion, float flockRadius, float maxVelocity) 
	{
		Vec3 lookPos = OceanicUtil.getLookPos(this.mob.getRotationVector(), this.mob.position(), 0, 0, 3.0F);
		BlockHitResult blockHit = this.mob.level.clip(new ClipContext(this.mob.position(), lookPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, mob));
		if(blockHit.getType() != Type.MISS) 
		{
			obstacles.add(new Boid.Obstacle(blockHit.getLocation(), 5.0F, 0.5F));
		}
		
		TargetingConditions conditions = TargetingConditions.forNonCombat().range(5.0D).ignoreLineOfSight().selector(t -> t instanceof IAvoid);
		AbstractOceanicCreature creature = this.mob.level.getNearestEntity(AbstractOceanicCreature.class, conditions, this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ(), this.mob.getBoundingBox().inflate(5.0D));
		
		if(creature instanceof IAvoid)
		{
			obstacles.add(new Boid.Obstacle(creature.position(), 5.0F, 0.5F));
		}
		
		List<Mob> flock = this.getInRange(boids, flockRadius);
		
		Vec3 acceleration = Vec3.ZERO;
		
		acceleration = acceleration.add(this.stayInBounds());
		
		if(avoidance)
		{
			Collection<Obstacle> flockObstacles = new ArrayList<Obstacle>();
			for(Mob boid : flock)
			{
				flockObstacles.add(new Boid.Obstacle(boid.position(), 2.0F, 0.05F));
			}
			acceleration = acceleration.add(this.awayFrom(obstacles));
			acceleration = acceleration.add(this.awayFrom(flockObstacles));
		}
		
		if(alignment) 
		{
			acceleration = acceleration.add(this.averageVelocity(flock).scale(0.1F));
		}
		
		if(cohesion) 
		{
			acceleration = acceleration.add(this.centerDisplacement(flock).scale(0.1F));
		}
		
		this.mob.addDeltaMovement(acceleration);
		
        Vec3 velocity = this.mob.getDeltaMovement();
        if(velocity.length() > maxVelocity)
        {
            velocity = velocity.normalize().scale(maxVelocity);
        }
        this.mob.setDeltaMovement(velocity);
	}
	
	private Vec3 stayInBounds() 
	{
		Vec3 acceleration = Vec3.ZERO;
		double magnitude = 0.025F;
	    if(this.mob.position().x < this.bounds.minX())
	    {
	        acceleration = new Vec3(magnitude, acceleration.y, acceleration.z);
	    }
	    if(this.mob.position().x > this.bounds.maxX())
	    {
	        acceleration = new Vec3(-magnitude, acceleration.y, acceleration.z);
	    }
	    if(this.mob.position().y < this.bounds.minY())
	    {
	        acceleration = new Vec3(acceleration.x, magnitude, acceleration.z);
	    }
	    if(this.mob.position().y > this.bounds.maxY())
	    {
	        acceleration = new Vec3(acceleration.x, -magnitude, acceleration.z);
	    }
	    if(this.mob.position().z < this.bounds.minZ()) 
	    {
	        acceleration = new Vec3(acceleration.x, acceleration.y, magnitude);
	    }
	    if(this.mob.position().z > this.bounds.maxZ()) 
	    {
	        acceleration = new Vec3(acceleration.x, acceleration.y, -magnitude);
	    }
		return acceleration;
	}
	
	public void setBounds(Bounds bounds)
	{
		this.bounds = bounds;
	}

	private Vec3 averageVelocity(List<Mob> flock)
	{
		Vec3 avg = Vec3.ZERO;
		if(flock.isEmpty())
		{
			return Vec3.ZERO;
		}
		for(Mob other : flock)
		{
			avg = avg.add(other.getDeltaMovement());
		}
		avg = avg.scale(1.0F / flock.size());
		return avg;
	}

	private Vec3 centerDisplacement(List<Mob> flock)
	{
		Vec3 center = Vec3.ZERO;
		if(flock.isEmpty())
		{
			return Vec3.ZERO;
		}
		for(Mob other : flock)
		{
			center = center.add(other.position());
		}
		center = center.scale(1.0F / flock.size());
		return center.subtract(this.mob.position());
	}

	private Vec3 awayFrom(Collection<Obstacle> obstacles) 
	{
		Vec3 acc = Vec3.ZERO;
		for(Obstacle obstacle : obstacles) 
		{
			double distance = this.mob.position().distanceTo(obstacle.position);
			if(distance > obstacle.avoidRadius)
			{
				continue;
			}

			Vec3 diff = this.mob.position().subtract(obstacle.position);
			acc = acc.add(diff.scale(obstacle.avoidFactor));
		}
		return acc;
	}

	private List<Mob> getInRange(List<? extends Mob> boids, double radius)
	{
		List<Mob> out = new ArrayList<>();
		for(Mob other : boids) 
		{
			double distance = this.mob.position().distanceTo(other.position());
			if(distance < radius)
			{
				out.add(other);
			}
		}
		return out;
	}

	public static class Obstacle 
	{
		public final Vec3 position;
		public final double avoidRadius;
		public final double avoidFactor;
		public Obstacle(Vec3 position, double avoidRadius, double avoidFactor)
		{
			this.position = position;
			this.avoidRadius = avoidRadius;
			this.avoidFactor = avoidFactor;
		}
	}
	
	public static class Bounds 
	{
		public Vec3 origin;
		public Vec3 size;
		
		public Bounds(Vec3 pos, Vec3 size)
		{
			this.origin = pos;
			this.size = size;
		}
		
		public static Bounds fromCenter(Vec3 pos, Vec3 size)
		{
			return new Bounds(pos.add(size.scale(-0.5F)), size);
		}

		public double minX()
		{
			return this.origin.x;
		}

		public double minY() 
		{
			return this.origin.y;
		}

		public double minZ()
		{
			return this.origin.z;
		}

		public double maxX()
		{
			return this.origin.x + this.size.x;
		}

		public double maxY() 
		{
			return this.origin.y + this.size.y;
		}

		public double maxZ()
		{
			return this.origin.z + this.size.z;
		}

		public boolean contains(Vec3 loc)
		{
			if(loc.x < this.minX() || this.maxX() < loc.x)
			{
				 return false;
			}
			if(loc.y < this.minY() || this.maxY() < loc.y)
			{
				 return false;
			}
			if(loc.z < this.minZ() || this.maxZ() < loc.z)
			{
				 return false;
			}
			return true;
		}
	}
}