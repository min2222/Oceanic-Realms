package com.min01.oceanicrealms.entity.living;

import com.min01.oceanicrealms.entity.AgeableWaterAnimal;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class EntityOarfishBody extends AbstractOarfishPart
{
	public EntityOarfishBody(EntityType<? extends AgeableWaterAnimal> p_33002_, Level p_33003_) 
	{
		super(p_33002_, p_33003_);
	}

    @Override
    public boolean hurt(DamageSource p_21016_, float p_21017_) 
    {
    	if(!this.isInvulnerableTo(p_21016_) && this.getOwner() != null)
    	{
    		this.getOwner().hurt(p_21016_, p_21017_);
    	}
    	return false;
    }
}
