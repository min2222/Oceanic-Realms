package com.min01.oceanicrealms.entity.living;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.ai.goal.BoidGoal;
import com.min01.oceanicrealms.entity.ai.goal.LimitSpeedAndLookInVelocityDirectionGoal;
import com.min01.oceanicrealms.entity.ai.goal.StayInWaterGoal;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class EntityDolphinfish extends AbstractOceanicCreature
{
	public final AnimationState dryAnimationState = new AnimationState();
	
	public EntityDolphinfish(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_)
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
    protected void registerGoals() 
    {
    	super.registerGoals();
        this.goalSelector.addGoal(5, new BoidGoal(this, 0.1F, 2.5F, 8 / 20.0F, 1 / 20.0F));
        this.goalSelector.addGoal(3, new StayInWaterGoal(this));
        this.goalSelector.addGoal(2, new LimitSpeedAndLookInVelocityDirectionGoal(this, 0.3F, 0.8F));
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
	}
}
