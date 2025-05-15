package com.min01.oceanicrealms.entity.living;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.IAvoid;
import com.min01.oceanicrealms.entity.IBoid;
import com.min01.oceanicrealms.misc.Boid;
import com.min01.oceanicrealms.misc.Boid.Bounds;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EntityDolphinfish extends AbstractOceanicCreature implements IBoid
{
	public Boid boid;
	public final List<Boid> boids = new ArrayList<>();
	public final List<Boid.Obstacle> obstacles = new ArrayList<>();
	
	public EntityDolphinfish leader;
	public int size;
	
	public final AnimationState dryAnimationState = new AnimationState();
	
	public EntityDolphinfish(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_)
	{
		super(p_33002_, p_33003_);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
        		.add(Attributes.MAX_HEALTH, 15.0F)
        		.add(Attributes.MOVEMENT_SPEED, 0.5F);
    }
    
	@Override
	public void tick() 
	{
		super.tick();
		if(this.level.isClientSide)
		{
			this.dryAnimationState.animateWhen(!this.isInWater(), this.tickCount);
		}
		OceanicUtil.fishFlopping(this);
		if(this.leader != null)
		{
			EntityDolphinfish fish = this.leader;
			if(fish.boid == null)
			{
				fish.boid = new Boid(fish, Bounds.fromCenter(fish.position(), new Vec3(8, 1, 8)));
				fish.boids.add(fish.boid);
			}
			else if(this.boid == null)
			{
				this.boid = new Boid(this, fish.boid.bounds);
			}
			else if(!fish.boids.contains(this.boid))
			{
				fish.boids.add(this.boid);
			}
			OceanicUtil.avoid(this, fish.boid.bounds, fish.obstacles, 5.0F, t -> t instanceof IAvoid);
			if(this == fish)
			{
				fish.boid.recreateBounds(fish.boids);
				for(Boid boid : fish.boids)
				{
					boid.update(fish.boids, fish.obstacles, true, true, true, 5.0F, 0.35F);
				}
			}
		}
		if(this.leader == null || !this.leader.isAlive() || this.size <= 4)
		{
			List<EntityDolphinfish> list = this.level.getEntitiesOfClass(EntityDolphinfish.class, this.getBoundingBox().inflate(5.0F));
			list.sort(Comparator.comparing(Entity::getUUID));
			if(!list.isEmpty())
			{
				this.leader = list.get(0);
				this.size = list.size();
			}
		}
	}
    
	@Override
	public int getBodyRotationSpeed() 
	{
		return 6;
	}
	
	@Override
	public Boid getBoid()
	{
		return this.boid;
	}
	
	@Override
	public void resetBoid() 
	{
		this.boid = null;
	}
	
	@Override
	public boolean canRandomSwim() 
	{
		return super.canRandomSwim() && this.boid != null;
	}
}
