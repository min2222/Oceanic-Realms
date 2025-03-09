package com.min01.oceanicrealms.entity.living;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import org.joml.Math;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.AbstractOceanicShark;
import com.min01.oceanicrealms.entity.IAvoid;
import com.min01.oceanicrealms.entity.IBoid;
import com.min01.oceanicrealms.entity.OceanicEntities;
import com.min01.oceanicrealms.misc.Boid;
import com.min01.oceanicrealms.misc.OceanicTags;
import com.min01.oceanicrealms.misc.Boid.Bounds;
import com.min01.oceanicrealms.misc.Boid.Obstacle;
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

public class EntityTuna extends AbstractOceanicCreature implements IBoid<EntityTuna>
{
	public static final EntityDataAccessor<Optional<UUID>> LEADER_UUID = SynchedEntityData.defineId(EntityTuna.class, EntityDataSerializers.OPTIONAL_UUID);
	public static final EntityDataAccessor<Boolean> IS_LEADER = SynchedEntityData.defineId(EntityTuna.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(EntityTuna.class, EntityDataSerializers.INT);
	
	public Bounds bounds;
	public final Collection<Boid.Obstacle> obstacles = new ArrayList<Boid.Obstacle>();
	public final Map<EntityTuna, Boid> boids = new HashMap<EntityTuna, Boid>();
	
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
		OceanicUtil.avoid(this, this.bounds, this.obstacles, 5.0F, t -> t instanceof AbstractOceanicShark || t instanceof IAvoid);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, SpawnGroupData p_21437_, CompoundTag p_21438_) 
	{
		this.setLeader(true);
		Bounds bounds = Bounds.fromCenter(this.position(), this.getBoundSize());
		Vec3 pos = new Vec3(bounds.minX() + Math.random() * bounds.size.x, bounds.minY() + Math.random() * bounds.size.y, bounds.minZ() + Math.random() * bounds.size.z);
		this.boids.put(this, new Boid(pos, bounds));
		this.bounds = bounds;
		this.createBoid(pos, bounds, p_21434_.getBiome(this.blockPosition()).is(OceanicTags.OceanicBiomes.COLD_OCEANS));
    	if(p_21434_.getBiome(this.blockPosition()).is(OceanicTags.OceanicBiomes.COLD_OCEANS))
    	{
    		this.setVariant(2);
    	}
		return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
	}
	
	public void createBoid(Vec3 pos, Bounds bounds, boolean isCold)
	{
		int schoolSize = this.random.nextInt(3, 7);
		for(int i = 0; i < schoolSize; i++)
		{
			EntityTuna tuna = new EntityTuna(OceanicEntities.TUNA.get(), this.level);
			tuna.setPos(this.position());
			tuna.setLeader(this);
			this.boids.put(tuna, new Boid(pos, bounds));
			if(isCold)
			{
				tuna.setVariant(2);
			}
			this.level.addFreshEntity(tuna);
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
    
    @Override
    public boolean rotLerp() 
    {
    	return true;
    }
	
	@Override
	public Vec3 getBoundSize()
	{
		return new Vec3(4, 4, 4);
	}
	
	@Override
	public Map<EntityTuna, Boid> getBoid() 
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
	public void setLeader(EntityTuna leader)
	{
		this.entityData.set(LEADER_UUID, Optional.of(leader.getUUID()));
	}
	
	@Nullable
    @Override
	public EntityTuna getLeader() 
	{
		if(this.entityData.get(LEADER_UUID).isPresent()) 
		{
			return (EntityTuna) OceanicUtil.getEntityByUUID(this.level, this.entityData.get(LEADER_UUID).get());
		}
		return null;
	}
}
