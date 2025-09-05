package com.min01.oceanicrealms.entity.living;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.AgeableWaterAnimal;
import com.min01.oceanicrealms.entity.IBoid;
import com.min01.oceanicrealms.entity.OceanicEntities;
import com.min01.oceanicrealms.entity.ai.goal.BoidGoal;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public class EntityDolphinfish extends AbstractOceanicCreature implements IBoid
{
	public final AnimationState dryAnimationState = new AnimationState();
	
	public EntityDolphinfish(EntityType<? extends AgeableWaterAnimal> p_33002_, Level p_33003_)
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
        //TODO proper navigation, aka disable boid;
        this.goalSelector.addGoal(5, new BoidGoal(this, 0.1F, 2.5F));
        this.goalSelector.addGoal(5, new BreedGoal(this, 1.0F));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(ItemTags.FISHES), false));
    }
    
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_)
    {
    	return OceanicEntities.DOLPHINFISH.get().create(p_146743_);
    }
    
    @Override
    public boolean isFood(ItemStack p_27600_) 
    {
    	return p_27600_.is(ItemTags.FISHES);
    }
    
    @Override
    public EntityDimensions getDimensions(Pose p_21047_) 
    {
    	return this.isBaby() ? EntityDimensions.scalable(0.6875F, 0.3125F) : super.getDimensions(p_21047_);
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
    
    @Override
    protected Component getTypeName()
    {
    	return this.isBaby() ? Component.translatable("entity.oceanicrealms.baby_dolphinfish") : super.getTypeName();
    }
}
