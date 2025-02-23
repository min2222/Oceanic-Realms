package com.min01.oceanicrealms.entity.living;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.min01.oceanicrealms.entity.AbstractOceanicShark;
import com.min01.oceanicrealms.entity.OceanicEntities;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.core.BlockPos;
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
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.Path;

public class EntityHammerheadShark extends AbstractOceanicShark
{	
	public static final EntityDataAccessor<Optional<UUID>> LEADER_UUID = SynchedEntityData.defineId(EntityHammerheadShark.class, EntityDataSerializers.OPTIONAL_UUID);
	public static final EntityDataAccessor<Boolean> IS_LEADER = SynchedEntityData.defineId(EntityHammerheadShark.class, EntityDataSerializers.BOOLEAN);
	
	public final AnimationState attackAnimationState = new AnimationState();
	public final AnimationState eatingAnimationState = new AnimationState();
	
	public EntityHammerheadShark(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_) 
	{
		super(p_33002_, p_33003_);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
        		.add(Attributes.MAX_HEALTH, 25.0F)
        		.add(Attributes.MOVEMENT_SPEED, 0.45F)
        		.add(Attributes.ATTACK_DAMAGE, 4.0F);
    }
    
    @Override
    protected void registerGoals() 
    {
    	super.registerGoals();
    	this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, WaterAnimal.class, false, t -> t.isInWater() && !(t instanceof Dolphin) && !(t instanceof AbstractOceanicShark))
    	{
    		@Override
    		public boolean canUse() 
    		{
    			return super.canUse() && EntityHammerheadShark.this.isHungry() && EntityHammerheadShark.this.getLeader() == null;
    		}
    	});
    }
    
    @Override
    protected void defineSynchedData()
    {
    	super.defineSynchedData();
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
		if(this.isLeader())
		{
			if(this.tickCount % 20 == 0)
			{
				List<EntityHammerheadShark> list = this.level.getEntitiesOfClass(EntityHammerheadShark.class, this.getBoundingBox().inflate(5.0F), t -> !t.isLeader() && t.getLeader() == null);
				list.forEach(t -> 
				{
					t.setLeader(this);
				});
			}
		}
		else if(this.getLeader() != null)
		{
			EntityHammerheadShark leader = this.getLeader();
			if(this.distanceTo(leader) > 2.5F)
			{
				this.getNavigation().moveTo(leader, 0.45F);
			}
			else
			{
				if(leader.getNavigation().getPath() != null)
				{
					BlockPos pos = leader.getNavigation().getPath().getTarget();
					Path path = this.getNavigation().createPath(pos, 1);
					this.getNavigation().moveTo(path, 0.45F);
				}
				if(leader.getTarget() != null)
				{
					this.setTarget(leader.getTarget());
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, SpawnGroupData p_21437_, CompoundTag p_21438_) 
	{
		if(p_21436_ == MobSpawnType.NATURAL && this.random.nextBoolean())
		{
			this.setLeader(true);
			int schoolSize = this.random.nextInt(1, 3);
			for(int i = 0; i < schoolSize; i++)
			{
				EntityHammerheadShark shark = new EntityHammerheadShark(OceanicEntities.HAMMERHEAD_SHARK.get(), this.level);
				shark.setPos(this.position());
				shark.setLeader(this);
				this.level.addFreshEntity(shark);
			}
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
    public boolean canRandomSwim() 
    {
    	return super.canRandomSwim() && this.getLeader() == null;
    }
    
    @Override
    public float getHeadDistance() 
    {
    	return 0.8F;
    }
    
    public void setLeader(boolean value)
    {
    	this.entityData.set(IS_LEADER, value);
    }
    
    public boolean isLeader()
    {
    	return this.entityData.get(IS_LEADER);
    }
	
	public void setLeader(EntityHammerheadShark leader)
	{
		this.entityData.set(LEADER_UUID, Optional.of(leader.getUUID()));
	}
	
	@Nullable
	public EntityHammerheadShark getLeader() 
	{
		if(this.entityData.get(LEADER_UUID).isPresent()) 
		{
			return (EntityHammerheadShark) OceanicUtil.getEntityByUUID(this.level, this.entityData.get(LEADER_UUID).get());
		}
		return null;
	}
}
