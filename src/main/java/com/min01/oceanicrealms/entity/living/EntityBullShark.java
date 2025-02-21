package com.min01.oceanicrealms.entity.living;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.min01.oceanicrealms.entity.AbstractOceanicShark;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;

public class EntityBullShark extends AbstractOceanicShark
{	
	public static final EntityDataAccessor<Optional<UUID>> LEADER_UUID = SynchedEntityData.defineId(EntityBullShark.class, EntityDataSerializers.OPTIONAL_UUID);
	public static final EntityDataAccessor<Integer> SCHOOL_SIZE = SynchedEntityData.defineId(EntityBullShark.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(EntityBullShark.class, EntityDataSerializers.INT);
	
	public final AnimationState attackAnimationState = new AnimationState();
	public final AnimationState eatingAnimationState = new AnimationState();
	
	public EntityBullShark(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_) 
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
    	this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, EntityGreatWhiteShark.class, 25.0F, 0.45D, 0.45D));
    	this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, WaterAnimal.class, false, t -> t.isInWater() && !(t instanceof Dolphin) && !(t instanceof AbstractOceanicShark))
    	{
    		@Override
    		public boolean canUse() 
    		{
    			return super.canUse() && EntityBullShark.this.isHungry() && EntityBullShark.this.getLeader() == null;
    		}
    	});
    	this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Dolphin.class, false, t -> t.isInWater())
    	{
    		@Override
    		public boolean canUse() 
    		{
    			return super.canUse() && EntityBullShark.this.isHungry() && EntityBullShark.this.getSchoolSize() >= 6;
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
		if(!this.isLeader())
		{
			if(this.getLeader() == null)
			{
				List<EntityBullShark> list = this.level.getEntitiesOfClass(EntityBullShark.class, this.getBoundingBox().inflate(2.5F), t -> t.isLeader());
				if(!list.isEmpty())
				{
					EntityBullShark leader = list.get(0);
					this.setLeader(leader);
					leader.setSchoolSize(leader.getSchoolSize() + 1);
				}
			}
			else
			{
				EntityBullShark leader = this.getLeader();
				if(leader.getNavigation().getPath() != null)
				{
					BlockPos pos = leader.getNavigation().getPath().getTarget();
					Path path = this.getNavigation().createPath(pos, 1);
					this.getNavigation().moveTo(path, 0.5F);
				}
				if(leader.getTarget() != null)
				{
					this.setTarget(leader.getTarget());
				}
			}
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
    public boolean canRandomSwim() 
    {
    	return super.canRandomSwim() && this.getLeader() == null;
    }
    
    @Override
    public float getHeadDistance() 
    {
    	return 0.8F;
    }
    
    public boolean isLeader()
    {
    	return this.getVariant() == 1;
    }
	
	public void setLeader(EntityBullShark leader)
	{
		this.entityData.set(LEADER_UUID, Optional.of(leader.getUUID()));
	}
	
	@Nullable
	public EntityBullShark getLeader() 
	{
		if(this.entityData.get(LEADER_UUID).isPresent()) 
		{
			return (EntityBullShark) OceanicUtil.getEntityByUUID(this.level, this.entityData.get(LEADER_UUID).get());
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
