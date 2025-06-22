package com.min01.oceanicrealms.entity;

import java.util.List;

import com.min01.oceanicrealms.entity.ai.goal.SharkAttackGoal;
import com.min01.oceanicrealms.entity.ai.goal.SharkEatingGoal;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractOceanicShark extends AbstractOceanicCreature implements IAvoid
{
	public static final EntityDataAccessor<Integer> HUNGER_COOLDOWN = SynchedEntityData.defineId(AbstractOceanicShark.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> IS_EAT = SynchedEntityData.defineId(AbstractOceanicShark.class, EntityDataSerializers.BOOLEAN);
	
	public AbstractOceanicShark(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_) 
	{
		super(p_33002_, p_33003_);
	}
    
    @Override
    protected void defineSynchedData()
    {
    	super.defineSynchedData();
    	this.entityData.define(HUNGER_COOLDOWN, 0);
    	this.entityData.define(IS_EAT, false);
    }
    
    @Override
    protected void registerGoals() 
    {
    	super.registerGoals();
    	this.goalSelector.addGoal(4, new SharkEatingGoal(this));
    	this.goalSelector.addGoal(4, new SharkAttackGoal(this));
    	this.targetSelector.addGoal(4, new HurtByTargetGoal(this));
    }
    
    @Override
    public void tick() 
    {
    	super.tick();
    	
		if(!this.isUsingSkill())
		{
			float size = this.getHeadSize();
			Vec3 lookPos = OceanicUtil.getLookPos(this.getRotationVector(), this.position(), 0.0F, 0.0F, this.getHeadDistance());
			AABB aabb = new AABB(-size, -size, -size, size, this.getYHeadSize(), size).move(lookPos);
			List<ItemEntity> list = this.level.getEntitiesOfClass(ItemEntity.class, aabb, item -> 
			{
				boolean flag = item.getItem().getFoodProperties(this) != null && item.getItem().getFoodProperties(this).isMeat();
				return item.getItem().is(ItemTags.FISHES) || flag;
			});
			if(!list.isEmpty())
			{
				ItemEntity item = list.get(0);
				this.setAnimationState(2);
				this.setAnimationTick(20);
				this.playSound(SoundEvents.PLAYER_BURP);
				item.discard();
			}
		}
		
		if(this.getAnimationTick() <= 0)
		{
			if(this.getAnimationState() == 2)
			{
				this.setAnimationState(0);
			}
		}
		
		if(this.getTarget() != null)
		{
			if(this.canMove())
			{
				this.getNavigation().moveTo(this.getTarget(), this.getAttributeBaseValue(Attributes.MOVEMENT_SPEED));
			}
			if(this.canLook())
			{
				this.lookAt(Anchor.EYES, this.getTarget().getEyePosition());
			}
		}
		
		if(!this.isHungry())
		{
			this.setHungerCooldown(this.getHungerCooldown() - 1);
		}
    }
	
	@Override
	public int getBodyRotationSpeed() 
	{
		return 6;
	}
	
	@Override
	public boolean canRandomSwim()
	{
		return super.canRandomSwim() && !this.isEat();
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag p_21484_) 
	{
		super.addAdditionalSaveData(p_21484_);
		p_21484_.putInt("Hunger", this.getHungerCooldown());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag p_21450_) 
	{
		super.readAdditionalSaveData(p_21450_);
		if(p_21450_.contains("Hunger"))
		{
			this.setHungerCooldown(p_21450_.getInt("Hunger"));
		}
	}
	
	public float getYHeadSize()
	{
		return 1.5F;
	}
	
	public float getHeadSize()
	{
		return 0.5F;
	}
	
	public float getHeadDistance()
	{
		return 2.0F;
	}
	
	public boolean isHungry()
	{
		return this.getHungerCooldown() <= 0;
	}
	
	public void setEat(boolean value)
	{
		this.entityData.set(IS_EAT, value);
	}
	
	public boolean isEat()
	{
		return this.entityData.get(IS_EAT);
	}
	
	public void setHungerCooldown(int value)
	{
		this.entityData.set(HUNGER_COOLDOWN, value);
	}
	
	public int getHungerCooldown()
	{
		return this.entityData.get(HUNGER_COOLDOWN);
	}
}
