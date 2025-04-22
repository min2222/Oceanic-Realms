package com.min01.oceanicrealms.entity.living;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.IAvoid;
import com.min01.oceanicrealms.entity.IBoid;
import com.min01.oceanicrealms.misc.Boid;
import com.min01.oceanicrealms.misc.OceanicTags;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AnimationState;
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
	public static final EntityDataAccessor<Optional<UUID>> LEADER_UUID = SynchedEntityData.defineId(EntityTuna.class, EntityDataSerializers.OPTIONAL_UUID);
	public static final EntityDataAccessor<Boolean> IS_LEADER = SynchedEntityData.defineId(EntityTuna.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(EntityTuna.class, EntityDataSerializers.INT);
	
	public Boid boid;
	public final List<Boid> boids = new ArrayList<>();
	public final List<Boid.Obstacle> obstacles = new ArrayList<>();
	
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
    public void onAddedToWorld() 
    {
    	super.onAddedToWorld();
    	this.boid = new Boid(this, new Vec3(4, 2, 4));
    }
    
    @Override
    protected void defineSynchedData()
    {
    	super.defineSynchedData();
    	this.entityData.define(LEADER_UUID, Optional.empty());
    	this.entityData.define(IS_LEADER, false);
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
		OceanicUtil.avoid(this, this.boid.bounds, this.obstacles, 5.0F, t -> t instanceof IAvoid);
		List<EntityTuna> list = this.level.getEntitiesOfClass(EntityTuna.class, this.getBoundingBox().inflate(50.0F));
		list.forEach(t -> 
		{
			if(!this.boids.contains(t.boid))
			{
				this.boids.add(t.boid);
			}
		});
		this.boid.update(this.boids, this.obstacles, true, true, true, 50.0F, 0.5F);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, SpawnGroupData p_21437_, CompoundTag p_21438_) 
	{
		super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
		if(p_21437_ == null)
		{
			p_21437_ = new LeaderSpawnGroupData(this);
		} 
		else
		{
			this.setLeader(((LeaderSpawnGroupData)p_21437_).leader);
		}
    	if(p_21434_.getBiome(this.blockPosition()).is(OceanicTags.OceanicBiomes.COLD_OCEANS))
    	{
    		this.setVariant(2);
    	}
		return p_21437_;
	}
	
	public static class LeaderSpawnGroupData implements SpawnGroupData 
	{
		public final EntityTuna leader;

		public LeaderSpawnGroupData(EntityTuna fish)
		{
			fish.setLeader(true);
			this.leader = fish;
		}
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
    	if(p_21450_.contains("Variant"))
    	{
    		this.setVariant(p_21450_.getInt("Variant"));
    	}
		if(p_21450_.hasUUID("Leader")) 
		{
			this.entityData.set(LEADER_UUID, Optional.of(p_21450_.getUUID("Leader")));
		}
		if(p_21450_.contains("isLeader")) 
		{
			this.setLeader(p_21450_.getBoolean("isLeader"));
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

    public void setLeader(boolean value)
    {
    	this.entityData.set(IS_LEADER, value);
    }

    public boolean isLeader()
    {
    	return this.entityData.get(IS_LEADER);
    }

	public void setLeader(EntityTuna leader)
	{
    	if(leader == null)
    	{
    		this.entityData.set(LEADER_UUID, Optional.empty());
    	}
    	else
    	{
    		this.entityData.set(LEADER_UUID, Optional.of(leader.getUUID()));
    	}
	}
	
	@Nullable
	public EntityTuna getLeader() 
	{
		if(this.entityData.get(LEADER_UUID).isPresent()) 
		{
			return (EntityTuna) OceanicUtil.getEntityByUUID(this.level, this.entityData.get(LEADER_UUID).get());
		}
		return null;
	}
	
	@Override
	public Vec3 getBoidDirection()
	{
		return this.boid.direction;
	}
}
