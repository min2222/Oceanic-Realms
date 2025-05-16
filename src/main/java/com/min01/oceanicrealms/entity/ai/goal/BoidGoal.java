package com.min01.oceanicrealms.entity.ai.goal;

import java.util.List;
import java.util.function.Predicate;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

//https://github.com/Tomate0613/boids/blob/1.21/src/main/java/dev/doublekekse/boids/goals/BoidGoal.java
public class BoidGoal extends Goal 
{
    public final float separationInfluence;
    public final float separationRange;
    public final float alignmentInfluence;
    public final float cohesionInfluence;
    private final Mob mob;
    private int timeToFindNearbyEntities;
    private List<? extends Mob> nearbyMobs;
    private boolean enabled = true;

    public BoidGoal(Mob mob, float separationInfluence, float separationRange, float alignmentInfluence, float cohesionInfluence)
    {
        this.timeToFindNearbyEntities = 0;
        this.mob = mob;
        this.separationInfluence = separationInfluence;
        this.separationRange = separationRange;
        this.alignmentInfluence = alignmentInfluence;
        this.cohesionInfluence = cohesionInfluence;
    }

    @Override
    public boolean canUse() 
    {
        return true;
    }

    @Override
    public void tick()
    {
        if(!this.enabled)
        {
            return;
        }
        if(--this.timeToFindNearbyEntities <= 0)
        {
            this.timeToFindNearbyEntities = this.adjustedTickDelay(40);
            this.nearbyMobs = getNearbyEntitiesOfSameClass(this.mob);
        } 
        else
        {
        	this.nearbyMobs.removeIf(LivingEntity::isDeadOrDying);
        }
        if(this.nearbyMobs.isEmpty()) 
        {
            this.enabled = false;
        }
        this.mob.addDeltaMovement(this.random());
        this.mob.addDeltaMovement(this.cohesion());
        this.mob.addDeltaMovement(this.alignment());
        this.mob.addDeltaMovement(this.separation());
    }

    public static List<? extends Mob> getNearbyEntitiesOfSameClass(Mob mob)
    {
        Predicate<Mob> predicate = (_mob) -> true;
        return mob.level.getEntitiesOfClass(mob.getClass(), mob.getBoundingBox().inflate(4.0F, 4.0F, 4.0F), predicate);
    }

    public Vec3 random() 
    {
        Vec3 velocity = this.mob.getDeltaMovement();
        if(Mth.abs((float) velocity.x) < 0.1 && Mth.abs((float) velocity.z) < 0.1)
        {
            return new Vec3(this.randomSign() * 0.2, 0, this.randomSign() * 0.2);
        }
        return Vec3.ZERO;
    }

    public int randomSign() 
    {
        boolean isNegative = this.mob.getRandom().nextBoolean();

        if(isNegative) 
        {
            return -1;
        }
        return 1;
    }

    public Vec3 separation() 
    {
        Vec3 c = Vec3.ZERO;
        for(Mob nearbyMob : this.nearbyMobs)
        {
            if((nearbyMob.position().subtract(this.mob.position()).length()) < this.separationRange)
            {
            	c = c.subtract(nearbyMob.position().subtract(this.mob.position()));
            }
        }
        return c.scale(this.separationInfluence);
    }

    public Vec3 alignment() 
    {
        Vec3 c = Vec3.ZERO;
        for(Mob nearbyMob : this.nearbyMobs)
        {
            c = c.add(nearbyMob.getDeltaMovement());
        }
        c = c.scale(1.0F / this.nearbyMobs.size());
        c = c.subtract(this.mob.getDeltaMovement());
        return c.scale(this.alignmentInfluence);
    }

    public Vec3 cohesion() 
    {
        Vec3 c = Vec3.ZERO;
        for(Mob nearbyMob : this.nearbyMobs)
        {
            c = c.add(nearbyMob.position());
        }
        c = c.scale(1.0F / this.nearbyMobs.size());
        c = c.subtract(this.mob.position());
        return c.scale(this.cohesionInfluence);
    }
}
