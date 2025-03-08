package com.min01.oceanicrealms.entity.living;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.misc.WormChain;
import com.min01.oceanicrealms.misc.WormChain.Worm;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class EntityWhaleshark extends AbstractOceanicCreature
{
	public final Worm wormFront = new Worm();
	public final Worm wormBack = new Worm();
	
	public EntityWhaleshark(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_)
	{
		super(p_33002_, p_33003_);
	}

    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
        		.add(Attributes.MAX_HEALTH, 200.0F)
        		.add(Attributes.MOVEMENT_SPEED, 0.45F);
    }
    
    @Override
    public void tick()
    {
    	super.tick();
    	WormChain.tick(this.wormFront, this, 0.01F, 0.5F);
    	WormChain.tick(this.wormBack, this.wormFront, 2.8F, 0.5F);
    }
}
