package com.min01.oceanicrealms.entity.living;

import com.min01.oceanicrealms.entity.AbstractOwnableOceanicCreature;
import com.min01.oceanicrealms.misc.WormChain;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;

public abstract class AbstractOarfishPart extends AbstractOwnableOceanicCreature<AbstractOarfishPart>
{
	public AbstractOarfishPart(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_)
	{
		super(p_33002_, p_33003_);
	}
	
	public boolean isHead()
	{
		return false;
	}
	
	@Override
	protected void registerGoals() 
	{
		if(this.isHead())
		{
			super.registerGoals();
		}
	}
	
	@Override
	protected void doPush(Entity p_21294_)
	{
		if(!(p_21294_ instanceof AbstractOarfishPart))
		{
			super.doPush(p_21294_);
		}
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		
		this.resetFallDistance();
		
		if(this.getOwner() != null)
		{
    		this.hurtTime = this.getOwner().hurtTime;
    		this.deathTime = this.getOwner().deathTime;
			WormChain.tick(this, this.getOwner(), this.getSegmentDistance(), 0.25F);
		}
		else if(!this.isHead())
		{
			this.discard();
		}
	}
	
	public float getSegmentDistance()
	{
		return this.getOwner().isHead() ? 1.15F : 1.35F;
	}
    
    @Override
    public boolean isInvulnerableTo(DamageSource p_20122_)
    {
    	return super.isInvulnerableTo(p_20122_) || p_20122_.is(DamageTypes.IN_WALL) || p_20122_.is(DamageTypeTags.IS_FALL);
    }
}
