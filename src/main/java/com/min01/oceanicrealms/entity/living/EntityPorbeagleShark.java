package com.min01.oceanicrealms.entity.living;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.min01.oceanicrealms.entity.AbstractOceanicShark;
import com.min01.oceanicrealms.entity.IAvoid;
import com.min01.oceanicrealms.entity.IBoid;
import com.min01.oceanicrealms.misc.Boid;
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
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

public class EntityPorbeagleShark extends AbstractOceanicShark implements IBoid<EntityPorbeagleShark>
{	
	public static final EntityDataAccessor<Optional<UUID>> LEADER_UUID = SynchedEntityData.defineId(EntityPorbeagleShark.class, EntityDataSerializers.OPTIONAL_UUID);
	public static final EntityDataAccessor<Integer> SCHOOL_SIZE = SynchedEntityData.defineId(EntityPorbeagleShark.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(EntityPorbeagleShark.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> IS_LEADER = SynchedEntityData.defineId(EntityPorbeagleShark.class, EntityDataSerializers.BOOLEAN);
	
	public Bounds bounds;
	public final Collection<Boid.Obstacle> obstacles = new ArrayList<Boid.Obstacle>();
	public final Map<EntityPorbeagleShark, Boid> boids = new HashMap<EntityPorbeagleShark, Boid>();
	
	public final AnimationState attackAnimationState = new AnimationState();
	public final AnimationState eatingAnimationState = new AnimationState();
	
	public EntityPorbeagleShark(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_) 
	{
		super(p_33002_, p_33003_);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
        		.add(Attributes.MAX_HEALTH, 20.0F)
        		.add(Attributes.MOVEMENT_SPEED, 0.5F)
        		.add(Attributes.ARMOR, 2.0F)
        		.add(Attributes.ATTACK_DAMAGE, 4.0F);
    }
    
    @Override
    protected void registerGoals() 
    {
    	super.registerGoals();
        this.goalSelector.addGoal(9, new AvoidEntityGoal<>(this, EntityGreatWhiteShark.class, 8.0F, 1.0D, 1.0D));
    	this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, WaterAnimal.class, false, t -> t.isInWater() && !(t instanceof Dolphin) && !(t instanceof AbstractOceanicShark) && !(t instanceof IAvoid) && !(t instanceof EntityWhaleshark))
    	{
    		@Override
    		public boolean canUse() 
    		{
    			return super.canUse() && EntityPorbeagleShark.this.isHungry() && EntityPorbeagleShark.this.getLeader() == null;
    		}
    	});
    	this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Dolphin.class, false, t -> t.isInWater())
    	{
    		@Override
    		public boolean canUse() 
    		{
    			return super.canUse() && EntityPorbeagleShark.this.isHungry() && EntityPorbeagleShark.this.getSchoolSize() >= 6 && EntityPorbeagleShark.this.getLeader() == null;
    		}
    	});
    	this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, false, t -> t.getHealth() < 5.0F));
    }
    
    @Override
    protected void defineSynchedData()
    {
    	super.defineSynchedData();
    	this.entityData.define(VARIANT, this.random.nextInt(1, 3));
    	this.entityData.define(SCHOOL_SIZE, 0);
    	this.entityData.define(LEADER_UUID, Optional.empty());
    	this.entityData.define(IS_LEADER, false);
    }
    
	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> p_219422_) 
	{
        if(ANIMATION_STATE.equals(p_219422_) && this.level.isClientSide) 
        {
            switch(this.getAnimationState()) 
            {
        		case 0: 
        		{
        			this.stopAllAnimationStates();
        			break;
        		}
        		case 1: 
        		{
        			this.stopAllAnimationStates();
        			this.attackAnimationState.start(this.tickCount);
        			break;
        		}
        		case 2: 
        		{
        			this.stopAllAnimationStates();
        			this.eatingAnimationState.start(this.tickCount);
        			break;
        		}
            }
        }
	}
	
	@Override
	public void stopAllAnimationStates() 
	{
		this.attackAnimationState.stop();
		this.eatingAnimationState.stop();
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		OceanicUtil.avoid(this, this.bounds, this.obstacles, 5.0F, t -> t instanceof EntityGreatWhiteShark || t instanceof IAvoid);
		
		if(this.getTarget() != null)
		{
			this.bounds = Bounds.fromCenter(this.getTarget().position(), new Vec3(2, 2, 2));
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, SpawnGroupData p_21437_, CompoundTag p_21438_) 
	{
		int schoolSize = this.random.nextInt(1, 3);
		OceanicUtil.spawnWithBoid(this, schoolSize);
		return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
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
    }
    
    @Override
    public float getHeadDistance() 
    {
    	return 0.8F;
    }
    
    @Override
    public boolean rotLerp() 
    {
    	return true;
    }
    
	@Override
	public Vec3 getBoundSize()
	{
		return new Vec3(8, 8, 8);
	}
	
	@Override
	public Map<EntityPorbeagleShark, Boid> getBoid() 
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
	public void setLeader(EntityPorbeagleShark leader)
	{
		this.entityData.set(LEADER_UUID, Optional.of(leader.getUUID()));
	}
	
	@Nullable
	@Override
	public EntityPorbeagleShark getLeader() 
	{
		if(this.entityData.get(LEADER_UUID).isPresent()) 
		{
			return (EntityPorbeagleShark) OceanicUtil.getEntityByUUID(this.level, this.entityData.get(LEADER_UUID).get());
		}
		return null;
	}
	
    public void setSchoolSize(int value)
    {
    	this.entityData.set(SCHOOL_SIZE, value);
    }
    
    public int getSchoolSize()
    {
    	return this.entityData.get(SCHOOL_SIZE);
    }
    
    public void setVariant(int value)
    {
    	this.entityData.set(VARIANT, value);
    }
    
    public int getVariant()
    {
    	return this.entityData.get(VARIANT);
    }
}
