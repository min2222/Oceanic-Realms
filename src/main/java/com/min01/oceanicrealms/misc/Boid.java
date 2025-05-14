package com.min01.oceanicrealms.misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

//https://github.com/TheCymaera/minecraft-boids/tree/master
public class Boid
{
	public final Entity entity;
	public Bounds bounds;
	public Vec3 boundsSize;
	public Vec3 position;
	public Vec3 velocity = Vec3.ZERO;

	public Boid(Entity entity, Bounds bounds)
	{
		this.entity = entity;
		this.bounds = bounds;
		this.boundsSize = bounds.size;
		this.position = new Vec3(this.bounds.minX() + Math.random() * this.bounds.size.x, this.bounds.minY() + Math.random() * this.bounds.size.y, this.bounds.minZ() + Math.random() * this.bounds.size.z);
		this.velocity = new Vec3(Math.random(), Math.random(), Math.random());
	}
	
	public void recreateBounds(List<Boid> boids)
	{
		if(this.entity.tickCount % 60 == 0)
		{
			Vec3 pos = new Vec3(this.bounds.minX() + Math.random() * this.bounds.size.x, this.bounds.minY() + Math.random() * this.bounds.size.y, this.bounds.minZ() + Math.random() * this.bounds.size.z);
			this.bounds = Bounds.fromCenter(pos, this.boundsSize);
			this.position = new Vec3(this.bounds.minX() + Math.random() * this.bounds.size.x, this.bounds.minY() + Math.random() * this.bounds.size.y, this.bounds.minZ() + Math.random() * this.bounds.size.z);
			for(Boid boid : boids)
			{
				boid.bounds = this.bounds;
			}
		}
	}

	public void update(Collection<Boid> boids, Collection<Boid.Obstacle> obstacles, boolean avoidance, boolean alignment, boolean cohesion, float flockRadius, float maxVelocity) 
	{
		Collection<Boid> flock = this.getInRange(boids, this.position, flockRadius);
		
		Vec3 acceleration = Vec3.ZERO;
		
		acceleration = acceleration.add(this.stayInBounds(this.entity.level.getBlockState(this.entity.blockPosition().above()).is(Blocks.WATER)));

		if(avoidance)
		{
			Collection<Obstacle> flockObstacles = new ArrayList<Obstacle>();
			for(Boid boid : flock)
			{
				flockObstacles.add(new Boid.Obstacle(boid.position, 0.5, 0.05));
			}

			acceleration = acceleration.add(this.awayFrom(obstacles));
			acceleration = acceleration.add(this.awayFrom(flockObstacles));
		}
		
		if(alignment) 
		{
			acceleration = acceleration.add(this.averageVelocity(flock).scale(0.06));
		}
		
		if(cohesion) 
		{
			acceleration = acceleration.add(this.centerDisplacement(flock).scale(0.006));
		}
		
		this.velocity = this.velocity.add(acceleration);

		if(!this.velocity.equals(Vec3.ZERO))
		{
			if(this.velocity.length() > maxVelocity)
			{
				this.velocity = this.velocity.normalize().scale(maxVelocity);
			}
		}
		
		this.position = this.position.add(this.velocity);
	}

	private Vec3 stayInBounds(boolean isWater) 
	{
		Vec3 acceleration = Vec3.ZERO;
		double magnitude = 0.025;
	    if(this.position.x < this.bounds.minX())
	    {
	        acceleration = new Vec3(magnitude, acceleration.y, acceleration.z);
	    }
	    if(this.position.x > this.bounds.maxX())
	    {
	        acceleration = new Vec3(-magnitude, acceleration.y, acceleration.z);
	    }
	    if(this.position.y < this.bounds.minY() && isWater)
	    {
	        acceleration = new Vec3(acceleration.x, magnitude, acceleration.z);
	    }
	    if(this.position.y > this.bounds.maxY() || !isWater)
	    {
	        acceleration = new Vec3(acceleration.x, !isWater ? -0.35 : -magnitude, acceleration.z);
	    }
	    if(this.position.z < this.bounds.minZ()) 
	    {
	        acceleration = new Vec3(acceleration.x, acceleration.y, magnitude);
	    }
	    if(this.position.z > this.bounds.maxZ()) 
	    {
	        acceleration = new Vec3(acceleration.x, acceleration.y, -magnitude);
	    }
		return acceleration;
	}

	private Vec3 averageVelocity(Collection<Boid> flock)
	{
		Vec3 avg = Vec3.ZERO;
		if(flock.size() == 0)
		{
			return Vec3.ZERO;
		}
		for(Boid other : flock)
		{
			avg = avg.add(other.velocity);
		}
		avg = avg.scale(1.0 / flock.size());
		return avg;
	}

	private Vec3 centerDisplacement(Collection<Boid> flock)
	{
		Vec3 center = Vec3.ZERO;
		if(flock.size() == 0)
		{
			return Vec3.ZERO;
		}
		for(Boid other : flock)
		{
			center = center.add(other.position);
		}
		center = center.scale(1.0 / flock.size());
		return center.subtract(this.position);
	}

	private Vec3 awayFrom(Collection<Obstacle> obstacles) 
	{
		Vec3 acc = Vec3.ZERO;
		for(Obstacle obstacle : obstacles) 
		{
			double distance = this.position.distanceTo(obstacle.position);
			if(distance > obstacle.avoidRadius)
			{
				continue;
			}

			Vec3 diff = this.position.subtract(obstacle.position);
			acc = acc.add(diff.scale(obstacle.avoidFactor));
		}
		return acc;
	}

	private Collection<Boid> getInRange(Collection<? extends Boid> boids, Vec3 location, double radius)
	{
		Collection<Boid> out = new ArrayList<Boid>();
		for(Boid other : boids) 
		{
			double distance = this.position.distanceTo(other.position);
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
			return new Bounds(pos.add(size.scale(-0.5)), size);
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
			if(loc.x <  this.minX() || this.maxX() < loc.x)
			{
				return false;
			}
			if(loc.y <  this.minY() || this.maxY() < loc.y)
			{
				return false;
			}
			if(loc.z <  this.minZ() || this.maxZ() < loc.z)
			{
				return false;
			}
			return true;
		}
	}
}
