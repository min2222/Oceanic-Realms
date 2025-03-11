package com.min01.oceanicrealms.entity.living;

import com.min01.oceanicrealms.entity.AbstractOceanicShark;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class EntityPorbeagleShark extends AbstractOceanicShark
{	
	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(EntityPorbeagleShark.class, EntityDataSerializers.INT);
	
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
        		.add(Attributes.MOVEMENT_SPEED, 0.45F)
        		.add(Attributes.ARMOR, 2.0F)
        		.add(Attributes.ATTACK_DAMAGE, 4.0F);
    }
    
    @Override
    protected void registerGoals() 
    {
    	super.registerGoals();
    	this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, WaterAnimal.class, false, OceanicUtil.TARGET_PREDICATE2)
    	{
    		@Override
    		public boolean canUse() 
    		{
    			return super.canUse() && EntityPorbeagleShark.this.isHungry();
    		}
    	});
    	this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, false, t -> t.getHealth() < 5.0F));
    }
    
    @Override
    protected void defineSynchedData()
    {
    	super.defineSynchedData();
    	this.entityData.define(VARIANT, this.random.nextInt(1, 3));
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
    public void addAdditionalSaveData(CompoundTag p_21484_)
    {
    	super.addAdditionalSaveData(p_21484_);
    	p_21484_.putInt("Variant", this.getVariant());
    }
    
    @Override
    public void readAdditionalSaveData(CompoundTag p_21450_) 
    {
    	super.readAdditionalSaveData(p_21450_);
    	if(p_21450_.contains("Variant"))
    	{
    		this.setVariant(p_21450_.getInt("Variant"));
    	}
    }
    
    @Override
    public float getHeadDistance() 
    {
    	return 0.8F;
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
