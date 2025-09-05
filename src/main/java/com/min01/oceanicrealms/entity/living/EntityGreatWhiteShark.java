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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class EntityGreatWhiteShark extends AbstractOceanicShark
{	
	public final SmoothAnimationState attackAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState eatingAnimationState = new SmoothAnimationState();
	
	public EntityGreatWhiteShark(EntityType<? extends AgeableWaterAnimal> p_33002_, Level p_33003_) 
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
    	this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, WaterAnimal.class, false, OceanicUtil.TARGET_PREDICATE)
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
	public void tick() 
	{
		super.tick();
		if(this.level.isClientSide)
		{
			this.attackAnimationState.updateWhen(this.isUsingSkill(1), this.tickCount);
			this.eatingAnimationState.updateWhen(this.isUsingSkill(2), this.tickCount);
		}
	}
}
