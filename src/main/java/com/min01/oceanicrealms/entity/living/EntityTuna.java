package com.min01.oceanicrealms.entity.living;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.IAvoid;
import com.min01.oceanicrealms.entity.IBoid;
import com.min01.oceanicrealms.misc.Boid;
import com.min01.oceanicrealms.misc.Boid.Bounds;
import com.min01.oceanicrealms.misc.OceanicTags;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

public class EntityTuna extends AbstractOceanicCreature implements IBoid
{
	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(EntityTuna.class, EntityDataSerializers.INT);
	
	public Boid boid;
	public final List<Boid> boids = new ArrayList<>();
	public final List<Boid.Obstacle> obstacles = new ArrayList<>();
	
	public EntityTuna leader;
	public int size;
	
	public final AnimationState dryAnimationState = new AnimationState();
	
	public EntityTuna(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_)
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
    protected void defineSynchedData()
    {
    	super.defineSynchedData();
    	this.entityData.define(VARIANT, 1);
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
			EntityTuna fish = this.leader;
			if(fish.boid == null)
			{
				fish.boid = new Boid(fish, Bounds.fromCenter(fish.position(), new Vec3(70, 1, 70)));
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
					boid.update(fish.boids, fish.obstacles, true, true, true, 15.0F, 0.35F);
				}
			}
		}
		if(this.tickCount % 20 == 0)
		{
			if(this.leader == null || !this.leader.isAlive() || this.size <= 4)
			{
				List<EntityTuna> list = this.level.getEntitiesOfClass(EntityTuna.class, this.getBoundingBox().inflate(15.0F));
				list.sort(Comparator.comparing(Entity::getUUID));
				if(!list.isEmpty())
				{
					this.leader = list.get(0);
					this.size = list.size();
				}
			}
		}
	}
	
	@Override
	protected void doPush(Entity p_20971_) 
	{
		if(!(p_20971_ instanceof EntityTuna))
		{
			super.doPush(p_20971_);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, SpawnGroupData p_21437_, CompoundTag p_21438_) 
	{
    	if(p_21434_.getBiome(this.blockPosition()).is(OceanicTags.OceanicBiomes.COLD_OCEANS))
    	{
    		this.setVariant(2);
    	}
		return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
	}
    
	@Override
	public int getBodyRotationSpeed() 
	{
		return 6;
	}
	
    @Override
    public void addAdditionalSaveData(CompoundTag p_21484_)
    {
    	super.addAdditionalSaveData(p_21484_);
    	p_21484_.putInt("Variant", this.getVariant());
    }
    
    @Override
    public void readAdditionalSaveData(CompoundTag p_21450_) 
    {
    	super.readAdditionalSaveData(p_21450_);
    	if(p_21450_.contains("Variant"))
    	{
    		this.setVariant(p_21450_.getInt("Variant"));
    	}
    }
    
    public void setVariant(int value)
    {
    	this.entityData.set(VARIANT, value);
    }
    
    public int getVariant()
    {
    	return this.entityData.get(VARIANT);
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
