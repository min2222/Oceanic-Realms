package com.min01.oceanicrealms.entity.ai.control;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
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
	private float targetX, targetY, targetZ;

	public OceanicSwimmingMoveControl(Mob p_148070_, int p_148071_, float p_148073_, float p_148074_, boolean p_148075_)
	{
		super(p_148070_);
		this.maxTurnX = p_148071_;
		this.inWaterSpeedModifier = p_148073_;
		this.outsideWaterSpeedModifier = p_148074_;
		this.applyGravity = p_148075_;
	}

	@Override
	public void tick() 
	{
		if(this.applyGravity && this.mob.isInWater()) 
		{
			this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
		}
		if(this.operation == MoveControl.Operation.MOVE_TO && !this.mob.getNavigation().isDone()) 
		{
			if(this.mob.tickCount % 60 == 0)
			{
				this.generateNewTarget();
			}
			double d0 = this.targetX - this.mob.getX();
			double d1 = this.targetY - this.mob.getY();
			double d2 = this.targetZ - this.mob.getZ();
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
					float f2 = getTurningSpeedFactor(f5);
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
                	this.targetX = targetPos.getX();
                	this.targetY = targetPos.getY();
                	this.targetZ = targetPos.getZ();
                	break;
                }
        	}
        }
    }
    
    public void setTargetPos(Vec3 pos)
    {
    	this.targetX = (float) pos.x;
    	this.targetY = (float) pos.y;
    	this.targetZ = (float) pos.z;
    }

	private static float getTurningSpeedFactor(float p_249853_) 
	{
		return 1.0F - Mth.clamp((p_249853_ - 10.0F) / 50.0F, 0.0F, 1.0F);
	}
}