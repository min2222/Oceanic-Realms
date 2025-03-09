package com.min01.oceanicrealms.entity.living;

import java.util.function.Predicate;

import com.min01.oceanicrealms.entity.AbstractOceanicShark;
import com.min01.oceanicrealms.entity.IBoid;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class EntityGreatWhiteShark extends AbstractOceanicShark
{	
	public final AnimationState attackAnimationState = new AnimationState();
	public final AnimationState eatingAnimationState = new AnimationState();
	
	public EntityGreatWhiteShark(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_) 
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
    protected void registerGoals() 
    {
    	super.registerGoals();
    	this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, WaterAnimal.class, false, t -> t.isInWater() && !(t instanceof AbstractOceanicShark) && !(t instanceof EntityWhaleshark))
    	{
    		@Override
    		public boolean canUse() 
    		{
    			return super.canUse() && EntityGreatWhiteShark.this.isHungry();
    		}
    	});
    	this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, false, t -> t.getHealth() < 3.0F));
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
	public int getBodyRotationSpeed() 
	{
		return 4;
	}
	
	@Override
	public <T extends Entity> Predicate<? super T> getPredicate()
	{
		return t -> !(t instanceof EntityGreatWhiteShark) && !(t instanceof IBoid<?>);
	}
}
