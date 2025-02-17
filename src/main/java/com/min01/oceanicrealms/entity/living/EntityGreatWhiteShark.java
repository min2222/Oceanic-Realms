package com.min01.oceanicrealms.entity.living;

import java.util.List;

import com.min01.oceanicrealms.entity.AbstractAnimatableCreature;
import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.ai.goal.GreatWhiteSharkAttackGoal;
import com.min01.oceanicrealms.entity.ai.goal.GreatWhiteSharkEatingItemGoal;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class EntityGreatWhiteShark extends AbstractOceanicCreature
{
	public static final EntityDataAccessor<Integer> HUNGER_COOLDOWN = SynchedEntityData.defineId(AbstractAnimatableCreature.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> IS_EAT = SynchedEntityData.defineId(AbstractAnimatableCreature.class, EntityDataSerializers.BOOLEAN);
	
	public final AnimationState attackAnimationState = new AnimationState();
	public final AnimationState eatingAnimationState = new AnimationState();
	
	public EntityGreatWhiteShark(EntityType<? extends PathfinderMob> p_33002_, Level p_33003_) 
	{
		super(p_33002_, p_33003_);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
        		.add(Attributes.MAX_HEALTH, 100.0F)
        		.add(Attributes.MOVEMENT_SPEED, 0.5F)
        		.add(Attributes.KNOCKBACK_RESISTANCE, 1.0F)
        		.add(Attributes.ATTACK_DAMAGE, 15.0F);
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
    	this.goalSelector.addGoal(4, new GreatWhiteSharkEatingItemGoal(this));
    	this.goalSelector.addGoal(4, new GreatWhiteSharkAttackGoal(this));
    	this.targetSelector.addGoal(4, new HurtByTargetGoal(this));
    	this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, WaterAnimal.class, false, t -> t.isInWater())
    	{
    		@Override
    		public boolean canUse() 
    		{
    			return super.canUse() && EntityGreatWhiteShark.this.isHungry();
    		}
    	});
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
		
		if(!this.isUsingSkill())
		{
			Vec3 lookPos = OceanicUtil.getLookPos(this.getRotationVector(), this.position(), 0.0F, 0.0F, 2.0F);
			AABB aabb = new AABB(-0.5F, -0.5F, -0.5F, 0.5F, 1.5F, 0.5F).move(lookPos);
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
				this.getNavigation().moveTo(this.getTarget(), 0.45F);
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
	public boolean canRandomSwim()
	{
		return super.canRandomSwim() && !this.isEat();
	}
	
	@Override
	public int getBodyRotationSpeed() 
	{
		return 4;
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
