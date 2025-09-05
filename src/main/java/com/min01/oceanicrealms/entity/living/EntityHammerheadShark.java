package com.min01.oceanicrealms.entity.living;

import com.min01.oceanicrealms.entity.AbstractOceanicShark;
import com.min01.oceanicrealms.entity.AgeableWaterAnimal;
import com.min01.oceanicrealms.misc.SmoothAnimationState;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class EntityHammerheadShark extends AbstractOceanicShark
{
	public final SmoothAnimationState attackAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState eatingAnimationState = new SmoothAnimationState();
	
	public EntityHammerheadShark(EntityType<? extends AgeableWaterAnimal> p_33002_, Level p_33003_) 
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
    	this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, WaterAnimal.class, false, OceanicUtil.TARGET_PREDICATE2)
    	{
    		@Override
    		public boolean canUse() 
    		{
    			return super.canUse() && EntityHammerheadShark.this.isHungry();
    		}
    	});
    }
    
	@Override
	public void tick() 
	{
		super.tick();
		if(this.level.isClientSide)
		{
			this.attackAnimationState.updateWhen(this.isUsingSkill(1), this.tickCount);
			this.eatingAnimationState.updateWhen(this.isUsingSkill(2), this.tickCount);
		}
	}
    
    @Override
    public float getHeadDistance() 
    {
    	return 0.8F;
    }
}
