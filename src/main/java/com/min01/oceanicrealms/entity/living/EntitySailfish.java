package com.min01.oceanicrealms.entity.living;

import java.util.List;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.IAvoid;
import com.min01.oceanicrealms.entity.ai.goal.SailfishEatingGoal;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class EntitySailfish extends AbstractOceanicCreature implements IAvoid
{
	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(EntitySailfish.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> HUNGER_COOLDOWN = SynchedEntityData.defineId(EntitySailfish.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> IS_EAT = SynchedEntityData.defineId(EntitySailfish.class, EntityDataSerializers.BOOLEAN);
	
	public final AnimationState dryAnimationState = new AnimationState();
	public final AnimationState eatingAnimationState = new AnimationState();
	
	public EntitySailfish(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_)
	{
		super(p_33002_, p_33003_);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
        		.add(Attributes.MAX_HEALTH, 40.0F)
        		.add(Attributes.MOVEMENT_SPEED, 0.5F);
    }
    
    @Override
    protected void registerGoals() 
    {
    	super.registerGoals();
    	this.goalSelector.addGoal(4, new SailfishEatingGoal(this));
    	this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, WaterAnimal.class, false, OceanicUtil.TARGET_PREDICATE2)
    	{
    		@Override
    		public boolean canUse() 
    		{
    			return super.canUse() && EntitySailfish.this.isHungry();
    		}
    	});
    }
    
    @Override
    protected void defineSynchedData()
    {
    	super.defineSynchedData();
    	this.entityData.define(VARIANT, this.random.nextInt(1, 3));
    	this.entityData.define(HUNGER_COOLDOWN, 0);
    	this.entityData.define(IS_EAT, false);
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
        			this.eatingAnimationState.start(this.tickCount);
        			break;
        		}
            }
        }
	}
	
	@Override
	public void stopAllAnimationStates() 
	{
		this.eatingAnimationState.stop();
	}
    
    @Override
    public void tick() 
    {
    	super.tick();
		if(this.level.isClientSide)
		{
			this.dryAnimationState.animateWhen(!this.isInWater(), this.tickCount);
		}
		if(!this.isUsingSkill())
		{
			float size = 0.5F;
			Vec3 lookPos = OceanicUtil.getLookPos(this.getRotationVector(), this.position(), 0.0F, 0.0F, 1.2F);
			AABB aabb = new AABB(-size, -size, -size, size, 2.0F, size).move(lookPos);
			List<ItemEntity> list = this.level.getEntitiesOfClass(ItemEntity.class, aabb, item -> 
			{
				boolean flag = item.getItem().getFoodProperties(this) != null && item.getItem().getFoodProperties(this).isMeat();
				return item.getItem().is(ItemTags.FISHES) || flag;
			});
			if(!list.isEmpty())
			{
				ItemEntity item = list.get(0);
				this.setAnimationState(1);
				this.setAnimationTick(10);
				this.playSound(SoundEvents.GENERIC_EAT);
				item.discard();
			}
		}
		if(this.getAnimationTick() <= 0)
		{
			if(this.getAnimationState() == 1)
			{
				this.setAnimationState(0);
			}
		}
		if(this.getTarget() != null)
		{
			if(this.canMove())
			{
				if(this.getFirstPassenger() != this.getTarget())
				{
					this.getNavigation().moveTo(this.getTarget(), 0.65F);
					Vec3 lookPos = OceanicUtil.getLookPos(this.getRotationVector(), this.position(), 0.0F, 0.4F, 1.5F);
					if(lookPos.distanceTo(this.getTarget().position()) <= 1.5F)
					{
						this.getTarget().startRiding(this);
					}
				}
				else
				{
					if(this.getTarget().hurt(this.damageSources().mobAttack(this), 4.0F))
					{
						if(!this.getTarget().isAlive())
						{
							this.setHungerCooldown(3600);
							this.getNavigation().stop();
						}
					}
				}
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
    protected void positionRider(Entity p_19957_, MoveFunction p_19958_) 
    {
		Vec3 lookPos = OceanicUtil.getLookPos(this.getRotationVector(), this.position(), 0.0F, 0.4F, 1.5F);
    	p_19958_.accept(p_19957_, lookPos.x, lookPos.y, lookPos.z);
    }
    
    @Override
    public void addAdditionalSaveData(CompoundTag p_21484_)
    {
    	super.addAdditionalSaveData(p_21484_);
    	p_21484_.putInt("Variant", this.getVariant());
		p_21484_.putInt("Hunger", this.getHungerCooldown());
    }
    
    @Override
    public void readAdditionalSaveData(CompoundTag p_21450_) 
    {
    	super.readAdditionalSaveData(p_21450_);
    	if(p_21450_.contains("Variant"))
    	{
    		this.setVariant(p_21450_.getInt("Variant"));
    	}
		if(p_21450_.contains("Hunger"))
		{
			this.setHungerCooldown(p_21450_.getInt("Hunger"));
		}
    }
    
	@Override
	public boolean canRandomSwim()
	{
		return super.canRandomSwim() && !this.isEat();
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
    
    public void setVariant(int value)
    {
    	this.entityData.set(VARIANT, value);
    }
    
    public int getVariant()
    {
    	return this.entityData.get(VARIANT);
    }
}
