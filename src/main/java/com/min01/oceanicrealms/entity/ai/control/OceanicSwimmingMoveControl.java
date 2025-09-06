package com.min01.oceanicrealms.entity.ai.control;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.IBoid;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class OceanicSwimmingMoveControl extends MoveControl 
{
	private final int maxTurnX;
	private final float inWaterSpeedModifier;
	private final float outsideWaterSpeedModifier;
	private final boolean applyGravity;
	private List<? extends Mob> nearbyMobs = new ArrayList<>();
	private Vec3 targetPos = Vec3.ZERO;
	private boolean forceTarget;

	public OceanicSwimmingMoveControl(Mob mob, int maxTurnX, float inWaterSpeedModifier, float outsideWaterSpeedModifier, boolean applyGravity)
	{
		super(mob);
		this.maxTurnX = maxTurnX;
		this.inWaterSpeedModifier = inWaterSpeedModifier;
		this.outsideWaterSpeedModifier = outsideWaterSpeedModifier;
		this.applyGravity = applyGravity;
	}

	@Override
	public void tick() 
	{
		if(this.applyGravity && this.mob.isInWater()) 
		{
			this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
		}
		if(this.mob instanceof IBoid)
		{
			this.tickBoid();
		}
		else
		{
			this.tickSwim();
		}
	}
	
	public void tickBoid()
	{
		if(this.operation == MoveControl.Operation.MOVE_TO) 
		{
	    	this.boid();
	        this.stayInWater();
			Vec3 direction = this.mob.getDeltaMovement();
			double d0 = direction.x;
			double d1 = direction.y;
			double d2 = direction.z;
			double d3 = d0 * d0 + d1 * d1 + d2 * d2;
			if(d3 < (double) 2.5000003E-7F) 
			{
				this.mob.setZza(0.0F);
			}
			else 
			{
				float f = -(float)(Mth.atan2(direction.x, direction.z) * (double)(180.0F / (float)Math.PI));
				this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f, 10.0F));
				this.mob.yBodyRot = this.mob.getYRot();
				this.mob.yHeadRot = this.mob.getYRot();
				float f1 = (float) (this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
				if(this.mob.isInWater()) 
				{
					this.mob.setSpeed(f1 * this.inWaterSpeedModifier);
					if(Math.abs(direction.y) > (double) 1.0E-5F || Math.abs(direction.horizontalDistance()) > (double) 1.0E-5F) 
					{
						float f3 = -((float) (Mth.atan2(direction.y, direction.horizontalDistance()) * (double) (180.0F / (float) Math.PI)));
						f3 = Mth.clamp(Mth.wrapDegrees(f3), (float) (-this.maxTurnX), (float) this.maxTurnX);
						this.mob.setXRot(this.rotlerp(this.mob.getXRot(), f3, 2.0F));
					}
					float f6 = Mth.cos(this.mob.getXRot() * ((float) Math.PI / 180.0F));
					float f4 = Mth.sin(this.mob.getXRot() * ((float) Math.PI / 180.0F));
					this.mob.zza = f6 * f1;
					this.mob.yya = -f4 * f1;
				}
				else
				{
					float f5 = Math.abs(Mth.wrapDegrees(this.mob.getYRot() - f));
					float f2 = this.getTurningSpeedFactor(f5);
					this.mob.setSpeed(f1 * this.outsideWaterSpeedModifier * f2);
				}
			}
		} 
		else 
		{
			this.mob.setSpeed(0.0F);
			this.mob.setXxa(0.0F);
			this.mob.setYya(0.0F);
			this.mob.setZza(0.0F);
		}
	}
	
	public void tickSwim()
	{
		if(this.operation == MoveControl.Operation.MOVE_TO && !this.mob.getNavigation().isDone()) 
		{
			if(this.mob.tickCount % 60 == 0)
			{
				this.generateNewTarget();
			}
			double d0 = this.targetPos.x - this.mob.getX();
			double d1 = this.targetPos.y - this.mob.getY();
			double d2 = this.targetPos.z - this.mob.getZ();
			double d3 = d0 * d0 + d1 * d1 + d2 * d2;
			if(d3 < (double) 2.5000003E-7F) 
			{
				this.mob.setZza(0.0F);
			}
			else 
			{
				float f = (float) (Mth.atan2(d2, d0) * (double) (180.0F / (float) Math.PI)) - 90.0F;
				this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f, 2.0F));
				this.mob.yBodyRot = this.mob.getYRot();
				this.mob.yHeadRot = this.mob.getYRot();
				float f1 = (float) (this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
				if(this.mob.isInWater()) 
				{
					this.mob.setSpeed(f1 * this.inWaterSpeedModifier);
					double d4 = Math.sqrt(d0 * d0 + d2 * d2);
					if(Math.abs(d1) > (double) 1.0E-5F || Math.abs(d4) > (double) 1.0E-5F) 
					{
						float f3 = -((float) (Mth.atan2(d1, d4) * (double) (180.0F / (float) Math.PI)));
						f3 = Mth.clamp(Mth.wrapDegrees(f3), (float) (-this.maxTurnX), (float) this.maxTurnX);
						this.mob.setXRot(this.rotlerp(this.mob.getXRot(), f3, 2.0F));
					}
					float f6 = Mth.cos(this.mob.getXRot() * ((float) Math.PI / 180.0F));
					float f4 = Mth.sin(this.mob.getXRot() * ((float) Math.PI / 180.0F));
					this.mob.zza = f6 * f1;
					this.mob.yya = -f4 * f1;
				}
				else
				{
					float f5 = Math.abs(Mth.wrapDegrees(this.mob.getYRot() - f));
					float f2 = this.getTurningSpeedFactor(f5);
					this.mob.setSpeed(f1 * this.outsideWaterSpeedModifier * f2);
				}
			}
		} 
		else 
		{
			this.mob.setSpeed(0.0F);
			this.mob.setXxa(0.0F);
			this.mob.setYya(0.0F);
			this.mob.setZza(0.0F);
		}
	}
    
    private void generateNewTarget() 
    {
        Level world = this.mob.level;
        AbstractOceanicCreature fish = (AbstractOceanicCreature) this.mob;
        for(int i = 0; i < 10; i++)
        {
        	Vec3 pos = OceanicUtil.getSpreadPosition(this.mob, fish.getSwimRadius());
        	HitResult hitResult = this.mob.level.clip(new ClipContext(this.mob.position(), pos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this.mob));
        	if(hitResult instanceof BlockHitResult blockHit)
        	{
                BlockPos targetPos = blockHit.getBlockPos();
                BlockState blockState = world.getBlockState(targetPos);
                if(blockState.is(Blocks.WATER))
                {
                	this.targetPos = blockHit.getLocation();
                	break;
                }
        	}
        }
    }

	private float getTurningSpeedFactor(float p_249853_) 
	{
		return 1.0F - Mth.clamp((p_249853_ - 10.0F) / 50.0F, 0.0F, 1.0F);
	}
    
    public boolean canBoid()
    {
        if(this.mob.tickCount % 40 == 0 || this.mob.tickCount <= 10)
        {
            this.nearbyMobs = this.getNearbyEntitiesOfSameClass(this.mob);
        }
        else
        {
        	this.nearbyMobs.removeIf(LivingEntity::isDeadOrDying);
        }
        return !this.nearbyMobs.isEmpty();
    }
    
    public void boid()
    {
    	if(this.canBoid())
    	{
            this.nearbyMobs.sort(Comparator.comparing(Entity::getUUID));
            this.mob.addDeltaMovement(this.cohesion());
            this.mob.addDeltaMovement(this.alignment());
            this.mob.addDeltaMovement(this.separation());
            this.lookAt();
        	if(this.mob.getUUID().equals(this.nearbyMobs.get(0).getUUID()) && !this.forceTarget)
        	{
        		if(this.mob.tickCount % 200 == 0 || this.targetPos.equals(Vec3.ZERO) || this.targetPos.subtract(this.mob.position()).length() <= 6.0F)
        		{
        			this.generateNewTarget();
        		}
        	}
    	}
    	else
    	{
    		this.generateNewTarget();
    	}
    }
    
    public void lookAt()
    {
        Vec3 velocity = this.mob.getDeltaMovement();
        double speed = velocity.length();
        float maxSpeed = 0.3F;
        if(speed > maxSpeed)
        {
            velocity = velocity.normalize().scale(maxSpeed);
        }
        this.mob.setDeltaMovement(velocity);
    }
    
    public void stayInWater()
    {
		if(this.mob.isInWater())
		{
	        BlockPos blockPos = this.mob.blockPosition();
	        BlockState blockAbove = this.mob.level.getBlockState(blockPos.above(2));
	        BlockState blockBelow = this.mob.level.getBlockState(blockPos.below(1));
	        float amount = this.amount();
	        if(blockBelow.getFluidState().isEmpty()) 
	        {
	        	this.mob.addDeltaMovement(new Vec3(0, amount, 0));
	        }
	        if(blockAbove.getFluidState().isEmpty())
	        {
	        	this.mob.addDeltaMovement(new Vec3(0, -amount, 0));
	        }
		}
    }
    
    public float amount() 
    {
        float amount = 0.1F;
        float dY = Mth.abs((float) this.mob.getDeltaMovement().y);
        if(dY > amount) 
        {
            amount = dY;
        }
        return amount;
    }
    
    public Vec3 separation() 
    {
        Vec3 c = Vec3.ZERO;
        for(Mob nearbyMob : this.nearbyMobs)
        {
            if((nearbyMob.position().subtract(this.mob.position()).length()) < ((IBoid) this.mob).separationRange())
            {
            	c = c.subtract(nearbyMob.position().subtract(this.mob.position()));
            }
        }
        return c.scale(0.05F);
    }
    
    public Vec3 cohesion()
	{
        if(this.nearbyMobs.isEmpty()) 
        {
        	return Vec3.ZERO;
        }
        Vec3 c = Vec3.ZERO;
        for(Mob nearbyMob : this.nearbyMobs)
        {
            c = c.add(nearbyMob.position());
        }
        c = this.setTargetPos(c, this.nearbyMobs.get(0));
        c = c.scale(1.0F / this.nearbyMobs.size());
        c = c.subtract(this.mob.position());
        return c.scale(0.05F);
	}
    
	public Vec3 alignment()
	{
        if(this.nearbyMobs.isEmpty()) 
        {
        	return Vec3.ZERO;
        }
        Vec3 c = Vec3.ZERO;
        for(Mob nearbyMob : this.nearbyMobs)
        {
            c = c.add(nearbyMob.getDeltaMovement());
        }
        c = this.setTargetPos(c, this.nearbyMobs.get(0));
        c = c.scale(1.0F / this.nearbyMobs.size());
        c = c.subtract(this.mob.getDeltaMovement());
        return c.scale(0.05F);
	}
	
	public Vec3 setTargetPos(Vec3 vec3, Mob mob)
	{
		Vec3 targetPos = ((OceanicSwimmingMoveControl) mob.getMoveControl()).getTargetPos();
		if(!targetPos.equals(Vec3.ZERO))
		{
			float scale = this.forceTarget ? 5.0F : 0.05F;
	        return vec3.add(targetPos.subtract(mob.position()).scale(scale));
		}
		return vec3;
	}

    public List<? extends Mob> getNearbyEntitiesOfSameClass(Mob mob)
    {
        return mob.level.getEntitiesOfClass(mob.getClass(), mob.getBoundingBox().inflate(4.0F), t -> t != mob && !t.isDeadOrDying());
    }
	
    public void setTargetPos(Vec3 pos)
    {
    	this.targetPos = pos;
    }
    
    public void setForceTarget(Vec3 pos)
    {
    	this.targetPos = pos;
    	this.forceTarget = true;
    }
    
    public void setForceTarget(boolean forceTarget)
    {
    	this.forceTarget = forceTarget;
    	if(!forceTarget)
    	{
    		this.generateNewTarget();
    	}
    }
    
    public Vec3 getTargetPos()
    {
    	return this.targetPos;
    }
}