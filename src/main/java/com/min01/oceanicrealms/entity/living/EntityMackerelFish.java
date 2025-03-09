package com.min01.oceanicrealms.entity.living;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.AbstractOceanicShark;
import com.min01.oceanicrealms.entity.IAvoid;
import com.min01.oceanicrealms.entity.IBoid;
import com.min01.oceanicrealms.item.OceanicItems;
import com.min01.oceanicrealms.misc.Boid;
import com.min01.oceanicrealms.misc.Boid.Bounds;
import com.min01.oceanicrealms.misc.Boid.Obstacle;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

public class EntityMackerelFish extends AbstractOceanicCreature implements Bucketable, IBoid<EntityMackerelFish>
{	
	public static final EntityDataAccessor<Optional<UUID>> LEADER_UUID = SynchedEntityData.defineId(EntityMackerelFish.class, EntityDataSerializers.OPTIONAL_UUID);
	public static final EntityDataAccessor<Boolean> IS_LEADER = SynchedEntityData.defineId(EntityMackerelFish.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(EntityMackerelFish.class, EntityDataSerializers.BOOLEAN);
	
	public Bounds bounds;
	public final Collection<Boid.Obstacle> obstacles = new ArrayList<Boid.Obstacle>();
	public final Map<EntityMackerelFish, Boid> boids = new HashMap<EntityMackerelFish, Boid>();
	
	public final AnimationState dryAnimationState = new AnimationState();
	
	public EntityMackerelFish(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_)
	{
		super(p_33002_, p_33003_);
	}
    
    @Override
    protected void defineSynchedData()
    {
    	super.defineSynchedData();
    	this.entityData.define(LEADER_UUID, Optional.empty());
    	this.entityData.define(IS_LEADER, false);
    	this.entityData.define(FROM_BUCKET, false);
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
		OceanicUtil.avoid(this, this.bounds, this.obstacles, 3.0F, t -> t instanceof AbstractOceanicShark || t instanceof IAvoid);
		
		List<EntityWhaleshark> list = this.level.getEntitiesOfClass(EntityWhaleshark.class, this.getBoundingBox().inflate(10.0F));
		if(!list.isEmpty())
		{
			this.bounds = Bounds.fromCenter(list.get(0).position(), new Vec3(2, 2, 2));
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, SpawnGroupData p_21437_, CompoundTag p_21438_) 
	{
		if(!this.fromBucket() && p_21436_ != MobSpawnType.BUCKET)
		{
			int schoolSize = this.random.nextInt(6, 11);
			OceanicUtil.spawnWithBoid(this, schoolSize);
		}
		return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
	}
	
    @Override
    public void addAdditionalSaveData(CompoundTag p_21484_)
    {
    	super.addAdditionalSaveData(p_21484_);
		if(this.entityData.get(LEADER_UUID).isPresent())
		{
			p_21484_.putUUID("Leader", this.entityData.get(LEADER_UUID).get());
		}
		p_21484_.putBoolean("isLeader", this.isLeader());
    }
    
    @Override
    public void readAdditionalSaveData(CompoundTag p_21450_) 
    {
    	super.readAdditionalSaveData(p_21450_);
		if(p_21450_.hasUUID("Leader")) 
		{
			this.entityData.set(LEADER_UUID, Optional.of(p_21450_.getUUID("Leader")));
		}
		if(p_21450_.contains("isLeader")) 
		{
			this.setLeader(p_21450_.getBoolean("isLeader"));
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
    }

    @SuppressWarnings("deprecation")
	@Override
    public void loadFromBucketTag(CompoundTag p_148708_)
    {
    	Bucketable.loadDefaultDataFromBucketTag(this, p_148708_);
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
	
	@Override
	public Vec3 getBoundSize()
	{
		return new Vec3(8, 8, 8);
	}
	
	@Override
	public Map<EntityMackerelFish, Boid> getBoid() 
	{
		return this.boids;
	}
	
	@Override
	public Collection<Obstacle> getObstacle() 
	{
		return this.obstacles;
	}
	
	@Override
	public Bounds getBounds() 
	{
		return this.bounds;
	}
    
	@Override
	public void setBound(Bounds bounds)
	{
		this.bounds = bounds;
	}
    
    @Override
    public void setLeader(boolean value)
    {
    	this.entityData.set(IS_LEADER, value);
    }
    
    @Override
    public boolean isLeader()
    {
    	return this.entityData.get(IS_LEADER);
    }

    @Override
	public void setLeader(EntityMackerelFish leader)
	{
		this.entityData.set(LEADER_UUID, Optional.of(leader.getUUID()));
	}
	
	@Nullable
	@Override
	public EntityMackerelFish getLeader() 
	{
		if(this.entityData.get(LEADER_UUID).isPresent()) 
		{
			return (EntityMackerelFish) OceanicUtil.getEntityByUUID(this.level, this.entityData.get(LEADER_UUID).get());
		}
		return null;
	}
}
