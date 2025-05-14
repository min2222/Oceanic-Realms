package com.min01.oceanicrealms.entity.living;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.IAvoid;
import com.min01.oceanicrealms.entity.IBoid;
import com.min01.oceanicrealms.item.OceanicItems;
import com.min01.oceanicrealms.misc.Boid;
import com.min01.oceanicrealms.misc.Boid.Bounds;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EntityMackerelFish extends AbstractOceanicCreature implements Bucketable, IBoid
{
	public static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(EntityMackerelFish.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> FOLLOW_DURATION = SynchedEntityData.defineId(EntityMackerelFish.class, EntityDataSerializers.INT);
	
	public Boid boid;
	public final List<Boid> boids = new ArrayList<>();
	public final List<Boid.Obstacle> obstacles = new ArrayList<>();
	
	public EntityMackerelFish leader;
	
	public final AnimationState dryAnimationState = new AnimationState();
	
	public EntityMackerelFish(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_)
	{
		super(p_33002_, p_33003_);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
        		.add(Attributes.MAX_HEALTH, 3.0F)
        		.add(Attributes.MOVEMENT_SPEED, 0.5F);
    }
    
    @Override
    protected void defineSynchedData()
    {
    	super.defineSynchedData();
    	this.entityData.define(FROM_BUCKET, false);
    	this.entityData.define(FOLLOW_DURATION, 1200);
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
			EntityMackerelFish fish = this.leader;
			if(fish.boid == null)
			{
				fish.boid = new Boid(fish, Bounds.fromCenter(fish.position(), new Vec3(2, 2, 2)));
				fish.boids.add(fish.boid);
			}
			else if(this.boid == null)
			{
				this.boid = new Boid(this, fish.boid.bounds);
				fish.boids.add(this.boid);
			}
			OceanicUtil.avoid(this, fish.boid.bounds, fish.obstacles, 3.0F, t -> t instanceof IAvoid);
			if(this == fish)
			{
				this.followWhaleshark();
				fish.boid.recreateBounds(fish.boids);
				for(Boid boid : fish.boids)
				{
					boid.update(fish.boids, fish.obstacles, true, true, true, 5.0F, 0.5F);
				}
			}
		}
		if(this.leader == null || !this.leader.isAlive())
		{
			List<EntityMackerelFish> list = this.level.getEntitiesOfClass(EntityMackerelFish.class, this.getBoundingBox().inflate(15.0F));
			list.sort(Comparator.comparing(Entity::getUUID));
			if(!list.isEmpty())
			{
				this.leader = list.get(0);
			}
		}
	}
	
	public void followWhaleshark()
	{
		List<EntityWhaleshark> list = this.level.getEntitiesOfClass(EntityWhaleshark.class, this.getBoundingBox().inflate(10.0F));
		if(!list.isEmpty())
		{
			if(this.getFollowDuration() > 0)
			{
				this.boid.bounds = Bounds.fromCenter(list.get(0).position(), new Vec3(4, 4, 4));
				for(Boid boid : this.boids)
				{
					boid.bounds = this.boid.bounds;
				}
				this.setFollowDuration(this.getFollowDuration() - 1);
			}
		}
		else
		{
			this.setFollowDuration(1200);
		}
	}
	
    @Override
    protected void doPush(Entity p_20971_) 
    {
    	if(!(p_20971_ instanceof EntityWhaleshark))
    	{
    		super.doPush(p_20971_);
    	}
    }
	
    @Override
    public void addAdditionalSaveData(CompoundTag p_21484_)
    {
    	super.addAdditionalSaveData(p_21484_);
		p_21484_.putInt("FollowDuration", this.getFollowDuration());
    }
    
    @Override
    public void readAdditionalSaveData(CompoundTag p_21450_) 
    {
    	super.readAdditionalSaveData(p_21450_);
		if(p_21450_.contains("FollowDuration")) 
		{
			this.setFollowDuration(p_21450_.getInt("FollowDuration"));
		}
    }
    
	@Override
    protected InteractionResult mobInteract(Player p_27477_, InteractionHand p_27478_)
    {
    	return Bucketable.bucketMobPickup(p_27477_, p_27478_, this).orElse(super.mobInteract(p_27477_, p_27478_));
    }

    @SuppressWarnings("deprecation")
	@Override
	public void saveToBucketTag(ItemStack p_27494_)
    {
    	Bucketable.saveDefaultDataToBucketTag(this, p_27494_);
    	this.addAdditionalSaveData(p_27494_.getOrCreateTag());
    }

    @SuppressWarnings("deprecation")
	@Override
    public void loadFromBucketTag(CompoundTag p_148708_)
    {
    	Bucketable.loadDefaultDataFromBucketTag(this, p_148708_);
    	this.readAdditionalSaveData(p_148708_);
    }
    
	@Override
	public boolean fromBucket() 
	{
		return this.entityData.get(FROM_BUCKET);
	}

	@Override
	public void setFromBucket(boolean p_148834_)
	{
		this.entityData.set(FROM_BUCKET, p_148834_);
	}

	@Override
	public ItemStack getBucketItemStack() 
	{
		return new ItemStack(OceanicItems.MACKEREL_FISH_BUCKET.get());
	}

	@Override
	public SoundEvent getPickupSound()
	{
		return SoundEvents.BUCKET_FILL_FISH;
	}
	
    public void setFollowDuration(int value)
    {
    	this.entityData.set(FOLLOW_DURATION, value);
    }
    
    public int getFollowDuration()
    {
    	return this.entityData.get(FOLLOW_DURATION);
    }
    
	@Override
	public Boid getBoid()
	{
		return this.boid;
	}
	
	@Override
	public boolean canRandomSwim()
	{
		return super.canRandomSwim() && this.boid != null;
	}
}
