package com.min01.oceanicrealms.entity.living;

import java.util.List;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.IAvoid;
import com.min01.oceanicrealms.entity.IBoid;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EntityLionfish extends AbstractOceanicCreature implements IAvoid
{
	public EntityLionfish(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_)
	{
		super(p_33002_, p_33003_);
	}

    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
        		.add(Attributes.MAX_HEALTH, 15.0F)
        		.add(Attributes.MOVEMENT_SPEED, 0.35F);
    }
    
    @Override
    protected void doPush(Entity p_20971_) 
    {
    	if(p_20971_ instanceof LivingEntity living)
    	{
    		living.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0));
    	}
    	super.doPush(p_20971_);
    }
    
    @Override
    public void tick() 
    {
    	super.tick();
		List<WaterAnimal> list = this.level.getEntitiesOfClass(WaterAnimal.class, this.getBoundingBox().inflate(5.0F), t -> !(t instanceof IBoid<?>));
		list.forEach(t -> 
		{
			if(this.tickCount % 20 == 0)
			{
		        Vec3 vec3 = DefaultRandomPos.getPosAway(t, 16, 7, this.position());
		        if(vec3 != null)
		        {
		            t.getNavigation().moveTo(vec3.x, vec3.y, vec3.z, 0.5F);
		        }
			}
		});
    }
}
